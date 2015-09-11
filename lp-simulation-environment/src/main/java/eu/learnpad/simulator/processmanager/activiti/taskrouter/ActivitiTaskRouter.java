/**
 *
 */
package eu.learnpad.simulator.processmanager.activiti.taskrouter;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import eu.learnpad.simulator.processmanager.ITaskRouter;

/**
 * @author Tom Jorquera - Linagora
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
