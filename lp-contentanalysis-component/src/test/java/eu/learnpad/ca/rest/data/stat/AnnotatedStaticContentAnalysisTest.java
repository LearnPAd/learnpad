package eu.learnpad.ca.rest.data.stat;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;


public class AnnotatedStaticContentAnalysisTest {

	@Test
	public void testAnnotatedStaticContentAnalysis() {
		AnnotatedStaticContentAnalysis asca = new AnnotatedStaticContentAnalysis(1234,"all");
		asca.setOverallQuality("overallquality");
		asca.setOverallQualityMeasure("OverallQualityMeasure");
		asca.setOverallRecommendations("Recommendation");
		StaticContent sc = new StaticContent("123","ciao");
		Content c = new Content();
		c.setContent("ciao");

		c.setContent(new Node(1234));

		sc.setContent(c);
		
		asca.setStaticContent(sc);
		Annotation a = new Annotation(12,"all",0,2,"rac");
	
		asca.setAnnotations(a);
		assertNotNull(asca);
		assertNotNull(asca.toString());
	}
	
	@Test
	public void testNode() {
		Node node = new Node(1234);
		Node node2 = new Node(123);
		assertNotEquals(node, node2);
		assertEquals(node, new Node(1234));
	}

}
