package eu.learnpad.ca.analysis.correctness;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.AnalyzedSentence;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import eu.learnpad.ca.analysis.AnalysisInterface;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;


public class CorrectnessAnalysis extends Thread implements AnalysisInterface{



	private Language language;
	private int numDefectiveSentences = 0;
	private CollaborativeContentAnalysis collaborativeContentAnalysis;
	private StaticContentAnalysis staticContentAnalysis;
	private AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContentAnalysis;
	private AnnotatedStaticContentAnalysis annotatedStaticContentAnalysis;

	public CorrectnessAnalysis(Language lang){

		this.language=lang;

	}

	public AnnotatedCollaborativeContentAnalysis check(CollaborativeContentAnalysis collaborativeContentInput){
		String title = collaborativeContentInput.getCollaborativeContent().getTitle();
		String idc = collaborativeContentInput.getCollaborativeContent().getId();
		String content = collaborativeContentInput.getCollaborativeContent().getContent().toString();

		JLanguageTool langTool = new JLanguageTool(language);

		List<RuleMatch> matches;
		try {
			annotatedCollaborativeContentAnalysis = new AnnotatedCollaborativeContentAnalysis();
			CollaborativeContent sc = new CollaborativeContent();
			annotatedCollaborativeContentAnalysis.setCollaborativeContent(sc);
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
				annotatedCollaborativeContentAnalysis.setAnnotations(annotations);
				id++;
			}
			
			
			
			


			


			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			annotatedCollaborativeContentAnalysis.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			annotatedCollaborativeContentAnalysis.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
			annotatedCollaborativeContentAnalysis.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			annotatedCollaborativeContentAnalysis.setType("Correctness");





			

			return annotatedCollaborativeContentAnalysis;



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}

	public AnnotatedStaticContentAnalysis check(StaticContentAnalysis staticContentInput){
		String title = staticContentInput.getStaticContent().getTitle();
		String idc = staticContentInput.getStaticContent().getId();
		String content = staticContentInput.getStaticContent().getContent().toString();

		JLanguageTool langTool = new JLanguageTool(language);

		List<RuleMatch> matches;
		try {

			matches = langTool.check(content);

			List<String> listsentence = langTool.sentenceTokenize(content);
		

			//System.out.println(content);
			annotatedStaticContentAnalysis = new AnnotatedStaticContentAnalysis();
			StaticContent sc = new StaticContent();
			annotatedStaticContentAnalysis.setStaticContent(sc);
			sc.setTitle(title);
			sc.setId(idc);
			Content c = new Content();
			sc.setContent(c);
			Integer id=0;
			for (String sentence : listsentence) {

				matches = langTool.check(sentence);
		
				List<Annotation> annotations =new ArrayList<Annotation>();
				id  = calculateAnnotations( sentence, matches, c, id,annotations);
				annotatedStaticContentAnalysis.setAnnotations(annotations);
				
				if(annotations.size()>0){
					numDefectiveSentences++;
				}
				
				id++;
			}

		


			
			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			annotatedStaticContentAnalysis.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			annotatedStaticContentAnalysis.setOverallQualityMeasure(qualitymmeasure+"%");
			annotatedStaticContentAnalysis.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			annotatedStaticContentAnalysis.setType("Correctness");



			

			return annotatedStaticContentAnalysis;



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}



	private int calculateAnnotations( String sentence,List<RuleMatch> matches, Content c, Integer id, List<Annotation> annotations){
		int precedentposition=0;

		

		boolean flag = true;
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

	private double calculateOverallQualityMeasure(Integer numsentence){
		double qm = (1-(numDefectiveSentences/numsentence.doubleValue()))*100;
		double qualityMeasure = Math.abs(qm);
		return qualityMeasure;
	}

	private String calculateOverallQuality(double qualityMeasure){
		String quality="";
		if(qualityMeasure<=25){
			quality="VERY BAD";
		}else if(qualityMeasure<=50){
			quality="BAD";
		}else if(qualityMeasure<=75){
			quality="GOOD";
		}else if(qualityMeasure<100){
			quality="VERY GOOD";
		}else if(qualityMeasure==100){
			quality="EXCELLENT";
		}
		return quality;
	}

	private String calculateOverallRecommendations(double qualityMeasure){
		String recommendations="";
		if(qualityMeasure<=25){
			recommendations="Quality is very poor, correct the errors";
		}else if(qualityMeasure<=50){
			recommendations="Quality is poor, correct the errors";
		}else if(qualityMeasure<=75){
			recommendations="Quality is acceptable, but there are still some errors";
		}else if(qualityMeasure<100){
			recommendations="Well done, still few errors remaining";
		}else if(qualityMeasure==100){
			recommendations="Well done, no errors found!";
		}
		return recommendations;
	}

	public CorrectnessAnalysis( Language lang, CollaborativeContentAnalysis collaborativeContentInput){

		this.language=lang;
		collaborativeContentAnalysis =collaborativeContentInput;
	}

	public CorrectnessAnalysis( Language lang, StaticContentAnalysis staticContentInput){

		this.language=lang;
		staticContentAnalysis =staticContentInput;
	}

	public AnnotatedStaticContentAnalysis getAnnotatedStaticContentAnalysis() {
		return annotatedStaticContentAnalysis;
	}

	public AnnotatedCollaborativeContentAnalysis getAnnotatedCollaborativeContentAnalysis() {
		return annotatedCollaborativeContentAnalysis;
	}

	public void run() {
		if(collaborativeContentAnalysis!=null){
			annotatedCollaborativeContentAnalysis = this.check(collaborativeContentAnalysis);	
		}

		if(staticContentAnalysis!=null){
			annotatedStaticContentAnalysis = this.check(staticContentAnalysis);	
		}

	}
	
	public String getStatus(){
		switch (this.getState()) {
		case TERMINATED:
			return "OK";

		default:
			return "IN PROGRESS";
		}
		
	}

}
