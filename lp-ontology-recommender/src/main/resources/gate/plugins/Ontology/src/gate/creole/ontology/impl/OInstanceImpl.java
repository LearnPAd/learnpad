/*
 *  OInstanceImpl.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: OInstanceImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gate.creole.ontology.DataType;
import gate.creole.ontology.OValue;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.DatatypeProperty;
import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.InvalidValueException;
import gate.creole.ontology.Literal;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OResource;
import gate.creole.ontology.OURI;
import gate.creole.ontology.ObjectProperty;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.RDFProperty;

/**
 * Implementation of the OInstance class.
 *
 * @authos Johann Petrak
 * @author Niraj Aswani
 * 
 */
public class OInstanceImpl extends OResourceImpl implements OInstance {
  /**
   * Constructor
   * 
   * @param aURI
   * @param ontology
   * @param repositoryID
   * @param owlimPort
   */
  public OInstanceImpl(OURI aURI, Ontology ontology,
          OntologyService owlimPort) {
    super(aURI, ontology, owlimPort);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#getOClasses(byte)
   */
  public Set<OClass> getOClasses(byte closure) {
    //throw new UnsupportedOperationException("Method with this parameter not longer supported");
    Closure theClosure = closure == OConstants.DIRECT_CLOSURE ?
      Closure.DIRECT_CLOSURE : Closure.TRANSITIVE_CLOSURE;
    return getOClasses(theClosure);
  }
  
  public Set<OClass> getOClasses(Closure closure) {
      ResourceInfo[] oClasses = ontologyService.getClassesOfIndividual(
              this.nodeId.toString(), closure);
      Set<OClass> set = new HashSet<OClass>();
      for(int i = 0; i < oClasses.length; i++) {
        set.add(Utils.createOClass(this.ontology,
                this.ontologyService, oClasses[i].getUri(),
                oClasses[i].getClassType()));
      }
      return set;
  }

  /**
   * Make this individual an instance of another class. This does nothing
   * if the individual is already an instance of the given class. Note
   * that this cann lead to inconsistencies, e.g. if this class is disjunct
   * with a class of which the individual is already an instance.
   *
   * @param theClass - the OClass object for the class of which the individual
   * should be an instance.
   */
  public void addOClass(OClass theClass) {
    ONodeID classONodeID = theClass.getONodeID();
    if(classONodeID.isAnonymousResource()) {
      Utils.error("Cannot add individual "+this.getOURI()+
              " to an anonymous class:"+theClass.getONodeID());
      return;
    }
    ontologyService.addIndividual(theClass.getONodeID().toString(),
            this.getOURI().toString());
  }
  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#isInstanceOf(gate.creole.ontology.OClass,
   *      byte)
   */
  public boolean isInstanceOf(OClass aClass, byte closure) {
    //throw new UnsupportedOperationException("Method with these parameters not longer supported");
    Closure theClosure = closure == OConstants.DIRECT_CLOSURE ?
      Closure.DIRECT_CLOSURE : Closure.TRANSITIVE_CLOSURE;
    return isInstanceOf(aClass, theClosure);
  }

