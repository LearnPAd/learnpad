/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.jetty.servlet.ServletHolder;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.IUserHandler;
import eu.learnpad.simulator.uihandler.IFormHandler;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class UIHandlerWebImpl implements IUserHandler, IProcessEventReceiver {

	private final IProcessManager.IProcessManagerProvider userEventReceiverProvider;
	private final IFormHandler formHandler;
	private final WebServer webserver;
	private final Map<String, ServletHolder> usersMap = Collections
			.synchronizedMap(new HashMap<String, ServletHolder>());
	private final Map<String, Collection<String>> tasksToUsers = Collections
			.synchronizedMap(new HashMap<String, Collection<String>>());
	private final Map<String, ServletHolder> tasksMap = Collections
			.synchronizedMap(new HashMap<String, ServletHolder>());

	/**
	 * @param webserver
	 * @param users
	 * @param taskService
	 * @throws Exception
	 */
	public UIHandlerWebImpl(WebServer webserver, Collection<String> users,
			IProcessManager.IProcessManagerProvider userEventReceiverProvider,
			IFormHandler formHandler) {
		super();
		this.userEventReceiverProvider = userEventReceiverProvider;
		this.formHandler = formHandler;

		// launch task webserver
		this.webserver = webserver;

		// create process ui servlet
		webserver.addProcessUIServlet(new UIProcessServlet(
				userEventReceiverProvider.processManager(), this, formHandler),
				"process");

		// instanciate users UI
		for (String user : users) {
			usersMap.put(user,
					this.webserver.addUIServlet(new UIServlet(user), user));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#addUser(java.lang.String)
	 */
	public void addUser(String userId) {
		usersMap.put(userId,
				webserver.addUIServlet(new UIServlet(userId), userId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#removeUser(java.lang.String)
	 */
	public void removeUser(String userId) {
		webserver.removeServletHolder(usersMap.get(userId));
		usersMap.remove(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#getUsers()
	 */
	public Collection<String> getUsers() {
		return new HashSet<String>(usersMap.keySet());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#sendTask(java.lang.String, java.util.Set)
	 */
	public void sendTask(String processId, String taskId, String taskDescr,
			Collection<String> users) {

		tasksMap.put(taskId, webserver.addTaskServlet(new TaskServlet(this,
				userEventReceiverProvider.processManager(), processId, taskId,
				taskDescr, formHandler), taskId));
		tasksToUsers.put(taskId, users);

		// note: it is important to signal new tasks to users *after* having
		// created the corresponding servlet otherwise the user may try to
		// connect to the task before it is available
		for (String user : users) {
			((UIServlet) usersMap.get(user).getServletInstance())
			.addTask(taskId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IUIHandler#signalProcessEnd(java.lang.String,
	 * java.util.Set)
	 */
	public void signalProcessEnd(String processId, Collection<String> users) {
		for (String userId : users) {
			((UIServlet) usersMap.get(userId).getServletInstance())
					.completeProcess(processId);
		}

	}

	public void completeTask(String processId, String taskId, String data) {
		for (String userId : tasksToUsers.get(taskId)) {
			((UIServlet) usersMap.get(userId).getServletInstance())
					.removeTask(taskId);
		}

		// remove task ui from webserver
		webserver.removeServletHolder(tasksMap.get(taskId));

		tasksToUsers.remove(taskId);
		tasksMap.remove(taskId);
	}

	/**
	 * stop the webserver
	 */
	public void stop() {
		webserver.stop();
	}

}
