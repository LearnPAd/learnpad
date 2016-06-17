package eu.learnpad.dash.server;

import eu.learnpad.dash.impl.DASHBridgeImpl;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

//	// Base URI the Grizzly HTTP server will listen on
//	public static final String BASE_URI = "http://0.0.0.0:8082/lp-content-analysis/";
//
//	/**
//	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
//	 * @return Grizzly HTTP server.
//	 */
//	public static HttpServer startServer() {
//		// create a resource config that scans for JAX-RS resources and providers
//
//		final ResourceConfig rc = new ResourceConfig(DASHBridgeImpl.class);
//
//		// create and start a new instance of grizzly http server
//		// exposing the Jersey application at BASE_URI
//		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc,false);
//	}

	/**
	 * Main method.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ResourceConfig config = new ResourceConfig();
		 config.packages("eu.learnpad.dash.server");
		 ServletHolder servlet = new ServletHolder(new ServletContainer(config));

		Server jettyServer = new Server(9098);
		 ServletContextHandler context = new ServletContextHandler(jettyServer, "/*");
		 context.addServlet(servlet, "/*");
		 
//		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//	        context.setContextPath("/");
//	 
//	        Server jettyServer = new Server(9098);
//	        jettyServer.setHandler(context);
//	 
//	        ServletHolder jerseyServlet = context.addServlet(
//	             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//	        jerseyServlet.setInitOrder(0);
//	 
//	        // Tells the Jersey Servlet which REST service/class to load.
//	        jerseyServlet.setInitParameter(
//	           "jersey.config.server.provider.classnames",
//	           DASHBridgeImpl.class.getCanonicalName());
	 
	        try {
	            jettyServer.start();
	            jettyServer.join();
	        } finally {
	            jettyServer.destroy();
	        }
	    }
}
