/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CONNECTOR Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getFROM <em>FROM</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getTO <em>TO</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getGroup <em>Group</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getATTRIBUTE <em>ATTRIBUTE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getRECORD <em>RECORD</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getINTERREF <em>INTERREF</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getClass_ <em>Class</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType()
 * @model extendedMetaData="name='CONNECTOR_._type' kind='elementOnly'"
 * @generated
 */
public interface CONNECTORType extends EObject {
	/**
	 * Returns the value of the '<em><b>FROM</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>FROM</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>FROM</em>' containment reference.
	 * @see #setFROM(FROMType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_FROM()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='FROM' namespace='##targetNamespace'"
	 * @generated
	 */
	FROMType getFROM();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getFROM <em>FROM</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>FROM</em>' containment reference.
	 * @see #getFROM()
	 * @generated
	 */
	void setFROM(FROMType value);

	/**
	 * Returns the value of the '<em><b>TO</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TO</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TO</em>' containment reference.
	 * @see #setTO(TOType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_TO()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='TO' namespace='##targetNamespace'"
	 * @generated
	 */
	TOType getTO();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getTO <em>TO</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TO</em>' containment reference.
	 * @see #getTO()
	 * @generated
	 */
	void setTO(TOType value);

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:2'"
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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_ATTRIBUTE()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ATTRIBUTE' namespace='##targetNamespace' group='#group:2'"
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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_RECORD()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='RECORD' namespace='##targetNamespace' group='#group:2'"
	 * @generated
	 */
	EList<RECORDType> getRECORD();

	/**
	 * Returns the value of the '<em><b>INTERREF</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>INTERREF</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>INTERREF</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_INTERREF()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='INTERREF' namespace='##targetNamespace' group='#group:2'"
	 * @generated
	 */
	EList<INTERREFType> getINTERREF();

	/**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_Class()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='class'"
	 * @generated
	 */
	String getClass_();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getCONNECTORType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // CONNECTORType
