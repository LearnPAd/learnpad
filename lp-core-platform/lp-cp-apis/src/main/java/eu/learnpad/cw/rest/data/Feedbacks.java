package eu.learnpad.cw.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Feedbacks", propOrder = { "feedbacks" })
@XmlRootElement(name = "feedbacks")
public class Feedbacks {
	// TODO: class to store verification results; to define

	@XmlElement(name = "feedback", required = true)
	protected List<Feedback> feedbacks;

	public Feedbacks() {
		this.feedbacks = new ArrayList<Feedback>();
	}

	public Feedbacks(List<Feedback> feedbacks) {
		this.feedbacks = new ArrayList<Feedback>(feedbacks);
	}

	public void add(Feedback feedback) {
		if (feedbacks.isEmpty()) {
			feedbacks.add(feedback);
		} else {
			for (Feedback fb : feedbacks) {
				if (feedback.getModelSetId().equals(fb.getModelSetId())
						&& feedback.getModelId().equals(fb.getModelId())
						&& feedback.getArtifactId().equals(fb.getArtifactId())) {
					fb.add(feedback.getContents());
				} else {
					feedbacks.add(feedback);
				}
			}
		}
	}
}
