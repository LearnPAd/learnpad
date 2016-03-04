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

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.rest.XWikiRestComponent;

import eu.learnpad.ca.rest.data.QualityCriteria;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.core.rest.XWikiRestUtils;
import eu.learnpad.cw.BridgeInterface;
import eu.learnpad.cw.Controller;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;
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
public class XwikiController extends Controller implements XWikiRestComponent, Initializable
{

    /** Set to true once the inherited BridgeInterface has been initialized. */
    private boolean initialized = false;

    /*
     * Note that in this solution the Controllers do not interact each-others, but each controller directly invokes the
     * BridgesInterfaces (from the other controllers) it needs. This is not actually what was originally planned, thus
     * in the future it may change. Also, not sure if this is the correct way to proceed. I would like to decide in a
     * configuration file the implementation to bind, and not into the source code. In fact, this second case implies to
     * rebuild the whole platform at each change.
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

    public synchronized void updateBridgeInterface(BridgeInterface bi)
    {
        this.bridge = bi;
    }

    /**
     * A means of instantiating the inherited BridgeInterface according to XWIKI (see
     * http://extensions.xwiki.org/xwiki/bin/view/Extension/Component+Module#HComponentInitialization). Actually in this
     * implementation we currently support only the class XwikiBridgeInterfaceRestResource, but other classes (such as
     * XwikiBridgeInterface) should be supported in the future Not sure if we can consider the default constructor.
     */
    @Override
    public synchronized void initialize() throws InitializationException
    {
        if (!this.initialized) {
            // Differently from the others, the XwikiBridge of the CW is
            // a concrete class. In fact, in this implementation the controller and the bridge
            // of the CW are supposed to be implemented with XWIKI technologies and to run
            // on the same instance of the LearnPAd Core Platform. Thus it has been
            // decided to avoid the communication over some REST channel
            this.bridge = new XwikiBridgeInterfaceRestResource();
            // this.bridge = new CWXwikiBridge();

            this.ca = new eu.learnpad.core.impl.ca.XwikiBridgeInterfaceRestResource();
            // this.db = new eu.learnpad.core.impl.db.XwikiBridgeInterfaceRestResource();
            this.me = new eu.learnpad.core.impl.me.XwikiBridgeInterfaceRestResource();
            this.mv = new eu.learnpad.core.impl.mv.XwikiBridgeInterfaceRestResource();
            this.mt = new eu.learnpad.core.impl.mt.XwikiBridgeInterfaceRestResource();
            this.lsm = new eu.learnpad.core.impl.lsm.XwikiBridgeInterfaceRestResource();
            this.or = new eu.learnpad.core.impl.or.XwikiBridgeInterfaceRestResource();
            this.qm = new eu.learnpad.core.impl.qm.XwikiBridgeInterfaceRestResource();
            this.sim = new eu.learnpad.core.impl.sim.XwikiBridgeInterfaceRestResource();

            this.initialized = true;
        }
    }

    @Override
    public void commentNotification(String modelSetId, String commentId, String action) throws LpRestException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void resourceNotification(String modelSetId, String resourceId, String artifactIds, String action)
        throws LpRestException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public InputStream getModel(String modelSetId, String type) throws LpRestException
    {
        String attachmentName = String.format("%s.%s", modelSetId, type);
        // TODO: Adapt the name dynamically for Adoxx or MagicDraw
        String fileName = "adoxx_modelset.xml";
        java.nio.file.Path filePath = Paths.get(fileName);
        return XWikiRestUtils.getFileInAttachment(RestResource.CORE_REPOSITORY_WIKI, RestResource.CORE_REPOSITORY_SPACE,
            modelSetId, attachmentName, filePath);
    }

    @Override
    public String startSimulation(String modelId, String currentUser, Collection<UserData> potentialUsers)
        throws LpRestException
    {
        return this.sim.addProcessInstance(modelId, potentialUsers, currentUser);
    }

    @Override
    public Recommendations getRecommendations(String modelSetId, String artifactId, String userId)
        throws LpRestException
    {
        Recommendations rec = this.or.askRecommendation(modelSetId, artifactId, userId, null);
        // Recommendations rec = new Recommendations();
        return rec;
    }

    @Override
    public InputStream transform(String type, InputStream model) throws LpRestException
    {
        switch (type) {
            case "adoxx":
            case "lpzip":
                return this.mt.transform("ADOXX", model);
            case "md":
                return this.mt.transform("MD", model);
            default:
                return null;
        }
    }

    @Override
    public String startAnalysis(String id, String language, List<String> options, InputStream body)
        throws LpRestException
    {
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
        html = "<![CDATA[ " + html + " ]]>";
        bodyScanner.close();
        collaborativeContent.setTitle(title);
        collaborativeContent.setContentplain(plain);
        collaborativeContent.setContenthtml(html);
        analysis.setCollaborativeContent(collaborativeContent);
        return this.ca.putValidateCollaborativeContent(analysis);
    }

    @Override
    public String getStatus(String analysisId) throws LpRestException
    {
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
    public AnnotatedCollaborativeContentAnalyses getResults(String analysisId) throws LpRestException
    {
        return this.ca.getCollaborativeContentVerifications(analysisId);
    }
}
