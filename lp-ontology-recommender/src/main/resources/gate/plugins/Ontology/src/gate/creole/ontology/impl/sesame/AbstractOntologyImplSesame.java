/*
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Johann Petrak 2009-08-13
 *
 *  $Id: AbstractOntologyImplSesame.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OConstants.OntologyFormat;
import gate.creole.ontology.OConstants.QueryLanguage;
import gate.creole.ontology.OURI;
import gate.creole.ontology.OntologyBooleanQuery;
import gate.creole.ontology.OntologyTripleStore;
import gate.creole.ontology.OntologyTupleQuery;
import gate.creole.ontology.impl.AbstractOntologyImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.log4j.Logger;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

/**
 *
 * @author johann
 */
public abstract class AbstractOntologyImplSesame extends AbstractOntologyImpl {

  
  private Logger logger;

  public AbstractOntologyImplSesame() {
    logger = Logger.getLogger(this.getClass().getName());
  }




  public void readOntologyData(java.net.URL theURL, String baseURI,
      OConstants.OntologyFormat format, boolean asImport) {
    Reader input = null;
    try {
      input = new InputStreamReader(theURL.openStream(), "UTF-8");
      boolean isBaseURIset = true;
      if(baseURI == null || baseURI.length() == 0) {
        isBaseURIset = false;
        baseURI = OConstants.ONTOLOGY_DEFAULT_BASE_URI;
      }
      ((OntologyServiceImplSesame) ontologyService).readOntologyData(
          input,
          baseURI, format, asImport);
      if(!asImport) {
        if(isBaseURIset && getDefaultNameSpace() == null) {
          setDefaultNameSpace(baseURI);
        } else {
          setDefaultNameSpaceFromRepository();
          if(format.equals(OConstants.OntologyFormat.RDFXML)) {
            setDefaultNameSpaceByPeeking(theURL);
          }
        }
      }      
    } catch (IOException ex) {
      throw new GateOntologyException("Problem reading from URL " + theURL, ex);
    } finally {
      try {
        if(input != null) input.close();
      } catch (IOException ex) {
        throw new GateOntologyException("Problem closing stream from URL " + theURL, ex);
      }
      
    }
  }

  public void readOntologyData(File selectedFile, String baseURI,
      OConstants.OntologyFormat format, boolean asImport) {
    Reader input = null;
    try {
      input = new InputStreamReader(new FileInputStream(selectedFile), "UTF-8");
      boolean isBaseURIset = true;
      if(baseURI == null || baseURI.length() == 0) {
        isBaseURIset = false;
        baseURI = OConstants.ONTOLOGY_DEFAULT_BASE_URI;
      }
     ((OntologyServiceImplSesame) ontologyService).readOntologyData(input,
          baseURI, format, asImport);
      if(!asImport) {
        if(isBaseURIset && getDefaultNameSpace() == null) {
          setDefaultNameSpace(baseURI);
        } else {
          setDefaultNameSpaceFromRepository();
          if(format.equals(OConstants.OntologyFormat.RDFXML)) {
            setDefaultNameSpaceByPeeking(selectedFile);
          }
        }
      }      
    } catch (IOException ex) {
      throw new GateOntologyException("Problem reading from file " + 
          selectedFile, ex);
    } finally {
      try {
        if(input != null)  input.close();
      } catch(IOException e) {
        throw new GateOntologyException("Problem closing file " + 
            selectedFile, e);
      }
    }

  }

  public void readOntologyData(Reader in, String baseURI, OntologyFormat format,
      boolean asImport) {
    boolean isBaseURIset = true;
    if(baseURI == null || baseURI.length() == 0) {
      isBaseURIset = false;
      baseURI = OConstants.ONTOLOGY_DEFAULT_BASE_URI;
    }
    ((OntologyServiceImplSesame) ontologyService).readOntologyData(in,
        baseURI, format, asImport);
    if(!asImport) {
      if(isBaseURIset && getDefaultNameSpace() == null) {
        setDefaultNameSpace(baseURI);
      } else {
        setDefaultNameSpaceFromRepository();
      }
    }
  }

