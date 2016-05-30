/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.google.common.io.ByteStreams;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.jena.riot.Lang;

/**
 * Runs inferencer with KPI rules evaluate KPI's. Write dasboard data files with
 * infered KPI values for each individual (person/user/performer) and each
 * organisational unit to the working directory.
 *
 * @author sandro.emmenegger
 */
public class KpiDashboard {

    private static final Logger LOGGER = Logger.getLogger(KpiDashboard.class.getName());

    private static final KpiDashboard SINGLETON = new KpiDashboard();

    private KpiDashboard() {
    }

    public static KpiDashboard getInstance() {
        return SINGLETON;
    }

    /**
     * Performs a KPI assessment and creates the result as data file for the
     * dashboard.
     *
     */
    public void runAssessment() {

        //1. run inferencer and apply KPI rules
        OntModel model = FileOntAO.getInstance().getModelWithExecutionData(SimpleModelTransformator.getInstance().getLatestModelSetId());
        Inferencer kpiInferencer = new Inferencer(model);
        
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tFactory.newTransformer(new StreamSource(KpiDashboard.class.getResourceAsStream(APP.CONF.getString("kpi.dashboard.xslt.file"))));
        } catch (TransformerConfigurationException ex) {
            LOGGER.log(Level.SEVERE, "Cannot initialize xslt transformer for KPI to dashboard transformations. ", ex);
            return;
        }
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        kpiInferencer.getModel().write(bout);
        byte[] modelAsByteArray = bout.toByteArray();
        
        //2. lookup persons/performers
        List<Individual> persons = getAllPersons(kpiInferencer.getModel());
        
        for (Individual person : persons) {
            String businessActorId = OntUtil.getLiteralPropertyString(kpiInferencer.getModel(), person.getURI(), APP.NS.EMO + "performerHasEmailAddress", null);
            if (businessActorId != null) {
                createBusinessActorDashboard(businessActorId, modelAsByteArray, transformer);
            }
        }

        //3. lookup organisational units
        //4. do transformation for organisational units
    }

    private void createBusinessActorDashboard(String businessActorId, byte[] modelAsByteArray, Transformer transformer) {
        File cockpitXmlFile = getKpiDashboardFile(businessActorId);
        if (cockpitXmlFile != null) {
            ByteArrayInputStream bin = null;
            StreamSource modelSource = null;
            try {
                bin = new ByteArrayInputStream(modelAsByteArray);
                modelSource = new StreamSource(bin);
                transformer.transform(modelSource, new StreamResult(cockpitXmlFile));
            } catch (TransformerException ex) {
                LOGGER.log(Level.WARNING, "Failed to transform model KPI's to dashboard xml for business actor: " + businessActorId, ex);
            }
        }
    }

    private List<Individual> getAllPersons(OntModel model) {
        OntClass rootClass = model.getOntClass(APP.NS.EO + "Person");
        return OntUtil.getInstances(model, rootClass);
    }

    private File getKpiDashboardFile(String businessActorId) {
        File kpiDashboardWorkingFolder = null;
        File cockpitXmlFile = null;
        try {
            Path kpiDashboardWorkingFolderPath = Paths.get(APP.CONF.getString("working.directory"), APP.CONF.getString("kpi.dashboard.data.folder.relative"));
            kpiDashboardWorkingFolder = kpiDashboardWorkingFolderPath.toFile();
            if (!kpiDashboardWorkingFolder.getParentFile().exists()) {
                kpiDashboardWorkingFolder.getParentFile().mkdirs();
            }
            cockpitXmlFile = new File(kpiDashboardWorkingFolder, businessActorId + "_cockpit.xml");
            if (!cockpitXmlFile.exists()) {
                cockpitXmlFile.createNewFile();
            }

        } catch (Exception ex) {
            LOGGER.log(Level.WARNING, "Cannot create KPI dashboard file for business actor: " + businessActorId, ex);
        }
        return cockpitXmlFile;
    }

}
