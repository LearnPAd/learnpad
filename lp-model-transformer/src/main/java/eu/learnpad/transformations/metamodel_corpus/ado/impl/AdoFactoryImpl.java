/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eu.learnpad.transformations.metamodel_corpus.ado.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AdoFactoryImpl extends EFactoryImpl implements AdoFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AdoFactory init() {
		try {
			AdoFactory theAdoFactory = (AdoFactory)EPackage.Registry.INSTANCE.getEFactory(AdoPackage.eNS_URI);
			if (theAdoFactory != null) {
				return theAdoFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AdoFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdoFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AdoPackage.ADOXML_TYPE: return createADOXMLType();
			case AdoPackage.APPLICATIONMODELS_TYPE: return createAPPLICATIONMODELSType();
			case AdoPackage.APPLICATIONMODEL_TYPE: return createAPPLICATIONMODELType();
			case AdoPackage.ATTRIBUTEPROFILES_TYPE: return createATTRIBUTEPROFILESType();
			case AdoPackage.ATTRIBUTEPROFILE_TYPE: return createATTRIBUTEPROFILEType();
			case AdoPackage.ATTRIBUTE_TYPE: return createATTRIBUTEType();
			case AdoPackage.ATTRPROFDIR_TYPE: return createATTRPROFDIRType();
			case AdoPackage.CONNECTOR_TYPE: return createCONNECTORType();
			case AdoPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case AdoPackage.FROM_TYPE: return createFROMType();
			case AdoPackage.INSTANCE_TYPE: return createINSTANCEType();
			case AdoPackage.INTERREF_TYPE: return createINTERREFType();
			case AdoPackage.IREF_TYPE: return createIREFType();
			case AdoPackage.MODELATTRIBUTES_TYPE: return createMODELATTRIBUTESType();
			case AdoPackage.MODELGROUPS_TYPE: return createMODELGROUPSType();
			case AdoPackage.MODELGROUP_TYPE: return createMODELGROUPType();
			case AdoPackage.MODELREFERENCE_TYPE: return createMODELREFERENCEType();
			case AdoPackage.MODELS_TYPE: return createMODELSType();
			case AdoPackage.MODEL_TYPE: return createMODELType();
			case AdoPackage.RECORD_TYPE: return createRECORDType();
			case AdoPackage.ROW_TYPE: return createROWType();
			case AdoPackage.TO_TYPE: return createTOType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ADOXMLType createADOXMLType() {
		ADOXMLTypeImpl adoxmlType = new ADOXMLTypeImpl();
		return adoxmlType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public APPLICATIONMODELSType createAPPLICATIONMODELSType() {
		APPLICATIONMODELSTypeImpl applicationmodelsType = new APPLICATIONMODELSTypeImpl();
		return applicationmodelsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public APPLICATIONMODELType createAPPLICATIONMODELType() {
		APPLICATIONMODELTypeImpl applicationmodelType = new APPLICATIONMODELTypeImpl();
		return applicationmodelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEPROFILESType createATTRIBUTEPROFILESType() {
		ATTRIBUTEPROFILESTypeImpl attributeprofilesType = new ATTRIBUTEPROFILESTypeImpl();
		return attributeprofilesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEPROFILEType createATTRIBUTEPROFILEType() {
		ATTRIBUTEPROFILETypeImpl attributeprofileType = new ATTRIBUTEPROFILETypeImpl();
		return attributeprofileType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEType createATTRIBUTEType() {
		ATTRIBUTETypeImpl attributeType = new ATTRIBUTETypeImpl();
		return attributeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRPROFDIRType createATTRPROFDIRType() {
		ATTRPROFDIRTypeImpl attrprofdirType = new ATTRPROFDIRTypeImpl();
		return attrprofdirType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CONNECTORType createCONNECTORType() {
		CONNECTORTypeImpl connectorType = new CONNECTORTypeImpl();
		return connectorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FROMType createFROMType() {
		FROMTypeImpl fromType = new FROMTypeImpl();
		return fromType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INSTANCEType createINSTANCEType() {
		INSTANCETypeImpl instanceType = new INSTANCETypeImpl();
		return instanceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INTERREFType createINTERREFType() {
		INTERREFTypeImpl interrefType = new INTERREFTypeImpl();
		return interrefType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IREFType createIREFType() {
		IREFTypeImpl irefType = new IREFTypeImpl();
		return irefType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELATTRIBUTESType createMODELATTRIBUTESType() {
		MODELATTRIBUTESTypeImpl modelattributesType = new MODELATTRIBUTESTypeImpl();
		return modelattributesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELGROUPSType createMODELGROUPSType() {
		MODELGROUPSTypeImpl modelgroupsType = new MODELGROUPSTypeImpl();
		return modelgroupsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELGROUPType createMODELGROUPType() {
		MODELGROUPTypeImpl modelgroupType = new MODELGROUPTypeImpl();
		return modelgroupType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELREFERENCEType createMODELREFERENCEType() {
		MODELREFERENCETypeImpl modelreferenceType = new MODELREFERENCETypeImpl();
		return modelreferenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELSType createMODELSType() {
		MODELSTypeImpl modelsType = new MODELSTypeImpl();
		return modelsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELType createMODELType() {
		MODELTypeImpl modelType = new MODELTypeImpl();
		return modelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RECORDType createRECORDType() {
		RECORDTypeImpl recordType = new RECORDTypeImpl();
		return recordType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ROWType createROWType() {
		ROWTypeImpl rowType = new ROWTypeImpl();
		return rowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOType createTOType() {
		TOTypeImpl toType = new TOTypeImpl();
		return toType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdoPackage getAdoPackage() {
		return (AdoPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AdoPackage getPackage() {
		return AdoPackage.eINSTANCE;
	}

} //AdoFactoryImpl
