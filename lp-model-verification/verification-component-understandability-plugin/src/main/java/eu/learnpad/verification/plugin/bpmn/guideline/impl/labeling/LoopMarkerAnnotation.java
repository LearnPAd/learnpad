package eu.learnpad.verification.plugin.bpmn.guideline.impl.labeling;


import java.util.Locale;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.Association;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.LoopCharacteristics;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StandardLoopCharacteristics;
import org.eclipse.bpmn2.SubProcess;

import eu.learnpad.verification.plugin.bpmn.guideline.Messages;
import eu.learnpad.verification.plugin.bpmn.guideline.impl.abstractGuideline;

public class LoopMarkerAnnotation extends abstractGuideline{


	public LoopMarkerAnnotation(Definitions diagram, Locale l){
		super(diagram,l);
		this.l=l;
		this.id = "39"; //$NON-NLS-1$
		this.Description = Messages.getString("LoopMarkerAnnotation.Description",l); //$NON-NLS-1$
		this.Name = Messages.getString("LoopMarkerAnnotation.Name",l); //$NON-NLS-1$


	}

	@Override
	protected void findGL(Definitions diagram) {
		StringBuilder temp = new StringBuilder();

		int num = 0;


		for (RootElement rootElement : diagram.getRootElements()) {
			if (rootElement instanceof Process) {
				Process process = (Process) rootElement;
				//System.out.format("Found a process: %s\n", process.getName());

				IDProcess = process.getId();
				for (FlowElement fe : process.getFlowElements()) {
					if(fe instanceof SubProcess){
						SubProcess sub = (SubProcess) fe;
						//System.out.format("Found a SubProcess: %s\n", sub.getName());
						this.searchSubProcess(sub);
					}else
						if (fe instanceof Activity) {
							Activity a = (Activity) fe;
							LoopCharacteristics loop = a.getLoopCharacteristics();
							if(loop!=null)
							if(loop instanceof StandardLoopCharacteristics){
								if(process.getArtifacts()!=null && !process.getArtifacts().isEmpty()){
									for( Artifact artifact :  process.getArtifacts()){
										if(artifact instanceof Association){
											Association asso = (Association)artifact;
											String source = asso.getSourceRef().toString();
											String target = asso.getTargetRef().toString();
											int starts = source.length();
											int startf = target.length();
											String ids = source.substring(source.indexOf("#")+1,starts-1 ); //$NON-NLS-1$
											String idt = target.substring(target.indexOf("#")+1,startf-1 ); //$NON-NLS-1$

											boolean flag = (a.getId().equals(ids) || a.getId().equals(idt));

											boolean flag2 = asso.getSourceRef().equals(a) || asso.getTargetRef().equals(a) ;
											if(!(flag || flag2)){
												num++;

												elementsBPMN.add(fe);
												String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
												setElements(fe.getId(),IDProcess,name);
												temp.append("* name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
														+ "\n"); //$NON-NLS-1$

											}
										}
									}
								}else{
									num++;

									elementsBPMN.add(fe);
									String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
									setElements(fe.getId(),IDProcess,name);
									temp.append("* name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
											+ "\n"); //$NON-NLS-1$
								}
							}
						} 
				}
			}
		}
		if (num>0) {

			this.Suggestion += Messages.getString("LoopMarkerAnnotation.SuggestionKO",l); //$NON-NLS-1$
			this.status = false;
		}else{
			this.status = true;
			this.Suggestion += Messages.getString("LoopMarkerAnnotation.SuggestionOK",l); //$NON-NLS-1$
		}
	}

	protected void searchSubProcess(SubProcess sub){
		StringBuilder temp = new StringBuilder();

		int num = 0;
		for ( FlowElement fe : sub.getFlowElements()) {
			if(fe instanceof SubProcess){
				SubProcess ssub = (SubProcess) fe;
				//System.out.format("Found a SubProcess: %s\n", ssub.getName());
				this.searchSubProcess(ssub);
			}else
				if (fe instanceof Activity) {

					Activity a = (Activity) fe;
					if(a.getName()==null || (a.getName().length()==0) ){
						//System.out.println(fe.eClass().getName() + ": name="+ fe.getName()!=null? fe.getName() : "Unlabeled" + " ID=" + fe.getId());
						num++;

						elementsBPMN.add(fe);
						String name = fe.getName()!=null? fe.getName() : Messages.getString("Generic.LabelEmpty",l);  //$NON-NLS-1$
						setElements(fe.getId(),IDProcess,name); 
						temp.append("* name=" + name + " ID=" + fe.getId() //$NON-NLS-1$ //$NON-NLS-2$
								+ "\n"); //$NON-NLS-1$
					}

				}
		}
		if ( num>0) {
			this.Suggestion += Messages.getString("LoopMarkerAnnotation.SuggestionSubprocessKO",l)+sub.getName()+" "; //$NON-NLS-1$ //$NON-NLS-2$
			this.status = false;
		}

	}

}
