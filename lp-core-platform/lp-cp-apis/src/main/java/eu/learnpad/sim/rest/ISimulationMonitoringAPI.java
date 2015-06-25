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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	 * Get the result file associated with a specific simulation
	 *
	 * @param simulationid
	 * @return the String to the result file
	 */
	@GET
	@Path("/results/simulations/{simulationid:.*}")
	public String getSimulationResults(
			@PathParam("simulationid") String simulationid);

	/**
	 * Get the trace file associated with a learner
	 *
	 * @param userid
	 * @return the String to the trace file
	 */
	@GET
	@Path("/results/users/{userid:.*}")
	public String getSimulationTraceForLearner(
			@PathParam("userid") String userid);

	/**
	 * Get the trace file associated with a model
	 *
	 * @param modelid
	 * @return the String to the trace file
	 */
	@GET
	@Path("/results/models/{modelid:.*}")
	public String getSimulationTraceForBP(@PathParam("modelid") String modelid);

}
