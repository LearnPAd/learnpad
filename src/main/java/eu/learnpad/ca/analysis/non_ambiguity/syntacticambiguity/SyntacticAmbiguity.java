package eu.learnpad.ca.analysis.non_ambiguity.syntacticambiguity;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;

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



public class SyntacticAmbiguity extends AbstractAnalysisClass{ 


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SyntacticAmbiguity.class);


	public SyntacticAmbiguity(CollaborativeContentAnalysis cca, Language lang){
		super();
		this.language=lang;
		collaborativeContentInput = cca;

	}


	public SyntacticAmbiguity(StaticContentAnalysis cca, Language lang){
		this.language=lang;
		staticContentInput = cca;

	}


	public void run() {
		if(collaborativeContentInput!=null){
			checkSyntacticAmbiguity(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			checkSyntacticAmbiguity(staticContentInput);	
		}

	}


	private void checkSyntacticAmbiguity(StaticContentAnalysis staticContentInput) {
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
		annotatedStaticContent.setType("non_ambiguity");

	}


	private void checkSyntacticAmbiguity(
			CollaborativeContentAnalysis collaborativeContentInput) {
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
		int id=0;
		for (String sentence : listsentence) {

			List<Annotation> annotations =new ArrayList<Annotation>();
			id = checkdefect(sentence,c, id, annotations);
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
			annotatedCollaborativeContent.setType("non_ambiguity");
			//System.out.println(annotatedCollaborativeContent);
			log.trace("\nnumDefectiveSentences AlternativeTerm: "+numDefectiveSentences);

		
	}

	private int checkdefect(String sentence,Content c,int nodeid,List<Annotation> annotations){
		Set<String> Listallp = PronominalExtensions.getAllPronouns();
		StringTokenizer tokenizer = new StringTokenizer(sentence);

		int precedentposition=0;
		
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if(Listallp.contains(token)){
				
				int initialpos = indexofElement(sentence,token);
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
				a.setType("non_ambiguity");
				a.setRecommendation("non_ambiguity into"+token);
				annotations.add(a);

			}
		}
		if(precedentposition<sentence.length()){
			String stringap = sentence.substring(precedentposition, sentence.length());
			c.setContent(stringap);
		}
		return nodeid;

	}
	
	private int indexofElement(String sentence, String word){
		StringTokenizer tokenizer = new StringTokenizer(sentence);
		int position = 0;
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if(token.equals(word)){
				return position;
			}else{
				position+=token.length()+1;
			}
		}
		return position;
	}
	

}
