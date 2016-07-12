package eu.learnpad.simulator.mon.rules.generator;

import java.util.Vector;

import eu.learnpad.simulator.mon.coverage.Activity;
import eu.learnpad.simulator.mon.coverage.Learner;
import eu.learnpad.simulator.mon.coverage.Path;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleActionListDocument;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

public interface RulesPerPath {
	//ComplexEventRuleActionListDocument generateAllPathsRules(Vector<Activity[]> theUnfoldedBusinessProcess, String idBpmn);
	Vector<Path> generateAllPaths(Vector<Activity[]> theUnfoldedBusinessProcess, String idBpmn);
	ComplexEventRuleType generateRuleForSinglePath(Activity[] anActivitiesSet, String rulesName, String idBPMN, String idPath); 
	//Vector<Path> generatePaths(ComplexEventRuleActionListDocument generatedRules, Vector<Activity[]> theUnfoldedBPMN, String theBPMNidentifier);
	Vector<Path> generatePathsRules(Vector<Path> thePaths);
	ComplexEventRuleActionListDocument instantiateRulesSetForUsersInvolved(Vector<Path> thePathsToInstantiate, Vector<Learner> usersInvolved, String sessionID);
}
