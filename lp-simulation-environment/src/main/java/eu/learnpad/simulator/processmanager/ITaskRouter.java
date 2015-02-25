/**
 *
 */
package eu.learnpad.simulator.processmanager;

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

import java.util.Set;

import org.activiti.engine.task.Task;

/**
 * Interface for the routing function.
 *
 * The sole purpose of this interface is to provide a mapping between tasks and
 * users. It should be instantiated process-by-process.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface ITaskRouter {

	/**
	 *
	 * @param task
	 *            the task to be routed
	 * @return the set of users id to which the task must be routed
	 */
	public Set<String> route(Task task);

}
