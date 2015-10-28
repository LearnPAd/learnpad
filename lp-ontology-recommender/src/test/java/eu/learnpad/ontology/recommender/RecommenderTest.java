/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.or.rest.data.Experts;
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
        
    }

    @Test
    public void testSuggestExpertWithSameRole() {
        Recommendations recomms = Recommender.getInstance().getRecommendations(TEST_MODEL_SET_ID_TITOLO_UNICO_V5, "", TEST_USER_2_EMAIL);
        assertNotNull(recomms);
        JAXB.marshal(recomms, System.out);        
        Experts experts = recomms.getExperts();
        assertNotNull(experts);
        assertTrue(experts.getBusinessActors().size() > 0);
    }

}
