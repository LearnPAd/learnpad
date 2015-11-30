package eu.learnpad.ca.analysis.simplicity.plugin;


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



public class Juridical extends Plugin {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Juridical.class);

	public Juridical(Language lang,  DocumentContent docContent, List<Node> listnode){
		this.language=lang;
		this.docContent = docContent;
		this.listnode = listnode;
	}


	public void checkJuridicalJargon(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){


		FeatureMap fe = Factory.newFeatureMap();

		//majorType lexicalambiguity
		fe.put("minorType", "juricaljargon");//  vagueness
		fe.put("majorType", "simplicity");
		Set<gate.Annotation> jj = gateu.getAnnotationSet("Lookup" , fe);


		String rac = "The term %s is juridical jargon. Substitute %s with a more common term.";

		String type = "Simplicity Juridical Jargon";
		if(!jj.isEmpty())
			gatevsleanpadAnnotation(jj, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log ,listSentence);
	}

	
}
