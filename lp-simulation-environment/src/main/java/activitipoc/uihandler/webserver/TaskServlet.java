/**
 *
 */
package activitipoc.uihandler.webserver;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import activitipoc.IFormHandler;

/**
 * @author jorquera
 *
 */
public class TaskServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	private final UIHandlerWebImpl uiHandler;
	private final String taskId;
	private final String taskDesc;
	private final IFormHandler formHandler;

	/**
	 * @param dispatcher
	 * @param task
	 */
	public TaskServlet(UIHandlerWebImpl uiHandler, String taskId,
			String taskDesc, IFormHandler formHandler) {
		super();
		this.uiHandler = uiHandler;
		this.taskId = taskId;
		this.taskDesc = taskDesc;
		this.formHandler = formHandler;

	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new TaskSocketCreator(taskId, taskDesc, this));
	}

	void completeTask(String data) {
		System.out.println("completed task " + taskId + " with data " + data);
		uiHandler.completeTask(taskId, data);
	}

	/**
	 * @author jorquera
	 *
	 */
	private static class TaskSocketCreator implements WebSocketCreator {

		private final String taskId;
		private final String taskDescr;
		private final TaskServlet container;

		/**
		 * @param uiHandler
		 * @param task
		 */
		public TaskSocketCreator(String taskId, String taskDescr,
				TaskServlet container) {
			super();
			this.taskId = taskId;
			this.taskDescr = taskDescr;
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
			return new TaskSocket(taskId, taskDescr, container);
		}

	}

	/**
	 * @author jorquera
	 *
	 */
	private static class TaskSocket extends WebSocketAdapter {

		private final String taskId;
		private final String taskDescr;
		private final TaskServlet container;

		/**
		 * @param uiHandler
		 * @param task
		 */
		public TaskSocket(String taskId, String taskDescr, TaskServlet container) {
			super();
			this.taskId = taskId;
			this.taskDescr = taskDescr;
			this.container = container;
		}

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
			System.out.println("Socket " + taskId + " connected: " + sess);

			try {
				sess.getRemote().sendString(
						"{\"description\":\""
								+ taskDescr.replaceAll("\n", "<p/>")
								+ "\", \"form\":"
								+ container.formHandler
										.createFormString(taskId) + "}");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);
			System.out.println("Socket " + taskId + " received TEXT message: "
					+ message);

			container.completeTask(message);

		}

		@Override
		public void onWebSocketClose(int statusCode, String reason) {
			super.onWebSocketClose(statusCode, reason);
			System.out.println("Socket " + taskId + " closed: [" + statusCode
					+ "] " + reason);
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}
}
