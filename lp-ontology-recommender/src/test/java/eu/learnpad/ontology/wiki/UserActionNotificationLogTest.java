/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.wiki;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import java.util.List;
import java.util.UUID;
import static junit.framework.Assert.*;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class UserActionNotificationLogTest extends AbstractUnitTest {

    public UserActionNotificationLogTest() {
    }

    @Test
    public void testLogResourceNotification() throws RecommenderException {
        String latestModelSetVersion = SimpleModelTransformator.getInstance().getLatestModelSetId();
        assertNotNull(latestModelSetVersion);
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(latestModelSetVersion);
        new Inferencer(model);
        
        //test page created
        String pageUrl = "http://learnpad.eu/unittest/NotificationLogTest_Page"+UUID.randomUUID();
        OntClass pageClass = model.getOntClass(APP.NS.XWIKI + "Page");
        OntProperty pageUrlProperty = model.getOntProperty(APP.NS.XWIKI + "pageHasURL");
        Literal value = model.createTypedLiteral(pageUrl);
        List<Individual> pageInstancs = OntUtil.getInstancesWithProperty(model, pageClass, pageUrlProperty, value);
        assertNotNull(pageInstancs);
        assertEquals(0, pageInstancs.size());
        
        Long timestamp = System.currentTimeMillis();
        UserActionNotificationLog.getInstance().logResourceNotification(MODELSET_ID, null, null, pageUrl, ResourceType.PAGE, null, TEST_USER, timestamp, NotificationActionType.ADDED);

        pageInstancs = OntUtil.getInstancesWithProperty(model, pageClass, pageUrlProperty, value);
        assertNotNull(pageInstancs);
        assertEquals(1, pageInstancs.size());
        Individual pageInstance = pageInstancs.get(0);
        
        //test page log created
        testLogCreated(model, pageInstance, "added", TEST_USER, 1);
        
        //test comment log created
        UserActionNotificationLog.getInstance().logResourceNotification(MODELSET_ID, null, null, "1", ResourceType.COMMENT, pageUrl, TEST_USER, timestamp, NotificationActionType.ADDED);
        
        OntClass commentClass = model.getOntClass(APP.NS.XWIKI + "Comment");
        OntProperty referedPageUrlProperty = model.getOntProperty(APP.NS.XWIKI + "annotationIsMadeToPage");
        List<Individual> commentInstancs = OntUtil.getInstancesWithProperty(model, commentClass, referedPageUrlProperty, pageInstance);

        assertNotNull(commentInstancs);
        assertEquals(1, commentInstancs.size());

        //test comment log created
        testLogCreated(model, commentInstancs.get(0), "added", TEST_USER, 1);
    }

    private void testLogCreated(OntModel model, Individual instance, String actionType, String userId, int expected) {
        
        OntClass actionHistoryClass = model.getOntClass(APP.NS.XWIKI + "ActionHistory");
        OntProperty actionAppliedToResourceProp = model.getOntProperty(APP.NS.XWIKI + "actionAppliedToResource");
        List<Individual> resourceHistInstancs = OntUtil.getInstancesWithProperty(model, actionHistoryClass, actionAppliedToResourceProp, instance);

        assertNotNull(resourceHistInstancs);
        assertEquals(expected, resourceHistInstancs.size());
        Individual resourceHistInstance = resourceHistInstancs.get(0);
        OntProperty actionTypeProp = model.getOntProperty(APP.NS.XWIKI + "actionType");
        assertTrue(resourceHistInstance.hasProperty(actionTypeProp));
        assertEquals(actionType, resourceHistInstance.getPropertyValue(actionTypeProp).asLiteral().getString());

        //Test if user assigned to history
        if (userId != null) {
            OntProperty userProp = model.getOntProperty(APP.NS.XWIKI + "user");
            assertTrue(resourceHistInstance.hasProperty(userProp));
            Resource user = resourceHistInstance.getPropertyResourceValue(userProp);
            Individual userInstance = user.as(Individual.class);
            assertNotNull(userInstance);
            OntProperty emailProp = model.getOntProperty(APP.NS.EMO + "performerHasEmailAddress");
            assertNotNull(emailProp);
            assertTrue(userInstance.hasProperty(emailProp));
            assertEquals(userId, userInstance.getPropertyValue(emailProp).asLiteral().getString());
        }

    }
    

}
