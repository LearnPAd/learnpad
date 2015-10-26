/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author semmeneg
 */
public class LoadModelTest {
    
    public LoadModelTest() {
    }

    @Test
    public void testLoad() {
        OntModel modelSet = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        File modelSetFile = new File("/home/semmeneg/_GIT/or_branch/learnpad/lp-platform/out/xwiki/ontology/instances/modelset-titolo-unico-v4/1/modelset-titolo-unico-v4.ttl");
        assertTrue(modelSetFile.exists());
        modelSet.read(modelSetFile.toURI().toString(), "TTL");
    }
}
