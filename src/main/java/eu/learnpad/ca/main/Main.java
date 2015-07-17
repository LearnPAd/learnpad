package eu.learnpad.ca.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.languagetool.AnalyzedSentence;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;
import org.languagetool.rules.RuleMatch;

import eu.learnpad.ca.analysis.correctness.CorrectnessAnalysis;
import eu.learnpad.ca.analysis.simplicity.Simplicity;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.ca.simplicity.juridicaljargon.JuridaljargonSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
		/*	String fileinput = "/Users/isiu/github/learnpadworkspace/ContentAnalysisComponent/script/jj.xml";
		//	InputStream is = Main.class.getClassLoader().getResourceAsStream("");
			
			JAXBContext jaxbContexti = JAXBContext.newInstance(JuridaljargonSet.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			JuridaljargonSet result = (JuridaljargonSet) jaxbUnmarshaller1.unmarshal(new File(fileinput));

			*/
			
			
			
			InputStream is = Main.class.getClassLoader().getResourceAsStream("CollaborativeContentXML.xml");
			JAXBContext  jaxbContexti = JAXBContext.newInstance(CollaborativeContentAnalysis.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			CollaborativeContentAnalysis cca = (CollaborativeContentAnalysis) jaxbUnmarshaller1.unmarshal(is);

			Simplicity sim = new Simplicity(cca, new  BritishEnglish());
			
		
			AnnotatedCollaborativeContentAnalysis acca = sim.getAnnotatedCollaborativeContentAnalysis();
			
			CorrectnessAnalysis corrana = new CorrectnessAnalysis( new BritishEnglish());
			 acca = corrana.check(cca);
			
			
			JAXBContext jaxbCtx;
			try {
				jaxbCtx = javax.xml.bind.JAXBContext.newInstance(AnnotatedCollaborativeContentAnalysis.class);

				Marshaller marshaller = jaxbCtx.createMarshaller();
				marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
				marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				marshaller.marshal(acca, System.out);
				OutputStream os = new FileOutputStream( "nosferatu.xml" );
				marshaller.marshal( acca, os );
			} catch (JAXBException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
/*

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

			testlanguagetoolEN();

			//testlanguagetoolIT();
*/
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
		Annotation a = new Annotation();
		a.setId(44);
		a.setEndNode(1);
		a.setStartNode(0);
		a.setType("type");
		a.setRecommendation("rac");
		asca.setAnnotations(a);


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

	public static void testlanguagetoolEN (){
		JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
		//langTool.activateDefaultPatternRules();  -- only needed for LT 2.8 or earlier
		List<RuleMatch> matches;
		try {
			String text = "A sentence with a error in the Hitchhiker's Guide tot he Galaxy. giorgio";
			matches = langTool.check(text);
			System.out.println(text);

	/*		List<String> st = langTool.sentenceTokenize(text);
			for (String string : st) {
				System.out.println(string);
			}


			List<AnalyzedSentence> at = langTool.analyzeText(text);
			for (AnalyzedSentence analyzedSentence : at) {
				System.out.println(analyzedSentence);
				String f = analyzedSentence.getAnnotations();
				Set<String> t = analyzedSentence.getLemmaSet();
				System.out.println();
			}

			AnalyzedSentence se = langTool.getAnalyzedSentence(text);
			*/
			
			
			for (RuleMatch match : matches) {
				System.out.println("Potential error at line " +
						match.getLine() + ", column " +
						match.getColumn() + ": " + match.getMessage());
				System.out.println("Suggested correction: " +
						match.getSuggestedReplacements());
				System.out.println("getToPos: " +	 match.getToPos());
				System.out.println("EndColumn: " +	 match.getEndColumn());
				System.out.println("EndLine: " +	 match.getEndLine());
				System.out.println("FromPos: " +	 match.getFromPos());
				System.out.println("ShortMessage: " +	 match.getShortMessage());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testlanguagetoolIT(){
		JLanguageTool langTool = new JLanguageTool(new Italian());
		//langTool.activateDefaultPatternRules();  -- only needed for LT 2.8 or earlier
		List<RuleMatch> matches;
		try {
			String text ="cioa questa frase e scorreta";
			matches = langTool.check(text);
			System.out.println(text);

			for (RuleMatch match : matches) {
				System.out.println("Potential error at line " +
						match.getLine() + ", column " +
						match.getColumn() + ": " + match.getMessage());
				System.out.println("Suggested correction: " +
						match.getSuggestedReplacements());
				System.out.println("getToPos: " +	 match.getToPos());
				System.out.println("EndColumn: " +	 match.getEndColumn());
				System.out.println("EndLine: " +	 match.getEndLine());
				System.out.println("FromPos: " +	 match.getFromPos());
				System.out.println("ShortMessage: " +	 match.getShortMessage());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
