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
public class RelatedObject {
    
    private String relationType;
    private String uri;
    private String name;
    private String documentUrl;
    private String mimeType;
    private String description;
    private String comment;

    public String getRelationType() {
        return relationType;
    }

    @XmlElement
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getUri() {
        return uri;
    }

    @XmlElement
    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    @XmlElement
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getMimeType() {
        return mimeType;
    }

    @XmlElement
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    @XmlElement
    public void setComment(String comment) {
        this.comment = comment;
    }
}
