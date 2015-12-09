/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sandro.emmenegger
 */
public class InferencerTest extends AbstractUnitTest {
    
    @Before
    public void loadTestModelset(){
        SimpleModelTransformator.getInstance();
    }    
    
    /**
     * Test of run method, of class Inferencer.
     */
//    @Ignore
    @Test
    public void testRun() {
        Inferencer inferencer = FileOntAO.getInstance().getInferencer(MODELSET_ID);
        assertNotNull(inferencer.getInferedModel());
        
//        inferencer.getInferedModel().write(System.out, "Turtle");
        
        assertTrue("Inferred triples > 0", inferencer.getInferedModel().size()>0);
    }
}
