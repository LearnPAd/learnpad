package eu.learnpad.sim.rest.data;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Utility method to store a set of {@link eu.learnpad.sim.rest.data.UserData
 * UserData}
 *
 * @author Tom Jorquera - Linagora
 *
 */
@XmlRootElement
public class UserDataCollection {
	@XmlElement(name = "record")
	public Collection<UserData> content;

	public UserDataCollection() {
		super();
	}

	public UserDataCollection(Collection<UserData> content) {
		this();
		this.content = content;
	}

	@Override
	public String toString() {
		return content.toString();
	}
}
