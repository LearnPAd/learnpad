/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.impl;

import eu.learnpad.core.impl.or.XwikiBridge;
import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.execution.ExecutionStates;
import eu.learnpad.ontology.recommender.Recommender;
import eu.learnpad.ontology.recommender.cbr.CBRAdapter;
import eu.learnpad.or.rest.data.States;
import eu.learnpad.ontology.transformation.ModellingEnvironmentType;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.SimulationData;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

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
    public void modelSetImported(String modelSetId, String type) throws LpRestException {
//            InputStream inputStream = new ByteArrayInputStream(this.corefacade.getModel(modelSetId, type));
            SimpleModelTransformator.getInstance().transform(modelSetId, this.corefacade.getModel(modelSetId, type), ModellingEnvironmentType.valueOf(type.toUpperCase()));
    }
    
    @Override
    public void sendResourceNotification(String modelSetId, String resourceId, String artifactIds, String action) throws LpRestException {
        // TODO later 
    }

    @Override
    public Recommendations askRecommendation(String modelSetId,
			String artifactId, String userId, String simulationSessionId) throws LpRestException {
    	
    	  Recommendations rec = Recommender.getInstance().getRecommendations(modelSetId, artifactId, userId, simulationSessionId);
          
          return rec;
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
