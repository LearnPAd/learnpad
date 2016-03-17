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
package eu.learnpad.cw;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.model.EntityType;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.DocumentReferenceResolver;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.query.Query;
import org.xwiki.query.QueryException;
import org.xwiki.query.QueryFilter;
import org.xwiki.query.QueryManager;

import com.xpn.xwiki.XWiki;
import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;

import eu.learnpad.core.impl.cw.XwikiBridge;
import eu.learnpad.core.rest.RestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.rest.model.jaxb.PFResults;
import eu.learnpad.rest.model.jaxb.PFResults.Feedbacks;
import eu.learnpad.rest.model.jaxb.PFResults.Feedbacks.Feedback;
import eu.learnpad.rest.model.jaxb.PFResults.Patches;
import eu.learnpad.rest.model.jaxb.PFResults.Patches.Patch;
import eu.learnpad.rest.model.jaxb.PFResults.Patches.Patch.Artefact;
import eu.learnpad.rest.model.jaxb.PFResults.Patches.Patch.Artefact.Attribute;
import eu.learnpad.rest.model.jaxb.PatchType;
import eu.learnpad.sim.rest.data.UserData;

@Component
@Singleton
@Named("eu.learnpad.cw.CWXwikiBridge")
@Path("/learnpad/cw/bridge")
public class CWXwikiBridge extends XwikiBridge implements UICWBridge
{
    private final String LEARNPAD_SPACE = "LPCode";

    private final String FEEDBACK_CLASS_PAGE = "FeedbackClass";

    private final String FEEDBACK_CLASS = String.format("%s.%s", LEARNPAD_SPACE, FEEDBACK_CLASS_PAGE);

    private final String BASEELEMENT_CLASS_PAGE = "BaseElementClass";

    private final String BASEELEMENT_CLASS = String.format("%s.%s", LEARNPAD_SPACE, BASEELEMENT_CLASS_PAGE);

    private final String XWIKI_SPACE = "XWiki";

    private final String USER_CLASS_PAGE = "XWikiUsers";

    private final String USER_CLASS = String.format("%s.%s", XWIKI_SPACE, USER_CLASS_PAGE);

    @Inject
    private Logger logger;

    @Inject
    @Named("secure")
    private QueryManager queryManager;

    @Inject
    @Named("unique")
    private QueryFilter uniqueDocumentFilter;

    @Inject
    private Provider<XWikiContext> xcontextProvider;

    @Inject
    @Named("current")
    private DocumentReferenceResolver<String> stringDocumentReferenceResolver;

    @Inject
    private DocumentReferenceResolver<EntityReference> referenceDocumentReferenceResolver;

    @Override
    public byte[] getComments(String modelSetId, String artifactId) throws LpRestException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public byte[] getResource(String modelSetId, String resourceId, String linkedTo, String action)
        throws LpRestException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void modelSetImported(String modelSetId, ModelSetType type) throws LpRestException
    {
        // Get the model file from Core Platform
        InputStream modelStream = this.corefacade.getModel(modelSetId, type);
        if (modelStream == null) {
            throw new LpRestExceptionXWikiImpl("Fail to get the new modelset");
        }

        InputStream packageStream = this.corefacade.transform(type, modelStream);
        if (packageStream == null) {
            throw new LpRestExceptionXWikiImpl("Fail to transform the new modelset");
        }
        this.loadPackage(packageStream);

    }

    private void loadPackage(InputStream packageStream)
    {
        // Now send the package's path to the importer for XWiki
        HttpClient httpClient = RestResource.getClient();

        String uri = RestResource.REST_URI + "/wikis/xwiki/xff";
        PostMethod postMethod = new PostMethod(uri);
        postMethod.addRequestHeader("Accept", "application/octet-stream");

        RequestEntity packageEntity = new InputStreamRequestEntity(packageStream);
        postMethod.setRequestEntity(packageEntity);

        try {
            httpClient.executeMethod(postMethod);
        } catch (IOException e) {
            String message = "Unable to upload the XFF package";
            logger.error(message, e);
        }
    }

