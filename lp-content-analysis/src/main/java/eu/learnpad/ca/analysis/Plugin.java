package eu.learnpad.ca.analysis;


import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;
import gate.util.InvalidOffsetException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.languagetool.Language;
import org.apache.log4j.Logger;
public abstract class Plugin {


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
			List<Annotation> annotations, Set<gate.Annotation> listSentenceDefected, 
			List<Node> listnode, DocumentContent docContent,String Type, String Racc,Logger log, 
			Set<gate.Annotation> listSentence, String featureA, String featureB, String featureC) {


		for (gate.Annotation gateA : setGateAnnotations) {

			gate.Node gatenodestart = gateA.getStartNode();
			gate.Node gatenodeend = gateA.getEndNode();
			String datafeatureA = "";
			String datafeatureB = "";
			String datafeatureC = "";

			if(featureA!=null)
				if(gateA.getFeatures().containsKey(featureA)){
					datafeatureA = gateA.getFeatures().get(featureA).toString();
					if(featureB!=null)
						datafeatureB = gateA.getFeatures().get(featureB).toString();
					if(featureC!=null)
						datafeatureC = gateA.getFeatures().get(featureC).toString();


				}



			Set<gate.Annotation> sentencedef = getSentenceFromNode(listSentence,gatenodestart,gatenodeend);
			for(gate.Annotation def : sentencedef){
				if(!listSentenceDefected.contains(def))
					listSentenceDefected.add(def);
			}
			int initialpos = gatenodestart.getOffset().intValue();


			Node init = new Node(gatenodestart.getId(), initialpos);

			Node end = new Node(gatenodeend.getId(), gatenodeend.getOffset()
					.intValue());

			

			Annotation a = new Annotation();
			a.setId(gateA.getId());
			a.setEndNode(end.getId());
			a.setStartNode(init.getId());
			a.setNodeEnd(end);
			a.setNodeStart(init);

			a.setType(Type);
			String recc = null;
			try{
				String sentence_gate = docContent.getContent(gatenodestart.getOffset(),gatenodeend.getOffset()).toString();
				//log.trace(sentence_gate);
				String[] split = sentence_gate.split(" ");
				if(Character.isUpperCase(sentence_gate.charAt(0)) && split.length<2){
					log.trace("Element "+sentence_gate+" cutted");
					continue;
				}
				listnode.add(init);
				listnode.add(end);
				if(datafeatureA.length()>1)
					recc = String.format(Racc,datafeatureA,datafeatureB,datafeatureC);
				else
					recc = String.format(Racc, sentence_gate, sentence_gate);

			}catch(InvalidOffsetException e){
				log.error(e);
			}

			a.setRecommendation(recc);

			annotations.add(a);

		}

	}

	public Set<gate.Annotation> getSentenceFromNode(Set<gate.Annotation> listSentence,gate.Node gatenodestart, gate.Node gatenodeend){
		Set<gate.Annotation> sent = new HashSet<gate.Annotation>();
		gate.Annotation sentenceorec = null;
		for(gate.Annotation sentence : listSentence){
			gate.Node startSentence = sentence.getStartNode();
			gate.Node endSentence = sentence.getEndNode();
			/*String nod=null;
			String sentence_gate=null;
			try{
				 nod = docContent.getContent(gatenodestart.getOffset(),gatenodeend.getOffset()).toString();
				 sentence_gate = docContent.getContent(startSentence.getOffset(),endSentence.getOffset()).toString();

			}catch(InvalidOffsetException e){

			}*/


			boolean initial = startSentence.getOffset()-gatenodestart.getOffset()<=0;
			boolean end = endSentence.getOffset()-gatenodeend.getOffset()>=-1;
			if(initial & end){
				sent.add(sentence);
				sentenceorec = null;
				break;
			}else{
				if(initial){
					if(gatenodestart.getOffset()<=endSentence.getOffset()){
						sentenceorec=sentence;
					}
				}else{
					if(end & sentenceorec!=null){
						if(gatenodeend.getOffset()>=startSentence.getOffset()){
							sent.add(sentenceorec);
							sent.add(sentence);
							sentenceorec = null;
							break;
						}
					}
				}
			}

		}

		return sent;
	}

}
