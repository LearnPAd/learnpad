/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.BusinessActor;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.LearningMaterials;
import eu.learnpad.or.rest.data.Recommendations;
import javax.xml.bind.JAXB;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public class RecommenderTest extends AbstractUnitTest {

    @Before
    public void init() {
        SimpleModelTransformator.getInstance();
    }

    @Test
    public void testRecommendations() {
        Recommendations recomms = Recommender.getInstance().getRecommendations(MODELSET_ID, APP.CONF.getString("testdata.artifactId"), APP.CONF.getString("testdata.user.email"));
        assertNotNull(recomms);
        JAXB.marshal(recomms, System.out);        
        Experts experts = recomms.getExperts();
        assertNotNull(experts);
        assertTrue(experts.getBusinessActors().size() > 0);
        
        LearningMaterials materials = recomms.getLearningMaterials();
        assertNotNull(materials);
        assertTrue(materials.getLearningMaterials().size() > 0);
        
        boolean lineManagerExpert = false;
        boolean otherUnitExpert = false;
        boolean mostOftenExecutedTaskExpert = false;
        for (BusinessActor businessActor : experts.getBusinessActors()) {
            if(businessActor.getDescription().equals(QueryMap.getQuery(Recommender.QUERY_LINEMANAGER_AS_EXPERT).getDescription())){
                lineManagerExpert = true;
            }
            if(businessActor.getDescription().equals(QueryMap.getQuery(Recommender.QUERY_EXPERTS_WITH_SAME_ROLE).getDescription())){
                otherUnitExpert = true;
            }
            if(businessActor.getDescription().equals(QueryMap.getQuery(Recommender.QUERY_EXPERT_MOST_OFTEN_EXECUTED_TASK).getDescription())){
                mostOftenExecutedTaskExpert = true;
            }            
        }
        
        assertTrue(lineManagerExpert);
        assertTrue(otherUnitExpert);
        assertTrue(mostOftenExecutedTaskExpert);
        
    }

}
