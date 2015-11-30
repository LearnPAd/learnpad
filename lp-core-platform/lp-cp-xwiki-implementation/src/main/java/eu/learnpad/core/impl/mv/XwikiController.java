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
package eu.learnpad.core.impl.mv;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.impl.mv.XwikiBridgeInterfaceRestResource;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.XWikiRestUtils;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.mv.Controller;
import eu.learnpad.mv.rest.data.FinalResultType;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.BridgeInterface;

@Component
@Singleton
@Named("eu.learnpad.core.impl.mv.XwikiController")
@Path("/learnpad/mv/corefacade")
public class XwikiController extends Controller implements XWikiRestComponent, Initializable{

//	@Inject
//	Logger logger;

    /** Set to true once the inherited BridgeInterface has been initialized. */
    private boolean initialized = false;	

    private eu.learnpad.cw.BridgeInterface cw;
    private eu.learnpad.or.BridgeInterface or;
    
    private Map<String, String> typesMap;
    
    
    
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
//			try {
//				this.bridge= this.manager.getInstance(XWikiRestComponent.class, "eu.learnpad.core.impl.qm.XwikiBridgeInterfaceRestResource");
//	        	} catch (ComponentLookupException e) {
//	        		throw new InitializationException(e.getMessage(), e);
//	        }
			this.cw = new eu.learnpad.core.impl.cw.XwikiBridgeInterfaceRestResource();
			this.or = new eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource();
			this.typesMap = new HashMap<String, String>();
			
			this.initialized=true;
			
		}
	}
		
	public synchronized void updateBridgeInterface (BridgeInterface bi){
		this.bridge = bi;    
    }
	

	@Override
	public byte[] getModel(String modelSetId, String type)
			throws LpRestException {
	    this.typesMap.put(modelSetId, type);
        String attachmentName = String.format("%s.%s", modelSetId, type);
        return XWikiRestUtils.getAttachment(RestResource.CORE_REPOSITORY_WIKI,
                RestResource.CORE_REPOSITORY_SPACE, modelSetId, attachmentName);
	}

	@Override
	public void notifyVerification(String verificationProcessId)
			throws LpRestException {
		VerificationResults res = this.bridge.getVerificationResult(verificationProcessId);
        
		String modelSetId = res.getModelID();
        String type = this.typesMap.get(modelSetId);
        
		boolean resultsOk = res.getFinalResult().equals(FinalResultType.OK);
		if(resultsOk){
		    if(XWikiRestUtils.isPage(RestResource.CORE_REPOSITORY_WIKI,
	                RestResource.CORE_REPOSITORY_SPACE, modelSetId) == true){
    		    this.cw.modelSetImported(modelSetId, type);
    		    this.or.modelSetImported(modelSetId, type);
		    }
		}
		
		this.cw.modelVerified(modelSetId, (resultsOk)?"OK":"KO");
	}

}
