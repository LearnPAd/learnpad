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
 *  $Id: CreateSesameOntology.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl.sesame;

import gate.Gate;
import gate.Resource;
import gate.creole.ResourceInstantiationException;

import gate.creole.metadata.CreoleParameter;
import gate.creole.metadata.CreoleResource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.log4j.Logger;


/**
 * An ontology LR that creates a repository based on a config template file.
 * The repository will be created with the specified ID at the given
 * repository location, which can either be a directory or the URL
 * of a sesame server.
 * The template should contain a template variable "id" which will be
 * replaced by the repository ID.
 * <p>
 * NOTE: Any Sesame repository can be created this way, but the functionality
 * of the GATE ontology API depends on an OWLIM repository with owl-max
 * reasoning and partialRDFS optimizations turned off.
 * <p>
 * After creation the owl.rdfs and rdf-schema.rdf files will be imported
 * into the newly created repository.
 *
 * @author Johann Petrak
 */
@CreoleResource(
    name = "CreateSesameOntology",
    interfaceName = "gate.creole.ontology.Ontology",
    comment = "Create a ontology from a Sesame configuration file for a repository",
    icon = "ontology",
    helpURL = "http://gate.ac.uk/userguide/sec:ontologies:ontoplugin:create")
public class CreateSesameOntology extends OntologyLR
{
  private static final long serialVersionUID = 1L;

  /**
   * 
   * @param id
   */
  @CreoleParameter(comment="The name of the repository to create")
  public void setRepositoryID(String id) {
    repositoryID = id;
  }
  public String getRepositoryID() {
    return repositoryID;
  }
  protected String repositoryID;
  
  
  @CreoleParameter(
     comment="The location of the repository, either a direcotry "+
       "or a Sesame server URL")
  public void setRepositoryLocation(java.net.URL loc) {
    repositoryLocation = loc;
  }
  public java.net.URL getRepositoryLocation() {
    return repositoryLocation;
  }
  protected java.net.URL repositoryLocation;

  /**
   *
   * @param url
   */
  @CreoleParameter(comment="The URL of the configuration file to use")
  public void setConfigFile(java.net.URL url) {
    configFileURL = url;
  }
  public java.net.URL getConfigFile() {
    return configFileURL;
  }
  protected java.net.URL configFileURL;


  private Logger logger;

  /**
   * Constructor
   * @throws ResourceInstantiationException
   */
  public CreateSesameOntology() throws ResourceInstantiationException {
    super();
    logger = Logger.getLogger(this.getClass().getName());
  }

  /** Initialises this resource, and returns it.
   * @return
   */
  public Resource init() throws ResourceInstantiationException {
    if(getRepositoryID() == null) {
      throw new ResourceInstantiationException("repositoryID must be set");
    }
    if(getRepositoryLocation() == null) {
      throw new ResourceInstantiationException("repositoryLocation must be set");
    }
    if(getConfigFile() == null) {
      throw new ResourceInstantiationException("configFile must be set");
    }
    load();
    Gate.getCreoleRegister().addCreoleListener(this);
    return this;
  }

  /**
   * Loads this ontology.
   */
  public void load() throws ResourceInstantiationException {
    File configFile;
    try {
      configFile = new File(getConfigFile().toURI());
    } catch (URISyntaxException e) {
      throw new ResourceInstantiationException("Problem with config file URL: "+getConfigFile(),e);
    }
    if(!configFile.exists()) {
      throw new ResourceInstantiationException("Not found: "+getConfigFile());
    }

    OntologyServiceImplSesame oService = OntologyServiceImplSesame.
      createForManagedRepository(this, getRepositoryLocation(), 
      getRepositoryID(),getConfigFile());
      
    //new OntologyServiceImplSesame(this);
    //oService.createManagedRepository(
    //   getRepositoryLocation(),
    //    getRepositoryID(),getConfigFile());

    ontologyService = oService;

    loadSystemImports();
  }


  public java.net.URL getSourceURL() {
    try {
      return new java.net.URL(getRepositoryLocation()+"/"+getRepositoryID());
    } catch (MalformedURLException ex) {
      return null;
    }
  }

}