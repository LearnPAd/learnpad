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

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.learnpad.exception.LpRestException;

/**
 *
 * This interface describes the REST API functionalities related to simulation
 * monitoring
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface ISimulationMonitoringAPI {

	/**
	 * Get the result file associated with a specific process instance
	 *
	 * @param processinstanceartifactid
	 * @return the result file
	 */
	@GET
	@Path("/results/instances/{processinstanceartifactid:.*}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream getProcessInstanceResults(
			@PathParam("processinstanceartifactid") String processinstanceartifactid) throws LpRestException;

	/**
	 * Get the results file associated with a learner
	 *
	 * @param userartifactid
	 * @return the result file
	 */
	@GET
	@Path("/results/users/{userartifactid:.*}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream getUserResults(@PathParam("userartifactid") String userartifactid) throws LpRestException;

	/**
	 * Get the results file associated with a process
	 *
	 * @param processartifactid
	 * @return the result file
	 */
	@GET
	@Path("/results/processes/{processartifactid:.*}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream getProcessResults(@PathParam("processartifactid") String processartifactid)
			throws LpRestException;
}