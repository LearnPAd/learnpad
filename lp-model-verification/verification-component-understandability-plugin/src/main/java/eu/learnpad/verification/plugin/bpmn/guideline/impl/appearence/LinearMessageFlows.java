package eu.learnpad.verification.plugin.bpmn.guideline.impl.appearence;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.InteractionNode;
import org.eclipse.bpmn2.MessageFlow;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.dd.dc.Point;
import org.eclipse.emf.ecore.EObject;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.BPMNUtils;

public class LinearMessageFlows extends abstractGuideline{


	public LinearMessageFlows(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "46"; //$NON-NLS-1$
		this.Description = Messages.getString("LinearMessageFlows.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("LinearMessageFlows.Name",l); //$NON-NLS-1$


	}

	@Override
	protected void findGL(Definitions diagram) {


		int num = 0;
		Map<BaseElement,BPMNEdge> BPMNEdges = BPMNUtils.getAllBPMNEdge(diagram);
		/*for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Collaboration) {
				Collaboration collaboration = (Collaboration) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());

				//Process process = (Process) rootElement;
				// System.out.format("Found a process: %s\n",
				// process.getName());

				//IDProcess = process.getId();
				for (MessageFlow fe : collaboration.getMessageFlows()) {						
					//BPMNEdge res = BPMNUtils.findBPMNEdge(diagram, sf);
					BaseElement base= (BaseElement)fe;
					boolean resd = BPMNEdges.containsKey(base);
					if(resd){
						List<Point> points = BPMNEdges.get(base).getWaypoint();
						if(points!=null)
							if(points.size()>1){
								num++;
								InteractionNode ele = fe.getSourceRef();
								EObject process = ele.eContainer();
								//elementsBPMN.add(fe);
								if(process instanceof Process){

									IDProcess =(( Process)process).getId();
								}
								String name = fe.getName()!=null? fe.getName() : "Unlabeled"; 
								setElements(fe.getId(),IDProcess,name);
								//ret.append(i++ +") name=" + name + " ID=" + fe.getId()
								//		+ "\n");
							}
					}


				}
			}
		}*/


		//
		for (BaseElement bpmnelement : BPMNEdges.keySet()) {
			if(bpmnelement instanceof MessageFlow){
				MessageFlow base= (MessageFlow)bpmnelement;
				bpmnelement.getId();
				List<Point> points = BPMNEdges.get(bpmnelement).getWaypoint();
				if(points!=null)
					if(points.size()>2){
						num++;
						InteractionNode ele = base.getSourceRef();
						if(ele!=null){
							EObject process = ele.eContainer();
							//elementsBPMN.add(fe);
							if(process instanceof Process){

								IDProcess =(( Process)process).getId();
							}
						}
						String name = base.getName()!=null? base.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
						setElements(base.getId(),IDProcess,name);
					}

			}
		}


		if (num>0) {

			this.Suggestion += Messages.getString("LinearMessageFlows.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("LinearMessageFlows.SuggestionOK",l); //$NON-NLS-1$
		}
	}



}
