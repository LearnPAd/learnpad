package eu.learnpad.verification.plugin.bpmn.guideline.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;










import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.utils.ElementID;



@XmlAccessorType(XmlAccessType.FIELD)

public abstract class abstractGuideline {

	@XmlTransient
	protected Collection<FlowElement> elementsBPMN;

	@XmlTransient
	protected boolean status;

	@XmlTransient
	protected String IDProcess;

	@XmlAttribute(name = "id", required = true)
	protected String id;
	@XmlAttribute(name = "Name", required = true)
	protected String Name;

	@XmlElement(name = "Description", required = true)
	protected String Description;

	@XmlElement(name = "Suggestion", required = true)
	protected String Suggestion;
	@XmlElement(name = "ElementID", required = false)
	@XmlElementWrapper(name = "Elements",  nillable=false)
	protected Collection<ElementID> Elements = null;

	abstractGuideline(){

	}

	abstractGuideline(Definitions diagram){
		elementsBPMN = new ArrayList<FlowElement>();

		status = false;
		findGL(diagram);
	}

	protected abstract void findGL(Definitions diagram);

	public boolean getStatus() {

		return status;
	}

	public String toString(){
		String ret = "ID: "+getid()+" \n\r"
				+"Name: "+getName()+" \n\r"
				+"Description: "+getDescription()+" \n\r"
				+"Status: "+getStatus()+" \n\r";
		if(!getStatus()){
			ret+="Suggestion: "+getSuggestion()+" \n\r";
			if(Elements!=null){
				ret+="Elements: "+Elements.toString()+" \n\r";
			}
		}


		return ret;
	}

	public String getid() {
		return id;
	}



	public void setElements(String element, String refprocessid) {
		if(Elements==null){
			Elements = new ArrayList<ElementID>();
		}
		Elements.add(new ElementID(element, refprocessid));
	}


	public String getDescription() {

		return Description;

	}

	public String getName() {

		return Name;
	}


	public String getProcessID() {
		return IDProcess;
	}

	public String getSuggestion() {
		return Suggestion;
	}

	protected abstract int searchSubProcess(SubProcess sub, StringBuilder ret, int i);

}
