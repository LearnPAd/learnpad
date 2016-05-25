/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import eu.learnpad.ontology.config.APP;

import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.recommender.cbr.CBRAdapter;
import eu.learnpad.ontology.util.ArgumentCheck;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.LearningMaterial;
import eu.learnpad.or.rest.data.LearningMaterials;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.SimilarCases;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandro.emmenegger
 */
public class Recommender {

    private static final Recommender instance = new Recommender();

    public static final String QUERY_LINEMANAGER_AS_EXPERT = "linemanagerAsExpert";
    public static final String QUERY_EXPERTS_WITH_SAME_ROLE = "expertsWithSameRole";
    public static final String QUERY_EXPERT_MOST_OFTEN_EXECUTED_TASK = "expertMostOftenExecutedTask";
    public static final String QUERY_LEARNING_MATERIAL_FOR_NEXT_COMPETENCY_LEVEL = "learningMaterialForNextCompetencyLevel";

    private Recommender() {
    }

    public static Recommender getInstance() {
        return instance;
    }

    public Recommendations getRecommendations(String modelSetId, String artifactId, String userId, String simulationSessionId) throws RecommenderException {
        ArgumentCheck.notNull(modelSetId, "modelsetId");
        ArgumentCheck.notNull(userId, "userId");

        Recommendations recommends = getRecommendations(modelSetId, artifactId, userId);
        if (simulationSessionId != null && !simulationSessionId.isEmpty() && !"--none--".equals(simulationSessionId)) {
            SimilarCases similarCases = null;
            try{
                similarCases = CBRAdapter.getInstance().retrieveSimilarCases(modelSetId, artifactId, userId, simulationSessionId);
            }catch(Exception ex){
                Logger.getLogger(CBRAdapter.class.getName()).log(Level.SEVERE, "Error when retrieving similar cases with simulationId: "+String.valueOf(simulationSessionId), ex);
            }
            recommends.setSimilarCases(similarCases);
        }

        return recommends;
    }

    public Recommendations getRecommendations(String modelSetId, String artifactId, String userId) throws RecommenderException {
        ArgumentCheck.notNull(modelSetId, "modelsetId");
        ArgumentCheck.notNull(userId, "userId");

        Recommendations recommends = new Recommendations();
        Experts experts = new Experts();
        addBusinessActorsWithoutDuplicatesOrSelf(experts, suggestLineManagerAsExpert(modelSetId, artifactId, userId), userId);
        addBusinessActorsWithoutDuplicatesOrSelf(experts, suggestExpertsWithSameRole(modelSetId, artifactId, userId), userId);
        addBusinessActorsWithoutDuplicatesOrSelf(experts, suggestExpertMostOftenExecutedTask(modelSetId, artifactId, userId), userId);
        recommends.setExperts(experts);

        LearningMaterials materials = new LearningMaterials();
        addLearningMaterialWithoutDuplicates(materials, getLearningMaterial(modelSetId, userId));
        recommends.setLearningMaterials(materials);
        return recommends;
    }

    public List<BusinessActor> suggestLineManagerAsExpert(String modelSetId, String artifactId, String userId) throws RecommenderException {
        return getExperts(QUERY_LINEMANAGER_AS_EXPERT, modelSetId, artifactId, userId);
    }

    public List<BusinessActor> suggestExpertsWithSameRole(String modelSetId, String artifactId, String userId) throws RecommenderException {
        return getExperts(QUERY_EXPERTS_WITH_SAME_ROLE, modelSetId, artifactId, userId);
    }

    public List<BusinessActor> suggestExpertMostOftenExecutedTask(String modelSetId, String artifactId, String userId) throws RecommenderException {
        if (artifactId == null) {
            return new ArrayList<BusinessActor>();
        }
        return getExperts(QUERY_EXPERT_MOST_OFTEN_EXECUTED_TASK, modelSetId, artifactId, userId);
    }

