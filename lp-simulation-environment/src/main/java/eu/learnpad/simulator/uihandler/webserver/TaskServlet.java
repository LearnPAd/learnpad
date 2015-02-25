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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.uihandler.IFormHandler;
import eu.learnpad.simulator.uihandler.webserver.msg.task.receive.BaseReceiveMessage;
import eu.learnpad.simulator.uihandler.webserver.msg.task.receive.Submit;
import eu.learnpad.simulator.uihandler.webserver.msg.task.receive.Subscribe;
import eu.learnpad.simulator.uihandler.webserver.msg.task.send.OtherValidated;
import eu.learnpad.simulator.uihandler.webserver.msg.task.send.Resubmit;
import eu.learnpad.simulator.uihandler.webserver.msg.task.send.TaskDesc;
import eu.learnpad.simulator.uihandler.webserver.msg.task.send.TaskError;
import eu.learnpad.simulator.uihandler.webserver.msg.task.send.Validated;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class TaskServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	private final UIHandlerWebImpl uiHandler;
	private final IProcessManager processManager;
	private final String processId;
	private final String taskId;
	private final String taskDesc;
	private final IFormHandler formHandler;

	private final Map<TaskSocket, String> activeSockets = new HashMap<TaskSocket, String>();

	/**
	 * @param dispatcher
	 * @param task
	 */
	public TaskServlet(UIHandlerWebImpl uiHandler,
			IProcessManager processManager, String processId, String taskId,
			String taskDesc, IFormHandler formHandler) {
		super();
		this.uiHandler = uiHandler;
		this.processManager = processManager;
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
		IProcessManager.TaskSubmissionStatus status = processManager
				.submitTaskCompletion(processId, taskId, formHandler
						.parseResult(data).getProperties());

		switch (status) {
		case VALIDATED:
			// signal task completion to users
			synchronized (activeSockets) {
				for (Entry<TaskSocket, String> e : activeSockets.entrySet()) {
					if (e.getValue().equals(activeSockets.get(socket))) {
						e.getKey().sendValidated();
					} else {
						e.getKey().sendOtherValidated();
					}
				}
			}
			uiHandler.completeTask(processId, taskId, data);
			System.out.println("task " + taskId + " has been validated");
			break;

		case REJECTED:
			// signal rejection to all interfaces of the same user
			synchronized (activeSockets) {
				for (Entry<TaskSocket, String> e : activeSockets.entrySet()) {
					if (e.getValue().equals(activeSockets.get(socket))) {
						e.getKey().sendResubmit();
					}
				}
			}
			System.out.println("task " + taskId + " has been resubmitted to "
					+ socket);
			break;

		case ALREADY_COMPLETED:
			// dismiss, the socket should already have transmitted the info
			break;

		case UNKOWN_TASK:
		case UNKOWN_ERROR:
			// weird error...
			socket.sendError();
			break;
		}
	}

	/**
	 * @author Tom Jorquera - Linagora
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
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private class TaskSocket extends WebSocketAdapter {

		private final TaskServlet container;

		private final ObjectMapper mapper = new ObjectMapper();

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
				getRemote().sendString(
						mapper.writeValueAsString(new Validated()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void sendResubmit() {
			try {
				getRemote().sendString(
						mapper.writeValueAsString(new Resubmit(taskDesc
								.replaceAll("\n", "<p/>"), processId,
								formHandler.createFormString(taskId))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void sendOtherValidated() {
			try {
				getRemote().sendString(
						mapper.writeValueAsString(new OtherValidated()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void sendError() {
			try {
				getRemote().sendString(
						mapper.writeValueAsString(new TaskError()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
			System.out.println("Socket " + taskId + " connected: " + sess);

			try {
				sess.getRemote().sendString(
						mapper.writeValueAsString(new TaskDesc(taskDesc
								.replaceAll("\n", "<p/>"), processId,
								formHandler.createFormString(taskId))));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);
			System.out.println("Socket " + taskId + " received TEXT message: "
					+ message);

			try {
				BaseReceiveMessage m = mapper.readValue(message,
						BaseReceiveMessage.class);

				switch (m.getType()) {

				case SUBSCRIBE:
					Subscribe subscMsg = mapper.readValue(message,
							Subscribe.class);
					synchronized (container.activeSockets) {
						container.activeSockets.put(this, subscMsg.user);
					}
					break;

				case SUBMIT:
					Submit submitMsg = mapper.readValue(message, Submit.class);
					container.submitTask(this, submitMsg.values);
					break;

				default:
					System.err
							.println("received unexpected message " + message);
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("received unexpected message " + message);
			}

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
