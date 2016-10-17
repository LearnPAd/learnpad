package eu.learnpad.simulator.monitoring;

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

import java.util.ArrayList;
import java.util.List;

import eu.learnpad.simulator.IProcessEventReceiver;
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
 * This class allows to multicast events to several subscribed events
 * dispatcher.
 *
 * The listeners are executed in the order that they have subscribed
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class EventDispatcherImpl implements IEventDispatcher {

	private final List<IProcessEventReceiver> receivers = new ArrayList<IProcessEventReceiver>();

	@Override
	public void subscribe(IProcessEventReceiver eventReceiver) {
		receivers.add(eventReceiver);
	}

	@Override
	public void unsubscribe(IProcessEventReceiver eventReceiver) {
		receivers.remove(eventReceiver);
	}

	@Override
	public void receiveProcessStartEvent(ProcessStartSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveProcessStartEvent(event);
		}
	}

	@Override
	public void receiveProcessEndEvent(ProcessEndSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveProcessEndEvent(event);
		}
	}

	@Override
	public void receiveTaskStartEvent(TaskStartSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveTaskStartEvent(event);
		}
	}

	@Override
	public void receiveTaskEndEvent(TaskEndSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveTaskEndEvent(event);
		}
	}

	@Override
	public void receiveTaskFailedEvent(TaskFailedSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveTaskFailedEvent(event);
		}
	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveSessionScoreUpdateEvent(event);
		}
	}

	@Override
	public void receiveSimulationStartEvent(SimulationStartSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveSimulationStartEvent(event);
		}

	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveSimulationEndEvent(event);
		}

	}

	@Override
	public void receiveSimulationFinalizeEvent(SimulationFinalizeSimEvent event) {
		for (IProcessEventReceiver receiver : receivers) {
			receiver.receiveSimulationFinalizeEvent(event);
		}

	}
}
