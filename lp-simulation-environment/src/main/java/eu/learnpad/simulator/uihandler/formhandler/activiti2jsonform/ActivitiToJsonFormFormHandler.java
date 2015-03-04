/**
 *
 */
package eu.learnpad.simulator.uihandler.formhandler.activiti2jsonform;

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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.activiti.engine.FormService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;

import eu.learnpad.simulator.uihandler.IFormHandler;

/**
 * Implements transformation between activiti tasks and <a
 * href="https://github.com/joshfire/jsonform">JsonForm</a> forms
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiToJsonFormFormHandler implements IFormHandler {

	// these properties are used to prefix special form properties used to map
	// roles to users. Hopefully no process will use properties with these
	// keys....
	private static final String SINGLE_USER_KEY_PREFIX = "#_learnpad_route_singlerole_#";
	private static final String GROUP_USER_KEY_PREFIX = "#_learnpad_route_grouprole_#";

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
	public String createStartingFormString(String processId,
			Collection<String> singleRoles, Collection<String> groupRoles,
			Collection<String> users) {
		StartFormData data = activitiFormService.getStartFormData(processId);

		String res = formDataToJSON(data);

		// insert roles to users mapping
		JSONObject tmp = new JSONObject(res);

		JSONArray userArray = new JSONArray(users);

		for (String role : singleRoles) {
			JSONObject o = new JSONObject();
			o.put("title", "Role: " + role);
			o.put("type", "string");
			o.put("enum", userArray);
			o.put("required", true);
			tmp.getJSONObject("schema").put(SINGLE_USER_KEY_PREFIX + role, o);
		}

		for (String role : groupRoles) {
			JSONObject items = new JSONObject();
			items.put("type", "string");
			items.put("enum", userArray);

			JSONObject o = new JSONObject();
			o.put("title", "Group Role: " + role);
			o.put("type", "array");
			o.put("items", items);
			o.put("minItems", 1);
			o.put("required", true);
			tmp.getJSONObject("schema").put(GROUP_USER_KEY_PREFIX + role, o);
		}

		JSONObject rolesForm = new JSONObject();
		rolesForm.put("type", "fieldset");
		rolesForm.put("title", "Roles Assignment");

		JSONArray items = new JSONArray();
		for (String role : singleRoles) {
			JSONObject o = new JSONObject();
			o.put("key", SINGLE_USER_KEY_PREFIX + role);
			items.put(o);
		}
		for (String role : groupRoles) {
			JSONObject o = new JSONObject();
			o.put("key", GROUP_USER_KEY_PREFIX + role);
			o.put("type", "checkboxes");
			items.put(o);
		}

		rolesForm.put("items", items);

		JSONArray form = tmp.getJSONArray("form");
		// remove this object and insert it back at the end
		Object submitButton = form.remove(form.length() - 1);
		form.put(rolesForm);
		form.put(submitButton);

		return tmp.toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.IFormHandler#parseResult(java.lang.String)
	 */
	public FormResult parseResult(String data) {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final Map<String, Collection<String>> routes = new HashMap<String, Collection<String>>();

		JSONObject jObject = new JSONObject(data);
		Iterator<?> keys = jObject.keys();

		while (keys.hasNext()) {

			// TODO dangerous cast
			String key = (String) keys.next();

			// separate properties from roles
			if (key.startsWith(SINGLE_USER_KEY_PREFIX)) {
				routes.put(key.replaceFirst(SINGLE_USER_KEY_PREFIX, ""),
						Arrays.asList(jObject.getString(key)));
			} else if (key.startsWith(GROUP_USER_KEY_PREFIX)) {
				Set<String> users = new HashSet<String>();
				JSONArray usersJ = jObject.getJSONArray(key);
				for (int i = 0; i < usersJ.length(); i++) {
					users.add(usersJ.getString(i));
				}
				routes.put(key.replaceFirst(GROUP_USER_KEY_PREFIX, ""), users);
			} else {
				Object value = jObject.get(key);
				parameters.put(key, value);
			}
		}

		return new FormResult() {

			public Map<String, Collection<String>> getRolesToUsersMapping() {
				return routes;
			}

			public Map<String, Object> getProperties() {
				return parameters;
			}
		};
	}

	private String formDataToJSON(FormData data) {
		String schema = "\"schema\": { ";
		String form = "\"form\": [ ";

		for (FormProperty prop : data.getFormProperties()) {

			schema += "\"" + prop.getId() + "\": {";
			schema += getTitle(prop) + ", ";
			schema += getType(prop) + ", ";
			schema += getRequired(prop) + "},";

			form += getForm(prop);
			form += ",";
		}
		// remove last comma from schemas (or space if object is empty)
		schema = schema.substring(0, schema.length() - 1);

		schema += " }";

		// add submit button to form
		form += "{\"type\": \"submit\",\"title\": \"Submit\"}";

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
			res += ",\"enum\":[ ";

			@SuppressWarnings("unchecked")
			Map<String, String> values = (Map<String, String>) prop.getType()
			.getInformation("values");

			for (String key : values.keySet()) {
				res += "\"" + key + "\",";
			}
			// remove last comma (or space if list is empty)
			res = res.substring(0, res.length() - 1);
			res += " ]";

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
			res += ",\"titleMap\":{ ";

			@SuppressWarnings("unchecked")
			Map<String, String> values = (Map<String, String>) prop.getType()
			.getInformation("values");
			for (Entry<String, String> entry : values.entrySet()) {
				res += "\"" + entry.getKey() + "\" : " + "\""
						+ entry.getValue() + "\",";
			}
			// remove last comma (or space if object is empty)
			res = res.substring(0, res.length() - 1);
			res += " }}";

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
