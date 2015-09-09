package eu.learnpad.ca.analysis;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;

public interface AnalysisInterface {

	String getStatus();
	
	
	AnnotatedStaticContentAnalysis getAnnotatedStaticContentAnalysis();
	
	AnnotatedCollaborativeContentAnalysis getAnnotatedCollaborativeContentAnalysis();
}
