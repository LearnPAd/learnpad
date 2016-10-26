/*
 *  OClassImpl.java
 *
 *  Niraj Aswani, 09/March/07
 *
 *  $Id: OClassImpl.java 17080 2013-11-12 19:29:34Z markagreenwood $
 */
package gate.creole.ontology.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import gate.creole.ontology.OConstants.Closure;
import gate.creole.ontology.OClass;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.Ontology;
import gate.util.ClosableIterator;

/**
 * Implementation of the OClass interface
 * 
 * @author niraj
 * 
 */
public class OClassImpl extends OResourceImpl implements OClass {
  /**
   * Constructor
   * 
   * @param aURI
   * @param ontology
   * @param ontologyService
   */
  public OClassImpl(ONodeID aURI, Ontology ontology,
          OntologyService ontologyService) {
    super(aURI, ontology, ontologyService);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#addSubClass(gate.creole.ontology.OClass)
   */
  public void addSubClass(OClass subClass) {
    // lets first check if the current class is a subclass of the
    // subClass. If so,
    // we don't allow this.
    if(this.equals(subClass)) {
      Utils
              .warning("addSubClass(subClass) : The super and sub classes are same.");
      return;
    }

    if(this.isSubClassOf(subClass, OConstants.Closure.TRANSITIVE_CLOSURE)) {
      Utils.warning(subClass.getONodeID().toString() + " is a super class of "
              + this.getONodeID().toTurtle());
      return;
    }

    if(this.isSuperClassOf(subClass, OConstants.Closure.DIRECT_CLOSURE)) {
      Utils.warning(subClass.getONodeID().toString()
              + " is already a sub class of " + this.getONodeID().toTurtle());
      return;
    }

    ontologyService.addSubClass(this.nodeId.toString(), subClass.getONodeID()
            .toString());
    ontology.fireResourceRelationChanged(this, subClass, OConstants.SUB_CLASS_ADDED_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#removeSubClass(gate.creole.ontology.OClass)
   */
  public void removeSubClass(OClass subClass) {

    if(this.equals(subClass)) {
      Utils
              .warning("addSubClass(subClass) : The super and sub classes are same.");
      return;
    }

    if(!subClass.isSubClassOf(this, OConstants.Closure.DIRECT_CLOSURE)) {
      Utils.warning(subClass.getONodeID().toString()
              + " is not a direct subclass of " + this.getONodeID().toTurtle());
      return;
    }

    ontologyService.removeSubClass(this.nodeId.toString(), subClass
            .getONodeID().toString());
    ontology.fireResourceRelationChanged(this, subClass, OConstants.SUB_CLASS_REMOVED_EVENT);  
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#getSubClassesOld(byte)
   */
  public Set<OClass> getSubClasses(byte closure) {
    //throw new UnsupportedOperationException("Method with these parameters not supported any more");
    gate.creole.ontology.OConstants.Closure theClosure =
        (closure == OConstants.DIRECT_CLOSURE) ?
      Closure.DIRECT_CLOSURE : Closure.TRANSITIVE_CLOSURE;
    return getSubClasses(theClosure);
  }

  public Set<OClass> getSubClasses(Closure closure) {
    return ontologyService.getSubClasses(this.nodeId,closure);
  }

  public ClosableIterator<OClass> getSubClassesIterator(Closure closure) {
    return ontologyService.getSubClassesIterator(this.nodeId, closure);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#getSuperClasses(byte)
   */
  public Set<OClass> getSuperClasses(byte closure) {
    //throw new UnsupportedOperationException("Method with these parameters not supported any more");
    Closure theClosure = closure == OConstants.DIRECT_CLOSURE ?
      Closure.DIRECT_CLOSURE : Closure.TRANSITIVE_CLOSURE;
    return getSuperClasses(theClosure);
  }

  public Set<OClass> getSuperClasses(Closure closure) {
    ResourceInfo[] superClasses = ontologyService.getSuperClasses(
            this.nodeId.toString(), closure);
    Set<OClass> oClasses = new HashSet<OClass>();
    for(int i = 0; i < superClasses.length; i++) {
      oClasses.add(Utils.createOClass(this.ontology,
              this.ontologyService, superClasses[i].getUri(), superClasses[i]
                      .getClassType()));
    }
    return oClasses;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#isSuperClassOf(gate.creole.ontology.OClass,
   *      byte)
   */
  @Deprecated
  public boolean isSuperClassOf(OClass aClass, byte closure) {
    //throw new UnsupportedOperationException("Method with these parameters not supported any more");
    Closure theClosure = Closure.TRANSITIVE_CLOSURE;
    if(closure == OConstants.DIRECT_CLOSURE) {
      theClosure = Closure.DIRECT_CLOSURE;
    }
    return ontologyService.isSuperClassOf(this.nodeId.toString(), aClass
            .getONodeID().toString(), theClosure);
  }

  public boolean isSuperClassOf(OClass aClass, Closure closure) {
    return ontologyService.isSuperClassOf(this.nodeId.toString(), aClass
            .getONodeID().toString(), closure);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#isSubClassOf(gate.creole.ontology.OClass,
   *      byte)
   */
  @Deprecated
  public boolean isSubClassOf(OClass aClass, byte closure) {
    //throw new UnsupportedOperationException("Method with these parameters not supported any more");
    Closure theClosure = Closure.TRANSITIVE_CLOSURE;
    if(closure == OConstants.DIRECT_CLOSURE) {
      theClosure = Closure.DIRECT_CLOSURE;
    }
    return ontologyService.isSubClassOf(aClass.getONodeID().toString(),
            this.nodeId.toString(), theClosure);
  }

  public boolean isSubClassOf(OClass aClass, OConstants.Closure closure) {
      return ontologyService.isSubClassOf(aClass.getONodeID().toString(),
            this.nodeId.toString(), closure);

  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#isTopClass()
   */
  public boolean isTopClass() {
    return ontologyService.isTopClass(this.nodeId.toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#setSameClassAs(gate.creole.ontology.OClass)
   */
  public void setEquivalentClassAs(OClass theClass) {
    // lets first check if the current class is a subclass of the
    // subClass. If so,
    // we don't allow this.
    if(this.equals(theClass)) {
      Utils
              .warning("setEquivalentClassAs(theClass) : Both the source and the argument classes refer to the same instance of class");
      return;
    }

    ontologyService.setEquivalentClassAs(this.nodeId, theClass.getONodeID());
    ontology.fireResourceRelationChanged(this, theClass, OConstants.EQUIVALENT_CLASS_EVENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#getSameClasses()
   */
  public Set<OClass> getEquivalentClasses() {
    ResourceInfo[] eqClasses = ontologyService.getEquivalentClasses(
            this.nodeId.toString());
    Set<OClass> oClasses = new HashSet<OClass>();
    for(int i = 0; i < eqClasses.length; i++) {
      oClasses.add(Utils.createOClass(this.ontology,
              this.ontologyService, eqClasses[i].getUri(), eqClasses[i].getClassType()));
    }
    return oClasses;
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.OClass#isSameClassAs(gate.creole.ontology.OClass)
   */
  public boolean isEquivalentClassAs(OClass aClass) {
    return ontologyService.isEquivalentClassAs(this.nodeId,
            aClass.getONodeID());
  }

  /**
   * Gets the super classes, and returns them in an array list where on
   * each index there is a collection of the super classes at distance -
   * the index.
   * @return
   */
  public ArrayList<Set<OClass>> getSuperClassesVSDistance() {
    ArrayList<Set<OClass>> result = new ArrayList<Set<OClass>>();
    Set<OClass> set;
    int level = 0;
    OClass c;
    Set<OClass> levelSet = new HashSet<OClass>();
    levelSet.add(this);
    boolean rollon = (0 < ontologyService.getSuperClasses(this.nodeId.toTurtle(),
            OConstants.Closure.DIRECT_CLOSURE).length);
    while(rollon) {
      set = new HashSet<OClass>();
      Iterator<OClass> li = levelSet.iterator();
      while(li.hasNext()) {
        c = li.next();
        set.addAll(c.getSuperClasses(OConstants.Closure.DIRECT_CLOSURE));
      }
      if(0 < set.size()) {
        result.add(level++, set);
      }
      levelSet = set;
      rollon = 0 < levelSet.size();
    }
    return result;
  }

  /**
   * Gets the sub classes, and returns them in an array list where on
   * each index there is a collection of the sub classes at distance -
   * the index.
   * @return
   */
  public ArrayList<Set<OClass>> getSubClassesVsDistance() {
    ArrayList<Set<OClass>> result = new ArrayList<Set<OClass>>();
    Set<OClass> set;
    int level = 0;
    OClass c;
    Set<OClass> levelSet = new HashSet<OClass>();
    levelSet.add(this);
    // TODO: this really just checks for existence and should be
    // done with a ask query .. something like hasSubClasses ..
    boolean rollon = (0 < ontologyService.getSubClasses(nodeId,
            OConstants.Closure.DIRECT_CLOSURE).size());
    while(rollon) {
      set = new HashSet<OClass>();
      Iterator<OClass> li = levelSet.iterator();
      while(li.hasNext()) {
        c = li.next();
        set.addAll(c.getSubClasses(OConstants.Closure.DIRECT_CLOSURE));
      }
      if(0 < set.size()) {
        result.add(level++, set);
      }
      levelSet = set;
      rollon = 0 < levelSet.size();
    }
    return result;
  }



}
