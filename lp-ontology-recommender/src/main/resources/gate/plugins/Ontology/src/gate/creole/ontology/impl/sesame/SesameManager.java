/*
 *  SesameManager.java
 *
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Johann Petrak, 2009-07-26
 *
 *  $Id: SesameManager.java 17551 2014-03-06 11:08:34Z johann_p $
 */
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.impl.OURIImpl;
import gate.creole.ontology.DataType;
import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.InvalidValueException;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.OURI;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.StringReader;
import java.io.InputStream;
import java.io.Reader;

import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openrdf.model.Graph;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.GraphImpl;
import org.openrdf.model.util.GraphUtil;
import org.openrdf.model.util.GraphUtilException;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;
import org.openrdf.repository.manager.LocalRepositoryManager;
import org.openrdf.repository.manager.RemoteRepositoryManager;
import org.openrdf.repository.manager.RepositoryManager;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;
import org.openrdf.rio.helpers.StatementCollector;
import org.openrdf.model.Resource;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.DelegatingRepository;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.DelegatingRepositoryImplConfig;
import org.openrdf.repository.config.RepositoryConfig;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.config.RepositoryConfigSchema;
import org.openrdf.repository.config.RepositoryConfigUtil;
import org.openrdf.repository.config.RepositoryFactory;
import org.openrdf.repository.config.RepositoryImplConfig;
import org.openrdf.repository.config.RepositoryRegistry;
import org.openrdf.sail.memory.model.MemValueFactory;
import gate.creole.ontology.OntologyTupleQuery;
import gate.creole.ontology.impl.LiteralOrONodeIDImpl;
import gate.creole.ontology.impl.OBNodeIDImpl;
import gate.creole.ontology.impl.ONodeIDImpl;
import java.util.Locale;
import org.openrdf.http.client.HTTPClient;
import org.openrdf.model.BNode;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.impl.BNodeImpl;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.model.impl.URIImpl;
import org.openrdf.query.BooleanQuery;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.Update;
/**
 * A class to encapsulate management of a Sesame2 repository and
 * make handling different repository types easier.
 * This class exposes as few Sesame-internal classes as possible.
 * All errors are handled by throwing its own runtime exception
 * SesameManagerException.
 * <p>
 * In order to create repositories the following protocol
 * of calling methods must be observed:
 *
 *
 * @author Johann Petrak
 */
public class SesameManager {
  private RepositoryConnection mRepositoryConnection;
  private Repository           mRepository;
  private RepositoryManager    mRepositoryManager;
  private String               mRepositoryLocation;
  private String               mRepositoryName;
  private static final Pattern TOKEN_PATTERN =
      Pattern.compile("\\{%[\\p{Print}&&[^\\}]]+%\\}");

  private boolean debug = true;

  private class SesameManagerException
      extends RuntimeException {
    public SesameManagerException() {}

    public SesameManagerException(String message) {
      super(message);
    }

    public SesameManagerException(String message, Throwable cause) {
      super(message, cause);
    }

    public SesameManagerException(Throwable e) {
      super(e);
    }
    /**
     * Overridden so we can print the enclosed exception's stacktrace too.
     */
    public void printStackTrace() {
      printStackTrace(System.err);
    }

    /**
     * Overridden so we can print the enclosed exception's stacktrace too.
     */
    public void printStackTrace(java.io.PrintStream s) {
      s.flush();
      super.printStackTrace(s);
      Throwable cause = getCause();
      if (cause != null) {
        s.print("Caused by:\n");
        cause.printStackTrace(s);
      }
    }

    /**
     * Overridden so we can print the enclosed exception's stacktrace too.
     */
    public void printStackTrace(java.io.PrintWriter s) {
      s.flush();
      super.printStackTrace(s);
      Throwable cause = getCause();
      if (cause != null) {
        s.print("Caused by:\n");
        cause.printStackTrace(s);
      }
    }
  }

    private Logger logger;

    public SesameManager() {
      logger = Logger.getLogger(this.getClass().getName());
    }



  boolean isManagedRepository = false;

