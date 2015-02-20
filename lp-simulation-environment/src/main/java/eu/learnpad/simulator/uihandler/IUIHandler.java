/**
 *
 */
package eu.learnpad.simulator.uihandler;

import java.util.Collection;

import eu.learnpad.simulator.processmanager.IProcessDispatcher;

/**
 * Defines the functions required to handle UI
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IUIHandler {

	static interface IUIHandlerProvider {
		public IUIHandler uiHandler();
	}

	/**
	 * Add a new user to the user list
	 *
	 * @param userId
	 *            the id of the new user
	 */
	public void addUser(String userId);

	/**
	 * Remove a user from the user list
	 *
	 * @param userId
	 */
	public void removeUser(String userId);

	/**
	 *
	 * @return a collection containing all the user IDs
	 */
	public Collection<String> getUsers();

	/**
	 * Register a new process
	 *
	 * @param process
	 *            the process id
	 * @param users
	 *            the users concerned by the process
	 * @param dispatcher
	 *            the process dispatcher
	 */
	public void addProcess(String process, Collection<String> users,
			IProcessDispatcher dispatcher);

	/**
	 * Send a task to a set of users
	 *
	 * @param processId
	 *            the id of the process
	 * @param taskId
	 *            the id of the task
	 * @param taskDescr
	 *            the description of the task
	 * @param users
	 *            the users concerned by the task
	 */
	public void sendTask(String processId, String taskId, String taskDescr,
			Collection<String> users);

	/**
	 * Signal the end of a process to concerned users
	 *
	 * @param processId
	 */
	public void signalProcessEnd(String processId);

}
