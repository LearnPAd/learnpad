package eu.learnpad.ca.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.exception.LpRestException;

@Path("/validatecollaborativecontent")
public interface ColloborativeContentVerifications {

	@Path("/")
	@POST
	String putValidateCollaborativeContent(CollaborativeContentAnalysis contentFile) throws LpRestException;

	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}")
	@GET
	AnnotatedCollaborativeContentAnalyses getCollaborativeContentVerifications(
			@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID) throws LpRestException;

	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}/status")
	@GET
	String getStatusCollaborativeContentVerifications(
			@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID) throws LpRestException;

	
	/**
	 *
	 * @param contentID
	 *            the id of contents that have been processed 
	 * @return the URL were is possible to access the default view (e.g. html) about of the AnnotatedCollaborativeContentAnalyses
	 * 		   processed for the given contentID
	 */
	@Path("/{idAnnotatedCollaborativeContentAnalysis:.*}/view")
	@GET
	String getCollaborativeContentVerificationsView(
			@PathParam("idAnnotatedCollaborativeContentAnalysis") String contentID) throws LpRestException;
}