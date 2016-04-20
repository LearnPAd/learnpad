/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandro.emmenegger
 */
@XmlRootElement
public class LearningMaterials {

	List<LearningMaterial> learningMaterials = null;

	public List<LearningMaterial> getLearningMaterials() {
		return learningMaterials;
	}

	@XmlElement(name = "learningMaterial")
	public void setLearningMaterials(List<LearningMaterial> learningMaterials) {
		this.learningMaterials = learningMaterials;
	}

	public void addLearningMaterial(LearningMaterial learningMaterial) {
		learningMaterials().add(learningMaterial);
	}

	public void addAllLearningMaterials(List<LearningMaterial> learningMaterials) {
		learningMaterials().addAll(learningMaterials);
	}

	private List<LearningMaterial> learningMaterials() {
		if (learningMaterials == null) {
			learningMaterials = new ArrayList<LearningMaterial>();
		}
		return learningMaterials;
	}
}