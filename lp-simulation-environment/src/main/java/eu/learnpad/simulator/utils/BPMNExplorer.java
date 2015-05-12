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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.activiti.bpmn.model.BpmnModel;
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

	private final Map<String, String> taskIDToSubProcess = new HashMap<String, String>();

	public BPMNExplorer(BpmnModel process) throws FileNotFoundException,
	SAXException, IOException, ParserConfigurationException {

		// parse the BPMN file and extract useful infos
		for (FlowElement element : process.getMainProcess().getFlowElements()) {
			// filter to keep only user tasks
			if (element instanceof UserTask) {
				UserTask task = (UserTask) element;

				// yes we put a null value to have the task ID as a key in the
				// map(will allow to get the set of tasks IDs)
				taskIDToSubProcess.put(task.getId(), null);
			}

			if (element instanceof SubProcess) {
				getUserTasksOfSubprocess((SubProcess) element);
			}
		}
	}

	/**
	 * Helper method to recurse over tasks of subprocesses
	 *
	 * @param p
	 * @return a Set with all the user task IDs in the subprocess
	 */
	private Set<String> getUserTasksOfSubprocess(SubProcess p) {
		final Set<String> res = new HashSet<String>();

		for (FlowElement element : p.getFlowElements()) {
			// filter to keep only user tasks
			if (element instanceof UserTask) {
				UserTask task = (UserTask) element;
				res.add(task.getId());

				// register the subprocess to which the task belongs
				taskIDToSubProcess.put(task.getId(), p.getId());
			}

			if (element instanceof SubProcess) {
				res.addAll(getUserTasksOfSubprocess((SubProcess) element));
			}
		}

		return res;
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

}
