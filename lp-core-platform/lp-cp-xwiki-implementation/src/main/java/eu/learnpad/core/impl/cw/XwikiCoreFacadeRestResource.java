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
package eu.learnpad.core.impl.cw;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.codehaus.jackson.map.ObjectMapper;

import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalyses;
import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.cw.CoreFacade;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.or.rest.data.Entities;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.sim.rest.data.ProcessInstanceData;
import eu.learnpad.sim.rest.data.UserDataCollection;

/*
 * The methods inherited form the CoreFacade in this
 * class should be implemented as a REST invocation
 * toward the CoreFacade binded at the provided URL
 */
public class XwikiCoreFacadeRestResource extends DefaultRestResource implements CoreFacade {

	public XwikiCoreFacadeRestResource() {
		this("localhost", 8080);
	}

	public XwikiCoreFacadeRestResource(String coreFacadeHostname, int coreFacadeHostPort) {
		// This constructor could change in the future
		this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
	}

	public void updateConfiguration(String coreFacadeHostname, int coreFacadeHostPort) {
		// This constructor has to be fixed, since it requires changes on the
		// class eu.learnpad.core.rest.RestResource
	}

	@Override
	public void commentNotification(String modelSetId, String commentId, String action) throws LpRestException {
		// TODO Auto-generated method stub
	}

	@Override
	public void resourceNotification(String modelSetId, String modelId,
			String artifactId, String resourceId, ResourceType type,
			NotificationActionType action, String userId)
			throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/resourcenotification/%s", DefaultRestResource.REST_URI, modelSetId);
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[6];
		queryString[0] = new NameValuePair("modelid", modelId);
		queryString[1] = new NameValuePair("artifactid", artifactId);
		queryString[2] = new NameValuePair("resourceid", resourceId);
		queryString[3] = new NameValuePair("resourcetype", type.toString());
		queryString[4] = new NameValuePair("action", action.toString());
		queryString[5] = new NameValuePair("userid", userId);
		putMethod.setQueryString(queryString);
		
		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public InputStream getModel(String modelSetId, ModelSetType type) throws LpRestException {
		// Now send the package's path to the importer for XWiki
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/getmodel/%s", DefaultRestResource.REST_URI, modelSetId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type.toString());
		getMethod.setQueryString(queryString);

		InputStream model = null;

		try {
			httpClient.executeMethod(getMethod);
			model = getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
		return model;
	}

	@Override
	public String startSimulation(String modelId, String currentUser, UserDataCollection potentialUsers)
			throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/simulation/start/%s", DefaultRestResource.REST_URI,
				modelId);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		postMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("currentuser", currentUser);
		postMethod.setQueryString(queryString);

		StringRequestEntity requestEntity = null;
		ObjectMapper om = new ObjectMapper();
		String potentialUsersJson = "[]";

