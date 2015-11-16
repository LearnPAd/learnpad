package eu.learnpad.ca.main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




@Path("/")
public class welcomPage {

	
	
	
	
	@Produces(MediaType.TEXT_HTML)
	@GET
	public String getWelcom(){
		
		
		String welkome =  "Welcome on Content Analysis Component!";
		
		String stringa = "This is the Content Analysis component of the LearnPAd platform.\n please use ReST Interfaces\n";
		
		String page = "<html> " + "<title>" + "Content Analysis Component Page" + "</title>"
		          + "<body style=\"white-space: pre-wrap;\"><h1>" + welkome + "</h1>"+stringa
				+"</body>" + "</html> ";
		
		return page;
	}
	
	
}
