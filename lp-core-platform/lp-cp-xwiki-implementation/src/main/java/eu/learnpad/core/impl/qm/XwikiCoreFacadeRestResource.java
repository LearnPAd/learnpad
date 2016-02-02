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
package eu.learnpad.core.impl.qm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.qm.CoreFacade;
import eu.learnpad.core.rest.RestResource;

/*
 * The methods inherited form the CoreFacade in this
 * class should be implemented as a REST invocation
 * toward the CoreFacade binded at the provided URL
 */
public class XwikiCoreFacadeRestResource extends RestResource implements CoreFacade {

	public XwikiCoreFacadeRestResource() {
		this("localhost",8080);
	}

	public XwikiCoreFacadeRestResource(String coreFacadeHostname,
			int coreFacadeHostPort) {
		// This constructor could change in the future
		this.updateConfiguration(coreFacadeHostname, coreFacadeHostPort);
	}
	
	public void updateConfiguration(String coreFacadeHostname, int coreFacadeHostPort){
// This constructor has to be fixed, since it requires changes on the class
//		eu.learnpad.core.rest.RestResource
		
	}
	
	@Override
	public void publish(String questionnairesId, String type,
			byte[] questionnairesFile) throws LpRestException {
		// Now actually notifying the CP via REST
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/qm/corefacade/publish/%s",
				RestResource.REST_URI, questionnairesId);
		
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type);
		putMethod.setQueryString(queryString);
		
		InputStream stream = new ByteArrayInputStream(questionnairesFile);
		RequestEntity requestEntity = new InputStreamRequestEntity(stream);
		putMethod.setRequestEntity(requestEntity);

		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			LpRestExceptionXWikiImpl e1 = new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
			throw e1;
		}		
	}

	@Override
	public void genrationCompleted(String questionnairesId)
			throws LpRestException {
		// Now actually notifying the CP via REST
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/learnpad/qm/corefacade/genrationcompleted/%s",
				RestResource.REST_URI, questionnairesId);
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");

		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			LpRestExceptionXWikiImpl e1 = new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
			throw e1;
		}		
	}
	
}
