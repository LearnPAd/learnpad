/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author sandro.emmenegger
 */
public class State {
    
    String pageId;
    String processInstanceId;
    String processName;
    String modelSetId;
    String artefactId;
    String artefactName;
    String artefactInstanceId;
    Date startTime;
    Date endingTime;

    public String getPageId() {
        return pageId;
    }

    @XmlElement
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }
    @XmlElement
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessName() {
        return processName;
    }
    @XmlElement
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getModelSetId() {
        return modelSetId;
    }
    @XmlElement
    public void setModelSetId(String modelSetId) {
        this.modelSetId = modelSetId;
    }

    public String getArtefactId() {
        return artefactId;
    }
    @XmlElement
    public void setArtefactId(String artefactId) {
        this.artefactId = artefactId;
    }

    public String getArtefactName() {
        return artefactName;
    }
    @XmlElement
    public void setArtefactName(String artefactName) {
        this.artefactName = artefactName;
    }

    public String getArtefactInstanceId() {
        return artefactInstanceId;
    }
    @XmlElement
    public void setArtefactInstanceId(String artefactInstanceId) {
        this.artefactInstanceId = artefactInstanceId;
    }

    public Date getStartTime() {
        return startTime;
    }
    @XmlElement
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }
    @XmlElement
    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }
    
}
