/*
 *  Test_TripleStore.java
 *
 *  Johann Petrak
 *
 *  $Id: Test_TripleStore.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl.test;

import gate.creole.ontology.*;
import gate.util.GateException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.impl.sesame.AbstractOntologyImplSesame;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Run tests on the test1.ttl ontology
 */
public class Test_TripleStore {
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
  public void testTripleStoreTest1() throws ResourceInstantiationException, MalformedURLException {
    FeatureMap fm = Factory.newFeatureMap();
    File demoFile = new File(ontologiesDir,"test1.ttl");
    URL turtleURL = demoFile.toURI().toURL();
    fm.put("turtleURL", turtleURL);
    AbstractOntologyImplSesame ontology = (AbstractOntologyImplSesame)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);

    long beginTime;
    long elapsed1;

    OntologyTripleStore ots = ontology.getOntologyTripleStore();
    MyTripleEventListener otsl = 
      new MyTripleEventListener();
    
    ots.addOntologyTripleStoreListener(otsl);
    String rdf_type = gate.creole.ontology.OConstants.RDF.TYPE;
    String owl_class = gate.creole.ontology.OConstants.OWL.CLASS;
    
    otsl.expectedTriple = 1;
    OURI subject = ontology.createOURIForName("MyClass1");
    OURI predicate = ontology.createOURI(rdf_type);
    OURI object = ontology.createOURI(owl_class);
    ots.addTriple(subject, predicate, object);
    // check if we actually have this class now!
    OClass oc1 = ontology.getOClass(ontology.createOURIForName("MyClass1"));
    assertNotNull(oc1);
    assertEquals("MyClass1",oc1.getONodeID().getResourceName());    
    
    otsl.expectedTriple = 2;
    subject = ontology.createOURIForName("MyInstance1");
    predicate = ontology.createOURI(rdf_type);
    object = ontology.createOURIForName("MyClass1");
    ots.addTriple(subject, predicate, object);
    OInstance oi1 = ontology.getOInstance(ontology.createOURIForName("MyInstance1")); 
    assertNotNull(oi1);
    assertEquals("MyInstance1",oi1.getOURI().getResourceName());
    
    
    otsl.expectedTriple = 3;
    subject = ontology.createOURIForName("MyInstance1");
    predicate = ontology.createOURI(rdf_type);
    object = ontology.createOURIForName("MyClass1");
    ots.removeTriple(subject, predicate, object);
    oi1 = ontology.getOInstance(ontology.createOURIForName("MyInstance1")); 
    assertNull(oi1);
    
    
    //beginTime = System.nanoTime();
    // Set<OClass> allclasses = ontology.getOClasses(false);
    //elapsed1 = System.nanoTime() - beginTime;
    //System.out.println("##### test1 all classes: "+allclasses.size());
    //System.out.println("@@@@@ test1 all classes: "+elapsed1);
    //System.out.println("test1 all classes: "+allclasses);
    //assertEquals(27, allclasses.size());

    ontology.cleanup();
  }


  public class MyTripleEventListener implements OntologyTripleStoreListener {
    public int expectedTriple = 1;
    public void tripleAdded(ONodeID subject, OURI predicate, ONodeID object) {
      if(expectedTriple == 1) {
        assertEquals("MyClass1",subject.getResourceName());
      } else if(expectedTriple == 2) {
        assertEquals("MyInstance1",subject.getResourceName());
      } 
    }

    public void tripleAdded(ONodeID subject, OURI predicate, Literal object) {
      if(expectedTriple == 1) {
        assertTrue("Invalid triple",false);
      } else if(expectedTriple == 2) {
        assertTrue("Invalid triple",false);
      }       
    }

    public void tripleRemoved(ONodeID subject, OURI predicate, ONodeID object) {
      
    }

    public void tripleRemoved(ONodeID subject, OURI predicate, Literal object) {
      
    }
    
  }

  // ***************************************************************
  // ************************** HELPER METHODS 
  // **************************************************************
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
