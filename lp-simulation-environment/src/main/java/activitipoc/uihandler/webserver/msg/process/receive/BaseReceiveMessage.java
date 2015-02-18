/**
 *
 */
package activitipoc.uihandler.webserver.msg.process.receive;

import activitipoc.uihandler.webserver.msg.process.IProcessMsg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author jorquera
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
