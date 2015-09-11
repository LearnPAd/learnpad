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

/**
 *
 * @author Tom Jorquera - Linagora
 *
 * @param <Input>
 *            the type of data which is submitted in input of a task
 * @param <Output>
 *            the type of data which is submitted in output of a task
 */
public interface ITaskValidator<Input, Output> {

	/**
	 *
	 * @param processId
	 *            the id of the concerned process
	 * @param taskId
	 *            the id of the concerned task
	 * @param inData
	 *            the data which was given in input of the task
	 * @param proposedResult
	 *            the proposed result for the task
	 * @return whether or not the proposed result is valid
	 */
	public boolean taskResultIsValid(String taskId, Input inData,
			Output proposedResult);
}
