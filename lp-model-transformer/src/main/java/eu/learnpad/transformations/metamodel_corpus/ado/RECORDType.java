/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>RECORD Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.RECORDType#getROW <em>ROW</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.RECORDType#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getRECORDType()
 * @model extendedMetaData="name='RECORD_._type' kind='elementOnly'"
 * @generated
 */
public interface RECORDType extends EObject {
	/**
	 * Returns the value of the '<em><b>ROW</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.ROWType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ROW</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ROW</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getRECORDType_ROW()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ROW' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ROWType> getROW();

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getRECORDType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.RECORDType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // RECORDType
