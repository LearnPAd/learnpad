/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.impl;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.or.rest.data.Entities;
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
    public void testModelSetImported() throws Exception {

    }

    @Test
    public void testSendResourceNotification() throws Exception {
    }

    @Test
    public void testAskRecommendation() throws Exception {
    }

    @Test
    public void testSimulationInstanceNotification() throws Exception {
    }

    @Test
    public void testSimulationTaskStartNotification() throws Exception {
    }

    @Test
    public void testSimulationTaskEndNotification() throws Exception {
    }

    @Test
    public void testAddExecutionState() throws Exception {
    }

    @Test
    public void testListExecutionStates() throws Exception {
    }

    @Test
    public void testAnalyseText() throws Exception {
        String modelSetId = MODELSET_ID;
        String artifactId = "obj.22332";
        String userId = "barnaby.barnes@learnpad.eu";
        String title = "Organize Service Conference Task";
        String text = "The activity <i>Organize Conference</i> specified by Sally Shugar should be <b>splitted</b> into 2 activities.";
        OntologyRecommenderImpl instance = new OntologyRecommenderImpl();
        Entities result = instance.analyseText(modelSetId, artifactId, userId, title, text);
        assertNotNull(result);
        assertNotNull(result.getEntities());
        assertTrue(result.getEntities().size()>0);
        
        JAXBContext context = JAXBContext.newInstance(Entities.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(result, System.out);
        
    }

    @Test
    public void testCreateBookmark() throws Exception {
    }

    @Test
    public void testGetAllBookmarks() throws Exception {
    }
    
}
