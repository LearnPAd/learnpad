/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Summary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getGuid <em>Guid</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getPageId <em>Page Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getPageVersion <em>Page Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getPageName <em>Page Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getPageAuthor <em>Page Author</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getPageAuthorName <em>Page Author Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl#getHeadline <em>Headline</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ObjectSummaryImpl extends LinkCollectionImpl implements ObjectSummary {
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
	 * The default value of the '{@link #getGuid() <em>Guid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected static final String GUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGuid() <em>Guid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected String guid = GUID_EDEFAULT;

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
	 * The default value of the '{@link #getWiki() <em>Wiki</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWiki()
	 * @generated
	 * @ordered
	 */
	protected static final String WIKI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWiki() <em>Wiki</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWiki()
	 * @generated
	 * @ordered
	 */
	protected String wiki = WIKI_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpace() <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpace()
	 * @generated
	 * @ordered
	 */
	protected static final String SPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpace() <em>Space</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpace()
	 * @generated
	 * @ordered
	 */
	protected String space = SPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPageName() <em>Page Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageName() <em>Page Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageName()
	 * @generated
	 * @ordered
	 */
	protected String pageName = PAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPageAuthor() <em>Page Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_AUTHOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageAuthor() <em>Page Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageAuthor()
	 * @generated
	 * @ordered
	 */
	protected String pageAuthor = PAGE_AUTHOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getPageAuthorName() <em>Page Author Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageAuthorName()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_AUTHOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageAuthorName() <em>Page Author Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageAuthorName()
	 * @generated
	 * @ordered
	 */
	protected String pageAuthorName = PAGE_AUTHOR_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

	/**
	 * This is true if the Number attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean numberESet;

	/**
	 * The default value of the '{@link #getHeadline() <em>Headline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadline()
	 * @generated
	 * @ordered
	 */
	protected static final String HEADLINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHeadline() <em>Headline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeadline()
	 * @generated
	 * @ordered
	 */
	protected String headline = HEADLINE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectSummaryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.OBJECT_SUMMARY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGuid(String newGuid) {
		String oldGuid = guid;
		guid = newGuid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__PAGE_ID, oldPageId, pageId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__PAGE_VERSION, oldPageVersion, pageVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWiki() {
		return wiki;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWiki(String newWiki) {
		String oldWiki = wiki;
		wiki = newWiki;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__WIKI, oldWiki, wiki));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpace() {
		return space;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpace(String newSpace) {
		String oldSpace = space;
		space = newSpace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__SPACE, oldSpace, space));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageName(String newPageName) {
		String oldPageName = pageName;
		pageName = newPageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__PAGE_NAME, oldPageName, pageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageAuthor() {
		return pageAuthor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageAuthor(String newPageAuthor) {
		String oldPageAuthor = pageAuthor;
		pageAuthor = newPageAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR, oldPageAuthor, pageAuthor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageAuthorName() {
		return pageAuthorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageAuthorName(String newPageAuthorName) {
		String oldPageAuthorName = pageAuthorName;
		pageAuthorName = newPageAuthorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR_NAME, oldPageAuthorName, pageAuthorName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		boolean oldNumberESet = numberESet;
		numberESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__NUMBER, oldNumber, number, !oldNumberESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetNumber() {
		int oldNumber = number;
		boolean oldNumberESet = numberESet;
		number = NUMBER_EDEFAULT;
		numberESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.OBJECT_SUMMARY__NUMBER, oldNumber, NUMBER_EDEFAULT, oldNumberESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetNumber() {
		return numberESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHeadline() {
		return headline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeadline(String newHeadline) {
		String oldHeadline = headline;
		headline = newHeadline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.OBJECT_SUMMARY__HEADLINE, oldHeadline, headline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XwikiPackage.OBJECT_SUMMARY__ID:
				return getId();
			case XwikiPackage.OBJECT_SUMMARY__GUID:
				return getGuid();
			case XwikiPackage.OBJECT_SUMMARY__PAGE_ID:
				return getPageId();
			case XwikiPackage.OBJECT_SUMMARY__PAGE_VERSION:
				return getPageVersion();
			case XwikiPackage.OBJECT_SUMMARY__WIKI:
				return getWiki();
			case XwikiPackage.OBJECT_SUMMARY__SPACE:
				return getSpace();
			case XwikiPackage.OBJECT_SUMMARY__PAGE_NAME:
				return getPageName();
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR:
				return getPageAuthor();
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR_NAME:
				return getPageAuthorName();
			case XwikiPackage.OBJECT_SUMMARY__CLASS_NAME:
				return getClassName();
			case XwikiPackage.OBJECT_SUMMARY__NUMBER:
				return getNumber();
			case XwikiPackage.OBJECT_SUMMARY__HEADLINE:
				return getHeadline();
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
			case XwikiPackage.OBJECT_SUMMARY__ID:
				setId((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__GUID:
				setGuid((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_ID:
				setPageId((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_VERSION:
				setPageVersion((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__WIKI:
				setWiki((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__SPACE:
				setSpace((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_NAME:
				setPageName((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR:
				setPageAuthor((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR_NAME:
				setPageAuthorName((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__NUMBER:
				setNumber((Integer)newValue);
				return;
			case XwikiPackage.OBJECT_SUMMARY__HEADLINE:
				setHeadline((String)newValue);
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
			case XwikiPackage.OBJECT_SUMMARY__ID:
				setId(ID_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__GUID:
				setGuid(GUID_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_ID:
				setPageId(PAGE_ID_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_VERSION:
				setPageVersion(PAGE_VERSION_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__WIKI:
				setWiki(WIKI_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__SPACE:
				setSpace(SPACE_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_NAME:
				setPageName(PAGE_NAME_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR:
				setPageAuthor(PAGE_AUTHOR_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR_NAME:
				setPageAuthorName(PAGE_AUTHOR_NAME_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case XwikiPackage.OBJECT_SUMMARY__NUMBER:
				unsetNumber();
				return;
			case XwikiPackage.OBJECT_SUMMARY__HEADLINE:
				setHeadline(HEADLINE_EDEFAULT);
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
			case XwikiPackage.OBJECT_SUMMARY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XwikiPackage.OBJECT_SUMMARY__GUID:
				return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
			case XwikiPackage.OBJECT_SUMMARY__PAGE_ID:
				return PAGE_ID_EDEFAULT == null ? pageId != null : !PAGE_ID_EDEFAULT.equals(pageId);
			case XwikiPackage.OBJECT_SUMMARY__PAGE_VERSION:
				return PAGE_VERSION_EDEFAULT == null ? pageVersion != null : !PAGE_VERSION_EDEFAULT.equals(pageVersion);
			case XwikiPackage.OBJECT_SUMMARY__WIKI:
				return WIKI_EDEFAULT == null ? wiki != null : !WIKI_EDEFAULT.equals(wiki);
			case XwikiPackage.OBJECT_SUMMARY__SPACE:
				return SPACE_EDEFAULT == null ? space != null : !SPACE_EDEFAULT.equals(space);
			case XwikiPackage.OBJECT_SUMMARY__PAGE_NAME:
				return PAGE_NAME_EDEFAULT == null ? pageName != null : !PAGE_NAME_EDEFAULT.equals(pageName);
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR:
				return PAGE_AUTHOR_EDEFAULT == null ? pageAuthor != null : !PAGE_AUTHOR_EDEFAULT.equals(pageAuthor);
			case XwikiPackage.OBJECT_SUMMARY__PAGE_AUTHOR_NAME:
				return PAGE_AUTHOR_NAME_EDEFAULT == null ? pageAuthorName != null : !PAGE_AUTHOR_NAME_EDEFAULT.equals(pageAuthorName);
			case XwikiPackage.OBJECT_SUMMARY__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case XwikiPackage.OBJECT_SUMMARY__NUMBER:
				return isSetNumber();
			case XwikiPackage.OBJECT_SUMMARY__HEADLINE:
				return HEADLINE_EDEFAULT == null ? headline != null : !HEADLINE_EDEFAULT.equals(headline);
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
		result.append(", guid: ");
		result.append(guid);
		result.append(", pageId: ");
		result.append(pageId);
		result.append(", pageVersion: ");
		result.append(pageVersion);
		result.append(", wiki: ");
		result.append(wiki);
		result.append(", space: ");
		result.append(space);
		result.append(", pageName: ");
		result.append(pageName);
		result.append(", pageAuthor: ");
		result.append(pageAuthor);
		result.append(", pageAuthorName: ");
		result.append(pageAuthorName);
		result.append(", className: ");
		result.append(className);
		result.append(", number: ");
		if (numberESet) result.append(number); else result.append("<unset>");
		result.append(", headline: ");
		result.append(headline);
		result.append(')');
		return result.toString();
	}

} //ObjectSummaryImpl
