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
import eu.learnpad.ontology.persistence.OntAO;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.Recommendations;

/**
 *
 * @author sandro.emmenegger
 */
public class Recommender {
    
    private static final Recommender instance = new Recommender();
    private OntAO ontAO;
    
    public static final String QUERY_LINEMANAGER_AS_EXPERT = "linemanagerAsExpert";
    public static final String QUERY_EXPERTS_WITH_SAME_ROLE = "expertsWithSameRole";
    public static final String QUERY_EXPERT_MOST_OFTEN_EXECUTED_TASK = "expertMostOftenExecutedTask";
    
    private Recommender() {
    	this.ontAO = new FileOntAO();
    }
    
    public static Recommender getInstance(){
        return instance;
    }
    
    public Recommendations getRecommendations(String modelSetId, String artifactId, String userId){
        Recommendations recommends = new Recommendations();
        Experts experts = new Experts();
        experts.addAllBusinessActors(suggestLineManagerAsExpert(modelSetId, artifactId, userId));
        experts.addAllBusinessActors(suggestExpertsWithSameRole(modelSetId, artifactId, userId));
        experts.addAllBusinessActors(suggestExpertMostOftenExecutedTask(modelSetId, artifactId, userId));
        recommends.setExperts(experts);
        return recommends;
    }
    public List<BusinessActor> suggestLineManagerAsExpert(String modelSetId, String artifactId, String userId){
        return getExperts(QUERY_LINEMANAGER_AS_EXPERT, modelSetId, artifactId, userId);
    }    
    
    public List<BusinessActor> suggestExpertsWithSameRole(String modelSetId, String artifactId, String userId){
        return getExperts(QUERY_EXPERTS_WITH_SAME_ROLE, modelSetId, artifactId, userId);
    }
    
    public List<BusinessActor> suggestExpertMostOftenExecutedTask(String modelSetId, String artifactId, String userId){
        if(artifactId == null){
            return new ArrayList<>();
        }
        return getExperts(QUERY_EXPERT_MOST_OFTEN_EXECUTED_TASK, modelSetId, artifactId, userId);
    }

    private List<BusinessActor> getExperts(String queryName, String modelSetId, String artifactId, String userId) {
        RecommenderQuery queryObj = QueryMap.getQuery(queryName);
        List<BusinessActor> experts = new ArrayList<>();
        Query query = QueryFactory.create(queryObj.getQueryString());
        //TODO replace if execution data is available.        
        OntModel model = ontAO.getExecutionData(modelSetId);
//        OntModel model = ontAO.getInferencer(modelSetId).getModel();
//        model.write(System.out, "Turtle");
        QueryExecution qexec = null;
        QuerySolutionMap initialBinding = new QuerySolutionMap();
        initialBinding.add("userId", model.createTypedLiteral(userId));
        if(artifactId != null){
           initialBinding.add("artifactId", model.getResource(APP.NS.TRANSFER+artifactId));
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
    
    private String getLiteralString(QuerySolution soln, String varName){
        Literal lit = soln.getLiteral(varName);
        if(lit != null){
            return lit.getString();
        }
        return null;
    }
    
    

}
