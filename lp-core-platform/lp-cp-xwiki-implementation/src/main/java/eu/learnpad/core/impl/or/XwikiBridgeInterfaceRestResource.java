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
package eu.learnpad.core.impl.or;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.or.BridgeInterface;
import eu.learnpad.or.rest.data.Entities;
import eu.learnpad.or.rest.data.NotificationActionType;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.or.rest.data.ResourceType;
import eu.learnpad.or.rest.data.SimulationData;
import eu.learnpad.or.rest.data.SimulationScoresMap;
import eu.learnpad.or.rest.data.States;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessId;
import eu.learnpad.or.rest.data.kbprocessing.KBProcessingStatus;
import eu.learnpad.sim.rest.event.ScoreType;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
@Component
@Named("or")
public class XwikiBridgeInterfaceRestResource extends DefaultRestResource
		implements BridgeInterface, Initializable {

	@Override
	public void initialize() throws InitializationException {
		this.restPrefix = "";
	}
	
	@Override
	public void resourceNotification(String modelSetId, String modelId,
			String artifactId, String resourceId, ResourceType resourceType,
			String userId, Long timestamp,
			NotificationActionType action) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/%s/resourcenotification", DefaultRestResource.REST_URI, modelSetId);
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[7];
		queryString[0] = new NameValuePair("modelid", modelId);
		queryString[1] = new NameValuePair("artifactid", artifactId);
		queryString[2] = new NameValuePair("resourceid", resourceId);
		queryString[3] = new NameValuePair("resourcetype", resourceType.toString());
		queryString[4] = new NameValuePair("userid", userId);
		queryString[5] = new NameValuePair("timestamp", timestamp.toString());
		queryString[6] = new NameValuePair("action", action.toString());
		putMethod.setQueryString(queryString);
		
		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public Recommendations askRecommendation(String modelSetId,
			String artifactId, String userId, String simulationSessionId)
			throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/%s/recommendation",
				DefaultRestResource.REST_URI, modelSetId);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		boolean hasSimulationId = false;
		if ((simulationSessionId != null) && (!simulationSessionId.isEmpty())) {
			hasSimulationId = true;
		}

		int queryStringSize = 2;
		if (hasSimulationId) {
			queryStringSize = 3;
		}
		NameValuePair[] queryString = new NameValuePair[queryStringSize];
		queryString[0] = new NameValuePair("artifactid", artifactId);
		queryString[1] = new NameValuePair("userid", userId);
		if (hasSimulationId) {
			queryString[2] = new NameValuePair("simulationsessionid",
					simulationSessionId);
		}
		getMethod.setQueryString(queryString);

		Recommendations recommendations = null;

		try {
			httpClient.executeMethod(getMethod);

			InputStream recStream = getMethod.getResponseBodyAsStream();

			if (recStream != null) {
				JAXBContext jc = JAXBContext.newInstance(Recommendations.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				recommendations = (Recommendations) unmarshaller
						.unmarshal(recStream);
			}
		} catch (JAXBException | IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}

		return recommendations;
	}

	@Override
	public void addExecutionState(String modelSetId, String executionId,
			String userId, String threadId, String pageId, String artifactId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
	}

	@Override
	public States listExecutionStates(String userId) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modelSetImported(String modelSetId, ModelSetType type)
			throws LpRestExceptionXWikiImpl {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/modelsetimported/%s",
				DefaultRestResource.REST_URI, modelSetId);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type.toString());
		postMethod.setQueryString(queryString);
		try {
			httpClient.executeMethod(postMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public void simulationInstanceNotification(String modelSetId,
			String modelId, String action, String simulationId,
			SimulationData data) throws LpRestException {
		// <host>/learnpad/or/bridge/{modelsetid}/{modelid}/simulationinstancenotification?action={started|stopped},simulationid=id
		String contentType = "application/xml";

		HttpClient httpClient = this.getClient();
		String uri = String.format(
				"%s/learnpad/or/bridge/%s/%s/simulationinstancenotification",
				DefaultRestResource.REST_URI, modelSetId, modelId);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", contentType);

		NameValuePair[] queryString = new NameValuePair[2];
		queryString[0] = new NameValuePair("action", action);
		queryString[1] = new NameValuePair("simulationid", simulationId);
		postMethod.setQueryString(queryString);

		try {
			Writer simDataWriter = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance(SimulationData.class);
			jc.createMarshaller().marshal(data, simDataWriter);

			RequestEntity requestEntity = new StringRequestEntity(
					simDataWriter.toString(), contentType, "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);

		} catch (JAXBException | IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void simulationTaskStartNotification(String modelSetId,
			String modelId, String artifactId, String simulationId,
			SimulationData data) throws LpRestException {
		// <host>/learnpad/or/bridge/{modelsetid}/{modelid}/simulationtaskstart?artifactid=aid,simulationid=id
		String restOperationName = "simulationtaskstart";
		this.invokeSimulationTaskNotification(restOperationName, modelSetId,
				modelId, artifactId, simulationId, data);
	}

	@Override
	public void simulationTaskEndNotification(String modelSetId,
			String modelId, String artifactId, String simulationId,
			SimulationData data) throws LpRestException {
		// <host>/learnpad/or/bridge/{modelsetid}/{modelid}/simulationtaskend?artifactid=aid,simulationid=id
		String restOperationName = "simulationtaskend";
		this.invokeSimulationTaskNotification(restOperationName, modelSetId,
				modelId, artifactId, simulationId, data);
	}

	private void invokeSimulationTaskNotification(String restOperationName,
			String modelSetId, String modelId, String artifactId,
			String simulationId, SimulationData data) throws LpRestException {
		// <host>/learnpad/or/bridge/{modelsetid}/{modelid}/{restOperationName}?artifactid=aid,simulationid=id
		String contentType = "application/xml";

		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/%s/%s/%s",
				DefaultRestResource.REST_URI, modelSetId, modelId,
				restOperationName);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Content-Type", contentType);

		NameValuePair[] queryString = new NameValuePair[2];
		queryString[0] = new NameValuePair("artifactid", artifactId);
		queryString[1] = new NameValuePair("simulationid", simulationId);
		postMethod.setQueryString(queryString);

		try {
			Writer simDataWriter = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance(SimulationData.class);
			jc.createMarshaller().marshal(data, simDataWriter);

			RequestEntity requestEntity = new StringRequestEntity(
					simDataWriter.toString(), contentType, "UTF-8");
			postMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(postMethod);

		} catch (JAXBException | IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

    @Override
    public Entities analyseText(String modelSetId, String contextArtifactId, String userId, String title, String text) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/%s/analysetext", DefaultRestResource.REST_URI, modelSetId);
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
    public void createBookmark(String modelSetId, String userId, String artifactId, String contextArtifactId) throws LpRestException {
        // TODO Auto-generated method stub
    }

    @Override
    public Recommendations getAllBookmarks(String modelSetId, String userId, String artifactId) throws LpRestException {
        // TODO Auto-generated method stub
		return null;
    }

	@Override
	public KBProcessId calculateKPI(String modelSetId) throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/kb/%s/calculatekpi",
				DefaultRestResource.REST_URI, modelSetId);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		KBProcessId processID = null;

		try {
			httpClient.executeMethod(getMethod);

			InputStream responseStream = getMethod.getResponseBodyAsStream();

			if (responseStream != null) {
				JAXBContext jc = JAXBContext.newInstance(KBProcessId.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				processID = (KBProcessId) unmarshaller
						.unmarshal(responseStream);
			}
		} catch (JAXBException | IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}

		return processID;
	}

	@Override
	public KBProcessingStatus getHandlingProcessStatus(String kbProcessProcessId)
			throws LpRestException {
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/kb/getstatus",
				DefaultRestResource.REST_URI);

		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("kbprocessprocessid", kbProcessProcessId);
		getMethod.setQueryString(queryString);

		KBProcessingStatus processingStatus = null;

		try {
			httpClient.executeMethod(getMethod);

			InputStream responseStream = getMethod.getResponseBodyAsStream();

			if (responseStream != null) {
				JAXBContext jc = JAXBContext.newInstance(KBProcessingStatus.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				processingStatus = (KBProcessingStatus) unmarshaller
						.unmarshal(responseStream);
			}
		} catch (JAXBException | IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}

		return processingStatus;
	}

    @Override
//    public void updateSimulationScore(String modelSetId, String simulationSessionId, String processArtifactId, Long timestamp, String userId, ScoreType scoreType, Float score) throws LpRestException {
//		HttpClient httpClient = this.getClient();
//		String uri = String.format("%s/learnpad/or/bridge/%s/simulationscore", DefaultRestResource.REST_URI, modelSetId);
//		PostMethod postMethod = new PostMethod(uri);
//		postMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);
//
//		NameValuePair[] queryString = new NameValuePair[6];		
//		queryString[0] = new NameValuePair("simulationsessionid", simulationSessionId);
//		queryString[1] = new NameValuePair("processartifactid", processArtifactId);
//		queryString[2] = new NameValuePair("timestamp", timestamp.toString()); 
//		queryString[3] = new NameValuePair("userid", userId);
//		queryString[4] = new NameValuePair("scoretype", scoreType.name()); 
//		queryString[5] = new NameValuePair("score", String.valueOf(score));
//		postMethod.setQueryString(queryString);
//                     
//		try {
//			httpClient.executeMethod(postMethod);
//		} catch (IOException e) {
//			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
//		}
//    }
    public void updateSimulationScore(String modelSetId, String simulationSessionId, String processArtifactId, Long timestamp, String userId, SimulationScoresMap scoreMap) throws LpRestException {		    	
    	HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/bridge/%s/simulationscore", DefaultRestResource.REST_URI, modelSetId);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);
		String contentType = "application/xml";

		NameValuePair[] queryString = new NameValuePair[4];		
		queryString[0] = new NameValuePair("simulationsessionid", simulationSessionId);
		queryString[1] = new NameValuePair("processartifactid", processArtifactId);
		queryString[2] = new NameValuePair("timestamp", timestamp.toString()); 
		queryString[3] = new NameValuePair("userid", userId);
		postMethod.setQueryString(queryString);
                     
		try {			
			Writer scoreMapWriter = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance(SimulationScoresMap.class);
			jc.createMarshaller().marshal(scoreMap, scoreMapWriter);

			RequestEntity requestEntity = new StringRequestEntity(
					scoreMapWriter.toString(), contentType, "UTF-8");
			postMethod.setRequestEntity(requestEntity);
			
			httpClient.executeMethod(postMethod);
		} catch (IOException | JAXBException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
    }

}