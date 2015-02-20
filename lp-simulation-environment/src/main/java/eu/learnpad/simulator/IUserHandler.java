/**
 *
 */
package eu.learnpad.simulator;

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
