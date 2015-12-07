package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;


public class LabelingStartandEndEvents extends abstractGuideline{


	public LabelingStartandEndEvents(Definitions diagram) {
		super(diagram);
		this.id = "32";
		this.Description = "The modeler should not label start none and end none event if there is only one instance of them. The modeler shoud use labeling when multiple start and end events are used. Label them according to what they represent using a noun. Do not repeat names.";
		this.Name = "Labeling Start and End Events";


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
						if (fe instanceof StartEvent) {
							StartEvent a = (StartEvent) fe;
							if(a.getName()==null || (a.getName().length()==0) ){
								num++;

								elementsBPMN.add(fe);
								String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
								setElements(fe.getId(),IDProcess,name);
								temp.append("* name=" + name + " ID=" + fe.getId()
										+ "\n");

							}

						} else
							if (fe instanceof EndEvent) {
								EndEvent a = (EndEvent) fe;
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
			this.Suggestion += "\nLabeling Start/End Events: ";
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
				if (fe instanceof StartEvent) {

					StartEvent a = (StartEvent) fe;
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
					if (fe instanceof EndEvent) {
						EndEvent a = (EndEvent) fe;
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
			this.Suggestion += "\nLabeling Start/End Events in SubProcess: "+sub.getName()+" ";
			this.status = false;
		}

	}

}
