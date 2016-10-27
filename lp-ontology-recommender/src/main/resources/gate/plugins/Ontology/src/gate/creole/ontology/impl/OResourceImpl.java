/*
 *  OResourceImpl.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: OResourceImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.openrdf.model.vocabulary.RDFS;

import gate.creole.ontology.AnnotationProperty;
import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.Literal;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.OResource;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.RDFProperty;
import gate.creole.ontology.URI;

/**
 * Constructor
 * 
 * @author niraj
 * 
 */
public class OResourceImpl implements OResource, Comparable<OResource> {


  /**
   * instance of the OWLIMServices
   */
  protected OntologyService ontologyService;

  /**
   * URI of the resource
   */
  protected ONodeID nodeId;

  /**
   * The ontology the current resource belongs to
   */
  protected AbstractOntologyImpl ontology;

  /**
   * Constructor
   * 
   * @param aURI
   * @param repositoryID
   * @param owlimPort
   */
  public OResourceImpl(ONodeID aURI, Ontology ontology,
          OntologyService owlimPort) {
    this.nodeId = aURI;
    this.ontologyService = owlimPort;
    this.ontology = (AbstractOntologyImpl)ontology;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#getURI()
   */
  @SuppressWarnings("deprecation")
  public URI getURI() {
    // we have to keep this around for making the ontology editor work
    // with both the new and old implementation as long as the old one
    // is around
    return new URI(this.nodeId.toString(),this.nodeId.isAnonymousResource());
  }



  public ONodeID getONodeID() {
    return this.nodeId;
  }

  public void setONodeID(ONodeID id) {
    this.nodeId =  id;
  }
  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#getURI()
   */
  public void setURI(URI uri) {
    throw new GateOntologyException(
            "This operation is not allowed in this version!");
  }

  /**
   * This method returns a set of labels specified on this resource.
   * 
   * @return
   */
  public Set<Literal> getLabels() {
    List<Literal> list = ontologyService.getAnnotationPropertyValues(
            this.nodeId, ontology.OURI_RDFS_LABEL);
    Set<Literal> set = new HashSet<Literal>();
    set.addAll(list);
    return set;
  }

  /**
   * This method returns a set of comments specified on this resource.
   * 
   * @return
   */
  public Set<Literal> getComments() {
    List<Literal> list = ontologyService.getAnnotationPropertyValues(
            this.nodeId, ontology.OURI_RDFS_COMMENT);
    Set<Literal> set = new HashSet<Literal>();
    set.addAll(list);
    return set;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#getComment(java.lang.String)
   */
  public String getComment(Locale language) {
    List<Literal> set = 
      ontologyService.getAnnotationPropertyValues(this.nodeId,
        ontology.OURI_RDFS_COMMENT);
    // find the first value we find with the specified language
    String comment = null;
    for(Literal l : set) {
      if(l.getLanguage().equals(language)) {
        comment = l.getValue();
        break;
      }
    }
    return comment;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#setComment(java.lang.String,
   *      java.lang.String)
   */
  public void setComment(String aComment, Locale language) {
    ontologyService.addAnnotationPropertyValue(this.nodeId,
      ontology.OURI_RDFS_COMMENT, aComment, language != null
                    ? language.getLanguage()
                    : null);
    Literal l = new Literal(aComment, language);
    // TODO: !!!!! do something about the map here!!!
    AnnotationProperty annp =
        ontology.getAnnotationProperty(ontology.createOURI(OConstants.RDFS.COMMENT));
    ontology.fireResourcePropertyValueChanged(this,
        (RDFProperty) annp,
        (Object)l,
        OConstants.ANNOTATION_PROPERTY_VALUE_ADDED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#getLabel(java.lang.String)
   */
  public String getLabel(Locale language) {
    List<Literal> set = 
      ontologyService.getAnnotationPropertyValues(this.nodeId,
        ontology.OURI_RDFS_LABEL);
    // find the first value we find with the specified language
    String label = null;
    for(Literal l : set) {
      if(l.getLanguage().equals(language)) {
        label = l.getValue();
        break;
      }
    }
    return label;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#setLabel(java.lang.String,
   *      java.lang.String)
   */
  public void setLabel(String aLabel, Locale language) {
    ontologyService.addAnnotationPropertyValue(this.nodeId,
            ontology.OURI_RDFS_LABEL, aLabel, language != null
                    ? language.getLanguage()
                    : null);
    Literal l = new Literal(aLabel, language);
    // TODO: TEST
    ontology.fireResourcePropertyValueChanged(this,
        Utils.createOProperty(ontology, ontologyService, RDFS.LABEL.toString(),
          OConstants.ANNOTATION_PROPERTY),
        (Object)l,
        OConstants.ANNOTATION_PROPERTY_VALUE_ADDED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#getName()
   */
  public String getName() {
    return this.nodeId.getResourceName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#getOntology()
   */
  public Ontology getOntology() {
    return this.ontology;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#addAnnotationPropertyValue(gate.creole.ontology.AnnotationProperty,
   *      gate.creole.ontology.Literal)
   */
  public void addAnnotationPropertyValue(
          AnnotationProperty theAnnotationProperty, Literal literal) {
    //OResource res = ontology.getOResourceFromMap(theAnnotationProperty.getOURI()
    //        .toString());
    RDFProperty res = ontology.getProperty(theAnnotationProperty.getOURI());
    if(res == null) {
      Utils
              .error(theAnnotationProperty.getOURI().toTurtle()
                      + " does not exist");
      return;
    }

    if(!(res instanceof AnnotationProperty)) {
      Utils.error(theAnnotationProperty.getOURI().toTurtle()
              + " is not a registered annotation property");
      return;
    }

    ontologyService.addAnnotationPropertyValue(this.nodeId,
            theAnnotationProperty.getOURI(), literal.getValue(),
            literal.getLanguage() != null
                    ? literal.getLanguage().getLanguage()
                    : null);
    ontology.fireResourcePropertyValueChanged(this, theAnnotationProperty, literal, OConstants.ANNOTATION_PROPERTY_VALUE_ADDED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#getAnnotationPropertyValues(gate.creole.ontology.AnnotationProperty)
   */
  public List<Literal> getAnnotationPropertyValues(
          AnnotationProperty theAnnotationProperty) {
    return ontologyService.getAnnotationPropertyValues(
            this.nodeId, theAnnotationProperty.getOURI());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#removeAnnotationPropertyValue(gate.creole.ontology.AnnotationProperty,
   *      gate.creole.ontology.Literal)
   */
  public void removeAnnotationPropertyValue(
          AnnotationProperty theAnnotationProperty, Literal literal) {
    ontologyService.removeAnnotationPropertyValue(this.nodeId,
            theAnnotationProperty.getOURI(), literal);
    ontology.fireResourcePropertyValueChanged(this, theAnnotationProperty, literal, OConstants.ANNOTATION_PROPERTY_VALUE_REMOVED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OResource#removeAnnotationPropertyValues(gate.creole.ontology.AnnotationProperty)
   */
  public void removeAnnotationPropertyValues(
          AnnotationProperty theAnnotationProperty) {
    ontologyService.removeAnnotationPropertyValues(
            this.nodeId, theAnnotationProperty.getOURI());
    ontology.fireResourcePropertyValueChanged(this, theAnnotationProperty, null, OConstants.ANNOTATION_PROPERTY_VALUE_REMOVED_EVENT);
  }

  /**
   * This method returns the annotation properties set on this resource.
   * 
   * @return
   */
  public Set<AnnotationProperty> getSetAnnotationProperties() {
    Property[] properties = ontologyService.getAnnotationProperties(
            this.nodeId.toString());
    Set<AnnotationProperty> annotProps = new HashSet<AnnotationProperty>();
    for(int i = 0; i < properties.length; i++) {
      if(properties[i].getType() != OConstants.ANNOTATION_PROPERTY) {
        throw new GateOntologyException("The property :"
                + properties[i].getUri()
                + " returned from the repository is not an AnnotationProperty");
      }
      String propUri = properties[i].getUri();
      OResource resource = null; //ontology.getOResourceFromMap(propUri);
      if(resource == null) {
        resource = new AnnotationPropertyImpl(ontology.createOURI(propUri),
                this.ontology, ontologyService);
        //ontology.addOResourceToMap(propUri, resource);
      }
      annotProps.add((AnnotationProperty)resource);
    }
    return annotProps;
  }

  /**
   * Checks if the resource has the provided annotation property set on
   * it with the specified value.
   * 
   * @param aProperty
   * @param aValue
   * @return
   */
  public boolean hasAnnotationPropertyWithValue(AnnotationProperty aProperty,
          Literal aValue) {
    List<Literal> literals = getAnnotationPropertyValues(aProperty);
    for(Literal l : literals) {
      if(l.getValue().equals(aValue.getValue())) {
        if(l.getDataType() != null && aValue.getDataType() != null) {
          if(!aValue.getDataType().getXmlSchemaURIString().equals(
                  l.getDataType().getXmlSchemaURIString())) continue;
        }
        
        if(l.getLanguage() != null && aValue.getLanguage() != null) {
          if(!aValue.getLanguage().toString().equals(l.getLanguage().toString())) continue;          
        }
        return true;
      }
    }
    return false;
  }

  /**
   * This method returns all the set properties set on this resource.
   * 
   * @return
   */
  public Set<RDFProperty> getAllSetProperties() {
    Set<RDFProperty> toReturn = new HashSet<RDFProperty>();
    toReturn.addAll(getSetAnnotationProperties());
    return toReturn;
  }

  /**
   * This method returns a set of all properties where the current
   * resource has been specified as one of the domain resources. Please
   * note that this method is different from the getAllSetProperties()
   * method which returns a set of properties set on the resource. For
   * each property in the ontology, this method checks if the current
   * resource is valid domain. If so, the property is said to be
   * applicable, and otherwise not..
   * 
   * @return
   */
  public Set<RDFProperty> getPropertiesWithResourceAsDomain() {
    Set<RDFProperty> toReturn = new HashSet<RDFProperty>();
    Property[] properties = this.ontologyService.getPropertiesWithResourceAsDomain(
            this.getONodeID().toString());
    for(int i = 0; i < properties.length; i++) {
      toReturn.add((RDFProperty)Utils.createOProperty(
              this.ontology, ontologyService, properties[i].getUri(), properties[i]
                      .getType()));
    }
    return toReturn;
  }

  /**
   * This method returns a set of all properties where the current
   * resource has been specified as one of the range resources. Please
   * note that this method is different from the getAllSetProperties()
   * method which returns a set of properties set on the resource. For
   * each property in the ontology, this method checks if the current
   * resource is valid range. If so, the property is said to be
   * applicable, and otherwise not.
   * 
   * @return
   */
  public Set<RDFProperty> getPropertiesWithResourceAsRange() {
    Set<RDFProperty> toReturn = new HashSet<RDFProperty>();
    Property[] properties = this.ontologyService.getPropertiesWithResourceAsRange(
            this.getONodeID().toString());
    for(int i = 0; i < properties.length; i++) {
      toReturn.add((RDFProperty)Utils.createOProperty(
              this.ontology, ontologyService, properties[i].getUri(), properties[i]
                      .getType()));
    }
    return toReturn;
  }

  /**
   * String representation of the resource: its name and not the URI.
   */
  public String toString() {
    return this.getName();
  }

  /**
   * HashCode for this resource.
   */
  public int hashCode() {
    return this.getONodeID().toString().hashCode();
  }

  /**
   * equals method overriden tocompare URIs
   */
  public boolean equals(Object a) {
    if(a instanceof OResource) {
      return this.getONodeID().toString().equals(((OResource)a).getONodeID().toString());
    }
    return false;
  }

  public int compareTo(OResource other) {
    return this.getONodeID().toString().compareTo(other.getONodeID().toString());
  }


}
