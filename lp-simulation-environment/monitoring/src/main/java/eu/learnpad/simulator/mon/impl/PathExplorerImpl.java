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
		
		Activity assessApplication = new Activity("Assess Application", "Assess Application", "obj.27830", kpiExample, 1.0f);
		Activity sendNotAmmissible = new Activity("Send Communication of non-admissible instance", "Send Communication of non-admissible instance", "obj.27812", kpiExample, 1.0f);
		Activity checkApplication = new Activity("Check Application", "Check Application", "obj.27782", kpiExample, 1.0f);
		Activity requestAmendment = new Activity("Request Amendment", "Request Amendment", "obj.29013", kpiExample, 1.0f);
		Activity checkAmendment = new Activity("Check Amendment", "Check Amendment", "obj.27833", kpiExample, 1.0f);	
		Activity sendAuthorization = new Activity("Send Authorization Document", "Send Authorization Document", "obj.27788", kpiExample, 1.0f);
		Activity manageInhibition = new Activity("Manage Inhibition", "Manage Inhibition", "obj.27839", kpiExample, 1.0f);
		Activity activateServiceConference = new Activity("Activate Service Conference", "Activate Service Conference", "obj.27987", kpiExample, 1.0f);
		Activity sendInstanceToThirdParties = new Activity("Send Instance to Third Party", "Send Instance to Third Party", "obj.27921", kpiExample, 1.0f);
		Activity receiveConfirmation = new Activity("Receive Confirmation", "Receive Confirmation", "obj.27990", kpiExample, 1.0f);
		
		
//		Activity serviceConfInv = new Activity("Service Conference Invitation","Service Conference Invitation", "usertask1", kpiExample, 1.0f);
//		Activity putStamps = new Activity("Putting Stamps on the received documentation", "Putting Stamps on the received documentation", "usertask2",kpiExample, 1.0f);
//		Activity checkProvided = new Activity("Check Provided Documentation","Check Provided Documentation","usertask4", kpiExample, 1.0f);
//		Activity takeADecision = new Activity("Take a decision","Take a decision","usertask8", kpiExample, 1.0f);	
//		Activity provideOp = new Activity("Provide Opinion","Provide Opinion", "usertask14",kpiExample, 1.0f);
//		Activity receivedOpinion = new Activity("Received Opinion Evaluation","Received Opinion Evaluation","taskArtifactID6", kpiExample, 1.0f);
//		Activity provideAuthUnder = new Activity("Provide Authorization Under Condition","Provide Authorization Under Condition","usertask19", kpiExample, 1.0f);
//		Activity provideAuth = new Activity("Provide Authorization","Provide Authorization","usertask18", kpiExample, 1.0f);
//		Activity provideFinal = new Activity("Provide Final Report","Provide Final Report","usertask22", kpiExample, 1.0f);
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, sendNotAmmissible});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, requestAmendment, checkAmendment, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, requestAmendment, checkAmendment, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, activateServiceConference, requestAmendment, checkAmendment, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, activateServiceConference, requestAmendment, checkAmendment, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, activateServiceConference, requestAmendment, checkAmendment, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, activateServiceConference, requestAmendment, checkAmendment, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, activateServiceConference, receiveConfirmation, requestAmendment, checkAmendment, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, activateServiceConference, receiveConfirmation, requestAmendment, checkAmendment, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, activateServiceConference, receiveConfirmation, requestAmendment, checkAmendment, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, receiveConfirmation, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, receiveConfirmation, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, activateServiceConference, receiveConfirmation, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, activateServiceConference, receiveConfirmation, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, receiveConfirmation, activateServiceConference, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, receiveConfirmation, activateServiceConference, manageInhibition});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, receiveConfirmation, activateServiceConference, requestAmendment, checkAmendment, sendAuthorization});
		
		lastExploredBPMN.add(new Activity[]
				{assessApplication, checkApplication, sendInstanceToThirdParties, receiveConfirmation, activateServiceConference, requestAmendment, checkAmendment, manageInhibition});
		

		//END FAKE EXPLORATION
		
		return lastExploredBPMN;
	}

	@Override
	public void setUnfoldedBPMN(Vector<Activity[]> theUnfoldedBusinessProcess) {
		lastExploredBPMN = theUnfoldedBusinessProcess;
	}

}
