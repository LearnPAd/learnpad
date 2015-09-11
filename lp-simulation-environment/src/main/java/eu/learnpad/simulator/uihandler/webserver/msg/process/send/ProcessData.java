/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.process.send;

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

import eu.learnpad.simulator.IProcessManager;
import eu.learnpad.simulator.uihandler.IFormHandler;
import eu.learnpad.simulator.uihandler.webserver.msg.process.IProcessMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class ProcessData implements IProcessMsg {

	public final List<ProcessDescr> processes;

	/**
	 * @param processes
	 */
	public ProcessData(List<ProcessDescr> processes) {
		super();
		this.processes = processes;
	}

	public ProcessData(IProcessManager processManager,
			IFormHandler formHandler, Collection<String> users) {
		super();
		this.processes = new ArrayList<ProcessData.ProcessDescr>();

		for (String processDefId : processManager
				.getAvailableProcessDefintion()) {
			processes
			.add(new ProcessDescr(
					processDefId,
					processManager
					.getProcessDefinitionName(processDefId),
					processManager
					.getProcessDefinitionDescription(processDefId),
					formHandler
					.createStartingFormString(
							processDefId,
							processManager
							.getProcessDefinitionSingleRoles(processDefId),
											processManager
							.getProcessDefinitionGroupRoles(processDefId),
									users)));
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.uihandler.webserver.msg.process.IProcessMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.DATA;
	}

	class ProcessDescr {

		public final String id;
		public final String name;
		public final String description;
		public final Object form;

		public ProcessDescr(String id, String name, String description,
				Object form) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.form = form;
		}

	}
}
