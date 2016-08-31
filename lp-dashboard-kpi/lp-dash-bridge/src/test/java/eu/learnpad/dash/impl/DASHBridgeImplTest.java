/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.dash.impl;

import eu.learnpad.dash.config.APP;
import eu.learnpad.dash.config.PropertyUtil;

import java.io.File;
import java.io.InputStream;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public class DASHBridgeImplTest {

    protected static final String MODELSET_ID = APP.CONF.getString("testdata.modelset.version");
    protected static final String TEST_USER = "barnaby.barnes@learnpad.com";

    private PropertyUtil conf;

    public DASHBridgeImplTest() {
    	this.conf=new PropertyUtil();
    }

    @Test
    public void testLoadKPIValues() throws Exception {
//        File baseWorkingDirectory = APP.getWorkingDirectory();
        File baseWorkingDirectory = this.conf.getWorkingDirectory();
        assertNotNull(baseWorkingDirectory);
        File dashobardFilesDir = new File(baseWorkingDirectory + "/" + this.conf.getProperty("lp-dash.kpi.dashboard.data.folder.relative"));
//        File dashobardFilesDir = new File(baseWorkingDirectory + "/" + APP.CONF.getString("lp-dash.kpi.dashboard.data.folder.relative"));
        if(dashobardFilesDir.exists()){
            deleteFiles(dashobardFilesDir);
        }
        
        InputStream cockpitContent = DASHBridgeImplTest.class.getResourceAsStream(this.conf.getProperty("testdata.kpi.input.file"));
//        InputStream cockpitContent = DASHBridgeImplTest.class.getResourceAsStream(APP.CONF.getString("testdata.kpi.input.file"));
        assertNotNull(cockpitContent);
        DASHBridgeImpl instance = new DASHBridgeImpl();
        instance.loadKPIValues(MODELSET_ID, null, TEST_USER, cockpitContent);
        
        File expectedDashboardKpiFile = new File(dashobardFilesDir + "/" + TEST_USER + "_cockpit.xml");
        assertTrue(expectedDashboardKpiFile.exists());
    }

    @Test
    public void testGetKPIValuesView() throws Exception {

        DASHBridgeImpl instance = new DASHBridgeImpl();
        String expResult = this.conf.getProperty("lp-dash.cockpit.url") + "?businessActorId=" + TEST_USER;
//        String expResult = APP.CONF.getString("lp-dash.cockpit.url") + "?businessActorId=" + TEST_USER;
        String result = instance.getKPIValuesView(MODELSET_ID, TEST_USER);
        assertEquals(expResult, result);
    }

    public static void deleteFiles(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();   //All files and sub folders
            for (int x = 0; files != null && x < files.length; x++) {
                deleteFiles(files[x]);
            }
        } else {
            file.delete();
        }
    }
}
