/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.Comment;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getPageId <em>Page Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getAuthorName <em>Author Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getDate <em>Date</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getHighlight <em>Highlight</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getText <em>Text</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl#getReplyTo <em>Reply To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CommentImpl extends LinkCollectionImpl implements Comment {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * This is true if the Id attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean idESet;

	/**
	 * The default value of the '{@link #getPageId() <em>Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageId()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageId() <em>Page Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageId()
	 * @generated
	 * @ordered
	 */
	protected String pageId = PAGE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected String author = AUTHOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getAuthorName() <em>Author Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorName()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthorName() <em>Author Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorName()
	 * @generated
	 * @ordered
	 */
	protected String authorName = AUTHOR_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final XMLGregorianCalendar DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected XMLGregorianCalendar date = DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getHighlight() <em>Highlight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHighlight()
	 * @generated
	 * @ordered
	 */
	protected static final String HIGHLIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHighlight() <em>Highlight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHighlight()
	 * @generated
	 * @ordered
	 */
	protected String highlight = HIGHLIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected String text = TEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getReplyTo() <em>Reply To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReplyTo()
	 * @generated
	 * @ordered
	 */
	protected static final Integer REPLY_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReplyTo() <em>Reply To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReplyTo()
	 * @generated
	 * @ordered
	 */
	protected Integer replyTo = REPLY_TO_EDEFAULT;

	/**
	 * This is true if the Reply To attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean replyToESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.COMMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		boolean oldIdESet = idESet;
		idESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__ID, oldId, id, !oldIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetId() {
		int oldId = id;
		boolean oldIdESet = idESet;
		id = ID_EDEFAULT;
		idESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.COMMENT__ID, oldId, ID_EDEFAULT, oldIdESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetId() {
		return idESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageId(String newPageId) {
		String oldPageId = pageId;
		pageId = newPageId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__PAGE_ID, oldPageId, pageId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__AUTHOR, oldAuthor, author));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthorName(String newAuthorName) {
		String oldAuthorName = authorName;
		authorName = newAuthorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__AUTHOR_NAME, oldAuthorName, authorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XMLGregorianCalendar getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDate(XMLGregorianCalendar newDate) {
		XMLGregorianCalendar oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHighlight() {
		return highlight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHighlight(String newHighlight) {
		String oldHighlight = highlight;
		highlight = newHighlight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__HIGHLIGHT, oldHighlight, highlight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__TEXT, oldText, text));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getReplyTo() {
		return replyTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReplyTo(Integer newReplyTo) {
		Integer oldReplyTo = replyTo;
		replyTo = newReplyTo;
		boolean oldReplyToESet = replyToESet;
		replyToESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.COMMENT__REPLY_TO, oldReplyTo, replyTo, !oldReplyToESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetReplyTo() {
		Integer oldReplyTo = replyTo;
		boolean oldReplyToESet = replyToESet;
		replyTo = REPLY_TO_EDEFAULT;
		replyToESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.COMMENT__REPLY_TO, oldReplyTo, REPLY_TO_EDEFAULT, oldReplyToESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetReplyTo() {
		return replyToESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XwikiPackage.COMMENT__ID:
				return getId();
			case XwikiPackage.COMMENT__PAGE_ID:
				return getPageId();
			case XwikiPackage.COMMENT__AUTHOR:
				return getAuthor();
			case XwikiPackage.COMMENT__AUTHOR_NAME:
				return getAuthorName();
			case XwikiPackage.COMMENT__DATE:
				return getDate();
			case XwikiPackage.COMMENT__HIGHLIGHT:
				return getHighlight();
			case XwikiPackage.COMMENT__TEXT:
				return getText();
			case XwikiPackage.COMMENT__REPLY_TO:
				return getReplyTo();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case XwikiPackage.COMMENT__ID:
				setId((Integer)newValue);
				return;
			case XwikiPackage.COMMENT__PAGE_ID:
				setPageId((String)newValue);
				return;
			case XwikiPackage.COMMENT__AUTHOR:
				setAuthor((String)newValue);
				return;
			case XwikiPackage.COMMENT__AUTHOR_NAME:
				setAuthorName((String)newValue);
				return;
			case XwikiPackage.COMMENT__DATE:
				setDate((XMLGregorianCalendar)newValue);
				return;
			case XwikiPackage.COMMENT__HIGHLIGHT:
				setHighlight((String)newValue);
				return;
			case XwikiPackage.COMMENT__TEXT:
				setText((String)newValue);
				return;
			case XwikiPackage.COMMENT__REPLY_TO:
				setReplyTo((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case XwikiPackage.COMMENT__ID:
				unsetId();
				return;
			case XwikiPackage.COMMENT__PAGE_ID:
				setPageId(PAGE_ID_EDEFAULT);
				return;
			case XwikiPackage.COMMENT__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case XwikiPackage.COMMENT__AUTHOR_NAME:
				setAuthorName(AUTHOR_NAME_EDEFAULT);
				return;
			case XwikiPackage.COMMENT__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case XwikiPackage.COMMENT__HIGHLIGHT:
				setHighlight(HIGHLIGHT_EDEFAULT);
				return;
			case XwikiPackage.COMMENT__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case XwikiPackage.COMMENT__REPLY_TO:
				unsetReplyTo();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case XwikiPackage.COMMENT__ID:
				return isSetId();
			case XwikiPackage.COMMENT__PAGE_ID:
				return PAGE_ID_EDEFAULT == null ? pageId != null : !PAGE_ID_EDEFAULT.equals(pageId);
			case XwikiPackage.COMMENT__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case XwikiPackage.COMMENT__AUTHOR_NAME:
				return AUTHOR_NAME_EDEFAULT == null ? authorName != null : !AUTHOR_NAME_EDEFAULT.equals(authorName);
			case XwikiPackage.COMMENT__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case XwikiPackage.COMMENT__HIGHLIGHT:
				return HIGHLIGHT_EDEFAULT == null ? highlight != null : !HIGHLIGHT_EDEFAULT.equals(highlight);
			case XwikiPackage.COMMENT__TEXT:
				return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
			case XwikiPackage.COMMENT__REPLY_TO:
				return isSetReplyTo();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		if (idESet) result.append(id); else result.append("<unset>");
		result.append(", pageId: ");
		result.append(pageId);
		result.append(", author: ");
		result.append(author);
		result.append(", authorName: ");
		result.append(authorName);
		result.append(", date: ");
		result.append(date);
		result.append(", highlight: ");
		result.append(highlight);
		result.append(", text: ");
		result.append(text);
		result.append(", replyTo: ");
		if (replyToESet) result.append(replyTo); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //CommentImpl
