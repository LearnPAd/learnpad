/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest.data;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sandro.emmenegger
 */
@XmlRootElement
public class RelatedObjects {
    
    private List<RelatedObject> relatedObjects;

    public List<RelatedObject> getRelatedObjects() {
        return relatedObjects;
    }
    
    @XmlElement
    public void setRelatedObjects(List<RelatedObject> relatedObjects) {
        this.relatedObjects = relatedObjects;
    }
    
}
