package eu.learnpad.ca.analysis.simplicity;


import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import eu.learnpad.ca.simplicity.juridicaljargon.JuridaljargonSet;
import eu.learnpad.ca.simplicity.juridicaljargon.Juridicaljargon;

public class JuridicalJargon extends  AbstractAnalysisClass{

	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(JuridicalJargon.class);

	
	
	private JuridaljargonSet juridaljargonSet;
	
	
	

	public JuridicalJargon(CollaborativeContentAnalysis cca, Language lang){
		this.language=lang;
		juridaljargonSet = readJJ(lang);
		collaborativeContentInput = cca;
		
	}
	
	
	public JuridicalJargon(StaticContentAnalysis cca, Language lang){
		this.language=lang;
		juridaljargonSet = readJJ(lang);
		staticContentInput = cca;
		
	}
	
	private void checkJJ(StaticContentAnalysis cca){
		String title = staticContentInput.getStaticContent().getTitle();
		String idc = staticContentInput.getStaticContent().getId();
		String content = staticContentInput.getStaticContent().getContentplain();

		annotatedStaticContent = new AnnotatedStaticContentAnalysis();
		StaticContent sc = new StaticContent();
		annotatedStaticContent.setStaticContent(sc);
		sc.setTitle(title);
		sc.setId(idc);
		Content c = new Content();
		sc.setContent(c);

		//String sResult = "This is a test. This is a T.L.A. test.";
		//String[] sSentence = content.split("(?<=[a-z])\\.\\s+");
		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentence = langTool.sentenceTokenize(content);
		int id=0;
		for (String sentence : listsentence) {


			List<Annotation> annotations =new ArrayList<Annotation>();
			id = checkdefect(sentence,c, id, annotations);
			if(annotations.size()>0){
				numDefectiveSentences++;
			}
			annotatedStaticContent.setAnnotations(annotations);
			id++;
		}

		

		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Simplicity");
		log.trace("\nnumDefectiveSentences AlternativeTerm: "+numDefectiveSentences);

	}
	

	private JuridaljargonSet readJJ(Language lang){
		InputStream is = null;
		if(lang instanceof BritishEnglish | lang instanceof AmericanEnglish){
			is = JuridicalJargon.class.getClassLoader().getResourceAsStream("JuridicalJargon_EnglishLatin.xml");

		}else
			if(lang instanceof Italian){
				is = JuridicalJargon.class.getClassLoader().getResourceAsStream("JuridicalJargon_EnglishLatin.xml");

			}

		assert is!=null;

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
		String content = collaborativeContentInput.getCollaborativeContent().getContentplain();

		annotatedCollaborativeContent = new AnnotatedCollaborativeContentAnalysis();
		CollaborativeContent sc = new CollaborativeContent();
		annotatedCollaborativeContent.setCollaborativeContent(sc);
		sc.setTitle(title);
		sc.setId(idc);
		Content c = new Content();
		sc.setContent(c);

		//String sResult = "This is a test. This is a T.L.A. test.";
		//String[] sSentence = content.split("(?<=[a-z])\\.\\s+");
		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentence = langTool.sentenceTokenize(content);
		int id=0;
		for (String sentence : listsentence) {


			List<Annotation> annotations =new ArrayList<Annotation>();
			id = checkdefect(sentence,c, id,annotations);
			if(annotations.size()>0){
				numDefectiveSentences++;
			}
			annotatedCollaborativeContent.setAnnotations(annotations);
			id++;
		}

		
		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Simplicity");

		log.trace("\nnumDefectiveSentences AlternativeTerm: "+numDefectiveSentences);
	}

	private int checkdefect(String sentence,Content c,int nodeid,List<Annotation> annotations){
		List<Juridicaljargon> Listjj = juridaljargonSet.getJuridicaljargon();
		StringTokenizer tokenizer = new StringTokenizer(sentence);
		
		int precedentposition=0;

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if(Listjj.contains(new Juridicaljargon(token))){
				
				int initialpos = sentence.indexOf(token);
				int finalpos = initialpos+token.length();
				if(precedentposition>initialpos){
					 initialpos = sentence.lastIndexOf(token);
				}
				String stringap = sentence.substring(precedentposition, initialpos);
				c.setContent(stringap);
				precedentposition=finalpos;
				nodeid++;
				Node init= new Node(nodeid);
				nodeid++;
				Node end= new Node(nodeid);
				c.setContent(init);
				c.setContent(token);
				c.setContent(end);

				Annotation a = new Annotation();
				a.setId(nodeid);
				a.setEndNode(end.getId());
				a.setStartNode(init.getId());
				a.setType("Simplicity");
				a.setRecommendation("The term "+token+" is juridical jargon. Substitute "+token+" with a more common term.");
				annotations.add(a);

			}
		}
		if(precedentposition<sentence.length()){
			String stringap = sentence.substring(precedentposition, sentence.length());
			c.setContent(stringap);
		}
		return nodeid;

	}



	
	public void run() {
		if(collaborativeContentInput!=null){
			checkJJ(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			checkJJ(staticContentInput);	
		}

	}
	
	
	


	
	
}
