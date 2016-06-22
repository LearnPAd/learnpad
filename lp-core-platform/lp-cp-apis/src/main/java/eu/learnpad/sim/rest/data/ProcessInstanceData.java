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
package eu.learnpad.sim.rest.data;

import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains the data structure with all the informations returned by
 * the API about a given process instance.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ProcessInstanceData {
	private String instanceid;
	private String processartifactid;
	private String processartifactkey;
	private Map<String, Object> parameters;
	private Collection<String> users;
	private Map<String, Collection<String>> routes;

	public ProcessInstanceData() {
	};

	@JsonCreator
	public ProcessInstanceData(@JsonProperty("processartifactid") String processartifactid,
			@JsonProperty("processartifactkey") String processartifactkey,
			@JsonProperty("parameters") Map<String, Object> parameters, @JsonProperty("users") Collection<String> users,
			@JsonProperty("routes") Map<String, Collection<String>> routes) {
		this.processartifactid = processartifactid;
		this.processartifactkey = processartifactkey;
		this.parameters = parameters;
		this.users = users;
		this.routes = routes;
	}

	public void setInstanceid(String instanceId) {
		this.instanceid = instanceId;
	}

	public String getInstanceid() {
		return this.instanceid;
	}

	public void setProcessartifactid(String processArtifactId) {
		this.processartifactid = processArtifactId;
	}

	public String getProcessartifactkey() {
		return processartifactkey;
	}

	public String setProcessartifactid() {
		return this.processartifactid;
	}

	public String getProcessartifactid() {
		return processartifactid;
	}

	public void setProcessartifactkey(String processArtifactKey) {
		this.processartifactkey = processArtifactKey;
	}

	public String setProcessartifactkey() {
		return this.processartifactkey;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	public void setUsers(Collection<String> users) {
		this.users = users;
	}

	public Collection<String> getUsers() {
		return this.users;
	}

	public void setRoutes(Map<String, Collection<String>> routes) {
		this.routes = routes;
	}

	public Map<String, Collection<String>> getRoutes() {
		return this.routes;
	}
}