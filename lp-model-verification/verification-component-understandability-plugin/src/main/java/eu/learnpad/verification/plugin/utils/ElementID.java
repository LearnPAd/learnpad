package eu.learnpad.verification.plugin.utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ElementID {

	@XmlAttribute(name = "refProcessID")
    private String refprocessid;

    @XmlValue
    private String value;
    
    ElementID(){
    
    }
    

	public ElementID( String value, String refprocessid) {
		
		
		this.value = value;
		this.refprocessid  = refprocessid;
	}


    
}
