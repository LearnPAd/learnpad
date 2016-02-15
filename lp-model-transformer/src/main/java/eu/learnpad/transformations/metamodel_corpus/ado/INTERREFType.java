/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>INTERREF Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType#getIREF <em>IREF</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getINTERREFType()
 * @model extendedMetaData="name='INTERREF_._type' kind='elementOnly'"
 * @generated
 */
public interface INTERREFType extends EObject {
	/**
	 * Returns the value of the '<em><b>IREF</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IREF</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IREF</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getINTERREFType_IREF()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='IREF' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<IREFType> getIREF();

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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getINTERREFType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // INTERREFType
