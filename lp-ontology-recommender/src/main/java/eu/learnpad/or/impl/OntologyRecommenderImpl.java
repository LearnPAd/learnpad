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
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.core.impl.or.XwikiBridge;
import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.ontology.kpi.dashboard.KPILoader;
import eu.learnpad.ontology.notification.NotificationLog;
import eu.learnpad.ontology.recommender.Recommender;
import eu.learnpad.ontology.recommender.RecommenderException;
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
import eu.learnpad.or.rest.data.kbprocessing.KBProcessId;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatus;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatusType;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatus.Info;

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
        if (modelSetInputStream == null) {
            throw new LpRestExceptionXWikiImpl("Modelset for id '" + modelSetId + "' and type '" + type + "' not found!");
        }
        SimpleModelTransformator.getInstance().transform(modelSetId, this.corefacade.getModel(modelSetId, type), type);
    }

    @Override
    public void resourceNotification(String modelSetId, String resourceId, ResourceType resourceType, String referringToResourceId, String[] modelArtifactIds, String userId, Long timestamp, NotificationActionType action) throws LpRestException {

        try {
            NotificationLog.getInstance().logResourceNotification(modelSetId, resourceId, resourceType, referringToResourceId, modelArtifactIds, userId, timestamp, action);
        } catch (RecommenderException ex) {
            Logger.getLogger(OntologyRecommenderImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new LpRestExceptionXWikiImpl("Loging resource notification failed. ", ex);
        }
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
                    + "modelsetId='" + modelSetId
                    + "' artifactId='" + artifactId
                    + "' userId='" + userId
                    + "' simulationSessionId='" + simulationSessionId + "'. ", ex);
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
//        States states = ExecutionStates.getInstance().getStatesOfLatestAddedModelSet(userId);
//        return states;
        return null; //not used up to now
    }

    @Override
    public Entities analyseText(String modelSetId, String contextArtifactId, String userId, String title, String text) throws LpRestException {

        Entities testData = new Entities();
        String id = UUID.randomUUID().toString();

        //For testing purposes only !
        String analysedText = text;
        if(text.contains("Sally Shugar")){
            analysedText = analysedText.replace("Sally Shugar", "<span data-recommendation=\"" + id + "\">Sally Shugar</span>");
        }
        
        testData.setAnalyzedContent(analysedText);

        Entity entity = new Entity();
        entity.setId(id);
        entity.setModelId(modelSetId);
        entity.setModelId("mod.39886");
        entity.setOjbectId("obj.39926");
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

    @Override
    public KBProcessId calculateKPI(String modelSetId) throws LpRestException {

        Logger.getLogger(OntologyRecommenderImpl.class.getName()).log(Level.INFO, "Inside " + this.getClass().getCanonicalName() + ".calculateKPI");
            
        KPILoader kpiLoader = new KPILoader(this.corefacade, modelSetId);
        kpiLoader.start();
        
        KBProcessId out = new KBProcessId();        
        out.setId("KPI_"+kpiLoader.getId());
        
        return out;
    }

    @Override
    public KBProcessingStatus getHandlingProcessStatus(String kbProcessProcessId)
            throws LpRestException {
        // TODO Auto-generated method stub
        Logger.getLogger(OntologyRecommenderImpl.class.getName()).log(Level.INFO, "Inside " + this.getClass().getCanonicalName() + ".getHandlingProcessStatus");
        KBProcessingStatus out = this.fakeKBProcessingStatus();

        return out;
    }

    private KBProcessingStatus fakeKBProcessingStatus() {
        KBProcessingStatus fake = new KBProcessingStatus();
        fake.setStatus(KBProcessingStatusType.IN_PROGRESS);

        Info info = new Info();
        JAXBElement<String> jaxbElement
                = new JAXBElement(new QName("InfoContentTag"),
                        String.class, "this-status-is-fake");
        info.getAny().add(jaxbElement);
        return fake;
    }

}
