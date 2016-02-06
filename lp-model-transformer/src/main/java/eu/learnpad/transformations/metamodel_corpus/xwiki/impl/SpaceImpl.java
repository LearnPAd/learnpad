/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.Space;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Space</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl#getHome <em>Home</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SpaceImpl extends LinkCollectionImpl implements Space {
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
	 * The default value of the '{@link #getHome() <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHome()
	 * @generated
	 * @ordered
	 */
	protected static final String HOME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHome() <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHome()
	 * @generated
	 * @ordered
	 */
	protected String home = HOME_EDEFAULT;

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
	protected SpaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.SPACE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SPACE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SPACE__WIKI, oldWiki, wiki));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SPACE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHome() {
		return home;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHome(String newHome) {
		String oldHome = home;
		home = newHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SPACE__HOME, oldHome, home));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SPACE__XWIKI_RELATIVE_URL, oldXwikiRelativeUrl, xwikiRelativeUrl));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.SPACE__XWIKI_ABSOLUTE_URL, oldXwikiAbsoluteUrl, xwikiAbsoluteUrl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XwikiPackage.SPACE__ID:
				return getId();
			case XwikiPackage.SPACE__WIKI:
				return getWiki();
			case XwikiPackage.SPACE__NAME:
				return getName();
			case XwikiPackage.SPACE__HOME:
				return getHome();
			case XwikiPackage.SPACE__XWIKI_RELATIVE_URL:
				return getXwikiRelativeUrl();
			case XwikiPackage.SPACE__XWIKI_ABSOLUTE_URL:
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
			case XwikiPackage.SPACE__ID:
				setId((String)newValue);
				return;
			case XwikiPackage.SPACE__WIKI:
				setWiki((String)newValue);
				return;
			case XwikiPackage.SPACE__NAME:
				setName((String)newValue);
				return;
			case XwikiPackage.SPACE__HOME:
				setHome((String)newValue);
				return;
			case XwikiPackage.SPACE__XWIKI_RELATIVE_URL:
				setXwikiRelativeUrl((String)newValue);
				return;
			case XwikiPackage.SPACE__XWIKI_ABSOLUTE_URL:
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
			case XwikiPackage.SPACE__ID:
				setId(ID_EDEFAULT);
				return;
			case XwikiPackage.SPACE__WIKI:
				setWiki(WIKI_EDEFAULT);
				return;
			case XwikiPackage.SPACE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case XwikiPackage.SPACE__HOME:
				setHome(HOME_EDEFAULT);
				return;
			case XwikiPackage.SPACE__XWIKI_RELATIVE_URL:
				setXwikiRelativeUrl(XWIKI_RELATIVE_URL_EDEFAULT);
				return;
			case XwikiPackage.SPACE__XWIKI_ABSOLUTE_URL:
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
			case XwikiPackage.SPACE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XwikiPackage.SPACE__WIKI:
				return WIKI_EDEFAULT == null ? wiki != null : !WIKI_EDEFAULT.equals(wiki);
			case XwikiPackage.SPACE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case XwikiPackage.SPACE__HOME:
				return HOME_EDEFAULT == null ? home != null : !HOME_EDEFAULT.equals(home);
			case XwikiPackage.SPACE__XWIKI_RELATIVE_URL:
				return XWIKI_RELATIVE_URL_EDEFAULT == null ? xwikiRelativeUrl != null : !XWIKI_RELATIVE_URL_EDEFAULT.equals(xwikiRelativeUrl);
			case XwikiPackage.SPACE__XWIKI_ABSOLUTE_URL:
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
		result.append(", wiki: ");
		result.append(wiki);
		result.append(", name: ");
		result.append(name);
		result.append(", home: ");
		result.append(home);
		result.append(", xwikiRelativeUrl: ");
		result.append(xwikiRelativeUrl);
		result.append(", xwikiAbsoluteUrl: ");
		result.append(xwikiAbsoluteUrl);
		result.append(')');
		return result.toString();
	}

} //SpaceImpl
