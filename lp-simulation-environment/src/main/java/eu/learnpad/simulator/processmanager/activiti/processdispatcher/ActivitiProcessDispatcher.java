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
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.processmanager.AbstractProcessDispatcher;
import eu.learnpad.simulator.processmanager.ITaskRouter;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProcessDispatcher extends AbstractProcessDispatcher
		implements ActivitiEventListener {

	private final TaskService taskService;
	private final RuntimeService runtimeService;
	private final HistoryService historyService;

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

	public ActivitiProcessDispatcher(
			ActivitiProcessManager processManager,
			IProcessEventReceiver processEventReceiver,
			ProcessInstance process,
			TaskService taskService,
			RuntimeService runtimeService,
			HistoryService historyService,
			ITaskRouter router,
			ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator,
			Collection<String> involvedUsers

	) {
		super(processManager, processEventReceiver, process.getId(),
				involvedUsers, router, taskValidator);
		this.taskService = taskService;
		this.runtimeService = runtimeService;
		this.historyService = historyService;

		runtimeService.addEventListener(this,
				ActivitiEventType.PROCESS_COMPLETED);

		List<Task> tasks = taskService.createTaskQuery()
				.processInstanceId(processId).list();

		if (tasks.isEmpty()) {
			throw new RuntimeException("Process without waiting task");
		} else {
			for (Task task : tasks) {
				processNewTask(new LearnPadTask(task.getProcessInstanceId(),
						task.getId(), task.getName(), task.getDescription()));
			}
		}

	}

	@Override
	protected void completeProcess() {
		// unsubscribe to events
		runtimeService.removeEventListener(this);

		super.completeProcess();
	}

	@Override
	protected void processNewTask(final LearnPadTask task) {
		registeredWaitingTasks.add(task.id);
		super.processNewTask(task);
	}

	@Override
	// note that we added the `synchronized` modifier (see comment on
	// processFinished declaration for why)
	protected synchronized void completeTask(LearnPadTask task,
			Map<String, Object> data) {
		{
			taskService.complete(task.id, data);

			registeredWaitingTasks.remove(task.id);

			// see comment on processFinished declaration
			if (processFinished) {
				completeProcess();
			} else {

				// check for newly triggered tasks
				List<Task> waitingTasks = taskService.createTaskQuery()
						.processInstanceId(processId).list();

				// ignore already processed tasks
				List<Task> newTasks = new ArrayList<Task>();
				for (Task t : waitingTasks) {
					if (!registeredWaitingTasks.contains(t.getId())) {
						newTasks.add(t);
					}
				}

				if (!newTasks.isEmpty()) {
					for (Task newTask : newTasks) {
						processNewTask(new LearnPadTask(
								newTask.getProcessInstanceId(),
								newTask.getId(), newTask.getName(),
								newTask.getDescription()));
					}
				}
			}
		}

	}

	@Override
	protected boolean doesTaskExist(String taskId) {
		return taskService.createTaskQuery().includeProcessVariables()
				.taskId(taskId).singleResult() != null;
	}

	@Override
	protected boolean isTaskAlreadyCompleted(String taskId) {
		return historyService.createHistoricTaskInstanceQuery().finished()
				.taskId(taskId).singleResult() != null;
	}

	@Override
	protected Map<String, Object> getTaskInputs(String taskId) {
		return taskService.createTaskQuery().includeProcessVariables()
				.taskId(taskId).singleResult().getProcessVariables();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.
	 * activiti.engine.delegate.event.ActivitiEvent)
	 */
	public synchronized void onEvent(ActivitiEvent event) {
		if (event.getProcessInstanceId().equals(processId)
				&& event.getType().equals(ActivitiEventType.PROCESS_COMPLETED)) {

			// see comment on processFinished declaration
			processFinished = true;
		}

	}

	@Override
	public boolean isFailOnException() {
		return false;
	}

}
