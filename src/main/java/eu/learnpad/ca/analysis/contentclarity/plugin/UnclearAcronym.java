package eu.learnpad.ca.analysis.contentclarity.plugin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class UnclearAcronym  extends  AbstractAnalysisClass{ 

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UnclearAcronym.class);

	

	public UnclearAcronym(StaticContentAnalysis cca, Language lang){
		this.language=lang;
		staticContentInput = cca;

	}


	public UnclearAcronym(CollaborativeContentAnalysis cca,
			Language lang) {
		this.language=lang;
		collaborativeContentInput = cca;
	}

	

	

	public void run() {
		if(collaborativeContentInput!=null){
			checkUnclearAcronym(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			checkUnclearAcronym(staticContentInput);	
		}

	}

	private void checkUnclearAcronym(StaticContentAnalysis staticContentInput2) {
		String title = staticContentInput.getStaticContent().getTitle();
		String idc = staticContentInput.getStaticContent().getId();
		String content = staticContentInput.getStaticContent().getContentplain();

		annotatedStaticContent = new AnnotatedStaticContentAnalysis();
		StaticContent sc = new StaticContent();
		annotatedStaticContent.setStaticContent(sc);
		sc.setTitle(title);
		sc.setId(idc);
		Content c = new Content();
		sc.setContent(c);

		//String sResult = "This is a test. This is a T.L.A. test.";
		//String[] sSentence = content.split("(?<=[a-z])\\.\\s+");
		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentence = langTool.sentenceTokenize(content);
		List<Annotation> annotations = checkdefect(content,c,listsentence);

		annotatedStaticContent.setAnnotations(annotations);
		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Content Clarity");

	}

	private void checkUnclearAcronym(CollaborativeContentAnalysis collaborativeContentInput) {
		String title = collaborativeContentInput.getCollaborativeContent().getTitle();
		String idc = collaborativeContentInput.getCollaborativeContent().getId();
		String content = collaborativeContentInput.getCollaborativeContent().getContentplain();

		annotatedCollaborativeContent = new AnnotatedCollaborativeContentAnalysis();
		CollaborativeContent sc = new CollaborativeContent();
		annotatedCollaborativeContent.setCollaborativeContent(sc);
		sc.setTitle(title);
		sc.setId(idc);
		Content c = new Content();
		sc.setContent(c);


		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentence = langTool.sentenceTokenize(content);

		List<Annotation> annotations =checkdefect(content,c,listsentence);
		annotatedCollaborativeContent.setAnnotations(annotations);

		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Content Clarity");
		

	}

	private List<Annotation>  checkdefect(String content, Content c, List<String> listsentence) {
		List<String> acronymdefected = new ArrayList<String>();
		List<String> listOfStrings = new ArrayList<String>(Arrays.asList(content.split(" ")));
		Stopwords stopw = new Stopwords();

		listOfStrings.removeAll(stopw.getStopwords());
		String ContentCleaned = StringUtils.join(listOfStrings, " ");
		Map<String,String> acronym = new HashMap<String, String>();

		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentenceofContentCleaned = langTool.sentenceTokenize(ContentCleaned);

		
		String regex = "[A-Z|\\.]{2,}";

		// Create a Pattern object
		Pattern r = Pattern.compile(regex);
		for (String sentencecleanend : listsentenceofContentCleaned) {

			// Now create matcher object.
			Matcher m = r.matcher(sentencecleanend);

			while (m.find()){
				String tmpcandidateAcronym = m.group();
				String candidateAcronym = tmpcandidateAcronym;
				if(candidateAcronym.length()<=1 | (candidateAcronym.contains(".")&candidateAcronym.length()==2 ) | (!candidateAcronym.contains(".")&candidateAcronym.length()>4 )){
					continue;
				}
				if(candidateAcronym.contains(".") ){
					candidateAcronym = candidateAcronym.replaceAll("\\.", "");
					
				}
				int lencandidateAcronym = candidateAcronym.length();
				String regex2 = "([A-Z]+\\w+([ ]|)){"+lencandidateAcronym+"}";

				// Create a Pattern object
				Pattern r2 = Pattern.compile(regex2);

				// Now create matcher object.
				Matcher m2 = r2.matcher(sentencecleanend);
				boolean flag = true;
				while(m2.find()){

					//ok trovato  acronimi estesi

					String candidateexAcr = m2.group();
					

					String [] splited = candidateexAcr.split("\\s");
					if(candidateAcronym.length()==splited.length){
						for (int i = 0; i < splited.length; i++) {
							if(!splited[i].startsWith(String.valueOf(candidateAcronym.charAt(i)))){
								flag = true;
								break;
							}else{
								flag = false;					
							}
						}

						if(!flag){
							//System.out.println("Acronym "+candidateAcronym+" "+candidateexAcr);
							//Acronym trovato
							acronym.put(candidateAcronym,candidateexAcr);
							break;
						}
					}
				}
				if(flag){
					//new defect
					if(!acronym.containsKey(candidateAcronym)){
						/*if(tmpcandidateAcronym.indexOf(".")==tmpcandidateAcronym.lastIndexOf(".") & tmpcandidateAcronym.contains(".")){
							tmpcandidateAcronym = tmpcandidateAcronym.replaceAll("\\.", "");
						}*/
						if(!acronymdefected.contains(tmpcandidateAcronym)){
							acronymdefected.add(tmpcandidateAcronym);
						}
					}
				}

			}
		}


		log.trace(acronym+"\nsize: "+acronym.size());
		log.trace(acronymdefected+"\nsize: "+acronymdefected.size());
		return insertdefectannotation(content, c ,  acronymdefected, listsentence );

	}


	private List<Annotation> insertdefectannotation(String content,Content c, List<String> acronymdefected, List<String> listsentence){
		int id = 0;
		List<Annotation> annotations =new ArrayList<Annotation>();
		for (String sentence : listsentence) {

			id = insertdefectannotationsentence(sentence, c , id, annotations, acronymdefected);
			if(annotations.size()>0){
				numDefectiveSentences++;
			}
			
			id++;

		}

		log.trace("\nnumDefectiveSentences UnclearAcronym: "+numDefectiveSentences);
		return annotations;
	}


	private int insertdefectannotationsentence(String sentence, Content c,
			int nodeid, List<Annotation> annotations,
			List<String> acronymdefected) {
		//StringTokenizer tokenizer = new StringTokenizer(sentence," \u201c\u201d.,?!:;()<>[]\b\t\n\f\r\"\'\"");
		String [] spliter = sentence.split("[\\s]");
		Map<String, Integer> elementfinded = new HashMap<String, Integer>();
		int precedentposition=0;

		//while (tokenizer.hasMoreTokens()) {
			//String token = tokenizer.nextToken();
			//token =	token.trim().replace(".", "").replace(":", "").replace(";", "").replace("\"","");
		for (int i = 0; i < spliter.length; i++) {
			
			String token = spliter[i];
			if(acronymdefected.contains(token)){
				int initialpos = indexofElement(sentence,token,elementfinded,"[\\s]");
				int finalpos = initialpos+token.length();
				if(precedentposition>initialpos){
					//initialpos = sentence.lastIndexOf(token);
					//finalpos = initialpos+token.length();
					log.fatal("error jump position");
				}
				String stringap = sentence.substring(precedentposition, initialpos);
				c.setContent(stringap);
				precedentposition=finalpos;
				nodeid++;
				Node init= new Node(nodeid);
				nodeid++;
				Node end= new Node(nodeid);
				c.setContent(init);
				c.setContent(token);
				c.setContent(end);

				Annotation a = new Annotation();
				a.setId(nodeid);
				a.setEndNode(end.getId());
				a.setStartNode(init.getId());
				a.setType("Content Clarity");
				a.setRecommendation("Explicit Acronym "+token);
				annotations.add(a);

			}
		}
		if(precedentposition<sentence.length()){
			String stringap = sentence.substring(precedentposition, sentence.length());
			c.setContent(stringap);
		}
		/*if(annotations.size()==0){
			c.setContent(sentence);
		}*/

		return nodeid;

	}

	
}
