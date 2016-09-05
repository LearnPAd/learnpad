package eu.learnpad.simulator.mon.rules;

import java.util.HashMap;
import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Learner;

public class ScoreTemporaryStorage {

	private static HashMap<String, Long> sessionScoreValues;
	private static String sessionID;
	
	public ScoreTemporaryStorage(Vector<Learner> theLearnersInvolvedInSession, String sessionID) {
		
		ScoreTemporaryStorage.sessionID = sessionID;
				
		sessionScoreValues = new HashMap<String, Long>(theLearnersInvolvedInSession.size());
		
		for (int i = 0; i<theLearnersInvolvedInSession.size(); i++) {
						sessionScoreValues.put(
									theLearnersInvolvedInSession.get(i).getId(), 
									0l);
		}	
	}
	
	public static String getSessionID() {
		return sessionID;
	}

	public static void setSessionID(String sessionID) {
		ScoreTemporaryStorage.sessionID = sessionID;
	}

	
	public static void setTemporaryLearnerSessionScore(String learnerID, Long scoreValue) {
						ScoreTemporaryStorage.sessionScoreValues.replace(
									learnerID, 
									ScoreTemporaryStorage.sessionScoreValues.get(learnerID), 
									scoreValue);
	}
	
	public static Long getTemporaryLearnerSessionScore(String learnerID) {
		return ScoreTemporaryStorage.sessionScoreValues.get(learnerID);
	}
}