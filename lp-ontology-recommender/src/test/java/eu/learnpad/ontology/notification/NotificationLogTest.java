/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.notification;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.kpi.dashboard.KpiDashboard;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.*;
import org.apache.jena.riot.Lang;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class NotificationLogTest extends AbstractUnitTest {

    public NotificationLogTest() {
    }

    @Test
    public void testLogResourceNotification() {
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(SimpleModelTransformator.getInstance().getLatestModelSetId());
        new Inferencer(model);
        
        //test page created
        String pageUrl = "http://learnpad.eu/unittest/TestPage";
        Long timestamp = System.currentTimeMillis();
        NotificationLog.getInstance().logResourceNotification(MODELSET_ID, pageUrl, ResourceType.PAGE, null, null, TEST_USER, timestamp, NotificationActionType.ADDED);

        OntClass pageClass = model.getOntClass(APP.NS.XWIKI + "Page");
        OntProperty pageUrlProperty = model.getOntProperty(APP.NS.XWIKI + "pageHasURL");
        Literal value = model.createTypedLiteral(pageUrl);
        List<Individual> pageInstancs = OntUtil.getInstancesWithProperty(model, pageClass, pageUrlProperty, value);

        assertNotNull(pageInstancs);
        assertEquals(1, pageInstancs.size());
        Individual pageInstance = pageInstancs.get(0);

        //test page log created
        testLogCreated(model, pageInstance, "added", TEST_USER);
        
        //test comment log created
        NotificationLog.getInstance().logResourceNotification(MODELSET_ID, "1", ResourceType.COMMENT, pageUrl, null, TEST_USER, timestamp, NotificationActionType.ADDED);
        
        OntClass commentClass = model.getOntClass(APP.NS.XWIKI + "Comment");
        OntProperty referedPageUrlProperty = model.getOntProperty(APP.NS.XWIKI + "annotationIsMadeToPage");
        List<Individual> commentInstancs = OntUtil.getInstancesWithProperty(model, commentClass, referedPageUrlProperty, pageInstance);

        assertNotNull(commentInstancs);
        assertEquals(1, commentInstancs.size());

        //test comment log created
        testLogCreated(model, commentInstancs.get(0), "added", TEST_USER);
        
        //assess KPIs 
        Map<String, String> prefixes = new HashMap<>();
        prefixes.put("exec", "http://learnpad.eu/exec#");
        prefixes.put("transfer", "http://learnpad.eu/transfer#");
        Inferencer kpiInferencer = new Inferencer(model, prefixes);

        ExtendedIterator<Individual> listOfInferedInstances = kpiInferencer.getModel().listIndividuals();
        boolean valueFound = false;
        while(listOfInferedInstances.hasNext()){
            Individual next = listOfInferedInstances.next();
            if(next.getURI().endsWith("_value")){
                valueFound = true;
            }
        }
        assertTrue(valueFound);
        
//        try {        
//            kpiInferencer.getModel().writeAll(new FileOutputStream("C:/temp/kpi_testdata_export3.xml"), Lang.RDFXML.getName());
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(KpiDashboard.class.getName()).log(Level.SEVERE, null, ex);
//        }             
        
        KpiDashboard.getInstance().runAssessment();


    }

    private void testLogCreated(OntModel model, Individual instance, String actionType, String userId) {
        
        OntClass actionHistoryClass = model.getOntClass(APP.NS.XWIKI + "ActionHistory");
        OntProperty actionAppliedToResourceProp = model.getOntProperty(APP.NS.XWIKI + "actionAppliedToResource");
        List<Individual> resourceHistInstancs = OntUtil.getInstancesWithProperty(model, actionHistoryClass, actionAppliedToResourceProp, instance);

        assertNotNull(resourceHistInstancs);
        assertEquals(1, resourceHistInstancs.size());
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
    
    private void write(OntModel model, String path){
        try {
            model.write(new FileWriter(path), "TTL");
        } catch (IOException ex) {
            Logger.getLogger(NotificationLogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
