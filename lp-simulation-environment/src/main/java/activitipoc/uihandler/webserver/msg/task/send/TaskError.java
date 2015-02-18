/**
 *
 */
package activitipoc.uihandler.webserver.msg.task.send;

import activitipoc.uihandler.webserver.msg.task.ITaskMsg;

/**
 * @author jorquera
 *
 */
public class TaskError implements ITaskMsg {

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.uihandler.webserver.msg.task.ITaskMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.ERROR;
	}

}
