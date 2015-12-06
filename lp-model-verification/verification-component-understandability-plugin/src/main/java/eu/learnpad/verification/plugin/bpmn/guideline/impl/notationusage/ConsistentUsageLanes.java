package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.utils.ElementID;

public class ConsistentUsageLanes extends abstractGuideline{


	public ConsistentUsageLanes(Definitions diagram) {
		super(diagram);
		this.id = "11";
		this.Description = "The modeler should model internal organizational units as lanes within a single process pool, not as separate pools; separate pools imply independent processes. Create a lane, in a pool, only if at least one activity or intermediate event is performed in it.";
		this.Name = "Consistent usage of lanes";


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
						
						String name = laneSet.getName()!=null? laneSet.getName() : "Unlabeled"; 
						Elementstemp.add(new ElementID(laneSet.getId(),IDProcess,name));
						temp.append("* name=" + name + " ID=" + laneSet.getId()
								+ "\n");
					}
					}
				}
				
			}
		}
		if (num>0) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += "\nLabeling Lanes:";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}

	

}
