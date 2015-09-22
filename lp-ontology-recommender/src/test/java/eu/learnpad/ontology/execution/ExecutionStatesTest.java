/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.execution;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.or.rest.data.States;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import javax.inject.Inject;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({FileOntAO.class})
public class ExecutionStatesTest extends AbstractUnitTest {
    
    @Inject
    ExecutionStates exec;
    
    @Inject
    SimpleModelTransformator transformator;

    @Before
    public void init(){
        transformator.setLatestModelSetId(TEST_MODEL_SET_ID_TITOLO_UNICO);
    }
    
    public ExecutionStatesTest() {
    }

    /**
     * Test of getStates method, of class ExecutionStates.
     */
    @Test
    public void testGetStates() {
        States states = exec.getStates(TEST_USER_1_NAME, TEST_MODEL_SET_ID_TITOLO_UNICO);
        JAXB.marshal(states, System.out);
    }
    
}


@XmlRootElement
        class MyObject{
            
            String name;

    public String getName() {
        return name;
    }
@XmlElement
    public void setName(String name) {
        this.name = name;
    }
            
            
        }