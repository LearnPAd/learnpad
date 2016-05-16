/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.sim.rest.event.impl;

import java.util.List;
import java.util.Map;

import eu.learnpad.sim.rest.event.AbstractEvent;
import eu.learnpad.sim.rest.event.EventType;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class TaskEndEvent extends AbstractEvent {

	/**
	 *
	 */
	private static final long serialVersionUID = -5328814254042585792L;

	/**
	 * Unique ID of the process artifact
	 */
	public String processartifactid;

	/**
	 * ID used to identify the task in the BP definition
	 */
	public String taskartifactid;

	/**
	 * The LearnPAd users that have been assigned to this task
	 */
	public List<String> assignedusers;

	public String completingUser;
	public Map<String, Object> submittedData;

	public TaskEndEvent() {
		super();
	}

	public TaskEndEvent(Long timestamp, String simulationsessionid, List<String> involvedusers, String modelsetid,
			Map<String, Object> simulationSessionData, String processartifactid, String taskartifactid,
			List<String> assignedusers, String completingUser, Map<String, Object> submittedData) {
		super(EventType.TASK_END, timestamp, simulationsessionid, involvedusers, modelsetid, simulationSessionData);
		this.processartifactid = processartifactid;
		this.taskartifactid = taskartifactid;
		this.assignedusers = assignedusers;
		this.completingUser = completingUser;
		this.submittedData = submittedData;
	}

	@Override
	public String toString() {
		return super.toString() + " processartifactid=" + processartifactid + " taskartifactid=" + taskartifactid
				+ " assignedusers=" + assignedusers + " completingUser=" + completingUser + " submittedData="
				+ submittedData;
	}
}