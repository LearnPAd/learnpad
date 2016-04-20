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
}