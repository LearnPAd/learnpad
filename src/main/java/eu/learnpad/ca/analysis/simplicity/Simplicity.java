package eu.learnpad.ca.analysis.simplicity;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.BritishEnglish;
import org.languagetool.language.Italian;

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
import eu.learnpad.ca.simplicity.juridicaljargon.JuridaljargonSet;
import eu.learnpad.ca.simplicity.juridicaljargon.Juridicaljargon;

public class Simplicity extends Thread implements AnalysisInterface{

	private Language language;
	private JuridaljargonSet juridaljargonSet;
	private Integer numDefectiveSentences = 0;
	
	private CollaborativeContentAnalysis collaborativeContentInput;
	private AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContent;
	
	private StaticContentAnalysis staticContentInput;
	private AnnotatedStaticContentAnalysis annotatedStaticContent;
	
	

	public Simplicity(CollaborativeContentAnalysis cca, Language lang){
		this.language=lang;
		juridaljargonSet = readJJ(lang);
		collaborativeContentInput = cca;
		
	}
	
	
	public Simplicity(StaticContentAnalysis cca, Language lang){
		this.language=lang;
		juridaljargonSet = readJJ(lang);
		staticContentInput = cca;
		
	}
	
	private void checkJJ(StaticContentAnalysis cca){
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
		int id=0;
		for (String sentence : listsentence) {


			List<Annotation> annotations = checkdefect(sentence,c, id);
			annotatedStaticContent.setAnnotations(annotations);
			id++;
		}

		//System.out.println(content);

		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Simplicity");


	}
	

	private JuridaljargonSet readJJ(Language lang){
		InputStream is = null;
		if(lang instanceof BritishEnglish | lang instanceof AmericanEnglish){
			is = Simplicity.class.getClassLoader().getResourceAsStream("JuridicalJargon_EnglishLatin.xml");

		}else
			if(lang instanceof Italian){
				is = Simplicity.class.getClassLoader().getResourceAsStream("JuridicalJargon_EnglishLatin.xml");

			}

		assertNotNull(is);

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

	private void checkJJ(CollaborativeContentAnalysis cca){
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

		//String sResult = "This is a test. This is a T.L.A. test.";
		//String[] sSentence = content.split("(?<=[a-z])\\.\\s+");
		JLanguageTool langTool = new JLanguageTool(language);
		List<String> listsentence = langTool.sentenceTokenize(content);
		int id=0;
		for (String sentence : listsentence) {


			List<Annotation> annotations = checkdefect(sentence,c, id);
			if(annotations.size()>0){
				numDefectiveSentences++;
			}
			annotatedCollaborativeContent.setAnnotations(annotations);
			id++;
		}

		//System.out.println(content);

		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Simplicity");


	}

	private List<Annotation> checkdefect(String sentence,Content c,int nodeid){
		List<Juridicaljargon> Listjj = juridaljargonSet.getJuridicaljargon();
		StringTokenizer tokenizer = new StringTokenizer(sentence);
		List<Annotation> annotations=new ArrayList<Annotation>();
		int precedentposition=0;

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if(Listjj.contains(new Juridicaljargon(token))){
				
				int initialpos = sentence.indexOf(token);
				int finalpos = initialpos+token.length();
				if(precedentposition>initialpos){
					 initialpos = sentence.lastIndexOf(token);
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
				a.setType("Simplicity");
				a.setRecommendation("juridical jargon, use simpler terms");
				annotations.add(a);

			}
		}
		if(annotations.size()==0){
			c.setContent(sentence);
		}
		return annotations;

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

	
	public void run() {
		if(collaborativeContentInput!=null){
			checkJJ(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			checkJJ(staticContentInput);	
		}

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
}
