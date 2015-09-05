package eu.learnpad.verification.plugin.bpmn.guideline.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.bpmn2.RootElement;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.explicitGateways;

@XmlRootElement(name = "Result")
public class GuidelinesFactory {

	@XmlElement(name = "Status", required = true)
	private String Status;
	@XmlElement(name = "Description", required = true)
	private String Description;
	@XmlTransient
	private List<RootElement> diagram;
	@XmlElementWrapper(name = "Guidelines", required = true)
    @XmlElement(name = "Guideline", required = true)
	private Collection<abstractGuideline> GLcollection;
	
	
	GuidelinesFactory(){
		
	}
	
	public GuidelinesFactory(List<RootElement> graph){
		diagram = graph;
		GLcollection = new ArrayList<abstractGuideline>();
		GLcollection.add(new explicitGateways(diagram));
		setStatus();

	}


	public String getStatus(){
		return Status;
	}

	private void setStatus() {
		Status = "OK";
		Description = "Well done, no errors found!";
		for (abstractGuideline abstractGuideline : GLcollection) {
			if(!abstractGuideline.getStatus()){
				Status = "not OK";
				Description = "Please follow these guidelines:";
				break;
			}
		}
	}

	@Override
	public String toString() {
		String ret = "GuideLineFactory: \n\r";
		int index=0;
		for(abstractGuideline bp: GLcollection){
			ret+=++index+") ";
			ret+=bp.toString();
			ret+="\n\r-------------------------------------\n\r"; 
		}
		return ret;
	}
	
}
