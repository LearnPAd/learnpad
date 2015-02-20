/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.task.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import eu.learnpad.simulator.uihandler.webserver.msg.task.ITaskMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseReceiveMessage implements ITaskMsg {

	public String type;

	/*
	 * (non-Javadoc)
	 *
	 * @see activitipoc.uihandler.webserver.msg.process.IProcessMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.valueOf(type);
	}
}
