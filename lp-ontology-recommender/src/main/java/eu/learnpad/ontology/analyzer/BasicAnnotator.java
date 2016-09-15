package eu.learnpad.ontology.analyzer;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.learnpad.ontology.persistence.FileOntAO;
import eu.learnpad.ontology.recommender.RecommenderException;
import eu.learnpad.or.rest.data.Entity;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.GateConstants;
import gate.clone.ql.OntoRootGaz;
import gate.corpora.DocumentImpl;
import gate.corpora.RepositioningInfo;
import gate.creole.POSTagger;
import gate.creole.SerialAnalyserController;
import gate.creole.gazetteer.FlexibleGazetteer;
import gate.creole.morph.Morph;
import gate.creole.ontology.impl.sesame.OWLIMOntology;
import gate.creole.splitter.SentenceSplitter;
import gate.creole.tokeniser.DefaultTokeniser;
import gate.util.GateException;
import gate.util.Out;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.riot.Lang;

public class BasicAnnotator {

    /**
     * The Corpus Pipeline application to contain ANNIE
     */
    private SerialAnalyserController annieController;
    //private CorpusController annieController;
    private SerialAnalyserController ontoController;
    
    private OntModel model;
    
    public BasicAnnotator(String modelSetId) throws RecommenderException, GateException, IOException {
        this.model = FileOntAO.getInstance().getModelWithExecutionData(modelSetId);
            prepareModelExtract();

            // initialise GATE
            initGate();

            // initialise ANNIE:
            initAnnie();

            // initialise the onto lookup controller:
            initOnto();
    }

    public void prepareModelExtract() {

        String query = "prefix omm:   <http://ikm-group.ch/archiMEO/omm#> \n"
                + "prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
                + "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> \n"
                + "prefix owl:   <http://www.w3.org/2002/07/owl#> \n"
                + "CONSTRUCT {\n"
                + "omm:Performer  a  owl:Class .\n"
                + "?s rdf:type omm:Performer ; rdf:type owl:Class .\n"
                + "?s ?p ?o \n"
                + "}\n"
                + " WHERE { \n"
                + "	?s a omm:Performer ; ?p ?o .\n"
                + "	FILTER(?p = rdfs:label)\n"
                + "}";

        QueryExecution ex = QueryExecutionFactory.create(query, model);
        OntModel ontologyPerformerExtract = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        ex.execConstruct(ontologyPerformerExtract);
        FileOutputStream out = null;
        try {
            File smallOntologyFile = new File("C:/Data/Projects/LearnPAd/Testing/EmbeddedGateWithOntoGazetteer/src/main/resources/testing/LP_ME_ADOXX_MODELSET_28600_small_extract.ttl");
            if (smallOntologyFile.exists()) {
                smallOntologyFile.delete();
            }
            out = new FileOutputStream(smallOntologyFile);
            ontologyPerformerExtract.write(out, Lang.TTL.getName());
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(BasicAnnotator.class.getName()).log(Level.SEVERE, null, ex1);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex1) {
                    Logger.getLogger(BasicAnnotator.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

    }

    /**
     * Initialise the GATE environment
     *
     * @throws GateException
     */
    public void initGate() throws GateException, IOException {
        // initialise the GATE library
        Out.prln("Initialising GATE...");

        //copy plugins to working folder

//        if (Gate.getGateHome() == null) {
//            Gate.setGateHome(new File("C:\\Data\\Projects\\LearnPAd\\Testing\\EmbeddedGateWithOntoGazetteer\\src\\main\\resources\\gate"));
//        }
        if (Gate.getPluginsHome() == null) {
            Gate.setPluginsHome(new File("C:\\Data\\Projects\\LearnPAd\\Testing\\EmbeddedGateWithOntoGazetteer\\src\\main\\resources\\gate\\plugins"));
        }

        if (!Gate.isInitialised()) {
            Gate.runInSandbox(true);
            Gate.init();
        }

        // register the needed plugins
        Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Tools").toURI().toURL());
        Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Ontology_Tools").toURI().toURL());
        Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Ontology").toURI().toURL());
        Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(), "Gazetteer_Ontology_Based").toURI().toURL());
        Out.prln("...GATE initialised");
    }

