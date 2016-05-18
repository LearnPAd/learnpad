/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.persistence;

import java.util.HashMap;
import java.util.Map;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.config.APP;

import eu.learnpad.ontology.recommender.Inferencer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.riot.Lang;

/**
 * Base class to access ontology and provide convenient methods.
 *
 * @author sandro.emmenegger
 */
public abstract class OntAO {

    private static final Logger LOGGER = Logger.getLogger(OntAO.class.getName());

    private OntModel metaModel;
    private Map<String, OntModel> modelSets;
    private Map<String, Inferencer> modelSetsInferencer;
    private Map<String, OntModel> modelsSetsExecutionData; //a union of the modelset ontolgy models and the execution data

    //Stores all resource, comment and simulation notifications independent for all modelsets
    private OntModel executionData;

    protected OntAO() {
        modelSets = new HashMap<>();
        modelSetsInferencer = new HashMap<>();
        modelsSetsExecutionData = new HashMap<>();
        metaModel = loadMetaModel();
    }

    /**
     * Returns the meta models only.
     *
     * @return
     */
    public OntModel getMetaModel() {
        return metaModel;
    }

    /**
     * Returns the lates instances of a set of models belonging to the given
     * model set id merged with the meta models.
     *
     * @param modelSetId
     * @return
     */
    public OntModel getModelSet(String modelSetId) {
        if (!modelSets.containsKey(modelSetId)) {
            OntModel modelSet = loadModelSet(modelSetId);
            if (modelSet != null) {
                modelSet.setNsPrefixes(metaModel.getNsPrefixMap());
                //Using the ModelFactory.createUnion function has 2 advantages: 
                //   1. Unified model is dynamic and not a copy of the underlying models
                //   2. All new instances are stored automaticly in the dataModel only
//                OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, ModelFactory.createUnion(modelSet, metaModel));
                modelSet.add(metaModel);
                modelSets.put(modelSetId, modelSet);
            }
        }
        return modelSets.get(modelSetId);
    }

    /**
     * Returns model set inclusive inferenced instances.
     *
     * @param modelSetId
     * @return
     */
    public Inferencer getInferencer(String modelSetId) {
        if (!modelSetsInferencer.containsKey(modelSetId)) {
            Inferencer inferencer = new Inferencer(getModelSet(modelSetId));
            modelSetsInferencer.put(modelSetId, inferencer);
        }
        return modelSetsInferencer.get(modelSetId);
    }

    /**
     * Returns the modelset inclusive inferenced instances and execution data.
     *
     * @param modelSetId
     * @return
     */
    public OntModel getModelWithExecutionData(String modelSetId) {
        if (!modelsSetsExecutionData.containsKey(modelSetId)) {
            OntModel modelSetWithExecutionData = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, ModelFactory.createUnion(getExecutionData(), getInferencer(modelSetId).getModel()));  
            modelsSetsExecutionData.put(modelSetId, modelSetWithExecutionData);
        }
        return modelsSetsExecutionData.get(modelSetId);
    }

    public OntModel getExecutionData() {
        if (this.executionData == null) {
            File executionDataFile = getExecutionDataFile();
            if (executionDataFile != null) {
                this.executionData = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
                this.executionData.setNsPrefix("xwiki", APP.NS.XWIKI.toString());
                this.executionData.setNsPrefix("exec", APP.NS.EXEC.toString());
                if (executionDataFile.length() > 0) {
                    this.executionData.read(executionDataFile.toURI().toString(), Lang.TTL.getName());
                }
            }
        }
        return executionData;
    }

    public void persistNotificationLogModel() {
        if (this.executionData != null) {
            File executionDataFile = getExecutionDataFile();
            if (executionDataFile != null) {
                try (FileOutputStream out = new FileOutputStream(executionDataFile)) {
                    this.executionData.write(out, Lang.TURTLE.getName());
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Cannot save execution data file. ", ex);
                }
            }
        }
    }

    private File getExecutionDataFile() {
        File executionDataFile = null;
        try{
            Path executionDataFilePath = Paths.get(APP.CONF.getString("working.directory"), APP.CONF.getString("execution.data.path.relative"));
            executionDataFile = executionDataFilePath.toFile();
            if(!executionDataFile.getParentFile().exists()){
                executionDataFile.getParentFile().mkdirs();
            }
            if(!executionDataFile.exists()){
                executionDataFile.createNewFile();
            }
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE, "Cannot load or create execution data file.", ex);
        }
        return executionDataFile;
    }
    
    protected abstract OntModel loadMetaModel();

    protected abstract OntModel loadModelSet(String modelSetId);

}
