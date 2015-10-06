package eu.learnpad.ca.analysis.simplicity;



import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.simplicity.difficultjargon.AlternativeTerm;
import eu.learnpad.ca.analysis.simplicity.difficultjargon.AlternativeTermSet;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;


public class DifficultJargonAlternative  extends  AbstractAnalysisClass{ 

	
	private AlternativeTermSet alternativetermset;
	


	public DifficultJargonAlternative(StaticContentAnalysis cca, Language lang){
		this.language=lang;
		alternativetermset = readAlternativeTerms(lang);
		staticContentInput = cca;

	}


	public DifficultJargonAlternative(CollaborativeContentAnalysis cca,
			Language lang) {
		this.language=lang;
		alternativetermset  = readAlternativeTerms(lang);
		collaborativeContentInput = cca;
	}

	
	public void run() {
		if(collaborativeContentInput!=null){
			checkUnclearAcronym(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			checkUnclearAcronym(staticContentInput);	
		}

	}

	private void checkUnclearAcronym(StaticContentAnalysis staticContentInput2) {
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
		insertdefectannotation(content,c,listsentence);

		//System.out.println(content);

		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Simplicity DifficultJargon Alternative");

	}

	private void checkUnclearAcronym(CollaborativeContentAnalysis collaborativeContentInput) {
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


		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentence = langTool.sentenceTokenize(content);

		insertdefectannotation(content,c,listsentence);


		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Simplicity DifficultJargon Alternative");
		//System.out.println(annotatedCollaborativeContent);

	}

	private AlternativeTermSet readAlternativeTerms(Language lang){
		InputStream is = null;
		if(lang instanceof BritishEnglish | lang instanceof AmericanEnglish){
			is = DifficultJargonAlternative.class.getClassLoader().getResourceAsStream("AlternativeTermSet_EnglishLatin.xml");

		}else
			if(lang instanceof Italian){
				is = DifficultJargonAlternative.class.getClassLoader().getResourceAsStream("AlternativeTermSet_EnglishLatin.xml");

			}

		assert is!=null;

		try {
			JAXBContext jaxbContexti = JAXBContext.newInstance(AlternativeTermSet.class);


			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			AlternativeTermSet atSet = (AlternativeTermSet) jaxbUnmarshaller1.unmarshal(is);
			return atSet;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}


	private void insertdefectannotation(String content,Content c, List<String> listsentence){
		int id = 0;
		for (String sentence : listsentence) {

			List<Annotation> annotations =new ArrayList<Annotation>();
			id = insertdefectannotationsentence(sentence, c , id, annotations);
			if(annotations.size()>0){
				numDefectiveSentences++;
			}
			annotatedCollaborativeContent.setAnnotations(annotations);
			id++;

		}

		System.out.println("\nnumDefectiveSentences AlternativeTerm: "+numDefectiveSentences);
		
	}


	private int insertdefectannotationsentence(String sentence, Content c,
			int nodeid, List<Annotation> annotations) {
		List<AlternativeTerm> listAltTermSet = alternativetermset.getAlternativeterms();
		
		String [] spliter = sentence.split("[\\W]");
		Map<String, Integer> elementfinded = new HashMap<String, Integer>();
		int precedentposition=0;

		//while (tokenizer.hasMoreTokens()) {
			//String token = tokenizer.nextToken();
			//token =	token.trim().replace(".", "").replace(":", "").replace(";", "").replace("\"","");
		for (int i = 0; i < spliter.length; i++) {
			
			String token = spliter[i];
			AlternativeTerm tmptoken = new AlternativeTerm(token);
			if(listAltTermSet.contains(tmptoken)){
				int initialpos = indexofElement(sentence,token,elementfinded,"[\\W]");
				int finalpos = initialpos+token.length();
				if(precedentposition>initialpos){
					//initialpos = sentence.lastIndexOf(token);
					//finalpos = initialpos+token.length();
					System.out.println();
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
				a.setType("Simplicity DifficultJargon Alternative");
				
				String suggestion = listAltTermSet.get(listAltTermSet.indexOf(tmptoken)).getSuggestion();
				a.setRecommendation("Please replace with: "+suggestion);
				annotations.add(a);

			}
		}
		if(precedentposition<sentence.length()){
			String stringap = sentence.substring(precedentposition, sentence.length());
			c.setContent(stringap);
		}
		/*if(annotations.size()==0){
			c.setContent(sentence);
		}*/

		return nodeid;

	}

	
}