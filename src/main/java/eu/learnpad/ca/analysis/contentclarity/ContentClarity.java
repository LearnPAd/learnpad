package eu.learnpad.ca.analysis.contentclarity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.contentclarity.plugin.ActorUnclear;
import eu.learnpad.ca.analysis.contentclarity.plugin.UnclearAcronym;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.gate.UtilsGate;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import gate.DocumentContent;
import gate.Factory;

public class ContentClarity extends AbstractAnalysisClass {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ContentClarity.class);
	
	private GateThread gateu = null;
	public ContentClarity(CollaborativeContentAnalysis collaborativeContentInput,Language lang, GateThread gate) {

		this.language = lang;
		this.collaborativeContentInput = collaborativeContentInput;
		this.gateu = gate;
	}

	public ContentClarity(StaticContentAnalysis staticContentInput, Language lang) {

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

		
		List<Annotation> listannotation  =new ArrayList<Annotation>();
		int numSentence = execute(content,c,listannotation);
		annotatedCollaborativeContent.setAnnotations(listannotation);
		double qualitymmeasure = calculateOverallQualityMeasure(numSentence);
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Content Clarity");

		return annotatedCollaborativeContent;

	}

	private int execute(String content, Content c,
			List<Annotation> listannotation) {
		try {
			gateu.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		DocumentContent docContent = gateu.getDocumentContent();
		Set<gate.Annotation> listSentence = gateu.getSentence();
		Set<gate.Annotation> listSentenceDefected = new HashSet<>();
		List<Node> listnode = new ArrayList<Node>();
		
		UnclearAcronym uclearac = new UnclearAcronym(language, docContent,listnode);
		uclearac.checkUnclearAcronym(listSentence, listSentenceDefected,listannotation);
		
		ActorUnclear ucleActor = new ActorUnclear(language, docContent, listnode);
		ucleActor.check(gateu, listannotation, listSentenceDefected);
		addNodeInContent(listnode,c,docContent);
		
		numDefectiveSentences = listSentenceDefected.size();
		Factory.deleteResource(gateu.getCorpus());
		return listSentence.size();
	}

	

	
}
