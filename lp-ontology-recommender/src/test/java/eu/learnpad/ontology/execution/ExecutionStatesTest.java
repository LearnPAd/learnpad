/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.execution;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.or.rest.data.States;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class ExecutionStatesTest extends AbstractUnitTest {

    /**
     * Test of getStates method, of class ExecutionStates.
     */
    @Test
    public void testGetStates() throws RecommenderException {
        States states = ExecutionStates.getInstance().getStates(APP.CONF.getString("testdata.user.email"), MODELSET_ID);
        assertNotNull(states);
//        assertNotNull(states.getStates());
//        assertTrue(states.getStates().size() > 0);
//        JAXB.marshal(states, System.out);
    }
    
}
