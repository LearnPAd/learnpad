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
import java.util.Set;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.simulator.Simulator;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.IChatMsg;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.receive.BaseReceiveMessage;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.receive.Register;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.receive.Submit;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.receive.Subscribe;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.receive.Unsubscribe;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.send.ReceiveErrorNotification;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.send.ReceiveJoinNotification;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.send.ReceiveLeftNotification;
import eu.learnpad.simulator.uihandler.webserver.msg.userchat.send.ReceiveMsg;

/**
 *
 * This class handles chat communications via channels. Users subscribe to
 * specific channels and receive messages and notification from these channels
 * until they unsubscribe.
 * <p>
 * Note: the user will be also sent the msgs and notifs they send themselves, it
 * is the client interface that will choose what to do with it.
 * <p>
 * The same user can connect with multiple clients, via separate sockets. For
 * this reason when a user connects to a socket it must first send a REGISTER
 * msg to indicate its identity. Until the user identifies so, its messages via
 * this socket will be rejected. Once a user registered via a given socket, no
 * other registration can be done.
 * <p>
 * Important note: All of this is really insecure. No authentication of the
 * users is done, it is trivial to spoof a user for a session. This is fine
 * because all this code is a proof of concept, but should never be use as it is
 * for a production project.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ChatServlet extends WebSocketServlet {

	public static final long TIMEOUT = Long.MAX_VALUE;

	private static final long serialVersionUID = 1L;

	private final ObjectMapper mapper = new ObjectMapper();

	// used to get user data
	private final Simulator simulator;

	private final HashMap<String, Set<ChatSocket>> usersSockets = new HashMap<String, Set<ChatSocket>>();

	// we need both:
	// - channelsToUsers allows us to quickly send a messages on a channel to
	// all the subscribers
	// - usersToChannels allows us to quickly send left notifications on all
	// channels when a user leaves
	// This means that when we modify the content of one of them, we should also
	// modify the content of the other to stay consistent
	private final HashMap<String, Set<String>> channelsToUsersSubscriptions = new HashMap<String, Set<String>>();
	private final HashMap<String, Set<String>> usersToChannelsSubscriptions = new HashMap<String, Set<String>>();

	public ChatServlet(Simulator simulator) {
		super();
		this.simulator = simulator;
	}

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.getPolicy().setIdleTimeout(TIMEOUT);
		factory.setCreator(new ChatSocketCreator(this));
	}

	public String name(String user) {
		return simulator.userHandler().getUserData(user).firstName;
	}

	public synchronized void register(String user, ChatSocket socket) {
		if (!usersSockets.containsKey(user)) {
			usersSockets.put(user, new HashSet<ChatSocket>());
		}
		usersSockets.get(user).add(socket);
	}

	public synchronized void unregister(String user, ChatSocket socket) {
		System.out.println("Unregister " + user);
		if (usersSockets.containsKey(user)) {
			usersSockets.get(user).remove(socket);
			if (usersSockets.get(user).isEmpty()) {
				// this was the last user, do some cleanup
				usersSockets.remove(user);
				// unregister for all remaining channels
				synchronized (usersToChannelsSubscriptions) {
					if (usersToChannelsSubscriptions.containsKey(user)
							&& !usersToChannelsSubscriptions.get(user)
									.isEmpty()) {
						for (String channel : usersToChannelsSubscriptions
								.get(user)) {
							unsubscribe(user, channel);
						}
					}
				}
			}
		}
	}

	public synchronized void sendMsg(String channel, String sender,
			String content) {
		synchronized (channelsToUsersSubscriptions) {
			Set<String> recipients = channelsToUsersSubscriptions.get(channel);
			if (recipients != null) {
				for (String recipient : recipients) {
					for (ChatSocket s : usersSockets.get(recipient)) {
						try {
							s.getRemote().sendString(
									mapper.writeValueAsString(new ReceiveMsg(
											channel, name(sender), content)));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public synchronized void sendJoinNotif(String recipient, String channel,
			String originalUser) {
		for (ChatSocket s : usersSockets.get(recipient)) {
			try {
				s.getRemote().sendString(
						mapper.writeValueAsString(new ReceiveJoinNotification(
								channel, name(originalUser))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void sendLeftNotif(String recipient, String channel,
			String originalUser) {
		for (ChatSocket s : usersSockets.get(recipient)) {
			try {
				s.getRemote().sendString(
						mapper.writeValueAsString(new ReceiveLeftNotification(
								channel, name(originalUser))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void subscribe(String user, String channel) {
		synchronized (channelsToUsersSubscriptions) {
			// add channel if it did not already exist
			if (!channelsToUsersSubscriptions.containsKey(channel)) {
				channelsToUsersSubscriptions
				.put(channel, new HashSet<String>());
			}

			// we check if the user is not already present in the channel (using
			// another client for example). If it is the case
			boolean alreadyPresent = channelsToUsersSubscriptions.get(channel)
					.contains(user);

			if (!alreadyPresent) {
				// subscribe user to channel
				channelsToUsersSubscriptions.get(channel).add(user);
				// notify other users
				for (String otherUser : channelsToUsersSubscriptions
						.get(channel)) {
					sendJoinNotif(otherUser, channel, user);
				}
			}
		}

		synchronized (usersToChannelsSubscriptions) {
			if (!usersToChannelsSubscriptions.containsKey(user)) {
				usersToChannelsSubscriptions.put(user, new HashSet<String>());
			}
			usersToChannelsSubscriptions.get(user).add(channel);
		}
	}

	public void unsubscribe(String user, String channel) {
		synchronized (channelsToUsersSubscriptions) {
			// unsubscribe user from channel (if it exists)
			if (channelsToUsersSubscriptions.containsKey(channel)) {
				channelsToUsersSubscriptions.get(channel).remove(user);
				if (channelsToUsersSubscriptions.get(channel).size() == 0) {
					// if it was the last user on the channel, remove the
					// channel
					channelsToUsersSubscriptions.remove(channel);
				} else {
					// notify other users
					for (String otherUser : channelsToUsersSubscriptions
							.get(channel)) {
						sendLeftNotif(otherUser, channel, user);
					}
				}
			}
		}
		synchronized (usersToChannelsSubscriptions) {
			if (usersToChannelsSubscriptions.containsKey(user)) {
				// remove channel from user subs
				usersToChannelsSubscriptions.get(user).remove(channel);
				if (usersToChannelsSubscriptions.get(user).isEmpty()) {
					// cleanup if it was the last channel
					usersToChannelsSubscriptions.remove(user);
				}
			}
		}
	}

	/**
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private static class ChatSocketCreator implements WebSocketCreator {

		private final ChatServlet container;

		/**
		 * @param uiHandler
		 * @param task
		 */
		public ChatSocketCreator(ChatServlet container) {
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
			return new ChatSocket(container);
		}

	}

	/**
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private static class ChatSocket extends WebSocketAdapter {

		private final ChatServlet container;

		private String user = null;

		private final ObjectMapper mapper = new ObjectMapper();

		public ChatSocket(ChatServlet container) {
			super();
			this.container = container;
		}

		@Override
		public void onWebSocketConnect(Session sess) {
			super.onWebSocketConnect(sess);
		}

		@Override
		public void onWebSocketText(String message) {
			super.onWebSocketText(message);
			BaseReceiveMessage m;
			try {
				m = mapper.readValue(message, BaseReceiveMessage.class);

				if (m.getType().equals(IChatMsg.TYPE.REGISTER)) {

					if (user != null) {
						// user already registered
						this.getRemote()
						.sendString(
								mapper.writeValueAsString(new ReceiveErrorNotification(
										"already registered")));
					} else {
						Register msgreg = mapper.readValue(message,
								Register.class);
						this.user = msgreg.user;
						// register user socket
						container.register(user, this);

					}
				} else if (user == null) {
					// user not registered, cannot handle message
					this.getRemote()
					.sendString(
							mapper.writeValueAsString(new ReceiveErrorNotification(
									"you must first register as a user")));
				} else {
					switch (m.getType()) {
					case SUBSCRIBE:
						Subscribe msgsubscr = mapper.readValue(message,
								Subscribe.class);
						;
						container.subscribe(user, msgsubscr.channel);
						break;
					case UNSUBSCRIBE:
						Unsubscribe msgunsub = mapper.readValue(message,
								Unsubscribe.class);
						;
						container.unsubscribe(user, msgunsub.channel);
						break;
					case SUBMIT:
						Submit msgsubm = mapper
						.readValue(message, Submit.class);
						container.sendMsg(msgsubm.channel, user, msgsubm.msg);
						break;
					default:
						// Unexpected message type
						System.err
						.println("ChatServlet: received unexpected message of type "
								+ m.getType());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onWebSocketClose(int statusCode, String reason) {
			super.onWebSocketClose(statusCode, reason);
			if (user != null) {
				// only required if the user has registered
				container.unregister(user, this);
			}
		}

		@Override
		public void onWebSocketError(Throwable cause) {
			super.onWebSocketError(cause);
			cause.printStackTrace(System.err);
		}
	}

}
