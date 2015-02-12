/**
 *
 */
package activitipoc.uihandler.webserver;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * @author jorquera
 *
 */
public class DummyChatServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new ChatSocketCreator());
	}

	/**
	 * @author jorquera
	 *
	 */
	private static class ChatSocketCreator implements WebSocketCreator {

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
			return new ChatSocket();
		}

	}

	/**
	 * @author jorquera
	 *
	 */
	private static class ChatSocket extends WebSocketAdapter {

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
			try {
				this.getRemote().sendString("Hello! I am an expert");
				this.getRemote().sendString(
						"Please ask me a question regarding your problem");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);
			try {
				this.getRemote().sendString(answer(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onWebSocketClose(int statusCode, String reason) {
			super.onWebSocketClose(statusCode, reason);
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}

	private static String answer(String msg) {
		List<String> answers = Arrays.asList("Use the Force",
				"Have you tried turning it off and on again?",
				"You know my methods. Apply them", "42");

		if (msg.endsWith("?")) {
			int r = new Random().nextInt(answers.size());
			return answers.get(r);
		} else {
			return "No time to chit-chat! Please ask me a question regarding your problem";
		}
	}
}
