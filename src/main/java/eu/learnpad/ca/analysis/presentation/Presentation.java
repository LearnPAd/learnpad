package eu.learnpad.ca.analysis.presentation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class Presentation extends AbstractAnalysisClass {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Presentation.class);


	private long lStartTime;
	public Presentation(CollaborativeContentAnalysis collaborativeContentInput,Language lang) {

		this.language = lang;
		this.collaborativeContentInput = collaborativeContentInput;

	}

	public Presentation(StaticContentAnalysis staticContentInput, Language lang) {

		this.language = lang;
		this.staticContentInput = staticContentInput;

	}

	public void run() {
		lStartTime = System.currentTimeMillis();
		//some tasks
		if (collaborativeContentInput != null) {
			check(collaborativeContentInput);
		}

		if (staticContentInput != null) {
			//check(staticContentInput);
		}
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;

		log.trace("Presentation Elapsed milliseconds: " + difference);


	}


	/*private AnnotatedStaticContentAnalysis check(StaticContentAnalysis staticContentInput) {
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

		// AnnotationImpl i;


		List<Annotation> listannotation  =new ArrayList<Annotation>();
		int numSentence = execute(content,c,listannotation);
		annotatedStaticContent.setAnnotations(listannotation);
		double qualitymmeasure = calculateOverallQualityMeasure(numSentence);
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Content Clarity");

		return annotatedStaticContent;

	}*/

	public AnnotatedCollaborativeContentAnalysis check(
			CollaborativeContentAnalysis collaborativeContentInput) {
		String title = collaborativeContentInput.getCollaborativeContent().getTitle();
		String idc = collaborativeContentInput.getCollaborativeContent().getId();
		String contenthtml = collaborativeContentInput.getCollaborativeContent().getContenthtml();

		annotatedCollaborativeContent = new AnnotatedCollaborativeContentAnalysis();
		CollaborativeContent sc = new CollaborativeContent();
		annotatedCollaborativeContent.setCollaborativeContent(sc);
		sc.setTitle(title);
		sc.setId(idc);


		// AnnotationImpl i;


		List<Annotation> listannotation  =new ArrayList<Annotation>();
		int numSentence = execute(contenthtml,listannotation);
		annotatedCollaborativeContent.setAnnotations(listannotation);
		double qualitymmeasure = calculateOverallQualityMeasure(numSentence);
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Presentation");

		return annotatedCollaborativeContent;

	}

	private int execute(String contenthtml, 
			List<Annotation> listannotation) {

		lStartTime = System.currentTimeMillis();
		try{
			if(contenthtml!=null){
				if(contenthtml!=""){
					Document doc = Jsoup.parse(contenthtml);
					Elements links = doc.select("a[href]");
					Elements h1 = doc.getElementsByTag("h1");
					Elements h2 = doc.getElementsByTag("h2");
					Elements h3 = doc.getElementsByTag("h3");
					Elements h4 = doc.getElementsByTag("h4");
					Elements h5 = doc.getElementsByTag("h5");
					Elements strong = doc.getElementsByTag("strong");
					Elements p = doc.getElementsByTag("p"); //paraghraf
					Elements b = doc.getElementsByTag("b"); //bold
					Elements i = doc.getElementsByTag("i"); //emphasis
					Elements em = doc.getElementsByTag("em"); //emphasis
					Elements mark = doc.getElementsByTag("mark"); //evidenziato
					Elements ul = doc.getElementsByTag("ul"); //list
					Elements li = doc.getElementsByTag("li");
					Elements ol = doc.getElementsByTag("ol"); //numered list
					Elements div = doc.getElementsByTag("div"); //block
					//Elements h1 = doc.getElementsByTag("h1");
					//Elements h1 = doc.getElementsByTag("h1");

					//numDefectiveSentences = listSentenceDefected.size();
					log.info("#links "+links.size());
					log.info("#h1 "+h1.size());
					log.info("#h2 "+h2.size());
					log.info("#h3 "+h3.size());
					log.info("#h4 "+h4.size());
					log.info("#h5 "+h5.size());
					log.info("#strong "+strong.size());
					log.info("#p "+p.size());
					log.info("#b "+b.size());
					log.info("#i "+i.size());
					log.info("#em "+em.size());
					log.info("#mark "+mark.size());
					log.info("#ul "+ul.size());
					log.info("#li "+li.size());
					log.info("#ol "+ol.size());
					log.info("#div "+div.size());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return 0;// listSentence.size();
	}




}
