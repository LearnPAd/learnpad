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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.eclipse.jetty.util.ConcurrentHashSet;

import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;
import eu.learnpad.simulator.datastructures.document.LearnPadDocument;
import eu.learnpad.simulator.datastructures.document.LearnPadDocumentField;
import eu.learnpad.simulator.processmanager.AbstractProcessDispatcher;
import eu.learnpad.simulator.processmanager.ITaskRouter;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.processmanager.activiti.workarounds.msg.MessageInfoData;
import eu.learnpad.simulator.uihandler.formhandler.dataobject2jsonform.DataObjectToJsonFormFormHandler;
import eu.learnpad.simulator.utils.BPMNExplorer;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProcessDispatcher extends AbstractProcessDispatcher {

	private final TaskService taskService;
	private final RuntimeService runtimeService;
	private final RepositoryService repositoryService;
	private final HistoryService historyService;
	private final BPMNExplorer explorer;

	private final Set<String> registeredWaitingTasks = new HashSet<String>();

	private final Set<MessageInfoData> waitingMessages = new ConcurrentHashSet<MessageInfoData>();

	private boolean processEndNotified = false;

	public ActivitiProcessDispatcher(
			ProcessInstanceData processInstanceData,
			ActivitiProcessManager processManager,
			IProcessEventReceiver processEventReceiver,
			TaskService taskService,
			RuntimeService runtimeService,
			RepositoryService repositoryService,
			HistoryService historyService,
			ITaskRouter router,
			ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator,
			BPMNExplorer explorer) {
		super(processInstanceData, processManager, processEventReceiver,
				router, taskValidator);
		this.taskService = taskService;
		this.runtimeService = runtimeService;
		this.repositoryService = repositoryService;
		this.historyService = historyService;
		this.explorer = explorer;
	}

	@Override
	protected void completeProcess() {

		if (!processEndNotified) {
			processEndNotified = true;
			super.completeProcess();
		}

	}

	private LearnPadTask fetchTask(Task t) {
		final ProcessInstance processWithVars = runtimeService
				.createProcessInstanceQuery().includeProcessVariables()
				.processInstanceId(processId).singleResult();

		Collection<LearnPadDocument> documents = new ArrayList<LearnPadDocument>();

		// add input data objects to task
		if (explorer != null) {
			Set<String> dataInputs = explorer.getDataInputs(t
					.getTaskDefinitionKey());

			for (String dataInput : dataInputs) {

				Map<LearnPadDocumentField, Object> fieldValues = new HashMap<LearnPadDocumentField, Object>();
				for (LearnPadDocumentField field : DataObjectToJsonFormFormHandler
						.dataObjectToFields(dataInput, explorer)) {
					fieldValues.put(field, processWithVars
							.getProcessVariables().get(field.id));
				}

				documents.add(new LearnPadDocument(dataInput, explorer
						.getDataObjectName(dataInput), "", fieldValues));
			}
		}

		return new LearnPadTask(this.simulationSessionId,
				t.getProcessInstanceId(), t.getId(), t.getTaskDefinitionKey(),
				explorer.getSubprocess(t.getTaskDefinitionKey()), t.getName(),
				t.getDescription(), documents, new Date().getTime());
	}

	// synchronized since in some case is it possible for several tasks to
	// complete at the same time, joining on the same next task. This can cause
	// a race condition where the next task is processed several times.
	@Override
	protected Collection<LearnPadTask> fetchNewTasks() {
		List<LearnPadTask> newTasks = new ArrayList<LearnPadTask>();

		// check for newly triggered tasks by getting the list of waiting
		// tasks...
		List<Task> waitingTasks = taskService.createTaskQuery()
				.processInstanceId(processId).list();

		// ... ignoring already processed tasks
		for (Task t : waitingTasks) {
			newTasks.add(fetchTask(t));
			registeredWaitingTasks.add(t.getId());
		}

		return newTasks;
	};

	@Override
	public synchronized void completeTask(final LearnPadTask task,
			final Map<String, Object> data, String completingUser,
			LearnPadTaskSubmissionResult submissionResult) {
		super.completeTask(task, data, completingUser, submissionResult);
		// complete task and de-register it
		taskService.complete(task.id, data);
		registeredWaitingTasks.remove(task.id);
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

	public synchronized void onEvent(final ActivitiEvent event) {
		if (event.getProcessInstanceId().equals(processId)
				&& !event.getType().equals(ActivitiEventType.PROCESS_COMPLETED)) {

			Set<MessageInfoData> msgInfoCopy;
			synchronized (waitingMessages) {
				msgInfoCopy = new HashSet<MessageInfoData>(waitingMessages);
				waitingMessages.clear();
			}

			for (final MessageInfoData msg : msgInfoCopy) {

				final HistoricProcessInstance processInstance = historyService
						.createHistoricProcessInstanceQuery()
						.processInstanceId(msg.originProcessInstanceId)
						.includeProcessVariables().singleResult();

				// get data from origin process, we will use the same users
				// and routes
				final ProcessInstanceData originData = this
						.getProcessInstanceInfos();

				// get the current variables of the process
				final Map<String, Object> processVariables = processInstance
						.getProcessVariables();

				// check if there is a process to be instantiated by the message
				// reception
				final ProcessDefinition processDef = repositoryService
						.createProcessDefinitionQuery()
						.messageEventSubscriptionName(msg.msgContent)
						.singleResult();

				if (processDef != null) {
					// note that we pass all the process variables just in case
					manager.startProjectInstance(processDef.getKey(),
							processVariables, originData.getUsers(),
							originData.getRoutes());
				}

				// check if there is some message subscription event pending
				// TODO: filter to only wake matching receiving process (and not
				// *all* receiving processes)
				List<Execution> otherExecutions = runtimeService
						.createExecutionQuery()
						.messageEventSubscriptionName(msg.msgContent)
						.processVariableValueEquals(
								ActivitiProcessManager.SIMULATION_ID_KEY,
								processVariables
										.get(ActivitiProcessManager.SIMULATION_ID_KEY))
								.list();

				// if it is the case, notify them
				if (!otherExecutions.isEmpty()) {
					for (Execution otherExec : otherExecutions) {
						runtimeService.messageEventReceived(msg.msgContent,
								otherExec.getId(), processVariables);
					}
				}

				// check if there is a process with a receive task waiting for
				// the message
				otherExecutions = runtimeService
						.createExecutionQuery()
						.activityId(msg.msgContent)
						.processVariableValueEquals(
								ActivitiProcessManager.SIMULATION_ID_KEY,
								processVariables
										.get(ActivitiProcessManager.SIMULATION_ID_KEY))
						.list();

				if (!otherExecutions.isEmpty()) {
					for (Execution otherExec : otherExecutions) {
						runtimeService.trigger(otherExec.getId(),
								processVariables);
					}
				}

			}
		}

		if (event.getProcessInstanceId().equals(processId)
				&& event.getType().equals(ActivitiEventType.PROCESS_COMPLETED)) {
			completeProcess();
		} else if (event.getType().equals(ActivitiEventType.TASK_CREATED)) {
				ActivitiEntityEvent activityEntityEvent = (ActivitiEntityEvent) event;
				TaskEntity t = (TaskEntity) activityEntityEvent.getEntity();

				LearnPadTask newTask = fetchTask(t);
				registeredWaitingTasks.add(t.getId());

				processNewTask(newTask);
		}
	}

	// Activiti message workaround
	public synchronized void receiveTaskMessage(MessageInfoData msgInfo) {
		synchronized (waitingMessages) {
			waitingMessages.add(msgInfo);
		}
	}

}
