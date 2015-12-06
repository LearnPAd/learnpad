package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;

public class LabelingActivities extends abstractGuideline{


	public LabelingActivities(Definitions diagram) {
		super(diagram);
		this.id = "30";
		this.Description = "Label activities with one verb, and one object. The verb used should use the present tense and be familiar to the organization. The object has to be qualified and also of meaning to the business. Multiple activ- ities should not be labeled with the same name, except for same Call Activities used many time in the process.";
		this.Name = "Labeling Activities";


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
						if (fe instanceof Activity) {
							Activity a = (Activity) fe;
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

			this.Suggestion += "\nLabeling Activities :";
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
				if (fe instanceof Activity) {

					Activity a = (Activity) fe;
					if(a.getName()==null || (a.getName().length()==0) ){
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
		if ( num>0) {
			this.Suggestion += "\nLabeling Activities in SubProcess "+sub.getName();
			this.status = false;
		}

	}

}
