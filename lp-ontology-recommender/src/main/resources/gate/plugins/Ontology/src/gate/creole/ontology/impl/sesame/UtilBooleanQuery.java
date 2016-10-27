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
 *  $Id: UtilBooleanQuery.java 16958 2013-09-28 11:49:04Z johann_p $
 */
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.Literal;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.OntologyBooleanQuery;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.ONodeID;
import org.apache.log4j.Logger;
import org.openrdf.model.Value;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.BooleanQuery;
import org.openrdf.repository.RepositoryConnection;

/**
 * A class representing a Sesame ASK Query. This class makes it easy to
 * use a boolean query and reuse a query with different variable
 * bindings.
 * <p>
 * The following steps should be carried out when using this class:
 * <ol>
 * <li>Create an object and pass on the repository connection, query string,
 * and query language constant
 * <li>Set any variables for the query using {@link #setBinding} one or more times
 * <li>Evaluate the query using {@link evaluate} and retrieve the boolean result.
 * <li>To reuse the query, repeat from step 2
 * </ol>
 *
 * @author Johann Petrak
 */
 public class UtilBooleanQuery implements OntologyBooleanQuery {

    private RepositoryConnection mRepositoryConnection;
    private SesameManager mSesameManager;
    private String mQuery;
    private BooleanQuery mBooleanQuery;
    private QueryLanguage mLang = QueryLanguage.SERQL;
    private Logger logger;
    private boolean mIsPrepared = false;

    public UtilBooleanQuery(
      SesameManager sm,
      String query, 
      OConstants.QueryLanguage lang) 
    {
      logger = Logger.getLogger(this.getClass().getName());
      mSesameManager = sm;
      mRepositoryConnection = sm.getRepositoryConnection();
      logger.debug("Creating query: " + query);
      mQuery = query;
      if(lang == OConstants.QueryLanguage.SPARQL) {
        mLang = QueryLanguage.SPARQL;
      } else if(lang == OConstants.QueryLanguage.SERQL) {
        mLang = QueryLanguage.SERQL;
      } else {
        throw new GateOntologyException("Query language not supported: "+lang);
      }
      prepare();
    }


    public void prepare() {
      try {
        mBooleanQuery = mRepositoryConnection.prepareBooleanQuery(mLang, mQuery);
        mIsPrepared = true;
      } catch (Exception e) {
        throw new GateOntologyException("Cannot prepare boolean query: "+mQuery, e);
      }
    }

    /**
     *
     * @param name
     * @param value
     */
    public void setBinding(String name, LiteralOrONodeID value) {
      mBooleanQuery.setBinding(name, mSesameManager.convertLiteralOrONodeID2SesameValue(value));
    }

    public void setBinding(String name, Value value) {
      mBooleanQuery.setBinding(name, value);
    }

    /**
     * 
     * @return 
     */
    public boolean evaluate() {
      boolean ret;
      if(!mIsPrepared) {
        prepare();
      }
      try {
        ret = mBooleanQuery.evaluate();
      } catch (Exception e) {
        throw new GateOntologyException("Cannot evaluate query: "+mQuery, e);
      }
      return ret;
    }

  public void setBinding(String string, ONodeID onid) {
    mBooleanQuery.setBinding(string, mSesameManager.toSesameResource(onid));
  }

  public void setBinding(String string, Literal ltrl) {
    mBooleanQuery.setBinding(string, mSesameManager.toSesameLiteral(ltrl));
  }

  }

