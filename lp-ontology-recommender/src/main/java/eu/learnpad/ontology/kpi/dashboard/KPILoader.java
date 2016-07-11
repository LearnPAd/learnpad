/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.or.CoreFacade;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Worker thread to calculate all KPI values for each business actor in the
 * models asynchronously and push the results to the dashboard component.
 *
 * @author sandro.emmenegger
 */
public class KPILoader extends Thread {

    private final CoreFacade corefacade;
    private final String modelSetId;

    public KPILoader(CoreFacade corefacade, String modelSetId) {
        this.corefacade = corefacade;
        this.modelSetId = modelSetId;
    }

    @Override
    public void run() {
        try {
            Map<String, byte[]> dasboardKpisOfBusinessActors = KpiDashboard.getInstance().runAssessment();
            for (Map.Entry<String, byte[]> entry : dasboardKpisOfBusinessActors.entrySet()) {
                String businessActorId = entry.getKey();
                byte[] dashboard = entry.getValue();
                InputStream dashboardStream = new ByteArrayInputStream(dashboard);
                this.corefacade.pushKPIValues(modelSetId, KPIValuesFormat.ADOXXCockpit, businessActorId, dashboardStream);
    
            }
        } catch (RecommenderException ex) {
            Logger.getLogger(KPILoader.class.getName()).log(Level.SEVERE, "KPI assessment failed. ", ex);
        } catch (LpRestException ex) {
            Logger.getLogger(KPILoader.class.getName()).log(Level.SEVERE, "Calling corefacade pushKPIValues failed. ", ex);
        }
    }

}
