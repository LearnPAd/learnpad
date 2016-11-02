package eu.learnpad.simulator.mon.storage;

import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.net.ntp.TimeStamp;

import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.utils.DebugMessages;

public class ScoreTemporaryStorage {

	private static HashMap<String, SessionScoreUpdateEvent> sessionScores;
	private static String sessionID;
	private static SessionScoreUpdateEvent lastScoreUpdateEventSeen = null;
	private static SessionScoreUpdateEvent emptySessionScoreUpdateEvent;

	public ScoreTemporaryStorage(Vector<Learner> theLearnersInvolvedInSession, String sessionID) {
		
		ScoreTemporaryStorage.sessionID = sessionID;
				
		sessionScores = new HashMap<String, SessionScoreUpdateEvent>(theLearnersInvolvedInSession.size());
		emptySessionScoreUpdateEvent = new SessionScoreUpdateEvent(0l, "", null,"",null,"","",0l);
		for (int i = 0; i<theLearnersInvolvedInSession.size(); i++) {
						sessionScores.put(
									theLearnersInvolvedInSession.get(i).getId(), 
									emptySessionScoreUpdateEvent);
		}	
	}
	
	public static String getSessionID() {
		return sessionID;
	}

	public static SessionScoreUpdateEvent getLastScoreUpdateEventSeen() {
		if (ScoreTemporaryStorage.lastScoreUpdateEventSeen != null)
			return ScoreTemporaryStorage.lastScoreUpdateEventSeen;
		else 
			return ScoreTemporaryStorage.emptySessionScoreUpdateEvent;
	}

	public static void setLastScoreUpdateEventSeen(SessionScoreUpdateEvent lastScoreUpdateEventReceived) {
		DebugMessages.print(TimeStamp.getCurrentTime(), ScoreTemporaryStorage.class.getSimpleName(), "Storing LastScoreUpdateEventSeen");
		
		DebugMessages.println(TimeStamp.getCurrentTime(),  ScoreTemporaryStorage.class.getSimpleName(), "ON setLastScoreUpdateEventSeen: " + lastScoreUpdateEventReceived.modelsetid + " - " + lastScoreUpdateEventReceived.simulationsessionid + " - " + lastScoreUpdateEventReceived.simulationSessionData );

		if (ScoreTemporaryStorage.lastScoreUpdateEventSeen != null && (lastScoreUpdateEventReceived.timestamp > ScoreTemporaryStorage.lastScoreUpdateEventSeen.timestamp)) {
			ScoreTemporaryStorage.lastScoreUpdateEventSeen = lastScoreUpdateEventReceived;
		}
		DebugMessages.ok(); 
	}
	
	public static void setSessionID(String sessionID) {
		ScoreTemporaryStorage.sessionID = sessionID;
	}

	
	public static void setTemporaryLearnerSessionScore(String learnerID, SessionScoreUpdateEvent scoreUpdateEvent) {
		DebugMessages.print(TimeStamp.getCurrentTime(), ScoreTemporaryStorage.class.getSimpleName(), "Storing LearnerSessionScore");
		if (scoreUpdateEvent.timestamp > ScoreTemporaryStorage.sessionScores.get(learnerID).timestamp) {
			ScoreTemporaryStorage.sessionScores.put(learnerID, scoreUpdateEvent);
		}
		DebugMessages.ok();

	}
	
	
	
	public static Long getTemporaryLearnerSessionScore(String learnerID) {
		if (ScoreTemporaryStorage.sessionScores.get(learnerID) != null) {
		return ScoreTemporaryStorage.sessionScores.get(learnerID).sessionscore;
		}
		else
			return 0l;
	}

	public static SessionScoreUpdateEvent getLastScoreUpdateEventSeenForUser(String learnerID) {
		return ScoreTemporaryStorage.sessionScores.get(learnerID);
	}
}