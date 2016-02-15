/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Translations;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page Summary</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getFullName <em>Full Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getParentId <em>Parent Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getAuthorName <em>Author Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getTranslations <em>Translations</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl#getSyntax <em>Syntax</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PageSummaryImpl extends LinkCollectionImpl implements PageSummary {
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
	 * The default value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFullName() <em>Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullName()
	 * @generated
	 * @ordered
	 */
	protected String fullName = FULL_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getParent() <em>Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected String parent = PARENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getParentId() <em>Parent Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentId()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParentId() <em>Parent Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentId()
	 * @generated
	 * @ordered
	 */
	protected String parentId = PARENT_ID_EDEFAULT;

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
	 * The cached value of the '{@link #getTranslations() <em>Translations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTranslations()
	 * @generated
	 * @ordered
	 */
	protected Translations translations;

	/**
	 * The default value of the '{@link #getSyntax() <em>Syntax</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSyntax()
	 * @generated
	 * @ordered
	 */
	protected static final String SYNTAX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSyntax() <em>Syntax</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSyntax()
	 * @generated
	 * @ordered
	 */
	protected String syntax = SYNTAX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageSummaryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.PAGE_SUMMARY;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullName(String newFullName) {
		String oldFullName = fullName;
		fullName = newFullName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__FULL_NAME, oldFullName, fullName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__WIKI, oldWiki, wiki));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__SPACE, oldSpace, space));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(String newParent) {
		String oldParent = parent;
		parent = newParent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__PARENT, oldParent, parent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentId(String newParentId) {
		String oldParentId = parentId;
		parentId = newParentId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__PARENT_ID, oldParentId, parentId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__VERSION, oldVersion, version));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__AUTHOR, oldAuthor, author));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__AUTHOR_NAME, oldAuthorName, authorName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__XWIKI_RELATIVE_URL, oldXwikiRelativeUrl, xwikiRelativeUrl));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__XWIKI_ABSOLUTE_URL, oldXwikiAbsoluteUrl, xwikiAbsoluteUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Translations getTranslations() {
		return translations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTranslations(Translations newTranslations, NotificationChain msgs) {
		Translations oldTranslations = translations;
		translations = newTranslations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__TRANSLATIONS, oldTranslations, newTranslations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTranslations(Translations newTranslations) {
		if (newTranslations != translations) {
			NotificationChain msgs = null;
			if (translations != null)
				msgs = ((InternalEObject)translations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XwikiPackage.PAGE_SUMMARY__TRANSLATIONS, null, msgs);
			if (newTranslations != null)
				msgs = ((InternalEObject)newTranslations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XwikiPackage.PAGE_SUMMARY__TRANSLATIONS, null, msgs);
			msgs = basicSetTranslations(newTranslations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__TRANSLATIONS, newTranslations, newTranslations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSyntax() {
		return syntax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSyntax(String newSyntax) {
		String oldSyntax = syntax;
		syntax = newSyntax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.PAGE_SUMMARY__SYNTAX, oldSyntax, syntax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XwikiPackage.PAGE_SUMMARY__TRANSLATIONS:
				return basicSetTranslations(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XwikiPackage.PAGE_SUMMARY__ID:
				return getId();
			case XwikiPackage.PAGE_SUMMARY__FULL_NAME:
				return getFullName();
			case XwikiPackage.PAGE_SUMMARY__WIKI:
				return getWiki();
			case XwikiPackage.PAGE_SUMMARY__SPACE:
				return getSpace();
			case XwikiPackage.PAGE_SUMMARY__NAME:
				return getName();
			case XwikiPackage.PAGE_SUMMARY__TITLE:
				return getTitle();
			case XwikiPackage.PAGE_SUMMARY__PARENT:
				return getParent();
			case XwikiPackage.PAGE_SUMMARY__PARENT_ID:
				return getParentId();
			case XwikiPackage.PAGE_SUMMARY__VERSION:
				return getVersion();
			case XwikiPackage.PAGE_SUMMARY__AUTHOR:
				return getAuthor();
			case XwikiPackage.PAGE_SUMMARY__AUTHOR_NAME:
				return getAuthorName();
			case XwikiPackage.PAGE_SUMMARY__XWIKI_RELATIVE_URL:
				return getXwikiRelativeUrl();
			case XwikiPackage.PAGE_SUMMARY__XWIKI_ABSOLUTE_URL:
				return getXwikiAbsoluteUrl();
			case XwikiPackage.PAGE_SUMMARY__TRANSLATIONS:
				return getTranslations();
			case XwikiPackage.PAGE_SUMMARY__SYNTAX:
				return getSyntax();
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
			case XwikiPackage.PAGE_SUMMARY__ID:
				setId((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__FULL_NAME:
				setFullName((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__WIKI:
				setWiki((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__SPACE:
				setSpace((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__NAME:
				setName((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__TITLE:
				setTitle((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__PARENT:
				setParent((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__PARENT_ID:
				setParentId((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__VERSION:
				setVersion((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__AUTHOR:
				setAuthor((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__AUTHOR_NAME:
				setAuthorName((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__XWIKI_RELATIVE_URL:
				setXwikiRelativeUrl((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__XWIKI_ABSOLUTE_URL:
				setXwikiAbsoluteUrl((String)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__TRANSLATIONS:
				setTranslations((Translations)newValue);
				return;
			case XwikiPackage.PAGE_SUMMARY__SYNTAX:
				setSyntax((String)newValue);
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
			case XwikiPackage.PAGE_SUMMARY__ID:
				setId(ID_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__FULL_NAME:
				setFullName(FULL_NAME_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__WIKI:
				setWiki(WIKI_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__SPACE:
				setSpace(SPACE_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__PARENT:
				setParent(PARENT_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__PARENT_ID:
				setParentId(PARENT_ID_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__AUTHOR_NAME:
				setAuthorName(AUTHOR_NAME_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__XWIKI_RELATIVE_URL:
				setXwikiRelativeUrl(XWIKI_RELATIVE_URL_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__XWIKI_ABSOLUTE_URL:
				setXwikiAbsoluteUrl(XWIKI_ABSOLUTE_URL_EDEFAULT);
				return;
			case XwikiPackage.PAGE_SUMMARY__TRANSLATIONS:
				setTranslations((Translations)null);
				return;
			case XwikiPackage.PAGE_SUMMARY__SYNTAX:
				setSyntax(SYNTAX_EDEFAULT);
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
			case XwikiPackage.PAGE_SUMMARY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XwikiPackage.PAGE_SUMMARY__FULL_NAME:
				return FULL_NAME_EDEFAULT == null ? fullName != null : !FULL_NAME_EDEFAULT.equals(fullName);
			case XwikiPackage.PAGE_SUMMARY__WIKI:
				return WIKI_EDEFAULT == null ? wiki != null : !WIKI_EDEFAULT.equals(wiki);
			case XwikiPackage.PAGE_SUMMARY__SPACE:
				return SPACE_EDEFAULT == null ? space != null : !SPACE_EDEFAULT.equals(space);
			case XwikiPackage.PAGE_SUMMARY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case XwikiPackage.PAGE_SUMMARY__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case XwikiPackage.PAGE_SUMMARY__PARENT:
				return PARENT_EDEFAULT == null ? parent != null : !PARENT_EDEFAULT.equals(parent);
			case XwikiPackage.PAGE_SUMMARY__PARENT_ID:
				return PARENT_ID_EDEFAULT == null ? parentId != null : !PARENT_ID_EDEFAULT.equals(parentId);
			case XwikiPackage.PAGE_SUMMARY__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case XwikiPackage.PAGE_SUMMARY__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case XwikiPackage.PAGE_SUMMARY__AUTHOR_NAME:
				return AUTHOR_NAME_EDEFAULT == null ? authorName != null : !AUTHOR_NAME_EDEFAULT.equals(authorName);
			case XwikiPackage.PAGE_SUMMARY__XWIKI_RELATIVE_URL:
				return XWIKI_RELATIVE_URL_EDEFAULT == null ? xwikiRelativeUrl != null : !XWIKI_RELATIVE_URL_EDEFAULT.equals(xwikiRelativeUrl);
			case XwikiPackage.PAGE_SUMMARY__XWIKI_ABSOLUTE_URL:
				return XWIKI_ABSOLUTE_URL_EDEFAULT == null ? xwikiAbsoluteUrl != null : !XWIKI_ABSOLUTE_URL_EDEFAULT.equals(xwikiAbsoluteUrl);
			case XwikiPackage.PAGE_SUMMARY__TRANSLATIONS:
				return translations != null;
			case XwikiPackage.PAGE_SUMMARY__SYNTAX:
				return SYNTAX_EDEFAULT == null ? syntax != null : !SYNTAX_EDEFAULT.equals(syntax);
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
		result.append(", fullName: ");
		result.append(fullName);
		result.append(", wiki: ");
		result.append(wiki);
		result.append(", space: ");
		result.append(space);
		result.append(", name: ");
		result.append(name);
		result.append(", title: ");
		result.append(title);
		result.append(", parent: ");
		result.append(parent);
		result.append(", parentId: ");
		result.append(parentId);
		result.append(", version: ");
		result.append(version);
		result.append(", author: ");
		result.append(author);
		result.append(", authorName: ");
		result.append(authorName);
		result.append(", xwikiRelativeUrl: ");
		result.append(xwikiRelativeUrl);
		result.append(", xwikiAbsoluteUrl: ");
		result.append(xwikiAbsoluteUrl);
		result.append(", syntax: ");
		result.append(syntax);
		result.append(')');
		return result.toString();
	}

} //PageSummaryImpl
