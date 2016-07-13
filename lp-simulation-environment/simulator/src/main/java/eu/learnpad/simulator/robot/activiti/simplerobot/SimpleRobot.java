/**
 *
 */
package eu.learnpad.simulator.robot.activiti.simplerobot;

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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;

import eu.learnpad.simulator.datastructures.document.LearnPadDocumentField;
import eu.learnpad.simulator.robot.IRobot;
import eu.learnpad.simulator.uihandler.formhandler.AbstractFormHandler;

/**
 * This robot uses the validation database to construct task responses which are
 * known to be correct. When encountering values for which it has no data, it
 * will use a default value.
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class SimpleRobot implements
		IRobot<Map<String, Object>, Map<String, Object>> {

	private final RepositoryService repositoryService;
	private final TaskService taskService;
	private final AbstractFormHandler formHandler;
	private final JSONObject jsonDB;

	public SimpleRobot(RepositoryService repositoryService,
			TaskService taskService, AbstractFormHandler formHandler) {
		super();
		this.repositoryService = repositoryService;
		this.taskService = taskService;
		this.formHandler = formHandler;

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
	 * @see eu.learnpad.simulator.robot.IRobot#handleTask(java.lang.String,
	 * java.lang.Object)
	 */
	public Map<String, Object> handleTask(String taskId,
			Map<String, Object> inputData) {

		Map<String, Object> result = new HashMap<>();

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		String processKey = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(task.getProcessDefinitionId())
				.singleResult().getKey();

		JSONObject process = jsonDB.optJSONObject(processKey);
		if (process != null) {

			JSONArray tests = process.optJSONArray(task.getTaskDefinitionKey());

			if (tests != null) {

				// collect all the output we know to be valid

				for (int i = 0; i < tests.length(); i++) {
					JSONObject test = tests.getJSONObject(i);
					boolean isRelevant = true;
					Iterator<String> propIter = test.getJSONObject("input")
							.keys();
					while (propIter.hasNext() && isRelevant) {
						String prop = propIter.next();
						isRelevant = inputData.containsKey(prop)
								&& inputData
								.get(prop)
								.toString()
								.equals(test.getJSONObject("input")
										.get(prop).toString());
					}

					if (isRelevant) {
						propIter = test.getJSONObject("output").keys();
						while (propIter.hasNext()) {
							String prop = propIter.next();
							result.put(prop,
									test.getJSONObject("output").get(prop));
						}
					}
				}
			}
		}

		// Now we need to fill the properties for which we do not have known
		// expected result
		List<LearnPadDocumentField> data = formHandler.getTaskFormFields(taskId);
		for (LearnPadDocumentField prop : data) {
			if (!result.containsKey(prop.id) && prop.required) {
				result.put(prop.id, getDefaultValue(prop));
			}
		}

		return result;
	}

	/**
	 * Helper function that returns a default value for activiti datatypes
	 *
	 * @param formType
	 *            the data type
	 * @return a default value
	 */
	private static Object getDefaultValue(LearnPadDocumentField formType) {
		String type = formType.type;

		if (type.equals("string") || type.equals("textarea")) {
			return "# i am a robot";
		} else if (type.equals("long")) {
			return 1L;
		} else if (type.equals("enum")) {
			@SuppressWarnings("unchecked")
			Map<String, String> values = formType.enumValues;
			// get first value of the enum
			return values.keySet().iterator().next();
		} else if (type.equals("date")) {
			return "1970-01-01";
		} else if (type.equals("boolean")) {
			return true;
		} else {
			throw new RuntimeException("unhandled type " + formType);
		}
	}
}
