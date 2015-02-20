/**
 *
 */
package eu.learnpad.simulator;

import java.util.Collection;
import java.util.Map;

/**
 * Define the functions required to manage processes
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IProcessManager {

	static interface IProcessManagerProvider {
		public IProcessManager processManager();
	}

	public static enum TaskSubmissionStatus {
		VALIDATED, REJECTED, ALREADY_COMPLETED, UNKOWN_TASK, UNKOWN_ERROR
	}

	/**
	 *
	 * @param resource
	 *            the path to a valid BPMN 2.0 file
	 * @return a collection containing the ID of the created process definitions
	 */
	public Collection<String> addProjectDefinitions(String resource);

	/**
	 *
	 * @return a collection containing the process definition id of the
	 *         available process definitions
	 */
	public Collection<String> getAvailableProcessDefintion();

	/**
	 *
	 * @param processDefinitionId
	 * @return the name of the process
	 */
	public String getProcessDefinitionName(String processDefinitionId);

	/**
	 *
	 * @param processDefinitionId
	 * @return the description of the process
	 */
	public String getProcessDefinitionDescription(String processDefinitionId);

	/**
	 *
	 * @param processDefinitionid
	 * @return a list containing all the single-user roles potentially involved
	 *         in the process
	 */
	public Collection<String> getProcessDefinitionSingleRoles(
			String processDefinitionId);

	/**
	 *
	 * @param processDefinitionid
	 * @return a list containing all the group roles potentially involved in the
	 *         process
	 */
	public Collection<String> getProcessDefinitionGroupRoles(
			String processDefinitionId);

	/**
	 *
	 * @return a collection containing the currently running process instances
	 */
	public Collection<String> getCurrentProcessInstances();

	/**
	 *
	 * @param projectDefinitionId
	 *            the process definition id of the process to instantiate
	 * @param parameters
	 *            the parameter list for the process
	 * @param users
	 *            the users involved in the process instance
	 * @param router
	 *            a router between process roles and actual users (a role can be
	 *            associated with several users)
	 * @return the ID of the created process instance
	 */
	public String startProjectInstance(String projectDefinitionId,
			Map<String, Object> parameters, Collection<String> users,
			Map<String, Collection<String>> route);

	/**
	 *
	 * @param processInstanceId
	 *            the ID of the process instance
	 * @return a collection of the IDs of the involved users
	 */
	public Collection<String> getProcessInstanceInvolvedUsers(
			String processInstanceId);

	/**
	 * Signal the completion of a given task for a given process, along with the
	 * corresponding proposed data
	 *
	 * @param processId
	 *            the id of the process involved
	 * @param taskId
	 *            the id of the completed task
	 * @param data
	 *            the data corresponding to the task completion
	 * @return the state of the task submission
	 */
	public TaskSubmissionStatus submitTaskCompletion(String processId,
			String taskId, Map<String, Object> data);

}
