/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wikis Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType#getWiki <em>Wiki</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getWikisType()
 * @model extendedMetaData="name='wikis_._type' kind='elementOnly'"
 * @generated
 */
public interface WikisType extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Wiki</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wiki</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wiki</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getWikisType_Wiki()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='wiki' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Wiki> getWiki();

} // WikisType
