/**
 *
 */
package eu.learnpad.simulator.robot;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import eu.learnpad.simulator.IProcessEventReceiver;
import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.IRobotHandler;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;
import eu.learnpad.simulator.monitoring.event.impl.ProcessEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.ProcessStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SessionScoreUpdateSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.SimulationStartSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskEndSimEvent;
import eu.learnpad.simulator.monitoring.event.impl.TaskStartSimEvent;

/**
 * This class is supposed to "wrap" a {@link IProcessEventReceiver} in order to
 * intercept task events that should be handled by robots.
 *
 * It implements the {@link IRobotHandler} API to handle robots adding/removal
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class RobotUserEventReceiverWrapper<TaskInput> implements
IProcessEventReceiver, IRobotHandler {

	private final IProcessEventReceiver eventReceiver;
	private final IRobotFactory<TaskInput, Map<String, Object>> robotFactory;
	private final IRobotInputExtractor<TaskInput> inputExtractor;
	private final IProcessManager processManager;

	// map of userId -> robot
	private final Map<String, IRobot<TaskInput, Map<String, Object>>> robots = new HashMap<String, IRobot<TaskInput, Map<String, Object>>>();

	public RobotUserEventReceiverWrapper(IProcessEventReceiver eventReceiver,
			IRobotFactory<TaskInput, Map<String, Object>> robotFactory,
			IRobotInputExtractor<TaskInput> inputExtractor,
			IProcessManager processManager) {
		super();
		this.eventReceiver = eventReceiver;
		this.robotFactory = robotFactory;
		this.inputExtractor = inputExtractor;
		this.processManager = processManager;
	}

	// robot handling

	/*
	 * (non-Javadoc)
	 *
	 * @see eu.learnpad.simulator.robot.IRobotHandler#addRobot(java.lang.String)
	 */
	@Override
	public void addRobot(String userId) {
		robots.put(userId, robotFactory.createRobot());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.robot.IRobotHandler#removeRobot(java.lang.String)
	 */
	@Override
	public void removeRobot(String userId) {
		robots.remove(userId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eu.learnpad.simulator.robot.IRobotHandler#getRobots()
	 */
	@Override
	public Set<String> getRobots() {
		return robots.keySet();
	}

	// event handling
	// note: most events are simply forwarded, some events trigger additional
	// action

	@Override
	public void receiveSimulationStartEvent(SimulationStartSimEvent event) {
		eventReceiver.receiveSimulationStartEvent(event);
	}

	@Override
	public void receiveSimulationEndEvent(SimulationEndSimEvent event) {
		eventReceiver.receiveSimulationEndEvent(event);
	}

	@Override
	public void receiveProcessStartEvent(ProcessStartSimEvent event) {
		eventReceiver.receiveProcessStartEvent(event);
	}

	@Override
	public void receiveProcessEndEvent(ProcessEndSimEvent event) {
		// propagate
		eventReceiver.receiveProcessEndEvent(event);

		robots.remove(event.processInstance.processartifactid);
	}

	@Override
	public void receiveTaskStartEvent(TaskStartSimEvent event) {

		// propagate removing robots from users list
		Collection<String> newUsers = new HashSet<String>(event.involvedusers);
		newUsers.removeAll(robots.keySet());
		eventReceiver.receiveTaskStartEvent(event);

		for (String user : robots.keySet()) {
			if (event.involvedusers.contains(user)) {

				// solve the task
				Map<String, Object> outData = robots.get(user).handleTask(
						event.task.id, inputExtractor.getInput(event.task.id));

				// submit the answer
				LearnPadTaskSubmissionResult result = processManager
						.submitTaskCompletion(event.task, user, outData);

				// check task validation status
				switch (result.status) {
				case VALIDATED:
				case ALREADY_COMPLETED:
					// ok
					break;

				case REJECTED:
				case UNKOWN_TASK:
				case UNKOWN_ERROR:
					// weird error...
					System.err
					.println("RobotUserEventReceiverWrapper: received weird response status "
							+ result.status
							+ " for completion of the task "
							+ event.task.id);
					break;
				}

				// no need for several robots to handle the same task
				break;
			}
		}

	}

	@Override
	public void receiveTaskEndEvent(TaskEndSimEvent event) {
		eventReceiver.receiveTaskEndEvent(event);
	}

	@Override
	public void receiveSessionScoreUpdateEvent(SessionScoreUpdateSimEvent event) {
		eventReceiver.receiveSessionScoreUpdateEvent(event);
	}

}
