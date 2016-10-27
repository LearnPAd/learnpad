/**
 * 
 */
package gate.creole.ontology.impl;

import org.openrdf.model.vocabulary.OWL;

import gate.creole.ontology.*;
import gate.util.GateRuntimeException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author niraj
 * 
 */
public class Utils {


  /**
   * Given required parameters, this method, based on the provided type,
   * returns an appropriate object of a property.
   * 
   * @param repositoryID
   * @param ontology
   * @param ontologyService
   * @param uri
   * @param type
   * @return
   */
  public static RDFProperty createOProperty(
          Ontology ontology, OntologyService ontologyService, String uri, byte type) {
    // TODO: get rid of getOResourceFromMap here!
    // Test simply commenting out for now
    RDFProperty prop = null; // = (RDFProperty)ontology.getOResourceFromMap(uri);
    //if(prop != null) return prop;
    switch(type) {
      case OConstants.ANNOTATION_PROPERTY:
        prop = new AnnotationPropertyImpl(new OURIImpl(uri), ontology,
                ontologyService);
        break;
      case OConstants.RDF_PROPERTY:
        prop = new RDFPropertyImpl(new OURIImpl(uri), ontology,
                ontologyService);
        break;
      case OConstants.OBJECT_PROPERTY:
        prop = new ObjectPropertyImpl(new OURIImpl(uri), ontology,
                ontologyService);
        break;
      case OConstants.SYMMETRIC_PROPERTY:
        prop = new SymmetricPropertyImpl(new OURIImpl(uri), ontology,
                ontologyService);
        break;
      case OConstants.TRANSITIVE_PROPERTY:
        prop = new TransitivePropertyImpl(new OURIImpl(uri), ontology,
                ontologyService);
        break;
      case OConstants.DATATYPE_PROPERTY:
        prop = new DatatypePropertyImpl(new OURIImpl(uri), ontology,
                ontologyService);
        break;
    }
    // TODO: check if commenting out does any harm
    //ontology.addOResourceToMap(uri, prop);
    // TODO: check for prop still being null?
    return prop;
  }

  /**
   * Creates a new instance of Ontology Class
   * 
   * @param ontology
   * @param owlim
   * @param uri
   * @param classType 
   * @return
   */
  public static OClass createOClass(Ontology ontology,
          OntologyService owlim, String uri, byte classType) {
    OClass aClass = null; //(OClass)ontology.getOResourceFromMap(uri);
    
    switch(classType) {
      case OConstants.HAS_VALUE_RESTRICTION:
        aClass = new HasValueRestrictionImpl(new OBNodeIDImpl(uri), ontology,
                owlim);
        break;
      case OConstants.ALL_VALUES_FROM_RESTRICTION:
        aClass = new AllValuesFromRestrictionImpl(new OBNodeIDImpl(uri), ontology,
                owlim);
        break;
      case OConstants.SOME_VALUES_FROM_RESTRICTION:
        aClass = new SomeValuesFromRestrictionImpl(new OBNodeIDImpl(uri),
                ontology, owlim);
        break;
      case OConstants.CARDINALITY_RESTRICTION:
        aClass = new CardinalityRestrictionImpl(new OBNodeIDImpl(uri), ontology,
                owlim);
        break;
      case OConstants.MIN_CARDINALITY_RESTRICTION:
        aClass = new MinCardinalityRestrictionImpl(new OBNodeIDImpl(uri),
                ontology, owlim);
        break;
      case OConstants.MAX_CARDINALITY_RESTRICTION:
        aClass = new MaxCardinalityRestrictionImpl(new OBNodeIDImpl(uri),
                ontology, owlim);
        break;
      case OConstants.ANNONYMOUS_CLASS:
        aClass = new AnonymousClassImpl(new OBNodeIDImpl(uri),
            ontology, owlim);
        break;
      default:
        aClass = new OClassImpl(new OURIImpl(uri), ontology,
                owlim);
        break;
    }

    //ontology.addOResourceToMap(uri, aClass);
    return aClass;
  }

  public static String getRestrictionName(byte classType) {
    String className = "Unknown";
    switch(classType) {
      case OConstants.HAS_VALUE_RESTRICTION:
        className = OWL.HASVALUE.toString();
        break;
      case OConstants.ALL_VALUES_FROM_RESTRICTION:
        className = OWL.ALLVALUESFROM.toString();
        break;
      case OConstants.SOME_VALUES_FROM_RESTRICTION:
        className = OWL.SOMEVALUESFROM.toString();
        break;
      case OConstants.CARDINALITY_RESTRICTION:
        className = OWL.CARDINALITY.toString();
        break;
      case OConstants.MIN_CARDINALITY_RESTRICTION:
        className = OWL.MINCARDINALITY.toString();
        break;
      case OConstants.MAX_CARDINALITY_RESTRICTION:
        className = OWL.MINCARDINALITY.toString();
        break;
      case OConstants.ANNONYMOUS_CLASS:
        className = "Annonymous";
        break;
    }
    return className; 
  }

  public static String getRestrictionName(Restriction res) {
    String className = "Unknown";
    if(res instanceof HasValueRestriction) {
      className = OWL.HASVALUE.toString();
    } else if(res instanceof AllValuesFromRestriction) {
        className = OWL.ALLVALUESFROM.toString();
    } else if(res instanceof SomeValuesFromRestriction) {
        className = OWL.SOMEVALUESFROM.toString();
    } else if(res instanceof CardinalityRestriction) {
        className = OWL.CARDINALITY.toString();
    } else if(res instanceof MinCardinalityRestriction) {
        className = OWL.MINCARDINALITY.toString();
    } else if(res instanceof MaxCardinalityRestriction) {
        className = OWL.MAXCARDINALITY.toString();
    } else if(res instanceof AnonymousClassImpl) {
        className = "Annonymous";
    }
    return className; 
  }
  
  
  /**
   * Creates a new instance of Ontology Instance
   */
  public static OInstance createOInstance(Ontology ontology,
      OntologyService owlim, String uri) {
    OResource aResource = null; // = ontology.getOResourceFromMap(uri);

    OInstance anInstance = (OInstance)aResource;
    if(anInstance != null) return anInstance;
    anInstance = new OInstanceImpl(ontology.createOURI(uri), ontology, owlim);
    // ontology.addOResourceToMap(uri, anInstance);
    return anInstance;

  }


  public static boolean hasSystemNameSpace(String uri) {
    if(uri.startsWith("http://www.w3.org/2002/07/owl#"))
      return true;
    else if(uri.startsWith("http://www.w3.org/2001/XMLSchema#"))
      return true;
    else if(uri.startsWith("http://www.w3.org/2000/01/rdf-schema#"))
      return true;
    else if(uri.startsWith("http://www.w3.org/1999/02/22-rdf-syntax-ns#"))
      return true;
    else return false;
  }

  /**
   * Utility method that shows warning to the user.
   *
   * @param warningMsg - message to be displayed to the user
   */
  public static void warning(String warningMsg) {
    System.err.println("WARNING :" + warningMsg);
  }

  public static void warnDeprecation(String methodName) {
    String msg = "Method "+methodName+" is deprecated and should not be used any more!";
    if(gate.Utils.isLoggedOnce(msg)) {
      warning("Method "+methodName+" is deprecated and should not be used any more!");
    }
  }

  /**
   * Utility method that throws a GateRuntimeException to the user.
   *
   * @param warningMsg - message to be displayed to the user
   */
  public static void error(String errorMsg) {
    throw new GateRuntimeException("ERROR :" + errorMsg);
  }




}
