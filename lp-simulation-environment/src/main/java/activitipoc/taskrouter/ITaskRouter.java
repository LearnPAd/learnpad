/**
 *
 */
package activitipoc.taskrouter;

import java.util.List;

import org.activiti.engine.task.Task;

import activitipoc.webserver.UIServlet;

/**
 * @author jorquera
 *
 */
public interface ITaskRouter {

	public UIServlet route(Task task, List<UIServlet> candidates);

}
