package eu.learnpad.ca.analysis.simplicity.plugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.Language;

import eu.learnpad.ca.analysis.Plugin;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;

public class ExcessiveLength extends Plugin {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ExcessiveLength.class);




	public ExcessiveLength(Language lang,  DocumentContent docContent, List<Node> listnode){
		this.language=lang;
		this.docContent = docContent;
		this.listnode = listnode;
	}
	

	public void check(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){
		
		HashSet<String> hs = new HashSet<String>();
		hs.add("Sent-Long");
		Set<gate.Annotation> SetActorUnclear = gateu.getAnnotationSet(hs);
		
		
		String rac = "Shorten the sentence. A sentence should not exceed 25 words.";
		String type = "Simplicity Excessive Length";
		gatevsleanpadAnnotation(SetActorUnclear, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log, listSentence,null,null,null );
	}
	
	

}