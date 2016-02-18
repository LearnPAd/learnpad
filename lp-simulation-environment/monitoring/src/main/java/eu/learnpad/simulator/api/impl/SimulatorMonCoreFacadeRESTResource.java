package eu.learnpad.simulator.api.impl;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.net.ntp.TimeStamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
import eu.learnpad.simulator.api.impl.utils.LpMonRestException;
import eu.learnpad.simulator.api.impl.utils.RestResource;
import eu.learnpad.sim.CoreFacade;

public class SimulatorMonCoreFacadeRESTResource implements CoreFacade {

	private ObjectWriter objectWriter;
	
	public SimulatorMonCoreFacadeRESTResource() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		this.objectWriter = objectMapper.writer();
	}
	
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
	public void receiveSimulationStartEvent(SimulationStartEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/simulationstart",RestResource.REST_URI);
		int response = 0;
		try {
			
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveSimulationStartEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}		
	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/simulationend",RestResource.REST_URI);
		int response = 0;
		try {
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveSimulationEndEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}		
	}

	@Override
	public void receiveProcessStartEvent(ProcessStartEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/processstart",RestResource.REST_URI);
		int response = 0;
		try {
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveProcessStartEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}			
	}

	@Override
	public void receiveProcessEndEvent(ProcessEndEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/processend",RestResource.REST_URI);
		int response = 0;
		try {
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveProcessEndEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}			
		
	}

	@Override
	public void receiveTaskStartEvent(TaskStartEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/taskstart",RestResource.REST_URI);
		int response = 0;
		try {
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveTaskStartEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}			
	}

	@Override
	public void receiveTaskEndEvent(TaskEndEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/taskend",RestResource.REST_URI);
		int response = 0;
		try {
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveTaskEndEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}			
	}

	@Override
	public void receiveTaskFailedEvent(TaskFailedEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/taskfailed",RestResource.REST_URI);
		
		int response = 0;
		try {
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveTaskFailedEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}			
	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateEvent event) throws LpMonRestException {
		HttpClient httpClient = RestResource.getClient();
		String uri = String.format("%s/sessionscoreupdate",RestResource.REST_URI);
		int response = 0;
		try {
			String marshalledData = objectWriter.writeValueAsString(event);
		    PostMethod method = new PostMethod(uri);
			RequestEntity requestEntity = new StringRequestEntity(marshalledData,"application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
		    response = httpClient.executeMethod(method);
			
		} catch (IOException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), 
					SimulatorMonCoreFacadeRESTResource.class.getCanonicalName(),
					"Exception in receiveSessionScoreUpdateEvent\nResponse: " + response);
			throw new LpMonRestException(e.getMessage(), e.getCause());
		}			
	}
}
