/*
 *  TestOntologyAPI.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: Test4Develop.java 15934 2012-07-13 12:37:22Z ian_roberts $
 */
package gate.creole.ontology.impl.test;

import gate.creole.ontology.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ontology.OConstants.QueryLanguage;
import gate.creole.ontology.impl.sesame.OWLIMOntology;
import gate.creole.ontology.impl.sesame.OntologyLR;
import gate.creole.ontology.impl.sesame.OntologyServiceImplSesame;
import gate.creole.ontology.impl.sesame.UtilTupleQueryIterator;
import gate.util.ClosableIterator;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Simple test class that load an ontology available online and accesses
 * its content via the ontology API
 */
public class Test4Develop {
  File testingDir = null;
  File owlimConfig = null;
  File configDir = null;
  File tmpDir = null;

  // establish file paths etc.
  @Before
  public void setUp() throws Exception {
    System.out.println("Starting up");
    Gate.init();
    File pluginHome = 
        new File(new File(Gate.getGateHome(), "plugins"),
                 "Ontology");
    System.out.println("Plugins Home is "+pluginHome.getAbsolutePath());
    Gate.getCreoleRegister().registerDirectories(
            pluginHome.toURI().toURL());
    // Read the log4j properties from the plugin directory
    /*
    File log4jPropsFile = new File(pluginHome,"log4j.properties");
    Properties props = new Properties();
    FileInputStream ps = new FileInputStream(log4jPropsFile);
    props.load(ps);
    ps.close();
    System.setProperties(props);
     * */

    testingDir = new File(pluginHome,"test");
    assertTrue(testingDir.exists());
    configDir = new File(pluginHome,"config");
    assertTrue(configDir.exists());
    // 1) create a CreateSesameOntology ontology
    // First check if the file exists ....
    owlimConfig = new File (configDir,"owlim-max-partial-persist.ttl");
    assertTrue(owlimConfig.exists());
    tmpDir = getTmpDir();
    assertTrue(tmpDir.exists());
    System.out.println("Start up complete");
  }

  // easiest way to run this test:
  // 1) mkdir /tmp/repo
  // 2) repeat: "rm -rf /tmp/repo/* ; ant test4develop"
  @Test
  public void testCreateSesameOntology() throws Exception {
    /*
    System.out.println("++++ RUNNING testCreateSesameOntology");
    FeatureMap fm = Factory.newFeatureMap();
    fm.put("repositoryID", "testID");
    fm.put("repositoryLocation","/tmp/repo");
    fm.put("configFile","/home/johann/gate-OAPI/plugins/Ontology/config/owlim-max-partial-persist.ttl");
    CreateSesameOntology ontology = (CreateSesameOntology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.CreateSesameOntology", fm);


    File ontoDir = new File(testingDir,"ontologies");
    File wineOnto = new File(ontoDir,"wine.rdf");

    //checkWineStuff(ontology);
    //checkWineStuff2(ontology);
    //checkBNodes(ontology);
    ontology.cleanup();
     * */
  }

