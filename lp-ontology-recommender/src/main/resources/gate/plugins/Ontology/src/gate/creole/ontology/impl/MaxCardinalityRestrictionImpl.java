/**
 * 
 */
package gate.creole.ontology.impl;

import gate.creole.ontology.DataType;
import gate.creole.ontology.InvalidValueException;
import gate.creole.ontology.MaxCardinalityRestriction;
import gate.creole.ontology.OConstants;
import gate.creole.ontology.ONodeID;
import gate.creole.ontology.Ontology;
import gate.creole.ontology.RDFProperty;

/**
 * @author niraj
 * 
 */
public class MaxCardinalityRestrictionImpl extends OClassImpl implements
                                                             MaxCardinalityRestriction {

  /**
   * @param aURI
   * @param ontology
   * @param owlimPort
   */
  public MaxCardinalityRestrictionImpl(ONodeID aURI, Ontology ontology,
          OntologyService owlimPort) {
    super(aURI, ontology, owlimPort);
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.MaxCardinalityRestriction#getValue()
   */
  public String getValue() {
    PropertyValue pv = ontologyService.getPropertyValue(this.getONodeID()
            .toString(), OConstants.MAX_CARDINALITY_RESTRICTION);
    return pv.getValue();
  }

  public void setValue(String value, DataType datatype)
          throws InvalidValueException {
    if(!datatype.isValidValue(value)) {
      throw new InvalidValueException(value + 
          " is not valid for datatype " + datatype.getXmlSchemaURIString());
    }
    ontologyService.setPropertyValue(this.getONodeID().toString(),
            OConstants.MAX_CARDINALITY_RESTRICTION, value, datatype
                    .getXmlSchemaURIString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see gate.creole.ontology.CardinalityRestriction#getDataType()
   */
  public DataType getDataType() {
    PropertyValue pv = ontologyService.getPropertyValue(this.getONodeID()
            .toString(), OConstants.MAX_CARDINALITY_RESTRICTION);
    return OntologyUtilities.getDataType(pv.getDatatype());
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
    ontology.fireResourceRelationChanged(this, property,OConstants.RESTRICTION_ON_PROPERTY_VALUE_CHANGED);
  }
}
