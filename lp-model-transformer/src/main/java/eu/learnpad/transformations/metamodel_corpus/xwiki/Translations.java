/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Translations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations#getTranslation <em>Translation</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations#getDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getTranslations()
 * @model extendedMetaData="name='Translations' kind='elementOnly'"
 * @generated
 */
public interface Translations extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Translation</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Translation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Translation</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getTranslations_Translation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='translation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Translation> getTranslation();

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getTranslations_Default()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='default'"
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

} // Translations
