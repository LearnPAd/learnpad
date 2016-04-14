/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Bookmarks;
import eu.learnpad.or.rest.data.Entities;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * Provides semantics for the wiki based on the ontology. 
 * 
 * @author sandro.emmenegger
 */
public interface SemanticWiki {
    
    /**
     * Analyses a (html) text and identifies named entities provided by the ontology.
     * Three entity types are identified: Persons, resource(document) links and organisational units.
     * 
     * @param modelSetId
     * @param artifactId
     * @param userId
     * @param title
     * @param text
     * @return
     * @throws LpRestException 
     */
    @Path("/{modelsetid}/analysetext")
    @POST    
    Entities analyseText(@PathParam("modelsetid") String modelSetId,
            @QueryParam("artifactid") String artifactId,
            @QueryParam("userid") String userId, 
            @QueryParam("title") String title, 
            String text) throws LpRestException;
    
    /**
     * Creates a bookmark of a selected named entity (person, document, organisationalUnit).
     * Either a global bookmark or one for the context is created. In case of a context realted 
     * bookmark, the artifactId is required and considered as context. This could be a task of a
     * process for instance.
     * 
     * @param modelSetId
     * @param userId
     * @param entityId
     * @throws LpRestException 
     */
    @Path("/{modelsetid}/bookmark")
    @GET
    void createBookmark(@PathParam("modelsetid") String modelSetId,
            @QueryParam("userid") String userId, 
            @QueryParam("entityId") String entityId) throws LpRestException;    
    
    /**
     * Returns all bookmarks of a user. If a context(artifactId) is provided,
     * all bookmarks related to that context are returned. If no artifactId is 
     * defined, global and all context related bookmarks are returned.
     * 
     * @param modelSetId
     * @param userId
     * @param artifactId
     * @return
     * @throws LpRestException 
     */
    @Path("/{modelsetid}/bookmarks")
    @GET
    Bookmarks getAllBookmarks(@PathParam("modelsetid") String modelSetId,
            @QueryParam("userid") String userId, 
            @QueryParam("artifactId") String artifactId) throws LpRestException;    
}
