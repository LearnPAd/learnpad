/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.user.send;

import eu.learnpad.simulator.uihandler.webserver.msg.user.IUserMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class DeleteTask implements IUserMsg {

	/**
	 * @param taskid
	 */
	public DeleteTask(String taskid) {
		super();
		this.taskid = taskid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.uihandler.webserver.msg.user.UserMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.DELTASK;
	}

	public final String taskid;

}
