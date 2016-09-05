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
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.SimilarCase;
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
    public void testMarshallingRecommendationsWithDemoData() throws RecommenderException{
    	String modelSetId = MODELSET_ID;
// At the time we wrote this test the parameter "artifactId" is not actually considered by the OR component    
    	String artifactId = "process_27772";
    	String userId = APP.CONF.getString("testdata.user.email");
    	String simulationSessionId = "9bc242fa-cb5f-4be3-a473-a8f5ec12049a";
    	
//    	Map<String, Object> simulationSessionData = DATA_SET_1(simulationSessionId);
//    	Map<String, Object> simulationSessionData = DATA_SET_2();
    	Map<String, Object> simulationSessionData = DATA_SET_3();
    	
    	SimulationData simData = new SimulationData();
    	simData.setSessionData(simulationSessionData);
    	simData.setSubmittedData(new HashMap<String, Object>());
    	
    	CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationSessionId, simData);
    	
    	Recommender rec = Recommender.getInstance();
        Recommendations data = rec.getRecommendations(modelSetId, artifactId, userId, simulationSessionId);
        
        JAXB.marshal(data, System.out); 
        
        assertTrue(true);
    }
    

    @Test
    public void printGenratedSimilarCase() throws RecommenderException{
    	boolean badValueFound = false;
    	
    	String modelSetId = MODELSET_ID;
// At the time we wrote this test the parameter "artifactId" is not actually considered by the OR component    
    	String artifactId = "process_27772";
    	String userId = APP.CONF.getString("testdata.user.email");
//    	String simulationSessionId = "9bc242fa-cb5f-4be3-a473-a8f5ec12049a";
    	
    	System.out.println("Similar Case for DATA_SET_2 ");
    	String simulationSessionId = "a8f5ec12049a";
    	Map<String, Object> simulationSessionData = DATA_SET_2();
    	SimulationData simData = new SimulationData();
    	simData.setSessionData(simulationSessionData);
    	simData.setSubmittedData(new HashMap<String, Object>());    	    	
    	
    	CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationSessionId, simData);    	
    	Recommender rec = Recommender.getInstance();
    	
        Recommendations recData = rec.getRecommendations(modelSetId, artifactId, userId, simulationSessionId);
        for (SimilarCase sc : recData.getSimilarCases().getSimilarCases()) {
        	System.out.println("Similar Case : " + sc.getName() + "-->" + sc.getSimilarityValue());
        	badValueFound = ((badValueFound) || (sc.getName().startsWith("Simulation Case")));
		}
        
    	System.out.println("Similar Case for DATA_SET_3");
    	simulationSessionId = "9bc242fa";
        simulationSessionData = DATA_SET_3();    	
    	simData = new SimulationData();
    	simData.setSessionData(simulationSessionData);
    	simData.setSubmittedData(new HashMap<String, Object>());
    	
    	CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationSessionId, simData);    	
    	rec = Recommender.getInstance();
    	
    	recData = rec.getRecommendations(modelSetId, artifactId, userId, simulationSessionId);
        for (SimilarCase sc : recData.getSimilarCases().getSimilarCases()) {
        	System.out.println("Similar Case : " + sc.getName() + "-->" + sc.getSimilarityValue());
        	badValueFound = ((badValueFound) || (sc.getName().startsWith("Simulation Case")));
		}
    	
    	System.out.println("Similar Case for DATA_SET_2");
    	simulationSessionId = "4be3-a473";
    	simulationSessionData = DATA_SET_2();    	
    	simData = new SimulationData();
    	simData.setSessionData(simulationSessionData);
    	simData.setSubmittedData(new HashMap<String, Object>());
    	
    	CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationSessionId, simData);    	
    	rec = Recommender.getInstance();
    	
        recData = rec.getRecommendations(modelSetId, artifactId, userId, simulationSessionId);
        for (SimilarCase sc : recData.getSimilarCases().getSimilarCases()) {
        	System.out.println("Similar Case : " + sc.getName() + "-->" + sc.getSimilarityValue());
        	badValueFound = ((badValueFound) || (sc.getName().startsWith("Simulation Case")));
		}
        
        assertFalse(badValueFound);
    }
    
    
	private final Map<String, Object> DATA_SET_1(String simulationSessionId) {
		Map<String, Object> metaData = new HashMap<String, Object>();
		metaData.put("applicationCity", "lpd:Belforte_del_Chienti");
		metaData.put("applicationATECOCategory", "28.93");
		metaData.put("applicationSubType", "lpd:Reactivation");
		metaData.put("applicationDescription", "request for reneval of authorization of industrial waste water discharge in sewer - coffee machines factory");
		metaData.put("applicationZone", "lpd:Regional_Protected_Area_Unione_Montana_Monti_Azzurri");
		metaData.put("applicationPublicAdministration", "lpd:SUAPMontiAzzurri");
		metaData.put("applicationBusinessActivity", "lpd:Industrial");
		metaData.put("case", "637-2015");
		metaData.put("applicationSector", "lpd:Waste_Sector");
		metaData.put("applicantName", "Ottavio Nandi");
		metaData.put("__LEARNPAD_SIMULATION_ID", simulationSessionId);

		return metaData;
	}
    
    private final Map<String, Object> DATA_SET_2() {
        Map<String, Object> metaData = new HashMap<>();
                metaData.put("applicationCity", "lpd:Ancona");
                metaData.put("applicationZone", "lpd:Beach_Area_At_The_Sea");
                metaData.put("applicationPublicAdministration", "lpd:SUAPSenigallia");
                metaData.put("applicationSubType", "lpd:Restructuring");
                metaData.put("applicationSector", "lpd:Building_Sector");
                metaData.put("applicationBusinessActivity", "lpd:Receptive_Toursim_Activity");
                metaData.put("applicationDescription", "Realization of a chalet on a beach area of Senigallia");
                metaData.put("applicationATECOCategory", "lpd:MarineAndMountaineSummerCamps");        

        return metaData;
    }    

    private final Map<String, Object> DATA_SET_3() {
        Map<String, Object> metaData = new HashMap<>();
    
        metaData.put("applicationDescription", "request for reneval of authorization of industrial waste water discharge in sewer - coffee machines factory");
        metaData.put("applicationCity", "lpd:San_Ginesio");
        metaData.put("applicationZone", "lpd:Regional_Protected_Area_Unione_Montana_Monti_Azzurri");
        metaData.put("applicationSubType", "lpd:Reactivation");
        metaData.put("applicationPublicAdministration", "lpd:SUAPMontiAzzurri");
        metaData.put("applicationSector", "lpd:Waste_Sector");
        metaData.put("applicationBusinessActivity", "lpd:Industrial_Activitiy");
        metaData.put("applicationATECOCategory", "lpd:InstallationOfElectricalSystems");
        return metaData;
    }


}
