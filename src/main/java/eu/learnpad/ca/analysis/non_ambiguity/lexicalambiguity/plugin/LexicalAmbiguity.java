package eu.learnpad.ca.analysis.non_ambiguity.lexicalambiguity.plugin;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.languagetool.Language;

import eu.learnpad.ca.analysis.Plugin;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;
import gate.Factory;
import gate.FeatureMap;



public class LexicalAmbiguity extends Plugin {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LexicalAmbiguity.class);

	public LexicalAmbiguity(Language lang,  DocumentContent docContent, List<Node> listnode){
		this.language=lang;
		this.docContent = docContent;
		this.listnode = listnode;
	}
	

	public void check(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){
		
		
		FeatureMap fe = Factory.newFeatureMap();
		
		//majorType lexicalambiguity
		fe.put("minorType", "vagueness");//  vagueness
		fe.put("majorType", "lexicalambiguity");
		Set<gate.Annotation> SetActorUnclear = gateu.getAnnotationSet("Lookup" , fe);
		
		
		String rac = "The term %s is vague. Remove %s or substitute it with a more unequivocal term.";
		String racs = "The term < t > is subjective. Remove t or substitute it with a more unequivocal term.";
		String raco = "The term < t > is optional. Remove t or substitute it with a more unequivocal term.";
		
		String type = "Lexical Ambiguity";
		gatevsleanpadAnnotation(SetActorUnclear, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log ,listSentence);
	}
}