  public void readOntologyData(InputStream in, String baseURI,
      OntologyFormat format, boolean asImport) {
    Reader input;
    try {
      input = new InputStreamReader(in, "UTF-8");
    } catch (IOException ex) {
      throw new GateOntologyException("Problem reading the input stream.", ex);
    }
    boolean isBaseURIset = true;
    if(baseURI == null || baseURI.length() == 0) {
      isBaseURIset = false;
      baseURI = OConstants.ONTOLOGY_DEFAULT_BASE_URI;
    }
    ((OntologyServiceImplSesame) ontologyService).readOntologyData(input,
        baseURI, format, asImport);
    if(!asImport) {
      if(isBaseURIset && getDefaultNameSpace() == null) {
        setDefaultNameSpace(baseURI);
      } else {
        setDefaultNameSpaceFromRepository();
      }
    }
  }

  // sets the default namespace if it is not already set from
  // either the default name space of the loaded ontology or
  // if no default namespace is found, the URI prefix of the ontology URI.
  protected void setDefaultNameSpaceFromRepository() {
    if (getDefaultNameSpace() == null) {
      RepositoryConnection conn = ((OntologyServiceImplSesame)ontologyService).getRepositoryConnection();
      String defaultNamespace = null;
      try {
        defaultNamespace = conn.getNamespace("");
        if(defaultNamespace != null && !defaultNamespace.isEmpty()) {
          String defaultNamespace2 = makeDnsUri(defaultNamespace);
          logger.info("Default name space set from repository default namespace "+defaultNamespace+" to " + defaultNamespace2);
          setDefaultNameSpace(defaultNamespace);
        }
      } catch (RepositoryException ex) {
        logger.info("Could not get default namespace from repository: "+ex);
      }
    }
    if (getDefaultNameSpace() == null) {
      Set<OURI> ouris = ontologyService.getOntologyURIs();
      if (ouris.size() == 1) {
        String uri = ouris.iterator().next().toString();
        String ontouri = uri;
        uri = makeDnsUri(uri);
        setDefaultNameSpace(uri);
        logger.info("Default name space set from ontology URI " + ontouri + " to " + uri);
      } else if (ouris.size() > 0) {
        logger.info("Error: several ontology URIs found, could not set default name space");
      }
    }
  }

  String makeDnsUri(String uri) {
    // if there is a hash, remove everything after it
    if (uri.contains("#") && (uri.indexOf("#") < (uri.length() - 1))) {
      uri = uri.substring(0, uri.indexOf("#") + 1);
    }
    if (!uri.endsWith("/") && !uri.endsWith("#")) {
      uri += "#";
    }
    return uri;
  }

  protected void setDefaultNameSpaceByPeeking(java.net.URL theURL) {
    if(getDefaultNameSpace() != null) return;
    InputStream is = null;
    String baseURI = null;
    try {
      is = theURL.openStream();
      baseURI = peekBaseURIFromRDF(is);
    } catch (Exception ex) {
      logger.debug("Problem opening stream for URL "+theURL, ex);
    } finally {
      if(is != null) {
        try {
          is.close();
        } catch(Exception ex) {
          logger.debug("Problem closing URL stream for "+theURL,ex);
        }
      }
    }
    if(baseURI != null) {
      String baseURI2 = makeDnsUri(baseURI);
      logger.info("Default name space set from URI peeking " + baseURI+" to "+baseURI2);
      setDefaultNameSpace(baseURI2);
    }
  }
  protected void setDefaultNameSpaceByPeeking(File theFile) {
    if(getDefaultNameSpace() != null) return;
    String baseURI = null;
    InputStream is = null;
    try {
      is = new FileInputStream(theFile);
      baseURI = peekBaseURIFromRDF(is);
    } catch (Exception ex) {
      logger.debug("Problem opening stream for File "+theFile, ex);
    } finally {
      if(is != null) {
        try {
          is.close();
        } catch(Exception ex) {
          logger.debug("Problem closing URL stream for "+theFile,ex);
        }
      }
    }
    if(baseURI != null) {
      String baseURI2 = makeDnsUri(baseURI);
      logger.info("Default name space set from file peeking "+baseURI+" to " + baseURI);
      setDefaultNameSpace(baseURI2);
    }
  }

  protected String peekBaseURIFromRDF(InputStream is) throws XMLStreamException {
    String baseURI = null;
    XMLStreamReader sr =
      XMLInputFactory.newInstance().createXMLStreamReader(is);
    if(sr.hasNext()) {
      sr.nextTag(); // skip to a start element
      QName qn = sr.getName();
      if(qn.toString().equals("{http://www.w3.org/1999/02/22-rdf-syntax-ns#}RDF")) {
        baseURI = sr.getAttributeValue("http://www.w3.org/XML/1998/namespace","base");
      }
    }
    return baseURI;
  }

