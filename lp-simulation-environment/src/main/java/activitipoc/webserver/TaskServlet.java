/**
 *
 */
package activitipoc.webserver;

import java.io.IOException;

import org.activiti.engine.task.Task;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import activitipoc.ProcessDispatcher;

/**
 * @author jorquera
 *
 */
public class TaskServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	private final ProcessDispatcher dispatcher;
	private final Task task;

	/**
	 * @param dispatcher
	 * @param task
	 */
	public TaskServlet(ProcessDispatcher dispatcher, Task task) {
		super();
		this.dispatcher = dispatcher;
		this.task = task;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new TaskSocketCreator(dispatcher, task));
	}

	/**
	 * @author jorquera
	 *
	 */
	private static class TaskSocketCreator implements WebSocketCreator {

		private final ProcessDispatcher dispatcher;
		private final Task task;

		/**
		 * @param dispatcher
		 * @param task
		 */
		public TaskSocketCreator(ProcessDispatcher dispatcher, Task task) {
			super();
			this.dispatcher = dispatcher;
			this.task = task;
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
			return new TaskSocket(dispatcher, task);
		}

	}

	/**
	 * @author jorquera
	 *
	 */
	private static class TaskSocket extends WebSocketAdapter {

		private final ProcessDispatcher dispatcher;
		private final Task task;

		/**
		 * @param dispatcher
		 * @param task
		 */
		public TaskSocket(ProcessDispatcher dispatcher, Task task) {
			super();
			this.dispatcher = dispatcher;
			this.task = task;
		}

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
			System.out
					.println("Socket " + task.getId() + " connected: " + sess);

			try {
				sess.getRemote().sendString(task.getDescription());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);
			System.out.println("Socket " + task.getId()
					+ " received TEXT message: " + message);

			this.getSession().close();
			dispatcher.completeTask(task, message);

		}

		@Override
		public void onWebSocketClose(int statusCode, String reason) {
			super.onWebSocketClose(statusCode, reason);
			System.out.println("Socket " + task.getId() + " closed: ["
					+ statusCode + "] " + reason);
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}
}
