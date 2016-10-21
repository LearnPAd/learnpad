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
 *  $Id: SesameCLIOptions.java 15721 2012-05-01 13:01:47Z johann_p $
 */
package gate.creole.ontology.impl.sesame;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;
import uk.co.flamingpenguin.jewel.cli.Unparsed;

import java.util.List;

@CommandLineInterface(application="SesameCLI")
public interface SesameCLIOptions {

  // TODO: pattern does not work?!?!?
  @Option(shortName="u",longName="serverURL",description="URL of where Sesame2 server is running")
  String getUrl();
  boolean isUrl();

  @Option(shortName="d",longName="sesameDir",description="The directory that should contain repositories")
  String getDir();
  boolean isDir();

  @Option(longName="configFile",description="The file containing a Sesame configuation")
  String getConfig();
  boolean isConfig();

  @Option(shortName="e",longName="do",description="what to do: query,ask,update,import,export,clear,create,delete,listids")
  String getCmd();

  @Option(shortName="i",longName="id",description="Repository id")
  String getId();
  boolean isId();

  @Option(shortName="f",longName="from",defaultValue="file",description="Where to read things in from: file or stdin")
  String getFrom();
  boolean isFrom();

  @Option(shortName="n",longName="file",description="Name of the file we read from or write to, or stdin/stdout")
  String getFile();
  boolean isFile();

  @Option(longName="baseuri",description="Base URI for importing data")
  String getBaseURI();
  boolean isBaseURI();

  @Option(shortName="t",longName="format",defaultValue="rdf/xml",
    description="Format: rdf/xml, turtle, n-triples, n3, trix, or trig for triples (if the file has a known extension, the format is ignored), sparql or serql for queries ")
  String getFormat();
  boolean isFormat();

  @Option(longName="max",description="Maximum number of results to return")
  int getMax();
  boolean isMax();


  @Option(longName="colsep",description="column separator string")
  String getColsep();
  boolean isColsep();

  @Option(longName="addheader",description="(query only) if present, add the variable names in a header row to the output")
  boolean isAddHeader();

  @Option(shortName="d")
  boolean isDebug();
  
  @Option(helpRequest=true,description="Display help and exit")
  boolean isHelp();

  @Unparsed
  List<String> getArgs();
  boolean isArgs();

}


