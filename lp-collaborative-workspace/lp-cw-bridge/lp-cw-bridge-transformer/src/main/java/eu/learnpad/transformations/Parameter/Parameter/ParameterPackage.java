/**
 */
package eu.learnpad.transformations.Parameter.Parameter;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.learnpad.transformations.Parameter.Parameter.ParameterFactory
 * @model kind="package"
 * @generated
 */
public interface ParameterPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Parameter";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "eu.learnpad.parameter";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Parameter";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ParameterPackage eINSTANCE = eu.learnpad.transformations.Parameter.Parameter.impl.ParameterPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.Parameter.Parameter.impl.ParamsImpl <em>Params</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.Parameter.Parameter.impl.ParamsImpl
	 * @see eu.learnpad.transformations.Parameter.Parameter.impl.ParameterPackageImpl#getParams()
	 * @generated
	 */
	int PARAMS = 0;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMS__FILE_NAME = 0;

	/**
	 * The number of structural features of the '<em>Params</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Params</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMS_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.Parameter.Parameter.Params <em>Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Params</em>'.
	 * @see eu.learnpad.transformations.Parameter.Parameter.Params
	 * @generated
	 */
	EClass getParams();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.Parameter.Parameter.Params#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see eu.learnpad.transformations.Parameter.Parameter.Params#getFileName()
	 * @see #getParams()
	 * @generated
	 */
	EAttribute getParams_FileName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ParameterFactory getParameterFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.Parameter.Parameter.impl.ParamsImpl <em>Params</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.Parameter.Parameter.impl.ParamsImpl
		 * @see eu.learnpad.transformations.Parameter.Parameter.impl.ParameterPackageImpl#getParams()
		 * @generated
		 */
		EClass PARAMS = eINSTANCE.getParams();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMS__FILE_NAME = eINSTANCE.getParams_FileName();

	}

} //ParameterPackage
