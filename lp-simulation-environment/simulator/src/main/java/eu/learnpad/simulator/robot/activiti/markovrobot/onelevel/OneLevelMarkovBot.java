package eu.learnpad.simulator.robot.activiti.markovrobot.onelevel;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2016 Linagora
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

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import eu.learnpad.simulator.robot.IRobot;

public class OneLevelMarkovBot implements IRobot<Map<String, Object>, Map<String, Object>> {

	private final TaskService taskService;
	private final RepositoryService repoService;
	private final Random rand;
	private final Collection<ObservationData> observationMemory = new ArrayList<>();

	public OneLevelMarkovBot(TaskService taskService, RepositoryService repoService, long seed,
			Collection<ObservationData> trainingData) {
		super();
		this.taskService = taskService;
		this.repoService = repoService;
		this.rand = new Random(seed);

		observationMemory.addAll(trainingData);

	}

	public OneLevelMarkovBot(TaskService taskService, RepositoryService repoService,
			Collection<ObservationData> trainingData) {
		this(taskService, repoService, new Random().nextLong(), trainingData);
	}

	public Map<String, Object> draw(ObservationData context) {

		List<ObservationData> candidates = new ArrayList<>();

		for (ObservationData candidate : observationMemory) {
			if (candidate.process.equals(context.process) && candidate.task.equals(context.task)
					&& context.input.entrySet().containsAll(candidate.input.entrySet())) {
				candidates.add(candidate);
			}
		}

		if (candidates.size() == 0) {
			return new HashMap<>();
		}

		final int drawnValue = rand.nextInt(candidates.size());

		return candidates.get(drawnValue).output;

	}

	@Override
	public Map<String, Object> handleTask(String taskId, Map<String, Object> inputData) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String taskKey = task.getTaskDefinitionKey();
		String processKey = repoService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId())
				.singleResult().getKey();
		return draw(new ObservationData(processKey, taskKey, inputData, new HashMap<String, Object>()));
	}

}
