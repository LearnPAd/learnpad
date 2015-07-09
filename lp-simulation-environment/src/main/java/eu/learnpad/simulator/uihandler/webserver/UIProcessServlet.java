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
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.IUserHandler;
import eu.learnpad.simulator.uihandler.IFormHandler;
import eu.learnpad.simulator.uihandler.webserver.msg.process.receive.BaseReceiveMessage;
import eu.learnpad.simulator.uihandler.webserver.msg.process.receive.InstanciateProcess;
import eu.learnpad.simulator.uihandler.webserver.msg.process.send.ProcessData;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class UIProcessServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	private final IProcessManager userEventReceiver;
	private final IUserHandler uiHandler;
	private final IFormHandler formHandler;

	/**
	 * @param processManager
	 * @param uiHandler
	 */
	public UIProcessServlet(IProcessManager userEventReceiver,
			IUserHandler uiHandler, IFormHandler formHandler) {
		super();
		this.userEventReceiver = userEventReceiver;
		this.uiHandler = uiHandler;
		this.formHandler = formHandler;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new UISocketCreator(userEventReceiver, uiHandler,
				formHandler));
	}

	/**
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private static class UISocketCreator implements WebSocketCreator {

		private final IProcessManager userEventReceiver;
		private final IUserHandler uiHandler;
		private final IFormHandler formHandler;

		/**
		 * @param processManager
		 * @param uiHandler
		 */
		public UISocketCreator(IProcessManager userEventReceiver,
				IUserHandler uiHandler, IFormHandler formHandler) {
			super();
			this.userEventReceiver = userEventReceiver;
			this.uiHandler = uiHandler;
			this.formHandler = formHandler;
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
			return new UIProcessSocket(userEventReceiver, uiHandler,
					formHandler);
		}

	}

	/**
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private static class UIProcessSocket extends WebSocketAdapter {

		private final IProcessManager userEventReceiver;
		private final IUserHandler uiHandler;
		private final IFormHandler formHandler;

		private final ObjectMapper mapper = new ObjectMapper();

		/**
		 * @param processManager
		 * @param uiHandler
		 */
		public UIProcessSocket(IProcessManager userEventReceiver,
				IUserHandler uiHandler, IFormHandler formHandler) {
			super();
			this.userEventReceiver = userEventReceiver;
			this.uiHandler = uiHandler;
			this.formHandler = formHandler;
		}

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
			System.out.println("UI Process Socket connected: " + sess);

			Collection<String> users = uiHandler.getUsers();

			try {
				this.getRemote().sendString(
						mapper.writeValueAsString(new ProcessData(
								userEventReceiver, formHandler, users)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);

			try {

				BaseReceiveMessage m = mapper.readValue(message,
						BaseReceiveMessage.class);

				switch (m.getType()) {

				case INSTANCIATE:
					InstanciateProcess msg = mapper.readValue(message,
							InstanciateProcess.class);

					String projectDefinitionId = msg.id;
					IFormHandler.FormResult result = formHandler
							.parseResult(msg.parameters);

					Map<String, Object> parameters = result.getProperties();

					Set<String> involvedUsers = new HashSet<String>();
					Map<String, Collection<String>> router = result
							.getRolesToUsersMapping();

					for (Collection<String> users : router.values()) {
						// add these users to list of concerned users
						involvedUsers.addAll(users);
					}

					userEventReceiver.startProjectInstance(projectDefinitionId,
							parameters, involvedUsers, router);

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
			System.out.println("UI Process Socket closed: [" + statusCode
					+ "] " + reason);
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}
}
