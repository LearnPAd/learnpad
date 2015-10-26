/**
 *
 */
package eu.learnpad.simulator.robot.activiti.markovrobot;

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
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.activiti.engine.TaskService;

import eu.learnpad.simulator.robot.IRobot;
import eu.learnpad.simulator.robot.IRobotFactory;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class MarkovBotFactory implements
		IRobotFactory<Map<String, Object>, Map<String, Object>> {

	private final TaskService taskService;
	private final Collection<List<MarkovData>> trainingData;
	private final Random rand;

	public MarkovBotFactory(TaskService taskService,
			Collection<List<MarkovData>> trainingData, Long seed) {
		super();
		this.taskService = taskService;
		this.trainingData = trainingData;
		this.rand = new Random(seed);
	}

	public MarkovBotFactory(TaskService taskService,
			Collection<List<MarkovData>> trainingData) {
		this(taskService, trainingData, new Random().nextLong());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.robot.IRobotFactory#createRobot()
	 */
	@Override
	public IRobot<Map<String, Object>, Map<String, Object>> createRobot() {
		return new MarkovBot(taskService, rand.nextLong(), trainingData);
	}

}
