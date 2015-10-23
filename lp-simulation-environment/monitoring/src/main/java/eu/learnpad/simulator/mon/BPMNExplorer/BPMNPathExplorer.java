package eu.learnpad.simulator.mon.BPMNExplorer;

import java.util.List;
import java.util.Vector;

import org.w3c.dom.Document;

public interface BPMNPathExplorer {
	
	List<String[]> getUnfoldedBPMN(Document theBusinessProcessToUnfold);
	void setUnfoldedBPMN(Vector<String[]> theUnfoldedBusinessProcess);
	
}
