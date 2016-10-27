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
 *  $Id: OntologyService.java 18701 2015-05-21 15:28:46Z markagreenwood $
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.Literal;

import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.OURI;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.OBNodeID;
import gate.creole.ontology.OResource;
import gate.creole.ontology.OntologyTripleStore;
import gate.creole.ontology.RDFProperty;
import gate.util.ClosableIterator;
import java.util.List;
import java.util.Set;

public interface OntologyService {


   public void setOntologyURI(OURI theURI);

   

  /**
   * Returns whether the theSuperClass is indeed a super class of the
   * theSubClassURI.
   * 
   * @param repositoryID
   * @param theSuperClassURI
   * @param theSubClassURI
   * @param direct
   * @return
   * @throws GateOntologyException
   */
  public boolean isSuperClassOf(
                   String theSuperClassURI,
          String theSubClassURI,
          Closure direct)
          throws GateOntologyException;

  /**
   * Returns whether the theSubClass is indeed a sub class of the
   * theSuperClassURI.
   * 
   * @param theSuperClassURI
   * @param theSubClassURI
   * @param direct
   * @return
   * @throws GateOntologyException
   */
  public boolean isSubClassOf(
          String theSuperClassURI,
          String theSubClassURI,
          Closure direct)
          throws GateOntologyException;

  /**
   * Given a property URI, this method returns an object of Property
   * 
   * @param thePropertyURI
   * @return
   * @throws GateOntologyException
   */
  public Property getPropertyFromOntology(
          String thePropertyURI)
          throws GateOntologyException;

  /**
   * Checks whether the two classes defined as same in the ontology.
   * 
   * @param theClassURI1
   * @param theClassURI2
   * @return
   * @throws GateOntologyException
   */
  public boolean isEquivalentClassAs(
          ONodeID theClassURI1,
          ONodeID theClassURI2)
          throws GateOntologyException;

