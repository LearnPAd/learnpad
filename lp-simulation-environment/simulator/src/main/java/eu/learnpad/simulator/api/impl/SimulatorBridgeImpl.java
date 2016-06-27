/**
 *
 */
package eu.learnpad.simulator.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.ResteasyProviderFactory;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.BridgeInterface;
import eu.learnpad.sim.rest.IUserInfosAPI;
import eu.learnpad.sim.rest.data.ProcessData;
import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.sim.rest.data.UserData;
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
public class SimulatorBridgeImpl implements BridgeInterface, IUserInfosAPI {

	// this will allow us to return specific response codes (specifically for
	// POST methods)
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

		response = ResteasyProviderFactory
				.getContextData(HttpServletResponse.class);

		// in some cases (as when calling the API programmatically), no response
		// will be captured (response == null), not really sure why. But since
		// this is not really critical setting the response on a best-effort
		// basis should be enough.
		if (response != null) {
			response.setStatus(Status.CREATED.getStatusCode());
			response.setHeader("Location", infos.getAbsolutePath()
					+ relativePath);
			try {
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
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

		Collection<String> processDefIds = simulator.processManager()
				.getAvailableProcessDefintion();

		// translate from process IDs to process keys
		Set<String> processDefKeys = new HashSet<String>();
		for (String id : processDefIds) {
			processDefKeys.add(simulator.processManager()
					.getProcessDefinitionKey(id));
		}
		return processDefKeys;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * addProcessDefinition(java.lang.String)
	 */
	public Collection<String> addProcessDefinition(
			String processDefinitionFilePath, String modelSetId) {

		// since we are potentially creating several resources we do not return
		// the location of a specific one.
		setResponseToCreated("");

		try {
			Collection<String> processDefIds = simulator.processManager()
					.addProjectDefinitions(
							new URL(processDefinitionFilePath).openStream());

			// translate from process IDs to process keys
			Set<String> processDefKeys = new HashSet<String>();
			for (String id : processDefIds) {
				processDefKeys.add(simulator.processManager()
						.getProcessDefinitionKey(id));

				// register modelsetid if provided
				if (modelSetId != null) {
					simulator.processManager().setModelSetId(id, modelSetId);
				}
			}
			return processDefKeys;
		} catch (IOException e) {
			e.printStackTrace();
			return new HashSet<String>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.api.functionalities.IProcessesHandlingBridge#
	 * getProcessInfos(java.lang.String)
	 */
	public ProcessData getProcessInfos(String processDefinitionKey) {

		String processDefId = simulator.processManager()
				.getProcessDefIdFromDefKey(processDefinitionKey);

		if (processDefId == null
				|| simulator.processManager().getAvailableProcessDefintion()
				.contains(processDefId)) {

			return new ProcessData(simulator.processManager()
					.getProcessDefinitionName(processDefId), simulator
					.processManager().getProcessDefinitionDescription(
							processDefId), simulator.processManager()
							.getProcessDefinitionSingleRoles(processDefId), simulator
							.processManager().getProcessDefinitionGroupRoles(
									processDefId));
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
				data.getProcessartifactkey(), data.getParameters(), data.getUsers(),
				data.getRoutes());
		setResponseToCreated(id);
		return id;
	}

	@Override
	public String addProcessInstance(String processKey,
			Collection<UserData> potentialUsers, String currentUser) {

		// Convert the collection into a Set
		// This ensures that we will have no duplicate.
		// Otherwise we could add the same user twice, since we only check
		// against the old user list
		Set<UserData> uniqueUsers = new HashSet<UserData>(potentialUsers);

		// add users that were not yet present in the platform
		Collection<String> users = simulator.userHandler().getUsers();
		for (UserData user : uniqueUsers) {
			if (!users.contains(user.id)) {
				simulator.userHandler().addUser(user);
			}
		}

		return "uisingleprocess?processid=" + processKey + "&" + "userid="
		+ currentUser;
	}

	@Override
	public String joinProcessInstance(String simulationId, String userId) throws LpRestException {
		return "ui?userid="	+ userId + "&simulationid=" + simulationId;
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
			return simulator.processManager().getProcessInstanceInfos(
					processInstanceId);
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

	@Override
	public List<String> getUsers() {
		return new ArrayList<String>(simulator.userHandler().getUsers());
	}

	@Override
	public UserData getUserData(String userartifactid) {
		return simulator.userHandler().getUserData(userartifactid);
	}
}
