/**
 *
 */
package eu.learnpad.simulator;

import java.util.Collection;

/**
 *
 * Define the interface required to subscribe to process events
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IProcessEventReceiver {

	static interface IProcessEventReceiverProvider {
		public IProcessEventReceiver processEventReceiver();
	}

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
	 * Signal the end of a process instance to a list of users
	 *
	 * @param processId
	 *            the id of the process
	 *
	 * @param users
	 *            the users concerned by the process
	 */
	public void signalProcessEnd(String processId, Collection<String> users);
}
