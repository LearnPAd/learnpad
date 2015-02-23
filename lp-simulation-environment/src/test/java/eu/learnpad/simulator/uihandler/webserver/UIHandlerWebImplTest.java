/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver;

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

import eu.learnpad.simulator.IProcessManager;
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
	Map<String, List<String>> processCompletionByUI;

	@Before
	public void webserverInit() {
		webserver = mock(WebServer.class);

		taskAddByUI = new HashMap<String, List<String>>();
		taskRemoveByUI = new HashMap<String, List<String>>();
		processCompletionByUI = new HashMap<String, List<String>>();

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
								invocation.getArgumentAt(0, String.class));
						return null;
					}

				}).when(mockServlet).addTask(any(String.class));

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

				// memorize complete process invocations
				doAnswer(new Answer<Void>() {

					public Void answer(InvocationOnMock invocation)
							throws Throwable {
						if (!processCompletionByUI.containsKey(userKey)) {
							processCompletionByUI.put(userKey,
									new ArrayList<String>());
						}
						processCompletionByUI.get(userKey).add(
								invocation.getArgumentAt(0, String.class));
						return null;
					}

				}).when(mockServlet).completeProcess(any(String.class));

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
			uiHandler.addUser("user" + i);

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

	// processes and tasks handling

	@Test
	public void signalTasksEventsToUsers() {
		UIHandlerWebImpl uiHandler = new UIHandlerWebImpl(webserver,
				Arrays.asList("user1", "user2", "user3"),
				mock(IProcessManager.IProcessManagerProvider.class),
				mock(IFormHandler.class));

		// send some tasks

		uiHandler.sendTask("process1", "task1", "", Arrays.asList("user1"));

		uiHandler.sendTask("process1", "task2", "",
				Arrays.asList("user1", "user2"));

		uiHandler.sendTask("process1", "task3", "",
				Arrays.asList("user1", "user2", "user3"));

		uiHandler.sendTask("process1", "task4", "",
				Arrays.asList("user2", "user3"));

		uiHandler.sendTask("process1", "task5", "", Arrays.asList("user3"));

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
		uiHandler.completeTask("process1", "task1", "");
		uiHandler.completeTask("process1", "task2", "");
		uiHandler.completeTask("process1", "task3", "");
		uiHandler.completeTask("process1", "task4", "");
		uiHandler.completeTask("process1", "task5", "");

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

		// signal some process completion

		uiHandler.signalProcessEnd("process1", Arrays.asList("user1"));

		uiHandler.signalProcessEnd("process2", Arrays.asList("user1", "user2"));

		uiHandler.signalProcessEnd("process3",
				Arrays.asList("user1", "user2", "user3"));

		uiHandler.signalProcessEnd("process4", Arrays.asList("user2", "user3"));

		uiHandler.signalProcessEnd("process5", Arrays.asList("user3"));

		// check that concerned users (and only them) received process
		// completion notification

		assertTrue(processCompletionByUI.get("user1").size() == 3);
		assertTrue(processCompletionByUI.get("user1").containsAll(
				Arrays.asList("process1", "process2", "process3")));

		assertTrue(processCompletionByUI.get("user2").size() == 3);
		assertTrue(processCompletionByUI.get("user2").containsAll(
				Arrays.asList("process2", "process3", "process4")));

		assertTrue(processCompletionByUI.get("user3").size() == 3);
		assertTrue(processCompletionByUI.get("user3").containsAll(
				Arrays.asList("process3", "process4", "process5")));

	}
}
