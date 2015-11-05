package eu.learnpad.ca.analysis.simplicity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.gate.UtilsGate;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import gate.DocumentContent;
import gate.util.InvalidOffsetException;

public class ExcessiveLength extends AbstractAnalysisClass {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExcessiveLength.class);




	public ExcessiveLength(CollaborativeContentAnalysis collaborativeContentInput,Language lang){

		this.language=lang;
		this.collaborativeContentInput=collaborativeContentInput;
	}

	public ExcessiveLength(StaticContentAnalysis staticContentInput,Language lang){

		this.language=lang;
		this.staticContentInput =staticContentInput;
	}

	public void run() {
		if(collaborativeContentInput!=null){
			check(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			check(staticContentInput);	
		}

	}

	public AnnotatedCollaborativeContentAnalysis check(CollaborativeContentAnalysis collaborativeContentInput){
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

		UtilsGate gateu = new UtilsGate(content);


		gateu.runProcessingResourcesforLenght();
		Set<gate.Annotation> result = gateu.getAnnotationSet(new HashSet<String>() {{
			add("Sent-Long");
		}});




		List<Annotation> annotations =new ArrayList<Annotation>();

		gatevsleanpad(c,result,annotations,content,gateu.getCorpus());


		annotatedCollaborativeContent.setAnnotations(annotations);




		Set<gate.Annotation> listSentence = gateu.getAnnotationSet(new HashSet<String>() {{
			add("Sentence");
		}});



		double qualitymmeasure = calculateOverallQualityMeasure(listSentence.size());
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Simplicity Excessive Length");





		return annotatedCollaborativeContent;


	}

	private void  gatevsleanpad(Content c, Set<gate.Annotation> setGateAnnotations,
			List<Annotation> annotations, String content, gate.Corpus corpus) {
		int precedentposition = 0;
		List<gate.Annotation> list=new ArrayList<>(setGateAnnotations);
		Collections.sort(list);
		for(gate.Annotation gateA : list){

			gate.Node gatenodestart = gateA.getStartNode();
			gate.Node gatenodeend =  gateA.getEndNode();
			try{
				gate.Document doc = corpus.get(0);
				DocumentContent token = doc.getContent().getContent(gatenodestart.getOffset(),gatenodeend.getOffset());
				log.info(token);
				int initialpos = gatenodestart.getOffset().intValue();
				if(precedentposition<initialpos){
					String stringap = content.substring(precedentposition, initialpos );
					c.setContent(stringap);
					precedentposition = gatenodeend.getOffset().intValue();
				}



				Node init= new Node(gatenodestart.getId());

				Node end= new Node(gatenodeend.getId());
				c.setContent(init);
				c.setContent(token.toString());
				c.setContent(end);

				Annotation a = new Annotation();
				a.setId(gateA.getId());
				a.setEndNode(end.getId());
				a.setStartNode(init.getId());
				a.setType("Simplicity Excessive Length");


				a.setRecommendation("Shorten the sentence. A sentence should not exceed 25 words.");
				annotations.add(a);


				numDefectiveSentences++;
			}catch(InvalidOffsetException e){
				log.debug(e);
			}
		}

	}

	public AnnotatedStaticContentAnalysis check(StaticContentAnalysis staticContentInput){
		String title = staticContentInput.getStaticContent().getTitle();
		String idc = staticContentInput.getStaticContent().getId();
		String content = staticContentInput.getStaticContent().getContentplain();

		/*JLanguageTool langTool = new JLanguageTool(language);

		List<RuleMatch> matches;
		try {

			matches = langTool.check(content);

			List<String> listsentence = langTool.sentenceTokenize(content);

		 */

		annotatedStaticContent = new AnnotatedStaticContentAnalysis();
		StaticContent sc = new StaticContent();
		annotatedStaticContent.setStaticContent(sc);
		sc.setTitle(title);
		sc.setId(idc);
		Content c = new Content();
		sc.setContent(c);

		UtilsGate gateu = new UtilsGate(content);

		gateu.runProcessingResourcesforLenght();
		Set<gate.Annotation> result = gateu.getAnnotationSet(new HashSet<String>() {{
			add("Sent-Long");
		}});

		List<Annotation> annotations =new ArrayList<Annotation>();

		gatevsleanpad(c,result,annotations,content,gateu.getCorpus());

		Set<gate.Annotation> listSentence = gateu.getAnnotationSet(new HashSet<String>() {{
			add("Sentence");
		}});

		//	id  = calculateAnnotations( sentence, matches, c, id,annotations);
		annotatedStaticContent.setAnnotations(annotations);

		double qualitymmeasure = calculateOverallQualityMeasure(listSentence.size());
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(qualitymmeasure+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Simplicity Excessive Length");

		return annotatedStaticContent;

	}

}
