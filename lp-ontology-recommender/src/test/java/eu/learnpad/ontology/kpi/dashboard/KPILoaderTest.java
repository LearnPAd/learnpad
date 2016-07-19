/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.or.CoreFacade;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author sandro.emmenegger
 */
public class KPILoaderTest extends AbstractKpiTest {
    
    public KPILoaderTest() {
    }

    @Test
    public void testRun() {
        System.out.println("run");
        KPILoader instance = new KPILoader(new DummyCoreFacade() , MODELSET_ID);
        instance.run();
        
    }
    
    @Test
    @Ignore
    public void testAgainstARemoteDashboard() {
        System.out.println("run");
        KPILoader instance = new KPILoader(new XwikiCoreFacadeRestResource(), MODELSET_ID);
        instance.run();
        
    }
    
    class DummyCoreFacade implements CoreFacade{

        @Override
        public byte[] getComments(String modelSetId, String artifactId) throws LpRestException {
            return null;
        }

        @Override
        public InputStream getModel(String modelSetId, ModelSetType type) throws LpRestException {
            return null;
        }

        @Override
        public void pushKPIValues(String modelSetId, KPIValuesFormat format, String businessActorId, InputStream cockpitContent) throws LpRestException {
            File kpiDashboardFilesFolder = new File(APP.CONF.getString("working.directory")+"/" + APP.CONF.getString("kpi.dashboard.data.folder.relative") + "/testing");
            if(!kpiDashboardFilesFolder.exists()){
                kpiDashboardFilesFolder.mkdirs();
            }
            File cockpitXmlFile = new File(kpiDashboardFilesFolder+"/"+businessActorId+"_cockpit.xml");
            try {
                Files.copy(cockpitContent, cockpitXmlFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Assert.fail("Writing cockpit file faild." + ex.getLocalizedMessage());
            }
        }
        
    }
    
}
