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

import eu.learnpad.simulator.monitoring.event.impl.ProcessEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.ProcessStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SessionScoreUpdateSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationFinalizeSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskFailedSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;

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

	public void receiveSimulationStartEvent(SimulationStartSimEvent event);

	public void receiveSimulationEndEvent(SimulationEndSimEvent event);

	public void receiveSimulationFinalizeEvent(SimulationFinalizeSimEvent event);

	public void receiveProcessStartEvent(ProcessStartSimEvent event);

	public void receiveProcessEndEvent(ProcessEndSimEvent event);

	public void receiveTaskStartEvent(TaskStartSimEvent event);

	public void receiveTaskEndEvent(TaskEndSimEvent event);

	public void receiveTaskFailedEvent(TaskFailedSimEvent event);

	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateSimEvent event);

}
