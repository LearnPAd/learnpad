package eu.learnpad.verification.plugin.bpmn.guideline.factory;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.MyBPMN2ModelReader;

public class GuidelinesFactoryTest {

    @Test
    public void testGuidelinesFactoryListOfRootElement() {
        genarateTestforFile("ExplicitStartEndEvents.bpmn","16");
        genarateTestforFile("annidategateway.bpmn","20");
        genarateTestforFile("SplitAndJoinFlows.bpmn","21");
        genarateTestforFile("modelloTest.bpmn","21");
        genarateTestforFile("modelloTestO.bpmn","21");
    }
    
    private void genarateTestforFile(String NameFile,String id){
        
        try {
            InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream(NameFile);
            assertNotNull(is);
            File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();




            GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readFileModel(temp.getAbsolutePath()));
            //System.out.println(eg);
            
            for ( abstractGuideline iterable_element : eg.getGuidelines()) {
                if(iterable_element.getid().equals(id)){
                    if(iterable_element.getStatus()){
                        fail();
                    }
                }
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(GuidelinesFactory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);



            //jaxbMarshaller.marshal(eg, System.out);
            assertTrue(eg.getStatus().equals("not OK"));
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGuidelinesFactoryListOfRootElementGood() {
        try {
            InputStream is = GuidelinesFactoryTest.class.getClassLoader().getResourceAsStream("TitoloUnico_MontiAzzurrSUB.bpmn");
            assertNotNull(is);
            File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();




            GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readFileModel(temp.getAbsolutePath()));
            //System.out.println(eg);

            JAXBContext jaxbContext = JAXBContext.newInstance(GuidelinesFactory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);



            //jaxbMarshaller.marshal(eg, System.out);
            assertTrue(eg.getStatus().equals("OK"));
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
