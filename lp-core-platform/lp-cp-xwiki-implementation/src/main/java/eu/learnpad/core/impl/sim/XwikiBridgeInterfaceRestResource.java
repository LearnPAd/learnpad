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
import java.util.Collection;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
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

	 	private ObjectReader objectReaderCollection;
	 	private ObjectReader objectReaderString;
	 	private ObjectReader objectReaderProcessData;
	 	private ObjectReader objectReaderProcessInstanceData;
	 
	 	private ObjectWriter objectWriterCollection;
	 	private ObjectWriter objectWriterProcessInstanceData;

	 	public XwikiBridgeInterfaceRestResource() {
			this("localhost",8080);
		}

		public XwikiBridgeInterfaceRestResource(String coreFacadeHostname,
				int coreFacadeHostPort) {
			ObjectMapper objectMapper = new ObjectMapper();

			this.objectReaderCollection = objectMapper.readerFor(Collection.class);
			this.objectReaderString = objectMapper.readerFor(String.class);
			this.objectReaderProcessData = objectMapper.readerFor(ProcessData.class);
		 	this.objectReaderProcessInstanceData = objectMapper.readerFor(ProcessInstanceData.class);
			
			this.objectWriterCollection = objectMapper.writerFor(Collection.class);
			this.objectWriterProcessInstanceData = objectMapper.writerFor(ProcessInstanceData.class);
			
			// This constructor could change in the future
			this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
		}
		
		public void updateConfiguration(String coreFacadeHostname, int coreFacadeHostPort){
	// This constructor has to be fixed, since it requires changes on the class
//			eu.learnpad.core.rest.RestResource
			
		}

	@Override
	public Collection<String> getProcessDefinitions() throws LpRestException {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/processes",
				RestResource.SIM_REST_URI);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");
		
		try {

			httpClient.executeMethod(getMethod);
		    
// Not fully tested, but is looks working for our purposes -- Gulyx
			 return this.objectReaderCollection.readValue(getMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collection<String> addProcessDefinition(
			String processDefinitionFileURL) throws LpRestException {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/processes",
				RestResource.SIM_REST_URI);

		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", "application/json");
		
		try {
			RequestEntity requestEntity = new StringRequestEntity(processDefinitionFileURL,"application/json", "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);
		    
// Not fully tested, but is looks working for our purposes -- Gulyx
			return this.objectReaderCollection.readValue(postMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public ProcessData getProcessInfos(String processArtifactId) throws LpRestException {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/processes/%s",
				RestResource.SIM_REST_URI,processArtifactId);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");

		try {

			httpClient.executeMethod(getMethod);
		    
// Not fully tested, but is looks working for our purposes -- Gulyx
			return objectReaderProcessData.readValue(getMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Collection<String> getProcessInstances() throws LpRestException {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances",
				RestResource.SIM_REST_URI);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");
		
		try {

			httpClient.executeMethod(getMethod);
		    
// Not fully tested, but is looks working for our purposes -- Gulyx
			return objectReaderCollection.readValue(getMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public String addProcessInstance(ProcessInstanceData data) throws LpRestException {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances",
				RestResource.SIM_REST_URI);

		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", "application/json");
		
		try {
// Not fully tested, but is looks working for our purposes -- Gulyx
			String mashelledData = objectWriterProcessInstanceData.writeValueAsString(data);

			RequestEntity requestEntity = new StringRequestEntity(mashelledData,"application/json", "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);
		    
// Not fully tested, but is looks working for our purposes -- Gulyx
			return objectReaderString.readValue(postMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public String addProcessInstance(String processId,
			Collection<UserData> potentialUsers, String currentUser) throws LpRestException {
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances/%s",
				RestResource.SIM_REST_URI, processId);
		
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", "application/json");
		
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("currentuser", currentUser);
		postMethod.setQueryString(queryString);
		
		StringRequestEntity requestEntity = null;
		String potentialUsersJson = "[]";
		
		try {
			potentialUsersJson = this.objectWriterCollection.writeValueAsString(potentialUsers);
			requestEntity = new StringRequestEntity(potentialUsersJson,
					"application/json", "UTF-8");

			postMethod.setRequestEntity(requestEntity);
		
			httpClient.executeMethod(postMethod);

			return IOUtils.toString(postMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public ProcessInstanceData getProcessInstanceInfos(
			String processInstanceArtifactId) throws LpRestException {
		
		HttpClient httpClient = RestResource.getAnonymousClient();
		String uri = String.format("%s/learnpad/sim/bridge/instances/%s",
				RestResource.SIM_REST_URI,processInstanceArtifactId);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Content-Type", "application/json");
		
		try {

			httpClient.executeMethod(getMethod);
		    
// Not fully tested, but is looks working for our purposes -- Gulyx
			return objectReaderProcessInstanceData.readValue(getMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public InputStream getProcessInstanceResults(
			String processinstanceartifactid) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUserResults(String userartifactid) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getProcessResults(String processartifactid) throws LpRestException {
		// TODO Auto-generated method stub
		return null;
	}

}
