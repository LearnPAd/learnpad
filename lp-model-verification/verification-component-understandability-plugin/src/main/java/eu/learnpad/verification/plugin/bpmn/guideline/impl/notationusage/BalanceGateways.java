package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;



import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;


public class BalanceGateways extends abstractGuideline {

	public BalanceGateways(Definitions diagram) {
		super(diagram);
		this.id = "19";
		this.Description = "The modeler should always use the same type of gateway used both for splitting and joining the flow. For example, when a flow is divided with a parallel gateway, the resulting parallel flows should be consolidated via another parallel gateway. In particular, the modeler should ensure that join parallel gateways have the correct number of incoming sequence flow-especially when used in conjunction with other gateways; this is related to ensuring the soundness property. Don’t apply this guidelines on Event-based or Complex Gateways.";
		this.Name = "Balance gateways";


	}
	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder ret = new StringBuilder("");

		int neg=0;
		int npg=0;
		int nig=0;
		int ncg=0;
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
						if (fe instanceof Gateway) {
							Gateway gateway = (Gateway) fe;
							if(gateway instanceof ExclusiveGateway){
								neg++;
							}else
								if(gateway instanceof ParallelGateway){
									npg++;
								}else
									if(gateway instanceof InclusiveGateway){
										nig++;
									}else
										if(gateway instanceof ComplexGateway){
											ncg++;
										}
						}
				}
			}
		}
		int sum = neg+npg+nig+ncg;
		long geg = sum>0? (neg/sum) : 0;
		long gpg = sum>0?(npg/sum): 0;
		long gig = sum>0?(nig/sum): 0;
		long gcg = sum>0?(ncg/sum): 0;
		long sum2= (geg)+(gpg)+(gig)+(gcg);
		if (sum2>0.92) {
			this.Suggestion += "Balance gateways " + ret;
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}

	protected void searchSubProcess(SubProcess sub){
		StringBuilder temp = new StringBuilder();
		
		int neg=0;
		int npg=0;
		int nig=0;
		int ncg=0;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				this.searchSubProcess(ssub);
			}else
				if (fe instanceof Gateway) {
					Gateway gateway = (Gateway) fe;
					if(gateway instanceof ExclusiveGateway){
						neg++;
					}else
						if(gateway instanceof ParallelGateway){
							npg++;
						}else
							if(gateway instanceof InclusiveGateway){
								nig++;
							}else
								if(gateway instanceof ComplexGateway){
									ncg++;
								}
				}
		}
		int sum = neg+npg+nig+ncg;
		long geg = sum>0? (neg/sum) : 0;
		long gpg = sum>0?(npg/sum): 0;
		long gig = sum>0?(nig/sum): 0;
		long gcg = sum>0?(ncg/sum): 0;
		long sum2= geg+gpg+gig+gcg;
		if (sum2>0.92) {

			this.Suggestion += "\nBalance gateways in SubProcess "+sub.getName()+" " + temp;
			this.status = false;
		}

	}
	
	


}
