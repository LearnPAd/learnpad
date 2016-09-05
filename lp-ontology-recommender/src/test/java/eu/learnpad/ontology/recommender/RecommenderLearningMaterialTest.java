/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.or.rest.data.LearningMaterials;
import eu.learnpad.or.rest.data.Recommendations;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the diferent expert recommendation rules.
 * 
 * @author sandro.emmenegger
 */
public class RecommenderLearningMaterialTest extends AbstractUnitTest  {

    @Test
    public void testLearningMaterialRecommendation() throws RecommenderException{
        Recommendations recomms = Recommender.getInstance().getRecommendations(MODELSET_ID, APP.CONF.getString("testdata.artifactId"), APP.CONF.getString("testdata.user.email"));
        assertNotNull(recomms);
        //  JAXB.marshal(recomms, System.out);
        
        LearningMaterials materials = recomms.getLearningMaterials();
        assertNotNull(materials);
        assertTrue(materials.getLearningMaterials().size() > 0);        
        
    }
}
