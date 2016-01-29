/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>APPLICATIONMODEL Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELTypeImpl#getMODELREFERENCE <em>MODELREFERENCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELTypeImpl#getApplib <em>Applib</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class APPLICATIONMODELTypeImpl extends MinimalEObjectImpl.Container implements APPLICATIONMODELType {
	/**
	 * The cached value of the '{@link #getMODELREFERENCE() <em>MODELREFERENCE</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMODELREFERENCE()
	 * @generated
	 * @ordered
	 */
	protected EList<MODELREFERENCEType> mODELREFERENCE;

	/**
	 * The default value of the '{@link #getApplib() <em>Applib</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplib()
	 * @generated
	 * @ordered
	 */
	protected static final String APPLIB_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getApplib() <em>Applib</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplib()
	 * @generated
	 * @ordered
	 */
	protected String applib = APPLIB_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected APPLICATIONMODELTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.APPLICATIONMODEL_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MODELREFERENCEType> getMODELREFERENCE() {
		if (mODELREFERENCE == null) {
			mODELREFERENCE = new EObjectContainmentEList<MODELREFERENCEType>(MODELREFERENCEType.class, this, AdoPackage.APPLICATIONMODEL_TYPE__MODELREFERENCE);
		}
		return mODELREFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getApplib() {
		return applib;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplib(String newApplib) {
		String oldApplib = applib;
		applib = newApplib;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.APPLICATIONMODEL_TYPE__APPLIB, oldApplib, applib));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.APPLICATIONMODEL_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.APPLICATIONMODEL_TYPE__MODELREFERENCE:
				return ((InternalEList<?>)getMODELREFERENCE()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.APPLICATIONMODEL_TYPE__MODELREFERENCE:
				return getMODELREFERENCE();
			case AdoPackage.APPLICATIONMODEL_TYPE__APPLIB:
				return getApplib();
			case AdoPackage.APPLICATIONMODEL_TYPE__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AdoPackage.APPLICATIONMODEL_TYPE__MODELREFERENCE:
				getMODELREFERENCE().clear();
				getMODELREFERENCE().addAll((Collection<? extends MODELREFERENCEType>)newValue);
				return;
			case AdoPackage.APPLICATIONMODEL_TYPE__APPLIB:
				setApplib((String)newValue);
				return;
			case AdoPackage.APPLICATIONMODEL_TYPE__NAME:
				setName((String)newValue);
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
			case AdoPackage.APPLICATIONMODEL_TYPE__MODELREFERENCE:
				getMODELREFERENCE().clear();
				return;
			case AdoPackage.APPLICATIONMODEL_TYPE__APPLIB:
				setApplib(APPLIB_EDEFAULT);
				return;
			case AdoPackage.APPLICATIONMODEL_TYPE__NAME:
				setName(NAME_EDEFAULT);
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
			case AdoPackage.APPLICATIONMODEL_TYPE__MODELREFERENCE:
				return mODELREFERENCE != null && !mODELREFERENCE.isEmpty();
			case AdoPackage.APPLICATIONMODEL_TYPE__APPLIB:
				return APPLIB_EDEFAULT == null ? applib != null : !APPLIB_EDEFAULT.equals(applib);
			case AdoPackage.APPLICATIONMODEL_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (applib: ");
		result.append(applib);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //APPLICATIONMODELTypeImpl
