/**
 *
 */
package eu.learnpad.simulator.processmanager.activiti.taskvalidator;

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

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;

import eu.learnpad.simulator.processmanager.ITaskValidator;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiDemoTaskValidator implements
		ITaskValidator<Map<String, Object>, Map<String, Object>> {

	private final RepositoryService repositoryService;
	private final TaskService taskService;
	private final JSONObject jsonDB;

	/**
	 * @param taskService
	 * @throws FileNotFoundException
	 */
	public ActivitiDemoTaskValidator(RepositoryService repositoryService,
			TaskService taskService) throws FileNotFoundException {
		super();
		this.repositoryService = repositoryService;
		this.taskService = taskService;

		Scanner in = new Scanner(getClass().getClassLoader()
				.getResourceAsStream("validation_db.json"));
		String jsonText = "";
		while (in.hasNextLine()) {
			jsonText = jsonText + in.nextLine();
		}
		in.close();

		jsonDB = new JSONObject(jsonText);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.ITaskValidator#taskResultIsValid(java.lang.String,
	 * java.lang.Object, java.lang.Object)
	 */
	public boolean taskResultIsValid(String taskId, Map<String, Object> inData,
			Map<String, Object> proposedResult) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		System.out.println("validating " + taskId + " ("
				+ task.getTaskDefinitionKey() + ") with " + proposedResult);

		String processKey = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(task.getProcessDefinitionId())
				.singleResult().getKey();

		JSONObject process = jsonDB.optJSONObject(processKey);
		if (process != null) {

			JSONArray tests = process.optJSONArray(task.getTaskDefinitionKey());
			if (tests != null) {

				for (int i = 0; i < tests.length(); i++) {
					JSONObject test = tests.getJSONObject(i);
					boolean isRelevant = true;
					Iterator<String> propIter = test.getJSONObject("input")
							.keys();
					while (propIter.hasNext() && isRelevant) {
						String prop = propIter.next();
						isRelevant = inData.containsKey(prop)
								&& inData
										.get(prop)
										.toString()
										.equals(test.getJSONObject("input")
												.get(prop).toString());
					}

					if (isRelevant) {
						boolean isOk = true;
						propIter = test.getJSONObject("output").keys();
						while (propIter.hasNext() && isOk) {
							String prop = propIter.next();
							isOk = proposedResult.containsKey(prop)
									&& proposedResult
											.get(prop)
											.toString()
											.equals(test
													.getJSONObject("output")
													.get(prop).toString());
						}

						if (!isOk) {
							// we can stop right now, one of the tests is
							// incorrect
							return false;
						}
					}
				}
			}
		}

		// return true by default
		return true;
	}
}