    @Override
    public void contentVerified(String modelSetId, String artifactId, String resourceId, String result)
        throws LpRestException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void modelVerified(String modelSetId, String result) throws LpRestException
    {
        // TODO Auto-generated method stub

    }

    private List<Object> getFeedbacksDocuments(String modelSetId)
    {
        String queryXWQL =
            String.format("from doc.object(%s) as feedback where doc.space = '%s'", FEEDBACK_CLASS, modelSetId);
        Query query = null;
        try {
            query = queryManager.createQuery(queryXWQL, Query.XWQL);
        } catch (QueryException e) {
            String message =
                String.format("Error in building the query to gather Feedbacks in '%s' model set.", modelSetId);
            logger.error(message, e);
            return null;
        }
        try {
            return query.addFilter(uniqueDocumentFilter).execute();
        } catch (QueryException e) {
            String message =
                String.format("Error in executing the query to gather Feedbacks in '%s' model set.", modelSetId);
            logger.error(message, e);
            return null;
        }
    }

    private Integer getPropertyFromParent(DocumentReference parentReference, String propertyName)
    {
        DocumentReference classReference = stringDocumentReferenceResolver.resolve(BASEELEMENT_CLASS);
        XWikiContext xcontext = xcontextProvider.get();
        XWiki xwiki = xcontext.getWiki();
        XWikiDocument parentDocument = null;
        try {
            parentDocument = xwiki.getDocument(parentReference, xcontext);
        } catch (XWikiException e) {
            String message = String.format("Error while trying to get a parent document '%s' to gather feedbacks.",
                parentReference.toString());
            logger.error(message, e);
            return null;
        }
        BaseObject artifactObject = parentDocument.getXObject(classReference);
        return Integer.parseInt(artifactObject.getStringValue(propertyName));
    }

    private Feedbacks getFeedbackList(String modelSetId)
    {
        XWikiContext xcontext = xcontextProvider.get();
        XWiki xwiki = xcontext.getWiki();
        DocumentReference classReference = stringDocumentReferenceResolver.resolve(FEEDBACK_CLASS);
        List<Object> documentNames = getFeedbacksDocuments(modelSetId);
        List<Feedback> feedbacksList = new ArrayList<Feedback>();
        for (Object documentName : documentNames) {
            DocumentReference documentReference = stringDocumentReferenceResolver.resolve((String) documentName);

            XWikiDocument document;
            try {
                document = xwiki.getDocument(documentReference, xcontext);
            } catch (Exception e) {
                String message =
                    String.format("Error while trying to get document '%s' to gather feedbacks on '%' model.",
                        documentReference.toString(), modelSetId);
                logger.error(message, e);
                return null;
            }
            BaseObject feedbackObject = document.getXObject(classReference);
            Integer id = Integer.parseInt(feedbackObject.getStringValue("id"));
            Integer modelId = feedbackObject.getIntValue("modelid");
            Integer artefactId = feedbackObject.getIntValue("artefactid");
            String content = feedbackObject.getStringValue("description");
            Feedback feedback = new Feedback();
            feedback.setId(id);
            feedback.setModelid(modelId);
            feedback.setArtefactid(artefactId);
            feedback.setValue(content);
            feedbacksList.add(feedback);
        }
        Feedbacks feedbacks = new Feedbacks();
        feedbacks.setFeedback(feedbacksList);
        return feedbacks;
    }

