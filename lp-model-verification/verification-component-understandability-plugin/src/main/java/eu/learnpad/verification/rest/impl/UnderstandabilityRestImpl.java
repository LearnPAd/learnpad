package eu.learnpad.verification.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.learnpad.verification.plugin.bpmn.guideline.factory.GuidelinesFactory;




@Path("/learnpad/ca/validatemodel")
@Produces(MediaType.APPLICATION_XML)
public class UnderstandabilityRestImpl {


	@Path("/")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String putModel(String modelxml){

		return null;
	}


	@Path("/{idmodel:\\d+}/status")
	@GET
	public String getStatusCollaborativeContentVerifications(@PathParam("idmodel") String modelID){

		return null;
	}

	
	@Path("/{idmodel:\\d+}")
	@GET
	public GuidelinesFactory getUnderstandabilityVerifications(@PathParam("idmodel") String modelID){
		
		
		return null;
	}
	
	
}
