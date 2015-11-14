package eu.learnpad.ca.analysis;


import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;
import gate.util.InvalidOffsetException;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.languagetool.Language;

public abstract class Plugin {
	
	protected static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Plugin.class);
	protected DocumentContent docContent;
	protected List<Node> listnode;
	protected Language language;
	
	
	protected  int indexofElement(String sentence, String word, Map<String, Integer> elementfinded, String split){
		String [] spliter = sentence.split(split);
		int position = 0;
		int numwordfinded = 0;
		for (int i = 0; i < spliter.length; i++) {
			int offset = 0;
			String token = spliter[i];
			if(token.equals(word)){
				numwordfinded++;
				if(!elementfinded.containsKey(token)){
					elementfinded.put(token, 1);
				}
				if(elementfinded.get(token).intValue()==numwordfinded){
					Integer I = elementfinded.get(token);
					int y  = I.intValue()+1;
					elementfinded.put(token, y);
					return position;
				}else{
					position+=token.length()+1+offset;
				}
			}else{
				position+=token.length()+1+offset;
			}
		}
		return position;
	}
	
	public void gatevsleanpadAnnotation(
			Set<gate.Annotation> setGateAnnotations,
			List<Annotation> annotations, Set<gate.Annotation> listSentenceDefected, List<Node> listnode, DocumentContent docContent,String Type, String Racc) {

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

			a.setType(Type);

			a.setRecommendation(Racc);
			annotations.add(a);

		}
	
	}
	
	
}
