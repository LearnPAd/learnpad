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
public class LearningMaterial {
    
    private String id;
    private String name;
    private String url;
    private String mimeType;
    private String description;
    private String comment;
    private String queryDescription;
    private Boolean bookmark;
    
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement
    public void setUrl(String url) {
        this.url = url;
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
    
    public String getQueryDescription() {
        return queryDescription;
    }

    @XmlElement
    public void setQueryDescription(String queryDescription) {
        this.queryDescription = queryDescription;
    }    

    public Boolean getBookmark() {
        return bookmark;
    }

    @XmlElement
    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }
}