  @Test
  public void testOWLIMOntology() throws Exception {
    System.out.println("++++ RUNNING testOWLIMOntology");
    FeatureMap fm = Factory.newFeatureMap();
    //fm.put("baseURI", "http://base.uri/#");
    //fm.put("rdfXmlURL","/home/johann/gate-OAPI/plugins/Ontology/test/ontologies/test1.rdfxml.owl");
    //fm.put("dataDirectoryName","/tmp/repo");
    OWLIMOntology ontology = (OWLIMOntology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);


    Set<Literal> literals = new HashSet<Literal>();
    Set<LiteralOrONodeID> lonodes = new HashSet<LiteralOrONodeID>();


    Literal l1 = new Literal("string1");
    System.out.println("Literal l1 hash: "+l1.hashCode());
    literals.add(l1);
    Literal l2 = new Literal("string"+1);
    System.out.println("Literal l2 hash: "+l2.hashCode());
    literals.add(l2);
    Literal l3 = new Literal("string1");
    System.out.println("Literal l3 hash: "+l3.hashCode());
    literals.add(l3);

    assertEquals(l1,l2);
    assertEquals(l1,l3);
    assertEquals(l2,l3);

    assertTrue(literals.contains(l1));
    assertTrue(literals.contains(l2));
    assertTrue(literals.contains(l3));

    for (Literal l : literals) {
      System.out.println("From set: "+l);
    }

    assertEquals(1, literals.size());

    lonodes.add(new gate.creole.ontology.impl.LiteralOrONodeIDImpl(new Literal("string1")));
    lonodes.add(new gate.creole.ontology.impl.LiteralOrONodeIDImpl(new Literal("string"+1)));
    lonodes.add(new gate.creole.ontology.impl.LiteralOrONodeIDImpl(new Literal("strin"+"g1")));

    assertEquals(1, lonodes.size());


    //checkWineStuff(ontology);
    //checkWineStuff2(ontology);
    //checkWineStuff3(ontology);
    //checkBNodes(ontology);
    //checkOInstances1(ontology);
    //checkQuery01(ontology);
    //checkOClasses01(ontology);
    /*
    AnnotationProperty anp2 = (AnnotationProperty)
        ontology.getProperty(ontology.createOURI(OConstants.RDFS.LABEL));

    System.out.println("Annotation property label2: "+anp2.getOURI().toTurtle());

    AnnotationProperty anp3 =
        ontology.getAnnotationProperty(ontology.createOURI(OConstants.RDFS.LABEL));
    System.out.println("Annotation property label3: "+anp3.getOURI().toTurtle());


    OURI theURI = ontology.getOntologyURI();
    System.out.println("BEFORE Got the ontology uri: "+theURI);
    ontology.setOntologyURI(ontology.createOURI("http://some.default.uri/#"));
    theURI = ontology.getOntologyURI();
    System.out.println("AFTER Got the ontology uri: "+theURI);

    ontology.setOntologyAnnotation(anp2, new Literal("Test1"));
    List<Literal> ls = ontology.getOntologyAnnotationValues(anp2);
    System.out.println("Ontology labels: "+ls);

    ontology.setDefaultNameSpace("http://some.test.uri/here#");
    OURI genURI1 = ontology.generateOURI(null);
    ontology.addOClass(genURI1);
    OURI genURI2 = ontology.generateOURI(null);
    System.out.println("Got URI1: "+genURI1);
    System.out.println("Got URI2: "+genURI2);

    boolean contains1 = ontology.getService().containsURI(genURI1);
    boolean contains2 = ontology.getService().containsURI(genURI2);
    System.out.println("Contains uri 1: "+contains1);
    System.out.println("Contains uri 2: "+contains2);

  */

    ontology.cleanup();
  }

  private void checkWineStuff(Ontology ontology) throws FileNotFoundException, IOException {
    File ontoDir = new File(testingDir,"ontologies");
    File wineOnto = new File(ontoDir,"wine.rdf");

    assert(wineOnto.exists());
    FileInputStream is = new FileInputStream(wineOnto);
    ontology.readOntologyData(is, null, OConstants.OntologyFormat.RDFXML, false);
    is.close();
    System.out.println("Ontology loaded: "+wineOnto.getAbsolutePath());

    // Try to get the ontology URI
    OntologyServiceImplSesame service = ((OntologyLR)ontology).getService();
    //System.out.println("Trying to find ontology URI before loading");
    //List<OURI> ontoURIs = ontology.getOntologyURIs();
    //System.out.println("Got the ontology URIs: "+ontoURIs);


    System.out.println("Resolving imports");
    ontology.resolveImports(new HashMap<String,String>());
    System.out.println("Imports loaded");

    //System.out.println("Trying to find ontology URI after loading");
    //ontoURIs = ontology.getOntologyURIs();
    //System.out.println("Got the ontology URIs: "+ontoURIs);


    System.out.println("Checking subclasses of wine using ontology API:");
    OClass wine = ontology.getOClass(new URI("http://www.w3.org/TR/2003/CR-owl-guide-20030818/wine#Wine",false));
    System.out.println("Have class wine from wine: "+wine.getURI());
    Set<OClass> subclasses = wine.getSubClasses(OConstants.DIRECT_CLOSURE);
    System.out.print("Direct subclasses of wine from wine ("+subclasses.size()+"): ");
    for (OClass c : subclasses) {
      System.out.print(c.getURI()+" ");
    }
    System.out.println();
    
    OClass wine2 = ontology.getOClass(new URI("http://www.w3.org/TR/2003/CR-owl-guide-20030818/food#Wine",false));
    System.out.println("Have class wine from food: "+wine2.getURI());
    subclasses = wine2.getSubClasses(OConstants.DIRECT_CLOSURE);
    System.out.print("Direct subclasses of wine from food ("+subclasses.size()+"): ");
    for (OClass c : subclasses) {
      System.out.print(c.getURI()+" ");
    }
    System.out.println();
    /*
    System.out.println();
    subclasses = wine.getSubClassesOld(OConstants.TRANSITIVE_CLOSURE);
    System.out.print("All subclasses of wine ("+subclasses.size()+"): ");
    for (OClass c : subclasses) {
      if(c.toString().equals("Wine")) {
        System.out.print("######");
      }
      System.out.print(c+" ");
    }
    System.out.println();
    */

  }