  public boolean isInstanceOf(OClass aClass, Closure closure) {
      //return ontologyService.hasIndividual(aClass.getONodeID().toString(),
      //        this.nodeId.toString(), closure);
      return ontologyService.hasInstance(this.getOURI(), aClass.getONodeID(), closure);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#setDifferentFrom(gate.creole.ontology.OInstance)
   */
  public void setDifferentFrom(OInstance theInstance) {
    if (this == theInstance) {
      Utils.warning("setDifferentFrom(theInstance) : the source and the argument instances refer to the same instance and therefore cannot be set as different from each other");
      return;
    }

    ontologyService.setDifferentIndividualFrom(this.nodeId,
      theInstance.getOURI());
    ontology.fireResourceRelationChanged(this, theInstance, OConstants.DIFFERENT_INSTANCE_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#getDifferentInstances()
   */
  public Set<OInstance> getDifferentInstances() {
      String[] oInsts = ontologyService.getDifferentIndividualFrom(
              this.nodeId.toString());
      Set<OInstance> set = new HashSet<OInstance>();
      for(int i = 0; i < oInsts.length; i++) {
        set.add(Utils.createOInstance(this.ontology,
                this.ontologyService, oInsts[i]));
      }
      return set;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#isDifferentFrom(gate.creole.ontology.OInstance)
   */
  public boolean isDifferentFrom(OInstance theInstance) {
      return ontologyService.isDifferentIndividualFrom(
        this.getOURI(), theInstance.getOURI());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#setSameInstanceAs(gate.creole.ontology.OInstance)
   */
  public void setSameInstanceAs(OInstance theInstance) {
      if(this == theInstance) {
        Utils
                .warning("setDifferentFrom(theInstance) : the source and the argument instances refer to the same instance and therefore cannot be set as same");
        return;
      }

      ontologyService.setSameIndividualAs(this.nodeId.toString(),
              theInstance.getOURI().toString());
      ontology.fireResourceRelationChanged(this, theInstance, OConstants.SAME_INSTANCE_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#getSameInstance()
   */
  public Set<OInstance> getSameInstance() {
      String[] oInsts = ontologyService.getSameIndividualAs(this.nodeId
              .toString());
      Set<OInstance> set = new HashSet<OInstance>();
      for(int i = 0; i < oInsts.length; i++) {
        set.add(Utils.createOInstance(this.ontology,
                this.ontologyService, oInsts[i]));
      }
      return set;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#isSameInstanceAs(gate.creole.ontology.OInstance)
   */
  public boolean isSameInstanceAs(OInstance theInstance) {
      return ontologyService.isSameIndividualAs(this.getOURI(),theInstance.getOURI());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#addRDFPropertyValue(gate.creole.ontology.RDFProperty,
   *      gate.creole.ontology.OResource)
   */
  public void addRDFPropertyValue(RDFProperty aProperty, OResource value)
          throws InvalidValueException {
      // we need to check if the current instance is a valid domain for
      // the property
      if(!aProperty.isValidDomain(this)) {
        Utils.error(this.getOURI().toTurtle()
                + " is not a valid domain for the property "
                + aProperty.getOURI().toString());
        return;
      }

      // we need to check if the current instance is a valid domain for
      // the property
      if(!aProperty.isValidRange(value)) {
        Utils.error(value.getONodeID().toTurtle()
                + " is not a valid range for the property "
                + aProperty.getOURI().toTurtle());
        return;
      }

      ontologyService.addRDFPropertyValue(this.nodeId,
              aProperty.getOURI(), value.getONodeID());
      ontology.fireResourcePropertyValueChanged(this, aProperty,
          value, OConstants.RDF_PROPERTY_VALUE_ADDED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#removeRDFPropertyValue(gate.creole.ontology.RDFProperty,
   *      gate.creole.ontology.OResource)
   */
  public void removeRDFPropertyValue(RDFProperty aProperty, OResource value) {
      ontologyService.removeRDFPropertyValue(this.nodeId,
              aProperty.getOURI(), value.getONodeID());
      ontology.fireResourcePropertyValueChanged(this, aProperty, value,
          OConstants.RDF_PROPERTY_VALUE_REMOVED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#getRDFPropertyValues(gate.creole.ontology.RDFProperty)
   */
  // TODO: this must be deprecated and/or done differently!

  // It is highly inefficient as it retrieves all individuals in here to
  // check, we should do this in the query, if posible!
  // DONE: we have replaced this with hasInstance

  // TODO: why does this not check for property values that coul be
  // literals?
  @Deprecated
  public List<OResource> getRDFPropertyValues(RDFProperty aProperty) {
    //System.out.println(aProperty.getOURI().toString());
    //Utils.warnDeprecation("getREDFPropertyValues");
    ResourceInfo[] list = ontologyService.getRDFPropertyValues(nodeId
              .toString(), aProperty.getOURI().toString());
      List<OResource> values = new ArrayList<OResource>();
      //List<String> individuals = Arrays.asList(ontologyService
      //        .getIndividuals());
      //ClosableIterator<OInstance> ii = ontologyService.getInstancesIterator(null,null);
      //List<String> individuals = new ArrayList<String>();
      //while(ii.hasNext()) {
      //  individuals.add(ii.next().getOURI().toString());
      //}
      // these resources can be anything - an instance, a property, or a
      // class
      // TODO: do something about the use of the map here!!!!
      for(int i = 0; i < list.length; i++) {
        // is it an individual
        if(ontologyService.hasInstance(ontology.createOURI(list[i].getUri()),null,null)) {
        //if(individuals.contains(list[i].toString())) {
          values.add(Utils.createOInstance(this.ontology,
                  this.ontologyService, list[i].getUri()));
          continue;
        }
        // is it a class
        if(ontologyService.hasClass(ontology.createOURI(
          list[i].getUri()))) {
          values.add(Utils.createOClass(this.ontology,
                  this.ontologyService, list[i].getUri(), list[i].getClassType()));
          continue;
        }

        // TODO: if we get something here that is not defined in the ontology,
        // we assume it is a property but that could be wrong if it is
        // the URI of a class or instance that is not defined as class or
        // instance in the ontology.
        // We probably should warn about this at some point but will
        // ignore it for now ...

        Property prop = ontologyService.getPropertyFromOntology(
                list[i].getUri());
        if(prop == null) {
          // System.err.println("Property is null for "+list[i]+"/"+list[i].getUri());
          continue;
        } else {
        values.add(Utils.createOProperty(this.ontology,
                this.ontologyService, prop.getUri(), prop.getType()));
        }
      }
      return values;
  }


  public List<OValue> getRDFPropertyOValues(RDFProperty aProperty) {
    List<LiteralOrONodeID> list =
        ontologyService.getRDFPropertyLiteralOrONodeIDs(
          this.nodeId, aProperty.getOURI());
    List<OValue> values = new ArrayList<OValue>();

    for(LiteralOrONodeID val : list) {
      if(val.isLiteral()) {
        values.add(new OValueImpl(val.getLiteral()));
      } else {
        ONodeID node = val.getONodeID();
        if(node instanceof OURI && ontologyService.hasInstance((OURI)node,null,null)) {
          values.add(new OValueImpl(Utils.createOInstance(
              this.ontology,
              this.ontologyService, 
              node.toString())));
          continue;
        }
        // is it a class ..
        if(ontologyService.hasClass(ontology.createOURI(node.toString()))) {
          values.add(new OValueImpl(Utils.createOClass(this.ontology,
                  this.ontologyService, node.toString(),
                  ontologyService.getClassType(node.toString()))));
          continue;
        }
        Property prop = ontologyService.getPropertyFromOntology(
                node.toString());
        if(prop == null) {
          System.err.println("Strange property value for instance "+
              this.nodeId+" property "+aProperty+": "+node);
          continue;
        } else {
          values.add(new OValueImpl(Utils.createOProperty(this.ontology,
                this.ontologyService, prop.getUri(), prop.getType())));
        }
      } // if literal
    } // for  list
    return values;
  }


  /**
   * This method returns the RDF properties set on this resource.
   * 
   * @return
   */
  public Set<RDFProperty> getSetRDFProperties() {
      Property[] properties = ontologyService.getRDFProperties(
              this.nodeId.toString());
      Set<RDFProperty> rdfProps = new HashSet<RDFProperty>();
      for(int i = 0; i < properties.length; i++) {
        if(properties[i].getType() != OConstants.RDF_PROPERTY) {
          throw new GateOntologyException("The property :"
                  + properties[i].getUri()
                  + " returned from the repository is not an RDFProperty");
        }
        String propUri = properties[i].getUri();
        OResource resource = null;
        resource = new RDFPropertyImpl(ontology.createOURI(propUri),
                  this.ontology, ontologyService);
        rdfProps.add((RDFProperty)resource);
      }
      return rdfProps;
  }

  /**
   * Checks if the resource has the provided RDF property set on it with
   * the specified value.
   * 
   * @param aProperty
   * @param aResource
   * @return
   */
  public boolean hasRDFPropertyWithValue(RDFProperty aProperty,
          OResource aResource) {
    List<OResource> resources = getRDFPropertyValues(aProperty);
    for(OResource r : resources) {
      if(r.equals(aResource)) return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#removeRDFPropertyValues(gate.creole.ontology.RDFProperty)
   */
  public void removeRDFPropertyValues(RDFProperty aProperty) {
      ontologyService.removeRDFPropertyValues(this.nodeId.toString(),
              aProperty.getOURI().toString());
      ontology.fireResourcePropertyValueChanged(this, aProperty, null, OConstants.RDF_PROPERTY_VALUE_REMOVED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#addDatatypePropertyValue(gate.creole.ontology.DatatypeProperty,
   *      gate.creole.ontology.Literal)
   */
  public void addDatatypePropertyValue(DatatypeProperty aProperty, Literal value)
          throws InvalidValueException {
      // we need to check if the current instance is a valid domain for
      // the property
      if(!aProperty.isValidDomain(this)) {
        Utils.error(this.getOURI().toTurtle()
                + " is not a valid domain for the property "
                + aProperty.getOURI().toTurtle());
        return;
      }

      DataType type = aProperty.getDataType();
      if(value.getDataType() == null) {
        type = aProperty.getDataType();
      }
      else {
        if(!type.getXmlSchemaURIString().equals(
                value.getDataType().getXmlSchemaURIString()))
          throw new GateOntologyException("Datatype :"
                  + value.getDataType().getXmlSchemaURIString()
                  + " doesn't match with the property's datatype :"
                  + type.getXmlSchemaURIString());
      }

      ontologyService.addDatatypePropertyValue(this.getOURI(),
              aProperty.getOURI(), value);
      ontology.fireResourcePropertyValueChanged(this, aProperty, value, OConstants.DATATYPE_PROPERTY_VALUE_ADDED_EVENT);  
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#removeDatatypePropertyValue(gate.creole.ontology.DatatypeProperty,
   *      gate.creole.ontology.Literal)
   */
  public void removeDatatypePropertyValue(DatatypeProperty aProperty,
          Literal value) {
      ontologyService.removeDatatypePropertyValue(getOURI(),
              aProperty.getOURI(), value);
      ontology.fireResourcePropertyValueChanged(this, aProperty, value, OConstants.DATATYPE_PROPERTY_VALUE_REMOVED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#getDatatypePropertyValues(gate.creole.ontology.DatatypeProperty)
   */
  public List<Literal> getDatatypePropertyValues(DatatypeProperty aProperty) {
    return ontologyService.getDatatypePropertyValues(getOURI(), aProperty.getOURI());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#removeDatatypePropertyValues(gate.creole.ontology.DatatypeProperty)
   */
  public void removeDatatypePropertyValues(DatatypeProperty aProperty) {
    ontologyService.removeDatatypePropertyValues(
      this.nodeId, aProperty.getOURI());
    ontology.fireResourcePropertyValueChanged(
      this, aProperty, null, OConstants.DATATYPE_PROPERTY_VALUE_REMOVED_EVENT);
  }

  /**
   * This method returns the datatype properties set on this resource.
   * 
   * @return
   */
  public Set<DatatypeProperty> getSetDatatypeProperties() {
      Property[] properties = ontologyService.getDatatypeProperties(
              this.nodeId.toString());
      Set<DatatypeProperty> dataProps = new HashSet<DatatypeProperty>();
      for(int i = 0; i < properties.length; i++) {
        if(properties[i].getType() != OConstants.DATATYPE_PROPERTY) {
          throw new GateOntologyException("The property :"
                  + properties[i].getUri()
                  + " returned from the repository is not an DatatypeProperty");
        }
        String propUri = properties[i].getUri();
        OResource resource = null;
        resource = new DatatypePropertyImpl(ontology.createOURI(propUri),
                  this.ontology, ontologyService);
        dataProps.add((DatatypeProperty)resource);
      }
      return dataProps;
  }

  /**
   * Checks if the resource has the provided datatype property set on it
   * with the specified value.
   * 
   * @param aProperty
   * @param aValue
   * @return
   */
  public boolean hasDatatypePropertyWithValue(DatatypeProperty aProperty,
          Literal aValue) {

    List<Literal> literals = getDatatypePropertyValues(aProperty);
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

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#addObjectPropertyValue(gate.creole.ontology.ObjectProperty,
   *      gate.creole.ontology.OInstance)
   */
  public void addObjectPropertyValue(ObjectProperty aProperty, OInstance value)
          throws InvalidValueException {
    // check if the ontology actually contains aProperty as an object
    // property
    if(!ontologyService.isObjectProperty(aProperty.getOURI())) {
      throw new GateOntologyException(
        "Attempt to set object property value but not known as object property: "+aProperty);
    }
      // we need to check if the current instance is a valid domain for
      // the property
      if(!aProperty.isValidDomain(this)) {
        Utils.error(this.getOURI().toTurtle()
                + " is not a valid domain for the property "
                + aProperty.getOURI().toTurtle());
        return;
      }

      // we need to check if the current instance is a valid domain for
      // the property
      if(!aProperty.isValidRange(value)) {
        Utils.error(value.getOURI().toTurtle()
                + " is not a valid range for the property "
                + aProperty.getOURI().toTurtle());
        return;
      }

      ontologyService.addObjectPropertyValue(this.nodeId,
              aProperty.getOURI(), value.getOURI());
      ontology.fireResourcePropertyValueChanged(this, aProperty, value, OConstants.OBJECT_PROPERTY_VALUE_ADDED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#removeObjectPropertyValue(gate.creole.ontology.ObjectProperty,
   *      gate.creole.ontology.OInstance)
   */
  public void removeObjectPropertyValue(ObjectProperty aProperty,
          OInstance value) {
      ontologyService.removeObjectPropertyValue(getOURI(),
              aProperty.getOURI(), value.getOURI());
      ontology.fireResourcePropertyValueChanged(this, aProperty, value, OConstants.OBJECT_PROPERTY_VALUE_REMOVED_EVENT);  
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#getObjectPropertyValues(gate.creole.ontology.ObjectProperty)
   */
  public List<OInstance> getObjectPropertyValues(ObjectProperty aProperty) {
      return ontologyService.getObjectPropertyValues(getOURI(),
         aProperty.getOURI());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OInstance#removeObjectPropertyValues(gate.creole.ontology.ObjectProperty)
   */
  public void removeObjectPropertyValues(ObjectProperty aProperty) {
      ontologyService.removeObjectPropertyValues(getOURI(),
              aProperty.getOURI());
      ontology.fireResourcePropertyValueChanged(this, aProperty, null, OConstants.OBJECT_PROPERTY_VALUE_REMOVED_EVENT);
  }

  /**
   * This method returns the object properties set on this resource.
   * 
   * @return
   */
  public Set<ObjectProperty> getSetObjectProperties() {
      Property[] properties = ontologyService.getObjectProperties(
              this.nodeId.toString());
      Set<ObjectProperty> objectProps = new HashSet<ObjectProperty>();
      for(int i = 0; i < properties.length; i++) {
        if(properties[i].getType() != OConstants.OBJECT_PROPERTY) {
          throw new GateOntologyException("The property :"
                  + properties[i].getUri()
                  + " returned from the repository is not an ObjectProperty");
        }
        String propUri = properties[i].getUri();
        OResource resource = new ObjectPropertyImpl(ontology.createOURI(propUri),
                  this.ontology, ontologyService);
        objectProps.add((ObjectProperty)resource);
      }
      return objectProps;
  }

  /**
   * Checks if the resource has the provided object property set on it
   * with the specified value.
   * 
   * @param aProperty
   * @param aValue
   * @return
   */
  public boolean hasObjectPropertyWithValue(ObjectProperty aProperty,
          OInstance aValue) {
    List<OInstance> instances = getObjectPropertyValues(aProperty);
    for(OInstance i : instances) {
      if(i.equals(aValue)) {
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
    toReturn.addAll(getSetDatatypeProperties());
    toReturn.addAll(getSetObjectProperties());
    toReturn.addAll(getSetRDFProperties());
    return toReturn;
  }

  public OURI getOURI() {
    // an instance may not be a blank node so we can always cast to OURI
    // but we better make sure anyways ...
    if(getONodeID().isAnonymousResource()) {
      throw new GateOntologyException(
          "Cannot return OInstance OURI, instance is a blank node: "+
          getONodeID().toTurtle());
    }
    return (OURI)getONodeID();
  }


}
