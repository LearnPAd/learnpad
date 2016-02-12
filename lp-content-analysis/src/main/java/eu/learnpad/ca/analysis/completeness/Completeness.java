package eu.learnpad.ca.analysis.completeness;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
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

public class Completeness  extends AbstractAnalysisClass {
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Completeness.class);


	private long lStartTime;
	private List<String> Elements_Template = Arrays.asList("Headline",
			"Source Documents",
			"Reference Documents",
			"Glossary",
			"Context",
			"Summary", 
			"Motivation", 
			"Intended readership",
			"Involved actors",
			"Input documents",
			"Output documents",
			"Required tools",
			"Description",
			"Examples/Experiences",
			"What to do in case of failures",
			"Contacts of involved offices",
			"Contacts of experts",
			"FAQ");


	public Completeness(CollaborativeContentAnalysis collaborativeContentInput,Language lang) {

		this.language = lang;
		this.collaborativeContentInput = collaborativeContentInput;

	}

	public Completeness(StaticContentAnalysis staticContentInput, Language lang) {

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

		log.trace("Completeness Elapsed milliseconds: " + difference); //$NON-NLS-1$


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
		annotatedStaticContent.setType("Completeness"); //$NON-NLS-1$

		return annotatedStaticContent;

	}

	protected double calculateOverallQualityMeasure(Integer NumberFields){
		double qm = (NumberFields.doubleValue()/18)*100;
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
		annotatedCollaborativeContent.setType("Completeness"); //$NON-NLS-1$

		return annotatedCollaborativeContent;

	}

	private int execute(String contenthtml, 
			List<Annotation> listannotation) {
		int id = 0;
		int NumberFields  = 0;
		lStartTime = System.currentTimeMillis();
		try{

			if(contenthtml!=null){
				if(contenthtml!=""){ //$NON-NLS-1$
					Document doc = Jsoup.parse(contenthtml);

					Elements mark = doc.getElementsByTag("mark"); //$NON-NLS-1$



					log.info("#mark "+mark.size()); //$NON-NLS-1$






					String type = "Completeness"; //$NON-NLS-1$



					List<String> Elements = new ArrayList<String>();


					for (Element element : mark) {
						String node = getString(element.text());
						//node = node.replace(".", "").replace(":", "").replace(" ", "");
						for(String Element :  Elements_Template){
							if(node.startsWith(Element)){
								Element e = (Element) element.parentNode();
								org.jsoup.select.Elements allEle = e.getAllElements();
								if(allEle.size()<=2 && allEle.size()>0){
									Node n = element.nextSibling();
									if(n!=null)
									if(n.toString().length()>6){
										Elements.add(Element);
										NumberFields++;
									}
								}else{
									for(int i=2;i<allEle.size();i++){
										org.jsoup.nodes.Element n = allEle.get(i);
										if(n!=null)
										if(n.toString().length()>6){
											Elements.add(Element);
											NumberFields++;
											break;
										}
									}
								}
								/*boolean flag = true;
								//while(flag){
									Node n = element.nextSibling();
									//for(org.jsoup.nodes.Element ele :e.getAllElements()){
									if(n.toString().length()>6){
										Elements.add(Element);
										NumberFields++;
									}
									if(e!=null){
										e = element.after(element);
										log.info("ee "+e); //$NON-NLS-1$
									}*/
								//element = (org.jsoup.nodes.Element) n;
								//}
								break;
							}
						}

					}

					//int n = getTotNumberTerms(mark);

					for (String field : Elements_Template) {

						if(!Elements.contains(field)){
							String rec = String.format("The field %s appears to be without content. Please provide additional information.", field);
							Annotation rule1 = new Annotation(id,type,0,0,rec);
							listannotation.add(rule1);
							log.trace(rec);
							id++;
						}

					}

				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return NumberFields;// listSentence.size();
	}

	private String getString(String in){
		return in.replace(":", "");
	}

	private int getTotNumberTerms(Elements elements) {
		String stringelements = ""; //$NON-NLS-1$
		for (Element element : elements) {
			stringelements +=element.text();
		}
		return stringelements.split(" ").length; //$NON-NLS-1$
	}





}
