/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandro.emmenegger
 */
@XmlRootElement
public class Entity {
    
    private String contextArtifactId; //page id or ontology instance URI of recognized resource
    private String type;
    private TextMarker textMarker;
    private BusinessActor person;
    private RelatedObjects relatedObjects;

    public String getContextArtifactId() {
        return contextArtifactId;
    }

    @XmlElement
    public void setContextArtifactId(String contextArtifactId) {
        this.contextArtifactId = contextArtifactId;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    public TextMarker getTextMarker() {
        return textMarker;
    }

    @XmlElement
    public void setTextMarker(TextMarker textMarker) {
        this.textMarker = textMarker;
    }

    public BusinessActor getPerson() {
        return person;
    }

    @XmlElement
    public void setPerson(BusinessActor person) {
        this.person = person;
    }

    public RelatedObjects getRelatedObjects() {
        return relatedObjects;
    }

    @XmlElement
    public void setRelatedObjects(RelatedObjects relatedObjects) {
        this.relatedObjects = relatedObjects;
    }
    
}
