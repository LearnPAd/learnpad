package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNShape;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.BPMNUtils;

public class ExclusiveGatewayMarking extends abstractGuideline{


	public ExclusiveGatewayMarking(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "17"; //$NON-NLS-1$
		this.Description = Messages.getString("ExclusiveGatewayMarking.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ExclusiveGatewayMarking.Name",l); //$NON-NLS-1$


	}

	@Override
	protected void findGL(Definitions diagram) {
		/*StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		 */

		int num = 0;
	
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				// System.out.format("Found a process: %s\n",
				// process.getName());

				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof ExclusiveGateway){
						ExclusiveGateway g = (ExclusiveGateway) fe;
						//BPMNEdge res = BPMNUtils.findBPMNEdge(diagram, sf);

						BPMNShape shape= BPMNUtils.findBPMNShape(diagram,g);
						if(shape!=null){
							if(!shape.isIsMarkerVisible()){

								num++;

							elementsBPMN.add(fe);
							String name = g.getName()!=null? g.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
							setElements(fe.getId(),IDProcess,name);
							//ret.append(i++ +") name=" + name + " ID=" + fe.getId()
							//		+ "\n");
							}
						}
					}
				}

			}

		}




		//
		/*	for (BPMNEdge bpmnEdge : BPMNEdges.values()) {
			BaseElement bpmnelement = bpmnEdge.getBpmnElement();
			if(bpmnelement instanceof SequenceFlow){
				bpmnelement.getId();
				List<Point> points = bpmnEdge.getWaypoint();
				if(points!=null)
					if(points.size()>2){

					}
			}
		}

		 */
		if (num>0) {

			this.Suggestion += Messages.getString("ExclusiveGatewayMarking.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ExclusiveGatewayMarking.SuggestionOK",l); //$NON-NLS-1$
		}
	}



	

	


}
