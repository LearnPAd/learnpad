package eu.learnpad.cw.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Feedback", propOrder = { "feedback" })
@XmlRootElement(name = "feedback")
public class Feedback {
	// TODO: class to store verification results; to define

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
}
