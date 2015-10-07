/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import eu.learnpad.core.impl.or.XwikiBridge;
import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.execution.ExecutionStates;
import eu.learnpad.or.rest.data.States;
import eu.learnpad.ontology.recommender.Recommender;
import eu.learnpad.or.rest.data.Recommendations;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

/**
 *
 * @author sandro.emmenegger
 */
@Path("/learnpad/or/bridge")
public class OntologyRecommenderImpl extends XwikiBridge implements Initializable {
    
    @Inject
    ExecutionStates executionStates;
    
    @Inject
    Recommender recommender;

	@Override
	public void initialize() throws InitializationException {
		this.corefacade = new XwikiCoreFacadeRestResource();
	}

	@Override
	public void modelSetImported(String modelSetId, String type) throws LpRestException {
		InputStream inputStream = new ByteArrayInputStream(this.corefacade.getModel(modelSetId, type));
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
    
    @Override
    public void sendResourceNotification(String modelSetId, String resourceId, String artifactIds, String action) throws LpRestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recommendations askRecommendation(String modelSetId, String artifactId, String userId, String type) throws LpRestException {
        Recommendations recomms = recommender.getRecommendations(modelSetId, artifactId, userId);
        return recomms;
    }

    @Override
    public byte[] simulationNotification(String modelSetId, String modelId, String action, String simulationId) throws LpRestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addExecutionState(String artifactName, String artifactDescription, String artifactType, String modelType, String freeDescription, String existingArtifactId, String existingArtifactStructureDepth) throws LpRestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addExecutionState(String modelSetId, String executionId, String userId, String threadId, String pageId, String artifactId) throws LpRestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public States listExecutionStates(String userId) throws LpRestException {
        States states = executionStates.getStatesOfLatestAddedModelSet(userId);
        return states;
    }

}
