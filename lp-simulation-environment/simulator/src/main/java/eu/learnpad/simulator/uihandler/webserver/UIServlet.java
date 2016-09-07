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
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.sim.rest.event.ScoreType;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.monitoring.event.impl.SimulationStartSimEvent;
import eu.learnpad.simulator.uihandler.webserver.msg.user.send.AddTask;
import eu.learnpad.simulator.uihandler.webserver.msg.user.send.DeleteTask;
import eu.learnpad.simulator.uihandler.webserver.msg.user.send.SessionFinished;
import eu.learnpad.simulator.uihandler.webserver.msg.user.send.SessionStarted;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class UIServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	public final String uiid;

	private final IProcessManager manager;

	private final Map<String, SimulationStartSimEvent> currentSessions = new HashMap<>();
	private final Map<String, String> currentTasksWithSession = new HashMap<>();
	private final Set<UISocket> activeSockets = new HashSet<>();

	private final ObjectMapper mapper = new ObjectMapper();

	/**
	 * @param dispatcher
	 * @param task
	 */
	public UIServlet(String uiid, IProcessManager manager) {
		super();
		this.uiid = uiid;
		this.manager = manager;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new UISocketCreator(uiid, this));
	}

	private void addSocket(UISocket sock) {
		this.activeSockets.add(sock);

		for (SimulationStartSimEvent session : currentSessions.values()) {
			try {
				sock.getRemote().sendString(
						mapper.writeValueAsString(new SessionStarted(
								session.simulationsessionid,
								session.simulationsessionname,
								session.involvedusers,
								session.initialProcessDefinitionKey)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Entry<String, String> task : currentTasksWithSession.entrySet()) {
			try {
				System.out.println("sending task " + task.getKey() + " to " + sock);
				sock.getRemote().sendString(
						mapper.writeValueAsString(new AddTask(task.getValue(), task.getKey())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void removeSocket(UISocket sock) {
		this.activeSockets.remove(sock);
	}

	public void addTask(String sessionId, String taskId) {
		currentTasksWithSession.put(taskId, sessionId);
		for (UISocket socket : activeSockets) {
			try {
				socket.getRemote().sendString(
						mapper.writeValueAsString(new AddTask(sessionId, taskId)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeTask(String taskId) {
		for (UISocket socket : activeSockets) {
			try {
				socket.getRemote().sendString(
						mapper.writeValueAsString(new DeleteTask(currentTasksWithSession.get(taskId), taskId)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		currentTasksWithSession.remove(taskId);
	}

	public void startSession(SimulationStartSimEvent simStartEvent) {
		currentSessions.put(simStartEvent.simulationsessionid, simStartEvent);
		for (UISocket session : activeSockets) {
			try {
				session.getRemote().sendString(
						mapper.writeValueAsString(new SessionStarted(
								simStartEvent.simulationsessionid,
								simStartEvent.simulationsessionname,
								simStartEvent.involvedusers,
								simStartEvent.initialProcessDefinitionKey)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void completeSession(String sessionId, Map<String, Map<ScoreType, Float>> probeScores) {

		Map<LearnPadTask, Integer> detailedScore = manager
				.getDetailedInstanceScore(sessionId, uiid);

		Map<String, String> taskNames = new HashMap<>();
		Map<String, Integer> taskScores = new HashMap<>();

		for (Entry<LearnPadTask, Integer> score : detailedScore.entrySet()) {
			taskNames.put(score.getKey().id, score.getKey().name);
			taskScores.put(score.getKey().id, score.getValue());
		}

		currentSessions.remove(sessionId);
		for (UISocket session : activeSockets) {
			try {
				session.getRemote().sendString(
						mapper.writeValueAsString(new SessionFinished(
								sessionId, taskNames, taskScores, probeScores)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @author Tom Jorquera - Linagora
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
	 * @author Tom Jorquera - Linagora
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

			container.addSocket(this);
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

			container.removeSocket(this);
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}
}
