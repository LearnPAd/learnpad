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
package eu.learnpad.rest.utils;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

public class RestResource {
	public String PROTOCOL = "http";
	public String HOSTNAME = "localhost";
	public int PORT = 8080;
	public String HOST = PROTOCOL + "://" + HOSTNAME + ":" + PORT;
	public String REST_URI = HOST + "/xwiki/rest";
	private String DEFAULT_USER = "superadmin";
	private String DEFAULT_PASSWORD = "LearnPAss";

	public HttpClient getClient() {
		return getClient(DEFAULT_USER, DEFAULT_PASSWORD);
	}

	public HttpClient getClient(String userName, String password) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials credentials = new UsernamePasswordCredentials(userName,
				password);
		AuthScope authentication = new AuthScope(this.HOSTNAME, this.PORT,
				AuthScope.ANY_REALM);
		httpClient.getState().setCredentials(authentication, credentials);
		return httpClient;
	}
}
