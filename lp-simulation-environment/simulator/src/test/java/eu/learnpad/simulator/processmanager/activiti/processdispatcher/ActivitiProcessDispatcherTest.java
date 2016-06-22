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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.Main;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult.TaskSubmissionStatus;
import eu.learnpad.simulator.monitoring.event.impl.ProcessEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;
import eu.learnpad.simulator.processmanager.ITaskRouter;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.processmanager.activiti.taskrouter.ActivitiTaskRouter;
import eu.learnpad.simulator.utils.BPMNExplorer;

/**
 *
 * To speed up testing, we compare page MD5 digests instead of the full page
 * content
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProcessDispatcherTest {

	public final static String TEST_PROCESS = "test.bpmn20.xml";
	public final static String TEST_PROCESS_KEY = "test";

	public final static List<String> TEST_PROCESS_USES = Arrays.asList("user1",
			"user2", "user3");

	static ProcessEngine processEngine;
	static EventForwarder eventForwarder;

	static Map<String, Collection<String>> routes;
	static ITaskRouter taskRouter;

	ProcessInstance processInstance;
	ProcessInstanceData processInstanceData;

	ActivitiProcessManager processManager;
	IProcessEventReceiver processEventReceiver;
	ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator;

	@SuppressWarnings("serial")
	@BeforeClass
	public static void initActivitiEngine() {
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(Main.class
						.getClassLoader().getResourceAsStream(
								Main.ACTIVITY_CONFIG_PATH));

		// create process engine
		processEngine = config.buildProcessEngine();

		// load test process
		processEngine.getRepositoryService().createDeployment()
				.addClasspathResource(TEST_PROCESS).deploy();

		// create task router
		routes = new HashMap<String, Collection<String>>() {
			{
				put("user1", Arrays.asList("user1", "user2", "user3"));
				put("user2", Arrays.asList("user2", "user3"));
				put("user3", Arrays.asList("user3"));
				put("user0", Arrays.asList("user3"));
			}
		};
		taskRouter = new ActivitiTaskRouter(processEngine.getTaskService(),
				routes);

	}

	@AfterClass
	public static void shutdownEngine() {
		processEngine.close();
	}

	@SuppressWarnings("unchecked")
	@Before
	public void instanciateTestProcess() {
		// reset event forwarder
		eventForwarder = new EventForwarder();

		processEngine.getRuntimeService().addEventListener(eventForwarder,
				ActivitiEventType.PROCESS_COMPLETED);
		processEngine.getRuntimeService().addEventListener(eventForwarder,
				ActivitiEventType.ACTIVITY_COMPLETED);
		processEngine.getRuntimeService().addEventListener(eventForwarder,
				ActivitiEventType.TASK_CREATED);

		processManager = mock(ActivitiProcessManager.class);
		processEventReceiver = mock(IProcessEventReceiver.class);

		// automatically validate tasks
		taskValidator = mock(ITaskValidator.class);
		when(
				taskValidator.taskResultIsValid(anyString(), any(Map.class),
						any(Map.class))).thenReturn(true);

		// start process
		processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey(TEST_PROCESS_KEY);

		processInstanceData = new ProcessInstanceData(processInstance.getId(),
				processInstance.getProcessDefinitionKey(),
				new HashMap<String, Object>(), TEST_PROCESS_USES,
				new HashMap<String, Collection<String>>());
	}

	@After
	public void cleanup() {
		processEngine.getRuntimeService().removeEventListener(eventForwarder);
	}

	@Test
	public void testProcessDispatcherInit() {

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processInstanceData, processManager, processEventReceiver,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getRepositoryService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				mock(BPMNExplorer.class));

		dispatcher.start();

		// dispatcher should have dispatched first task
		// (since task processing is multithreaded to avoid blocking,
		// we may need to wait a little. 5 sec should be *far* more than
		// enough)
		ArgumentCaptor<TaskStartSimEvent> taskEvent = ArgumentCaptor
				.forClass(TaskStartSimEvent.class);
		verify(processEventReceiver, timeout(5000)).receiveTaskStartEvent(
				taskEvent.capture());
		assertEquals(taskEvent.getValue().task.processId,
				processInstance.getId());
		assertEquals(taskEvent.getValue().task.key, "t1");
	}

	@Test
	public void testProcessDispatcherDispatch() {

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processInstanceData, processManager, processEventReceiver,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getRepositoryService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				mock(BPMNExplorer.class));

		eventForwarder.setDispatcher(dispatcher);

		validateAllTasks(dispatcher, processEventReceiver);

		dispatcher.start();

		// wait for all tasks to be processed
		checkEndProcess(processEventReceiver);

		// should have processed 6 tasks in total
		ArgumentCaptor<TaskStartSimEvent> taskEvent = ArgumentCaptor
				.forClass(TaskStartSimEvent.class);
		verify(processEventReceiver, times(6)).receiveTaskStartEvent(
				taskEvent.capture());
		for (TaskStartSimEvent t : taskEvent.getAllValues()) {
			assertEquals(t.task.processId, processInstance.getId());
		}

	}

	@Test
	public void testProcessDispatcherRouting() {

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processInstanceData, processManager, processEventReceiver,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getRepositoryService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				mock(BPMNExplorer.class));

		eventForwarder.setDispatcher(dispatcher);

		// automatically respond to tasks dispatch
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {

				final TaskStartSimEvent taskEvent = invocation.getArgumentAt(0,
						TaskStartSimEvent.class);
				Collection<String> receivedRoutes = taskEvent.involvedusers;

				assertEquals(taskEvent.task.processId, processInstance.getId());

				// check task is correctly routed to users
				Set<String> expectedRoutes = new HashSet<String>();
				for (String role : getTaskRolesHelper(taskEvent.task.id)) {
					expectedRoutes.addAll(routes.get(role));
				}

				// both sets should be equal
				assertTrue(receivedRoutes.containsAll(expectedRoutes)
						&& expectedRoutes.containsAll(receivedRoutes));

				// new Thread(new Runnable() {
				//
				// @Override
				// public void run() {
				dispatcher.completeTask(taskEvent.task,
						new HashMap<String, Object>(), taskEvent.involvedusers
						.iterator().next(),
						new LearnPadTaskSubmissionResult(
								TaskSubmissionStatus.VALIDATED, 0, 0));

				// }
				// }).start();

				return null;
			}
		}).when(processEventReceiver).receiveTaskStartEvent(
				any(TaskStartSimEvent.class));

		dispatcher.start();

		checkEndProcess(processEventReceiver);

	}

	@Test
	public void testProcessDispatcherSignalProcessEndToUsers() {

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processInstanceData, processManager, processEventReceiver,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getRepositoryService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				mock(BPMNExplorer.class));

		eventForwarder.setDispatcher(dispatcher);

		validateAllTasks(dispatcher, processEventReceiver);

		dispatcher.start();

		// wait for all tasks to be processed
		ArgumentCaptor<ProcessEndSimEvent> endEvent = checkEndProcess(processEventReceiver);

		// check that the signal for process end is send to all the required
		// users

		assertTrue(endEvent.getValue().involvedusers.size() == TEST_PROCESS_USES
				.size());
		assertTrue(endEvent.getValue().involvedusers
				.containsAll(TEST_PROCESS_USES));

	}

	@Test
	public void testProcessDispatcherUnregister() {

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processInstanceData, processManager, processEventReceiver,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getRepositoryService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				mock(BPMNExplorer.class));

		eventForwarder.setDispatcher(dispatcher);

		validateAllTasks(dispatcher, processEventReceiver);

		dispatcher.start();

		// wait for all tasks to be processed
		checkEndProcess(processEventReceiver);

		// should have unregistered itself from process manager at the end of
		// the process
		verify(processManager).signalProcessCompletion(
				eq(processInstance.getId()));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessDispatchValidation() {

		// replace task validator mock with a custom one
		// This validator rejects first submission of a task, then accepts
		// latter ones
		Answer<Boolean> validatorAnswer = new Answer<Boolean>() {
			Set<String> alreadySubmittedTasks = new HashSet<String>();

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				String taskId = invocation.getArgumentAt(0, String.class);
				if (!alreadySubmittedTasks.contains(taskId)) {
					alreadySubmittedTasks.add(taskId);
					return false;
				} else {
					return true;
				}
			}

		};
		taskValidator = mock(ITaskValidator.class);
		when(
				taskValidator.taskResultIsValid(anyString(), any(Map.class),
						any(Map.class))).thenAnswer(validatorAnswer);

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processInstanceData, processManager, processEventReceiver,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getRepositoryService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				mock(BPMNExplorer.class));

		eventForwarder.setDispatcher(dispatcher);

		// automatically respond to tasks dispatch
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {

				TaskStartSimEvent taskEvent = invocation.getArgumentAt(0,
						TaskStartSimEvent.class);

				verify(processEventReceiver, timeout(5000).times(1))
						.receiveTaskStartEvent(taskEvent);
				assertEquals(taskEvent.task.processId, processInstance.getId());

				// check that the task submission is correctly rejected the
				// first time then accepted the second
				LearnPadTaskSubmissionResult result = dispatcher
						.submitTaskCompletion(taskEvent.task,
								taskEvent.involvedusers.iterator().next(),
								new HashMap<String, Object>());

				assertEquals(TaskSubmissionStatus.REJECTED, result.status);

				result = dispatcher.submitTaskCompletion(taskEvent.task,
						taskEvent.involvedusers.iterator().next(),
						new HashMap<String, Object>());

				assertEquals(TaskSubmissionStatus.VALIDATED, result.status);

				dispatcher.completeTask(taskEvent.task,
						new HashMap<String, Object>(), taskEvent.involvedusers
								.iterator().next(),
						new LearnPadTaskSubmissionResult(
								TaskSubmissionStatus.VALIDATED, 0, 0));

				return null;
			}
		}).when(processEventReceiver).receiveTaskStartEvent(
				any(TaskStartSimEvent.class));

		dispatcher.start();

		// wait for all tasks to be processed
		checkEndProcess(processEventReceiver);

		// check correct number of rejections and validation
		verify(processEventReceiver, times(6)).receiveTaskEndEvent(
				any(TaskEndSimEvent.class));

	}

	/**
	 * Helper method which create the set of roles involved in a task
	 *
	 * @param id
	 * @return the set of roles involved in a task
	 */
	private Set<String> getTaskRolesHelper(String id) {
		Set<String> roles = new HashSet<String>();

		// open the BPMN model of the process
		BpmnModel model = processEngine.getRepositoryService().getBpmnModel(
				processInstance.getProcessDefinitionId());

		for (FlowElement element : model.getMainProcess().getFlowElements()) {
			// filter to keep only user tasks
			if (element instanceof UserTask) {
				UserTask task = (UserTask) element;
				roles.addAll(task.getCandidateUsers());
				roles.addAll(task.getCandidateGroups());
			}
		}

		Set<String> result = new HashSet<String>();

		for (String role : roles) {
			if (!processEngine.getTaskService().createTaskQuery().taskId(id)
					.taskCandidateOrAssigned(role).list().isEmpty()
					|| !processEngine.getTaskService().createTaskQuery()
							.taskId(id).taskCandidateGroup(role).list()
							.isEmpty()) {
				result.add(role);
			}
		}

		return result;
	}

	/**
	 * Helper method to complete a process by validating all tasks
	 *
	 * @param dispatcher
	 * @param processEventReceiver
	 */
	private void validateAllTasks(final ActivitiProcessDispatcher dispatcher,
			final IProcessEventReceiver processEventReceiver) {

		// automatically respond to tasks dispatch
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {

				TaskStartSimEvent taskEvent = invocation.getArgumentAt(0,
						TaskStartSimEvent.class);

				verify(processEventReceiver, timeout(5000).times(1))
						.receiveTaskStartEvent(taskEvent);
				assertEquals(taskEvent.task.processId, processInstance.getId());

				dispatcher.completeTask(taskEvent.task,
						new HashMap<String, Object>(), taskEvent.involvedusers
								.iterator().next(),
						new LearnPadTaskSubmissionResult(
								TaskSubmissionStatus.VALIDATED, 0, 0));

				return null;
			}
		}).when(processEventReceiver).receiveTaskStartEvent(
				any(TaskStartSimEvent.class));

	}

	/**
	 * Helper method to check that the process reaches its end
	 *
	 * @param processEventReceiver
	 * @return
	 */
	private ArgumentCaptor<ProcessEndSimEvent> checkEndProcess(
			IProcessEventReceiver processEventReceiver) {
		ArgumentCaptor<ProcessEndSimEvent> endEvent = ArgumentCaptor
				.forClass(ProcessEndSimEvent.class);

		// since task processing is multithreaded to avoid blocking,
		// we may need to wait a little
		verify(processEventReceiver, timeout(5000)).receiveProcessEndEvent(
				endEvent.capture());

		assertEquals(endEvent.getValue().processInstance.getProcessartifactid(),
				processInstance.getId());

		return endEvent;
	}

	/**
	 * Helper class to emulate the message forwarding behavior of
	 * ActivitiProcessManager
	 *
	 * @author Tom Jorquera - Linagora
	 *
	 */
	private static class EventForwarder implements ActivitiEventListener {

		private final Executor jobHandler = Executors.newSingleThreadExecutor();

		private ActivitiProcessDispatcher dispatcher;

		public void setDispatcher(ActivitiProcessDispatcher dispatcher) {
			this.dispatcher = dispatcher;
		}

		@Override
		public void onEvent(final ActivitiEvent event) {
			if (this.dispatcher != null
					&& (event.getProcessInstanceId() != null)) {
				jobHandler.execute(new Runnable() {
					@Override
					public void run() {
						dispatcher.onEvent(event);
					}
				});
			}
		}

		@Override
		public boolean isFailOnException() {
			return false;
		}

	}
}
