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

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskGameInfos;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;
import eu.learnpad.simulator.monitoring.event.impl.ProcessEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SessionScoreUpdateSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskFailedSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.processmanager.gamification.TaskScorer;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public abstract class AbstractProcessDispatcher implements IProcessDispatcher {

	private final ProcessInstanceData processInstanceData;
	protected final String processId;
	protected final Collection<String> involvedUsers;
	protected final IProcessManager manager;
	private final IProcessEventReceiver processEventReceiver;
	private final ITaskRouter router;
	private final ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator;
	protected final String simulationSessionId;

	private final Map<String, Integer> usersScores = new HashMap<String, Integer>();

	private final Map<String, TaskScorer> taskScorers = new HashMap<String, TaskScorer>();

	public AbstractProcessDispatcher(
			ProcessInstanceData processInstanceData,
			IProcessManager manager,
			IProcessEventReceiver processEventReceiver,
			ITaskRouter router,
			ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator) {
		super();
		this.processInstanceData = processInstanceData;
		this.processId = processInstanceData.getProcessartifactid();
		this.involvedUsers = processInstanceData.getUsers();
		this.manager = manager;
		this.processEventReceiver = processEventReceiver;
		this.router = router;
		this.taskValidator = taskValidator;

		this.simulationSessionId = (String) processInstanceData.getParameters()
				.get(ActivitiProcessManager.SIMULATION_ID_KEY);

		for (String user : involvedUsers) {
			usersScores.put(user, 0);
		}

		System.out.println("Created dispatcher for process " + processId);
	}

	public ProcessInstanceData getProcessInstanceInfos() {
		return processInstanceData;
	}

	/**
	 * Start the ProcessDispatcher
	 *
	 * We need to check initial waiting tasks at the beginning. However fetching
	 * new tasks is implementation dependent, so it cannot be done in the
	 * abstract constructor since all the required attributes of the
	 * implementation may not have been implemented yet.
	 *
	 * Also, as soon as the dispatcher is started, it will begin to send tasks
	 * to be completed. So the manager needs to be ready to receive results and
	 * route them to the dispatcher. So the manager need some time after
	 * creating the dispatcher to register it and all...
	 *
	 * The start method is provided to launch the dispatcher.
	 */
	public void start() {

		// process initial waiting tasks
		final Collection<LearnPadTask> newTasks = fetchNewTasks();

		for (LearnPadTask newTask : newTasks) {
			processNewTask(newTask);
		}
	}

	@Override
	// synchronized because several users can try to submit results for the same
	// task simultaneously
	public synchronized LearnPadTaskSubmissionResult submitTaskCompletion(
			LearnPadTask task, String userId, Map<String, Object> data) {

		if (isTaskAlreadyCompleted(task.id)) {
			return LearnPadTaskSubmissionResult.alreadyCompleted();
		} else {
			if (!(doesTaskExist(task.id))) {
				return LearnPadTaskSubmissionResult.unknownTask();
			} else {
				if (!taskValidator.taskResultIsValid(task.id,
						getTaskInputs(task.id), data)) {

					// add attempt to user failed count
					taskScorers.get(task.id).addUserAttemptFail(userId);

					LearnPadTaskSubmissionResult res = LearnPadTaskSubmissionResult
							.rejected();

					// send failure notification
					processEventReceiver
					.receiveTaskFailedEvent(new TaskFailedSimEvent(
							System.currentTimeMillis(),
							simulationSessionId, involvedUsers, task,
									userId, data, res));

					// task result is invalid and must be resubmitted
					return res;
				} else {

					int taskScore = 0;

					// Can be false if a robot is submitting the result
					// in this case there is no point in calculating a user
					// score
					if (usersScores.containsKey(userId)) {
						// TODO calculate actual task score
						taskScore = (int) (taskScorers.get(task.id)
								.userComplete(userId) * 100);

						usersScores.put(userId, usersScores.get(userId)
								+ taskScore);

						LearnPadTaskSubmissionResult res = LearnPadTaskSubmissionResult
								.validated(usersScores.get(userId), taskScore);

						processEventReceiver
								.receiveSessionScoreUpdateEvent(new SessionScoreUpdateSimEvent(
										System.currentTimeMillis(),
										simulationSessionId, involvedUsers,
										processId, userId, res.sessionScore));

						return res;
					} else {
						// probably a robot, no score needed
						return LearnPadTaskSubmissionResult.validated(0, 0);
					}
				}
			}
		}
	}

	@Override
	public Integer getInstanceScore(String userId) {
		return usersScores.get(userId);
	}

	public LearnPadTaskGameInfos getGameInfos(LearnPadTask task, String userId) {
		// TODO: set correct time parameters
		return new LearnPadTaskGameInfos(30000, taskScorers.get(task.id)
				.getNbAttemps(userId));
	}

	/**
	 * this method is called when the process is completed. It should be used
	 * for clean-up or signaling process completion.
	 */
	protected void completeProcess() {
		// signal process end to users
		processEventReceiver.receiveProcessEndEvent(new ProcessEndSimEvent(
				System.currentTimeMillis(), simulationSessionId, involvedUsers,
				processInstanceData));

		// remove itself from the process manager
		manager.signalProcessCompletion(processId);

		System.out.println("Process " + processId + " finished");
	}

	/**
	 * This method is called to process new tasks.
	 *
	 * @param task
	 *            a new task
	 */
	protected void processNewTask(final LearnPadTask task) {
		// TODO: correct init parameters
		taskScorers.put(task.id, new TaskScorer(new Date(), 30000));

		processEventReceiver.receiveTaskStartEvent(new TaskStartSimEvent(System
				.currentTimeMillis(), simulationSessionId, router
				.route(task.id), task));
	}

	/**
	 *
	 * @return a collection of new tasks to be processed
	 */
	protected abstract Collection<LearnPadTask> fetchNewTasks();

	public void completeTask(LearnPadTask task, Map<String, Object> data,
			String completingUser, LearnPadTaskSubmissionResult submissionResult) {

		// signal task end event
		processEventReceiver.receiveTaskEndEvent(new TaskEndSimEvent(System
				.currentTimeMillis(), simulationSessionId, involvedUsers, task,
				completingUser, data, submissionResult));

	};

	/**
	 * This method should be implemented to check if a task exists or not.
	 *
	 * @param taskId
	 * @return whether the task actually exists or not
	 */
	protected abstract boolean doesTaskExist(String taskId);

	/**
	 * This method should be implemented to check if a task has been already
	 * completed or not
	 *
	 * @param task
	 */
	protected abstract boolean isTaskAlreadyCompleted(String taskId);

	/**
	 *
	 * @param task
	 * @return the inputs associated with the task
	 */
	protected abstract Map<String, Object> getTaskInputs(String taskId);
}
