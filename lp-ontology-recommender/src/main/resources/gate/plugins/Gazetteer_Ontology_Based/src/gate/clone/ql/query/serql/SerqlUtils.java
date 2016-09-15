/*
 *  SerqlUtils.java
 *
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 */
package gate.clone.ql.query.serql;

import gate.clone.ql.CATConstants;
import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.OConstants.OWL;
import gate.creole.ontology.OConstants.RDF;
import gate.creole.ontology.OConstants.RDFS;
import gate.creole.ontology.Ontology;

import java.util.HashSet;
import java.util.Set;
//import org.openrdf.model.vocabulary.OWL;
//import org.openrdf.model.vocabulary.RDF;
//import org.openrdf.model.vocabulary.RDFS;


/**
 * This class executes queries against the ontology with which it is initialized
 * first using SerqlUtils.init(Ontology o) method.
 * 
 * @author Danica Damljanovic
 * 
 */
@SuppressWarnings("deprecation")
public class SerqlUtils {

  // public static Ontology ontology;

  // public static SesameRepository myRepository;

  // public static QueryResultsTable resultsTable = null;

  // public static void init(Ontology o) {
  // ontology = o;
  // myRepository = ontology.getSesameRepository();
  // }
  /**
   * Method for retrieving labels of all resources
   * 
   * @return a table with 2 columns: first one is a URI of a resource and the
   *         second one is the value for its label.
   */
  public static String getLabels(Ontology ontology)
    throws GateOntologyException {
    String query =
      "Select distinct x, z " + "from " + " {x} y {z} where y=<" + RDFS.LABEL
        + "> ";
    return ontology.executeQuery(query);
  }

  /**
   * Method for retrieving instances and their properties
   * 
   * @return a table with 3 columns: first column is instance URI, second column
   *         is property URI that is assigned to that instance, third column
   *         with a value of that property for that instance.
   */
  public static String getInstances(Ontology ontology)
    throws GateOntologyException {
    String query =
      "Select distinct x, y, z " + "from {x} rdf:type {} "
        + "rdf:type {<http://www.w3.org/2002/07/owl#Class>},"
        + " {x} y {z}, {y} rdf:type {<" + RDF.PROPERTY + ">} "
        + "WHERE EXISTS (SELECT * FROM " + " {x} y {z}) and isLiteral(z)";
    return ontology.executeQuery(query);
  }

  /**
   * TMethod for retrieving properties and their properties
   * 
   * @return table where: first column is property uri, second column is
   *         property uri that is assigned to the property in the first column,
   *         third column with a value of the property for the property uri.
   */
  public static String getPropertiesOfProperties(Ontology ontology)
    throws GateOntologyException {
    String query =
      "Select distinct y, m, n " + "from {y} rdf:type {<" + RDF.PROPERTY
        + ">}, " + "{y} m {n}, {m} rdf:type {<" + RDF.PROPERTY + ">}"
        + "WHERE exists " + "(select * from {y} m {n})  and isLiteral(n)";
    return ontology.executeQuery(query);
  }

  /**
   * Method for retrieving classes and their properties
   * 
   * @return table where: first column is class uri, second column is property
   *         uri that is assigned to the first class uri, third column with a
   *         value of the property for the class uri.
   * @throws GateOntologyException
   */
  public static String getClasses(Ontology ontology)
    throws GateOntologyException {
    String query =
      "SELECT DISTINCT x,y,z "
        + "from {x} rdf:type {<http://www.w3.org/2002/07/owl#Class>}, "
        + " {x} y {z}, {y} rdf:type {<" + RDF.PROPERTY + ">} "
        + "WHERE EXISTS (SELECT * FROM " + " {x} y {z}) and isLiteral(z)";
    return ontology.executeQuery(query);
  }

  /**
   * This method is retrieving instance types: classes which are being
   * instantiated by the instances.
   * 
   * @return the table with two columns: first is instace uri, second is class
   *         uri representing the type of the instance.
   * @throws GateOntologyException
   */
  public static String getInstanceTypes(Ontology ontology)
    throws GateOntologyException {
    String query =
      "SELECT DISTINCT x,y " + "from {x} serql:directType {y} "
        + "rdf:type {<http://www.w3.org/2002/07/owl#Class>}";
    return ontology.executeQuery(query);
  }

