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
		if (newTasks.isEmpty()) {
			throw new RuntimeException("Process without waiting task");
		} else {
			for (LearnPadTask newTask : newTasks) {
				processNewTask(newTask);
			}
		}
	}

	@Override
	// synchronized because several users can try to submit results for the same
	// task simultaneously
	public synchronized TaskSubmissionStatus submitTaskCompletion(
			LearnPadTask task, String userId, Map<String, Object> data) {

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

					if (isProcessFinished()) {
						completeProcess();
					} else {
						// process new tasks in a new thread to avoid
						// blocking current completion
						new Thread(new Runnable() {
							public void run() {
								for (LearnPadTask newTask : fetchNewTasks()) {
									processNewTask(newTask);
								}
							}
						}).start();
					}

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
	 * This method is called to process new tasks.
	 *
	 * @param task
	 *            a new task
	 */
	protected void processNewTask(final LearnPadTask task) {
		processEventReceiver.sendTask(task, router.route(task.id));
	}

	/**
	 *
	 * @return a collection of new tasks to be processed
	 */
	protected abstract Collection<LearnPadTask> fetchNewTasks();

	/**
	 * This method should be implemented to complete a given task
	 *
	 * @param task
	 * @param data
	 */
	protected abstract void completeTask(LearnPadTask task,
			Map<String, Object> data);

	/**
	 *
	 * @return true if the process is finished
	 */
	protected abstract boolean isProcessFinished();

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