		try {
			potentialUsersJson = om.writeValueAsString(potentialUsers);
			requestEntity = new StringRequestEntity(potentialUsersJson, "application/json", "UTF-8");

			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			// UnsupportedEncodingException is also caught here!
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		try {
			return IOUtils.toString(postMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String joinSimulation(String simulationId, String userId) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/simulation/join/%s/%s", DefaultRestResource.REST_URI,
				simulationId, userId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		getMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		try {
			return IOUtils.toString(getMethod.getResponseBodyAsStream());
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public Collection<String> listSimulations() throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/simulation/instances", DefaultRestResource.REST_URI);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		getMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			// Unchecked cast
			return (Collection<String>) mapper.readValue(getMethod.getResponseBodyAsStream(), Collection.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ProcessInstanceData getSimulationInfo(String simulationId) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/simulation/instances/%s", DefaultRestResource.REST_URI,
				simulationId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		getMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		try {
			ObjectMapper mapper = new ObjectMapper();
			// Unchecked cast
			return (ProcessInstanceData) mapper.readValue(getMethod.getResponseBodyAsStream(),
					ProcessInstanceData.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Recommendations getRecommendations(String modelSetId, String artifactId, String userId)
			throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/recommendation", DefaultRestResource.REST_URI);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);

		NameValuePair[] queryString = new NameValuePair[3];
		queryString[0] = new NameValuePair("modelsetid", modelSetId);
		queryString[1] = new NameValuePair("artifactid", artifactId);
		queryString[2] = new NameValuePair("userid", userId);
		getMethod.setQueryString(queryString);

		InputStream feedbacksStream = null;
		try {
			httpClient.executeMethod(getMethod);
			feedbacksStream = getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}

		Recommendations recommendations = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(Recommendations.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			recommendations = (Recommendations) unmarshaller.unmarshal(feedbacksStream);
		} catch (JAXBException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
		return recommendations;
	}

	@Override
	public Entities analyseText(String modelSetId, String contextArtifactId,
			String userId, String title, String text) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/semantic/%s/analysetext", DefaultRestResource.REST_URI, modelSetId);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);

		NameValuePair[] queryString = new NameValuePair[4];
		queryString[0] = new NameValuePair("modelsetid", modelSetId);
		queryString[1] = new NameValuePair("contextArtifactId", contextArtifactId);
		queryString[2] = new NameValuePair("userid", userId);
		queryString[3] = new NameValuePair("title", title);
		postMethod.setQueryString(queryString);
		
		RequestEntity requestEntity;
		InputStream entitiesAsStream = null;
		try {
			requestEntity = new StringRequestEntity(text, MediaType.APPLICATION_XML, "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);
			entitiesAsStream = postMethod.getResponseBodyAsStream(); 
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}

		Entities entities = null;

		try {
			JAXBContext jc = JAXBContext.newInstance(Entities.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			entities = (Entities) unmarshaller.unmarshal(entitiesAsStream);
		} catch (JAXBException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
		return entities;
	}
	
	@Override
	public String getDashboardKpiDefaultViewer(String modelSetId, String userId)
			throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/dashboardkpi/viewer", DefaultRestResource.REST_URI);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);

		NameValuePair[] queryString = new NameValuePair[2];
		queryString[0] = new NameValuePair("modelsetid", modelSetId);
		queryString[1] = new NameValuePair("userid", userId);
		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
			String url = getMethod.getResponseBodyAsString();
			return url;
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}
	
	@Override
	public String calculateKPI(String modelSetId) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/dashboardkpi/%s/calculatekpi", DefaultRestResource.REST_URI, modelSetId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("modelsetid", modelSetId);
		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
			String url = getMethod.getResponseBodyAsString();
			return url;
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}
	
	@Override
	public InputStream transform(ModelSetType type, InputStream model) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/transform", DefaultRestResource.REST_URI);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM);
		postMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_OCTET_STREAM);

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type.toString());
		postMethod.setQueryString(queryString);

		RequestEntity requestEntity = new InputStreamRequestEntity(model);
		postMethod.setRequestEntity(requestEntity);

		try {
			httpClient.executeMethod(postMethod);
			return postMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public String startAnalysis(String id, String language, List<String> options, InputStream body)
			throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/analyze", DefaultRestResource.REST_URI);
		PostMethod postMethod = new PostMethod(uri);

		NameValuePair[] queryString = new NameValuePair[2 + options.size()];
		queryString[0] = new NameValuePair("id", id);
		queryString[1] = new NameValuePair("language", language);
		int count = 2;
		for (String option : options) {
			queryString[count] = new NameValuePair("option", option);
			count++;
		}
		postMethod.setQueryString(queryString);

		RequestEntity requestEntity = new InputStreamRequestEntity(body);
		postMethod.setRequestEntity(requestEntity);

		try {
			httpClient.executeMethod(postMethod);
			return postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public String getStatus(String analysisId) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/analyze/%s/status", DefaultRestResource.REST_URI,
				analysisId);
		GetMethod getMethod = new GetMethod(uri);

		try {
			httpClient.executeMethod(getMethod);
			return getMethod.getResponseBodyAsString();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public String getView(String analysisId) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/analyze/%s/view", DefaultRestResource.REST_URI,
				analysisId);
		GetMethod getMethod = new GetMethod(uri);

		try {
			httpClient.executeMethod(getMethod);
			String url = getMethod.getResponseBodyAsString();
			return url;
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public AnnotatedCollaborativeContentAnalyses getResults(String analysisId) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/analyze/%s", DefaultRestResource.REST_URI, analysisId);
		GetMethod getMethod = new GetMethod(uri);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnnotatedCollaborativeContentAnalyses analysis = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AnnotatedCollaborativeContentAnalyses.class);
			InputStream retIs = getMethod.getResponseBodyAsStream();
			analysis = (AnnotatedCollaborativeContentAnalyses) jaxbContext.createUnmarshaller().unmarshal(retIs);
			return analysis;
		} catch (Exception e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

}
