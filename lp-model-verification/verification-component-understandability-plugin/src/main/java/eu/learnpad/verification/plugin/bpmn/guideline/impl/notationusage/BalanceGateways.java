package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;

import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;

public class BalanceGateways extends abstractGuideline {

	public BalanceGateways(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "19"; //$NON-NLS-1$
		this.Description = Messages.getString("BalanceGateways.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("BalanceGateways.Name",l); //$NON-NLS-1$

	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder ret = new StringBuilder(""); //$NON-NLS-1$

		int neg = 0;
		int npg = 0;
		int nig = 0;
		int ncg = 0;
		int negc = 0;
		int npgc = 0;
		int nigc = 0;
		int ncgc = 0;
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				// System.out.format("Found a process: %s\n",
				// process.getName());

				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if (fe instanceof SubProcess) {
						SubProcess sub = (SubProcess) fe;
						// System.out.format("Found a SubProcess: %s\n",
						// sub.getName());
						this.searchSubProcess(sub);
					} else if (fe instanceof Gateway) {
						Gateway gateway = (Gateway) fe;
						List<SequenceFlow> listout = gateway.getOutgoing();
						List<SequenceFlow> listin = gateway.getIncoming();
						boolean diverging = listout.size() > listin.size();
						if (diverging) {
							if (gateway instanceof ExclusiveGateway) {

								neg += listout.size();
							} else if (gateway instanceof ParallelGateway) {
								npg += listout.size();
							} else if (gateway instanceof InclusiveGateway) {
								nig += listout.size();
							} else if (gateway instanceof ComplexGateway) {
								ncg += listout.size();
							}
						} else {
							if (gateway instanceof ExclusiveGateway) {

								negc += listin.size();
							} else if (gateway instanceof ParallelGateway) {
								npgc += listin.size();
							} else if (gateway instanceof InclusiveGateway) {
								nigc += listin.size();
							} else if (gateway instanceof ComplexGateway) {
								ncgc += listin.size();
							}
						}

					}
				}
			}
		}
		int sum = Math.abs(neg - negc) + Math.abs(npg - npgc)
				+ Math.abs(nig - nigc) + Math.abs(ncg - ncgc);
		if (sum >= 15) {
			this.Suggestion += Messages.getString("BalanceGateways.SuggestionKO",l) + ret; //$NON-NLS-1$
			this.status = false;
		} else {
			this.status = true;
			this.Suggestion += Messages.getString("BalanceGateways.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	protected void searchSubProcess(SubProcess sub) {
		StringBuilder temp = new StringBuilder();

		int neg = 0;
		int npg = 0;
		int nig = 0;
		int ncg = 0;
		int negc = 0;
		int npgc = 0;
		int nigc = 0;
		int ncgc = 0;
		for (FlowElement fe : sub.getFlowElements()) {
			if (fe instanceof SubProcess) {
				SubProcess ssub = (SubProcess) fe;
				// System.out.format("Found a SubProcess: %s\n",
				// ssub.getName());
				this.searchSubProcess(ssub);
			} else if (fe instanceof Gateway) {
				Gateway gateway = (Gateway) fe;
				List<SequenceFlow> listout = gateway.getOutgoing();
				List<SequenceFlow> listin = gateway.getIncoming();
				boolean diverging = listout.size() > listin.size();
				if (diverging) {
					if (gateway instanceof ExclusiveGateway) {

						neg += listout.size();
					} else if (gateway instanceof ParallelGateway) {
						npg += listout.size();
					} else if (gateway instanceof InclusiveGateway) {
						nig += listout.size();
					} else if (gateway instanceof ComplexGateway) {
						ncg += listout.size();
					}
				} else {
					if (gateway instanceof ExclusiveGateway) {

						negc += listin.size();
					} else if (gateway instanceof ParallelGateway) {
						npgc += listin.size();
					} else if (gateway instanceof InclusiveGateway) {
						nigc += listin.size();
					} else if (gateway instanceof ComplexGateway) {
						ncgc += listin.size();
					}
				}
			}
		}
		int sum = Math.abs(neg - negc) + Math.abs(npg - npgc)
				+ Math.abs(nig - nigc) + Math.abs(ncg - ncgc);
		if (sum >= 15) {

			this.Suggestion += Messages.getString("BalanceGateways.SuggestionSubprocessKO",l) //$NON-NLS-1$
					+ sub.getName() + " " + temp; //$NON-NLS-1$
			this.status = false;
		}

	}

}
