/*
 *  Ontology2Map.java
 *
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 */
package gate.clone.ql.utils;

import gate.clone.ql.query.serql.SerqlUtils;
import gate.creole.ontology.Ontology;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Danica Damljanovic
 * 
 * This class serves to cache all data we retrieve from the ontology and is
 * singlenton.
 */
public class Ontology2Map {

  // read ontology and store data

  /* String is instanceURI, and Set is the list i.e. set of classURIs */
  protected Map<String, Set<String>> instanceTypes =
    new HashMap<String, Set<String>>();

  protected String listOfClasses;

  private String listOfInstances;

  private String listOfProperties;

  private String classURIs;

  private String propertyURIs;

  public Ontology2Map(Ontology o) {

    // SerqlUtils.init(o);
    // for OntoRoot Gazetteer
    instanceTypes = StringUtil.fromStringToMap(SerqlUtils.getInstanceTypes(o));
    listOfClasses = SerqlUtils.getClasses(o);
    listOfProperties = SerqlUtils.getPropertiesOfProperties(o);
    listOfInstances = SerqlUtils.getInstances(o);
    classURIs = SerqlUtils.getClassURIs(o);
    propertyURIs = SerqlUtils.getPropertyURIs(o);
  }

  public Map<String, Set<String>> getInstanceTypes() {
    return instanceTypes;
  }

  public void setInstanceTypes(Map<String, Set<String>> instanceTypes) {
    this.instanceTypes = instanceTypes;
  }

  public String getListOfClasses() {
    return listOfClasses;
  }

  public String getListOfProperties() {
    return listOfProperties;
  }

  public String getClassURIs() {
    return classURIs;
  }

  public String getPropertyURIs() {
    return propertyURIs;
  }

  public String getListOfInstances() {
    return listOfInstances;
  }

}
