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

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.Main;
import eu.learnpad.simulator.processmanager.ITaskRouter;
import eu.learnpad.simulator.processmanager.ITaskValidator;
import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.processmanager.activiti.taskrouter.ActivitiTaskRouter;

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

	ProcessInstance processInstance;

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

	}

	@AfterClass
	public static void shutdownEngine() {
		processEngine.close();
	}

	@Before
	public void instanciateTestProcess() {
		// start process
		processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey(TEST_PROCESS_KEY);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessDispatcherInit() {

		ActivitiProcessManager processManger = mock(ActivitiProcessManager.class);
		IProcessEventReceiver processEventReceiver = mock(IProcessEventReceiver.class);

		new ActivitiProcessDispatcher(processManger, processEventReceiver,
				processInstance, processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getHistoryService(), mock(ITaskRouter.class),
				mock(ITaskValidator.class), TEST_PROCESS_USES);

		// dispatcher should have dispatched first task
		// (since task processing is multithreaded to avoid blocking,
		// we may need to wait a little. 5 sec should be *far* more than
		// enough)
		verify(processEventReceiver, timeout(5000)).sendTask(
				eq(processInstance.getId()), anyString(), anyString(),
				anyString(), any(Collection.class));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessDispatcherDispatch() throws InterruptedException {

		ActivitiProcessManager processManger = mock(ActivitiProcessManager.class);
		IProcessEventReceiver processEventReceiver = mock(IProcessEventReceiver.class);
		ITaskRouter taskRouter = mock(ITaskRouter.class);
		ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator = mock(ITaskValidator.class);

		when(taskRouter.route(any(Task.class))).thenReturn(
				new HashSet<String>(Arrays.asList("user1")));

		// automatically validate tasks
		when(
				taskValidator.taskResultIsValid(anyString(), any(Map.class),
						any(Map.class))).thenReturn(true);

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processManger, processEventReceiver, processInstance,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				TEST_PROCESS_USES);

		validateAllTasks(dispatcher, processEventReceiver);

		// should have processed 6 tasks in total
		verify(processEventReceiver, times(6)).sendTask(
				eq(processInstance.getId()), anyString(), anyString(),
				anyString(), any(Collection.class));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessDispatcherRouting() throws InterruptedException {

		@SuppressWarnings("serial")
		final Map<String, Collection<String>> routes = new HashMap<String, Collection<String>>() {
			{
				put("user1", Arrays.asList("user1, user2, user3"));
				put("user2", Arrays.asList("user2, user3"));
				put("user3", Arrays.asList("user3"));
				put("user0", Arrays.asList("user3"));
			}
		};

		ITaskRouter taskRouter = new ActivitiTaskRouter(
				processEngine.getTaskService(), routes);

		@SuppressWarnings("rawtypes")
		final ArgumentCaptor<Collection> userRoute = ArgumentCaptor
		.forClass(Collection.class);

		ActivitiProcessManager processManger = mock(ActivitiProcessManager.class);
		IProcessEventReceiver processEventReceiver = mock(IProcessEventReceiver.class);

		// automatically validate tasks
		ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator = mock(ITaskValidator.class);
		when(
				taskValidator.taskResultIsValid(anyString(), any(Map.class),
						any(Map.class))).thenReturn(true);

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processManger, processEventReceiver, processInstance,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				TEST_PROCESS_USES);

		// dispatcher should have dispatched first task
		final ArgumentCaptor<String> taskId = ArgumentCaptor
				.forClass(String.class);
		verify(processEventReceiver, timeout(5000).times(1)).sendTask(
				eq(processInstance.getId()), taskId.capture(), anyString(),
				anyString(), userRoute.capture());

		// check task is correctly routed to users
		Set<String> expectedUserRoutes = new HashSet<String>();
		for (String role : getTaskRolesHelper(taskId.getValue())) {
			expectedUserRoutes.addAll(routes.get(role));
		}

		// both sets should be equal
		assertTrue(userRoute.getValue().containsAll(expectedUserRoutes)
				&& expectedUserRoutes.containsAll(userRoute.getValue()));

		// automatically respond to next tasks dispatch
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {

				Collection<String> receivedRoutes = invocation.getArgumentAt(4,
						Collection.class);

				// check task is correctly routed to users
				Set<String> expectedRoutes = new HashSet<String>();
				for (String role : getTaskRolesHelper(invocation.getArgumentAt(
						1, String.class))) {
					expectedRoutes.addAll(routes.get(role));
				}

				// both sets should be equal
				assertTrue(receivedRoutes.containsAll(expectedRoutes)
						&& expectedRoutes.containsAll(receivedRoutes));

				dispatcher.submitTaskCompletion(
						invocation.getArgumentAt(1, String.class), null);
				return null;
			}
		}).when(processEventReceiver).sendTask(anyString(), anyString(),
				anyString(), anyString(), any(Collection.class));

		// respond to first task dispatch
		dispatcher.submitTaskCompletion(taskId.getValue(), null);

		// wait for all tasks to be processed
		// (again, since task processing is multithreaded to avoid blocking,
		// we may need to wait a little)
		verify(processEventReceiver, timeout(5000)).signalProcessEnd(
				eq(processInstance.getId()), any(Collection.class));

	}

	@SuppressWarnings("unchecked")
	public void testProcessDispatcherSignalProcessEndToUsers()
			throws InterruptedException {

		ActivitiProcessManager processManager = mock(ActivitiProcessManager.class);
		ITaskRouter taskRouter = mock(ITaskRouter.class);
		IProcessEventReceiver processEventReceiver = mock(IProcessEventReceiver.class);

		when(taskRouter.route(any(Task.class))).thenReturn(
				new HashSet<String>(Arrays.asList("user1")));

		// automatically validate tasks
		ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator = mock(ITaskValidator.class);
		when(
				taskValidator.taskResultIsValid(anyString(), any(Map.class),
						any(Map.class))).thenReturn(true);

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processManager, processEventReceiver, processInstance,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				TEST_PROCESS_USES);

		validateAllTasks(dispatcher, processEventReceiver);

		// check that the signal for process end is send to all the required
		// users

		@SuppressWarnings("rawtypes")
		final ArgumentCaptor<Collection> notifiedUsers = ArgumentCaptor
				.forClass(Collection.class);

		verify(processEventReceiver, timeout(5000)).signalProcessEnd(
				eq(processInstance.getId()), notifiedUsers.capture());

		assertTrue(notifiedUsers.getValue().size() == TEST_PROCESS_USES.size());
		assertTrue(notifiedUsers.getValue().containsAll(TEST_PROCESS_USES));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessDispatcherUnregister() throws InterruptedException {

		ActivitiProcessManager processManager = mock(ActivitiProcessManager.class);
		IProcessEventReceiver processEventReceiver = mock(IProcessEventReceiver.class);
		ITaskRouter taskRouter = mock(ITaskRouter.class);
		ITaskValidator<Map<String, Object>, Map<String, Object>> taskValidator = mock(ITaskValidator.class);

		when(taskRouter.route(any(Task.class))).thenReturn(
				new HashSet<String>(Arrays.asList("user1")));

		// automatically validate tasks
		when(
				taskValidator.taskResultIsValid(anyString(), any(Map.class),
						any(Map.class))).thenReturn(true);

		final ActivitiProcessDispatcher dispatcher = new ActivitiProcessDispatcher(
				processManager, processEventReceiver, processInstance,
				processEngine.getTaskService(),
				processEngine.getRuntimeService(),
				processEngine.getHistoryService(), taskRouter, taskValidator,
				TEST_PROCESS_USES);

		// reach end of process
		validateAllTasks(dispatcher, processEventReceiver);

		// should have unregistered itself from process manager at the end of
		// the process
		verify(processManager).removeDispatcher(eq(processInstance.getId()));

	}

	/**
	 * Helper method which create the set of roles involved in a task
	 *
	 * @param taskId
	 * @return the set of roles involved in a task
	 */
	private Set<String> getTaskRolesHelper(String taskId) {
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
			if (!processEngine.getTaskService().createTaskQuery()
					.taskId(taskId).taskCandidateOrAssigned(role).list()
					.isEmpty()
					|| !processEngine.getTaskService().createTaskQuery()
							.taskId(taskId).taskCandidateGroup(role).list()
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
	@SuppressWarnings("unchecked")
	private void validateAllTasks(final ActivitiProcessDispatcher dispatcher,
			IProcessEventReceiver processEventReceiver) {
		// we capture the task id to respond to task
		ArgumentCaptor<String> taskId = ArgumentCaptor.forClass(String.class);
		verify(processEventReceiver, timeout(5000).times(1)).sendTask(
				eq(processInstance.getId()), taskId.capture(), anyString(),
				anyString(), any(Collection.class));

		// automatically respond to next tasks dispatch
		doAnswer(new Answer<Void>() {
			public Void answer(InvocationOnMock invocation) throws Throwable {
				dispatcher.submitTaskCompletion(
						invocation.getArgumentAt(1, String.class), null);
				return null;
			}
		}).when(processEventReceiver).sendTask(anyString(), anyString(),
				anyString(), anyString(), any(Collection.class));

		// respond to first task dispatch
		dispatcher.submitTaskCompletion(taskId.getValue(), null);

		// wait for all tasks to be processed
		// (again, since task processing is multithreaded to avoid blocking,
		// we may need to wait a little)
		verify(processEventReceiver, timeout(5000)).signalProcessEnd(
				eq(processInstance.getId()), any(Collection.class));
	}
}
