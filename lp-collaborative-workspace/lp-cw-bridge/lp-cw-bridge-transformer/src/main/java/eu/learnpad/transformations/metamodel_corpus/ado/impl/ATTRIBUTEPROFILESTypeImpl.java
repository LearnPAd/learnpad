/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ATTRIBUTEPROFILES Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILESTypeImpl#getATTRPROFDIR <em>ATTRPROFDIR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILESTypeImpl#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ATTRIBUTEPROFILESTypeImpl extends MinimalEObjectImpl.Container implements ATTRIBUTEPROFILESType {
	/**
	 * The cached value of the '{@link #getATTRPROFDIR() <em>ATTRPROFDIR</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATTRPROFDIR()
	 * @generated
	 * @ordered
	 */
	protected ATTRPROFDIRType aTTRPROFDIR;

	/**
	 * The cached value of the '{@link #getATTRIBUTEPROFILE() <em>ATTRIBUTEPROFILE</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATTRIBUTEPROFILE()
	 * @generated
	 * @ordered
	 */
	protected ATTRIBUTEPROFILEType aTTRIBUTEPROFILE;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ATTRIBUTEPROFILESTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.ATTRIBUTEPROFILES_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRPROFDIRType getATTRPROFDIR() {
		return aTTRPROFDIR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetATTRPROFDIR(ATTRPROFDIRType newATTRPROFDIR, NotificationChain msgs) {
		ATTRPROFDIRType oldATTRPROFDIR = aTTRPROFDIR;
		aTTRPROFDIR = newATTRPROFDIR;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR, oldATTRPROFDIR, newATTRPROFDIR);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setATTRPROFDIR(ATTRPROFDIRType newATTRPROFDIR) {
		if (newATTRPROFDIR != aTTRPROFDIR) {
			NotificationChain msgs = null;
			if (aTTRPROFDIR != null)
				msgs = ((InternalEObject)aTTRPROFDIR).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR, null, msgs);
			if (newATTRPROFDIR != null)
				msgs = ((InternalEObject)newATTRPROFDIR).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR, null, msgs);
			msgs = basicSetATTRPROFDIR(newATTRPROFDIR, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR, newATTRPROFDIR, newATTRPROFDIR));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEPROFILEType getATTRIBUTEPROFILE() {
		return aTTRIBUTEPROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetATTRIBUTEPROFILE(ATTRIBUTEPROFILEType newATTRIBUTEPROFILE, NotificationChain msgs) {
		ATTRIBUTEPROFILEType oldATTRIBUTEPROFILE = aTTRIBUTEPROFILE;
		aTTRIBUTEPROFILE = newATTRIBUTEPROFILE;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE, oldATTRIBUTEPROFILE, newATTRIBUTEPROFILE);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setATTRIBUTEPROFILE(ATTRIBUTEPROFILEType newATTRIBUTEPROFILE) {
		if (newATTRIBUTEPROFILE != aTTRIBUTEPROFILE) {
			NotificationChain msgs = null;
			if (aTTRIBUTEPROFILE != null)
				msgs = ((InternalEObject)aTTRIBUTEPROFILE).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE, null, msgs);
			if (newATTRIBUTEPROFILE != null)
				msgs = ((InternalEObject)newATTRIBUTEPROFILE).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE, null, msgs);
			msgs = basicSetATTRIBUTEPROFILE(newATTRIBUTEPROFILE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE, newATTRIBUTEPROFILE, newATTRIBUTEPROFILE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR:
				return basicSetATTRPROFDIR(null, msgs);
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE:
				return basicSetATTRIBUTEPROFILE(null, msgs);
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
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR:
				return getATTRPROFDIR();
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE:
				return getATTRIBUTEPROFILE();
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
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR:
				setATTRPROFDIR((ATTRPROFDIRType)newValue);
				return;
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE:
				setATTRIBUTEPROFILE((ATTRIBUTEPROFILEType)newValue);
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
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR:
				setATTRPROFDIR((ATTRPROFDIRType)null);
				return;
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE:
				setATTRIBUTEPROFILE((ATTRIBUTEPROFILEType)null);
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
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR:
				return aTTRPROFDIR != null;
			case AdoPackage.ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE:
				return aTTRIBUTEPROFILE != null;
		}
		return super.eIsSet(featureID);
	}

} //ATTRIBUTEPROFILESTypeImpl
