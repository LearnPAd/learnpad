package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNShape;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.BPMNUtils;
import eu.learnpad.verification.plugin.utils.ElementID;

public class ConsistentUsagePools extends abstractGuideline{


	public ConsistentUsagePools(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "10"; //$NON-NLS-1$
		this.Description = Messages.getString("ConsistentUsagePools.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ConsistentUsagePools.Name",l); //$NON-NLS-1$


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
						String name = participant.getName()!=null? participant.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
						Elementstemp.add(new ElementID(participant.getId(),IDProcess,name));
						temp.append("Name=" +name + " ID=" + participant.getId() //$NON-NLS-1$ //$NON-NLS-2$
								+ "; "); //$NON-NLS-1$
					}
				}
			}
		}
		if (num>1) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += Messages.getString("ConsistentUsagePools.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ConsistentUsagePools.SuggestionOK",l); //$NON-NLS-1$
		}
	}



}
