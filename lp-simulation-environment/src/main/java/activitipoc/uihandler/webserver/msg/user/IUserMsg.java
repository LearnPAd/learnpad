/**
 *
 */
package activitipoc.uihandler.webserver.msg.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jorquera
 *
 */
public interface IUserMsg {
	static enum TYPE {
		ADDTASK, DELTASK, FINISHED
	}

	@JsonProperty("type")
	public TYPE getType();

}
