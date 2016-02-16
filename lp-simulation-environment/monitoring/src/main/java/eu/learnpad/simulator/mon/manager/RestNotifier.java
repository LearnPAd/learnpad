package eu.learnpad.simulator.mon.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import eu.learnpad.simulator.mon.utils.DebugMessages;

public class RestNotifier extends Thread {
	
	private static HttpClient client;
	private static HttpPost post;
	private static HttpResponse response;
	
	public RestNotifier(String restEventNotificationURL) {
		client = HttpClientBuilder.create().build();
		post = new HttpPost(restEventNotificationURL); 
	}

	public void run() {
		
	}
	
	public static void notifySimulationStart(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
		RestNotifier.executePostAction(processID, processName, processTimeStamp, learnerID, sessionID);
	}
		
	public static void notifySimulationStop(String processID, String processName, String processTimeStamp,	String learnerID, String sessionID) {
		RestNotifier.executePostAction(processID, processName, processTimeStamp, learnerID, sessionID);
	}
		
	public static void notifyProcessStart(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
		RestNotifier.executePostAction(processID, processName, processTimeStamp, learnerID, sessionID);
	}
	
	public static void notifyProcessStop(String processID, String processName, String processTimeStamp,	String learnerID, String sessionID) {
		RestNotifier.executePostAction(processID, processName, processTimeStamp, learnerID, sessionID);
	}
	
	public static void notifyTaskStart(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
		RestNotifier.executePostAction(processID, processName, processTimeStamp, learnerID, sessionID);
	}
	
	public static void notifyTaskStop(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
		RestNotifier.executePostAction(processID, processName, processTimeStamp, learnerID, sessionID);
	}
	
	
	public static List<BasicNameValuePair> setupValue(String processID, String processName, String processTimeStamp,
																		String learnerID, String sessionID) {
		List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("processID", processID));
		nameValuePairs.add(new BasicNameValuePair("processName", processName));
		nameValuePairs.add(new BasicNameValuePair("processTimeStamp", processTimeStamp));
		nameValuePairs.add(new BasicNameValuePair("learnerID", learnerID));
		nameValuePairs.add(new BasicNameValuePair("sessionID", sessionID));
		return nameValuePairs;
	}
	
	public static void executePostAction(String processID, String processName, String processTimeStamp,
																		String learnerID, String sessionID) {
		try {
			post.setEntity(new UrlEncodedFormEntity(RestNotifier.setupValue(processID,processName, processTimeStamp, learnerID, sessionID)));		
			response = client.execute(post);
	
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(),e.getCause().toString());
		}
	}

	public static void notifySessionScoreUpdates(String learnersID, int idPath, String idBPMN, float sessionScore) {
		// TODO Auto-generated method stub
		
	}
}
