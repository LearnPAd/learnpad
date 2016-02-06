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


public class ApplyHierarchicalStructure extends abstractGuideline {



	public ApplyHierarchicalStructure(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "3"; //$NON-NLS-1$
		this.Description = Messages.getString("ApplyHierarchicalStructure.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ApplyHierarchicalStructure.Name",l); //$NON-NLS-1$


	}

	public void findGL(Definitions diagram) {
		//StringBuilder ret = new StringBuilder("");
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
		if (i>29 & subele<=2) {
			this.Suggestion += Messages.getString("ApplyHierarchicalStructure.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ApplyHierarchicalStructure.SuggestionOK",l); //$NON-NLS-1$
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
