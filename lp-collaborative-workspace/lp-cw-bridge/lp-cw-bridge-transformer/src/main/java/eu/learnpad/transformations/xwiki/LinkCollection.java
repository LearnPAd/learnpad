/**
 */
package eu.learnpad.transformations.xwiki;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.xwiki.LinkCollection#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.xwiki.XwikiPackage#getLinkCollection()
 * @model extendedMetaData="name='LinkCollection' kind='elementOnly'"
 * @generated
 */
public interface LinkCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>Link</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.xwiki.Link}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' containment reference list.
	 * @see eu.learnpad.transformations.xwiki.XwikiPackage#getLinkCollection_Link()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Link> getLink();

} // LinkCollection
