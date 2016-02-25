/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.core.impl.sim;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.query.Query;
import org.xwiki.query.QueryException;
import org.xwiki.query.QueryManager;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.impl.sim.XwikiBridgeInterfaceRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.sim.BridgeInterface;
import eu.learnpad.sim.Controller;
import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SimulationEndEvent;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskFailedEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;

/*
 * It is not clear yet who is responsible for the instantiation
 * of this class. From what I read from Jean it is supposed to be done
 * automatically when registering the Java component into the XWiki Platform.
 * Thus I do not know how may instances we may actually have. Thus, for the
 * moment I marked it as Singleton.
 *  
 */
@Component
@Singleton
@Named("eu.learnpad.core.impl.sim.XwikiController")
@Path("/learnpad/sim/corefacade")
public class XwikiController extends Controller implements XWikiRestComponent, Initializable {
	
	@Inject
	private ComponentManager componentManager;
	
    /** Set to true once the inherited BridgeInterface has been initialized. */
    private boolean initialized = false;	
    
/*
 * Note that in this solution the Controllers do not interact
 * each-others, but each controller directly invokes the BridgesInterfaces
 * (from the other controllers) it needs. This is not actually what was
 * originally planned, thus in the future it may change.
 *
 * Also, not sure if this is the correct way to proceed.
 * I would like to decide in a configuration file
 * the implementation to bind, and not into the source
 * code. In fact, this second case implies to rebuild the
 * whole platform at each change.	
 */

    private eu.learnpad.cw.BridgeInterface cw;
    private eu.learnpad.or.BridgeInterface or;
// this other attribute may be useful in case we will implement the dashboard
//    private eu.learnpad.db.BridgeInterface db;    
    
    public synchronized void updateBridgeInterface (BridgeInterface bi){
		this.bridge = bi;    
    }

	 /** A means of instantiating the inherited BridgeInterface according
	  * to XWIKI (see  http://extensions.xwiki.org/xwiki/bin/view/Extension/Component+Module#HComponentInitialization).
	  * Actually in this implementation we currently support only 
	  * the class XwikiBridgeInterfaceRestResource, but other classes (such as XwikiBridgeInterface)
	  * should be supported in the future
	  * 
	  * Not sure if we can consider the default constructor.*/	
	@Override
	public synchronized void initialize() throws InitializationException {
		if (!this.initialized){
			this.bridge = new XwikiBridgeInterfaceRestResource();

			this.cw = new eu.learnpad.core.impl.cw.XwikiBridgeInterfaceRestResource();
			this.or = new eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource();

			this.initialized=true;
		}
	}
    
