package eu.learnpad.ca.rest.data.collaborative;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AnnotatedCollaborativeContentAnalyses")
public class AnnotatedCollaborativeContentAnalyses {

	@XmlElement(required = true, name = "AnnotatedCollaborativeContentAnalysis")
	protected List<AnnotatedCollaborativeContentAnalysis> AnnotatedCollaborativeContentAnalyses;

	/**
	 * Gets the value of the annotateCollaborativeContentAnalysis property.
	 * 
	 * @return possible object is
	 *         {@link AnnotatedCollaborativeContentAnalyses.AnnotateCollaborativeContentAnalysis }
	 * 
	 */
	public List<AnnotatedCollaborativeContentAnalysis> getAnnotateCollaborativeContentAnalysis() {
		if (AnnotatedCollaborativeContentAnalyses == null) {
			AnnotatedCollaborativeContentAnalyses = new ArrayList<AnnotatedCollaborativeContentAnalysis>();
		}
		return AnnotatedCollaborativeContentAnalyses;
	}

	public void setAnnotateCollaborativeContentAnalysis(AnnotatedCollaborativeContentAnalysis value) {
		if (AnnotatedCollaborativeContentAnalyses == null) {
			AnnotatedCollaborativeContentAnalyses = new ArrayList<AnnotatedCollaborativeContentAnalysis>();
		}
		this.AnnotatedCollaborativeContentAnalyses.add(value);
	}
}
