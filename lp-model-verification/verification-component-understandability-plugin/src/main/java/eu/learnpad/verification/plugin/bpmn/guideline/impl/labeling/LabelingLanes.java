package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


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

public class LabelingLanes extends abstractGuideline{


	public LabelingLanes(Definitions diagram) {
		super(diagram);
		this.id = "29";
		this.Description = "Lanes must always have a label. The label should identify the responsi- ble entity for the process. Lanes are often used for representing things as internal roles (e.g., manager, associate), systems (e.g., an enterprise application), or internal departments (e.g., shipping, finance).";
		this.Name = "Labeling Lanes";


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
						if (fe instanceof EndEvent) {
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
			this.Suggestion = "\nUse only one End End Event :" + temp;
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
			if (fe instanceof EndEvent) {
				
				
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
			this.Suggestion += "\nUse only one End Event in SubProcess "+sub.getName()+" " + temp;
			this.status = false;
		}
		
	}

}
