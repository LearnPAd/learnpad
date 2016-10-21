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
 *  $Id: OBNodeIDImpl.java 15334 2012-02-07 13:57:47Z ian_roberts $
 */

package gate.creole.ontology.impl;

import gate.creole.ontology.OBNodeID;
import gate.creole.ontology.OURI;

/**
 *
 * @author johann
 */
public class OBNodeIDImpl extends ONodeIDImpl implements OBNodeID {
  public OBNodeIDImpl(String uri) {
    super(uri,true);
  }
}
