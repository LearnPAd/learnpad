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
 *  $Id: OWLIMOntologyLR.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.owlim;

import gate.creole.ResourceInstantiationException;
import gate.creole.ontology.impl.sesame.*;
import gate.creole.metadata.CreoleParameter;
import gate.creole.metadata.CreoleResource;
import gate.creole.metadata.Optional;

import java.net.URL;


import org.apache.log4j.Logger;


/**
 * This ontology LR behaves identically to 
 * {@link gate.creole.ontology.impl.sesame.OWLIMOntology}
 * but uses the same package and class name as the LR in OntologyOWLIM2.
 * It is provided to make adaption of existing pipelines and applications
 * to the new ontology implementation easier but is deprecated and will be
 * removed when the OntologyOWLIM2 plugin is removed.
 * <p>
 * NOTE: never use this LR for new applications and change to the proper
 * LR as soon as possible for existing applications!
 * 
 * @author Johann Petrak
 * 
 */
@CreoleResource(
    name = "OWLIM Ontology DEPRECATED",
    interfaceName = "gate.creole.ontology.Ontology",
    comment = "Ontology created as a temporary OWLIM3 in-memory repository, for backwards compatibility only",
    icon = "ontology",
    helpURL = "http://gate.ac.uk/userguide/sec:ontologies:ontoplugin:owlim2compat",
    isPrivate = true)
public class OWLIMOntologyLR 
    extends OWLIMOntology
  {

  private static final long serialVersionUID = 1L;

  
  @CreoleParameter(comment="",defaultValue="false")
  @Override
  public void setPersistent(Boolean persistent) {
    isPersistent = persistent;
  }
  public Boolean getPersistent() {
    return isPersistent;
  }

  @Optional
  @CreoleParameter(comment="",disjunction="url",priority=1)
  @Override
  public void setRdfXmlURL(URL theURL) {
    rdfXmlURL = theURL;
  }
  public URL getRdfXmlURL() {
    return rdfXmlURL;
  }

  @Optional
  @CreoleParameter(comment="",disjunction="url",priority=2)
  @Override
  public void setN3URL(URL theURL) {
    n3URL = theURL;
  }
  @Override
  public URL getN3URL() {
    return n3URL;
  }

  @Optional
  @CreoleParameter(comment="",disjunction="url",priority=3)
  @Override
  public void setNtriplesURL(URL theURL) {
    ntriplesURL = theURL;
  }
  @Override
  public URL getNtriplesURL() {
    return ntriplesURL;
  }

  @Optional
  @CreoleParameter(comment="",disjunction="url",priority=4)
  @Override
  public void setTurtleURL(URL theURL) {
    turtleURL = theURL;
  }
  @Override
  public URL getTurtleURL() {
    return turtleURL;
  }

  @Optional
  @CreoleParameter(comment="Directory that should contain the repository director")
  /**
   * Set the name of the directory in which the directory "storage-folder"
   * which contains the ontology repository data will be created.
   * If the directory does not exist but its parent exists, it will be 
   * created.
   */
  @Override
  public void setDataDirectoryURL(URL dataDirectoryURL) {
    this.dataDirectoryURL = dataDirectoryURL;
  }
  @Override
  public URL getDataDirectoryURL() {
    return dataDirectoryURL;
  }

  // this is included for backwards compatibility with the old OWLIMOntologyLR
  public void setPersistLocation(URL persistLocation) {
    this.persistLocation = persistLocation;
  }
  public URL getPersistLocation() {
    return persistLocation;
  }
  protected URL persistLocation;

  // included for backwards compatibility with the old OWLIMOntologyLR
  @Optional
  @CreoleParameter(
      comment="Ontology base URI"
      )
  @Override
  public void setDefaultNameSpace(String theURI) {
    baseURI = theURI;
  }
  @Override
  public String getDefaultNameSpace() {
    return baseURI;
  }


  @Optional
  @CreoleParameter(
      comment="The URL of a file containing mappings between ontology import URIs and URLs or blank"
      )
  @Override
  public void setMappingsURL(URL theMappings) {
    mappingsURL = theMappings;
  }
  public URL getMappingsURL() {
    return mappingsURL;
  }

  @CreoleParameter(
      comment="If the ontology imports specified in the ontology should get automatically loaded",
      defaultValue = "true")
  @Override
  public void setLoadImports(Boolean doit) {
    loadImports = doit;
  }
  @Override
  public Boolean getLoadImports() {
    return loadImports;
  }

  /**
   * Constructor
   */
  public OWLIMOntologyLR()  {
    super();
    //logger = initLogger(this.getClass().getName());
    doSetAutoLabel = true;
    logger = Logger.getLogger(this.getClass().getName());
  }

  @Override
  public void load() throws ResourceInstantiationException {
    System.out.println("Loading OWLIMOntologyLR, doSetAutoLabel is "+doSetAutoLabel);
    // convert the backwards compatible parameters to the new ones
    
    // the persist location is used and set in ways that we rather not
    // continue to use, so we instead use the default location in the
    // temporary directory for now.
    // dataDirectoryName = persistLocation.toString();
    baseURI = defaultNameSpace;
    super.load();
  }
}
