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
 *
 * Define the interface required to subscribe to process events
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IProcessEventReceiver {

	static interface IProcessEventReceiverProvider {
		public IProcessEventReceiver processEventReceiver();
	}

	/**
	 * Send a task to a set of users
	 *
	 * @param processId
	 *            the id of the process
	 * @param taskId
	 *            the id of the task
	 * @param taskDescr
	 *            the description of the task
	 * @param users
	 *            the users concerned by the task
	 */
	public void sendTask(String processId, String taskId, String taskDescr,
			Collection<String> users);

	/**
	 * Signal the end of a process instance to a list of users
	 *
	 * @param processId
	 *            the id of the process
	 *
	 * @param users
	 *            the users concerned by the process
	 */
	public void signalProcessEnd(String processId, Collection<String> users);
}
