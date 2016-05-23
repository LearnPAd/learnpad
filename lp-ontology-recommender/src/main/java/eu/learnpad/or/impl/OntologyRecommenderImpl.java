/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.impl;

import java.io.InputStream;
import java.util.HashMap;
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
import eu.learnpad.ontology.notification.NotificationLog;
import eu.learnpad.ontology.recommender.Recommender;
import eu.learnpad.ontology.recommender.cbr.CBRAdapter;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Entities;
import eu.learnpad.or.rest.data.Entity;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.LearningMaterial;
import eu.learnpad.or.rest.data.LearningMaterials;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.OrganisationalUnit;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.RelatedObject;
import eu.learnpad.or.rest.data.RelatedObjects;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.or.rest.data.SimulationData;
import eu.learnpad.or.rest.data.States;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    public void resourceNotification(String modelSetId, String resourceId, ResourceType resourceType, String referringToResourceId, String[] modelArtifactIds, String userId, Long timestamp, NotificationActionType action) throws LpRestException {
        NotificationLog.getInstance().logResourceNotification(modelSetId, resourceId, resourceType, referringToResourceId, modelArtifactIds, userId, timestamp, action);
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
    	// This modification is done because currently the ontology does not deals with
    	// data submitted by the user during simulation, but only with session data.
    	// In future when the ontology will support this, the following line should be removed    	
    	data.setSubmittedData(new HashMap<String, Object>());
    	CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationId, data);
    }

    @Override
    public void simulationTaskStartNotification(String modelSetId, String modelId, String artifactId, String simulationId, SimulationData data) throws LpRestException {
    	// This modification is done because currently the ontology does not deals with
    	// data submitted by the user during simulation, but only with session data.
    	// In future when the ontology will support this, the following line should be removed    	
    	data.setSubmittedData(new HashMap<String, Object>());
        CBRAdapter.getInstance().createOrUpdateSimulationSessionCase(simulationId, data);
    }

    @Override
    public void simulationTaskEndNotification(String modelSetId, String modelId, String artifactId, String simulationId, SimulationData data) throws LpRestException {
    	// This modification is done because currently the ontology does not deals with
    	// data submitted by the user during simulation, but only with session data.
    	// In future when the ontology will support this, the following line should be removed    	
    	data.setSubmittedData(new HashMap<String, Object>());
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

    @Override
    public Entities analyseText(String modelSetId, String contextArtifactId, String userId, String title, String text) throws LpRestException {
        
        Entities testData = new Entities();
        String id = UUID.randomUUID().toString();
        String analysedText = "The activity <i>Organize Conference</i> specified by <span data-recommendation=\""+id+"\">Sally Shugar</span> should be <b>splitted</b> into 2 activities.";
        testData.setAnalyzedContent(analysedText);
        
        Entity entity = new Entity();
        entity.setId(id);
        entity.setContextArtifactId("transfer:obj.35315");
        entity.setType("eo:Person");
        BusinessActor person = new BusinessActor();
        person.setUri("transfer:obj.34872");
        person.setName("Sally Shugar");
        person.setEmail("sally.shugar@learnpad.eu");
        person.setSkypeId("learnpad_sally");
        person.setPhoneNumber("+234 23223 123");
        person.setOfficeAddress("Yellow drive 244b, East Juhee, Malta");
        person.setRole("Responsible SUAP Officer");
        OrganisationalUnit orgUnit = new OrganisationalUnit();
        orgUnit.setName("SUAP Office");
        orgUnit.setUri("transfer:obj.122121");
        person.setOrganisationalUnit(orgUnit);
        entity.setPerson(person);
        
        //related objects
        List<RelatedObject> listOfRelatedObjects = new ArrayList<>();
        RelatedObject relatedObject1 = new RelatedObject();
        relatedObject1.setRelationType("sameCreator");
        relatedObject1.setName("Management ABC for public administrations");
        relatedObject1.setDescription("This self study book with learning material is the definitve guide to manage a team in public administration.");
        relatedObject1.setDocumentUrl("http://learnpad.eu/material/PublicAdministrationABC.pdf");
        relatedObject1.setMimeType("application/pdf");
        relatedObject1.setUri("transfer:obj.21321");
        listOfRelatedObjects.add(relatedObject1);
        
        RelatedObject relatedObject2 = new RelatedObject();
        relatedObject1.setRelationType("sameAuthor");
        relatedObject2.setName("Best practices for organizing a service conference");
        relatedObject2.setDescription("A set of best practices with many hints for organizing a service conference.");
        relatedObject2.setDocumentUrl("http://learnpad.eu/material/BestPracticesServiceConferenceOrganisatoin.pdf");
        relatedObject2.setMimeType("application/pdf");
        relatedObject2.setUri("transfer:obj.21322");
        listOfRelatedObjects.add(relatedObject2);
        RelatedObjects relatedObjects = new RelatedObjects();
        relatedObjects.setRelatedObjects(listOfRelatedObjects);        
        
        entity.setRelatedObjects(relatedObjects);
        List<Entity> entities = new ArrayList<>();
        entities.add(entity);
        testData.setEntities(entities);
        return testData;
    }

    @Override
    public void createBookmark(String modelSetId, String userId, String artifactId, String contextArtifactId) throws LpRestException {
        //TODO
    }

    @Override
    public Recommendations getAllBookmarks(String modelSetId, String userId, String artifactId) throws LpRestException {
        
        Recommendations testData = new Recommendations();
        
        BusinessActor person = new BusinessActor();
        person.setName("Sally Shugar");
        person.setEmail("sally.shugar@learnpad.eu");
        person.setPhoneNumber("+234 23223 123");
        person.setSkypeId("learnpad_sally");
        person.setOfficeAddress("Yellow drive 244b, East Juhee, Malta");
        person.setRole("Responsible SUAP Officer");
        person.setUri("lpd:Sally_Shugar");
        OrganisationalUnit orgUnit = new OrganisationalUnit();
        orgUnit.setName("SUAP Office");
        orgUnit.setUri("obj.122121");
        person.setOrganisationalUnit(orgUnit);
        List<BusinessActor> expertList = new ArrayList();
        expertList.add(person);
        Experts experts = new Experts();
        experts.setBusinessActors(expertList);
        testData.setExperts(experts);
        
        LearningMaterial learningMaterial = new LearningMaterial();
        learningMaterial.setName("Management ABC for public administrations");
        learningMaterial.setDescription("This self study book with learning material is the definitve guide to manage a team in public administration.");
        learningMaterial.setUrl("http://learnpad.eu/material/PublicAdministrationABC.pdf");
        learningMaterial.setMimeType("application/pdf");
        learningMaterial.setId("transfer:obj.21321");
        
        List<LearningMaterial> learningMaterialsList = new ArrayList<>();
        learningMaterialsList.add(learningMaterial);
        LearningMaterials materials = new LearningMaterials();
        materials.setLearningMaterials(learningMaterialsList);
        testData.setLearningMaterials(materials);
        
        return testData;
    }

}
