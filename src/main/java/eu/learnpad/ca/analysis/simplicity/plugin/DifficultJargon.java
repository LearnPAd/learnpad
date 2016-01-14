package eu.learnpad.ca.analysis.simplicity.plugin;



import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;

import eu.learnpad.ca.analysis.Plugin;
import eu.learnpad.ca.analysis.localizzation.Messages;
import eu.learnpad.ca.analysis.simplicity.plugin.utils.AlternativeTerm;
import eu.learnpad.ca.analysis.simplicity.plugin.utils.AlternativeTermSet;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;
import gate.util.InvalidOffsetException;


public class DifficultJargon extends Plugin { 

	protected static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DifficultJargon.class);
	
	private AlternativeTermSet alternativetermset;
	//private Language language;
	

	public DifficultJargon(Language lang, DocumentContent docContent, List<Node> listnode) {
		//this.language=lang;
		alternativetermset  = readAlternativeTerms(lang);
		this.docContent=docContent;
		this.listnode = listnode;
	}




	public void checkUnclearAcronym(Set<gate.Annotation> listsentence, Set<gate.Annotation> listSentenceDefected, List<Annotation> listannotations) {
		int id = 900_000;
		for (gate.Annotation sentence_gate : listsentence) {


			gate.Node gatenodestart = sentence_gate.getStartNode();
			gate.Node gatenodeend =  sentence_gate.getEndNode();
			try{

				DocumentContent sentence = docContent.getContent(gatenodestart.getOffset(),gatenodeend.getOffset());

				int len = listannotations.size();
				id= insertdefectannotationsentence(sentence.toString(), id, listannotations,gatenodestart.getOffset().intValue());
				if(listannotations.size()>len){
				  if(!listSentenceDefected.contains(sentence_gate))
					  listSentenceDefected.add(sentence_gate);
				}

			}catch(InvalidOffsetException e){
				log.debug(e);
			}

		}
		

	}



	private AlternativeTermSet readAlternativeTerms(Language lang){
		InputStream is = null;
		if(lang instanceof BritishEnglish | lang instanceof AmericanEnglish){
			is = DifficultJargon.class.getClassLoader().getResourceAsStream("AlternativeTermSet_EnglishLatin.xml"); //$NON-NLS-1$

		}else
			if(lang instanceof Italian){
				is = DifficultJargon.class.getClassLoader().getResourceAsStream("AlternativeTermSet_EnglishLatin.xml"); //$NON-NLS-1$

			}

		assert is!=null;

		try {
			JAXBContext jaxbContexti = JAXBContext.newInstance(AlternativeTermSet.class);


			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			AlternativeTermSet atSet = (AlternativeTermSet) jaxbUnmarshaller1.unmarshal(is);
			return atSet;
		} catch (JAXBException e) {
			log.error(e);
			return null;
		}


	}





	private int insertdefectannotationsentence(String sentence,
			int nodeid, List<Annotation> annotations, int offset) {
		List<AlternativeTerm> listAltTermSet = alternativetermset.getAlternativeterms();

		String [] spliter = sentence.split("[\\W]"); //$NON-NLS-1$
		Map<String, Integer> elementfinded = new HashMap<String, Integer>();
		int precedentposition=0;

		for (int i = 0; i < spliter.length; i++) {

			String token = spliter[i];
			AlternativeTerm tmptoken = new AlternativeTerm(token);
			if(listAltTermSet.contains(tmptoken)){
				int initialpos = indexofElement(sentence,token,elementfinded,"[\\W]"); //$NON-NLS-1$
				int finalpos = initialpos+token.length();
				if(precedentposition>initialpos){
					//initialpos = sentence.lastIndexOf(token);
					//finalpos = initialpos+token.length();
					System.out.println();
					log.error("token not find"); //$NON-NLS-1$
				}
				//String stringap = sentence.substring(precedentposition, initialpos);
				
				precedentposition=finalpos;
				nodeid++;
				Node init= new Node(nodeid,initialpos+offset);
				nodeid++;
				Node end= new Node(nodeid,finalpos+offset);
				listnode.add(init);
				listnode.add(end);

				Annotation a = new Annotation();
				a.setId(nodeid);
				a.setEndNode(end.getId());
				a.setStartNode(init.getId());
				a.setNodeEnd(end);
				a.setNodeStart(init);
				a.setType("Simplicity Difficult Jargon"); //$NON-NLS-1$

				String suggestion = listAltTermSet.get(listAltTermSet.indexOf(tmptoken)).getSuggestion();
				String recomandation = String.format(Messages.getString("DifficultJargon.Recomandation", language),tmptoken.getWord(),suggestion); //$NON-NLS-1$
				a.setRecommendation(recomandation);
				annotations.add(a);

			}
		}
		/*if(precedentposition<sentence.length()){
			String stringap = sentence.substring(precedentposition, sentence.length());
			//c.setContent(stringap);
		}
		if(annotations.size()==0){
			c.setContent(sentence);
		}*/

		return nodeid;

	}

	

}