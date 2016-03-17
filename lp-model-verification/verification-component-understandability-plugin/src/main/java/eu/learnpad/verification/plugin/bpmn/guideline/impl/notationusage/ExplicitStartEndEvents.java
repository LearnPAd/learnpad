package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;

public class ExplicitStartEndEvents extends abstractGuideline{


	public ExplicitStartEndEvents(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "12"; //$NON-NLS-1$
		this.Description = Messages.getString("ExplicitStartEndEvents.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ExplicitStartEndEvents.Name",l); //$NON-NLS-1$


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder ret = new StringBuilder(""); //$NON-NLS-1$
		int i = 1;
		boolean flag = false;
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());
				
				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SubProcess){
						SubProcess sub = (SubProcess) fe;
						//System.out.format("Found a SubProcess: %s\n", sub.getName());
						i = this.searchSubProcess(sub, ret, i);
					}else
						if (fe instanceof StartEvent) {
							Event event = (Event) fe;
							flag = true;
							//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());

							if (event.getOutgoing().size() < 1) {
								elementsBPMN.add(fe);
								String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
								setElements(fe.getId(),IDProcess,name);
								ret.append(i++ +") name=" + name+ " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
										+ "\n"); //$NON-NLS-1$
							}
						} else if (fe instanceof EndEvent) {
							Event event = (Event) fe;
							flag = true;
							//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());

							if (event.getIncoming().size() < 1) {
								elementsBPMN.add(fe);
								String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
								setElements(fe.getId(),IDProcess,name);
								ret.append(i++ +") name=" + name+ " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
										+ "\n"); //$NON-NLS-1$
							}
						}
				}
			}
		}
		if (!elementsBPMN.isEmpty() | !flag) {
			this.Suggestion += Messages.getString("ExplicitStartEndEvents.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ExplicitStartEndEvents.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	protected int searchSubProcess(SubProcess sub, StringBuilder ret, int i){
		boolean flag = false;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				i = this.searchSubProcess(ssub, ret, i);
			}else
			if (fe instanceof StartEvent) {
				Event event = (Event) fe;
				flag = true;
				//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());

				if (event.getOutgoing().size() < 1) {
					elementsBPMN.add(fe);
					String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
					setElements(fe.getId(),IDProcess,name);
					ret.append(i++ +") name=" + name+ " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
							+ "\n"); //$NON-NLS-1$
				}
			} else if (fe instanceof EndEvent) {
				Event event = (Event) fe;
				flag = true;
				//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());

				if (event.getIncoming().size() < 1) {
					elementsBPMN.add(fe);
					String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
					setElements(fe.getId(),IDProcess,name);
					ret.append(i++ +") name=" + name+ " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
							+ "\n"); //$NON-NLS-1$
				}
			}
		}
		if(!sub.getFlowElements().isEmpty())
		if ( !flag) {
			this.Suggestion += Messages.getString("ExplicitStartEndEvents.SuggestionSubprocessKO",l)+sub.getName()+" "; //$NON-NLS-1$ //$NON-NLS-2$
			this.status = false;
		}
		return i;
	}

}
