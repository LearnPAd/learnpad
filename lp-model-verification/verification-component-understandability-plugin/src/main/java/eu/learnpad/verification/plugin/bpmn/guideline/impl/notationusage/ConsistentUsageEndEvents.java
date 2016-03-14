package eu.learnpad.verification.plugin.bpmn.guideline.impl.notationusage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;
import eu.learnpad.verification.plugin.utils.ElementID;

public class ConsistentUsageEndEvents extends abstractGuideline{


	public ConsistentUsageEndEvents(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "14"; //$NON-NLS-1$
		this.Description = Messages.getString("ConsistentUsageEndEvents.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("ConsistentUsageEndEvents.Name",l); //$NON-NLS-1$


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		boolean flag=false;
	
		
		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());
				int num = 0;
				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SubProcess){
						SubProcess sub = (SubProcess) fe;
						//System.out.format("Found a SubProcess: %s\n", sub.getName());
					//	this.searchSubProcess(sub);
					}else
						if (fe instanceof EndEvent) {
							num++;
							
								
								
								elementsBPMNtemp.add(fe);
								String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
								Elementstemp.add(new ElementID(fe.getId(),IDProcess,name));
								temp.append("Name=" +name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
										+ "; "); //$NON-NLS-1$
							
						} 
				}
				if(num>1){
					Collection<FlowElement> removeBPMNtemps = new ArrayList<FlowElement>();
					for( FlowElement e :elementsBPMNtemp){
						boolean flags = false;
						String name = e.getName();
						if(name==null)
							 name = getName(e);
						if(name!=null)
						for( FlowElement ee :elementsBPMNtemp){
							if(!ee.equals(e)){
								String  namee = ee.getName();
								if(namee==null)
									 namee = getName(ee);
								if(namee!=null)
								if(name.equals(namee)){
									flags = true;
								}
							}
							
						}
						if(!flags){
							removeBPMNtemps.add(e);
						}
					}
					elementsBPMNtemp.removeAll(removeBPMNtemps);
					if(!elementsBPMNtemp.isEmpty()){
					flag=true;
					}
				}else{
					elementsBPMNtemp = new ArrayList<FlowElement>();
					Elementstemp = new ArrayList<ElementID>();
				}
			}
		}
		if (flag) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += Messages.getString("ConsistentUsageEndEvents.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("ConsistentUsageEndEvents.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	protected void searchSubProcess(SubProcess sub){
		StringBuilder temp = new StringBuilder();
		Collection<FlowElement> elementsBPMNtemp = new ArrayList<FlowElement>();
		Collection<ElementID> Elementstemp = new ArrayList<ElementID>();
		int num = 0;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				this.searchSubProcess(ssub);
			}else
			if (fe instanceof EndEvent) {
				
				
				//System.out.println(fe.eClass().getName() + ": name="+ fe.getName() + " ID=" + fe.getId());
				num++;
				
					elementsBPMNtemp.add(fe);
					
					String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
					Elementstemp.add(new ElementID(fe.getId(),IDProcess,name));
					temp.append(" Name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
							+ "; "); //$NON-NLS-1$
				
			
			}
		}
		if ( num>1) {
			elementsBPMN.addAll(elementsBPMNtemp);
			setAllElements(Elementstemp);
			this.Suggestion += Messages.getString("ConsistentUsageEndEvents.SuggestionSubprocessKO",l)+sub.getName()+" "; //$NON-NLS-1$ //$NON-NLS-2$
			this.status = false;
		}
		
		
	}
	
	
	public String getName(FlowElement fe){
		List<ExtensionAttributeValue> Listed = fe.getExtensionValues();
		for(ExtensionAttributeValue ed :Listed){
			FeatureMap val = ed.getValue();
			for(int d=0;d<val.size();d++){
				Entry elem = val.get(d);
				AnyType obj = (AnyType) elem.getValue();
				FeatureMap any = obj.getAny();
				for(int s=0;s<any.size();s++){
					Entry entry = any.get(s);
					entry.getValue();
					AnyType objt = (AnyType) entry.getValue();
					FeatureMap anyt = objt.getAnyAttribute();
					Entry nameex = anyt.get(0);
					if(nameex.getValue().equals("Representation")){
						String descrpt = objt.getMixed().get(0).getValue().toString();
						return descrpt;
						/*if(descrpt.equals("without name")){
							flag=true;
						}*/
					}
				//	System.out.println();
				}

			}

		}
		return null;
	}

}
