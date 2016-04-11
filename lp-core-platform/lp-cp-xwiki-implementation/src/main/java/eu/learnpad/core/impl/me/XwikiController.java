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
import eu.learnpad.core.rest.XWikiRestUtils;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.BridgeInterface;
import eu.learnpad.me.Controller;
import eu.learnpad.me.rest.data.ModelSetType;
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
public class XwikiController extends Controller implements XWikiRestComponent, Initializable
{

    @Inject
    @Named("xwiki")
    private Utils utils;

    @Inject
    private ComponentManager componentManager;

    /*
     * Note that in this solution the Controllers do not interact each-others, but each controller directly invokes the
     * BridgesInterfaces (from the other controllers) it needs. This is not actually what was originally planned, thus
     * in the future it may change. Also, not sure if this is the correct way to proceed. I would like to decide in a
     * configuration file the implementation to bind, and not into the source code. In fact, this second case implies to
     * rebuild the whole platform at each change.
     */
    private eu.learnpad.mv.BridgeInterface mv;

    private eu.learnpad.cw.BridgeInterface cw;

    @Inject
    Logger logger;

    @Override
    public void initialize() throws InitializationException
    {
        try {
            this.bridge = this.componentManager.getInstance(RestResource.class, "me");

            this.mv = this.componentManager.getInstance(RestResource.class, "mv");
            this.cw = this.componentManager.getInstance(RestResource.class, "cw");
        } catch (ComponentLookupException e) {
            throw new InitializationException(e.getMessage(), e);
        }
    }

    @Override
    public VerificationId putModelSet(String modelSetId, ModelSetType type, InputStream modelSetFile)
        throws LpRestException
    {
        if (utils.isPage(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
            modelSetId) == false) {
            utils.createEmptyPage(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
                modelSetId);
        }
        String attachmentName = String.format("%s.%s", modelSetId, type);
        utils.putAttachment(DefaultRestResource.CORE_REPOSITORY_WIKI, DefaultRestResource.CORE_REPOSITORY_SPACE,
            modelSetId, attachmentName, modelSetFile);
        return this.mv.startVerification(modelSetId, "ALL");
    }

    @Override
    public PFResults getFeedbacks(String modelSetId) throws LpRestException
    {
        return this.cw.getFeedbacks(modelSetId);
    }

    @Override
    public VerificationId startModelSetVerification(String modelSetId, String type, String verification)
        throws LpRestException
    {
        VerificationId vId = this.mv.startVerification(modelSetId, verification);
        VerificationStatus vStatus = this.mv.getVerificationStatus(vId.getId());
        // TODO: show the vStatus.getStatus() of the verification with id vId.getId() somewhere in the wiki?
        // The verification status (currently IN PROGRESS) should be visualizes somewhere in the cw for the given
        // modelsetid so the modeler can check it.
        return vId;
    }

    @Override
    public VerificationStatus checkModelSetVerification(String verificationProcessId) throws LpRestException
    {
        return this.mv.getVerificationStatus(verificationProcessId);
    }

    @Override
    public VerificationResults getModelSetVerificationResults(String verificationProcessId) throws LpRestException
    {
        return this.mv.getVerificationResult(verificationProcessId);
    }

    @Override
    public VerificationsAvailable getAvailableVerifications() throws LpRestException
    {
        return this.mv.getAvailableVerifications();
    }
}
