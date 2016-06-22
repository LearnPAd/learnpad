/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.analyzer;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Resource;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.query.OntRetrieval;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Entities;
import eu.learnpad.or.rest.data.Entity;
import eu.learnpad.or.rest.data.OrganisationalUnit;
import eu.learnpad.or.rest.data.RelatedObjects;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author sandro.emmenegger
 */
public class Analyzer {

    private static final Analyzer instance = new Analyzer();

    public static Analyzer getInstance() {
        return instance;
    }
    
    private Analyzer(){
        initGATE();
    }

    public Entities analyze(String modelSetId, String htmlPageContent) throws RecommenderException {
        Entities entities = new Entities();
        List<Entity> entitiesList = new ArrayList<>();
        
        //TODO analyis with GATE here and iterate over all recognized persons
        
              OntModel model = FileOntAO.getInstance().getModelWithExecutionData(modelSetId);
              String authorUri = "";
              Individual author = model.getIndividual(authorUri);
              if(author != null){
                  String uniqueId = UUID.randomUUID().toString();
                  Entity entity = new Entity();
                  entity.setId(uniqueId);
                  entity.setType(APP.NS.EO+"Person");
                  BusinessActor person = mapToBusinessActor(model, author);
                  entity.setPerson(person);
                  OntRetrieval ontRet = new OntRetrieval();
                  RelatedObjects relatedObjects = ontRet.getDocumentsOfAuthor(modelSetId, author);
                  entity.setRelatedObjects(relatedObjects);
                  entitiesList.add(entity);
              }
        
        entities.setEntities(entitiesList);
        return entities;

    }

    private BusinessActor mapToBusinessActor(OntModel model, Individual author) {
        BusinessActor person = new BusinessActor();
        person.setUri(author.getURI());
        person.setFirstname(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM+"performerHasFirstName", ""));
        person.setLastname(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM+"performerHasLastName", ""));
        person.setEmail(OntUtil.getLiteralPropertyString(model, author, APP.NS.EMO+"performerHasEmailAddress", ""));
        
        person.setOfficeAddress("");  //TODO: Currently not available, could be added to meta model in modelling environment
        
        OntProperty orgUnitProperty = model.getOntProperty(APP.NS.OMM+"performerBelongsToOrganisationalUnit");
        if(orgUnitProperty != null){
            Resource orgUnitRes = author.getPropertyResourceValue(orgUnitProperty);
            if(orgUnitRes != null){
                OrganisationalUnit orgUnit = new OrganisationalUnit();
                orgUnit.setUri(orgUnitRes.getURI());
                String name = orgUnit.getName();
                if(orgUnitRes instanceof OntResource){
                    name = OntUtil.getLabel((OntResource)orgUnit);
                }
                orgUnit.setName(name);
                person.setOrganisationalUnit(orgUnit);
            }
        }
        person.setPhoneNumber(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM+"performerHasPhoneNumber", ""));
        
        person.setSkypeId(""); //TODO: Currently not available, could be added to meta model in modelling environment
        
        OntProperty roleProperty = model.getOntProperty(APP.NS.EO+"performerHasRole");
        if(roleProperty != null){
            Resource roleRes = author.getPropertyResourceValue(roleProperty);
            if(roleRes != null){
                String name = roleRes.getLocalName();
                if(roleRes instanceof OntResource){
                    name = OntUtil.getLabel((OntResource)roleRes);
                }
                person.setRole(name);
            }
        }
        
        person.setDescription(""); //TODO: Currently not available, could be added to meta model in modelling environment
        
        return person;
    }
    
    private void initGATE(){
//        try { 
//            Gate.init();
//        } catch (GateException ex) {
//            Logger.getLogger(Analyzer.class.getName()).log(Level.SEVERE, "Cannot initialze GATE for text analaysis.", ex);
//        }
        
    }

}