    private List<BusinessActor> getExperts(String queryName, String modelSetId, String artifactId, String userId) throws RecommenderException {
        RecommenderQuery queryObj = QueryMap.getQuery(queryName);
        List<BusinessActor> experts = new ArrayList<BusinessActor>();
        Query query = QueryFactory.create(queryObj.getQueryString());
        OntModel model = getExecutionDataModel(modelSetId);
//        model.write(System.out, "Turtle");
        QueryExecution qexec = null;
        QuerySolutionMap initialBinding = new QuerySolutionMap();
        initialBinding.add("userId", model.createTypedLiteral(userId));
        if (artifactId != null) {
            initialBinding.add("artifactId", model.getResource(APP.NS.TRANSFER + artifactId));
        }

        try {
            qexec = QueryExecutionFactory.create(query, model, initialBinding);
            ResultSet results = qexec.execSelect();
            for (; results.hasNext();) {
                QuerySolution soln = results.nextSolution();
                BusinessActor expert = new BusinessActor();
                Resource businessActor = soln.getResource("businessActor");
                expert.setUri(businessActor.getURI());
                expert.setName(getLiteralString(soln, "name"));
                expert.setEmail(getLiteralString(soln, "email"));
                expert.setPhoneNumber(getLiteralString(soln, "phone"));
                expert.setRole(getLiteralString(soln, "roleName"));
                expert.setDescription(queryObj.getDescription());
                experts.add(expert);
            }
        } finally {
            if (qexec != null) {
                qexec.close();
            }
        }
        return experts;
    }

    public OntModel getExecutionDataModel(String modelSetId) throws RecommenderException {
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(modelSetId);
        if (model == null) {
            throw new RecommenderException("Ontology model for modelset with id '" + modelSetId + "' cannot be found or causes problems during loading.");
        }
        return model;
    }

    private String getLiteralString(QuerySolution soln, String varName) {
        Literal lit = soln.getLiteral(varName);
        if (lit != null) {
            return lit.getString();
        }
        return null;
    }

    private List<LearningMaterial> getLearningMaterial(String modelSetId, String userId) throws RecommenderException {
        RecommenderQuery queryObj = QueryMap.getQuery(QUERY_LEARNING_MATERIAL_FOR_NEXT_COMPETENCY_LEVEL);
        List<LearningMaterial> materials = new ArrayList<LearningMaterial>();
        Query query = QueryFactory.create(queryObj.getQueryString());
        OntModel model = getExecutionDataModel(modelSetId);
        QueryExecution qexec = null;
        QuerySolutionMap initialBinding = new QuerySolutionMap();
        initialBinding.add("userId", model.createTypedLiteral(userId));

        try {
            qexec = QueryExecutionFactory.create(query, model, initialBinding);
            ResultSet results = qexec.execSelect();
            for (; results.hasNext();) {
                QuerySolution soln = results.nextSolution();
                LearningMaterial material = new LearningMaterial();
                Resource businessActor = soln.getResource("learningMaterialDocument");
                material.setId(businessActor.getURI());
                material.setName(getLiteralString(soln, "documentLabel"));
                material.setUrl(getLiteralString(soln, "documentURL"));
                material.setMimeType(getLiteralString(soln, "documentMimeType"));
                material.setDescription(getLiteralString(soln, "documentDescription"));
                material.setComment(getLiteralString(soln, "documentComment"));
                material.setQueryDescription(queryObj.getDescription());
                materials.add(material);
            }
        } finally {
            if (qexec != null) {
                qexec.close();
            }
        }
        return materials;
    }

    private void addBusinessActorsWithoutDuplicatesOrSelf(Experts experts, List<BusinessActor> toAdd, String userId) {

        List<BusinessActor> businessActors = experts.getBusinessActors();
        if (businessActors == null) {
            businessActors = new ArrayList<BusinessActor>();
        }
        for (BusinessActor baToAdd : toAdd) {
            if (userId != null && userId.equals(baToAdd.getEmail())) {
                continue;
            }
            boolean exists = false;
            for (BusinessActor businessActor : businessActors) {
                if (businessActor.getUri().equals(baToAdd.getUri())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                businessActors.add(baToAdd);
            }
        }

        experts.setBusinessActors(businessActors);
    }

    private void addLearningMaterialWithoutDuplicates(LearningMaterials materials, List<LearningMaterial> toAdd) {

        List<LearningMaterial> materialsList = materials.getLearningMaterials();
        if (materialsList == null) {
            materialsList = new ArrayList<LearningMaterial>();
            for (LearningMaterial materialToAdd : toAdd) {
                boolean exists = false;
                for (LearningMaterial learningMaterial : materialsList) {
                    if (learningMaterial.getUrl().equals(materialToAdd.getUrl())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    materialsList.add(materialToAdd);
                }
            }
        }
        materials.setLearningMaterials(materialsList);
    }
}
