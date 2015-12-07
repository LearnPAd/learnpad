package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;



import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.TerminateEventDefinition;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;



public class RestrictUsageTerminateEndEvent extends abstractGuideline {

	public RestrictUsageTerminateEndEvent(Definitions diagram) {
		super(diagram);
		this.id = "15";
		this.Description = "The process modeler should use terminate events only when strictly necessary; they are used to model situations where several alternative paths are enabled and the entire process have to be finished when one of them is completed. The modeler should use other end events rather than the terminate end event (e.g. a generic end event), to guarantee that the executions of the reaming process paths or activities will not be stopped.";
		this.Name = "Restrict usage of terminate end event";


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();
		/*Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();*/
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
						if (fe instanceof EndEvent ) {
							EndEvent e = (EndEvent) fe;
							List<EventDefinition> edef = e.getEventDefinitions();
							for (EventDefinition eventDefinition : edef) {
								if(eventDefinition instanceof TerminateEventDefinition){
									num++;
									elementsBPMN.add(fe);
									String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
									setElements(fe.getId(),IDProcess,name);
									temp.append("* name=" +name + " ID=" + fe.getId()
											+ "\n");
								}
							}



							/*elementsBPMNtemp.add(fe);
								Elementstemp.add(new ElementID(fe.getId(),IDProcess));
								temp.append("* name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");*/

						} 
				}
			}
		}
		if (num>0) {
			/*elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);*/
			this.Suggestion += "\nDon't use Terminate End Event :";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}

	protected void searchSubProcess(SubProcess sub){
		StringBuilder temp = new StringBuilder();
		/*Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();*/
		int num = 0;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				this.searchSubProcess(ssub);
			}else
				if (fe instanceof EndEvent ) {
					EndEvent e = (EndEvent) fe;
					List<EventDefinition> edef = e.getEventDefinitions();
					for (EventDefinition eventDefinition : edef) {
						if(eventDefinition instanceof TerminateEventDefinition){
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
			/*	elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);*/
			this.Suggestion += "\nDon't use Terminate Event in SubProcess "+sub.getName()+" ";
			this.status = false;
		}

	}
}
