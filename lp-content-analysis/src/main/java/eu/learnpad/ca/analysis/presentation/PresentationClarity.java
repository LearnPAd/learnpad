package eu.learnpad.ca.analysis.presentation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.languagetool.Language;

import eu.learnpad.ca.analysis.AbstractAnalysisClass;
import eu.learnpad.ca.analysis.localizzation.Messages;
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

		log.trace("PresentationClarity Elapsed milliseconds: " + difference); //$NON-NLS-1$


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
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%"); //$NON-NLS-1$ //$NON-NLS-2$
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("Presentation Clarity"); //$NON-NLS-1$

		return annotatedStaticContent;

	}
	protected double calculateOverallQualityMeasure(Integer numdef){
		double qm = (1-(numdef.doubleValue()/7))*100;
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
		annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%"); //$NON-NLS-1$ //$NON-NLS-2$
		annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedCollaborativeContent.setType("Presentation Clarity"); //$NON-NLS-1$

		return annotatedCollaborativeContent;

	}

	private int execute(String contenthtml, 
			List<Annotation> listannotation) {
		int id = 0;
		int offset = 2300;
		id = offset;
		lStartTime = System.currentTimeMillis();
		try{
			Properties prop = new Properties();
			// load a properties file
			prop.load(PresentationClarity.class.getClassLoader().getResourceAsStream("presentationclarity.properties")); //$NON-NLS-1$

			if(contenthtml!=null){
				//if(contenthtml!=""){ //$NON-NLS-1$
				Document doc = Jsoup.parse(contenthtml);
				Elements links = doc.select("a[href]"); //$NON-NLS-1$
				Elements h1 = doc.getElementsByTag("h1"); //$NON-NLS-1$
				Elements h2 = doc.getElementsByTag("h2"); //$NON-NLS-1$
				Elements h3 = doc.getElementsByTag("h3"); //$NON-NLS-1$
				Elements h4 = doc.getElementsByTag("h4"); //$NON-NLS-1$
				Elements h5 = doc.getElementsByTag("h5"); //$NON-NLS-1$
				Elements strong = doc.getElementsByTag("strong"); //$NON-NLS-1$
				Elements p = doc.getElementsByTag("p"); //paraghraf //$NON-NLS-1$
				Elements b = doc.getElementsByTag("b"); //bold //$NON-NLS-1$
				Elements i = doc.getElementsByTag("i"); //emphasis //$NON-NLS-1$
				Elements em = doc.getElementsByTag("em"); //emphasis //$NON-NLS-1$
				Elements mark = doc.getElementsByTag("mark"); //evidenziato //$NON-NLS-1$
				Elements ul = doc.getElementsByTag("ul"); //list //$NON-NLS-1$
				Elements li = doc.getElementsByTag("li"); //$NON-NLS-1$
				Elements ol = doc.getElementsByTag("ol"); //numered list //$NON-NLS-1$
				Elements div = doc.getElementsByTag("div"); //block //$NON-NLS-1$
				Elements a = doc.getElementsByTag("h1"); //links //$NON-NLS-1$
				//Elements h1 = doc.getElementsByTag("h1");

				//numDefectiveSentences = listSentenceDefected.size();
				log.info("#links "+links.size()); //$NON-NLS-1$
				log.info("#h1 "+h1.size()); //$NON-NLS-1$
				log.info("#h2 "+h2.size()); //$NON-NLS-1$
				log.info("#h3 "+h3.size()); //$NON-NLS-1$
				log.info("#h4 "+h4.size()); //$NON-NLS-1$
				log.info("#h5 "+h5.size()); //$NON-NLS-1$
				log.info("#strong "+strong.size()); //$NON-NLS-1$
				log.info("#p "+p.size()); //$NON-NLS-1$
				log.info("#b "+b.size()); //$NON-NLS-1$
				log.info("#i "+i.size()); //$NON-NLS-1$
				log.info("#em "+em.size()); //$NON-NLS-1$
				log.info("#mark "+mark.size()); //$NON-NLS-1$
				log.info("#ul "+ul.size()); //$NON-NLS-1$
				log.info("#li "+li.size()); //$NON-NLS-1$
				log.info("#ol "+ol.size()); //$NON-NLS-1$
				log.info("#div "+div.size()); //$NON-NLS-1$

				String type = "PresentationClarity: Poor section partitioning"; //$NON-NLS-1$
				//RULE 1: N = number of <h* > tags, N > 1.
				//RULE 2: L = number of sentences between <p>, L < t. //and div??
				boolean PSPrule1 = (h1.size()+h2.size()+h3.size()+h4.size()+h5.size())>1;


				if(!PSPrule1){
					String rec = Messages.getString("PresentationClarity.PoorSectionRecomandation",language); //$NON-NLS-1$
					Annotation rule1 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule1);
					log.trace(rec);
					id++;
				}
				String res = prop.getProperty("num_p_tag_max"); //$NON-NLS-1$
				int num_p_tag_max= res!=null ? Integer.valueOf(res): 5;
				boolean PSPrule2 = p.size()<num_p_tag_max;

				if(!PSPrule2){
					String rec = String.format(Messages.getString("PresentationClarity.PoorSectionParagraphRecomandation",language), num_p_tag_max); //$NON-NLS-1$ //$NON-NLS-2$
					Annotation rule2 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule2);
					log.trace(rec);
					id++;
				}

				//Recommendation (RULE 1): Partition your document into sections.
				//Recommendation (RULE 2): Split your paragraphs. Each paragraph shall be less than 5 sentences.


				type = "PresentationClarity: Relevant content not emphasised"; //$NON-NLS-1$

				//RULE 1: n = number of terms within <strong> and <b> tags, tot = total number of terms, n/tot ·
				//100% > X%

				int n = getTotNumberTerms(strong)+getTotNumberTerms(b);

				int tot = getTotNumberTerms(doc);

				res = prop.getProperty("min_emphasised"); //$NON-NLS-1$
				int min_emphasised = res!=null ? Integer.valueOf(res): 1;

				boolean RCErule1 = ((n*100)/tot)>min_emphasised;

				res = prop.getProperty("max_emphasised"); //$NON-NLS-1$
				int max_emphasised = res!=null ? Integer.valueOf(res): 20;

				boolean RCErule2 = ((n*100)/tot)<max_emphasised;

				if(!RCErule1){
					String rec = Messages.getString("PresentationClarity.RelevantContentRecomandation",language); //$NON-NLS-1$
					Annotation rule1 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule1);
					log.trace(rec);
					id++;
				}

				type = "PresentationClarity: Excessive amount of emphasised content";

				if(!RCErule2){
					String rec = Messages.getString("PresentationClarity.BoldRecomandation",language); //$NON-NLS-1$
					// TODO: Ritestare
					Annotation rule1 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule1);
					log.trace(rec);
					id++;
				}
				//Recommendation: Highlight in bold the relevant sentences and keywords of your text.

				type = "PresentationClarity: Instructions hard to identify"; //$NON-NLS-1$

				//RULE1: N =numberof<ol>or<ul>tags,N >t

				res = prop.getProperty("num_min_lists"); //$NON-NLS-1$
				int num_min_lists = res!=null ? Integer.valueOf(res): 2;

				boolean IHIPrule1 = (ol.size()>num_min_lists)|(ul.size()>num_min_lists);

				if(!IHIPrule1){
					String rec = Messages.getString("PresentationClarity.MinListRecomandation",language); //$NON-NLS-1$
					Annotation rule1 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule1);
					log.trace(rec);
					id++;
				}

				//Recommendation: Provide bullet point lists or numbered lists for your instructions.

				type = "PresentationClarity: Excessive number of instructions"; //$NON-NLS-1$

				//RULE 1: N = number of <li> tags between <ol> or <ul> tags, N < t.
				//per ogni lista puntata o numerata controlla che sia minore della soglia


				res = prop.getProperty("num_max_lists"); //$NON-NLS-1$
				int num_max_lists = res!=null ? Integer.valueOf(res): 11;


				boolean ENIPrule1 = true;
				for( Element elem : ol){
					Elements allin = elem.children();
					ENIPrule1 = (allin.size())<num_max_lists;
				}
				for( Element elem : ul){
					Elements allin = elem.children();
					ENIPrule1 = ENIPrule1 | (allin.size())<num_max_lists;
				}


				if(!ENIPrule1){
					String rec = Messages.getString("PresentationClarity.MaxListRecomandation",language); //$NON-NLS-1$
					Annotation rule1 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule1);
					log.trace(rec);
					id++;
				}

				//Recommendation: Limit the number of elements in the lists. Each list shall not be longer than 10 items. If needed, split the list into sub-tasks.

				type = "PresentationClarity: Excessive length of the document"; //$NON-NLS-1$

				//RULE 1: N = number of words in the document, N < t.
				res = prop.getProperty("num_max_word"); //$NON-NLS-1$
				int num_max_word = res!=null ? Integer.valueOf(res): 1600;

				boolean ELDrule1 = tot<num_max_word;

				if(!ELDrule1){
					String rec = String.format(Messages.getString("PresentationClarity.TooLongContentRecomandation",language), num_max_word); //$NON-NLS-1$ //$NON-NLS-2$
					Annotation rule1 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule1);
					log.trace(rec);
					id++;
				}

				//Recommendation: The document is too long. A document shall not be longer than t words.

				type = "PresentationClarity: Excessive references"; //$NON-NLS-1$

				//RULE1: N =numberof<a>tags,N < t.
				res = prop.getProperty("num_min_ref"); //$NON-NLS-1$
				int num_min_ref = res!=null ? Integer.valueOf(res): 5;

				boolean ERrule1 = a.size()<num_min_ref;

				if(!ERrule1){
					String rec = String.format(Messages.getString("PresentationClarity.ExcessiveReferencesRecomandation",language),num_min_ref); //$NON-NLS-1$ //$NON-NLS-2$
					Annotation rule1 = new Annotation(id,type,0,0,rec);
					listannotation.add(rule1);
					log.trace(rec);
					id++;
				}
				//Recommendation: Do not refer more than t external documents. The reader might be confused. Refer only relevant external documents.

				/*}else
					id+=7;*/
			}else
				id+=7;
		} catch (Exception e) {
			log.error(e);
		}
		return id-offset;// listSentence.size();
	}



	private int getTotNumberTerms(Elements elements) {
		if(!elements.isEmpty()){
			String stringelements = ""; //$NON-NLS-1$
			for (Element element : elements) {
				stringelements +=element.text();
			}
			return stringelements.split(" ").length; //$NON-NLS-1$
		}
		return 0;
	}

	private int getTotNumberTerms(Document doc) {
		int numdoc = 0;
		String stringdoc = doc.text();
		numdoc = stringdoc.split(" ").length; //$NON-NLS-1$
		return numdoc;
	}




}
