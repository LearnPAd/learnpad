package eu.learnpad.ca.correctness;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


public class CorrectnessAnalysis {
	


	private Language language;
	
	public CorrectnessAnalysis( Language lang){
		
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
			System.out.println(content);
			AnnotatedCollaborativeContentAnalysis acca = new AnnotatedCollaborativeContentAnalysis();
			CollaborativeContent sc = new CollaborativeContent();
			acca.setCollaborativeContent(sc);
			sc.setTitle(title);
			sc.setId(idc);
			Content c = new Content();
			sc.setContent(c);
			
			List<Annotation> annotations = calculateAnnotations(content, matches, c);
			acca.setAnnotations(annotations);
			
			
			acca.setId(1234);
			acca.setOverallQuality("overallquality");
			acca.setOverallQualityMeasure("OverallQualityMeasure");
			acca.setOverallRecommendations("Recommendation");
			acca.setType("Correctness");
			
			

			
			
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
			System.out.println(content);
			AnnotatedStaticContentAnalysis asca = new AnnotatedStaticContentAnalysis();
			StaticContent sc = new StaticContent();
			asca.setStaticContent(sc);
			sc.setTitle(title);
			sc.setId(idc);
			Content c = new Content();
			sc.setContent(c);
			
			List<Annotation> annotations = calculateAnnotations(content, matches, c);
			asca.setAnnotations(annotations);
			
			
			asca.setId(1234);
			asca.setOverallQuality("overallquality");
			asca.setOverallQualityMeasure("OverallQualityMeasure");
			asca.setOverallRecommendations("Recommendation");
			asca.setType("Correctness");
			
			

			
			
			return asca;
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	private List<Annotation> calculateAnnotations(String content,List<RuleMatch> matches, Content c){
		int precedentposition=0;
		int id = 1;
		List<Annotation> annotations=new ArrayList<Annotation>();
		for (RuleMatch match : matches) {
		//for(int i=matches.size();i>=0;i--){
			//RuleMatch match = matches.get(i);
			/*System.out.println("Potential error at line " +
					match.getLine() + ", column " +
					match.getColumn() + ": " + match.getMessage());
			System.out.println("Suggested correction: " +
					match.getSuggestedReplacements());
			System.out.println("getToPos: " +	 match.getToPos());
			System.out.println("EndColumn: " +	 match.getEndColumn());
			System.out.println("EndLine: " +	 match.getEndLine());
			System.out.println("FromPos: " +	 match.getFromPos());
			System.out.println("ShortMessage: " +	 match.getShortMessage());*/
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
	
}
