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
package eu.learnpad.core.impl.me;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.XWikiRestUtils;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.me.BridgeInterface;
import eu.learnpad.me.Controller;
import eu.learnpad.mv.rest.data.MVResults;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.VerificationsAvailable;
import eu.learnpad.rest.model.jaxb.PFResults;

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
@Named("eu.learnpad.core.impl.me.XwikiController")
@Path("/learnpad/me/corefacade")
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
	private eu.learnpad.mv.BridgeInterface mv;
	private eu.learnpad.cw.BridgeInterface cw;
	private eu.learnpad.sim.BridgeInterface sim;
	private eu.learnpad.qm.BridgeInterface qm;
	private eu.learnpad.or.BridgeInterface or;

	private Map<String, MVResults> mvResults;

	@Inject
	Logger logger;

    public synchronized void updateBridgeInterface (BridgeInterface bi){
		this.bridge = bi;    
    }

	@Override
	public synchronized void initialize() throws InitializationException {
		if (!this.initialized){
			this.bridge = new XwikiBridgeInterfaceRestResource();
			
			this.mv = new eu.learnpad.core.impl.mv.XwikiBridgeInterfaceRestResource();
			this.cw = new eu.learnpad.core.impl.cw.XwikiBridgeInterfaceRestResource();
			this.sim = new eu.learnpad.core.impl.sim.XwikiBridgeInterfaceRestResource();
			this.qm = new eu.learnpad.core.impl.qm.XwikiBridgeInterfaceRestResource();
			this.or = new eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource();

			this.mvResults = new HashMap<String, MVResults>();
			
			this.initialized=true;
		}
	}

	@Override
	public void putModelSet(String modelSetId, String type, byte[] modelSetFile)
			throws LpRestExceptionImpl {
		if (XWikiRestUtils.isPage(RestResource.CORE_REPOSITORY_WIKI,
				RestResource.CORE_REPOSITORY_SPACE, modelSetId) == false) {
			XWikiRestUtils.createEmptyPage(RestResource.CORE_REPOSITORY_WIKI,
					RestResource.CORE_REPOSITORY_SPACE, modelSetId);
		}
		String attachmentName = String.format("%s.%s", modelSetId, type);
		XWikiRestUtils.putAttachment(RestResource.CORE_REPOSITORY_WIKI,
				RestResource.CORE_REPOSITORY_SPACE, modelSetId, attachmentName,
				modelSetFile);
	}
	
	@Override
	public PFResults getFeedbacks(String modelSetId) throws LpRestException {
		return this.cw.getFeedbacks(modelSetId);
	}

    @Override
    public VerificationId startModelSetVerification(String modelSetId, String type, String verification)
            throws LpRestException {
        VerificationId vId = this.mv.startVerification(modelSetId, verification);
        VerificationStatus vStatus = this.mv.getVerificationStatus(vId.getId());
        //TODO: show the vStatus.getStatus() of the verification with id vId.getId() somewhere in the wiki?
        //The verification status (currently IN PROGRESS) should be visualizes somewhere in the cw for the given modelsetid so the modeler can check it.
        return vId;
    }
    
    @Override
    public VerificationStatus checkModelSetVerification(String verificationProcessId) throws LpRestException {
        return this.mv.getVerificationStatus(verificationProcessId);
    }
    
    @Override
    public VerificationResults getModelSetVerificationResults(String verificationProcessId) throws LpRestException {
        return this.mv.getVerificationResult(verificationProcessId);
    }
    
    @Override
    public VerificationsAvailable getAvailableVerifications() throws LpRestException {
        return this.mv.getAvailableVerifications();
    }
}
