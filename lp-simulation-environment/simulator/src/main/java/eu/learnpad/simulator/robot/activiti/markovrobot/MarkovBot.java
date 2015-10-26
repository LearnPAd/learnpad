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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.activiti.engine.TaskService;

import eu.learnpad.simulator.robot.IRobot;
import eu.learnpad.simulator.utils.SimpleMarkovModel;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class MarkovBot implements
		IRobot<Map<String, Object>, Map<String, Object>> {

	private final TaskService taskService;
	private final SimpleMarkovModel<MarkovData> markov;

	public static final MarkovData startToken = new MarkovData(
			"# markov startoken", new HashMap<String, Object>());

	private static final int contextSize = 1;

	public MarkovBot(TaskService taskService, long seed,
			Collection<List<MarkovData>> trainingData) {
		super();
		this.taskService = taskService;
		this.markov = new SimpleMarkovModel<MarkovData>(contextSize,
				MarkovBot.startToken, seed);

		for (List<MarkovData> series : trainingData) {
			markov.addSeries(series.subList(0, contextSize),
					series.subList(contextSize, series.size()));
		}
	}

	public MarkovBot(TaskService taskService,
			Collection<List<MarkovData>> trainingData) {
		this(taskService, new Random().nextLong(), trainingData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.learnpad.simulator.robot.IRobot#handleTask(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public Map<String, Object> handleTask(String taskId,
			Map<String, Object> inputData) {
		return markov.draw(Arrays.asList(new MarkovData(taskService
				.createTaskQuery().taskId(taskId).singleResult()
				.getTaskDefinitionKey(), inputData))).properties;
	}
}
