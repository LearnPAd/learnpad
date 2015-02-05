/**
 *
 */
package activitipoc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

import activitipoc.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler;
import activitipoc.processmanager.activiti.ActivitiProcessManager;
import activitipoc.uihandler.webserver.UIHandlerWebImpl;

class Main {

	public static final String ACTIVITY_CONFIG_PATH = "src/main/resources/activiti.cfg.xml";
	public static final String DEMO_PROCESS_FILE = "VacationRequest.bpmn20.xml";

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// launch activiti process engine
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(new FileInputStream(
						ACTIVITY_CONFIG_PATH));

		ProcessEngine processEngine = config.buildProcessEngine();

		IProcessManager processManager = new ActivitiProcessManager(
				processEngine);

		// load process definition
		String processId = processManager
				.addProjectDefinitions(DEMO_PROCESS_FILE).iterator().next();

		// instantiate process

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("employeeName", "Tom");
		parameters.put("numberOfDays", new Integer(7));
		parameters.put("vacationMotivation", "I'm really tired!");

		// create users ui handler
		IUIHandler uiHandler = new UIHandlerWebImpl(Arrays.asList("user1",
				"user2"), new ActivitiToJsonFormFormHandler(
				processEngine.getFormService()));

		// launch process dispatcher
		Map<String, Collection<String>> routes = new HashMap<String, Collection<String>>();
		routes.put("Tom", Arrays.asList("user1"));
		routes.put("management", Arrays.asList("user2"));

		processManager.startProjectInstance(processId, parameters, routes,
				uiHandler);
	}
}
