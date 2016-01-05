/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Search Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getType <em>Type</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getPageFullName <em>Page Full Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getPageName <em>Page Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getAuthorName <em>Author Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getObjectNumber <em>Object Number</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getFilename <em>Filename</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getScore <em>Score</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl#getObject <em>Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SearchResultImpl extends LinkCollectionImpl implements SearchResult {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

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
	 * The default value of the '{@link #getPageFullName() <em>Page Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageFullName()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_FULL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageFullName() <em>Page Full Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageFullName()
	 * @generated
	 * @ordered
	 */
	protected String pageFullName = PAGE_FULL_NAME_EDEFAULT;

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
	 * The default value of the '{@link #getObjectNumber() <em>Object Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int OBJECT_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getObjectNumber() <em>Object Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectNumber()
	 * @generated
	 * @ordered
	 */
	protected int objectNumber = OBJECT_NUMBER_EDEFAULT;

	/**
	 * This is true if the Object Number attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean objectNumberESet;

	/**
	 * The default value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected static final String FILENAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected String filename = FILENAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getScore() <em>Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScore()
	 * @generated
	 * @ordered
	 */
	protected static final float SCORE_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getScore() <em>Score</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScore()
	 * @generated
	 * @ordered
	 */
	protected float score = SCORE_EDEFAULT;

	/**
	 * This is true if the Score attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean scoreESet;

	/**
	 * The cached value of the '{@link #getObject() <em>Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObject()
	 * @generated
	 * @ordered
	 */
	protected eu.learnpad.transformations.metamodel_corpus.xwiki.Object object;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SearchResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.SEARCH_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageFullName() {
		return pageFullName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageFullName(String newPageFullName) {
		String oldPageFullName = pageFullName;
		pageFullName = newPageFullName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__PAGE_FULL_NAME, oldPageFullName, pageFullName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__TITLE, oldTitle, title));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__WIKI, oldWiki, wiki));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__SPACE, oldSpace, space));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__PAGE_NAME, oldPageName, pageName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__MODIFIED, oldModified, modified));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__AUTHOR, oldAuthor, author));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__AUTHOR_NAME, oldAuthorName, authorName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__VERSION, oldVersion, version));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__LANGUAGE, oldLanguage, language));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getObjectNumber() {
		return objectNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObjectNumber(int newObjectNumber) {
		int oldObjectNumber = objectNumber;
		objectNumber = newObjectNumber;
		boolean oldObjectNumberESet = objectNumberESet;
		objectNumberESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__OBJECT_NUMBER, oldObjectNumber, objectNumber, !oldObjectNumberESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetObjectNumber() {
		int oldObjectNumber = objectNumber;
		boolean oldObjectNumberESet = objectNumberESet;
		objectNumber = OBJECT_NUMBER_EDEFAULT;
		objectNumberESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.SEARCH_RESULT__OBJECT_NUMBER, oldObjectNumber, OBJECT_NUMBER_EDEFAULT, oldObjectNumberESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetObjectNumber() {
		return objectNumberESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilename(String newFilename) {
		String oldFilename = filename;
		filename = newFilename;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__FILENAME, oldFilename, filename));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getScore() {
		return score;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScore(float newScore) {
		float oldScore = score;
		score = newScore;
		boolean oldScoreESet = scoreESet;
		scoreESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__SCORE, oldScore, score, !oldScoreESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetScore() {
		float oldScore = score;
		boolean oldScoreESet = scoreESet;
		score = SCORE_EDEFAULT;
		scoreESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XwikiPackage.SEARCH_RESULT__SCORE, oldScore, SCORE_EDEFAULT, oldScoreESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetScore() {
		return scoreESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public eu.learnpad.transformations.metamodel_corpus.xwiki.Object getObject() {
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetObject(eu.learnpad.transformations.metamodel_corpus.xwiki.Object newObject, NotificationChain msgs) {
		eu.learnpad.transformations.metamodel_corpus.xwiki.Object oldObject = object;
		object = newObject;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__OBJECT, oldObject, newObject);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObject(eu.learnpad.transformations.metamodel_corpus.xwiki.Object newObject) {
		if (newObject != object) {
			NotificationChain msgs = null;
			if (object != null)
				msgs = ((InternalEObject)object).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XwikiPackage.SEARCH_RESULT__OBJECT, null, msgs);
			if (newObject != null)
				msgs = ((InternalEObject)newObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XwikiPackage.SEARCH_RESULT__OBJECT, null, msgs);
			msgs = basicSetObject(newObject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SEARCH_RESULT__OBJECT, newObject, newObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XwikiPackage.SEARCH_RESULT__OBJECT:
				return basicSetObject(null, msgs);
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
			case XwikiPackage.SEARCH_RESULT__TYPE:
				return getType();
			case XwikiPackage.SEARCH_RESULT__ID:
				return getId();
			case XwikiPackage.SEARCH_RESULT__PAGE_FULL_NAME:
				return getPageFullName();
			case XwikiPackage.SEARCH_RESULT__TITLE:
				return getTitle();
			case XwikiPackage.SEARCH_RESULT__WIKI:
				return getWiki();
			case XwikiPackage.SEARCH_RESULT__SPACE:
				return getSpace();
			case XwikiPackage.SEARCH_RESULT__PAGE_NAME:
				return getPageName();
			case XwikiPackage.SEARCH_RESULT__MODIFIED:
				return getModified();
			case XwikiPackage.SEARCH_RESULT__AUTHOR:
				return getAuthor();
			case XwikiPackage.SEARCH_RESULT__AUTHOR_NAME:
				return getAuthorName();
			case XwikiPackage.SEARCH_RESULT__VERSION:
				return getVersion();
			case XwikiPackage.SEARCH_RESULT__LANGUAGE:
				return getLanguage();
			case XwikiPackage.SEARCH_RESULT__CLASS_NAME:
				return getClassName();
			case XwikiPackage.SEARCH_RESULT__OBJECT_NUMBER:
				return getObjectNumber();
			case XwikiPackage.SEARCH_RESULT__FILENAME:
				return getFilename();
			case XwikiPackage.SEARCH_RESULT__SCORE:
				return getScore();
			case XwikiPackage.SEARCH_RESULT__OBJECT:
				return getObject();
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
			case XwikiPackage.SEARCH_RESULT__TYPE:
				setType((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__ID:
				setId((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__PAGE_FULL_NAME:
				setPageFullName((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__TITLE:
				setTitle((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__WIKI:
				setWiki((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__SPACE:
				setSpace((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__PAGE_NAME:
				setPageName((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__MODIFIED:
				setModified((XMLGregorianCalendar)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__AUTHOR:
				setAuthor((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__AUTHOR_NAME:
				setAuthorName((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__VERSION:
				setVersion((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__OBJECT_NUMBER:
				setObjectNumber((Integer)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__FILENAME:
				setFilename((String)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__SCORE:
				setScore((Float)newValue);
				return;
			case XwikiPackage.SEARCH_RESULT__OBJECT:
				setObject((eu.learnpad.transformations.metamodel_corpus.xwiki.Object)newValue);
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
			case XwikiPackage.SEARCH_RESULT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__ID:
				setId(ID_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__PAGE_FULL_NAME:
				setPageFullName(PAGE_FULL_NAME_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__WIKI:
				setWiki(WIKI_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__SPACE:
				setSpace(SPACE_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__PAGE_NAME:
				setPageName(PAGE_NAME_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__AUTHOR_NAME:
				setAuthorName(AUTHOR_NAME_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__OBJECT_NUMBER:
				unsetObjectNumber();
				return;
			case XwikiPackage.SEARCH_RESULT__FILENAME:
				setFilename(FILENAME_EDEFAULT);
				return;
			case XwikiPackage.SEARCH_RESULT__SCORE:
				unsetScore();
				return;
			case XwikiPackage.SEARCH_RESULT__OBJECT:
				setObject((eu.learnpad.transformations.metamodel_corpus.xwiki.Object)null);
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
			case XwikiPackage.SEARCH_RESULT__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case XwikiPackage.SEARCH_RESULT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XwikiPackage.SEARCH_RESULT__PAGE_FULL_NAME:
				return PAGE_FULL_NAME_EDEFAULT == null ? pageFullName != null : !PAGE_FULL_NAME_EDEFAULT.equals(pageFullName);
			case XwikiPackage.SEARCH_RESULT__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case XwikiPackage.SEARCH_RESULT__WIKI:
				return WIKI_EDEFAULT == null ? wiki != null : !WIKI_EDEFAULT.equals(wiki);
			case XwikiPackage.SEARCH_RESULT__SPACE:
				return SPACE_EDEFAULT == null ? space != null : !SPACE_EDEFAULT.equals(space);
			case XwikiPackage.SEARCH_RESULT__PAGE_NAME:
				return PAGE_NAME_EDEFAULT == null ? pageName != null : !PAGE_NAME_EDEFAULT.equals(pageName);
			case XwikiPackage.SEARCH_RESULT__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case XwikiPackage.SEARCH_RESULT__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case XwikiPackage.SEARCH_RESULT__AUTHOR_NAME:
				return AUTHOR_NAME_EDEFAULT == null ? authorName != null : !AUTHOR_NAME_EDEFAULT.equals(authorName);
			case XwikiPackage.SEARCH_RESULT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case XwikiPackage.SEARCH_RESULT__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case XwikiPackage.SEARCH_RESULT__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case XwikiPackage.SEARCH_RESULT__OBJECT_NUMBER:
				return isSetObjectNumber();
			case XwikiPackage.SEARCH_RESULT__FILENAME:
				return FILENAME_EDEFAULT == null ? filename != null : !FILENAME_EDEFAULT.equals(filename);
			case XwikiPackage.SEARCH_RESULT__SCORE:
				return isSetScore();
			case XwikiPackage.SEARCH_RESULT__OBJECT:
				return object != null;
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
		result.append(" (type: ");
		result.append(type);
		result.append(", id: ");
		result.append(id);
		result.append(", pageFullName: ");
		result.append(pageFullName);
		result.append(", title: ");
		result.append(title);
		result.append(", wiki: ");
		result.append(wiki);
		result.append(", space: ");
		result.append(space);
		result.append(", pageName: ");
		result.append(pageName);
		result.append(", modified: ");
		result.append(modified);
		result.append(", author: ");
		result.append(author);
		result.append(", authorName: ");
		result.append(authorName);
		result.append(", version: ");
		result.append(version);
		result.append(", language: ");
		result.append(language);
		result.append(", className: ");
		result.append(className);
		result.append(", objectNumber: ");
		if (objectNumberESet) result.append(objectNumber); else result.append("<unset>");
		result.append(", filename: ");
		result.append(filename);
		result.append(", score: ");
		if (scoreESet) result.append(score); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //SearchResultImpl
