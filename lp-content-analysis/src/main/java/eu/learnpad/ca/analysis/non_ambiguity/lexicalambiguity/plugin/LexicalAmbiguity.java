package eu.learnpad.ca.analysis.non_ambiguity.lexicalambiguity.plugin;


import java.util.List;
import java.util.Set;

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


	public void checkVagueness(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){


		FeatureMap fe = Factory.newFeatureMap();

		//majorType lexicalambiguity
		fe.put("minorType", "vagueness");//  vagueness
		fe.put("majorType", "lexicalambiguity");
		Set<gate.Annotation> SetVagueness = gateu.getAnnotationSet("Lookup" , fe);


		String rac = "The term %s is vague. Remove %s or substitute it with a more unequivocal term.";

		String type = "Lexical Ambiguity Vagueness";
		if(!SetVagueness.isEmpty())
			gatevsleanpadAnnotation(SetVagueness, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log ,listSentence);
	}

	public void checkSubjective(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){


		FeatureMap fe = Factory.newFeatureMap();

		//majorType lexicalambiguity
		fe.put("minorType", "subjectivity");
		fe.put("majorType", "lexicalambiguity");
		Set<gate.Annotation> SetSubjectivity = gateu.getAnnotationSet("Lookup" , fe);



		String rac = "The term %s is subjective. Remove %s or substitute it with a more unequivocal term.";

		String type = "Lexical Ambiguity Subjectivity";
		if(!SetSubjectivity.isEmpty())
			gatevsleanpadAnnotation(SetSubjectivity, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log ,listSentence);
	}
	public void checkOptional(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){


		FeatureMap fe = Factory.newFeatureMap();

		//majorType lexicalambiguity
		fe.put("minorType", "optionality");
		fe.put("majorType", "lexicalambiguity");
		Set<gate.Annotation> SetOptionality = gateu.getAnnotationSet("Lookup" , fe);



		String rac = "The term %s is optional. Remove %s or substitute it with a more unequivocal term.";

		String type = "Lexical Ambiguity Optionality";
		if(!SetOptionality.isEmpty())
			gatevsleanpadAnnotation(SetOptionality, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log ,listSentence);
	}
}
