package eu.learnpad.ca.analysis.syntacticambiguity;

import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.hcoref.CorefCoreAnnotations.CorefGraphAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.parser.lexparser.LexicalizedParserQuery;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.IntTuple;
import edu.stanford.nlp.util.Pair;
import edu.stanford.nlp.util.ScoredObject;
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
import eu.learnpad.ca.simplicity.juridicaljargon.Juridicaljargon;


public class SyntacticAmbiguity extends Thread implements AnalysisInterface{ 

	private Language language;

	private Integer numDefectiveSentences = 0;

	private CollaborativeContentAnalysis collaborativeContentInput;
	private AnnotatedCollaborativeContentAnalysis annotatedCollaborativeContent;

	private StaticContentAnalysis staticContentInput;
	private AnnotatedStaticContentAnalysis annotatedStaticContent;


	public SyntacticAmbiguity(CollaborativeContentAnalysis cca, Language lang){
		super();
		this.language=lang;
		collaborativeContentInput = cca;

	}


	public SyntacticAmbiguity(StaticContentAnalysis cca, Language lang){
		this.language=lang;
		staticContentInput = cca;

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
			checkSyntacticAmbiguity(collaborativeContentInput);	
		}

		if(staticContentInput!=null){
			checkSyntacticAmbiguity(staticContentInput);	
		}

	}


	private void checkSyntacticAmbiguity(StaticContentAnalysis staticContentInput) {
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


		/*LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
				"-maxLength", "80", "-retainTmpSubcategories");
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		tlp.setGenerateOriginalDependencies(true);
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

		String[] sent = {"This", "is", "an", "easy", "sentence", "." };
		Tree parse = lp.apply(Sentence.toWordList(sent));
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		Collection<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
		System.out.println(tdl);


		double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
		annotatedStaticContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
		annotatedStaticContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
		annotatedStaticContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
		annotatedStaticContent.setType("non_ambiguity");*/

	}


	private void checkSyntacticAmbiguity(
			CollaborativeContentAnalysis collaborativeContentInput) {
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
		int id=0;
		for (String sentence : listsentence) {

			List<Annotation> annotations =new ArrayList<Annotation>();
			id = checkdefect(sentence,c, id, annotations);
			if(annotations.size()>0){
				numDefectiveSentences++;
			}
			annotatedCollaborativeContent.setAnnotations(annotations);
			id++;
			/*LexicalizedParser lp = LexicalizedParser.loadModel(
					"edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz",
					"-maxLength", "80", "-retainTmpSubcategories");

			LexicalizedParserQuery lpq = lp.lexicalizedParserQuery();
			lpq.parse(tokenize(sentence));
			Tree t = lpq.getBestPCFGParse();
			System.out.println(sentence);
			System.out.println(t);
			System.out.println(lpq.getPCFGScore());
			System.out.println();
			int i = 1;
			List<ScoredObject<Tree>> kBest = lpq.getKBestPCFGParses(2);*/

			/*edu.stanford.nlp.pipeline.Annotation document = new edu.stanford.nlp.pipeline.Annotation(sentence);
			Properties props = new Properties();
			props.put("annotators", "tokenize, ssplit, pos, parse, lemma, ner, dcoref");
		    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		    pipeline.annotate(document);


            Map<Integer, CorefChain> graph = document.get(CorefChainAnnotation.class);
			 */

			/*double tmp = 0;
			for (ScoredObject<Tree> scoredObject : kBest) {
				if(tmp==0){
					tmp = scoredObject.score();
				}else
				if((tmp>=scoredObject.score()+1) | (tmp<=scoredObject.score()+1)){
					System.out.println("frase numero:"+i);
					System.out.println(scoredObject.score());
					System.out.println(scoredObject);
				}

				i++;
			}*/
			//System.out.println(graph);
			//System.out.println(coref);

			double qualitymmeasure = calculateOverallQualityMeasure(listsentence.size());
			annotatedCollaborativeContent.setOverallQuality(this.calculateOverallQuality(qualitymmeasure));
			annotatedCollaborativeContent.setOverallQualityMeasure(new DecimalFormat("##.##").format(qualitymmeasure)+"%");
			annotatedCollaborativeContent.setOverallRecommendations(this.calculateOverallRecommendations(qualitymmeasure));
			annotatedCollaborativeContent.setType("non_ambiguity");
			System.out.println(annotatedCollaborativeContent);

		}
	}

	private int checkdefect(String sentence,Content c,int nodeid,List<Annotation> annotations){
		Set<String> Listallp = PronominalExtensions.getAllPronouns();
		StringTokenizer tokenizer = new StringTokenizer(sentence);

		int precedentposition=0;

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if(Listallp.contains(token)){

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
				a.setType("non_ambiguity");
				a.setRecommendation("non_ambiguity");
				annotations.add(a);

			}
		}
		if(annotations.size()==0){
			c.setContent(sentence);
		}
		return nodeid;

	}

	private List<CoreLabel> tokenize(String input) {
		List<CoreLabel> list = new ArrayList<CoreLabel>();

		TokenizerFactory<CoreLabel> tokenizerFactory = 
				PTBTokenizer.factory(new CoreLabelTokenFactory(), "");

		List<CoreLabel> tokens = 
				tokenizerFactory.getTokenizer(
						new StringReader(input)).tokenize();



		return tokens;
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
