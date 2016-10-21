/*
 *  CloneQlConstants.java
 *
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 */
package gate.clone.ql;

/**
 * Constants for use in Content Augumentation Tool
 * 
 * @author Danica Damljanovic
 * 
 */
public class CATConstants {

  public static String TYPE_INSTANCE = "instance";

  public static String FEATURE_INSTANCE_URI = "instanceURI";

  /* used to set feature names for Lookups inside OntoRootGaz */
  public static String FEATURE_URI = "URI";

  public static String FEATURE_PROPERTY_URI = "propertyURI";

  public static String FEATURE_PROPERTY_VALUE = "propertyValue";

  public static String FEATURE_HEURISTIC_LEVEL = "heuristic_level";

  public static String FEATURE_HEURISTIC_VALUE = "heuristic_value";

  public static String TYPE_CLASS = "class";

  public static String CLASS_URI = "classURI";

  public static String CLASS_URI_LIST = "classURIList";

  public static String TYPE_PROPERTY = "property";

  public static String TYPE_DATATYPE_PROPERTY_VALUE = "datatypePropertyValue";

  public static String ANNOTATION_TYPE_ONTORES = "OntoRes";

  public static String ANNOTATION_TYPE_ONTORESCHUNK = "OntoResChunk";

  /** used to set feature names for Lookups inside OntoRootGaz */
  public static String ONTORES_TYPE = "type";

  /**
   * Name of the common logger
   */
  public static String LOGGER_NAME = "CAT-logger";

  public static String LOGGER_OUPUT_LEVEL = "2000";

  /** separator used during formatting of results */
  public static String NEW_LINE = "\n";

  public static String REGEX_CAMEL_CASE = "([a-z])([A-Z])";

}
