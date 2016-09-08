/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletMapping;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;

import eu.learnpad.simulator.Simulator;
import eu.learnpad.simulator.api.impl.SimulatorBridgeServletHolder;
import eu.learnpad.simulator.utils.SimulatorProperties;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class WebServer {

	public static final long TIMEOUT = Long.MAX_VALUE;
	public static final String UI_PATH = "ui.html";
	public static final String UI_PROCESS_PATH = "ui-process.html";
	public static final String UI_SINGLE_PROCESS_PATH = "ui-singleprocess.html";
	public static final String STATIC_RESOURCES_PATH = "static";
	public static final String WEBJARS_RESOURCES_PATH = "META-INF/resources/webjars";

	final Server server;
	final ServletContextHandler context;
	private final String uiPath;
	private final String tasksPath;

	public WebServer(final int port, String uiPath, String tasksPath,
			final Simulator simulator) throws Exception {
		this.server = new Server();
		final ServerConnector connector = new ServerConnector(server);

		connector.setPort(port);
		this.server.addConnector(connector);
		this.uiPath = uiPath;
		this.tasksPath = tasksPath;

		this.context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		// serve UI webpage (after dynamically setting server ip)
		HttpServlet ui_servlet = new IPTokenHTTPServlet(port, UI_PATH);
		this.context.addServlet(new ServletHolder(ui_servlet), "/");

		// serve UI Process webpage (after dynamically setting server ip)
		HttpServlet ui_process_servlet = new IPTokenHTTPServlet(port,
				UI_PROCESS_PATH);
		this.context.addServlet(new ServletHolder(ui_process_servlet),
				"/uiprocess");

		// serve UI Process webpage (after dynamically setting server ip)
		HttpServlet ui_single_process_servlet = new IPTokenHTTPServlet(port,
				UI_SINGLE_PROCESS_PATH);
		this.context.addServlet(new ServletHolder(ui_single_process_servlet),
				"/uisingleprocess");

		// related static resources
		ContextHandler resourcesContext = new ContextHandler();
		resourcesContext.setContextPath("/resources");
		ResourceHandler rh = new ResourceHandler();
		rh.setBaseResource(Resource.newClassPathResource(STATIC_RESOURCES_PATH));
		resourcesContext.setHandler(rh);

		ContextHandler webjarsContext = new ContextHandler();
		webjarsContext.setContextPath("/webjars/");
		rh = new ResourceHandler() {
			@Override
			public Resource getResource(String path)
					throws MalformedURLException {
				Resource resource = Resource.newClassPathResource(path);
				if (resource == null || !resource.exists()) {
					resource = Resource
							.newClassPathResource(WEBJARS_RESOURCES_PATH + path);
				}
				return resource;
			}
		};
		webjarsContext.setHandler(rh);

		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] { webjarsContext, resourcesContext,
				context });
		server.setHandler(contexts);

		context.setContextPath("/");

		// add REST bridge servlet
		String apiBasePath = eu.learnpad.sim.BridgeInterface.class
				.getAnnotation(Path.class).value();
		context.addServlet(new SimulatorBridgeServletHolder(simulator, context,
				apiBasePath), apiBasePath + "*");

		// start server
		this.server.start();

		// set chat servlet
		ServletHolder holder = new ServletHolder(new ChatServlet(simulator));
		String fullPath = "/chat/*";

		this.context.addServlet(holder, fullPath);

		System.out.println("chat servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

		holder = new ServletHolder(new DummyChatServlet());
		fullPath = "/dummychat/*";

		this.context.addServlet(holder, fullPath);

	}

	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ServletHolder addUIServlet(WebSocketServlet servlet, String subpath) {
		ServletHolder holderEvents = new ServletHolder(servlet);

		String fullPath = "/" + this.uiPath + "/" + subpath + "/*";

		synchronized (this.context) {
			this.context.addServlet(holderEvents, fullPath);
		}

		System.out.println("new UI servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

		return holderEvents;
	}

	public ServletHolder addTaskServlet(WebSocketServlet servlet, String subpath) {

		ServletHolder holderEvents = new ServletHolder(servlet);
		String fullPath = "/" + this.tasksPath + "/" + subpath + "/*";

		synchronized (this.context) {
			this.context.addServlet(holderEvents, fullPath);
		}

		System.out.println("new task servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

		return holderEvents;
	}

	public ServletHolder addServlet(Servlet servlet, String subpath) {
		ServletHolder holderEvents = new ServletHolder(servlet);
		String fullPath = "/" + subpath + "/*";

		synchronized (this.context) {
			this.context.addServlet(holderEvents, fullPath);
		}

		System.out.println("new servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

		return holderEvents;
	}

	public void removeServletHolder(ServletHolder servletHolder) {

		synchronized (this.context) {

			ServletHandler handler = context.getServletHandler();

			/*
			 * A list of all the servlets that don't implement the class
			 * 'servlet', (i.e. They should be kept in the context
			 */
			List<ServletHolder> servlets = new ArrayList<ServletHolder>();

			/*
			 * The names all the servlets that we remove so we can drop the
			 * mappings too
			 */
			Set<String> names = new HashSet<String>();

			for (ServletHolder holder : handler.getServlets()) {
				/*
				 * If it is the class we want to remove, then just keep track of
				 * its name
				 */
				if (servletHolder.equals(holder)) {
					names.add(holder.getName());
				} else /* We keep it */
				{
					servlets.add(holder);
				}
			}

			List<ServletMapping> mappings = new ArrayList<ServletMapping>();

			for (ServletMapping mapping : handler.getServletMappings()) {
				/*
				 * Only keep the mappings that didn't point to one of the
				 * servlets we removed
				 */
				if (!names.contains(mapping.getServletName())) {
					mappings.add(mapping);
				}
			}

			/* Set the new configuration for the mappings and the servlets */
			handler.setServletMappings(mappings.toArray(new ServletMapping[0]));
			handler.setServlets(servlets.toArray(new ServletHolder[0]));
		}
	}

	/**
	 * Select an IP address among the ones available to be used by the
	 * Simulator.
	 *
	 * The selection procedure tries to do the "correct" thing by default, by
	 * doing the following:
	 * <ol>
	 * <li>Try to select a non-local address</i>
	 * <li>If none if found, try to select a non-link-local, non-loopback
	 * address </i>
	 * <li>If none if found, try to select any responding address</li>
	 * </ol>
	 *
	 * @return The IP address considered by the Simulator
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static String getIPAddress() throws UnknownHostException,
			SocketException {
		// First check if the IP address has been specified in a properties file
		if (SimulatorProperties.props
				.containsKey(SimulatorProperties.PROP_ADDRESS)) {
			return SimulatorProperties.props
					.getProperty(SimulatorProperties.PROP_ADDRESS);
		} else {

			// try to guess a good address
			Set<InetAddress> localValidAddresses = new HashSet<InetAddress>();

			Enumeration<NetworkInterface> e = NetworkInterface
					.getNetworkInterfaces();

			// try to find a non-local address
			while (e.hasMoreElements()) {
				NetworkInterface n = e.nextElement();
				Enumeration<InetAddress> ee = n.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress i = ee.nextElement();
					String hostAddress = i.getHostAddress();
					if (!i.isLoopbackAddress() && !i.isLinkLocalAddress()) {

						if (!i.isAnyLocalAddress() && !i.isSiteLocalAddress()) {
							// got one !
							if (i instanceof Inet6Address) {
								return "[" + hostAddress.split("%")[0] + "]";
							} else {
								return hostAddress;
							}
						} else {
							// not good enough, store it in case we go to step 2
							localValidAddresses.add(i);
						}

					}
				}
			}

			// did not found any non-local address, try with a local (but
			// non-link
			// local)
			// address
			if (!localValidAddresses.isEmpty()) {
				InetAddress i = localValidAddresses.iterator().next();
				String hostAddress = i.getHostAddress();

				if (i instanceof Inet6Address) {
					return "[" + hostAddress.split("%")[0] + "]";
				} else {
					return hostAddress;
				}
			}

			// still no luck, let's try any answering address...
			e = NetworkInterface.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				NetworkInterface n = e.nextElement();
				Enumeration<InetAddress> ee = n.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress i = ee.nextElement();
					try {
						if (i.isReachable(500)) {
							String hostAddress = i.getHostAddress();
							if (i instanceof Inet6Address) {
								return "[" + hostAddress.split("%")[0] + "]";
							} else {
								return hostAddress;
							}
						}
					} catch (IOException e1) {
						// nothing more to do
					}
				}
			}

			// give up
			throw new RuntimeException("No valid IP address");
		}
	}

	private static class IPTokenHTTPServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		private final int port;
		private final String filePath;

		/**
		 * @param port
		 * @param filePath
		 */
		public IPTokenHTTPServlet(int port, String filePath) {
			super();
			this.port = port;
			this.filePath = filePath;
		}

		@Override
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException,
				IOException {

			// here we load the page html source in order to dynamically set
			// the server ip (required for websockets)
			Scanner scan = new Scanner(WebServer.class.getClassLoader()
					.getResourceAsStream(filePath));
			String uiPage = scan.useDelimiter("\\Z").next();
			scan.close();

			// set server ip
			uiPage = uiPage.replace("#serveripaddress#", "\"" + getIPAddress()
					+ ":" + port + "\"");
			uiPage = uiPage.replace("#platformaddress#", "\"" + SimulatorProperties.props
					.getProperty(SimulatorProperties.PLATFORM_ADDRESS) + "\"");

			response.setContentType("text/html; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(uiPage);

		}
	}
}
