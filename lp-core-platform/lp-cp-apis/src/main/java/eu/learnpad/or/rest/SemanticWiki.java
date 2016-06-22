/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.learnpad.or.rest;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Entities;
import eu.learnpad.or.rest.data.Recommendations;
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
     * Ex.
     * 
     * <code>
     * 
     * <entities>
     *   <entity> 					
     * 	   <contextArtifactId>transfer:obj.34872</contextArtifactId> 	<!-- uri of ontology instance-->
     * 	   <type>eo:Person</type> 				<!-- [eo:Person|foaf:Document|eo:OrganisationalUnit] -->
     *     <textMarker>
     * 		<startposition>26</startposition>
     * 		<length>14<length/>
     * 	   </textMarker>
     *     <businessActor>			<!-- person or organisational unit -->
     * 	       <uri>transfer:obj.34872</uri>  	<!-- uri of ontology instance-->
     *         <description></description>
     *         <name>Sally Shugar</name>
     *         <email>sally.shugar@learnpad.eu</email>
     *         <skypeId>sshugar@learnpad.eu</skypeId>
     *         <phoneNumber>+39 0733 656 344</phoneNumber>
     *         <officeAddress>Curved street 22A</officeAddress>
     *         <role>SUAP Officer</role>
     *         <organisationalUnit>
     *           <name>SUAP Office</name>
     *           <uri>transfer:obj.35099</uri> <!-- uri of ontology instance-->
     *         </organisationalUnit>
     *     </businessActor>
     *     <relatedObjects>
     *       <relatedObject>
     *         <relationType>sameAuthor</relationType> <!-- [sameCreator|sameAuthor|sameTaskContext|page] -->
     *         <uri>transfer:obj.34748</uri>
     *         <name>Pioneering Sustainable Buildings</name>
     *         <documentUrl>https://www.youtube.com/watch?v=lwsEV-lDqmo</documentUrl>
     *         <mimeType>text/html</mimeType>
     *         <description>Example of applying evolving water and power technologies with new light management and computer controlled environment</description>
     *         <comment></comment>
     *       </relatedObject>
     *     </relatedObjects>
     *   </entity>
     * </entities>
     * 
     * </code>
     * 
     * @param modelSetId
     * @param contextArtifactId
     * @param userId
     * @param title
     * @param text (HTML) text to analyse and anotate. 
     * @return
     * @throws LpRestException 
     */
    @Path("/{modelsetid}/analysetext")
    @POST    
    Entities analyseText(@PathParam("modelsetid") String modelSetId,
            @QueryParam("contextArtifactId") String contextArtifactId,
            @QueryParam("userid") String userId, 
            @QueryParam("title") String title, 
            String text) throws LpRestException;
    
    /**
     * Creates a bookmark of a selected named entity (person, document, organisationalUnit).
     * Either a global bookmark or one for the context is created. In case of a context realted 
     * bookmark, the artifactId (page id or URI of ontology instance, like a task) is required and considered as context. 
     * 
     * @param modelSetId
     * @param userId
     * @param artifactId the URI of the recognized entity resp. resource (person, document, OU) to bookmark
     * @param contextArtifactId the id of the context (page or URI of ontology instance)
     * @throws LpRestException 
     */
    @Path("/{modelsetid}/bookmark")
    @GET
    void createBookmark(@PathParam("modelsetid") String modelSetId,
            @QueryParam("userid") String userId, 
            @QueryParam("artifactId") String artifactId,
            @QueryParam("contextArtifactId") String contextArtifactId) throws LpRestException;    
    
    /**
     * Returns all bookmarks of a user as recommendations. If a context(artifactId) is provided,
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
    Recommendations getAllBookmarks(@PathParam("modelsetid") String modelSetId,
            @QueryParam("userid") String userId, 
            @QueryParam("artifactId") String artifactId) throws LpRestException;    
}
