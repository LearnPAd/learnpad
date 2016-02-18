package eu.learnpad.simulator.mon.manager;

import java.util.List;
import java.util.Map;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.exception.LpRestException;
import eu.learnpad.sim.CoreFacade;
import eu.learnpad.simulator.mon.utils.DebugMessages;
import eu.learnpad.simulator.api.impl.SimulatorMonCoreFacadeRESTResource;
import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.sim.rest.event.impl.SimulationEndEvent;
import eu.learnpad.sim.rest.event.impl.ProcessStartEvent;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;

public class RestNotifier extends Thread {
	
    private volatile static CoreFacade platformCoreFacade = null;

    public RestNotifier() {
    }

    public static synchronized CoreFacade getCoreFacade() {
        if (platformCoreFacade == null) {
        	platformCoreFacade = new SimulatorMonCoreFacadeRESTResource();
        }
        return platformCoreFacade;
    }
    
//
//	public static void Main(String[] args) {
//		//test
//		Vector<String> testInvolvedUsers = new Vector<String>();
//		testInvolvedUsers.add("userOne");
//		testInvolvedUsers.add("userTwo");
//		testInvolvedUsers.add("userThree");
//		RestNotifier.notifySimulationStop(System.currentTimeMillis(), testInvolvedUsers, "sessionID", "modelSetID");
//		
//		
//	}
	
	
	public void run() {
	}
	
	public static void notifySimulationStart(Long processTimeStamp, List<String> involvedUsers, String sessionID, String modelSetID, Map<String, Object> simulationSessionData) {
		SimulationStartEvent event = new SimulationStartEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData);
		try {
			RestNotifier.getCoreFacade().receiveSimulationStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SimulatioStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySimulationStart method");
		}
	}
		
	public static void notifySimulationStop(Long processTimeStamp, List<String> involvedUsers, String sessionID, String modelSetID, Map<String, Object> simulationSessionData) {
		SimulationEndEvent event = new SimulationEndEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData);
		try {
			RestNotifier.getCoreFacade().receiveSimulationEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SimulatioStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySimulationStop method");
		}
	}
		
	public static void notifyProcessStart(Long processTimeStamp, List<String> involvedUsers, String sessionID, String modelSetID, Map<String, Object> simulationSessionData, String processID, String processDefinitionID) {
		ProcessStartEvent event = new ProcessStartEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processID, processDefinitionID);
		try {
			RestNotifier.getCoreFacade().receiveProcessStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStart method");
		}
	}
	
	public static void notifyProcessStop(Long processTimeStamp, List<String> involvedUsers, String sessionID, String modelSetID, Map<String, Object> simulationSessionData, String processID, String processDefinitionID) {
		ProcessEndEvent event = new ProcessEndEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processID, processDefinitionID);
		try {
			RestNotifier.getCoreFacade().receiveProcessEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStop method");
		}
	}
	
	public static void notifyTaskStart(Long processTimeStamp, String sessionID, List<String> involvedUsers, String modelSetID, Map<String, Object> simulationSessionData, String processID, String taskID, String taskDefinitionID, List<String> assignedUsers) {
		TaskStartEvent event = new TaskStartEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processID, taskID, taskDefinitionID, assignedUsers);
		try {
			RestNotifier.getCoreFacade().receiveTaskStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskStart method");
		}
	}
	
	public static void notifyTaskStop(Long processTimeStamp, String sessionID, List<String> involvedUsers, String modelSetID, Map<String, Object> simulationSessionData, String processID, String taskID, String taskDefinitionID, List<String> assignedUsers, String completingUserID, Map<String,Object> submittedData) {
		TaskEndEvent event = new TaskEndEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processID, taskID, taskDefinitionID, assignedUsers, completingUserID, submittedData);
		try {
			RestNotifier.getCoreFacade().receiveTaskEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskEndEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskEnd method");
		}
	}
	
	public static void notifySessionScoreUpdate(Long processTimeStamp, String sessionID, List<String> involvedUsers, String modelSetID, Map<String, Object> simulationSessionData, String processID, String userID, Long sessionScore) {
		SessionScoreUpdateEvent event = new SessionScoreUpdateEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processID, userID, sessionScore);
		try {
			RestNotifier.getCoreFacade().receiveSessionScoreUpdateEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SessionScoreUpdateEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySessionScoreUpdate method");
		}
	}
}
