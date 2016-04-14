/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author sandro.emmenegger
 */
public class Bookmark {
    
    //optional association to resource
    private String artifactId;
    private BusinessActor expert;
    private LearningMaterial learningMaterial;

    public String getArtifactId() {
        return artifactId;
    }

    @XmlElement
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    
    public BusinessActor getExpert() {
        return expert;
    }

    @XmlElement
    public void setExpert(BusinessActor expert) {
        this.expert = expert;
    }
    
    public LearningMaterial getLearningMaterial() {
        return learningMaterial;
    }

    @XmlElement  
    public void setLearningMaterial(LearningMaterial learningMaterial) {
        this.learningMaterial = learningMaterial;
    }    
    
}
