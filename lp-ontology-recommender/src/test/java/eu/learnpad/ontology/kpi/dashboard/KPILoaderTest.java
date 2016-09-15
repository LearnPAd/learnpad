/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.kpi.KBProcessorNotifier;
import static eu.learnpad.ontology.kpi.dashboard.AbstractKpiTest.TEST_WIKI_PAGE_URI;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.simulation.SimulationScoreLog;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import eu.learnpad.or.CoreFacade;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatusType;
import eu.learnpad.sim.rest.event.ScoreType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import static junit.framework.Assert.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author sandro.emmenegger
 */
public class KPILoaderTest extends AbstractKpiTest {

    public KPILoaderTest() {
    }

    @Test
    public void testRun() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, RecommenderException {

        cleanUpDataFile();
        
        String kpiValueXPath = "//CRITERION[@NAME=\"" + KPI_LABEL__GLOBAL_ACTIONS + "\"]//ATTRIBUTES[@NAME=\"IsValue\"]/VALUE";
        
        Long previousLastModified = System.currentTimeMillis();
        runLoader();
        File testUserDashboardFile = new File(APP.CONF.getString("working.directory"), APP.CONF.getString("kpi.dashboard.data.folder.relative") + "/" + APP.CONF.getString("testdata.user.email") + "_cockpit.xml");
        assertTrue(testUserDashboardFile.exists());
        Long lastModified = testUserDashboardFile.lastModified();
        assertTrue(lastModified > previousLastModified);

        String kpiValue = extractKpiValue(testUserDashboardFile, kpiValueXPath);
        previousLastModified = lastModified;

        //create new page log
        createUserActionLog(MODELSET_ID, MODEL_ID, ARTIFACT_ID, TEST_WIKI_PAGE_URI, ResourceType.PAGE, TEST_USER, System.currentTimeMillis(), NotificationActionType.ADDED);

        //create comment on page
        createUserActionLog(MODELSET_ID, MODEL_ID, ARTIFACT_ID, TEST_WIKI_PAGE_URI + "_1", ResourceType.COMMENT, TEST_USER, System.currentTimeMillis(), NotificationActionType.ADDED);
        runLoader();
        lastModified = testUserDashboardFile.lastModified();
        assertTrue(lastModified > previousLastModified);
        kpiValue = extractKpiValue(testUserDashboardFile, kpiValueXPath);
        assertEquals("2", kpiValue);
        previousLastModified = lastModified;
        
        //update comment on page
        createUserActionLog(MODELSET_ID, MODEL_ID, ARTIFACT_ID, TEST_WIKI_PAGE_URI + "_1", ResourceType.COMMENT, TEST_USER, System.currentTimeMillis(), NotificationActionType.MODIFIED);
        runLoader();
        lastModified = testUserDashboardFile.lastModified();
        assertTrue(lastModified > previousLastModified);
        
        kpiValue = extractKpiValue(testUserDashboardFile, kpiValueXPath);
        assertEquals("3", kpiValue);
        
        //with sim log
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(SimpleModelTransformator.getInstance().getLatestModelSetId());
        SimulationScoreLog.getInstance().logSimulationScore(System.currentTimeMillis(), UUID.randomUUID().toString(), MODELSET_ID, getOneProcess(model).getLocalName(), TEST_USER, SIM_TEST_SCORES);
        runLoader();

        //Global score
        kpiValueXPath = "//CRITERION[@NAME=\"" + KPI_LABEL__GLOBAL_SCORE + "\"]//ATTRIBUTES[@NAME=\"IsValue\"]/VALUE";
        kpiValue = extractKpiValue(testUserDashboardFile, kpiValueXPath);
        Float expectedScore = (SIM_TEST_SCORES.get(ScoreType.GLOBAL_SCORE)/SIM_TEST_SCORES.get(ScoreType.ABSOLUTE_GLOBAL_SCORE))*100;
        assertEquals(expectedScore, Float.valueOf(kpiValue), 0.1f);   
        
        //Session score
        kpiValueXPath = "//CRITERION[@NAME=\"" + KPI_LABEL__SESSION_SCORE + "\"]//ATTRIBUTES[@NAME=\"IsValue\"]/VALUE";
        kpiValue = extractKpiValue(testUserDashboardFile, kpiValueXPath);
        expectedScore = (SIM_TEST_SCORES.get(ScoreType.SESSION_SCORE)/SIM_TEST_SCORES.get(ScoreType.ABSOLUTE_SESSION_SCORE))*100;
        assertEquals(expectedScore, Float.valueOf(kpiValue), 0.1f);   

        //BP score
        kpiValueXPath = "//CRITERION[@NAME=\"" + KPI_LABEL__BP_SCORE + "\"]//ATTRIBUTES[@NAME=\"IsValue\"]/VALUE";
        kpiValue = extractKpiValue(testUserDashboardFile, kpiValueXPath);
        expectedScore = (SIM_TEST_SCORES.get(ScoreType.BP_SCORE)/SIM_TEST_SCORES.get(ScoreType.ABSOLUTE_BP_SCORE))*100;
        assertEquals(expectedScore, Float.valueOf(kpiValue), 0.1f);               
    }

