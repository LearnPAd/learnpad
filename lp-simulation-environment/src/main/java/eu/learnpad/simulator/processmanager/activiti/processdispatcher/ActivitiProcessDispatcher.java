/**
 *
 */
package eu.learnpad.simulator.processmanager.activiti.processdispatcher;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.IProcessManager.TaskSubmissionStatus;
import eu.learnpad.simulator.processmanager.IProcessDispatcher;
import eu.learnpad.simulator.processmanager.ITaskRouter;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProcessDispatcher implements IProcessDispatcher,
		ActivitiEventListener {

	private final ActivitiProcessManager processManager;
	private final IProcessEventReceiver processEventReceiver;
	private final ProcessInstance process;
	private final TaskService taskService;
	private final ITaskRouter router;
	private final RuntimeService runtimeService;
	private final HistoryService historyService;

	private final ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator;

	private final Set<String> registeredWaitingTasks = new HashSet<String>();

	// Ok... due to some weirdness in the way activiti fire end process signals
	// we have to do some weird things here. The activiti workflow when
	// processing the last task seems to be the following:
	// 1. Start processing task
	// 2. Notice process is finished, fire PROCESS_FINISHED signal
	// 3. Finish processing task
	//
	// If we are not careful and try to terminate the process too soon, the last
	// task is not completely processed and activiti may freak out (concurrent
	// modification exception)
	// So we set a boolean when we notice a process is finished
	// (during onEvent). When completing tasks (completeTask) we terminate by
	// checking if the boolean is set to true. In this case we know that we can
	// safely end the process.
	//
	// NOTE: the two mentioned methods are set to synchronized. This does not
	// seems to be currently necessary as activiti seems to handle all of this
	// into a single thread, but better save than sorry.
	//
	// NOTE: If this does not work anymore, it may be due to a change in the way
	// activiti process the last task. In this case removing this boolean (and
	// the synchronized) and simply calling completeProcess() during onEvent()
	// could be sufficient (if things are sane on activiti side).
	private boolean processFinished = false;

	/**
	 * @param webserver
	 * @param process
	 * @param taskService
	 */
	public ActivitiProcessDispatcher(
			ActivitiProcessManager processManager,
			IProcessEventReceiver processEventReceiver,
			ProcessInstance process,
			TaskService taskService,
			RuntimeService runtimeService,
			HistoryService historyService,
			ITaskRouter router,
			ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator,
			Collection<String> involvedUsers) {
		super();
		this.processManager = processManager;
		this.processEventReceiver = processEventReceiver;
		this.process = process;
		this.taskService = taskService;
		this.router = router;
		this.runtimeService = runtimeService;
		this.taskValidator = taskValidator;
		this.historyService = historyService;

		List<Task> tasks = taskService.createTaskQuery()
				.processInstanceId(process.getId()).list();

		if (tasks.isEmpty()) {
			throw new RuntimeException("Process without waiting task");
		} else {
			processNewTasks(tasks);
		}

		runtimeService.addEventListener(this,
				ActivitiEventType.PROCESS_COMPLETED);

		System.out.println("Created dispatcher for process " + process.getId());

	}

	private void processNewTasks(List<Task> tasks) {
		for (final Task task : tasks) {
			registeredWaitingTasks.add(task.getId());

			// process new tasks in a new thread to avoid blocking
			// current completion
			new Thread(new Runnable() {
				public void run() {
					processEventReceiver.sendTask(task.getProcessInstanceId(),
							task.getId(), task.getDescription(),
							router.route(task));
				}
			}).start();
		}
	}

	private void completeProcess() {

		// signal process end to users
		processEventReceiver
		.signalProcessEnd(process.getId(), processManager
				.getProcessInstanceInvolvedUsers(process.getId()));

		// unsubscribe to events
		runtimeService.removeEventListener(this);

		// remove itself from the process manager
		processManager.removeDispatcher(process.getId());

		System.out.println("Process " + process.getId() + " finished");
	}

	// synchronized because several users can try to submit results for the same
	// task simultaneously
	public synchronized IProcessManager.TaskSubmissionStatus submitTaskCompletion(
			String taskId, Map<String, Object> data) {
		try {
			if (historyService.createHistoricTaskInstanceQuery().finished()
					.taskId(taskId).singleResult() != null) {
				return TaskSubmissionStatus.ALREADY_COMPLETED;
			} else {

				Task task = taskService.createTaskQuery()
						.includeProcessVariables().taskId(taskId)
						.singleResult();

				if (task == null) {
					return TaskSubmissionStatus.UNKOWN_TASK;
				} else {

					Map<String, Object> processVariables = taskService
							.createTaskQuery().includeProcessVariables()
							.taskId(taskId).singleResult()
							.getProcessVariables();

					if (!taskValidator.taskResultIsValid(process.getId(),
							taskId, processVariables, data)) {
						// task result is invalid and must be resubmitted
						return TaskSubmissionStatus.REJECTED;
					} else {

						taskService.complete(taskId, data);

						registeredWaitingTasks.remove(taskId);

						// see comment on processFinished declaration
						if (processFinished) {
							completeProcess();
						} else {

							// check for newly triggered tasks
							List<Task> waitingTasks = taskService
									.createTaskQuery()
									.processInstanceId(process.getId()).list();

							// ignore already processed tasks
							List<Task> newTasks = new ArrayList<Task>();
							for (Task t : waitingTasks) {
								if (!registeredWaitingTasks.contains(t.getId())) {
									newTasks.add(t);
								}
							}

							if (!newTasks.isEmpty()) {
								processNewTasks(newTasks);
							}

						}

						return TaskSubmissionStatus.VALIDATED;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return TaskSubmissionStatus.UNKOWN_ERROR;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.
	 * activiti.engine.delegate.event.ActivitiEvent)
	 */
	public synchronized void onEvent(ActivitiEvent event) {
		if (event.getProcessInstanceId().equals(process.getId())
				&& event.getType().equals(ActivitiEventType.PROCESS_COMPLETED)) {

			// see comment on processFinished declaration
			processFinished = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#isFailOnException
	 * ()
	 */
	public boolean isFailOnException() {
		return false;
	}
}
