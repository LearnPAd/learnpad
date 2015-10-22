/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.impl;

import java.util.ArrayList;
import java.util.List;

import eu.learnpad.core.impl.or.XwikiBridge;
import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.execution.ExecutionStates;
import eu.learnpad.ontology.recommender.Recommender;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.States;
import eu.learnpad.ontology.transformation.ModellingEnvironmentType;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.Recommendations;

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
    }

    @Override
    public void modelSetImported(String modelSetId, String type) throws LpRestException {
//            InputStream inputStream = new ByteArrayInputStream(this.corefacade.getModel(modelSetId, type));
            SimpleModelTransformator.getInstance().transform(modelSetId, this.corefacade.getModel(modelSetId, type), ModellingEnvironmentType.valueOf(type.toUpperCase()));
    }
    
    @Override
    public void sendResourceNotification(String modelSetId, String resourceId, String artifactIds, String action) throws LpRestException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recommendations askRecommendation(String modelSetId, String artifactId, String userId, String type) throws LpRestException {
        Recommendations recomms = Recommender.getInstance().getRecommendations(modelSetId, artifactId, userId);
//        Recommendations recomms = new Recommendations();
//        Experts experts = new Experts();
//        List<BusinessActor> businessActors = new ArrayList<BusinessActor>();
//        BusinessActor businessActor1 = new BusinessActor();
//        BusinessActor businessActor2 = new BusinessActor();
//        businessActor1.setName("Jean");
//        businessActor1.setEmail("jean@localhost.org");
//        businessActor1.setPhoneNumber("+33123456789");
//        businessActors.add(businessActor1);
//        businessActor2.setName("Sandro");
//        businessActor2.setEmail("sandro@localhost.org");
//        businessActors.add(businessActor2);
//		experts.setBusinessActors(businessActors);
//        recomms.setExperts(experts);
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
        States states = ExecutionStates.getInstance().getStatesOfLatestAddedModelSet(userId);
        return states;
    }

}
