/*
 *  TestOntologyAPI.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: Test_OWLIMOntology.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl.test;

import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.AnnotationProperty;
import gate.creole.ontology.DatatypeProperty;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OURI;
import gate.creole.ontology.ObjectProperty;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.RDFProperty;
import gate.creole.ontology.SymmetricProperty;
import gate.creole.ontology.TransitiveProperty;
import gate.util.GateException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Run various tests ...
 */
public class Test_OWLIMOntology {
  static File ontologiesDir = null;
  static File configDir = null;
  static File tmpDir = null;
  // TODO: it seems we cannot use a static as intended here: the
  // init code still gets run for each fixture?
  static boolean isInitialized = false;
  Logger log =  Logger.getLogger(this.getClass());
  OConstants.Closure DIRECT_CLOSURE = Closure.DIRECT_CLOSURE;
  OConstants.Closure TRANSITIVE_CLOSURE = Closure.TRANSITIVE_CLOSURE;

  @BeforeClass
  public static void init() throws GateException, MalformedURLException {
    if(!isInitialized) {
    System.out.println("Inititalizing ...");
    Gate.init();
    File pluginHome =
        new File(new File(Gate.getGateHome(), "plugins"),
                 "Ontology");
    System.out.println("Plugin home directory is "+pluginHome.getAbsolutePath());
    Gate.getCreoleRegister().registerDirectories(
            pluginHome.toURI().toURL());
    File testingDir = new File(pluginHome,"test");
    assertTrue(testingDir.exists());
    ontologiesDir = new File(testingDir, "ontologies");
    assertTrue(ontologiesDir.exists());
    tmpDir = getUniqueTmpDir();
    assertTrue(tmpDir.canWrite());
    System.out.println("Init complete");
    } else {
      isInitialized = true;
    }
  }
  
  @AfterClass
  public static void cleanup() throws Exception {
    if(tmpDir != null) {
      FileUtils.deleteDirectory(tmpDir);
    }
  }


  // testLoadImports: test the loading of an existing ontology, finding the ontology
  // URI, import mappings, imports etc.
  @Test
  public void testLoadImports() throws MalformedURLException,
          ResourceInstantiationException {
    FeatureMap fm = Factory.newFeatureMap();
    File demoFile = new File(ontologiesDir,"protonu.owl");
    URL rdfXmlURL = demoFile.toURI().toURL();
    fm.put("rdfXmlURL", rdfXmlURL);
    fm.put("baseURI", "");
    File mappingsFile = new File(ontologiesDir,"mappingsfile1.txt");
    URL mappingsFileURL = mappingsFile.toURI().toURL();
    fm.put("mappingsURL",mappingsFileURL);
    fm.put("persistent","false");
    fm.put("loadImports", "true");
    Ontology ontology = (Ontology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);
    // we should now have the protou ontology loaded and the protons ontology
    // imported from the local directory. The protont import should have
    // been ignored, as specified in the mappings file.
    Set<OClass> allClasses = ontology.getOClasses(false);
    //for(OClass aClass : allClasses ) {
    //  System.out.println("Class: "+aClass+" / "+aClass.getONodeID().toTurtle());
    //}
    // Check if the Base URI was set from the loaded ontology
    System.out.println("Found default nameSpace: "+ontology.getDefaultNameSpace());
    assertEquals("Default Name Space",
        "http://proton.semanticweb.org/2005/04/protonu#",
        ontology.getDefaultNameSpace());
    //System.out.println("Found ontology URIs: "+ontology.getOntologyURIs());
    // make sure we find classes from protonu and protont, but not protons
    OClass c1 = ontology.getOClass(ontology.createOURIForName("SportEvent"));
    assertNotNull(c1);
    ontology.cleanup();
  }

