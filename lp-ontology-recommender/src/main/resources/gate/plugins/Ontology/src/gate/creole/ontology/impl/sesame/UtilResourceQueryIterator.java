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
 *  $Id: UtilResourceQueryIterator.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.GateOntologyException;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.OInstance;
import gate.creole.ontology.OResource;
import gate.creole.ontology.impl.OResourceImpl;
import gate.creole.ontology.impl.Utils;
import gate.util.ClosableIterator;
import org.apache.log4j.Logger;

/**
 *
 * @param <T> 
 * @author Johann Petrak
 */
 public class UtilResourceQueryIterator<T extends OResource>
     implements ClosableIterator<T> {

    private OntologyServiceImplSesame mService;
    private UtilTupleQueryIterator mQuery;
    private Class mClass;
    private Logger logger;

    public UtilResourceQueryIterator(
        OntologyServiceImplSesame service, 
        UtilTupleQueryIterator query,
        Class theClass
        ) {
      mService = service;
      mQuery = query;
      mClass = theClass;
    }


    public void setBinding(String name, LiteralOrONodeID value) {
      mQuery.setBinding(name, mService.getSesameManager().convertLiteralOrONodeID2SesameValue(value));
    }

    public boolean hasNext() {
      return mQuery.hasNext();
    }

    public T next() {
      if(mClass == OClass.class) {
        OClass aClass = null;
        LiteralOrONodeID l = mQuery.nextFirst();
        if(l.isONodeID()) {
          if(l.getONodeID().isAnonymousResource()) {
            aClass = mService.getRestrictionForONodeID(l.getONodeID());
          } else {
            aClass = Utils.createOClass(mService.ontology, mService, l.getONodeID().toString(), OConstants.OWL_CLASS);
          }
        } else {
          throw new GateOntologyException("Unexpected literal when retrieving classes");
        }
        return (T)aClass;
      } else if(mClass == OInstance.class) {
        OInstance aInstance = null;
        LiteralOrONodeID l = mQuery.nextFirst();
        if(l.isONodeID()) {
          if(l.getONodeID().isAnonymousResource()) {
            throw new GateOntologyException("Unexpected blank node when retrieving instances");
          } else {
            aInstance = Utils.createOInstance(mService.ontology, mService, l.getONodeID().toString());
          }
        } else {
          throw new GateOntologyException("Unexpected literal when retrieving instances");
        }
        return (T)aInstance;
      }
      return null;
    }



    public void close() {
      mQuery.close();
    }

    public void remove() {
      throw new UnsupportedOperationException("remove method not supported");
    }
  }

