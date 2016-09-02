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

import eu.learnpad.sim.rest.event.ScoreType;
import eu.learnpad.simulator.monitoring.event.AbstractSimEvent;
import eu.learnpad.simulator.monitoring.event.SimEventType;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SimulationEndSimEvent extends AbstractSimEvent {

	public final Map<String, Map<ScoreType, Float>> probeScores;

	public SimulationEndSimEvent(Long timestamp, String simulationsessionid,
			Collection<String> involvedusers, Map<String, Map<ScoreType, Float>> probeScores) {
		super(timestamp, simulationsessionid, involvedusers);
		this.probeScores = probeScores;
	}

	@Override
	public SimEventType getType() {
		return SimEventType.SIMULATION_END;
	}

}
