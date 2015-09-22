/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.OntAO;
import eu.learnpad.ontology.persistence.util.OntologyFileLoader;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import java.io.IOException;
import javax.inject.Inject;
import org.apache.jena.riot.Lang;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.system.SPINModuleRegistry;
import org.topbraid.spin.util.JenaUtil;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({FileOntAO.class, OntAO.class, SimpleModelTransformator.class})
public class InferencerTest extends AbstractUnitTest {
    
    @Inject
    OntAO ontAO;
    
    public InferencerTest() {
    }

    /**
     * Test of run method, of class Inferencer.
     */
    //@Ignore
    @Test
    public void testRun() throws IOException {
        assertNotNull(ontAO);
        Inferencer inferencer = ontAO.getInferencer(TEST_MODEL_SET_ID_TITOLO_UNICO);
        assertNotNull(inferencer.getInferedModel());
        assertTrue("Inferred triples > 0", inferencer.getInferedModel().size()>0);
    
        inferencer.getModel().write(System.out, "Turtle");
    
//        Property prop = modelSet.getProperty("rdf:type");
//        Resource object = modelSet.getOntResource(APP.NS.ARCHI+"BusinessRole");
//        assertTrue(modelSet.contains(null, prop, object));
    }
    
    @Ignore
    @Test
    public void run2() throws IOException {
        // Initialize system functions and templates
        SPINModuleRegistry.get().init();

        // Load main file
        Model baseModel = ModelFactory.createDefaultModel();
        //baseModel.read(Paths.get("C:\\Data\\Projects\\LearnPad\\_GIT\\semmeneg\\lp-ontology-recommender\\target\\test\\ontology\\instances\\modelset-222\\1\\modelset-222.ttl").toUri().toString()); //load ontology
        String[] directories = {"C:\\Data\\Projects\\LearnPad\\_GIT\\semmeneg\\lp-ontology-recommender\\target\\test\\ontology\\instances\\modelset-222\\1\\modelset-222.ttl",
        "C:\\Data\\Projects\\LearnPad\\_GIT\\ontology\\archimeo",
        "C:\\Data\\Projects\\LearnPad\\_GIT\\ontology\\learnpad"};
        baseModel = OntologyFileLoader.loadModel(directories, Lang.TURTLE);
        
        // Create OntModel with imports
        OntModel ontModel = JenaUtil.createOntologyModel(OntModelSpec.OWL_MEM, baseModel);

        // Create and add Model for inferred triples
        Model newTriples = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ontModel.addSubModel(newTriples);

        // Register locally defined functions
        SPINModuleRegistry.get().registerAll(ontModel, null);

        // Run all inferences
        SPINInferences.run(ontModel, newTriples, null, null, false, null);
    }

}
