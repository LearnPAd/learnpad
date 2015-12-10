package eu.learnpad.verification.plugin.bpmn.reader;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.dd.di.DiagramElement;

public class BPMNUtils {
	
	public static BPMNShape findBPMNShape(Definitions definitions, BaseElement baseElement) {
		
		if (definitions!=null) {
			for (BPMNDiagram d : definitions.getDiagrams()) {
				BPMNShape bpmnShape = findBPMNShape(d, baseElement);
				if (bpmnShape!=null)
					return bpmnShape;
			}
		}
		return null;
	}
	

	public static BPMNShape findBPMNShape(BPMNDiagram bpmnDiagram, BaseElement baseElement) {
		for (DiagramElement de : bpmnDiagram.getPlane().getPlaneElement()) {
			if (de instanceof BPMNShape) {
				BaseElement ele = ((BPMNShape)de).getBpmnElement();
				if (ele == baseElement)
					return (BPMNShape)de;
			}
		}
		return null;
	}


	
}
