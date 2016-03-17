package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;

import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;

public class UsageDefaultFlows extends abstractGuideline{


	public UsageDefaultFlows(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "22"; //$NON-NLS-1$
		this.Description = Messages.getString("UsageDefaultFlows.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("UsageDefaultFlows.Name",l); //$NON-NLS-1$
	}

	@Override
	protected void findGL(Definitions diagram) {
		/*StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
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
						if (fe instanceof ExclusiveGateway) {
							ExclusiveGateway gateway = (ExclusiveGateway) fe;
							List<SequenceFlow> listout = gateway.getOutgoing();
							List<SequenceFlow> listin = gateway.getIncoming();
							boolean diverging = listout.size() >= listin.size();
							SequenceFlow def =gateway.getDefault();
							if (diverging & def==null) {
								num++;
									elementsBPMN.add(fe);
									String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
									setElements(fe.getId(),IDProcess,name);
								//	temp.append("* name=" + name + " ID=" + fe.getId()
									//		+ "\n");
								
								/*elementsBPMNtemp.add(fe);
								Elementstemp.add(new ElementID(fe.getId(),IDProcess));
								temp.append("* name=" + fe.getName() + " ID=" + fe.getId()
										+ "\n");*/
							}

						} 
				}
			}
		}
		if (num>0) {
			/*elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);*/
			this.Suggestion += Messages.getString("UsageDefaultFlows.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("UsageDefaultFlows.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	protected void searchSubProcess(SubProcess sub){
		/*StringBuilder temp = new StringBuilder();
			Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();*/
		int num = 0;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				this.searchSubProcess(ssub);
			}else
				if (fe instanceof ExclusiveGateway) {
					ExclusiveGateway gateway = (ExclusiveGateway) fe;
					List<SequenceFlow> listout = gateway.getOutgoing();
					List<SequenceFlow> listin = gateway.getIncoming();
					boolean diverging = listout.size() >= listin.size();
					SequenceFlow def =gateway.getDefault();
					if (diverging & def==null) {
							num++;
							elementsBPMN.add(fe);
							String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
							setElements(fe.getId(),IDProcess,name);
					}
				}
		}
		if ( num>0) {
			/*elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);*/
			this.Suggestion += Messages.getString("UsageDefaultFlows.SuggestionSubprocessKO",l)+sub.getName()+" "; //$NON-NLS-1$ //$NON-NLS-2$
			this.status = false;
		}

	}

}
