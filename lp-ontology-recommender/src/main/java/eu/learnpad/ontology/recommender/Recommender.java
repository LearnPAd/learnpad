/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;
import eu.learnpad.ontology.persistence.OntAO;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.Recommendations;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author sandro.emmenegger
 */
@Singleton
public class Recommender {
    
    @Inject
    OntAO ontAO;
    
    public Recommendations getRecommendations(String modelSetId, String artifactId, String userId){
        Recommendations recommends = new Recommendations();
        Experts experts = new Experts();
        experts.addAllBusinessActors(suggestExpertsWithSameRole(modelSetId, artifactId, userId));
        experts.addAllBusinessActors(suggestExpertMostOftenExecutedTask(modelSetId, artifactId, userId));
        recommends.setExperts(experts);
        return recommends;
    }
        
    public List<BusinessActor> suggestExpertsWithSameRole(String modelSetId, String artifactId, String userId){
        return getExperts("expertsWithSameRole", modelSetId, artifactId, userId);
    }
    
    public List<BusinessActor> suggestExpertMostOftenExecutedTask(String modelSetId, String artifactId, String userId){
        return getExperts("expertMostOftenExecutedTask", modelSetId, artifactId, userId);
    }

    private List<BusinessActor> getExperts(String queryName, String modelSetId, String artifactId, String userId) {
        RecommenderQuery queryObj = QueryMap.getQuery(queryName);
        List<BusinessActor> experts = new ArrayList<>();
        Query query = QueryFactory.create(queryObj.getQueryString());
        OntModel model = ontAO.getExecutionData(modelSetId);
        QueryExecution qexec = null;
        try {
            qexec = QueryExecutionFactory.create(query, model);
            ResultSet results = qexec.execSelect();
            for (; results.hasNext();) {
                QuerySolution soln = results.nextSolution();
                BusinessActor expert = new BusinessActor();
                Resource businessActor = soln.getResource("businessActor");
                expert.setUri(businessActor.getURI());
                expert.setName(getLiteralString(soln, "otherPerformerName"));
                expert.setName(getLiteralString(soln, "email"));
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
