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
import java.util.Date;
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

import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.document.LearnPadDocument;
import eu.learnpad.simulator.datastructures.document.LearnPadDocumentField;
import eu.learnpad.simulator.processmanager.AbstractProcessDispatcher;
import eu.learnpad.simulator.processmanager.ITaskRouter;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.utils.BPMNExplorer;

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
	private final BPMNExplorer explorer;

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
	// modification exception).
	// So we set a boolean when we notice a process is finished
	// (during onEvent).
	//
	// This is less an issue now, as we now have abstracted most of the
	// treatment in the AbstractProcessDispatcher. Things work out ok
	// "by default" because completeTask() is called and returns before we try
	// to check if the process is terminated using isProcessFinished().
	// So no issue arises as we will never try to terminate the process before
	// the last task is completed.
	// This comment is left here in case a change in the way things work either
	// in the project or activiti requires some changes that could stumble upon
	// this problem.
	//
	// NOTE: the isProcessFinished method and the activiti listener method are
	// set to synchronized. This does not seems to be currently necessary as
	// activiti seems to handle all of this into a single thread, but better
	// safe than sorry.
	private boolean processFinished = false;

	public ActivitiProcessDispatcher(
			ProcessInstanceData processInstanceData,
			ActivitiProcessManager processManager,
			IProcessEventReceiver processEventReceiver,
			TaskService taskService,
			RuntimeService runtimeService,
			HistoryService historyService,
			ITaskRouter router,
			ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator,
			BPMNExplorer explorer) {
		super(processInstanceData, processManager, processEventReceiver,
				router, taskValidator);
		this.taskService = taskService;
		this.runtimeService = runtimeService;
		this.historyService = historyService;
		this.explorer = explorer;

		runtimeService.addEventListener(this,
				ActivitiEventType.PROCESS_COMPLETED);
	}

	@Override
	protected void completeProcess() {
		// unsubscribe to events
		runtimeService.removeEventListener(this);
		super.completeProcess();
	}

	// synchronized since in some case is it possible for several tasks to
	// complete at the same time, joining on the same next task. This can cause
	// a race condition where the next task is processed several times.
	@Override
	synchronized protected Collection<LearnPadTask> fetchNewTasks() {
		List<LearnPadTask> newTasks = new ArrayList<LearnPadTask>();

		// check for newly triggered tasks by getting the list of waiting
		// tasks...
		List<Task> waitingTasks = taskService.createTaskQuery()
				.processInstanceId(processId).list();

		final ProcessInstance processWithVars = runtimeService
				.createProcessInstanceQuery().includeProcessVariables()
				.processInstanceId(processId).singleResult();

		// ... ignoring already processed tasks
		for (Task t : waitingTasks) {
			if (!registeredWaitingTasks.contains(t.getId())) {

				Collection<LearnPadDocument> documents = new ArrayList<LearnPadDocument>();

				// add input data objects to task
				if (explorer != null) {
					Set<String> dataInputs = explorer.getDataInputs(t
							.getTaskDefinitionKey());

					for (String dataInput : dataInputs) {

						Collection<LearnPadDocumentField> fields = new ArrayList<LearnPadDocumentField>();

						for (String element : explorer
								.getDataObjectContent(dataInput)) {
							fields.add(new LearnPadDocumentField(element,
									element, "string", "", processWithVars
											.getProcessVariables().get(element)
									.toString()));
						}

						documents.add(new LearnPadDocument(dataInput, explorer
								.getDataObjectName(dataInput), "", fields));
					}
				}

				newTasks.add(new LearnPadTask(t.getProcessInstanceId(), t
						.getId(), t.getName(), t.getDescription(), documents,
						new Date().getTime()));

				registeredWaitingTasks.add(t.getId());
			}
		}

		return newTasks;
	};

	@Override
	protected void completeTask(LearnPadTask task, Map<String, Object> data) {
		// complete task and de-register it
		taskService.complete(task.id, data);
		registeredWaitingTasks.remove(task.id);
	}

	@Override
	// note that we added the `synchronized` modifier (see comment on
	// processFinished declaration for why)
	protected synchronized boolean isProcessFinished() {
		return processFinished;
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
