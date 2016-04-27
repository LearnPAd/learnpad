/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandro.emmenegger
 */
@XmlRootElement
public class SimilarCase {

	private String id; // uri
	private String name;
	private String similarityValue;

	// case characterisation and content properties
	private Map<String, ListOfStringWrapper> data;

	// referenced case content items
	private Experts experts;
	private LearningMaterials learningMaterials;

	public String getId() {
		return id;
	}

	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getSimilarityValue() {
		return similarityValue;
	}

	@XmlElement
	public void setSimilarityValue(String similarityValue) {
		this.similarityValue = similarityValue;
	}

	public Map<String, ListOfStringWrapper> getData() {
		return data;
	}

	@XmlElement(name = "data")
	public void setData(Map<String, ListOfStringWrapper> data) {
		this.data = data;
	}

	public Experts getExperts() {
		return experts;
	}

	@XmlElement
	public void setExperts(Experts experts) {
		this.experts = experts;
	}

	public LearningMaterials getLearningMaterials() {
		return learningMaterials;
	}

	public void setLearningMaterials(LearningMaterials learningMaterials) {
		this.learningMaterials = learningMaterials;
	}
}