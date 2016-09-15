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
 *  $Id: OURIImpl.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.OURI;

/**
 *
 * @author Johann Petrak
 */
public class OURIImpl extends ONodeIDImpl implements OURI {
  public OURIImpl(String theURI) {
    super(theURI,false);
  }

  // TODO: implement URI encoding, IRI encoding, validation etc.
  // check out e.g. java.net.URLDecoder and
  // org.apache.commons.vfs.provider.UriParser
  // se also com.hp.hpl.jena.util.URIref and
  // org.semwebcentral.sweetrules.util.URIEncode
  // http://jena.sourceforge.net/iri/documentation.html
  // http://jena.sourceforge.net/iri/javadoc/com/hp/hpl/jena/iri/IRI.html
  // http://mindprod.com/jgloss/urlencoded.html
  // http://www.permadi.com/tutorial/urlEncoding/
  // also see http://www.javadocexamples.com/java/net/URLDecoder/decode%28String%20s,String%20enc%29.html

  // for encoding in relation to toTurtle see the turtle spec:
  // http://www.w3.org/TeamSubmission/turtle/



}
