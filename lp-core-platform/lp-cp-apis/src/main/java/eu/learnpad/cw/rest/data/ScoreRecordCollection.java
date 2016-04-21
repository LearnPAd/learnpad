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
	public Collection<ScoreRecord> content;

	public ScoreRecordCollection() {
		super();
	}

	public ScoreRecordCollection(Collection<ScoreRecord> content) {
		this();
		this.content = content;
	}

	@Override
	public String toString() {
		return content.toString();
	}
}
