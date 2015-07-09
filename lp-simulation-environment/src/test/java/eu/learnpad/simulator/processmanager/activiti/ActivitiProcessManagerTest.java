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

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.Main;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiProcessManagerTest {
	static ProcessEngine processEngine;

	public final static String TEST_PROCESS = "test.bpmn20.xml";

	public final static List<String> TEST_PROCESS_USES = Arrays.asList("user1",
			"user2", "user3");

	@Before
	public void initActivitiEngine() {
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(Main.class
						.getClassLoader().getResourceAsStream(
								Main.ACTIVITY_CONFIG_PATH));

		// create process engine
		processEngine = config.buildProcessEngine();

	}

	@After
	public void shutdownEngine() {
		processEngine.close();
	}

	@Test
	public void testProcessDefinition() throws FileNotFoundException {
		ActivitiProcessManager manager = new ActivitiProcessManager(
				processEngine,
				mock(IProcessEventReceiver.IProcessEventReceiverProvider.class));

		assertTrue(manager.getAvailableProcessDefintion().size() == 0);

		manager.addProjectDefinitions(TEST_PROCESS);

		assertTrue(manager.getAvailableProcessDefintion().size() == 1);

		String processDefinitionId = manager.getAvailableProcessDefintion()
				.iterator().next();

		assertTrue(manager.getProcessDefinitionName(processDefinitionId)
				.equals("Test process"));
	}

	@Test
	public void testProcessDefinitionRoles() throws FileNotFoundException {
		ActivitiProcessManager manager = new ActivitiProcessManager(
				processEngine,
				mock(IProcessEventReceiver.IProcessEventReceiverProvider.class));

		String processDefinitionId = manager
				.addProjectDefinitions(TEST_PROCESS).iterator().next();

		assertTrue(manager.getProcessDefinitionGroupRoles(processDefinitionId)
				.containsAll(Arrays.asList("user1", "user2", "user3"))
				&& manager.getProcessDefinitionGroupRoles(processDefinitionId)
				.size() == 3);

		assertTrue(manager.getProcessDefinitionSingleRoles(processDefinitionId)
				.contains("user0"));
	}

	@SuppressWarnings({ "serial", "unchecked" })
	@Test
	public void testProcessInstantation() throws FileNotFoundException {

		final IProcessEventReceiver processEventReceiver = mock(IProcessEventReceiver.class);

		IProcessEventReceiver.IProcessEventReceiverProvider provider = new IProcessEventReceiver.IProcessEventReceiverProvider() {

			public IProcessEventReceiver processEventReceiver() {
				return processEventReceiver;
			}
		};

		ActivitiProcessManager manager = new ActivitiProcessManager(
				processEngine, provider);

		String processDefinitionId = manager
				.addProjectDefinitions(TEST_PROCESS).iterator().next();

		assertTrue(manager.getCurrentProcessInstances().size() == 0);

		String processInstanceId = manager.startProjectInstance(
				processDefinitionId, null,
				Arrays.asList("user1", "user2", "user3"),
				new HashMap<String, Collection<String>>() {
					{
						put("user1", Arrays.asList("user1"));
						put("user2", Arrays.asList("user2"));
						put("user3", Arrays.asList("user3"));

					}
				});

		// if we stop right now, this will cause an exception during
		// shutdownEngine (the test still pass). This is because the code to
		// submit tasks is done in a different thread, and may try to access the
		// activiti database *after* it has shutdown. The verify allows us to
		// "block" until the task submission thread has done its job.
		// 5 seconds should be *far more* than enough for this.
		verify(processEventReceiver, timeout(5000)).sendTask(
				eq(processInstanceId), any(String.class), any(String.class),
				any(String.class), any(Collection.class));
		;

	}
}
