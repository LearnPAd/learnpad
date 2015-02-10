/**
 *
 */
package activitipoc.taskrouter.activiti;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import activitipoc.ITaskRouter;

/**
 * @author jorquera
 *
 */
public class ActivitiTaskRouter implements ITaskRouter {

	private final TaskService taskService;
	private final Map<String, Collection<String>> processRoleToUser;

	/**
	 * @param taskService
	 * @param taskRoleToUser
	 *            a mapping from process roles to users (one role can correspond
	 *            to several users)
	 */
	public ActivitiTaskRouter(TaskService taskService,
			Map<String, Collection<String>> processRoleToUser) {
		super();
		this.taskService = taskService;
		this.processRoleToUser = processRoleToUser;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.ITaskRouter#route(org.activiti.engine.task.Task,
	 * java.util.List)
	 */
	public Set<String> route(Task task) {
		Set<String> result = new HashSet<String>();

		for (String role : processRoleToUser.keySet()) {
			if (!taskService.createTaskQuery().taskId(task.getId())
					.taskCandidateOrAssigned(role).list().isEmpty()
					|| !taskService.createTaskQuery().taskId(task.getId())
					.taskCandidateGroup(role).list().isEmpty()) {
				result.addAll(processRoleToUser.get(role));
			}
		}

		if (result.isEmpty()) {
			// We should throw the following exception here. But since at this
			// point the demo does not allow to define routes, we provide a
			// default route to the first user associated with the first role
			// throw new RuntimeException("Could not route task " + task
			// + " to any user");
			result.add(processRoleToUser.values().iterator().next().iterator()
					.next());
			return result;
		} else {
			return result;
		}
	}
}
