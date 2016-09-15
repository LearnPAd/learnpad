/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.persistence.util.OntUtil;
import eu.learnpad.ontology.recommender.Inferencer;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.ontology.transformation.SimpleModelTransformator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.XdmAtomicValue;

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
     * @return
     * @throws eu.learnpad.ontology.recommender.RecommenderException
     */
    public Map<String, byte[]> runAssessment() throws RecommenderException {

        //1. run inferencer and apply KPI rules
        OntModel model = FileOntAO.getInstance().getModelWithLatestExecutionData(SimpleModelTransformator.getInstance().getLatestModelSetId());
        Inferencer kpiInferencer = new Inferencer(model);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        kpiInferencer.getModel().writeAll(bout, "RDF/XML");
        byte[] modelAsByteArray = bout.toByteArray();

        //2. lookup persons/performers
        List<Individual> persons = getAllPersons(kpiInferencer.getModel());

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tFactory.newTransformer(new StreamSource(KpiDashboard.class.getResourceAsStream(APP.CONF.getString("kpi.dashboard.xslt.file"))));
        } catch (TransformerConfigurationException ex) {
            throw new RecommenderException("Cannot initialize xslt transformer for KPI to dashboard transformations. ", ex);
        }

        Map<String, byte[]> dasboardKpisOfBusinessActors = new HashMap<>();

        for (Individual person : persons) {
            String businessActorId = OntUtil.getLiteralPropertyString(kpiInferencer.getModel(), person.getURI(), APP.NS.EMO + "performerHasEmailAddress", null);
            if (businessActorId != null) {
                byte[] dashboard = createBusinessActorDashboard(businessActorId, person.getURI(), modelAsByteArray, transformer);
                if (dashboard != null && dashboard.length > 0) {
                    dasboardKpisOfBusinessActors.put(businessActorId, dashboard);
                }
            }
        }

        //3. lookup organisational units
        List<Individual> organisationalUnits = getAllOrganisationalUnits(kpiInferencer.getModel());

        for (Individual organisationalUnit : organisationalUnits) {
            byte[] dashboard = createBusinessActorDashboard(organisationalUnit.getLocalName(), organisationalUnit.getURI(), modelAsByteArray, transformer);
            if (dashboard != null && dashboard.length > 0) {
                dasboardKpisOfBusinessActors.put(organisationalUnit.getLocalName(), dashboard);
            }
        }

        return dasboardKpisOfBusinessActors;
    }

    private byte[] createBusinessActorDashboard(String businessActorId, String businessActorUri, byte[] modelAsByteArray, Transformer transformer) {

            ByteArrayInputStream bin = new ByteArrayInputStream(modelAsByteArray);
            StreamSource modelSource = new StreamSource(bin);
            transformer.setParameter("businessActorUri", new XdmAtomicValue(businessActorUri));
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(bout);
            try {
                transformer.transform(modelSource, result);
            } catch (TransformerException ex) {
                LOGGER.log(Level.WARNING, "Failed to transform model KPI's to dashboard xml for business actor: " + businessActorId, ex);
            }
            return bout.toByteArray();
    }

    private List<Individual> getAllPersons(OntModel model) {
        OntClass rootClass = model.getOntClass(APP.NS.OMM + "Performer");
        return OntUtil.getInstances(model, rootClass);
    }

    private List<Individual> getAllOrganisationalUnits(OntModel model) {
        OntClass rootClass = model.getOntClass(APP.NS.OMM + "OrganisationalUnit");
        return OntUtil.getInstances(model, rootClass);
    }

}
