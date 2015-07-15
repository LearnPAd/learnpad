/**
 *
 */
package eu.learnpad.simulator.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import eu.learnpad.sim.BridgeInterface;
import eu.learnpad.sim.rest.data.ProcessData;
import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.simulator.Simulator;

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

/**
 * @author Tom Jorquera - Linagora
 *
 */
@Path("/")
public class SimulatorBridgeImpl implements BridgeInterface {

	// this will allow us to return specific response codes (specifically for
	// POST methods)
	@Context
	private HttpServletResponse response;
	@Context
	private UriInfo infos;

	private final Simulator simulator;

	public SimulatorBridgeImpl(@Context Simulator simulator) {
		this.simulator = simulator;
	}

	/**
	 * Configure the response to signal a created resource (set status to
	 * created + return new resource location)
	 *
	 * @param relativePath
	 */
	private void setResponseToCreated(String relativePath) {
		response.setStatus(Status.CREATED.getStatusCode());
		response.setHeader("Location", infos.getAbsolutePath() + relativePath);

		try {
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Process handling

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * getProcessDefinitions()
	 */
	public Collection<String> getProcessDefinitions() {

		return simulator.processManager().getAvailableProcessDefintion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * addProcessDefinition(java.lang.String)
	 */
	public Collection<String> addProcessDefinition(
			String processDefinitionFilePath) {

		// since we are potentially creating several resources we do not return
		// the location of a specific one.
		setResponseToCreated("");

		return simulator.processManager().addProjectDefinitions(
				processDefinitionFilePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * getProcessInfos(java.lang.String)
	 */
	public ProcessData getProcessInfos(String processId) {
		if (simulator.processManager().getAvailableProcessDefintion()
				.contains(processId)) {

			return new ProcessData(simulator.processManager()
					.getProcessDefinitionName(processId), simulator
					.processManager()
					.getProcessDefinitionDescription(processId), simulator
					.processManager()
					.getProcessDefinitionSingleRoles(processId), simulator
					.processManager().getProcessDefinitionGroupRoles(processId));
		} else {
			throw new NotFoundException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * getProcessInstances()
	 */
	public Collection<String> getProcessInstances() {
		return simulator.processManager().getCurrentProcessInstances();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * addProcessInstance(eu.learnpad.simulator.api.msg.ProcessInstanceData)
	 */
	public String addProcessInstance(ProcessInstanceData data) {
		String id = simulator.processManager().startProjectInstance(
				data.processartifactid, data.parameters, data.users,
				data.routes);
		setResponseToCreated(id);
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * getProcessInstanceInfos(java.lang.String)
	 */
	public ProcessInstanceData getProcessInstanceInfos(String processInstanceId) {
		if (simulator.processManager().getCurrentProcessInstances()
				.contains(processInstanceId)) {
			return new ProcessInstanceData("", null, simulator.processManager()
					.getProcessInstanceInvolvedUsers(processInstanceId), null);
		} else {
			throw new NotFoundException();
		}
	}

	// simulation monitoring

	public InputStream getProcessInstanceResults(String arg0) {
		// TODO Proper Implementation of Simulation Monitoring API
		return this.getClass().getClassLoader()
				.getResourceAsStream("validation_db.json");
	}

	public InputStream getProcessResults(String arg0) {
		// TODO Proper Implementation of Simulation Monitoring API
		return this.getClass().getClassLoader()
				.getResourceAsStream("validation_db.json");
	}

	public InputStream getUserResults(String arg0) {
		// TODO Proper Implementation of Simulation Monitoring API
		return this.getClass().getClassLoader()
				.getResourceAsStream("validation_db.json");
	}

}
