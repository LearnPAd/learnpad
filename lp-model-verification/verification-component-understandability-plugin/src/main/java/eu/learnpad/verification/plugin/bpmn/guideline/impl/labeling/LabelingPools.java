package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.RootElement;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.utils.ElementID;

public class LabelingPools extends abstractGuideline{


	public LabelingPools(Definitions diagram) {
		super(diagram);
		this.id = "28";
		this.Description = "The modeler should label pools using the participant’s name. The main pool can be labeled using the process name. If a pool is present in a subprocess, the name of the pool must be the same of the upper-level process pool which includes the subprocess activity. This means that the pool of the upper-level process and the pool of the subprocess needs to be the same.";
		this.Name = "Labeling Pools";


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		int num = 0;
	
		
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Collaboration) {
				Collaboration collaboration = (Collaboration) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());



				List<Participant> partecipants = collaboration.getParticipants();
				for (Participant participant : partecipants) {
					participant.getId();
					IDProcess = participant.getProcessRef().getId();
					if(participant.getName()==null){
						num++;

						/*elementsBPMN.add(fe);
					setElements(fe.getId(),IDProcess);
					ret.append(i++ +") name=" + fe.getName() + " ID=" + fe.getId()
							+ "\n");*/

						//elementsBPMNtemp.add(participant);
						String name = participant.getName()!=null? participant.getName() : "Unlabeled"; 
						Elementstemp.add(new ElementID(participant.getId(),IDProcess,name));
						temp.append("Name=" +name + " ID=" + participant.getId()
								+ "; ");
					}
				}
			}
		}
		if (num>0) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += "\nLabeling Pools:";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}



}
