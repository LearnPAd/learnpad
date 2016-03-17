/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELSType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MODELS Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELSTypeImpl#getMODEL <em>MODEL</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MODELSTypeImpl extends MinimalEObjectImpl.Container implements MODELSType {
	/**
	 * The cached value of the '{@link #getMODEL() <em>MODEL</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMODEL()
	 * @generated
	 * @ordered
	 */
	protected EList<MODELType> mODEL;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MODELSTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.MODELS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MODELType> getMODEL() {
		if (mODEL == null) {
			mODEL = new EObjectContainmentEList<MODELType>(MODELType.class, this, AdoPackage.MODELS_TYPE__MODEL);
		}
		return mODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.MODELS_TYPE__MODEL:
				return ((InternalEList<?>)getMODEL()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.MODELS_TYPE__MODEL:
				return getMODEL();
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
			case AdoPackage.MODELS_TYPE__MODEL:
				getMODEL().clear();
				getMODEL().addAll((Collection<? extends MODELType>)newValue);
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
			case AdoPackage.MODELS_TYPE__MODEL:
				getMODEL().clear();
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
			case AdoPackage.MODELS_TYPE__MODEL:
				return mODEL != null && !mODEL.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MODELSTypeImpl
