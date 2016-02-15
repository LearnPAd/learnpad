/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MODELATTRIBUTES Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getGroup <em>Group</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getATTRIBUTE <em>ATTRIBUTE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getRECORD <em>RECORD</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELATTRIBUTESType()
 * @model extendedMetaData="name='MODELATTRIBUTES_._type' kind='elementOnly'"
 * @generated
 */
public interface MODELATTRIBUTESType extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELATTRIBUTESType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>ATTRIBUTE</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRIBUTE</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRIBUTE</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELATTRIBUTESType_ATTRIBUTE()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ATTRIBUTE' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ATTRIBUTEType> getATTRIBUTE();

	/**
	 * Returns the value of the '<em><b>RECORD</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.RECORDType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RECORD</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RECORD</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELATTRIBUTESType_RECORD()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='RECORD' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<RECORDType> getRECORD();

} // MODELATTRIBUTESType
