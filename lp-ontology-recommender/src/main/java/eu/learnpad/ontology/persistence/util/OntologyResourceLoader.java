/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence.util;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.jena.riot.Lang;

/**
 * Loads and combines ontology files by given relative paths.
 *
 * @author sandro.emmenegger
 */
public class OntologyResourceLoader {

    public static OntModel loadModel(String[] filePaths, Lang format) throws IOException {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.getDocumentManager().setProcessImports(false);

        for (String filePath : filePaths) {
            Reader in = null;
            try {
                in = new InputStreamReader(OntologyResourceLoader.class.getResourceAsStream(filePath));
                model.read(in, null, format.getName());
            } catch (Exception ex){
                Logger.getLogger(OntologyResourceLoader.class.getName()).log(Level.SEVERE, "Failed to load ontology file: "+filePath, ex);
            } 
            finally {
                if (in != null) {
                    in.close();
                }
            }
        }

        return model;
    }
}
