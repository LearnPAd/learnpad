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
    public void testCreateOrUpdateSimulationSessionCase() {
        String simulationId = "testSimId";
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("applicationCity", "lpd:Senigallia");
        metaData.put("applicationZone", "lpd:Beach_Area_At_The_Sea");
        metaData.put("applicationSubType", "lpd:Restructuring");
        metaData.put("applicationPublicAdministration", "lpd:SUAPSenigallia");
        metaData.put("applicationSector", "lpd:Building_Sector");
        metaData.put("applicationSector", "lpd:Environment_Sector");
        metaData.put("applicationSector", "lpd:Public_Land_Sector");
        metaData.put("applicationSector", "lpd:Tourism_Sector");
        metaData.put("applicationBusinessActivity", "lpd:Receptive_Toursim_Activity");
        metaData.put("applicationATECOCategory", "lpd:MarineAndMountaineSummerCamps");
        metaData.put("applicationATECOCategory", "lpd:MarineAndMountaineSummerCamps");
        metaData.put("applicationATECOCategory", "lpd:MarineAndMountaineSummerCamps");
        metaData.put("applicationATECOCategory", "lpd:CampingGrounds_RecreationalVehiclesAndTrailers");
        metaData.put("applicationATECOCategory", "lpd:PlasterAndStucco");
        metaData.put("applicationATECOCategory", "lpd:SpecializedConstructionActivities");
        
        Map<String, Object> userData = null;
        CBRAdapter instance = CBRAdapter.getInstance();
        SimulationData simData = new SimulationData();
        simData.setSessionData(metaData);
        simData.setSubmittedData(userData);
        CaseInstanceVO result = instance.createOrUpdateSimulationSessionCase(simulationId, simData);
        assertNotNull(result);
        
        SimilarCases similarCases = instance.retrieveSimilarCases("modelSetId", "", "barneby.barnes@learnpad.eu", simulationId);
        assertNotNull(similarCases);
        assertNotNull(similarCases.getSimilarCases());
        assertEquals(3, similarCases.getSimilarCases().size());
        
        for (SimilarCase similarCase : similarCases.getSimilarCases()) {
            System.out.println("Case: " + similarCase.getName() + " " + similarCase.getSimilarityValue() + "\n________________________________\n");
            for(Map.Entry data : similarCase.getData().entrySet()){
                System.out.println(data.getKey()+": "+data.getValue());
            }
        }
        
    }
    
}