  /**
   * This method retrieves all instance URIs
   * 
   * @return the table with one column which is instance uris from the ontology
   *         this class is initialized to work with.
   */
  public static String getInstanceURIs(Ontology ontology)
    throws GateOntologyException {
    String query =
      "Select distinct x" + " from {x} rdf:type {} "
        + "rdf:type {<http://www.w3.org/2002/07/owl#Class>}";
    return ontology.executeQuery(query);
  }

  /**
   * This method retrieves property URIs
   * 
   * @return the table with one column which is property uris from the ontology
   *         this class is initialized to work with.
   */
  public static String getPropertyURIs(Ontology ontology)
    throws GateOntologyException {
    String query =
      "Select distinct y " + "from {y} rdf:type {<" + OWL.DATATYPEPROPERTY
        + ">} union ";

    String query2 =
      "Select distinct y " + "from {y} rdf:type {<" + OWL.OBJECTPROPERTY + ">}";
    return ontology.executeQuery(query + query2);
  }

  /**
   * This method retrieves URIs of all Object Properties
   * 
   * @return the table with one column which is object property uris from the
   *         ontology this class is initialized to work with.
   */
  public static String getObjectPropertyURIs(Ontology ontology)
    throws GateOntologyException {

    String query =
      "Select distinct y " + "from {y} rdf:type {<" + OWL.OBJECTPROPERTY + ">}";
    return ontology.executeQuery(query);
  }

  /**
   * This method retrieves URIs of classes
   * 
   * @return the table with one column which is class uris from the ontology
   *         this class is initialized to work with.
   * @throws GateOntologyException
   */
  public static String getClassURIs(Ontology ontology)
    throws GateOntologyException {
    String query =
      "SELECT DISTINCT x"
        + " from {x} rdf:type {<http://www.w3.org/2002/07/owl#Class>}";
    return ontology.executeQuery(query);
  }

  /**
   * This method is excluded from using at the moment as it is replaced by more
   * generic ones getPropertyRange and getPropertyDomain
   * 
   * @param firstUri
   * @param secondUri
   * @return
   * @throws GateOntologyException
   */
  public static String getRelationsBtwTwoResourcesIsTakingTooMuchTime(
    String firstUri, String secondUri, Ontology ontology)
    throws GateOntologyException {

    String rangeQueryRep = "{<" + secondUri + ">}";
    String domainQueryRep = "{<" + firstUri + ">}";

    String rangeClasses =
      "SELECT DISTINCT Parent FROM " + rangeQueryRep
        + "rdfs:subClassOf {Parent}";

    String domainClasses =
      "SELECT DISTINCT Parent FROM " + domainQueryRep
        + "rdfs:subClassOf {Parent}";

    String queryDomain =
      "select distinct p, x " + "from {x} rdfs:subClassOf {y}, "
        + "     {p} rdfs:domain {y}, " + "     {p} rdfs:domain {x}, "
        + "     {p} rdf:type {<" + OWL.OBJECTPROPERTY + ">} " + "where y in ("
        + domainClasses + ") and x in (" + domainClasses + ")" + " MINUS "
        + "select distinct p,y " + "from {x} rdfs:subClassOf {y}, "
        + "     {p} rdfs:domain {y}, " + "     {p} rdfs:domain {x}, "
        + "     {p} rdf:type {<" + OWL.OBJECTPROPERTY + ">} " + "where y in ("
        + domainClasses + ") and x in (" + domainClasses + ")  and x!=y";

    @SuppressWarnings("unused")
    String domainAddition =
      " select distinct p, x" + " from {p} rdfs:domain {x}" + " where x=<"
        + firstUri + "> ";

    @SuppressWarnings("unused")
    String rangeAddition =
      " select distinct p, x" + " from {p} rdfs:range {x}" + " where x=<"
        + secondUri + "> ";

    String queryRange =
      "select distinct p, x " + "from {x} rdfs:subClassOf {y}, "
        + "     {p} rdfs:range {y}, " + "     {p} rdfs:range {x}, "
        + "     {p} rdf:type {<" + OWL.OBJECTPROPERTY + ">} " + "where y in ("
        + rangeClasses + ") and x in (" + rangeClasses + ")" + " MINUS "
        + "select distinct p, y " + "from {x} rdfs:subClassOf {y}, "
        + "     {p} rdfs:range {y}, " + "     {p} rdfs:range {x}, "
        + "     {p} rdf:type {<" + OWL.OBJECTPROPERTY + ">} " + "where y in ("
        + rangeClasses + ") and x in (" + rangeClasses + ") and x!=y";// +

    Set<String> intersectionList = new HashSet<String>();

    Set<String> domainList = new HashSet<String>();
    // resultsTable = myRepository.performTableQuery(QueryLanguage.SERQL,
    // queryDomain);//
    String domainProps = ontology.executeQuery(queryDomain);
    String[] rows = domainProps.split("\n");
    for(String row : rows) {
      String[] cols = row.split("\\|");
      String prop = cols[0].trim();
      if(prop.indexOf("#") > -1) domainList.add(prop);
    }
    // resultsTable = myRepository.performTableQuery(QueryLanguage.SERQL,
    // queryRange);// + " union "+rangeAddition);
    String rangeProps = ontology.executeQuery(queryRange);
    rows = rangeProps.split("\n");
    for(String row : rows) {
      String[] cols = row.split("\\|");
      String prop = cols[0].trim();
      if(domainList.contains(prop)) intersectionList.add(prop);
    }

    /* transform list into the table with one column */
    StringBuffer toReturn = new StringBuffer("");
    for(String prop : intersectionList) {
      toReturn.append(prop).append(CATConstants.NEW_LINE);
    }
    return toReturn.toString();
  }