    private Patches getPatchList(String modelSetId)
    {
        Attribute attribute1 = new Attribute();
        attribute1.setId("123");
        attribute1.setName("name");
        attribute1.setValue("adsfdsafY");
        Attribute attribute2 = new Attribute();
        attribute2.setId("234");
        attribute2.setName("xs");
        attribute2.setValue("adsfdsafY");
        Attribute attribute3 = new Attribute();
        attribute3.setId("345");
        attribute3.setName("sd");
        attribute3.setValue("adsfdsafY");
        List<Attribute> attributesList = new ArrayList<Attribute>();
        attributesList.add(attribute1);
        attributesList.add(attribute2);
        attributesList.add(attribute3);
        Artefact artefact1 = new Artefact();
        artefact1.setId("");
        artefact1.setName("sdfds");
        artefact1.setClassName("Task");
        artefact1.setAttribute(attributesList);
        Artefact artefact2 = new Artefact();
        artefact2.setId("123");
        artefact2.setName("sdfasdf");
        artefact2.setClassName("Task");
        artefact2.setAttribute(attributesList);
        Artefact artefact3 = new Artefact();
        artefact3.setId("123");
        artefact3.setName("sdfasdf");
        artefact3.setClassName("End Event");
        Patch patch1 = new Patch();
        patch1.setType(PatchType.ADD);
        patch1.setId(Integer.parseInt("123"));
        patch1.setModelid(Integer.parseInt("123456"));
        patch1.setArtefact(artefact1);
        Patch patch2 = new Patch();
        patch2.setType(PatchType.EDIT);
        patch2.setId(Integer.parseInt("123"));
        patch2.setModelid(Integer.parseInt("123456"));
        patch2.setArtefact(artefact2);
        Patch patch3 = new Patch();
        patch3.setType(PatchType.DELETE);
        patch3.setId(Integer.parseInt("123"));
        patch3.setModelid(Integer.parseInt("123456"));
        patch3.setArtefact(artefact3);
        List<Patch> patchesList = new ArrayList<Patch>();
        patchesList.add(patch1);
        patchesList.add(patch2);
        patchesList.add(patch3);
        Patches patches = new Patches();
        patches.setPatch(patchesList);
        return patches;
    }

    @Override
    public PFResults getFeedbacks(String modelSetId) throws LpRestException
    {
        PFResults pf = new PFResults();
        pf.setFeedbacks(getFeedbackList(modelSetId));
        pf.setPatches(getPatchList(modelSetId));
        return pf;
    }

    @Override
    public Recommendations getRecommendations(String modelSetId, String artifactId, String userId)
        throws LpRestException
    {
        return this.corefacade.getRecommendations(modelSetId, artifactId, userId);
    }

    private Collection<UserData> getUserProfiles(Collection<String> potentialUsers)
    {
        XWikiContext xcontext = xcontextProvider.get();
        XWiki xwiki = xcontext.getWiki();
        DocumentReference userClassReference = stringDocumentReferenceResolver.resolve(USER_CLASS);
        Collection<UserData> potentialUsersCollection = new ArrayList<UserData>();

        for (String userId : potentialUsers) {
            UserData user = new UserData();
            user.id = userId;

            DocumentReference userReference = stringDocumentReferenceResolver.resolve(userId);
            try {
                XWikiDocument userDocument = xwiki.getDocument(userReference, xcontext);
                if (userDocument != null) {
                    BaseObject userObject = userDocument.getXObject(userClassReference);
                    if (userObject != null) {
                        user.firstName = userObject.getStringValue("first_name");
                        user.lastName = userObject.getStringValue("last_name");
                        user.profileURL = userDocument.getURL("view", xcontext);
                        user.pictureURL =
                            userDocument.getAttachmentURL(userDocument.getStringValue("avatar"), xcontext);
                    }
                }
            } catch (XWikiException e) {
                String message = String.format("Error while trying to get profile information for the user '%s'.",
                    userReference.toString());
                logger.error(message, e);
            }
            potentialUsersCollection.add(user);
        }
        return potentialUsersCollection;
    }

    @Override
    public String startSimulation(String modelId, String currentUser, Collection<String> potentialUsers)
        throws LpRestException
    {
        Collection<UserData> potentialUsersCollection = getUserProfiles(potentialUsers);
        return this.corefacade.startSimulation(modelId, currentUser, potentialUsersCollection);
    }

    @Override
    public void notifyRecommendations(String modelSetId, String simulationid, String userId, Recommendations rec) throws LpRestException
    {
        // TODO Auto-generated method stub
    }
}
