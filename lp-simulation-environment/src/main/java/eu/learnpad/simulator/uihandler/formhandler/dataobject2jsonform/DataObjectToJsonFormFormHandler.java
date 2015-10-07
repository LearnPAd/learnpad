package eu.learnpad.simulator.uihandler.formhandler.dataobject2jsonform;

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
import java.util.Collection;
import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import eu.learnpad.simulator.uihandler.formhandler.AbstractFormHandler;
import eu.learnpad.simulator.utils.BPMNExplorer;
import eu.learnpad.simulator.utils.BPMNExplorerRepository;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class DataObjectToJsonFormFormHandler extends AbstractFormHandler {

	private final BPMNExplorerRepository repo;
	private final TaskService taskService;

	public DataObjectToJsonFormFormHandler(BPMNExplorerRepository repo,
			TaskService taskService) {
		super();
		this.repo = repo;
		this.taskService = taskService;
	}

	private List<FormField> dataObjectsToFields(Collection<String> dataObjects,
			BPMNExplorer explorer) {
		List<FormField> res = new ArrayList<FormField>();
		if (dataObjects != null) {
			for (String dataObject : dataObjects) {
				for (String line : explorer.getDataObjectContent(dataObject)) {
					res.add(new FormField(line, line, "string", true, null));
				}
			}
		}
		return res;
	}

	@Override
	public List<FormField> getStartFormData(String processId) {
		BPMNExplorer explorer = repo.getExplorer(processId);
		return dataObjectsToFields(explorer.getStandaloneDataObjects(),
				explorer);
	}

	@Override
	public List<FormField> getTaskFormFields(String taskId) {
		Task task = taskService.createTaskQuery().includeProcessVariables()
				.taskId(taskId).singleResult();
		BPMNExplorer explorer = repo.getExplorer(task.getProcessDefinitionId());
		return dataObjectsToFields(
				explorer.getDataOutputs(task.getTaskDefinitionKey()), explorer);
	}

}