  private void checkWineStuff2(Ontology ontology) throws FileNotFoundException, IOException {
    File ontoDir = new File(testingDir,"ontologies");
    File wineOnto = new File(ontoDir,"wine.rdf");

    assert(wineOnto.exists());
    FileInputStream is = new FileInputStream(wineOnto);
    ontology.readOntologyData(is, "http://base.uri/#", OConstants.OntologyFormat.RDFXML, false);
    is.close();
    System.out.println("Ontology loaded: "+wineOnto.getAbsolutePath());
    System.out.println("Resolving imports");
    ontology.resolveImports(new HashMap<String,String>());
    System.out.println("Imports loaded");

    OntologyServiceImplSesame service =
        ((OntologyLR)ontology).getService();

    String wineURIBaseString = "http://www.w3.org/TR/2003/CR-owl-guide-20030818/wine#";
    String wineryString = wineURIBaseString+"Winery";

    OURI wURI = ontology.createOURI(wineryString);
    OClass wClass = ontology.getOClass(wURI);
    Set<OInstance> inds = ontology.getOInstances(wClass, OConstants.Closure.DIRECT_CLOSURE);
    for(OInstance i : inds) {
      System.out.println("Got individual: "+i);
    }

  }

  private void checkWineStuff3(Ontology ontology) throws FileNotFoundException, IOException {
    File ontoDir = new File(testingDir,"ontologies");
    File wineOnto = new File(ontoDir,"wine.rdfxml.owl");

    assert(wineOnto.exists());
    FileInputStream is = new FileInputStream(wineOnto);
    ontology.readOntologyData(is, null, OConstants.OntologyFormat.RDFXML, false);
    is.close();
    System.out.println("Ontology loaded: "+wineOnto.getAbsolutePath());
    System.out.println("Resolving imports");
    ontology.resolveImports(new HashMap<String,String>());
    System.out.println("Imports loaded");

    OntologyServiceImplSesame service =
        ((OntologyLR)ontology).getService();

    System.out.println("Default name space is "+ontology.getDefaultNameSpace());
    // get the super class(es) of PetiteSyrah and Medoc
    OURI w1 = ontology.createOURIForName("Medoc");
    OClass w1c = ontology.getOClass(w1);
    Set<OClass> w1csuper = w1c.getSuperClasses(OConstants.Closure.DIRECT_CLOSURE);
    System.out.println("SUperclasses of Medoc: "+w1csuper);
    Set<OClass> w1csub = w1c.getSubClasses(OConstants.Closure.DIRECT_CLOSURE);
    System.out.println("Subclasses of Medoc: "+w1csub);

  }

