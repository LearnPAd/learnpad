/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.notification.NotificationLog;
import eu.learnpad.ontology.notification.NotificationLogTest;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author sandro.emmenegger
 */
public class KpiDashboardTest extends AbstractUnitTest{
    
    public KpiDashboardTest() {
    }
    
    @Before
    public void setupTestData() throws RecommenderException{
        
        new Inferencer(getLatestModel());
       
        String pageUrl = "http://learnpad.eu/unittest/TestPage";
        Long timestamp = System.currentTimeMillis();
        NotificationLog.getInstance().logResourceNotification(MODELSET_ID, pageUrl, ResourceType.PAGE, null, null, TEST_USER, timestamp, NotificationActionType.ADDED);

        NotificationLog.getInstance().logResourceNotification(MODELSET_ID, "1", ResourceType.COMMENT, pageUrl, null, TEST_USER, timestamp, NotificationActionType.ADDED);
        OntModel model = getLatestModel();
    }

    @Test
    public void testRunAssessment() throws RecommenderException {
        
        
        //assess KPIs 
        Map<String, String> prefixes = new HashMap<>();
        prefixes.put("exec", "http://learnpad.eu/exec#");
        prefixes.put("transfer", "http://learnpad.eu/transfer#");
        Inferencer kpiInferencer = new Inferencer(getLatestModel(), prefixes);

        ExtendedIterator<Individual> listOfInferedInstances = kpiInferencer.getModel().listIndividuals();
        boolean valueFound = false;
        while(listOfInferedInstances.hasNext()){
            Individual next = listOfInferedInstances.next();
            if(next.getURI().endsWith("_value")){
                valueFound = true;
            }
        }
        assertTrue(valueFound);
        
//        write(kpiInferencer.getModel(), "C:/Data/Projects/LearnPad/D5.4/testdata/kpi_testdata_export.xml", "RDF/XML");
        
        Map<String, byte[]> dashboards = KpiDashboard.getInstance().runAssessment();
        assertTrue(dashboards.size() > 0);
        
        
    }
    
    private OntModel getLatestModel() throws RecommenderException {
        String latestModelSetVersion = SimpleModelTransformator.getInstance().getLatestModelSetId();
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(latestModelSetVersion);
        return model;
    }    
    
    private void write(OntModel model, String path, String type){
        try {
            model.writeAll(new FileOutputStream(path), type);
        } catch (IOException ex) {
            Logger.getLogger(NotificationLogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
