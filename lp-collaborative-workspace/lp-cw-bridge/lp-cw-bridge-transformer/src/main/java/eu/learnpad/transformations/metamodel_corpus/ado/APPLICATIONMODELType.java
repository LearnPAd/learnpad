/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>APPLICATIONMODEL Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getMODELREFERENCE <em>MODELREFERENCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getApplib <em>Applib</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getAPPLICATIONMODELType()
 * @model extendedMetaData="name='APPLICATIONMODEL_._type' kind='elementOnly'"
 * @generated
 */
public interface APPLICATIONMODELType extends EObject {
	/**
	 * Returns the value of the '<em><b>MODELREFERENCE</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELREFERENCE</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELREFERENCE</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getAPPLICATIONMODELType_MODELREFERENCE()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='MODELREFERENCE' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MODELREFERENCEType> getMODELREFERENCE();

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getAPPLICATIONMODELType_Applib()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='applib'"
	 * @generated
	 */
	String getApplib();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getApplib <em>Applib</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Applib</em>' attribute.
	 * @see #getApplib()
	 * @generated
	 */
	void setApplib(String value);

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getAPPLICATIONMODELType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // APPLICATIONMODELType
