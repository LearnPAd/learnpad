package eu.learnpad.simulator.mon.rulesGenerator;

public class RuleElements {

	
	static String header;
	
	public static String getHeader(String ruleName, String dialect) {
	
		header ="\t\timport eu.learnpad.monitoring.glimpse.event.GlimpseBaseEventBPMN;\n\t\t" +
				"import eu.learnpad.monitoring.glimpse.manager.ResponseDispatcher;\n\t\t" +
				"import eu.learnpad.monitoring.glimpse.utils.NotifierUtils;\n\t\t" +
				"import eu.learnpad.monitoring.glimpse.rules.DroolsRulesManager;\n\n" +
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
	
	public static String getThenClause() {
		return "\n\t\tthen \n\t\t\t$0Event.setConsumed(true); \n\t\t\tupdate($0Event);";
	}
	
	public static String getEnd() {
		return "\n\t\tend\n\n\t";
	}
	
}
