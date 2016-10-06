/**
 *
 */
package eu.learnpad.simulator.robot.activiti.markovrobot.onelevel;

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
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;

import eu.learnpad.simulator.robot.IRobot;
import eu.learnpad.simulator.robot.IRobotFactory;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class OneLevelMarkovBotFactory implements IRobotFactory<Map<String, Object>, Map<String, Object>> {

	private final TaskService taskService;
	private final RepositoryService repoService;
	private final Collection<ObservationData> trainingData;
	private final Random rand;

	public OneLevelMarkovBotFactory(TaskService taskService, RepositoryService repoService,
			Collection<ObservationData> trainingData, Long seed) {
		super();
		this.taskService = taskService;
		this.repoService = repoService;
		this.trainingData = trainingData;
		this.rand = new Random(seed);
	}

	public OneLevelMarkovBotFactory(TaskService taskService, RepositoryService repoService,
			Collection<ObservationData> trainingData) {
		this(taskService, repoService, trainingData, new Random().nextLong());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see eu.learnpad.simulator.robot.IRobotFactory#createRobot()
	 */
	@Override
	public IRobot<Map<String, Object>, Map<String, Object>> createRobot() {
		return new OneLevelMarkovBot(taskService, repoService, rand.nextLong(), trainingData);
	}

	@SuppressWarnings("unchecked")
	public static Collection<ObservationData> readTrainingData(Collection<String> files) {
		Collection<ObservationData> res = new ArrayList<>();

		for (String file : files) {

			// read JSON data
			Scanner in = new Scanner(OneLevelMarkovBotFactory.class.getClassLoader().getResourceAsStream(file));
			String jsonText = "";
			while (in.hasNextLine()) {
				jsonText = jsonText + in.nextLine();
			}
			in.close();
			System.out.println(jsonText);

			JSONObject jsonObject = new JSONObject(jsonText);

			// transform to collection of Observation data
			Iterator<String> processIter = jsonObject.keys();

			while (processIter.hasNext()) {
				String processKey = processIter.next();

				JSONObject taskObject = jsonObject.getJSONObject(processKey);
				Iterator<String> taskIter = taskObject.keys();

				while (taskIter.hasNext()) {
					String taskKey = taskIter.next();

					JSONArray obsArray = taskObject.getJSONArray(taskKey);

					for (int i = 0; i < obsArray.length(); i++) {
						JSONObject obs = obsArray.getJSONObject(i);

						Map<String, Object> input = new HashMap<>();
						JSONObject inputJs = obs.getJSONObject("input");
						Iterator<String> inputIter = inputJs.keys();
						while (inputIter.hasNext()) {
							String inputKey = inputIter.next();
							input.put(inputKey, inputJs.get(inputKey));
						}

						Map<String, Object> output = new HashMap<>();
						JSONObject outputJs = obs.getJSONObject("output");
						Iterator<String> outputIter = outputJs.keys();
						while (outputIter.hasNext()) {
							String outputKey = outputIter.next();
							output.put(outputKey, outputJs.get(outputKey));
						}

						res.add(new ObservationData(processKey, taskKey, input, output));

					}

				}

			}
		}

		return res;
	}

}
