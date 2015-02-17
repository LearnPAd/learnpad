/**
 *
 */
package activitipoc.uihandler.webserver;

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

import activitipoc.IFormHandler;
import activitipoc.IProcessDispatcher;

/**
 * @author jorquera
 *
 */
public class TaskServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	private final UIHandlerWebImpl uiHandler;
	private final IProcessDispatcher dispatcher;
	private final String processId;
	private final String taskId;
	private final String taskDesc;
	private final IFormHandler formHandler;

	private final Set<TaskSocket> activeSockets = new HashSet<TaskSocket>();

	/**
	 * @param dispatcher
	 * @param task
	 */
	public TaskServlet(UIHandlerWebImpl uiHandler,
			IProcessDispatcher dispatcher, String processId, String taskId,
			String taskDesc, IFormHandler formHandler) {
		super();
		this.uiHandler = uiHandler;
		this.dispatcher = dispatcher;
		this.processId = processId;
		this.taskId = taskId;
		this.taskDesc = taskDesc;
		this.formHandler = formHandler;

	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new TaskSocketCreator(this));
	}

	void submitTask(TaskSocket socket, String data) {
		System.out.println("submitted task " + taskId + " with data " + data);

		// signal task submission to dispatcher and check validation
		IProcessDispatcher.TaskSubmissionStatus status = dispatcher
				.submitTaskCompletion(taskId, formHandler.parseResult(data)
						.getProperties());

		switch (status) {
		case VALIDATED:
			// signal task completion to users
			synchronized (activeSockets) {
				for (TaskSocket s : activeSockets) {
					s.sendValidated();
				}
			}

			uiHandler.completeTask(processId, taskId, data);

			System.out.println("task " + taskId + " has been validated");
			break;
		case REJECTED:
			synchronized (activeSockets) {
				for (TaskSocket s : activeSockets) {
					s.sendResubmit();
				}
			}
			System.out.println("task " + taskId + " has been resubmitted");
			break;
		default:
			throw new RuntimeException("should not happen");
		}
	}

	/**
	 * @author jorquera
	 *
	 */
	private class TaskSocketCreator implements WebSocketCreator {

		private final TaskServlet container;

		/**
		 * @param uiHandler
		 * @param task
		 */
		public TaskSocketCreator(TaskServlet container) {
			super();
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
			return new TaskSocket(container);
		}

	}

	/**
	 * @author jorquera
	 *
	 */
	private class TaskSocket extends WebSocketAdapter {

		private final TaskServlet container;

		/**
		 * @param uiHandler
		 * @param task
		 */
		public TaskSocket(TaskServlet container) {
			super();
			this.container = container;
		}

		void sendValidated() {
			try {
				getRemote().sendString("{ \"type\": \"VALIDATED\" }");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void sendResubmit() {
			try {
				getRemote().sendString(
						"{ \"type\": \"RESUBMIT\", \"description\":\""
								+ taskDesc.replaceAll("\n", "<p/>")
								+ "\", \"processid\": \"" + processId
								+ "\", \"form\":"
								+ formHandler.createFormString(taskId) + "}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
			System.out.println("Socket " + taskId + " connected: " + sess);

			synchronized (container.activeSockets) {
				container.activeSockets.add(this);
			}

			try {
				sess.getRemote().sendString(
						"{ \"type\": \"TASKDESC\", \"description\":\""
								+ taskDesc.replaceAll("\n", "<p/>")
								+ "\", \"processid\": \""
								+ container.processId
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

			container.submitTask(this, message);
		}

		@Override
		public void onWebSocketClose(int statusCode, String reason) {
			super.onWebSocketClose(statusCode, reason);
			synchronized (container.activeSockets) {
				container.activeSockets.remove(this);
			}
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
