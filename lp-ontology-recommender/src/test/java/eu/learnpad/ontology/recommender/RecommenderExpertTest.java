/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.Recommendations;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the diferent expert recommendation rules.
 * 
 * @author sandro.emmenegger
 */
public class RecommenderExpertTest extends AbstractUnitTest  {

    @Test
    public void testLineManagerExpert() throws RecommenderException{
        Recommendations recomms = Recommender.getInstance().getRecommendations(MODELSET_ID, APP.CONF.getString("testdata.artifactId"), APP.CONF.getString("testdata.user.email"));
        assertNotNull(recomms);
        //  JAXB.marshal(recomms, System.out);
        
        assertTrue(hasRecommendationsFor(recomms, QueryMap.getQuery(Recommender.QUERY_LINEMANAGER_AS_EXPERT).getDescription()));
        assertTrue(hasRecommendationsFor(recomms, QueryMap.getQuery(Recommender.QUERY_EXPERTS_WITH_EXPERT_ROLE).getDescription()));
        
        recomms = Recommender.getInstance().getRecommendations(MODELSET_ID, null, APP.CONF.getString("testdata.user.expert.email"));
        assertTrue(hasRecommendationsFor(recomms, QueryMap.getQuery(Recommender.QUERY_EXPERTS_FOR_EXPERTS).getDescription()));
        
        //COMMENT: Disabled rule tests due to missing testdata and further evaluation of rules
        // assertTrue(assertExpectExpertRecommendationFor(recomms, QueryMap.getQuery(Recommender.QUERY_EXPERTS_WITH_SAME_ROLE).getDescription()));
        // assertTrue(assertExpectExpertRecommendationFor(recomms, QueryMap.getQuery(Recommender.QUERY_EXPERT_MOST_OFTEN_EXECUTED_TASK).getDescription()));        
    }

    
    private boolean hasRecommendationsFor(Recommendations recomms, String description) throws RecommenderException {
                
        Experts experts = recomms.getExperts();
        assertNotNull(experts);
        assertTrue(experts.getBusinessActors().size() > 0);
        
        for (BusinessActor businessActor : experts.getBusinessActors()) {
            if(businessActor.getDescription().equals(description)){
                return true;
            }
        }
        
        return false;
        
    }
}