	@Override
	public List<String> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData getUserData(String userartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receiveProcessStartEvent(ProcessStartEvent event) throws LpRestException{
		String modelId = event.processid;
		String action = "started";		
		String modelSetId = event.modelsetid;		
		String simulationId = event.simulationsessionid;
		Map<String, Object> simSessionData = event.simulationSessionData;
		
		this.or.simulationInstanceNotification(modelSetId, modelId, action, simulationId, simSessionData);				

		this.handleRecommendations(modelSetId, modelId, event.involvedusers, simulationId);
	}

	@Override
	public void receiveProcessEndEvent(ProcessEndEvent event)  throws LpRestException{
		String modelId = event.processid;
		String action = "stopped";		
		String modelSetId = event.modelsetid;		
		String simulationId = event.simulationsessionid;
		Map<String, Object> simSessionData = event.simulationSessionData;
		
		this.or.simulationInstanceNotification(modelSetId, modelId, action, simulationId, simSessionData);				

// Not sure if it is always the case to notify the CW, as this notification ends the simulation		
// and probably the Recommendations are not needed anymore!
		this.handleRecommendations(modelSetId, modelId, event.involvedusers, simulationId);
	}

	@Override
	public void receiveTaskStartEvent(TaskStartEvent event) throws LpRestException {
		String modelSetId = event.modelsetid;
		String artifactId = event.taskdefid; 
		String simulationId = event.simulationsessionid;

// It seems not useful for the moment, this notification
//		this.or.simulationTaskStartNotification(modelSetId, modelId, artifactId, simulationId, simSessionData);
		
		this.handleRecommendations(modelSetId, artifactId, event.involvedusers, simulationId);
	}

	@Override
	public void receiveTaskEndEvent(TaskEndEvent event) throws LpRestException {
		String modelId = event.processid;
		String artifactId = event.taskdefid;
		String modelSetId = event.modelsetid;
		Map<String, Object> dataFilledByLearner = event.submittedData; 
		Map<String, Object> simulationSessionData = event.simulationSessionData; 
		
		
		String simulationId = event.simulationsessionid;
		this.or.simulationTaskEndNotification(modelSetId, modelId, artifactId, simulationId, simulationSessionData, dataFilledByLearner);

// Not sure if it is always the case to notify the CW, as this notification should
// happen just suddenly because of either "receiveTaskStartEvent" or "receiveProcessEndEvent" 			
		this.handleRecommendations(modelSetId, artifactId, event.involvedusers, simulationId);
	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receiveSimulationStartEvent(SimulationStartEvent event) {
		// TODO probably we do not want do anything here. The actual interaction
		// between the OR and the SIM starts when receiveProcessStartEvent will be
		// received
	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndEvent event) {
		// TODO probably we do not want do anything here. The actual interaction
		// between the OR and the SIM ends when receiveProcessEndEvent will be
		// received
	}

	@Override
	public void receiveTaskFailedEvent(TaskFailedEvent event) {
		// TODO Auto-generated method stub
	}

	private String converUserID (String simUserId) throws LpRestException {
//		http://<server>/xwiki/rest/wikis/query?q=object:XWiki.XWikiUsers		
//		
//		try {
//			QueryManager queryManager = (QueryManager) componentManager.getInstance(QueryManager.class);
//			String xwqlstatement = "doc.fullName from Document doc, doc.object(XWiki.XWikiUsers) as user where user.email;
//			Query query = queryManager.createQuery(xwqlstatement,Query.HQL);
//			List<Object> results = query.execute();
//
//			  when(
//				        mockQueryManager
//				            .createQuery(
//				                "select doc.fullName from Document doc, doc.object(XWiki.XWikiUsers) as user where LOWER(user.email) like :pattern",
//				                Query.XWQL)).thenReturn(mockQuery);
//				    when(mockQuery.bindValue("pattern", "auser@host.org")).thenReturn(mockQuery);
//				    when(mockQuery.execute()).thenReturn(queryResult);
//
//				    IMAUser maUser = mailutils.parseUser("A User <auser@host.org>", false);
//				    assertEquals("auser@host.org", maUser.getAddress());
//				    assertEquals("A User", maUser.getDisplayName());
//				    assertEquals("A User <auser@host.org>", maUser.getOriginalAddress());
//				    assertEquals("toto", maUser.getWikiProfile());		
//		
//		} catch (ComponentLookupException | QueryException e) {
//			throw new LpRestExceptionXWikiImpl(e.getMessage(),e.getCause());
//		}
//		
		
		// TODO Convert from Sim UserID to OR UserID by implementing something in the utils
		throw new LpRestExceptionXWikiImpl("the metohod eu.learnpad.core.impl.sim.XwikiController.converUserID has not been implemented yet!");
	}
	
	private void handleRecommendations(String modelSetId, String artifactId, List<String> involvedUsers, String simulationId) throws LpRestException {
		for (String simUserId : involvedUsers){
			String userId = this.converUserID(simUserId);

			Recommendations rec = this.or.askRecommendation(modelSetId, artifactId, userId, simulationId); 		
			this.cw.notifyRecommendations(simulationId, rec, simUserId);		
		}
		
	}
}
