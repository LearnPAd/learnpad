/**
 *
 */
package activitipoc.uihandler.webserver.msg.task.receive;

import activitipoc.uihandler.webserver.msg.task.ITaskMsg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author jorquera
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
