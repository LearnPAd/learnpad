/**
 *
 */
package activitipoc;

import java.util.Collection;

import activitipoc.activiti.ActivitiProcessDispatcher;

/**
 * Defines the functions required to handle UI
 *
 * @author jorquera
 *
 */
public interface IUIHandler {

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
	 * Register a new process
	 *
	 * @param process
	 *            the process id
	 * @param users
	 *            the users involved in the process
	 * @param dispatcher
	 *            the process dispatcher
	 */
	public void addProcess(String process, Collection<String> users,
			ActivitiProcessDispatcher dispatcher);

	/**
	 * Send a task to a set of users
	 *
	 * @param taskId
	 *            the id of the task
	 * @param taskDescr
	 *            the description of the task
	 * @param users
	 *            the users concerned by the task
	 */
	public void sendTask(String taskId, String taskDescr,
			Collection<String> users);

	/**
	 * Signal the end of a process to a group of users
	 *
	 * @param processId
	 * @param users
	 */
	public void signalProcessEnd(String processId, Collection<String> users);

}
