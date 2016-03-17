/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.ontology.recommender;

/**
 *
 * @author sandro.emmenegger
 */
public class RecommenderException extends Exception {

    public RecommenderException() {
        super();
    }

    public RecommenderException(String message) {
        super(message);
    }

    public RecommenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecommenderException(Throwable cause) {
        super(cause);
    }
}
