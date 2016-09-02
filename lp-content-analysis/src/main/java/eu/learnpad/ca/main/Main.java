package eu.learnpad.ca.main;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.PathParam;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import eu.learnpad.ca.config.PropertyUtil;
import eu.learnpad.ca.gate.GateServletContextListener;
import eu.learnpad.ca.impl.BridgeImpl;
import eu.learnpad.exception.LpRestException;


public class Main {

	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://0.0.0.0:8082/lp-content-analysis/";
    private final String PORT_Label = "port_server";
    private final String PORT_DEFAULT = "9092";

    private final String configFileLocationLabel = "config.file.location";
    private PropertyUtil conf;

	public Main() {
		String confFileName = System.getProperty(configFileLocationLabel);
		if (confFileName == null) {
			this.conf = new PropertyUtil();
		} else {
			this.conf = new PropertyUtil(confFileName);
		}
	}

	private String forgeBaseURI(){
		String port = this.conf.getProperty(PORT_Label, PORT_DEFAULT);
		
		return "http://0.0.0.0:"+port+"/lp-content-analysis/";
	}
	
	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer(URI baseURI) {
		// create a resource config that scans for JAX-RS resources and providers

		final ResourceConfig rc = new ResourceConfig(BridgeImpl.class);

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(baseURI, rc,false);
//		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc,false);
	}

	/**
	 * Main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Main m = new Main();
		URI baseURI = URI.create(m.forgeBaseURI());		
		HttpServer server = null;
		try{
			server = startServer(baseURI);

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
