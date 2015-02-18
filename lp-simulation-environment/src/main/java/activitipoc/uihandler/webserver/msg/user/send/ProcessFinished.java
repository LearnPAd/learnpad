/**
 *
 */
package activitipoc.uihandler.webserver.msg.user.send;

import activitipoc.uihandler.webserver.msg.user.IUserMsg;

/**
 * @author jorquera
 *
 */
public class ProcessFinished implements IUserMsg {

	/**
	 * @param processid
	 */
	public ProcessFinished(String processid) {
		super();
		this.processid = processid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see activitipoc.uihandler.webserver.msg.user.UserMsg#getType()
	 */
	public TYPE getType() {
		return TYPE.FINISHED;
	}

	public final String processid;

}