    /**
     * Initialise the ANNIE system. This creates a "corpus pipeline" application
     * that can be used to run sets of documents through the extraction system.
     */
    public void initAnnie() throws GateException, IOException {
        Out.prln("Initialising ANNIE...; GATE plugins home: " + Gate.getPluginsHome());

        URL anniePluginsUrl = BasicAnnotator.class.getResource("/gate/plugins/ANNIE");
        Gate.getCreoleRegister().registerDirectories(anniePluginsUrl);
        URL toolsPluginsUrl = BasicAnnotator.class.getResource("/gate/plugins/Tools");
        Gate.getCreoleRegister().registerDirectories(toolsPluginsUrl);

        Gate.getCreoleRegister().registerComponent(gate.creole.tokeniser.SimpleTokeniser.class);
        Gate.getCreoleRegister().registerComponent(gate.creole.tokeniser.DefaultTokeniser.class);
        Gate.getCreoleRegister().registerComponent(gate.creole.POSTagger.class);
        Gate.getCreoleRegister().registerComponent(gate.creole.morph.Morph.class);

        // create a slim ANNIE pipeline by initialising a tokeniser, splitter, POS tagger and morpher
        DefaultTokeniser tokeniser = (DefaultTokeniser) Factory.createResource("gate.creole.tokeniser.DefaultTokeniser", Factory.newFeatureMap());
        SentenceSplitter splitter = (SentenceSplitter) Factory.createResource("gate.creole.splitter.SentenceSplitter", Factory.newFeatureMap());
        POSTagger postagger = (POSTagger) Factory.createResource("gate.creole.POSTagger", Factory.newFeatureMap());
        Morph morpher = (Morph) Factory.createResource("gate.creole.morph.Morph", Factory.newFeatureMap());

        annieController = (SerialAnalyserController) Factory.createResource("gate.creole.SerialAnalyserController");
        annieController.add(tokeniser);
        annieController.add(splitter);
        annieController.add(postagger);
        annieController.add(morpher);

        /*
		// load the ANNIE application from the saved state in plugins/ANNIE
		File pluginsHome = Gate.getPluginsHome();
		File anniePlugin = new File(pluginsHome, "ANNIE");
		File annieGapp = new File(anniePlugin, "ANNIE_with_morpher.gapp");
		annieController =
				(CorpusController) PersistenceManager.loadObjectFromFile(annieGapp);
         */
        Out.prln("...ANNIE loaded");
    } // initAnnie()

