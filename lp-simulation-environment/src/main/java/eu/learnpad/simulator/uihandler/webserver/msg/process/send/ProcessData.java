/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.process.send;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.learnpad.simulator.processmanager.IProcessManager;
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
