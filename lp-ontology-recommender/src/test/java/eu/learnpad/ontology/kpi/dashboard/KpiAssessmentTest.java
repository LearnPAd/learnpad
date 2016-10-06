/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.wiki.UserActionNotificationLogTest;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static junit.framework.Assert.*;
import org.junit.After;

/**
 * Runs KPI inferencing rules to calculate KPI values for testdata loaded 
 * from defined testdata file (ex. ontology.metamodel.path=/testdata/kpi/kpiTestData.ttl).
 * Expect testdata values for platform actions (user comment and pages) as well as simulation scores.
 * 
 *
 * @author sandro.emmenegger
 */
public class KpiAssessmentTest extends AbstractKpiTest{
    
    private static final Map<String, String> prefixes = new HashMap<>();
     
     static{
        prefixes.put("exec", "http://learnpad.eu/exec#");
        prefixes.put("transfer", "http://learnpad.eu/transfer#");
     }
    
    public KpiAssessmentTest() {
    }
    
    @Test
    public void testRunAssessment() throws RecommenderException {
        OntModel model = latestModel();
        
        testPerformanceValue(model, KPI_LABEL__BP_SCORE, 0);
        
        Individual oneProcessForTesting = getOneProcess(model);
        
        Individual logEntry = createUserActionLog(MODELSET_ID, null, null, TEST_WIKI_PAGE_URI, ResourceType.PAGE, TEST_USER, System.currentTimeMillis(), NotificationActionType.ADDED);
        assertNotNull(logEntry);
        
        testPerformanceValue(model, KPI_LABEL__GLOBAL_ACTIONS, 1);    
        
        Map<String, byte[]> dashboards = KpiDashboard.getInstance().runAssessment();
        assertTrue(dashboards.size() > 0);
        
    }

    private void testPerformanceValue(OntModel model, String labelOfKpi, int expected) throws RecommenderException {
        //instantiation of Inferencer executes KPIs inferencing rules
        new Inferencer(latestModel(), prefixes);

        List<Individual> performanceValues = getPerformanceValuesOfKpi(model, labelOfKpi);
        assertEquals("Performance value for '"+labelOfKpi+"'.", expected, performanceValues.size());
    }
    
    @After
    public void cleanUpTestEntries(){
        super.cleanUp();
    }        
    
    
    private void write(OntModel model, String path, String type){
        try {
            model.writeAll(new FileOutputStream(path), type);
        } catch (IOException ex) {
            Logger.getLogger(UserActionNotificationLogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Individual> getPerformanceValuesOfKpi(OntModel model, String kpiLabel) throws RecommenderException {
        OntClass kpiClass = model.getOntClass(APP.NS.KPI + "KPI");
        List<Individual> allKPIs = OntUtil.getInstances(model, kpiClass);
        Individual kpi = null;
        for (Individual aKpi : allKPIs) {
            if(kpiLabel.equals(aKpi.getLabel(null))){
                kpi = aKpi;
                break;
            }
        }
        if(kpi != null){
            List<Individual> kpiValues = getInstancesWithProperty(kpi.getURI(), APP.NS.LPD + "performanceValueBelongsToBusinessActor", getTestUser());  
            return kpiValues;
        }

        return new ArrayList<>();
    }
    
}
