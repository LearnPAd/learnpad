/*
 *  DatatypePropertyImpl.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: DatatypePropertyImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import gate.creole.ontology.DataType;
import gate.creole.ontology.DatatypeProperty;
import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OResource;
import gate.creole.ontology.OURI;
import gate.creole.ontology.Ontology;


/**
 * Implementation of the DatatypeProperty
 * @author niraj
 * 
 */
public class DatatypePropertyImpl extends RDFPropertyImpl implements
                                                         DatatypeProperty {
  /**
   * Constructor
   * @param aURI
   * @param ontology
   * @param owlimPort
   */
  public DatatypePropertyImpl(OURI aURI, Ontology ontology,
          OntologyService owlimPort) {
    super(aURI, ontology, owlimPort); 
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.DatatypeProperty#getDataType()
   */
  public DataType getDataType() {
      String datatypeURI = ontologyService.getDatatype(getOURI());
      if(datatypeURI == null) { return DataType.getStringDataType(); }
      return OntologyUtilities.getDataType(datatypeURI);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.DatatypeProperty#isValidDataTypeValue(java.lang.String)
   */
  public boolean isValidDataTypeValue(String value) {
    return getDataType().isValidValue(value);
  }

  /*
   * Check if the given instance is compatible with the domain of this 
   * property.
   */
  public boolean isValidDomain(OInstance anInstance) {
    // For each class in the domain, there must
    // be a direct type in the instance that is either identical or a subclass
    // In other words, if we expand the classes of the instance, all the 
    // domain classes must be contained in the expanded set.
    Set<OResource> domainOResources = getDomain();
    Set<OClass> instanceClassClosure = anInstance.getOClasses(OConstants.Closure.TRANSITIVE_CLOSURE);
    return instanceClassClosure.containsAll(domainOResources);
  }
  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isValidRange(gate.creole.ontology.OResource)
   */
  public boolean isValidRange(OResource aResource) {
    throw new GateOntologyException(
            "Datatype Properties do not have Range, but a Datatype. Please use the isValidDatatypeValue(String value) method.");
  }

  /*
   * Included for compatibility with the super class. 
   * If this is called with a resource that is not an OInstance, false 
   * is returned.
   */
  public boolean isValidDomain(OResource aResource) {
    if(aResource instanceof OInstance)
      return isValidDomain((OInstance)aResource);
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#getDomain()
   */
  public Set<OResource> getDomain() {
      return ontologyService.getDomain(getOURI());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#getRange()
   */
  public Set<OResource> getRange() {
    throw new GateOntologyException(
            "Datatype Properties do not have Range, but a Datatype specified. Please use the getDatatype() method.");
  }
}
