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
 *  $Id: ONodeIDImpl.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.InvalidURIException;
import gate.creole.ontology.ONodeID;

/**
 * This class implements interface ONodeID and can represent either
 * blank node or an URI. What each method returns depends on whether the
 * object actually returns a URI or a blank node.
 * @author Johann Petrak
 */
public class ONodeIDImpl implements ONodeID {

  /**
   * Namespace for this URI (in current version - a value before the
   * last occurance of '#' or '/')
   */
  protected String namespace;

  /**
   * A Resource name (in current version - a value after the last
   * occurance of '#' or '/')
   */
  protected String aResourceName;

  /**
   * String representation of the URI
   */
  protected String uri;

  /**
   * Denotes whether the OResource this URI belongs to is an anonymous
   * or not.
   */
  protected boolean isAnonymousResource;

  /**
   * Constructor
   *
   * @param uri
   * @param isAnonymousResource
   * @throws InvalidURIException
   */
  protected ONodeIDImpl(String uri, boolean isAnonymousResource)
          throws InvalidURIException {
    this.isAnonymousResource = isAnonymousResource;
    if(!this.isAnonymousResource) {
      // do this in the same way as the sesame implementation does it
      // in sesame 2.3 see org/openrdf/model/util/URIUtil.java
      // Split into a namespace and local name part after:
      // - the first occurrence of a #, if not found ...
      // - the last occurrence of a /, if not found ...
      // - the last occurence of a :
      // Unlike in the old implementation, an URI can now have
      // a blank resource name (i.e. be all namespace)
      int index = uri.indexOf('#');
      if(index < 0) {
        index = uri.lastIndexOf('/');
      }
      if(index < 0) {
        index = uri.lastIndexOf(':');
      }
      if(index < 0) {
        throw new InvalidURIException("Invalid URI: " + uri);
      }
      index++;  // increase by one: this is the length of the namespace
      this.uri = uri;
      this.namespace = uri.substring(0,index);
      if(uri.length() > index) {
        aResourceName = uri.substring(index,uri.length());
      } else {
        aResourceName = "";
      }
    }
    else {
      this.uri = uri;
      this.namespace = "";
      this.aResourceName = "[" + uri + "]";
    }
  }

  /**
   * Retrieves the name space part from the URI. In this implementation
   * it retrieves the string that appears before the last occurance of
   * '#' or '/'.
   *
   * @return
   */
  public String getNameSpace() {
    return this.namespace;
  }

  /**
   * Retrieves the resource name from the given URI. In this
   * implementation it retrieves the string that appears after the last
   * occurance of '#' or '/'.
   *
   * @return
   */
  public String getResourceName() {
    return this.aResourceName;
  }

  /**
   * Returns the string representation of the uri. In case of anonymous
   * class, it returns the '[' + resourcename + ']'.
   */
  public String toString() {
    return this.uri;
  }

  /**
   * Indicates whether the URI refers to an anonymous resource
   *
   * @return
   */
  public boolean isAnonymousResource() {
    return this.isAnonymousResource;
  }

  public int compareTo(ONodeID other) {
    return this.toString().compareTo(other.toString());
  }

  public boolean equals(Object other) {
    if(other instanceof ONodeID) {
      return this.toString().equals(((ONodeID)other).toString());
    } else {
      return false;
    }
  }

  public int hashCode() {
    return uri.hashCode();
  }

  public String toTurtle() {
    if(isAnonymousResource()) {
      if(uri.startsWith("_:")) {
        return uri;
      } else {
        return "_:"+uri;
      }
    } else {
      return "<"+uri+">";
    }
  }

  public void validate() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public String toASCIIString() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public String toDisplayString() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

}
