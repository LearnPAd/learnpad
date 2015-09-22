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
public class RecommenderQuery {
    
    private String queryString;
    private String description;
    
    public RecommenderQuery(String queryString, String queryDescription){
        this.queryString = queryString;
        this.description = queryDescription;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
