/**
 *
 */
package activitipoc.uihandler.webserver.msg.task;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jorquera
 *
 */
public interface ITaskMsg {
	static enum TYPE {
		SUBSCRIBE, SUBMIT, TASKDESC, VALIDATED, RESUBMIT, OTHER_VALIDATED, ERROR
	}

	@JsonProperty("type")
	public TYPE getType();
}
