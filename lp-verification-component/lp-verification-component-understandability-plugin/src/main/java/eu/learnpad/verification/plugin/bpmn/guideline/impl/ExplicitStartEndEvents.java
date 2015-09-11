package eu.learnpad.verification.plugin.bpmn.guideline.impl;

import java.util.List;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;

public class ExplicitStartEndEvents extends abstractGuideline{


	public ExplicitStartEndEvents(List<RootElement> diagram) {
		super(diagram);
		this.id = "16";
		this.Description = "The modeler should explicitly make use of start and end events. The use of start and end events is necessary to represent the different states that begin and complete the modeled process. Processes with implicit start and end events are undesirable and could lead to misinterpretations.";
		this.Name = "Explicit usage of start and end events";


	}

	@Override
	protected void findGL(List<RootElement> diagram) {
		StringBuilder ret = new StringBuilder("");
		int i = 1;
		boolean flag = false;
		for (RootElement rootElement : diagram) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				System.out.format("Found a process: %s\n", process.getName());
				NameProcess = process.getName();
				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SubProcess){
						SubProcess sub = (SubProcess) fe;
						System.out.format("Found a SubProcess: %s\n", sub.getName());
						i = this.searchSubProcess(sub, ret, i);
					}else
						if (fe instanceof StartEvent) {
							Event event = (Event) fe;
							flag = true;
							System.out.println(fe.eClass().getName() + ": name="
									+ fe.getName() + " ID=" + fe.getId());

							if (event.getOutgoing().size() < 1) {
								elementsBPMN.add(fe);
								setElements(fe.getId());
								ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");
							}
						} else if (fe instanceof EndEvent) {
							Event event = (Event) fe;
							flag = true;
							System.out.println(fe.eClass().getName() + ": name="
									+ fe.getName() + " ID=" + fe.getId());

							if (event.getIncoming().size() < 1) {
								elementsBPMN.add(fe);
								setElements(fe.getId());
								ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");
							}
						}
				}
			}
		}
		if (!elementsBPMN.isEmpty() | !flag) {
			this.Suggestion = "Add Start or/and End Event " + ret;
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion = "Well done!";
		}
	}

	protected int searchSubProcess(SubProcess sub, StringBuilder ret, int i){
		boolean flag = false;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				System.out.format("Found a SubProcess: %s\n", ssub.getName());
				i = this.searchSubProcess(ssub, ret, i);
			}else
			if (fe instanceof StartEvent) {
				Event event = (Event) fe;
				flag = true;
				System.out.println(fe.eClass().getName() + ": name="
						+ fe.getName() + " ID=" + fe.getId());

				if (event.getOutgoing().size() < 1) {
					elementsBPMN.add(fe);
					setElements(fe.getId());
					ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
							+ "\n");
				}
			} else if (fe instanceof EndEvent) {
				Event event = (Event) fe;
				flag = true;
				System.out.println(fe.eClass().getName() + ": name="
						+ fe.getName() + " ID=" + fe.getId());

				if (event.getIncoming().size() < 1) {
					elementsBPMN.add(fe);
					setElements(fe.getId());
					ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
							+ "\n");
				}
			}
		}
		if ( !flag) {
			this.Suggestion += "\nAdd Start or/and End Event in SubProcess "+sub.getName()+" " + ret;
			this.status = false;
		}
		return i;
	}

}
