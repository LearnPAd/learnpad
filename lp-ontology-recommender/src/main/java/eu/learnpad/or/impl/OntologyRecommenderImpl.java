/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.impl;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.core.impl.or.XwikiBridge;
import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.ontology.execution.ExecutionStates;
import eu.learnpad.ontology.recommender.Recommender;
import eu.learnpad.ontology.recommender.cbr.CBRAdapter;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.SimulationData;
import eu.learnpad.or.rest.data.States;

/**
 *
 * @author sandro.emmenegger
 */

@Component
@Singleton
@Named("eu.learnpad.or.impl.OntologyRecommenderImpl")
@Path("/learnpad/or/bridge")
public class OntologyRecommenderImpl extends XwikiBridge implements Initializable {
    
    @Override
    public void initialize() throws InitializationException {
            this.corefacade = new XwikiCoreFacadeRestResource();
            SimpleModelTransformator.getInstance();
    }

    @Override
    public void modelSetImported(String modelSetId, ModelSetType type) throws LpRestException {
            InputStream modelSetInputStream = this.corefacade.getModel(modelSetId, type);
            if(modelSetInputStream == null){
                throw new LpRestExceptionXWikiImpl("Modelset for id '" + modelSetId + "' and type '"+type+"' not found!");
            }
            SimpleModelTransformator.getInstance().transform(modelSetId, this.corefacade.getModel(modelSetId, type), type);
    }
    
    @Override
    public void sendResourceNotification(String modelSetId, String resourceId, String artifactIds, String action) throws LpRestException {
        // TODO later 
    }

    @Override
    public Recommendations askRecommendation(String modelSetId,
			String artifactId, String userId, String simulationSessionId) throws LpRestException {
    	
        try {
            Recommendations rec = Recommender.getInstance().getRecommendations(modelSetId, artifactId, userId, simulationSessionId);
            return rec;
        } catch (Exception ex) {
            Logger.getLogger(OntologyRecommenderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LpRestExceptionXWikiImpl("Asking for recommendations failed with parameters: "
                    + "modelsetId='"+modelSetId
                    +"' artifactId='"+artifactId
                    +"' userId='"+userId
                    +"' simulationSessionId='"+simulationSessionId+"'. ", ex);
        }
    }    
    
    @Override
    public void simulationInstanceNotification(String modelSetId, String modelId, String action, String simulationId, SimulationData data) throws LpRestException {
        CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationId, data);
    }

    @Override
    public void simulationTaskStartNotification(String modelSetId, String modelId, String artifactId, String simulationId, SimulationData data) throws LpRestException {
        CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationId, data);
    }

    @Override
    public void simulationTaskEndNotification(String modelSetId, String modelId, String artifactId, String simulationId, SimulationData data) throws LpRestException {
        CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationId, data);
    }
    
    @Override
    public void addExecutionState(String modelSetId, String executionId, String userId, String threadId, String pageId, String artifactId) throws LpRestException {
        // TODO Postponed
    }

    @Override
    public States listExecutionStates(String userId) throws LpRestException {
        States states = ExecutionStates.getInstance().getStatesOfLatestAddedModelSet(userId);
        return states;
    }    
}
