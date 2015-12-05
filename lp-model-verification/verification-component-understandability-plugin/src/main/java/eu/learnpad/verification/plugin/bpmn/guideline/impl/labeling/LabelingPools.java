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

public class LabelingPools extends abstractGuideline{


	public LabelingPools(Definitions diagram) {
		super(diagram);
		this.id = "28";
		this.Description = "The modeler should label pools using the participant’s name. The main pool can be labeled using the process name. If a pool is present in a subprocess, the name of the pool must be the same of the upper-level process pool which includes the subprocess activity. This means that the pool of the upper-level process and the pool of the subprocess needs to be the same.";
		this.Name = "Labeling Pools";


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
