/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.task.send;

import eu.learnpad.simulator.uihandler.webserver.msg.task.ITaskMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class TaskDesc implements ITaskMsg {

	public String description;
	public String processid;
	public String form;

	/**
	 * @param description
	 * @param processd
	 * @param form
	 */
	public TaskDesc(String description, String processid, String form) {
		super();
		this.description = description;
		this.processid = processid;
		this.form = form;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.uihandler.webserver.msg.task.ITaskMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.TASKDESC;
	}

}
