package eu.learnpad.verification.plugin.bpmn.guideline.impl;


import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;


public class ModelLoops extends abstractGuideline {



	public ModelLoops(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "7"; //$NON-NLS-1$
		this.Description = Messages.getString("ModelLoops.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ModelLoops.Name",l); //$NON-NLS-1$


	}

	public void findGL(Definitions diagram) {
		//	StringBuilder ret = new StringBuilder("");
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
						if(fe instanceof Activity){
							Activity a = (Activity) fe;
							Gateway outg=null;
							Gateway ing=null;
							List<SequenceFlow> out = a.getOutgoing();
							List<SequenceFlow> in = a.getIncoming();
							boolean outgateway = false;
							boolean ingateway = false;
							for (SequenceFlow sequenceFlow : out) {
								if(sequenceFlow.getTargetRef() instanceof Gateway){
									outg=(Gateway) sequenceFlow.getTargetRef();
									outgateway = true;
								}
							}
							if(outgateway){
								for (SequenceFlow sequenceFlow : in) {
									if(sequenceFlow.getSourceRef() instanceof Gateway){
										ing=(Gateway) sequenceFlow.getSourceRef();
										ingateway = true;
									}
								}
								if(ingateway){
									List<SequenceFlow> flowsoutg = outg.getOutgoing();
									for (SequenceFlow sequenceFlow : flowsoutg) {
										if(sequenceFlow.getTargetRef().equals(ing)){
											flag=true;
											elementsBPMN.add(fe);
											
											String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
											setElements(fe.getId(),IDProcess,name);
											//temp.append("* name=" + name + " ID=" + fe.getId()
												//	+ "\n");
										}
									}
								}
							}
							/*if (fe instanceof Gateway ) {
							Gateway g =(Gateway) fe;
							String nameclass = fe.getClass().getName();
							List<SequenceFlow> out = g.getOutgoing();
							for (SequenceFlow sequenceFlow : out) {
								FlowNode target = sequenceFlow.getTargetRef();
								String nameclasst = target.getClass().getName();
								//String targetid = sequenceFlow.getTargetRef().getId();
								if(nameclasst.equals(nameclass)){
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
								}

							}*/
						}  



				}

			}
		}
		if (flag) {
			this.Suggestion += Messages.getString("ModelLoops.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ModelLoops.SuggestionOK",l); //$NON-NLS-1$
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