  private void checkBNodes1(Ontology ontology) throws FileNotFoundException, IOException {
    File ontoDir = new File(testingDir,"ontologies");
    File wineOnto = new File(ontoDir,"wine.rdf");

    assert(wineOnto.exists());
    FileInputStream is = new FileInputStream(wineOnto);
    ontology.readOntologyData(is, "http://base.uri/#", OConstants.OntologyFormat.RDFXML, false);
    is.close();

    OntologyServiceImplSesame service =
        ((OntologyLR)ontology).getService();

    Set<OClass> classes = ontology.getOClasses(false);
    for(OClass c : classes) {
      System.out.println("Class: "+c.getURI());
    }
  }
  private void checkOInstances1(Ontology ontology) throws FileNotFoundException, IOException {
    File ontoDir = new File(testingDir,"ontologies");
    File wineOnto = new File(ontoDir,"wine.rdf");

    assert(wineOnto.exists());
    FileInputStream is = new FileInputStream(wineOnto);
    ontology.readOntologyData(is, "http://base.uri/#", OConstants.OntologyFormat.RDFXML, false);
    is.close();

    OntologyServiceImplSesame service =
        ((OntologyLR)ontology).getService();

    ClosableIterator<OInstance> insts = service.getInstancesIterator(null,null);
    while(insts.hasNext()) {
      System.out.println("Instance: "+insts.next());
    }


    Set<OInstance> instances = ontology.getOInstances();
    for(OInstance i : instances) {
      System.out.println("Instance: "+i.getURI());
    }
  }



  private void checkQuery01(Ontology ontology) throws FileNotFoundException, IOException {
    File ontoDir = new File(testingDir,"ontologies");
    File theOnto = new File(ontoDir,"test1.owl.rdf");

    assert(theOnto.exists());
    FileInputStream is = new FileInputStream(theOnto);
    ontology.readOntologyData(is, "", OConstants.OntologyFormat.RDFXML, false);
    is.close();

    OntologyServiceImplSesame service =
        ((OntologyLR)ontology).getService();

    // Test new tuple query stuff
    UtilTupleQueryIterator q = new UtilTupleQueryIterator(
        service.getSesameManager(),
        "select distinct ?x { ?a ?b ?x }",QueryLanguage.SPARQL);

    while(q.hasNext()) {
      //String s = q.nextFirstAsString();
      //System.out.println("String: "+s);
      LiteralOrONodeID ret = q.nextFirst();
      if(ret.isLiteral()) {
        Literal l = ret.getLiteral();
        System.out.println("Got literal: "+l);
      } else {
        ONodeID n = ret.getONodeID();
        System.out.println("Got node: "+n);
      }
      System.out.println("As turtle: "+ret.toTurtle());
    }

  }

  public void checkOClasses01(Ontology ontology) throws FileNotFoundException, IOException {
    File ontoDir = new File(testingDir,"ontologies");
    File theOnto = new File(ontoDir,"test1.rdfxml.owl");

    assert(theOnto.exists());
    FileInputStream is = new FileInputStream(theOnto);
    ontology.readOntologyData(is, "http://base.uri/#", OConstants.OntologyFormat.RDFXML, false);
    is.close();

    OntologyServiceImplSesame service =
        ((OntologyLR)ontology).getService();

    // test getting all top classes via standard OAPI
    Set<OClass> topclasses = ontology.getOClasses(true);
    for (OClass c : topclasses) {
      System.out.println("Top class: "+c);
    }

    OClass class04 = null;
    OClass bnode = null;
    Set<OClass> allclasses = ontology.getOClasses(false);
    for (OClass c : allclasses) {
      System.out.println("All class: "+c);
      if(c.getONodeID().isAnonymousResource()) {
        bnode = c;
      } else if(c.getONodeID().getResourceName().equals("Class04")) {
        class04 = c;
      }
    }

    if(bnode != null) {
      System.out.println("Found a bnode: "+bnode);
      Set<OClass> subclasses =
          service.getSubClasses(bnode.getONodeID(), OConstants.Closure.DIRECT_CLOSURE);
      System.out.println("Found direct subclasses of bnode: "+subclasses.size());
      for (OClass c : subclasses) {
        System.out.println("Direct subclass: "+c);
      }
      subclasses =
          service.getSubClasses(bnode.getONodeID(), OConstants.Closure.TRANSITIVE_CLOSURE);
      System.out.println("Found all subclasses of bnode: "+subclasses.size());
      for (OClass c : subclasses) {
        System.out.println("All subclass: "+c);
      }
    } else {
      System.err.println("No bnode");
    }
    
    if(class04 != null) {
      System.out.println("Found class04: "+class04);
      Set<OClass> subclasses =
          service.getSubClasses(class04.getONodeID(), OConstants.Closure.DIRECT_CLOSURE);
      System.out.println("Found direct subclasses of class04: "+subclasses.size());
      for (OClass c : subclasses) {
        System.out.println("Direct subclass: "+c);
      }
      subclasses =
          service.getSubClasses(class04.getONodeID(), OConstants.Closure.TRANSITIVE_CLOSURE);
      System.out.println("Found all subclasses of class04: "+subclasses.size());
      for (OClass c : subclasses) {
        System.out.println("All subclass: "+c);
      }
    } else {
      System.err.println("*** No class04");
    }

    ontology.removeOClass(class04);



  }


