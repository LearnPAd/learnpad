package eu.learnpad.rest.utils;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

public class RestUtils {
	public String PROTOCOL = "http";
	public String HOSTNAME = "localhost";
	public int PORT = 8080;
	public String HOST = PROTOCOL + "://" + HOSTNAME + ":" + PORT;
	public String REST_URI = HOST + "/xwiki/rest";
	private String DEFAULT_USER = "Admin";
	private String DEFAULT_PASSWORD = "admin";

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
