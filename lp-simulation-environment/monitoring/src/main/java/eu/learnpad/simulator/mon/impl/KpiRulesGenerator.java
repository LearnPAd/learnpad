package eu.learnpad.simulator.mon.impl;

import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Learner;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;

public class KpiRulesGenerator {

	public static ComplexEventRuleActionListDocument generateAll(Vector<Learner> usersInvolved, String sessionID,
			String bpmnID, Vector<Activity[]> theUnfoldedBPMN) {
		
		CaptureAndUpdateSessionScoreForUsers(usersInvolved, sessionID, bpmnID);
		
		
		return null;
	}
	
	public static ComplexEventRuleActionListDocument CaptureAndUpdateSessionScoreForUsers(Vector<Learner> usersInvolved, String sessionID, String bpmnID) {
		//TODO: other rules
		
		return null;
	}
}
