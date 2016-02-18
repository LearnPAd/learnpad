package eu.learnpad.simulator.mon;

import java.util.Vector;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.sim.rest.event.impl.SimulationStartEvent;
import eu.learnpad.simulator.api.impl.SimulatorMonCoreFacadeRESTResource;
import eu.learnpad.simulator.api.impl.utils.LpMonRestException;

public class TestSimulatorMonCoreFacadeRESTResource {

	public static void main(String[] args) {
		SimulatorMonCoreFacadeRESTResource simMinRest = new SimulatorMonCoreFacadeRESTResource();
		
		Vector<String> involvedUsers = new Vector<String>();
		involvedUsers.add("involvedUsers1");
		involvedUsers.add("involvedUsers2");
		
		SimulationStartEvent event = new SimulationStartEvent(TimeStamp.getCurrentTime().getTime(),
				"theSimsessionID",involvedUsers,"theModelSetID");
		
		try {
			simMinRest.receiveSimulationStartEvent(event);
		} catch (LpMonRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
