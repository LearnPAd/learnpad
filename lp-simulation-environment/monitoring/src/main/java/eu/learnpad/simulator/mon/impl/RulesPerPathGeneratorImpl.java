package eu.learnpad.simulator.mon.impl;

import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.xmlbeans.XmlException;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import eu.learnpad.simulator.mon.rules.RuleElements;
import eu.learnpad.simulator.mon.rules.generator.RulesPerPath;
import eu.learnpad.simulator.mon.utils.ComputeLearnerScore;
import eu.learnpad.simulator.mon.utils.DebugMessages;

public class RulesPerPathGeneratorImpl implements RulesPerPath {

	ComplexEventRuleActionListDocument rulesToLoad;
	
	
	@Override
	public Vector<Path> generateAllPaths(
			Vector<Activity[]> theUnfoldedBusinessProcess, String idBpmn) {
		
		Vector<Path> thePathOfTheBPMN = new Vector<Path>();
		
		for (int i =0; i<theUnfoldedBusinessProcess.size();i++) {
					
			Path theCompletePathObject = new Path(idBpmn + "-" + i, idBpmn, ComputeLearnerScore.absoluteSession(theUnfoldedBusinessProcess.get(i)),
													"", theUnfoldedBusinessProcess.get(i));
			thePathOfTheBPMN.add(theCompletePathObject);
		}	
		return thePathOfTheBPMN;
	}
	
	@Override
	public Vector<Path> generatePathsRules(Vector<Path> thePaths) {

		Vector<Path> local = thePaths;
		ComplexEventRuleType aInsert = ComplexEventRuleType.Factory.newInstance();
		
		for(int i = 0; i<local.size(); i++) {
			
			ComplexEventRuleType generated = generateRuleForSinglePath(local.get(i).getActivities(), local.get(i).getId(), local.get(i).getIdBpmn().toString(), local.get(i).getId());
			aInsert.setRuleBody(generated.getRuleBody());
			aInsert.setRuleName(generated.getRuleName());
			aInsert.setRuleType(generated.getRuleType());
			
			local.get(i).setPathRule(aInsert.xmlText());
		}
		return local;
	}
	
	@Override
	public ComplexEventRuleType generateRuleForSinglePath(
			Activity[] anActivitiesSet, String rulesName, String idBPMN, String idPath) {

		ComplexEventRuleType aInsert = ComplexEventRuleType.Factory.newInstance();
		aInsert.setRuleName(rulesName);
		aInsert.setRuleType("drools");
				
		String concat = "";
		
		
		if (anActivitiesSet.length > 0) {
			concat = "\t\t\t$0Event : GlimpseBaseEventBPMN("+
					"this.isConsumed == true, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
					+", this.getEvent.type.toString() == EventType.SIMULATION_START.toString()"
					+", this.isException == false);\n";
		}
		
		for(int j = 0; j<anActivitiesSet.length; j++) {				
			concat +="\t\t\t$"+((j)+1)+"Event : GlimpseBaseEventBPMN(" +
					"this.isConsumed == true, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
					+", this.getEvent.type.toString() == EventType.TASK_END.toString()"
					+", this.getTaskEndEvent().completingUser.toString() == \"##USERSINVOLVEDTASKENDIDS##\""
					+", this.isException == false"
					+", this.getTaskEndEvent().taskartifactid.toString() == \"" + anActivitiesSet[j].getTaskArtifactID() +"\""
					+", this after $" + (j) + "Event);\n";
	}

	concat +="\t\t\t$"+((anActivitiesSet.length)+1)+"Event : GlimpseBaseEventBPMN(" +
		"this.isConsumed == true, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
		+", this.getEvent.type.toString() == EventType.SIMULATION_END.toString()"
		+", this.isException == false"
		+", this after $" + (anActivitiesSet.length) + "Event);\n";
			
		
		
//			for(int j = 0; j<anActivitiesSet.length; j++) {				
//					concat +="\t\t\t$"+((2*j)+1)+"Event : GlimpseBaseEventBPMN(" +
//							"this.isConsumed == false, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
//							+", this.getEvent.type == EventType.SESSION_SCORE_UPDATE.toString()"
//							+", this.getUserID == \"##USERSINVOLVEDSESSIONSCOREIDS##\""
//							+", this.isException == false"
//							+", this after $" + (2*j) + "Event);\n";
//						
//					concat +="\t\t\t$"+((2*j)+2)+"Event : GlimpseBaseEventBPMN(" +
//							"this.isConsumed == false, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
//							+", this.getEvent.type == EventType.TASK_END.toString()"
//							+", this.getTaskEndEvent().completingUser.toString() == \"##USERSINVOLVEDTASKENDIDS##\""
//							+", this.isException == false"
//							+", this.getEventName == \"" + anActivitiesSet[j].getName() +"\""
//							+", this after $" + ((2*j)+1) + "Event);\n";
//			}
//
//			concat +="\t\t\t$"+((anActivitiesSet.length*2)+1)+"Event : GlimpseBaseEventBPMN(" +
//				"this.isConsumed == false, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
//				+", this.getEvent.type == EventType.SIMULATION_END.toString()"
//				+", this.isException == false"
//				+", this after $" + (anActivitiesSet.length*2) + "Event);\n";

		aInsert.setRuleBody(RuleElements.getHeader(aInsert.getRuleName(),  "java") +
				RuleElements.getWhenClause() + 
				concat + 
				RuleElements.getThenClausesForSetPathsAsCompletedAndPropagateScoresToPlatformAndOR(anActivitiesSet, idBPMN, idPath) +
				//RuleElements.getThenClauseForCoverageWithLearnersScoreUpdateAndProcessStartNotification(anActivitiesSet, idBPMN, idPath) +
				RuleElements.getEnd());
		return aInsert;
	}

/*	@Override
	public Vector<Path> generatePaths(ComplexEventRuleActionListDocument generatedRules, Vector<Activity[]> theUnfoldedBPMN, String theBPMNidentifier) {
		
		Vector<Path> thePathOfTheBPMN = new Vector<Path>();
		
		for (int i =0; i<theUnfoldedBPMN.size();i++) {
					
			Path theCompletePathObject = new Path(theUnfoldedBPMN.get(i) + "-" + i, theBPMNidentifier, ComputeScore.absoluteSession(theUnfoldedBPMN.get(i)),
													generatedRules.getComplexEventRuleActionList().getInsertArray()[i].toString(), theUnfoldedBPMN.get(i));
			thePathOfTheBPMN.add(theCompletePathObject);
		}	
		return thePathOfTheBPMN;
	}*/
	
