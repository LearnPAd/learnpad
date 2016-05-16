package eu.learnpad.cw.rest.data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Utility method to store a score record
 *
 * @author Tom Jorquera - Linagora
 *
 */
@XmlRootElement
public class ScoreRecord {
	private String userArtifactId;
	private String processArtifactId;
	private String sessionId;
	private long score;

	public ScoreRecord() {
		super();
	}

	public ScoreRecord(String userArtifactId, String processArtifactId, String sessionId, long score) {
		this();
		this.userArtifactId = userArtifactId;
		this.processArtifactId = processArtifactId;
		this.sessionId = sessionId;
		this.score = score;
	}

	public String getUserArtifactId() {
		return this.userArtifactId;
	}

	public void setUserArtifactId(String userid) {
		this.userArtifactId = userid;
	}

	public String getProcessArtifactId() {
		return this.processArtifactId;
	}

	public void setProcessArtifactId(String modelid) {
		this.processArtifactId = modelid;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionid) {
		this.sessionId = sessionid;
	}

	public long getScore() {
		return this.score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ScoreRecord : { userArtifactId " + userArtifactId + ", processArtifactId " + processArtifactId
				+ ", sessionId " + sessionId + ", score " + score + " }";
	}
}