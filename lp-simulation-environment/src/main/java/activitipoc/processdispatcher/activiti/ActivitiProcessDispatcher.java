/**
 *
 */
package activitipoc.processdispatcher.activiti;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import activitipoc.IProcessDispatcher;
import activitipoc.ITaskRouter;
import activitipoc.IUIHandler;

/**
 * @author jorquera
 *
 */
public class ActivitiProcessDispatcher implements IProcessDispatcher,
		ActivitiEventListener {

	private final ProcessInstance process;
	private final TaskService taskService;
	private final ITaskRouter router;
	private final RuntimeService runtimeService;

	private final IUIHandler uiHandler;

	private final Set<String> registeredWaitingTasks = new HashSet<String>();

	// Ok... due to some weirdness in the way activiti fire end process signals
	// we have to do some weird things here. The activiti workflow when
	// processing the last task seems to be the following:
	// 1. Start processing task
	// 2. Notice process is finished, fire PROCESS_FINISHED signal
	// 3. Finish processing task
	//
	// If we are not careful and try to terminate the process too soon, the last
	// task is not completely processed and activiti may freak out (concurrent
	// modification exception)
	// So we set a boolean when we notice a process is finished
	// (during onEvent). When completing tasks (completeTask) we terminate by
	// checking if the boolean is set to true. In this case we know that we can
	// safely end the process.
	//
	// NOTE: the two mentioned methods are set to synchronized. This does not
	// seems to be currently necessary as activiti seems to handle all of this
	// into a single thread, but better save than sorry.
	//
	// NOTE: If this does not work anymore, it may be due to a change in the way
	// activiti process the last task. In this case removing this boolean (and
	// the synchronized) and simply calling completeProcess() during onEvent()
	// could be sufficient (if things are sane on activiti side).
	private boolean processFinished = false;

	/**
	 * @param webserver
	 * @param process
	 * @param taskService
	 */
	public ActivitiProcessDispatcher(ProcessInstance process,
			TaskService taskService, RuntimeService runtimeService,
			ITaskRouter router, IUIHandler uiHandler) {
		super();
		this.process = process;
		this.taskService = taskService;
		this.router = router;
		this.runtimeService = runtimeService;
		this.uiHandler = uiHandler;

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

	private void processNewTasks(List<Task> tasks) {
		for (Task task : tasks) {
			registeredWaitingTasks.add(task.getId());
			uiHandler.sendTask(task.getProcessInstanceId(), task.getId(),
					task.getDescription(), router.route(task));
		}

	}

	private void completeProcess() {

		// signal process end to users
		uiHandler.signalProcessEnd(process.getId());

		// unsubscribe to events
		runtimeService.removeEventListener(this);

		System.out.println("Process " + process.getId() + " finished");
	}

	public synchronized void completeTask(String taskId,
			Map<String, Object> data) {

		taskService.complete(taskId, data);

		registeredWaitingTasks.remove(taskId);

		// check for newly triggered tasks
		List<Task> waitingTasks = taskService.createTaskQuery()
				.processInstanceId(process.getId()).list();

		// ignore already processed tasks
		List<Task> newTasks = new ArrayList<Task>();
		for (Task t : waitingTasks) {
			if (!registeredWaitingTasks.contains(t.getId())) {
				newTasks.add(t);
			}
		}

		if (!newTasks.isEmpty()) {
			processNewTasks(newTasks);
		}

		// see comment on processFinished declaration
		if (processFinished) {
			completeProcess();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.activiti.engine.delegate.event.ActivitiEventListener#onEvent(org.
	 * activiti.engine.delegate.event.ActivitiEvent)
	 */
	public synchronized void onEvent(ActivitiEvent event) {
		if (event.getProcessInstanceId().equals(process.getId())
				&& event.getType().equals(ActivitiEventType.PROCESS_COMPLETED)) {

			// see comment on processFinished declaration
			processFinished = true;
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
