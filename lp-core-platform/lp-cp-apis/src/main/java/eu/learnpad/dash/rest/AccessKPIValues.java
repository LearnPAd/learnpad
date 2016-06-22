package eu.learnpad.dash.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

public interface AccessKPIValues {

	/**
	 * @param modelSetId
	 *            the id of the referred model set 
	 * 
	 * @param businessActorId
	 *            the id of the business actor that have been processed 
	 * 
	 * @return the URL were is possible to access the default view (e.g. html)
	 *         about of the AnnotatedCollaborativeContentAnalyses processed for
	 *         the given contentID
	 */
	@Path("/view/{modelsetid}")
	@GET
	String getKPIValuesView(@PathParam("modelsetid") String modelSetId,
			@QueryParam("businessactor") String businessActorId) throws LpRestException;
}
