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

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import eu.learnpad.core.rest.DefaultRestResource;
import eu.learnpad.dash.rest.data.KPIValuesFormat;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.exception.impl.LpRestExceptionImpl;
import eu.learnpad.exception.impl.LpRestExceptionXWikiImpl;
import eu.learnpad.me.rest.data.KPIsFormat;
import eu.learnpad.me.rest.data.ModelSetType;
import eu.learnpad.or.CoreFacade;

/*
 * The methods inherited form the CoreFacade in this
 * class should be implemented as a REST invocation
 * toward the CoreFacade binded at the provided URL
 */
public class XwikiCoreFacadeRestResource extends DefaultRestResource implements
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
		// class eu.learnpad.core.rest.RestResource
	}

	@Override
	public byte[] getComments(String modelSetId, String artifactId)
			throws LpRestExceptionImpl {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getModel(String modelSetId, ModelSetType type)
			throws LpRestException {
		// Now send the package's path to the importer for XWiki
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/corefacade/getmodel/%s",
				DefaultRestResource.REST_URI, modelSetId);
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
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
		return model;
	}

	@Override
	public InputStream getExternalKPIs(String modelSetId, String kpisid, KPIsFormat type)
			throws LpRestException {
		// Now send the package's path to the importer for XWiki
		HttpClient httpClient = this.getClient();
		String uri = String.format("%s/learnpad/or/corefacade/getkpis/%s/%s",
				DefaultRestResource.REST_URI, modelSetId, kpisid);
		GetMethod getMethod = new GetMethod(uri);
		getMethod.addRequestHeader("Accept", "application/xml");

		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type.toString());
		getMethod.setQueryString(queryString);

		InputStream kpisStream = null;
		try {
			httpClient.executeMethod(getMethod);
			kpisStream = getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e.getCause());
		}
		return kpisStream;
	}

	@Override
	public void pushKPIValues(String modelSetId, KPIValuesFormat format,
			String businessActorId, InputStream cockpitContent)
			throws LpRestException {
		String contentType = "application/xml";

		HttpClient httpClient = this.getAnonymousClient();
		String uri = String.format("%s/learnpad/or/corefacade/pushkpivalues/%s",
				DefaultRestResource.REST_URI, modelSetId);
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Content-Type", contentType);

		NameValuePair[] queryString = new NameValuePair[2];
		queryString[0] = new NameValuePair("format", format.toString());
		queryString[1] = new NameValuePair("businessactor", businessActorId);
		putMethod.setQueryString(queryString);

		RequestEntity requestEntity = new InputStreamRequestEntity(
				cockpitContent);
		putMethod.setRequestEntity(requestEntity);

		try {
			httpClient.executeMethod(putMethod);
		} catch (IOException e) {
			throw new LpRestExceptionXWikiImpl(e.getMessage(), e);
		}
	}
}
