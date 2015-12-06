package eu.learnpad.verification.plugin.bpmn.guideline.impl;


import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;


public class ModelLoops extends abstractGuideline {



	public ModelLoops(Definitions diagram) {
		super(diagram);
		this.id = "7";
		this.Description = "Where possible, and if this practice contributes to simplify the model, the modeler should model a loop via activity looping (with the loop marker) instead of using a sequence flow looping.";
		this.Name = "Model loops via activity looping";


	}

	public void findGL(Definitions diagram) {
		StringBuilder ret = new StringBuilder("");
		boolean flag=false;
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());

				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {

					if(fe instanceof SubProcess){
						//SubProcess sub = (SubProcess) fe;
						//System.out.format("Found a SubProcess: %s\n", sub.getName());
						//i = this.searchSubProcess(sub, ret, i);
						
					}else
						if (fe instanceof Gateway ) {
							Gateway g =(Gateway) fe;
							String nameclass = fe.getClass().getName();
							List<SequenceFlow> out = g.getOutgoing();
							for (SequenceFlow sequenceFlow : out) {
								FlowNode target = sequenceFlow.getTargetRef();
								String nameclasst = target.getClass().getName();
								//String targetid = sequenceFlow.getTargetRef().getId();
								/*if(nameclasst.equals(nameclass)){
									Gateway t = (Gateway)target;
									List<SequenceFlow> in = t.getIncoming();
									for (SequenceFlow sequenceFlowin : in) {
										FlowNode s = sequenceFlowin.getSourceRef();
										if(s.equals(g)){
											//flag=true;
										}
									}
									GatewayDirection dir = t.getGatewayDirection();
									if(dir.equals(GatewayDirection.DIVERGING)){
										flag=true;
									}
									//System.out.format("Found a targetid: %s\n", targetid);
								}*/
								
							}
						}  



				}

			}
		}
		if (flag) {
			this.Suggestion += "Model loops via activity looping: ";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}

	protected int searchSubProcess(SubProcess sub, StringBuilder ret, int i){

		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				i = this.searchSubProcess(ssub, ret, i);
			}else
				if (fe instanceof Activity) {


				}
		}
		return i;
	}

}
