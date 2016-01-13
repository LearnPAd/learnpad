package eu.learnpad.ca.analysis.simplicity.plugin.utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"Suggestion"
})
@XmlRootElement(name = "Alternative")
public class AlternativeTerm {
	
	@XmlElement(required = true)
	protected String Suggestion;
	@XmlAttribute(name = "word")
	protected String word;
	
	public AlternativeTerm(){
		
	}
	
	public AlternativeTerm(String token) {
		this.word=token;
	}
	public String getSuggestion() {
		return Suggestion;
	}
/*	public void setSuggestion(String suggestion) {
		Suggestion = suggestion;
	}*/
	public String getWord() {
		return word;
	}
	/*public void setWord(String word) {
		this.word = word;
	}*/
	@Override
	public String toString() {
		return "AlternativeTerm [word=" + word + ",Suggestion =" + Suggestion
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AlternativeTerm){
			AlternativeTerm objj = (AlternativeTerm) obj;
			return	this.getWord().toLowerCase().equals(objj.getWord().toLowerCase());
		}
		return super.equals(obj);
	}
	
}
