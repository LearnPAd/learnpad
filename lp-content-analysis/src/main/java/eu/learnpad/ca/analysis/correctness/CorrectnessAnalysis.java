package eu.learnpad.ca.analysis.correctness;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.rules.RuleMatch;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.localizzation.Messages;
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
	private String listsentences = "";

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
			int offset = 0;
			for (int i = 0; i < listsentence.size(); i++) {
				
				String sentence = listsentence.get(i);

				matches = langTool.check(sentence);
				//List<Annotation> annotations = checkdefect(sentence,c, id);
				List<Annotation> annotations =new ArrayList<Annotation>();
				
				id = calculateAnnotations(sentence, matches, c, id,annotations,offset);
				int l = sentence.length();
				offset+= l;

				if(annotations.size()>0){
					numDefectiveSentences++;
				}
				boolean last = i==listsentence.size()-1;
				if(last && listsentences!=""){
					c.setContent(listsentences);
					listsentences = "";
				}
				annotatedCollaborativeContent.setAnnotations(annotations);
				id++;
			}




			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%"); //$NON-NLS-1$ //$NON-NLS-2$
			annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			annotatedCollaborativeContent.setType("Correctness"); //$NON-NLS-1$





			return annotatedCollaborativeContent;



		} catch (IOException e) {
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
			int offset = 0;
			for (int i = 0; i < listsentence.size(); i++) {
				
			String sentence = listsentence.get(i);

				matches = langTool.check(sentence);

				List<Annotation> annotations =new ArrayList<Annotation>();
				id  = calculateAnnotations( sentence, matches, c, id,annotations, offset);
				annotatedStaticContent.setAnnotations(annotations);
				offset+=sentence.length();
				if(annotations.size()>0){
					numDefectiveSentences++;
				}
				boolean last = i==listsentence.size()-1;
				if(last && listsentences!=""){
					c.setContent(listsentences);
					listsentences = "";
				}
				id++;
			}





			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			annotatedStaticContent.setOverallQualityMeasure(qualitymmeasure+"%"); //$NON-NLS-1$
			annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			annotatedStaticContent.setType("Correctness"); //$NON-NLS-1$





			return annotatedStaticContent;



		} catch (IOException e) {
			log.error(e);
			return null;
		}


	}



	private int calculateAnnotations( String sentence,List<RuleMatch> matches, Content c, Integer id, List<Annotation> annotations, int offset){
		int precedentposition=0;

		int finalpos = 0;
		
		Annotation prev = new Annotation(-1,"",-1,-1,"");
		
		for (RuleMatch match : matches) {

			String stringa = sentence.substring(match.getFromPos(),match.getToPos());
			if(!stringa.isEmpty()){
				if(Character.isUpperCase(stringa.charAt(0))){
					continue;
				}else{
					try{
						String prestringa =  sentence.substring(match.getFromPos()-1, match.getFromPos());
						String poststringa =  sentence.substring(match.getToPos(), match.getToPos()+1);
						boolean flag = (prestringa.contains("“") || poststringa.contains("”")) || (prestringa.contains("\"") || poststringa.contains("\""));
						if(flag){
							continue;
						}
					}catch(Exception e){

					}

				}
			}
			if(precedentposition>=match.getFromPos()){
				precedentposition =  match.getFromPos();
			}else{
				String stringap = sentence.substring(precedentposition, match.getFromPos());
				listsentences+=stringap;
				//c.setContent(stringap);
			}
			

			if(match.getToPos()!=(prev.getNodeStart().getOffSet()-offset)){
				c.setContent(listsentences);
				listsentences = "";
				id++;
				Node init= new Node(id, match.getFromPos()+offset);
				c.setContent(init);
				precedentposition= match.getToPos();
				
				c.setContent(stringa);
				id++;
				Node end= new Node(id,match.getToPos()+offset);
				c.setContent(end);
				Annotation a = new Annotation();
				a.setId(id);
				a.setEndNode(end.getId());
				a.setStartNode(init.getId());
				a.setNodeEnd(end);
				a.setNodeStart(init);
				a.setType("Correctness"); //$NON-NLS-1$
				a.setRecommendation(getSuggestion(match.getMessage())+Messages.getString("CorrectnessAnalysis.Reccomandation", language) +match.getSuggestedReplacements()); //$NON-NLS-1$
				annotations.add(a);
				prev=a;
				id++;
				finalpos = match.getToPos();
			}else{
				id++;
				Annotation a = new Annotation();
				a.setId(id);
				a.setEndNode(prev.getNodeEnd().getId());
				a.setStartNode(prev.getNodeStart().getId());
				a.setNodeEnd(prev.getNodeEnd());
				a.setNodeStart(prev.getNodeStart());
				a.setType("Correctness"); //$NON-NLS-1$
				a.setRecommendation(getSuggestion(match.getMessage())+Messages.getString("CorrectnessAnalysis.Reccomandation", language) +match.getSuggestedReplacements()); //$NON-NLS-1$
				annotations.add(a);
				prev=a;
				id++;
				finalpos = match.getToPos();
			}
		}

		if(annotations.size()==0){
			listsentences+=sentence;
		}else{
			if(finalpos< sentence.length()){
				listsentences+=sentence.substring(finalpos, sentence.length());
				
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

		log.trace("CorrectnessAnalysis Elapsed milliseconds: " + difference); //$NON-NLS-1$

	}

	private String getSuggestion(String a){
		return a.replace("<suggestion>","").replace("</suggestion>","");
	}

}
