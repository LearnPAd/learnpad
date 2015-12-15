package eu.learnpad.ca.analysis.presentation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.rest.data.Annotation;
import eu.learnpad.ca.rest.data.collaborative.AnnotatedCollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContent;
import eu.learnpad.ca.rest.data.collaborative.CollaborativeContentAnalysis;
import eu.learnpad.ca.rest.data.stat.AnnotatedStaticContentAnalysis;
import eu.learnpad.ca.rest.data.stat.StaticContent;
import eu.learnpad.ca.rest.data.stat.StaticContentAnalysis;

public class PresentationClarity extends AbstractAnalysisClass {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(PresentationClarity.class);


	private long lStartTime;
	public PresentationClarity(CollaborativeContentAnalysis collaborativeContentInput,Language lang) {

		this.language = lang;
		this.collaborativeContentInput = collaborativeContentInput;

	}

	public PresentationClarity(StaticContentAnalysis staticContentInput, Language lang) {

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
			check(staticContentInput);
		}
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;

		log.trace("PresentationClarity Elapsed milliseconds: " + difference);


	}


	private AnnotatedStaticContentAnalysis check(StaticContentAnalysis staticContentInput) {
		String title = staticContentInput.getStaticContent().getTitle();
		String idc = staticContentInput.getStaticContent().getId();
		String contenthtml = staticContentInput.getStaticContent().getContenthtml();

		annotatedStaticContent = new AnnotatedStaticContentAnalysis();
		StaticContent sc = new StaticContent();
		annotatedStaticContent.setStaticContent(sc);
		sc.setTitle(title);
		sc.setId(idc);
		

		// AnnotationImpl i;


		List<Annotation> listannotation  =new ArrayList<Annotation>();
		int numdef = execute(contenthtml,listannotation);
		annotatedStaticContent.setAnnotations(listannotation);
		double qualitymmeasure = calculateOverallQualityMeasure(numdef);
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Presentation Clarity");

		return annotatedStaticContent;

	}
	protected double calculateOverallQualityMeasure(Integer numdef){
		double qm = (1-(numdef.doubleValue()/6))*100;
		double qualityMeasure = Math.abs(qm);
		return qualityMeasure;
	}
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
		int numdef = execute(contenthtml,listannotation);
		annotatedCollaborativeContent.setAnnotations(listannotation);
		double qualitymmeasure = calculateOverallQualityMeasure(numdef);;
		annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Presentation Clarity");

		return annotatedCollaborativeContent;

	}

	private int execute(String contenthtml, 
			List<Annotation> listannotation) {
		int id = 0;
		int offset = 2300;
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
					Elements a = doc.getElementsByTag("h1"); //links
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

					String type = "PresentationClarity: Poor section partitioning";
					//RULE 1: N = number of <h* > tags, N > 1.
					//RULE 2: L = number of sentences between <p>, L < t. //and div??
					id = offset;
					boolean PSPrule1 = (h1.size()+h2.size()+h3.size()+h4.size()+h5.size())>1;


					if(!PSPrule1){
						String rec = "Partition your document into sections";
						Annotation rule1 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule1);
						log.trace(rec);
						id++;
					}

					int tau=5;
					boolean PSPrule2 = p.size()<tau;

					if(!PSPrule2){
						String rec = "Split your paragraphs. Each paragraph shall be less than "+tau+" sentences.";
						Annotation rule2 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule2);
						log.trace(rec);
						id++;
					}

					//Recommendation (RULE 1): Partition your document into sections.
					//Recommendation (RULE 2): Split your paragraphs. Each paragraph shall be less than 5 sentences.


					type = "PresentationClarity: Relevant content not emphasised";

					//RULE 1: n = number of terms within <strong> and <b> tags, tot = total number of terms, n/tot ·
					//100% > X%

					int n = getTotNumberTerms(strong)+getTotNumberTerms(b);
					int tot = getTotNumberTerms(doc);
					int X = 1;
					boolean RCErule1 = ((n*100)/tot)>X;
					int X2 = 20;
					boolean RCErule2 = ((n*100)/tot)<X2;

					if(!RCErule1){
						String rec = "Highlight in bold the relevant sentences and keywords of your text.";
						Annotation rule1 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule1);
						log.trace(rec);
						id++;
					}

					type = "PresentationClarity: Excessive amount of emphasised content";
					
					if(!RCErule2){
						String rec = "Reduce the amount of bold terms and sentences. Only the parts of the text that "
								+ "are particularly relevant shall be emphasised.";
						Annotation rule1 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule1);
						log.trace(rec);
						id++;
					}
					//Recommendation: Highlight in bold the relevant sentences and keywords of your text.

					type = "PresentationClarity: Instructions hard to identify";

					//RULE1: N =numberof<ol>or<ul>tags,N >t

					tau=2;
					boolean IHIPrule1 = (ol.size()>tau)|(ul.size()>tau);

					if(!IHIPrule1){
						String rec = " Provide bullet point lists or numbered lists for your instructions";
						Annotation rule1 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule1);
						log.trace(rec);
						id++;
					}

					//Recommendation: Provide bullet point lists or numbered lists for your instructions.

					type = "PresentationClarity: Excessive number of instructions";

					//RULE 1: N = number of <li> tags between <ol> or <ul> tags, N < t.
					//per ogni lista puntata o numerata controlla che sia minore della soglia
					tau=11;
					
					
					boolean ENIPrule1 = true;
					for( Element elem : ol){
						Elements allin = elem.children();
						ENIPrule1 = (allin.size())<tau;
					}
					for( Element elem : ul){
						Elements allin = elem.children();
						ENIPrule1 = ENIPrule1 | (allin.size())<tau;
					}
					

					if(!ENIPrule1){
						String rec = "Limit the number of elements in the lists. Each list shall not be longer than 10 items. If needed, split the list into sub-tasks";
						Annotation rule1 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule1);
						log.trace(rec);
						id++;
					}

					//Recommendation: Limit the number of elements in the lists. Each list shall not be longer than 10 items. If needed, split the list into sub-tasks.

					type = "PresentationClarity: Excessive length of the document";

					//RULE 1: N = number of words in the document, N < t.
					tau = 1600;
					boolean ELDrule1 = tot<tau;

					if(!ELDrule1){
						String rec = "The document is too long. A document shall not be longer than "+tau+" words.";
						Annotation rule1 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule1);
						log.trace(rec);
						id++;
					}

					//Recommendation: The document is too long. A document shall not be longer than t words.

					type = "PresentationClarity: Excessive references";

					//RULE1: N =numberof<a>tags,N < t.
					tau = 5;
					boolean ERrule1 = a.size()<tau;

					if(!ERrule1){
						String rec = "Do not refer more than "+tau+" external documents. The reader might be confused. Refer only relevant external documents.";
						Annotation rule1 = new Annotation(id,type,0,0,rec);
						listannotation.add(rule1);
						log.trace(rec);
						id++;
					}
					//Recommendation: Do not refer more than t external documents. The reader might be confused. Refer only relevant external documents.

				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return id-offset;// listSentence.size();
	}

	private int numElementsOfType(Element elem, String string) {
	   Elements anni = elem.select("li > ol");
		Elements ele = elem.getElementsByTag(string);
		return ele.size();
	}

	private int getTotNumberTerms(Elements elements) {
		String stringelements = "";
		for (Element element : elements) {
			stringelements +=element.text();
		}
		return stringelements.split(" ").length;
	}

	private int getTotNumberTerms(Document doc) {
		int numdoc = 0;
		String stringdoc = doc.text();
		numdoc = stringdoc.split(" ").length;
		return numdoc;
	}




}