	@Override
	public ComplexEventRuleActionListDocument instantiateRulesSetForUsersInvolved(
								Vector<Path> thePathsToInstantiate,
								Vector<Learner> usersInvolved, String sessionID) {
		
		rulesToLoad = ComplexEventRuleActionListDocument.Factory.newInstance();
		String updatedPath;
		List<ComplexEventRuleType> preparedRules = new ArrayList<ComplexEventRuleType>();
		
		for (int j = 1; j<usersInvolved.size()+1; j++) {
			
			for (int i = 0; i<thePathsToInstantiate.size(); i++) {
				updatedPath = thePathsToInstantiate.get(i).getPathRule().replaceAll("##SESSIONIDPLACEHOLDER##", sessionID);
				updatedPath = updatedPath.replaceAll("##USERSINVOLVEDSESSIONSCOREIDS##", usersInvolved.get(j-1).getId());
				updatedPath = updatedPath.replaceAll("##USERSINVOLVEDTASKENDIDS##", usersInvolved.get(j-1).getId());
				updatedPath = updatedPath.replaceAll("##LEARNERSINVOLVEDID##", usersInvolved.get(j-1).getId());
				try {
					ComplexEventRuleType rule = ComplexEventRuleType.Factory.parse(updatedPath);
					preparedRules.add(rule);
					
				} catch (XmlException e) {
					e.printStackTrace();
				}
			}
		}
		for (int i = 0; i<preparedRules.size(); i++) {
			preparedRules.get(i).setRuleName(preparedRules.get(i).getRuleName()+"-"+i);
			preparedRules.get(i).setRuleBody(preparedRules.get(i).getRuleBody().replaceAll("##INSTANCE##", "-"+i));
		}
		ComplexEventRuleType[] rules = preparedRules.toArray(new ComplexEventRuleType[preparedRules.size()]);
		rulesToLoad.addNewComplexEventRuleActionList().setInsertArray(rules);
		DebugMessages.println(TimeStamp.getCurrentTime(),this.getClass().getCanonicalName(),rulesToLoad.xmlText());
		return rulesToLoad;
	}
}
		
