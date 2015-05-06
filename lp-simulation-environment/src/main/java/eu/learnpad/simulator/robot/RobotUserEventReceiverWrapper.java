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
import eu.learnpad.simulator.datastructures.LearnPadTask;
import eu.learnpad.simulator.datastructures.LearnPadTaskSubmissionResult;

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

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.IProcessEventReceiver#sendTask(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String,
	 * java.util.Collection)
	 */
	@Override
	public void sendTask(LearnPadTask task, Collection<String> users) {

		// propagate removing robots from users list
		Collection<String> newUsers = new HashSet<String>(users);
		newUsers.removeAll(robots.keySet());
		eventReceiver.sendTask(task, newUsers);

		for (String user : robots.keySet()) {
			if (users.contains(user)) {

				// solve the task
				Map<String, Object> outData = robots.get(user).handleTask(
						task.id, inputExtractor.getInput(task.id));

				// submit the answer
				LearnPadTaskSubmissionResult result = processManager
						.submitTaskCompletion(task, user, outData);

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
							+ " for completion of the task " + task.id);
					break;
				}

				// no need for several robots to handle the same task
				break;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * eu.learnpad.simulator.IProcessEventReceiver#signalProcessEnd(java.lang
	 * .String, java.util.Collection)
	 */
	@Override
	public void signalProcessEnd(String processId, Collection<String> users) {

		// propagate
		eventReceiver.signalProcessEnd(processId, users);

		robots.remove(processId);

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

}
