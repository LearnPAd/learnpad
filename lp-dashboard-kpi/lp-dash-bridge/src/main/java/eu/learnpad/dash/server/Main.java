/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.dash.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import eu.learnpad.dash.impl.DASHBridgeImpl;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * 
 * @author gulyx
 */
public class Main {

	private static String PORT_LABEL="lp-dash.port";
	private static String WELCOME_LABEL="lp-dash.welcome";
	private static String COCKPIT_URL_LABEL="lp-dash.cockpit.url";
		
	private static String PORT = "7078";
	private static String WELCOME_PATH_SPEC = "/lp-dashboard-kpi";
	private static String COCKPIT_URL = "http://localhost:9098";

	 public static boolean initProps(String propertyFile) {
		boolean status = true;
		Properties systemProps = new Properties();			
        try {
        	InputStream is = new FileInputStream(propertyFile);
        	systemProps.load(is);

        	PORT =              systemProps.getProperty(PORT_LABEL, PORT);
            WELCOME_PATH_SPEC = systemProps.getProperty(WELCOME_LABEL, WELCOME_PATH_SPEC);
            COCKPIT_URL = systemProps.getProperty(COCKPIT_URL_LABEL, COCKPIT_URL);
         } catch (IOException e) {
 			System.err.println("Something wrong happened. Default Configuration Loaded");
            status = false;
     }
        return status;
 }

	/**
	 * Main method.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception {
		try{		
			Main.initProps(args[0]);
		}catch(Exception e){
			System.err.println("Default Configuration Loaded");
		}
			
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	    context.setContextPath("/");
	 
	    Server jettyServer = new Server(Integer.valueOf(Main.PORT));
	    jettyServer.setHandler(context);
	 
		ResourceConfig config = new ResourceConfig();
		config.packages(Main.class.getPackage().getName());
		
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		
		context.addServlet(servlet, Main.WELCOME_PATH_SPEC);
		servlet.setInitOrder(0);
		
		DASHBridgeImpl.setCockpitURL(Main.COCKPIT_URL);
		
		ServletHolder jerseyServlet = context.addServlet(
	             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
	    jerseyServlet.setInitOrder(1);
	 
	    // Tells the Jersey Servlet which REST service/class to load.
	    jerseyServlet.setInitParameter(
	           "jersey.config.server.provider.classnames",
	           DASHBridgeImpl.class.getCanonicalName());
	 
	    try {
	        jettyServer.start();
	        jettyServer.join();
	    } finally {
	        jettyServer.destroy();
	    }
	}
}
