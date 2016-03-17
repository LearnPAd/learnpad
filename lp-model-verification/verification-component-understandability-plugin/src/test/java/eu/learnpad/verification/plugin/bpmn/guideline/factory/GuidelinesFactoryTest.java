package eu.learnpad.verification.plugin.bpmn.guideline.factory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.MyBPMN2ModelReader;

public class GuidelinesFactoryTest {

	@Test
	public void testGuidelinesFactoryListOfRootElement() {
		genarateTestforFile("lanes.bpmn","11");
		genarateTestforFile("lanes.bpmn","29");
		genarateTestforFile("pizza.bpmn","16");
		genarateTestforFile("24485.bpmn","16");
		genarateTestforFile("ExplicitStartEndEvents.bpmn","12");
		genarateTestforFile("annidategateway.bpmn","16");
		genarateTestforFile("SplitAndJoinFlows.bpmn","18");
		genarateTestforFile("modelloTest.bpmn","18");
		genarateTestforFile("modelloTestO.bpmn","18");
		//genarateTestforFile("test7.bpmn","13");
		genarateTestforFile("test7.bpmn","13");
		genarateTestforFile("test8.bpmn","15");
		genarateTestforFile("MeaningfulGateways.bpmn","20");
		genarateTestforFile("testorg.bpmn","21");
		genarateTestforFile("test7.bpmn","30");
		genarateTestforFile("test7.bpmn","31");
		genarateTestforFile("test7.bpmn","33");
		genarateTestforFile("test7.bpmn","32");
		genarateTestforFile("test7.bpmn","34");
		genarateTestforFile("ConvergingGateways.bpmn","35");
		genarateTestforFile("ConvergingGateways.bpmn","36");
		genarateTestforFile("ConvergingGateways.bpmn","37");
		genarateTestforFile("LoopMarkerAnnotation.bpmn","39");
		String sep = File.separator;
		genarateTestforFile("journal"+sep+"EsempioFig1_1.bpmn","17");

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
						System.out.println(NameFile+" "+ id);
						fail();
					}
				}
			}
			JAXBContext jaxbContext = JAXBContext.newInstance(GuidelinesFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			//OutputStream os = new FileOutputStream( "nosferatu"+ NameFile.substring(0, NameFile.length()-4)+".xml" );
			//jaxbMarshaller.marshal( eg, os );

			// jaxbMarshaller.marshal(eg, System.out);

			assertTrue(eg.getStatus().equals("KO"));
		} catch (JAXBException  | URISyntaxException  | IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	@Test
	public void testGuidelinesFactoryListOfRootElementGood() {
		genarateTestforFileOk2("TitoloUnico_MontiAzzurrSUB.bpmn","2");
		genarateTestforFileOk2("EPBR - Business Process.bpmn","8");
		genarateTestforFileOk2("EPBR - Business Process.bpmn","35");
		genarateTestforFileOk2("EPBR - Business Process.bpmn","36");
		genarateTestforFileOk2("test7.bpmn","2");
		genarateTestforFileOk2("TitoloUnico/19311.bpmn","14");
	}
/*	private void genarateTestforFileOk(String NameFile,String id){
		try {

			URL is = GuidelinesFactoryTest.class.getClassLoader().getResource(NameFile);
			assertNotNull(is);
			 File temp = File.createTempFile("tempfiletest", ".tmp"); 
            temp.deleteOnExit();

            Files.copy(is,temp.toPath(),java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			 

			MyBPMN2ModelReader readerBPMN = new MyBPMN2ModelReader();





			GuidelinesFactory eg = new GuidelinesFactory(readerBPMN.readJavaURIModel(is.toURI().toString()));
			eg.setVerificationType("UNDERSTANDABILITY");
			eg.StartSequential();
			//System.out.println(eg);

			JAXBContext jaxbContext = JAXBContext.newInstance(GuidelinesFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			for ( abstractGuideline iterable_element : eg.getGuidelines()) {
				if(iterable_element.getid().equals(id)){
					if(!iterable_element.getStatus()){
						fail();
					}
				}
			}

			//OutputStream os = new FileOutputStream( "nosferatuB"+ NameFile.substring(0, NameFile.length()-4)+".xml" );
			//jaxbMarshaller.marshal( eg, os );

			//jaxbMarshaller.marshal(eg, System.out);
			assertTrue(eg.getStatus().equals("OK"));

		} catch (JAXBException  | URISyntaxException  | IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}*/

	
	private void genarateTestforFileOk2(String NameFile,String id){
		try {

			URL is = GuidelinesFactoryTest.class.getClassLoader().getResource(NameFile);
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

			for ( abstractGuideline iterable_element : eg.getGuidelines()) {
				if(iterable_element.getid().equals(id)){
					if(!iterable_element.getStatus()){
						fail();
					}
				}
			}

			//OutputStream os = new FileOutputStream( "nosferatuBB"+ NameFile.substring(0, NameFile.length()-4)+".xml" );
			//jaxbMarshaller.marshal( eg, os );

			//jaxbMarshaller.marshal(eg, System.out);
			//assertTrue(eg.getStatus().equals("OK"));

		} catch (JAXBException  | URISyntaxException  | IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}
	
	
	@Test
	public void testGuidelinesFactoryListGood() {
		List<String> ldirectory = new ArrayList<String>();
		String sep = File.separator;
		ldirectory.add("EPBR-Coordinator"+sep+"20903.bpmn");
		//ldirectory.add("EPBR-Coordinator"+sep+"21099.bpmn");
		//ldirectory.add("EPBR-Coordinator"+sep+"21203.bpmn");
		//ldirectory.add("EPBR-Coordinator"+sep+"21385.bpmn");
		ldirectory.add("EPBR-Coordinator"+sep+"21417.bpmn");
		ldirectory.add("EPBR-Coordinator"+sep+"21823.bpmn");
		//ldirectory.add("TitoloUnico"+sep+"diagram.bpmn");
		//ldirectory.add("TitoloUnico"+sep+"20250.bpmn");
		//ldirectory.add("TitoloUnico"+sep+"20386.bpmn");
		ldirectory.add("TitoloUnico"+sep+"20461.bpmn");
		for(String filename: ldirectory){
		genarateTestforFileOk2(filename);
		}
		
	}
	
	private void genarateTestforFileOk2(String NameFile){
		try {
			System.out.println(NameFile);
			URL is = GuidelinesFactoryTest.class.getClassLoader().getResource(NameFile);
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
		/*		String nFile = NameFile.replace(File.separator, "");
		OutputStream os = new FileOutputStream( "nosferatuvv"+ nFile.substring(0, nFile.length()-4)+"xml" );
			jaxbMarshaller.marshal( eg, os );
			*/
				if(!eg.getStatus().equals("OK")){
					
						fail();
					
				}
			

			

			//jaxbMarshaller.marshal(eg, System.out);
			//assertTrue(eg.getStatus().equals("OK"));

		} catch (JAXBException  | URISyntaxException  | IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}
}