  private void printOResourceSet(Set<? extends OResource> set) {
    for(OResource res : set ) {
      System.out.println("Class="+res.getClass()+" name="+res.getName()+" uri="+res.getURI());
    }
  }


  public File getTmpDir() {
    String tmpDirName = "/tmp";  // fallback for Linux
    String tmplocation = System.getProperty("run.java.io.tmpdir");
     System.out.println("run.java.io.tmpdir is "+tmplocation);
     if(tmplocation == null) {
       tmplocation = System.getProperty("java.io.tmpdir");
       System.out.println("java.io.tmpdir is "+tmplocation);
     }
     if(tmplocation != null) {
       tmpDirName = tmplocation;
     }
     File td = new File(tmpDirName);
     return td;
  }

}


// OLD CODE FOR REFERENCE
    //System.err.println("Got top classes: "+classes.size());
    // import the files necessary for reasoning
    //File owlFile = new File(configDir,"owl.rdfs");
    //assert(owlFile.exists());
    /* this is now done in the LR
    System.out.println("Importing owl.rdfs");
    ontology.readOntologyData(owlFile, "http://www.w3.org/2002/07/owl#", true, OConstants.OntologyFormat.RDFXML);
    // import the rdf-schema stuff
    File rdfSchema = new File(configDir,"rdf-schema.rdf");
    assert(rdfSchema.exists());
    System.out.println("Importing rdf-schema.rdf");
    ontology.readOntologyData(owlFile, "http://www.w3.org/2000/01/rdf-schema#", true, OConstants.OntologyFormat.RDFXML);
    // should still show zero classes ...
     * */
    /*
    classes = ontology.getOClasses(true);
    System.err.println("After system imports got top classes: "+classes.size());

    // now load some user data
    File ontoDir = new File(testingDir,"ontologies");
    File demoOnto = new File(ontoDir,"demo.owl");
    System.out.println("ontology file is now "+demoOnto.getAbsolutePath());
    assert(demoOnto.exists());
    ontology.readOntologyData(demoOnto, "http://base.uri/#", OConstants.OntologyFormat.RDFXML, false);
    classes = ontology.getOClasses(true);
    System.err.println("After kiad got top classes: "+classes.size());
    // Try to add a class manually
    ontology.addOClass(new URI("http://x.y.com/#stuff",false));
    // TODO: add an ontology API method to create an uri from just the
    // fragment identifier
    classes = ontology.getOClasses(true);
    System.err.println("After adding class got top classes: "+classes.size());
    printOResourceSet(classes);
    Vector<OClass> classesvec = new Vector<OClass>(classes);
    if(classesvec.get(0).equals(classesvec.get(1))) {
      System.out.println("Classes one and two are euqal");
    } else {
      System.out.println("Classes one and two are different");
    }
    if(classesvec.get(0).equals(classesvec.get(0))) {
      System.out.println("Classes one and one are euqal");
    } else {
      System.out.println("Classes one and one are different");
    }
    OClass c1 = classesvec.get(0);
    System.out.println("Add a new class as a subclass of "+c1);
    OClass cn1 = ontology.addOClass(new URI("http://x.y.com/#otherstuff",false));
    c1.addSubClass(cn1);
    classes = ontology.getOClasses(false);
    System.out.println("All classes after adding otherstuff");
    printOResourceSet(classes);
    System.out.println("Find all the direct superclasses of otherstuff");
    classes = cn1.getSuperClasses(OConstants.DIRECT_CLOSURE);
    printOResourceSet(classes);
    classes = cn1.getSuperClasses(OConstants.TRANSITIVE_CLOSURE);
    printOResourceSet(classes);
    File protonuOnto = new File(ontoDir,"protonu.owl");
    System.out.println("Loading the protonu ontology");
    ontology.readOntologyData(protonuOnto, "http://base.uri2/#", OConstants.OntologyFormat.RDFXML, false);
    classes = ontology.getOClasses(false);
    System.out.println("Have now classes: "+classes.size());
    Set<URI> iuris = ontology.getImportURIs();
    System.out.println("Found "+iuris.size()+"Import URIs: "+iuris);
    Map<String,String> mappings = new HashMap<String,String>();
    mappings.put("http://proton.semanticweb.org/2005/04/protont", "");
    mappings.put("http://proton.semanticweb.org/2005/04/protons",
        "file:///home/johann/gate-OAPI/plugins/Ontology/test/ontologies/protons.owl");
    ontology.resolveImports(mappings);
    classes = ontology.getOClasses(false);
    System.out.println("Have now classes after importing: "+classes.size());
    // Test the hasClass method
    boolean hasit;
    OntologyServiceImplSesame service =
        ((OntologyLR)ontology).getService();
    hasit = service.hasClass("http://x.y.com/#otherstuff");
    System.out.println("Should have this and have: "+hasit);
    hasit = service.hasClass("http://a.s.d.f/#sdfsdfsdf");
    System.out.println("SHould not have this and have: "+hasit);
    Set<RDFProperty> props = ontology.getPropertyDefinitions();
    System.out.println("Found properties: "+props.size());
    for(RDFProperty p : props) {
      System.out.println("Property: "+p);
    }
    Set<ObjectProperty> oprops = ontology.getObjectProperties();
    System.out.println("Found object properties: "+props.size());
    for(RDFProperty p : props) {
      System.out.println("OProperty: "+p);
    }
     * */

  /*
  public void testSesameManager() throws IOException
    SesameManager mgr = new SesameManager();
    // create a directory for the unmanaged repository within the test
    File repoDir = new File(testingDir,"unmanaged");
    if(!repoDir.exists()) {
      repoDir.mkdir();
    }
    String configString = FileUtils.readFileToString(owlimConfig);
    mgr.createUnmanagedRepository(repoDir,configString);

    //printTuples4Query(mgr,"select ?a ?b ?c where {?a ?b ?c}");
    //FileUtils.deleteDirectory(repoDir);
  }
   * */

  // load a known ontology and access it
  // through the API objects
  /*
  public void testLoadingOWLOntology() throws MalformedURLException,
          ResourceInstantiationException {
    FeatureMap fm = Factory.newFeatureMap();
    File demoFile = new File(testingDir,"demo.owl");
    URL url = demoFile.toURI().toURL();
    fm.put("rdfXmlURL", url);
    fm.put("defaultNameSpace", "http://www.owl-ontologies.com/unnamed.owl");
    Ontology ontology = (Ontology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);
    System.out.println("Testing to get all top classes");
    Set<OClass> classes = ontology.getOClasses(true);
    System.err.println("Got top classes: "+classes.size());

    System.out.println("Testing to add an annotation property");
    URI annotURI = new URI("http://some.property.uri/#annProp1",false);
    ontology.addAnnotationProperty(annotURI);
    System.out.println("Annotation property added");
    int classNum = ontology.getOClasses(false).size();
    assertEquals(19, classNum);
    // count the number of top classes
    Set topclasses = ontology.getOClasses(true);
    assertEquals(6, topclasses.size());
    // get the class Department
    OClass aClass = ontology.getOClass(new URI(ontology.getDefaultNameSpace()
            + "Department", false));
    assertNotNull(aClass);
    // and count the number of super classes
    Set supclassbydist = aClass.getSuperClasses(OConstants.TRANSITIVE_CLOSURE);
    // the list contains 2 arrays of classes i-e 2 levels
    assertEquals(1, supclassbydist.size());
    // get the class Department
    aClass = ontology.getOClass(new URI(ontology.getDefaultNameSpace()
            + "Organization", false));
    assertNotNull(aClass);
    assertTrue(aClass.isTopClass());
    Set subclasses = aClass.getSubClassesOld(OConstants.TRANSITIVE_CLOSURE);
    assertEquals(3, subclasses.size());
    Factory.deleteResource(ontology);
  }
     * */
