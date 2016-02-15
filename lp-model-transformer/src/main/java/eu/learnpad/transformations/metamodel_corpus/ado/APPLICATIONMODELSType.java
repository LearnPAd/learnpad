/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>APPLICATIONMODELS Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType#getAPPLICATIONMODEL <em>APPLICATIONMODEL</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getAPPLICATIONMODELSType()
 * @model extendedMetaData="name='APPLICATIONMODELS_._type' kind='elementOnly'"
 * @generated
 */
public interface APPLICATIONMODELSType extends EObject {
	/**
	 * Returns the value of the '<em><b>APPLICATIONMODEL</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>APPLICATIONMODEL</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>APPLICATIONMODEL</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getAPPLICATIONMODELSType_APPLICATIONMODEL()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='APPLICATIONMODEL' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<APPLICATIONMODELType> getAPPLICATIONMODEL();

} // APPLICATIONMODELSType
