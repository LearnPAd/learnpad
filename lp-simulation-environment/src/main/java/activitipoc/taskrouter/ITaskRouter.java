/**
 *
 */
package activitipoc.taskrouter;

import java.util.List;

import org.activiti.engine.task.Task;

import activitipoc.webserver.UIServlet;

/**
 * Interface for the routing function.
 *
 * The sole purpose of this interface is to provide a mapping between tasks and
 * users. It should be instantiated process-by-process.
 *
 * @author jorquera
 *
 */
public interface ITaskRouter {

	/**
	 *
	 * @param task
	 *            the task to be routed
	 * @param candidates
	 *            a list of candidate users
	 * @return the list of users to which the task must be routed
	 */
	public List<UIServlet> route(Task task, List<UIServlet> candidates);

}
