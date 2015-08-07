/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence;

import eu.learnpad.ontology.persistence.util.OntUtil;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.config.APP;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class to access ontology and provide convenient methods.
 * 
 * @author sandro.emmenegger
 */
public abstract class OntAO {
    
    private static OntAO instance = null;
    
    private OntModel metaModel;
    private Map<String, OntModel> modelSets;
    
    static{
        String source = APP.CONF.getString("ontology.persistence.source");
            switch (SOURCE.valueOf(source)) {
            case FILE:
                instance = new FileOntAO();
                break;
            case TDB: //TODO;
                break;
            case SDB: //TODO;
                break;
        }
    }
    
    protected OntAO(){
        modelSets = new HashMap<>();
        metaModel = loadMetaModel();
    }
    
    public static OntAO getInstance(){
        return instance;
    }

    /**
     * Returns the meta models only.
     * 
     * @return 
     */
    public OntModel getMetaModel(){
        return metaModel;
    }
    
    /**
     * Returns the lates instances of a set of models belonging to the given model
     * set id merged with the meta models.
     * 
     * @param modelSetId
     * @return 
     */
    public OntModel getModelSet(String modelSetId){
        if(!modelSets.containsKey(modelSetId)){
            OntModel modelSet = loadModelSet(modelSetId);
            if(modelSet != null){
                modelSet.setNsPrefixes(metaModel.getNsPrefixMap());
                //Using the ModelFactory.createUnion function has 2 advantages: 
                //   1. Unified model is dynamic and not a copy of the underlying models
                //   2. All new instances are stored automaticly in the dataModel only
                OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, ModelFactory.createUnion(modelSet, metaModel));  
                modelSets.put(modelSetId, model);
            }
        }
        return modelSets.get(modelSetId);
    }
    
    protected abstract OntModel loadMetaModel();
    
    protected abstract OntModel loadModelSet(String modelSetId);
    
    private enum SOURCE {

        TDB, SDB, FILE
    }
            
}
