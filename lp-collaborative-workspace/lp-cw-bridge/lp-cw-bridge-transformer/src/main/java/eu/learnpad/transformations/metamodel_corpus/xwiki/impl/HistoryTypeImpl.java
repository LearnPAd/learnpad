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

import eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>History Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistoryTypeImpl#getHistorySummary <em>History Summary</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HistoryTypeImpl extends LinkCollectionImpl implements HistoryType {
	/**
	 * The cached value of the '{@link #getHistorySummary() <em>History Summary</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHistorySummary()
	 * @generated
	 * @ordered
	 */
	protected EList<HistorySummary> historySummary;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HistoryTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XwikiPackage.Literals.HISTORY_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HistorySummary> getHistorySummary() {
		if (historySummary == null) {
			historySummary = new EObjectContainmentEList<HistorySummary>(HistorySummary.class, this, XwikiPackage.HISTORY_TYPE__HISTORY_SUMMARY);
		}
		return historySummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XwikiPackage.HISTORY_TYPE__HISTORY_SUMMARY:
				return ((InternalEList<?>)getHistorySummary()).basicRemove(otherEnd, msgs);
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
			case XwikiPackage.HISTORY_TYPE__HISTORY_SUMMARY:
				return getHistorySummary();
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
			case XwikiPackage.HISTORY_TYPE__HISTORY_SUMMARY:
				getHistorySummary().clear();
				getHistorySummary().addAll((Collection<? extends HistorySummary>)newValue);
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
			case XwikiPackage.HISTORY_TYPE__HISTORY_SUMMARY:
				getHistorySummary().clear();
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
			case XwikiPackage.HISTORY_TYPE__HISTORY_SUMMARY:
				return historySummary != null && !historySummary.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //HistoryTypeImpl
