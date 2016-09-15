/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.simulation;

import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.RDFNode;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.kpi.dashboard.AbstractKpiTest;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.sim.rest.event.ScoreType;
import java.util.List;
import static junit.framework.Assert.*;
import org.jgroups.util.UUID;
import org.junit.After;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class SimulationScoreLogTest extends AbstractKpiTest {
    
    public SimulationScoreLogTest() {
    }

    @Test
    public void testLogSimulationScore() throws RecommenderException {
        
        cleanUpDataFile();
        
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID);
        
        Individual testUser = getTestUser();
        List<Individual> scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expected no scores up to now. ", 0, scores.size());
        
        OntClass processClass = model.getOntClass(APP.NS.BPMN + "Process");
        List<Individual> processes = OntUtil.getInstances(model, processClass);
        if(processes.isEmpty()){
            fail("Expect at least one process model ontology for unit testing.");
        }
        Individual oneProcessForTesting = processes.get(0);
        
        //Log simulation scores
        Long timestamp = System.currentTimeMillis();
        String simulationSessionId = UUID.randomUUID().toString();
        String processArtifactId = oneProcessForTesting.getLocalName();
        SimulationScoreLog.getInstance().logSimulationScore(timestamp, simulationSessionId, MODELSET_ID, processArtifactId, TEST_USER, SIM_TEST_SCORES);
        
        //check BP score
        scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect a business process simulation score value created for testuser. ", 1, scores.size());
        
        Float expectedScore = (SIM_TEST_SCORES.get(ScoreType.BP_SCORE)/SIM_TEST_SCORES.get(ScoreType.ABSOLUTE_BP_SCORE))*100;
        checkCommonScoreProperties(model, scores.get(0), expectedScore, timestamp);
        
        //check session score
        scores = getInstancesWithProperty(APP.NS.LPD + "SimulationSessionScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect one simulation session score value created for testuser. ",  1, scores.size());
        
        expectedScore = (SIM_TEST_SCORES.get(ScoreType.SESSION_SCORE)/SIM_TEST_SCORES.get(ScoreType.ABSOLUTE_SESSION_SCORE))*100;
        checkCommonScoreProperties(model, scores.get(0), expectedScore, timestamp);
        
        //check global score
        scores = getInstancesWithProperty(APP.NS.LPD + "GlobalSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect one global score value created for testuser. ", 1, scores.size());
        
        expectedScore = (SIM_TEST_SCORES.get(ScoreType.GLOBAL_SCORE)/SIM_TEST_SCORES.get(ScoreType.ABSOLUTE_GLOBAL_SCORE))*100;
        checkCommonScoreProperties(model, scores.get(0), expectedScore, timestamp);
    }
    
    @After
    public void cleanUpTestEntries(){
        super.cleanUpSimScoreLogs();
    }    
    
    private void checkCommonScoreProperties(OntModel model, Individual scoreValueInstance, Float score, Long timestamp){
        //score value
        OntProperty ontProperty = model.getOntProperty(APP.NS.LPD + "hasSimulationScore");
        RDFNode value = scoreValueInstance.getPropertyValue(ontProperty);
        assertNotNull(value);
        assertEquals(score, value.asLiteral().getFloat());
        
        //timestamp
        ontProperty = model.getOntProperty(APP.NS.LPD + "simulationScoreCreatedAt");
        value = scoreValueInstance.getPropertyValue(ontProperty);
        assertNotNull(value);
        assertEquals(timestamp.longValue(), ((XSDDateTime)value.asLiteral().getValue()).asCalendar().getTimeInMillis());
        
    }
    
}
