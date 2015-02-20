/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.process.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import eu.learnpad.simulator.uihandler.webserver.msg.process.IProcessMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseReceiveMessage implements IProcessMsg {

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
