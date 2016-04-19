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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.xwiki.component.annotation.Component;
import org.xwiki.contrib.websocket.WebSocket;
import org.xwiki.contrib.websocket.WebSocketHandler;
import org.xwiki.model.reference.EntityReferenceSerializer;
import org.xwiki.rest.XWikiRestComponent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.core.impl.cw.XwikiCoreFacadeRestResource;
import eu.learnpad.cw.ObjectsByUser;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;

@Component
@Named("recommendation")
public class RecommendationWebsocketServer implements WebSocketHandler {

	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	private XWikiRestComponent cwBridge;

	@Inject
	@Named("compactwiki")
	private EntityReferenceSerializer<String> stringEntityReferenceSerializer;

	@Inject
	@Named("socket")
	private ObjectsByUser<List<WebSocket>> socketsByUser;

	private void addSocketToUser(String user, WebSocket sock) {
		this.socketsByUser.get(user).add(sock);
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

	public void onWebSocketConnect(WebSocket sock) {
		String user = stringEntityReferenceSerializer.serialize(sock.getUser());

		this.addSocketToUser(user, sock);

		sock.onMessage(new WebSocket.Callback() {
			public void call(WebSocket sock) {
				XwikiCoreFacadeRestResource corefacade = new XwikiCoreFacadeRestResource();
				String message = sock.recv();
				String[] data = message.split(",");
				String modelSetId = data[0];
				String artifactId = data[1];
				String userId = data[2];
				Recommendations recommendations;
				try {
					recommendations = corefacade.getRecommendations(modelSetId, artifactId, userId);
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
				sock.send(msg);
			}
		});
	}
}
