/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender.cbr;

import ch.fhnw.cbr.service.data.CaseInstanceVO;
import eu.learnpad.or.rest.data.SimilarCase;
import eu.learnpad.or.rest.data.SimilarCases;
import eu.learnpad.or.rest.data.SimulationData;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public class CBRAdapterTest {

    @Test
    public void testCBR() {
        String simulationId = "d6983703-5c42-4512-bc80-efde3756259d";
        
        testWithTestSet(DATA_SET_1(), simulationId);
        testWithTestSet(DATA_SET_2(), simulationId);
    }

    private void testWithTestSet(Map<String, Object> metaData, String simulationId) {
        Map<String, Object> userData = null;
        CBRAdapter instance = CBRAdapter.getInstance();
        SimulationData simData = new SimulationData();
        simData.setSessionData(metaData);
        simData.setSubmittedData(userData);
        CaseInstanceVO result = instance.createOrUpdateSimulationSessionCase(simulationId, simData);
        assertNotNull(result);
        
        SimilarCases similarCases = instance.retrieveSimilarCases("modelSetId", "", "barneby.barnes@learnpad.eu", simulationId);
        checkRetrievedCases(similarCases, "Case A without third parties and decision");
        
        userData = new HashMap();
        userData.put("decision", "false");
        simData.setSubmittedData(userData);
        instance.createOrUpdateSimulationSessionCase(simulationId, simData);
        checkRetrievedCases(similarCases, "Case A with decision");
        
        
        //add some third parties to the query
        userData.put("involvedThirdParties", "lpd:Building_office");
        userData.put("involvedThirdParties", "lpd:Environment_office");
        simData.setSubmittedData(userData);
        instance.createOrUpdateSimulationSessionCase(simulationId, simData);        
        checkRetrievedCases(similarCases, "Case A with decision and third parties");
    }

    private void checkRetrievedCases(SimilarCases similarCases, String testName) {
        assertNotNull(similarCases);
        assertNotNull(similarCases.getSimilarCases());
        assertEquals(3, similarCases.getSimilarCases().size());
        System.out.println("Test: "+testName);
        for (SimilarCase similarCase : similarCases.getSimilarCases()) {
            System.out.println("\n________________________________\nCase: " + similarCase.getName() + " " + similarCase.getSimilarityValue());
            for(Map.Entry data : similarCase.getData().entrySet()){
                System.out.println(data.getKey()+": "+data.getValue());
            }
        }
    }

    private final Map<String, Object> DATA_SET_1() {
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
}
