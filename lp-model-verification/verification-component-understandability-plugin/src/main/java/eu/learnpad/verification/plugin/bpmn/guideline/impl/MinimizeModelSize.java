package eu.learnpad.verification.plugin.bpmn.guideline.impl;


import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;


public class MinimizeModelSize extends abstractGuideline {

	MinimizeModelSize() {

	}


	public MinimizeModelSize(Definitions diagram) {
		super(diagram);
		this.id = "2";
		this.Description = "The modeler should try to keep models as small as possible. Large process models are difficult to read and comprehend. Additionally, they tend to contain more errors. Defining the correct scope of tasks and level of detail of processes is key to reduce the overage of information.";
		this.Name = "Minimize Model Size";


	}

	public void findGL(Definitions diagram) {
		
		int i = 0;
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());
				
				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if (fe instanceof Activity || fe instanceof Gateway || fe instanceof SubProcess) {

						i++;
					}  
				}

			}
		}
		if (i>31) {
			this.Suggestion += "Too much elements "+i+". Please use max 31  the number of nodes: number of activities and routing elements in a process model";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}

	protected int searchSubProcess(SubProcess sub, StringBuilder ret, int i){

		
		return i;
	}

}
