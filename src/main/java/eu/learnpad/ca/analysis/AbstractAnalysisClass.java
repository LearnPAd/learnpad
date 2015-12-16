package eu.learnpad.ca.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.languagetool.Language;

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


	public String getStatus(){
		switch (this.getState()) {
		case TERMINATED:
			return "OK";

		default:
			return "IN PROGRESS";
		}

	}

	protected double calculateOverallQualityMeasure(Integer numsentence){
		double qm = (1-(numDefectiveSentences.doubleValue()/numsentence.doubleValue()))*100;
		double qualityMeasure = Math.abs(qm);
		return qualityMeasure;
	}

	protected String calculateOverallQuality(double qualityMeasure){
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

	protected String calculateOverallRecommendations(double qualityMeasure){
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


				String token = "";
				if(precedentposition>pos){
					//String stringap = //content.substring(precedentposition, initialpos );
					log.debug("error Annotation");

				}
				token = docContent.getContent(precedentposition.longValue(),pos.longValue()).toString();
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

}
