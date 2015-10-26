/**
 *
 */
package eu.learnpad.simulator.robot;

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

/**
 *
 * @author Tom Jorquera - Linagora
 *
 * @param <TaskInput>
 *            the type of data received as input for tasks
 * @param <TaskOutput>
 *            the type of results expected for tasks
 */
public interface IRobot<TaskInput, TaskOutput> {

	/**
	 *
	 * @param taskId
	 *            the ID of the task to handle
	 * @param inputData
	 *            the input data accompanying the task
	 *
	 * @return the task result proposed by the robot
	 *
	 */
	public TaskOutput handleTask(String taskId, TaskInput inputData);

}
