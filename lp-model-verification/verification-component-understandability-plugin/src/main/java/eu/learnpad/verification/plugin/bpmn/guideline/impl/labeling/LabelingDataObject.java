package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;


public class LabelingDataObject extends abstractGuideline{


	public LabelingDataObject(Definitions diagram) {
		super(diagram);
		this.id = "37";
		this.Description = "All data objects should be labeled using a qualified noun that is the name of a business object. Multiple instances of the same data object are labeled (which are really data object references) using a matching label followed by the applicable state in square brackets.";
		this.Name = "Labeling Data Object";


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
						if (fe instanceof DataObject) {
							DataObject data = (DataObject) fe;
							if(data.getName()==null || (data.getName().length()==0) ){
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

			this.Suggestion += "\nLabeling Data Objects: ";
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
				if (fe instanceof DataObject) {
					DataObject data = (DataObject) fe;
					if(data.getName()==null || (data.getName().length()==0) ){
				
						elementsBPMN.add(fe);
						String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
						setElements(fe.getId(),IDProcess,name);
						temp.append("* name=" + name + " ID=" + fe.getId()
								+ "\n");
					}

				}  
		}
		if ( num>0) {
			this.Suggestion += "\nLabeling Data Objects in SubProcess: "+sub.getName()+" ";
			this.status = false;
		}

	}

}
