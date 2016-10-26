/*
 *  StringUtil.java
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

import gate.clone.ql.CATConstants;
import gate.creole.ontology.InvalidURIException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * 
 * @author Danica Damljanovic
 * 
 */
public class StringUtil {

  static Logger logger = Logger.getLogger(CATConstants.LOGGER_NAME);

  /**
   * This method assume that given string is in the form of
   * QueryResultsTable.toString() with 2 columns and converts it to HashMap
   * where key is the first entry from the table and the value is the set of
   * second entries.
   * 
   * @param resultsTable
   * @return a Map with a key taken from the first column of the table and a
   *         value being a set of values taken from the second column.
   */
  @SuppressWarnings("deprecation")
  public static Map<String, Set<String>> fromStringToMap(String resultsTable) {
    Map<String, Set<String>> map = new HashMap<String, Set<String>>();

    String[] rows = resultsTable.split(CATConstants.NEW_LINE);
    for(String eachRow : rows) {
      String[] columns = eachRow.split("\\|");
      if(columns.length == 2) {
        String propertyUri = columns[0].trim();
        String classUri = columns[1].trim();
        Set<String> classes = map.get(propertyUri);
        if(classes == null) {
          classes = new HashSet<String>();
        }
        classes.add(classUri);
        try {
          @SuppressWarnings("unused")
          gate.creole.ontology.URI uri = new gate.creole.ontology.URI(propertyUri, false);
          map.put(propertyUri, classes);
        }
        catch(InvalidURIException e) {
          // logger.info("URI:" + propertyUri + " is not valid.\n");
        }
      }
    }
    return map;
  }

  /**
   * This method assume that given string is in the form of
   * QueryResultsTable.toString() with 1 column and converts it to the Set of
   * strings i.e. values from the each row of the table
   * 
   * @param resultsTable
   * @return a Set of Strings from the given table (string separated by new
   *         lines)
   */
  @SuppressWarnings("deprecation")
  public static Set<String> fromStringToSet(String resultsTable) {
    Set<String> set = new HashSet<String>();
    String[] rows = resultsTable.split(CATConstants.NEW_LINE);
    for(String eachRow : rows) {
      String uri = eachRow.trim();
      try {
        @SuppressWarnings("unused")
        gate.creole.ontology.URI uriUri = new gate.creole.ontology.URI(uri, false);
        set.add(uri);
      }
      catch(InvalidURIException e) {
        // logger.info("URI:" + uri + " is not valid.\n");
      }

    }
    return set;
  }

}
