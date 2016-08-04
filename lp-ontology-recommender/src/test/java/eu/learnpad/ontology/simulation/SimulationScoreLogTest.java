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
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.or.rest.data.SimulationScoreType;
import java.util.List;
import static junit.framework.Assert.*;
import org.jgroups.util.UUID;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class SimulationScoreLogTest extends AbstractUnitTest {
    
    public SimulationScoreLogTest() {
    }

    @Test
    public void testLogSimulationScore() throws RecommenderException {
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID);
        
        //This has to be in synch with the testdata loaded from file with location defined in property (ex. ontology.metamodel.path=/testdata/kpi/kpidemo.ttl): 
        int alreadyLoadedBPScores = 2;
        int alreadyLoadedGlobalScores = 1;
        int alreadyLoadeSimScores = 0;
        
        Individual testUser = getTestUser();
        List<Individual> scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expected process scores in ontology for given test user loaded via testdata file. ", alreadyLoadedBPScores, scores.size());
        
        OntClass processClass = model.getOntClass(APP.NS.BPMN + "Process");
        List<Individual> processes = OntUtil.getInstances(model, processClass);
        if(processes.isEmpty()){
            fail("Expect at least one process model ontology for unit testing.");
        }
        
        Individual oneProcessForTesting = processes.get(0);
        int scoresBelongingToBPs = 0;
        for (Individual process : processes) {
            scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreBelongsToBusinessProcess", process);
            if(scores.size() > 0){
                scoresBelongingToBPs += scores.size();
            }
        }
        assertEquals("Expected process scores in ontology loaded via testdata file.  ", alreadyLoadedBPScores, scoresBelongingToBPs);
        
        //Create PB score
        Long timestamp = System.currentTimeMillis();
        String simulationSessionId = UUID.randomUUID().toString();
        String processArtifactId = oneProcessForTesting.getLocalName();
        Float score = 4.8f;
        SimulationScoreLog.getInstance().logSimulationScore(timestamp, simulationSessionId, MODELSET_ID, processArtifactId, TEST_USER, SimulationScoreType.BP_SCORE, score);
        
        scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect a business process simulation score value created for testuser. ", alreadyLoadedBPScores + 1, scores.size());
        
        checkCommonScoreProperties(model, scores.get(0), score, timestamp);
        
        
        //Create session score
        timestamp = System.currentTimeMillis();
        score = 6.2f;
        SimulationScoreLog.getInstance().logSimulationScore(timestamp, simulationSessionId, MODELSET_ID, processArtifactId, TEST_USER, SimulationScoreType.SESSION_SCORE, score);
        
        scores = getInstancesWithProperty(APP.NS.LPD + "SimulationSessionScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect one simulation session score value created for testuser. ", alreadyLoadeSimScores + 1, scores.size());
        
        checkCommonScoreProperties(model, scores.get(0), score, timestamp);
        
        //test if still one pb score
        scores = getInstancesWithProperty(APP.NS.LPD + "BPSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect a business process simulation score value created for testuser. ", alreadyLoadedBPScores + 1, scores.size());
        
        //Create global score
        timestamp = System.currentTimeMillis();
        score = 8.4f;
        SimulationScoreLog.getInstance().logSimulationScore(timestamp, simulationSessionId, MODELSET_ID, processArtifactId, TEST_USER, SimulationScoreType.GLOBAL_SCORE, score);
        
        scores = getInstancesWithProperty(APP.NS.LPD + "GlobalSimulationScore", APP.NS.LPD + "simulationScoreOfPerformer", testUser);
        assertEquals("Expect one global score value created for testuser. ", alreadyLoadedGlobalScores + 1, scores.size());
        
        checkCommonScoreProperties(model, scores.get(0), score, timestamp);
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
