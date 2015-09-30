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
package eu.learnpad.cw.rest;

import java.util.Collection;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.data.UserData;

public interface Simulation {
	/**
	 * @param modelId
	 *            the BPMN ID of the model to simulate
	 * @param currentUser
	 *            is the name of the current user (will be used as a display in
	 *            simulator)
	 * @param potentialUsers
	 *            list of users likely to participate in the simulation
	 * @return TODO
	 * @throws LpRestException
	 */
	// <host>/learnpad/cw/corefacade/simulation/start/{modelsetid}?currentUser=<name_of_user>
	@Path("/simulation/start/{modelsetid}")
	@POST
	String startSimulation(@PathParam("modelid") String modelId,
			@QueryParam("currentuser") String currentUser,
			Collection<UserData> potentialUsers) throws LpRestException;
}
