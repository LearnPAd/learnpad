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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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

	public static Collection<List<MarkovData>> readTrainingData(
			Collection<String> files) {
		Collection<List<MarkovData>> res = new ArrayList<>();

		for (String file : files) {

			List<MarkovData> data = new ArrayList<>();

			Scanner in = new Scanner(MarkovBotFactory.class.getClassLoader()
					.getResourceAsStream(file));
			String text = "";
			String currentTask = in.nextLine();
			Map<String, Object> currentProps = new HashMap<>();
			while (in.hasNextLine()) {
				text = in.nextLine();
				if (text.equals("")) {

					data.add(new MarkovData(currentTask, currentProps));

					currentTask = in.nextLine();
					currentProps = new HashMap<>();
				} else {
					String[] split = text.split(";");
					if (split.length == 1) {
						currentProps.put(split[0], "");
					} else {
						// TODO: Implement proper attribute type handling
						if (split[1].equals("true")) {
							currentProps.put(split[0], true);
						} else if (split[1].equals("false")) {
							currentProps.put(split[0], false);
						} else {
							currentProps.put(split[0], split[1]);
						}
					}

				}
			}
			data.add(new MarkovData(currentTask, currentProps));
			in.close();
			res.add(data);
		}

		return res;
	}

}
