/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XWiki</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XWikiImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XWikiImpl#getSyntaxes <em>Syntaxes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XWikiImpl extends LinkCollectionImpl implements XWiki {
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
	 * The cached value of the '{@link #getSyntaxes() <em>Syntaxes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSyntaxes()
	 * @generated
	 * @ordered
	 */
	protected Syntaxes syntaxes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XWikiImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.XWIKI;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.XWIKI__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Syntaxes getSyntaxes() {
		return syntaxes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSyntaxes(Syntaxes newSyntaxes, NotificationChain msgs) {
		Syntaxes oldSyntaxes = syntaxes;
		syntaxes = newSyntaxes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwikiPackage.XWIKI__SYNTAXES, oldSyntaxes, newSyntaxes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSyntaxes(Syntaxes newSyntaxes) {
		if (newSyntaxes != syntaxes) {
			NotificationChain msgs = null;
			if (syntaxes != null)
				msgs = ((InternalEObject)syntaxes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XwikiPackage.XWIKI__SYNTAXES, null, msgs);
			if (newSyntaxes != null)
				msgs = ((InternalEObject)newSyntaxes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XwikiPackage.XWIKI__SYNTAXES, null, msgs);
			msgs = basicSetSyntaxes(newSyntaxes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XwikiPackage.XWIKI__SYNTAXES, newSyntaxes, newSyntaxes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XwikiPackage.XWIKI__SYNTAXES:
				return basicSetSyntaxes(null, msgs);
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
			case XwikiPackage.XWIKI__VERSION:
				return getVersion();
			case XwikiPackage.XWIKI__SYNTAXES:
				return getSyntaxes();
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
			case XwikiPackage.XWIKI__VERSION:
				setVersion((String)newValue);
				return;
			case XwikiPackage.XWIKI__SYNTAXES:
				setSyntaxes((Syntaxes)newValue);
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
			case XwikiPackage.XWIKI__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case XwikiPackage.XWIKI__SYNTAXES:
				setSyntaxes((Syntaxes)null);
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
			case XwikiPackage.XWIKI__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case XwikiPackage.XWIKI__SYNTAXES:
				return syntaxes != null;
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
		result.append(" (version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //XWikiImpl
