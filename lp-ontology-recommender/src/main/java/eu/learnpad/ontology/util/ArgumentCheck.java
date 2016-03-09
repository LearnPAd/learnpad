/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.util;

/**
 *
 * @author sandro.emmenegger
 */
public class ArgumentCheck {
    
    static public void notNull(Object argument, String failMessage) throws IllegalArgumentException{
        if(argument == null){
            throw new IllegalArgumentException("Expect non null argument: "+failMessage);
        }
    }
    
}
