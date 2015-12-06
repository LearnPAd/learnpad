package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNShape;

import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.BPMNUtils;
import eu.learnpad.verification.plugin.utils.ElementID;

public class ConsistentUsagePools extends abstractGuideline{


	public ConsistentUsagePools(Definitions diagram) {
		super(diagram);
		this.id = "10";
		this.Description = "The modeler should define as many pools as processes and/or participants. Use a black-box pool to represent external participant/processes. The modeled pools need to be in relation with each other and have to be linked to the main process through message exchange.";
		this.Name = "Consistent usage of Pools";


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
					BPMNShape shape= BPMNUtils.findBPMNShape(diagram,participant);
					if(shape!=null)
					if(shape.isIsExpanded()){
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
		if (num>1) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += "\nOpen only one pools: ";
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += "Well done!";
		}
	}



}
