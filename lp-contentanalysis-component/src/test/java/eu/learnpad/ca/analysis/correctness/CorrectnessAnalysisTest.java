package eu.learnpad.ca.analysis.correctness;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import junit.framework.Assert;

import org.junit.Test;
import org.languagetool.language.BritishEnglish;

import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class CorrectnessAnalysisTest {

	@Test
	public void testCorrectnessAnalysisLanguage() {
		CorrectnessAnalysis ca = new CorrectnessAnalysis(new BritishEnglish());
		assertNotNull("Ok",ca);
	}

	@Test
	public void testCheckCollaborativeContentAnalysis() {
		try{
			CorrectnessAnalysis ca = new CorrectnessAnalysis(new BritishEnglish());
			InputStream is = CorrectnessAnalysisTest.class.getClassLoader().getResourceAsStream("CollaborativeContentXML.xml");
			assertNotNull(is);
			JAXBContext jaxbContexti = JAXBContext.newInstance(CollaborativeContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			CollaborativeContentAnalysis collaborativeContentInput = (CollaborativeContentAnalysis) jaxbUnmarshaller1.unmarshal(is);

			AnnotatedCollaborativeContentAnalysis acca1 = ca.check(collaborativeContentInput);
			assertNotNull(acca1);
			AnnotatedCollaborativeContentAnalysis acca = ca.getAnnotatedCollaborativeContentAnalysis();
			assertNotNull(ca.getNumSentenceDiffected());
			assertNotNull(acca);
			
		}catch(JAXBException e){
			fail("Error Check Correctness Analysis");
		}

	}

	@Test
	public void testCheckStaticContentAnalysis() {
		try{
			CorrectnessAnalysis ca = new CorrectnessAnalysis(new BritishEnglish());
			InputStream is = CorrectnessAnalysisTest.class.getClassLoader().getResourceAsStream("StaticContentXML.xml");
			assertNotNull(is);
			JAXBContext jaxbContexti = JAXBContext.newInstance(StaticContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			StaticContentAnalysis collaborativeContentInput = (StaticContentAnalysis) jaxbUnmarshaller1.unmarshal(is);

			AnnotatedStaticContentAnalysis acca1 = ca.check(collaborativeContentInput);
			assertNotNull(acca1);
			AnnotatedStaticContentAnalysis acca = ca.getAnnotatedStaticContentAnalysis();
			assertNotNull(ca.getNumSentenceDiffected());
			assertNotNull(acca);
			
		}catch(JAXBException e){
			fail("Error Check Correctness Analysis");
		}
	}


	@Test
	public void testCorrectnessAnalysisLanguageCollaborativeContentAnalysis() {
		try{
			InputStream is = CorrectnessAnalysisTest.class.getClassLoader().getResourceAsStream("CollaborativeContentXML.xml");
			assertNotNull(is);
			JAXBContext jaxbContexti = JAXBContext.newInstance(CollaborativeContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			CollaborativeContentAnalysis collaborativeContentInput = (CollaborativeContentAnalysis) jaxbUnmarshaller1.unmarshal(is);
			assertNotNull(collaborativeContentInput.toString());
			CorrectnessAnalysis ca = new CorrectnessAnalysis(new BritishEnglish(),collaborativeContentInput);
			assertNotNull("Ok",ca);
		}catch(JAXBException e){
			fail("Error Check Correctness Analysis");
		}
	}

	@Test
	public void testCorrectnessAnalysisLanguageStaticContentAnalysis() {
		try{
			InputStream is = CorrectnessAnalysisTest.class.getClassLoader().getResourceAsStream("StaticContentXML.xml");
			assertNotNull(is);
			JAXBContext jaxbContexti = JAXBContext.newInstance(StaticContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			StaticContentAnalysis staticContentInput = (StaticContentAnalysis) jaxbUnmarshaller1.unmarshal(is);
			assertNotNull(staticContentInput.toString());
			CorrectnessAnalysis ca = new CorrectnessAnalysis(new BritishEnglish(),staticContentInput);
			assertNotNull("Ok",ca);
		}catch(JAXBException e){
			fail("Error Check Correctness Analysis");
		}
	}



}
