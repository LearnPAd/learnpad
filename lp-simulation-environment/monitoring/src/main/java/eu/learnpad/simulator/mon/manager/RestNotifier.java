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
import eu.learnpad.sim.rest.event.impl.ScoreUpdateEvent;
import eu.learnpad.sim.rest.event.impl.ProcessEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskStartEvent;
import eu.learnpad.sim.rest.event.impl.TaskEndEvent;
import eu.learnpad.sim.rest.event.impl.TaskFailedEvent;
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
	
	public static void notifySimulationStartDemo(Long processTimeStamp, SimulationStartEvent event) {
		try {
			RestNotifier.getCoreFacade().receiveSimulationStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SimulatioStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySimulationStart method");
		}
	}
		
	public static void notifySimulationEnd(Long processTimeStamp, List<String> involvedUsers, String sessionID, String modelSetID, Map<String, Object> simulationSessionData) {
		SimulationEndEvent event = new SimulationEndEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData);
		try {
			RestNotifier.getCoreFacade().receiveSimulationEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SimulatioStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySimulationStop method");
		}
	}
	
	public static void notifySimulationEndDemo(Long processTimeStamp, SimulationEndEvent event) {
		try {
			RestNotifier.getCoreFacade().receiveSimulationEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SimulatioStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySimulationStop method");
		}
	}
		
	public static void notifyProcessStart(Long processTimeStamp, List<String> involvedUsers, String sessionID, String modelSetID, Map<String, Object> simulationSessionData, String processArtifactId) {
		ProcessStartEvent event = new ProcessStartEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processArtifactId);
		try {
			RestNotifier.getCoreFacade().receiveProcessStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStart method");
		}
	}
	
	public static void notifyProcessStartDemo(Long processTimeStamp, ProcessStartEvent event) {
		try {
			RestNotifier.getCoreFacade().receiveProcessStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStart method");
		}
	}
	
	public static void notifyProcessEnd(Long processTimeStamp, List<String> involvedUsers, String sessionID, String modelSetID, Map<String, Object> simulationSessionData, String processArtifactId) {
		ProcessEndEvent event = new ProcessEndEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processArtifactId);
		try {
			RestNotifier.getCoreFacade().receiveProcessEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStop method");
		}
	}
	
	public static void notifyProcessEndDemo(Long processTimeStamp, ProcessEndEvent event) {
		try {
			RestNotifier.getCoreFacade().receiveProcessEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStop method");
		}
	}
	
	public static void notifyTaskStart(Long processTimeStamp, String sessionID, List<String> involvedUsers, String modelSetID, Map<String, Object> simulationSessionData, String processArtifactId, String taskArtifactId, List<String> assignedUsers) {
		TaskStartEvent event = new TaskStartEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processArtifactId, taskArtifactId, assignedUsers);
		try {
			RestNotifier.getCoreFacade().receiveTaskStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskStart method");
		}
	}
	
	public static void notifyTaskStartDemo(Long processTimeStamp, TaskStartEvent event) {
		try {
			RestNotifier.getCoreFacade().receiveTaskStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskStart method");
		}
	}
	
	public static void notifyTaskFailedDemo(Long processTimeStamp, TaskFailedEvent event) {
		try {
			RestNotifier.getCoreFacade().receiveTaskFailedEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SessionScoreUpdateEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySessionScoreUpdate method");
		}
	}
	
	public static void notifyTaskEnd(Long processTimeStamp, String sessionID, List<String> involvedUsers, String modelSetID, Map<String, Object> simulationSessionData, String processArtifactId, String taskArtifactId, List<String> assignedUsers, String completingUserID, Map<String,Object> submittedData) {
		TaskEndEvent event = new TaskEndEvent(processTimeStamp, sessionID, involvedUsers, modelSetID, simulationSessionData, processArtifactId, taskArtifactId, assignedUsers, completingUserID, submittedData);
		try {
			RestNotifier.getCoreFacade().receiveTaskEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskEndEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskEnd method");
		}
	}
	
	public static void notifyTaskEndDemo(Long processTimeStamp, TaskEndEvent event) {
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
	
	public static void notifySessionScoreUpdateDemo(Long processTimeStamp, SessionScoreUpdateEvent event) {
		try {
			RestNotifier.getCoreFacade().receiveSessionScoreUpdateEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SessionScoreUpdateEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySessionScoreUpdate method");
		}
	}
	
	public static void notifyScoreUpdate(Long processTimeStamp, ScoreUpdateEvent event)  {
				
		//try {
			//TODO: RestNotifier.getCoreFacade().receiveScoreUpdateEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ScoreUpdateEvent sent");
		//} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyScoreUpdate method");
		//}
	}
	
}
