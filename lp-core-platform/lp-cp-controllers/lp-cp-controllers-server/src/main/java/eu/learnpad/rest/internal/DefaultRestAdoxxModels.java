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

import javax.inject.Named;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.io.IOUtils;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;
import org.xwiki.rest.XWikiRestException;

import eu.learnpad.rest.RestAdoxxModels;

@Component
@Named("eu.learnpad.rest.internal.DefaultRestAdoxxModels")
public class DefaultRestAdoxxModels implements XWikiRestComponent,
		RestAdoxxModels {
	@Override
	public byte[] getAdoxxFile(String modelId) throws XWikiRestException {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		GetMethod getMethod = new GetMethod(
				"http://localhost:8080/xwiki/rest/wikis/xwiki/spaces/" + modelId
						+ "/pages/WebHome/attachments/" + modelId + ".adoxx");
		getMethod.addRequestHeader("Accept", "application/xml");
		getMethod.addRequestHeader("Accept-Ranges", "bytes");
		try {
			httpClient.executeMethod(getMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InputStream adoxxStream = getMethod.getResponseBodyAsStream();
			return IOUtils.toByteArray(adoxxStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void putAdoxxFile(String modelId, byte[] adoxxFile)
			throws XWikiRestException {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		PutMethod putMethod = new PutMethod(
				"http://localhost:8080/xwiki/rest/wikis/xwiki/spaces/"
						+ modelId + "/pages/WebHome/attachments/" + modelId
						+ ".adoxx");
		putMethod.addRequestHeader("Accept", "application/xml");
		putMethod.addRequestHeader("Accept-Ranges", "bytes");
		RequestEntity fileRequestEntity = new ByteArrayRequestEntity(adoxxFile,
				"application/xml");
		putMethod.setRequestEntity(fileRequestEntity);
		try {
			httpClient.executeMethod(putMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
