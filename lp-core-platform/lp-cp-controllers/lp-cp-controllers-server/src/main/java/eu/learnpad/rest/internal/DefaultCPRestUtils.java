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
package eu.learnpad.rest.internal;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;

import eu.learnpad.rest.CPRestUtils;
import eu.learnpad.rest.RestUtils;

@Component
public class DefaultCPRestUtils extends RestUtils implements CPRestUtils {

	@Inject
	private Logger logger;

	public InputStream getModel(String modelId, String type) {
		HttpClient httpClient = getClient("Admin", "admin");
		String uri = REST_URI + "/learnpad/model/" + modelId;
		
		GetMethod getMethod = new GetMethod(uri);
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("type", type);
		getMethod.setQueryString(queryString);
		getMethod.addRequestHeader("Accept", "application/xml");
		getMethod.addRequestHeader("Accept-Ranges", "bytes");

		try {
			httpClient.executeMethod(getMethod);
		} catch (HttpException e) {
			logger.error("Unable to process the GET request for model '"
					+ modelId + "' (" + type + ").", e);
			return null;
		} catch (IOException e) {
			logger.error("Unable to GET the '" + modelId + "' model (" + type
					+ ").", e);
			return null;
		}
		try {
			return getMethod.getResponseBodyAsStream();
		} catch (IOException e) {
			logger.error("Unable to extract the '" + modelId + "' model ("
					+ type + ") from GET response.", e);
			return null;
		}
	}
}
