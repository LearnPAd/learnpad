package eu.learnpad.ca.analysis.correctness;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;

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


public class CorrectnessAnalysis extends  AbstractAnalysisClass{


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CorrectnessAnalysis.class);
	

	public CorrectnessAnalysis(Language lang){

		this.language=lang;

	}

	public AnnotatedCollaborativeContentAnalysis check(CollaborativeContentAnalysis collaborativeContentInput){
		String title = collaborativeContentInput.getCollaborativeContent().getTitle();
		String idc = collaborativeContentInput.getCollaborativeContent().getId();
		String content = collaborativeContentInput.getCollaborativeContent().getContentplain();

		JLanguageTool langTool = new JLanguageTool(language);

		List<RuleMatch> matches;
		try {
			annotatedCollaborativeContent = new AnnotatedCollaborativeContentAnalysis();
			CollaborativeContent sc = new CollaborativeContent();
			annotatedCollaborativeContent.setCollaborativeContent(sc);
			sc.setTitle(title);
			sc.setId(idc);
			Content c = new Content();
			sc.setContent(c);

			
			List<String> listsentence = langTool.sentenceTokenize(content);
			Integer id=0;
			for (String sentence : listsentence) {

				matches = langTool.check(sentence);
				//List<Annotation> annotations = checkdefect(sentence,c, id);
				List<Annotation> annotations =new ArrayList<Annotation>();
				id = calculateAnnotations(sentence, matches, c, id,annotations);
				
				
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
			annotatedCollaborativeContent.setType("Correctness");



			

			return annotatedCollaborativeContent;



		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
			return null;
		}


	}

	public AnnotatedStaticContentAnalysis check(StaticContentAnalysis staticContentInput){
		String title = staticContentInput.getStaticContent().getTitle();
		String idc = staticContentInput.getStaticContent().getId();
		String content = staticContentInput.getStaticContent().getContentplain();

		JLanguageTool langTool = new JLanguageTool(language);

		List<RuleMatch> matches;
		try {

			matches = langTool.check(content);

			List<String> listsentence = langTool.sentenceTokenize(content);
		

			
			annotatedStaticContent = new AnnotatedStaticContentAnalysis();
			StaticContent sc = new StaticContent();
			annotatedStaticContent.setStaticContent(sc);
			sc.setTitle(title);
			sc.setId(idc);
			Content c = new Content();
			sc.setContent(c);
			Integer id=0;
			for (String sentence : listsentence) {

				matches = langTool.check(sentence);
		
				List<Annotation> annotations =new ArrayList<Annotation>();
				id  = calculateAnnotations( sentence, matches, c, id,annotations);
				annotatedStaticContent.setAnnotations(annotations);
				
				if(annotations.size()>0){
					numDefectiveSentences++;
				}
				
				id++;
			}

		


			
			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			annotatedStaticContent.setOverallQualityMeasure(qualitymmeasure+"%");
			annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			annotatedStaticContent.setType("Correctness");



			

			return annotatedStaticContent;



		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
			return null;
		}


	}



	private int calculateAnnotations( String sentence,List<RuleMatch> matches, Content c, Integer id, List<Annotation> annotations){
		int precedentposition=0;

		int finalpos = 0;
		for (RuleMatch match : matches) {

			if(precedentposition>match.getFromPos()){
				precedentposition =  match.getFromPos();
			}
			String stringap = sentence.substring(precedentposition, match.getFromPos());
			c.setContent(stringap);
			id++;
			Node init= new Node(id);
			c.setContent(init);
			String stringa = sentence.substring(match.getFromPos(),match.getToPos());
			precedentposition= match.getToPos();
			c.setContent(stringa);
			id++;
			Node end= new Node(id);
			c.setContent(end);
			Annotation a = new Annotation();
			a.setId(id);
			a.setEndNode(end.getId());
			a.setStartNode(init.getId());
			a.setType("Correctness");
			a.setRecommendation(match.getMessage()+"\n Suggested correction: " +match.getSuggestedReplacements());
			annotations.add(a);
			id++;
			finalpos = match.getToPos();
		}
		
		if(annotations.size()==0){
			c.setContent(sentence);
		}else{
			if(finalpos< sentence.length()){
				c.setContent(sentence.substring(finalpos, sentence.length()));
			}
		}
		return id;

	}

	
	public int getNumSentenceDiffected() {
		return numDefectiveSentences;
	}

	
	public CorrectnessAnalysis( Language lang, CollaborativeContentAnalysis collaborativeContentInput){

		this.language=lang;
		this.collaborativeContentInput =collaborativeContentInput;
	}

	public CorrectnessAnalysis( Language lang, StaticContentAnalysis staticContentInput){

		this.language=lang;
		this.staticContentInput =staticContentInput;
	}

	

	
	public void run() {
		

		long lStartTime = System.currentTimeMillis();
		//some tasks
		if(collaborativeContentInput!=null){
			annotatedCollaborativeContent = this.check(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			annotatedStaticContent = this.check(staticContentInput);	
		}
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;

		log.trace("CorrectnessAnalysis Elapsed milliseconds: " + difference);

	}
	
	

}
