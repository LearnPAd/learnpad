/**
 * 
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.OConstants;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.OResource;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.RDFProperty;
import gate.creole.ontology.SomeValuesFromRestriction;

/**
 * @author niraj
 * 
 */
public class SomeValuesFromRestrictionImpl extends OClassImpl implements
                                                             SomeValuesFromRestriction {

  /**
   * @param aURI
   * @param ontology
   * @param repositoryID
   * @param owlimPort
   */
  
  public SomeValuesFromRestrictionImpl(ONodeID aURI, Ontology ontology,
          OntologyService owlimPort) {
    super(aURI, ontology, owlimPort);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.SomeValuesFromRestriction#getHasValue()
   */
  public OResource getHasValue() {
      ResourceInfo resource = ontologyService.getRestrictionValue(
              this.nodeId.toString(), OConstants.SOME_VALUES_FROM_RESTRICTION);

      if(resource.getClassType() == OConstants.INSTANCE)
        return Utils.createOInstance(this.ontology,
                this.ontologyService, resource.getUri());

      return Utils.createOClass(this.ontology, this.ontologyService,
              resource.getUri(), resource.getClassType());
  }

  /**
   * Sets the resource as a restricted value.
   * 
   * @param resource
   */
  public void setHasValue(OResource resource) {
    ontologyService.setRestrictionValue(this.nodeId,
      ontology.OURI_OWL_SOMEVALUESFROM, resource.getONodeID());
  }
  
  
  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.Restriction#getOnPropertyValue()
   */
  public RDFProperty getOnPropertyValue() {
      Property property = ontologyService.getOnPropertyValue(this.nodeId
              .toString());
      return Utils.createOProperty(ontology, ontologyService, property
              .getUri(), property.getType());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.Restriction#setOnPropertyValue(gate.creole.ontology.RDFProperty)
   */
  public void setOnPropertyValue(RDFProperty property) {
      ontologyService.setOnPropertyValue(this.nodeId, property.getOURI());
      ontology.fireResourceRelationChanged(this, property, OConstants.RESTRICTION_ON_PROPERTY_VALUE_CHANGED);
  }

}
