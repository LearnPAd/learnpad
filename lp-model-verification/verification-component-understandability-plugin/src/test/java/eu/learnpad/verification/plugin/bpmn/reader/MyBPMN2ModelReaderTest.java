package eu.learnpad.verification.plugin.bpmn.reader;

import static org.junit.Assert.*;

import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;



import org.junit.Test;

import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactoryTest;

public class MyBPMN2ModelReaderTest {

    @Test
    public void test() {
        try {
            URL is = GuidelinesFactoryTest.class.getClassLoader().getResource("TitoloUnico_MontiAzzurrSUB.bpmn");
            assertNotNull(is);
           /* File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);
*/
            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();


            readerBPMN.readJavaURIModel(is.toURI().toString());

            
        } catch (IOException | URISyntaxException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
    }
    
    
    @Test
    public void test2() {
        try {
        	URL is = GuidelinesFactoryTest.class.getClassLoader().getResource("modelloTest.bpmn");
            assertNotNull(is);
           /* File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);
*/
            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();


            readerBPMN.readJavaURIModel(is.toURI().toString());
            //readerBPMN.ReadThisModel(temp.getAbsolutePath());

            
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
    }
    

    @Test
    public void testE() {
        try {
        	URL is = GuidelinesFactoryTest.class.getClassLoader().getResource("modelloTestO.bpmn");
            assertNotNull(is);
           /* File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);
*/
            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();

            readerBPMN.readJavaURIModel(is.toURI().toString());
           // readerBPMN.ReadThisModel(temp.getAbsolutePath());

            
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
    }
    

}

