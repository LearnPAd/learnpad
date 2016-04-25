package eu.learnpad.cw.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import eu.learnpad.cw.rest.data.ScoreRecord;
import eu.learnpad.cw.rest.data.ScoreRecordCollection;
import eu.learnpad.exception.LpRestException;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public interface ScoreUpdateReceiver {

	@Path("/scores")
	@POST
	public void receiveScoreUpdate(ScoreRecord record) throws LpRestException;

	@Path("/scores")
	@GET
	public ScoreRecordCollection getScores(@QueryParam("user") String user,
			@QueryParam("process") String process) throws LpRestException;
}
