package eu.learnpad.verification.plugin.bpmn.guideline.impl;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.utils.ElementID;

public class SplitAndJoinFlows extends abstractGuideline {

	public SplitAndJoinFlows(Definitions diagram) {
		super(diagram);
		this.id = "21";
		this.Description = "The modeler should not use gateways to join and split at the same time.";
		this.Name = "Split and Join Flows";


	}
	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder ret = new StringBuilder("");
		int i = 1;
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());
			
				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SubProcess){
						SubProcess sub = (SubProcess) fe;
						//System.out.format("Found a SubProcess: %s\n", sub.getName());
						i = this.searchSubProcess(sub, ret, i);
					}else
						if (fe instanceof Gateway) {
							Gateway gateway = (Gateway) fe;
							
							//System.out.println(fe.eClass().getName() + ": name="+ fe.getName() + " ID=" + fe.getId());

							boolean bool = ((gateway.getIncoming().size() == 1 & gateway.getOutgoing().size() > 1) | (gateway.getIncoming().size() > 1 & gateway.getOutgoing().size() == 1));
							if (!bool) {
								elementsBPMN.add(fe);
								setElements(  fe.getId(),IDProcess);
								ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");
							}
						}
				}
			}
		}
		if (!elementsBPMN.isEmpty()) {
			this.Suggestion = "Split following Gateway :" + ret;
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion = "Well done!";
		}
	}

	@Override
	protected int searchSubProcess(SubProcess sub, StringBuilder ret, int i) {
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				i = this.searchSubProcess(ssub, ret, i);
			}else
				if (fe instanceof Gateway) {
					Gateway gateway = (Gateway) fe;
					
					//System.out.println(fe.eClass().getName() + ": name="+ fe.getName() + " ID=" + fe.getId());

					boolean bool = ((gateway.getIncoming().size() == 1 & gateway.getOutgoing().size() > 1) | (gateway.getIncoming().size() > 1 & gateway.getOutgoing().size() == 1));
					if (!bool) {
						elementsBPMN.add(fe);
						setElements( fe.getId(),IDProcess);
						ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
								+ "\n");
					}
				}
		}
		return i;
	}

}
