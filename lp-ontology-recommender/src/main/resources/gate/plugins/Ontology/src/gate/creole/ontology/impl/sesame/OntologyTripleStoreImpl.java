/*
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  $Id: OntologyServiceImplSesame.java 14003 2011-06-12 15:23:44Z johann_p $
 */
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.OURI;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.OntologyTripleStore;
import gate.creole.ontology.OntologyTripleStoreListener;
import gate.creole.ontology.Triple;
import gate.util.ClosableIterator;
import gate.util.GateRuntimeException;
import java.util.ArrayList;
import java.util.List;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

/**
 * Implementation of an OntologyTripleStore for the Sesame ontology backend.
 * 
 * @author Johann Petrak
 */
public class OntologyTripleStoreImpl implements OntologyTripleStore {

  protected Ontology ontology;
  protected SesameManager sesameManager;
  protected RepositoryConnection repositoryConnection;
  protected List<OntologyTripleStoreListener> listeners = 
    new ArrayList<OntologyTripleStoreListener>();
  protected org.openrdf.model.URI contextURI;
  // disallow default constructor
  private OntologyTripleStoreImpl() {} 
  OntologyTripleStoreImpl(Ontology onto, SesameManager sm, 
    org.openrdf.model.URI curi) {
    ontology = onto;
    sesameManager = sm;
    repositoryConnection = sm.getRepositoryConnection();
    contextURI = curi;
 }
  

  public void addTriple(ONodeID subject, OURI predicate, ONodeID object) {
    if(subject == null || predicate == null || object == null) {
      throw new GateRuntimeException(
        "triple add - none subject/predicate/object may be null: "+
        subject+"/"+predicate+"/"+object);
    }
    Resource rs = sesameManager.toSesameResource(subject);
    URI rp = sesameManager.toSesameURI(predicate);
    Resource ro = sesameManager.toSesameResource(object);
    try {
      repositoryConnection.add(rs, rp, ro, contextURI);
    } catch (RepositoryException ex) {
       throw new GateOntologyException(
          "error while adding statement into the repository where subject:" + subject + " predicate:" + predicate + " objectURI:" + object, ex);     
    }
    for(OntologyTripleStoreListener listener : listeners) {
      listener.tripleAdded(subject, predicate, object);
    }    
  }
  
  public void addTriple(ONodeID subject, OURI predicate, gate.creole.ontology.Literal object) {
    if(subject == null || predicate == null || object == null) {
      throw new GateRuntimeException(
        "triple add - none subject/predicate/literal may be null: "+
        subject+"/"+predicate+"/"+object);
    }
    Resource s = sesameManager.toSesameResource(subject);
    URI p = sesameManager.toSesameURI(predicate);
    org.openrdf.model.Literal o = sesameManager.toSesameLiteral(object);
    try {
      repositoryConnection.add(s, p, o, contextURI);
    } catch (RepositoryException ex) {
       throw new GateOntologyException(
          "error while adding statement into the repository where subject:" + subject + " predicate:" + predicate + " objectURI:" + object, ex);     
    }
    for(OntologyTripleStoreListener listener : listeners) {
      listener.tripleAdded(subject, predicate, object);
    }    
  }
  
  public void removeTriple(ONodeID subject, OURI predicate, ONodeID object) {
    Resource rs = sesameManager.toSesameResource(subject);
    URI rp = sesameManager.toSesameURI(predicate);
    Resource ro = sesameManager.toSesameResource(object);
    try {
      repositoryConnection.remove(rs, rp, ro);
    } catch (RepositoryException ex) {
       throw new GateOntologyException(
          "error while removing statement from the repository where subject:" + subject + " predicate:" + predicate + " objectURI:" + object, ex);     
    }
    for(OntologyTripleStoreListener listener : listeners) {
      listener.tripleRemoved(subject, predicate, object);
    }
  }
  
  public void removeTriple(ONodeID subject, OURI predicate, gate.creole.ontology.Literal object) {
    Resource s = sesameManager.toSesameResource(subject);
    URI p = sesameManager.toSesameURI(predicate);
    org.openrdf.model.Literal o = sesameManager.toSesameLiteral(object);
    try {
      repositoryConnection.remove(s, p, o);
    } catch (RepositoryException ex) {
       throw new GateOntologyException(
          "error while removing statement from the repository where subject:" + subject + " predicate:" + predicate + " objectURI:" + object, ex);     
    }    
    for(OntologyTripleStoreListener listener : listeners) {
      listener.tripleRemoved(subject, predicate, object);
    }   
  }

  public synchronized void addOntologyTripleStoreListener(OntologyTripleStoreListener listener) {
    if(listener == null) {
      throw new GateRuntimeException("Listener object must not be null");
    }
    listeners.add(listener);
  }

  public synchronized void removeOntologyTripleStoreListener(OntologyTripleStoreListener listener) {
    if(listener == null) {
      throw new GateRuntimeException("Listener object must not be null");
    }
    listeners.remove(listener);
  }

