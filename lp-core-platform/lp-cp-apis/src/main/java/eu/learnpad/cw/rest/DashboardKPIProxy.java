package eu.learnpad.cw.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;

public interface DashboardKPIProxy {
		
	@GET
	@Path("/dashboardkpi/viewer")
	String getDashboardKpiDefaultViewer(@QueryParam("modelsetid") String modelSetId, @QueryParam("userid") String userId) throws LpRestException;

	@GET
    @Path("/dashboardkpi/{modelsetid}/calculatekpi")
	String calculateKPI(@PathParam("modelsetid") String modelSetId) throws LpRestException;
	
}
