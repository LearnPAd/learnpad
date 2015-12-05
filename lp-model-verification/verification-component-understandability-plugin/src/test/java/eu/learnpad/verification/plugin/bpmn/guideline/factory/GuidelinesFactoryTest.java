package eu.learnpad.verification.plugin.bpmn.guideline.factory;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;



import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.MyBPMN2ModelReader;

public class GuidelinesFactoryTest {

    @Test
    public void testGuidelinesFactoryListOfRootElement() {
    	genarateTestforFile("24485.bpmn","20");
        genarateTestforFile("ExplicitStartEndEvents.bpmn","12");
        genarateTestforFile("annidategateway.bpmn","16");
        genarateTestforFile("SplitAndJoinFlows.bpmn","18");
        genarateTestforFile("modelloTest.bpmn","18");
        genarateTestforFile("modelloTestO.bpmn","18");
        //genarateTestforFile("test7.bpmn","14");
        genarateTestforFile("test7.bpmn","13");
       
    }
    
    private void genarateTestforFile(String NameFile,String id){
        
        try {

            URL is = GuidelinesFactoryTest.class.getClassLoader().getResource(NameFile);
            assertNotNull(is);
           // File temp = File.createTempFile("tempfiletest", ".tmp"); 
            //temp.deleteOnExit();

            //Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);


            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();





            GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readJavaURIModel(is.toURI().toString()));
           eg.setVerificationType("UNDERSTANDABILITY");
           eg.StartSequential();
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
           
            OutputStream os = new FileOutputStream( "nosferatu"+ NameFile.substring(0, NameFile.length()-4)+".xml" );
            jaxbMarshaller.marshal( eg, os );

           // jaxbMarshaller.marshal(eg, System.out);

            assertTrue(eg.getStatus().equals("KO"));
        } catch (JAXBException  | URISyntaxException  | IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

    }
    
    @Test
    public void testGuidelinesFactoryListOfRootElementGood() {
        try {

            URL is = GuidelinesFactoryTest.class.getClassLoader().getResource("TitoloUnico_MontiAzzurrSUB.bpmn");
            assertNotNull(is);
           /* File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);
*/

            MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();





            GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readJavaURIModel(is.toURI().toString()));
            eg.setVerificationType("UNDERSTANDABILITY");
            eg.StartSequential();
            //System.out.println(eg);

            JAXBContext jaxbContext = JAXBContext.newInstance(GuidelinesFactory.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);



            //jaxbMarshaller.marshal(eg, System.out);
            assertTrue(eg.getStatus().equals("OK"));

        } catch (JAXBException  | URISyntaxException  | IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 


    }

}