    /**
     * Connect to a managed repository located at the given location
     * and connect to the repository with the given name.
     * The repository connection is assumed to be remote if it starts with
     * http:// or https://, otherwise the location is assumed to be a local
     * directory name.
     * 
     * @param repositoryLocation
     * @param repositoryName
     */
    public void connectToRepository(String repositoryLocation, String repositoryName) {
    // connect to location and get the manager
    closeRepository();
    connectToLocation(repositoryLocation);
    openRepository(repositoryName);
  }
    public void connectToRepository(java.net.URL repositoryLocation, String repositoryName) {
    // connect to location and get the manager
    closeRepository();
    connectToLocation(repositoryLocation);
    openRepository(repositoryName);
  }

    /**
     * Connect to a managed repository location.
     * The repository connection is assumed to be remote if it starts with
     * http:// or https://, otherwise the location is assumed to be a local
     * directory name.
     * 
     * @param repositoryLocation
     */
    public void connectToLocation(String repositoryLocation) {
    // if the location starts with http:// it will be assumed that this
    // is a remote location, otherwise it will be regarded as a directory
    // name.
      logger.debug("Calling SesameManager.connectToLocation with String: "+repositoryLocation);
    if(repositoryLocation.startsWith("http://") ||
       repositoryLocation.startsWith("https://")) {
      connectToRemoteLocation(repositoryLocation);
    } else {
      connectToLocalLocation(repositoryLocation,true);
    }
  }
    public void connectToLocation(java.net.URL repositoryLocation) {
    // if the location starts with http:// it will be assumed that this
    // is a remote location, otherwise it will be regarded as a directory
    // name.
      logger.debug("Calling SesameManager.connectToLocation with URL: "+repositoryLocation);
      logger.debug("Protocol is: "+repositoryLocation.getProtocol());
    if(repositoryLocation.getProtocol().startsWith("http")) {
      connectToRemoteLocation(repositoryLocation.toString());
    } else {
      connectToLocalLocation(repositoryLocation,true);
    }
  }

    /**
     * Connect to a remote managed repository location.
     * 
     * @param url
     */
    public void connectToRemoteLocation(String url) {
      isManagedRepository = true;
      HTTPClient httpClient = new HTTPClient();
      httpClient.setQueryURL(url);
      httpClient.setUpdateURL(url);
      RemoteRepositoryManager mgr = new RemoteRepositoryManager(url);
      try {
        java.net.URL javaurl = new java.net.URL(url);
        String userpass = javaurl.getUserInfo();
        if(userpass != null) {
          String[] userpassfields = userpass.split(":");
          if(userpassfields.length != 2) {
            throw new SesameManagerException("URL has login data but not username and password");
          } else {
            mgr.setUsernameAndPassword(userpassfields[0], userpassfields[1]);
          }
        }
      } catch(Exception ex) {
        throw new SesameManagerException("Problem processing remote URL: "+ex);
      }
      setManager(mgr, url);
    
  }

  /**
   * Connect to a local repository location at the given directory.
   * If mustexist is true, it is an error if the directory is not found.
   *
   * @param dirname
   * @param mustexist
   */
  public void connectToLocalLocation(String dirname, boolean mustexist) {
    isManagedRepository = true;
    //dirname = dirname.replaceAll("/$", "");
    File dir = new File(dirname);
    if (!dir.exists()) {
      throw new SesameManagerException("Specified path does not exist: " + 
        dir.getAbsolutePath());
    }
    if (!dir.isDirectory()) {
      throw new SesameManagerException("Specified path is not a directory: " + 
        dir.getAbsolutePath());
    }
    setManager(new LocalRepositoryManager(dir), dir.toString());

  }
  public void connectToLocalLocation(java.net.URL dirname, boolean mustexist) {
    isManagedRepository = true;
    logger.debug("Called connectToLocalLocation "+dirname+"/"+mustexist);
    File dir;
    try {
      dir = new File(dirname.toURI());
    } catch (URISyntaxException ex) {
      throw new SesameManagerException("Specified URL is invalid: "+dirname,ex);
    }
    if (!dir.exists()) {
      throw new SesameManagerException("Specified path does not exist: " +
        dir.getAbsolutePath());
    }
    if (!dir.isDirectory()) {
      throw new SesameManagerException("Specified path is not a directory: " + 
        dir.getAbsolutePath());
    }
    setManager(new LocalRepositoryManager(dir), dir.toString());

  }

