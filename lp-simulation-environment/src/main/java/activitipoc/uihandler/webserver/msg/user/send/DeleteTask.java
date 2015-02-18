/**
 *
 */
package activitipoc.uihandler.webserver.msg.user.send;

import activitipoc.uihandler.webserver.msg.user.IUserMsg;

/**
 * @author jorquera
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
