package eu.learnpad.simulator.mon.rules;

import eu.learnpad.simulator.mon.coverage.Activity;
import it.cnr.isti.labse.glimpse.xml.complexEventRule.ComplexEventRuleType;

public class RuleElements {

	
	static String header;
	
	public static String getHeader(String ruleName, String dialect) {
	
		header ="\n\t\t"+
				"import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.ResponseDispatcher;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.RestNotifier;\n\t\t" +
				"import eu.learnpad.simulator.mon.utils.NotifierUtils;\n\t\t" +
				"import eu.learnpad.simulator.mon.rules.DroolsRulesManager;\n\t\t" +
				"import eu.learnpad.sim.rest.event.AbstractEvent;\n\t\t" +
				"import eu.learnpad.sim.rest.event.EventType;\n\t\t" +
				"import eu.learnpad.sim.rest.event.impl.SessionScoreUpdateEvent;\n\t\t" +
				"import eu.learnpad.sim.rest.event.impl.TaskEndEvent;\n\t\t" +
				"\t\tdeclare GlimpseBaseEventBPMN\n" +
				"\t\t\t@role( event )\n" +
				"\t\t\t@timestamp( timeStamp )\n" +
				"\t\tend\n\n" + 
				"\t\trule \"" + ruleName + "##INSTANCE##\"\n" +
				"\t\tno-loop true\n" +
				"\t\tsalience 99999\n" +
				"\t\tdialect \"" + dialect + "\"\n\n";
		
		return header;
	}
	
	public static String getWhenClause() {
		return "\t\twhen\n";
	}
	
	public static String getThenClause(Activity[] theActivitySetToSetConsumed) {
	
		String concat = "\n\t\tthen ";
		for (int i = 1; i<theActivitySetToSetConsumed.length; i++) {
			concat = concat + "\n\t\t\t$"+ i
					+ "Event.setConsumed(true); \n\t\t\tupdate($"+ i +"Event);"
					+ "\n\t\t\tretract($"+ i +"Event);"; 
		}
		return concat;
	}
	
	public static String getEnd() {
		return "\n\t\tend\n\n\t";
	}

	public static String getThenClausesForSetPathsAsCompletedAndPropagateScoresToPlatformAndOR(
			Activity[] anActivitiesSet, String idBPMN, String idPath) {
		
		String concat = "\n\t\tthen ";
//		for (int i = 1; i<((anActivitiesSet.length)+1); i++) {
//			concat = concat + "\n\t\t\t$"+ i
//					+ "Event.setConsumed(true); \n\t\t\tupdate($"+ i +"Event);"
//					+ "\n\t\t\tretract($"+ i +"Event);";					
//		}
		 concat = concat + "\n\t\t\t" +
			"ResponseDispatcher.SetPathCompleted(\"##LEARNERSINVOLVEDID##\",\"" + idPath + "\", \"" + idBPMN + "\");";
		return concat;
	}
	
	public static ComplexEventRuleType ruleForSimulationEnd(String learnersID, String simulationSessionID, String idBPMN) {
		
		ComplexEventRuleType aInsert = ComplexEventRuleType.Factory.newInstance();
		aInsert.setRuleName("ENDSimulation-" + simulationSessionID);
		aInsert.setRuleType("drools");
		
		String theRule = RuleElements.getHeader("ENDSimulation", "java");
		
		theRule = theRule.replaceAll("salience 99999", "salience 99");
		theRule = theRule.replaceAll("##INSTANCE##", "-"+simulationSessionID);

		theRule += RuleElements.getWhenClause();
		
		theRule +="\t\t\t$0Event : GlimpseBaseEventBPMN(" +
				"this.isConsumed == false, this.getEvent().simulationsessionid == \"" + simulationSessionID + "\""
				+", this.getEvent.type.toString() == EventType.SIMULATION_END.toString()"
				+", this.isException == false);\n"
				+"\t\tthen\n"
				+ "\t\t\t$0Event.setConsumed(true); \n\t\t\tupdate($0Event);"
				+ "\n\t\t\tretract($0Event); \n\t\t\t" +
				"ResponseDispatcher.PropagateScores(\""+ learnersID.substring(0,learnersID.length()-1) + "\", \"" + idBPMN + "\", $0Event.getEvent().simulationsessionid );";
		
		theRule += RuleElements.getEnd();
		aInsert.setRuleBody(theRule);
		return aInsert;
	}
	
}
