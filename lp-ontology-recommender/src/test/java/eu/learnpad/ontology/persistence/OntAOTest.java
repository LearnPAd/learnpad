/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.AbstractUnitTest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public class OntAOTest extends AbstractUnitTest{
    
    public OntAOTest() {
    }

    /**
     * Test of getModelSet method, of class OntAO.
     */
    @Test
    public void testGetModelSet() {
        OntModel result = OntAO.getInstance().getModelSet(TEST_MODEL_SET_ID_1);
        assertNotNull(result);
    }
    
}
