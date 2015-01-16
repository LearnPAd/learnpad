/**
 *
 */
package activitipoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.eclipse.jetty.servlet.ServletHolder;

import activitipoc.taskrouter.ITaskRouter;
import activitipoc.webserver.TaskServlet;
import activitipoc.webserver.UIServlet;
import activitipoc.webserver.WebServer;

/**
 * @author jorquera
 *
 */
public class ProcessDispatcher implements ActivitiEventListener {

	private final WebServer webserver;
	private final ProcessInstance process;
	private final TaskService taskService;
	private final ITaskRouter router;
	private final List<UIServlet> users;

	private final Map<Task, ServletHolder> waitingTasksHolders = new HashMap<Task, ServletHolder>();
	private final Map<Task, UIServlet> taskToUIroute = new HashMap<Task, UIServlet>();

	/**
	 * @param webserver
	 * @param process
	 * @param taskService
	 */
	public ProcessDispatcher(WebServer webserver, ProcessInstance process,
			TaskService taskService, RuntimeService runtimeService,
			ITaskRouter router, List<UIServlet> users) {
		super();
		this.webserver = webserver;
		this.process = process;
		this.taskService = taskService;
		this.router = router;
		this.users = users;

		List<Task> tasks = taskService.createTaskQuery()
				.processInstanceId(process.getId()).list();

		if (tasks.isEmpty()) {
			throw new RuntimeException("Process without waiting task");
		} else {
			processNewTasks(tasks);
		}

		runtimeService.addEventListener(this,
				ActivitiEventType.PROCESS_COMPLETED);

		System.out.println("Created dispatcher for process " + process.getId());

	}

	void processNewTasks(List<Task> tasks) {
		for (Task task : tasks) {
			waitingTasksHolders.put(
					task,
					webserver.addTaskServlet(new TaskServlet(this, task),
							task.getId()));

			// route task to correct ui
			UIServlet ui = router.route(task, users);
			taskToUIroute.put(task, ui);
			ui.addTask(task.getId());
		}
	}

	void completeProcess() {
		System.out.println("Process " + process.getId() + " finished");
		// TODO: do what ?
		for (UIServlet ui : users) {
			ui.completeProcess();
		}
	}

	public void completeTask(Task task, String data) {

		webserver.removeServletHolder(waitingTasksHolders.get(task));
		waitingTasksHolders.remove(task);

		taskToUIroute.get(task).removeTask(task.getId());
		taskToUIroute.remove(task);

		taskService.complete(task.getId(), parseTaskVariables(data));

		// check for newly triggered tasks
		List<Task> tasks = taskService.createTaskQuery()
				.processInstanceId(process.getId()).list();

		// ignore already processed tasks
		tasks.removeAll(waitingTasksHolders.keySet());

		if (!tasks.isEmpty()) {
			processNewTasks(tasks);
		}
	}

	private int iteration = 0;

	private Map<String, Object> parseTaskVariables(String data) {
		Map<String, Object> result = new HashMap<String, Object>();

		// TODO: really parse data and remove this
		result.put("vacationApproved", iteration < 2 ? "false" : "true");
		result.put("managerMotivation", "More work to do on LearnPAd!");
		result.put("resendRequest", iteration < 2 ? "true" : "false");

		iteration++;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.
	 * activiti.engine.delegate.event.ActivitiEvent)
	 */
	public void onEvent(ActivitiEvent event) {
		if (event.getProcessInstanceId().equals(process.getId())
				&& event.getType().equals(ActivitiEventType.PROCESS_COMPLETED)) {
			completeProcess();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#isFailOnException
	 * ()
	 */
	public boolean isFailOnException() {
		return false;
	}
}