  public void writeOntologyData(File selectedFile,
      OConstants.OntologyFormat format, boolean includeImports) {
    Writer writer = null;
    try {
      writer = new OutputStreamWriter(new FileOutputStream(selectedFile), 
          "UTF-8");
      ((OntologyServiceImplSesame) ontologyService).writeOntologyData(writer,
          format, includeImports);      
    } catch (IOException ex) {
      throw new GateOntologyException("Could not open writer for file " +
          selectedFile.getAbsolutePath(), ex);
    } finally {
      try {
        if(writer != null) writer.close();
      } catch(IOException e) {
        throw new GateOntologyException("Could not close writer for file " +
            selectedFile.getAbsolutePath(), e);
      }
    }

  }

  public void writeOntologyData(OutputStream out, OntologyFormat format,
      boolean includeImports) {
    Writer writer;
    try {
      writer = new OutputStreamWriter(out, "UTF-8");
    } catch (IOException ex) {
      throw new GateOntologyException("Could not open writer", ex);
    }
    ((OntologyServiceImplSesame) ontologyService).writeOntologyData(writer,
        format, includeImports);
  }

  public void writeOntologyData(Writer out, OntologyFormat format,
      boolean includeImports) {
    ((OntologyServiceImplSesame) ontologyService).writeOntologyData(out,
        format, includeImports);
  }

  /**
   * Load the system imports into a repository that does not have them
   * loaded yet.
   */
  public void loadSystemImports() {
    // according to a discussion with Ivan Peikov from OntoText, these
    // imports are not necessary any more. For now this method is left
    // here for reference and the code of the LRs is not chenged, but
    // if no "system" imports are needed anywhere in the future, all the
    // relevant code can be removed.
    // UPDATE 2010-02-10: as it turns out, the owl.rdfs import is needed
    // to correctly define the predefined owl:AnnotationProperty properties
    // like rdfs:label
    
    pluginDir = getPluginDir();
    File owlFile = new File(new File(pluginDir, "config"), "owl.rdfs");
    ((OntologyServiceImplSesame) ontologyService).loadSystemImport(owlFile,
        "http://www.w3.org/2002/07/owl#", OConstants.OntologyFormat.RDFXML);

    //File rdfsFile = new File(new File(pluginDir, "config"), "rdf-schema.rdf");
    //((OntologyServiceImplSesame) ontologyService).loadSystemImport(rdfsFile,
    //    "http://www.w3.org/2000/01/rdf-schema#", OConstants.OntologyFormat.RDFXML);
    
  }

  @Override
  public void cleanOntology() {
    super.cleanOntology();
    loadSystemImports();
  }


  public OntologyBooleanQuery createBooleanQuery(String query, QueryLanguage lang) {
    return new UtilBooleanQuery(
        ((OntologyServiceImplSesame)ontologyService).getSesameManager(),
        query, lang);
  }

  public OntologyTupleQuery createTupleQuery(String query, QueryLanguage lang) {
    return new UtilTupleQueryIterator(
        ((OntologyServiceImplSesame)ontologyService).getSesameManager(),
        query, lang);
    
  }

  @Deprecated
  public String executeQuery(String serqlQuery) {
    return ((OntologyServiceImplSesame)ontologyService).executeQuery(serqlQuery);
  }

  
  public static final String TRIPLE_CONTEXT_DATA =
      "http://gate.ac.uk/dummyuri/OWLIM3OntologyPlugin/#DataContext";

  public static final String TRIPLE_CONTEXT_META =
      "http://gate.ac.uk/dummyuri/OWLIM3OntologyPlugin/#MetaContext";

  
  // Set this ontology to be a meta ontology. This only changes the URI of
  // the graph where things get stored, thus allowing one and the same 
  // Sesame repository to be used for both the Meta and the Domain ontology.
  public void setTripleContextURI(String uri) {
    ((OntologyServiceImplSesame)ontologyService).setContextURIString(uri);
  }
  
  // Below is the stuff added for implementing the OntologyTripleStore 
 
  public  OntologyTripleStore getOntologyTripleStore() {
    OntologyTripleStore ots = 
      ontologyService.getOntologyTripleStore();
    return ots;
  }
  
  
}
