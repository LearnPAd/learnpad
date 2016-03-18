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
package eu.learnpad.simulator.monitoring.event.impl;

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

import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;
import eu.learnpad.simulator.monitoring.event.SimEventType;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class TaskFailedSimEvent extends TaskStartSimEvent {

	public final String completingUser;
	public Map<String, Object> submittedData;
	public final LearnPadTaskSubmissionResult submissionResult;

	public TaskFailedSimEvent(Long timestamp, String simulationsessionid,
			Collection<String> involvedusers, LearnPadTask task,
			String completingUser, Map<String, Object> submittedData,
			LearnPadTaskSubmissionResult submissionResult) {
		super(timestamp, simulationsessionid, involvedusers, task);
		this.completingUser = completingUser;
		this.submittedData = submittedData;
		this.submissionResult = submissionResult;
	}

	@Override
	public SimEventType getType() {
		return SimEventType.TASK_FAILED;
	}

}
