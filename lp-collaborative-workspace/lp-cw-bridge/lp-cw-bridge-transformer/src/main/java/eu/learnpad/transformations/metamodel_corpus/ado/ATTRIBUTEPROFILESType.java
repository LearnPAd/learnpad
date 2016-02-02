/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ATTRIBUTEPROFILES Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRPROFDIR <em>ATTRPROFDIR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getATTRIBUTEPROFILESType()
 * @model extendedMetaData="name='ATTRIBUTEPROFILES_._type' kind='elementOnly'"
 * @generated
 */
public interface ATTRIBUTEPROFILESType extends EObject {
	/**
	 * Returns the value of the '<em><b>ATTRPROFDIR</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRPROFDIR</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRPROFDIR</em>' containment reference.
	 * @see #setATTRPROFDIR(ATTRPROFDIRType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getATTRIBUTEPROFILESType_ATTRPROFDIR()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ATTRPROFDIR' namespace='##targetNamespace'"
	 * @generated
	 */
	ATTRPROFDIRType getATTRPROFDIR();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRPROFDIR <em>ATTRPROFDIR</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATTRPROFDIR</em>' containment reference.
	 * @see #getATTRPROFDIR()
	 * @generated
	 */
	void setATTRPROFDIR(ATTRPROFDIRType value);

	/**
	 * Returns the value of the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRIBUTEPROFILE</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRIBUTEPROFILE</em>' containment reference.
	 * @see #setATTRIBUTEPROFILE(ATTRIBUTEPROFILEType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getATTRIBUTEPROFILESType_ATTRIBUTEPROFILE()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ATTRIBUTEPROFILE' namespace='##targetNamespace'"
	 * @generated
	 */
	ATTRIBUTEPROFILEType getATTRIBUTEPROFILE();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATTRIBUTEPROFILE</em>' containment reference.
	 * @see #getATTRIBUTEPROFILE()
	 * @generated
	 */
	void setATTRIBUTEPROFILE(ATTRIBUTEPROFILEType value);

} // ATTRIBUTEPROFILESType
