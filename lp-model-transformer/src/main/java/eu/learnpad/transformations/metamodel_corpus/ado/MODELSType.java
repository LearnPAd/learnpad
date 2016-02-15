/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MODELS Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELSType#getMODEL <em>MODEL</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELSType()
 * @model extendedMetaData="name='MODELS_._type' kind='elementOnly'"
 * @generated
 */
public interface MODELSType extends EObject {
	/**
	 * Returns the value of the '<em><b>MODEL</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODEL</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODEL</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELSType_MODEL()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='MODEL' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MODELType> getMODEL();

} // MODELSType
