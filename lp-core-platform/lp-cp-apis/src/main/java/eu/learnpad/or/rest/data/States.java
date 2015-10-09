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
public class States {
    
    List<State> states = null;

    public List<State> getStates() {
        return states;
    }

    @XmlElement(name="state")
    public void setStates(List<State> states) {
        this.states = states;
    }
    
    public void addState(State state){
        if(states == null){
            states = new ArrayList();
        }
        states.add(state);
    }
    
    
}

