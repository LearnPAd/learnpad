package eu.learnpad.simulator.mon.rulesGenerator;

import eu.learnpad.simulator.mon.coverage.Activity;

public class RuleElements {

	
	static String header;
	
	public static String getHeader(String ruleName, String dialect) {
	
		header ="\t\t"+
				"import eu.learnpad.simulator.mon.event.GlimpseBaseEventBPMN;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.ResponseDispatcher;\n\t\t" +
				"import eu.learnpad.simulator.mon.manager.RestNotifier;\n\t\t" +
				"import eu.learnpad.simulator.mon.utils.NotifierUtils;\n\t\t" +
				"import eu.learnpad.simulator.mon.rules.DroolsRulesManager;\n\n" +
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
	
	public static String getThenClauseWithLearnersScoreUpdateAndProcessStartNotification(Activity[] theActivitySetToSetConsumed, String idBPMN, int idPath) {
		String concat = "\n\t\tthen ";
		for (int i = 0; i<theActivitySetToSetConsumed.length; i++) {
			concat = concat + "\n\t\t\t$"+ i
					+ "Event.setConsumed(true); \n\t\t\tupdate($"+ i +"Event);"
					+ "\n\t\t\tretract($"+ i +"Event);";					
		}
		 concat = concat + "\n\t\t\t"
			+ "ResponseDispatcher.saveAndNotifyLearnersScore(\"##LEARNERSINVOLVEDID##\", \""+ idBPMN +"\", " +  theActivitySetToSetConsumed[0].getPath_id() + ", " + "##TASKSCORE##);";
		 //TODO: fix idPath
		return concat;
	}
		
	
	public static String getEnd() {
		return "\n\t\tend\n\n\t";
	}
	
}
