/**
 *
 */
package activitipoc.uihandler.webserver;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.impl.util.json.JSONObject;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import activitipoc.IFormHandler;
import activitipoc.IProcessManager;
import activitipoc.IUIHandler;

/**
 * @author jorquera
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
	 * @author jorquera
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
	 * @author jorquera
	 *
	 */
	private static class UIProcessSocket extends WebSocketAdapter {

		private final IProcessManager processManager;
		private final IUIHandler uiHandler;
		private final IFormHandler formHandler;

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

			// set message type
			String msg = "{ \"type\" : \"DATA\",";

			// list processes
			// (schema {
			// processes: [
			// {
			// id: <>, name:<>, description: <>, form : {<>}
			// },...]
			// })
			msg += "\"processes\": [";

			for (String processDefId : processManager
					.getAvailableProcessDefintion()) {
				msg += "{ \"id\": \""
						+ processDefId
						+ "\", \"name\": \""
						+ processManager.getProcessDefinitionName(processDefId)
						+ "\", \"description\": \""
						+ processManager
						.getProcessDefinitionDescription(processDefId)
						+ "\", \"form\": "
						+ formHandler
								.createStartingFormString(
										processDefId,
										processManager
												.getProcessDefinitionSingleRoles(processDefId),
										processManager
												.getProcessDefinitionGroupRoles(processDefId),
										users);

				msg += " },";
			}

			// remove last ,
			msg = msg.substring(0, msg.length() - 1);
			msg += " ],";

			// list users
			// (schema {users: [ id, ...]})
			msg += "\"users\": [ ";
			for (String user : uiHandler.getUsers()) {
				msg += "\"" + user + "\",";
			}
			// remove last ,"
			msg = msg.substring(0, msg.length() - 1);

			msg += " ]}";

			try {
				this.getRemote().sendString(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);

			JSONObject msg = new JSONObject(message);

			// expect a JSON message in the following format:
			// {
			// id: <processDef id>,
			// parameters: "[...]",
			// }

			String projectDefinitionId = msg.getString("id");

			IFormHandler.FormResult result = formHandler.parseResult(msg
					.getString("parameters"));

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
