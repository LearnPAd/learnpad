package eu.learnpad.ca.analysis.simplicity.plugin.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"alternativeterms"
})
@XmlRootElement(name = "AlternativeTermSet")
public class AlternativeTermSet {
	
	@XmlElement(name = "Alternative")
	List<AlternativeTerm> alternativeterms;

	public List<AlternativeTerm> getAlternativeterms() {
		if (alternativeterms == null) {
			alternativeterms = new ArrayList<AlternativeTerm>();
		}
		return alternativeterms;
	}
	
	
	
}
