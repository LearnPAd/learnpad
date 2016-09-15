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
        
        //Create PB score
        Long timestamp = System.currentTimeMillis();
        String simulationSessionId = UUID.randomUUID().toString();
        String processArtifactId = oneProcessForTesting.getLocalName();
        Float score = 4.8f;
        Individual logEntry = createSimScoreLog(timestamp, simulationSessionId, MODELSET_ID, processArtifactId, TEST_USER_EMAIL, ScoreType.BP_SCORE, score);
        assertNotNull(logEntry);
        
        scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect a business process simulation score value created for testuser. ", 1, scores.size());
        
        checkCommonScoreProperties(model, scores.get(0), score, timestamp);
        
        
        //Create session score
        timestamp = System.currentTimeMillis();
        score = 6.2f;
        logEntry = createSimScoreLog(timestamp, simulationSessionId, MODELSET_ID, processArtifactId, TEST_USER_EMAIL, ScoreType.SESSION_SCORE, score);
        assertNotNull(logEntry);
        
        scores = getInstancesWithProperty(APP.NS.LPD + "SimulationSessionScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect one simulation session score value created for testuser. ",  1, scores.size());
        
        checkCommonScoreProperties(model, scores.get(0), score, timestamp);
        
        //test if still one pb score
        scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect a business process simulation score value created for testuser. ", 1, scores.size());
        
        //Create global score
        timestamp = System.currentTimeMillis();
        score = 8.4f;
        logEntry = createSimScoreLog(timestamp, simulationSessionId, MODELSET_ID, processArtifactId, TEST_USER_EMAIL, ScoreType.GLOBAL_SCORE, score);
        assertNotNull(logEntry);
        
        scores = getInstancesWithProperty(APP.NS.LPD + "GlobalSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect one global score value created for testuser. ", 1, scores.size());
        
        checkCommonScoreProperties(model, scores.get(0), score, timestamp);
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
