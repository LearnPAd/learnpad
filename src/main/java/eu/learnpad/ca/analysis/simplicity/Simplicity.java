package eu.learnpad.ca.analysis.simplicity;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.simplicity.plugin.DifficultJargonAlternative;
import eu.learnpad.ca.analysis.simplicity.plugin.JuridicalJargon;
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


public class Simplicity extends AbstractAnalysisClass {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Simplicity.class);
	private DocumentContent docContent;

	public Simplicity(CollaborativeContentAnalysis collaborativeContentInput,Language lang) {

		this.language = lang;
		this.collaborativeContentInput = collaborativeContentInput;
	}

	public Simplicity(StaticContentAnalysis staticContentInput, Language lang) {

		this.language = lang;
		this.staticContentInput = staticContentInput;
	}

	public void run() {
		if (collaborativeContentInput != null) {
			check(collaborativeContentInput);
		}

		if (staticContentInput != null) {
			check(staticContentInput);
		}

	}

	public AnnotatedCollaborativeContentAnalysis check(
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

		// AnnotationImpl i;
		List<Annotation> listannotation  =new ArrayList<Annotation>();
		int numSentence = execute(content,c,listannotation);
		annotatedCollaborativeContent.setAnnotations(listannotation);
		double qualitymmeasure = calculateOverallQualityMeasure(numSentence);
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Simplicity");

		return annotatedCollaborativeContent;

	}
	
	private int execute(String content, Content c, List<Annotation> listannotations){
		UtilsGate gateu = new UtilsGate(content);
		docContent = gateu.getCorpus().get(0).getContent();
		gateu.runProcessingResourcesforLenght();
		Set<gate.Annotation> listSentence = gateu.getAnnotationSet(new HashSet<String>() {{
						add("Sentence");
					}});
		Set<gate.Annotation> listSentenceDefected = new HashSet<>();
		
		DifficultJargonAlternative dja = new DifficultJargonAlternative(language, gateu.getCorpus().get(0).getContent());
		dja.checkUnclearAcronym(listSentence,listSentenceDefected,listannotations);
		
		JuridicalJargon jj = new JuridicalJargon(language, gateu.getCorpus().get(0).getContent());
		listannotations.addAll(jj.checkJJ(listSentence,listSentenceDefected));
		
		Set<gate.Annotation> SetExcessiveLength = gateu.getAnnotationSet(new HashSet<String>() {{
				add("Sent-Long");
			}});
		gatevsleanpadExcessiveLength(SetExcessiveLength, listannotations,listSentenceDefected);


		addNodeInContent(listannotations,c);
		
		numDefectiveSentences = listSentenceDefected.size();
		return listSentence.size();
	}
	
	private void check(StaticContentAnalysis staticContentInput2) {
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

		
		List<Annotation> listannotation  =new ArrayList<Annotation>();
		int numSentence = execute(content,c,listannotation);
		annotatedStaticContent.setAnnotations(listannotation);
		double qualitymmeasure = calculateOverallQualityMeasure(numSentence);
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Simplicity");

	}

	private void addNodeInContent(List<Annotation> listannotations, Content c){
		List<Node> listnode = this.extractNode(listannotations);
		Collections.sort(listnode);
		Integer precedentposition = 0;
		for(Node node :listnode){
			
			Integer pos = node.getOffSet();
			
			try{
				String token = "";
				if(precedentposition>pos){
					//String stringap = //content.substring(precedentposition, initialpos );
					log.debug("error Annotation Simplicity");

				}
				token = docContent.getContent(precedentposition.longValue(),pos.longValue()).toString();
				c.setContent(token);
				c.setContent(node);
				precedentposition = pos;
				//c.setContent(token.toString());
				//c.setContent(nodeend);

			}catch(InvalidOffsetException e){
				log.debug(e);
			}

		}



	}

	private List<Node> extractNode(List<Annotation> listannotation){
		List<Node> listnode = new ArrayList<Node>();
		for(Annotation a : listannotation){
			listnode.add(a.getNodeEnd());
			listnode.add(a.getNodeStart());
		}
		return listnode;
	}
	private void gatevsleanpadExcessiveLength(
			Set<gate.Annotation> setGateAnnotations,
			List<Annotation> annotations, Set<gate.Annotation> listSentenceDefected) {

		for (gate.Annotation gateA : setGateAnnotations) {

			gate.Node gatenodestart = gateA.getStartNode();
			gate.Node gatenodeend = gateA.getEndNode();
			try{
				String sentence_gate = docContent.getContent(gatenodestart.getOffset(),gatenodeend.getOffset()).toString();
				if(!listSentenceDefected.contains(sentence_gate))
					listSentenceDefected.add(gateA);
			}catch(InvalidOffsetException e){
				log.error(e);
			}
			int initialpos = gatenodestart.getOffset().intValue();


			Node init = new Node(gatenodestart.getId(), initialpos);

			Node end = new Node(gatenodeend.getId(), gatenodeend.getOffset()
					.intValue());

			Annotation a = new Annotation();
			a.setId(gateA.getId());
			a.setEndNode(end.getId());
			a.setStartNode(init.getId());
			a.setNodeEnd(end);
			a.setNodeStart(init);

			a.setType("Simplicity Excessive Length");

			a.setRecommendation("Shorten the sentence. A sentence should not exceed 25 words.");
			annotations.add(a);

		}

	}

}
