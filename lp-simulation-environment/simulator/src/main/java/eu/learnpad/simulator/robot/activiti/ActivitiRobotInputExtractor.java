/**
 *
 */
package eu.learnpad.simulator.robot.activiti;

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

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.activiti.engine.TaskService;

import eu.learnpad.simulator.processmanager.activiti.ActivitiProcessManager;
import eu.learnpad.simulator.robot.IRobotInputExtractor;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiRobotInputExtractor implements
IRobotInputExtractor<Map<String, Object>> {

	private final TaskService taskService;

	public ActivitiRobotInputExtractor(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.learnpad.simulator.robot.IRobotInputExtractor#getInput(java.lang.String
	 * )
	 */
	@Override
	public Map<String, Object> getState(String taskId) {
		Map<String, Object> cleanedInput = taskService.createTaskQuery()
				.includeProcessVariables().taskId(taskId).singleResult()
				.getProcessVariables();

		// ignore values used only for technical purposes
		cleanedInput.remove(ActivitiProcessManager.SIMULATION_ID_KEY);

		// remove entries with null values
		Set<String> nullKeys = new HashSet<>();

		for (Entry<String, Object> e : cleanedInput.entrySet()) {
			if (e.getValue() == null) {
				nullKeys.add(e.getKey());
			}
		}

		for (String k : nullKeys) {
			cleanedInput.remove(k);
		}

		return cleanedInput;
	}
}
