package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.utils.ElementID;

public class ConsistentUsageLanes extends abstractGuideline{


	public ConsistentUsageLanes(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "11"; //$NON-NLS-1$
		this.Description = Messages.getString("ConsistentUsageLanes.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ConsistentUsageLanes.Name",l); //$NON-NLS-1$


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		int num = 0;
	
		
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());
				
				IDProcess = process.getId();
				List<LaneSet> lanes = process.getLaneSets();
				for (LaneSet laneSet : lanes) {
					for (Lane lane : laneSet.getLanes()){
						List<FlowNode> flow = lane.getFlowNodeRefs();
					
					if(flow.isEmpty()){
						num++;
						//elementsBPMNtemp.add(laneSet);
						
						String name = laneSet.getName()!=null? laneSet.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
						Elementstemp.add(new ElementID(laneSet.getId(),IDProcess,name));
						temp.append("* name=" + name + " ID=" + laneSet.getId() //$NON-NLS-1$ //$NON-NLS-2$
								+ "\n"); //$NON-NLS-1$
					}
					}
				}
				
			}
		}
		if (num>0) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += Messages.getString("ConsistentUsageLanes.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ConsistentUsageLanes.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	

}