  /**
   * Create lots of ontologies in quick succession to test if the creation
   * of temporary storage location directories for them works.
   * (many more than 50 ontologies cannot be created here or we would run into
   * heap memory problems with the default memory settings used when the 
   * tests are run) 
   * @throws ResourceInstantiationException 
   */
  @Test
  public void testFastCreation() throws ResourceInstantiationException  {
    Set<Ontology> ontologies = new HashSet<Ontology>();
    for (int i=0; i<50; i++) {
      FeatureMap fm = Factory.newFeatureMap();
      Ontology ontology = (Ontology)Factory.createResource(
              "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);
      ontologies.add(ontology);
    }
    for(Ontology ontology : ontologies) {
      ontology.cleanup();
    }
  }


  
  
  // testAccessOldNew: test ontology methods that were already present in the old
  // implementation - output measurements and do some benchmarking so
  // we can compare.
  @Test
  public void testAccessOldNew() throws MalformedURLException,
          ResourceInstantiationException {
    FeatureMap fm = Factory.newFeatureMap();
    File demoFile = new File(ontologiesDir,"protonust.owl");
    URL rdfXmlURL = demoFile.toURI().toURL();
    fm.put("rdfXmlURL", rdfXmlURL);
    Ontology ontology = (Ontology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);

    long beginTime;
    long elapsed1;

    beginTime = System.nanoTime();
    Set<OClass> classes = ontology.getOClasses(false);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### all classes: "+classes.size());
    System.out.println("@@@@@ all classes: "+elapsed1);
    assertEquals(252, classes.size());
    //System.out.println("Classes: "+classes);


    beginTime = System.nanoTime();
    classes = ontology.getOClasses(true);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### top classes: "+classes.size());
    System.out.println("@@@@@ top classes: "+elapsed1);
    assertEquals(15, classes.size());

    beginTime = System.nanoTime();
    Set<AnnotationProperty> anns = ontology.getAnnotationProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("List  ann props: "+anns);
    System.out.println("##### ann props: "+anns.size());
    System.out.println("@@@@@ ann props: "+elapsed1);
    assertEquals(6, anns.size());

    beginTime = System.nanoTime();
    Set<DatatypeProperty> dats = ontology.getDatatypeProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### dat props: "+dats.size());
    System.out.println("@@@@@ dat props: "+elapsed1);
    assertEquals(11, dats.size());

    beginTime = System.nanoTime();
    Set<ObjectProperty> obs = ontology.getObjectProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### obj props: "+obs.size());
    System.out.println("@@@@@ obj props: "+elapsed1);
    assertEquals(43, obs.size());

    beginTime = System.nanoTime();
    @SuppressWarnings("deprecation")
    Set<OInstance> insts = ontology.getOInstances();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### insts: "+insts.size());
    System.out.println("@@@@@ insts: "+elapsed1);
    assertEquals(0, insts.size());


    beginTime = System.nanoTime();
    Set<RDFProperty> props =  ontology.getPropertyDefinitions();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### propdefs: "+props.size());
    System.out.println("@@@@@ propdefs: "+elapsed1);
    // assertEquals(0, insts.size());


    beginTime = System.nanoTime();
    Set<SymmetricProperty> symprops = ontology.getSymmetricProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### symprops: "+symprops.size());
    System.out.println("@@@@@ symprops: "+elapsed1);
    // assertEquals(0, insts.size());

    beginTime = System.nanoTime();
    Set<TransitiveProperty> transprops = ontology.getTransitiveProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### transprops: "+transprops.size());
    System.out.println("@@@@@ transprops: "+elapsed1);
    // assertEquals(0, insts.size());

    OURI cBAURI = ontology.createOURIForName("BusinessAbstraction");
    //System.out.println("OUIR: "+cBAURI+" / "+cBAURI.toTurtle());
    OClass cBA = ontology.getOClass(cBAURI);
    assertNotNull(cBA);

    beginTime = System.nanoTime();
    classes = cBA.getSubClasses(TRANSITIVE_CLOSURE);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### BA subclasses all: "+classes.size());
    System.out.println("@@@@@ BA subclasses all: "+elapsed1);
    assertEquals(2, classes.size());

    beginTime = System.nanoTime();
    classes = cBA.getSubClasses(DIRECT_CLOSURE);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### BA subclasses direct: "+classes.size());
    System.out.println("@@@@@ BA subclasses direct: "+elapsed1);
    assertEquals(2, classes.size());



    //ontology.getOResourcesWith(null, null);
    //ontology.getOResourcesWith(null, null);
    //ontology.getDistance(null, null);

    // TODO:
    // for a couple of classes, get all subclasses, direct subclasses
    //   get all superclasses ...

    ontology.cleanup();
  }

  @Test
  public void testCreateModify() throws MalformedURLException,
          ResourceInstantiationException,
          FileNotFoundException,
          IOException {
    FeatureMap fm = Factory.newFeatureMap();
    Ontology ontology = (Ontology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);

    long beginTime;
    long elapsed1;

    beginTime = System.nanoTime();
    Set<OClass> classes = ontology.getOClasses(false);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### all classes: "+classes.size());
    System.out.println("@@@@@ all classes: "+elapsed1);
    assertEquals(0, classes.size());

    // set the Base URI
    ontology.setDefaultNameSpace("http://gate.ac.uk/Ontology/testCreateModify/#");
    // create some classes
    ontology.addOClass(getURI4Name(ontology,"Class01"));
    OClass c02 = ontology.addOClass(getURI4Name(ontology,"Class02"));
    assertNotNull(c02);
    OClass c0201 = ontology.addOClass(getURI4Name(ontology,"Class0201"));
    assertNotNull(c0201);
    c02.addSubClass(c0201);
    OClass c020101 = ontology.addOClass(getURI4Name(ontology,"Class020101"));
    assertNotNull(c020101);
    c0201.addSubClass(c020101);


    beginTime = System.nanoTime();
    classes = ontology.getOClasses(false);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### all classes: "+classes.size());
    System.out.println("@@@@@ all classes: "+elapsed1);
    assertEquals(4, classes.size());

    OClass c02b = ontology.getOClass(getURI4Name(ontology,"Class02"));
    assertNotNull("Find Class02 again",c02b);

    beginTime = System.nanoTime();
    classes = c02b.getSubClasses(DIRECT_CLOSURE);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### c02 sub classes: "+classes.size());
    System.out.println("@@@@@ c02 sub classes: "+elapsed1);
    assertEquals(1, classes.size());

    beginTime = System.nanoTime();
    classes = c02b.getSubClasses(TRANSITIVE_CLOSURE);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### c02 all classes: "+classes.size());
    System.out.println("@@@@@ c02 all classes: "+elapsed1);
    assertEquals(2, classes.size());

    // add annotation, datatype, object properties, subproperties,
    // transitive properties
    // instances
    // restrictions

    // delete some of the stuff added and see if it is gone



    // save to the tmp dir
    File savedOntology = new File(tmpDir,"testCreateModify.rdfxml.owl");
    FileOutputStream os = new FileOutputStream(savedOntology);
    ontology.writeOntologyData(os, OConstants.OntologyFormat.RDFXML, false);
    os.close();
    File savedOntologyT = new File(tmpDir,"testCreateModify.turtle.owl");
    os = new FileOutputStream(savedOntologyT);
    ontology.writeOntologyData(os, OConstants.OntologyFormat.TURTLE, false);
    os.close();
    savedOntologyT = new File(tmpDir,"testCreateModifyWithImports.turtle.owl");
    os = new FileOutputStream(savedOntologyT);
    ontology.writeOntologyData(os, OConstants.OntologyFormat.TURTLE, true);
    os.close();

    ontology.cleanup();

    //////////////////////////////////////////////////
    URL savedOntoURL = savedOntology.toURI().toURL();
    fm.put("rdfXmlURL", savedOntoURL);
    ontology = (Ontology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);
    ontology.setDefaultNameSpace("http://gate.ac.uk/Ontology/testCreateModify/#");

    beginTime = System.nanoTime();
    classes = ontology.getOClasses(false);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### 2 all classes: "+classes.size());
    System.out.println("@@@@@ 2 all classes: "+elapsed1);
    assertEquals(4, classes.size());

    c02b = ontology.getOClass(getURI4Name(ontology,"Class02"));
    assertNotNull("Find Class02 again",c02b);

    beginTime = System.nanoTime();
    classes = c02b.getSubClasses(DIRECT_CLOSURE);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### c02 sub classes: "+classes.size());
    System.out.println("@@@@@ c02 sub classes: "+elapsed1);
    assertEquals(1, classes.size());

    beginTime = System.nanoTime();
    classes = c02b.getSubClasses(TRANSITIVE_CLOSURE);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### c02 all classes: "+classes.size());
    System.out.println("@@@@@ c02 all classes: "+elapsed1);
    assertEquals(2, classes.size());


    ontology.cleanup();


   }



  // ***************************************************************
  // ************************** HELPER METHODS 
  // ***************************************************************

   // recursively find the set of all subclasses for a given set of classes
   // until no more subclasses are found. We limit the maximum depth at 20.
   // The set initially should contain all the classes for which we want to
   // find all the subclasses.
   public void expandAllClasses(Set<OClass> classes, int depth) {
     if(depth > 20) {
      assertTrue("Depth must not exceed 20",false);
    }
    if(classes.isEmpty()) return;  // there are no classes to expand!
    // number of new classes found
    Set<OClass> addclasses = new HashSet<OClass>();
    for (OClass c : classes) {
      Set<OClass> subclasses = c.getSubClasses(OConstants.Closure.DIRECT_CLOSURE);
      if(subclasses.contains(c)) {
        System.out.println("Subclass of itself: "+c);
        assertTrue(false);
      }
      addclasses.addAll(subclasses);
    }
    if(classes.addAll(addclasses)) expandAllClasses(classes,depth+1);
  }

  protected static File getUniqueTmpDir() {
    String tmplocation = System.getProperty("run.java.io.tmpdir");
    if(tmplocation == null) {
      tmplocation = System.getProperty("java.io.tmpdir");
    }
    if(tmplocation == null) {
      tmplocation = "/tmp";
    }
    if(tmplocation == null) {
       System.err.println("Not temp-directory found, cannot continue");
       System.exit(1);
    }
    File tmpdir = new File(tmplocation);
    if(!tmpdir.exists()) {
       System.err.println("Temp dir does not exist: "+tmpdir.getAbsolutePath());
       System.exit(1);
    }
    String tmpString = Long.toString(System.currentTimeMillis(),36);
    File uniqTmpDir = new File(tmpdir,"gate-towlim-"+tmpString);
    uniqTmpDir.mkdir();
    return uniqTmpDir;
  }

  protected OURI getURI(Ontology o, String uri) {
    return o.createOURI(uri);
  }

  protected OURI getURI4Name(Ontology o, String uri) {
    return o.createOURIForName(uri);
  }
}
