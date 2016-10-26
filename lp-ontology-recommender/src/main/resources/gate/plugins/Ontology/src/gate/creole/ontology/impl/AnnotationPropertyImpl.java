/*
 *  AnnotationPropertyImpl.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: AnnotationPropertyImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;

import java.util.HashSet;
import java.util.Set;
import gate.creole.ontology.AnnotationProperty;
import gate.creole.ontology.OResource;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.RDFProperty;
import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.OURI;

/**
 * Provides an implementation of the AnnotationProperty interface.
 * @author niraj
 */
public class AnnotationPropertyImpl
    extends RDFPropertyImpl
    implements AnnotationProperty
{
  /**
   * Constructor
   * 
   * @param aURI
   * @param ontology
   * @param repositoryID
   * @param owlimPort
   */
  public AnnotationPropertyImpl(OURI aURI, Ontology ontology,
          OntologyService owlimPort) {
    super(aURI, ontology, owlimPort);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#setEquivalentPropertyAs(gate.creole.ontology.RDFProperty)
   */
  @Override
  public void setEquivalentPropertyAs(RDFProperty theProperty) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#getEquivalentPropertyAs()
   */
  @Override
  public Set<RDFProperty> getEquivalentPropertyAs() {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isEquivalentPropertyAs(gate.creole.ontology.RDFProperty)
   */
  @Override
  public boolean isEquivalentPropertyAs(RDFProperty theProperty) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#addSuperProperty(gate.creole.ontology.RDFProperty)
   */
  public void addSuperProperty(RDFProperty property) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#removeSuperProperty(gate.creole.ontology.RDFProperty)
   */
  public void removeSuperProperty(RDFProperty property) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#getSuperProperties(byte)
   */
  @Override
  public Set<RDFProperty> getSuperProperties(byte closure) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isSuperPropertyOf(gate.creole.ontology.RDFProperty,
   *      byte)
   */
  @Override
  public boolean isSuperPropertyOf(RDFProperty theProperty, byte closure) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#addSubProperty(gate.creole.ontology.RDFProperty)
   */
  @Override
  public void addSubProperty(RDFProperty property) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#removeSubProperty(gate.creole.ontology.RDFProperty)
   */
  @Override
  public void removeSubProperty(RDFProperty property) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#getSubProperties(byte)
   */
  @Override
  public Set<RDFProperty> getSubProperties(byte closure) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isSubPropertyOf(gate.creole.ontology.RDFProperty,
   *      byte)
   */
  @Override
  public boolean isSubPropertyOf(RDFProperty theProperty, byte closure) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isFunctional()
   */
  @Override
  public boolean isFunctional() {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#setFunctional(boolean)
   */
  @Override
  public void setFunctional(boolean functional) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isInverseFunctional()
   */
  @Override
  public boolean isInverseFunctional() {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#setInverseFunctional(boolean)
   */
  @Override
  public void setInverseFunctional(boolean inverseFunctional) {
    throw new GateOntologyException(
            "This operation is not valid for AnnotationProperties.");
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isValidRange(gate.creole.ontology.OResource)
   */
  @Override
  public boolean isValidRange(OResource aResource) {
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isValidDomain(gate.creole.ontology.OResource)
   */
  @Override
  public boolean isValidDomain(OResource aResource) {
    return true;
  }

  /*
   * (non-Javadoc)
   *  
   * @see gate.creole.ontology.RDFProperty#getDomain()
   */
  @Override
  public Set<OResource> getDomain() {
    return new HashSet<OResource>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#getRange()
   */
  @Override
  public Set<OResource> getRange() {
     return new HashSet<OResource>();
  }
}
