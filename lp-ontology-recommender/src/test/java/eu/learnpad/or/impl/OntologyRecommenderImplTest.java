/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.impl;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.or.rest.data.Entities;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessId;
import eu.learnpad.sim.rest.event.ScoreType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public class OntologyRecommenderImplTest extends AbstractUnitTest {

    public OntologyRecommenderImplTest() {
    }

    @Test
    public void testInitialize() throws Exception {
        OntologyRecommenderImpl instance = new OntologyRecommenderImpl();
        instance.initialize();
    }

    @Test
    public void testAskRecommendation() throws LpRestException {
        OntologyRecommenderImpl instance = new OntologyRecommenderImpl();
        instance.askRecommendation(MODELSET_ID, null, TEST_USER, "test");
    }

    @Test
    public void testCalculateKPI() throws Exception {
        OntologyRecommenderImpl instance = new OntologyRecommenderImpl();
        instance.initialize();
        KBProcessId processId = instance.calculateKPI(MODELSET_ID);
        assertNotNull(processId);
        assertTrue(processId.getId().startsWith("KPI_"));
    }

    @Test
    public void testAnalyseText() throws Exception {
        String modelSetId = MODELSET_ID;
        String artifactId = "obj.22332";
        String userId = "b.barnes@learnpad.eu";
        String title = "Organize Service Conference Task";
        String text = "The activity <i>Organize Conference</i> specified by Sally Shugar should be <b>splitted</b> into 2 activities.";
        OntologyRecommenderImpl instance = new OntologyRecommenderImpl();
        Entities result = instance.analyseText(modelSetId, artifactId, userId, title, text);
        assertNotNull(result);
        assertNotNull(result.getEntities());
        assertTrue(result.getEntities().size() > 0);

        JAXBContext context = JAXBContext.newInstance(Entities.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(result, System.out);

    }

//    @Test
//    public void testSimulationScoreLog() {
//        Long timestamp = System.currentTimeMillis();
//        Float score = 4.8f;
//        OntologyRecommenderImpl instance = new OntologyRecommenderImpl(); 
//        try {
//            instance.updateSimulationScore(MODELSET_ID, "fakeSessionId", null, timestamp, TEST_USER, ScoreType.BP_SCORE, score);
//            fail("Expected LpRestException due to null value for processId.");
//        } catch (LpRestException ex) {}
//
//        try {
//            instance.updateSimulationScore(MODELSET_ID, null, "fakeProcessId", timestamp, TEST_USER, ScoreType.BP_SCORE, score);
//            fail("Expected LpRestException due to null value for sessionId.");
//        } catch (LpRestException ex) {}   
//    }
}
