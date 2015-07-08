package eu.learnpad.ca.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{

			InputStream is = Main.class.getClassLoader().getResourceAsStream("CollaborativeContentXML.xml");
			JAXBContext jaxbContexti = JAXBContext.newInstance(CollaborativeContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			CollaborativeContentAnalysis result = (CollaborativeContentAnalysis) jaxbUnmarshaller1.unmarshal(is);





			System.out.println(result);

			System.out.println();
			System.out.println();

			is = Main.class.getClassLoader().getResourceAsStream("StaticContenXML.xml");
			jaxbContexti = JAXBContext.newInstance(StaticContentAnalysis.class);

			jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			StaticContentAnalysis result2 = (StaticContentAnalysis) jaxbUnmarshaller1.unmarshal(is);
			System.out.println(result2);

			System.out.println();
			System.out.println();


			is = Main.class.getClassLoader().getResourceAsStream("annotatedStaticContentAnalysis.xml");
			jaxbContexti = JAXBContext.newInstance(AnnotatedStaticContentAnalysis.class);

			jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			AnnotatedStaticContentAnalysis result3 = (AnnotatedStaticContentAnalysis) jaxbUnmarshaller1.unmarshal(is);
			System.out.println(result3);






			is = Main.class.getClassLoader().getResourceAsStream("annotatedCollaborativeContentAnalysis.xml");//fc.getSelectedFile();

			JAXBContext jaxbContext = JAXBContext.newInstance(AnnotatedCollaborativeContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			AnnotatedCollaborativeContentAnalysis customer = (AnnotatedCollaborativeContentAnalysis) jaxbUnmarshaller.unmarshal(is);
			System.out.println(customer);
			
			testwrite();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void testwrite(){
		AnnotatedStaticContentAnalysis asca = new AnnotatedStaticContentAnalysis();
		asca.setId(1234);
		asca.setOverallQuality("overallquality");
		asca.setOverallQualityMeasure("OverallQualityMeasure");
		asca.setOverallRecommendations("Recommendation");
		asca.setType("all");
		StaticContent sc = new StaticContent();
		Content c = new Content();
		c.setContent("ciao");
		
		c.setContent(new Node(1234));
		
		sc.setContent(c);
		sc.setTitle("title");
		sc.setId("id");
		asca.setStaticContent(sc);

		JAXBContext jaxbCtx;
		try {
			jaxbCtx = javax.xml.bind.JAXBContext.newInstance(AnnotatedStaticContentAnalysis.class);

			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(asca, System.out);
			OutputStream os = new FileOutputStream( "nosferatu.xml" );
			//marshaller.marshal( asca, os );
		} catch (JAXBException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
