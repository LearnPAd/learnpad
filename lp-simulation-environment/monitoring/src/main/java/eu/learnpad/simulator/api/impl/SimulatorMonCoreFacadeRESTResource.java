package eu.learnpad.simulator.api.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.rest.data.UserData;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.SimulationEndEvent;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskFailedEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.mon.utils.RestResource;

public class SimulatorMonCoreFacadeRESTResource implements eu.learnpad.sim.CoreFacade {

	@Override
	public List<String> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData getUserData(String userartifactid) {
		return null;
	}

	@Override
	public void receiveSimulationStartEvent(SimulationStartEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/simulationstart/%s",
				RestResource.REST_URI, event);
		int response = 0;
		// Request parameters and other properties.
		//List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		//params.add(new BasicNameValuePair("param-1", "asd"));
		//params.add(new BasicNameValuePair("param-2", "n1hehe"));
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveSimulationStartEvent\nResponse: " + response);
			e.printStackTrace();
		}		
	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/simulationend/%s",
				RestResource.REST_URI, event);
		int response = 0;
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveSimulationEndEvent\nResponse: " + response);
			e.printStackTrace();
		}		
	}

	@Override
	public void receiveProcessStartEvent(ProcessStartEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/processstart/%s",
				RestResource.REST_URI, event);
		int response = 0;
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveProcessStartEvent\nResponse: " + response);
			e.printStackTrace();
		}			
	}

	@Override
	public void receiveProcessEndEvent(ProcessEndEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/processend/%s",
				RestResource.REST_URI, event);
		int response = 0;
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveProcessEndEvent\nResponse: " + response);
			e.printStackTrace();
		}			
		
	}

	@Override
	public void receiveTaskStartEvent(TaskStartEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/taststart/%s",
				RestResource.REST_URI, event);
		int response = 0;
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveTaskStartEvent\nResponse: " + response);
			e.printStackTrace();
		}			
	}

	@Override
	public void receiveTaskEndEvent(TaskEndEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/taskend/%s",
				RestResource.REST_URI, event);
		int response = 0;
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveTaskEndEvent\nResponse: " + response);
			e.printStackTrace();
		}			
	}

	@Override
	public void receiveTaskFailedEvent(TaskFailedEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/taskfailed/%s",
				RestResource.REST_URI, event);
		int response = 0;
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveTaskFailedEvent\nResponse: " + response);
			e.printStackTrace();
		}			
	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateEvent event) throws LpRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/sessionscoreupdate/%s",
				RestResource.REST_URI, event);
		int response = 0;
		try {
		    PostMethod method = new PostMethod(uri);
			response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveSessionScoreUpdateEvent\nResponse: " + response);
			e.printStackTrace();
		}			
	}

}
