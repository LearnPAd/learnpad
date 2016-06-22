package eu.learnpad.simulator.mon.coverage;

import java.util.Vector;

import org.w3c.dom.Document;

public interface PathExplorer {
	
	Vector<Activity[]> getUnfoldedBPMN(Document theBusinessProcessToUnfold);
	void setUnfoldedBPMN(Vector<Activity[]> theUnfoldedBusinessProcess);
	
}
