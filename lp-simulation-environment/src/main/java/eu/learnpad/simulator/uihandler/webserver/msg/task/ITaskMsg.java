/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.task;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public interface ITaskMsg {
	static enum TYPE {
		SUBSCRIBE, SUBMIT, TASKDESC, VALIDATED, RESUBMIT, OTHER_VALIDATED, ERROR
	}

	@JsonProperty("type")
	public TYPE getType();
}
