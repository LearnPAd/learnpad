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
import eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MODELGROUPS Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPSTypeImpl#getMODELGROUP <em>MODELGROUP</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MODELGROUPSTypeImpl extends MinimalEObjectImpl.Container implements MODELGROUPSType {
	/**
	 * The cached value of the '{@link #getMODELGROUP() <em>MODELGROUP</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMODELGROUP()
	 * @generated
	 * @ordered
	 */
	protected EList<MODELGROUPType> mODELGROUP;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MODELGROUPSTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.MODELGROUPS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MODELGROUPType> getMODELGROUP() {
		if (mODELGROUP == null) {
			mODELGROUP = new EObjectContainmentEList<MODELGROUPType>(MODELGROUPType.class, this, AdoPackage.MODELGROUPS_TYPE__MODELGROUP);
		}
		return mODELGROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.MODELGROUPS_TYPE__MODELGROUP:
				return ((InternalEList<?>)getMODELGROUP()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.MODELGROUPS_TYPE__MODELGROUP:
				return getMODELGROUP();
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
			case AdoPackage.MODELGROUPS_TYPE__MODELGROUP:
				getMODELGROUP().clear();
				getMODELGROUP().addAll((Collection<? extends MODELGROUPType>)newValue);
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
			case AdoPackage.MODELGROUPS_TYPE__MODELGROUP:
				getMODELGROUP().clear();
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
			case AdoPackage.MODELGROUPS_TYPE__MODELGROUP:
				return mODELGROUP != null && !mODELGROUP.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MODELGROUPSTypeImpl
