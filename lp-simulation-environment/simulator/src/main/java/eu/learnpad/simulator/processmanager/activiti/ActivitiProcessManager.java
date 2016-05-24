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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.xml.sax.SAXException;

import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskGameInfos;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;
import eu.learnpad.simulator.monitoring.event.impl.ProcessStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationStartSimEvent;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.processdispatcher.ActivitiProcessDispatcher;
import eu.learnpad.simulator.processmanager.activiti.taskrouter.ActivitiTaskRouter;
import eu.learnpad.simulator.processmanager.activiti.taskvalidator.ActivitiDemoTaskValidator;
import eu.learnpad.simulator.processmanager.activiti.workarounds.msg.BPMNTransformer;
import eu.learnpad.simulator.processmanager.activiti.workarounds.msg.MessageInfoData;
import eu.learnpad.simulator.utils.BPMNExplorerRepository;

/**
 *
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProcessManager implements IProcessManager,
		ActivitiEventListener {

	private final RepositoryService repositoryService;
	private final RuntimeService runtimeService;
	private final TaskService taskService;
	private final HistoryService historyService;

	private final Executor jobHandler = Executors.newSingleThreadExecutor();

	private final ProcessDiagramGenerator generator;

	private final BPMNExplorerRepository explorerRepo;

	private final IProcessEventReceiver.IProcessEventReceiverProvider processEventReceiverProvider;

	private final ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator;

	private final Map<String, ActivitiProcessDispatcher> processDispatchers = Collections
			.synchronizedMap(new HashMap<String, ActivitiProcessDispatcher>());

	private final Map<String, Integer> nbProcessesBySession = new HashMap<>();
	private final Map<String, Collection<String>> usersBySession = new HashMap<>();

	private final Map<String, Map<String, Object>> sessionParametersData = new HashMap<>();

	private final Map<String, Map<String, Map<LearnPadTask, Integer>>> taskScoresByUsersBySession = new HashMap<>();

	private final Map<String, String> processDefToModelSet = new ConcurrentHashMap<String, String>();

	private final Map<String, String> simSessionIdToModelSet = new ConcurrentHashMap<String, String>();

	public ActivitiProcessManager(
			ProcessEngine processEngine,
			IProcessEventReceiver.IProcessEventReceiverProvider processEventReceiverProvider,
			BPMNExplorerRepository explorerRepo) throws FileNotFoundException {
		repositoryService = processEngine.getRepositoryService();
		runtimeService = processEngine.getRuntimeService();
		taskService = processEngine.getTaskService();
		historyService = processEngine.getHistoryService();

		// register itself as a listener
		this.runtimeService.addEventListener(this,
				ActivitiEventType.PROCESS_COMPLETED);
		this.runtimeService.addEventListener(this,
				ActivitiEventType.ACTIVITY_COMPLETED);
		this.runtimeService.addEventListener(this,
				ActivitiEventType.TASK_CREATED);

		this.generator = new DefaultProcessDiagramGenerator();
		this.explorerRepo = explorerRepo;

		taskValidator = new ActivitiDemoTaskValidator(repositoryService,
				taskService);

		this.processEventReceiverProvider = processEventReceiverProvider;
	}

	@Override
	public String getProcessDefIdFromDefKey(String processDefinitionKey) {
		return repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).singleResult()
				.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IProcessManager#addProjectDefininition(java.lang.String)
	 */
	public Collection<String> addProjectDefinitions(String resource) {
		return this.addProjectDefinitions(this.getClass().getClassLoader()
				.getResourceAsStream(resource));
	}

	@Override
	public Collection<String> addProjectDefinitions(InputStream resource) {
		Set<String> res = new HashSet<String>();

		try {
			// Activiti message workaround
			InputStream transformedResource = BPMNTransformer
					.transform(resource);

			String deploymentId = repositoryService
					.createDeployment()
					.addInputStream(
							Long.toString(new Random().nextLong())
									+ ".bpmn20.xml", transformedResource)
					.deploy().getId();

			for (ProcessDefinition processDef : repositoryService
					.createProcessDefinitionQuery().deploymentId(deploymentId)
					.list()) {
				res.add(processDef.getId());
			}
		} catch (XPathExpressionException | SAXException | IOException
				| ParserConfigurationException | TransformerException
				| TransformerFactoryConfigurationError e) {
			e.printStackTrace();
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
	 * eu.learnpad.simulator.IProcessManager#getProcessDefinitionKey(java.lang
	 * .String)
	 */
	public String getProcessDefinitionKey(String processDefinitionId) {
		return repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult()
				.getKey();
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

		// in order to handle collaboration diagrams, we need to get the users
		// of *all* the processes
		for (org.activiti.bpmn.model.Process process : model.getProcesses()) {
			for (FlowElement element : process.getFlowElements()) {
				// filter to keep only user tasks
				if (element instanceof UserTask) {
					UserTask task = (UserTask) element;
					result.addAll(task.getCandidateUsers());
				}
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

		// in order to handle collaboration diagrams, we need to get the users
		// of *all* the processes
		for (org.activiti.bpmn.model.Process process : model.getProcesses()) {
			for (FlowElement element : process.getFlowElements()) {
				// filter to keep only user tasks
				if (element instanceof UserTask) {
					UserTask task = (UserTask) element;
					result.addAll(task.getCandidateGroups());
				}
			}
		}
		return result;
	}

	public static final String SIMULATION_ID_KEY = "__LEARNPAD_SIMULATION_ID";

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IProcessManager#startProjectInstance(java.lang.String,
	 * java.util.Map, activitipoc.ITaskRouter)
	 */
	public String startProjectInstance(String projectDefinitionKey,
			Map<String, Object> parameters, Collection<String> users,
			Map<String, Collection<String>> router) {

		String simSession;

		if (parameters.containsKey(SIMULATION_ID_KEY)) {
			simSession = (String) parameters.get(SIMULATION_ID_KEY);
		} else {
			// this is a new session

			simSession = UUID.randomUUID().toString();
			nbProcessesBySession.put(simSession, 0);
			usersBySession.put(simSession, users);

			// add a UUID to be shared by the processes involved in the
			// simulation
			parameters.put(SIMULATION_ID_KEY, simSession);

			ProcessDefinition processDefInfos = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionKey(projectDefinitionKey).singleResult();

			// register session parameters data
			sessionParametersData.put(simSession, parameters);

			if (processDefToModelSet.containsKey(projectDefinitionKey)) {
				simSessionIdToModelSet.put(simSession,
						processDefToModelSet.get(projectDefinitionKey));
			}

			// signal simulation session start
			// signal process start
			this.processEventReceiverProvider.processEventReceiver()
					.receiveSimulationStartEvent(
					new SimulationStartSimEvent(System
							.currentTimeMillis(), simSession, users,
							processDefInfos.getName(),
							projectDefinitionKey));
		}

		nbProcessesBySession.put(simSession,
				nbProcessesBySession.get(simSession) + 1);

		ProcessInstance process = runtimeService.startProcessInstanceByKey(
				projectDefinitionKey, parameters);

		ProcessInstanceData data = new ProcessInstanceData(process.getId(),
				projectDefinitionKey, parameters, users, router);

		processDispatchers.put(
				process.getId(),
				new ActivitiProcessDispatcher(data, this,
						this.processEventReceiverProvider
								.processEventReceiver(), taskService,
						runtimeService, repositoryService, historyService,
						new ActivitiTaskRouter(taskService, router),
						taskValidator, explorerRepo.getExplorer(process
								.getProcessDefinitionId())));

		// we are ready, so we can start the dispatcher
		processDispatchers.get(process.getId()).start();

		// signal process start
		this.processEventReceiverProvider.processEventReceiver()
				.receiveProcessStartEvent(
				new ProcessStartSimEvent(System.currentTimeMillis(),
						(String) parameters.get(SIMULATION_ID_KEY),
						users, data));

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
	 * eu.learnpad.simulator.IProcessManager#getProcessDefinitionId(java.lang
	 * .String)
	 */
	public String getProcessDefinitionId(String processId) {
		return runtimeService.createProcessInstanceQuery()
				.processInstanceId(processId).singleResult()
				.getProcessDefinitionId();
	}

	@Override
	public ProcessInstanceData getProcessInstanceInfos(String processInstanceId) {
		return processDispatchers.get(processInstanceId)
				.getProcessInstanceInfos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.learnpad.simulator.processmanager.IProcessManager#submitTaskCompletion
	 * (eu.learnpad.simulator.datastructures.LearnPadTask, java.lang.String,
	 * java.util.Map)
	 */
	public LearnPadTaskSubmissionResult submitTaskCompletion(LearnPadTask task,
			String userId, Map<String, Object> data) {

		LearnPadTaskSubmissionResult result = processDispatchers.get(
				task.processId).submitTaskCompletion(task, userId, data);

		if (result.status
				.equals(LearnPadTaskSubmissionResult.TaskSubmissionStatus.VALIDATED)) {

			if (!taskScoresByUsersBySession.containsKey(task.sessionId)) {
				taskScoresByUsersBySession.put(task.sessionId,
						new HashMap<String, Map<LearnPadTask, Integer>>());
			}

			if (!taskScoresByUsersBySession.get(task.sessionId).containsKey(
					userId)) {
				taskScoresByUsersBySession.get(task.sessionId).put(userId,
						new HashMap<LearnPadTask, Integer>());
			}

			taskScoresByUsersBySession.get(task.sessionId).get(userId)
					.put(task, result.taskScore);
		}

		return result;
	}

	@Override
	public void completeTask(LearnPadTask task, Map<String, Object> data,
			String completingUser, LearnPadTaskSubmissionResult submissionResult) {
		processDispatchers.get(task.processId).completeTask(task, data,
				completingUser, submissionResult);
	}

	@Override
	public synchronized void signalProcessCompletion(String processId) {

		String simSession = (String) processDispatchers.get(processId)
				.getProcessInstanceInfos().getParameters().get(SIMULATION_ID_KEY);

		processDispatchers.remove(processId);

		// check if session is terminated
		int nbActiveProcesses = nbProcessesBySession.get(simSession);
		if (nbActiveProcesses > 1) {
			nbProcessesBySession.put(simSession, nbActiveProcesses - 1);
		} else {
			// this was the last process of the simulation

			this.processEventReceiverProvider.processEventReceiver()
			.receiveSimulationEndEvent(
					new SimulationEndSimEvent(System
							.currentTimeMillis(), simSession,
							usersBySession.get(simSession)));

			nbProcessesBySession.remove(simSession);
			usersBySession.remove(simSession);
			sessionParametersData.remove(simSession);
			simSessionIdToModelSet.remove(simSession);

			taskScoresByUsersBySession.remove(simSession);
		}

	}

	@Override
	public Integer getInstanceScore(String processId, String userId) {
		return processDispatchers.get(processId).getInstanceScore(userId);
	}

	public Map<LearnPadTask, Integer> getDetailedInstanceScore(
			String sessionId, String userId) {

		if (taskScoresByUsersBySession.get(sessionId) == null) {
			return new HashMap<LearnPadTask, Integer>();
		} else {
			return taskScoresByUsersBySession.get(sessionId).get(userId);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.processmanager.IDiagramGenerator#getProcessDiagram
	 * (java.lang.String)
	 */
	public InputStream getProcessDiagram(String processDefinitionKey) {

		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).singleResult();

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

	@Override
	public LearnPadTaskGameInfos getGameInfos(LearnPadTask task, String userId) {
		return processDispatchers.get(task.processId)
				.getGameInfos(task, userId);
	}

	// Activiti message workaround
	public void receiveTaskMessage(final MessageInfoData msgInfo) {
		jobHandler.execute(new Runnable() {
			@Override
			public void run() {
				processDispatchers.get(msgInfo.originProcessInstanceId)
						.receiveTaskMessage(msgInfo);
			}
		});

	}

	@Override
	public synchronized void onEvent(final ActivitiEvent event) {
		// send event to corresponding dispatcher
		if (event.getProcessInstanceId() != null
				&& processDispatchers.get(event.getProcessInstanceId()) != null) {

			jobHandler.execute(new Runnable() {
				@Override
				public void run() {
					processDispatchers.get(event.getProcessInstanceId())
							.onEvent(event);
				}
			});
		}

	}

	@Override
	public boolean isFailOnException() {
		return false;
	}

	// // ModelSetId related methods

	@Override
	public void setModelSetId(String processDefId, String modelSetId) {
		processDefToModelSet.put(processDefId, modelSetId);
	}

	@Override
	public String getModelSetId(String processDefId) {
		return processDefToModelSet.get(processDefId);
	}

	@Override
	public String getModelSetIdFromSessionId(String sessionId) {
		return simSessionIdToModelSet.get(sessionId);
	}

	@Override
	public Map<String, Object> getSimulationSessionParametersData(
			String simulationSessionId) {
		return sessionParametersData.get(simulationSessionId);
	}

}
