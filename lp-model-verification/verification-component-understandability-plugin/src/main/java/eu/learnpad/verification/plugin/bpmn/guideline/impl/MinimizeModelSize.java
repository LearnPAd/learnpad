package eu.learnpad.verification.plugin.bpmn.guideline.impl;


import java.util.Locale;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;


public class MinimizeModelSize extends abstractGuideline {

	MinimizeModelSize() {

	}


	public MinimizeModelSize(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "2"; //$NON-NLS-1$
		this.Description = Messages.getString("MinimizeModelSize.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("MinimizeModelSize.Name",l); //$NON-NLS-1$


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
			this.Suggestion += String.format(Messages.getString("MinimizeModelSize.SuggestionKO",l),i); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("MinimizeModelSize.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	protected int searchSubProcess(SubProcess sub, StringBuilder ret, int i){

		
		return i;
	}

}
