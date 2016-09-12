/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.kpi.dashboard;

import eu.learnpad.core.impl.or.XwikiCoreFacadeRestResource;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.ontology.config.APP;
import eu.learnpad.ontology.kpi.KBProcessorNotifier;
import eu.learnpad.or.CoreFacade;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatusType;

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
        KBProcessorNotifier dummyNotifier = new DummyKBProcessingStatusNotifier();
        
        KPILoader instance = new KPILoader(dummyNotifier, MODELSET_ID);
        instance.run();
        
    }
    
    @Test
    @Ignore
    public void testRemoteRun() {
        System.out.println("run");
        
        KBProcessorNotifier dummyNotifier = new DummyRemoteKBProcessingStatusNotifier();
        
        KPILoader instance = new KPILoader(dummyNotifier, MODELSET_ID);
        instance.run();
        
    }

    class DummyKBProcessingStatusNotifier implements KBProcessorNotifier{
		@Override
		public void notifyProcessingStatus(String kbProcessId,
				KBProcessingStatusType status) {
			// TODO Auto-generated method stub				
		}    	

		@Override
        public void notifyKPIValues(String modelSetId, KPIValuesFormat format, String businessActorId, InputStream cockpitContent) throws LpRestException {
            File kpiDashboardFilesFolder = new File(APP.CONF.getString("working.directory")+"/" + APP.CONF.getString("kpi.dashboard.data.folder.relative"));
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
    
    class DummyRemoteKBProcessingStatusNotifier implements KBProcessorNotifier{
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
