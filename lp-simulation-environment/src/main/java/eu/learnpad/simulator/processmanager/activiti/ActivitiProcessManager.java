/**
 *
 */
package eu.learnpad.simulator.processmanager.activiti;

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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.processmanager.IProcessDispatcher;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.processdispatcher.ActivitiProcessDispatcher;
import eu.learnpad.simulator.processmanager.activiti.taskrouter.ActivitiTaskRouter;
import eu.learnpad.simulator.processmanager.activiti.taskvalidator.ActivitiDemoTaskValidator;

/**
 *
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProcessManager implements IProcessManager {

	private final RepositoryService repositoryService;
	private final RuntimeService runtimeService;
	private final TaskService taskService;
	private final HistoryService historyService;

	private final ProcessDiagramGenerator generator;

	private final IProcessEventReceiver.IProcessEventReceiverProvider processEventReceiverProvider;

	private final ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator;

	private final Map<String, IProcessDispatcher> processDispatchers = Collections
			.synchronizedMap(new HashMap<String, IProcessDispatcher>());

	private final Map<String, Collection<String>> processInstanceToUsers = Collections
			.synchronizedMap(new HashMap<String, Collection<String>>());

	public ActivitiProcessManager(
			ProcessEngine processEngine,
			IProcessEventReceiver.IProcessEventReceiverProvider processEventReceiverProvider)
					throws FileNotFoundException {
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		historyService = processEngine.getHistoryService();

		this.generator = new DefaultProcessDiagramGenerator();

		taskValidator = new ActivitiDemoTaskValidator(taskService);

		this.processEventReceiverProvider = processEventReceiverProvider;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IProcessManager#addProjectDefininition(java.lang.String)
	 */
	public Collection<String> addProjectDefinitions(String resource) {
		Set<String> res = new HashSet<String>();

		String deploymentId = repositoryService.createDeployment()
				.addClasspathResource(resource).deploy().getId();

		for (ProcessDefinition processDef : repositoryService
				.createProcessDefinitionQuery().deploymentId(deploymentId)
				.list()) {
			res.add(processDef.getId());
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IProcessManager#getAvailableProcessDefintion()
	 */
	public Collection<String> getAvailableProcessDefintion() {
		Set<String> res = new HashSet<String>();

		List<ProcessDefinition> processes = repositoryService
				.createProcessDefinitionQuery().list();

		for (ProcessDefinition process : processes) {
			res.add(process.getId());
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * activitipoc.IProcessManager#getProcessDefinitionName(java.lang.String)
	 */
	public String getProcessDefinitionName(String processDefinitionId) {
		String res = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult()
				.getName();

		if (res == null) {
			res = "";
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * activitipoc.IProcessManager#getProcessDefinitionDescription(java.lang
	 * .String)
	 */
	public String getProcessDefinitionDescription(String processDefinitionId) {
		String res = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult()
				.getDescription();

		if (res == null) {
			res = "";
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * activitipoc.IProcessManager#getProcessDefinitionRoles(java.lang.String)
	 */
	public Collection<String> getProcessDefinitionSingleRoles(
			String processDefinitionId) {
		Set<String> result = new HashSet<String>();

		// open the BPMN model of the process
		BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
		for (FlowElement element : model.getMainProcess().getFlowElements()) {
			// filter to keep only user tasks
			if (element instanceof UserTask) {
				UserTask task = (UserTask) element;
				result.addAll(task.getCandidateUsers());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * activitipoc.IProcessManager#getProcessDefinitionRoles(java.lang.String)
	 */
	public Collection<String> getProcessDefinitionGroupRoles(
			String processDefinitionId) {
		Set<String> result = new HashSet<String>();

		// open the BPMN model of the process
		BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);
		for (FlowElement element : model.getMainProcess().getFlowElements()) {
			// filter to keep only user tasks
			if (element instanceof UserTask) {
				UserTask task = (UserTask) element;
				result.addAll(task.getCandidateGroups());
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IProcessManager#startProjectInstance(java.lang.String,
	 * java.util.Map, activitipoc.ITaskRouter)
	 */
	public String startProjectInstance(String projectDefinitionId,
			Map<String, Object> parameters, Collection<String> users,
			Map<String, Collection<String>> router) {

		ProcessInstance process = runtimeService.startProcessInstanceById(
				projectDefinitionId, parameters);

		processInstanceToUsers.put(process.getId(), new HashSet<String>(users));

		processDispatchers.put(process.getId(), new ActivitiProcessDispatcher(
				this, this.processEventReceiverProvider.processEventReceiver(),
				process, taskService, runtimeService, historyService,
				new ActivitiTaskRouter(taskService, router), taskValidator,
				users));

		return process.getId();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IProcessManager#getCurrentProcessInstances()
	 */
	public Collection<String> getCurrentProcessInstances() {
		Set<String> res = new HashSet<String>();

		List<ProcessInstance> processes = runtimeService
				.createProcessInstanceQuery().list();

		for (ProcessInstance process : processes) {
			res.add(process.getId());
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.IProcessManager#getProcessInstanceInvolvedUsers
	 * (java.lang.String)
	 */
	public Collection<String> getProcessInstanceInvolvedUsers(
			String processInstanceId) {
		return processInstanceToUsers.get(processInstanceId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.learnpad.simulator.processmanager.IProcessManager#submitTaskCompletion
	 * (java.lang.String, java.lang.String, java.util.Map)
	 */
	public TaskSubmissionStatus submitTaskCompletion(String processId,
			String taskId, Map<String, Object> data) {
		return processDispatchers.get(processId).submitTaskCompletion(taskId,
				data);
	}

	/**
	 * Remove the dispatcher associated with a given processId
	 *
	 * @param processId
	 */
	public void removeDispatcher(String processId) {
		processDispatchers.remove(processId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.processmanager.IDiagramGenerator#getProcessDiagram
	 * (java.lang.String)
	 */
	public InputStream getProcessDiagram(String processDefinitionId) {

		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();

		if (processDefinition == null) {
			return null;
		} else {
			String diagramResourceName = processDefinition
					.getDiagramResourceName();

			if (diagramResourceName == null) {
				return null;
			} else {
				return repositoryService.getResourceAsStream(
						processDefinition.getDeploymentId(),
						diagramResourceName);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.processmanager.IDiagramGenerator#getCurrentTaskDiagram
	 * (java.lang.String, java.lang.String)
	 */
	public InputStream getCurrentTaskDiagram(String processInstanceId,
			String taskId) {

		ProcessInstance instance = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();

		if (instance == null) {
			return null;
		} else {

			ProcessDefinition processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(instance.getProcessDefinitionId())
					.singleResult();

			if (processDefinition == null
					|| processDefinition.getDiagramResourceName() == null) {
				return null;
			} else {

				BpmnModel model = repositoryService.getBpmnModel(instance
						.getProcessDefinitionId());

				// Iterate over all active activities for the process instance,
				// and
				// filter to select only the one which correspond to the task
				// TODO:this is not very efficient :( Possible to do it directly
				// with a
				// query?
				List<String> res = new ArrayList<String>();
				for (String activityId : runtimeService
						.getActiveActivityIds(processInstanceId)) {
					if (this.historyService
							.createHistoricActivityInstanceQuery()
							.processInstanceId(processInstanceId)
							.activityId(activityId).unfinished().singleResult()
							.getTaskId().equals(taskId)) {
						res.add(activityId);
					}
				}

				if (res.isEmpty()) {
					return null;
				} else {
					return generator.generateDiagram(model, "png", res);
				}

			}
		}
	}

}
