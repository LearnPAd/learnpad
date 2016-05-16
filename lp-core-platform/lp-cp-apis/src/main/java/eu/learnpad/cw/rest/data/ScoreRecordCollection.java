package eu.learnpad.cw.rest.data;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Utility method to store a set of {@link eu.learnpad.cw.rest.data.ScoreRecord
 * ScoreRecords}
 *
 * @author Tom Jorquera - Linagora
 *
 */
@XmlRootElement
public class ScoreRecordCollection {
	@XmlElement(name = "record")
	private Collection<ScoreRecord> scoreRecords;

	public ScoreRecordCollection() {
		super();
	}

	public ScoreRecordCollection(Collection<ScoreRecord> content) {
		this();
		this.scoreRecords = content;
	}

	public Collection<ScoreRecord> getScoreRecords() {
		return scoreRecords;
	}

	@Override
	public String toString() {
		return scoreRecords.toString();
	}
}
