/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.task.send;

import eu.learnpad.simulator.uihandler.webserver.msg.task.ITaskMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class Resubmit extends TaskDesc implements ITaskMsg {

	/**
	 * @param description
	 * @param processd
	 * @param form
	 */
	public Resubmit(String description, String process, String form) {
		super(description, process, form);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.uihandler.webserver.msg.task.ITaskMsg#getType()
	 */
	@Override
	public TYPE getType() {
		return TYPE.RESUBMIT;
	}

}
