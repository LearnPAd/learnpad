/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.IREFType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IREF Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl#getTclassname <em>Tclassname</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl#getTmodelname <em>Tmodelname</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl#getTmodeltype <em>Tmodeltype</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl#getTmodelver <em>Tmodelver</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl#getTobjname <em>Tobjname</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IREFTypeImpl extends MinimalEObjectImpl.Container implements IREFType {
	/**
	 * The default value of the '{@link #getTclassname() <em>Tclassname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTclassname()
	 * @generated
	 * @ordered
	 */
	protected static final String TCLASSNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTclassname() <em>Tclassname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTclassname()
	 * @generated
	 * @ordered
	 */
	protected String tclassname = TCLASSNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTmodelname() <em>Tmodelname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTmodelname()
	 * @generated
	 * @ordered
	 */
	protected static final String TMODELNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTmodelname() <em>Tmodelname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTmodelname()
	 * @generated
	 * @ordered
	 */
	protected String tmodelname = TMODELNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTmodeltype() <em>Tmodeltype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTmodeltype()
	 * @generated
	 * @ordered
	 */
	protected static final String TMODELTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTmodeltype() <em>Tmodeltype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTmodeltype()
	 * @generated
	 * @ordered
	 */
	protected String tmodeltype = TMODELTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTmodelver() <em>Tmodelver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTmodelver()
	 * @generated
	 * @ordered
	 */
	protected static final String TMODELVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTmodelver() <em>Tmodelver</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTmodelver()
	 * @generated
	 * @ordered
	 */
	protected String tmodelver = TMODELVER_EDEFAULT;

	/**
	 * The default value of the '{@link #getTobjname() <em>Tobjname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTobjname()
	 * @generated
	 * @ordered
	 */
	protected static final String TOBJNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTobjname() <em>Tobjname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTobjname()
	 * @generated
	 * @ordered
	 */
	protected String tobjname = TOBJNAME_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IREFTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.IREF_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTclassname() {
		return tclassname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTclassname(String newTclassname) {
		String oldTclassname = tclassname;
		tclassname = newTclassname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.IREF_TYPE__TCLASSNAME, oldTclassname, tclassname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTmodelname() {
		return tmodelname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTmodelname(String newTmodelname) {
		String oldTmodelname = tmodelname;
		tmodelname = newTmodelname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.IREF_TYPE__TMODELNAME, oldTmodelname, tmodelname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTmodeltype() {
		return tmodeltype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTmodeltype(String newTmodeltype) {
		String oldTmodeltype = tmodeltype;
		tmodeltype = newTmodeltype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.IREF_TYPE__TMODELTYPE, oldTmodeltype, tmodeltype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTmodelver() {
		return tmodelver;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTmodelver(String newTmodelver) {
		String oldTmodelver = tmodelver;
		tmodelver = newTmodelver;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.IREF_TYPE__TMODELVER, oldTmodelver, tmodelver));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTobjname() {
		return tobjname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTobjname(String newTobjname) {
		String oldTobjname = tobjname;
		tobjname = newTobjname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.IREF_TYPE__TOBJNAME, oldTobjname, tobjname));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.IREF_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AdoPackage.IREF_TYPE__TCLASSNAME:
				return getTclassname();
			case AdoPackage.IREF_TYPE__TMODELNAME:
				return getTmodelname();
			case AdoPackage.IREF_TYPE__TMODELTYPE:
				return getTmodeltype();
			case AdoPackage.IREF_TYPE__TMODELVER:
				return getTmodelver();
			case AdoPackage.IREF_TYPE__TOBJNAME:
				return getTobjname();
			case AdoPackage.IREF_TYPE__TYPE:
				return getType();
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
			case AdoPackage.IREF_TYPE__TCLASSNAME:
				setTclassname((String)newValue);
				return;
			case AdoPackage.IREF_TYPE__TMODELNAME:
				setTmodelname((String)newValue);
				return;
			case AdoPackage.IREF_TYPE__TMODELTYPE:
				setTmodeltype((String)newValue);
				return;
			case AdoPackage.IREF_TYPE__TMODELVER:
				setTmodelver((String)newValue);
				return;
			case AdoPackage.IREF_TYPE__TOBJNAME:
				setTobjname((String)newValue);
				return;
			case AdoPackage.IREF_TYPE__TYPE:
				setType((String)newValue);
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
			case AdoPackage.IREF_TYPE__TCLASSNAME:
				setTclassname(TCLASSNAME_EDEFAULT);
				return;
			case AdoPackage.IREF_TYPE__TMODELNAME:
				setTmodelname(TMODELNAME_EDEFAULT);
				return;
			case AdoPackage.IREF_TYPE__TMODELTYPE:
				setTmodeltype(TMODELTYPE_EDEFAULT);
				return;
			case AdoPackage.IREF_TYPE__TMODELVER:
				setTmodelver(TMODELVER_EDEFAULT);
				return;
			case AdoPackage.IREF_TYPE__TOBJNAME:
				setTobjname(TOBJNAME_EDEFAULT);
				return;
			case AdoPackage.IREF_TYPE__TYPE:
				setType(TYPE_EDEFAULT);
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
			case AdoPackage.IREF_TYPE__TCLASSNAME:
				return TCLASSNAME_EDEFAULT == null ? tclassname != null : !TCLASSNAME_EDEFAULT.equals(tclassname);
			case AdoPackage.IREF_TYPE__TMODELNAME:
				return TMODELNAME_EDEFAULT == null ? tmodelname != null : !TMODELNAME_EDEFAULT.equals(tmodelname);
			case AdoPackage.IREF_TYPE__TMODELTYPE:
				return TMODELTYPE_EDEFAULT == null ? tmodeltype != null : !TMODELTYPE_EDEFAULT.equals(tmodeltype);
			case AdoPackage.IREF_TYPE__TMODELVER:
				return TMODELVER_EDEFAULT == null ? tmodelver != null : !TMODELVER_EDEFAULT.equals(tmodelver);
			case AdoPackage.IREF_TYPE__TOBJNAME:
				return TOBJNAME_EDEFAULT == null ? tobjname != null : !TOBJNAME_EDEFAULT.equals(tobjname);
			case AdoPackage.IREF_TYPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
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
		result.append(" (tclassname: ");
		result.append(tclassname);
		result.append(", tmodelname: ");
		result.append(tmodelname);
		result.append(", tmodeltype: ");
		result.append(tmodeltype);
		result.append(", tmodelver: ");
		result.append(tmodelver);
		result.append(", tobjname: ");
		result.append(tobjname);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //IREFTypeImpl
