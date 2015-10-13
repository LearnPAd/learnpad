/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.transformation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public class ModellingEnvironmentTypeTest {
    
    /**
     * Test of valueOf method, of class ModellingEnvironmentType.
     */
    @Test
    public void testValueOf() {
        assertNotNull(ModellingEnvironmentType.valueOf("ADOXX"));
        assertNotNull(ModellingEnvironmentType.valueOf("MD"));
    }
    
}
