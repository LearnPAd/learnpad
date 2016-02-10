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
package eu.learnpad.core.impl.sim;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.sim.BridgeInterface;
import eu.learnpad.sim.rest.data.ProcessData;
import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.sim.rest.data.UserData;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
 public class XwikiBridgeInterfaceRestResource extends RestResource implements BridgeInterface{

		public XwikiBridgeInterfaceRestResource() {
			this("localhost",8080);
		}

		public XwikiBridgeInterfaceRestResource(String coreFacadeHostname,
				int coreFacadeHostPort) {
			// This constructor could change in the future
			this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
		}
		
		public void updateConfiguration(String coreFacadeHostname, int coreFacadeHostPort){
	// This constructor has to be fixed, since it requires changes on the class
//			eu.learnpad.core.rest.RestResource
			
		}

	@Override
	public Collection<String> getProcessDefinitions() {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/processes",
				RestResource.SIM_REST_URI);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");
		
		Collection<String> unmashelledList = new ArrayList<String>();
		try {

			httpClient.executeMethod(getMethod);
		    
			ObjectMapper objectMapper = new ObjectMapper();
// Not fully tested, but is looks working for our purposes -- Gulyx
			unmashelledList = objectMapper.readValue(getMethod.getResponseBodyAsStream(), Collection.class);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unmashelledList;
	}

	@Override
	public Collection<String> addProcessDefinition(
			String processDefinitionFileURL) {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/processes",
				RestResource.SIM_REST_URI);

		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", "application/json");
		
		Collection<String> unmashelledList = new ArrayList<String>();
		try {
			RequestEntity requestEntity = new StringRequestEntity(processDefinitionFileURL,"application/json", "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);
		    
			ObjectMapper objectMapper = new ObjectMapper();
// Not fully tested, but is looks working for our purposes -- Gulyx
			unmashelledList = objectMapper.readValue(postMethod.getResponseBodyAsStream(), Collection.class);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unmashelledList;
	}
	
	@Override
	public ProcessData getProcessInfos(String processArtifactId) {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/processes/%s",
				RestResource.SIM_REST_URI,processArtifactId);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");
		
		ProcessData unmashelledProcessData = new ProcessData();
		
		try {

			httpClient.executeMethod(getMethod);
		    
			ObjectMapper objectMapper = new ObjectMapper();
// Not fully tested, but is looks working for our purposes -- Gulyx
			unmashelledProcessData = objectMapper.readValue(getMethod.getResponseBodyAsStream(), ProcessData.class);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unmashelledProcessData;
	}

	@Override
	public Collection<String> getProcessInstances() {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances",
				RestResource.SIM_REST_URI);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");
		
		Collection<String> unmashelledList = new ArrayList<String>();
		try {

			httpClient.executeMethod(getMethod);
		    
			ObjectMapper objectMapper = new ObjectMapper();
// Not fully tested, but is looks working for our purposes -- Gulyx
			unmashelledList = objectMapper.readValue(getMethod.getResponseBodyAsStream(), Collection.class);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unmashelledList;
	}

	@Override
	public String addProcessInstance(ProcessInstanceData data) {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances",
				RestResource.SIM_REST_URI);

		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", "application/json");
		
		String unmashelledProcessInstanceID = "no-process-instance-added";
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			// Not fully tested, but is looks working for our purposes -- Gulyx
			String mashelledData = objectMapper.writeValueAsString(data);

			RequestEntity requestEntity = new StringRequestEntity(mashelledData,"application/json", "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);
		    
// Not fully tested, but is looks working for our purposes -- Gulyx
			unmashelledProcessInstanceID = objectMapper.readValue(postMethod.getResponseBodyAsStream(), String.class);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unmashelledProcessInstanceID;
	}

	@Override
	public String addProcessInstance(String processId,
			Collection<UserData> potentialUsers, String currentUser) {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances/%s",
				RestResource.SIM_REST_URI, processId);
		
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", "application/json");
		
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("currentuser", currentUser);
		postMethod.setQueryString(queryString);
		
		StringRequestEntity requestEntity = null;
		ObjectMapper om = new ObjectMapper();
		String potentialUsersJson = "[]";
		try {
			potentialUsersJson = om.writeValueAsString(potentialUsers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			requestEntity = new StringRequestEntity(potentialUsersJson,
					"application/json", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		postMethod.setRequestEntity(requestEntity);
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return IOUtils.toString(postMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public ProcessInstanceData getProcessInstanceInfos(
			String processInstanceArtifactId) {
		
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances/%s",
				RestResource.SIM_REST_URI,processInstanceArtifactId);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");
		
		ProcessInstanceData unmashelledProcessInstanceData = new ProcessInstanceData();
		
		try {

			httpClient.executeMethod(getMethod);
		    
			ObjectMapper objectMapper = new ObjectMapper();
// Not fully tested, but is looks working for our purposes -- Gulyx
			unmashelledProcessInstanceData = objectMapper.readValue(getMethod.getResponseBodyAsStream(), ProcessInstanceData.class);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return unmashelledProcessInstanceData;
	}

	@Override
	public InputStream getProcessInstanceResults(
			String processinstanceartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUserResults(String userartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getProcessResults(String processartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

}
