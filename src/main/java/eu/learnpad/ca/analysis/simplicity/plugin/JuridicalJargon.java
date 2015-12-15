package eu.learnpad.ca.analysis.simplicity.plugin;


import java.io.InputStream;
import java.util.ArrayList;
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
import eu.learnpad.ca.analysis.simplicity.juridicaljargon.JuridaljargonSet;
import eu.learnpad.ca.analysis.simplicity.juridicaljargon.Juridicaljargon;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Node;
import gate.DocumentContent;
import gate.util.InvalidOffsetException;
@Deprecated
public class JuridicalJargon extends Plugin{


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(JuridicalJargon.class);



	private JuridaljargonSet juridaljargonSet;
	private DocumentContent docContent;
	 List<Node> listnode;


	public JuridicalJargon(Language lang,  DocumentContent docContent, List<Node> listnode){
		juridaljargonSet = readJJ(lang);
		this.docContent=docContent;
		this.listnode=listnode;
	}


	public List<Annotation> checkJJ(Set<gate.Annotation> listsentence, Set<gate.Annotation> listSentenceDefected) {
		List<Annotation> annotations =new ArrayList<Annotation>();
		int id = 100_000;

		for (gate.Annotation sentence_gate : listsentence) {


			gate.Node gatenodestart = sentence_gate.getStartNode();
			gate.Node gatenodeend =  sentence_gate.getEndNode();
			try{

				DocumentContent sentence = docContent.getContent(gatenodestart.getOffset(),gatenodeend.getOffset());

				int len = annotations.size();
				id = checkdefect(sentence.toString(),id,annotations,gatenodestart.getOffset().intValue());
				if(annotations.size()>len){
					if(!listSentenceDefected.contains(sentence_gate))
						listSentenceDefected.add(sentence_gate);
				}

			}catch(InvalidOffsetException e){
				log.debug(e);
			}

		}
		return annotations;
	}

	private int checkdefect(String sentence,int nodeid,List<Annotation> annotations, int offset){
		List<Juridicaljargon> Listjj = juridaljargonSet.getJuridicaljargon();
		//StringTokenizer tokenizer = new StringTokenizer(sentence);
		String [] spliter = sentence.split("[\\s]");
		Map<String, Integer> elementfinded = new HashMap<String, Integer>();
		int precedentposition=0;

		/*while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();*/
		for (int i = 0; i < spliter.length; i++) {

			String token = spliter[i];
			if(Listjj.contains(new Juridicaljargon(token))){

				int initialpos = indexofElement(sentence,token,elementfinded,"[\\s]");//sentence.indexOf(token);
				int finalpos = initialpos+token.length();
				if(precedentposition>initialpos){
					initialpos = sentence.lastIndexOf(token);
					log.error("problemi con indici");
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
				a.setType("Juridical Jargon");
				a.setRecommendation("The term "+token+" is juridical jargon. Substitute "+token+" with a more common term.");
				annotations.add(a);

			}
		}
		
		return nodeid;

	}




	private JuridaljargonSet readJJ(Language lang){
		InputStream is = null;
		if(lang instanceof BritishEnglish | lang instanceof AmericanEnglish){
			is = JuridicalJargon.class.getClassLoader().getResourceAsStream("JuridicalJargon_EnglishLatin.xml");

		}else
			if(lang instanceof Italian){
				is = JuridicalJargon.class.getClassLoader().getResourceAsStream("JuridicalJargon_EnglishLatin.xml");

			}

		assert is!=null;

		try {
			JAXBContext jaxbContexti = JAXBContext.newInstance(JuridaljargonSet.class);


			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			JuridaljargonSet jjSet = (JuridaljargonSet) jaxbUnmarshaller1.unmarshal(is);
			return jjSet;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}








}
