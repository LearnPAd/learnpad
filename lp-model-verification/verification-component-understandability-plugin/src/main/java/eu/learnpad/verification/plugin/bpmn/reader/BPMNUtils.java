package eu.learnpad.verification.plugin.bpmn.reader;

import java.util.HashMap;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.di.BPMNEdge;
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

	public static BPMNEdge findBPMNEdge(Definitions definitions, BaseElement baseElement) {

		if (definitions!=null) {
			for (BPMNDiagram d : definitions.getDiagrams()) {
				BPMNEdge bpmnEdge = findBPMNEdge(d, baseElement);
				if (bpmnEdge!=null)
					return bpmnEdge;
			}
		}
		return null;
	}



	public static  BPMNEdge findBPMNEdge(BPMNDiagram bpmnDiagram, BaseElement baseElement) {
		for (DiagramElement de : bpmnDiagram.getPlane().getPlaneElement()) {
			if (de instanceof BPMNEdge) {
				BaseElement ele = ((BPMNEdge)de).getBpmnElement();
				if (ele == baseElement)
					return (BPMNEdge)de;
			}
		}
		return null;
	}

	public static java.util.Map<BaseElement,BPMNEdge>  getAllBPMNEdge(Definitions definitions) {

		if (definitions!=null) {
			for (BPMNDiagram d : definitions.getDiagrams()) {
				java.util.Map<BaseElement,BPMNEdge>  bpmnEdges = getAllBPMNEdge(d);
				if (bpmnEdges!=null)
					return bpmnEdges;
			}
		}
		return null;
	}

	public static  java.util.Map<BaseElement,BPMNEdge>  getAllBPMNEdge(BPMNDiagram bpmnDiagram) {
		java.util.Map<BaseElement,BPMNEdge>  colledge = new HashMap<BaseElement, BPMNEdge>();
		for (DiagramElement de : bpmnDiagram.getPlane().getPlaneElement()) {
			if (de instanceof BPMNEdge) {
				BPMNEdge edge = (BPMNEdge)de;
				colledge.put(edge.getBpmnElement(),edge);
			}
		}
		return colledge;
	}

}
