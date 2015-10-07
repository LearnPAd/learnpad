/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.topbraid.spin.inference.SPINInferences;
import org.topbraid.spin.system.SPINModuleRegistry;


/**
 *
 * @author sandro.emmenegger
 */
public class Inferencer {

    private OntModel model;
    private OntModel inferedModel;

    public Inferencer(OntModel model) {
        this.model = model;
        run();
    }

    private void run() {
        // Initialize system functions and templates
        SPINModuleRegistry.get().init();

        // Create and add Model for inferred triples
        inferedModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.addSubModel(inferedModel);
        // Register any new functions defined in EO
        //SPINFunctions.init();
        SPINModuleRegistry.get().registerAll(model, null);
        SPINInferences.run(model, inferedModel, null, null, false, null);
    }

    public OntModel getModel() {
        return model;
    }

    public OntModel getInferedModel() {
        return inferedModel;
    }

}
