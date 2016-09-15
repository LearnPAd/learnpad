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
import gate.util.GateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandro.emmenegger
 */
public class Analyzer {

    private static final Analyzer instance = new Analyzer();

    private static final Map<String, BasicAnnotator> annotatorCache = new HashMap();

    public static Analyzer getInstance() {
        return instance;
    }

    private Analyzer() {
    }

    public Entities analyze(String modelSetId, String htmlPageContent) throws RecommenderException {

        BasicAnnotator basicAnnotator = getCachedAnnotator(modelSetId);
        Entities entities = basicAnnotator.entityLookup(htmlPageContent);

        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(modelSetId);
        for (Entity entity : entities.getEntities()) {
            entity.setModelSetId(modelSetId);
            entity.setModelId("");
            String authorUri = entity.getContextArtifactId();
            String objectId = authorUri.startsWith(APP.NS.TRANSFER.toString()) ? authorUri.replace(APP.NS.TRANSFER.toString(), "") : "";
            entity.setObjectId(objectId);
            Individual author = model.getIndividual(authorUri);
            if (author != null) {
                entity.setType(APP.NS.EO + "Person");
                BusinessActor person = mapToBusinessActor(model, author);
                entity.setPerson(person);
                OntRetrieval ontRet = new OntRetrieval();
                RelatedObjects relatedObjects = ontRet.getDocumentsOfAuthor(modelSetId, author);
                entity.setRelatedObjects(relatedObjects);
            }
        }
        
        return entities;

    }

    private BusinessActor mapToBusinessActor(OntModel model, Individual author) {
        BusinessActor person = new BusinessActor();
        person.setUri(author.getURI());
        person.setName(OntUtil.getLabel(author));
        person.setFirstname(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM + "performerHasFirstName", ""));
        person.setLastname(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM + "performerHasLastName", ""));
        person.setEmail(OntUtil.getLiteralPropertyString(model, author, APP.NS.EMO + "performerHasEmailAddress", ""));
        person.setSkypeId(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM + "performerHasSkypeId", ""));
        person.setPhoneNumber(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM + "performerHasPhoneNumber", ""));
        person.setOfficeAddress(OntUtil.getLiteralPropertyString(model, author, APP.NS.OMM + "performerHasOfficeAddress", ""));
        person.setRole("");

        OntProperty orgUnitProperty = model.getOntProperty(APP.NS.OMM + "performerBelongsToOrganisationalUnit");
        if (orgUnitProperty != null) {
            Resource orgUnitRes = author.getPropertyResourceValue(orgUnitProperty);
            if (orgUnitRes != null) {
                OrganisationalUnit orgUnit = new OrganisationalUnit();
                orgUnit.setUri(orgUnitRes.getURI());
                String name = orgUnit.getName();
                if (orgUnitRes instanceof OntResource) {
                    name = OntUtil.getLabel((OntResource) orgUnit);
                }
                orgUnit.setName(name);
                person.setOrganisationalUnit(orgUnit);
            }
        }

        OntProperty roleProperty = model.getOntProperty(APP.NS.EO + "performerHasRole");
        if (roleProperty != null) {
            Resource roleRes = author.getPropertyResourceValue(roleProperty);
            if (roleRes != null) {
                String name = roleRes.getLocalName();
                if (roleRes instanceof OntResource) {
                    name = OntUtil.getLabel((OntResource) roleRes);
                }
                person.setRole(name);
            }
        }

        person.setDescription(""); //Currently not available, could be added to meta model in modelling environment

        return person;
    }

    private BasicAnnotator getCachedAnnotator(String modelSetId) throws RecommenderException {
        if (!annotatorCache.containsKey(modelSetId)) {
            try {
                BasicAnnotator basicAnnotator = new BasicAnnotator(modelSetId);
                annotatorCache.put(modelSetId, basicAnnotator);
            } catch (GateException ex) {
                Logger.getLogger(Analyzer.class.getName()).log(Level.SEVERE, null, ex);
                throw new RecommenderException("GATE text analyis failed.", ex);
            } catch (IOException ex) {
                Logger.getLogger(Analyzer.class.getName()).log(Level.SEVERE, null, ex);
                throw new RecommenderException("GATE text analyis failed due to IO problems.", ex);
            }
        }
        return annotatorCache.get(modelSetId);
    }

}
