/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver;

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

import eu.learnpad.simulator.processmanager.IProcessManager;
import eu.learnpad.simulator.uihandler.IFormHandler;
import eu.learnpad.simulator.uihandler.IUIHandler;
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

	private final IProcessManager processManager;
	private final IUIHandler uiHandler;
	private final IFormHandler formHandler;

	/**
	 * @param processManager
	 * @param uiHandler
	 */
	public UIProcessServlet(IProcessManager processManager,
			IUIHandler uiHandler, IFormHandler formHandler) {
		super();
		this.processManager = processManager;
		this.uiHandler = uiHandler;
		this.formHandler = formHandler;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new UISocketCreator(processManager, uiHandler,
				formHandler));
	}

	/**
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private static class UISocketCreator implements WebSocketCreator {

		private final IProcessManager processManager;
		private final IUIHandler uiHandler;
		private final IFormHandler formHandler;

		/**
		 * @param processManager
		 * @param uiHandler
		 */
		public UISocketCreator(IProcessManager processManager,
				IUIHandler uiHandler, IFormHandler formHandler) {
			super();
			this.processManager = processManager;
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
			return new UIProcessSocket(processManager, uiHandler, formHandler);
		}

	}

	/**
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private static class UIProcessSocket extends WebSocketAdapter {

		private final IProcessManager processManager;
		private final IUIHandler uiHandler;
		private final IFormHandler formHandler;

		private final ObjectMapper mapper = new ObjectMapper();

		/**
		 * @param processManager
		 * @param uiHandler
		 */
		public UIProcessSocket(IProcessManager processManager,
				IUIHandler uiHandler, IFormHandler formHandler) {
			super();
			this.processManager = processManager;
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
								processManager, formHandler, users)));
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

					processManager.startProjectInstance(projectDefinitionId,
							parameters, involvedUsers, router, uiHandler);

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