    /**
     * Disconnect from a local or remote repository manager.
     * 
     */
  public void disconnect() {
    closeRepository();
    if (mRepositoryManager != null) {
      logger.debug("Shutting down the repository manager");
			mRepositoryManager.shutDown();
      logger.debug("manager is shut down");
			mRepositoryManager = null;
			mRepositoryLocation = null;
      logger.debug("manager and location set to null");
		}     
  }

  private void setManager(RepositoryManager manager, String location) {
    logger.debug("setManager called");
    try {
      disconnect();
      manager.initialize();
      mRepositoryManager = manager;
      mRepositoryLocation = location;
    } catch (RepositoryException e) {
      throw new SesameManagerException("Error initializing manager: "+e);
    }
  }

  /**
   * Open a repository with the given name at the remote or local location
   * previously connected to.
   * An error is raised if no local or remote location was set prior to
   * calling this method.
   * 
   * @param name
   */
  public void openRepository(String name) {
    logger.debug("Called openRespository with ID "+name);
    if(mRepositoryManager != null) {
      try {
        mRepository = mRepositoryManager.getRepository(name);
      } catch (Exception e) {
        throw new SesameManagerException("Could not get repository "+name+" error is "+e);
      }
      if(mRepository == null) {
        throw new SesameManagerException("Getting repository failed - no repository of this name found: "+name);
      }
      try {
        mRepositoryConnection = mRepository.getConnection();
        logger.debug("repository connection set");
      } catch (Exception e) {
        throw new SesameManagerException("Could not get connection "+name+" error is "+e);
      }
    } else {
      throw new SesameManagerException("Not connected to a repository location for openRepository "+name);
    }
  }

  /**
   * Close the currently opened repository. This works for managed and
   * unmanaged repositories.
   */
  public void closeRepository() {
		if (mRepositoryConnection != null) {
      try {
        logger.debug("Commiting the connection");
			  //mRepositoryConnection.commit();
        logger.debug("Closing the connection");
        mRepositoryConnection.close();
        logger.debug("Connection closed");
        // the following is NOT needed as the manager shutDown method
        // shuts down all repositories
        // mRepository.shutDown();
        logger.debug("Repository shut down");
      } catch (RepositoryException e) {
        logger.debug("!!!!! Error: ",e);
        // TODO: do not throw exception, might still need to disconnect
        // manager!
        // throw new SesameManagerException("Could not close Repository: "+e);
      }
      mRepositoryConnection = null;
      mRepository = null;
			mRepositoryName = null;
      logger.debug("connection, repository and name set to null");
		}
  }

