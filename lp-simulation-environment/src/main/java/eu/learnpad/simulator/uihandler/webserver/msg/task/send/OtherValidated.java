/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.task.send;

import eu.learnpad.simulator.uihandler.webserver.msg.task.ITaskMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class OtherValidated implements ITaskMsg {

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.uihandler.webserver.msg.task.ITaskMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.OTHER_VALIDATED;
	}

}
