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
package eu.learnpad.core.impl.cw;

import java.util.Collection;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.XWikiRestUtils;
import eu.learnpad.cw.BridgeInterface;
import eu.learnpad.cw.Controller;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.data.UserData;

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
@Named("eu.learnpad.core.impl.cw.XwikiController")
@Path("/learnpad/cw/corefacade")
public class XwikiController extends Controller implements XWikiRestComponent, Initializable{

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
	private eu.learnpad.ca.BridgeInterface ca;
//	private eu.learnpad.db.BridgeInterface db;
	private eu.learnpad.me.BridgeInterface me;
	private eu.learnpad.mv.BridgeInterface mv;
	private eu.learnpad.lsm.BridgeInterface lsm;
	private eu.learnpad.or.BridgeInterface or;
	private eu.learnpad.qm.BridgeInterface qm;
	private eu.learnpad.sim.BridgeInterface sim;

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
// Differently from the others, the XwikiBridge of the CW is
// a concrete class. In fact, in this implementation the controller and the bridge
// of the CW are supposed to be implemented with XWIKI technologies and to run
// on the same instance of the LearnPAd Core Platform. Thus it has been
// decided to avoid the communication over some REST channel 			
			this.bridge = new XwikiBridgeInterfaceRestResource();
//			this.bridge = new CWXwikiBridge();

			this.ca = new eu.learnpad.core.impl.ca.XwikiBridgeInterfaceRestResource();			
//			this.db = new eu.learnpad.core.impl.db.XwikiBridgeInterfaceRestResource();			
			this.me = new eu.learnpad.core.impl.me.XwikiBridgeInterfaceRestResource();			
			this.mv = new eu.learnpad.core.impl.mv.XwikiBridgeInterfaceRestResource();			
			this.lsm = new eu.learnpad.core.impl.lsm.XwikiBridgeInterfaceRestResource();			
			this.or = new eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource();			
			this.qm = new eu.learnpad.core.impl.qm.XwikiBridgeInterfaceRestResource();			
			this.sim = new eu.learnpad.core.impl.sim.XwikiBridgeInterfaceRestResource();			
			
			this.initialized=true;
		}
	}

	@Override
	public void commentNotification(String modelSetId, String commentId,
			String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void resourceNotification(String modelSetId, String resourceId,
			String artifactIds, String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getModel(String modelSetId, String type)
			throws LpRestException {
		String attachmentName = String.format("%s.%s", modelSetId, type);
		return XWikiRestUtils.getAttachment(RestResource.CORE_REPOSITORY_WIKI,
				RestResource.CORE_REPOSITORY_SPACE, modelSetId, attachmentName);
	}

	@Override
	public String startSimulation(String modelId, String currentUser,
			Collection<UserData> potentialUsers) throws LpRestException {
		return this.sim.addProcessInstance(modelId, potentialUsers, currentUser);
	}
}
