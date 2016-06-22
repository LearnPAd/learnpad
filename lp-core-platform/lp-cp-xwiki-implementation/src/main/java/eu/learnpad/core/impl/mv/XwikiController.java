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

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.Utils;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.mv.Controller;
import eu.learnpad.mv.rest.data.FinalResultType;
import eu.learnpad.mv.rest.data.FormalVerificationResult;
import eu.learnpad.mv.rest.data.FormalVerificationResult.CounterExampleTrace;
import eu.learnpad.mv.rest.data.FormalVerificationResult.CounterExampleTrace.Step;
import eu.learnpad.mv.rest.data.StatusType;
import eu.learnpad.mv.rest.data.UnderstandabilityResult;
import eu.learnpad.mv.rest.data.UnderstandabilityResult.Guidelines.Guideline;
import eu.learnpad.mv.rest.data.UnderstandabilityResult.Guidelines.Guideline.Elements.ElementID;
import eu.learnpad.mv.rest.data.VerificationResults;
import eu.learnpad.mv.rest.data.VerificationStatus;

@Component
@Singleton
@Named("eu.learnpad.core.impl.mv.XwikiController")
@Path("/learnpad/mv/corefacade")
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

	private eu.learnpad.qm.BridgeInterface qm;

	private eu.learnpad.sim.BridgeInterface sim;

	private eu.learnpad.dash.BridgeInterface dash;

	private Map<String, ModelSetType> typesMap;

	@Override
	public void initialize() throws InitializationException {
		try {
			this.bridge = this.componentManager.getInstance(RestResource.class, "mv");

			this.cw = this.componentManager.getInstance(RestResource.class, "cw");
			this.or = this.componentManager.getInstance(RestResource.class, "or");
			this.sim = this.componentManager.getInstance(RestResource.class, "sim");
			this.qm = this.componentManager.getInstance(RestResource.class, "qm");
			this.dash = this.componentManager.getInstance(RestResource.class, "dash");
		} catch (ComponentLookupException e) {
			throw new InitializationException(e.getMessage(), e);
		}

		this.typesMap = new HashMap<String, ModelSetType>();
	}

	@Override
	public InputStream getModel(String modelSetId, ModelSetType type) throws LpRestException {
		this.typesMap.put(modelSetId, type);
		String attachmentName = String.format("%s.%s", modelSetId, type);
		return utils.getAttachment(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
				modelSetId, attachmentName);
	}

	private void printResults(VerificationResults res) {
		System.out.println("################");
		System.out.println(String.format("Final result: %s", res.getFinalResult()));
		System.out.println(String.format("Model Set ID: %s", res.getModelID()));
		System.out.println(String.format("Verification type: %s", res.getVerificationType()));
		if (res.getResults() != null) {
			for (Object o : res.getResults().getAny()) {
				if (o instanceof FormalVerificationResult) {
					FormalVerificationResult r = (FormalVerificationResult) o;
					System.out.println(String.format("# Formal Verification: %s", r.getStatus()));
					System.out.println(String.format("    ID: %s", r.getDefinitionID()));
					System.out.println(String.format("    Description: %s", r.getDescription()));
					int countCET = 0;
					for (CounterExampleTrace cet : r.getCounterExampleTrace()) {
						System.out.println(String.format("    CounterExampleTrace #%d", countCET));
						int countStep = 0;
						for (Step s : cet.getStep()) {
							System.out.println(String.format("      Step #%d", countStep));
							System.out.println(String.format("        Object ID: %s", s.getObjectID()));
							System.out.println(String.format("        Num: %s", s.getNum()));
							countStep++;
						}
						countCET++;
					}
				} else if (o instanceof UnderstandabilityResult) {
					UnderstandabilityResult r = (UnderstandabilityResult) o;
					System.out.println(String.format("# Understandability: %s", r.getStatus()));
					System.out.println(String.format("    ID: %s", r.getDefinitionID()));
					System.out.println(String.format("    Description: %s", r.getDescription()));
					int countG = 0;
					for (Guideline g : r.getGuidelines().getGuideline()) {
						System.out.println(String.format("    Guideline #%d", countG));
						System.out.println(String.format("      Guideline ID: %s", g.getId()));
						System.out.println(String.format("      Name: %s", g.getName()));
						System.out.println(String.format("      Description: %s", g.getDescription()));
						System.out.println(String.format("      Suggestion: %s", g.getSuggestion()));
						int countEID = 0;
						if (g.getElements() != null) {
							for (ElementID eid : g.getElements().getElementID()) {
								System.out.println(String.format("      ElementID #%s", countEID));
								System.out.println(String.format("        Reference Name: %s", eid.getRefName()));
								System.out.println(
										String.format("        Reference Process ID: %s", eid.getRefProcessID()));
								System.out.println(String.format("        Value: %s", eid.getValue()));
								countEID++;
							}
						}
						countG++;
					}
				} else {
					System.out.println(String.format("Unkown Result type [%s]", o.getClass()));
				}
			}
		}
		System.out.println("################");
	}

	@Override
	public void notifyVerification(String verificationProcessId) throws LpRestException {
		VerificationStatus status = this.bridge.getVerificationStatus(verificationProcessId);
		// TODO: show the status.getStatus() somewhere in the wiki for the
		// verification with id verificationProcessId?

		if (!status.getStatus().equals(StatusType.COMPLETED)) {
			// A severe error occurred in the verification phase that is
			// terminated unexpectedly
			// The verification results in this case is null. If
			// this.bridge.getVerificationResult will be called an exception
			// will rise up
			// Notify somewhere the status of the verification id? or obtain the
			// modelSet Id from the verification id and notify the verification
			// status of the modelset id?
			return;
		}

		VerificationResults res = this.bridge.getVerificationResult(verificationProcessId);

		printResults(res);

		String modelSetId = res.getModelID();
		ModelSetType type = this.typesMap.get(modelSetId);

		boolean resultsOk = res.getFinalResult().equals(FinalResultType.OK);
		boolean importedInTheSimulator = true;
		if (resultsOk) {
			if (utils.isPage(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
					modelSetId) == true) {
				this.cw.modelSetImported(modelSetId, type);
				this.or.modelSetImported(modelSetId, type);
				this.dash.modelSetImported(modelSetId, type);

				InputStream modelContent = utils.getAttachmentFromCoreRepository(modelSetId, type.toString());
				this.qm.importModelSet(modelSetId, type, modelContent);

				Collection<String> uriCollection = utils.exposeBPMNFromCoreRepository(modelSetId, type.toString());
				Iterator<String> uriIterator = uriCollection.iterator();
				while (importedInTheSimulator && uriIterator.hasNext()) {
					String bpmnFileURL = uriIterator.next();
					Collection<String> savedProcessesList = this.sim.addProcessDefinition(bpmnFileURL, modelSetId);
					importedInTheSimulator = this.verifyImportedProcesses(savedProcessesList, bpmnFileURL);
				}
			}
		}

		// the value for message may change accordingly to
		// eu.learnpad.cw.rest.ModelVerified
		String message = "KO";
		if (resultsOk) {
			if (!importedInTheSimulator) {
				// the value for message may change accordingly to
				// eu.learnpad.cw.rest.ModelVerified
				message = "OK but Problems with the Simulator";
			} else {
				// the value for message may change accordingly to
				// eu.learnpad.cw.rest.ModelVerified
				message = "OK";
			}
		}

		this.cw.modelVerified(modelSetId, message);
	}

	private boolean verifyImportedProcesses(Collection<String> savedProcessesList, String bpmnFileURL) {
		// TODO This is a stub implementation that may change in the future
		boolean isValid = false;
		if (savedProcessesList != null) {
			isValid = savedProcessesList.size() > 0;
		}

		return isValid;
	}
}