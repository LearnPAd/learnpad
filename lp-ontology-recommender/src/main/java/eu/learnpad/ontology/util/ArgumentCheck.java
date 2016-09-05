/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.util;

import eu.learnpad.ontology.recommender.RecommenderException;

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
    
    static public void notNullThrowException(Object argument, String failMessage) throws RecommenderException{
        if(argument == null){
            throw new RecommenderException("Expect non null argument: "+failMessage);
        }
    }
}
