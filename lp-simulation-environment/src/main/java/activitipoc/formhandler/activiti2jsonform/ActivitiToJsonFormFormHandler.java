/**
 *
 */
package activitipoc.formhandler.activiti2jsonform;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.impl.util.json.JSONObject;

import activitipoc.IFormHandler;

/**
 * Implements transformation between activiti tasks and <a
 * href="https://github.com/joshfire/jsonform">JsonForm</a> forms
 *
 * @author jorquera
 *
 */
public class ActivitiToJsonFormFormHandler implements IFormHandler {

	private final FormService activitiFormService;

	/**
	 * @param activitiFormService
	 */
	public ActivitiToJsonFormFormHandler(FormService activitiFormService) {
		super();
		this.activitiFormService = activitiFormService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IFormHandler#createFormString(java.lang.String)
	 */
	public String createFormString(String taskId) {
		FormData data = activitiFormService.getTaskFormData(taskId);
		return formDataToJSON(data);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IFormHandler#createStartingFormString(java.lang.String)
	 */
	public String createStartingFormString(String processId) {
		StartFormData data = activitiFormService.getStartFormData(processId);
		return formDataToJSON(data);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IFormHandler#parseResult(java.lang.String)
	 */
	public Map<String, Object> parseResult(String data) {
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

	private String formDataToJSON(FormData data) {
		String schema = "\"schema\": {";
		String form = "\"form\": [ ";

		for (FormProperty prop : data.getFormProperties()) {

			schema += "\"" + prop.getId() + "\": {";
			schema += getTitle(prop) + ", ";
			schema += getType(prop) + ", ";
			schema += getRequired(prop) + "},";

			form += getForm(prop);
			form += ",";
		}
		// remove last comma from schemas
		schema = schema.substring(0, schema.length() - 1);

		schema += " }";

		// add submit button to form
		form += "{\"type\": \"submit\",\"title\": \"Submit Task\"}";

		form += " ]";

		String res = "{ " + schema + ", " + form + "}";
		return res;
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

	private static String getForm(FormProperty prop) {
		String res = "";

		String type = prop.getType().getName();

		if (type.equals("string")) {
			res += "\"" + prop.getId() + "\"";
		} else if (type.equals("long")) {
			res += "\"" + prop.getId() + "\"";
		} else if (type.equals("enum")) {
			// in the case of enum, we need to set a specific schema in order
			// for the correct name to be displayed on the form but still get in
			// return valid data
			// see https://github.com/joshfire/jsonform/wiki#fields-selection
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
			res += ",\"type\": \"date\"";
			res += "}";

		} else if (type.equals("boolean")) {
			res += "\"" + prop.getId() + "\"";
		}

		return res;
	}

}
