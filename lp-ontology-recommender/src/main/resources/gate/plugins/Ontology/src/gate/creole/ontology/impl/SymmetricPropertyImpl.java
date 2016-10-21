/*
 *  SymmetricPropertyImpl.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: SymmetricPropertyImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.OURI;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.SymmetricProperty;

/**
 * Implementation of the SymmetricProperty
 * @author niraj
 *
 */
public class SymmetricPropertyImpl extends ObjectPropertyImpl implements
                                                             SymmetricProperty {
  /**
   * Constructor
   * @param aURI
   * @param ontology
   * @param repositoryID
   * @param owlimPort
   */
  public SymmetricPropertyImpl(OURI aURI, Ontology ontology,
          OntologyService owlimPort) {
    super(aURI, ontology, owlimPort);
  }
}
