/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence;

import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.AbstractUnitTest;
import java.io.FileWriter;
import java.io.IOException;
import javax.inject.Inject;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;

/**
 *
 * @author sandro.emmenegger
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({FileOntAO.class})
public class OntAOTest extends AbstractUnitTest{
    
    @Inject
    OntAO ontAO;
    
    public OntAOTest() {
    }

    /**
     * Test of getModelSet method, of class OntAO.
     */
    @Ignore
    @Test
    public void testGetModelSet() {
        OntModel result = ontAO.getModelSet(TEST_MODEL_SET_ID_1);
        assertNotNull(result);
    }
    
    /**
     * Test of getExecutionData method, of class OntAO.
     */
    @Test
    public void testGetExecutionData() throws IOException {
        OntModel result = ontAO.getModelSet(TEST_MODEL_SET_ID_TITOLO_UNICO);
        assertNotNull(result);
        
        result.write(new FileWriter("C:/temp/exec.ttl"), "Turtle");
    }    
    
}
