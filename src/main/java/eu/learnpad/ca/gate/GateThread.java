package eu.learnpad.ca.gate;

import eu.learnpad.ca.rest.data.QualityCriteria;
import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.DocumentContent;
import gate.Factory;
import gate.FeatureMap;
import gate.ProcessingResource;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.creole.gazetteer.DefaultGazetteer;
import gate.event.StatusListener;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class GateThread extends Thread implements StatusListener{

	private Corpus corpus;
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(GateThread.class);
	private QualityCriteria qualitycriteria;


	private ThreadLocal<CorpusController> controller = new ThreadLocal<CorpusController>() {
		protected CorpusController initialValue() { return initPersistentGateResources();
		} };

		//private BlockingQueue<CorpusController> pool;


		/*public void init() {
		pool = new LinkedBlockingQueue<CorpusController>(); for(int i = 0; i < 10; i++) {
		    pool.add(initPersistentGateResources());
		  }
		}*/


		public void run() {
			long lStartTime = System.currentTimeMillis();
			//some tasks
			runProcessingResourcesforAll();
			long lEndTime = System.currentTimeMillis();
			long difference = lEndTime - lStartTime;

			log.trace("GATE Elapsed milliseconds: " + difference);

		}

		public GateThread(String content, QualityCriteria qc) {
			this.qualitycriteria = qc;
			CreateCorpusFromContent(content);
			//init();
		}

		public GateThread(File content) {
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
				List<String> processingResources = new ArrayList<String>();
				processingResources.add("gate.creole.tokeniser.DefaultTokeniser");
				processingResources.add("gate.creole.splitter.SentenceSplitter");
				processingResources.add("gate.creole.POSTagger");

				if(qualitycriteria.isNonAmbiguity()){
					processingResources.add("mark.chunking.GATEWrapper");

				}
				for(String res :processingResources) {
					log.info("\t* Loading " + res + " ... ");

					serialcorpusController.add((gate.LanguageAnalyser)Factory
							.createResource(res));
					log.info("done");
				}
				if(qualitycriteria.isNonAmbiguity() | qualitycriteria.isSimplicity()){
					log.info("\t* Loading gate.creole.gazetteer.DefaultGazetteer ... ");
					FeatureMap fm = Factory.newFeatureMap();
					fm.put(DefaultGazetteer.DEF_GAZ_CASE_SENSITIVE_PARAMETER_NAME, true);
					serialcorpusController.add((gate.LanguageAnalyser)Factory
							.createResource("gate.creole.gazetteer.DefaultGazetteer",fm));
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


		private void LoadJAPELenght(ArrayList<String> JAPLenght ){

			String sep = File.separator;
			JAPLenght.add("sentlen"+sep+"annotate_sent_len.jape");

			JAPLenght.add("sentlen"+sep+"annotate_sent_len_nominal.jape");


		}

		private void LoadJAPEActorUnclear(ArrayList<String> JAPEs){
			String sep = File.separator;
			JAPEs.add("passiveforms"+sep+"annotate_preliminary_passive_forms.jape");
			JAPEs.add("passiveforms"+sep+"annotate_passive_forms_RULE_ALT.jape");
			


		}
		private void LoadJAPEPragmaticAmbiguity(ArrayList<String> JAPEs){
			String sep = File.separator;
			JAPEs.add("pragmaticambiguity"+sep+"annotate_pragmatic_ambiguity_names_adjective.jape");
			JAPEs.add("pragmaticambiguity"+sep+"annotate_pragmatic_ambiguity_names_alone.jape");
			JAPEs.add("pragmaticambiguity"+sep+"annotate_pragmatic_ambiguity_names_specified.jape");



		}
		private void LoadJAPESyntacticAmbiguityCoordination(ArrayList<String> JAPEs){
			String sep = File.separator;
			JAPEs.add("coordambiguity"+sep+"annotate_coord_ambiguity_PREPROCESS.jape");
			JAPEs.add("coordambiguity"+sep+"annotate_coord_ambiguity_RULE_1.jape");
			JAPEs.add("coordambiguity"+sep+"annotate_coord_ambiguity_RULE_2.jape");



		}

		private void LoadJAPESyntacticAmbiguityAnaphoric(ArrayList<String> JAPEs){
			String sep = File.separator;
			JAPEs.add("anaphoricambiguity"+sep+"annotate_anaphoric_ambiguity_RULE_0.jape");
			JAPEs.add("anaphoricambiguity"+sep+"annotate_anaphoric_ambiguity_RULE_1.jape");
			JAPEs.add("anaphoricambiguity"+sep+"annotate_anaphoric_ambiguity_RULE_2.jape");



		}

		private Collection<FeatureMap> loadJAPE(ArrayList<String> JAPElist){
			Collection<FeatureMap> JapeCollection = new ArrayList<>();

			for(String jape :JAPElist){
				FeatureMap japeFM = Factory.newFeatureMap();
				URL japeFMFILE = GateThread.class.getClassLoader().getResource(jape);
				japeFM.put("grammarURL", japeFMFILE);

				JapeCollection.add(japeFM);
			}

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

		public void runProcessingResourcesforAll() {
			try{

				SerialAnalyserController pipeline = (SerialAnalyserController)Factory.duplicate( controller.get());

				//SerialAnalyserController pipeline = (SerialAnalyserController)Factory.duplicate( pool.take());

				// create an instance of a JAPE Transducer processing resource
				ArrayList<String> listJAPE = new ArrayList<String>();
				if(qualitycriteria.isSimplicity()){
					LoadJAPELenght(listJAPE);
				}
				if(qualitycriteria.isContentClarity()){
					LoadJAPEActorUnclear(listJAPE);
				}
				if(qualitycriteria.isNonAmbiguity()){
					LoadJAPESyntacticAmbiguityCoordination(listJAPE);
					LoadJAPESyntacticAmbiguityAnaphoric(listJAPE);
					LoadJAPEPragmaticAmbiguity(listJAPE);
				}
				Collection<FeatureMap> features = loadJAPE(listJAPE);
				for(FeatureMap feature :features){
					ProcessingResource japeTransducer = (ProcessingResource) Factory.createResource("gate.creole.Transducer", feature);
					// add the language resources to application, here SerialAccessController
					pipeline.add(japeTransducer);
				}



				log.info("Creating corpus from documents obtained...");
				pipeline.setCorpus(corpus);
				log.info("done");
				pipeline.addStatusListener(this);
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

		public Set<gate.Annotation> getSentence(){
			HashSet<String> hs = new HashSet<String>();
			hs.add("Sentence");
			return this.getAnnotationSet(hs);
		}

		public DocumentContent getDocumentContent() {
			return this.getCorpus().get(0).getContent();
		}

		public Set<Annotation> getAnnotationSet(Set<String> TypesRequired){
			try{

				for( Document doc : corpus){

					AnnotationSet defaultAnnotSet = doc.getAnnotations();

					Set<Annotation> peopleAndPlaces =
							new HashSet<Annotation>(defaultAnnotSet.get(TypesRequired));

					return peopleAndPlaces;

				} // for each doc
			}catch(Exception e){
				log.error(e.getMessage());


			}
			return null;
		}

		public Set<Annotation> getAnnotationSet(String TypeRequired, FeatureMap fe){
			try{

				for( Document doc : corpus){

					AnnotationSet defaultAnnotSet = doc.getAnnotations();

					Set<Annotation> peopleAndPlaces =
							new HashSet<Annotation>(defaultAnnotSet.get(TypeRequired,fe));

					return peopleAndPlaces;

				} // for each doc
				log.info("fine");
			}catch(Exception e){
				log.error(e.getMessage());


			}
			return null;
		}

		@Override
		public void statusChanged(String text) {

			log.trace(text);	

		}

		/*public void destroy() {
		for(CorpusController c : pool) 
			Factory.deleteResource(c);
	}*/
}
