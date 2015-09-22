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
public class Recommendations {
    
    private Experts experts;

    public Experts getExperts() {
        return experts;
    }

    @XmlElement(name="experts")
    public void setExperts(Experts experts) {
        this.experts = experts;
    }
    
}
