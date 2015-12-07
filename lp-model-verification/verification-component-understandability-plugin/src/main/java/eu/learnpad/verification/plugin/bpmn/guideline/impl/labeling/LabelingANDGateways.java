package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;


public class LabelingANDGateways extends abstractGuideline{


	public LabelingANDGateways(Definitions diagram) {
		super(diagram);
		this.id = "35";
		this.Description = "Omit labels on AND-splits and joins (and sequence flows connecting them); they add no new information, so it is best to omit them.";
		this.Name = "Labeling AND Gateway";


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();

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
						if (fe instanceof ParallelGateway) {
							ParallelGateway gateway = (ParallelGateway) fe;
							if(gateway.getName()!=null ){
								if(gateway.getName().length()>1){
									num++;

									elementsBPMN.add(fe);
									String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
									setElements(fe.getId(),IDProcess,name);
									temp.append("* name=" + name + " ID=" + fe.getId()
											+ "\n");
								}

							}

						} 
				}
			}
		}
		if (num>0) {

			this.Suggestion += "\nNot Labeling AND Gateways: ";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}

	protected void searchSubProcess(SubProcess sub){
		StringBuilder temp = new StringBuilder();

		int num = 0;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				this.searchSubProcess(ssub);
			}else
				if (fe instanceof ParallelGateway) {
					ParallelGateway gateway = (ParallelGateway) fe;
					if(gateway.getName()!=null  ){
						if(gateway.getName().length()>1){
							//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());
							num++;

							elementsBPMN.add(fe);
							String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
							setElements(fe.getId(),IDProcess,name);
							temp.append("* name=" + name + " ID=" + fe.getId()
									+ "\n");
						}
					}
				}


		}
		if ( num>0) {
			this.Suggestion += "\nNot Labeling AND Gateways in SubProcess: "+sub.getName()+" ";
			this.status = false;
		}

	}

}
