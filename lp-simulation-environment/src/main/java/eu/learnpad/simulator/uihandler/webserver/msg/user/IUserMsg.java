/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public interface IUserMsg {
	static enum TYPE {
		ADDTASK, DELTASK, FINISHED
	}

	@JsonProperty("type")
	public TYPE getType();

}
