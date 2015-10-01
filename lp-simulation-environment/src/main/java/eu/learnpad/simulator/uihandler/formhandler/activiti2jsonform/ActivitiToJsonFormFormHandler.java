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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;

import eu.learnpad.simulator.uihandler.formhandler.AbstractFormHandler;

/**
 * Implements transformation between activiti tasks and <a
 * href="https://github.com/joshfire/jsonform">JsonForm</a> forms
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ActivitiToJsonFormFormHandler extends AbstractFormHandler {

	private final FormService activitiFormService;

	/**
	 * @param activitiFormService
	 */
	public ActivitiToJsonFormFormHandler(FormService activitiFormService) {
		super();
		this.activitiFormService = activitiFormService;
	}

	/**
	 * Convert activiti form properties to general form fields
	 *
	 * @param props
	 * @return a list of form fields
	 */
	private List<FormField> propertyToField(List<FormProperty> props) {
		List<FormField> res = new ArrayList<FormField>();
		for (FormProperty prop : props) {

			String type = prop.getType().getName();

			// boolean have a specific attribute
			String title = prop.getName();

			@SuppressWarnings("unchecked")
			Map<String, String> enumValues = type.equals("enum") ? (Map<String, String>) prop
					.getType().getInformation("values") : null;

			res.add(new FormField(prop.getId(), title, type, prop.isRequired(),
					enumValues));
		}

		return res;
	}

	@Override
	public List<FormField> getStartFormData(String processId) {
		StartFormData data = activitiFormService.getStartFormData(processId);
		return propertyToField(data.getFormProperties());
	}

	@Override
	public List<FormField> getTaskFormFields(String taskId) {
		FormData data = activitiFormService.getTaskFormData(taskId);
		return propertyToField(data.getFormProperties());
	}

}
