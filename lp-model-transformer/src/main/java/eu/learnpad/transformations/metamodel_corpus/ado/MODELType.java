/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MODEL Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getINSTANCE <em>INSTANCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getCONNECTOR <em>CONNECTOR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getApplib <em>Applib</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getLibtype <em>Libtype</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getModeltype <em>Modeltype</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType()
 * @model extendedMetaData="name='MODEL_._type' kind='elementOnly'"
 * @generated
 */
public interface MODELType extends EObject {
	/**
	 * Returns the value of the '<em><b>MODELATTRIBUTES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELATTRIBUTES</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELATTRIBUTES</em>' containment reference.
	 * @see #setMODELATTRIBUTES(MODELATTRIBUTESType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_MODELATTRIBUTES()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='MODELATTRIBUTES' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELATTRIBUTESType getMODELATTRIBUTES();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELATTRIBUTES</em>' containment reference.
	 * @see #getMODELATTRIBUTES()
	 * @generated
	 */
	void setMODELATTRIBUTES(MODELATTRIBUTESType value);

	/**
	 * Returns the value of the '<em><b>INSTANCE</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>INSTANCE</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>INSTANCE</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_INSTANCE()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='INSTANCE' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<INSTANCEType> getINSTANCE();

	/**
	 * Returns the value of the '<em><b>CONNECTOR</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CONNECTOR</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CONNECTOR</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_CONNECTOR()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='CONNECTOR' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<CONNECTORType> getCONNECTOR();

	/**
	 * Returns the value of the '<em><b>Applib</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applib</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applib</em>' attribute.
	 * @see #setApplib(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_Applib()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='applib'"
	 * @generated
	 */
	String getApplib();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getApplib <em>Applib</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applib</em>' attribute.
	 * @see #getApplib()
	 * @generated
	 */
	void setApplib(String value);

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Libtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Libtype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Libtype</em>' attribute.
	 * @see #setLibtype(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_Libtype()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='libtype'"
	 * @generated
	 */
	String getLibtype();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getLibtype <em>Libtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Libtype</em>' attribute.
	 * @see #getLibtype()
	 * @generated
	 */
	void setLibtype(String value);

	/**
	 * Returns the value of the '<em><b>Modeltype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modeltype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modeltype</em>' attribute.
	 * @see #setModeltype(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_Modeltype()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='modeltype'"
	 * @generated
	 */
	String getModeltype();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getModeltype <em>Modeltype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modeltype</em>' attribute.
	 * @see #getModeltype()
	 * @generated
	 */
	void setModeltype(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELType_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // MODELType
