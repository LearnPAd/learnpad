package eu.learnpad.simulator.api.impl.utils;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

import eu.learnpad.simulator.mon.MainMonitoring;
import eu.learnpad.simulator.mon.utils.Manager;

public class RestResource {
	final private static String PROTOCOL = "http";
	final private static String HOSTNAME = Manager.Read(MainMonitoring.RESTNOTIFIERURLSTRING).getProperty("post.rest.url");
	final private static int PORT = 8080;
	final private static int SIM_PORT = 8081;
	final private static String HOST = PROTOCOL + "://" + HOSTNAME + ":" + PORT;
	final private static String SIM_HOST = PROTOCOL + "://" + HOSTNAME + ":" + SIM_PORT;
	final private static String DEFAULT_USER = "superadmin";
	final private static String DEFAULT_PASSWORD = "LearnPAss";
	final public static String REST_URI = HOST + "/xwiki/rest/learnpad/sim/corefacade";
	final public static String SIM_REST_URI = SIM_HOST;

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

	public static HttpClient getAnonymousClient() {
		HttpClient httpClient = new HttpClient();
		return httpClient;
	}
}