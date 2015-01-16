/**
 *
 */
package activitipoc.webserver;

import java.io.File;
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

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletMapping;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;

/**
 * @author jorquera
 *
 */
public class WebServer {

	public static final long TIMEOUT = Long.MAX_VALUE;
	public static final String UI_PATH = "src/main/resources/ui.html";

	final Server server;
	final ServletContextHandler context;
	private final String uiPath;
	private final String tasksPath;

	public WebServer(final int port, String uiPath, String tasksPath) {
		this.server = new Server();
		final ServerConnector connector = new ServerConnector(server);

		connector.setPort(port);
		this.server.addConnector(connector);
		this.uiPath = uiPath;
		this.tasksPath = tasksPath;

		// Setup the basic application "context" for this application at "/"
		// This is also known as the handler tree (in jetty speak)
		this.context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		this.context.setContextPath("/");

		// serve UI webpage (after dynamically setting server ip)
		HttpServlet ui_servlet = new HttpServlet() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void doGet(HttpServletRequest request,
					HttpServletResponse response) throws ServletException,
					IOException {
				Scanner scan = new Scanner(new File(UI_PATH));
				String uiPage = scan.useDelimiter("\\Z").next();
				scan.close();

				System.out.println();

				// set server ip
				uiPage = uiPage.replace("#serveripaddress#", "\""
						+ getIPAdress() + ":" + port + "\"");

				response.setContentType("text/html; charset=utf-8");
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(uiPage);
			}
		};
		this.context.addServlet(new ServletHolder(ui_servlet), "/");

		server.setHandler(context);

		// start server
		try {
			this.server.start();
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}

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

	private String getIPAdress() throws UnknownHostException, SocketException {

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

}