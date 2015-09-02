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
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;
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
	private final LearnPadTask task;
	private final IFormHandler formHandler;

	// need to be concurrent as it may be accessed by different
	// sockets representing different users that can try to complete the task
	private final ConcurrentHashMap<TaskSocket, String> activeSockets = new ConcurrentHashMap<TaskSocket, String>();

	/**
	 * @param dispatcher
	 * @param task
	 */
	public TaskServlet(UIHandlerWebImpl uiHandler,
			IProcessManager processManager, LearnPadTask task,
			IFormHandler formHandler) {
		super();
		this.uiHandler = uiHandler;
		this.processManager = processManager;
		this.task = task;
		this.formHandler = formHandler;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new TaskSocketCreator(this));
	}

	// Note: this method does not need to be synchronized as concurrent
	// validation check is made at the lowest level (process dispatcher)
	void submitTask(TaskSocket socket, String userId, String data) {
		System.out.println("User " + userId + "submitted task " + task.id
				+ " with data " + data);

		// signal task submission to dispatcher and check validation
		LearnPadTaskSubmissionResult result = processManager
				.submitTaskCompletion(task, userId,
						formHandler.parseResult(data).getProperties());

		LearnPadTaskSubmissionResult.TaskSubmissionStatus status = result.status;

		switch (status) {
		case VALIDATED:
			// signal task completion to users
			for (Entry<TaskSocket, String> e : activeSockets.entrySet()) {
				if (e.getValue().equals(activeSockets.get(socket))) {
					e.getKey().sendValidated(result.sessionScore);
				} else {
					e.getKey().sendOtherValidated();
				}
			}
			uiHandler.completeTask(task.processId, task.id, data);
			System.out.println("task " + task.id + " has been validated");
			break;

		case REJECTED:
			// signal rejection to all interfaces of the same user

			for (Entry<TaskSocket, String> e : activeSockets.entrySet()) {
				if (e.getValue().equals(activeSockets.get(socket))) {
					e.getKey().sendResubmit(userId);
				}
			}
			System.out.println("task " + task.id + " has been resubmitted to "
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

		void sendValidated(Integer sessionScore) {
			try {
				getRemote().sendString(
						mapper.writeValueAsString(new Validated(sessionScore)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		void sendResubmit(String userId) {
			try {
				getRemote()
				.sendString(
						mapper.writeValueAsString(new Resubmit(
								task.name,
								task.desc.replaceAll("\n", "<p/>"),
								task.processId,
								processManager
								.getProcessDefinitionName(processManager
										.getProcessDefinitionId(task.processId)),
										task.startingTime, formHandler
										.createFormString(task.id),
										task.documents,
										processManager.getGameInfos(task,
												userId).nbAttempts,
										processManager.getGameInfos(task,
												userId).expectedTime)));
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
			System.out.println("Socket " + task.id + " connected: " + sess);
		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);
			System.out.println("Socket " + task.id + " received TEXT message: "
					+ message);

			try {
				BaseReceiveMessage m = mapper.readValue(message,
						BaseReceiveMessage.class);

				switch (m.getType()) {

				case SUBSCRIBE:
					Subscribe subscMsg = mapper.readValue(message,
							Subscribe.class);
					container.activeSockets.put(this, subscMsg.user);

					// send task description to new subscriber
					try {
						this.getRemote()
								.sendString(
										mapper.writeValueAsString(new TaskDesc(
												task.name,
												task.desc.replaceAll("\n",
												"<p/>"),
												task.processId,
												processManager
														.getProcessDefinitionName(processManager
																.getProcessDefinitionId(task.processId)),
												task.startingTime,
												formHandler
														.createFormString(task.id),
												task.documents,
														processManager
														.getInstanceScore(
																task.processId,
																subscMsg.user),
																processManager.getGameInfos(
																		task, subscMsg.user).nbAttempts,
																		+processManager.getGameInfos(
																				task, subscMsg.user).expectedTime)));
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;

				case SUBMIT:
					Submit submitMsg = mapper.readValue(message, Submit.class);
					container
							.submitTask(this,
									container.activeSockets.get(this),
									submitMsg.values);
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
			container.activeSockets.remove(this);
			System.out.println("Socket " + task.id + " closed: [" + statusCode
					+ "] " + reason);
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}
}
