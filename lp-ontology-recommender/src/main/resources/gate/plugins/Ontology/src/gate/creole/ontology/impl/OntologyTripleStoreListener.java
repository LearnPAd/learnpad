/*
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Johann Petrak
 *
 *  $Id:  $
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.Literal;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.OURI;

/**
 * The interface that must be implemented by an OntologyTripleStore listener.
 * The listener's tripleAdded  or tripleRemoved methods are invoked according
 * the the second parameter specified in the 
 * {@link OntologyTripleStore.addOntologyTripleStoreListener(OntologyTripleStoreListener, boolean)}
 * method: if that parameter was true, only direct triple additions or removals
 * carried out via the OntologyTripleStore object are observed, otherwise 
 * all additions or removals including those from other Ontology API methods 
 * are observed (NOTE: the latter is not implemented yet!!). 
 * <p>
 * The subject, predicate and object values passed are the same that were
 * used for the actual triple addition or removal. For removals, a null
 * value indicates a wildcard meaning 
 * "all triples having any value for this parameter".
 * 
 * @author Johann Petrak
 */
public interface OntologyTripleStoreListener {
  public void tripleAdded(ONodeID subject, OURI predicate, ONodeID object);
  public void tripleAdded(ONodeID subject, OURI predicate, Literal object);
  public void tripleRemoved(ONodeID subject, OURI predicate, ONodeID object);
  public void tripleRemoved(ONodeID subject, OURI predicate, Literal object);
}
