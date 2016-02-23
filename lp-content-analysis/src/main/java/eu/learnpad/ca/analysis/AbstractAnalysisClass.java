package eu.learnpad.ca.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.languagetool.Language;

import eu.learnpad.ca.analysis.localizzation.Messages;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;
import gate.DocumentContent;
import gate.util.InvalidOffsetException;

public abstract class AbstractAnalysisClass extends Thread{

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AbstractAnalysisClass.class);
	protected AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContent;
	protected AnnotatedStaticContentAnalysis annotatedStaticContent;

	protected Language language;
	protected Integer numDefectiveSentences = 0;
	protected CollaborativeContentAnalysis collaborativeContentInput;
	protected StaticContentAnalysis staticContentInput;
	protected GateThread gateu = null;

	public String getStatus(){
		switch (this.getState()) {
		case TERMINATED:
			return Messages.getString("AbstractAnalysisClass.Ok",language); //$NON-NLS-1$

		default:
			return Messages.getString("AbstractAnalysisClass.InProgess",language); //$NON-NLS-1$
		}

	}

	protected double calculateOverallQualityMeasure(Integer numsentence){
		double qm = (1-(numDefectiveSentences.doubleValue()/numsentence.doubleValue()))*100;
		double qualityMeasure = Math.abs(qm);
		return qualityMeasure;
	}

	protected String calculateOverallQuality(double qualityMeasure){
		String quality=""; //$NON-NLS-1$
		if(qualityMeasure<=25){
			quality=Messages.getString("AbstractAnalysisClass.VeryBad",language); //$NON-NLS-1$
		}else if(qualityMeasure<=50){
			quality=Messages.getString("AbstractAnalysisClass.Bad",language); //$NON-NLS-1$
		}else if(qualityMeasure<=75){
			quality=Messages.getString("AbstractAnalysisClass.Good",language); //$NON-NLS-1$
		}else if(qualityMeasure<100){
			quality=Messages.getString("AbstractAnalysisClass.VeryGood",language); //$NON-NLS-1$
		}else if(qualityMeasure==100){
			quality=Messages.getString("AbstractAnalysisClass.Excellent",language); //$NON-NLS-1$
		}
		return quality;
	}

	protected String calculateOverallRecommendations(double qualityMeasure){
		String recommendations=""; //$NON-NLS-1$
		if(qualityMeasure<=25){
			recommendations=Messages.getString("AbstractAnalysisClass.QualityVeryPoor",language); //$NON-NLS-1$
		}else if(qualityMeasure<=50){
			recommendations=Messages.getString("AbstractAnalysisClass.QualityPoor",language); //$NON-NLS-1$
		}else if(qualityMeasure<=75){
			recommendations=Messages.getString("AbstractAnalysisClass.QualityAcceptable",language); //$NON-NLS-1$
		}else if(qualityMeasure<100){
			recommendations=Messages.getString("AbstractAnalysisClass.WellDoneFewErrors",language); //$NON-NLS-1$
		}else if(qualityMeasure==100){
			recommendations=Messages.getString("AbstractAnalysisClass.WellDone",language); //$NON-NLS-1$
		}
		return recommendations;
	}


	public AnnotatedCollaborativeContentAnalysis getAnnotatedCollaborativeContentAnalysis(){
		return annotatedCollaborativeContent;
	}

	public AnnotatedStaticContentAnalysis getAnnotatedStaticContentAnalysis() {
		return annotatedStaticContent;
	} 

	protected void addNodeInContent(List<Node> listnode, Content c, DocumentContent docContent){
		Collections.sort(listnode);
		Integer precedentposition = 0;
		List<Node> nodeadded = new ArrayList<Node>();
		try{
			for(Node node :listnode){

				Integer pos = node.getOffSet();
				

				String token = ""; //$NON-NLS-1$
				if(precedentposition>pos){
					//String stringap = //content.substring(precedentposition, initialpos );
					log.debug("error Annotation"); //$NON-NLS-1$

				}
				token = docContent.getContent(precedentposition.longValue(),pos.longValue()).toString();
				if(token.length()>0)
					c.setContent(token);
				if(!nodeadded.contains(node)){
					c.setContent(node);
					nodeadded.add(node);
				}
				precedentposition = pos;
				//c.setContent(token.toString());
				//c.setContent(nodeend);



			}
			if(precedentposition.longValue()<docContent.size()){
				String finalsentece = docContent.getContent(precedentposition.longValue(),docContent.size()).toString();
				c.setContent(finalsentece);
			}
			if(listnode.isEmpty()){
				String token = docContent.toString();
				c.setContent(token);
			}
		}catch(InvalidOffsetException e){
			log.debug(e);
		}


	}
	
	public GateThread getGate(){
		return gateu;
	}

}
