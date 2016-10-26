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
 *  $Id: OValueImpl.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.Literal;
import gate.creole.ontology.OValue;
import gate.creole.ontology.OResource;
import gate.util.GateRuntimeException;

/**
 * Wrap either a Literal or a OResource object
 *
 * @author Johann Petrak
 */
public class OValueImpl implements OValue  {
  protected Object theObject;
  protected boolean isLiteral;
  
  public OValueImpl(Literal aLiteral) {
    theObject = aLiteral;
    isLiteral = true;
  }
  public OValueImpl(OResource aResource) {
    theObject = aResource;
    isLiteral = false;
  }
  public boolean isLiteral() {
    return isLiteral;
  }

  public boolean isOResource() {
    return !isLiteral;
  }

  public OResource getOResource() {
    if(isLiteral) {
      throw new GateRuntimeException(
          "Cannot return an OResource, have a Literal: "+((Literal)theObject));
    }
    return (OResource)theObject;
  }

  public Literal getLiteral() {
    if(!isLiteral) {
      throw new GateRuntimeException(
          "Cannot return a Literal, have an ONodeID: "+((OResource)theObject));
    }
    return (Literal)theObject;
  }

  @Override
  public String toString() {
    if(isLiteral) {
      return ((Literal)theObject).toString();
    } else {
      return ((OResource)theObject).toString();
    }
  }

  public String toTurtle() {
    if(isLiteral) {
      return ((Literal)theObject).toTurtle();
    } else {
      return ((OResource)theObject).toString();
    }
  }

  @Override
  public int hashCode() {
    if(isLiteral) {
      return ((Literal)theObject).hashCode();
    } else {
      return ((OResource)theObject).hashCode();
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
    final OValueImpl other = (OValueImpl) obj;
    if (this.isLiteral != other.isLiteral) {
      return false;
    }
    if(this.isLiteral) {
      return ((Literal)this.theObject).equals((Literal)other.theObject);
    } else {
      return ((OResource)this.theObject).equals((OResource)other.theObject);
    }
  }



}
