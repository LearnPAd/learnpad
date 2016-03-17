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
import eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType;
import eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>MODEL Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getINSTANCE <em>INSTANCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getCONNECTOR <em>CONNECTOR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getApplib <em>Applib</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getLibtype <em>Libtype</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getModeltype <em>Modeltype</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MODELTypeImpl extends MinimalEObjectImpl.Container implements MODELType {
	/**
	 * The cached value of the '{@link #getMODELATTRIBUTES() <em>MODELATTRIBUTES</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMODELATTRIBUTES()
	 * @generated
	 * @ordered
	 */
	protected MODELATTRIBUTESType mODELATTRIBUTES;

	/**
	 * The cached value of the '{@link #getINSTANCE() <em>INSTANCE</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getINSTANCE()
	 * @generated
	 * @ordered
	 */
	protected EList<INSTANCEType> iNSTANCE;

	/**
	 * The cached value of the '{@link #getCONNECTOR() <em>CONNECTOR</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCONNECTOR()
	 * @generated
	 * @ordered
	 */
	protected EList<CONNECTORType> cONNECTOR;

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
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	protected MODELTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.MODEL_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELATTRIBUTESType getMODELATTRIBUTES() {
		return mODELATTRIBUTES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELATTRIBUTES(MODELATTRIBUTESType newMODELATTRIBUTES, NotificationChain msgs) {
		MODELATTRIBUTESType oldMODELATTRIBUTES = mODELATTRIBUTES;
		mODELATTRIBUTES = newMODELATTRIBUTES;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__MODELATTRIBUTES, oldMODELATTRIBUTES, newMODELATTRIBUTES);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELATTRIBUTES(MODELATTRIBUTESType newMODELATTRIBUTES) {
		if (newMODELATTRIBUTES != mODELATTRIBUTES) {
			NotificationChain msgs = null;
			if (mODELATTRIBUTES != null)
				msgs = ((InternalEObject)mODELATTRIBUTES).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdoPackage.MODEL_TYPE__MODELATTRIBUTES, null, msgs);
			if (newMODELATTRIBUTES != null)
				msgs = ((InternalEObject)newMODELATTRIBUTES).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdoPackage.MODEL_TYPE__MODELATTRIBUTES, null, msgs);
			msgs = basicSetMODELATTRIBUTES(newMODELATTRIBUTES, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__MODELATTRIBUTES, newMODELATTRIBUTES, newMODELATTRIBUTES));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<INSTANCEType> getINSTANCE() {
		if (iNSTANCE == null) {
			iNSTANCE = new EObjectContainmentEList<INSTANCEType>(INSTANCEType.class, this, AdoPackage.MODEL_TYPE__INSTANCE);
		}
		return iNSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CONNECTORType> getCONNECTOR() {
		if (cONNECTOR == null) {
			cONNECTOR = new EObjectContainmentEList<CONNECTORType>(CONNECTORType.class, this, AdoPackage.MODEL_TYPE__CONNECTOR);
		}
		return cONNECTOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__APPLIB, oldApplib, applib));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__LIBTYPE, oldLibtype, libtype));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__MODELTYPE, oldModeltype, modeltype));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.MODEL_TYPE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.MODEL_TYPE__MODELATTRIBUTES:
				return basicSetMODELATTRIBUTES(null, msgs);
			case AdoPackage.MODEL_TYPE__INSTANCE:
				return ((InternalEList<?>)getINSTANCE()).basicRemove(otherEnd, msgs);
			case AdoPackage.MODEL_TYPE__CONNECTOR:
				return ((InternalEList<?>)getCONNECTOR()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.MODEL_TYPE__MODELATTRIBUTES:
				return getMODELATTRIBUTES();
			case AdoPackage.MODEL_TYPE__INSTANCE:
				return getINSTANCE();
			case AdoPackage.MODEL_TYPE__CONNECTOR:
				return getCONNECTOR();
			case AdoPackage.MODEL_TYPE__APPLIB:
				return getApplib();
			case AdoPackage.MODEL_TYPE__ID:
				return getId();
			case AdoPackage.MODEL_TYPE__LIBTYPE:
				return getLibtype();
			case AdoPackage.MODEL_TYPE__MODELTYPE:
				return getModeltype();
			case AdoPackage.MODEL_TYPE__NAME:
				return getName();
			case AdoPackage.MODEL_TYPE__VERSION:
				return getVersion();
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
			case AdoPackage.MODEL_TYPE__MODELATTRIBUTES:
				setMODELATTRIBUTES((MODELATTRIBUTESType)newValue);
				return;
			case AdoPackage.MODEL_TYPE__INSTANCE:
				getINSTANCE().clear();
				getINSTANCE().addAll((Collection<? extends INSTANCEType>)newValue);
				return;
			case AdoPackage.MODEL_TYPE__CONNECTOR:
				getCONNECTOR().clear();
				getCONNECTOR().addAll((Collection<? extends CONNECTORType>)newValue);
				return;
			case AdoPackage.MODEL_TYPE__APPLIB:
				setApplib((String)newValue);
				return;
			case AdoPackage.MODEL_TYPE__ID:
				setId((String)newValue);
				return;
			case AdoPackage.MODEL_TYPE__LIBTYPE:
				setLibtype((String)newValue);
				return;
			case AdoPackage.MODEL_TYPE__MODELTYPE:
				setModeltype((String)newValue);
				return;
			case AdoPackage.MODEL_TYPE__NAME:
				setName((String)newValue);
				return;
			case AdoPackage.MODEL_TYPE__VERSION:
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
			case AdoPackage.MODEL_TYPE__MODELATTRIBUTES:
				setMODELATTRIBUTES((MODELATTRIBUTESType)null);
				return;
			case AdoPackage.MODEL_TYPE__INSTANCE:
				getINSTANCE().clear();
				return;
			case AdoPackage.MODEL_TYPE__CONNECTOR:
				getCONNECTOR().clear();
				return;
			case AdoPackage.MODEL_TYPE__APPLIB:
				setApplib(APPLIB_EDEFAULT);
				return;
			case AdoPackage.MODEL_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case AdoPackage.MODEL_TYPE__LIBTYPE:
				setLibtype(LIBTYPE_EDEFAULT);
				return;
			case AdoPackage.MODEL_TYPE__MODELTYPE:
				setModeltype(MODELTYPE_EDEFAULT);
				return;
			case AdoPackage.MODEL_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AdoPackage.MODEL_TYPE__VERSION:
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
			case AdoPackage.MODEL_TYPE__MODELATTRIBUTES:
				return mODELATTRIBUTES != null;
			case AdoPackage.MODEL_TYPE__INSTANCE:
				return iNSTANCE != null && !iNSTANCE.isEmpty();
			case AdoPackage.MODEL_TYPE__CONNECTOR:
				return cONNECTOR != null && !cONNECTOR.isEmpty();
			case AdoPackage.MODEL_TYPE__APPLIB:
				return APPLIB_EDEFAULT == null ? applib != null : !APPLIB_EDEFAULT.equals(applib);
			case AdoPackage.MODEL_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case AdoPackage.MODEL_TYPE__LIBTYPE:
				return LIBTYPE_EDEFAULT == null ? libtype != null : !LIBTYPE_EDEFAULT.equals(libtype);
			case AdoPackage.MODEL_TYPE__MODELTYPE:
				return MODELTYPE_EDEFAULT == null ? modeltype != null : !MODELTYPE_EDEFAULT.equals(modeltype);
			case AdoPackage.MODEL_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case AdoPackage.MODEL_TYPE__VERSION:
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
		result.append(" (applib: ");
		result.append(applib);
		result.append(", id: ");
		result.append(id);
		result.append(", libtype: ");
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

} //MODELTypeImpl
