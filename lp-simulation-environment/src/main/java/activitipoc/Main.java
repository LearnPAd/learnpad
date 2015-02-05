/**
 *
 */
package activitipoc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

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

		final TaskService taskService = processEngine.getTaskService();

		// create users ui handler
		IUIHandler uiHandler = new UIHandlerWebImpl(Arrays.asList("user1",
				"user2"), new ActivitiToJsonFormFormHandler(
				processEngine.getFormService()));

		// launch process dispatcher
		ITaskRouter router = new ITaskRouter() {
			List<String> candidates = Arrays.asList("user1", "user2");

			public Set<String> route(Task task) {

				Set<String> result = new HashSet<String>();

				if (!taskService.createTaskQuery()
						.taskCandidateOrAssigned("Tom").taskId(task.getId())
						.list().isEmpty()) {

					result.add(candidates.get(0));

				}

				if (!taskService.createTaskQuery()
						.taskCandidateGroup("management").taskId(task.getId())
						.list().isEmpty()) {

					result.add(candidates.get(1));

				}

				if (result.isEmpty()) {
					throw new RuntimeException("Could not route task " + task
							+ " to any user");
				} else {
					return result;
				}

			}

		};

		processManager.startProjectInstance(processId, parameters, router,
				Arrays.asList("user1", "user2"), uiHandler);
	}
}
