package eu.learnpad.ca.analysis.simplicity;


import java.text.DecimalFormat;
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
			// check(staticContentInput);
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

		UtilsGate gateu = new UtilsGate(content);
		docContent = gateu.getCorpus().get(0).getContent();
		gateu.runProcessingResourcesforLenght();
		Set<gate.Annotation> listSentence = gateu
				.getAnnotationSet(new HashSet<String>() {
					{
						add("Sentence");
					}
				});

		DifficultJargonAlternative dja = new DifficultJargonAlternative(language, gateu.getCorpus().get(0).getContent());
		Set<gate.Annotation> listSentenceDefected = new HashSet<>();
		List<Annotation> listannotationsdja = dja.checkUnclearAcronym(listSentence,listSentenceDefected);
		Set<gate.Annotation> SetExcessiveLength = gateu.getAnnotationSet(new HashSet<String>() {
			{
				add("Sent-Long");
			}
		});
		gatevsleanpadExcessiveLength(SetExcessiveLength, listannotationsdja,listSentenceDefected);
		

		addNodeInContent(listannotationsdja,c);
		annotatedCollaborativeContent.setAnnotations(listannotationsdja);
		numDefectiveSentences = listSentenceDefected.size();
		double qualitymmeasure = calculateOverallQualityMeasure(listSentence.size());
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Simplicity");

		return annotatedCollaborativeContent;

	}

	private void addNodeInContent(List<Annotation> listannotations, Content c){
		Collections.sort(listannotations);
		Integer precedentposition = 0;
		for(Annotation annotation :listannotations){
			Node nodestart = annotation.getNodeStart();
			Integer startpos = annotation.getNodeStart().getOffSet();
			Node nodeend = annotation.getNodeEnd();
			Integer endpos = annotation.getNodeEnd().getOffSet();
			try{
				String token = "";
				if(precedentposition<startpos){
					//String stringap = //content.substring(precedentposition, initialpos );
					token = docContent.getContent(precedentposition.longValue(),startpos.longValue()).toString();
					c.setContent(token);
					
				}
				precedentposition = endpos;
				token = docContent.getContent(startpos.longValue(),endpos.longValue()).toString();
				c.setContent(nodestart);
				c.setContent(token.toString());
				c.setContent(nodeend);

			}catch(InvalidOffsetException e){
				log.debug(e);
			}

		}



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
