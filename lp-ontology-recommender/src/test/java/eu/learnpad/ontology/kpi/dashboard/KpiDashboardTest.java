/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import eu.learnpad.ontology.wiki.UserActionNotificationLogTest;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.recommender.RecommenderException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertTrue;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;

/**
 *
 * @author sandro.emmenegger
 */
public class KpiDashboardTest extends AbstractKpiTest{
    
    public KpiDashboardTest() {
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
    
    
    private void write(OntModel model, String path, String type){
        try {
            model.writeAll(new FileOutputStream(path), type);
        } catch (IOException ex) {
            Logger.getLogger(UserActionNotificationLogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
