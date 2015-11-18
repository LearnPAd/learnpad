package eu.learnpad.verification.plugin.bpmn.guideline.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;









import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.ExplicitStartEndEvents;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.SplitAndJoinFlows;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.explicitGateways;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"definitionID",
		"status",
		"description",
		"guidelines"
})
@XmlRootElement(name = "UnderstandabilityResult")
public class GuidelinesFactory {

	@XmlElement(name = "DefinitionID", required = true)
	private String definitionID;
	@XmlElement(name = "Status", required = true)
	private String status;
	@XmlElement(name = "Description", required = true)
	private String description;
	@XmlTransient
	private Definitions diagram;
	@XmlElementWrapper(name = "Guidelines", required = true)
	@XmlElement(name = "Guideline", required = true)
	private Collection<abstractGuideline> guidelines;


	GuidelinesFactory(){

	}

	public GuidelinesFactory(Definitions graph){
		diagram = graph;
		guidelines = new ArrayList<abstractGuideline>();
		setDefinitionID(diagram.getId());
		guidelines.add(new ExplicitStartEndEvents(diagram));
		guidelines.add(new explicitGateways(diagram));
		guidelines.add(new SplitAndJoinFlows(diagram));
		setStatus();
		/*
		setProcessID(explicitSEevent.getProcessID());*/	
	}


	public Collection<abstractGuideline> getGuidelines(){
		return guidelines;
	}

	public String getDefinitionID() {
		return definitionID;
	}

	public void setDefinitionID(String DefinitionID) {
		this.definitionID = DefinitionID;
	}

	public String getStatus(){
		return status;
	}

	private void setStatus() {
		status = "OK";
		description = "Well done, no errors found!";
		for (abstractGuideline abstractGuideline : guidelines) {
			if(!abstractGuideline.getStatus()){
				status = "not OK";
				description = "Please follow these guidelines:";
				break;
			}
		}
	}

	@Override
	public String toString() {
		String ret = "GuideLineFactory: \n\r";
		int index=0;
		for(abstractGuideline bp: guidelines){
			ret+=++index+") ";
			ret+=bp.toString();
			ret+="\n\r-------------------------------------\n\r"; 
		}
		return ret;
	}

}
