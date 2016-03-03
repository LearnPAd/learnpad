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
import eu.learnpad.or.rest.data.SimilarCase;
import eu.learnpad.or.rest.data.SimilarCases;
import eu.learnpad.or.rest.data.SimulationData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //later 
    }

    @Override
    public Recommendations askRecommendation(String modelSetId, String artifactId, String userId) throws LpRestException {
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
    public Recommendations askRecommendation(String modelSetId,
			String artifactId, String userId, String simulationSessionId) throws LpRestException {
    	
    	  Recommendations rec = new Recommendations();
          SimilarCases cases = CBRAdapter.getInstance().retrieveSimilarCases(modelSetId, artifactId, userId, simulationSessionId);
//          SimilarCases cases = new SimilarCases();
//          List<SimilarCase> caseList = new ArrayList();
//          SimilarCase case1 = new SimilarCase();
//          case1.setSimilarityValue("82%");
//          case1.setName("Capelletti - Chalet at the beach (Test)");
//          Map<String, Object> data = new HashMap<>();
//          data.put("applicantName", "Giuseppe Cappelletti");
//          data.put("applicationCity", "Senigallia");
//          data.put("applicationZones", new String[]{"Beach area at the sea"});
//          data.put("applicationPublicAdministration", "Senigallia");
//          data.put("applicationType", "Modification");
//          data.put("applicationSubtype", "Restructuring");
//          data.put("applicationSectors", new String[]{"Building","Environment","Public Land","Tourism"});
//          data.put("applicationBusinessActivities", new String[]{"Receptive Tourism"});
//          data.put("applicationDescription", "Realization of a chalet on a beach area of Senigallia");
//          data.put("applicationATECOCategories", new String[]{"55.20.51: Boutique hotels for short staying, houses and apatments for holidays, bed&breakfast, residence", "43.39.01: Non specialized construction (masons)"});
//          caseList.add(case1);
//          SimilarCase case2 = new SimilarCase();
//          case2.setSimilarityValue("42%");
//          case2.setName("Gianna Morbidelli - Walls on hotel(Test)");
//          caseList.add(case2);
//          SimilarCase case3 = new SimilarCase();
//          case3.setSimilarityValue("38%");
//          case3.setName("Ermenegildo Fiori - Restructuring for B&B (Test)");
//          caseList.add(case3);
//          cases.setSimilarCases(caseList);
//          rec.setSimilarCases(cases);
          
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
        //Postponed
    }

    @Override
    public States listExecutionStates(String userId) throws LpRestException {
        States states = ExecutionStates.getInstance().getStatesOfLatestAddedModelSet(userId);
        return states;
    }    
}
