package eu.learnpad.ca.analysis.non_ambiguity.syntacticambiguity.plugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.Language;

import eu.learnpad.ca.analysis.Plugin;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;

public class PragmaticAmbiguity  extends Plugin {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PragmaticAmbiguity.class);

	public PragmaticAmbiguity(Language lang,  DocumentContent docContent, List<Node> listnode){
		this.language=lang;
		this.docContent = docContent;
		this.listnode = listnode;
	}
	

	public void check(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){
		
		HashSet<String> hs = new HashSet<String>();
		hs.add("CoordAmbiguity");
		Set<gate.Annotation> SetActorUnclear = gateu.getAnnotationSet(hs);
		
		
		String rac = "The sentence is ambiguous because you are using complex combinations of \"and\" or \"or\". Clarify the sentence by introducing some commas, or by splitting it into two sentences.";
		
		String type = "Pragmatic Ambiguity";
		gatevsleanpadAnnotation(SetActorUnclear, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log,listSentence );
	}
}