  /**
   * This method retrieves domain classes for object properties.
   * 
   * @return a table where first column is property uri and the second is a
   *         class uri which is defined to be the domain of the given property.
   *         Note here that duplicate domain classes (created because of way
   *         owlim works) are excluded and only specific ones are returned.
   */
  public static String getPropertyDomain(Ontology ontology)
    throws GateOntologyException {
    String queryAll =
      "SELECT DISTINCT p,y " + " from {p} rdf:type  {<" + OWL.OBJECTPROPERTY
        + ">}," + " {p} rdfs:domain {y} ";

    String queryDomain =
      "select distinct p, y " + "from {x} rdfs:subClassOf {y}, "
        + "     {p} rdfs:domain {y}, " + "     {p} rdfs:domain {x}, "
        + "     {p} rdf:type {<" + OWL.OBJECTPROPERTY + ">} " + "where y != x ";
    return ontology.executeQuery(queryAll + " MINUS " + queryDomain);
  }

  /**
   * This method retrieves range classes for object properties.
   * 
   * @returns a table where first column is property uri and the second is a
   *          class uri which is defined to be the range of the given property.
   *          Note here that duplicate range classes (created because of way
   *          owlim works) are excluded and only specific ones are returned.
   */
  public static String getPropertyRange(Ontology ontology)
    throws GateOntologyException {
    String queryAll =
      "SELECT DISTINCT p,y " + " from {p} rdf:type  {<" + OWL.OBJECTPROPERTY
        + ">}," + " {p} rdfs:range {y} ";

    String queryRange =
      "select distinct p, y " + "from {x} rdfs:subClassOf {y}, "
        + "     {p} rdfs:range {y}, " + "     {p} rdfs:range {x}, "
        + "     {p} rdf:type {<" + OWL.OBJECTPROPERTY + ">} " + "where y != x ";
    return ontology.executeQuery(queryAll + " MINUS " + queryRange);
  }

  /**
   * This method retrieves super classes.
   * 
   * @return a table where first column is a clas uri and the second is the set
   *         of class uris being super class of the first.
   */

  public static String getSuperClasses(Ontology ontology)
    throws GateOntologyException {
    String query = "SELECT DISTINCT x,y " + " from {x} rdfs:subClassOf {y}";
    return ontology.executeQuery(query);
  }

  /**
   * This method retrieves super classes.
   * 
   * 
   * 
   * @return returns a table where first column is a clas uri and the second is
   *         the set of class uris being super class of the first.
   */
  public static String getSubClasses(Ontology ontology)
    throws GateOntologyException {
    String query = "SELECT DISTINCT y,x " + " from {x} rdfs:subClassOf {y}";
    return ontology.executeQuery(query);
  }

  /**
   * This method retrieves properties and their superproperties
   * 
   * @return a table with two columns: propertyURI, propertyURI of the super
   *         property
   */
  public static String getSuperProperties(Ontology ontology)
    throws GateOntologyException {
    String query =
      "Select distinct SUB, SUPER FROM " + " {SUB} rdfs:subPropertyOf {SUPER}"
        + " WHERE SUPER!=SUB " + " MINUS "
        + " select distinct B FROM {B} owl:equivalentProperty {SUB}";
    return ontology.executeQuery(query);
  }


}
