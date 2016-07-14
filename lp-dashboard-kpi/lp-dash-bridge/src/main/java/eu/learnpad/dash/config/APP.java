/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.dash.config;

import java.io.File;
import java.nio.file.Files;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 * Encapsulates configuration properties for this component.
 *
 * @author sandro.emmenegger
 */
public class APP {

    private static Logger log = Logger.getLogger(APP.class);
    public static PropertiesConfiguration CONF;

    static {
        try {
            CONF = new PropertiesConfiguration("config/component.properties");
        } catch (ConfigurationException ex) {
            log.error("Cannot load application properties for dashboard component.", ex);
        }
    }
    
    /**
     * Lookup list of working directories and returns first existing one or null if none exists.
     * @return 
     */
    public static File getWorkingDirectory(){
        String[] workdirs = CONF.getStringArray("lp-dash.working.directory");
        if(workdirs == null || workdirs.length == 0){
            return null;
        }
        for (String workdir : workdirs) {
            File existingDir = new File(workdir);
            if(existingDir.exists()){
                return existingDir;
            }
        }
        return null;
       
    }
}
