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
import java.util.Map;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.IProcessManager.TaskSubmissionStatus;
import eu.learnpad.simulator.datastructures.LearnPadTask;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public abstract class AbstractProcessDispatcher implements IProcessDispatcher {

	protected final String processId;
	protected final Collection<String> involvedUsers;
	private final IProcessManager manager;
	private final IProcessEventReceiver processEventReceiver;
	private final ITaskRouter router;
	private final ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator;

	public AbstractProcessDispatcher(
			IProcessManager manager,
			IProcessEventReceiver processEventReceiver,
			String processId,
			Collection<String> involvedUsers,
			ITaskRouter router,
			ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator) {
		super();
		this.processId = processId;
		this.involvedUsers = involvedUsers;
		this.manager = manager;
		this.processEventReceiver = processEventReceiver;
		this.router = router;
		this.taskValidator = taskValidator;

		System.out.println("Created dispatcher for process " + processId);

	}

	@Override
	// synchronized because several users can try to submit results for the same
	// task simultaneously
	public synchronized TaskSubmissionStatus submitTaskCompletion(
			LearnPadTask task, Map<String, Object> data) {

		if (isTaskAlreadyCompleted(task.id)) {
			return TaskSubmissionStatus.ALREADY_COMPLETED;
		} else {
			if (!(doesTaskExist(task.id))) {
				return TaskSubmissionStatus.UNKOWN_TASK;
			} else {
				if (!taskValidator.taskResultIsValid(task.id,
						getTaskInputs(task.id), data)) {
					// task result is invalid and must be resubmitted
					return TaskSubmissionStatus.REJECTED;
				} else {
					completeTask(task, data);
					return TaskSubmissionStatus.VALIDATED;
				}
			}
		}
	}

	/**
	 * this method is called when the process is completed. It should be used
	 * for clean-up or signaling process completion.
	 */
	protected void completeProcess() {
		// signal process end to users
		processEventReceiver.signalProcessEnd(processId, involvedUsers);

		// remove itself from the process manager
		manager.signalProcessCompletion(processId);

		System.out.println("Process " + processId + " finished");
	}

	/**
	 * This method should be called to process new tasks.
	 *
	 * @param task
	 *            a new task
	 */
	protected void processNewTask(final LearnPadTask task) {
		// process new tasks in a new thread to avoid blocking
		// current completion
		new Thread(new Runnable() {
			public void run() {
				processEventReceiver.sendTask(task, router.route(task.id));

			}
		}).start();
	}

	/**
	 * This method should be implemented to complete a given task
	 *
	 * @param task
	 * @param data
	 */
	protected abstract void completeTask(LearnPadTask task,
			Map<String, Object> data);

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
