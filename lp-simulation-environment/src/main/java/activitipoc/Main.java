/**
 *
 */
package activitipoc;

import java.io.IOException;
import java.util.Arrays;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

import activitipoc.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler;
import activitipoc.processmanager.activiti.ActivitiProcessManager;
import activitipoc.uihandler.webserver.UIHandlerWebImpl;

class Main {

	public static final String ACTIVITY_CONFIG_PATH = "activiti.cfg.xml";
	public static final String DEMO_PROCESS_FOLDER = "process";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// launch activiti process engine
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(Main.class
						.getClassLoader().getResourceAsStream(
								ACTIVITY_CONFIG_PATH));

		ProcessEngine processEngine = config.buildProcessEngine();

		IProcessManager processManager = new ActivitiProcessManager(
				processEngine);

		// load process definitions
		processManager.addProjectDefinitions(DEMO_PROCESS_FOLDER
				+ "/VacationRequest.bpmn20.xml");
		processManager.addProjectDefinitions(DEMO_PROCESS_FOLDER
				+ "/demo.bpmn20.xml");

		// create users ui handler and add users
		new UIHandlerWebImpl(Arrays.asList("user1", "user2"), processManager,
				new ActivitiToJsonFormFormHandler(
						processEngine.getFormService()));

	}
}
