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
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import eu.learnpad.core.rest.RestResource;
import eu.learnpad.cw.BridgeInterface;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.or.rest.data.Recommendations;
import eu.learnpad.rest.model.jaxb.PFResults;

/*
 * The methods inherited form the BridgeInterface in this
 * class should be implemented as a REST invocation
 * toward the BridgeInterface binded at the provided URL
 */
public class XwikiBridgeInterfaceRestResource extends RestResource implements
		BridgeInterface {

	public XwikiBridgeInterfaceRestResource() {
		this("localhost", 8080);
	}

	public XwikiBridgeInterfaceRestResource(String coreFacadeHostname,
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
	public byte[] getComments(String modelSetId, String artifactId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getResource(String modelSetId, String resourceId,
			String artifactIds, String action) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modelSetImported(String modelSetId, ModelSetType type)
			throws LpRestExceptionXWikiImpl {

		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/bridge/modelsetimported/%s",
				RestResource.REST_URI, modelSetId);
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type.toString());
		putMethod.setQueryString(queryString);
		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			e.printStackTrace();
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public void contentVerified(String modelSetId, String artifactId,
			String resourceId, String result) throws LpRestExceptionImpl {
		// TODO Auto-generated method stub

	}

	@Override
	public void modelVerified(String modelSetId, String result)
			throws LpRestExceptionXWikiImpl {

		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/bridge/modelverified/%s",
				RestResource.REST_URI, modelSetId);
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("result", result);
		putMethod.setQueryString(queryString);
		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			e.printStackTrace();
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}

	@Override
	public PFResults getFeedbacks(String modelSetId) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/bridge/%s/feedbacks",
				RestResource.REST_URI, modelSetId);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		try {
			httpClient.executeMethod(getMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream pfStream = null;
		try {
			pfStream = getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PFResults pf = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(PFResults.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			pf = (PFResults) unmarshaller.unmarshal(pfStream);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pf;
	}

	@Override
	public void notifyRecommendations(String modelSetId, String simulationid, String userId, Recommendations rec) throws LpRestException {		
		String contentType = "application/xml";

		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/cw/bridge/notify/%s",
				RestResource.REST_URI, modelSetId);

		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", contentType);

		NameValuePair[] queryString = new NameValuePair[2];
		queryString[0] = new NameValuePair("simulationid", simulationid);
		queryString[1] = new NameValuePair("userid", userId);
		putMethod.setQueryString(queryString);

		try {
			JAXBContext jc = JAXBContext.newInstance(Recommendations.class);
			Writer recWriter = new StringWriter();

			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(rec, recWriter);

			RequestEntity requestEntity = new StringRequestEntity(
					recWriter.toString(), contentType, "UTF-8");
			putMethod.setRequestEntity(requestEntity);

			httpClient.executeMethod(putMethod);
		} catch (JAXBException | IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
	}

}
