package eu.learnpad.cw.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import eu.learnpad.cw.rest.data.ScoreRecord;
import eu.learnpad.exception.LpRestException;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface ScoreUpdateReceiver {

	@Path("/scores")
	@POST
	public void notifyScoreUpdate(ScoreRecord record) throws LpRestException;
}
