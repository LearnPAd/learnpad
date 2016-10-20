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
					"this.isConsumed == false, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
					+", this.getEvent.type.toString() == EventType.SIMULATION_START.toString()"
					+", this.isException == false);\n";
		}
		
		for(int j = 0; j<anActivitiesSet.length; j++) {				
			concat +="\t\t\t$"+((j)+1)+"Event : GlimpseBaseEventBPMN(" +
					"this.isConsumed == false, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
					+", this.getEvent.type.toString() == EventType.TASK_END.toString()"
					+", this.getTaskEndEvent().completingUser.toString() == \"##USERSINVOLVEDTASKENDIDS##\""
					+", this.isException == false"
					+", this.getTaskEndEvent().taskartifactid.toString() == \"" + anActivitiesSet[j].getTaskArtifactID() +"\""
					+", this after $" + (j) + "Event);\n";
	}

//	concat +="\t\t\t$"+((anActivitiesSet.length)+1)+"Event : GlimpseBaseEventBPMN(" +
//		"this.isConsumed == true, this.getEvent().simulationsessionid == \"##SESSIONIDPLACEHOLDER##\""
//		+", this.getEvent.type.toString() == EventType.SIMULATION_END.toString()"
//		+", this.isException == false"
//		+", this after $" + (anActivitiesSet.length) + "Event);\n";
			
		aInsert.setRuleBody(RuleElements.getHeader(aInsert.getRuleName(),  "java") +
				RuleElements.getWhenClause() + 
				concat + 
				RuleElements.getThenClausesForSetPathsAsCompletedAndPropagateScoresToPlatformAndOR(anActivitiesSet, idBPMN, idPath) +
				//RuleElements.getThenClauseForCoverageWithLearnersScoreUpdateAndProcessStartNotification(anActivitiesSet, idBPMN, idPath) +
				RuleElements.getEnd());
		return aInsert;
	}

	@Override
	public ComplexEventRuleActionListDocument instantiateRulesSetForUsersInvolved(
								Vector<Path> thePathsToInstantiate,
								Vector<Learner> usersInvolved, String sessionID) {
		
		rulesToLoad = ComplexEventRuleActionListDocument.Factory.newInstance();
		String updatedPath = "";
		ComplexEventRuleType rule;
		List<ComplexEventRuleType> preparedRules = new ArrayList<ComplexEventRuleType>();
		String learnersList = "";
		try {
			for (int j = 1; j<usersInvolved.size()+1; j++) {
				learnersList += usersInvolved.get(j-1).getId() + ",";
			}
			for (int j = 1; j<usersInvolved.size()+1; j++) {
				for (int i = 0; i<thePathsToInstantiate.size(); i++) {
					updatedPath = thePathsToInstantiate.get(i).getPathRule().replaceAll("##SESSIONIDPLACEHOLDER##", sessionID);
					updatedPath = updatedPath.replaceAll("##USERSINVOLVEDSESSIONSCOREIDS##", usersInvolved.get(j-1).getId());
					updatedPath = updatedPath.replaceAll("##USERSINVOLVEDTASKENDIDS##", usersInvolved.get(j-1).getId());
					updatedPath = updatedPath.replaceAll("##LEARNERSINVOLVEDID##", usersInvolved.get(j-1).getId());
//					updatedPath = updatedPath.replaceAll("##ALLLEARNERSINVOLVEDIDS##", learnersList.substring(0, learnersList.length()-1));
					rule = ComplexEventRuleType.Factory.parse(updatedPath);
					preparedRules.add(rule);	
					
				}
			}
			preparedRules.add(RuleElements.ruleForSimulationEnd(learnersList, sessionID, thePathsToInstantiate.get(0).getIdBpmn()));			
		} catch (XmlException e) {
			e.printStackTrace();
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