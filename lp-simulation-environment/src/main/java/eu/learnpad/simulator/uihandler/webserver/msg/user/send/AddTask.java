/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.user.send;

import eu.learnpad.simulator.uihandler.webserver.msg.user.IUserMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class AddTask implements IUserMsg {

	/**
	 * @param taskid
	 */
	public AddTask(String taskid) {
		super();
		this.taskid = taskid;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.uihandler.webserver.msg.user.UserMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.ADDTASK;
	}

	public final String taskid;

}
