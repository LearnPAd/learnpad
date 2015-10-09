package eu.learnpad.ca.analysis.simplicity;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.languagetool.language.BritishEnglish;

import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class SimplicityTest {

	@Test
	public void testSimplicityAnalysisCollaborativeContentAnalysis() {
		try{
			InputStream is = SimplicityTest.class.getClassLoader().getResourceAsStream("CollaborativeContentXMLS.xml");
			assertNotNull(is);
			JAXBContext jaxbContexti = JAXBContext.newInstance(CollaborativeContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			CollaborativeContentAnalysis collaborativeContentInput = (CollaborativeContentAnalysis) jaxbUnmarshaller1.unmarshal(is);
			assertNotNull(collaborativeContentInput.toString());
			Simplicity ca = new Simplicity(collaborativeContentInput, new BritishEnglish());
			ca.run();
			assertNotNull("Ok",ca);
		}catch(JAXBException e){
			fail("Error Check Simplicity Analysis");
		}
	}

	@Test
	public void testSimplicityAnalysisStaticContentAnalysis() {
		try{
			InputStream is = SimplicityTest.class.getClassLoader().getResourceAsStream("StaticContentXML.xml");
			assertNotNull(is);
			JAXBContext jaxbContexti = JAXBContext.newInstance(StaticContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			StaticContentAnalysis staticContentInput = (StaticContentAnalysis) jaxbUnmarshaller1.unmarshal(is);
			assertNotNull(staticContentInput.toString());
			Simplicity ca = new Simplicity(staticContentInput, new BritishEnglish());
			ca.run();
			assertNotNull("Ok",ca);
		}catch(JAXBException e){
			fail("Error Check Simplicity Analysis");
		}
	}

}
