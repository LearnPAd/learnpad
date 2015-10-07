package eu.learnpad.cw.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Feedback", propOrder = { "modelSetId", "modelId", "artifactId", "contents" })
@XmlRootElement(name = "feedback")
public class Feedback {
	// TODO: class to store verification results; to define

	public String getModelSetId() {
		return modelSetId;
	}

	public String getModelId() {
		return modelId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public List<String> getContents() {
		return contents;
	}

	@XmlElement(name = "modelsetid", required = true)
	protected String modelSetId;

	@XmlElement(name = "modelid")
	protected String modelId;

	@XmlElement(name = "artifactid")
	protected String artifactId;

	@XmlElement(name = "content")
	protected List<String> contents;

	public Feedback() {
		this.contents = new ArrayList<String>();
	}

	public Feedback(String modelSetId, String modelId, String artifactId,
			List<String> contents) {
		this.modelSetId = modelSetId;
		this.modelId = modelId;
		this.artifactId = artifactId;
		this.contents = new ArrayList<String>(contents);
	}

	public void add(List<String> newcontents) {
		contents.addAll(newcontents);
	}
}
