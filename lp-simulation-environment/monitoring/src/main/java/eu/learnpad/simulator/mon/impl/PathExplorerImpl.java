package eu.learnpad.simulator.mon.impl;

import java.util.HashMap;
import java.util.Vector;

import org.w3c.dom.Document;

import eu.learnpad.simulator.mon.BPMN.PathExplorer;
import eu.learnpad.simulator.mon.coverage.Activity;

public class PathExplorerImpl implements PathExplorer {

	public Vector<Activity[]> lastExploredBPMN;
	
	@Override
	public Vector<Activity[]> getUnfoldedBPMN(Document theBusinessProcessToUnfold) {
		
		//call the software provided by third parties
		
		
		//TODO: REPLACE THIS CODE WITH THE  THIRDY PARTIES COMPONENT IN CHARGE TO EXPLORE THE BPMN AND PROVIDE A LIST 
		lastExploredBPMN = new Vector<Activity[]>();
		
		HashMap<String, Float> kpiExample = new HashMap<String, Float>();
		kpiExample.put("kpiOne", 0.1f);
		kpiExample.put("kpiTwo", 0.2f);
		kpiExample.put("kpiThree", 0.3f);
		kpiExample.put("kpiFour", 0.4f);
		
		Activity checkApp = new Activity("act1","Check Application",kpiExample, 2.0f);
		Activity validateEleg = new Activity("act2", "Validate elegibility",kpiExample, 2.0f);
		Activity inviteInterview = new Activity("act3","Invite for interview",kpiExample, 2.0f);
		Activity sendReject = new Activity("act4","Send rejection letter",kpiExample, 2.0f);
		Activity makeInterview = new Activity("act5","Make Interview",kpiExample, 2.0f);
		Activity decideApplication = new Activity("act6","Decide application",kpiExample, 2.0f);
		Activity decidefee = new Activity("act7","Decide fee",kpiExample, 2.0f);
		Activity sendAcceptance = new Activity("act8","Send Acceptance letter",kpiExample, 2.0f);
		
		lastExploredBPMN.add(new Activity[]
						{checkApp, validateEleg, sendReject});
		lastExploredBPMN.add(new Activity[]
						{checkApp, validateEleg, inviteInterview, makeInterview, decideApplication, sendReject});
		lastExploredBPMN.add(new Activity[]{checkApp, validateEleg, inviteInterview,
						makeInterview, decideApplication, decidefee, sendAcceptance});
		
		//END FAKE EXPLORATION
		
		return lastExploredBPMN;
	}

	@Override
	public void setUnfoldedBPMN(Vector<Activity[]> theUnfoldedBusinessProcess) {
		lastExploredBPMN = theUnfoldedBusinessProcess;
	}

}