    /**
     * Initialise an onto root gazetter app:
     *
     * @throws GateException
     * @throws IOException
     */
    public void initOnto() throws GateException, IOException {

        // set up a new pipeline, based on ANNIE, but containing a flexible gazetteer with an OntoRootGazetter in it:
//                URL pluginsUrl = BasicAnnotator.class.getResource("/gate/plugins/Ontology");
//                Gate.getCreoleRegister().registerDirectories(pluginsUrl);
//                pluginsUrl = BasicAnnotator.class.getResource("/gate/plugins/Ontology_Tools");
//                Gate.getCreoleRegister().registerDirectories(pluginsUrl);
//                pluginsUrl = BasicAnnotator.class.getResource("/gate/plugins/Gazetteer_Ontology_Based");
//                Gate.getCreoleRegister().registerDirectories(pluginsUrl);
//                
//                Gate.getCreoleRegister().registerComponent(gate.creole.ontology.impl.sesame.OWLIMOntology.class);
//                Gate.getCreoleRegister().registerComponent(gate.clone.ql.OntoRootGaz.class);
//                Gate.getCreoleRegister().registerComponent(gate.creole.gazetteer.FlexibleGazetteer.class);
//                Gate.getCreoleRegister().registerComponent(gate.creole.SerialAnalyserController.class);
//                
        // first, we set up the ontology:
        FeatureMap ontParams = Factory.newFeatureMap();
//		File pluginsHome = Gate.getPluginsHome();
//		File anniePlugin = new File(pluginsHome, "ANNIE");
//		File ontologyFile = new File(anniePlugin, "LP_ME_ADOXX_MODELSET_28600_small.ttl");
//		URL ontoURL = ontologyFile.toURI().toURL();
//                URL ontoTestUrl = BasicAnnotator.class.getResource("/testing/LP_ME_ADOXX_MODELSET_28600_small.ttl");
//                File ontologyFile = new File("C:\\Data\\Projects\\LearnPAd\\Testing\\EmbeddedGateWithOntoGazetteer\\src\\main\\resources\\testing\\LP_ME_ADOXX_MODELSET_28600_small.ttl");
        File ontologyFile = new File("C:\\Data\\Projects\\LearnPAd\\Testing\\EmbeddedGateWithOntoGazetteer\\src\\main\\resources\\testing\\LP_ME_ADOXX_MODELSET_28600_small_extract.ttl");
//                File ontologyFile = new File("C:\\Temp\\learnpad\\ontology\\instances\\LP_ME_ADOXX_MODELSET_28600\\1\\LP_ME_ADOXX_MODELSET_28600.ttl");
        URL ontoTestUrl = ontologyFile.toURI().toURL();
        ontParams.put("loadImports", false);
        ontParams.put("turtleURL", ontoTestUrl);
        OWLIMOntology ontology = (OWLIMOntology) Factory.createResource("gate.creole.ontology.impl.sesame.OWLIMOntology", ontParams);

        // now we create the onto root gazetter containing the ontology
        FeatureMap orgParams = Factory.newFeatureMap();
        orgParams.put("ontology", ontology);
        orgParams.put("rootFinderApplication", annieController);
        OntoRootGaz org = (OntoRootGaz) Factory.createResource("gate.clone.ql.OntoRootGaz", orgParams);

        // create a flexible gazetteer to contain the onto root gazetteer:
        FeatureMap flexParams = Factory.newFeatureMap();
        flexParams.put("gazetteerInst", org);
        List<String> featureNames = new ArrayList<String>();
        featureNames.add("Token.root");
        flexParams.put("inputFeatureNames", featureNames);
        FlexibleGazetteer flexGaz = (FlexibleGazetteer) Factory.createResource("gate.creole.gazetteer.FlexibleGazetteer", flexParams);

        // finally, we create a pipeline that contains only the flexible gazetteer:
        ontoController = (SerialAnalyserController) Factory.createResource("gate.creole.SerialAnalyserController");
        ontoController.add(flexGaz);
        Out.prln("...Onto lookup loaded");
    }

    /**
     * Build a corpus from a single piece of text (a String)
     *
     * @param content The textual content to be analysed
     * @return A corpus containing only one document whose content is equal to
     * the input variable
     * @throws GateException
     */
    public static Corpus buildCorpus(String content) throws GateException {
        Corpus corpus = Factory.newCorpus("LearnPAdTestCorpus");
        FeatureMap params = Factory.newFeatureMap();
        params.put(gate.Document.DOCUMENT_STRING_CONTENT_PARAMETER_NAME, content);
        params.put("preserveOriginalContent", new Boolean(true));
        params.put("collectRepositioningInfo", new Boolean(true));
        Document doc = (gate.Document) Factory.createResource("gate.corpora.DocumentImpl", params);
        corpus.add(doc);
        return corpus;
    }

