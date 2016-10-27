/*
 *  ObjectPropertyImpl.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: ObjectPropertyImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OResource;
import gate.creole.ontology.OURI;
import gate.creole.ontology.ObjectProperty;
import gate.creole.ontology.Ontology;

/**
 * Implementation of the ObjectProperty
 * 
 * @author niraj
 * 
 */
public class ObjectPropertyImpl extends RDFPropertyImpl implements
                                                       ObjectProperty {
  /**
   * Constructor
   * 
   * @param aURI
   * @param ontology
   * @param repositoryID
   * @param owlimPort
   */
  public ObjectPropertyImpl(OURI aURI, Ontology ontology,
          OntologyService owlimPort) {
    super(aURI, ontology, owlimPort);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.ObjectProperty#getInverseProperties()
   */
  public Set<ObjectProperty> getInverseProperties() {
    Property[] properties = ontologyService.getInverseProperties(
            this.nodeId.toString());
    Set<ObjectProperty> set = new HashSet<ObjectProperty>();
    for(int i = 0; i < properties.length; i++) {
      byte type = properties[i].getType();
      if(type != OConstants.OBJECT_PROPERTY
              && type != OConstants.SYMMETRIC_PROPERTY
              && type != OConstants.TRANSITIVE_PROPERTY)
        throw new GateOntologyException(
                "Invalid Property type returned as an inverse property");
      set.add((ObjectProperty)Utils.createOProperty(
              this.ontology, this.ontologyService, properties[i].getUri(), properties[i]
                      .getType()));
    }
    return set;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.ObjectProperty#setInverseOf(gate.creole.ontology.ObjectProperty)
   */
  public void setInverseOf(ObjectProperty theInverse) {
    if(this == theInverse) {
      Utils
              .warning("setInverseOf(ObjectProperty) : The source and the argument properties are referring to the same property");
      return;
    }

    ontologyService.setInverseOf(nodeId.toString(), theInverse.getOURI()
            .toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.ObjectProperty#isValidRange(gate.creole.ontology.OInstance)
   */
  public boolean isValidRange(OInstance anInstance) {
    // for this to be true, the instance must be a member of the intersection
    // of all range classes. In other words, all the domain classes must either
    // be identical to a direct type of the instance or a subclass of a direct
    // type of an instance.
    Set<OResource> rangeOResources = getRange();
    Set<OClass> instanceClassClosure = anInstance.getOClasses(OConstants.Closure.TRANSITIVE_CLOSURE);
    return instanceClassClosure.containsAll(rangeOResources);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.DatatypeProperty#isValidDomain(gate.creole.ontology.OInstance)
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
    if(aResource instanceof OInstance)
      return isValidRange((OInstance)aResource);
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.RDFProperty#isValidDomain(gate.creole.ontology.OResource)
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
    ResourceInfo[] list = ontologyService.getRange(nodeId.toString());
    // this is a list of classes
    Set<OResource> domain = new HashSet<OResource>();
    // these resources can be anything - an instance, a property, or a
    // class
    for(int i = 0; i < list.length; i++) {
      domain.add(Utils.createOClass(this.ontology,
              this.ontologyService, list[i].getUri(), list[i].getClassType()));
    }
    return domain;
  }
}
