package eu.learnpad.simulator.mon.rules;

import eu.learnpad.simulator.mon.coverage.Activity;

public class RuleElements {

	
	static String header;
	
	public static String getHeader(String ruleName, String dialect) {
	
		header ="\t\t\n\n"+
				"import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.ResponseDispatcher;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.RestNotifier;\n\t\t" +
				"import eu.learnpad.simulator.mon.utils.NotifierUtils;\n\t\t" +
				"import eu.learnpad.simulator.mon.rules.DroolsRulesManager;\n\t\t" +
				"import eu.learnpad.sim.rest.event.AbstractEvent;\n\t\t" +
				"import eu.learnpad.sim.rest.event.EventType;\n\t\t" +
				"\t\tdeclare GlimpseBaseEventBPMN\n" +
				"\t\t\t@role( event )\n" +
				"\t\t\t@timestamp( timeStamp )\n" +
				"\t\tend\n\n" + 
				"\t\trule \"" + ruleName + "\"\n" +
				"\t\tno-loop true\n" +
				"\t\tsalience 20\n" +
				"\t\tdialect \"" + dialect + "\"\n\n";
		
		return header;
	}
	
	public static String getWhenClause() {
		return "\t\twhen\n";
	}
	
	public static String getThenClause(Activity[] theActivitySetToSetConsumed) {
	
		String concat = "\n\t\tthen ";
		for (int i = 0; i<theActivitySetToSetConsumed.length; i++) {
			concat = concat + "\n\t\t\t$"+ i
					+ "Event.setConsumed(true); \n\t\t\tupdate($"+ i +"Event);"
					+ "\n\t\t\tretract($"+ i +"Event);"; 
		}
		return concat;
	}
	
	public static String getThenClauseForCoverageWithLearnersScoreUpdateAndProcessStartNotification(Activity[] theActivitySetToSetConsumed, String idBPMN, String idPath) {
		String concat = "\n\t\tthen ";
		for (int i = 0; i<theActivitySetToSetConsumed.length+1; i++) {
			concat = concat + "\n\t\t\t$"+ i
					+ "Event.setConsumed(true); \n\t\t\tupdate($"+ i +"Event);"
					+ "\n\t\t\tretract($"+ i +"Event);";					
		}
		 concat = concat + "\n\t\t\t" +
			"ResponseDispatcher.saveAndNotifyLearnersScore(\"##LEARNERSINVOLVEDID##\", \"" + idBPMN + "\", drools.getRule().getName(), $"+ (theActivitySetToSetConsumed.length-1)+"Event.getEvent().timestamp);";
		return concat;
	}
		
	
	public static String getEnd() {
		return "\n\t\tend\n\n\t";
	}
	
}