    /**
     * Build a corpus around the content that should be analysed and runs it
     * through the pipeline
     *
     * @param content
     * @return
     */
    public Corpus analyseContent(String content) {

        // create a GATE corpus and add a document with the same content as the input file
        Corpus corpus = null;

        try {
            corpus = buildCorpus(content);

            // tell the pipeline about the corpus and run it
            annieController.setCorpus(corpus);
            annieController.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return corpus;
    }

    /**
     * Build a corpus around the content that should be analysed and runs it
     * through the pipeline
     *
     * @param content
     * @return
     */
    public Corpus analyseContentOnto(String content) {

        // create a GATE corpus and add a document with the same content as the input file
        Corpus corpus = null;

        try {
            corpus = buildCorpus(content);

            // tell the two pipelines about the corpus and run it
            annieController.setCorpus(corpus);
            annieController.execute();
            ontoController.setCorpus(corpus);
            ontoController.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return corpus;
    }

    /**
     * Returns all tokens of a corpus in their proper order
     *
     * @param corpus
     * @return
     */
    public List<Annotation> getAllTokens(Corpus corpus) {

        Document doc = (DocumentImpl) corpus.iterator().next();
        Map<String, Integer> ret = new HashMap<String, Integer>();
        AnnotationSet defaultAnnotSet = doc.getAnnotations();
        Set annotTypesRequired = new HashSet();
        annotTypesRequired.add("Token");
        AnnotationSet annos = defaultAnnotSet.get(annotTypesRequired);
        List<Annotation> tokens = gate.Utils.inDocumentOrder(annos);
        return tokens;
    }

    /**
     * Extracts and returns all the nouns from a given textual content
     *
     * @param content
     * @return
     */
    public Map<String, Integer> getNouns(Corpus corpus) {

        Document doc = (DocumentImpl) corpus.iterator().next();
        Map<String, Integer> ret = new HashMap<String, Integer>();
        AnnotationSet defaultAnnotSet = doc.getAnnotations();
        Set annotTypesRequired = new HashSet();
        annotTypesRequired.add("Token");
        Set<Annotation> tokens
                = new HashSet<Annotation>(defaultAnnotSet.get(annotTypesRequired));

        // iterate over all token annotations
        Iterator it = tokens.iterator();
        Annotation currAnnot;
        while (it.hasNext()) {
            currAnnot = (Annotation) it.next();
            FeatureMap features = currAnnot.getFeatures();

            // check if POS is noun. If so, add the string of the token to the set of returned strings
            if (((String) features.get("category")).startsWith("NN")) {
                String noun = (String) features.get("root");
                if (!ret.containsKey(noun)) {
                    ret.put(noun, 1);
                } else {
                    ret.put(noun, ret.get(noun) + 1);
                }
            }
        }
        return ret;
    }

    /**
     * performs an ontology lookup on a given text, i.e. identifies all ontology
     * instances in the text and returns them in the form of an Entities object
     * (where only URIs are set)
     *
     * @param content
     * @return
     */
    public List<Entity> entityLookup(String content) {

        List<Entity> entities = new ArrayList<Entity>();
        Corpus corpus = analyseContentOnto(content);

        // filter out the lookup annotations
        Document doc = (DocumentImpl) corpus.iterator().next();
        FeatureMap features = doc.getFeatures();
        RepositioningInfo info = (RepositioningInfo) features.get(GateConstants.DOCUMENT_REPOSITIONING_INFO_FEATURE_NAME);
        AnnotationSet defaultAnnotSet = doc.getAnnotations();
        Set annotTypesRequired = new HashSet();
        annotTypesRequired.add("Lookup");
        Set<Annotation> instances = new HashSet<Annotation>(defaultAnnotSet.get(annotTypesRequired));

        // find those lookup annotations that have a "URI" feature
        for (Annotation annotation : instances) {
            FeatureMap curFeatures = annotation.getFeatures();
            if (curFeatures.containsKey("URI") && curFeatures.containsKey("classURI")) {
                for (Object key : curFeatures.keySet()) {
                    System.out.println(key + ": " + curFeatures.get(key));
                }

                // for such an annotation: create an Entity instance with id, type and TextMarker, add it to entities
                Entity entity = new Entity();
                entity.setContextArtifactId((String) curFeatures.get("URI"));
                entity.setType((String) curFeatures.get("classURI"));
//                TextMarker textMarker = new TextMarker();
//                int startPos = (int) annotation.getStartNode().getOffset().longValue();
//                int startPosAbsolute = (int) info.getOriginalPos(startPos);
//                int endPos = (int) annotation.getEndNode().getOffset().longValue();
//                int length = endPos - startPos;
//                textMarker.setStartPosition(startPosAbsolute);
//                textMarker.setLength(length);
//                entity.setTextMarker(textMarker);

                entities.add(entity);
            }
        }

        return entities;

    }

    private void copyFromClasspathToWorkingDirectory(String path) {
        InputStream is = BasicAnnotator.class.getResourceAsStream(path);
        Path output = Paths.get("C:/Temp/test");
        

    }

}
