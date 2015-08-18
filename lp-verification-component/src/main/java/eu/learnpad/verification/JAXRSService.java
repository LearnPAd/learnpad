package eu.learnpad.verification;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import eu.learnpad.verification.utils.Utils;

@Path("/LPVerificationComponent")
public class JAXRSService {
	@GET
    @Path("/getSupportedVerifications")
    @Produces(MediaType.TEXT_PLAIN)
	public static String getSupportedVerifications() throws Exception{
		String[] verificationList = VerificationComponent.getSupportedVerifications();
		String ret = "";
		for(String verification:verificationList)
			ret += verification + "\n";
		return ret;
	}
	
	@PUT
	@Path("/loadModel")
    @Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_XML)
	public static String loadModel(String model){
		return VerificationComponent.loadModel(model);
	}
	
	@GET
    @Path("/startVerification")
    @Produces(MediaType.TEXT_PLAIN)
	public static String startVerification(@QueryParam("modelId") String modelId, @QueryParam("verificationType") String verificationType) throws Exception{
		return VerificationComponent.startVerification(modelId, verificationType);
	}
	
	@GET
    @Path("/getVerificationStatus")
    @Produces(MediaType.TEXT_PLAIN)
	public static String getVerificationStatus(@QueryParam("verificationId") String verificationId) throws Exception{
		return VerificationComponent.getVerificationStatus(verificationId);
	}
	
	@GET
    @Path("/getVerificationResult")
    @Produces(MediaType.TEXT_PLAIN)
	public static String getVerificationResult(@QueryParam("verificationId") String verificationId) throws Exception{
		return VerificationComponent.getVerificationResult(verificationId);
	}
	
	public static void main(String[] args) {
		try{
			URI baseUri = UriBuilder.fromUri("http://127.0.0.1/rest").port(8080).build();
			final ResourceConfig resourceConfig = new ResourceConfig(JAXRSService.class);
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri,resourceConfig, false);
            server.start();
		}catch(Exception ex){ex.printStackTrace(); Utils.log(ex);}
	}
}
