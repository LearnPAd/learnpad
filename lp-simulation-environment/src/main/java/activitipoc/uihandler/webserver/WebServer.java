/**
 *
 */
package activitipoc.uihandler.webserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * @author jorquera
 *
 */
public class WebServer {

	public static final long TIMEOUT = Long.MAX_VALUE;
	public static final String UI_PATH = "ui.html";
	public static final String UI_PROCESS_PATH = "ui-process.html";
	public static final String STATIC_RESOURCES_PATH = "static";

	final Server server;
	final ServletContextHandler context;
	private final String uiPath;
	private final String tasksPath;
	private final String uiProcessPath;

	public WebServer(final int port, String uiPath, String tasksPath,
			String uiProcessPath, UIProcessServlet uiProcessServlet)
			throws Exception {
		this.server = new Server();
		final ServerConnector connector = new ServerConnector(server);

		connector.setPort(port);
		this.server.addConnector(connector);
		this.uiPath = uiPath;
		this.tasksPath = tasksPath;
		this.uiProcessPath = uiProcessPath;

		this.context = new ServletContextHandler(ServletContextHandler.SESSIONS);

		// serve UI webpage (after dynamically setting server ip)
		HttpServlet ui_servlet = new IPTokenHTTPServlet(port, UI_PATH);
		this.context.addServlet(new ServletHolder(ui_servlet), "/");

		// serve UI Process webpage (after dynamically setting server ip)
		HttpServlet ui_process_servlet = new IPTokenHTTPServlet(port,
				UI_PROCESS_PATH);
		this.context.addServlet(new ServletHolder(ui_process_servlet),
				"/uiprocess");

		// related static resources
		ContextHandler resourcesContext = new ContextHandler();
		resourcesContext.setContextPath("/resources");
		ResourceHandler rh = new ResourceHandler();
		rh.setBaseResource(Resource.newClassPathResource(STATIC_RESOURCES_PATH));
		resourcesContext.setHandler(rh);

		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] { resourcesContext, context });
		server.setHandler(contexts);

		// start server
		this.server.start();

		// set ui process servlet
		ServletHolder holder = new ServletHolder(uiProcessServlet);
		String fullPath = "/" + this.uiProcessPath + "/*";

		this.context.addServlet(holder, fullPath);

		System.out.println("UI Process servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

		holder = new ServletHolder(new DummyChatServlet());
		fullPath = "/chat/*";

		this.context.addServlet(holder, fullPath);

		System.out.println("chat servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

	}

	public ServletHolder addUIServlet(WebSocketServlet servlet, String subpath) {
		ServletHolder holderEvents = new ServletHolder(servlet);

		String fullPath = "/" + this.uiPath + "/" + subpath + "/*";

		this.context.addServlet(holderEvents, fullPath);

		System.out.println("new UI servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

		return holderEvents;
	}

	public ServletHolder addTaskServlet(WebSocketServlet servlet, String subpath) {

		ServletHolder holderEvents = new ServletHolder(servlet);

		String fullPath = "/" + this.tasksPath + "/" + subpath + "/*";

		this.context.addServlet(holderEvents, fullPath);

		System.out.println("new task servlet launched at "
				+ server.getURI().toString()
				.substring(0, server.getURI().toString().length() - 1)
				+ fullPath);

		return holderEvents;
	}

	public void removeServletHolder(ServletHolder servletHolder) {
		ServletHandler handler = context.getServletHandler();

		/*
		 * A list of all the servlets that don't implement the class 'servlet',
		 * (i.e. They should be kept in the context
		 */
		List<ServletHolder> servlets = new ArrayList<ServletHolder>();

		/*
		 * The names all the servlets that we remove so we can drop the mappings
		 * too
		 */
		Set<String> names = new HashSet<String>();

		for (ServletHolder holder : handler.getServlets()) {
			/*
			 * If it is the class we want to remove, then just keep track of its
			 * name
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
			 * Only keep the mappings that didn't point to one of the servlets
			 * we removed
			 */
			if (!names.contains(mapping.getServletName())) {
				mappings.add(mapping);
			}
		}

		/* Set the new configuration for the mappings and the servlets */
		handler.setServletMappings(mappings.toArray(new ServletMapping[0]));
		handler.setServlets(servlets.toArray(new ServletHolder[0]));

	}

	public static String getIPAdress() throws UnknownHostException,
	SocketException {
		// TODO: ip should be read in a config file

		Enumeration<NetworkInterface> e = NetworkInterface
				.getNetworkInterfaces();
		while (e.hasMoreElements()) {
			NetworkInterface n = e.nextElement();
			Enumeration<InetAddress> ee = n.getInetAddresses();
			while (ee.hasMoreElements()) {
				InetAddress i = ee.nextElement();
				if (!i.getHostAddress().startsWith("127")
						&& !i.getHostAddress().startsWith("fe")) {
					return i.getHostAddress();
				}

			}
		}

		throw new RuntimeException("No valid IP adress");
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
			uiPage = uiPage.replace("#serveripaddress#", "\"" + getIPAdress()
					+ ":" + port + "\"");

			response.setContentType("text/html; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(uiPage);

		}
	}
}