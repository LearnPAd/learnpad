/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>History Summary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getPageId <em>Page Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getMajorVersion <em>Major Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getMinorVersion <em>Minor Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getModifierName <em>Modifier Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl#getComment <em>Comment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HistorySummaryImpl extends LinkCollectionImpl implements HistorySummary {
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
	 * The default value of the '{@link #getMajorVersion() <em>Major Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMajorVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int MAJOR_VERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMajorVersion() <em>Major Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMajorVersion()
	 * @generated
	 * @ordered
	 */
	protected int majorVersion = MAJOR_VERSION_EDEFAULT;

	/**
	 * This is true if the Major Version attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean majorVersionESet;

	/**
	 * The default value of the '{@link #getMinorVersion() <em>Minor Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinorVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int MINOR_VERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinorVersion() <em>Minor Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinorVersion()
	 * @generated
	 * @ordered
	 */
	protected int minorVersion = MINOR_VERSION_EDEFAULT;

	/**
	 * This is true if the Minor Version attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean minorVersionESet;

	/**
	 * The default value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected static final XMLGregorianCalendar MODIFIED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected XMLGregorianCalendar modified = MODIFIED_EDEFAULT;

	/**
	 * The default value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifier()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifier()
	 * @generated
	 * @ordered
	 */
	protected String modifier = MODIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getModifierName() <em>Modifier Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifierName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModifierName() <em>Modifier Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModifierName()
	 * @generated
	 * @ordered
	 */
	protected String modifierName = MODIFIER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected static final String COMMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected String comment = COMMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HistorySummaryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.HISTORY_SUMMARY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__PAGE_ID, oldPageId, pageId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__WIKI, oldWiki, wiki));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__SPACE, oldSpace, space));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMajorVersion() {
		return majorVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMajorVersion(int newMajorVersion) {
		int oldMajorVersion = majorVersion;
		majorVersion = newMajorVersion;
		boolean oldMajorVersionESet = majorVersionESet;
		majorVersionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__MAJOR_VERSION, oldMajorVersion, majorVersion, !oldMajorVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMajorVersion() {
		int oldMajorVersion = majorVersion;
		boolean oldMajorVersionESet = majorVersionESet;
		majorVersion = MAJOR_VERSION_EDEFAULT;
		majorVersionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.HISTORY_SUMMARY__MAJOR_VERSION, oldMajorVersion, MAJOR_VERSION_EDEFAULT, oldMajorVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMajorVersion() {
		return majorVersionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinorVersion() {
		return minorVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinorVersion(int newMinorVersion) {
		int oldMinorVersion = minorVersion;
		minorVersion = newMinorVersion;
		boolean oldMinorVersionESet = minorVersionESet;
		minorVersionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__MINOR_VERSION, oldMinorVersion, minorVersion, !oldMinorVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMinorVersion() {
		int oldMinorVersion = minorVersion;
		boolean oldMinorVersionESet = minorVersionESet;
		minorVersion = MINOR_VERSION_EDEFAULT;
		minorVersionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.HISTORY_SUMMARY__MINOR_VERSION, oldMinorVersion, MINOR_VERSION_EDEFAULT, oldMinorVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMinorVersion() {
		return minorVersionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XMLGregorianCalendar getModified() {
		return modified;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModified(XMLGregorianCalendar newModified) {
		XMLGregorianCalendar oldModified = modified;
		modified = newModified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__MODIFIED, oldModified, modified));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifier(String newModifier) {
		String oldModifier = modifier;
		modifier = newModifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__MODIFIER, oldModifier, modifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifierName() {
		return modifierName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifierName(String newModifierName) {
		String oldModifierName = modifierName;
		modifierName = newModifierName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__MODIFIER_NAME, oldModifierName, modifierName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(String newLanguage) {
		String oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__LANGUAGE, oldLanguage, language));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(String newComment) {
		String oldComment = comment;
		comment = newComment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.HISTORY_SUMMARY__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XwikiPackage.HISTORY_SUMMARY__PAGE_ID:
				return getPageId();
			case XwikiPackage.HISTORY_SUMMARY__WIKI:
				return getWiki();
			case XwikiPackage.HISTORY_SUMMARY__SPACE:
				return getSpace();
			case XwikiPackage.HISTORY_SUMMARY__NAME:
				return getName();
			case XwikiPackage.HISTORY_SUMMARY__VERSION:
				return getVersion();
			case XwikiPackage.HISTORY_SUMMARY__MAJOR_VERSION:
				return getMajorVersion();
			case XwikiPackage.HISTORY_SUMMARY__MINOR_VERSION:
				return getMinorVersion();
			case XwikiPackage.HISTORY_SUMMARY__MODIFIED:
				return getModified();
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER:
				return getModifier();
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER_NAME:
				return getModifierName();
			case XwikiPackage.HISTORY_SUMMARY__LANGUAGE:
				return getLanguage();
			case XwikiPackage.HISTORY_SUMMARY__COMMENT:
				return getComment();
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
			case XwikiPackage.HISTORY_SUMMARY__PAGE_ID:
				setPageId((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__WIKI:
				setWiki((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__SPACE:
				setSpace((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__NAME:
				setName((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__VERSION:
				setVersion((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MAJOR_VERSION:
				setMajorVersion((Integer)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MINOR_VERSION:
				setMinorVersion((Integer)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MODIFIED:
				setModified((XMLGregorianCalendar)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER:
				setModifier((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER_NAME:
				setModifierName((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case XwikiPackage.HISTORY_SUMMARY__COMMENT:
				setComment((String)newValue);
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
			case XwikiPackage.HISTORY_SUMMARY__PAGE_ID:
				setPageId(PAGE_ID_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__WIKI:
				setWiki(WIKI_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__SPACE:
				setSpace(SPACE_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MAJOR_VERSION:
				unsetMajorVersion();
				return;
			case XwikiPackage.HISTORY_SUMMARY__MINOR_VERSION:
				unsetMinorVersion();
				return;
			case XwikiPackage.HISTORY_SUMMARY__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER:
				setModifier(MODIFIER_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER_NAME:
				setModifierName(MODIFIER_NAME_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case XwikiPackage.HISTORY_SUMMARY__COMMENT:
				setComment(COMMENT_EDEFAULT);
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
			case XwikiPackage.HISTORY_SUMMARY__PAGE_ID:
				return PAGE_ID_EDEFAULT == null ? pageId != null : !PAGE_ID_EDEFAULT.equals(pageId);
			case XwikiPackage.HISTORY_SUMMARY__WIKI:
				return WIKI_EDEFAULT == null ? wiki != null : !WIKI_EDEFAULT.equals(wiki);
			case XwikiPackage.HISTORY_SUMMARY__SPACE:
				return SPACE_EDEFAULT == null ? space != null : !SPACE_EDEFAULT.equals(space);
			case XwikiPackage.HISTORY_SUMMARY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case XwikiPackage.HISTORY_SUMMARY__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case XwikiPackage.HISTORY_SUMMARY__MAJOR_VERSION:
				return isSetMajorVersion();
			case XwikiPackage.HISTORY_SUMMARY__MINOR_VERSION:
				return isSetMinorVersion();
			case XwikiPackage.HISTORY_SUMMARY__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER:
				return MODIFIER_EDEFAULT == null ? modifier != null : !MODIFIER_EDEFAULT.equals(modifier);
			case XwikiPackage.HISTORY_SUMMARY__MODIFIER_NAME:
				return MODIFIER_NAME_EDEFAULT == null ? modifierName != null : !MODIFIER_NAME_EDEFAULT.equals(modifierName);
			case XwikiPackage.HISTORY_SUMMARY__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case XwikiPackage.HISTORY_SUMMARY__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
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
		result.append(" (pageId: ");
		result.append(pageId);
		result.append(", wiki: ");
		result.append(wiki);
		result.append(", space: ");
		result.append(space);
		result.append(", name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(", majorVersion: ");
		if (majorVersionESet) result.append(majorVersion); else result.append("<unset>");
		result.append(", minorVersion: ");
		if (minorVersionESet) result.append(minorVersion); else result.append("<unset>");
		result.append(", modified: ");
		result.append(modified);
		result.append(", modifier: ");
		result.append(modifier);
		result.append(", modifierName: ");
		result.append(modifierName);
		result.append(", language: ");
		result.append(language);
		result.append(", comment: ");
		result.append(comment);
		result.append(')');
		return result.toString();
	}

} //HistorySummaryImpl
