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

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import eu.learnpad.sim.rest.data.ProcessData;
import eu.learnpad.sim.rest.data.ProcessInstanceData;

/**
 *
 * This interface describes the REST API functionalities related to processes
 * and process instances handling
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface IProcessHandlingAPI {

	/**
	 *
	 * @return current process definitions artifact IDs
	 */
	@GET
	@Path("/processes")
	public Collection<String> getProcessDefinitions();

	/**
	 *
	 * @param processDefinitionFileURL
	 *            an exact URL to get the processes definition file
	 * @return the artifact IDs of all the added process definitions
	 */
	@POST
	@Path("/processes")
	public Collection<String> addProcessDefinition(
			String processDefinitionFileURL);

	/**
	 *
	 * @param processArtifactId
	 *            the artifact id of a process definition
	 * @return the info associated with this process definition
	 */
	@GET
	@Path("/processes/{artifactid:.*}")
	public ProcessData getProcessInfos(
			@PathParam("artifactid") String processArtifactId);

	/**
	 *
	 * @return a collection of the current process instances artifact IDs
	 */
	@GET
	@Path("/instances")
	public Collection<String> getProcessInstances();

	/**
	 *
	 * @param data
	 *            the process instance data
	 * @return the created process instance artifact id
	 */
	@POST
	@Path("/instances")
	public String addProcessInstance(ProcessInstanceData data);

	/**
	 *
	 * @param processInstanceArtifactId
	 *            the id of a process instance
	 * @return the info associated with this process instance
	 */
	@GET
	@Path("/instances/{artifactid:.*}")
	public ProcessInstanceData getProcessInstanceInfos(
			@PathParam("artifactid") String processInstanceArtifactId);
}
