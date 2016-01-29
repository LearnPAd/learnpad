/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Objects Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectsTypeImpl#getObjectSummary <em>Object Summary</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ObjectsTypeImpl extends LinkCollectionImpl implements ObjectsType {
	/**
	 * The cached value of the '{@link #getObjectSummary() <em>Object Summary</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectSummary()
	 * @generated
	 * @ordered
	 */
	protected EList<ObjectSummary> objectSummary;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectsTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.OBJECTS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectSummary> getObjectSummary() {
		if (objectSummary == null) {
			objectSummary = new EObjectContainmentEList<ObjectSummary>(ObjectSummary.class, this, XwikiPackage.OBJECTS_TYPE__OBJECT_SUMMARY);
		}
		return objectSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XwikiPackage.OBJECTS_TYPE__OBJECT_SUMMARY:
				return ((InternalEList<?>)getObjectSummary()).basicRemove(otherEnd, msgs);
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
			case XwikiPackage.OBJECTS_TYPE__OBJECT_SUMMARY:
				return getObjectSummary();
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
			case XwikiPackage.OBJECTS_TYPE__OBJECT_SUMMARY:
				getObjectSummary().clear();
				getObjectSummary().addAll((Collection<? extends ObjectSummary>)newValue);
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
			case XwikiPackage.OBJECTS_TYPE__OBJECT_SUMMARY:
				getObjectSummary().clear();
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
			case XwikiPackage.OBJECTS_TYPE__OBJECT_SUMMARY:
				return objectSummary != null && !objectSummary.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ObjectsTypeImpl
