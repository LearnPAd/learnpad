/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import eu.learnpad.ontology.AbstractUnitTest;
import eu.learnpad.ontology.config.APP;
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

/**
 * Base class for KPI related tests. Supports creation and handling of testdata.
 *
 * @author sandro.emmenegger
 */
public abstract class AbstractKpiTest extends AbstractUnitTest {

    private final static String TEST_WIKI_PAGE_URI = "http://learnpad.eu/unittest/NotificationLogTest_Page";
    private static Individual testWikiPage;

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

    protected Individual createSimScoreLog(Long timestamp, String simulationSessionId,
            String modelSetId, String processArtifactId, String userId,
            ScoreType scoreType, Float score) throws RecommenderException {

        Individual logEntry = SimulationScoreLog.getInstance().logSimulationScore(timestamp, simulationSessionId, modelSetId, processArtifactId, userId, scoreType, score);
        addLog(scoreType, logEntry);
        return logEntry;
    }

    protected Individual createUserActionLog(String modelSetId, String modelId,
            String artifactId, String resourceId, ResourceType resourceType,
            String referringToResourceId, String userId, Long timestamp,
            NotificationActionType action) throws RecommenderException {

        Individual logEntry = UserActionNotificationLog.getInstance().logResourceNotification(modelSetId, modelId, artifactId, resourceId, resourceType, referringToResourceId, userId, timestamp, action);
        addLog(resourceType, logEntry);
        return logEntry;
    }

    protected Individual getTestWikiPage(OntModel model) {
        if (testWikiPage == null) {
            OntClass pageClass = model.getOntClass(APP.NS.XWIKI + "Page");
            testWikiPage = pageClass.createIndividual(TEST_WIKI_PAGE_URI);
            OntProperty pageUrlProperty = model.getOntProperty(APP.NS.XWIKI + "pageHasURL");
            Literal value = model.createTypedLiteral(TEST_WIKI_PAGE_URI);
            testWikiPage.addProperty(pageUrlProperty, value);
        }
        return testWikiPage;
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
}
