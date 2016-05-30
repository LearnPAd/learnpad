/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.cw.internal;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.contrib.websocket.WebSocket;
import org.xwiki.contrib.websocket.WebSocketHandler;
import org.xwiki.model.reference.EntityReferenceSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;

@Component
@Named("recommendation")
public class RecommendationWebsocketServer implements WebSocketHandler {

	private static final long TIMEOUT_MILLISECONDS = 30000;
	
	@Inject
	@Named("compactwiki")
	private EntityReferenceSerializer<String> stringEntityReferenceSerializer;

	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	private UICWBridge bridge;
	
	public static final SocketBox socketBox = new SocketBox();

	public static class SocketBox {
		private Map<WebSocket, WebSocketMetadata> sockets = new ConcurrentHashMap<WebSocket, WebSocketMetadata>();

		WebSocketMetadata get(WebSocket socket) {
			return socketBox.sockets.get(socket);
		}

		List<WebSocketMetadata> byUserid(String userid) {
			List<WebSocket> sockets = new LinkedList<WebSocket>(socketBox.sockets.keySet());
			List<WebSocketMetadata> userSockets = new LinkedList<WebSocketMetadata>();
			for (WebSocket socket : sockets) {
				WebSocketMetadata socketMetadata = socketBox.sockets.get(socket);
				if (userid.equals(socketMetadata.userid)) {
					userSockets.add(socketMetadata);
				}
			}
			return userSockets;
		}

		List<WebSocketMetadata> bySimulationid(String simulationid) {
			List<WebSocket> sockets = new LinkedList<WebSocket>(socketBox.sockets.keySet());
			List<WebSocketMetadata> simulationSockets = new LinkedList<WebSocketMetadata>();
			for (WebSocket socket : sockets) {
				WebSocketMetadata socketMetadata = socketBox.sockets.get(socket);
				if (simulationid.equals(socketMetadata.simulationid)) {
					simulationSockets.add(socketMetadata);
				}
			}
			return simulationSockets;
		}

		void addMetadata(WebSocket socket, WebSocketMetadata socketMetadata) {
			socketBox.sockets.put(socket, socketMetadata);
		}

		void removeSocket(WebSocket socket) {
			socketBox.sockets.remove(socket);
		}

		void removeSocketMetadata(WebSocketMetadata socketMetadata) {
			List<WebSocket> sockets = new LinkedList<WebSocket>(socketBox.sockets.keySet());
			List<WebSocket> simulationSockets = new LinkedList<WebSocket>();
			for (WebSocket socket : sockets) {
				WebSocketMetadata smd = socketBox.sockets.get(socket);
				if (smd.userid.equals(socketMetadata.userid) && smd.simulationid.equals(socketMetadata.simulationid)) {
					simulationSockets.add(socket);
				}
			}
			socketBox.sockets.remove(socketMetadata);
		}
	}

	public static class WebSocketMetadata {
		final WebSocket socket;
		final String userid;
		final String simulationid;
		long timeOfLastInteraction;

		public WebSocketMetadata(WebSocket s, String uid) {
			socket = s;
			userid = uid;
			simulationid = "";
			timeOfLastInteraction = System.currentTimeMillis();
		}
	}
	
	private void addSocketToUser(WebSocket socket) {
		String userid = stringEntityReferenceSerializer.serialize(socket.getUser());
		WebSocketMetadata smd = new WebSocketMetadata(socket, userid);
		socketBox.addMetadata(socket, smd);
	}
	//
	// private class RecommendationWebSocket extends NettyWebSocket {
	//
	// RecommendationWebSocket(DocumentReference user, String path,
	// ChannelHandlerContext ctx, String key,
	// String wiki) {
	// super(user, path, ctx, key, wiki);
	// // TODO Auto-generated constructor stub
	// }
	//
	// }

	private void closeWebSocket(WebSocket socket) {
		socketBox.removeSocket(socket);
	}

	private void cleanWebSockets() {
		long now = System.currentTimeMillis();
		List<WebSocket> sockets = new LinkedList<WebSocket>(socketBox.sockets.keySet());
		for (WebSocket socket : sockets) {
			if (now - socketBox.get(socket).timeOfLastInteraction > TIMEOUT_MILLISECONDS) {
				closeWebSocket(socket);
			}
		}
	}

	public void onWebSocketConnect(WebSocket socket) {

		this.addSocketToUser(socket);

		socket.onDisconnect(new WebSocket.Callback() {
			@Override
			public void call(WebSocket socket) {
				closeWebSocket(socket);
			}
		});

		socket.onMessage(new WebSocket.Callback() {
			@Override
			public void call(WebSocket socket) {
				socketBox.get(socket).timeOfLastInteraction = System.currentTimeMillis();
				cleanWebSockets();

				String message = socket.recv();
				String[] data = message.split(",");
				if (data.length < 3) {
					closeWebSocket(socket);
					return;
				}
				String modelSetId;
				String artifactId;
				String userId;
				try {
					modelSetId = data[0];
					artifactId = data[1];
					userId = data[2];
				} catch (ArrayIndexOutOfBoundsException e) {
					closeWebSocket(socket);
					return;
				}
				Recommendations recommendations;
				try {
					recommendations = bridge.getRecommendations(modelSetId, artifactId, userId);
				} catch (LpRestException e) {
					recommendations = new Recommendations();
				}

				ObjectMapper mapper = new ObjectMapper();
				String msg = "";
				try {
					msg = mapper.writeValueAsString(recommendations);
				} catch (JsonProcessingException e) {
					msg = "";
				}
				socket.send(msg);
			}
		});
	}
}