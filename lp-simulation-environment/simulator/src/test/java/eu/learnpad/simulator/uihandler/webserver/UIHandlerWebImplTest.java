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

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.monitoring.event.impl.SimulationEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;
import eu.learnpad.simulator.uihandler.IFormHandler;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class UIHandlerWebImplTest {

	WebServer webserver;

	// some method call maps
	Map<String, List<String>> taskAddByUI;
	Map<String, List<String>> taskRemoveByUI;
	Map<String, List<String>> sessionCompletionByUI;

	@Before
	public void webserverInit() {
		webserver = mock(WebServer.class);

		taskAddByUI = new HashMap<>();
		taskRemoveByUI = new HashMap<>();
		sessionCompletionByUI = new HashMap<>();

		// Ok this is a little over the top...
		//
		// When the UI Handler add an UI servlet to the webserver, it gets in
		// return a ServletHolder.
		// We use this to "intercept" the UI servlet by making the servlet
		// holder return a mock UIServlet. These UI servlets fill the method
		// call maps and allow us to see what did the UIHandler try to invoke on
		// the UI servlets.
		//
		// We do this because the UI handler create itself the UI servlets, so
		// we cannot mock them directly
		doAnswer(new Answer<ServletHolder>() {

			public ServletHolder answer(InvocationOnMock invocation)
					throws Throwable {

				ServletHolder holder = mock(ServletHolder.class);

				final String userKey = invocation
						.getArgumentAt(1, String.class);

				UIServlet mockServlet = mock(UIServlet.class);

				// memorize add task invocations
				doAnswer(new Answer<Void>() {

					public Void answer(InvocationOnMock invocation)
							throws Throwable {
						if (!taskAddByUI.containsKey(userKey)) {
							taskAddByUI.put(userKey, new ArrayList<String>());
						}
						taskAddByUI.get(userKey).add(
								invocation.getArgumentAt(1, String.class));
						return null;
					}

				}).when(mockServlet).addTask(any(String.class), any(String.class));

				// memorize remove task invocations
				doAnswer(new Answer<Void>() {

					public Void answer(InvocationOnMock invocation)
							throws Throwable {
						if (!taskRemoveByUI.containsKey(userKey)) {
							taskRemoveByUI
									.put(userKey, new ArrayList<String>());
						}
						taskRemoveByUI.get(userKey).add(
								invocation.getArgumentAt(0, String.class));
						return null;
					}

				}).when(mockServlet).removeTask(any(String.class));

				// memorize complete session invocations
				doAnswer(new Answer<Void>() {

					public Void answer(InvocationOnMock invocation)
							throws Throwable {
						if (!sessionCompletionByUI.containsKey(userKey)) {
							sessionCompletionByUI.put(userKey,
									new ArrayList<String>());
						}
						sessionCompletionByUI.get(userKey).add(
								invocation.getArgumentAt(0, String.class));
						return null;
					}

				}).when(mockServlet).completeSession(any(String.class), any(Map.class));

				when(holder.getServletInstance()).thenReturn(mockServlet);

				return holder;
			}

		}).when(webserver)
		.addUIServlet(any(UIServlet.class), any(String.class));
	}

	// users handling

	@Test
	public void testAddUsersAtInit() {

		Collection<String> initUsers = Arrays.asList("init0", "init1", "init2");

		UIHandlerWebImpl uiHandler = new UIHandlerWebImpl(webserver, initUsers,
				mock(IProcessManager.IProcessManagerProvider.class),
				mock(IFormHandler.class));

		// should contain users passed at init
		assertTrue(uiHandler.getUsers().size() == initUsers.size());
		assertTrue(uiHandler.getUsers().containsAll(initUsers));

	}

	@Test
	public void testUsersAddAndRemoval() {
		UIHandlerWebImpl uiHandler = new UIHandlerWebImpl(webserver,
				new HashSet<String>(),
				mock(IProcessManager.IProcessManagerProvider.class),
				mock(IFormHandler.class));

		// should be empty
		assertTrue(uiHandler.getUsers().isEmpty());

		final int nbUsers = 5;

		// add some users
		for (int i = 1; i <= nbUsers; i++) {
			uiHandler.addUser(new UserData("user" + i, "", "", "", "", ""));

			assertTrue(uiHandler.getUsers().size() == i);
			assertTrue(uiHandler.getUsers().contains("user" + i));

			// should have notified webserver
			verify(webserver, times(i)).addUIServlet(
					any(WebSocketServlet.class), anyString());
		}

		// remove some users
		for (int i = nbUsers; i >= 1; i--) {
			uiHandler.removeUser("user" + i);

			assertTrue(uiHandler.getUsers().size() == i - 1);
			assertTrue(!uiHandler.getUsers().contains("user" + i));

			// should have notified webserver
			Mockito.verify(webserver, times(nbUsers - i + 1))
			.removeServletHolder(any(ServletHolder.class));
		}

		// should be empty
		assertTrue(uiHandler.getUsers().isEmpty());

	}

	// sessions and tasks handling

	@Test
	public void signalTasksEventsToUsers() {
		UIHandlerWebImpl uiHandler = new UIHandlerWebImpl(webserver,
				Arrays.asList("user1", "user2", "user3"),
				mock(IProcessManager.IProcessManagerProvider.class),
				mock(IFormHandler.class));

		List<String> tasks = Arrays.asList("task1", "task2", "task3", "task4",
				"task5");

		Map<String, List<String>> tasksToUsers = new HashMap<>();
		tasksToUsers.put("task1", Arrays.asList("user1"));
		tasksToUsers.put("task2", Arrays.asList("user1", "user2"));
		tasksToUsers.put("task3", Arrays.asList("user1", "user2", "user3"));
		tasksToUsers.put("task4", Arrays.asList("user2", "user3"));
		tasksToUsers.put("task5", Arrays.asList("user3"));

		// create some tasks

		// send some tasks
		for (String task : tasks) {
			uiHandler.receiveTaskStartEvent(new TaskStartSimEvent(System
					.currentTimeMillis(), "", tasksToUsers.get(task),
					new LearnPadTask("session1", "session1", task, "", null,
							null, null, null, 0L)));
		}

		// check all user has been notified of its tasks

		assertTrue(taskAddByUI.get("user1").size() == 3);
		assertTrue(taskAddByUI.get("user1").containsAll(
				Arrays.asList("task1", "task2", "task3")));

		assertTrue(taskAddByUI.get("user2").size() == 3);
		assertTrue(taskAddByUI.get("user2").containsAll(
				Arrays.asList("task2", "task3", "task4")));

		assertTrue(taskAddByUI.get("user3").size() == 3);
		assertTrue(taskAddByUI.get("user3").containsAll(
				Arrays.asList("task3", "task4", "task5")));

		// complete tasks
		for (String task : tasks) {
			uiHandler.receiveTaskEndEvent(new TaskEndSimEvent(System
					.currentTimeMillis(), "", tasksToUsers.get(task),
					new LearnPadTask("session1", "session1", task, "", null,
							null, null, null, 0L), "",
					new HashMap<String, Object>(), null));
		}

		// check all user has been notified of its tasks completion

		assertTrue(taskRemoveByUI.get("user1").size() == 3);
		assertTrue(taskRemoveByUI.get("user1").containsAll(
				Arrays.asList("task1", "task2", "task3")));

		assertTrue(taskRemoveByUI.get("user2").size() == 3);
		assertTrue(taskRemoveByUI.get("user2").containsAll(
				Arrays.asList("task2", "task3", "task4")));

		assertTrue(taskRemoveByUI.get("user3").size() == 3);
		assertTrue(taskRemoveByUI.get("user3").containsAll(
				Arrays.asList("task3", "task4", "task5")));
	}

	@Test
	public void signalProcessEndToUsers() {

		UIHandlerWebImpl uiHandler = new UIHandlerWebImpl(webserver,
				Arrays.asList("user1", "user2", "user3"),
				mock(IProcessManager.IProcessManagerProvider.class),
				mock(IFormHandler.class));

		// signal some session completion

		uiHandler.receiveSimulationEndEvent(new SimulationEndSimEvent(System
				.currentTimeMillis(), "session1", Arrays.asList("user1"), null));

		uiHandler.receiveSimulationEndEvent(new SimulationEndSimEvent(System
				.currentTimeMillis(), "session2", Arrays.asList("user1",
						"user2"),
				null));

		uiHandler.receiveSimulationEndEvent(new SimulationEndSimEvent(System
				.currentTimeMillis(), "session3", Arrays.asList("user1",
						"user2", "user3"),
				null));

		uiHandler.receiveSimulationEndEvent(new SimulationEndSimEvent(System
				.currentTimeMillis(), "session4", Arrays.asList("user2",
						"user3"),
				null));

		uiHandler.receiveSimulationEndEvent(new SimulationEndSimEvent(System
				.currentTimeMillis(), "session5", Arrays.asList("user3"), null));

		// check that concerned users (and only them) received session
		// completion notification

		assertTrue(sessionCompletionByUI.get("user1").size() == 3);
		assertTrue(sessionCompletionByUI.get("user1").containsAll(
				Arrays.asList("session1", "session2", "session3")));

		assertTrue(sessionCompletionByUI.get("user2").size() == 3);
		assertTrue(sessionCompletionByUI.get("user2").containsAll(
				Arrays.asList("session2", "session3", "session4")));

		assertTrue(sessionCompletionByUI.get("user3").size() == 3);
		assertTrue(sessionCompletionByUI.get("user3").containsAll(
				Arrays.asList("session3", "session4", "session5")));

	}
}
