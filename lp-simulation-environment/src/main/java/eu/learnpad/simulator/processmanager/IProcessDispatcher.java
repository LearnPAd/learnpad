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

import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskGameInfos;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;

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
	 *
	 * @return the process instance data info of the associated process instance
	 */
	public ProcessInstanceData getProcessInstanceInfos();

	/**
	 * Signal the completion of a given task, along with the corresponding
	 * proposed data
	 *
	 * Note that implementations of this method should take care of enforcing
	 * concurrent access to handle cases when users (possibly accessing the
	 * dispatcher from different threads) try to complete the same task
	 * simultaneously.
	 *
	 * @param task
	 *            the completed task
	 * @param users
	 *            the users involved in the process instance
	 * @param data
	 *            the data corresponding to the task completion
	 * @return the result of the task submission
	 */
	public LearnPadTaskSubmissionResult submitTaskCompletion(LearnPadTask task,
			String userId, Map<String, Object> data);

	/**
	 *
	 * @param task
	 * @return the score associated with the given user in the handled process,
	 *         or null if no score have been assigned yet.
	 */
	public Integer getInstanceScore(String userId);

	/**
	 *
	 * @param task
	 * @param userId
	 * @return the game-related information associated with the given task for
	 *         the given user
	 */
	public LearnPadTaskGameInfos getGameInfos(LearnPadTask task, String userId);
}
