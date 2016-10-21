/*
 *  Copyright (c) 1995-2012, The University of Sheffield. See the file
 *  COPYRIGHT.txt in the software or at http://gate.ac.uk/gate/COPYRIGHT.txt
 *
 *  This file is part of GATE (see http://gate.ac.uk/), and is free
 *  software, licenced under the GNU Library General Public License,
 *  Version 2, June 1991 (in the distribution as file licence.html,
 *  and also available at http://gate.ac.uk/gate/licence.html).
 *
 *  Johann Petrak 2009-08-20
 *
 *  $Id: LiteralOrONodeIDImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.Literal;
import gate.creole.ontology.LiteralOrONodeID;
import gate.creole.ontology.ONodeID;
import gate.util.GateRuntimeException;

/**
 * Wrap either a Literal or a ONodeID object.
 * <p>
 * TODO: should we implement comparable and equals/hashcode for this?
 * 
 * @author Johann Petrak
 */
public class LiteralOrONodeIDImpl implements LiteralOrONodeID  {
  protected Object theObject;
  protected boolean isLiteral;
  
  public LiteralOrONodeIDImpl(Literal aLiteral) {
    theObject = aLiteral;
    isLiteral = true;
  }
  public LiteralOrONodeIDImpl(ONodeID aONodeID) {
    theObject = aONodeID;
    isLiteral = false;
  }
  public boolean isLiteral() {
    return isLiteral;
  }

  public boolean isONodeID() {
    return !isLiteral;
  }

  public ONodeID getONodeID() {
    if(isLiteral) {
      throw new GateRuntimeException(
          "Cannot return an ONodeID, have a Literal: "+((Literal)theObject));
    }
    return (ONodeID)theObject;
  }

  public Literal getLiteral() {
    if(!isLiteral) {
      throw new GateRuntimeException(
          "Cannot return a Literal, have an ONodeID: "+((ONodeID)theObject));
    }
    return (Literal)theObject;
  }

  @Override
  public String toString() {
    if(isLiteral) {
      return ((Literal)theObject).toString();
    } else {
      return ((ONodeID)theObject).toString();
    }
  }

  public String toTurtle() {
    if(isLiteral) {
      return ((Literal)theObject).toTurtle();
    } else {
      return ((ONodeID)theObject).toTurtle();
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final LiteralOrONodeIDImpl other = (LiteralOrONodeIDImpl) obj;
    if (this.isLiteral != other.isLiteral) {
      return false;
    }
    if(this.isLiteral) {
      return ((Literal)this.theObject).equals((Literal)other.theObject);
    } else {
      return ((ONodeID)this.theObject).equals((ONodeID)other.theObject);
    }
  }

  @Override
  public int hashCode() {
    if(isLiteral) {
      return ((Literal)theObject).hashCode();
    } else {
      return ((ONodeID)theObject).hashCode();
    }
  }

}
