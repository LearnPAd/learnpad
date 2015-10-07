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
public class Experts {
    
    List<BusinessActor> businessActors = null;

    public List<BusinessActor> getBusinessActors() {
        return businessActors;
    }

    @XmlElement(name="businessActor")
    public void setBusinessActors(List<BusinessActor> businessActors) {
        this.businessActors = businessActors;
    }
    
    public void addBusinessActor(BusinessActor state){
        businessActors().add(state);
    }
    
    public void addAllBusinessActors(List<BusinessActor> experts){
        businessActors().addAll(experts);
    }
    
    private List<BusinessActor> businessActors(){
        if(businessActors == null){
            businessActors = new ArrayList();
        }
        return businessActors;
    }
    
}
