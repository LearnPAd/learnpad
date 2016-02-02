/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Objects Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType#getObjectSummary <em>Object Summary</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getObjectsType()
 * @model extendedMetaData="name='objects_._type' kind='elementOnly'"
 * @generated
 */
public interface ObjectsType extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Object Summary</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Summary</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Summary</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getObjectsType_ObjectSummary()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='objectSummary' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ObjectSummary> getObjectSummary();

} // ObjectsType
