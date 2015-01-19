/**
 *
 */
package activitipoc.webserver;

import java.io.IOException;
import java.util.List;

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
	private final List<UIServlet> users;

	/**
	 * @param dispatcher
	 * @param task
	 */
	public TaskServlet(ProcessDispatcher dispatcher, Task task,
			List<UIServlet> users) {
		super();
		this.dispatcher = dispatcher;
		this.task = task;
		this.users = users;

		for (UIServlet ui : this.users) {
			ui.addTask(task.getId());
		}
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new TaskSocketCreator(task, this));
	}

	void completeTask(String data) {
		for (UIServlet ui : users) {
			ui.removeTask(task.getId());
		}
		dispatcher.completeTask(task, data);
	}

	/**
	 * @author jorquera
	 *
	 */
	private static class TaskSocketCreator implements WebSocketCreator {

		private final Task task;
		private final TaskServlet container;

		/**
		 * @param dispatcher
		 * @param task
		 */
		public TaskSocketCreator(Task task, TaskServlet container) {
			super();
			this.task = task;
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
			return new TaskSocket(task, container);
		}

	}

	/**
	 * @author jorquera
	 *
	 */
	private static class TaskSocket extends WebSocketAdapter {

		private final Task task;
		private final TaskServlet container;

		/**
		 * @param dispatcher
		 * @param task
		 */
		public TaskSocket(Task task, TaskServlet container) {
			super();
			this.task = task;
			this.container = container;
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

			container.completeTask(message);

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
