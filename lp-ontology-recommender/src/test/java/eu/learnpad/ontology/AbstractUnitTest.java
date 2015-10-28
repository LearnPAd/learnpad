/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology;

import eu.learnpad.ontology.config.APP;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.Assert.*;

/**
 *
 * @author sandro.emmenegger
 */
public abstract class AbstractUnitTest {
    
    public static final String TEST_MODEL_SET_ID_1 = "modelset-222"; 
    public static final String TEST_MODEL_SET_ID_2 = "modelset-224";
    public static final String TEST_MODEL_SET_ID_TITOLO_UNICO = "modelset-titolo-unico-v4";
    public static final String TEST_USER_1_NAME = "Barnaby Barnes";
    public static final String TEST_USER_1_EMAIL= "barnaby.barnes@fhnw.ch";
    
    public static final String TEST_MODEL_SET_ID_TITOLO_UNICO_V5 = "modelset-titolo-unico-v5";
    public static final String TEST_USER_2_EMAIL= "barnaby.barnes@learnpad.com";

    protected void testPath(String configKey) {
        for (String pathString : APP.CONF.getStringArray(configKey)) {
            Path path = Paths.get(pathString);
            assertNotNull(path);
            assertTrue(path.toFile().isDirectory());
        }
    }

}