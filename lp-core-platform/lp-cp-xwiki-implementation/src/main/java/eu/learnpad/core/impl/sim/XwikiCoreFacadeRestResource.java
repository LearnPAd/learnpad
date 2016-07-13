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
package eu.learnpad.core.impl.sim;

import java.util.List;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.sim.CoreFacade;
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

/*
 * The methods inherited form the CoreFacade in this
 * class should be implemented as a REST invocation
 * toward the CoreFacade binded at the provided URL
 */
public class XwikiCoreFacadeRestResource extends DefaultRestResource implements CoreFacade {

	public XwikiCoreFacadeRestResource() {
		this("localhost", 8080);
	}

	public XwikiCoreFacadeRestResource(String coreFacadeHostname, int coreFacadeHostPort) {
		// This constructor could change in the future
		this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
	}

	public void updateConfiguration(String coreFacadeHostname, int coreFacadeHostPort) {
		// This constructor has to be fixed, since it requires changes on the
		// class eu.learnpad.core.rest.RestResource
	}

	@Override
	public List<String> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData getUserData(String userartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyProcessStartEvent(ProcessStartEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyProcessEndEvent(ProcessEndEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTaskStartEvent(TaskStartEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTaskEndEvent(TaskEndEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifySessionScoreUpdateEvent(SessionScoreUpdateEvent event) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void notifyScoreUpdateEvent(ScoreUpdateEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifySimulationStartEvent(SimulationStartEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifySimulationEndEvent(SimulationEndEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTaskFailedEvent(TaskFailedEvent event) {
		// TODO Auto-generated method stub

	}
}