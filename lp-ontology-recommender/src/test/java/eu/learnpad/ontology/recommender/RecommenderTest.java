/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

import eu.learnpad.ontology.AbstractUnitTest;
import static eu.learnpad.ontology.AbstractUnitTest.TEST_MODEL_SET_ID_TITOLO_UNICO;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.or.rest.data.Experts;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import javax.inject.Inject;
import javax.xml.bind.JAXB;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({FileOntAO.class})
public class RecommenderTest extends AbstractUnitTest {

    @Inject
    Recommender recommender;

    @Inject
    SimpleModelTransformator transformator;

    @Before
    public void init() {
        transformator.setLatestModelSetId(TEST_MODEL_SET_ID_TITOLO_UNICO);
    }

    @Test
    public void testSuggestExpertWithSameRole() {
        Recommendations recomms = recommender.getRecommendations(TEST_MODEL_SET_ID_TITOLO_UNICO, "", TEST_USER_1_EMAIL);
        assertNotNull(recomms);
        JAXB.marshal(recomms, System.out);        
        Experts experts = recomms.getExperts();
        assertNotNull(experts);
        assertTrue(experts.getBusinessActors().size() > 0);
    }

}
