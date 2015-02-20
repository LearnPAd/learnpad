/**
 *
 */
package eu.learnpad.simulator.processmanager.activiti;

import java.io.FileNotFoundException;
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

}