  // methods we may bump to the interface later
  /**
   * Check if a triple with the given subject, predicate and object exists,
   * either asserted or inferred.
   * 
   * @param subject
   * @param predicate
   * @param object
   * @return 
   */
  public boolean hasTriple(ONodeID subject, OURI predicate, ONodeID object) {
    Resource rs = sesameManager.toSesameResource(subject);
    URI rp = sesameManager.toSesameURI(predicate);
    Resource ro = sesameManager.toSesameResource(object);
    try {
      return repositoryConnection.hasStatement(rs, rp, ro, true);
    } catch (RepositoryException ex) {
      throw new GateOntologyException("Could not do hasStatement for "+rs+"/"+rp+"/"+ro);
    }  
  }
  
  /**
   * Check if a triple with the given subject, predicate and object exists,
   * either asserted or inferred.
   * 
   * @param subject
   * @param predicate
   * @param object
   * @return 
   */
  public boolean hasTriple(ONodeID subject, OURI predicate, 
    gate.creole.ontology.Literal object) 
  {
    Resource rs = sesameManager.toSesameResource(subject);
    URI rp = sesameManager.toSesameURI(predicate);
    org.openrdf.model.Literal ro = sesameManager.toSesameLiteral(object);
    try {
      return repositoryConnection.hasStatement(rs, rp, ro, true);
    } catch (RepositoryException ex) {
      throw new GateOntologyException("Could not do hasStatement for "+rs+"/"+rp+"/"+ro);
    }      
  }

  /**
   * Check if a triple with the given subject, predicate and object has been
   * asserted.
   * 
   * @param subject
   * @param predicate
   * @param object
   * @return 
   */
  public boolean hasAssertedTriple(ONodeID subject, OURI predicate, ONodeID object) {
    Resource rs = sesameManager.toSesameResource(subject);
    URI rp = sesameManager.toSesameURI(predicate);
    Resource ro = sesameManager.toSesameResource(object);
    try {
      return repositoryConnection.hasStatement(rs, rp, ro, false);
    } catch (RepositoryException ex) {
      throw new GateOntologyException("Could not do hasStatement for "+rs+"/"+rp+"/"+ro);
    }  
  }
  
  
  /**
   * Check if a triple with the given subject, predicate and object has been
   * asserted.
   * 
   * @param subject
   * @param predicate
   * @param object
   * @return 
   */
  public boolean hasAssertedTriple(ONodeID subject, OURI predicate, 
    gate.creole.ontology.Literal object) 
  {
    Resource rs = sesameManager.toSesameResource(subject);
    URI rp = sesameManager.toSesameURI(predicate);
    org.openrdf.model.Literal ro = sesameManager.toSesameLiteral(object);
    try {
      return repositoryConnection.hasStatement(rs, rp, ro, false);
    } catch (RepositoryException ex) {
      throw new GateOntologyException("Could not do hasStatement for "+rs+"/"+rp+"/"+ro);
    }      
  }
  
  ClosableIterator<Triple> getTriples(ONodeID subject, OURI predicate, LiteralOrONodeID object)  {
    Resource rs = sesameManager.toSesameResource(subject);
    URI rp = sesameManager.toSesameURI(predicate);
    Value ro = null;
    if(object.isLiteral()) {
      ro = sesameManager.toSesameLiteral(object.getLiteral());
    } else {
      ro = sesameManager.toSesameResource(object.getONodeID());
    }
    RepositoryResult<Statement> statements = null;
    try {
      statements = repositoryConnection.getStatements(rs, rp, ro, false);
    } catch (RepositoryException ex) {
      throw new GateOntologyException("Error getting triples",ex);
    } finally {
      if (statements != null) {
        try {
          statements.close();
        } catch (RepositoryException ex) {
          // ignore
        }
      }
    }
    ClosableIterator<Triple> it = new ClosableTripleIterator(statements);
    return it;
  }
  
  
  
  // methods specific to the implementation, not present on the interface  
  void setContextURI(org.openrdf.model.URI theURI) {
    contextURI = theURI;
  }
 
  public class ClosableTripleIterator implements ClosableIterator<Triple> {

    protected RepositoryResult<Statement> statements;
    
    public ClosableTripleIterator(RepositoryResult<Statement> statements) {
      this.statements = statements;
    }
    public void close() {
      try {
        statements.close();
      } catch (RepositoryException ex) {
        throw new GateOntologyException("Error closing triple iterator",ex);
      } finally {
        statements = null;
      }
      statements = null;
    }

    public boolean hasNext() {
      try {
        return statements.hasNext();
      } catch (RepositoryException ex) {
        throw new GateOntologyException("Error getting next triple",ex);
      } finally {
        try {
          statements.close();
        } catch (RepositoryException ex) {
          // ignore;
        }
        statements = null;
      }
        
    }

    public Triple next() {
      Statement s = null;
      try {
        s = statements.next();
      } catch (RepositoryException ex) {
        throw new GateOntologyException("Error getting next triple",ex);
      } finally {
        try {
          statements.close();
        } catch (RepositoryException ex) {
          // ignore
        }
        statements = null;
      }
      Resource ss = s.getSubject();
      URI sp = s.getPredicate();
      Value so = s.getObject();      
      TripleImplSesame triple = 
        new TripleImplSesame(
          sesameManager.toGateONodeID(ss),
          sesameManager.toGateOURI(sp),
          sesameManager.toGateLiteralOrONodeID(so));
      return triple;
    }

    public void remove() {
      throw new UnsupportedOperationException(
        "Method remove() not supported for ClosableIterator<Triple>");
    }
    
  }
  
  
}
