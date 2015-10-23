/**
 *
 */
package eu.learnpad.simulator.uihandler;

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
import java.util.Map;

/**
 * This interface defines transformations between tasks IO and form data
 *
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IFormHandler {

	static interface FormResult {

		/**
		 *
		 * @return the parameters to initialize the process
		 */
		public Map<String, Object> getProperties();

		/**
		 *
		 * @return a mapping from roles to users (some roles may be mapped to
		 *         several users)
		 */
		public Map<String, Collection<String>> getRolesToUsersMapping();
	}

	/**
	 * Generate the String required to instantiate a form corresponding to the
	 * given task
	 *
	 * @param activitiFormService
	 * @param taskId
	 * @return
	 */
	public String createFormString(String taskId);

	/**
	 * Generate the String required to instantiate a form corresponding to the
	 * starting activity of a process
	 *
	 * @param processId
	 * @param singleRoles
	 * @param groupRoles
	 * @param users
	 * @return
	 */
	public String createStartingFormString(String processId,
			Collection<String> singleRoles, Collection<String> groupRoles,
			Collection<String> users);

	/**
	 * Transform a given form result data string into a result object
	 *
	 * @param data
	 * @return
	 */
	public FormResult parseResult(String data);

}
