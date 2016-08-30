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
 * Defines a helper interface to extract the input associated with a task, in
 * order to provide it to a robot
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IRobotInputExtractor<TaskInput> {

	/**
	 * Get the task input associated with a task
	 *
	 * @param taskId
	 *            the id of the involved task
	 * @return the task input
	 */
	public TaskInput getState(String taskId);

}
