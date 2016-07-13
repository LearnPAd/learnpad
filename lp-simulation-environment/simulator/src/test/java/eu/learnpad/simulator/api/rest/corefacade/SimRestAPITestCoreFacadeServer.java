package eu.learnpad.simulator.api.rest.corefacade;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.ScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SimulationEndEvent;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskFailedEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SimRestAPITestCoreFacadeServer implements
SimRestAPITestCoreFacadeInterface {

	private final List<String> userIds = new ArrayList<String>();
	private final Map<String, UserData> idtoUserMap = new HashMap<String, UserData>();

	public int nbSimulationStartEvents = 0;
	public int nbSimulationEndEvents = 0;
	public int nbProcessStartEvents = 0;
	public int nbProcessEndEvents = 0;
	public int nbTaskStartEvents = 0;
	public int nbTaskEndEvents = 0;
	public int nbTaskFailedEvents = 0;
	public int nbSessionScoreUpdateEvents = 0;
	public int nbScoreUpdateEvents = 0;

	public SimRestAPITestCoreFacadeServer(List<UserData> users) {
		super();

		for (UserData u : users) {
			userIds.add(u.id);
			idtoUserMap.put(u.id, u);
		}
	}

	public SimRestAPITestCoreFacadeServer() {
		this(new ArrayList<UserData>());
	}

	public void adduser(UserData u) {
		userIds.add(u.id);
		idtoUserMap.put(u.id, u);
	}

	@Override
	public List<String> getUsers() {
		return userIds;
	}

	@Override
	public UserData getUserData(String userartifactid) {
		return idtoUserMap.get(userartifactid);
	}

	@Override
	public void notifySimulationStartEvent(SimulationStartEvent event) {
		nbSimulationStartEvents++;
	}

	@Override
	public void notifySimulationEndEvent(SimulationEndEvent event) {
		nbSimulationEndEvents++;
	}

	@Override
	public void notifyProcessStartEvent(ProcessStartEvent event) {
		nbProcessStartEvents++;
	}

	@Override
	public void notifyProcessEndEvent(ProcessEndEvent event) {
		nbProcessEndEvents++;
	}

	@Override
	public void notifyTaskStartEvent(TaskStartEvent event) {
		nbTaskStartEvents++;
	}

	@Override
	public void notifyTaskEndEvent(TaskEndEvent event) {
		nbTaskEndEvents++;
	}

	@Override
	public void notifyTaskFailedEvent(TaskFailedEvent event) {
		nbTaskFailedEvents++;
	}

	@Override
	public void notifySessionScoreUpdateEvent(SessionScoreUpdateEvent event) {
		nbSessionScoreUpdateEvents++;
	}

	@Override
	public void notifyScoreUpdateEvent(ScoreUpdateEvent event) throws LpRestException {
		nbScoreUpdateEvents++;		
	}

}
