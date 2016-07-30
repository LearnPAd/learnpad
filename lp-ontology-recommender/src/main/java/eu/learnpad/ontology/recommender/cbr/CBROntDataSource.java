/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender.cbr;

import ch.fhnw.cbr.persistence.OntDataSource;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.persistence.FileOntAO;

/**
 *
 * @author sandro.emmenegger
 */
public class CBROntDataSource extends OntDataSource {
    
    public CBROntDataSource(){
        load(FileOntAO.getInstance().getMetaModel(), ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM));
    }

    @Override
    public void persistData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
