package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;



import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;


public class SplitAndJoinFlows extends abstractGuideline {

	public SplitAndJoinFlows(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "18"; //$NON-NLS-1$
		this.Description = Messages.getString("SplitAndJoinFlows.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("SplitAndJoinFlows.Name",l); //$NON-NLS-1$


	}
	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder ret = new StringBuilder(""); //$NON-NLS-1$
		int i = 1;
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
						if (fe instanceof Gateway) {
							Gateway gateway = (Gateway) fe;
							
							//System.out.println(fe.eClass().getName() + ": name="+ fe.getName() + " ID=" + fe.getId());

							boolean bool = ((gateway.getIncoming().size() == 1 & gateway.getOutgoing().size() > 1) | (gateway.getIncoming().size() > 1 & gateway.getOutgoing().size() == 1));
							if (!bool) {
								elementsBPMN.add(fe);
								String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
								setElements(fe.getId(),IDProcess,name); 
								ret.append(i++ +") name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
										+ "\n"); //$NON-NLS-1$
							}
						}
				}
			}
		}
		if (!elementsBPMN.isEmpty()) {
			this.Suggestion += Messages.getString("SplitAndJoinFlows.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("SplitAndJoinFlows.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	@Override
	protected int searchSubProcess(SubProcess sub, StringBuilder ret, int i) {
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				i = this.searchSubProcess(ssub, ret, i);
			}else
				if (fe instanceof Gateway) {
					Gateway gateway = (Gateway) fe;
					
					//System.out.println(fe.eClass().getName() + ": name="+ fe.getName() + " ID=" + fe.getId());

					boolean bool = ((gateway.getIncoming().size() == 1 & gateway.getOutgoing().size() > 1) | (gateway.getIncoming().size() > 1 & gateway.getOutgoing().size() == 1));
					if (!bool) {
						elementsBPMN.add(fe);
						String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
						setElements(fe.getId(),IDProcess,name);
						ret.append(i++ +") name=" + name+ " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
								+ "\n"); //$NON-NLS-1$
					}
				}
		}
		return i;
	}

}
