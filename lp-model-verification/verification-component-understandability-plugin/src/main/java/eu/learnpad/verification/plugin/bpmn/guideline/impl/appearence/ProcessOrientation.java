package eu.learnpad.verification.plugin.bpmn.guideline.impl.appearence;


import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.di.BPMNShape;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.bpmn.reader.BPMNUtils;

public class ProcessOrientation extends abstractGuideline{


	public ProcessOrientation(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "47"; //$NON-NLS-1$
		this.Description = Messages.getString("ProcessOrientation.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ProcessOrientation.Name",l); //$NON-NLS-1$


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

						String name = participant.getName()!=null? participant.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
						setElements(participant.getId(),IDProcess,name);
						//temp.append("Name=" +name + " ID=" + participant.getId()
								//+ "; ");
					}
				}
			}
		}
		if (num>1) {

			this.Suggestion += Messages.getString("ProcessOrientation.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ProcessOrientation.SuggestionOK",l); //$NON-NLS-1$
		}
	}



}
