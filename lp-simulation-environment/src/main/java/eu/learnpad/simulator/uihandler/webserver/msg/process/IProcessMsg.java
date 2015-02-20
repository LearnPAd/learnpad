/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.process;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public interface IProcessMsg {
	static enum TYPE {
		DATA, INSTANCIATE
	}

	@JsonProperty("type")
	public TYPE getType();
}
