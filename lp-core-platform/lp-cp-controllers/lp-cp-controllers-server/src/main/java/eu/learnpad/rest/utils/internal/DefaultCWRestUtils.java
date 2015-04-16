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
package eu.learnpad.rest.utils.internal;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;

import eu.learnpad.rest.utils.CWRestUtils;
import eu.learnpad.rest.utils.RestResource;

@Component
@Singleton
public class DefaultCWRestUtils extends RestResource implements CWRestUtils {

	@Inject
	private Logger logger;

	@Override
	public boolean postNotifyModel(String modelId, String type) {
		HttpClient httpClient = getClient();

		String uri = REST_URI + "/learnpad/cw/notify/model";
		PostMethod postMethod = new PostMethod(uri);
		postMethod.addRequestHeader("Accept", "application/xml");
		postMethod.addRequestHeader("Accept-Ranges", "bytes");
		
		NameValuePair[] queryString = new NameValuePair[2];
		queryString[0] = new NameValuePair("modelid", modelId);
		queryString[1] = new NameValuePair("type", type);
		postMethod.setQueryString(queryString);
		
		try {
			httpClient.executeMethod(postMethod);
			return true;
		} catch (HttpException e) {
			logger.error(
					"Unable to process the notification request to Collaborative Workspace for the '"
							+ modelId + "' model (" + type + ").", e);
			return false;
		} catch (IOException e) {
			logger.error("Unable to notify Collaborative Workspace of the '"
					+ modelId + "' model (" + type + ").", e);
			return false;
		}
	}

	@Override
	public boolean putXWikiPackage(String packagePath) {
		HttpClient httpClient = getClient();

		String uri = REST_URI + "/learnpad/cw/package";
		PutMethod putMethod = new PutMethod(uri);
		putMethod.addRequestHeader("Accept", "application/xml");
		putMethod.addRequestHeader("Accept-Ranges", "bytes");
		
		NameValuePair[] queryString = new NameValuePair[1];
		queryString[0] = new NameValuePair("path", packagePath);
		putMethod.setQueryString(queryString);
		
		try {
			httpClient.executeMethod(putMethod);
			return true;
		} catch (HttpException e) {
			logger.error(
					"Unable to process the PUT request for XWiki package '"
							+ packagePath
							+ "' onto the Collaborative Workspace.", e);
			return false;
		} catch (IOException e) {
			logger.error("Unable to PUT XWiki package '" + packagePath
					+ "' to the Collaborative Workspace.", e);
			return false;
		}
	}
}