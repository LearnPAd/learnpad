package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;



import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.TerminateEventDefinition;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;



public class RestrictUsageTerminateEndEvent extends abstractGuideline {

	public RestrictUsageTerminateEndEvent(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "15"; //$NON-NLS-1$
		this.Description = Messages.getString("RestrictUsageTerminateEndEvent.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("RestrictUsageTerminateEndEvent.Name",l); //$NON-NLS-1$


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
									String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
									setElements(fe.getId(),IDProcess,name);
									temp.append("* name=" +name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
											+ "\n"); //$NON-NLS-1$
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
			this.Suggestion += Messages.getString("RestrictUsageTerminateEndEvent.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("RestrictUsageTerminateEndEvent.SuggestionOK",l); //$NON-NLS-1$
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
							String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
							setElements(fe.getId(),IDProcess,name);
							temp.append("* name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
									+ "\n"); //$NON-NLS-1$
						}
					}
				}
		}
		if ( num>0) {
			/*	elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);*/
			this.Suggestion += Messages.getString("RestrictUsageTerminateEndEvent.SuggestionSubprocessKO",l)+sub.getName()+" "; //$NON-NLS-1$ //$NON-NLS-2$
			this.status = false;
		}

	}
}
