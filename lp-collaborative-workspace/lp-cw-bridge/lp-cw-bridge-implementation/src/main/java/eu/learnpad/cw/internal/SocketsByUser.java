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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.contrib.websocket.WebSocket;

import eu.learnpad.cw.ObjectsByUser;

@Component
@Singleton
@Named("socket")
public class SocketsByUser implements ObjectsByUser<List<WebSocket>> {

	private volatile Map<String, List<WebSocket>> socketsByUser = new HashMap<String, List<WebSocket>>();

	@Override
	public synchronized List<WebSocket> get(String user) {
		if (!this.socketsByUser.containsKey(user)) {
			this.socketsByUser.put(user, new ArrayList<WebSocket>());
		}
		return this.socketsByUser.get(user);
	}

	@Override
	public synchronized void put(String user, List<WebSocket> listSockets) {
		if (!this.socketsByUser.containsKey(user)) {
			this.socketsByUser.put(user, new ArrayList<WebSocket>());
		}
		this.socketsByUser.put(user, listSockets);
	}
}