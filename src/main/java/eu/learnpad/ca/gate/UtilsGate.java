package eu.learnpad.ca.gate;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.ProcessingResource;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.util.GateException;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class UtilsGate {

	private Corpus corpus;
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UtilsGate.class);
	
	private ThreadLocal<CorpusController> controller = new ThreadLocal<CorpusController>() {
		protected CorpusController initialValue() { return initPersistentGateResources();
		} };

	private BlockingQueue<CorpusController> pool;
	
	
	public void init() {
		pool = new LinkedBlockingQueue<CorpusController>(); for(int i = 0; i < 10; i++) {
		    pool.add(initPersistentGateResources());
		  }
		}
		

	public UtilsGate(String content) {
		CreateCorpusFromContent(content);
		//init();
	}

	public UtilsGate(File content) {
		CreateCorpusFromFile(content);
	}


	public Corpus getCorpus() {
		return corpus;
	}
	
	

	private CorpusController initPersistentGateResources() {
		SerialAnalyserController  serialcorpusController = null;
	    try {
	        //Corpus corpus = Factory.newCorpus("New Corpus");
	    	 serialcorpusController = (SerialAnalyserController) Factory.createResource("gate.creole.SerialAnalyserController");
	        String[] processingResources = {"gate.creole.tokeniser.DefaultTokeniser",
			"gate.creole.splitter.SentenceSplitter"};
	        for(int pr = 0; pr < processingResources.length; pr++) {
				log.info("\t* Loading " + processingResources[pr] + " ... ");
				
				serialcorpusController.add((gate.LanguageAnalyser)Factory
						.createResource(processingResources[pr]));
				log.info("done");
			}
	        
	       
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	       return serialcorpusController;
	}

	private void CreateCorpusFromContent(String content){

		try {
			// create a GATE corpus and add a document
			// argument
			corpus = Factory.newCorpus("Corpus");


			Document doc = (Document)
					Factory.newDocument(content);
			corpus.add(doc);

		} catch (ResourceInstantiationException e) {
			log.error(e.getMessage());

		}finally {
			Factory.deleteResource(corpus);
		}


	}


	private void CreateCorpusFromFile(File TxtFile){
		try {
			// create a GATE corpus and add a document
			// argument
			Corpus corpus = Factory.newCorpus("Corpus");


			Document doc = (Document)
					Factory.newDocument(TxtFile.toURI().toURL());
			corpus.add(doc);

		} catch (ResourceInstantiationException | MalformedURLException e) {
			log.error(e.getMessage());


		}

	}


	private Collection<FeatureMap> loadJAPE(){
		Collection<FeatureMap> JapeCollection = new ArrayList<>();
		FeatureMap sent_len = Factory.newFeatureMap();
		URL annotate_sent_len = UtilsGate.class.getClassLoader().getResource("annotate_sent_len.jape");
			sent_len.put("grammarURL", annotate_sent_len);

		JapeCollection.add(sent_len);

		FeatureMap len_nominal = Factory.newFeatureMap();
		URL annotate_sent_len_nominalURL = UtilsGate.class.getClassLoader().getResource("annotate_sent_len_nominal.jape");//.getResourceAsStream("annotate_sent_len_nominal.jape");
		
		len_nominal.put("grammarURL", annotate_sent_len_nominalURL);

		JapeCollection.add(len_nominal);
		return JapeCollection;
	}
	
	public void runProcessingResources() {
		try{
			

			
			SerialAnalyserController pipeline = (SerialAnalyserController)Factory.duplicate( controller.get());
	
			//SerialAnalyserController pipeline = (SerialAnalyserController)Factory.duplicate( pool.take());
			
			log.info("Creating corpus from documents obtained...");
			pipeline.setCorpus(corpus);
			log.info("done");

			log.info("Running processing resources over corpus...");
			pipeline.execute();
			log.info("done");
			
			Factory.deleteResource(pipeline);
			
		}catch(GateException     e){
			log.error(e.getMessage());
		} 
	}
	
	public void runProcessingResourcesforLenght() {
		try{
			/*String[] processingResources = {"gate.creole.tokeniser.DefaultTokeniser",
			"gate.creole.splitter.SentenceSplitter"};
			SerialAnalyserController pipeline = (SerialAnalyserController)Factory
					.createResource("gate.creole.SerialAnalyserController");

			for(int pr = 0; pr < processingResources.length; pr++) {
				log.info("\t* Loading " + processingResources[pr] + " ... ");
				pipeline.add((gate.LanguageAnalyser)Factory
						.createResource(processingResources[pr]));
				log.info("done");
			}*/

			
			SerialAnalyserController pipeline = (SerialAnalyserController)Factory.duplicate( controller.get());
			
			//SerialAnalyserController pipeline = (SerialAnalyserController)Factory.duplicate( pool.take());
			
			// create an instance of a JAPE Transducer processing resource
			Collection<FeatureMap> features = loadJAPE();
			for(FeatureMap feature :features){
				ProcessingResource japeTransducer = (ProcessingResource) Factory.createResource("gate.creole.Transducer", feature);
				// add the language resources to application, here SerialAccessController
				pipeline.add(japeTransducer);
			}

			

			log.info("Creating corpus from documents obtained...");
			pipeline.setCorpus(corpus);
			log.info("done");

			log.info("Running processing resources over corpus...");
			pipeline.execute();
			
			Factory.deleteResource(pipeline);
			
			log.info("done");
		}catch(GateException     e){
			log.error(e.getMessage());
		} /*finally{
			//return worker to the pool
			pool.add(app1);
		}*/
	}

	public Set<Annotation> getAnnotationSet(Set<String> TypesRequired){
		try{

			for( Document doc : corpus){

				AnnotationSet defaultAnnotSet = doc.getAnnotations();
				Set<String> annotTypesRequired = new HashSet<>();
				annotTypesRequired.addAll(TypesRequired);
				//annotTypesRequired.add("Sent-Len");
				//annotTypesRequired.add("Sent-Long");
				//annotTypesRequired.add("Split");
				//annotTypesRequired.add("SpaceToken");
				Set<Annotation> peopleAndPlaces =
						new HashSet<Annotation>(defaultAnnotSet.get(annotTypesRequired));

				return peopleAndPlaces;

			} // for each doc
			log.info("fine");
		}catch(Exception e){
			log.error(e.getMessage());


		}
		return null;
	}

	public void destroy() {
		for(CorpusController c : pool) 
			Factory.deleteResource(c);
	}
}
