/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.util.OntologyResourceLoader;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.riot.Lang;

/**
 *
 * @author sandro.emmenegger
 */
public class FileOntAO extends OntAO {
    
    private static final FileOntAO INSTANCE = new FileOntAO();
    
    private FileOntAO(){}
    
    public static FileOntAO getInstance(){
        return INSTANCE;
    }
    
    public byte[] getModelSetFile(String pathToModelSetFile){
        
        byte[] testModelFile = null;
        try {
            InputStream in = getClass().getResourceAsStream(pathToModelSetFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int next = in.read();
            while (next > -1) {
                bos.write(next);
                next = in.read();
            }
            bos.flush();
            testModelFile = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(SimpleModelTransformator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return testModelFile;
    }

    @Override
    protected OntModel loadMetaModel() {
        OntModel model = null;
        try {
            model = OntologyResourceLoader.loadModel(APP.CONF.getStringArray("ontology.metamodel.path"), Lang.TTL);
        } catch (IOException ex) {
            Logger.getLogger(FileOntAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

    @Override
    protected OntModel loadModelSet(String modelSetId) {
        File modelSetFile = SimpleModelTransformator.getInstance().getLatestVersionFile(modelSetId);
        if (modelSetFile == null) {
            return null;
        }

        OntModel modelSet = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        modelSet.read(modelSetFile.toURI().toString(), "TTL");
        return modelSet;
    }



}
