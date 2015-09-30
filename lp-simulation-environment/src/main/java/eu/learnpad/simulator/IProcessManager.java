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

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskGameInfos;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;

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
	 * @param processDefinitionKey
	 * @return the process definition Id associated to the definition key
	 */
	public String getProcessDefIdFromDefKey(String processDefinitionKey);

	/**
	 *
	 * @param resource
	 *            the path to a valid BPMN 2.0 file
	 * @return a collection containing the ID of the created process definitions
	 */
	public Collection<String> addProjectDefinitions(String resource);

	/**
	 *
	 * @param resource
	 *            the input stream corresponding to a valid BPMN 2.0 file
	 * @return a collection containing the ID of the created process definitions
	 */
	public Collection<String> addProjectDefinitions(InputStream resource);

	/**
	 *
	 * @return a collection containing the process definition id of the
	 *         available process definitions
	 */
	public Collection<String> getAvailableProcessDefintion();

	/**
	 * This method returns the key associated with a process definition. The key
	 * correspond to the id of the process declared into the bpmn file. However
	 * it may not be unique (in case of versioning for example).
	 *
	 * @param processDefinitionId
	 * @return the key associated with this process definition
	 */
	public String getProcessDefinitionKey(String processDefinitionId);

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
	 * @param processId
	 * @return the process definition id associated with the process
	 */
	public String getProcessDefinitionId(String processId);

	/**
	 *
	 * @param projectDefinitionKey
	 *            the process definition key of the process to instantiate
	 * @param parameters
	 *            the parameter list for the process
	 * @param users
	 *            the users involved in the process instance
	 * @param router
	 *            a router between process roles and actual users (a role can be
	 *            associated with several users)
	 * @return the ID of the created process instance
	 */
	public String startProjectInstance(String projectDefinitionKey,
			Map<String, Object> parameters, Collection<String> users,
			Map<String, Collection<String>> route);

	/**
	 *
	 * @param processInstanceId
	 *            the ID of the process instance
	 * @return the data associated with this process instance
	 */
	public ProcessInstanceData getProcessInstanceInfos(String processInstanceId);

	/**
	 * Signal the completion of a given task for a given process, along with the
	 * corresponding proposed data
	 *
	 * @param task
	 *            the completed task
	 * @param userId
	 *            the id of the user that submit the task completion
	 * @param data
	 *            the data corresponding to the task completion
	 * @return the result of the task submission
	 */
	public LearnPadTaskSubmissionResult submitTaskCompletion(LearnPadTask task,
			String userId, Map<String, Object> data);

	/**
	 * Signal the completion of a given process
	 *
	 * @param processId
	 *            the completed process
	 */
	public void signalProcessCompletion(String processId);

	/**
	 *
	 * @param processInstanceId
	 * @param userId
	 * @return the score of a user for a given process instance, or null if the
	 *         user has no associated score in that process instance yet.
	 */
	public Integer getInstanceScore(String processInstanceId, String userId);

	/**
	 * @param processDefinitionId
	 *            the definition ID of the process for which we want the
	 *            diagram.
	 * @return the input stream of the image containing the diagram of the given
	 *         process, or null if no diagram is available
	 */
	public InputStream getProcessDiagram(String processDefinitionId);

	/**
	 *
	 * @param processInstanceId
	 *            the involved process instance
	 * @param taskId
	 *            the task we want to highlight
	 * @return the input stream of the image containing the diagram of the given
	 *         process instance with the specified task highlighted, or null if
	 *         no diagram is available
	 */
	public InputStream getCurrentTaskDiagram(String processInstanceId,
			String taskId);

	/**
	 *
	 * @param task
	 * @param userId
	 * @return the game-related information associated with the given task for
	 *         the given user
	 */
	public LearnPadTaskGameInfos getGameInfos(LearnPadTask task, String userId);

}
