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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import com.opencsv.CSVParser;

import eu.learnpad.simulator.uihandler.formhandler.AbstractFormHandler;
import eu.learnpad.simulator.utils.BPMNExplorer;
import eu.learnpad.simulator.utils.BPMNExplorerRepository;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class DataObjectToJsonFormFormHandler extends AbstractFormHandler {

	static final int NB_FIELDS = 5;

	static final int FIELD_ID = 0;
	static final int FIELD_NAME = 1;
	static final int FIELD_TYPE = 2;
	static final int FIELD_REQUIRED = 3;
	static final int FIELD_CATEGORY = 4;

	private final BPMNExplorerRepository repo;
	private final TaskService taskService;

	private final CSVParser csvParser = new CSVParser(',');

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
					String[] elements;
					try {
						elements = csvParser.parseLine(line);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}

					if (elements.length == 1) {
						res.add(new FormField(line, line, "string", true, "",
								null));
					} else {

						boolean required = elements[FIELD_REQUIRED]
								.equals("true");

						Map<String, String> enumValues = null;

						if (elements[FIELD_TYPE].equals("enum")) {
							enumValues = new LinkedHashMap<String, String>();
							for (int i = NB_FIELDS; i < elements.length; i += 2) {
								enumValues.put(elements[i], elements[i + 1]);
							}
						}

						res.add(new FormField(elements[FIELD_ID],
								elements[FIELD_NAME], elements[FIELD_TYPE],
								required, elements[FIELD_CATEGORY], enumValues));

					}
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
