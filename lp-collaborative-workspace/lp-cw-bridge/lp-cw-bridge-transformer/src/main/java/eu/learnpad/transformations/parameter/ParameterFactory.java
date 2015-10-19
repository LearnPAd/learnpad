/**
 */
package eu.learnpad.transformations.Parameter.Parameter;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eu.learnpad.transformations.Parameter.Parameter.ParameterPackage
 * @generated
 */
public interface ParameterFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ParameterFactory eINSTANCE = eu.learnpad.transformations.Parameter.Parameter.impl.ParameterFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Params</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Params</em>'.
	 * @generated
	 */
	Params createParams();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ParameterPackage getParameterPackage();

} //ParameterFactory
