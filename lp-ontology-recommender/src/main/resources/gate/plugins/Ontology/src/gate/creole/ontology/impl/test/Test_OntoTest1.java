/*
 *  Test_OntoTest1.java
 *
 *  Johann Petrak
 *
 *  $Id: Test_OntoTest1.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl.test;

import gate.creole.ontology.*;
import gate.util.GateException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.OConstants.Closure;
import java.util.HashSet;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Run tests on the test1.ttl ontology
 */
public class Test_OntoTest1 {
  static File ontologiesDir = null;
  static File configDir = null;
  static File tmpDir = null;
  // TODO: it seems we cannot use a static as intended here: the
  // init code still gets run for each fixture?
  static boolean isInitialized = false;
  Logger log =  Logger.getLogger(this.getClass());
  OConstants.Closure DIRECT_CLOSURE = Closure.DIRECT_CLOSURE;
  OConstants.Closure TRANSITIVE_CLOSURE = Closure.TRANSITIVE_CLOSURE;

  // global preparation stuff - check if stuff already initialized, if
  // yes, do nothing
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
  

  @Test
  public void testOntologyTest1() throws ResourceInstantiationException, MalformedURLException {
    FeatureMap fm = Factory.newFeatureMap();
    File demoFile = new File(ontologiesDir,"test1.ttl");
    URL turtleURL = demoFile.toURI().toURL();
    fm.put("turtleURL", turtleURL);
    Ontology ontology = (Ontology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);

    String dns = ontology.getDefaultNameSpace();
    System.out.println("test1 default namespace: "+dns);
    OURI ontouri = ontology.getOntologyURI();
    System.out.println("test1 ontology URI: "+ontouri);
    
    long beginTime;
    long elapsed1;

    // SECTION 1: get entities of a kind from the ontology
    
    beginTime = System.nanoTime();
    Set<OClass> allclasses = ontology.getOClasses(false);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 all classes: "+allclasses.size());
    System.out.println("@@@@@ test1 all classes: "+elapsed1);
    System.out.println("test1 all classes: "+allclasses);
    //assertEquals(27, allclasses.size());

    beginTime = System.nanoTime();
    Set<OClass> topclasses = ontology.getOClasses(true);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 top classes: "+topclasses.size());
    System.out.println("@@@@@ test1 top classes: "+elapsed1);
    System.out.println("test1 top classes: "+topclasses);
    //assertEquals(9, topclasses.size());

    beginTime = System.nanoTime();
    Set<AnnotationProperty> annprops = ontology.getAnnotationProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 annot props: "+annprops.size());
    System.out.println("@@@@@ test1 annot props: "+elapsed1);
    System.out.println("test1 annot props: "+annprops);
    //assertEquals(9, annprops.size());
    
    beginTime = System.nanoTime();
    Set<DatatypeProperty> datprops = ontology.getDatatypeProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 data props: "+datprops.size());
    System.out.println("@@@@@ test1 data props: "+elapsed1);
    System.out.println("test1 data props: "+datprops);
    //assertEquals(9, datprops.size());
    
    beginTime = System.nanoTime();
    Set<ObjectProperty> objprops = ontology.getObjectProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 obj props: "+objprops.size());
    System.out.println("@@@@@ test1 obj props: "+elapsed1);
    System.out.println("test1 obj props: "+objprops);
    //assertEquals(9, objprops.size());
    
    beginTime = System.nanoTime();
    Set<SymmetricProperty> symprops = ontology.getSymmetricProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 sym props: "+symprops.size());
    System.out.println("@@@@@ test1 sym props: "+elapsed1);
    System.out.println("test1 sym props: "+symprops);
    //assertEquals(9, symprops.size());
    
    beginTime = System.nanoTime();
    Set<TransitiveProperty> transprops = ontology.getTransitiveProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 trans props: "+transprops.size());
    System.out.println("@@@@@ test1 trans props: "+elapsed1);
    System.out.println("test1 obj props: "+transprops);
    //assertEquals(9, transprops.size());
    
    beginTime = System.nanoTime();
    Set<RDFProperty> rdfprops = ontology.getRDFProperties();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 rdf props: "+rdfprops.size());
    System.out.println("@@@@@ test1 rdf props: "+elapsed1);
    System.out.println("test1 rdf props: "+rdfprops);
    //assertEquals(9, rdfprops.size());
    
    beginTime = System.nanoTime();
    Set<OInstance> insts = ontology.getOInstances();
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### test1 insts: "+insts.size());
    System.out.println("@@@@@ test1 insts: "+elapsed1);
    System.out.println("test1 insts: "+insts);
    //assertEquals(9, insts.size());
    
    // SECTION2: perform the tests as outlined in the comments in the 
    // Turtle ontology source file.
    
    // check the iClass01s01 is instance of Class01
    OInstance iClass01s01 = ontology.getOInstance(getURI4Name(ontology,"iClass01s01"));
    assertNotNull(iClass01s01);
    Set<OClass> iClass01s01types = iClass01s01.getOClasses(TRANSITIVE_CLOSURE);
    assertNotNull(iClass01s01types);
    OClass cClass01 = ontology.getOClass(getURI4Name(ontology,"Class01"));
    assertNotNull(cClass01);
    // check the superclass is indeed a type of the individual
    assertTrue(iClass01s01types.contains(cClass01)); 
    // other way round: get all instances of class01 and check if the individual is member
    Set<OInstance> instsClass01 = ontology.getOInstances(cClass01, TRANSITIVE_CLOSURE);
    assertNotNull(instsClass01);
    assertTrue(instsClass01.contains(iClass01s01));
    
    // TODO: check inference from class equivalence 
    
    // TODO: check inference from individual sameness
    
    // check datatype property domain stuff
    DatatypeProperty dpc04p2 = ontology.getDatatypeProperty(getURI4Name(ontology,"dpc04p2"));
    assertNotNull(dpc04p2);
    OInstance iClass04i02 = ontology.getOInstance(getURI4Name(ontology,"iClass04i02"));
    OInstance iClass04i03 = ontology.getOInstance(getURI4Name(ontology,"iClass04i03"));
    OInstance iClass04i01 = ontology.getOInstance(getURI4Name(ontology,"iClass04i01"));
    OInstance iClass04i04 = ontology.getOInstance(getURI4Name(ontology,"iClass04i04"));
    assertTrue(dpc04p2.isValidDomain(iClass04i02));
    assertFalse(dpc04p2.isValidDomain(iClass04i03));
    assertFalse(dpc04p2.isValidDomain(iClass04i01));
    assertTrue(dpc04p2.isValidDomain(iClass04i04));

    OInstance iClass100I01 = ontology.getOInstance(getURI4Name(ontology,"iClass100I01"));
    assertNotNull(iClass100I01);
    DatatypeProperty dp100a = ontology.getDatatypeProperty(getURI4Name(ontology,"dp100a"));
    assertNotNull(dp100a);
    List<Literal> l100i01s = iClass100I01.getDatatypePropertyValues(dp100a);
    assertEquals(1, l100i01s.size());
    Literal l100i01 = l100i01s.iterator().next();
    assertEquals("plain literal, no language tag", l100i01.getValue());
    assertEquals(null,l100i01.getLanguage());
    assertEquals(null,l100i01.getDataType());
    assertEquals("\"plain literal, no language tag\"",l100i01.toTurtle());
    
    OInstance iClass100I02 = ontology.getOInstance(getURI4Name(ontology,"iClass100I02"));
    assertNotNull(iClass100I02);
    DatatypeProperty dp100b = ontology.getDatatypeProperty(getURI4Name(ontology,"dp100b"));
    assertNotNull(dp100b);
    List<Literal> l100i02s = iClass100I02.getDatatypePropertyValues(dp100b);
    assertEquals(1, l100i02s.size());
    Literal l100i02 = l100i02s.iterator().next();
    assertEquals("plain literal, lang tag es", l100i02.getValue());
    assertNotNull(l100i02.getLanguage());
    assertEquals("es",l100i02.getLanguage().getLanguage());
    assertEquals(null,l100i02.getDataType());
    assertEquals("\"plain literal, lang tag es\"@es",l100i02.toTurtle());
    
    OInstance iClass100I03 = ontology.getOInstance(getURI4Name(ontology,"iClass100I03"));
    assertNotNull(iClass100I03);
    DatatypeProperty dp100c = ontology.getDatatypeProperty(getURI4Name(ontology,"dp100c"));
    assertNotNull(dp100c);
    List<Literal> l100i03s = iClass100I03.getDatatypePropertyValues(dp100c);
    assertEquals(1, l100i03s.size());
    Literal l100i03 = l100i03s.iterator().next();
    assertEquals("typed literal, string", l100i03.getValue());
    assertEquals(null,l100i03.getLanguage());
    assertEquals(DataType.getStringDataType(),l100i03.getDataType());
    System.out.println("l100i03 literal: "+l100i03.toTurtle());
    assertEquals("\"typed literal, string\"^^<"+DataType.getStringDataType().getXmlSchemaURIString()+">",l100i03.toTurtle());

    OInstance iClass100I04 = ontology.getOInstance(getURI4Name(ontology,"iClass100I04"));
    assertNotNull(iClass100I04);
    DatatypeProperty dp100d = ontology.getDatatypeProperty(getURI4Name(ontology,"dp100d"));
    assertNotNull(dp100d);
    List<Literal> l100i04s = iClass100I04.getDatatypePropertyValues(dp100d);
    assertEquals(1, l100i04s.size());
    Literal l100i04 = l100i04s.iterator().next();
    assertEquals("12", l100i04.getValue());
    assertEquals(null,l100i04.getLanguage());
    assertEquals(DataType.getIntDataType(),l100i04.getDataType());
    assertEquals("\"12\"^^<"+DataType.getIntDataType().getXmlSchemaURIString()+">",l100i04.toTurtle());

    
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
