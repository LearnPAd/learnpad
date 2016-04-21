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
	public String userArtifactId;
	public String processArtifactId;
	public String sessionId;
	public long score;

	public ScoreRecord() {
		super();
	}

	public ScoreRecord(String userArtifactId, String processArtifactId,
			String sessionId, long score) {
		this();
		this.userArtifactId = userArtifactId;
		this.processArtifactId = processArtifactId;
		this.sessionId = sessionId;
		this.score = score;
	}

	@Override
	public String toString() {
		return "ScoreRecord : { userArtifactId " + userArtifactId
				+ ", processArtifactId " + processArtifactId + ", sessionId "
				+ sessionId + ", score " + score + " }";
	}
}