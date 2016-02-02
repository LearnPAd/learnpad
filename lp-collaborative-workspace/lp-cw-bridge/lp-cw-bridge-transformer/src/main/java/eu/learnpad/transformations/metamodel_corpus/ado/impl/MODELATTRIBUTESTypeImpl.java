/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType;
import eu.learnpad.transformations.metamodel_corpus.ado.RECORDType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MODELATTRIBUTES Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELATTRIBUTESTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELATTRIBUTESTypeImpl#getATTRIBUTE <em>ATTRIBUTE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELATTRIBUTESTypeImpl#getRECORD <em>RECORD</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MODELATTRIBUTESTypeImpl extends MinimalEObjectImpl.Container implements MODELATTRIBUTESType {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MODELATTRIBUTESTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.MODELATTRIBUTES_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, AdoPackage.MODELATTRIBUTES_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ATTRIBUTEType> getATTRIBUTE() {
		return getGroup().list(AdoPackage.Literals.MODELATTRIBUTES_TYPE__ATTRIBUTE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RECORDType> getRECORD() {
		return getGroup().list(AdoPackage.Literals.MODELATTRIBUTES_TYPE__RECORD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.MODELATTRIBUTES_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case AdoPackage.MODELATTRIBUTES_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getATTRIBUTE()).basicRemove(otherEnd, msgs);
			case AdoPackage.MODELATTRIBUTES_TYPE__RECORD:
				return ((InternalEList<?>)getRECORD()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.MODELATTRIBUTES_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case AdoPackage.MODELATTRIBUTES_TYPE__ATTRIBUTE:
				return getATTRIBUTE();
			case AdoPackage.MODELATTRIBUTES_TYPE__RECORD:
				return getRECORD();
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
			case AdoPackage.MODELATTRIBUTES_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case AdoPackage.MODELATTRIBUTES_TYPE__ATTRIBUTE:
				getATTRIBUTE().clear();
				getATTRIBUTE().addAll((Collection<? extends ATTRIBUTEType>)newValue);
				return;
			case AdoPackage.MODELATTRIBUTES_TYPE__RECORD:
				getRECORD().clear();
				getRECORD().addAll((Collection<? extends RECORDType>)newValue);
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
			case AdoPackage.MODELATTRIBUTES_TYPE__GROUP:
				getGroup().clear();
				return;
			case AdoPackage.MODELATTRIBUTES_TYPE__ATTRIBUTE:
				getATTRIBUTE().clear();
				return;
			case AdoPackage.MODELATTRIBUTES_TYPE__RECORD:
				getRECORD().clear();
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
			case AdoPackage.MODELATTRIBUTES_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case AdoPackage.MODELATTRIBUTES_TYPE__ATTRIBUTE:
				return !getATTRIBUTE().isEmpty();
			case AdoPackage.MODELATTRIBUTES_TYPE__RECORD:
				return !getRECORD().isEmpty();
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
		result.append(" (group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //MODELATTRIBUTESTypeImpl
