package eu.learnpad.cw.rest;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;

public interface NotifyRecommendations {

	@Path("/notify/{modelsetid}")
	@PUT
	public void notifyRecommendations(@PathParam("modelsetid") String modelSetId,
			@QueryParam("simulationid") @DefaultValue("") String simulationid,
			@QueryParam("userid") @DefaultValue("") String userId, Recommendations rec) throws LpRestException;

	@Path("/notify/deleterecs/{modelsetid}")
	@PUT
	public void deleteRecommendations(@PathParam("modelsetid") String modelSetId,
			@QueryParam("simulationid") @DefaultValue("") String simulationid,
			@QueryParam("userid") @DefaultValue("") String userId) throws LpRestException;
}