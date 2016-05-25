/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.util.Map;
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
        this(model, null);
    }
    
    public Inferencer(OntModel model, Map<String, String> prefixes) {
        this.model = model;
        run(prefixes);
    }

    private void run(Map<String, String> prefixes) {
        if (model != null) {
            // Initialize system functions and templates
            SPINModuleRegistry.get().init();

            // Create and add Model for inferred triples
            inferedModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            if(prefixes != null){
                inferedModel.setNsPrefixes(prefixes);
            }
            
            model.addSubModel(inferedModel);
            // Register any new functions defined in EO
            // SPINFunctions.init();
            SPINModuleRegistry.get().registerAll(model, null);
            SPINInferences.run(model, inferedModel, null, null, true, null);
        }
    }    

    public OntModel getModel() {
        return model;
    }

    public OntModel getInferedModel() {
        return inferedModel;
    }

}
