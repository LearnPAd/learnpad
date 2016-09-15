/*
 *  Test_TupleQuery.java
 *
 *  Johann Petrak
 *
 *  $Id: Test_TupleQuery.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl.test;

import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OURI;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.OntologyTupleQuery;
import gate.util.GateException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Run tests on the test1.ttl ontology
 */
public class Test_TupleQuery {
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
    File demoFile = new File(ontologiesDir,"test2.ttl");
    URL turtleURL = demoFile.toURI().toURL();
    fm.put("turtleURL", turtleURL);
    Ontology ontology = (Ontology)Factory.createResource(
            "gate.creole.ontology.impl.sesame.OWLIMOntology", fm);

    String dns = ontology.getDefaultNameSpace();
    System.out.println("test2 default namespace: "+dns);
    OURI ontouri = ontology.getOntologyURI();
    System.out.println("test2 ontology URI: "+ontouri);
    
    long beginTime;
    long elapsed1;

    // Test SPARQL query with optional results
    
    String queryString = 
      "PREFIX : <http://www.gate.ac.uk/dummyurl/OntologyPlugin/test1.turtle.owl#>\n"+
      "SELECT ?s ?o1 ?o2 ?o3 { \n"+
      "  ?s a :C1 . \n"+
      "  OPTIONAL { ?s :p1 ?o1 . }\n"+
      "  OPTIONAL { ?s :p2 ?o2 . }\n"+
      "  OPTIONAL { ?s :p3 ?o3 . } }";
    OntologyTupleQuery tq = 
      ontology.createTupleQuery(queryString, OConstants.QueryLanguage.SPARQL);
    
    
    // we should get exactly one result here with the variables
    // ?s ?o1 and ?o2 bound and ?o3 unbound
    assertTrue(tq.hasNext());
    Vector<LiteralOrONodeID> ret = tq.next();
    System.out.println("Got query result row: "+ret);
    assertEquals(4, ret.size());
    assertNotNull(ret.get(0));
    assertEquals("http://www.gate.ac.uk/dummyurl/OntologyPlugin/test1.turtle.owl#s1",ret.get(0).toString());
    assertNotNull(ret.get(1));
    assertEquals("http://www.gate.ac.uk/dummyurl/OntologyPlugin/test1.turtle.owl#o1",ret.get(1).toString());
    assertNull(ret.get(2));
    assertNotNull(ret.get(3));
    assertEquals("http://www.gate.ac.uk/dummyurl/OntologyPlugin/test1.turtle.owl#o3",ret.get(3).toString());
    tq.close();
    
    ontology.cleanup();
  }



  // ***************************************************************
  // ************************** HELPER METHODS 
  // ***************************************************************


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
