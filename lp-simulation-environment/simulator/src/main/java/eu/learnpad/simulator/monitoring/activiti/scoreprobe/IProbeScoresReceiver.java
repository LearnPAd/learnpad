package eu.learnpad.simulator.monitoring.activiti.scoreprobe;

import eu.learnpad.sim.rest.event.ScoreType;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2016 Linagora
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
 * This interface define the mean to receive a monitoring score event from the
 * monitoring component.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IProbeScoresReceiver {

	/**
	 * Receive a monitoring score event from the monitoring component
	 *
	 * @param sessionId
	 *         the ID of the involved session
	 * @param userId
	 *         the ID of the involved user
	 * @param type
	 *         the type of score update
	 * @param value
	 *         the new score value
	 */
	public void receiveScore(String sessionId, String userId, ScoreType type, Float value);

}
