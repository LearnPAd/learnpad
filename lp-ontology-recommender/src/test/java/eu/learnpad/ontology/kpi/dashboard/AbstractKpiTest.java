/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.wiki.UserActionNotificationLog;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import org.junit.Before;

/**
 *
 * @author sandro.emmenegger
 */
public class AbstractKpiTest extends AbstractUnitTest {
    
    public AbstractKpiTest() {
    }

    @Before
    public void setupTestData() throws RecommenderException {
        new Inferencer(getLatestModel());
        String pageUrl = "http://learnpad.eu/unittest/TestPage";
        Long timestamp = System.currentTimeMillis();
        UserActionNotificationLog.getInstance().logResourceNotification(MODELSET_ID, null, null, pageUrl, ResourceType.PAGE, null, TEST_USER, timestamp, NotificationActionType.ADDED);
        UserActionNotificationLog.getInstance().logResourceNotification(MODELSET_ID, null, null, "1", ResourceType.COMMENT, pageUrl, TEST_USER, timestamp, NotificationActionType.ADDED);
    }

    protected OntModel getLatestModel() throws RecommenderException {
        String latestModelSetVersion = SimpleModelTransformator.getInstance().getLatestModelSetId();
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(latestModelSetVersion);
        return model;
    }
    
}