  // *******************************************************************
  // property methods
  // *******************************************************************
  // **************
  // Annotation Property
  // ************
  /**
   * Creates a new AnnotationProperty.
   * 
   * @param aPropertyURI URI of the property to be added into the
   *          ontology. Done
   * @throws GateOntologyException 
   */
  public void addAnnotationProperty(OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * Gets the annotation properties set on the specified resource
   * 
   * @param theResourceURI
   * @return
   * @throws GateOntologyException
   */
  public Property[] getAnnotationProperties(
          String theResourceURI)
          throws GateOntologyException;

  /**
   * Gets the RDF properties set on the specified resource
   * 
   * @param theResourceURI
   * @return
   * @throws GateOntologyException
   */
  public Property[] getRDFProperties(
          String theResourceURI)
          throws GateOntologyException;

  /**
   * Gets the datatype properties set on the specified resource
   * 
   * @param theResourceURI
   * @return
   * @throws GateOntologyException
   */
  public Property[] getDatatypeProperties(
          String theResourceURI)
          throws GateOntologyException;

  /**
   * Gets the object properties set on the specified resource
   * 
   * @param theResourceURI
   * @return
   * @throws GateOntologyException
   */
  public Property[] getObjectProperties(
          String theResourceURI)
          throws GateOntologyException;

  /**
   * Gets the transitive properties set on the specified resource
   * 
   * @param theResourceURI
   * @return
   * @throws GateOntologyException
   */
  public Property[] getTransitiveProperties(
          String theResourceURI)
          throws GateOntologyException;

  /**
   * Gets the symmetric properties set on the specified resource
   * 
   * @param theResourceURI
   * @return
   * @throws GateOntologyException
   */
  public Property[] getSymmetricProperties(
          String theResourceURI)
          throws GateOntologyException;

  /**
   * returns if the given property is an Annotation property
   * 
   * @param aPropertyURI
   * @return Done
   * @throws GateOntologyException s
   */
  public boolean isAnnotationProperty(
          OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * Adds a new annotation property value and specifies the language.
   * 
   * @param theResourceURI 
   * @param theAnnotationPropertyURI 
   * @param value the value containing some value
   * @param language
   * @param language
   * @throws GateOntologyException
   */
  public void addAnnotationPropertyValue(
          ONodeID theResourceURI,
          OURI theAnnotationPropertyURI,
          String value,
          String language)
          throws GateOntologyException;

  /**
   * Gets the list of annotation property values
   * 
   * @param theResourceURI
   * @param theAnnotationPropertyURI
   * @return
   * @throws GateOntologyException
   */
  public List<Literal> getAnnotationPropertyValues(
          ONodeID theResourceURI,
          OURI theAnnotationPropertyURI)
          throws GateOntologyException;

  /**
   * For the current resource, the method removes the given literal for
   * the given property.
   * 
   * @param theAnnotationProperty
   * @param literal
   */
  public void removeAnnotationPropertyValue(
          ONodeID theResourceURI,
          OURI theAnnotationPropertyURI,
          Literal value)
          throws GateOntologyException;

  /**
   * Removes all values for a named property.
   * 
   * @param theProperty the property
   */
  public void removeAnnotationPropertyValues(
          ONodeID theResourceURI,
          OURI theAnnotationPropertyURI)
          throws GateOntologyException;

  // **************
  // RDFProperties
  // *************
  /**
   * The method adds a generic property specifiying domain and range for
   * the same. All classes specified in domain and range must exist.
   * 
   * @param aPropertyURI
   * @param domainClassesURIs
   * @param rangeClassesTypes Done
   */
  public void addRDFProperty(
          OURI aPropertyURI,
          Set<OResource> domain,
          Set<OResource> range)
          throws GateOntologyException;

  /**
   * returns if the given property is an RDF property
   * 
   * @param aPropertyURI
   * @return Done
   */
  public boolean isRDFProperty(
          OURI aPropertyURI)
          throws GateOntologyException;

  // **************
  // Datatype Properties
  // *************
  /**
   * The method adds a data type property specifiying domain and range
   * for the same. All classes specified in domain and range must exist.
   * 
   * @param aPropertyURI
   * @param domainClassesURIs
   * @param dataTypeURI Done
   */
  public void addDataTypeProperty(
          OURI aPropertyURI,
          Set<OClass> domainClassesURIs,
          String dataTypeURI)
          throws GateOntologyException;

  /**
   * Returns the datatype uri specified for the given datatype property.
   * 
   * @param repositoryID
   * @param theDatatypePropertyURI
   * @return
   * @throws GateOntologyException
   */
  public String getDatatype(
          OURI theDatatypePropertyURI)
          throws GateOntologyException;

  // **************
  // Symmetric Properties
  // *************
  /**
   * The method adds a symmetric property specifiying domain and range
   * for the same. All classes specified in domain and range must exist.
   * 
   * @param aPropertyURI
   * @param domainAndRangeClassesURIs Done
   */
  public void addSymmetricProperty(
          OURI aPropertyURI,
          Set<OClass> domainAndRangeClassesURIs)
          throws GateOntologyException;

  /**
   * Checkes whether the two properties are Equivalent.
   * 
   * @param repositoryID
   * @param aPropertyURI
   * @return
   * @throws GateOntologyException
   */
      public boolean isEquivalentPropertyAs(
          OURI aPropertyURI1,
          OURI aPropertyURI2)
          throws GateOntologyException;

  /**
   * for the given property, the method returns all its super properties
   * 
   * @param aPropertyURI
   * @param direct
   * @return
   */
        public Property[] getSuperProperties(
                   String aPropertyURI,
          Closure direct)
          throws GateOntologyException;

  /**
   * for the given property, the method returns all its sub properties
   * 
   * @param aPropertyURI
   * @param direct
   * @return
   */
        public Property[] getSubProperties(
                   String aPropertyURI,
          Closure direct)
          throws GateOntologyException;

  /**
   * Checkes whether the two properties have a super-sub relation.
   * 
   * @param repositoryID
   * @param aSuperPropertyURI
   * @param aSubPropertyURI
   * @param direct
   * @return
   * @throws GateOntologyException
   */
      public boolean isSuperPropertyOf(
                   String aSuperPropertyURI,
          String aSubPropertyURI,
          Closure direct)
          throws GateOntologyException;

  /**
   * Checkes whether the two properties have a super-sub relation.
   * 
   * @param repositoryID
   * @param aSuperPropertyURI
   * @param aSubPropertyURI
   * @param direct
   * @return
   * @throws GateOntologyException
   */
      public boolean isSubPropertyOf(
                   String aSuperPropertyURI,
          String aSubPropertyURI,
          Closure direct)
          throws GateOntologyException;


  /**
   * Returns whether the individual1 is different from the individual2.
   * 
   * @param theInstanceURI1
   * @param theInstanceURI2
   * @return
   * @throws GateOntologyException
   */
      public boolean isDifferentIndividualFrom(
          OURI theInstanceURI1,
          OURI theInstanceURI2)
          throws GateOntologyException;

  /**
   * Checkes whether the two individuals are same.
   * 
   * @param repositoryID
   * @param individualURI1
   * @param invidualURI2
   * @return
   * @throws GateOntologyException
   */
      public boolean isSameIndividualAs(
          OURI theInstanceURI1,
          OURI theInstanceURI2)
          throws GateOntologyException;

  // *************
  // Instances and properties
  // **************
  /**
   * adds the RDF Property value on the specified instance
   * 
   * @param repositoryID
   * @param anInstanceURI
   * @param anRDFPropertyURI
   * @param aResourceURI
   * @throws InvalidValueException
   */
      public void addRDFPropertyValue(
                   ONodeID anInstanceURI,
          OURI anRDFPropertyURI,
          ONodeID aResourceURI)
          throws GateOntologyException;

  /**
   * Removes the specified RDF Property Value
   * 
   * @param repositoryID
   * @param anInstanceURI
   * @param anRDFPropertyURI
   * @param aResourceURI
   */
      public void removeRDFPropertyValue(
                   ONodeID anInstanceURI,
          OURI anRDFPropertyURI,
          ONodeID aResourceURI)
          throws GateOntologyException;

  /**
   * gets the rdf property values for the specified instance.
   * 
   * @param repositoryID
   * @param anInstanceURI
   * @param anRDFPropertyURI
   * @return resource URIs
   * @deprecated
   */
      @Deprecated
        public ResourceInfo[] getRDFPropertyValues(
                   String anInstanceURI,
          String anRDFPropertyURI);

        public List<LiteralOrONodeID> getRDFPropertyLiteralOrONodeIDs(
                   ONodeID anInstanceURI,
          OURI anRDFPropertyURI);

  /**
   * Removes all the RDF Property values from the given instance.
   * 
   * @param repositoryID
   * @param anInstanceURI
   * @param anRDFPropertyURI
   */
      public void removeRDFPropertyValues(
                   String anInstanceURI,
          String anRDFPropertyURI)
          throws GateOntologyException;

  // ******************
  // DataType Properties
  // *****************
  /**
   * Adds the value for the given Property.
   * 
   * @param repositoryID
   * @param anInstanceURI
   * @param aDatatypePropertyURI
   * @param datatypeURI
   * @param value
   * @throws InvalidValueException
   */
      public void addDatatypePropertyValue(
          OURI anInstanceURI,
          OURI aDatatypePropertyURI,
          Literal value)
          throws GateOntologyException;

  /**
   * Removes the provided value for the given instance.
   * 
   * @param repositoryID
   * @param anInstanceURI
   * @param aDatatypePropertyURI
   * @param datatypeURI
   * @param value
   */
      public void removeDatatypePropertyValue(
          OURI anInstanceURI,
          OURI aDatatypePropertyURI,
          Literal value);

  /**
   * Gets a list of values for the given Property.
   * 
   * @param anInstanceURI
   * @param aDatatypePropertyURI
   * @return
   */
        public List<Literal> getDatatypePropertyValues(
          OURI anInstanceURI,
          OURI aDatatypePropertyURI);

  /**
   * Removes all property values set on the provided instance for the
   * current property.
   * 
   * @param repositoryID
   * @param anInstanceURI
   * @param aDatatypePropertyURI
   */
      public void removeDatatypePropertyValues(
          ONodeID anInstanceURI,
          OURI aDatatypePropertyURI)
          throws GateOntologyException;

  // ******************
  // Object, Symmetric and Transitive Properties
  // *****************
  /**
   * Adds the value for the given property (Object, Symmetric and
   * Transitive).
   * 
   * @param repositoryID
   * @param sourceInstanceURI
   * @param anObjectPropertyURI
   * @param theValueInstanceURI
   * @throws InvalidValueException
   */
      public void addObjectPropertyValue(
          ONodeID sourceInstanceURI,
          OURI anObjectPropertyURI,
          OURI theValueInstanceURI)
          throws GateOntologyException;

  /**
   * Remove the provided value for the given property (Object, Symmetric
   * and Transitive).
   * 
   * @param repositoryID
   * @param sourceInstanceURI
   * @param anObjectPropertyURI
   * @param theValueInstanceURI
   * @return
   */
      public void removeObjectPropertyValue(
          OURI sourceInstanceURI,
          OURI anObjectPropertyURI,
          OURI theValueInstanceURI)
          throws GateOntologyException;

  /**
   * Gets a list of values for the given Property (Object, Symmetric and
   * Transitive).
   * 
   * @param repositoryID
   * @param sourceInstanceURI
   * @param anObjectPropertyURI
   * @return
   */
        public List<OInstance> getObjectPropertyValues(
                   OURI sourceInstanceURI,
          OURI anObjectPropertyURI)
          throws GateOntologyException;

  /**
   * Removes all property values set for the current property (Object,
   * Symmetric and Transitive).
   * 
   * @param repositoryID
   * @param sourceInstanceURI
   * @param anObjectPropertyURI
   */
      public void removeObjectPropertyValues(
          OURI sourceInstanceURI,
          OURI anObjectPropertyURI)
          throws GateOntologyException;

  // ****************************************************************************
  // user management methods
  // ****************************************************************************
  /**
   * Call to this method is necessary in order to login in to the Sesame
   * server. Unless user is registered with Sesame server, he/she cannot
   * have write or modify access to any of the repositories (unless
   * given write access to world users) available on the server.
   * However, unregistered users are and will be allowed to have read
   * access on all repositories.
   * 
   * @param username
   * @param password
   * @return
   */
  /* NOTE: not needed anymore
      public boolean login(
          String username,
          String password)
          throws GateOntologyException;
   * */

  /**
   * End the session by logging out
   */
  /* NOTE: not needed anymore!
      public void logout(
          String repositoryID)
          throws GateOntologyException;
   * */



  // *******************************************************************
  // *************************** Ontology Methods **********************
  // *******************************************************************
  /**
   * The method removes all data from the available graph.
   */
      public void cleanOntology()
          throws GateOntologyException;



  /**
   * The method returns the version information of the repository.
   * 
   * @return
   */
      public String getVersion()
          throws GateOntologyException;

  // *******************************************************************
  // class methods
  // *******************************************************************
  /**
   * The method allows adding a class to repository.
   * 
   * @param classURI
   */
  public void addClass(OURI classURI) throws GateOntologyException;
  public void addRestriction(OBNodeID classURI) throws GateOntologyException;

  /**
   * Given a class to delete, it removes it from the repository.
   * 
   * @param repositoryID
   * @param classURI
   * @param deleteSubTree
   * @return a list of other resources, which got removed as a result of
   *         this deletion
   */
        public String[] removeClass(
                   String classURI,
          boolean deleteSubTree)
          throws GateOntologyException;

  
  /**
   * The method returns if the current repository has a class with URI
   * that matches with the class parameter.
   * 
   * @return
   */
      public boolean hasClass(
                   ONodeID classURI)
          throws GateOntologyException;

  public boolean containsURI(OURI theURI);
    
      
  /**
   * if top set to true, the method returns only the top classes (i.e.
   * classes with no super class). Otherwise it returns all classes
   * available in repository.
   * 
   * @param top
   * @return
   */

  public Set<OClass> getClasses(
          boolean top)
          throws GateOntologyException;

  public ClosableIterator<OClass> getClassesIterator(boolean top)
      throws GateOntologyException;

  /**
   * Returns if the given class is a top class. It also returns false if
   * the class is an instance of BNode
   * 
   * @param classURI
   * @return
   */
      public boolean isTopClass(
                   String classURI)
          throws GateOntologyException;

  // ****************************************************************************
  // relations among classes
  // ****************************************************************************
  /**
   * The method creates a new class with the URI as specified in
   * className and adds it as a subClassOf the parentClass. It also adds
   * the provided comment on the subClass.
   * 
   * @param superClassURI
   * @param subClassURI
   */
      public void addSubClass(
                   String superClassURI,
          String subClassURI)
          throws GateOntologyException;

  /**
   * The method creates a new class with the URI as specified in
   * className and adds it as a superClassOf the parentClass. It also
   * adds the provided comment on the subClass.
   * 
   * @param superClassURI
   * @param subClassURI
   */
      public void addSuperClass(
                   String superClassURI,
          String subClassURI)
          throws GateOntologyException;

  /**
   * Removes the subclass relationship
   * 
   * @param superClassURI
   * @param subClassURI
   */
      public void removeSubClass(
                   String superClassURI,
          String subClassURI)
          throws GateOntologyException;

  /**
   * Removes the superclass relationship
   * 
   * @param superClassURI
   * @param subClassURI
   */
      public void removeSuperClass(
                   String superClassURI,
          String subClassURI)
          throws GateOntologyException;

  /**
   * This method returns all sub classes of the given class
   * 
   * @param superClassURI
   * @param direct
   * @return
   */
      /*
        public ResourceInfo[] getSubClassesOld(
                   String superClassURI,
          Closure direct)
          throws GateOntologyException;
   */
   public Set<OClass> getSubClasses(ONodeID superClassURI, 
       gate.creole.ontology.OConstants.Closure direct);

   public ClosableIterator<OClass> getSubClassesIterator(
      ONodeID forClass, Closure closure);

  /**
   * This method returns all super classes of the given class
   * 
   * @param subClassURI
   * @param direct
   * @return
   */
        public ResourceInfo[] getSuperClasses(
          String subClassURI,
          Closure direct)
          throws GateOntologyException;

  /**
   * Sets the classes as same classes
   * 
   * @param class1URI
   * @param class2URI
   */
      public void setEquivalentClassAs(
          ONodeID class1URI,
          ONodeID class2URI)
          throws GateOntologyException;

  /**
   * returns an array of classes which are equivalent as the given class
   * 
   * @param aClassURI
   * @return
   */
        public ResourceInfo[] getEquivalentClasses(
          String aClassURI)
          throws GateOntologyException;

  /**
   * Removes the given property
   * @param repositoryID 
   * @param aPropertyURI
   * @param removeSubTree
   * @return a list of names of resources deleted as a result of deleting this property from the ontology.
   */
        public String[] removePropertyFromOntology(
          String aPropertyURI,
          boolean removeSubTree)
          throws GateOntologyException;

  /**
   * The method adds an object property specifiying domain and range for
   * the same. All classes specified in domain and range must exist.
   * 
   * @param aPropertyURI
   * @param domainClassesURIs
   * @param rangeClassesTypes
   */
      public void addObjectProperty(
          String aPropertyURI,
          String[] domainClassesURIs,
          String[] rangeClassesTypes)
          throws GateOntologyException;

  /**
   * The method adds a transitive property specifiying domain and range
   * for the same. All classes specified in domain and range must exist.
   * 
   * @param aPropertyURI
   * @param domainClassesURIs
   * @param rangeClassesTypes
   */
      public void addTransitiveProperty(
          String aPropertyURI,
          String[] domainClassesURIs,
          String[] rangeClassesTypes)
          throws GateOntologyException;

  /**
   * The method returns an array of properties. Property is a complex
   * structure, which contains type and URI information.
   * 
   * @return
   */
        public Set<RDFProperty> getRDFProperties()
          throws GateOntologyException;

  /**
   * The method returns an array of properties. Property is a complex
   * structure, which contains type and URI information.
   * 
   * @return
   */
        public Property[] getObjectProperties()
          throws GateOntologyException;

  /**
   * The method returns an array of properties. Property is a complex
   * structure, which contains type and URI information.
   * 
   * @return
   */
        public Property[] getSymmetricProperties()
          throws GateOntologyException;

  /**
   * The method returns an array of properties. Property is a complex
   * structure, which contains type and URI information.
   * 
   * @return
   */
        public Property[] getTransitiveProperties()
          throws GateOntologyException;

  /**
   * The method returns an array of properties. Property is a complex
   * structure, which contains type and URI information.
   * 
   * @return
   */
        public Property[] getDatatypeProperties()
          throws GateOntologyException;

  /**
   * The method returns an array of properties. Property is a complex
   * structure, which contains type and URI information.
   * 
   * @return
   */
        public Property[] getAnnotationProperties()
          throws GateOntologyException;

  /**
   * Given a property, this method returns its domain
   * 
   * @param aPropertyURI
   * @return
   */
        public Set<OResource> getDomain(
          OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * Given a property, this method returns its range
   * 
   * @param aPropertyURI
   * @return
   */
        public ResourceInfo[] getRange(
          String aPropertyURI)
          throws GateOntologyException;

  /**
   * Returns if the provided property is functional
   * 
   * @param aPropertyURI
   * @return
   */
      public boolean isFunctional(
          OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * sets the current property as functional
   * 
   * @param aPropertyURI
   * @param isFunctional
   */
      public void setFunctional(
          String aPropertyURI,
          boolean isFunctional)
          throws GateOntologyException;

  /**
   * returns if the given property is inverse functional property
   * 
   * @param aPropertyURI
   * @return
   */
      public boolean isInverseFunctional(
          OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * Sets the current property as inverse functional property
   * 
   * @param aPropertyURI
   * @param isInverseFunctional
   */
      public void setInverseFunctional(
          String aPropertyURI,
          boolean isInverseFunctional)
          throws GateOntologyException;

  /**
   * returns if the given property is a symmetric property
   * 
   * @param aPropertyURI
   * @return
   */
      public boolean isSymmetricProperty(
          OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * returns if the given property is a transitive property
   * 
   * @param aPropertyURI
   * @return
   */
      public boolean isTransitiveProperty(
          OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * returns if the given property is a datatype property
   * 
   * @param aPropertyURI
   * @return
   */
      public boolean isDatatypeProperty(
          OURI aPropertyURI)
          throws GateOntologyException;

  /**
   * returns if the given property is an object property
   * 
   * @param aPropertyURI
   * @return
   */
      public boolean isObjectProperty(
          OURI aPropertyURI)
          throws GateOntologyException;

  // *************************************
  // Relations among properties
  // *************************************
  /**
   * Sets two properties as same
   * 
   * @param property1URI
   * @param property2URI
   */
      public void setEquivalentPropertyAs(
          String property1URI,
          String property2URI)
          throws GateOntologyException;

  /**
   * For the given property, this method returns all properties marked
   * as Equivalent as it
   * 
   * @param aPropertyURI
   * @return
   */
        public Property[] getEquivalentPropertyAs(
          String aPropertyURI)
          throws GateOntologyException;

  /**
   * For the given properties, this method registers the super, sub
   * relation
   * 
   * @param superPropertyURI
   * @param subPropertyURI
   */
      public void addSuperProperty(
          String superPropertyURI,
          String subPropertyURI)
          throws GateOntologyException;

  /**
   * For the given properties, this method removes the super, sub
   * relation
   * 
   * @param superPropertyURI
   * @param subPropertyURI
   */
      public void removeSuperProperty(
          String superPropertyURI,
          String subPropertyURI)
          throws GateOntologyException;

  /**
   * For the given properties, this method registers the super, sub
   * relation
   * 
   * @param superPropertyURI
   * @param subPropertyURI
   */
      public void addSubProperty(
          String superPropertyURI,
          String subPropertyURI)
          throws GateOntologyException;

  /**
   * For the given properties, this method removes the super, sub
   * relation
   * 
   * @param superPropertyURI
   * @param subPropertyURI
   */
      public void removeSubProperty(
          String superPropertyURI,
          String subPropertyURI)
          throws GateOntologyException;

  /**
   * for the given property, the method returns all its super properties
   * 
   * @param aPropertyURI
   * @param direct
   * @return
   */
      public Property[] getSuperProperties(
          String aPropertyURI,
          boolean direct)
          throws GateOntologyException;

  /**
   * for the given property, the method returns all its sub properties
   * 
   * @param aPropertyURI
   * @param direct
   * @return
   */
      public Property[] getSubProperties(
          String aPropertyURI,
          boolean direct)
          throws GateOntologyException;

  /**
   * for the given property, the method returns all its inverse
   * properties
   * 
   * @param aPropertyURI
   * @return
   */
        public Property[] getInverseProperties(
          String aPropertyURI)
          throws GateOntologyException;

  /**
   * property1 is set as inverse of property 2
   * 
   * @param property1URI
   * @param property2URI
   */
      public void setInverseOf(
          String propertyURI1,
          String propertyURI2)
          throws GateOntologyException;

  // *******************************************************************
  // *************************** Instance Methods **********************
  // *******************************************************************
  /**
   * The method adds a new instance (literal) into the repository. It
   * then creates a statement indicating membership relation with the
   * provided class.
   * 
   * @param superClassURI
   * @param individualURI
   */
      public void addIndividual(
          String superClassURI,
          String individualURI)
          throws GateOntologyException;

  /**
   * The method removes the provided instance from the repository.
   * 
   * @param individual
   * @return
   */
        public String[] removeIndividual(
          String individualURI)
          throws GateOntologyException;

   public ClosableIterator<OInstance> getInstancesIterator(
       ONodeID aClass, OConstants.Closure closure);

   public boolean hasInstance(OURI theURI, ONodeID theClass, Closure closure);

  /**
   * For the given individual, the method returns a set of classes for
   * which the individual is registered as instance of
   * 
   * @param individualURI
   */
        public ResourceInfo[] getClassesOfIndividual(
          String individualURI,
          Closure direct)
          throws GateOntologyException;

  // *******************************************************************
  // relations among individuals
  // *******************************************************************
  /**
   * individual1 is sets as different individual from individual2
   * 
   * @param individual1URI
   * @param individual2URI
   */
      public void setDifferentIndividualFrom(
          ONodeID individual1URI,
          ONodeID individual2URI)
          throws GateOntologyException;

  /**
   * for the given individual, the method returns all individuals
   * registered as different from the given individual
   * 
   * @param individualURI
   * @return
   */
        public String[] getDifferentIndividualFrom(
          String individualURI)
          throws GateOntologyException;

  /**
   * individual1 is set as same as the individual2
   * 
   * @param individual1URI
   * @param individual2URI
   */
      public void setSameIndividualAs(
          String individual1URI,
          String individual2URI)
          throws GateOntologyException;

  /**
   * for the given individual, the method returns all individuals which
   * are registered as same as the provided individual
   * 
   * @param inidividualURI
   * @return
   */
        public String[] getSameIndividualAs(
          String individualURI)
          throws GateOntologyException;

  // ***********************************************
  // ********* Restrictions ***********************
  // ***********************************************

  /**
   * This method given a restriction uri returns the value for the
   * onProperty element.
   * 
   * @param repositoryId
   * @param restrictionURI
   * @return
   * @throws GateOntologyException
   */
      public Property getOnPropertyValue(
          String restrictionURI)
          throws GateOntologyException;

  /**
   * This method sets the value for onProperty element on the given
   * restriction.
   * 
   * @param repositoryId
   * @param restrictionURI
   * @param propertyURI
   * @throws GateOntologyException
   */
      public void setOnPropertyValue(
          ONodeID restrictionURI,
          OURI propertyURI)
          throws GateOntologyException;

  /**
   * Gets the property value specified on the given restriction uri.
   * 
   * @param repositoryID
   * @param restrictionURI
   * @param restrictionType
   * @return
   * @throws GateOntologyException
   */
      public PropertyValue getPropertyValue(
          String restrictionURI,
          byte restrictionType)
          throws GateOntologyException;

  /**
   * Sets the datatype uri for the given restriction uri.
   * 
   * @param repositoryID
   * @param restrictionURI
   * @param restrictionType
   * @param value
   * @param datatypeURI
   * @throws GateOntologyException
   */
      public void setPropertyValue(
          String restrictionURI,
          byte restrictionType,
          String value,
          String datatypeURI)
          throws GateOntologyException;

  /**
   * Gets the cardinality value specified on the given restriction uri.
   * 
   * @param repositoryID
   * @param restrictionURI
   * @param restrictionType - either of the following constants from the
   *          OConstants - ALL_VALUES_FROM_RESTRICTION,
   *          SOME_VALUES_FROM_RESTRICTION, and HAS_VALUE_RESTRICTION
   * @return
   * @throws GateOntologyException
   */
      public ResourceInfo getRestrictionValue(
          String restrictionURI,
          byte restrictionType)
          throws GateOntologyException;

  /**
   * Sets the cardinality value for the given restriction uri.
   * 
   * @param repositoryID
   * @param restrictionURI
   * @param restrictionType - either of the following constants from the
   *          OConstants - ALL_VALUES_FROM_RESTRICTION,
   *          SOME_VALUES_FROM_RESTRICTION, and HAS_VALUE_RESTRICTION
   * @param value
   * @return
   * @throws GateOntologyException
   */
      public void setRestrictionValue(
          ONodeID restrictionURI,
          OURI restrictionTypeURI,
          ONodeID value)
          throws GateOntologyException;
      public void setRestrictionValue(
          ONodeID restrictionURI,
          OURI restrictionTypeURI,
          Literal value)
          throws GateOntologyException;

  /**
   * This method tells what type of restriction the given uri refers to.
   * If the given URI is not a restriction, the method returns -1.
   * Otherwise one of the following values from the OConstants class.
   * OWL_CLASS, CARDINALITY_RESTRICTION, MIN_CARDINALITY_RESTRICTION,
   * MAX_CARDINALITY_RESTRICTION, HAS_VALUE_RESTRICTION,
   * ALL_VALUES_FROM_RESTRICTION.
   * 
   * @param repositoryID
   * @param restrictionURI
   * @return
   * @throws GateOntologyException
   */
      public byte getClassType(
          String restrictionURI)
          throws GateOntologyException;

  
        public Property[] getPropertiesWithResourceAsDomain(
          String theResourceURI)
          throws GateOntologyException;
  
  
        public Property[] getPropertiesWithResourceAsRange(
          String theResourceURI)
          throws GateOntologyException;
  
  // ****************************************************
  // ******************** Generic statements ************
  // ****************************************************


    public Set<String> getImportURIStrings();

    public Set<OURI> getOntologyURIs();


  public void shutdown();

  public Set<OClass> getClassesByName(String name);

  public Set<OInstance> getInstancesByName(String name);

  public Set<RDFProperty> getPropertiesByName(String name);

  public OntologyTripleStore getOntologyTripleStore();
  
}
