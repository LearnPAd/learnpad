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

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.codehaus.jackson.map.ObjectMapper;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.cw.CoreFacade;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.sim.rest.data.UserData;

/*
 * The methods inherited form the CoreFacade in this
 * class should be implemented as a REST invocation
 * toward the CoreFacade binded at the provided URL
 */
public class XwikiCoreFacadeRestResource extends RestResource implements
		CoreFacade {
	
	public XwikiCoreFacadeRestResource() {
		this("localhost", 8080);
	}

	public XwikiCoreFacadeRestResource(String coreFacadeHostname,
			int coreFacadeHostPort) {
		// This constructor could change in the future
		this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
	}

	public void updateConfiguration(String coreFacadeHostname,
			int coreFacadeHostPort) {
		// This constructor has to be fixed, since it requires changes on the
		// class
		// eu.learnpad.core.rest.RestResource

	}

	@Override
	public void commentNotification(String modelSetId, String commentId,
			String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public void resourceNotification(String modelSetId, String resourceId,
			String artifactIds, String action) throws LpRestException {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream getModel(String modelSetId, String type)
			throws LpRestException {
		// Now send the package's path to the importer for XWiki
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/getmodel/%s",
				RestResource.REST_URI, modelSetId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type);
		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream model = null;
		try {
			model = getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public String startSimulation(String modelId, String currentUser,
			Collection<UserData> potentialUsers) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/simulation/start/%s",
				RestResource.REST_URI, modelId);
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Accept", "application/json");

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
	public Recommendations getRecommendations(String modelSetId,
			String artifactId, String userId) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/corefacade/recommendation",
				RestResource.REST_URI);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML);

		NameValuePair[] queryString = new NameValuePair[3];
		queryString[0] = new NameValuePair("modelsetid", modelSetId);
		queryString[1] = new NameValuePair("artifactid", artifactId);
		queryString[2] = new NameValuePair("userid", userId);
		getMethod.setQueryString(queryString);

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream feedbacksStream = null;
		try {
			feedbacksStream = getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Recommendations recommendations = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(Recommendations.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			recommendations = (Recommendations) unmarshaller.unmarshal(feedbacksStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recommendations;
		/*
		Client client = ClientBuilder.newClient();
        client.register(RestResource.getXWikiAuthenticator());
        
        
// We should look a way for accessing the annotations with reflection
//        eu.learnpad.or.Bridge.class.getAnnotation(Path.class).value();
        
        String URI = this.REST_URI + "learnpad/cw/corefacade/recommendation";
        
		Recommendations response = client.target(URI)
				.queryParam("modelsetid", modelSetId)
				.queryParam("artifactid", artifactId)
				.queryParam("userid", userId).request("application/xml")
				.get(Recommendations.class);
        
		return response;
		*/
	}

    @Override
    public InputStream transform(String type, InputStream model) throws LpRestException
    {
        HttpClient httpClient = RestResource.getClient();
        String uri = String.format("%s/learnpad/cw/corefacade/transform",
                RestResource.REST_URI);
        PostMethod postMethod = new PostMethod(uri);
        postMethod.addRequestHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM);
        postMethod.addRequestHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_OCTET_STREAM);

        NameValuePair[] queryString = new NameValuePair[1];
        queryString[0] = new NameValuePair("type", type);
        postMethod.setQueryString(queryString);
        
        RequestEntity requestEntity = new InputStreamRequestEntity(model);
        postMethod.setRequestEntity(requestEntity);

        try {
            httpClient.executeMethod(postMethod);
            return postMethod.getResponseBodyAsStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
