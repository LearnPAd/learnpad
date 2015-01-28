/**
 *
 */
package activitipoc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.util.json.JSONObject;
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
	private final FormService formService;
	private final ITaskRouter router;
	private final List<UIServlet> users;

	private final Map<Task, ServletHolder> waitingTasksHolders = new HashMap<Task, ServletHolder>();

	/**
	 * @param webserver
	 * @param process
	 * @param taskService
	 */
	public ProcessDispatcher(WebServer webserver, ProcessInstance process,
			TaskService taskService, FormService formService,
			RuntimeService runtimeService, ITaskRouter router,
			List<UIServlet> users) {
		super();
		this.webserver = webserver;
		this.process = process;
		this.taskService = taskService;
		this.formService = formService;
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
			waitingTasksHolders.put(task, webserver.addTaskServlet(
					new TaskServlet(this, task, router.route(task, users),
							formService), task.getId()));
		}
	}

	void completeProcess() {
		System.out.println("Process " + process.getId() + " finished");
		for (UIServlet ui : users) {
			ui.completeProcess();
		}
	}

	public void completeTask(Task task, String data) {

		webserver.removeServletHolder(waitingTasksHolders.get(task));
		waitingTasksHolders.remove(task);

		taskService.complete(task.getId(), parseTaskVariables(data));

		// check for newly triggered tasks
		List<Task> waitingTasks = taskService.createTaskQuery()
				.processInstanceId(process.getId()).list();

		// ignore already processed tasks
		waitingTasks.removeAll(waitingTasksHolders.keySet());

		if (!waitingTasks.isEmpty()) {
			processNewTasks(waitingTasks);
		}
	}

	/**
	 * Transform the given data string into a map of key-values corresponding to
	 * the data for a task
	 *
	 * @param data
	 * @return
	 */
	private Map<String, Object> parseTaskVariables(String data) {
		Map<String, Object> result = new HashMap<String, Object>();

		JSONObject jObject = new JSONObject(data);
		Iterator<?> keys = jObject.keys();

		while (keys.hasNext()) {

			String key = (String) keys.next();
			Object value = jObject.get(key);
			result.put(key, value);
		}

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
