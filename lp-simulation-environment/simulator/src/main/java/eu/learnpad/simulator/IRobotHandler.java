/**
 *
 */
package eu.learnpad.simulator;

import java.util.Set;

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
 * Define the available operations for operating with robots
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IRobotHandler {

	static interface IRobotHandlerProvider {
		public IRobotHandler robotHandler();
	}

	/**
	 * Add a robot as a specific user
	 *
	 * @param userId
	 *            the userId of the robot
	 */
	public void addRobot(String userId);

	/**
	 * Remove a robot
	 *
	 * @param userId
	 *            the userId of the robot
	 */
	public void removeRobot(String userId);

	/**
	 * Get a collection of all robots
	 *
	 * @return the current active robots ids
	 */
	public Set<String> getRobots();

}
