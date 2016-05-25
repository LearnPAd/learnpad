/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author sandro.emmenegger
 */
public class FileOntAOTest extends AbstractUnitTest{
    
    @Before
    public void loadTestModelset(){
        SimpleModelTransformator.getInstance();
    }
    
    /**
     * Test of getModelSet method, of class OntAO.
     */
    @Test
    public void testGetMetaModelSet() {
        OntModel result = FileOntAO.getInstance().getMetaModel();
//        result.write(System.out, "Turtle");
        assertNotNull(result);
    }
        

    /**
     * Test of getModelSet method, of class OntAO.
     */
//    @Ignore
    @Test
    public void testGetModelSet() {
        OntModel result = FileOntAO.getInstance().getModelSet(MODELSET_ID);
//        result.write(System.out, "Turtle");
        assertNotNull(result);
    }
    
    /**
     * Test of getExecutionData method, of class OntAO.
     */
//    @Ignore
    @Test
    public void testgetExecutionData() {
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(MODELSET_ID);
        assertNotNull(model);
        
        OntClass pageClass = model.createClass(APP.NS.XWIKI+"Page");
        pageClass.createIndividual(APP.NS.EXEC+"LOG_Page_"+UUID.randomUUID());
        
        FileOntAO.getInstance().persistNotificationLogModel();
        
    }
    
}
