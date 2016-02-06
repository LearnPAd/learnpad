/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>History Summary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getPageId <em>Page Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getSpace <em>Space</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getVersion <em>Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMajorVersion <em>Major Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMinorVersion <em>Minor Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModified <em>Modified</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifier <em>Modifier</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifierName <em>Modifier Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getLanguage <em>Language</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary()
 * @model extendedMetaData="name='HistorySummary' kind='elementOnly'"
 * @generated
 */
public interface HistorySummary extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Id</em>' attribute.
	 * @see #setPageId(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_PageId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='pageId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPageId();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getPageId <em>Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Id</em>' attribute.
	 * @see #getPageId()
	 * @generated
	 */
	void setPageId(String value);

	/**
	 * Returns the value of the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wiki</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wiki</em>' attribute.
	 * @see #setWiki(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Wiki()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='wiki' namespace='##targetNamespace'"
	 * @generated
	 */
	String getWiki();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getWiki <em>Wiki</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wiki</em>' attribute.
	 * @see #getWiki()
	 * @generated
	 */
	void setWiki(String value);

	/**
	 * Returns the value of the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space</em>' attribute.
	 * @see #setSpace(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Space()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='space' namespace='##targetNamespace'"
	 * @generated
	 */
	String getSpace();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getSpace <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Space</em>' attribute.
	 * @see #getSpace()
	 * @generated
	 */
	void setSpace(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='version' namespace='##targetNamespace'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_MajorVersion()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='majorVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	int getMajorVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMajorVersion <em>Major Version</em>}' attribute.
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
	 * Unsets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMajorVersion <em>Major Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMajorVersion()
	 * @see #getMajorVersion()
	 * @see #setMajorVersion(int)
	 * @generated
	 */
	void unsetMajorVersion();

	/**
	 * Returns whether the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMajorVersion <em>Major Version</em>}' attribute is set.
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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_MinorVersion()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='minorVersion' namespace='##targetNamespace'"
	 * @generated
	 */
	int getMinorVersion();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMinorVersion <em>Minor Version</em>}' attribute.
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
	 * Unsets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMinorVersion <em>Minor Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMinorVersion()
	 * @see #getMinorVersion()
	 * @see #setMinorVersion(int)
	 * @generated
	 */
	void unsetMinorVersion();

	/**
	 * Returns whether the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMinorVersion <em>Minor Version</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified</em>' attribute.
	 * @see #setModified(XMLGregorianCalendar)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Modified()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime" required="true"
	 *        extendedMetaData="kind='element' name='modified' namespace='##targetNamespace'"
	 * @generated
	 */
	XMLGregorianCalendar getModified();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModified <em>Modified</em>}' attribute.
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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Modifier()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='modifier' namespace='##targetNamespace'"
	 * @generated
	 */
	String getModifier();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifier <em>Modifier</em>}' attribute.
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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_ModifierName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='modifierName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getModifierName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifierName <em>Modifier Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modifier Name</em>' attribute.
	 * @see #getModifierName()
	 * @generated
	 */
	void setModifierName(String value);

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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Language()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='language' namespace='##targetNamespace'"
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getHistorySummary_Comment()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='comment' namespace='##targetNamespace'"
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

} // HistorySummary
