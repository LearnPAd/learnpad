/**
 *
 */
package activitipoc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import activitipoc.taskrouter.ITaskRouter;
import activitipoc.webserver.UIServlet;
import activitipoc.webserver.WebServer;

/**
 * @author jorquera
 *
 */
public class Main {

	public static final String ACTIVITY_CONFIG_PATH = "src/main/resources/activiti.cfg.xml";
	public static final String DEMO_PROCESS_FILE = "VacationRequest.bpmn20.xml";

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// launch task webserver
		WebServer webserver = new WebServer(8081, "ui", "tasks");

		// launch activiti process engine
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(new FileInputStream(
						ACTIVITY_CONFIG_PATH));

		ProcessEngine processEngine = config.buildProcessEngine();

		// load new process
		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		repositoryService.createDeployment()
		.addClasspathResource(DEMO_PROCESS_FILE).deploy();

		System.out.println("Number of process definitions: "
				+ repositoryService.createProcessDefinitionQuery().count());

		// Instantiate process
		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Tom");
		variables.put("numberOfDays", new Integer(7));
		variables.put("vacationMotivation", "I'm really tired!");

		ProcessInstance process = runtimeService.startProcessInstanceByKey(
				"vacationRequest", variables);

		// Verify that we started a new process instance
		System.out.println("Number of process instances: "
				+ runtimeService.createProcessInstanceQuery().count());

		final TaskService taskService = processEngine.getTaskService();

		// create users

		UIServlet ui1 = new UIServlet("user1");
		webserver.addUIServlet(ui1, "user1");

		UIServlet ui2 = new UIServlet("user2");
		webserver.addUIServlet(ui2, "user2");

		// launch process dispatcher
		new ProcessDispatcher(webserver, process, taskService,
				processEngine.getFormService(),
				processEngine.getRuntimeService(), new ITaskRouter() {

			public List<UIServlet> route(Task task,
					List<UIServlet> candidates) {

				List<UIServlet> result = new ArrayList<UIServlet>();

				if (!taskService.createTaskQuery()
						.taskCandidateOrAssigned("Tom")
						.taskId(task.getId()).list().isEmpty()) {

					result.add(candidates.get(0));

				}

				if (!taskService.createTaskQuery()
						.taskCandidateGroup("management")
						.taskId(task.getId()).list().isEmpty()) {

					result.add(candidates.get(1));

				}

				if (result.isEmpty()) {
					throw new RuntimeException("Could not route task "
							+ task + " to any user");
				} else {
					return result;
				}

			}

		}, Arrays.asList(ui1, ui2));
	}
}
