/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import java.util.HashMap;
import java.util.Map;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.recommender.cbr.CBRAdapter;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.LearningMaterials;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.SimulationData;

import javax.xml.bind.JAXB;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger, gulyx
 */
public class RecommenderTest extends AbstractUnitTest {

    @Before
    public void init() {
        SimpleModelTransformator.getInstance();
    }

    @Test
    public void testRecommendations() throws RecommenderException {
        Recommendations recomms = Recommender.getInstance().getRecommendations(MODELSET_ID, APP.CONF.getString("testdata.artifactId"), APP.CONF.getString("testdata.user.email"));
        assertNotNull(recomms);
        JAXB.marshal(recomms, System.out);        
        Experts experts = recomms.getExperts();
        assertNotNull(experts);
        assertTrue(experts.getBusinessActors().size() > 0);
        
        LearningMaterials materials = recomms.getLearningMaterials();
        assertNotNull(materials);
        assertTrue(materials.getLearningMaterials().size() > 0);
        
        boolean lineManagerExpert = false;
        boolean otherUnitExpert = false;
        boolean mostOftenExecutedTaskExpert = false;
        for (BusinessActor businessActor : experts.getBusinessActors()) {
            if(businessActor.getDescription().equals(QueryMap.getQuery(Recommender.QUERY_LINEMANAGER_AS_EXPERT).getDescription())){
                lineManagerExpert = true;
            }
            if(businessActor.getDescription().equals(QueryMap.getQuery(Recommender.QUERY_EXPERTS_WITH_SAME_ROLE).getDescription())){
                otherUnitExpert = true;
            }
            if(businessActor.getDescription().equals(QueryMap.getQuery(Recommender.QUERY_EXPERT_MOST_OFTEN_EXECUTED_TASK).getDescription())){
                mostOftenExecutedTaskExpert = true;
            }            
        }
        
        assertTrue(lineManagerExpert);
        assertTrue(otherUnitExpert);
        
        //depends on execution testdata which is assigned to the latest loaded modelset and their object.id's  -> difficult to keep that up to date
//        assertTrue(mostOftenExecutedTaskExpert);
        
    }
    
    @Test
    public void testMarshallingRecommendationsWithDemoData() throws RecommenderException{
    	String modelSetId = MODELSET_ID;
// At the time we wrote this test the parameter "artifactId" is not actually considered by the OR component    
    	String artifactId = "process_27772";
    	String userId = APP.CONF.getString("testdata.user.email");
    	String simulationSessionId = "9bc242fa-cb5f-4be3-a473-a8f5ec12049a";
    	
    	Map<String, Object> simulationSessionData = new HashMap<String,Object>();
    	simulationSessionData.put("applicationCity","lpd:Belforte_del_Chienti");
    	simulationSessionData.put("applicationATECOCategory","28.93");
    	simulationSessionData.put("applicationSubType","lpd:Reactivation");
    	simulationSessionData.put("applicationDescription","request for reneval of authorization of industrial waste water discharge in sewer - coffee machines factory");
    	simulationSessionData.put("applicationZone","lpd:Regional_Protected_Area_Unione_Montana_Monti_Azzurri");
    	simulationSessionData.put("applicationPublicAdministration","lpd:SUAPMontiAzzurri");
    	simulationSessionData.put("applicationBusinessActivity","lpd:Industrial");
    	simulationSessionData.put("case","637-2015");
    	simulationSessionData.put("applicationSector","lpd:Waste_Sector");
    	simulationSessionData.put("applicantName","Ottavio Nandi");
    	simulationSessionData.put("__LEARNPAD_SIMULATION_ID",simulationSessionId);

    	SimulationData simData = new SimulationData();
    	simData.setSessionData(simulationSessionData);
    	simData.setSubmittedData(new HashMap<String, Object>());
    	
    	CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationSessionId, simData);
    	
    	Recommender rec = Recommender.getInstance();
        Recommendations data = rec.getRecommendations(modelSetId, artifactId, userId, simulationSessionId);
        
        JAXB.marshal(data, System.out); 
        
        assertTrue(true);
    }

}
