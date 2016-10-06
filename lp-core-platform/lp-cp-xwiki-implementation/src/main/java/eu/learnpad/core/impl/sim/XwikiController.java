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

import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rendering.listener.chaining.BlockStateChainingListener.Event;
import org.xwiki.rest.XWikiRestComponent;

import com.sun.star.xml.dom.events.EventType;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.Utils;
import eu.learnpad.cw.rest.data.ScoreRecord;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.SimulationData;
import eu.learnpad.or.rest.data.SimulationScoresMap;
import eu.learnpad.sim.Controller;
import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.sim.rest.event.ScoreType;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.ScoreUpdateEvent;
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
	@Named("xwiki")
	private Utils utils;

	@Inject
	private ComponentManager componentManager;

	/*
	 * Note that in this solution the Controllers do not interact each-others,
	 * but each controller directly invokes the BridgesInterfaces (from the
	 * other controllers) it needs. This is not actually what was originally
	 * planned, thus in the future it may change. Also, not sure if this is the
	 * correct way to proceed. I would like to decide in a configuration file
	 * the implementation to bind, and not into the source code. In fact, this
	 * second case implies to rebuild the whole platform at each change.
	 */
	private eu.learnpad.cw.BridgeInterface cw;

	private eu.learnpad.or.BridgeInterface or;

	@Override
	public void initialize() throws InitializationException {
		try {
			this.bridge = this.componentManager.getInstance(RestResource.class, "sim");

			this.cw = this.componentManager.getInstance(RestResource.class, "cw");
			this.or = this.componentManager.getInstance(RestResource.class, "or");
		} catch (ComponentLookupException e) {
			throw new InitializationException(e.getMessage(), e);
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
	public void notifyProcessStartEvent(ProcessStartEvent event) throws LpRestException {
		String modelId = event.processartifactid;
		String action = "started";
		String modelSetId = event.modelsetid;
		String simulationId = event.simulationsessionid;

		SimulationData data = new SimulationData();
		data.setSessionData(event.simulationSessionData);

		this.or.simulationInstanceNotification(modelSetId, modelId, action, simulationId, data);

		this.handleRecommendations(modelSetId, modelId, event.involvedusers, simulationId);
	}

	@Override
	public void notifyProcessEndEvent(ProcessEndEvent event) throws LpRestException {
		String modelId = event.processartifactid;
		String action = "stopped";
		String modelSetId = event.modelsetid;
		String simulationId = event.simulationsessionid;

		SimulationData data = new SimulationData();
		data.setSessionData(event.simulationSessionData);

		this.or.simulationInstanceNotification(modelSetId, modelId, action, simulationId, data);

		// Not sure if it is always the case to notify the CW, as this
		// notification ends the simulation
		// and probably the Recommendations are not needed anymore!
		this.deleteRecommendations(modelSetId, modelId, event.involvedusers, simulationId);
	}

	@Override
	public void notifyTaskStartEvent(TaskStartEvent event) throws LpRestException {
		String modelSetId = event.modelsetid;
		String artifactId = event.taskartifactid;
		String simulationId = event.simulationsessionid;

		// It seems not useful for the moment, this notification
		// this.or.simulationTaskStartNotification(modelSetId, modelId,
		// artifactId, simulationId, simSessionData);

		this.handleRecommendations(modelSetId, artifactId, event.involvedusers, simulationId);
	}

	@Override
	public void notifyTaskEndEvent(TaskEndEvent event) throws LpRestException {
		String modelId = event.processartifactid;
		String artifactId = event.taskartifactid;
		String modelSetId = event.modelsetid;

		SimulationData data = new SimulationData();
		data.setSessionData(event.simulationSessionData);
		data.setSubmittedData(event.submittedData);

		String simulationId = event.simulationsessionid;
		this.or.simulationTaskEndNotification(modelSetId, modelId, artifactId, simulationId, data);

		// Not sure if it is always the case to notify the CW, as this
		// notification should
		// happen just suddenly because of either "receiveTaskStartEvent" or
		// "receiveProcessEndEvent"
		this.handleRecommendations(modelSetId, artifactId, event.involvedusers, simulationId);
	}

	@Override
	public void notifySessionScoreUpdateEvent(SessionScoreUpdateEvent event) throws LpRestException {
		this.cw.notifyScoreUpdate(new ScoreRecord(event.user, event.processartifactid,
				event.simulationsessionid, event.sessionscore));
	}
	
	@Override
	public void notifyScoreUpdateEvent(ScoreUpdateEvent event) throws LpRestException {
		//TODO: this method should be adapted according to the structure of the ScoreUpdateEvent
		//TODO: the event contains a JsonNode reachable through event.get/setUpdatedScore
		//TODO: don't know if simulator is "interested" to store those values. 
	
		String modelSetId = event.modelsetid;
		String simulationSessionId = event.simulationsessionid;
		String processArtifactId = event.processartifactid;
		
		Long timestamp = event.timestamp; 
		
		String simUserId = event.user;
		String userEmail = this.convertUserID(simUserId);
		
		SimulationScoresMap simScoreMap = new SimulationScoresMap();
		simScoreMap.setScoreMap(event.scores);
	
		this.or.updateSimulationScore(modelSetId, simulationSessionId, processArtifactId, timestamp, userEmail, simScoreMap);
	}

	@Override
	public void notifySimulationStartEvent(SimulationStartEvent event) {
		// TODO probably we do not want do anything here. The actual interaction
		// between the OR and the SIM starts when receiveProcessStartEvent will
		// be
		// received
	}

	@Override
	public void notifySimulationEndEvent(SimulationEndEvent event) {
		// TODO probably we do not want do anything here. The actual interaction
		// between the OR and the SIM ends when receiveProcessEndEvent will be
		// received
	}

	@Override
	public void notifyTaskFailedEvent(TaskFailedEvent event) {
		// TODO Auto-generated method stub
	}

	private String convertUserID(String simUserId) throws LpRestException {
		String wikiName = DefaultRestResource.CORE_REPOSITORY_WIKI;
		String username = simUserId.replaceFirst("XWiki\\.", "");
		return utils.getEmailAddress(wikiName, username);
	}

	private void handleRecommendations(String modelSetId, String artifactId, List<String> involvedUsers,
			String simulationId) throws LpRestException {
		checkBeforeProcessingRecommendations(modelSetId, artifactId, involvedUsers, simulationId);

		for (String simUserId : involvedUsers) {
			String userEmail = this.convertUserID(simUserId);
			Recommendations rec = this.or.askRecommendation(modelSetId, artifactId, userEmail, simulationId);
			this.cw.notifyRecommendations(modelSetId, simulationId, simUserId, rec);
		}
	}

	private void deleteRecommendations(String modelSetId, String artifactId, List<String> involvedUsers,
			String simulationId) throws LpRestException {
		checkBeforeProcessingRecommendations(modelSetId, artifactId, involvedUsers, simulationId);

		for (String simUserId : involvedUsers) {
			this.cw.deleteRecommendations(modelSetId, simulationId, simUserId);
		}
	}

	private void checkBeforeProcessingRecommendations(String modelSetId, String artifactId, List<String> involvedUsers,
			String simulationId) throws LpRestExceptionXWikiImpl {
		if (involvedUsers == null) {
			String message = "List \"involvedUsers\" is null (ModelSetId:" + modelSetId + ", ArtifactId:" + artifactId
					+ ",SimulationId:" + simulationId + ")";
			throw new LpRestExceptionXWikiImpl(message);
		}
	}
}