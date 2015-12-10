package eu.learnpad.verification.plugin.bpmn.guideline.impl;


import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;


public class ApplyHierarchicalStructure extends abstractGuideline {



	public ApplyHierarchicalStructure(Definitions diagram) {
		super(diagram);
		this.id = "3";
		this.Description = "The modeler should create a hierarchical Business Process Model with multi layers of details for the Process. BPMN sub-processes are used to split the Process into “phases” or “layers”. The modeler can expand the sub-processes later to expose details of lower levels of hierarchy. A process model will contain multiple pages, but internally the integrity of a single model is maintained.";
		this.Name = "Apply hierarchical structure with SubProcesses";


	}

	public void findGL(Definitions diagram) {
		StringBuilder ret = new StringBuilder("");
		int i = 0;
		int subele=0;
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
						subele++;
					}else
						if (fe instanceof Activity || fe instanceof Gateway || fe instanceof SubProcess) {

							i++;
						}  



				}

			}
		}
		if (i>10 & subele<=2) {
			this.Suggestion += "Apply hierarchical structure with SubProcesses: ";
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
