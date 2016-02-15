/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getPageId <em>Page Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthor <em>Author</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthorName <em>Author Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getDate <em>Date</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getHighlight <em>Highlight</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getText <em>Text</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getReplyTo <em>Reply To</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment()
 * @model extendedMetaData="name='Comment' kind='elementOnly'"
 * @generated
 */
public interface Comment extends LinkCollection {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #isSetId()
	 * @see #unsetId()
	 * @see #setId(int)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_Id()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int" required="true"
	 *        extendedMetaData="kind='element' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #isSetId()
	 * @see #unsetId()
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Unsets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetId()
	 * @see #getId()
	 * @see #setId(int)
	 * @generated
	 */
	void unsetId();

	/**
	 * Returns whether the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getId <em>Id</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Id</em>' attribute is set.
	 * @see #unsetId()
	 * @see #getId()
	 * @see #setId(int)
	 * @generated
	 */
	boolean isSetId();

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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_PageId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='pageId' namespace='##targetNamespace'"
	 * @generated
	 */
	String getPageId();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getPageId <em>Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Id</em>' attribute.
	 * @see #getPageId()
	 * @generated
	 */
	void setPageId(String value);

	/**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Author</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_Author()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='author' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAuthor();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(String value);

	/**
	 * Returns the value of the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Author Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Author Name</em>' attribute.
	 * @see #setAuthorName(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_AuthorName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='authorName' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAuthorName();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthorName <em>Author Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author Name</em>' attribute.
	 * @see #getAuthorName()
	 * @generated
	 */
	void setAuthorName(String value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(XMLGregorianCalendar)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_Date()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.DateTime" required="true"
	 *        extendedMetaData="kind='element' name='date' namespace='##targetNamespace'"
	 * @generated
	 */
	XMLGregorianCalendar getDate();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(XMLGregorianCalendar value);

	/**
	 * Returns the value of the '<em><b>Highlight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Highlight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Highlight</em>' attribute.
	 * @see #setHighlight(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_Highlight()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='highlight' namespace='##targetNamespace'"
	 * @generated
	 */
	String getHighlight();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getHighlight <em>Highlight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Highlight</em>' attribute.
	 * @see #getHighlight()
	 * @generated
	 */
	void setHighlight(String value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_Text()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace'"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Reply To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reply To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reply To</em>' attribute.
	 * @see #isSetReplyTo()
	 * @see #unsetReplyTo()
	 * @see #setReplyTo(Integer)
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getComment_ReplyTo()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.IntObject" required="true"
	 *        extendedMetaData="kind='element' name='replyTo' namespace='##targetNamespace'"
	 * @generated
	 */
	Integer getReplyTo();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getReplyTo <em>Reply To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reply To</em>' attribute.
	 * @see #isSetReplyTo()
	 * @see #unsetReplyTo()
	 * @see #getReplyTo()
	 * @generated
	 */
	void setReplyTo(Integer value);

	/**
	 * Unsets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getReplyTo <em>Reply To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetReplyTo()
	 * @see #getReplyTo()
	 * @see #setReplyTo(Integer)
	 * @generated
	 */
	void unsetReplyTo();

	/**
	 * Returns whether the value of the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getReplyTo <em>Reply To</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Reply To</em>' attribute is set.
	 * @see #unsetReplyTo()
	 * @see #getReplyTo()
	 * @see #setReplyTo(Integer)
	 * @generated
	 */
	boolean isSetReplyTo();

} // Comment
