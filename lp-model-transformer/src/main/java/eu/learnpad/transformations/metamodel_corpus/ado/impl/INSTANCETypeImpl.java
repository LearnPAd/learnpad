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

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType;
import eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType;
import eu.learnpad.transformations.metamodel_corpus.ado.RECORDType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>INSTANCE Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl#getATTRIBUTE <em>ATTRIBUTE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl#getRECORD <em>RECORD</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl#getINTERREF <em>INTERREF</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class INSTANCETypeImpl extends MinimalEObjectImpl.Container implements INSTANCEType {
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
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected String class_ = CLASS_EDEFAULT;

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
	protected INSTANCETypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.INSTANCE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, AdoPackage.INSTANCE_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ATTRIBUTEType> getATTRIBUTE() {
		return getGroup().list(AdoPackage.Literals.INSTANCE_TYPE__ATTRIBUTE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RECORDType> getRECORD() {
		return getGroup().list(AdoPackage.Literals.INSTANCE_TYPE__RECORD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<INTERREFType> getINTERREF() {
		return getGroup().list(AdoPackage.Literals.INSTANCE_TYPE__INTERREF);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClass_() {
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass(String newClass) {
		String oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.INSTANCE_TYPE__CLASS, oldClass, class_));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.INSTANCE_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AdoPackage.INSTANCE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.INSTANCE_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case AdoPackage.INSTANCE_TYPE__ATTRIBUTE:
				return ((InternalEList<?>)getATTRIBUTE()).basicRemove(otherEnd, msgs);
			case AdoPackage.INSTANCE_TYPE__RECORD:
				return ((InternalEList<?>)getRECORD()).basicRemove(otherEnd, msgs);
			case AdoPackage.INSTANCE_TYPE__INTERREF:
				return ((InternalEList<?>)getINTERREF()).basicRemove(otherEnd, msgs);
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
			case AdoPackage.INSTANCE_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case AdoPackage.INSTANCE_TYPE__ATTRIBUTE:
				return getATTRIBUTE();
			case AdoPackage.INSTANCE_TYPE__RECORD:
				return getRECORD();
			case AdoPackage.INSTANCE_TYPE__INTERREF:
				return getINTERREF();
			case AdoPackage.INSTANCE_TYPE__CLASS:
				return getClass_();
			case AdoPackage.INSTANCE_TYPE__ID:
				return getId();
			case AdoPackage.INSTANCE_TYPE__NAME:
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
			case AdoPackage.INSTANCE_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case AdoPackage.INSTANCE_TYPE__ATTRIBUTE:
				getATTRIBUTE().clear();
				getATTRIBUTE().addAll((Collection<? extends ATTRIBUTEType>)newValue);
				return;
			case AdoPackage.INSTANCE_TYPE__RECORD:
				getRECORD().clear();
				getRECORD().addAll((Collection<? extends RECORDType>)newValue);
				return;
			case AdoPackage.INSTANCE_TYPE__INTERREF:
				getINTERREF().clear();
				getINTERREF().addAll((Collection<? extends INTERREFType>)newValue);
				return;
			case AdoPackage.INSTANCE_TYPE__CLASS:
				setClass((String)newValue);
				return;
			case AdoPackage.INSTANCE_TYPE__ID:
				setId((String)newValue);
				return;
			case AdoPackage.INSTANCE_TYPE__NAME:
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
			case AdoPackage.INSTANCE_TYPE__GROUP:
				getGroup().clear();
				return;
			case AdoPackage.INSTANCE_TYPE__ATTRIBUTE:
				getATTRIBUTE().clear();
				return;
			case AdoPackage.INSTANCE_TYPE__RECORD:
				getRECORD().clear();
				return;
			case AdoPackage.INSTANCE_TYPE__INTERREF:
				getINTERREF().clear();
				return;
			case AdoPackage.INSTANCE_TYPE__CLASS:
				setClass(CLASS_EDEFAULT);
				return;
			case AdoPackage.INSTANCE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case AdoPackage.INSTANCE_TYPE__NAME:
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
			case AdoPackage.INSTANCE_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case AdoPackage.INSTANCE_TYPE__ATTRIBUTE:
				return !getATTRIBUTE().isEmpty();
			case AdoPackage.INSTANCE_TYPE__RECORD:
				return !getRECORD().isEmpty();
			case AdoPackage.INSTANCE_TYPE__INTERREF:
				return !getINTERREF().isEmpty();
			case AdoPackage.INSTANCE_TYPE__CLASS:
				return CLASS_EDEFAULT == null ? class_ != null : !CLASS_EDEFAULT.equals(class_);
			case AdoPackage.INSTANCE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case AdoPackage.INSTANCE_TYPE__NAME:
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
		result.append(" (group: ");
		result.append(group);
		result.append(", class: ");
		result.append(class_);
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //INSTANCETypeImpl
