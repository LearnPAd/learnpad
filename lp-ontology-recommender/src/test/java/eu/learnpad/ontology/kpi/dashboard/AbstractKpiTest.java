/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.simulation.SimulationScoreLog;
import eu.learnpad.ontology.wiki.UserActionNotificationLog;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.sim.rest.event.ScoreType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static junit.framework.Assert.*;

/**
 * Base class for KPI related tests. Supports creation and handling of testdata.
 *
 * @author sandro.emmenegger
 */
public abstract class AbstractKpiTest extends AbstractUnitTest {

//    protected final static String TEST_WIKI_PAGE_URI = "http://learnpad.eu/unittest/NotificationLogTest_Page";
    protected final static String TEST_WIKI_PAGE_URI = UserActionNotificationLog.createResourceId(MODELSET_ID, MODEL_ID, ARTIFACT_ID);
    private static Individual testWikiPage;
    
    protected static final String KPI_LABEL__BP_SCORE = "business process score per simulation and user";
    protected static final String KPI_LABEL__SESSION_SCORE = "session score per simulation and user";
    protected static final String KPI_LABEL__GLOBAL_SCORE = "global score per simulation and user";
    protected static final String KPI_LABEL__GLOBAL_ACTIONS = "global actions per user";

    protected final static Map<ScoreType, Float> SIM_TEST_SCORES = new HashMap();

    static {
        SIM_TEST_SCORES.put(ScoreType.ABSOLUTE_BP_SCORE, 107.0f);
        SIM_TEST_SCORES.put(ScoreType.ABSOLUTE_GLOBAL_SCORE, 107.0f);
        SIM_TEST_SCORES.put(ScoreType.ABSOLUTE_SESSION_SCORE, 8.0f);
        SIM_TEST_SCORES.put(ScoreType.BP_COVERAGE, 11.0f);
        SIM_TEST_SCORES.put(ScoreType.BP_SCORE, 6.0f);
        SIM_TEST_SCORES.put(ScoreType.GLOBAL_SCORE, 6.0f);
        SIM_TEST_SCORES.put(ScoreType.RELATIVE_BP_SCORE, 7.0f);
        SIM_TEST_SCORES.put(ScoreType.RELATIVE_GLOBAL_SCORE, 7.0f);
        SIM_TEST_SCORES.put(ScoreType.SESSION_SCORE, 3.0f);
    }

    private Map<String, List<Individual>> logEntries = new HashMap();
    private List<Individual> testWikiPages = new ArrayList();

    protected List<Individual> logs(ScoreType kpiLogType) {
        return logs(kpiLogType.toString());
    }

    protected List<Individual> logs(ResourceType kpiLogType) {
        return logs(kpiLogType.toString());
    }

    protected List<Individual> logs(String kpiLogType) {
        if (!logEntries.containsKey(kpiLogType)) {
            logEntries.put(kpiLogType, new ArrayList<Individual>());
        }
        return logEntries.get(kpiLogType);
    }

    protected void addLog(ScoreType kpiLogType, Individual logEntry) {
        logs(kpiLogType).add(logEntry);
    }

    protected void addLog(ResourceType kpiLogType, Individual logEntry) {
        if (logEntry != null) {
            logs(kpiLogType).add(logEntry);
        }
    }

    protected void addTestPage(Individual testPage) {
        testWikiPages.add(testPage);
    }

    protected Individual createUserActionLog(String modelSetId, String modelId,
            String artifactId, String resourceId, ResourceType resourceType,
            String userId, Long timestamp,
            NotificationActionType action) throws RecommenderException {

        Individual logEntry = UserActionNotificationLog.getInstance().logResourceNotification(modelSetId, modelId, artifactId, resourceId, resourceType, userId, timestamp, action);
        addLog(resourceType, logEntry);
        return logEntry;
    }

    protected void cleanUpSimScoreLogs() {
        for (ScoreType type : ScoreType.values()) {
            for (Individual logEntryToDelete : logs(type)) {
                logEntryToDelete.remove();
            }
        }
        if (testWikiPage != null) {
            testWikiPage.remove();
            testWikiPage = null;
        }
    }

    protected void cleanUpUserActionLogs() {
        for (ResourceType type : ResourceType.values()) {
            for (Individual logEntryToDelete : logs(type)) {
                logEntryToDelete.remove();
            }
        }
    }

    protected void cleanUp() {
        cleanUpSimScoreLogs();
        cleanUpUserActionLogs();
    }

    protected Individual getOneProcess(OntModel model) {
        OntClass processClass = model.getOntClass(APP.NS.BPMN + "Process");
        List<Individual> processes = OntUtil.getInstances(model, processClass);
        if (processes.isEmpty()) {
            fail("Expect at least one process model ontology for unit testing.");
        }
        Individual oneProcessForTesting = processes.get(0);
        return oneProcessForTesting;
    }
}
