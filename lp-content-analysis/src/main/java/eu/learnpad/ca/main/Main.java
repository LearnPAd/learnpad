package eu.learnpad.ca.main;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import eu.learnpad.ca.gate.GateServletContextListener;
import eu.learnpad.ca.rest.impl.ColloborativeContentVerificationsImpl;
import eu.learnpad.ca.rest.impl.StaticContentVerificationsImpl;



public class Main {

	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8082/lp-content-analysis/";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and providers

		final ResourceConfig rc = new ResourceConfig(ColloborativeContentVerificationsImpl.class, StaticContentVerificationsImpl.class);

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc,false);
	}

	/**
	 * Main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		HttpServer server = null;
		try{
			server = startServer();

			WebappContext webappContext = new WebappContext("lp-content-analysis");

			webappContext.addListener(GateServletContextListener.class);
			//ServletRegistration servletRegistration = webappContext.addServlet( "jersey-servlet", "org.glassfish.jersey.servlet.ServletContainer" );
			webappContext.deploy( server );
			server.start();
			System.out.println(String.format("Web app started with WADL available at "
					+ "%sapplication.wadl", BASE_URI));

		}catch(Exception e){
			server.shutdown();
		}
	}
}
