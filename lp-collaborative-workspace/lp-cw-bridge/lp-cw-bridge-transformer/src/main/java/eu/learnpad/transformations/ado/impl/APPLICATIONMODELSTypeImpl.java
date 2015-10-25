/**
 */
package eu.learnpad.transformations.ado.impl;

import eu.learnpad.transformations.ado.APPLICATIONMODELSType;
import eu.learnpad.transformations.ado.APPLICATIONMODELType;
import eu.learnpad.transformations.ado.AdoPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>APPLICATIONMODELS Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.ado.impl.APPLICATIONMODELSTypeImpl#getAPPLICATIONMODEL <em>APPLICATIONMODEL</em>}</li>
 * </ul>
 *
 * @generated
 */
public class APPLICATIONMODELSTypeImpl extends MinimalEObjectImpl.Container implements APPLICATIONMODELSType {
	/**
	 * The cached value of the '{@link #getAPPLICATIONMODEL() <em>APPLICATIONMODEL</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAPPLICATIONMODEL()
	 * @generated
	 * @ordered
	 */
	protected EList<APPLICATIONMODELType> aPPLICATIONMODEL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected APPLICATIONMODELSTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.APPLICATIONMODELS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<APPLICATIONMODELType> getAPPLICATIONMODEL() {
		if (aPPLICATIONMODEL == null) {
			aPPLICATIONMODEL = new EObjectContainmentEList<APPLICATIONMODELType>(APPLICATIONMODELType.class, this, AdoPackage.APPLICATIONMODELS_TYPE__APPLICATIONMODEL);
		}
		return aPPLICATIONMODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.APPLICATIONMODELS_TYPE__APPLICATIONMODEL:
				return ((InternalEList<?>)getAPPLICATIONMODEL()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.APPLICATIONMODELS_TYPE__APPLICATIONMODEL:
				return getAPPLICATIONMODEL();
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
			case AdoPackage.APPLICATIONMODELS_TYPE__APPLICATIONMODEL:
				getAPPLICATIONMODEL().clear();
				getAPPLICATIONMODEL().addAll((Collection<? extends APPLICATIONMODELType>)newValue);
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
			case AdoPackage.APPLICATIONMODELS_TYPE__APPLICATIONMODEL:
				getAPPLICATIONMODEL().clear();
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
			case AdoPackage.APPLICATIONMODELS_TYPE__APPLICATIONMODEL:
				return aPPLICATIONMODEL != null && !aPPLICATIONMODEL.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //APPLICATIONMODELSTypeImpl
