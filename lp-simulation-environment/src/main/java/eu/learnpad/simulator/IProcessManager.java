/**
 *
 */
package eu.learnpad.simulator;

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
