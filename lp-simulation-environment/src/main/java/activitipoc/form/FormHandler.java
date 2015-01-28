/**
 *
 */
package activitipoc.form;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.runtime.ProcessInstance;

import activitipoc.Main;

/**
 *
 * This class provides transformations between tasks IO and form data
 *
 * @author jorquera
 *
 */
public class FormHandler {

	/**
	 * Generate the JSON String required to instantiate a form corresponding to
	 * the given task
	 *
	 * @param activitiFormService
	 * @param taskId
	 * @return
	 */
	public static String createFormString(FormService activitiFormService,
			String taskId) {

		String schema = "\"schema\": {";

		for (FormProperty prop : activitiFormService.getTaskFormData(taskId)
				.getFormProperties()) {

			schema += "\"" + prop.getId() + "\": {";

			schema += getTitle(prop) + ", ";
			schema += getType(prop) + ", ";
			schema += getRequired(prop) + "},";
		}
		// remove last comma
		schema = schema.substring(0, schema.length() - 1);

		schema += " }";

		String form = "\"form\": [ ";

		for (FormProperty prop : activitiFormService.getTaskFormData(taskId)
				.getFormProperties()) {
			form += getSchema(prop);
			form += ",";
		}

		// add sumit button
		form += "{\"type\": \"submit\",\"title\": \"Submit Task\"}";

		form += " ]";

		String res = "{ " + schema + ", " + form + "}";
		return res;
	}

	/**
	 * Transform a given form result data string into a map of key-values pairs
	 *
	 * @param data
	 * @return
	 */
	public static Map<String, Object> parseResult(String data) {
		Map<String, Object> result = new HashMap<String, Object>();

		JSONObject jObject = new JSONObject(data);
		Iterator<?> keys = jObject.keys();

		while (keys.hasNext()) {

			// TODO dangerous cast
			String key = (String) keys.next();
			Object value = jObject.get(key);
			result.put(key, value);
		}

		return result;
	}

	private static String getType(FormProperty prop) {
		String res = "\"type\": ";

		String type = prop.getType().getName();

		if (type.equals("string")) {
			res += "\"string\"";
		} else if (type.equals("long")) {
			res += "\"number\"";
		} else if (type.equals("enum")) {
			res += "\"string\"";
			res += ",\"enum\":[";
			String values = prop.getType().getInformation("values").toString();
			values = values.substring(1, values.length() - 1);
			values = values.replaceAll("=\\w+", "");
			values = values.replaceAll(", ", "\", \"");
			values = "\"" + values + "\"";
			res += values;
			res += "]";

		} else if (type.equals("date")) {
			res += "\"string\"";
			res += ",\"format\": \"date\"";
		} else if (type.equals("boolean")) {
			res += "\"boolean\"";
		}

		return res;
	}

	private static String getTitle(FormProperty prop) {
		return "\"title\": \"" + prop.getName() + "\"";
	}

	private static String getRequired(FormProperty prop) {
		if (prop.isRequired()) {
			return "\"required\": true";
		} else {
			return "\"required\": false";
		}
	}

	private static String getSchema(FormProperty prop) {
		String res = "";

		String type = prop.getType().getName();

		if (type.equals("string")) {
			res += "\"" + prop.getId() + "\"";
		} else if (type.equals("long")) {
			res += "\"" + prop.getId() + "\"";
		} else if (type.equals("enum")) {
			res += "{ \"key\": \"" + prop.getId() + "\"";
			res += ",\"titleMap\":{";
			String values = prop.getType().getInformation("values").toString();
			values = values.substring(1, values.length() - 1);
			values = values.replaceAll(", ", "\", \"");
			values = values.replaceAll("=", "\":\"");

			values = "\"" + values + "\"";
			res += values;
			res += "}}";

		} else if (type.equals("date")) {
			res += "{ \"key\": \"" + prop.getId() + "\"";
			res += ",\"format\": \"date\"";
			res += "}";

		} else if (type.equals("boolean")) {
			res += "\"" + prop.getId() + "\"";
		}

		return res;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// launch activiti process engine
		ProcessEngineConfiguration config = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromInputStream(new FileInputStream(
						Main.ACTIVITY_CONFIG_PATH));

		ProcessEngine processEngine = config.buildProcessEngine();

		// load new process
		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		repositoryService.createDeployment()
		.addClasspathResource(Main.DEMO_PROCESS_FILE).deploy();

		System.out.println("Number of process definitions: "
				+ repositoryService.createProcessDefinitionQuery().count());

		// Instantiate process
		RuntimeService runtimeService = processEngine.getRuntimeService();

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Tom");
		variables.put("numberOfDays", new Integer(7));
		variables.put("vacationMotivation", "I'm really tired!");

		ProcessInstance process = runtimeService.startProcessInstanceByKey(
				"vacationRequest", variables);

		// Verify that we started a new process instance
		System.out.println("Number of process instances: "
				+ runtimeService.createProcessInstanceQuery().count());

		final TaskService taskService = processEngine.getTaskService();

		System.out.println(createFormString(processEngine.getFormService(),
				taskService.createTaskQuery()
				.processInstanceId(process.getId()).singleResult()
				.getId()));

	}
}
