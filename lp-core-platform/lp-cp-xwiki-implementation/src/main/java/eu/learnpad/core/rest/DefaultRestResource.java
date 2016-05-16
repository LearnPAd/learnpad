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

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.xwiki.component.annotation.Component;
import org.xwiki.configuration.ConfigurationSource;

@Component
@Named("default")
public class DefaultRestResource implements RestResource {

	@Inject
	@Named("learnpadproperties")
	public ConfigurationSource configurationSource;

	public String restPrefix;

	final private static String PROTOCOL = "http";

	final private static String HOSTNAME = "localhost";

	final private static int PORT = 8080;

	final private static String HOST = PROTOCOL + "://" + HOSTNAME + ":" + PORT;

	final public static String REST_URI = HOST + "/xwiki/rest";

	final private static String DEFAULT_USER = "superadmin";

	final private static String DEFAULT_PASSWORD = "LearnPAss";

	final public static String CORE_REPOSITORY_WIKI = "xwiki";

	final public static String CORE_REPOSITORY_SPACE = "CoreRepository";

	public HttpClient getClient() {
		return getClient(DefaultRestResource.DEFAULT_USER, DefaultRestResource.DEFAULT_PASSWORD);
	}

	public HttpClient getClient(String userName, String password) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setAuthenticationPreemptive(true);
		Credentials credentials = new UsernamePasswordCredentials(userName, password);
		AuthScope authentication = new AuthScope(DefaultRestResource.HOSTNAME, DefaultRestResource.PORT,
				AuthScope.ANY_REALM);
		httpClient.getState().setCredentials(authentication, credentials);
		return httpClient;
	}

	public HttpClient getAnonymousClient() {
		HttpClient httpClient = new HttpClient();
		return httpClient;
	}
}