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

import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.Utils;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.Controller;
import eu.learnpad.me.rest.data.ImportStatus;
import eu.learnpad.me.rest.data.ImportStatusType;
import eu.learnpad.me.rest.data.KPIsFormat;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.mv.rest.data.VerificationId;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;
import eu.learnpad.mv.rest.data.VerificationsAvailable;
import eu.learnpad.rest.model.jaxb.PFResults;
import eu.leranpad.core.impl.others.MVStubber;

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
	private eu.learnpad.mv.BridgeInterface mv;
	private eu.learnpad.or.BridgeInterface or;
	private eu.learnpad.cw.BridgeInterface cw;

	@Inject
	Logger logger;

	@Override
	public void initialize() throws InitializationException {
		try {
			this.bridge = this.componentManager.getInstance(RestResource.class, "me");

			this.mv = this.componentManager.getInstance(RestResource.class, "mv");
			this.or = this.componentManager.getInstance(RestResource.class, "or");
			this.cw = this.componentManager.getInstance(RestResource.class, "cw");
		} catch (ComponentLookupException e) {
			throw new InitializationException(e.getMessage(), e);
		}
	}

	@Override
	public VerificationId putModelSet(String modelSetId, ModelSetType type, InputStream modelSetFile)
			throws LpRestException {
		this.createDedicatedEntryInCoreRepository(modelSetId);
		String attachmentName = String.format("%s.%s", modelSetId, type);
		utils.putAttachment(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
				modelSetId, attachmentName, modelSetFile);
		
		VerificationId verificationID;
		// TODO Fix this differences when implemented the rest of the features for
		// both MD and LPZIP files. Probably the notion LPZIP would be dropped since
		// in practice we always process ZIP and the type actually distinguish if
		// inside the model there was a file from MD or ADOXX
		if (type.equals(ModelSetType.ADOXX)){
			verificationID = this.mv.startVerification(modelSetId, "ALL"); 
		}else{
			// Note that in this case the model will not be imported in the
			// platform. The following operations will not work as expected for the given modelSetId :
			//  + "getFeedbacks"
			//  + "getAvailableVerifications"
			verificationID = MVStubber.getVerificationId();
		}
			
		return verificationID;
	}

	@Override
	public ImportStatus putKPIs(String modelSetId, String kpisId, KPIsFormat type,
			InputStream kpisFile) throws LpRestException {
		this.createDedicatedEntryInCoreRepository(modelSetId);
		String kpisAttachmentName = String.format("%s.%s", kpisId, type.toString().toLowerCase());
		utils.putAttachment(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
				modelSetId, kpisAttachmentName , kpisFile);
		this.or.kpisImported(modelSetId, kpisId, type);
		ImportStatus status = new ImportStatus();
		status.setStatus(ImportStatusType.COMPLETED);
		ImportStatus.Info msg = new ImportStatus.Info();
//		msg.getAny().add("OK");
		status.setInfo(msg);
		return status;
	}

	@Override
	public PFResults getFeedbacks(String modelSetId) throws LpRestException {
		return this.cw.getFeedbacks(modelSetId);
	}

	@Override
	public VerificationId startModelSetVerification(String modelSetId, String type, String verification)
			throws LpRestException {
		VerificationId verificationID;
		
		// TODO Fix this differences when implemented the rest of the features for
		// both MD and LPZIP files. Probably the notion LPZIP would be dropped since
		// in practice we always process ZIP and the type actually distinguish if
		// inside the model there was a file from MD or ADOXX
		if (type.equals(ModelSetType.ADOXX)){
			verificationID = this.mv.startVerification(modelSetId, verification);
			VerificationStatus vStatus = this.mv.getVerificationStatus(verificationID.getId());
		}else{
			// Note that in this case the model will not be imported in the
			// platform. The following operations will not work as expected for the given modelSetId :
			//  + "getFeedbacks"			
			//  + "getAvailableVerifications"
			verificationID = MVStubber.getVerificationId();			
		}	
			
		// TODO: show the vStatus.getStatus() of the verification with id
		// vId.getId() somewhere in the wiki?
		// The verification status (currently IN PROGRESS) should be visualizes
		// somewhere in the cw for the given
		// modelsetid so the modeler can check it.
		return verificationID;
	}

	@Override
	public VerificationStatus checkModelSetVerification(String verificationProcessId) throws LpRestException {
		VerificationStatus status;
		// TODO Fix this differences when implemented the rest of the features for
		// both MD and LPZIP files. Probably the notion LPZIP would be dropped since
		// in practice we always process ZIP and the type actually distinguish if
		// inside the model there was a file from MD or ADOXX
		if (! MVStubber.isFakeVerificationID(verificationProcessId)){		
			status = this.mv.getVerificationStatus(verificationProcessId);
		}else{
			status = MVStubber.getVerificationStatus();
		}	
		return status;
	}

	@Override
	public VerificationResults getModelSetVerificationResults(String verificationProcessId) throws LpRestException {
		VerificationResults results;
		if (! MVStubber.isFakeVerificationID(verificationProcessId)){		
			results = this.mv.getVerificationResult(verificationProcessId);
		}else{
			results = MVStubber.getVerificationResults();
		}	
		return results;
	}

	@Override
	public VerificationsAvailable getAvailableVerifications() throws LpRestException {
		return this.mv.getAvailableVerifications();
	}

	private synchronized void createDedicatedEntryInCoreRepository(String modelSetId) {
		if (utils.isPage(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
				modelSetId) == false) {
			utils.createEmptyPage(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
					modelSetId);
		}
	}
}