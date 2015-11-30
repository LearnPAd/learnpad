package eu.learnpad.verification.plugin.bpmn.reader;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactory;
import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactoryTest;

public class MyBPMN2ModelReaderTest {

    @Test
    public void test() {
        try {
            InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("TitoloUnico_MontiAzzurrSUB.bpmn");
            assertNotNull(is);
            File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();


            readerBPMN.ReadThisModel(temp.getAbsolutePath());

            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
    }
    
    
    @Test
    public void test2() {
        try {
            InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("modelloTest.bpmn");
            assertNotNull(is);
            File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();


            readerBPMN.ReadThisModel(temp.getAbsolutePath());

            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
    }
    

    @Test
    public void testE() {
        try {
            InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("modelloTestO.bpmn");
            assertNotNull(is);
            File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();


            readerBPMN.ReadThisModel(temp.getAbsolutePath());

            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        }
    }
    

}

