package eu.learnpad.ca.simplicity;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import eu.learnpad.ca.correctness.CorrectnessAnalysisTest;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.simplicity.juridicaljargon.JuridaljargonSet;
import eu.learnpad.ca.simplicity.juridicaljargon.Juridicaljargon;

public class Simplicity {


	private JuridaljargonSet juridaljargonSet;
	private CollaborativeContentAnalysis collaborativeContentInput;
	
	public Simplicity(CollaborativeContentAnalysis cca){
		juridaljargonSet = readJJ();
		collaborativeContentInput = cca;
	}

	private JuridaljargonSet readJJ(){
		InputStream is = CorrectnessAnalysisTest.class.getClassLoader().getResourceAsStream("JuridicalJargon_EnglishLatin.xml");
		assertNotNull(is);

		try {
			JAXBContext jaxbContexti = JAXBContext.newInstance(JuridaljargonSet.class);


			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			JuridaljargonSet jjSet = (JuridaljargonSet) jaxbUnmarshaller1.unmarshal(is);
			return jjSet;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}
	
	private void checkJJ(CollaborativeContentAnalysis cca){
		String title = collaborativeContentInput.getCollaborativeContent().getTitle();
		String idc = collaborativeContentInput.getCollaborativeContent().getId();
		String content = collaborativeContentInput.getCollaborativeContent().getContent().toString();
		
		List<Juridicaljargon> Listjj = juridaljargonSet.getJuridicaljargon();

		
	}



}
