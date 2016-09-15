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
 *  $Id: SesameCLI.java 15721 2012-05-01 13:01:47Z johann_p $
 */
// TODO: id is currenlty required but ignored when creating
// repositorues, could be used for replacing a specifically named
// variable in the config template, e.g. repoid
//
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.OntologyTupleQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openrdf.query.BooleanQuery;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.Update;
import org.openrdf.query.UpdateExecutionException;
import uk.co.flamingpenguin.jewel.cli.ArgumentValidationException;
import uk.co.flamingpenguin.jewel.cli.CliFactory;

/**
 *
 * @author johann
 */
public class SesameCLI {

  /**
   * @param args the command line arguments
   */
  static String optionUrl;
  static String optionId;
  static String optionDir;
  static String optionCmd;
  static SesameCLIOptions options;
  static SesameManager sesameManager;
  static SesameManager mManager;

  public static void main(String[] args) {
    // TODO: parse command line args, figure out what to do and
    // delegate it to one of the specialist-classes
    // for now only the query is implemented.
    try {
      options =
          CliFactory.parseArguments(SesameCLIOptions.class, args);

    } catch (ArgumentValidationException e) {
      System.err.println("Error parsing options: " + e);
      System.exit(1);
    }

    if(options.isUrl()  && options.isDir()) {
      System.err.println("Options serverUrl and sesameDir cannot be used at the same time");
      System.exit(1);
    }
    if(!options.isUrl() && !options.isDir()) {
      System.err.println("Either option serverUrl or option sesameDir must be specified");
    }
    if(options.isUrl()) {
      optionUrl = options.getUrl();
    } else if (options.isDir()) {
      optionDir = options.getDir();
    }
    if(options.isId()) {
      optionId = options.getId();
    }
    optionCmd = options.getCmd();
  
    // if we need other args, this is how to do it
    //List<String> otherArgs = options.getArgs();
    //if (otherArgs == null) {
    //  otherArgs = new LinkedList<String>();
    //}


    // depending on the command, delegate to one of the functions
    if (optionCmd.equals("clear")) {
      do_clear();
      mManager.disconnect();
    } else if (optionCmd.equals("query")) {
      do_query();
      mManager.disconnect();
    } else if (optionCmd.equals("ask")) {
      do_ask();
      mManager.disconnect();
    } else if (optionCmd.equals("update")) {
      do_update();
      mManager.disconnect();
    } else if (optionCmd.equals("import")) {
      do_import();
      mManager.disconnect();
    } else if (optionCmd.equals("export")) {
      do_export();
      mManager.disconnect();
    } else if (optionCmd.equals("create")) {
      try {
        do_create();
      } catch(Exception ex) {
        System.err.println("Problem creating repository");
        ex.printStackTrace(System.err);
      } finally {
        mManager.disconnect();
      }
    } else if (optionCmd.equals("delete")) {
      do_delete();
      mManager.disconnect();
    } else if (optionCmd.equals("listids")) {
      do_listids();
      mManager.disconnect();
    } else {
      System.err.println("Unknown command: " + optionCmd);
      System.err.println("Use one of: query,import,export,create,clear,delete,listids");
      System.exit(1);
    }
  }


  private static void setManager(String localdir, String url, String repo) {
    mManager = new SesameManager();
    if(localdir != null) {
      mManager.connectToLocalLocation(localdir,true);
    } else {
      mManager.connectToRemoteLocation(url);
    }
    if (repo != null) {
      mManager.openRepository(repo);
    } 
  }

  private static void do_clear() {
    setManager(optionDir,optionUrl,optionId);
    mManager.clearRepository();
    System.err.println("Command clear executed on "+optionUrl+", repository "+optionId);
  }

   private static void do_delete() {
    setManager(optionDir,optionUrl,optionId);
    mManager.deleteRepository(optionId);
    System.err.println("Command delete executed on "+optionUrl+", repository "+optionId);
  }
   
 private static void do_query() {
    if(optionId == null) {
      System.err.println("Repository id (--id or -i) is needed");
      System.exit(1);
    }
    setManager(optionDir,optionUrl,optionId);
    String optionFrom = "file";
    if (options.isFrom()) {
      optionFrom = options.getFrom();
    }
    String optionFile = options.getFile();
    int max = 0; // 0 means all
    if (options.isMax()) {
      max = options.getMax();
    }
    String colsep = "\t";
    if (options.isColsep()) {
      colsep = options.getColsep();
    }
    String queryString = "";
    if (optionFrom.equals("file")) {
      //SELECT ?x ?y WHERE {?x ?p ?y}";
      // read the query from the file whose name is in option file
      try {
        queryString = readTextFile(optionFile, "utf-8");
      } catch (IOException e) {
        System.err.println("Error reading file " + optionFile + ": " + e);
        System.exit(2);
      }
    } else if (optionFrom.equals("stdin")) {
      System.err.println("stdin not implemented yet!");
      System.exit(2);
    } else {
      System.err.println("from must be stdin or file (onyl file implemented yet)");
      System.exit(0);
    }
    doQuery(queryString, max, colsep, options.isAddHeader());

  }
 
