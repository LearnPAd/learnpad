/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import eu.learnpad.transformations.metamodel_corpus.ado.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage
 * @generated
 */
public class AdoSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AdoPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdoSwitch() {
		if (modelPackage == null) {
			modelPackage = AdoPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AdoPackage.ADOXML_TYPE: {
				ADOXMLType adoxmlType = (ADOXMLType)theEObject;
				T result = caseADOXMLType(adoxmlType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.APPLICATIONMODELS_TYPE: {
				APPLICATIONMODELSType applicationmodelsType = (APPLICATIONMODELSType)theEObject;
				T result = caseAPPLICATIONMODELSType(applicationmodelsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.APPLICATIONMODEL_TYPE: {
				APPLICATIONMODELType applicationmodelType = (APPLICATIONMODELType)theEObject;
				T result = caseAPPLICATIONMODELType(applicationmodelType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.ATTRIBUTEPROFILES_TYPE: {
				ATTRIBUTEPROFILESType attributeprofilesType = (ATTRIBUTEPROFILESType)theEObject;
				T result = caseATTRIBUTEPROFILESType(attributeprofilesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.ATTRIBUTEPROFILE_TYPE: {
				ATTRIBUTEPROFILEType attributeprofileType = (ATTRIBUTEPROFILEType)theEObject;
				T result = caseATTRIBUTEPROFILEType(attributeprofileType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.ATTRIBUTE_TYPE: {
				ATTRIBUTEType attributeType = (ATTRIBUTEType)theEObject;
				T result = caseATTRIBUTEType(attributeType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.ATTRPROFDIR_TYPE: {
				ATTRPROFDIRType attrprofdirType = (ATTRPROFDIRType)theEObject;
				T result = caseATTRPROFDIRType(attrprofdirType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.CONNECTOR_TYPE: {
				CONNECTORType connectorType = (CONNECTORType)theEObject;
				T result = caseCONNECTORType(connectorType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.FROM_TYPE: {
				FROMType fromType = (FROMType)theEObject;
				T result = caseFROMType(fromType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.INSTANCE_TYPE: {
				INSTANCEType instanceType = (INSTANCEType)theEObject;
				T result = caseINSTANCEType(instanceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.INTERREF_TYPE: {
				INTERREFType interrefType = (INTERREFType)theEObject;
				T result = caseINTERREFType(interrefType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.IREF_TYPE: {
				IREFType irefType = (IREFType)theEObject;
				T result = caseIREFType(irefType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.MODELATTRIBUTES_TYPE: {
				MODELATTRIBUTESType modelattributesType = (MODELATTRIBUTESType)theEObject;
				T result = caseMODELATTRIBUTESType(modelattributesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.MODELGROUPS_TYPE: {
				MODELGROUPSType modelgroupsType = (MODELGROUPSType)theEObject;
				T result = caseMODELGROUPSType(modelgroupsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.MODELGROUP_TYPE: {
				MODELGROUPType modelgroupType = (MODELGROUPType)theEObject;
				T result = caseMODELGROUPType(modelgroupType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.MODELREFERENCE_TYPE: {
				MODELREFERENCEType modelreferenceType = (MODELREFERENCEType)theEObject;
				T result = caseMODELREFERENCEType(modelreferenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.MODELS_TYPE: {
				MODELSType modelsType = (MODELSType)theEObject;
				T result = caseMODELSType(modelsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.MODEL_TYPE: {
				MODELType modelType = (MODELType)theEObject;
				T result = caseMODELType(modelType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.RECORD_TYPE: {
				RECORDType recordType = (RECORDType)theEObject;
				T result = caseRECORDType(recordType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.ROW_TYPE: {
				ROWType rowType = (ROWType)theEObject;
				T result = caseROWType(rowType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AdoPackage.TO_TYPE: {
				TOType toType = (TOType)theEObject;
				T result = caseTOType(toType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ADOXML Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ADOXML Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseADOXMLType(ADOXMLType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>APPLICATIONMODELS Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>APPLICATIONMODELS Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAPPLICATIONMODELSType(APPLICATIONMODELSType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>APPLICATIONMODEL Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>APPLICATIONMODEL Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAPPLICATIONMODELType(APPLICATIONMODELType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ATTRIBUTEPROFILES Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ATTRIBUTEPROFILES Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseATTRIBUTEPROFILESType(ATTRIBUTEPROFILESType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ATTRIBUTEPROFILE Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ATTRIBUTEPROFILE Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseATTRIBUTEPROFILEType(ATTRIBUTEPROFILEType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ATTRIBUTE Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ATTRIBUTE Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseATTRIBUTEType(ATTRIBUTEType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ATTRPROFDIR Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ATTRPROFDIR Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseATTRPROFDIRType(ATTRPROFDIRType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CONNECTOR Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CONNECTOR Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCONNECTORType(CONNECTORType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FROM Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FROM Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFROMType(FROMType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INSTANCE Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INSTANCE Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINSTANCEType(INSTANCEType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INTERREF Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INTERREF Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINTERREFType(INTERREFType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IREF Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IREF Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIREFType(IREFType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MODELATTRIBUTES Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MODELATTRIBUTES Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMODELATTRIBUTESType(MODELATTRIBUTESType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MODELGROUPS Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MODELGROUPS Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMODELGROUPSType(MODELGROUPSType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MODELGROUP Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MODELGROUP Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMODELGROUPType(MODELGROUPType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MODELREFERENCE Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MODELREFERENCE Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMODELREFERENCEType(MODELREFERENCEType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MODELS Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MODELS Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMODELSType(MODELSType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>MODEL Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>MODEL Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMODELType(MODELType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>RECORD Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>RECORD Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRECORDType(RECORDType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ROW Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ROW Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseROWType(ROWType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TO Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TO Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTOType(TOType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //AdoSwitch