    protected String extractKpiValue(File testUserDashboardFile, String kpiValueXPath) throws SAXException, XPathExpressionException, IOException, ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(testUserDashboardFile);
        XPath xPath = XPathFactory.newInstance().newXPath();
        String kpiValue = xPath.compile(kpiValueXPath).evaluate(xmlDocument);
        return kpiValue;
    }

    protected void runLoader() {

        KBProcessorNotifier dummyNotifier = new DummyKBProcessingStatusNotifier();
        KPILoader instance = new KPILoader(dummyNotifier, MODELSET_ID);
        instance.run();
        while (instance.isAlive()) {
            try {
                Thread.currentThread().wait(1000);
            } catch (InterruptedException ex) {
            }
        }
    }

    @Test
    @Ignore
    public void testRemoteRun() {
        System.out.println("run");

        KBProcessorNotifier dummyNotifier = new DummyRemoteKBProcessingStatusNotifier();

        KPILoader instance = new KPILoader(dummyNotifier, MODELSET_ID);
        instance.run();

    }

    class DummyKBProcessingStatusNotifier implements KBProcessorNotifier {

        @Override
        public void notifyProcessingStatus(String kbProcessId,
                KBProcessingStatusType status) {
            // TODO Auto-generated method stub				
        }

        @Override
        public void notifyKPIValues(String modelSetId, KPIValuesFormat format, String businessActorId, InputStream cockpitContent) throws LpRestException {
            File kpiDashboardFilesFolder = new File(APP.CONF.getString("working.directory") + "/" + APP.CONF.getString("kpi.dashboard.data.folder.relative"));
            if (!kpiDashboardFilesFolder.exists()) {
                kpiDashboardFilesFolder.mkdirs();
            }
            File cockpitXmlFile = new File(kpiDashboardFilesFolder + "/" + businessActorId + "_cockpit.xml");
            try {
                Files.copy(cockpitContent, cockpitXmlFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Assert.fail("Writing cockpit file faild." + ex.getLocalizedMessage());
            }
        }
    }

    class DummyRemoteKBProcessingStatusNotifier implements KBProcessorNotifier {

        @Override
        public void notifyProcessingStatus(String kbProcessId,
                KBProcessingStatusType status) {
            // TODO Auto-generated method stub				
        }

        @Override
        public void notifyKPIValues(String modelSetId, KPIValuesFormat format, String businessActorId, InputStream cockpitContent) throws LpRestException {
            CoreFacade corefacade = new XwikiCoreFacadeRestResource();
            corefacade.pushKPIValues(modelSetId, format, businessActorId, cockpitContent);
        }
    }
}
