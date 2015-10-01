/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.config;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author sandro.emmenegger
 */
public class APP {

    public static PropertiesConfiguration CONF;
    
    static{
        try {
            CONF = new PropertiesConfiguration("application.properties");
        } catch (ConfigurationException ex) {
            Logger.getLogger(APP.class.getName()).log(Level.SEVERE, "Cannot load application properties for ontology component.", ex);
        }
    }
    
    public static enum NS{
        EMO("http://ikm-group.ch/archiMEO/emo#"),
        ARCHI("http://ikm-group.ch/archiMEO/archimate");
        
        private String namespace;
        
        private NS(String ns){
            this.namespace = ns;
        }
        
        @Override
        public String toString(){
            return namespace;
        }
    }
}
