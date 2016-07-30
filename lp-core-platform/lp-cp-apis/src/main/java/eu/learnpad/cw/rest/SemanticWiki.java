/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.cw.rest;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Entities;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * @author gulyx
 */
public interface SemanticWiki {
    
    /**
     * Retrieve the entities to a given (html) text. 
     * See also {@link See eu.learnpad.or.rest.data.Entities}) 
     *  
     * @param modelSetId
     * @param contextArtifactId
     * @param userId
     * @param title
     * @param text (HTML) text to analyse and anotate. 
     * @return
     * @throws LpRestException 
     */
    @Path("/semantic/{modelsetid}/analysetext")
    @POST    
    Entities analyseText(@PathParam("modelsetid") String modelSetId,
            @QueryParam("contextArtifactId") String contextArtifactId,
            @QueryParam("userid") String userId, 
            @QueryParam("title") String title, 
            String text) throws LpRestException;
    
}
