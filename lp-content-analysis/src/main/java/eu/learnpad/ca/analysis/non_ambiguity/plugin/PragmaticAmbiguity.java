package eu.learnpad.ca.analysis.non_ambiguity.plugin;

/*import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.Language;


import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;*/

import eu.learnpad.ca.analysis.Plugin;

public class PragmaticAmbiguity  extends Plugin {

/*	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PragmaticAmbiguity.class);

	public PragmaticAmbiguity(Language lang,  DocumentContent docContent, List<Node> listnode){
		this.language=lang;
		this.docContent = docContent;
		this.listnode = listnode;
	}
	

	public void check(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){
		
		HashSet<String> hs = new HashSet<String>();
		hs.add("SpecifiedName");
		Set<gate.Annotation> SetActorUnclear = gateu.getAnnotationSet(hs);
		
		
		String rac = "The term %s is ambiguous. Please specify it more precisely.";
		String type = "Pragmatic Ambiguity";
		gatevsleanpadAnnotation(SetActorUnclear, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log,listSentence );
	}*/
}
