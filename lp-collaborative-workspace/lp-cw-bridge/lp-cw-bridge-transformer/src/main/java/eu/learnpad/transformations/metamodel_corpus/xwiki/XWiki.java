/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XWiki</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getVersion <em>Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getSyntaxes <em>Syntaxes</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getXWiki()
 * @model extendedMetaData="name='XWiki' kind='elementOnly'"
 * @generated
 */
public interface XWiki extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getXWiki_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='version' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Syntaxes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Syntaxes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Syntaxes</em>' containment reference.
	 * @see #setSyntaxes(Syntaxes)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getXWiki_Syntaxes()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='syntaxes' namespace='##targetNamespace'"
	 * @generated
	 */
	Syntaxes getSyntaxes();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getSyntaxes <em>Syntaxes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Syntaxes</em>' containment reference.
	 * @see #getSyntaxes()
	 * @generated
	 */
	void setSyntaxes(Syntaxes value);

} // XWiki
