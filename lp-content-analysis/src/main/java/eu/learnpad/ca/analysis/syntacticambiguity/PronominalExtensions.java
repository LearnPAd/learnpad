package eu.learnpad.ca.analysis.syntacticambiguity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PronominalExtensions {

	
	
	private static Set<String> pronouns_demonstratives = new HashSet<String>(Arrays.asList("this", "that", "these", "those"));
	
	private static Set<String> pronouns_male = new HashSet<String>(Arrays.asList("he","his", "him", "himself"));
	private static Set<String> pronouns_female = new HashSet<String>(Arrays.asList("she", "her", "hers", "herself"));
	private static Set<String> pronouns_neuter = new HashSet<String>(Arrays.asList("it", "its", "itself", "they", "their", "theirs", "them", "themselves",
	          "you", "yourself", "your", "yours", "yourselves", "we", "us", "i", "me", "mine",
	          "myself", "my", "ours", "our", "ourselves"));
	
	
	
	
	
	public static Set<String> getAllPronouns() {
		Set<String> allPronouns = new HashSet<String>();
		allPronouns.addAll(pronouns_male);
		allPronouns.addAll(pronouns_female);
		allPronouns.addAll(pronouns_neuter);
		//allPronouns.addAll(pronouns_demonstratives);
		return allPronouns;
	}
	
	
	
}
