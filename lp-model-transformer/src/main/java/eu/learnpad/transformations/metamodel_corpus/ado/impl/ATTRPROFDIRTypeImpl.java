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

import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ATTRPROFDIR Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRPROFDIRTypeImpl#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRPROFDIRTypeImpl#getATTRPROFDIR <em>ATTRPROFDIR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRPROFDIRTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ATTRPROFDIRTypeImpl extends MinimalEObjectImpl.Container implements ATTRPROFDIRType {
	/**
	 * The cached value of the '{@link #getATTRIBUTEPROFILE() <em>ATTRIBUTEPROFILE</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATTRIBUTEPROFILE()
	 * @generated
	 * @ordered
	 */
	protected EList<ATTRIBUTEPROFILEType> aTTRIBUTEPROFILE;

	/**
	 * The cached value of the '{@link #getATTRPROFDIR() <em>ATTRPROFDIR</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATTRPROFDIR()
	 * @generated
	 * @ordered
	 */
	protected EList<ATTRPROFDIRType> aTTRPROFDIR;

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
	protected ATTRPROFDIRTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.ATTRPROFDIR_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ATTRIBUTEPROFILEType> getATTRIBUTEPROFILE() {
		if (aTTRIBUTEPROFILE == null) {
			aTTRIBUTEPROFILE = new EObjectContainmentEList<ATTRIBUTEPROFILEType>(ATTRIBUTEPROFILEType.class, this, AdoPackage.ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE);
		}
		return aTTRIBUTEPROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ATTRPROFDIRType> getATTRPROFDIR() {
		if (aTTRPROFDIR == null) {
			aTTRPROFDIR = new EObjectContainmentEList<ATTRPROFDIRType>(ATTRPROFDIRType.class, this, AdoPackage.ATTRPROFDIR_TYPE__ATTRPROFDIR);
		}
		return aTTRPROFDIR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ATTRPROFDIR_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE:
				return ((InternalEList<?>)getATTRIBUTEPROFILE()).basicRemove(otherEnd, msgs);
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRPROFDIR:
				return ((InternalEList<?>)getATTRPROFDIR()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE:
				return getATTRIBUTEPROFILE();
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRPROFDIR:
				return getATTRPROFDIR();
			case AdoPackage.ATTRPROFDIR_TYPE__NAME:
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
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE:
				getATTRIBUTEPROFILE().clear();
				getATTRIBUTEPROFILE().addAll((Collection<? extends ATTRIBUTEPROFILEType>)newValue);
				return;
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRPROFDIR:
				getATTRPROFDIR().clear();
				getATTRPROFDIR().addAll((Collection<? extends ATTRPROFDIRType>)newValue);
				return;
			case AdoPackage.ATTRPROFDIR_TYPE__NAME:
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
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE:
				getATTRIBUTEPROFILE().clear();
				return;
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRPROFDIR:
				getATTRPROFDIR().clear();
				return;
			case AdoPackage.ATTRPROFDIR_TYPE__NAME:
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
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE:
				return aTTRIBUTEPROFILE != null && !aTTRIBUTEPROFILE.isEmpty();
			case AdoPackage.ATTRPROFDIR_TYPE__ATTRPROFDIR:
				return aTTRPROFDIR != null && !aTTRPROFDIR.isEmpty();
			case AdoPackage.ATTRPROFDIR_TYPE__NAME:
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

} //ATTRPROFDIRTypeImpl