  // create repository from a template, no substitution of variables
  // also opens the newly created repository
  /**
   * Create a new managed repository at the current remote or local location
   * using the configuration information passed on as a string.
   * 
   * @param config
   */
  public void createRepository(String config) {
    logger.debug("createRepository called");
    if(mRepositoryManager == null) {
      throw new SesameManagerException("No connect prior to createRepository");
    }
    Repository systemRepo = mRepositoryManager.getSystemRepository();

    ValueFactory vf = systemRepo.getValueFactory();
    Graph graph = new GraphImpl(vf);
    RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE, vf);
    rdfParser.setRDFHandler(new StatementCollector(graph));
    try {
      rdfParser.parse(new StringReader(config), RepositoryConfigSchema.NAMESPACE);
    } catch (Exception e) {
      throw new SesameManagerException("Error parsing the config string: ",e);
    }
    try {
      Resource repositoryNode =
        GraphUtil.getUniqueSubject(graph, RDF.TYPE,RepositoryConfigSchema.REPOSITORY);
      RepositoryConfig repConfig = RepositoryConfig.create(graph, repositoryNode);
      repConfig.validate();
      if (RepositoryConfigUtil.hasRepositoryConfig(systemRepo, repConfig.getID())) {
        throw new SesameManagerException("Repository already exists with ID "+
          repConfig.getID());
      } else {
        RepositoryConfigUtil.updateRepositoryConfigs(systemRepo, repConfig);
        mRepository = mRepositoryManager.getRepository(repConfig.getID());
        // Sesame complains about the repository already being initialized
        // for native but not for OWLIM here ... can we always not initialize
        // here????
        try {
          mRepository.initialize();
        } catch (IllegalStateException ex) {
          System.err.println("Got an IllegalStateException, ignored: "+ex);
          // we get this if the SAIL has already been initialized, just
          // ignore and be happy that we can be sure that indeed it has
        }
        openRepository(repConfig.getID());
      }
    } catch (Exception e) {
      throw new SesameManagerException("Error creating repository",e);
    }
  }

  /**
   * Create an unmanaged repository with files stored in the directory
   * given from the configuration passed as a string.
   * 
   * @param configstring
   * @return
   * @throws java.sesame_lang.Exception
   */
  public void createUnmanagedRepository(File repositoryDirFile, String configstring) {
    isManagedRepository = false;
    logger.debug("SesameManager: creating unmanaged repo, dir is "+repositoryDirFile.getAbsolutePath());
    ValueFactory vf = new MemValueFactory();
    Graph graph = parseRdf(configstring, vf, RDFFormat.TURTLE);
    Resource repositoryNode;
    try {
      repositoryNode = GraphUtil.getUniqueSubject(graph, RDF.TYPE, RepositoryConfigSchema.REPOSITORY);
    } catch (GraphUtilException ex) {
      throw new SesameManagerException("Could not get subject of config RDF",ex);
    }
    RepositoryConfig repConfig;
    try {
      repConfig = RepositoryConfig.create(graph, repositoryNode);
    } catch (RepositoryConfigException ex) {
      throw new SesameManagerException("Could not create repository from RDF graph",ex);
    }
    try {
      repConfig.validate();
    } catch (RepositoryConfigException ex) {
      throw new SesameManagerException("Could not validate repository",ex);
    }
    RepositoryImplConfig rpc = repConfig.getRepositoryImplConfig();
    Repository repo = createRepositoryStack(rpc);
    repo.setDataDir(repositoryDirFile);
    try {
      repo.initialize();
    } catch (RepositoryException ex) {
      throw new SesameManagerException("Could not initialize repository",ex);
    }
    try {
      mRepositoryConnection = repo.getConnection();
      logger.debug("Repo dir is "+repo.getDataDir().getAbsolutePath());
      logger.debug("Repo is writable "+repo.isWritable());
    } catch (RepositoryException ex) {
      throw new SesameManagerException("Could not get connection for unmanaged repository",ex);
    }
  }

  private Graph parseRdf(String config, ValueFactory vf, RDFFormat lang) {
    Graph graph = new GraphImpl(vf);
    RDFParser rdfParser = Rio.createParser(lang, vf);
    rdfParser.setRDFHandler(new StatementCollector(graph));
    try {
      rdfParser.parse(new StringReader(config), RepositoryConfigSchema.NAMESPACE);
    } catch (Exception e) {
      throw new SesameManagerException("Could not parse rdf: " + e);
    }
    return graph;
  }

 
  private RepositoryConfig getConfig(String config) {
		Repository myRepository = new SailRepository(new MemoryStore());
    RepositoryConfig repConfig;
		try {
	    myRepository.initialize();
		} catch (RepositoryException e) {
		  throw new SesameManagerException("Error initializing memory store: "+e);
		}
    ValueFactory vf = myRepository.getValueFactory();
    Graph graph = new GraphImpl(vf);
    RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE, vf);
    rdfParser.setRDFHandler(new StatementCollector(graph));
    try {
      rdfParser.parse(new StringReader(config), RepositoryConfigSchema.NAMESPACE);
	    Resource repositoryNode =
          GraphUtil.getUniqueSubject(graph, RDF.TYPE,RepositoryConfigSchema.REPOSITORY);
			repConfig = RepositoryConfig.create(graph, repositoryNode);
			repConfig.validate();

    } catch (Exception e) {
      throw new SesameManagerException("Error parsing the config string "+e);
    }
    return repConfig;
  }


	private Repository createRepositoryStack(RepositoryImplConfig config) {
		RepositoryFactory factory = RepositoryRegistry.getInstance().get(config.getType());
		if (factory == null) {
			throw new SesameManagerException("Unsupported repository type: " + config.getType());
		}

		Repository repository;
    try {
      repository = factory.getRepository(config);
    } catch (RepositoryConfigException ex) {
      throw new SesameManagerException("Could not get repository from factory",ex);
    }

		if (config instanceof DelegatingRepositoryImplConfig) {
			RepositoryImplConfig delegateConfig = ((DelegatingRepositoryImplConfig)config).getDelegate();

			Repository delegate = createRepositoryStack(delegateConfig);

			try {
				((DelegatingRepository)repository).setDelegate(delegate);
			}
			catch (ClassCastException e) {
				throw new SesameManagerException(
						"Delegate specified for repository that is not a DelegatingRepository: "
								+ delegate.getClass());
			}
		}

		return repository;
	}

  /**
   * Substitute variables in a configuration template string.
   * 
   * @param configtemplate
   * @param variables
   * @return
   */
  public static String substituteConfigTemplate(String configtemplate, Map<String,String> variables) {
    // replace all variables in the template then do the actual createRepository
    StringBuffer result = new StringBuffer(configtemplate.length()*2);
    Matcher matcher = TOKEN_PATTERN.matcher(configtemplate);
		while (matcher.find()) {
			String group = matcher.group();
      // get the variable name and default
			String[] tokensArray = group.substring(2, group.length() - 2).split("\\|");

			String var = tokensArray[0].trim();

			String value = variables.get(var);
      if(value == null) {
        // try to get the default
        if(tokensArray.length > 1) {
          value = tokensArray[1].trim();
        } else {
          value = "";
        }
      }
			matcher.appendReplacement(result, value);
		}

		matcher.appendTail(result);

    return result.toString();
  }

  /**
   * Delete the managed repository with that name.
   * 
   * @param name
   */
  public void deleteRepository(String name) {
    if(mRepositoryManager != null) {
      closeRepository();
      try {
        boolean done = mRepositoryManager.removeRepositoryConfig(name);
      } catch (RepositoryException e) {
        throw new SesameManagerException("Could not delete repository "+name+": "+e);
      } catch (RepositoryConfigException e) {
        throw new SesameManagerException("Could not delete repository "+name+": "+e);
      }
    } else {
      throw new SesameManagerException("Must be connected to a location");
    }
  }
 
  /**
   * Clear the current repository and remove all data from it.
   * 
   */
  public void clearRepository() {
    try {
      mRepositoryConnection.clear();
    } catch (RepositoryException e) {
      throw new SesameManagerException("Could not clear repository: "+e);
    }
  }


 /**
  * Load data into the current repository from a file.
  * 
  * @param from
  * @param baseURI
  * @param format
  */
  public void importIntoRepository(File from, String baseURI, String format) {
    if(mRepositoryConnection != null) {
      RDFFormat sesameFormat = RDFFormat.valueOf(format);
      if(sesameFormat==null) {
        throw new SesameManagerException(
          "Could not import - format not supported: "+format);
      }
      try {
        mRepositoryConnection.add(from,baseURI,sesameFormat);
      } catch(Exception e) {
        throw new SesameManagerException("Could not import",e);
      }
    } else {
      throw new SesameManagerException("Cannot import, no connection");
    }
  }

  /**
   * Load data into the current repository from a stream.
   * 
   * @param from
   * @param baseURI
   * @param format
   */
  public void importIntoRepository(InputStream from, String baseURI, String format) {
    if(mRepositoryConnection != null) {
      RDFFormat sesameFormat = RDFFormat.valueOf(format);
      try {
        mRepositoryConnection.add(from,baseURI,sesameFormat);
      } catch(Exception e) {
        throw new SesameManagerException("Could not import: "+e);
      }
    } else {
      throw new SesameManagerException("Cannot import, no connection");
    }
  }

  /**
   * Load data into the current repository from a reader
   * 
   * @param from
   * @param baseURI
   * @param format
   */
  public void importIntoRepository(Reader from, String baseURI, String format) {
    if(mRepositoryConnection != null) {
      RDFFormat sesameFormat = RDFFormat.valueOf(format);
      try {
        mRepositoryConnection.add(from,baseURI,sesameFormat);
      } catch(Exception e) {
        throw new SesameManagerException("Could not import: "+e);
      }
    } else {
      throw new SesameManagerException("Cannot import, no connection");
    }
  }

  /**
   * Create a query object for the current repository.
   * 
   * @param query
   * @return
   */
  public OntologyTupleQuery createQuery(String query) {
    if(mRepositoryConnection != null) {
      return new UtilTupleQueryIterator(
          this,
          query,
          OConstants.QueryLanguage.SPARQL);
    } else {
      throw new SesameManagerException("Cannot create a query, no connection");
    }
  }

  public BooleanQuery createAskQuery(String query) {
    if(mRepositoryConnection != null) {
      try {
        return mRepositoryConnection.prepareBooleanQuery(QueryLanguage.SPARQL, query);
      } catch (Exception ex) {
        throw new SesameManagerException("Could not prepare BooleanQuery",ex);
      }
    } else {
      throw new SesameManagerException("Could not create an ask query, no connection");
    }
  }

  public Update createUpdate(String query) {
    if(mRepositoryConnection != null) {
      try {
        return mRepositoryConnection.prepareUpdate(QueryLanguage.SPARQL, query);
      } catch (Exception ex) {
        throw new SesameManagerException("Could not prepare an Update operation",ex);
      }
    } else {
      throw new SesameManagerException("Cannot create an update operation, no connection");
    }
  }

  public Set<String> getRepositories() {
    if(mRepositoryManager == null) {
      return new HashSet<String>();
    }
    try {
      return mRepositoryManager.getRepositoryIDs();
    } catch (RepositoryException ex) {
      throw new SesameManagerException("Could not get repository IDs: ",ex);
    }
  }

  /**
   * Obtain the repository connection object.
   * 
   * @return
   */
  public RepositoryConnection getRepositoryConnection() {
    return mRepositoryConnection;
  }

  
  // Utility functions for converting between Sesame and our own 
  // representations
  /** 
   * Convert a Sesame literal to a GATE literal
   * 
   * @param sesameLiteral 
   * @return 
   */
  public gate.creole.ontology.Literal convertSesameLiteral2Literal(
      org.openrdf.model.Literal sesameLiteral) {
    URI sesame_datatype = sesameLiteral.getDatatype();
    String sesame_lang = sesameLiteral.getLanguage();
    
    gate.creole.ontology.Literal ret = null;
    if(sesame_datatype != null) {
      try {
        ret = new gate.creole.ontology.Literal(
          sesameLiteral.getLabel(), 
          toGateDataType(sesame_datatype));
      } catch (InvalidValueException ex) {
        throw new GateOntologyException(
            "Could not convert literal from Sesame: "+sesameLiteral,ex);
      }
    } else {
      if(sesame_lang != null) {
        ret = new gate.creole.ontology.Literal(
          sesameLiteral.getLabel(),
          lang2locale(sesame_lang));
      } else {
        ret = new gate.creole.ontology.Literal(sesameLiteral.getLabel());
      }
    }
    /*
    DataType gate_datatype = null;
    System.out.println("Converting from Sesame Literal: "+sesameLiteral+","+sesameLiteral.getDatatype()+","+sesameLiteral.getLanguage());
    if(sesame_datatype == null) {
      if(sesame_lang != null) {
        // If we find a language, we use String 
        gate_datatype = DataType.getStringDataType();
      } else {
        // if there is no Datatype and no Language, use anyType
        // gate_datatype = new DataType(new OURIImpl("http://www.w3.org/2001/XMLSchema#anyType"));
      }
    } else {
      gate_datatype = toGateDataType(sesame_datatype);
    }
    Locale locale = null;
    if(sesame_lang != null) {
      locale = lang2locale(sesame_lang);
    }
    gate.creole.ontology.Literal ret = null;
    if(locale != null) {
      ret = new gate.creole.ontology.Literal(sesameLiteral.getLabel(),locale);
    } else {
      try {
        ret = new gate.creole.ontology.Literal(sesameLiteral.getLabel(), gate_datatype);
      } catch (InvalidValueException ex) {
        // TODO: what to do here?
        throw new GateOntologyException(
            "Could not convert literal from Sesame: "+sesameLiteral);
      }
    }*/
    //System.out.println("Converted sesame to gate literal: "+sesameLiteral+"/"+ret.getValue()+","+ret.getDataType()+","+ret.getLanguage());
    return ret;
  }

  
  public org.openrdf.model.Value convertLiteralOrONodeID2SesameValue(LiteralOrONodeID val) {
    if(val.isLiteral()) {
      return toSesameLiteral(val.getLiteral());
    } else {
      ONodeID id = val.getONodeID();
      return toSesameResource(id);
    }
  }
  
  public Value toSesameValue(ONodeID id) {
    return toSesameResource(id);
  }
  
  public gate.creole.ontology.DataType toGateDataType(org.openrdf.model.URI uri) {
    if(uri == null) {
      return null;
    }
    DataType dt = new DataType(new OURIImpl(uri.toString()));
    return dt;
  }

  public Locale lang2locale(String lang) {
    if(lang == null) {
      return null;
    }
    Locale locale = new Locale(lang);
    return locale;
  }
  
  // ********************************************
  // *********************** Sesame -> Ontology 
  // ********************************************
  public ONodeID toGateONodeID(org.openrdf.model.Resource resource) {
    ONodeID onodeid = null;
    if(resource instanceof BNode) {
      return new OBNodeIDImpl(resource.stringValue());
    } else if(resource instanceof org.openrdf.model.URI) {
      return new OBNodeIDImpl(((org.openrdf.model.URI)resource).stringValue());
    } else {
      throw new SesameManagerException("Cannot convert Resource to ONodeID, not a BNode or a URI: "+resource);
    }
  }
  
  public OURI toGateOURI(org.openrdf.model.URI uri) {
    return new OURIImpl(uri.stringValue());
  }
  
  public LiteralOrONodeID toGateLiteralOrONodeID(Value v) {
    if (v instanceof BNode) {
      return new LiteralOrONodeIDImpl(new OBNodeIDImpl(v.stringValue()));
    } else if (v instanceof org.openrdf.model.Literal) {
      return new LiteralOrONodeIDImpl(
        convertSesameLiteral2Literal((org.openrdf.model.Literal) v));
    } else if (v instanceof org.openrdf.model.URI) {
      URI u = (URI) v;
      return new LiteralOrONodeIDImpl(new OURIImpl(u.stringValue()));
    } else {
      throw new SesameManagerException("Cannot convert Value to LiteralOrONodeID: " + v);
    }
  }
  
  // ******************************************** 
  // ********************** Ontology -> Sesame 
  // ********************************************
  
  /** 
   * Convert an ontology plugin ONodeID to a Sesame Resource.
   * 
   * @param node
   * @return 
   */
  public org.openrdf.model.Resource toSesameResource(ONodeID node) {
    Resource r = null;
    if (node != null) {
      if (node.isAnonymousResource()) {
        String id = node.toString();
        if (id.startsWith("_:")) {
          id = id.substring(2);
        }
        r = mRepositoryConnection.getValueFactory().createBNode(id);
      } else {
        r = mRepositoryConnection.getValueFactory().createURI(node.toString());
      }
    }
    return r;
  }
  
  /** 
   * Convert an ontology plugin OURI to a Sesame URI
   * 
   * @param node
   * @return 
   */
  public org.openrdf.model.URI toSesameURI(OURI node) {
    org.openrdf.model.URI r = null;
    if (node != null) {
      r = mRepositoryConnection.getValueFactory().createURI(node.toString());
    }
    return r;
  }
  
  
  /**
   * Convert an Ontology plugin Literal represenation into a Sesame Literal
   * 
   * @param literal
   * @return  
   */ 
  public org.openrdf.model.Literal toSesameLiteral(gate.creole.ontology.Literal literal) {
    org.openrdf.model.Literal l = null;
    if (literal != null) {
      if (literal.getLanguage() != null && !literal.getLanguage().getLanguage().equals("")) {
        l = mRepositoryConnection.
          getValueFactory().
          createLiteral(
            literal.getValue(), 
            literal.getLanguage().getLanguage());
      } else if (literal.getDataType() != null) {
        l = mRepositoryConnection.
          getValueFactory().
          createLiteral(
            literal.getValue(),
            mRepositoryConnection.
              getValueFactory().
              createURI(literal.getDataType().getXmlSchemaURIString()));
      } else {
        l = mRepositoryConnection.
          getValueFactory().
          createLiteral(literal.getValue());
      }
    }
    return l;
  }
  
  
  

}
