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

import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MODELGROUP Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPTypeImpl#getMODELREFERENCE <em>MODELREFERENCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPTypeImpl#getMODELGROUP <em>MODELGROUP</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MODELGROUPTypeImpl extends MinimalEObjectImpl.Container implements MODELGROUPType {
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
	 * The cached value of the '{@link #getMODELGROUP() <em>MODELGROUP</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMODELGROUP()
	 * @generated
	 * @ordered
	 */
	protected EList<MODELGROUPType> mODELGROUP;

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
	protected MODELGROUPTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.MODELGROUP_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MODELREFERENCEType> getMODELREFERENCE() {
		if (mODELREFERENCE == null) {
			mODELREFERENCE = new EObjectContainmentEList<MODELREFERENCEType>(MODELREFERENCEType.class, this, AdoPackage.MODELGROUP_TYPE__MODELREFERENCE);
		}
		return mODELREFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MODELGROUPType> getMODELGROUP() {
		if (mODELGROUP == null) {
			mODELGROUP = new EObjectContainmentEList<MODELGROUPType>(MODELGROUPType.class, this, AdoPackage.MODELGROUP_TYPE__MODELGROUP);
		}
		return mODELGROUP;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODELGROUP_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.MODELGROUP_TYPE__MODELREFERENCE:
				return ((InternalEList<?>)getMODELREFERENCE()).basicRemove(otherEnd, msgs);
			case AdoPackage.MODELGROUP_TYPE__MODELGROUP:
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
			case AdoPackage.MODELGROUP_TYPE__MODELREFERENCE:
				return getMODELREFERENCE();
			case AdoPackage.MODELGROUP_TYPE__MODELGROUP:
				return getMODELGROUP();
			case AdoPackage.MODELGROUP_TYPE__NAME:
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
			case AdoPackage.MODELGROUP_TYPE__MODELREFERENCE:
				getMODELREFERENCE().clear();
				getMODELREFERENCE().addAll((Collection<? extends MODELREFERENCEType>)newValue);
				return;
			case AdoPackage.MODELGROUP_TYPE__MODELGROUP:
				getMODELGROUP().clear();
				getMODELGROUP().addAll((Collection<? extends MODELGROUPType>)newValue);
				return;
			case AdoPackage.MODELGROUP_TYPE__NAME:
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
			case AdoPackage.MODELGROUP_TYPE__MODELREFERENCE:
				getMODELREFERENCE().clear();
				return;
			case AdoPackage.MODELGROUP_TYPE__MODELGROUP:
				getMODELGROUP().clear();
				return;
			case AdoPackage.MODELGROUP_TYPE__NAME:
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
			case AdoPackage.MODELGROUP_TYPE__MODELREFERENCE:
				return mODELREFERENCE != null && !mODELREFERENCE.isEmpty();
			case AdoPackage.MODELGROUP_TYPE__MODELGROUP:
				return mODELGROUP != null && !mODELGROUP.isEmpty();
			case AdoPackage.MODELGROUP_TYPE__NAME:
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //MODELGROUPTypeImpl
