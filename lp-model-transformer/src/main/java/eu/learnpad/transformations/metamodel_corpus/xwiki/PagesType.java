/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pages Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType#getPageSummary <em>Page Summary</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPagesType()
 * @model extendedMetaData="name='pages_._type' kind='elementOnly'"
 * @generated
 */
public interface PagesType extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Page Summary</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Summary</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Summary</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPagesType_PageSummary()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='pageSummary' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PageSummary> getPageSummary();

} // PagesType
