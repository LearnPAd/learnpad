package eu.learnpad.ca.analysis.contentclarity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;

import eu.learnpad.ca.analysis.AnalysisInterface;

import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.Content;
import eu.learnpad.ca.rest.data.Node;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class UnclearAcronym  extends Thread implements AnalysisInterface{ 

	private Language language;

	private Integer numDefectiveSentences = 0;

	private CollaborativeContentAnalysis collaborativeContentInput;
	private AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContent;

	private StaticContentAnalysis staticContentInput;
	private AnnotatedStaticContentAnalysis annotatedStaticContent;





	public UnclearAcronym(CollaborativeContentAnalysis cca,
			Language lang) {
		this.language=lang;
		collaborativeContentInput = cca;
	}

	private double calculateOverallQualityMeasure(Integer numsentence){
		double qm = (1-(numDefectiveSentences.doubleValue()/numsentence.doubleValue()))*100;
		double qualityMeasure = Math.abs(qm);
		return qualityMeasure;
	}

	private String calculateOverallQuality(double qualityMeasure){
		String quality="";
		if(qualityMeasure<=25){
			quality="VERY BAD";
		}else if(qualityMeasure<=50){
			quality="BAD";
		}else if(qualityMeasure<=75){
			quality="GOOD";
		}else if(qualityMeasure<100){
			quality="VERY GOOD";
		}else if(qualityMeasure==100){
			quality="EXCELLENT";
		}
		return quality;
	}

	private String calculateOverallRecommendations(double qualityMeasure){
		String recommendations="";
		if(qualityMeasure<=25){
			recommendations="Quality is very poor, correct the errors";
		}else if(qualityMeasure<=50){
			recommendations="Quality is poor, correct the errors";
		}else if(qualityMeasure<=75){
			recommendations="Quality is acceptable, but there are still some errors";
		}else if(qualityMeasure<100){
			recommendations="Well done, still few errors remaining";
		}else if(qualityMeasure==100){
			recommendations="Well done, no errors found!";
		}
		return recommendations;
	}

	public AnnotatedCollaborativeContentAnalysis getAnnotatedCollaborativeContentAnalysis(){
		return annotatedCollaborativeContent;
	}

	public String getStatus(){
		switch (this.getState()) {
		case TERMINATED:
			return "OK";

		default:
			return "IN PROGRESS";
		}

	}



	public AnnotatedStaticContentAnalysis getAnnotatedStaticContentAnalysis() {
		return annotatedStaticContent;
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
		String content = staticContentInput.getStaticContent().getContent().toString();

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
		checkdefect(content,c,listsentence);

		//System.out.println(content);

		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Content Clarity");

	}

	private void checkUnclearAcronym(CollaborativeContentAnalysis collaborativeContentInput) {
		String title = collaborativeContentInput.getCollaborativeContent().getTitle();
		String idc = collaborativeContentInput.getCollaborativeContent().getId();
		String content = collaborativeContentInput.getCollaborativeContent().getContent().toString();

		annotatedCollaborativeContent = new AnnotatedCollaborativeContentAnalysis();
		CollaborativeContent sc = new CollaborativeContent();
		annotatedCollaborativeContent.setCollaborativeContent(sc);
		sc.setTitle(title);
		sc.setId(idc);
		Content c = new Content();
		sc.setContent(c);


		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentence = langTool.sentenceTokenize(content);

		//	for (String sentence : listsentence) {


		checkdefect(content,c,listsentence);



		//}
		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Content Clarity");
		//System.out.println(annotatedCollaborativeContent);

	}

	private void checkdefect(String content, Content c, List<String> listsentence) {
		List<String> acronymdefected = new ArrayList<String>();
		List<String> listOfStrings = new ArrayList<String>(Arrays.asList(content.split(" ")));
		Stopwords stopw = new Stopwords();

		listOfStrings.removeAll(stopw.getStopwords());
		String ContentCleaned = StringUtils.join(listOfStrings, " ");
		Map<String,String> acronym = new HashMap<String, String>();
		
		//System.out.println(ContentCleaned); 
		String regex = "[A-Z]\\.|[A-Z]{2,}";

		// Create a Pattern object
		Pattern r = Pattern.compile(regex);

		// Now create matcher object.
		Matcher m = r.matcher(ContentCleaned);

		while (m.find()){
			String tmpcandidateAcronym = m.group();
			String candidateAcronym = tmpcandidateAcronym;
			if(candidateAcronym.length()<=1 | (candidateAcronym.contains(".")&candidateAcronym.length()==2 )){
				//System.out.println("candidato scartato "+candidateAcronym);
				continue;
			}
			if(candidateAcronym.contains(".")){
				candidateAcronym = candidateAcronym.replace(".", "");
			}
			int lencandidateAcronym = candidateAcronym.length();
			String regex2 = "([A-Z]+\\w+([ ]|)){"+lencandidateAcronym+"}";

			// Create a Pattern object
			Pattern r2 = Pattern.compile(regex2);

			// Now create matcher object.
			Matcher m2 = r2.matcher(ContentCleaned);
			boolean flag = false;
			while(m2.find()){

				//ok trovato  acronimi estesi

				String candidateexAcr = m2.group();
				//System.out.println(candidateexAcr);

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
				if(!acronymdefected.contains(tmpcandidateAcronym)){
					acronymdefected.add(tmpcandidateAcronym);
				}
			}

		}



		System.out.println(acronym);
		insertdefectannotation(content, c ,  acronymdefected, listsentence );

	}


	private void insertdefectannotation(String content,Content c, List<String> acronymdefected, List<String> listsentence){
		int id = 0;
		for (String sentence : listsentence) {

			List<Annotation> annotations =new ArrayList<Annotation>();
			id = insertdefectannotationsentence(sentence, c , id, annotations, acronymdefected);
			if(annotations.size()>0){
				numDefectiveSentences++;
			}
			annotatedCollaborativeContent.setAnnotations(annotations);
			id++;

		}


	}


	private int insertdefectannotationsentence(String sentence, Content c,
			int nodeid, List<Annotation> annotations,
			List<String> acronymdefected) {
		StringTokenizer tokenizer = new StringTokenizer(sentence," \u201c\u201d.,?!:;()<>[]\b\t\n\f\r\"\'\"");
		Map<String, Integer> elementfinded = new HashMap<String, Integer>();
		int precedentposition=0;

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			//token =	token.trim().replace(".", "").replace(":", "").replace(";", "").replace("\"","");
			if(acronymdefected.contains(token)){

				int initialpos = indexofElement(sentence,token,elementfinded);
				int finalpos = initialpos+token.length();
				/*if(precedentposition>initialpos){
					initialpos = sentence.lastIndexOf(token);
					finalpos = initialpos+token.length();
				}*/
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

	private int indexofElement(String sentence, String word, Map<String, Integer> elementfinded){
		//StringTokenizer tokenizer = new StringTokenizer(sentence);
		String [] spliter = sentence.split("[\\W]");
		int position = 0;
		int numwordfinded = 0;
		for (int i = 0; i < spliter.length; i++) {
			int offset = 0;
			String token = spliter[i];
			/*if(token.contains(":")|token.contains(".") |  token.contains(String.valueOf('\u201d'))){
				offset = 1;
				token = token.substring(0, token.length()-1);
			}else
				if( token.contains(String.valueOf('\u201c'))){
					offset = 1;
					token = token.substring(1, token.length());
				}*/
			if(token.equals(word)){
				numwordfinded++;
				if(!elementfinded.containsKey(token)){
					elementfinded.put(token, 2);
					return position;
				}
				if(elementfinded.get(token).intValue()==numwordfinded){
					Integer I = elementfinded.get(token);
					int y  = I.intValue()+1;
					elementfinded.put(token, y);
					return position;
				}else{
					position+=token.length()+1+offset;
				}
				//if(elementfinded.containsKey(token))

			}else{
				position+=token.length()+1+offset;
			}
		}
		return position;
	}
}
