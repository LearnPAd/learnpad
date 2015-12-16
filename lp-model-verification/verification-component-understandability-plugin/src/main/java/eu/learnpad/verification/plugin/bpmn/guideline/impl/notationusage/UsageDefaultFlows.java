package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;

public class UsageDefaultFlows extends abstractGuideline{


	public UsageDefaultFlows(Definitions diagram) {
		super(diagram);
		this.id = "22";
		this.Description = "Where possible, after an exclusive and an inclusive gateway, the modeler should express the default flow. One way for the modeler to ensure that the process does not get stuck at a gateway is to use a default condition for one of the outgoing sequence flow. This default sequence flow will always evaluate to true if all the other sequence flow conditions turn out to be false.";
		this.Name = "Usage of default flows";
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
									String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
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
			this.Suggestion += "Usage of default flows in Exclusive Gateways";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
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
							String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
							setElements(fe.getId(),IDProcess,name);
					}
				}
		}
		if ( num>0) {
			/*elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);*/
			this.Suggestion += "\nUsage of default flows in SubProcess "+sub.getName()+" ";
			this.status = false;
		}

	}

}
