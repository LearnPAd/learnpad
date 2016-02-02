/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attachment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getSize <em>Size</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getPageId <em>Page Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getPageVersion <em>Page Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getMimeType <em>Mime Type</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getAuthorName <em>Author Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getDate <em>Date</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttachmentImpl extends LinkCollectionImpl implements Attachment {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected int size = SIZE_EDEFAULT;

	/**
	 * This is true if the Size attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sizeESet;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

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
	 * The default value of the '{@link #getPageVersion() <em>Page Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageVersion() <em>Page Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageVersion()
	 * @generated
	 * @ordered
	 */
	protected String pageVersion = PAGE_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMimeType() <em>Mime Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMimeType()
	 * @generated
	 * @ordered
	 */
	protected static final String MIME_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMimeType() <em>Mime Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMimeType()
	 * @generated
	 * @ordered
	 */
	protected String mimeType = MIME_TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getXwikiRelativeUrl() <em>Xwiki Relative Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXwikiRelativeUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String XWIKI_RELATIVE_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getXwikiRelativeUrl() <em>Xwiki Relative Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXwikiRelativeUrl()
	 * @generated
	 * @ordered
	 */
	protected String xwikiRelativeUrl = XWIKI_RELATIVE_URL_EDEFAULT;

	/**
	 * The default value of the '{@link #getXwikiAbsoluteUrl() <em>Xwiki Absolute Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXwikiAbsoluteUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String XWIKI_ABSOLUTE_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getXwikiAbsoluteUrl() <em>Xwiki Absolute Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXwikiAbsoluteUrl()
	 * @generated
	 * @ordered
	 */
	protected String xwikiAbsoluteUrl = XWIKI_ABSOLUTE_URL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttachmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.ATTACHMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(int newSize) {
		int oldSize = size;
		size = newSize;
		boolean oldSizeESet = sizeESet;
		sizeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__SIZE, oldSize, size, !oldSizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSize() {
		int oldSize = size;
		boolean oldSizeESet = sizeESet;
		size = SIZE_EDEFAULT;
		sizeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.ATTACHMENT__SIZE, oldSize, SIZE_EDEFAULT, oldSizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSize() {
		return sizeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__VERSION, oldVersion, version));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__PAGE_ID, oldPageId, pageId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageVersion() {
		return pageVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageVersion(String newPageVersion) {
		String oldPageVersion = pageVersion;
		pageVersion = newPageVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__PAGE_VERSION, oldPageVersion, pageVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMimeType(String newMimeType) {
		String oldMimeType = mimeType;
		mimeType = newMimeType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__MIME_TYPE, oldMimeType, mimeType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__AUTHOR, oldAuthor, author));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__AUTHOR_NAME, oldAuthorName, authorName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXwikiRelativeUrl() {
		return xwikiRelativeUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXwikiRelativeUrl(String newXwikiRelativeUrl) {
		String oldXwikiRelativeUrl = xwikiRelativeUrl;
		xwikiRelativeUrl = newXwikiRelativeUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__XWIKI_RELATIVE_URL, oldXwikiRelativeUrl, xwikiRelativeUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXwikiAbsoluteUrl() {
		return xwikiAbsoluteUrl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXwikiAbsoluteUrl(String newXwikiAbsoluteUrl) {
		String oldXwikiAbsoluteUrl = xwikiAbsoluteUrl;
		xwikiAbsoluteUrl = newXwikiAbsoluteUrl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.ATTACHMENT__XWIKI_ABSOLUTE_URL, oldXwikiAbsoluteUrl, xwikiAbsoluteUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XwikiPackage.ATTACHMENT__ID:
				return getId();
			case XwikiPackage.ATTACHMENT__NAME:
				return getName();
			case XwikiPackage.ATTACHMENT__SIZE:
				return getSize();
			case XwikiPackage.ATTACHMENT__VERSION:
				return getVersion();
			case XwikiPackage.ATTACHMENT__PAGE_ID:
				return getPageId();
			case XwikiPackage.ATTACHMENT__PAGE_VERSION:
				return getPageVersion();
			case XwikiPackage.ATTACHMENT__MIME_TYPE:
				return getMimeType();
			case XwikiPackage.ATTACHMENT__AUTHOR:
				return getAuthor();
			case XwikiPackage.ATTACHMENT__AUTHOR_NAME:
				return getAuthorName();
			case XwikiPackage.ATTACHMENT__DATE:
				return getDate();
			case XwikiPackage.ATTACHMENT__XWIKI_RELATIVE_URL:
				return getXwikiRelativeUrl();
			case XwikiPackage.ATTACHMENT__XWIKI_ABSOLUTE_URL:
				return getXwikiAbsoluteUrl();
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
			case XwikiPackage.ATTACHMENT__ID:
				setId((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__NAME:
				setName((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__SIZE:
				setSize((Integer)newValue);
				return;
			case XwikiPackage.ATTACHMENT__VERSION:
				setVersion((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__PAGE_ID:
				setPageId((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__PAGE_VERSION:
				setPageVersion((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__MIME_TYPE:
				setMimeType((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__AUTHOR:
				setAuthor((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__AUTHOR_NAME:
				setAuthorName((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__DATE:
				setDate((XMLGregorianCalendar)newValue);
				return;
			case XwikiPackage.ATTACHMENT__XWIKI_RELATIVE_URL:
				setXwikiRelativeUrl((String)newValue);
				return;
			case XwikiPackage.ATTACHMENT__XWIKI_ABSOLUTE_URL:
				setXwikiAbsoluteUrl((String)newValue);
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
			case XwikiPackage.ATTACHMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__SIZE:
				unsetSize();
				return;
			case XwikiPackage.ATTACHMENT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__PAGE_ID:
				setPageId(PAGE_ID_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__PAGE_VERSION:
				setPageVersion(PAGE_VERSION_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__MIME_TYPE:
				setMimeType(MIME_TYPE_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__AUTHOR_NAME:
				setAuthorName(AUTHOR_NAME_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__XWIKI_RELATIVE_URL:
				setXwikiRelativeUrl(XWIKI_RELATIVE_URL_EDEFAULT);
				return;
			case XwikiPackage.ATTACHMENT__XWIKI_ABSOLUTE_URL:
				setXwikiAbsoluteUrl(XWIKI_ABSOLUTE_URL_EDEFAULT);
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
			case XwikiPackage.ATTACHMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XwikiPackage.ATTACHMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case XwikiPackage.ATTACHMENT__SIZE:
				return isSetSize();
			case XwikiPackage.ATTACHMENT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case XwikiPackage.ATTACHMENT__PAGE_ID:
				return PAGE_ID_EDEFAULT == null ? pageId != null : !PAGE_ID_EDEFAULT.equals(pageId);
			case XwikiPackage.ATTACHMENT__PAGE_VERSION:
				return PAGE_VERSION_EDEFAULT == null ? pageVersion != null : !PAGE_VERSION_EDEFAULT.equals(pageVersion);
			case XwikiPackage.ATTACHMENT__MIME_TYPE:
				return MIME_TYPE_EDEFAULT == null ? mimeType != null : !MIME_TYPE_EDEFAULT.equals(mimeType);
			case XwikiPackage.ATTACHMENT__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case XwikiPackage.ATTACHMENT__AUTHOR_NAME:
				return AUTHOR_NAME_EDEFAULT == null ? authorName != null : !AUTHOR_NAME_EDEFAULT.equals(authorName);
			case XwikiPackage.ATTACHMENT__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case XwikiPackage.ATTACHMENT__XWIKI_RELATIVE_URL:
				return XWIKI_RELATIVE_URL_EDEFAULT == null ? xwikiRelativeUrl != null : !XWIKI_RELATIVE_URL_EDEFAULT.equals(xwikiRelativeUrl);
			case XwikiPackage.ATTACHMENT__XWIKI_ABSOLUTE_URL:
				return XWIKI_ABSOLUTE_URL_EDEFAULT == null ? xwikiAbsoluteUrl != null : !XWIKI_ABSOLUTE_URL_EDEFAULT.equals(xwikiAbsoluteUrl);
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
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", size: ");
		if (sizeESet) result.append(size); else result.append("<unset>");
		result.append(", version: ");
		result.append(version);
		result.append(", pageId: ");
		result.append(pageId);
		result.append(", pageVersion: ");
		result.append(pageVersion);
		result.append(", mimeType: ");
		result.append(mimeType);
		result.append(", author: ");
		result.append(author);
		result.append(", authorName: ");
		result.append(authorName);
		result.append(", date: ");
		result.append(date);
		result.append(", xwikiRelativeUrl: ");
		result.append(xwikiRelativeUrl);
		result.append(", xwikiAbsoluteUrl: ");
		result.append(xwikiAbsoluteUrl);
		result.append(')');
		return result.toString();
	}

} //AttachmentImpl