 private static void do_ask() {
    setManager(optionDir,optionUrl,optionId);
    String optionFrom = "file";
    if (options.isFrom()) {
      optionFrom = options.getFrom();
    }
    String optionFile = options.getFile();
    String queryString = "";
    if (optionFrom.equals("file")) {
      try {
        queryString = readTextFile(optionFile, "utf-8");
      } catch (IOException e) {
        System.err.println("Error reading file " + optionFile + ": " + e);
        System.exit(2);
      }
    } else if (optionFrom.equals("stdin")) {
      System.err.println("stdin not implemented yet!");
      System.exit(2);
    } else {
      System.err.println("from must be stdin or file (onyl file implemented yet)");
      System.exit(0);
    }
    doAsk(queryString);

  }
 private static void do_update() {
    setManager(optionDir,optionUrl,optionId);
    String optionFrom = "file";
    if (options.isFrom()) {
      optionFrom = options.getFrom();
    }
    String optionFile = options.getFile();
    String queryString = "";
    if (optionFrom.equals("file")) {
      try {
        queryString = readTextFile(optionFile, "utf-8");
      } catch (IOException e) {
        System.err.println("Error reading file " + optionFile + ": " + e);
        System.exit(2);
      }
    } else if (optionFrom.equals("stdin")) {
      System.err.println("stdin not implemented yet!");
      System.exit(2);
    } else {
      System.err.println("from must be stdin or file (onyl file implemented yet)");
      System.exit(0);
    }
    doUpdate(queryString);

  }

  private static void do_import() {
    setManager(optionDir,optionUrl,optionId);
    String fileName = options.getFile();
    File inFile = new File(fileName);
    String baseURI;
    if(options.isBaseURI()) {
      baseURI = options.getBaseURI();
    } else {
      baseURI = "http://dummy.base.uri.com/#";
    }
    String format = options.getFormat();
    mManager.importIntoRepository(inFile, baseURI, format);
  }

  private static void do_export() {
    System.err.println("NOT YET IMPLEMENTED!");
  }

  private static void do_create() {
    if(!options.isConfig()) {
      System.out.println("Need a configFile for creating a repository");
      System.exit(1);
    }
    if(!options.isId()) {
      System.out.println("Need a repository id for creating a repository");
      System.exit(1);
    }
    // create the Map with the variable substutution for the repo id
    // The variable name must be "id" for this to work
    HashMap<String,String> map = new HashMap<String,String>();
    map.put("id", optionId);
    String configFileName = options.getConfig();
    // create a native empty repository at the given URL with the given id
     // use the specified config file for the creation
    String configData = "";
    try {
      configData = readTextFile(configFileName, "utf-8");
    } catch (IOException e) {
      System.err.println("Could not open config file "+configFileName+": "+e);
    }
    setManager(optionDir,optionUrl,null);
    // before we try to create the repository, lets check if it is already there
    Set<String> repositories = mManager.getRepositories();
    if(repositories.contains(optionId)) {
      System.err.println("Repository with this ID already exists: "+optionId);
      System.exit(1);
    }
    configData = SesameManager.substituteConfigTemplate(configData, map);
    System.out.println("Config file is: \n"+configData);
    mManager.createRepository(configData);
  }

  private static void do_listids() {
    setManager(optionDir,optionUrl,null);
    Set<String> reps = mManager.getRepositories();
    for(String rep : reps) {
      System.out.println(rep);
    }
  }

  private static String readTextFile(String f, String encoding)
      throws IOException {
    Reader r = new InputStreamReader(
        new FileInputStream(f), encoding);
    try {
      StringWriter w = new StringWriter();
      char[] buffer = new char[65536];

      int len;
      while ((len = r.read(buffer)) != -1) {
        w.write(buffer, 0, len);
      }

      return w.toString();
    } finally {
      r.close();
    }
  }


  private static void doQuery(String query, int max, String colsep, boolean addheaders) {
    UtilTupleQueryIterator sq = (UtilTupleQueryIterator)mManager.createQuery(query);
    sq.evaluate();
    if(addheaders) {
      int i = 0;
      List<String> headers = sq.getBindingNames();
      for(String varname : headers) {
        System.out.print(varname);
        if (i < headers.size()) {
          System.out.print(colsep);
        }  
      }
      System.out.println();
    }
    int n = 0;
    // for now we remove a trailing new line and replace embedded newlines
    // and any occurrence of the colsep with a space.
    // This may change in the future ...
    Pattern trailingNL = Pattern.compile("\\n$");
    Pattern embedded = Pattern.compile("\\n(?!$)|"+colsep);
    while (sq.hasNext() && (max == 0 || max > n)) {
      //System.err.println("Have a result row");
      Vector<String> row = sq.nextAsString();
      int i = 0;
      for (String value : row) {
        Matcher trailingNLMatcher = trailingNL.matcher(value);
        value = trailingNLMatcher.replaceAll("");
        Matcher embeddedMatcher = embedded.matcher(value);
        value = embeddedMatcher.replaceAll(" ");
        i++;
        // clean the value: replace all occurrences of the column separator 
        // and embedded newlines by space, completely remove trailing newlines
        System.out.print(value);
        if (i < row.size()) {
          System.out.print(colsep);
        }
      }
      System.out.println();
      n++;
    }
  }
  private static void doAsk(String query) {
    BooleanQuery sq = mManager.createAskQuery(query);
    boolean result;
    try {
      result = sq.evaluate();
      System.out.println(result);
    } catch (QueryEvaluationException ex) {
      System.err.println("Could not evaluate boolean query: "+ex);
    }
  }
  
  private static void doUpdate(String query) {
    Update sq = mManager.createUpdate(query);
    try {
      sq.execute();
    } catch (UpdateExecutionException ex) {
      System.err.println("Could not execute update: "+ex);
      ex.printStackTrace(System.err);
    }
  }
}


