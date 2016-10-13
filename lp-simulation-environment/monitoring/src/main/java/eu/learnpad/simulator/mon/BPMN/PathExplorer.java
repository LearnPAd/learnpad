package eu.learnpad.simulator.mon.BPMN;

import java.util.Vector;

import org.w3c.dom.Document;

import eu.learnpad.simulator.mon.coverage.Activity;

public interface PathExplorer {
	
	Vector<Activity[]> getUnfoldedBPMN(Document theBusinessProcessToUnfold, String idBPMN);
	void setUnfoldedBPMN(Vector<Activity[]> theUnfoldedBusinessProcess);
	
}
