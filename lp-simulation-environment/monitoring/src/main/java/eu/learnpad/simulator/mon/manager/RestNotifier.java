package eu.learnpad.simulator.mon.manager;

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
	
	public static void notifySimulationStart(Long processTimeStamp, SimulationStartEvent event) {
		try {
			RestNotifier.getCoreFacade().notifySimulationStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SimulationStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySimulationStart method");
		}
	}
		
		
	public static void notifySimulationEnd(Long processTimeStamp, SimulationEndEvent event) {
		try {
			RestNotifier.getCoreFacade().notifySimulationEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SimulationStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySimulationStop method");
		}
	}
		
	public static void notifyProcessStart(Long processTimeStamp, ProcessStartEvent event) {
		try {
			RestNotifier.getCoreFacade().notifyProcessStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStart method");
		}
	}
	
	public static void notifyProcessEnd(Long processTimeStamp, ProcessEndEvent event) {
		try {
			RestNotifier.getCoreFacade().notifyProcessEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ProcessStopEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyProcessStop method");
		}
	}
	
	public static void notifyTaskStart(Long processTimeStamp, TaskStartEvent event) {
		try {
			RestNotifier.getCoreFacade().notifyTaskStartEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskStartEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskStart method");
		}
	}
	
	public static void notifyTaskFailed(Long processTimeStamp, TaskFailedEvent event) {
		try {
			RestNotifier.getCoreFacade().notifyTaskFailedEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskFailedEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskFailedEvent method");
		}
	}
	
	public static void notifyTaskEnd(Long processTimeStamp, TaskEndEvent event) {
		try {
			RestNotifier.getCoreFacade().notifyTaskEndEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "TaskEndEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyTaskEnd method");
		}
	}
	
	public static void notifySessionScoreUpdate(Long processTimeStamp, SessionScoreUpdateEvent event) {
		try {
			RestNotifier.getCoreFacade().notifySessionScoreUpdateEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "SessionScoreUpdateEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifySessionScoreUpdate method");
		}
	}
	
	public static void notifyScoreUpdate(Long processTimeStamp, ScoreUpdateEvent event)  {
		
		try {
			RestNotifier.getCoreFacade().notifyScoreUpdateEvent(event);
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "ScoreUpdateEvent sent");
		} catch (LpRestException e) {
			DebugMessages.println(TimeStamp.getCurrentTime(), RestNotifier.class.getSimpleName(), "Error in RestNotifier:notifyScoreUpdate method");
		}
	}
	
}
