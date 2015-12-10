package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.utils.ElementID;

public class LabelingLanes extends abstractGuideline{


	public LabelingLanes(Definitions diagram) {
		super(diagram);
		this.id = "29";
		this.Description = "Lanes must always have a label. The label should identify the responsi- ble entity for the process. Lanes are often used for representing things as internal roles (e.g., manager, associate), systems (e.g., an enterprise application), or internal departments (e.g., shipping, finance).";
		this.Name = "Labeling Lanes";


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
				List<LaneSet> listlanes = process.getLaneSets();
				for (LaneSet laneSet : listlanes) {
					List<Lane> lanes = laneSet.getLanes();
					for (Lane lane : lanes) {


						if(lane.getName()==null){
							num++;
							//elementsBPMNtemp.add(laneSet);
							String name = lane.getName()!=null? lane.getName() : "Unlabeled"; 
							Elementstemp.add(new ElementID(lane.getId(),IDProcess,name));

							temp.append("* name=" + name + " ID=" + lane.getId()
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
