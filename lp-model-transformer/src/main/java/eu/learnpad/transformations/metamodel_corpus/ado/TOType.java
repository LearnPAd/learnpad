/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TO Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.TOType#getClass_ <em>Class</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.TOType#getInstance <em>Instance</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getTOType()
 * @model extendedMetaData="name='TO_._type' kind='empty'"
 * @generated
 */
public interface TOType extends EObject {
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
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getTOType_Class()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='class'"
	 * @generated
	 */
	String getClass_();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.TOType#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(String value);

	/**
	 * Returns the value of the '<em><b>Instance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance</em>' attribute.
	 * @see #setInstance(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getTOType_Instance()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='instance'"
	 * @generated
	 */
	String getInstance();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.TOType#getInstance <em>Instance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance</em>' attribute.
	 * @see #getInstance()
	 * @generated
	 */
	void setInstance(String value);

} // TOType
