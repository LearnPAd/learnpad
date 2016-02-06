/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MODELGROUPS Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType#getMODELGROUP <em>MODELGROUP</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELGROUPSType()
 * @model extendedMetaData="name='MODELGROUPS_._type' kind='elementOnly'"
 * @generated
 */
public interface MODELGROUPSType extends EObject {
	/**
	 * Returns the value of the '<em><b>MODELGROUP</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELGROUP</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELGROUP</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELGROUPSType_MODELGROUP()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='MODELGROUP' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MODELGROUPType> getMODELGROUP();

} // MODELGROUPSType
