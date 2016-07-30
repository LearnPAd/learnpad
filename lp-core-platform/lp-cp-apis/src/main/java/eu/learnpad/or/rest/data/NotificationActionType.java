package eu.learnpad.or.rest.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * All possible actions of notification events.
 * 
 * @author sandro.emmenegger
 */
@XmlType(name = "NotificationActionType")
@XmlEnum
public enum NotificationActionType {
	VISITED, ADDED, MODIFIED, DELETED;

	public String value() {
		return name();
	}

	public static NotificationActionType fromValue(String v) {
		return valueOf(v);
	}
}