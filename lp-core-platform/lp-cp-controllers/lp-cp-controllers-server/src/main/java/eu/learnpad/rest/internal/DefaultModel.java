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
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;
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
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.xwiki.component.annotation.Component;
import org.xwiki.rest.XWikiRestComponent;
import org.xwiki.rest.XWikiRestException;

import eu.learnpad.rest.Model;
import eu.learnpad.rest.utils.CWRestUtils;

@Component
@Named("eu.learnpad.rest.internal.DefaultModel")
public class DefaultModel implements XWikiRestComponent, Model {

	@Inject
	CWRestUtils cwRestUtils;

	@Override
	public byte[] getModel(String modelId, String type)
			throws XWikiRestException {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		GetMethod getMethod = new GetMethod(
				"http://localhost:8080/xwiki/rest/wikis/xwiki/spaces/"
						+ modelId + "/pages/WebHome/attachments/" + modelId
						+ "." + type);
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

	private boolean isPage(String spaceName, String pageName) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		GetMethod getMethod = new GetMethod(
				"http://localhost:8080/xwiki/rest/wikis/xwiki/spaces/"
						+ spaceName + "/pages/" + pageName);
		getMethod.addRequestHeader("Accept", "application/xml");
		getMethod.addRequestHeader("Accept-Ranges", "bytes");
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == 200) {
				return true;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean putPage(String spaceName, String pageName) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials defaultcreds = new UsernamePasswordCredentials("Admin",
				"admin");
		httpClient.getState().setCredentials(
				new AuthScope("localhost", 8080, AuthScope.ANY_REALM),
				defaultcreds);

		PutMethod putMethod = new PutMethod(
				"http://localhost:8080/xwiki/rest/wikis/xwiki/spaces/"
						+ spaceName + "/pages/" + pageName);
		putMethod.addRequestHeader("Accept", "application/xml");
		putMethod.addRequestHeader("Accept-Ranges", "bytes");
		String pageXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><page xmlns=\"http://www.xwiki.org\"><content/></page>";
		RequestEntity pageRequestEntity = null;
		try {
			pageRequestEntity = new StringRequestEntity(pageXML,
					"application/xml", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		putMethod.setRequestEntity(pageRequestEntity);
		try {
			int statusCode = httpClient.executeMethod(putMethod);
			if (statusCode == 200) {
				return true;
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void putModel(String modelId, String type, byte[] adoxxFile)
			throws XWikiRestException {
		if (!isPage(modelId, "webHome")) {
			putPage(modelId, "WebHome");
		}
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
						+ "." + type);
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
		cwRestUtils.postNotifyModel(modelId, type);
	}
}
