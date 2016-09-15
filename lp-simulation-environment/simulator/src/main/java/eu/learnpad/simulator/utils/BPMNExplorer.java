/**
 *
 */
package eu.learnpad.simulator.utils;

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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.DataAssociation;
import org.activiti.bpmn.model.DataObject;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.bpmn.model.UserTask;
import org.xml.sax.SAXException;

/**
 * Utility class to explore BPMN files and extract information
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class BPMNExplorer {

	private static final String DATA_OBJECTS_FOLDER = "dataobjects";

	private final Map<String, String> taskIDToSubProcess;

	private final Map<String, Set<String>> taskIDToDataInputs;
	private final Map<String, Set<String>> taskIDToDataOutputs;

	private final Set<String> standaloneDataObjects;

	private final Map<String, String> dataObjectsIdtoName;

	private final Map<String, List<String>> dataObjectContent;

	public BPMNExplorer(String processDefinitionKey, BpmnModel processDef)
			throws FileNotFoundException, SAXException, IOException,
			ParserConfigurationException {

		// get Process definition corresponding to the given ID
		// (note that some BPMN files may contain several process definitions)
		org.activiti.bpmn.model.Process process = null;
		for (org.activiti.bpmn.model.Process p : processDef.getProcesses()) {
			if (p.getId().equals(processDefinitionKey)) {
				process = p;
			}
		}
		if (process == null) {
			throw new RuntimeException("Cannot find process with key "
					+ processDefinitionKey + " in BPMN model");
		}

		final Map<String, String> mutTaskIDToSubProcess = new HashMap<>();

		final Map<String, Set<String>> mutTaskIDToDataInputs = new HashMap<>();
		final Map<String, Set<String>> mutTaskIDToDataOutputs = new HashMap<>();

		final Set<String> mutDataObjects = new HashSet<>();
		final Set<String> mutNotStandaloneDataObjects = new HashSet<>();

		final Map<String, String> mutDataObjectsIdtoName = new HashMap<>();

		final Map<String, List<String>> mutDataObjectContent = new HashMap<>();

		// parse the BPMN file and extract useful infos
		initializeWithElements(null, process.getFlowElements(),
				mutTaskIDToSubProcess, mutTaskIDToDataInputs,
				mutTaskIDToDataOutputs, mutDataObjects,
				mutNotStandaloneDataObjects, mutDataObjectsIdtoName);

		// for all data objects, read its attributes
		for (String dataObject : mutDataObjects) {

			try {

				List<String> elements = new ArrayList<>();

				try (BufferedReader br = new BufferedReader(
						new InputStreamReader(this

								.getClass()
								.getClassLoader()
								.getResourceAsStream(
										DATA_OBJECTS_FOLDER
										+ "/"
										+ mutDataObjectsIdtoName
										.get(dataObject))));

						) {
					String line;
					while ((line = br.readLine()) != null) {
						elements.add(line);
					}
				}

				mutDataObjectContent.put(dataObject, elements);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// remove all the data objects which have been produced by a task
		mutDataObjects.removeAll(mutNotStandaloneDataObjects);

		// create read-only versions of the internal maps
		taskIDToSubProcess = Collections.unmodifiableMap(mutTaskIDToSubProcess);
		taskIDToDataInputs = Collections.unmodifiableMap(mutTaskIDToDataInputs);
		taskIDToDataOutputs = Collections
				.unmodifiableMap(mutTaskIDToDataOutputs);
		standaloneDataObjects = Collections.unmodifiableSet(mutDataObjects);

		dataObjectsIdtoName = Collections
				.unmodifiableMap(mutDataObjectsIdtoName);

		dataObjectContent = Collections.unmodifiableMap(mutDataObjectContent);
	}

	/**
	 * Helper method to recursively initialize informations of process and
	 * subprocesses.
	 *
	 * @param elements
	 * @param mutTaskIDToSubProcess
	 * @param mutTaskIDToDataInputs
	 * @param mutTaskIDToDataOutputs
	 * @param mutDataObjects
	 * @param mutNotStandaloneDataObjects
	 * @param mutDataObjectsIdtoName
	 */
	private void initializeWithElements(String subprocess,
			Collection<FlowElement> elements,
			Map<String, String> mutTaskIDToSubProcess,
			Map<String, Set<String>> mutTaskIDToDataInputs,
			Map<String, Set<String>> mutTaskIDToDataOutputs,
			Set<String> mutDataObjects,
			Set<String> mutNotStandaloneDataObjects,
			Map<String, String> mutDataObjectsIdtoName) {

		for (FlowElement element : elements) {

			if (element instanceof UserTask) {
				UserTask task = (UserTask) element;

				// register the subprocess to which the task belongs
				mutTaskIDToSubProcess.put(task.getId(), subprocess);

				// store data I/O associations

				mutTaskIDToDataInputs.put(task.getId(), new HashSet<String>());
				for (DataAssociation in : task.getDataInputAssociations()) {
					mutTaskIDToDataInputs.get(task.getId()).add(
							in.getSourceRef());
				}

				mutTaskIDToDataOutputs.put(task.getId(), new HashSet<String>());
				for (DataAssociation out : task.getDataOutputAssociations()) {
					mutTaskIDToDataOutputs.get(task.getId()).add(
							out.getTargetRef());

					// also add output data objects to the set of *not*
					// standalone data objects
					mutNotStandaloneDataObjects.add(out.getTargetRef());
				}

			}

			if (element instanceof DataObject) {

				mutDataObjectsIdtoName.put(element.getId(),
						((DataObject) element).getName());
				mutDataObjects.add(element.getId());
			}

			if (element instanceof SubProcess) {
				// recurse
				initializeWithElements(element.getId(),
						((SubProcess) element).getFlowElements(),
						mutTaskIDToSubProcess, mutTaskIDToDataInputs,
						mutTaskIDToDataOutputs, mutDataObjects,
						mutNotStandaloneDataObjects, mutDataObjectsIdtoName);
			}

		}
	}

	/**
	 *
	 * @return a Set with all the user task IDs
	 */
	public Set<String> getUserTasks() {
		return taskIDToSubProcess.keySet();
	}

	/**
	 * Return the ID of the subprocess to which the task belongs (return null if
	 * the task does not exists or is not in a subprocess)
	 *
	 * @param taskId
	 * @return
	 */
	public String getSubprocess(String taskId) {
		return taskIDToSubProcess.get(taskId);
	}

	/**
	 *
	 * @param taskId
	 * @return the IDs of all the data objects in input of the task
	 */
	public Set<String> getDataInputs(String taskId) {
		return taskIDToDataInputs.get(taskId);
	}

	/**
	 *
	 * @param taskId
	 * @return the IDs of all the data objects in output of the task
	 */
	public Set<String> getDataOutputs(String taskId) {
		return taskIDToDataOutputs.get(taskId);
	}

	/**
	 *
	 * @return the IDs of all the data objects which are not produced by a task
	 */
	public Set<String> getStandaloneDataObjects() {
		return standaloneDataObjects;
	}

	/**
	 *
	 * @param id
	 * @return the name of the data object
	 */
	public String getDataObjectName(String id) {
		return dataObjectsIdtoName.get(id);
	}

	/**
	 *
	 * @param dataObject
	 * @return the content of the data object
	 */
	public List<String> getDataObjectContent(String dataObject) {
		return dataObjectContent.get(dataObject);
	}

}
