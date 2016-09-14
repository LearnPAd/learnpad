/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;

import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.kpi.KBProcessorNotifier;
import eu.learnpad.ontology.kpi.data.SOMEService;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatusType;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 * Worker thread to calculate all KPI values for each business actor in the
 * models asynchronously and push the results to the dashboard component.
 *
 * @author sandro.emmenegger
 */
public class KPILoader extends Thread {

    private final KBProcessorNotifier notifier;
    private final String modelSetId;
    
    private final String computationProcessID;

    public KPILoader(KBProcessorNotifier notifier, String modelSetId) {
        this.modelSetId = modelSetId;
        this.notifier = notifier;
        
        String idPrefix = "KPI_"; 
        String idPostfix = String.valueOf(System.currentTimeMillis());
        this.computationProcessID = idPrefix + idPostfix;
    }

    @Override
    public void run() {
        try {
            this.notifier.notifyProcessingStatus(this.computationProcessID, KBProcessingStatusType.IN_PROGRESS);
        	
            //1. load external data files (excel files) from working directory if available
            File kpiDataFolder = new File(APP.CONF.getString("working.directory") + "/" + APP.CONF.getString("kpi.dashboard.data.folder.relative"));
            if (kpiDataFolder.exists()) {
                File[] dataFiles = kpiDataFolder.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathToFile) {
                        if (pathToFile.isFile() && pathToFile.getName().endsWith(".xlsx")) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                OntModel model = FileOntAO.getInstance().getModelSet(this.modelSetId);

                for (File dataFile : dataFiles) {
                    SOMEService service = new SOMEService(dataFile);
                    Model insertedKPIValuesModel = service.getModel(model);
                    model.add(insertedKPIValuesModel);
                }

            }

            Map<String, byte[]> dasboardKpisOfBusinessActors = KpiDashboard.getInstance().runAssessment();
            for (Map.Entry<String, byte[]> entry : dasboardKpisOfBusinessActors.entrySet()) {
                String businessActorId = entry.getKey();
                byte[] dashboard = entry.getValue();
                InputStream dashboardStream = new ByteArrayInputStream(dashboard);
                this.notifier.notifyKPIValues(modelSetId, KPIValuesFormat.ADOXXCockpit, businessActorId, dashboardStream);
            }
        } catch (RecommenderException ex) {
            Logger.getLogger(KPILoader.class.getName()).log(Level.SEVERE, "KPI assessment failed. ", ex);
            this.notifier.notifyProcessingStatus(this.computationProcessID, KBProcessingStatusType.ABORTED);
        } catch (LpRestException ex) {
            Logger.getLogger(KPILoader.class.getName()).log(Level.SEVERE, "Calling corefacade pushKPIValues failed. ", ex);
            this.notifier.notifyProcessingStatus(this.computationProcessID, KBProcessingStatusType.ABORTED);
        } catch (IOException ex) {
            Logger.getLogger(KPILoader.class.getName()).log(Level.SEVERE, "Error when loading KPI data file. ", ex);
            this.notifier.notifyProcessingStatus(this.computationProcessID, KBProcessingStatusType.ABORTED);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(KPILoader.class.getName()).log(Level.SEVERE, null, ex);
            this.notifier.notifyProcessingStatus(this.computationProcessID, KBProcessingStatusType.ABORTED);
        }
        this.notifier.notifyProcessingStatus(this.computationProcessID, KBProcessingStatusType.COMPLETED);
    }

	public String getKPILoaderID() {
		return this.computationProcessID;
	}

    
}
