/*package eu.learnpad.ca.rest.data.collaborative;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;

public class AnnotatedCollaborativeContentAnalysisTest {

	@Test
	public void testAnnotatedCollaborativeContentAnalysis() {
		AnnotatedCollaborativeContentAnalysis asca = new AnnotatedCollaborativeContentAnalysis(1234,"all");
		asca.setOverallQuality("overallquality");
		asca.setOverallQualityMeasure("OverallQualityMeasure");
		asca.setOverallRecommendations("Recommendation");
		CollaborativeContent sc = new CollaborativeContent("123","ciao");
		Content c = new Content();
		c.setContent("ciao");

		c.setContent(new Node(1234));

		sc.setContent(c);
		
		asca.setCollaborativeContent(sc);
		Annotation a = new Annotation(12,"all",0,2,"rac");
	
		asca.setAnnotations(a);
		assertNotNull(asca);
		assertNotNull(asca.toString());
	}

}
*/