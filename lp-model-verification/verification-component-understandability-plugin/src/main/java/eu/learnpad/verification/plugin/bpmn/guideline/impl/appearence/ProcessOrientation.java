package eu.learnpad.verification.plugin.bpmn.guideline.impl.appearence;


import java.util.List;

import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNShape;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.BPMNUtils;

public class ProcessOrientation extends abstractGuideline{


	public ProcessOrientation(Definitions diagram) {
		super(diagram);
		this.id = "47";
		this.Description = "The modeler should draw pools horizontally and use consistent layout with horizontal sequence flows, and vertical message flows and associa- tions.";
		this.Name = "Process Orientation";


	}

	@Override
	protected void findGL(Definitions diagram) {


		int num = 0;
		
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Collaboration) {
				Collaboration collaboration = (Collaboration) rootElement;
				
				List<Participant> partecipants = collaboration.getParticipants();
				for (Participant participant : partecipants) {
					participant.getId();
					IDProcess = participant.getProcessRef().getId();
					BPMNShape shape= BPMNUtils.findBPMNShape(diagram,participant);
					if(shape!=null)
					if(!shape.isIsHorizontal()){
						num++;

						String name = participant.getName()!=null? participant.getName() : "Unlabeled"; 
						setElements(participant.getId(),IDProcess,name);
						//temp.append("Name=" +name + " ID=" + participant.getId()
								//+ "; ");
					}
				}
			}
		}
		if (num>1) {

			this.Suggestion += "Change Process Orientation: ";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}



}
