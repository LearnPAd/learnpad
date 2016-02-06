/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>History Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType#getHistorySummary <em>History Summary</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistoryType()
 * @model extendedMetaData="name='history_._type' kind='elementOnly'"
 * @generated
 */
public interface HistoryType extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>History Summary</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>History Summary</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>History Summary</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistoryType_HistorySummary()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='historySummary' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<HistorySummary> getHistorySummary();

} // HistoryType
