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

	public String processartifactid;
	public String processartifactkey;
	public Map<String, Object> parameters;
	public Collection<String> users;
	public Map<String, Collection<String>> routes;

	public ProcessInstanceData() {
	};

	@JsonCreator
	public ProcessInstanceData(
			@JsonProperty("processartifactid") String processartifactid,
			@JsonProperty("processartifactkey") String processartifactkey,
			@JsonProperty("parameters") Map<String, Object> parameters,
			@JsonProperty("users") Collection<String> users,
			@JsonProperty("routes") Map<String, Collection<String>> routes) {
		this.processartifactid = processartifactid;
		this.processartifactkey = processartifactkey;
		this.parameters = parameters;
		this.users = users;
		this.routes = routes;
	}

}
