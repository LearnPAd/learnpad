package eu.learnpad.verification.plugin.bpmn.guideline.impl;

import java.util.ArrayList;
import java.util.Collection;












import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.utils.ElementID;



@XmlAccessorType(XmlAccessType.FIELD)

public  class abstractGuideline implements Runnable {

	@XmlTransient
	protected Collection<FlowElement> elementsBPMN;

	@XmlTransient
	protected boolean status;

	@XmlTransient
	protected String IDProcess;
	
	@XmlTransient
	protected Definitions diagram;

	@XmlAttribute(name = "id", required = true)
	protected String id;
	@XmlAttribute(name = "Name", required = true)
	protected  String Name;

	@XmlElement(name = "Description", required = true)
	protected String Description;

	@XmlElement(name = "Suggestion", required = true)
	protected String Suggestion;
	@XmlElement(name = "ElementID", required = false)
	@XmlElementWrapper(name = "Elements",  nillable=false)
	protected Collection<ElementID> Elements = null;

	abstractGuideline(){

	}

	public abstractGuideline(Definitions diagram){
		this.elementsBPMN = new ArrayList<FlowElement>();
		this.Suggestion="";
		this.status = false;
		this.diagram=diagram;
	}
	
	public void Start() {
		findGL(diagram);
	}

	protected  void findGL(Definitions diagram){
		
	}

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



	public void setElements(String element, String refprocessid, String name) {
		if(Elements==null){
			Elements = new ArrayList<ElementID>();
		}
		Elements.add(new ElementID(element, refprocessid,name));
	}
	
	public void setAllElements(Collection<ElementID> Elementstemp) {
		if(Elements==null){
			Elements = new ArrayList<ElementID>();
		}
		Elements.addAll(Elementstemp);
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

	protected  int searchSubProcess(SubProcess sub, StringBuilder ret, int i){
		return 0;
	}

	@Override
	public void run() {
		Start();
		
	}
	
	
	public String getState(){
		switch (Thread.currentThread().getState()) {
		case TERMINATED:
			return "OK";

		default:
			return "IN PROGRESS";
		}

	}
	
}
