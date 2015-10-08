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
package eu.learnpad.or.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import eu.learnpad.exception.LpRestException;

// <host>/learnpad/or/{modelsetid}/{modelid}/simulationnotification?action={started|stopped},simulationid=id
@Path("/{modelsetid}/{modelid}/simulationnotification")
public interface SimulationNotification {
	/**
	 * @param modelSetId
	 *            is the modelset ID concerned by the simulation
	 * @param modelId
	 *            is the model ID concerned by the simulation
	 * @param action
	 *            is one of the following values: started, stopped
	 * @param simulationId
	 *            is the ID of the concerned simulation
	 * @return
	 * @throws LpRestException
	 */
	@POST
	byte[] simulationNotification(@PathParam("modelsetid") String modelSetId,
			@PathParam("modelid") String modelId,
			@QueryParam("action") String action,
			@QueryParam("simulationid") String simulationId)
			throws LpRestException;
}
