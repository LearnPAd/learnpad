/**
 * 
 */
package gate.creole.ontology.ocat;

import gate.FeatureMap;

/**
 * @author niraj
 */
public class Utils {

  /**
   * Given an Annotation this method gets the value of
   * gate.creole.ANNIEConstants.LOOKUP_CLASS_FEATURE_NAME feature.
   * 
   * @param annot
   * @return
   */
  public static String getClassFeatureValue(gate.Annotation annot,
    OntologyViewerOptions options) {
    String ontoClassName = options.getSelectedClassURIFeatureName();
    FeatureMap map = annot.getFeatures();

    String aName = (String)map.get(ontoClassName);
    if(aName == null) return null;
    int index = aName.lastIndexOf("#");
    if(index < 0) index = aName.lastIndexOf("/");
    if(index < 0) index = aName.lastIndexOf(":");
    if(index >= 0) {
      aName = aName.substring(index + 1, aName.length());
    }
    return aName;
  }

  /**
   * Given an Annotation this method gets the value of
   * gate.creole.ANNIEConstants.LOOKUP_INSTANCE_FEATURE_NAME feature.
   * 
   * @param annot
   * @return
   */
  public static String getInstanceFeatureValue(gate.Annotation annot,
    OntologyViewerOptions options) {
    String ontoInstanceName = options.getSelectedInstanceURIFeatureName();
    FeatureMap map = annot.getFeatures();

    String aName = (String)map.get(ontoInstanceName);
    if(aName == null) return null;
    int index = aName.lastIndexOf("#");
    if(index < 0) index = aName.lastIndexOf("/");
    if(index < 0) index = aName.lastIndexOf(":");
    if(index >= 0) {
      aName = aName.substring(index + 1, aName.length());
    }
    return aName;
  }

}
