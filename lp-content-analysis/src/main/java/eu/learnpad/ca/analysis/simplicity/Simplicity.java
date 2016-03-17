package eu.learnpad.ca.analysis.simplicity;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.simplicity.plugin.DifficultJargon;
import eu.learnpad.ca.analysis.simplicity.plugin.ExcessiveLength;
import eu.learnpad.ca.analysis.simplicity.plugin.Juridical;
import eu.learnpad.ca.analysis.simplicity.plugin.TechnicalJargon;
import eu.learnpad.ca.gate.GateThread;
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
import gate.Factory;


public class Simplicity extends AbstractAnalysisClass {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Simplicity.class);
	private long lStartTime;
	

	public Simplicity(CollaborativeContentAnalysis collaborativeContentInput,Language lang, GateThread gate) {

		this.language = lang;
		this.collaborativeContentInput = collaborativeContentInput;
		this.gateu = gate;
	}

	public Simplicity(StaticContentAnalysis staticContentInput, Language lang, GateThread gate) {

		this.language = lang;
		this.staticContentInput = staticContentInput;
		this.gateu = gate;
	}

	public void run() {
		lStartTime = System.currentTimeMillis();
		//some tasks
		if (collaborativeContentInput != null) {
			check(collaborativeContentInput);
		}

		if (staticContentInput != null) {
			check(staticContentInput);
		}
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;

		log.trace("Simplicity Elapsed milliseconds: " + difference);

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

		//gateu.runProcessingResourcesforLenght();
		try {
			gateu.join();


			lStartTime = System.currentTimeMillis();
			DocumentContent docContent = gateu.getDocumentContent();
			Set<gate.Annotation> listSentence = gateu.getSentence();
			Set<gate.Annotation> listSentenceDefected = new HashSet<>();
			List<Node> listnode = new ArrayList<Node>();
			DifficultJargon dja = new DifficultJargon(language, docContent,listnode);

			dja.checkUnclearAcronym(listSentence,listSentenceDefected,listannotations);

			long lEndTime = System.currentTimeMillis();
			long difference = lEndTime - lStartTime;

			
			log.trace("Simplicity DifficultJargon Elapsed milliseconds: " + difference);
			long lStartTime = System.currentTimeMillis();
			
			//JuridicalJargon jj = new JuridicalJargon(language, docContent,listnode);
			//listannotations.addAll(jj.checkJJ(listSentence,listSentenceDefected));
			Juridical jj = new Juridical(language, docContent,listnode);
			jj.checkJuridicalJargon(gateu, listannotations, listSentenceDefected, listSentenceDefected);
			
			TechnicalJargon tj = new TechnicalJargon(language, docContent,listnode);
			tj.checkTechnicalJargon(gateu, listannotations, listSentenceDefected, listSentenceDefected);
			
			
			
			 lEndTime = System.currentTimeMillis();
			 difference = lEndTime - lStartTime;

			log.trace("Simplicity JuridicalJargon Elapsed milliseconds: " + difference);
			/*HashSet<String> hs = new HashSet<String>();
			hs.add("Sent-Long");
			Set<gate.Annotation> SetExcessiveLength = gateu.getAnnotationSet(hs);
			gatevsleanpadExcessiveLength(SetExcessiveLength, listannotations,listSentenceDefected,listnode,docContent);
*/
			 lStartTime = System.currentTimeMillis();
			ExcessiveLength excessiveLength = new ExcessiveLength(language, docContent,listnode);
			excessiveLength.check(gateu, listannotations, listSentenceDefected,listSentence);

			lEndTime = System.currentTimeMillis();
			 difference = lEndTime - lStartTime;

			log.trace("Simplicity ExcessiveLength Elapsed milliseconds: " + difference);
			addNodeInContent(listnode,c,docContent);

			numDefectiveSentences = listSentenceDefected.size();
			Factory.deleteResource(gateu.getCorpus());
			return listSentence.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);	
		}
		return 0 ;
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


	/*private List<Node> extractNode(List<Annotation> listannotation){
		List<Node> listnode = new ArrayList<Node>();
		for(Annotation a : listannotation){
			listnode.add(a.getNodeEnd());
			listnode.add(a.getNodeStart());
		}
		return listnode;
	}*/
	


}
