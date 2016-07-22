package eu.learnpad.cw.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

public interface DashboardKPIProxy {
		
	@GET
	@Path("/dashboardkpi/viewer")
	String getDashboardKpiDefaultViewer(@QueryParam("modelsetid") String modelSetId, @QueryParam("userid") String userId) throws LpRestException;

}
