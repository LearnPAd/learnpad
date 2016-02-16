package eu.learnpad.simulator.api.impl;

import java.util.List;

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

public class SimulatorMonCoreFacadeImpl implements eu.learnpad.sim.CoreFacade {

	@Override
	public List<String> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData getUserData(String userartifactid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void receiveSimulationStartEvent(SimulationStartEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveProcessStartEvent(ProcessStartEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveProcessEndEvent(ProcessEndEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveTaskStartEvent(TaskStartEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveTaskEndEvent(TaskEndEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveTaskFailedEvent(TaskFailedEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateEvent event) throws LpRestException {
		// TODO Auto-generated method stub
		
	}

}
