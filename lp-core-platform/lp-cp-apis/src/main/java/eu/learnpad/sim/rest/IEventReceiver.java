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
package eu.learnpad.sim.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
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
public interface IEventReceiver {

	@POST
	@Path("/simulationstart")
	public void receiveSimulationStartEvent(SimulationStartEvent event)
			throws LpRestException;

	@POST
	@Path("/simulationend")
	public void receiveSimulationEndEvent(SimulationEndEvent event)
			throws LpRestException;

	@POST
	@Path("/processstart")
	public void receiveProcessStartEvent(ProcessStartEvent event)
			throws LpRestException;

	@POST
	@Path("/processend")
	public void receiveProcessEndEvent(ProcessEndEvent event)
			throws LpRestException;

	@POST
	@Path("/taskstart")
	public void receiveTaskStartEvent(TaskStartEvent event)
			throws LpRestException;

	@POST
	@Path("/taskend")
	public void receiveTaskEndEvent(TaskEndEvent event) throws LpRestException;

	@POST
	@Path("/taskfailed")
	public void receiveTaskFailedEvent(TaskFailedEvent event)
			throws LpRestException;

	@POST
	@Path("/sessionscoreupdate")
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateEvent event)
			throws LpRestException;

}
