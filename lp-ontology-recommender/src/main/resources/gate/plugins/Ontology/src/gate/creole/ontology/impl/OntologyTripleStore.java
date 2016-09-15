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
 * An OntologyTripleStore gives access to the underlying triple store of 
 * an ontology implementation, if the implementation supports it.
 * In that case, the OntologyTripleStore object can be acquired using the
 * method {@link Ontology#getOntologyTripleStore()}.
 * 
 * @author Johann Petrak
 */
public interface OntologyTripleStore {
  /** 
   * Add a triple to the ontology. In most cases, you will want to 
   * use {@link OURI} in place of {@link ONodeID} for the subject and 
   * object parameters.
   *
   * @param subject the subject of the triple to add
   * @param predicate the predicate of the triple to add
   * @param object the object of the triple to add
   */
  public void addTriple(ONodeID subject, OURI predicate, ONodeID object);
  
  /** 
   * Add a triple with a literal triple object to the ontology. 
   * In most cases, you will want to 
   * use {@link OURI} in place of {@link ONodeID} for the subject parameter.
   *
   * @param subject the subject of the triple to add
   * @param predicate the predicate of the triple to add
   * @param object the object of the triple to add
   */
  public void addTriple(ONodeID subject, OURI predicate, Literal object);
  
  /** 
   * Remove a statement(triple) with a non-literal triple object from the 
   * ontology triple
   * store. The null value can be used for any of the subject, predicate,
   * or object parameters as a wildcard indicator: in that case, triples
   * with any value for that parameter will be removed.
   * <p>
   * NOTE: if a null value is passed for the object parameter, triples with
   * any value for the triple object, no matter if it is a literal or
   * non-literal or a blank node will be removed!!
   * 
   * @param subject the triple subject to match for triple removals or null 
   * @param predicate the triple predicate to match for triple removals or null
   * @param object the triple object to match for triple removals or null
   */
  public void removeTriple(ONodeID subject, OURI predicate, ONodeID object);
  /** 
   * Remove a statement(triple) with a non-literal triple object from the 
   * ontology triple
   * store. The null value can be used for any of the subject, predicate,
   * or object parameters as a wildcard indicator: in that case, triples
   * with any value for that parameter will be removed.
   * <p>
   * NOTE: if a null value is passed for the object parameter, triples with
   * any value for the triple object, no matter if it is a literal or
   * non-literal or a blank node will be removed!!
   * 
   * @param subject the triple subject to match for triple removals or null 
   * @param predicate the triple predicate to match for triple removals or null
   * @param object the triple object to match for triple removals or null
   */
  public void removeTriple(ONodeID subject, OURI predicate, Literal object);

  /** 
   * Add a listener for ontology triple store additions and removals. 
   * The listener will get the parameters of the original addition or 
   * deletion request, not any indication of which concrete triples were 
   * actually added or removed!
   * <p>
   * NOTE: this is not fully implemented yet and will at the moment only
   * receive events for direct triple additions and removal from this interface,
   * not for triple additions or removals initiaded by other Ontology API
   * operations.
   * 
   * @param listener The OntologyTripleStoreListener instance
   * @param directTripleEventsOnly if true, only listen for direct triple 
   * addition or removal operations through this interface; if false, also
   * listen for triple additions or removals caused by other Ontology API 
   * calls (NOTE: the behavior for false is not implemented yet!!)
   */
  public void addOntologyTripleStoreListener(OntologyTripleStoreListener listener, 
      boolean directTripleEventsOnly);
  /** 
   * Add a listener for ontology triple store additions and removals. 
   * The listener will get the parameters of the original addition or 
   * deletion request, not any indication of which concrete triples were 
   * actually added or removed!
   * <p>
   * NOTE: this is not fully implemented yet and will at the moment only
   * receive events for direct triple additions and removal from this interface,
   * not for triple additions or removals initiaded by other Ontology API
   * operations.
   * 
   * @param listener The OntologyTripleStoreListener instance
   * @param directTripleEventsOnly if true, only listen for direct triple 
   * addition or removal operations through this interface; if false, also
   * listen for triple additions or removals caused by other Ontology API 
   * calls (NOTE: the behavior for false is not implemented yet!!)
   */
  public void removeOntologyTripleStoreListener(OntologyTripleStoreListener listener);
}
