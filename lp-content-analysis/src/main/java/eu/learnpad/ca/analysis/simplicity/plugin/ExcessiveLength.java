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
import gate.util.InvalidOffsetException;

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
		gatevsleanpadAnnotation(SetActorUnclear, listannotations,listSentenceDefected,listnode,docContent,type ,rac );
	}
	
	private void gatevsleanpadAnnotation(
			Set<gate.Annotation> setGateAnnotations,
			List<Annotation> annotations, Set<gate.Annotation> listSentenceDefected, List<Node> listnode, DocumentContent docContent, String type, String rac) {

		for (gate.Annotation gateA : setGateAnnotations) {

			gate.Node gatenodestart = gateA.getStartNode();
			gate.Node gatenodeend = gateA.getEndNode();
			try{
				String sentence_gate = docContent.getContent(gatenodestart.getOffset(),gatenodeend.getOffset()).toString();
				if(!listSentenceDefected.contains(sentence_gate))
					listSentenceDefected.add(gateA);
			}catch(InvalidOffsetException e){
				log.error(e);
			}
			int initialpos = gatenodestart.getOffset().intValue();


			Node init = new Node(gatenodestart.getId(), initialpos);

			Node end = new Node(gatenodeend.getId(), gatenodeend.getOffset()
					.intValue());

			listnode.add(init);
			listnode.add(end);

			Annotation a = new Annotation();
			a.setId(gateA.getId());
			a.setEndNode(end.getId());
			a.setStartNode(init.getId());
			a.setNodeEnd(end);
			a.setNodeStart(init);

			a.setType(type);

			a.setRecommendation(rac);
			annotations.add(a);

		}

	}

}