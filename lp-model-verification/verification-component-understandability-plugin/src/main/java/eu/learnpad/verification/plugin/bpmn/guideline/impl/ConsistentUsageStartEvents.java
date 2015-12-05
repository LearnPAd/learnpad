package eu.learnpad.verification.plugin.bpmn.guideline.impl;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.utils.ElementID;

public class ConsistentUsageStartEvents extends abstractGuideline{


	public ConsistentUsageStartEvents(Definitions diagram) {
		super(diagram);
		this.id = "13";
		this.Description = "The modeler should include, in the model, only one start event. Where necessary, alternative instantiations of the process should be depicted with separate start events and using a event-based start gateway.";
		this.Name = "Consistent Usage of Start Events";


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		int num = 0;
	
		
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());
				
				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SubProcess){
						SubProcess sub = (SubProcess) fe;
						//System.out.format("Found a SubProcess: %s\n", sub.getName());
						this.searchSubProcess(sub);
					}else
						if (fe instanceof StartEvent) {
							num++;
							
								/*elementsBPMN.add(fe);
								setElements(fe.getId(),IDProcess);
								ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");*/
								
								elementsBPMNtemp.add(fe);
								Elementstemp.add(new ElementID(fe.getId(),IDProcess));
								temp.append("* name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");
							
						} 
				}
			}
		}
		if (num>1) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion = "\nUse only one Start End Event :" + temp;
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion = "Well done!";
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
			if (fe instanceof StartEvent) {
				
				
				//System.out.println(fe.eClass().getName() + ": name="+ fe.getName() + " ID=" + fe.getId());
				num++;
				
					elementsBPMNtemp.add(fe);
					Elementstemp.add(new ElementID(fe.getId(),IDProcess));
					temp.append("* name=" + fe.getName() + " ID=" + fe.getId()
							+ "\n");
				
			
			}
		}
		if ( num>1) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += "\nUse only one Start Event in SubProcess "+sub.getName()+" " + temp;
			this.status = false;
		}
		
	}

}
