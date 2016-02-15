/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHref <em>Href</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHrefLang <em>Href Lang</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getRel <em>Rel</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getLink()
 * @model extendedMetaData="name='Link' kind='empty'"
 * @generated
 */
public interface Link extends EObject {
	/**
	 * Returns the value of the '<em><b>Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Href</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Href</em>' attribute.
	 * @see #setHref(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getLink_Href()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='href'"
	 * @generated
	 */
	String getHref();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHref <em>Href</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Href</em>' attribute.
	 * @see #getHref()
	 * @generated
	 */
	void setHref(String value);

	/**
	 * Returns the value of the '<em><b>Href Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Href Lang</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Href Lang</em>' attribute.
	 * @see #setHrefLang(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getLink_HrefLang()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='hrefLang'"
	 * @generated
	 */
	String getHrefLang();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHrefLang <em>Href Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Href Lang</em>' attribute.
	 * @see #getHrefLang()
	 * @generated
	 */
	void setHrefLang(String value);

	/**
	 * Returns the value of the '<em><b>Rel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rel</em>' attribute.
	 * @see #setRel(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getLink_Rel()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='rel'"
	 * @generated
	 */
	String getRel();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getRel <em>Rel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rel</em>' attribute.
	 * @see #getRel()
	 * @generated
	 */
	void setRel(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getLink_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // Link
