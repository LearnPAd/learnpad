package eu.learnpad.cw.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;

@Path("/recommendation")
public interface OntologyRecommenderProxy {

	@GET
	public Recommendations getRecommendations(@QueryParam("modelsetid") String modelSetId,
			@QueryParam("artifactid") String artifactId,
			@QueryParam("userid") String userId) throws LpRestException;
}
