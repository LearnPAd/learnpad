package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;


public class LabelingEvents extends abstractGuideline{


	public LabelingEvents(Definitions diagram) {
		super(diagram);
		this.id = "31";
		this.Description = "All events should have a label representing the state of the process: Events of type message, signal, escalation, and error events should be labeled with a past participle using an active verb; Link events should be labeled with a noun; Timer events should be labeled with time-date or schedule; Conditional events should be labeled with the condition that triggers them.";
		this.Name = "Labeling Events";


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
						if (fe instanceof IntermediateCatchEvent) {
							IntermediateCatchEvent a = (IntermediateCatchEvent) fe;
							if(a.getName()==null || (a.getName().length()==0) ){
								num++;

								elementsBPMN.add(fe);
								String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
								setElements(fe.getId(),IDProcess,name); 
								temp.append("* name=" + name + " ID=" + fe.getId()
										+ "\n");

							}

						} else
							if (fe instanceof IntermediateThrowEvent) {
								IntermediateThrowEvent a = (IntermediateThrowEvent) fe;
								if(a.getName()==null || (a.getName().length()==0) ){
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
		if (num>0) {

			this.Suggestion += "\nUse Labeling in Events :";
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
				if (fe instanceof IntermediateCatchEvent) {

					IntermediateCatchEvent a = (IntermediateCatchEvent) fe;
					if(a.getName()==null || (a.getName().length()==0) ){
						//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());
						num++;

						elementsBPMN.add(fe);
						String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
						setElements(fe.getId(),IDProcess,name); 
						temp.append("* name=" + name + " ID=" + fe.getId()
								+ "\n");
					}

				} else
					if (fe instanceof IntermediateThrowEvent) {
						IntermediateThrowEvent a = (IntermediateThrowEvent) fe;
						if(a.getName()==null || (a.getName().length()==0) ){
							num++;

							elementsBPMN.add(fe);
							String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
							setElements(fe.getId(),IDProcess,name); 
							temp.append("* name=" + name + " ID=" + fe.getId()
									+ "\n");

						}

					} 
		}
		if ( num>0) {
			this.Suggestion += "\nUse Labeling in Events of the SubProcess "+sub.getName()+" ";
			this.status = false;
		}

	}

}
