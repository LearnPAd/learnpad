package eu.learnpad.verification.plugin.bpmn.guideline.impl.appearence;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.dd.dc.Point;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.BPMNUtils;

public class LinearSequenceFlows extends abstractGuideline{


	public LinearSequenceFlows(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "45"; //$NON-NLS-1$
		this.Description = Messages.getString("LinearSequenceFlows.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("LinearSequenceFlows.Name",l); //$NON-NLS-1$


	}

	@Override
	protected void findGL(Definitions diagram) {
		/*StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		 */

		int num = 0;
		Map<BaseElement,BPMNEdge> BPMNEdges = BPMNUtils.getAllBPMNEdge(diagram);
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				// System.out.format("Found a process: %s\n",
				// process.getName());

				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SequenceFlow){
						SequenceFlow sf = (SequenceFlow) fe;
						//BPMNEdge res = BPMNUtils.findBPMNEdge(diagram, sf);


						//boolean flagprocess = sf.getSourceRef().eContainer() == sf.getTargetRef().eContainer();
						boolean flaglane =false;
						if(sf.getSourceRef().getLanes()!=null)
							if(sf.getTargetRef().getLanes()!=null)
								if(!sf.getSourceRef().getLanes().isEmpty())
									if(!sf.getTargetRef().getLanes().isEmpty())
										flaglane = sf.getSourceRef().getLanes().get(0) != sf.getTargetRef().getLanes().get(0);
						boolean flaggateawy = sf.getSourceRef() instanceof Gateway | sf.getTargetRef() instanceof Gateway;

						BaseElement base= (BaseElement)sf;
						boolean resd = BPMNEdges.containsKey(base);
						if(resd){
							List<Point> points = BPMNEdges.get(base).getWaypoint();
							if(points!=null)
								if(!comparerangepoints(points)){
									if(!((flaggateawy |flaglane ) & (points.size()==3)| points.size()==4 ))
										if((!((flaggateawy | flaglane)&  points.size()==6)  ) ){
											num++;

											elementsBPMN.add(fe);
											String name = sf.getName()!=null? sf.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
											setElements(fe.getId(),IDProcess,name);
											//ret.append(i++ +") name=" + name + " ID=" + fe.getId()
											//		+ "\n");
										}
								}/*else{
									Point x =null;
									boolean flagx=false;
									boolean flagy=false;
									for (Point point : points) {
										if(x==null)
											x = point;
										else{
											flagy = comparerange( point.getY(),x.getY());
											flagx =comparerange( point.getX(),x.getX());
										}

									}
									if(!(flagx | flagy)){
										num++;

										elementsBPMN.add(fe);
										String name = sf.getName()!=null? sf.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
										setElements(fe.getId(),IDProcess,name);
										//ret.append(i++ +") name=" + name + " ID=" + fe.getId()
										//		+ "\n");
									}

								}*/
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

			this.Suggestion += Messages.getString("LinearSequenceFlows.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("LinearSequenceFlows.SeggestionOK",l); //$NON-NLS-1$
		}
	}



	private boolean comparerange(float f, float g){


		if(Math.abs(f-g)<=10){
			return true;
		}

		return false;
	}

	private boolean comparerangepoints(List<Point> points){
		boolean flag=false;
		int next = -1;
		if(points.size()>1)
			for(int i = 1;i<points.size();i++){
				float px = points.get(i-1).getX();
				float py = points.get(i-1).getY();
				float x = points.get(i).getX();
				float y = points.get(i).getY();
				if(i==1){
					if(comparerange(px,x)){
						next = 0;
						flag=true;
					}else{
						if(comparerange(py,y)){
							next = 1;
							flag=true;
						}else{
							flag=false;
							break;
						}
					}
				}else{
					if(next==1){
						if(comparerange(py,y)){
							flag=true;
						}else{
							flag=false;
							break;
						}
					}else{
						if(next==0){
							if(comparerange(px,x)){
								flag=true;
							}else{
								flag=false;
								break;
							}
						}else{
							flag=false;
							break;
						}
					}
				}
			}

		return flag;
	}


}
