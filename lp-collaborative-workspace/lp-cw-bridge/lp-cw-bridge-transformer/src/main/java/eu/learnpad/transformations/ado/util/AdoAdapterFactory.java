/**
 */
package eu.learnpad.transformations.ado.util;

import eu.learnpad.transformations.ado.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see eu.learnpad.transformations.ado.AdoPackage
 * @generated
 */
public class AdoAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AdoPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdoAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AdoPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdoSwitch<Adapter> modelSwitch =
		new AdoSwitch<Adapter>() {
			@Override
			public Adapter caseADOXMLType(ADOXMLType object) {
				return createADOXMLTypeAdapter();
			}
			@Override
			public Adapter caseAPPLICATIONMODELSType(APPLICATIONMODELSType object) {
				return createAPPLICATIONMODELSTypeAdapter();
			}
			@Override
			public Adapter caseAPPLICATIONMODELType(APPLICATIONMODELType object) {
				return createAPPLICATIONMODELTypeAdapter();
			}
			@Override
			public Adapter caseATTRIBUTEPROFILESType(ATTRIBUTEPROFILESType object) {
				return createATTRIBUTEPROFILESTypeAdapter();
			}
			@Override
			public Adapter caseATTRIBUTEPROFILEType(ATTRIBUTEPROFILEType object) {
				return createATTRIBUTEPROFILETypeAdapter();
			}
			@Override
			public Adapter caseATTRIBUTEType(ATTRIBUTEType object) {
				return createATTRIBUTETypeAdapter();
			}
			@Override
			public Adapter caseATTRPROFDIRType(ATTRPROFDIRType object) {
				return createATTRPROFDIRTypeAdapter();
			}
			@Override
			public Adapter caseCONNECTORType(CONNECTORType object) {
				return createCONNECTORTypeAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseFROMType(FROMType object) {
				return createFROMTypeAdapter();
			}
			@Override
			public Adapter caseINSTANCEType(INSTANCEType object) {
				return createINSTANCETypeAdapter();
			}
			@Override
			public Adapter caseINTERREFType(INTERREFType object) {
				return createINTERREFTypeAdapter();
			}
			@Override
			public Adapter caseIREFType(IREFType object) {
				return createIREFTypeAdapter();
			}
			@Override
			public Adapter caseMODELATTRIBUTESType(MODELATTRIBUTESType object) {
				return createMODELATTRIBUTESTypeAdapter();
			}
			@Override
			public Adapter caseMODELGROUPSType(MODELGROUPSType object) {
				return createMODELGROUPSTypeAdapter();
			}
			@Override
			public Adapter caseMODELGROUPType(MODELGROUPType object) {
				return createMODELGROUPTypeAdapter();
			}
			@Override
			public Adapter caseMODELREFERENCEType(MODELREFERENCEType object) {
				return createMODELREFERENCETypeAdapter();
			}
			@Override
			public Adapter caseMODELSType(MODELSType object) {
				return createMODELSTypeAdapter();
			}
			@Override
			public Adapter caseMODELType(MODELType object) {
				return createMODELTypeAdapter();
			}
			@Override
			public Adapter caseRECORDType(RECORDType object) {
				return createRECORDTypeAdapter();
			}
			@Override
			public Adapter caseROWType(ROWType object) {
				return createROWTypeAdapter();
			}
			@Override
			public Adapter caseTOType(TOType object) {
				return createTOTypeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.ADOXMLType <em>ADOXML Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.ADOXMLType
	 * @generated
	 */
	public Adapter createADOXMLTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.APPLICATIONMODELSType <em>APPLICATIONMODELS Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.APPLICATIONMODELSType
	 * @generated
	 */
	public Adapter createAPPLICATIONMODELSTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.APPLICATIONMODELType <em>APPLICATIONMODEL Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.APPLICATIONMODELType
	 * @generated
	 */
	public Adapter createAPPLICATIONMODELTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.ATTRIBUTEPROFILESType <em>ATTRIBUTEPROFILES Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.ATTRIBUTEPROFILESType
	 * @generated
	 */
	public Adapter createATTRIBUTEPROFILESTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.ATTRIBUTEPROFILEType <em>ATTRIBUTEPROFILE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.ATTRIBUTEPROFILEType
	 * @generated
	 */
	public Adapter createATTRIBUTEPROFILETypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.ATTRIBUTEType <em>ATTRIBUTE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.ATTRIBUTEType
	 * @generated
	 */
	public Adapter createATTRIBUTETypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.ATTRPROFDIRType <em>ATTRPROFDIR Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.ATTRPROFDIRType
	 * @generated
	 */
	public Adapter createATTRPROFDIRTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.CONNECTORType <em>CONNECTOR Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.CONNECTORType
	 * @generated
	 */
	public Adapter createCONNECTORTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.FROMType <em>FROM Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.FROMType
	 * @generated
	 */
	public Adapter createFROMTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.INSTANCEType <em>INSTANCE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.INSTANCEType
	 * @generated
	 */
	public Adapter createINSTANCETypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.INTERREFType <em>INTERREF Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.INTERREFType
	 * @generated
	 */
	public Adapter createINTERREFTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.IREFType <em>IREF Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.IREFType
	 * @generated
	 */
	public Adapter createIREFTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.MODELATTRIBUTESType <em>MODELATTRIBUTES Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.MODELATTRIBUTESType
	 * @generated
	 */
	public Adapter createMODELATTRIBUTESTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.MODELGROUPSType <em>MODELGROUPS Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.MODELGROUPSType
	 * @generated
	 */
	public Adapter createMODELGROUPSTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.MODELGROUPType <em>MODELGROUP Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.MODELGROUPType
	 * @generated
	 */
	public Adapter createMODELGROUPTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.MODELREFERENCEType <em>MODELREFERENCE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.MODELREFERENCEType
	 * @generated
	 */
	public Adapter createMODELREFERENCETypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.MODELSType <em>MODELS Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.MODELSType
	 * @generated
	 */
	public Adapter createMODELSTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.MODELType <em>MODEL Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.MODELType
	 * @generated
	 */
	public Adapter createMODELTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.RECORDType <em>RECORD Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.RECORDType
	 * @generated
	 */
	public Adapter createRECORDTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.ROWType <em>ROW Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.ROWType
	 * @generated
	 */
	public Adapter createROWTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.ado.TOType <em>TO Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.ado.TOType
	 * @generated
	 */
	public Adapter createTOTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //AdoAdapterFactory
