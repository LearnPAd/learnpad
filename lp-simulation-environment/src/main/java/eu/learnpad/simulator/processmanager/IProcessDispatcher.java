/**
 *
 */
package eu.learnpad.simulator.processmanager;

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

import java.util.Map;

import eu.learnpad.simulator.IProcessManager.TaskSubmissionStatus;

/**
 * This interface indicate the functionalities which must be exposed by a
 * process dispatcher. The role of a process dispatcher is, in the context of a
 * specific process, to signal to the UI which tasks must be sent to which
 * users, or the termination of the process itself.
 *
 * This interface allows the UI to informs the process dispatcher of tasks
 * completion.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IProcessDispatcher {

	/**
	 * Signal the completion of a given task, along with the corresponding
	 * proposed data
	 *
	 * @param taskId
	 *            the id of the completed task
	 * @param data
	 *            the data corresponding to the task completion
	 * @return the state of the task submission
	 */
	public TaskSubmissionStatus submitTaskCompletion(String taskId,
			Map<String, Object> data);
}
