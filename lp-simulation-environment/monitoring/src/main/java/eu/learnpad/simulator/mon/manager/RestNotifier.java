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

import eu.learnpad.sim.CoreFacade;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.api.impl.SimulatorMonCoreFacadeImpl;
import eu.learnpad.sim.rest.event.AbstractEvent;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;

public class RestNotifier extends Thread {
	
	private volatile static CoreFacade platformCoreFacade = new SimulatorMonCoreFacadeImpl(); 
	
	public RestNotifier() { 
	}

	public void run() {
		
	}
	
	public static void notifySimulationStart(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
		//AbstractEvent event = new SimulationStartEvent(processTimeStamp,  sessionID,  , modelsetid)
				
	//	platformCoreFacade.receiveSimulationStartEvent( );
	}
		
	public static void notifySimulationStop(String processID, String processName, String processTimeStamp,	String learnerID, String sessionID) {
		
	}
		
	public static void notifyProcessStart(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
	}
	
	public static void notifyProcessStop(String processID, String processName, String processTimeStamp,	String learnerID, String sessionID) {
	}
	
	public static void notifyTaskStart(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
	}
	
	public static void notifyTaskStop(String processID, String processName, String processTimeStamp, String learnerID, String sessionID) {
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
	
	public static void notifySessionScoreUpdates(String learnersID, int idPath, String idBPMN, float sessionScore) {
		// TODO Auto-generated method stub
		
	}
}
