package eu.learnpad.simulator.uihandler.formhandler;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;

import eu.learnpad.simulator.datastructures.document.LearnPadDocumentField;
import eu.learnpad.simulator.uihandler.IFormHandler;
import eu.learnpad.simulator.utils.SimulatorProperties;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public abstract class AbstractFormHandler implements IFormHandler {

	// these properties are used to prefix special form properties used to map
	// roles to users. Hopefully no process will use properties with these
	// keys....
	protected static final String SINGLE_USER_KEY_PREFIX = "#_learnpad_route_singlerole_#";
	protected static final String GROUP_USER_KEY_PREFIX = "#_learnpad_route_grouprole_#";

	protected static final String SINGLE_USER_IS_HUMAN_KEY_PREFIX = "#_learnpad_route_singlerole_is_human#";
	protected static final String GROUP_USER_IS_HUMAN_KEY_PREFIX = "#_learnpad_route_grouprole_is_human#";

	public static final String DEFAULT_ROBOT_ROLE = "#_robot_robby";

	public abstract List<LearnPadDocumentField> getStartFormData(
			String processId);

	public abstract List<LearnPadDocumentField> getTaskFormFields(String taskId);

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IFormHandler#createFormString(java.lang.String)
	 */
	public String createFormString(String taskId) {
		return generateForm(getTaskFormFields(taskId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IFormHandler#createStartingFormString(java.lang.String)
	 */
	public String createStartingFormString(String processId,
			Collection<String> singleRoles, Collection<String> groupRoles,
			Collection<String> users) {
		return this.concatUsersAndRoles(
				generateForm(getStartFormData(processId)), singleRoles,
				groupRoles, users);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.IFormHandler#parseResult(java.lang.String)
	 */
	public FormResult parseResult(String data) {
		final Map<String, Object> parameters = new LinkedHashMap<String, Object>();
		final Map<String, Collection<String>> routes = new LinkedHashMap<String, Collection<String>>();

		final Set<String> robots = new HashSet<>();

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
			} else if (key.startsWith(SINGLE_USER_IS_HUMAN_KEY_PREFIX)) {
				if (!jObject.getBoolean(key)) {
					robots.add(key.replaceFirst(SINGLE_USER_IS_HUMAN_KEY_PREFIX, ""));
				}
			} else if (key.startsWith(GROUP_USER_IS_HUMAN_KEY_PREFIX)) {
				if (!jObject.getBoolean(key)) {
					robots.add(key.replaceFirst(GROUP_USER_IS_HUMAN_KEY_PREFIX, ""));
				}
			} else {
				Object value = jObject.get(key);
				parameters.put(key, value);
			}

			// final pass on routes to set robots
			for (String role : robots) {
				routes.put(role, Arrays.asList(DEFAULT_ROBOT_ROLE));
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

	/**
	 * Add users and roles to the string representation of a JSON msg.
	 *
	 * @param base
	 * @param singleRoles
	 * @param groupRoles
	 * @param users
	 * @return the base String with the necessary fields to add users and roles
	 */
	private String concatUsersAndRoles(String base,
			Collection<String> singleRoles, Collection<String> groupRoles,
			Collection<String> users) {

		// do we add robots or not?
		boolean withRobot = !SimulatorProperties.props.getProperty(SimulatorProperties.ROBOT_TYPE)
				.equals(SimulatorProperties.ROBOT_TYPE_VALUE.none.toString());

		// insert roles to users mapping
		JSONObject res = new JSONObject(base);

		JSONArray userArray = new JSONArray(users);

		for (String role : singleRoles) {

			JSONObject isHuman = new JSONObject();
			isHuman.put("type", "boolean");
			isHuman.put("required", true);

			if (!withRobot) {
				isHuman.put("default", true);
			}

			res.getJSONObject("schema").put(SINGLE_USER_IS_HUMAN_KEY_PREFIX + role, isHuman);

			JSONObject o = new JSONObject();
			o.put("type", "string");
			o.put("enum", userArray);
			res.getJSONObject("schema").put(SINGLE_USER_KEY_PREFIX + role, o);
		}

		for (String role : groupRoles) {

			JSONObject isHuman = new JSONObject();
			isHuman.put("type", "boolean");
			isHuman.put("required", true);

			if (!withRobot) {
				isHuman.put("default", true);
			}

			res.getJSONObject("schema").put(GROUP_USER_IS_HUMAN_KEY_PREFIX + role,
					isHuman);

			JSONObject items = new JSONObject();
			items.put("type", "string");
			items.put("enum", userArray);

			JSONObject o = new JSONObject();
			o.put("title", "Group Role: " + role);
			o.put("type", "array");
			o.put("items", items);
			o.put("minItems", 1);
			o.put("required", true);
			res.getJSONObject("schema").put(GROUP_USER_KEY_PREFIX + role, o);
		}

		JSONObject rolesForm = new JSONObject();
		rolesForm.put("type", "fieldset");
		rolesForm.put("title", "Roles Assignment");

		JSONArray items = new JSONArray();
		for (String role : singleRoles) {

			JSONObject o = new JSONObject();

			if (withRobot) {
				JSONObject isHuman = new JSONObject();
				isHuman.put("title", "Role: " + role);
				isHuman.put("key", SINGLE_USER_IS_HUMAN_KEY_PREFIX + role);
				isHuman.put("type", "radios");
				isHuman.put("inline", true);

				JSONObject isHumanOptions = new JSONObject();
				isHumanOptions.put("true", "human");
				isHumanOptions.put("false", "robot");
				isHuman.put("options", isHumanOptions);

				JSONObject toggleNextMap = new JSONObject();
				toggleNextMap.put("true", true);
				isHuman.put("toggleNextMap", toggleNextMap);
				items.put(isHuman);
			} else {
				JSONObject isHuman = new JSONObject();
				isHuman.put("key", SINGLE_USER_IS_HUMAN_KEY_PREFIX + role);
				isHuman.put("type", "hidden");
				items.put(isHuman);

				o.put("title", "Role: " + role);
			}

			o.put("key", SINGLE_USER_KEY_PREFIX + role);
			items.put(o);
		}
		for (String role : groupRoles) {

			JSONObject o = new JSONObject();

			if (withRobot) {
				JSONObject isHuman = new JSONObject();
				isHuman.put("title", "Role: " + role);
				isHuman.put("key", GROUP_USER_IS_HUMAN_KEY_PREFIX + role);
				isHuman.put("type", "radios");
				isHuman.put("inline", true);

				JSONObject isHumanOptions = new JSONObject();
				isHumanOptions.put("true", "human");
				isHumanOptions.put("false", "robot");
				isHuman.put("options", isHumanOptions);

				JSONObject toggleNextMap = new JSONObject();
				toggleNextMap.put("true", true);
				isHuman.put("toggleNextMap", toggleNextMap);
				items.put(isHuman);
			} else {
				JSONObject isHuman = new JSONObject();
				isHuman.put("key", GROUP_USER_IS_HUMAN_KEY_PREFIX + role);
				isHuman.put("type", "hidden");
				items.put(isHuman);

				o.put("title", "Role: " + role);
			}

			o.put("key", GROUP_USER_KEY_PREFIX + role);
			o.put("type", "checkboxes");
			items.put(o);
		}

		rolesForm.put("items", items);

		JSONArray form = res.getJSONArray("form");
		// remove this object and insert it back at the end
		Object submitButton = form.remove(form.length() - 1);
		form.put(rolesForm);
		form.put(submitButton);

		return res.toString();
	}

	private String generateForm(List<LearnPadDocumentField> fields) {

		String schema = "\"schema\": { ";
		String form = "\"form\": [ ";

		Map<String, List<LearnPadDocumentField>> fieldSets = new LinkedHashMap<String, List<LearnPadDocumentField>>();

		for (LearnPadDocumentField field : fields) {

			schema += "\"" + field.id + "\": {";
			schema += getTitle(field) + ", ";
			schema += getType(field) + ", ";
			schema += getRequired(field) + "},";

			if (field.category.equals("")) {
				form += getForm(field);
				form += ",";
			} else {
				if (!fieldSets.containsKey(field.category)) {
					fieldSets.put(field.category,
							new ArrayList<LearnPadDocumentField>());
				}
				fieldSets.get(field.category).add(field);
			}

		}
		// remove last comma from schemas (or space if object is empty)
		schema = schema.substring(0, schema.length() - 1);

		schema += " }";

		// add field sets to form
		for (Entry<String, List<LearnPadDocumentField>> fieldSet : fieldSets
				.entrySet()) {
			form += "{\"type\": \"fieldset\"," + "\"title\": \""
					+ fieldSet.getKey() + "\"," + "\"expandable\": true,"
					+ "\"items\": [";
			for (LearnPadDocumentField field : fieldSet.getValue()) {
				form += getForm(field) + ",";
			}
			// remove last comma
			form = form.substring(0, form.length() - 1);
			form += "]},";
		}

		// add submit button to form
		form += "{\"type\": \"submit\",\"title\": \"Submit\"}";

		form += " ]";

		String res = "{ " + schema + ", " + form + "}";
		return res;
	}

	private static String getType(LearnPadDocumentField field) {
		String res = "\"type\": ";

		String type = field.type;

		if (type.equals("string")) {
			res += "\"string\"";
		} else if (type.equals("long")) {
			res += "\"number\"";
		} else if (type.equals("enum")) {

			res += "\"string\"";
			res += ",\"enum\":[ ";

			Map<String, String> values = field.enumValues;

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
		} else if (type.equals("textarea")) {
			res += "\"string\"";
		}

		return res;
	}

	private static String getTitle(LearnPadDocumentField field) {
		if (field.type.equals("boolean")) {
			return "\"title\": \"\"";
		} else {
			return "\"title\": \"" + field.name + "\"";
		}
	}

	private static String getRequired(LearnPadDocumentField field) {
		return "\"required\":" + field.required;
	}

	private static String getForm(LearnPadDocumentField field) {
		String res = "";

		String type = field.type;

		if (type.equals("string")) {
			res += "\"" + field.id + "\"";
		} else if (type.equals("long")) {
			res += "\"" + field.id + "\"";
		} else if (type.equals("enum")) {
			// in the case of enum, we need to set a specific schema in order
			// for the correct name to be displayed on the form but still get in
			// return valid data
			// see https://github.com/joshfire/jsonform/wiki#fields-selection
			res += "{ \"key\": \"" + field.id + "\"";
			res += ",\"titleMap\":{ ";

			for (Entry<String, String> entry : field.enumValues.entrySet()) {
				res += "\"" + entry.getKey() + "\" : " + "\""
						+ entry.getValue() + "\",";
			}
			// remove last comma (or space if object is empty)
			res = res.substring(0, res.length() - 1);
			res += " }}";

		} else if (type.equals("date")) {
			res += "{ \"key\": \"" + field.id + "\"";
			res += ",\"type\": \"date\"";
			res += "}";

		} else if (type.equals("boolean")) {
			res += "{ \"key\": \"" + field.id + "\"";
			res += ",\"inlinetitle\": \"" + field.name + "\"";
			res += "}";
		} else if (type.equals("textarea")) {
			res += "{ \"key\": \"" + field.id + "\"";
			res += ",\"type\": \"textarea\"";
			res += "}";
		}

		return res;
	}
}
