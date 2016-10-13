package eu.learnpad.cw.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatus;

public interface DashboardKPIProxy {
		
	@GET
	@Path("/dashboardkpi/viewer")
	String getDashboardKpiDefaultViewer(@QueryParam("modelsetid") String modelSetId, @QueryParam("userid") String userId) throws LpRestException;

	@GET
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
    @Path("/dashboardkpi/{modelsetid}/calculatekpi")
	String calculateKPI(@PathParam("modelsetid") String modelSetId) throws LpRestException;
	
	@GET
	@Path("/dashboardkpi/{kpiCalculationProcessId}/status")
	KBProcessingStatus getKPICalculationStatus(@PathParam("kpiCalculationProcessId") String kpiCalculationProcessId) throws LpRestException;

}
