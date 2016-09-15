/*
 *  Test_OntoWine.java
 *
 *  Johann Petrak
 *
 *  $Id: Test_OntoWine.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl.test;

import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.DatatypeProperty;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OResource;
import gate.creole.ontology.OURI;
import gate.creole.ontology.ObjectProperty;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.impl.AbstractOntologyImpl;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Run tests on the wine ontology
 */
public class Test_OntoWine {
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
  public void testWineOntology() throws MalformedURLException,
          ResourceInstantiationException {
    FeatureMap fm = Factory.newFeatureMap();
    // For local wine:
    File demoFile = new File(ontologiesDir,"wine.rdfxml.owl");
    URL rdfXmlURL = demoFile.toURI().toURL();
    File mappingsFile = new File(ontologiesDir,"winemappings.txt");
    URL mappingsURL = mappingsFile.toURI().toURL();
    fm.put("rdfXmlURL", rdfXmlURL);
    // instead of loading the included food ontology from the web, use 
    // a mappings file to load it from our ontology directory
    fm.put("mappingsURL", mappingsURL);
    Ontology ontology = (Ontology)Factory.createResource(
           "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);

    String dns = ontology.getDefaultNameSpace();
    assertEquals("http://www.w3.org/TR/2003/CR-owl-guide-20030818/wine#",dns);
    // For remote wine:
    //fm.put("repositoryID","owlim-wine");
    //fm.put("repositoryLocation","http://localhost:8080/openrdf-sesame");
    //Ontology ontology = (Ontology)Factory.createResource(
    //        "gate.creole.ontology.impl.sesame.ConnectSesameOntology", fm);

    long beginTime;
    long elapsed1;

    beginTime = System.nanoTime();
    Set<OClass> allclasses = ontology.getOClasses(false);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### all WINE classes: "+allclasses.size());
    System.out.println("@@@@@ all WINE classes: "+elapsed1);
    assertEquals(661,allclasses.size());

    beginTime = System.nanoTime();
    Set<OClass> topclasses = ontology.getOClasses(true);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("##### top WINE classes: "+topclasses.size());
    System.out.println("@@@@@ top WINE classes: "+elapsed1);
    assertEquals(459,topclasses.size());

    beginTime = System.nanoTime();
    System.out.println("Starting to expand all classes ... ");
    Set<OClass> collected = new HashSet<OClass>();
    collected.addAll(topclasses);
    expandAllClasses(collected, 0);
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("Expanding classes complete");
    System.out.println("##### expanded WINE classes: "+collected.size());
    System.out.println("@@@@@ expanded WINE classes: "+elapsed1);
    // NOTE: class WineColor is not found by expanding subclasses, because
    // it is a subclass of a oneOf restriction which is not in OWL-Lite and
    // cannot be represented by the Ontology API. Therefore, one less.
    assertEquals(660,collected.size());
    

    beginTime = System.nanoTime();
    Set<OClass> ocbn = ((AbstractOntologyImpl)ontology).getOClassesByName("Wine");
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("Classes with name Wine: ");
    for(OClass cl : ocbn) {
      System.out.println("  "+cl.getONodeID().toTurtle());
    }
    System.out.println("##### classes by name wine: "+ocbn.size());
    System.out.println("@@@@@ classes by name wine: "+elapsed1);
    assertEquals("Classes for name Wine",2,ocbn.size());

    beginTime = System.nanoTime();
    List<OResource> ors = ontology.getOResourcesByName("yearValue");
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("Resources with name yearValue: ");
    for(OResource or : ors) {
      System.out.println("  "+or.getONodeID().toTurtle());
    }
    System.out.println("##### resources by name yearValue: "+ors.size());
    System.out.println("@@@@@ resources by name yearValue: "+elapsed1);
    assertEquals("Resources for name yearValue",1,ors.size());
    assertTrue("yearValue is a datatype property",ontology.isDatatypeProperty((OURI)ors.get(0).getONodeID()));

    beginTime = System.nanoTime();
    ors = ontology.getOResourcesByName("Wine");
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("Resources with name Wine: ");
    for(OResource or : ors) {
      System.out.println("  "+or.getONodeID().toTurtle());
    }
    System.out.println("##### resources by name Wine: "+ors.size());
    System.out.println("@@@@@ resources by name Wine: "+elapsed1);
    assertEquals("Resources for name Wine",2,ors.size());
    assertEquals("Class of resource 1 for wine",
            "gate.creole.ontology.impl.OClassImpl",
            ors.get(0).getClass().getName());
    assertEquals("Class of resource 2 for wine",
            "gate.creole.ontology.impl.OClassImpl",
            ors.get(1).getClass().getName());

    beginTime = System.nanoTime();
    ors = ontology.getOResourcesByName("Delicate");
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("Resources with name Delicate: ");
    for(OResource or : ors) {
      System.out.println("  "+or.getONodeID().toTurtle());
    }
    System.out.println("##### resources by name Delicate: "+ors.size());
    System.out.println("@@@@@ resources by name Delicate: "+elapsed1);
    assertEquals("Resources for name Delicate",2,ors.size());
    assertEquals("Class of resource 1 for Delicate",
            "gate.creole.ontology.impl.OInstanceImpl",
            ors.get(0).getClass().getName());

    beginTime = System.nanoTime();
    OClass c_WineColor = ontology.getOClass(getURI4Name(ontology,"WineColor"));
    OClass c_WineTaste = ontology.getOClass(getURI4Name(ontology,"WineTaste"));
    OClass c_WineSugar = ontology.getOClass(getURI4Name(ontology,"WineSugar"));
    OClass c_VintageYear = ontology.getOClass(getURI4Name(ontology,"VintageYear"));
    OClass c_WineDescriptor = ontology.getOClass(getURI4Name(ontology,"WineDescriptor"));
    ObjectProperty op_hasMaker = ontology.getObjectProperty(getURI4Name(ontology,"hasMaker"));
    ObjectProperty op_hasWineDescriptor = ontology.getObjectProperty(getURI4Name(ontology,"hasWineDescriptor"));
    ObjectProperty op_hasSugar = ontology.getObjectProperty(getURI4Name(ontology,"hasSugar"));
    DatatypeProperty dp_yearValue = ontology.getDatatypeProperty(getURI4Name(ontology,"yearValue"));
    elapsed1 = System.nanoTime() - beginTime;
    System.out.println("@@@@@ find specific entities: "+elapsed1);
    assertNotNull("Find WineColor",c_WineColor);
    assertNotNull("Find WineTaste",c_WineTaste);
    assertNotNull("Find WineSugar",c_WineSugar);
    assertNotNull("Find VintageYear",c_VintageYear);
    assertNotNull("Find VintageYear",c_VintageYear);
    assertNotNull("Find hasMaker",op_hasMaker);
    assertNotNull("Find hasWineDescriptor",op_hasWineDescriptor);
    assertNotNull("Find hasSugar",op_hasSugar);
    assertNotNull("Find yearValue",dp_yearValue);
    Set<OResource> domain_yearValue = dp_yearValue.getDomain();
    assertEquals(1, domain_yearValue.size());
    assertTrue((domain_yearValue.iterator().next() instanceof OClass));
    OClass c_domain_yearValue = (OClass)domain_yearValue.iterator().next();
    assertEquals("VintageYear",c_domain_yearValue.getName());
    assertEquals(c_VintageYear,c_domain_yearValue);
    
    // Check the directo subclasses of WineDescriptor:
    Set<OClass> dsub1 = c_WineDescriptor.getSubClasses(DIRECT_CLOSURE);
    System.out.println("Subclasses of WineDescriptor: "+dsub1);
    // Again, WineColor not found because it is subclass of a oneOf 
    // restriction, therefore just one instead of 2 subclasses!
    assertEquals(1,dsub1.size());
    assertTrue(dsub1.contains(c_WineTaste));
    
    beginTime = System.nanoTime();
    Set<OInstance> instances = ontology.getOInstances();
    elapsed1 = System.nanoTime() - beginTime;
    // NOTE: protege4 gets 207 here because it also finds an instance without
    // a type and URI: http://www.w3.org/TR/2003/WD-owl-guide-2003-331/wine
    // This is the URI of the prior version of the Ontology
    System.out.println("##### number of instances: "+instances.size());
    System.out.println("@@@@@ number of instances: "+elapsed1);
    assertEquals(206,instances.size());
    
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
