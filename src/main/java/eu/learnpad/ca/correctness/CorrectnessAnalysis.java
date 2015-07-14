package eu.learnpad.ca.correctness;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.AnalyzedSentence;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;

import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;


public class CorrectnessAnalysis extends Thread {



	private Language language;
	private Integer numDefectiveSentences = 0;
	private CollaborativeContentAnalysis collaborativeContentAnalysis;
	private StaticContentAnalysis staticContentAnalysis;
	private AnnotatedCollaborativeContentAnalysis annotateCollaborativeContentAnalysis;
	private AnnotatedStaticContentAnalysis annotateStaticContentAnalysis;

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

			matches = langTool.check(content);

			List<String> listsentence = langTool.sentenceTokenize(content);
			List<Integer> possepa = posSentenceSeparator(listsentence);


			System.out.println(content);
			AnnotatedCollaborativeContentAnalysis acca = new AnnotatedCollaborativeContentAnalysis();
			CollaborativeContent sc = new CollaborativeContent();
			acca.setCollaborativeContent(sc);
			sc.setTitle(title);
			sc.setId(idc);
			Content c = new Content();
			sc.setContent(c);

			List<Annotation> annotations = calculateAnnotations(content, matches, c, possepa);
			acca.setAnnotations(annotations);


			acca.setId(1234);


			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			acca.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			acca.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
			acca.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			acca.setType("Correctness");





			annotateCollaborativeContentAnalysis=acca;

			return acca;



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
			List<Integer> possepa = posSentenceSeparator(listsentence);

			System.out.println(content);
			AnnotatedStaticContentAnalysis asca = new AnnotatedStaticContentAnalysis();
			StaticContent sc = new StaticContent();
			asca.setStaticContent(sc);
			sc.setTitle(title);
			sc.setId(idc);
			Content c = new Content();
			sc.setContent(c);



			List<Annotation> annotations = calculateAnnotations(content, matches, c,possepa);
			asca.setAnnotations(annotations);


			asca.setId(1234);
			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			asca.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			asca.setOverallQualityMeasure(qualitymmeasure+"%");
			asca.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			asca.setType("Correctness");



			annotateStaticContentAnalysis = asca;

			return asca;



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}



	private List<Annotation> calculateAnnotations(String content,List<RuleMatch> matches, Content c, List<Integer> possepa){
		int precedentposition=0;
		int id = 1;
		List<Annotation> annotations=new ArrayList<Annotation>();
		numDefectiveSentences = 0;
		boolean flag = true;
		int i=0;
		for (RuleMatch match : matches) {
			
			if(match.getFromPos()>possepa.get(i)){
				i++;
				flag=true;
			}else{
				if(match.getFromPos()<possepa.get(i) & flag){
					numDefectiveSentences++;
					flag=false;				
				}
			}
			String stringap = content.substring(precedentposition, match.getFromPos());
			c.setContent(stringap);
			Node init= new Node(id);
			c.setContent(init);
			String stringa = content.substring(match.getFromPos(),match.getToPos());
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
			a.setRecommendation(match.getMessage()+" Suggested correction: " +match.getSuggestedReplacements());
			annotations.add(a);
			id++;
		}
		return annotations;

	}

	private List<Integer> posSentenceSeparator(List<String> sentences){
		List<Integer> listpos = new ArrayList<Integer>();
		int offsett=0;
		for(String sentence : sentences){
			listpos.add(sentence.length()+offsett);
			offsett=sentence.length();
		}
		return listpos;
	}

	public int getNumSentenceDiffected() {
		return numDefectiveSentences;
	}

	private double calculateOverallQualityMeasure(Integer numsentence){
		double qm = (1-(numDefectiveSentences.doubleValue()/numsentence.doubleValue()))*100;
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
		return annotateStaticContentAnalysis;
	}
	
	public AnnotatedCollaborativeContentAnalysis getAnnotatedCollaborativeContentAnalysis() {
		return annotateCollaborativeContentAnalysis;
	}

	public void run() {
		if(collaborativeContentAnalysis!=null){
			annotateCollaborativeContentAnalysis = this.check(collaborativeContentAnalysis);	
		}
		
		if(staticContentAnalysis!=null){
			annotateStaticContentAnalysis = this.check(staticContentAnalysis);	
		}
		
	}

}
