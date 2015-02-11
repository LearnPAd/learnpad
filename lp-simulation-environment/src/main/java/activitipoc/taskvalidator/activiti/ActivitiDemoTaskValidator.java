/**
 *
 */
package activitipoc.taskvalidator.activiti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;

import activitipoc.ITaskValidator;

/**
 * @author jorquera
 *
 */
public class ActivitiDemoTaskValidator implements
		ITaskValidator<Map<String, Object>, Map<String, Object>> {

	private final TaskService taskService;
	private final JSONObject jsonDB;

	/**
	 * @param taskService
	 * @throws FileNotFoundException
	 */
	public ActivitiDemoTaskValidator(TaskService taskService)
			throws FileNotFoundException {
		super();
		this.taskService = taskService;

		Scanner in = new Scanner(new FileReader(
				"src/main/resources/validation_db.json"));
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
	 * java.lang.String, java.lang.Object, java.lang.Object)
	 */
	public boolean taskResultIsValid(String processId, String taskId,
			Map<String, Object> inData, Map<String, Object> proposedResult) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		System.out.println("validating " + taskId + " ("
				+ task.getTaskDefinitionKey() + ") with " + proposedResult);

		JSONArray tests = jsonDB.optJSONArray(task.getTaskDefinitionKey());
		if (tests != null) {

			for (int i = 0; i < tests.length(); i++) {
				JSONObject test = tests.getJSONObject(i);
				boolean isRelevant = true;
				Iterator<String> propIter = test.getJSONObject("input").keys();
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
										.equals(test.getJSONObject("output")
												.get(prop).toString());
					}

					if (!isOk) {
						// we can stop right now, one of the tests is incorrect
						return false;
					}
				}
			}
		}

		// return true by default
		return true;
	}
}
