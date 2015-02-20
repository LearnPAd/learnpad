/**
 *
 */
package eu.learnpad.simulator.uihandler.webserver.msg.task.receive;

import eu.learnpad.simulator.uihandler.webserver.msg.task.ITaskMsg;

/**
 * @author Tom Jorquera - Linagora
 *
 */
public class Subscribe extends BaseReceiveMessage implements ITaskMsg {

	public String user;

}
