/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getLanguage <em>Language</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMajorVersion <em>Major Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMinorVersion <em>Minor Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreated <em>Created</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreator <em>Creator</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreatorName <em>Creator Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModified <em>Modified</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifier <em>Modifier</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifierName <em>Modifier Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getComment <em>Comment</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage()
 * @model extendedMetaData="name='Page' kind='elementOnly'"
 * @generated
 */
public interface Page extends PageSummary {
	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_Language()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='language' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Major Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Major Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Major Version</em>' attribute.
	 * @see #isSetMajorVersion()
	 * @see #unsetMajorVersion()
	 * @see #setMajorVersion(int)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_MajorVersion()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='majorVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	int getMajorVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMajorVersion <em>Major Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Major Version</em>' attribute.
	 * @see #isSetMajorVersion()
	 * @see #unsetMajorVersion()
	 * @see #getMajorVersion()
	 * @generated
	 */
	void setMajorVersion(int value);

	/**
	 * Unsets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMajorVersion <em>Major Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMajorVersion()
	 * @see #getMajorVersion()
	 * @see #setMajorVersion(int)
	 * @generated
	 */
	void unsetMajorVersion();

	/**
	 * Returns whether the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMajorVersion <em>Major Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Major Version</em>' attribute is set.
	 * @see #unsetMajorVersion()
	 * @see #getMajorVersion()
	 * @see #setMajorVersion(int)
	 * @generated
	 */
	boolean isSetMajorVersion();

	/**
	 * Returns the value of the '<em><b>Minor Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minor Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minor Version</em>' attribute.
	 * @see #isSetMinorVersion()
	 * @see #unsetMinorVersion()
	 * @see #setMinorVersion(int)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_MinorVersion()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='minorVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	int getMinorVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMinorVersion <em>Minor Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minor Version</em>' attribute.
	 * @see #isSetMinorVersion()
	 * @see #unsetMinorVersion()
	 * @see #getMinorVersion()
	 * @generated
	 */
	void setMinorVersion(int value);

	/**
	 * Unsets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMinorVersion <em>Minor Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMinorVersion()
	 * @see #getMinorVersion()
	 * @see #setMinorVersion(int)
	 * @generated
	 */
	void unsetMinorVersion();

	/**
	 * Returns whether the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMinorVersion <em>Minor Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Minor Version</em>' attribute is set.
	 * @see #unsetMinorVersion()
	 * @see #getMinorVersion()
	 * @see #setMinorVersion(int)
	 * @generated
	 */
	boolean isSetMinorVersion();

	/**
	 * Returns the value of the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Created</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Created</em>' attribute.
	 * @see #setCreated(XMLGregorianCalendar)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_Created()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime" required="true"
	 *        extendedMetaData="kind='element' name='created' namespace='##targetNamespace'"
	 * @generated
	 */
	XMLGregorianCalendar getCreated();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreated <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created</em>' attribute.
	 * @see #getCreated()
	 * @generated
	 */
	void setCreated(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Creator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creator</em>' attribute.
	 * @see #setCreator(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_Creator()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='creator' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCreator();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreator <em>Creator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creator</em>' attribute.
	 * @see #getCreator()
	 * @generated
	 */
	void setCreator(String value);

	/**
	 * Returns the value of the '<em><b>Creator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creator Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creator Name</em>' attribute.
	 * @see #setCreatorName(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_CreatorName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='creatorName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getCreatorName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreatorName <em>Creator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creator Name</em>' attribute.
	 * @see #getCreatorName()
	 * @generated
	 */
	void setCreatorName(String value);

	/**
	 * Returns the value of the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified</em>' attribute.
	 * @see #setModified(XMLGregorianCalendar)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_Modified()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime" required="true"
	 *        extendedMetaData="kind='element' name='modified' namespace='##targetNamespace'"
	 * @generated
	 */
	XMLGregorianCalendar getModified();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModified <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified</em>' attribute.
	 * @see #getModified()
	 * @generated
	 */
	void setModified(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Modifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifier</em>' attribute.
	 * @see #setModifier(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_Modifier()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='modifier' namespace='##targetNamespace'"
	 * @generated
	 */
	String getModifier();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifier <em>Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modifier</em>' attribute.
	 * @see #getModifier()
	 * @generated
	 */
	void setModifier(String value);

	/**
	 * Returns the value of the '<em><b>Modifier Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modifier Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modifier Name</em>' attribute.
	 * @see #setModifierName(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_ModifierName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='modifierName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getModifierName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifierName <em>Modifier Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modifier Name</em>' attribute.
	 * @see #getModifierName()
	 * @generated
	 */
	void setModifierName(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_Comment()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='comment' namespace='##targetNamespace'"
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getPage_Content()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='content' namespace='##targetNamespace'"
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

} // Page
