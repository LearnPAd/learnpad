/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType;
import eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELSType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ADOXML Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getMODELS <em>MODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getMODELGROUPS <em>MODELGROUPS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getAdoversion <em>Adoversion</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getDate <em>Date</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getTime <em>Time</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getUsername <em>Username</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl#getVersion <em>Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ADOXMLTypeImpl extends MinimalEObjectImpl.Container implements ADOXMLType {
	/**
	 * The cached value of the '{@link #getATTRIBUTEPROFILES() <em>ATTRIBUTEPROFILES</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getATTRIBUTEPROFILES()
	 * @generated
	 * @ordered
	 */
	protected ATTRIBUTEPROFILESType aTTRIBUTEPROFILES;

	/**
	 * The cached value of the '{@link #getMODELS() <em>MODELS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMODELS()
	 * @generated
	 * @ordered
	 */
	protected MODELSType mODELS;

	/**
	 * The cached value of the '{@link #getAPPLICATIONMODELS() <em>APPLICATIONMODELS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAPPLICATIONMODELS()
	 * @generated
	 * @ordered
	 */
	protected APPLICATIONMODELSType aPPLICATIONMODELS;

	/**
	 * The cached value of the '{@link #getMODELGROUPS() <em>MODELGROUPS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMODELGROUPS()
	 * @generated
	 * @ordered
	 */
	protected MODELGROUPSType mODELGROUPS;

	/**
	 * The default value of the '{@link #getAdoversion() <em>Adoversion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdoversion()
	 * @generated
	 * @ordered
	 */
	protected static final String ADOVERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdoversion() <em>Adoversion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdoversion()
	 * @generated
	 * @ordered
	 */
	protected String adoversion = ADOVERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDatabase() <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected static final String DATABASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected String database = DATABASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected static final String DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDate() <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDate()
	 * @generated
	 * @ordered
	 */
	protected String date = DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final String TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected String time = TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getUsername() <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsername()
	 * @generated
	 * @ordered
	 */
	protected static final String USERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsername() <em>Username</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsername()
	 * @generated
	 * @ordered
	 */
	protected String username = USERNAME_EDEFAULT;

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
	protected ADOXMLTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.ADOXML_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEPROFILESType getATTRIBUTEPROFILES() {
		return aTTRIBUTEPROFILES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetATTRIBUTEPROFILES(ATTRIBUTEPROFILESType newATTRIBUTEPROFILES, NotificationChain msgs) {
		ATTRIBUTEPROFILESType oldATTRIBUTEPROFILES = aTTRIBUTEPROFILES;
		aTTRIBUTEPROFILES = newATTRIBUTEPROFILES;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES, oldATTRIBUTEPROFILES, newATTRIBUTEPROFILES);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setATTRIBUTEPROFILES(ATTRIBUTEPROFILESType newATTRIBUTEPROFILES) {
		if (newATTRIBUTEPROFILES != aTTRIBUTEPROFILES) {
			NotificationChain msgs = null;
			if (aTTRIBUTEPROFILES != null)
				msgs = ((InternalEObject)aTTRIBUTEPROFILES).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES, null, msgs);
			if (newATTRIBUTEPROFILES != null)
				msgs = ((InternalEObject)newATTRIBUTEPROFILES).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES, null, msgs);
			msgs = basicSetATTRIBUTEPROFILES(newATTRIBUTEPROFILES, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES, newATTRIBUTEPROFILES, newATTRIBUTEPROFILES));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELSType getMODELS() {
		return mODELS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELS(MODELSType newMODELS, NotificationChain msgs) {
		MODELSType oldMODELS = mODELS;
		mODELS = newMODELS;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__MODELS, oldMODELS, newMODELS);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELS(MODELSType newMODELS) {
		if (newMODELS != mODELS) {
			NotificationChain msgs = null;
			if (mODELS != null)
				msgs = ((InternalEObject)mODELS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__MODELS, null, msgs);
			if (newMODELS != null)
				msgs = ((InternalEObject)newMODELS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__MODELS, null, msgs);
			msgs = basicSetMODELS(newMODELS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__MODELS, newMODELS, newMODELS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public APPLICATIONMODELSType getAPPLICATIONMODELS() {
		return aPPLICATIONMODELS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAPPLICATIONMODELS(APPLICATIONMODELSType newAPPLICATIONMODELS, NotificationChain msgs) {
		APPLICATIONMODELSType oldAPPLICATIONMODELS = aPPLICATIONMODELS;
		aPPLICATIONMODELS = newAPPLICATIONMODELS;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__APPLICATIONMODELS, oldAPPLICATIONMODELS, newAPPLICATIONMODELS);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAPPLICATIONMODELS(APPLICATIONMODELSType newAPPLICATIONMODELS) {
		if (newAPPLICATIONMODELS != aPPLICATIONMODELS) {
			NotificationChain msgs = null;
			if (aPPLICATIONMODELS != null)
				msgs = ((InternalEObject)aPPLICATIONMODELS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__APPLICATIONMODELS, null, msgs);
			if (newAPPLICATIONMODELS != null)
				msgs = ((InternalEObject)newAPPLICATIONMODELS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__APPLICATIONMODELS, null, msgs);
			msgs = basicSetAPPLICATIONMODELS(newAPPLICATIONMODELS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__APPLICATIONMODELS, newAPPLICATIONMODELS, newAPPLICATIONMODELS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELGROUPSType getMODELGROUPS() {
		return mODELGROUPS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELGROUPS(MODELGROUPSType newMODELGROUPS, NotificationChain msgs) {
		MODELGROUPSType oldMODELGROUPS = mODELGROUPS;
		mODELGROUPS = newMODELGROUPS;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__MODELGROUPS, oldMODELGROUPS, newMODELGROUPS);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELGROUPS(MODELGROUPSType newMODELGROUPS) {
		if (newMODELGROUPS != mODELGROUPS) {
			NotificationChain msgs = null;
			if (mODELGROUPS != null)
				msgs = ((InternalEObject)mODELGROUPS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__MODELGROUPS, null, msgs);
			if (newMODELGROUPS != null)
				msgs = ((InternalEObject)newMODELGROUPS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AdoPackage.ADOXML_TYPE__MODELGROUPS, null, msgs);
			msgs = basicSetMODELGROUPS(newMODELGROUPS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__MODELGROUPS, newMODELGROUPS, newMODELGROUPS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAdoversion() {
		return adoversion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdoversion(String newAdoversion) {
		String oldAdoversion = adoversion;
		adoversion = newAdoversion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__ADOVERSION, oldAdoversion, adoversion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(String newDatabase) {
		String oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__DATABASE, oldDatabase, database));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDate() {
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDate(String newDate) {
		String oldDate = date;
		date = newDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__DATE, oldDate, date));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTime(String newTime) {
		String oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsername(String newUsername) {
		String oldUsername = username;
		username = newUsername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__USERNAME, oldUsername, username));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.ADOXML_TYPE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES:
				return basicSetATTRIBUTEPROFILES(null, msgs);
			case AdoPackage.ADOXML_TYPE__MODELS:
				return basicSetMODELS(null, msgs);
			case AdoPackage.ADOXML_TYPE__APPLICATIONMODELS:
				return basicSetAPPLICATIONMODELS(null, msgs);
			case AdoPackage.ADOXML_TYPE__MODELGROUPS:
				return basicSetMODELGROUPS(null, msgs);
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
			case AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES:
				return getATTRIBUTEPROFILES();
			case AdoPackage.ADOXML_TYPE__MODELS:
				return getMODELS();
			case AdoPackage.ADOXML_TYPE__APPLICATIONMODELS:
				return getAPPLICATIONMODELS();
			case AdoPackage.ADOXML_TYPE__MODELGROUPS:
				return getMODELGROUPS();
			case AdoPackage.ADOXML_TYPE__ADOVERSION:
				return getAdoversion();
			case AdoPackage.ADOXML_TYPE__DATABASE:
				return getDatabase();
			case AdoPackage.ADOXML_TYPE__DATE:
				return getDate();
			case AdoPackage.ADOXML_TYPE__TIME:
				return getTime();
			case AdoPackage.ADOXML_TYPE__USERNAME:
				return getUsername();
			case AdoPackage.ADOXML_TYPE__VERSION:
				return getVersion();
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
			case AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES:
				setATTRIBUTEPROFILES((ATTRIBUTEPROFILESType)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__MODELS:
				setMODELS((MODELSType)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__APPLICATIONMODELS:
				setAPPLICATIONMODELS((APPLICATIONMODELSType)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__MODELGROUPS:
				setMODELGROUPS((MODELGROUPSType)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__ADOVERSION:
				setAdoversion((String)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__DATABASE:
				setDatabase((String)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__DATE:
				setDate((String)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__TIME:
				setTime((String)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__USERNAME:
				setUsername((String)newValue);
				return;
			case AdoPackage.ADOXML_TYPE__VERSION:
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
			case AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES:
				setATTRIBUTEPROFILES((ATTRIBUTEPROFILESType)null);
				return;
			case AdoPackage.ADOXML_TYPE__MODELS:
				setMODELS((MODELSType)null);
				return;
			case AdoPackage.ADOXML_TYPE__APPLICATIONMODELS:
				setAPPLICATIONMODELS((APPLICATIONMODELSType)null);
				return;
			case AdoPackage.ADOXML_TYPE__MODELGROUPS:
				setMODELGROUPS((MODELGROUPSType)null);
				return;
			case AdoPackage.ADOXML_TYPE__ADOVERSION:
				setAdoversion(ADOVERSION_EDEFAULT);
				return;
			case AdoPackage.ADOXML_TYPE__DATABASE:
				setDatabase(DATABASE_EDEFAULT);
				return;
			case AdoPackage.ADOXML_TYPE__DATE:
				setDate(DATE_EDEFAULT);
				return;
			case AdoPackage.ADOXML_TYPE__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case AdoPackage.ADOXML_TYPE__USERNAME:
				setUsername(USERNAME_EDEFAULT);
				return;
			case AdoPackage.ADOXML_TYPE__VERSION:
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
			case AdoPackage.ADOXML_TYPE__ATTRIBUTEPROFILES:
				return aTTRIBUTEPROFILES != null;
			case AdoPackage.ADOXML_TYPE__MODELS:
				return mODELS != null;
			case AdoPackage.ADOXML_TYPE__APPLICATIONMODELS:
				return aPPLICATIONMODELS != null;
			case AdoPackage.ADOXML_TYPE__MODELGROUPS:
				return mODELGROUPS != null;
			case AdoPackage.ADOXML_TYPE__ADOVERSION:
				return ADOVERSION_EDEFAULT == null ? adoversion != null : !ADOVERSION_EDEFAULT.equals(adoversion);
			case AdoPackage.ADOXML_TYPE__DATABASE:
				return DATABASE_EDEFAULT == null ? database != null : !DATABASE_EDEFAULT.equals(database);
			case AdoPackage.ADOXML_TYPE__DATE:
				return DATE_EDEFAULT == null ? date != null : !DATE_EDEFAULT.equals(date);
			case AdoPackage.ADOXML_TYPE__TIME:
				return TIME_EDEFAULT == null ? time != null : !TIME_EDEFAULT.equals(time);
			case AdoPackage.ADOXML_TYPE__USERNAME:
				return USERNAME_EDEFAULT == null ? username != null : !USERNAME_EDEFAULT.equals(username);
			case AdoPackage.ADOXML_TYPE__VERSION:
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
		result.append(" (adoversion: ");
		result.append(adoversion);
		result.append(", database: ");
		result.append(database);
		result.append(", date: ");
		result.append(date);
		result.append(", time: ");
		result.append(time);
		result.append(", username: ");
		result.append(username);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //ADOXMLTypeImpl
