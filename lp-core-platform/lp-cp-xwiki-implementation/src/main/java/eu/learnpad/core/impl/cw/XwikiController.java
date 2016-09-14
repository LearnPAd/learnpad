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

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import eu.learnpad.ca.rest.data.QualityCriteria;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.Utils;
import eu.learnpad.cw.Controller;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.or.rest.data.Entities;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessId;
import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.sim.rest.data.UserDataCollection;

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
	private eu.learnpad.ca.BridgeInterface ca;

	// private eu.learnpad.db.BridgeInterface db;
	private eu.learnpad.me.BridgeInterface me;

	private eu.learnpad.mv.BridgeInterface mv;

	private eu.learnpad.mt.BridgeInterface mt;

	private eu.learnpad.lsm.BridgeInterface lsm;

	private eu.learnpad.or.BridgeInterface or;

	private eu.learnpad.qm.BridgeInterface qm;

	private eu.learnpad.sim.BridgeInterface sim;

	private eu.learnpad.dash.BridgeInterface dash;

	@Override
	public void initialize() throws InitializationException {
		try {
			this.bridge = this.componentManager.getInstance(RestResource.class, "cw");

			this.ca = this.componentManager.getInstance(RestResource.class, "ca");
			this.me = this.componentManager.getInstance(RestResource.class, "me");
			this.mv = this.componentManager.getInstance(RestResource.class, "mv");
			this.mt = this.componentManager.getInstance(RestResource.class, "mt");
			this.lsm = this.componentManager.getInstance(RestResource.class, "lsm");
			this.or = this.componentManager.getInstance(RestResource.class, "or");
			this.qm = this.componentManager.getInstance(RestResource.class, "qm");
			this.sim = this.componentManager.getInstance(RestResource.class, "sim");
			this.dash = this.componentManager.getInstance(RestResource.class, "dash");
		} catch (ComponentLookupException e) {
			throw new InitializationException(e.getMessage(), e);
		}
	}

	@Override
	public void commentNotification(String modelSetId, String commentId, String action) throws LpRestException {
		// TODO Auto-generated method stub
	}

	@Override
	public void resourceNotification(String modelSetId, String modelId,
			String artifactId, String resourceId, ResourceType type,
			NotificationActionType action, String userId)
			throws LpRestException {
		Long timestamp = new Long(System.currentTimeMillis());
		String userEmail = this.convertUserID(userId);
		this.or.resourceNotification(modelSetId, modelId, artifactId, resourceId, type, userEmail, timestamp, action);		
	}	
	
	@Override
	public Entities analyseText(String modelSetId, String contextArtifactId,
			String userId, String title, String text) throws LpRestException {
		String userEmail = this.convertUserID(userId);
		Entities e = this.or.analyseText(modelSetId, contextArtifactId, userEmail, title, text);
		return e;
	}

	@Override
	public InputStream getModel(String modelSetId, ModelSetType type) throws LpRestException {
		String attachmentName = String.format("%s.%s", modelSetId, type);
		// TODO: Adapt the name dynamically for Adoxx or MagicDraw
		String fileName = "adoxx_modelset.xml";
		java.nio.file.Path filePath = Paths.get(fileName);
		return utils.getFileInAttachment(DefaultRestResource.CORE_REPOSITORY_WIKI,
				DefaultRestResource.CORE_REPOSITORY_SPACE, modelSetId, attachmentName, filePath);
	}

	@Override
	public String startSimulation(String modelId, String currentUser, UserDataCollection potentialUsers)
			throws LpRestException {
		currentUser = this.removePrefixes(currentUser);

		if (potentialUsers != null) {
			for (UserData userData : potentialUsers.content) {
				userData.id = this.removePrefixes(userData.id);
			}
		}

		return this.sim.addProcessInstance(modelId, potentialUsers.content, currentUser);
	}

	@Override
	public String joinSimulation(String simulationId, String userId) throws LpRestException {
		userId = this.removePrefixes(userId);

		return this.sim.joinProcessInstance(simulationId, userId);
	}

	@Override
	public Collection<String> listSimulations() throws LpRestException {
		return this.sim.getProcessInstances();
	}

	@Override
	public ProcessInstanceData getSimulationInfo(String simulationId) throws LpRestException {
		return this.sim.getProcessInstanceInfos(simulationId);
	}

	@Override
	public Recommendations getRecommendations(String modelSetId, String artifactId, String userId)
			throws LpRestException {
		String userEmail = this.convertUserID(userId);
		Recommendations rec = this.or.askRecommendation(modelSetId, artifactId, userEmail, null);
		return rec;
	}

	@Override
	public String getDashboardKpiDefaultViewer(String modelSetId, String userId)
			throws LpRestException {
		String businessActorEmail = this.convertUserID(userId);
		String url = this.dash.getKPIValuesView(modelSetId, businessActorEmail);
		return url;
	}	
	
	@Override
	public InputStream transform(ModelSetType type, InputStream model) throws LpRestException {
		return this.mt.transform(type, model);
	}

	@Override
	public String startAnalysis(String id, String language, List<String> options, InputStream body)
			throws LpRestException {
		CollaborativeContentAnalysis analysis = new CollaborativeContentAnalysis();
		analysis.setLanguage(language);
		QualityCriteria qualityCriteria = new QualityCriteria();
		for (String option : options) {
			switch (option) {
			case "simplicity":
				qualityCriteria.setSimplicity(true);
				break;
			case "non_ambiguity":
				qualityCriteria.setNonAmbiguity(true);
				break;
			case "content_clarity":
				qualityCriteria.setContentClarity(true);
				break;
			case "presentation_clarity":
				qualityCriteria.setPresentationClarity(true);
				break;
			case "completeness":
				qualityCriteria.setCompleteness(true);
				break;
			case "correctness":
				qualityCriteria.setCorrectness(true);
				break;
			}
		}
		analysis.setQualityCriteria(qualityCriteria);
		CollaborativeContent collaborativeContent = new CollaborativeContent();
		collaborativeContent.setId(id);

		@SuppressWarnings("resource")
		Scanner bodyScanner = new Scanner(body).useDelimiter("-\\*-\\*-");
		String title = bodyScanner.next();
		String plain = bodyScanner.next();
		String html = bodyScanner.next();
		bodyScanner.close();
		collaborativeContent.setTitle(title);
		collaborativeContent.setContentplain(plain);
		collaborativeContent.setContenthtml(html);
		analysis.setCollaborativeContent(collaborativeContent);
		return this.ca.putValidateCollaborativeContent(analysis);
	}

	@Override
	public String getStatus(String analysisId) throws LpRestException {
		String status = this.ca.getStatusCollaborativeContentVerifications(analysisId);
		if ("OK".equals(status)) {
			return "1.0";
		} else {
			Pattern pattern = Pattern.compile("InProgress_(\\d*)%");
			Matcher matcher = pattern.matcher(status);
			boolean matches = matcher.matches();
			if (matches) {
				return Double.toString(Double.parseDouble(matcher.group(1)) / 100.0);
			} else {
				return "-1.0";
			}
		}
	}

	@Override
	public String getView(String analysisId) throws LpRestException {
		String url = this.ca.getCollaborativeContentVerificationsView(analysisId);
		return url;
	}

	@Override
	public AnnotatedCollaborativeContentAnalyses getResults(String analysisId) throws LpRestException {
		return this.ca.getCollaborativeContentVerifications(analysisId);
	}

	@Override
	public String calculateKPI(String modelSetId) throws LpRestException {
		KBProcessId kbPID = this.or.calculateKPI(modelSetId);
		if (kbPID != null)
			return kbPID.getId();
		return "";
	}

	private String removePrefixes(String userId) {
		String username = userId.replaceFirst("XWiki\\.", "");
		return username;
	}

	private String convertUserID(String userId) throws LpRestException {

		String wikiName = DefaultRestResource.CORE_REPOSITORY_WIKI;
		String username = this.removePrefixes(userId);
		return utils.getEmailAddress(wikiName, username);
	}

}
