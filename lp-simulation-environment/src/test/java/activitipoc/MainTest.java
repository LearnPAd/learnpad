/**
 *
 */
package activitipoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

import activitipoc.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler;
import activitipoc.processmanager.activiti.ActivitiProcessManager;
import activitipoc.uihandler.webserver.UIHandlerWebImpl;

/**
 * @author jorquera
 *
 */
public class MainTest {

	@Test
	public void testCorrectBoot() {

		try {
			// launch activiti process engine
			ProcessEngineConfiguration config = ProcessEngineConfiguration
					.createProcessEngineConfigurationFromInputStream(Main.class
							.getClassLoader().getResourceAsStream(
									Main.ACTIVITY_CONFIG_PATH));

			ProcessEngine processEngine = config.buildProcessEngine();

			IProcessManager processManager = new ActivitiProcessManager(
					processEngine);

			// load process definitions
			processManager.addProjectDefinitions(Main.DEMO_PROCESS_FOLDER
					+ "/VacationRequest.bpmn20.xml");
			processManager.addProjectDefinitions(Main.DEMO_PROCESS_FOLDER
					+ "/demo.bpmn20.xml");

			// check loaded processes
			assertEquals(processManager.getAvailableProcessDefintion().size(),
					2);

			// create users ui handler and add users
			new UIHandlerWebImpl(Main.PORT, Arrays.asList("sarah", "tom"),
					processManager, new ActivitiToJsonFormFormHandler(
							processEngine.getFormService())).stop();

			processEngine.close();

		} catch (Exception e) {
			System.err.println(e);
			fail("got exception during setup");
		}
	}
}
