/**
 *
 */
package activitipoc.webserver;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * @author jorquera
 *
 */
public class UIServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	public final String uiid;

	private final Set<String> currentTasks = new HashSet<String>();
	private final Set<Session> activeSessions = new HashSet<Session>();

	/**
	 * @param dispatcher
	 * @param task
	 */
	public UIServlet(String uiid) {
		super();
		this.uiid = uiid;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new UISocketCreator(uiid, this));
	}

	void addSession(Session sess) {
		this.activeSessions.add(sess);

		for (String taskid : currentTasks) {
			try {
				System.out.println("sending task " + taskid + " to " + sess);
				sess.getRemote().sendString(
						"{\"type\": \"ADDTASK\", \"taskid\":\"" + taskid
								+ "\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	void removeSession(Session sess) {
		this.activeSessions.remove(sess);
	}

	public void addTask(String id) {
		currentTasks.add(id);
		for (Session session : activeSessions) {
			try {
				session.getRemote().sendString(
						"{\"type\": \"ADDTASK\", \"taskid\":\"" + id + "\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeTask(String id) {
		currentTasks.remove(id);
		for (Session session : activeSessions) {
			try {
				session.getRemote().sendString(
						"{\"type\": \"DELTASK\", \"taskid\":\"" + id + "\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void completeProcess() {
		for (Session session : activeSessions) {
			try {
				session.getRemote().sendString("{\"type\": \"FINISHED\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author jorquera
	 *
	 */
	private static class UISocketCreator implements WebSocketCreator {

		private final String uiid;
		private final UIServlet container;

		/**
		 * @param dispatcher
		 * @param task
		 */
		public UISocketCreator(String uiid, UIServlet container) {
			super();
			this.uiid = uiid;
			this.container = container;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see
		 * org.eclipse.jetty.websocket.servlet.WebSocketCreator#createWebSocket
		 * (org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest,
		 * org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse)
		 */
		public Object createWebSocket(ServletUpgradeRequest req,
				ServletUpgradeResponse resp) {
			return new UISocket(uiid, container);
		}

	}

	/**
	 * @author jorquera
	 *
	 */
	private static class UISocket extends WebSocketAdapter {

		private final String uiid;
		private final UIServlet container;

		/**
		 * @param dispatcher
		 * @param task
		 */
		public UISocket(String uiid, UIServlet container) {
			super();
			this.uiid = uiid;
			this.container = container;
		}

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
			System.out.println("UI Socket " + uiid + " connected: " + sess);

			container.addSession(sess);
		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);
			System.err.println("UI Socket " + uiid
					+ " received unexpected message: " + message);
		}

		@Override
		public void onWebSocketClose(int statusCode, String reason) {
			super.onWebSocketClose(statusCode, reason);
			System.out.println("UI Socket " + uiid + " closed: [" + statusCode
					+ "] " + reason);

			container.removeSession(this.getSession());
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}
}
