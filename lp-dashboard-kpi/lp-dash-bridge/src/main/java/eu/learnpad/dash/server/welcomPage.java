package eu.learnpad.dash.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class welcomPage {
	@Produces(MediaType.TEXT_HTML)
	@GET
	public String getWelcom(){
		
		
		String header =  "Welcome on Dashboard-KPI Component!";
		
		String message = "This is the Dashboard-KPI component of the Learn PAd platform.\n please use ReST Interfaces\n";
		
		String page = "<html> " + "<title>" + "Dashboard-KPI Component Page" + "</title>"
		          + "<body style=\"white-space: pre-wrap;\"><h1>" + header + "</h1>"+message
				+"</body>" + "</html> ";
		
		return page;
	}
	
}
