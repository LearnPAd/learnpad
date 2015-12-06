package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.utils.ElementID;

public class ConsistentUsageEndEvents extends abstractGuideline{


	public ConsistentUsageEndEvents(Definitions diagram) {
		super(diagram);
		this.id = "14";
		this.Description = "The modeler should distinguish success and failure end states in a process or a sub-process with separate end events. Flows that end in the same end state should be merged to the same end event. Therefore, separate end events that do not represent distinct end states must be merged in a single end event.";
		this.Name = "Consistent Usage of End Events";


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		boolean flag=false;
	
		
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());
				int num = 0;
				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SubProcess){
						SubProcess sub = (SubProcess) fe;
						//System.out.format("Found a SubProcess: %s\n", sub.getName());
						this.searchSubProcess(sub);
					}else
						if (fe instanceof EndEvent) {
							num++;
							
								/*elementsBPMN.add(fe);
								setElements(fe.getId(),IDProcess);
								ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");*/
								
								elementsBPMNtemp.add(fe);
								String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
								Elementstemp.add(new ElementID(fe.getId(),IDProcess,name));
								temp.append("Name=" +name + " ID=" + fe.getId()
										+ "; ");
							
						} 
				}
				if(num>1){
					flag=true;
				}else{
					elementsBPMNtemp = new ArrayList<FlowElement>();
					Elementstemp = new ArrayList<ElementID>();
				}
			}
		}
		if (flag) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += "\nUse only one End Events :";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}

	protected void searchSubProcess(SubProcess sub){
		StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		int num = 0;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				this.searchSubProcess(ssub);
			}else
			if (fe instanceof EndEvent) {
				
				
				//System.out.println(fe.eClass().getName() + ": name="+ fe.getName() + " ID=" + fe.getId());
				num++;
				
					elementsBPMNtemp.add(fe);
					
					String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
					Elementstemp.add(new ElementID(fe.getId(),IDProcess,name));
					temp.append(" Name=" + name + " ID=" + fe.getId()
							+ "; ");
				
			
			}
		}
		if ( num>1) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += "\nUse only one End Event in SubProcess "+sub.getName()+" ";
			this.status = false;
		}
		
	}

}
