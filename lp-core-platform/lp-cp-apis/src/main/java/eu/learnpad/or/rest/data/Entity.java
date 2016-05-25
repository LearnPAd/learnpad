/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Encapsulates details about a named entity (like a person) recognizd in semanticly analyzed text.
 * The enitity has an id attribute to be used to refere to this entity. 
 *
 * @author sandro.emmenegger
 */
@XmlRootElement
public class Entity {
    
    private String id; //unique id to be referenced within analysed text
    private String contextArtifactId; //page id or ontology instance URI of recognized resource
    private String type;
    private BusinessActor person;
    private RelatedObjects relatedObjects;

    public String getContextArtifactId() {
        return contextArtifactId;
    }

    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
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
