/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MODELREFERENCE Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl#getLibtype <em>Libtype</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl#getModeltype <em>Modeltype</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MODELREFERENCETypeImpl extends MinimalEObjectImpl.Container implements MODELREFERENCEType {
	/**
	 * The default value of the '{@link #getLibtype() <em>Libtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibtype()
	 * @generated
	 * @ordered
	 */
	protected static final String LIBTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLibtype() <em>Libtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibtype()
	 * @generated
	 * @ordered
	 */
	protected String libtype = LIBTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getModeltype() <em>Modeltype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeltype()
	 * @generated
	 * @ordered
	 */
	protected static final String MODELTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModeltype() <em>Modeltype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModeltype()
	 * @generated
	 * @ordered
	 */
	protected String modeltype = MODELTYPE_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MODELREFERENCETypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.MODELREFERENCE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLibtype() {
		return libtype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLibtype(String newLibtype) {
		String oldLibtype = libtype;
		libtype = newLibtype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODELREFERENCE_TYPE__LIBTYPE, oldLibtype, libtype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModeltype() {
		return modeltype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModeltype(String newModeltype) {
		String oldModeltype = modeltype;
		modeltype = newModeltype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODELREFERENCE_TYPE__MODELTYPE, oldModeltype, modeltype));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODELREFERENCE_TYPE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODELREFERENCE_TYPE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AdoPackage.MODELREFERENCE_TYPE__LIBTYPE:
				return getLibtype();
			case AdoPackage.MODELREFERENCE_TYPE__MODELTYPE:
				return getModeltype();
			case AdoPackage.MODELREFERENCE_TYPE__NAME:
				return getName();
			case AdoPackage.MODELREFERENCE_TYPE__VERSION:
				return getVersion();
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
			case AdoPackage.MODELREFERENCE_TYPE__LIBTYPE:
				setLibtype((String)newValue);
				return;
			case AdoPackage.MODELREFERENCE_TYPE__MODELTYPE:
				setModeltype((String)newValue);
				return;
			case AdoPackage.MODELREFERENCE_TYPE__NAME:
				setName((String)newValue);
				return;
			case AdoPackage.MODELREFERENCE_TYPE__VERSION:
				setVersion((String)newValue);
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
			case AdoPackage.MODELREFERENCE_TYPE__LIBTYPE:
				setLibtype(LIBTYPE_EDEFAULT);
				return;
			case AdoPackage.MODELREFERENCE_TYPE__MODELTYPE:
				setModeltype(MODELTYPE_EDEFAULT);
				return;
			case AdoPackage.MODELREFERENCE_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AdoPackage.MODELREFERENCE_TYPE__VERSION:
				setVersion(VERSION_EDEFAULT);
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
			case AdoPackage.MODELREFERENCE_TYPE__LIBTYPE:
				return LIBTYPE_EDEFAULT == null ? libtype != null : !LIBTYPE_EDEFAULT.equals(libtype);
			case AdoPackage.MODELREFERENCE_TYPE__MODELTYPE:
				return MODELTYPE_EDEFAULT == null ? modeltype != null : !MODELTYPE_EDEFAULT.equals(modeltype);
			case AdoPackage.MODELREFERENCE_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AdoPackage.MODELREFERENCE_TYPE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
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
		result.append(" (libtype: ");
		result.append(libtype);
		result.append(", modeltype: ");
		result.append(modeltype);
		result.append(", name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //MODELREFERENCETypeImpl
