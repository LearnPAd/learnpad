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

import eu.learnpad.simulator.monitoring.event.AbstractSimEvent;
import eu.learnpad.simulator.monitoring.event.SimEventType;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SessionScoreUpdateSimEvent extends AbstractSimEvent {

	/**
	 * Unique ID of the process instance
	 */
	public String processid;

	/**
	 * The LearnPAd user those score is updated
	 */
	public String user;

	/**
	 * The new session score of the user
	 */
	public long sessionscore;

	public SessionScoreUpdateSimEvent(Long timestamp,
			String simulationsessionid, Collection<String> involvedusers,
			String processid, String user, long sessionScore) {
		super(timestamp, simulationsessionid, involvedusers);
		this.processid = processid;
		this.sessionscore = sessionScore;
		this.user = user;
	}

	@Override
	public SimEventType getType() {
		return SimEventType.SESSION_SCORE_UPDATE;
	}

}
