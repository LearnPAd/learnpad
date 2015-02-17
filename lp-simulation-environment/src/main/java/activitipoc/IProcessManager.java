package activitipoc;

import java.util.Collection;
import java.util.Map;

/**
 * This interface defines the functionalities of process management.
 *
 * @author jorquera
 *
 */
public interface IProcessManager {

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
	 * @param projectDefinitionId
	 *            the process definition id of the process to instantiate
	 * @param parameters
	 *            the parameter list for the process
	 * @param users
	 *            the users involved in the process instance
	 * @param router
	 *            a router between process roles and actual users (a role can be
	 *            associated with several users)
	 * @param uiHandler
	 *            the UI handler
	 * @return the ID of the created process instance
	 */
	public String startProjectInstance(String projectDefinitionId,
			Map<String, Object> parameters, Collection<String> users,
			Map<String, Collection<String>> route, IUIHandler uiHandler);

	/**
	 *
	 * @return a collection containing the currently running process instances
	 */
	public Collection<String> getCurrentProcessInstances();

}
