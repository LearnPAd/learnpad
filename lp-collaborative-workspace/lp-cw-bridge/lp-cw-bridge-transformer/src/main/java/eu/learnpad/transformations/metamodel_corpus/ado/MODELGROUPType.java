/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>MODELGROUP Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getMODELREFERENCE <em>MODELREFERENCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getMODELGROUP <em>MODELGROUP</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELGROUPType()
 * @model extendedMetaData="name='MODELGROUP_._type' kind='elementOnly'"
 * @generated
 */
public interface MODELGROUPType extends EObject {
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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELGROUPType_MODELREFERENCE()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MODELREFERENCE' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MODELREFERENCEType> getMODELREFERENCE();

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELGROUPType_MODELGROUP()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='MODELGROUP' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MODELGROUPType> getMODELGROUP();

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getMODELGROUPType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // MODELGROUPType
