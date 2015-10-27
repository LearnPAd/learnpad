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
package eu.learnpad.core.rest;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

public class RestResource {
	final private static String PROTOCOL = "http";
	final private static String HOSTNAME = "localhost";
	final private static int PORT = 8080;
	final private static int SIM_PORT = 8081;
	final private static String HOST = PROTOCOL + "://" + HOSTNAME + ":" + PORT;
	final private static String SIM_HOST = PROTOCOL + "://" + HOSTNAME + ":" + SIM_PORT;
	final private static String DEFAULT_USER = "superadmin";
	final private static String DEFAULT_PASSWORD = "LearnPAss";
	final public static String REST_URI = HOST + "/xwiki/rest";
	final public static String SIM_REST_URI = SIM_HOST;
	final public static String CORE_REPOSITORY_WIKI = "xwiki";
	final public static String CORE_REPOSITORY_SPACE = "CoreRepository";

	public static HttpClient getClient() {
		return getClient(RestResource.DEFAULT_USER, RestResource.DEFAULT_PASSWORD);
	}

	public static HttpClient getClient(String userName, String password) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials credentials = new UsernamePasswordCredentials(userName,
				password);
		AuthScope authentication = new AuthScope(RestResource.HOSTNAME, RestResource.PORT,
				AuthScope.ANY_REALM);
		httpClient.getState().setCredentials(authentication, credentials);
		return httpClient;
	}
}
