package eu.learnpad.simulator.uihandler.formhandler.multi2jsonform;

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
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;

import eu.learnpad.simulator.uihandler.formhandler.AbstractFormHandler;
import eu.learnpad.simulator.uihandler.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler;
import eu.learnpad.simulator.uihandler.formhandler.dataobject2jsonform.DataObjectToJsonFormFormHandler;
import eu.learnpad.simulator.utils.BPMNExplorerRepository;

/**
 * This form handler aggregates multiple form generation mechanisms
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class Multi2JsonFormFormHandler extends AbstractFormHandler {

	private final List<AbstractFormHandler> handlers;

	public Multi2JsonFormFormHandler(List<AbstractFormHandler> handlers) {
		super();
		this.handlers = handlers;
	}

	/**
	 * Convenience method to initialize aggregator handler with specific
	 * handlers:
	 * <ul>
	 * <li>
	 * {@link eu.learnpad.simulator.uihandler.formhandler.activiti2jsonform.ActivitiToJsonFormFormHandler
	 * ActivitiToJsonFormFormHandler}</li>
	 * <li>
	 * {@link eu.learnpad.simulator.uihandler.formhandler.dataobject2jsonform.DataObjectToJsonFormFormHandler
	 * DataObjectToJsonFormFormHandler}</li>
	 * </ul>
	 *
	 * @param repo
	 * @param taskService
	 * @param activitiFormService
	 */
	public Multi2JsonFormFormHandler(BPMNExplorerRepository repo,
			TaskService taskService, FormService activitiFormService) {
		this(Arrays.asList(new ActivitiToJsonFormFormHandler(
				activitiFormService), new DataObjectToJsonFormFormHandler(repo,
				taskService)));
	}

	@Override
	public List<FormField> getStartFormData(String processId) {
		List<FormField> res = new ArrayList<AbstractFormHandler.FormField>();
		for (AbstractFormHandler handler : handlers) {
			res.addAll(handler.getStartFormData(processId));
		}
		return res;
	}

	@Override
	public List<FormField> getTaskFormFields(String taskId) {
		List<FormField> res = new ArrayList<AbstractFormHandler.FormField>();
		for (AbstractFormHandler handler : handlers) {
			res.addAll(handler.getTaskFormFields(taskId));
		}
		return res;
	}

}
