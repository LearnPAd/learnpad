/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.Page;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getMajorVersion <em>Major Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getMinorVersion <em>Minor Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getCreator <em>Creator</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getCreatorName <em>Creator Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getModifier <em>Modifier</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getModifierName <em>Modifier Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PageImpl extends PageSummaryImpl implements Page {
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
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected static final XMLGregorianCalendar CREATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected XMLGregorianCalendar created = CREATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreator() <em>Creator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreator()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreator() <em>Creator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreator()
	 * @generated
	 * @ordered
	 */
	protected String creator = CREATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreatorName() <em>Creator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreatorName()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATOR_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreatorName() <em>Creator Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreatorName()
	 * @generated
	 * @ordered
	 */
	protected String creatorName = CREATOR_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.PAGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__LANGUAGE, oldLanguage, language));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__MAJOR_VERSION, oldMajorVersion, majorVersion, !oldMajorVersionESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.PAGE__MAJOR_VERSION, oldMajorVersion, MAJOR_VERSION_EDEFAULT, oldMajorVersionESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__MINOR_VERSION, oldMinorVersion, minorVersion, !oldMinorVersionESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.PAGE__MINOR_VERSION, oldMinorVersion, MINOR_VERSION_EDEFAULT, oldMinorVersionESet));
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
	public XMLGregorianCalendar getCreated() {
		return created;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreated(XMLGregorianCalendar newCreated) {
		XMLGregorianCalendar oldCreated = created;
		created = newCreated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__CREATED, oldCreated, created));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreator(String newCreator) {
		String oldCreator = creator;
		creator = newCreator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__CREATOR, oldCreator, creator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreatorName(String newCreatorName) {
		String oldCreatorName = creatorName;
		creatorName = newCreatorName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__CREATOR_NAME, oldCreatorName, creatorName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__MODIFIED, oldModified, modified));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__MODIFIER, oldModifier, modifier));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__MODIFIER_NAME, oldModifierName, modifierName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__COMMENT, oldComment, comment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent(String newContent) {
		String oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE__CONTENT, oldContent, content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XwikiPackage.PAGE__LANGUAGE:
				return getLanguage();
			case XwikiPackage.PAGE__MAJOR_VERSION:
				return getMajorVersion();
			case XwikiPackage.PAGE__MINOR_VERSION:
				return getMinorVersion();
			case XwikiPackage.PAGE__CREATED:
				return getCreated();
			case XwikiPackage.PAGE__CREATOR:
				return getCreator();
			case XwikiPackage.PAGE__CREATOR_NAME:
				return getCreatorName();
			case XwikiPackage.PAGE__MODIFIED:
				return getModified();
			case XwikiPackage.PAGE__MODIFIER:
				return getModifier();
			case XwikiPackage.PAGE__MODIFIER_NAME:
				return getModifierName();
			case XwikiPackage.PAGE__COMMENT:
				return getComment();
			case XwikiPackage.PAGE__CONTENT:
				return getContent();
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
			case XwikiPackage.PAGE__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case XwikiPackage.PAGE__MAJOR_VERSION:
				setMajorVersion((Integer)newValue);
				return;
			case XwikiPackage.PAGE__MINOR_VERSION:
				setMinorVersion((Integer)newValue);
				return;
			case XwikiPackage.PAGE__CREATED:
				setCreated((XMLGregorianCalendar)newValue);
				return;
			case XwikiPackage.PAGE__CREATOR:
				setCreator((String)newValue);
				return;
			case XwikiPackage.PAGE__CREATOR_NAME:
				setCreatorName((String)newValue);
				return;
			case XwikiPackage.PAGE__MODIFIED:
				setModified((XMLGregorianCalendar)newValue);
				return;
			case XwikiPackage.PAGE__MODIFIER:
				setModifier((String)newValue);
				return;
			case XwikiPackage.PAGE__MODIFIER_NAME:
				setModifierName((String)newValue);
				return;
			case XwikiPackage.PAGE__COMMENT:
				setComment((String)newValue);
				return;
			case XwikiPackage.PAGE__CONTENT:
				setContent((String)newValue);
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
			case XwikiPackage.PAGE__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case XwikiPackage.PAGE__MAJOR_VERSION:
				unsetMajorVersion();
				return;
			case XwikiPackage.PAGE__MINOR_VERSION:
				unsetMinorVersion();
				return;
			case XwikiPackage.PAGE__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case XwikiPackage.PAGE__CREATOR:
				setCreator(CREATOR_EDEFAULT);
				return;
			case XwikiPackage.PAGE__CREATOR_NAME:
				setCreatorName(CREATOR_NAME_EDEFAULT);
				return;
			case XwikiPackage.PAGE__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case XwikiPackage.PAGE__MODIFIER:
				setModifier(MODIFIER_EDEFAULT);
				return;
			case XwikiPackage.PAGE__MODIFIER_NAME:
				setModifierName(MODIFIER_NAME_EDEFAULT);
				return;
			case XwikiPackage.PAGE__COMMENT:
				setComment(COMMENT_EDEFAULT);
				return;
			case XwikiPackage.PAGE__CONTENT:
				setContent(CONTENT_EDEFAULT);
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
			case XwikiPackage.PAGE__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case XwikiPackage.PAGE__MAJOR_VERSION:
				return isSetMajorVersion();
			case XwikiPackage.PAGE__MINOR_VERSION:
				return isSetMinorVersion();
			case XwikiPackage.PAGE__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case XwikiPackage.PAGE__CREATOR:
				return CREATOR_EDEFAULT == null ? creator != null : !CREATOR_EDEFAULT.equals(creator);
			case XwikiPackage.PAGE__CREATOR_NAME:
				return CREATOR_NAME_EDEFAULT == null ? creatorName != null : !CREATOR_NAME_EDEFAULT.equals(creatorName);
			case XwikiPackage.PAGE__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case XwikiPackage.PAGE__MODIFIER:
				return MODIFIER_EDEFAULT == null ? modifier != null : !MODIFIER_EDEFAULT.equals(modifier);
			case XwikiPackage.PAGE__MODIFIER_NAME:
				return MODIFIER_NAME_EDEFAULT == null ? modifierName != null : !MODIFIER_NAME_EDEFAULT.equals(modifierName);
			case XwikiPackage.PAGE__COMMENT:
				return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
			case XwikiPackage.PAGE__CONTENT:
				return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
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
		result.append(" (language: ");
		result.append(language);
		result.append(", majorVersion: ");
		if (majorVersionESet) result.append(majorVersion); else result.append("<unset>");
		result.append(", minorVersion: ");
		if (minorVersionESet) result.append(minorVersion); else result.append("<unset>");
		result.append(", created: ");
		result.append(created);
		result.append(", creator: ");
		result.append(creator);
		result.append(", creatorName: ");
		result.append(creatorName);
		result.append(", modified: ");
		result.append(modified);
		result.append(", modifier: ");
		result.append(modifier);
		result.append(", modifierName: ");
		result.append(modifierName);
		result.append(", comment: ");
		result.append(comment);
		result.append(", content: ");
		result.append(content);
		result.append(')');
		return result.toString();
	}

} //PageImpl
