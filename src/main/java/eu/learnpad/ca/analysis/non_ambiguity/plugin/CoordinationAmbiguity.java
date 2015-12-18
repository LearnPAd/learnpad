package eu.learnpad.ca.analysis.non_ambiguity.plugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.languagetool.Language;
import org.languagetool.language.Italian;

import eu.learnpad.ca.analysis.Plugin;
import eu.learnpad.ca.analysis.localizzation.Messages;
import eu.learnpad.ca.gate.GateThread;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;

public class CoordinationAmbiguity extends Plugin {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CoordinationAmbiguity.class);

	public CoordinationAmbiguity(Language lang,  DocumentContent docContent, List<Node> listnode){
		this.language=lang;
		this.docContent = docContent;
		this.listnode = listnode;
	}


	public void check(GateThread gateu, List<Annotation> listannotations, Set<gate.Annotation> listSentenceDefected,Set<gate.Annotation> listSentence){
		if(!(language instanceof Italian)){
			HashSet<String> hs = new HashSet<String>();
			hs.add("CoordAmbiguity"); //$NON-NLS-1$
			Set<gate.Annotation> SetActorUnclear = gateu.getAnnotationSet(hs);


			String rac = Messages.getString("CoordinationAmbiguity.Recomandation", language); //$NON-NLS-1$

			String type = "Coordination Ambiguity"; //$NON-NLS-1$
			gatevsleanpadAnnotation(SetActorUnclear, listannotations,listSentenceDefected,listnode,docContent,type ,rac,log,listSentence );
		}
	}


}
