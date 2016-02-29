package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;


public class LabelingStartandEndEvents extends abstractGuideline{


	public LabelingStartandEndEvents(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "32"; //$NON-NLS-1$
		this.Description = Messages.getString("LabelingStartandEndEvents.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("LabelingStartandEndEvents.Name",l); //$NON-NLS-1$


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
							List<EventDefinition> la = a.getEventDefinitions();
						//	MessageEventDefinition
							if((a.getName()==null || (a.getName().length()==0))&&!la.isEmpty() ){
								num++;

								elementsBPMN.add(fe);
								String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
								setElements(fe.getId(),IDProcess,name);
								temp.append("* name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
										+ "\n"); //$NON-NLS-1$

							}

						} else
							if (fe instanceof EndEvent) {
								EndEvent a = (EndEvent) fe;
								List<EventDefinition> la = a.getEventDefinitions();
								if((a.getName()==null || (a.getName().length()==0))&&!la.isEmpty() ){
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
		}
		if (num>0) {
			this.Suggestion += Messages.getString("LabelingStartandEndEvents.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("LabelingStartandEndEvents.SuggestionOK",l); //$NON-NLS-1$
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
					List<EventDefinition> la = a.getEventDefinitions();
					//	MessageEventDefinition
						if((a.getName()==null || (a.getName().length()==0))&&!la.isEmpty() ){
						//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());
						num++;

						elementsBPMN.add(fe);
						String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
						setElements(fe.getId(),IDProcess,name);
						temp.append("* name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
								+ "\n"); //$NON-NLS-1$
					}

				} else
					if (fe instanceof EndEvent) {
						EndEvent a = (EndEvent) fe;
						List<EventDefinition> la = a.getEventDefinitions();
						//	MessageEventDefinition
							if((a.getName()==null || (a.getName().length()==0))&&!la.isEmpty() ){
							num++;

							elementsBPMN.add(fe);
							String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
							setElements(fe.getId(),IDProcess,name); 
							temp.append("* name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
									+ "\n"); //$NON-NLS-1$

						}

					} 
		}
		if ( num>0) {
			this.Suggestion += Messages.getString("LabelingStartandEndEvents.SuggestionSubprocessKO",l)+sub.getName()+" "; //$NON-NLS-1$ //$NON-NLS-2$
			this.status = false;
		}

	}

}
