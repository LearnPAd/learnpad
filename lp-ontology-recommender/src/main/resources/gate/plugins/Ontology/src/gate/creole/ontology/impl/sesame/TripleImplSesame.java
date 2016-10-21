/*
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  $Id: TripleImplSesame.java 14003 2011-06-12 15:23:44Z johann_p $
 */
package gate.creole.ontology.impl.sesame;

import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.OURI;
import gate.creole.ontology.Triple;

/**
 * Implementation of a Sesame Triple.
 * 
 * @author Johann Petrak
 */
public class TripleImplSesame implements Triple {

  ONodeID subject;
  OURI predicate;
  LiteralOrONodeID object;
  public TripleImplSesame(ONodeID s, OURI p, LiteralOrONodeID o) {
    subject = s;
    predicate = p;
    object = o;
  }
  public ONodeID getSubject() {
    return subject;
  }

  public OURI getPredicate() {
    return predicate;
  }

  public LiteralOrONodeID getObject() {
    return object;
  }

  public String toTurtle() {
    return subject.toTurtle() + " " + predicate.toTurtle() + " " +
      object.toTurtle() + " .";
  }

  @Override
  public String toString() {
    return toTurtle();
  }

  /** 
   * A triple object is identical to another triple object if all three 
   * of subject, predicate and object are equal.This is independent of 
   * the implementation of the other triple.
   * 
   * @param other
   * @return 
   */
  @Override
  public boolean equals(Object other) {
    if(other instanceof Triple) {
      Triple otherTriple = (Triple)other;
      if(!subject.equals(otherTriple.getSubject())) {
        return false;
      } else if(!predicate.equals(otherTriple.getPredicate())) {
        return false;
      } else if(!object.equals(otherTriple.getObject())) {
        return false;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 37 * hash + (this.subject != null ? this.subject.hashCode() : 0);
    hash = 37 * hash + (this.predicate != null ? this.predicate.hashCode() : 0);
    hash = 37 * hash + (this.object != null ? this.object.hashCode() : 0);
    return hash;
  }
  
  
}
