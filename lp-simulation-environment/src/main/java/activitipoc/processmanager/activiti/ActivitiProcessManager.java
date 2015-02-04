/**
 *
 */
package activitipoc.processmanager.activiti;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import activitipoc.IProcessManager;
import activitipoc.ITaskRouter;
import activitipoc.IUIHandler;
import activitipoc.processdispatcher.activiti.ActivitiProcessDispatcher;

/**
 *
 *
 * @author jorquera
 *
 */
public class ActivitiProcessManager implements IProcessManager {

	private final RepositoryService repositoryService;
	private final RuntimeService runtimeService;
	private final TaskService taskService;

	public ActivitiProcessManager(ProcessEngine processEngine) {
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
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
	 * activitipoc.IProcessManager#getProcessDefinitionDescription(java.lang
	 * .String)
	 */
	public String getProcessDefinitionDescription(String processDefinitionId) {
		return repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult()
				.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IProcessManager#startProjectInstance(java.lang.String,
	 * java.util.Map, activitipoc.ITaskRouter)
	 */
	public String startProjectInstance(String projectDefinitionId,
			Map<String, Object> parameters, ITaskRouter router,
			List<String> users, IUIHandler uiHandler) {

		ProcessInstance process = runtimeService.startProcessInstanceById(
				projectDefinitionId, parameters);

		new ActivitiProcessDispatcher(process, taskService, runtimeService,
				router, users, uiHandler);

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

}
