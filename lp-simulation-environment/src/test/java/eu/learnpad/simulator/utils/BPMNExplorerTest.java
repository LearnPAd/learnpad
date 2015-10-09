/**
 *
 */
package eu.learnpad.simulator.utils;

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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.Main;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class BPMNExplorerTest {

	final static String TEST_PROCESS = "SubprocessExample.bpmn20.xml";

	ProcessEngine processEngine;
	BPMNExplorer explorer;

	@Before
	public void setup() throws FileNotFoundException, SAXException,
			IOException, ParserConfigurationException {

		// Initialize the process engine and load the process
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(Main.class
						.getClassLoader().getResourceAsStream(
								Main.ACTIVITY_CONFIG_PATH));
		processEngine = config.buildProcessEngine();
		String deploymentId = processEngine.getRepositoryService()
				.createDeployment().addClasspathResource(TEST_PROCESS).deploy()
				.getId();
		String processId = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().deploymentId(deploymentId)
				.list().iterator().next().getId();
		String processKey = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().processDefinitionId(processId)
				.singleResult().getKey();

		explorer = new BPMNExplorer(processKey, processEngine
				.getRepositoryService().getBpmnModel(processId));
	}

	@After
	public void teardown() {
		processEngine.close();
	}

	@Test
	public void testTasksList() throws FileNotFoundException, SAXException,
			IOException, ParserConfigurationException {

		// we know the process contains these tasks
		Set<String> expectedTasks = new HashSet<String>();
		for (int i = 1; i <= 8; i++) {
			expectedTasks.add("usertask" + i);
		}

		Set<String> tasks = explorer.getUserTasks();

		assertTrue(tasks.containsAll(expectedTasks));
		assertTrue(expectedTasks.containsAll(tasks));

	}

	@Test
	public void testSubprocess() throws FileNotFoundException, SAXException,
			IOException, ParserConfigurationException {

		// we know this to be correct
		Map<String, String> expectedResult = new HashMap<String, String>();
		expectedResult.put("usertask1", null);
		expectedResult.put("usertask2", "subprocess1");
		expectedResult.put("usertask3", "subprocess2");
		expectedResult.put("usertask4", "subprocess2");
		expectedResult.put("usertask5", "subprocess2");
		expectedResult.put("usertask6", "subprocess3");
		expectedResult.put("usertask7", "subprocess3");
		expectedResult.put("usertask8", null);

		for (String task : explorer.getUserTasks()) {
			String subProcess = explorer.getSubprocess(task);

			if (subProcess != null) {
				assertTrue(subProcess.equals(expectedResult.get(task)));
			} else {
				assertTrue(expectedResult.get(task) == null);
			}

		}

	}
}
