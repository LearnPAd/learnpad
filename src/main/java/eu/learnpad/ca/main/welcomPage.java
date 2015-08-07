package eu.learnpad.ca.main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eu.learnpad.exception.LpRestException;



@Path("/")
public class welcomPage {

	
	
	
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public Response getWelcom(){
		String output =  "Welcome on Content Analysis Component! \n please use ReST Interfaces\n";
		
		return Response.status(200).entity(output).build();
	}
	
	
}
