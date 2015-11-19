/**
 *
 */
package eu.learnpad.simulator.processmanager.activiti.workaround.smsg;

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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import eu.learnpad.simulator.Main;
import eu.learnpad.simulator.processmanager.activiti.workarounds.msg.BPMNTransformer;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class BPMNTransformerTest {

	static ProcessEngine processEngine;

	@BeforeClass
	public static void initActivitiEngine() {
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(Main.class
						.getClassLoader().getResourceAsStream(
								Main.ACTIVITY_CONFIG_PATH));

		// create process engine
		processEngine = config.buildProcessEngine();

	}

	@AfterClass
	public static void shutdownEngine() {
		processEngine.close();
	}

	@Before
	public void instanciateTestProcess() {

	}

	@Test
	public void testMsgRewriteTasks() throws XPathExpressionException,
	TransformerConfigurationException, SAXException, IOException,
	ParserConfigurationException, TransformerException,
	TransformerFactoryConfigurationError {

		testLoadProcess("collaboration_msg_with_tasks.bpmn20.xml", 2);

	}

	@Test
	public void testMsgRewriteEvents() throws XPathExpressionException,
			TransformerConfigurationException, SAXException, IOException,
			ParserConfigurationException, TransformerException,
			TransformerFactoryConfigurationError {

		testLoadProcess("collaboration_msg_with_events.bpmn20.xml", 2);

	}

	private void testLoadProcess(String processFile, int expectedNbProcesses)
			throws XPathExpressionException, TransformerConfigurationException,
			SAXException, IOException, ParserConfigurationException,
			TransformerException, TransformerFactoryConfigurationError {

		// transform process
		InputStream in = BPMNTransformer.transform(this.getClass()
				.getClassLoader().getResourceAsStream(processFile));

		// load test process

		String deploymentId = processEngine.getRepositoryService()
				.createDeployment().addInputStream("test.bpmn20.xml", in)
				.deploy().getId();

		List<ProcessDefinition> res = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().deploymentId(deploymentId)
				.list();

		assertEquals(res.size(), expectedNbProcesses);

	}

}
