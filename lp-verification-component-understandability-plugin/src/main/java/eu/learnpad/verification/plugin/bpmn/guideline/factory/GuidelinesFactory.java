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

import org.eclipse.bpmn2.RootElement;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.ExplicitStartEndEvents;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.SplitAndJoinFlows;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.explicitGateways;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"processName",
		"processID",
		"status",
		"description",
		"guidelines"
})
@XmlRootElement(name = "Result")
public class GuidelinesFactory {
	
	@XmlElement(name = "ProcessName", required = true)
	private String processName;
	@XmlElement(name = "ProcessID", required = true)
	private String processID;
	@XmlElement(name = "Status", required = true)
	private String status;
	@XmlElement(name = "Description", required = true)
	private String description;
	@XmlTransient
	private List<RootElement> diagram;
	@XmlElementWrapper(name = "Guidelines", required = true)
	@XmlElement(name = "Guideline", required = true)
	private Collection<abstractGuideline> guidelines;


	GuidelinesFactory(){

	}

	public GuidelinesFactory(List<RootElement> graph){
		diagram = graph;
		guidelines = new ArrayList<abstractGuideline>();
		ExplicitStartEndEvents  explicitSEevent=new ExplicitStartEndEvents(diagram);
		guidelines.add(explicitSEevent);
		guidelines.add(new explicitGateways(diagram));
		guidelines.add(new SplitAndJoinFlows(diagram));
		setStatus();
		if(explicitSEevent.getProcessName()!=null){
			setProcessName(explicitSEevent.getProcessName());}
		else{
			setProcessName("empty");
		}
		setProcessID(explicitSEevent.getProcessID());	
	}


	public Collection<abstractGuideline> getGuidelines(){
		return guidelines;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String nameProcess) {
		this.processName = nameProcess;
	}

	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
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
