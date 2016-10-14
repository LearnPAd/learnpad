package eu.learnpad.me.rest.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for StatusType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="ImportStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FAILED"/>
 *     &lt;enumeration value="COMPLETED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ImportStatusType")
@XmlEnum
public enum ImportStatusType {

	@XmlEnumValue("FAILED") FAILED("FAILED"), COMPLETED("COMPLETED");
	private final String value;

	ImportStatusType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static ImportStatusType fromValue(String v) {
		for (ImportStatusType c : ImportStatusType.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}