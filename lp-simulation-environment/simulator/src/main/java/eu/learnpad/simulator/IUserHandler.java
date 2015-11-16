/**
 *
 */
package eu.learnpad.simulator;

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

/**
 * Defines the functions required to handle Users
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IUserHandler {

	/**
	 * Add a new user to the user list
	 *
	 * @param userId
	 *            the id of the new user
	 */
	public void addUser(String userId);

	/**
	 * Remove a user from the user list
	 *
	 * @param userId
	 */
	public void removeUser(String userId);

	/**
	 *
	 * @return a collection containing all the user IDs
	 */
	public Collection<String> getUsers();
}
