package eu.learnpad.ca.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
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

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
