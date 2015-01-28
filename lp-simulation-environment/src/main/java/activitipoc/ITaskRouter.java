/**
 *
 */
package activitipoc;

import java.util.List;
import java.util.Set;

import org.activiti.engine.task.Task;

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
	 *            a list of candidate users id (order may matter)
	 * @return the set of users id to which the task must be routed
	 */
	public Set<String> route(Task task, List<String> candidates);

}