//		ComplexEventRuleType[] rules = new ComplexEventRuleType[thePathsToInstantiate.size()];
//		
//		for (int i = 0; i<thePathsToInstantiate.size(); i++) {
//			
//			updatedPath = thePathsToInstantiate.get(i).getPathRule().replaceAll("##SESSIONIDPLACEHOLDER##", sessionID);
//			
//			String usersInvolvedTaskEndText = "";
//			String usersInvolvedTaskEndList = "";
//			
//			String usersInvolvedSSUpdateText = "";
//			String usersInvolvedSSUpdateList = "";
//			
//			if (usersInvolved.size() > 1) {
//				for (int j=0; j< usersInvolved.size()-1;j++) {
//					usersInvolvedTaskEndText += usersInvolved.get(j).getId() + "\" || this.getTaskEndEvent().completingUser.toString() == \"";
//					usersInvolvedTaskEndList += usersInvolved.get(j).getId() + ","; 
//					
//					usersInvolvedSSUpdateText += usersInvolved.get(j).getId() + "\" || this.getSessionScoreUpdateEvent().user.toString() == \"";
//					usersInvolvedSSUpdateList += usersInvolved.get(j).getId() + ","; 
//				}
//				usersInvolvedTaskEndText += usersInvolved.get(usersInvolved.size()-1).getId();
//				usersInvolvedTaskEndList +=usersInvolved.get(usersInvolved.size()-1).getId();
//				
//				usersInvolvedSSUpdateText += usersInvolved.get(usersInvolved.size()-1).getId();
//				usersInvolvedSSUpdateList +=usersInvolved.get(usersInvolved.size()-1).getId();
//			}
//			else {
//				usersInvolvedTaskEndText = usersInvolved.get(0).getId();				
//				usersInvolvedTaskEndList = usersInvolved.get(0).getId() + "\");}}";
//
//				usersInvolvedSSUpdateText = usersInvolved.get(0).getId();				
//				usersInvolvedSSUpdateList = usersInvolved.get(0).getId() + "\");}}";
//			}
//			
//
//			updatedPath = updatedPath.replaceAll("##USERSINVOLVEDSESSIONSCOREIDS##", usersInvolvedSSUpdateText);
//			updatedPath = updatedPath.replaceAll("##USERSINVOLVEDTASKENDIDS##", usersInvolvedTaskEndText);
//			//updatedPath = updatedPath.replaceAll("##LEARNERSINVOLVEDID##", usersInvolvedList);
//			
//
//			
//			try {
//				
//				ComplexEventRuleType rule = ComplexEventRuleType.Factory.parse(updatedPath);
//				rules[i]= rule;
//			} catch (XmlException e) {
//				e.printStackTrace();
//			}
//		}
//		try {
//			ComplexEventRuleType ruleExtras = ComplexEventRuleType.Factory.parse(generateUpdatePathRule(sessionID, thePathsToInstantiate.get(0).getIdBpmn()));
//			rules[thePathsToInstantiate.size()]= ruleExtras;
//		} catch (XmlException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		rulesToLoad.addNewComplexEventRuleActionList().setInsertArray(rules);
//		return rulesToLoad;
//	}

//	private String generateUpdatePathRule(String sessionID, String idBpmn) {
//	String theRule = 
//			
//			"import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;\n"+
//	"import eu.learnpad.simulator.mon.manager.ResponseDispatcher;\n"+
//	"import eu.learnpad.simulator.mon.manager.RestNotifier;\n"+
//	"import eu.learnpad.simulator.mon.utils.NotifierUtils;\n"+
//	"import eu.learnpad.simulator.mon.rules.DroolsRulesManager;\n"+
//	"import eu.learnpad.sim.rest.event.AbstractEvent;\n"+
//	"import eu.learnpad.sim.rest.event.EventType;\n"+
//	"import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;\n"+
//	"import eu.learnpad.sim.rest.event.impl.TaskEndEvent;\n"+
//	"		declare GlimpseBaseEventBPMN\n"+
//	"	@role( event )\n"+
//	"	@timestamp( timeStamp )\n"+
//	"end\n"+
//	"rule \""+ idBpmn + "extras\"\n"+
//	"no-loop true"+
//	"salience 20"+
//	"dialect \"java\"\n"+
//
//	"when\n"+
//	"	$0Event : GlimpseBaseEventBPMN(this.isConsumed == false, this.getEvent().simulationsessionid == \""+ sessionID + "\", this.getEvent.type == EventType.SESSION_SCORE_UPDATE.toString(), this.isException == false);"+
//	"then\n"+ 
//		"$0Event.setConsumed(true);\n"+ 
//		"update($0Event);\n"+
//		"retract($0Event);\n"+
//		"ResponseDispatcher.updateLearnerScore((SessionScoreUpdateEvent)$0Event.getEvent().user, \""+idBpmn +"\", (SessionScoreUpdateEvent)$0Event.getEvent());\n"+
//	"end";
//		return theRule;
//	}
//}
