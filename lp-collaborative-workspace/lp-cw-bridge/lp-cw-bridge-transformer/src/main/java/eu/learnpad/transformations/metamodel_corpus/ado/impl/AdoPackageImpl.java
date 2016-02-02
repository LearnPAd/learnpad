/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType;
import eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType;
import eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoFactory;
import eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage;
import eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType;
import eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot;
import eu.learnpad.transformations.metamodel_corpus.ado.FROMType;
import eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType;
import eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType;
import eu.learnpad.transformations.metamodel_corpus.ado.IREFType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELSType;
import eu.learnpad.transformations.metamodel_corpus.ado.MODELType;
import eu.learnpad.transformations.metamodel_corpus.ado.RECORDType;
import eu.learnpad.transformations.metamodel_corpus.ado.ROWType;
import eu.learnpad.transformations.metamodel_corpus.ado.TOType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AdoPackageImpl extends EPackageImpl implements AdoPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adoxmlTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationmodelsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationmodelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeprofilesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeprofileTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attrprofdirTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fromTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interrefTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass irefTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelattributesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelgroupsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelgroupTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelreferenceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass recordTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rowTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AdoPackageImpl() {
		super(eNS_URI, AdoFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AdoPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AdoPackage init() {
		if (isInited) return (AdoPackage)EPackage.Registry.INSTANCE.getEPackage(AdoPackage.eNS_URI);

		// Obtain or create and register package
		AdoPackageImpl theAdoPackage = (AdoPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AdoPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AdoPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theAdoPackage.createPackageContents();

		// Initialize created meta-data
		theAdoPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAdoPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AdoPackage.eNS_URI, theAdoPackage);
		return theAdoPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getADOXMLType() {
		return adoxmlTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getADOXMLType_ATTRIBUTEPROFILES() {
		return (EReference)adoxmlTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getADOXMLType_MODELS() {
		return (EReference)adoxmlTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getADOXMLType_APPLICATIONMODELS() {
		return (EReference)adoxmlTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getADOXMLType_MODELGROUPS() {
		return (EReference)adoxmlTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getADOXMLType_Adoversion() {
		return (EAttribute)adoxmlTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getADOXMLType_Database() {
		return (EAttribute)adoxmlTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getADOXMLType_Date() {
		return (EAttribute)adoxmlTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getADOXMLType_Time() {
		return (EAttribute)adoxmlTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getADOXMLType_Username() {
		return (EAttribute)adoxmlTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getADOXMLType_Version() {
		return (EAttribute)adoxmlTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAPPLICATIONMODELSType() {
		return applicationmodelsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAPPLICATIONMODELSType_APPLICATIONMODEL() {
		return (EReference)applicationmodelsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAPPLICATIONMODELType() {
		return applicationmodelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAPPLICATIONMODELType_MODELREFERENCE() {
		return (EReference)applicationmodelTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAPPLICATIONMODELType_Applib() {
		return (EAttribute)applicationmodelTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAPPLICATIONMODELType_Name() {
		return (EAttribute)applicationmodelTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getATTRIBUTEPROFILESType() {
		return attributeprofilesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getATTRIBUTEPROFILESType_ATTRPROFDIR() {
		return (EReference)attributeprofilesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getATTRIBUTEPROFILESType_ATTRIBUTEPROFILE() {
		return (EReference)attributeprofilesTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getATTRIBUTEPROFILEType() {
		return attributeprofileTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEPROFILEType_Group() {
		return (EAttribute)attributeprofileTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getATTRIBUTEPROFILEType_ATTRIBUTE() {
		return (EReference)attributeprofileTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getATTRIBUTEPROFILEType_RECORD() {
		return (EReference)attributeprofileTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getATTRIBUTEPROFILEType_INTERREF() {
		return (EReference)attributeprofileTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEPROFILEType_Applib() {
		return (EAttribute)attributeprofileTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEPROFILEType_Class() {
		return (EAttribute)attributeprofileTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEPROFILEType_Name() {
		return (EAttribute)attributeprofileTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEPROFILEType_Version() {
		return (EAttribute)attributeprofileTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getATTRIBUTEType() {
		return attributeTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEType_Mixed() {
		return (EAttribute)attributeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEType_Name() {
		return (EAttribute)attributeTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEType_Type() {
		return (EAttribute)attributeTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRIBUTEType_Value() {
		return (EAttribute)attributeTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getATTRPROFDIRType() {
		return attrprofdirTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getATTRPROFDIRType_ATTRIBUTEPROFILE() {
		return (EReference)attrprofdirTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getATTRPROFDIRType_ATTRPROFDIR() {
		return (EReference)attrprofdirTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getATTRPROFDIRType_Name() {
		return (EAttribute)attrprofdirTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCONNECTORType() {
		return connectorTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCONNECTORType_FROM() {
		return (EReference)connectorTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCONNECTORType_TO() {
		return (EReference)connectorTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCONNECTORType_Group() {
		return (EAttribute)connectorTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCONNECTORType_ATTRIBUTE() {
		return (EReference)connectorTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCONNECTORType_RECORD() {
		return (EReference)connectorTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCONNECTORType_INTERREF() {
		return (EReference)connectorTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCONNECTORType_Class() {
		return (EAttribute)connectorTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCONNECTORType_Id() {
		return (EAttribute)connectorTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ADOXML() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_APPLICATIONMODEL() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_APPLICATIONMODELS() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ATTRIBUTE() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ATTRIBUTEPROFILE() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ATTRIBUTEPROFILES() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ATTRPROFDIR() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CONNECTOR() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FROM() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_INSTANCE() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_INTERREF() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_IREF() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MODEL() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MODELATTRIBUTES() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MODELGROUP() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MODELGROUPS() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MODELREFERENCE() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MODELS() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_RECORD() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ROW() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TO() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFROMType() {
		return fromTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFROMType_Class() {
		return (EAttribute)fromTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFROMType_Instance() {
		return (EAttribute)fromTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getINSTANCEType() {
		return instanceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getINSTANCEType_Group() {
		return (EAttribute)instanceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getINSTANCEType_ATTRIBUTE() {
		return (EReference)instanceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getINSTANCEType_RECORD() {
		return (EReference)instanceTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getINSTANCEType_INTERREF() {
		return (EReference)instanceTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getINSTANCEType_Class() {
		return (EAttribute)instanceTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getINSTANCEType_Id() {
		return (EAttribute)instanceTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getINSTANCEType_Name() {
		return (EAttribute)instanceTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getINTERREFType() {
		return interrefTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getINTERREFType_IREF() {
		return (EReference)interrefTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getINTERREFType_Name() {
		return (EAttribute)interrefTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIREFType() {
		return irefTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIREFType_Tclassname() {
		return (EAttribute)irefTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIREFType_Tmodelname() {
		return (EAttribute)irefTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIREFType_Tmodeltype() {
		return (EAttribute)irefTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIREFType_Tmodelver() {
		return (EAttribute)irefTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIREFType_Tobjname() {
		return (EAttribute)irefTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIREFType_Type() {
		return (EAttribute)irefTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMODELATTRIBUTESType() {
		return modelattributesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELATTRIBUTESType_Group() {
		return (EAttribute)modelattributesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELATTRIBUTESType_ATTRIBUTE() {
		return (EReference)modelattributesTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELATTRIBUTESType_RECORD() {
		return (EReference)modelattributesTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMODELGROUPSType() {
		return modelgroupsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELGROUPSType_MODELGROUP() {
		return (EReference)modelgroupsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMODELGROUPType() {
		return modelgroupTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELGROUPType_MODELREFERENCE() {
		return (EReference)modelgroupTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELGROUPType_MODELGROUP() {
		return (EReference)modelgroupTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELGROUPType_Name() {
		return (EAttribute)modelgroupTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMODELREFERENCEType() {
		return modelreferenceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELREFERENCEType_Libtype() {
		return (EAttribute)modelreferenceTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELREFERENCEType_Modeltype() {
		return (EAttribute)modelreferenceTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELREFERENCEType_Name() {
		return (EAttribute)modelreferenceTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELREFERENCEType_Version() {
		return (EAttribute)modelreferenceTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMODELSType() {
		return modelsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELSType_MODEL() {
		return (EReference)modelsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMODELType() {
		return modelTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELType_MODELATTRIBUTES() {
		return (EReference)modelTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELType_INSTANCE() {
		return (EReference)modelTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMODELType_CONNECTOR() {
		return (EReference)modelTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELType_Applib() {
		return (EAttribute)modelTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELType_Id() {
		return (EAttribute)modelTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELType_Libtype() {
		return (EAttribute)modelTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELType_Modeltype() {
		return (EAttribute)modelTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELType_Name() {
		return (EAttribute)modelTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMODELType_Version() {
		return (EAttribute)modelTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRECORDType() {
		return recordTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRECORDType_ROW() {
		return (EReference)recordTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRECORDType_Name() {
		return (EAttribute)recordTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getROWType() {
		return rowTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getROWType_Group() {
		return (EAttribute)rowTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getROWType_ATTRIBUTE() {
		return (EReference)rowTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getROWType_INTERREF() {
		return (EReference)rowTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getROWType_Id() {
		return (EAttribute)rowTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getROWType_Number() {
		return (EAttribute)rowTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTOType() {
		return toTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOType_Class() {
		return (EAttribute)toTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOType_Instance() {
		return (EAttribute)toTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdoFactory getAdoFactory() {
		return (AdoFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		adoxmlTypeEClass = createEClass(ADOXML_TYPE);
		createEReference(adoxmlTypeEClass, ADOXML_TYPE__ATTRIBUTEPROFILES);
		createEReference(adoxmlTypeEClass, ADOXML_TYPE__MODELS);
		createEReference(adoxmlTypeEClass, ADOXML_TYPE__APPLICATIONMODELS);
		createEReference(adoxmlTypeEClass, ADOXML_TYPE__MODELGROUPS);
		createEAttribute(adoxmlTypeEClass, ADOXML_TYPE__ADOVERSION);
		createEAttribute(adoxmlTypeEClass, ADOXML_TYPE__DATABASE);
		createEAttribute(adoxmlTypeEClass, ADOXML_TYPE__DATE);
		createEAttribute(adoxmlTypeEClass, ADOXML_TYPE__TIME);
		createEAttribute(adoxmlTypeEClass, ADOXML_TYPE__USERNAME);
		createEAttribute(adoxmlTypeEClass, ADOXML_TYPE__VERSION);

		applicationmodelsTypeEClass = createEClass(APPLICATIONMODELS_TYPE);
		createEReference(applicationmodelsTypeEClass, APPLICATIONMODELS_TYPE__APPLICATIONMODEL);

		applicationmodelTypeEClass = createEClass(APPLICATIONMODEL_TYPE);
		createEReference(applicationmodelTypeEClass, APPLICATIONMODEL_TYPE__MODELREFERENCE);
		createEAttribute(applicationmodelTypeEClass, APPLICATIONMODEL_TYPE__APPLIB);
		createEAttribute(applicationmodelTypeEClass, APPLICATIONMODEL_TYPE__NAME);

		attributeprofilesTypeEClass = createEClass(ATTRIBUTEPROFILES_TYPE);
		createEReference(attributeprofilesTypeEClass, ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR);
		createEReference(attributeprofilesTypeEClass, ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE);

		attributeprofileTypeEClass = createEClass(ATTRIBUTEPROFILE_TYPE);
		createEAttribute(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__GROUP);
		createEReference(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__ATTRIBUTE);
		createEReference(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__RECORD);
		createEReference(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__INTERREF);
		createEAttribute(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__APPLIB);
		createEAttribute(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__CLASS);
		createEAttribute(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__NAME);
		createEAttribute(attributeprofileTypeEClass, ATTRIBUTEPROFILE_TYPE__VERSION);

		attributeTypeEClass = createEClass(ATTRIBUTE_TYPE);
		createEAttribute(attributeTypeEClass, ATTRIBUTE_TYPE__MIXED);
		createEAttribute(attributeTypeEClass, ATTRIBUTE_TYPE__NAME);
		createEAttribute(attributeTypeEClass, ATTRIBUTE_TYPE__TYPE);
		createEAttribute(attributeTypeEClass, ATTRIBUTE_TYPE__VALUE);

		attrprofdirTypeEClass = createEClass(ATTRPROFDIR_TYPE);
		createEReference(attrprofdirTypeEClass, ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE);
		createEReference(attrprofdirTypeEClass, ATTRPROFDIR_TYPE__ATTRPROFDIR);
		createEAttribute(attrprofdirTypeEClass, ATTRPROFDIR_TYPE__NAME);

		connectorTypeEClass = createEClass(CONNECTOR_TYPE);
		createEReference(connectorTypeEClass, CONNECTOR_TYPE__FROM);
		createEReference(connectorTypeEClass, CONNECTOR_TYPE__TO);
		createEAttribute(connectorTypeEClass, CONNECTOR_TYPE__GROUP);
		createEReference(connectorTypeEClass, CONNECTOR_TYPE__ATTRIBUTE);
		createEReference(connectorTypeEClass, CONNECTOR_TYPE__RECORD);
		createEReference(connectorTypeEClass, CONNECTOR_TYPE__INTERREF);
		createEAttribute(connectorTypeEClass, CONNECTOR_TYPE__CLASS);
		createEAttribute(connectorTypeEClass, CONNECTOR_TYPE__ID);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ADOXML);
		createEReference(documentRootEClass, DOCUMENT_ROOT__APPLICATIONMODEL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__APPLICATIONMODELS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTEPROFILE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTEPROFILES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRPROFDIR);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTOR);
		createEReference(documentRootEClass, DOCUMENT_ROOT__FROM);
		createEReference(documentRootEClass, DOCUMENT_ROOT__INSTANCE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__INTERREF);
		createEReference(documentRootEClass, DOCUMENT_ROOT__IREF);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MODEL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MODELATTRIBUTES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MODELGROUP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MODELGROUPS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MODELREFERENCE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MODELS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RECORD);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ROW);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TO);

		fromTypeEClass = createEClass(FROM_TYPE);
		createEAttribute(fromTypeEClass, FROM_TYPE__CLASS);
		createEAttribute(fromTypeEClass, FROM_TYPE__INSTANCE);

		instanceTypeEClass = createEClass(INSTANCE_TYPE);
		createEAttribute(instanceTypeEClass, INSTANCE_TYPE__GROUP);
		createEReference(instanceTypeEClass, INSTANCE_TYPE__ATTRIBUTE);
		createEReference(instanceTypeEClass, INSTANCE_TYPE__RECORD);
		createEReference(instanceTypeEClass, INSTANCE_TYPE__INTERREF);
		createEAttribute(instanceTypeEClass, INSTANCE_TYPE__CLASS);
		createEAttribute(instanceTypeEClass, INSTANCE_TYPE__ID);
		createEAttribute(instanceTypeEClass, INSTANCE_TYPE__NAME);

		interrefTypeEClass = createEClass(INTERREF_TYPE);
		createEReference(interrefTypeEClass, INTERREF_TYPE__IREF);
		createEAttribute(interrefTypeEClass, INTERREF_TYPE__NAME);

		irefTypeEClass = createEClass(IREF_TYPE);
		createEAttribute(irefTypeEClass, IREF_TYPE__TCLASSNAME);
		createEAttribute(irefTypeEClass, IREF_TYPE__TMODELNAME);
		createEAttribute(irefTypeEClass, IREF_TYPE__TMODELTYPE);
		createEAttribute(irefTypeEClass, IREF_TYPE__TMODELVER);
		createEAttribute(irefTypeEClass, IREF_TYPE__TOBJNAME);
		createEAttribute(irefTypeEClass, IREF_TYPE__TYPE);

		modelattributesTypeEClass = createEClass(MODELATTRIBUTES_TYPE);
		createEAttribute(modelattributesTypeEClass, MODELATTRIBUTES_TYPE__GROUP);
		createEReference(modelattributesTypeEClass, MODELATTRIBUTES_TYPE__ATTRIBUTE);
		createEReference(modelattributesTypeEClass, MODELATTRIBUTES_TYPE__RECORD);

		modelgroupsTypeEClass = createEClass(MODELGROUPS_TYPE);
		createEReference(modelgroupsTypeEClass, MODELGROUPS_TYPE__MODELGROUP);

		modelgroupTypeEClass = createEClass(MODELGROUP_TYPE);
		createEReference(modelgroupTypeEClass, MODELGROUP_TYPE__MODELREFERENCE);
		createEReference(modelgroupTypeEClass, MODELGROUP_TYPE__MODELGROUP);
		createEAttribute(modelgroupTypeEClass, MODELGROUP_TYPE__NAME);

		modelreferenceTypeEClass = createEClass(MODELREFERENCE_TYPE);
		createEAttribute(modelreferenceTypeEClass, MODELREFERENCE_TYPE__LIBTYPE);
		createEAttribute(modelreferenceTypeEClass, MODELREFERENCE_TYPE__MODELTYPE);
		createEAttribute(modelreferenceTypeEClass, MODELREFERENCE_TYPE__NAME);
		createEAttribute(modelreferenceTypeEClass, MODELREFERENCE_TYPE__VERSION);

		modelsTypeEClass = createEClass(MODELS_TYPE);
		createEReference(modelsTypeEClass, MODELS_TYPE__MODEL);

		modelTypeEClass = createEClass(MODEL_TYPE);
		createEReference(modelTypeEClass, MODEL_TYPE__MODELATTRIBUTES);
		createEReference(modelTypeEClass, MODEL_TYPE__INSTANCE);
		createEReference(modelTypeEClass, MODEL_TYPE__CONNECTOR);
		createEAttribute(modelTypeEClass, MODEL_TYPE__APPLIB);
		createEAttribute(modelTypeEClass, MODEL_TYPE__ID);
		createEAttribute(modelTypeEClass, MODEL_TYPE__LIBTYPE);
		createEAttribute(modelTypeEClass, MODEL_TYPE__MODELTYPE);
		createEAttribute(modelTypeEClass, MODEL_TYPE__NAME);
		createEAttribute(modelTypeEClass, MODEL_TYPE__VERSION);

		recordTypeEClass = createEClass(RECORD_TYPE);
		createEReference(recordTypeEClass, RECORD_TYPE__ROW);
		createEAttribute(recordTypeEClass, RECORD_TYPE__NAME);

		rowTypeEClass = createEClass(ROW_TYPE);
		createEAttribute(rowTypeEClass, ROW_TYPE__GROUP);
		createEReference(rowTypeEClass, ROW_TYPE__ATTRIBUTE);
		createEReference(rowTypeEClass, ROW_TYPE__INTERREF);
		createEAttribute(rowTypeEClass, ROW_TYPE__ID);
		createEAttribute(rowTypeEClass, ROW_TYPE__NUMBER);

		toTypeEClass = createEClass(TO_TYPE);
		createEAttribute(toTypeEClass, TO_TYPE__CLASS);
		createEAttribute(toTypeEClass, TO_TYPE__INSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(adoxmlTypeEClass, ADOXMLType.class, "ADOXMLType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getADOXMLType_ATTRIBUTEPROFILES(), this.getATTRIBUTEPROFILESType(), null, "aTTRIBUTEPROFILES", null, 0, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getADOXMLType_MODELS(), this.getMODELSType(), null, "mODELS", null, 0, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getADOXMLType_APPLICATIONMODELS(), this.getAPPLICATIONMODELSType(), null, "aPPLICATIONMODELS", null, 0, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getADOXMLType_MODELGROUPS(), this.getMODELGROUPSType(), null, "mODELGROUPS", null, 0, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getADOXMLType_Adoversion(), theXMLTypePackage.getString(), "adoversion", null, 1, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getADOXMLType_Database(), theXMLTypePackage.getString(), "database", null, 0, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getADOXMLType_Date(), theXMLTypePackage.getString(), "date", null, 1, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getADOXMLType_Time(), theXMLTypePackage.getString(), "time", null, 1, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getADOXMLType_Username(), theXMLTypePackage.getString(), "username", null, 0, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getADOXMLType_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, ADOXMLType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationmodelsTypeEClass, APPLICATIONMODELSType.class, "APPLICATIONMODELSType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAPPLICATIONMODELSType_APPLICATIONMODEL(), this.getAPPLICATIONMODELType(), null, "aPPLICATIONMODEL", null, 1, -1, APPLICATIONMODELSType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationmodelTypeEClass, APPLICATIONMODELType.class, "APPLICATIONMODELType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAPPLICATIONMODELType_MODELREFERENCE(), this.getMODELREFERENCEType(), null, "mODELREFERENCE", null, 1, -1, APPLICATIONMODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAPPLICATIONMODELType_Applib(), theXMLTypePackage.getString(), "applib", null, 1, 1, APPLICATIONMODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAPPLICATIONMODELType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, APPLICATIONMODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeprofilesTypeEClass, ATTRIBUTEPROFILESType.class, "ATTRIBUTEPROFILESType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getATTRIBUTEPROFILESType_ATTRPROFDIR(), this.getATTRPROFDIRType(), null, "aTTRPROFDIR", null, 0, 1, ATTRIBUTEPROFILESType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getATTRIBUTEPROFILESType_ATTRIBUTEPROFILE(), this.getATTRIBUTEPROFILEType(), null, "aTTRIBUTEPROFILE", null, 0, 1, ATTRIBUTEPROFILESType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeprofileTypeEClass, ATTRIBUTEPROFILEType.class, "ATTRIBUTEPROFILEType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getATTRIBUTEPROFILEType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ATTRIBUTEPROFILEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getATTRIBUTEPROFILEType_ATTRIBUTE(), this.getATTRIBUTEType(), null, "aTTRIBUTE", null, 0, -1, ATTRIBUTEPROFILEType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getATTRIBUTEPROFILEType_RECORD(), this.getRECORDType(), null, "rECORD", null, 0, -1, ATTRIBUTEPROFILEType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getATTRIBUTEPROFILEType_INTERREF(), this.getINTERREFType(), null, "iNTERREF", null, 0, -1, ATTRIBUTEPROFILEType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRIBUTEPROFILEType_Applib(), theXMLTypePackage.getString(), "applib", null, 1, 1, ATTRIBUTEPROFILEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRIBUTEPROFILEType_Class(), theXMLTypePackage.getString(), "class", null, 1, 1, ATTRIBUTEPROFILEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRIBUTEPROFILEType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ATTRIBUTEPROFILEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRIBUTEPROFILEType_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, ATTRIBUTEPROFILEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeTypeEClass, ATTRIBUTEType.class, "ATTRIBUTEType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getATTRIBUTEType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ATTRIBUTEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRIBUTEType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ATTRIBUTEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRIBUTEType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, ATTRIBUTEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRIBUTEType_Value(), ecorePackage.getEString(), "value", null, 0, 1, ATTRIBUTEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attrprofdirTypeEClass, ATTRPROFDIRType.class, "ATTRPROFDIRType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getATTRPROFDIRType_ATTRIBUTEPROFILE(), this.getATTRIBUTEPROFILEType(), null, "aTTRIBUTEPROFILE", null, 0, -1, ATTRPROFDIRType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getATTRPROFDIRType_ATTRPROFDIR(), this.getATTRPROFDIRType(), null, "aTTRPROFDIR", null, 0, -1, ATTRPROFDIRType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getATTRPROFDIRType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ATTRPROFDIRType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectorTypeEClass, CONNECTORType.class, "CONNECTORType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCONNECTORType_FROM(), this.getFROMType(), null, "fROM", null, 1, 1, CONNECTORType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCONNECTORType_TO(), this.getTOType(), null, "tO", null, 1, 1, CONNECTORType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCONNECTORType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, CONNECTORType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCONNECTORType_ATTRIBUTE(), this.getATTRIBUTEType(), null, "aTTRIBUTE", null, 0, -1, CONNECTORType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCONNECTORType_RECORD(), this.getRECORDType(), null, "rECORD", null, 0, -1, CONNECTORType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCONNECTORType_INTERREF(), this.getINTERREFType(), null, "iNTERREF", null, 0, -1, CONNECTORType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getCONNECTORType_Class(), theXMLTypePackage.getString(), "class", null, 1, 1, CONNECTORType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCONNECTORType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, CONNECTORType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ADOXML(), this.getADOXMLType(), null, "aDOXML", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_APPLICATIONMODEL(), this.getAPPLICATIONMODELType(), null, "aPPLICATIONMODEL", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_APPLICATIONMODELS(), this.getAPPLICATIONMODELSType(), null, "aPPLICATIONMODELS", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ATTRIBUTE(), this.getATTRIBUTEType(), null, "aTTRIBUTE", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ATTRIBUTEPROFILE(), this.getATTRIBUTEPROFILEType(), null, "aTTRIBUTEPROFILE", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ATTRIBUTEPROFILES(), this.getATTRIBUTEPROFILESType(), null, "aTTRIBUTEPROFILES", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ATTRPROFDIR(), this.getATTRPROFDIRType(), null, "aTTRPROFDIR", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_CONNECTOR(), this.getCONNECTORType(), null, "cONNECTOR", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_FROM(), this.getFROMType(), null, "fROM", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_INSTANCE(), this.getINSTANCEType(), null, "iNSTANCE", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_INTERREF(), this.getINTERREFType(), null, "iNTERREF", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_IREF(), this.getIREFType(), null, "iREF", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MODEL(), this.getMODELType(), null, "mODEL", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MODELATTRIBUTES(), this.getMODELATTRIBUTESType(), null, "mODELATTRIBUTES", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MODELGROUP(), this.getMODELGROUPType(), null, "mODELGROUP", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MODELGROUPS(), this.getMODELGROUPSType(), null, "mODELGROUPS", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MODELREFERENCE(), this.getMODELREFERENCEType(), null, "mODELREFERENCE", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_MODELS(), this.getMODELSType(), null, "mODELS", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_RECORD(), this.getRECORDType(), null, "rECORD", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ROW(), this.getROWType(), null, "rOW", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_TO(), this.getTOType(), null, "tO", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(fromTypeEClass, FROMType.class, "FROMType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFROMType_Class(), theXMLTypePackage.getString(), "class", null, 1, 1, FROMType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFROMType_Instance(), theXMLTypePackage.getString(), "instance", null, 1, 1, FROMType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(instanceTypeEClass, INSTANCEType.class, "INSTANCEType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getINSTANCEType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, INSTANCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getINSTANCEType_ATTRIBUTE(), this.getATTRIBUTEType(), null, "aTTRIBUTE", null, 0, -1, INSTANCEType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getINSTANCEType_RECORD(), this.getRECORDType(), null, "rECORD", null, 0, -1, INSTANCEType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getINSTANCEType_INTERREF(), this.getINTERREFType(), null, "iNTERREF", null, 0, -1, INSTANCEType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getINSTANCEType_Class(), theXMLTypePackage.getString(), "class", null, 1, 1, INSTANCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getINSTANCEType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, INSTANCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getINSTANCEType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, INSTANCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(interrefTypeEClass, INTERREFType.class, "INTERREFType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getINTERREFType_IREF(), this.getIREFType(), null, "iREF", null, 0, -1, INTERREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getINTERREFType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, INTERREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(irefTypeEClass, IREFType.class, "IREFType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIREFType_Tclassname(), theXMLTypePackage.getString(), "tclassname", null, 0, 1, IREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIREFType_Tmodelname(), theXMLTypePackage.getString(), "tmodelname", null, 1, 1, IREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIREFType_Tmodeltype(), theXMLTypePackage.getString(), "tmodeltype", null, 1, 1, IREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIREFType_Tmodelver(), theXMLTypePackage.getString(), "tmodelver", null, 1, 1, IREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIREFType_Tobjname(), theXMLTypePackage.getString(), "tobjname", null, 0, 1, IREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIREFType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, IREFType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelattributesTypeEClass, MODELATTRIBUTESType.class, "MODELATTRIBUTESType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMODELATTRIBUTESType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, MODELATTRIBUTESType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMODELATTRIBUTESType_ATTRIBUTE(), this.getATTRIBUTEType(), null, "aTTRIBUTE", null, 0, -1, MODELATTRIBUTESType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getMODELATTRIBUTESType_RECORD(), this.getRECORDType(), null, "rECORD", null, 0, -1, MODELATTRIBUTESType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(modelgroupsTypeEClass, MODELGROUPSType.class, "MODELGROUPSType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMODELGROUPSType_MODELGROUP(), this.getMODELGROUPType(), null, "mODELGROUP", null, 1, -1, MODELGROUPSType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelgroupTypeEClass, MODELGROUPType.class, "MODELGROUPType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMODELGROUPType_MODELREFERENCE(), this.getMODELREFERENCEType(), null, "mODELREFERENCE", null, 0, -1, MODELGROUPType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMODELGROUPType_MODELGROUP(), this.getMODELGROUPType(), null, "mODELGROUP", null, 0, -1, MODELGROUPType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELGROUPType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, MODELGROUPType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelreferenceTypeEClass, MODELREFERENCEType.class, "MODELREFERENCEType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMODELREFERENCEType_Libtype(), theXMLTypePackage.getString(), "libtype", null, 1, 1, MODELREFERENCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELREFERENCEType_Modeltype(), theXMLTypePackage.getString(), "modeltype", null, 1, 1, MODELREFERENCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELREFERENCEType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, MODELREFERENCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELREFERENCEType_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, MODELREFERENCEType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelsTypeEClass, MODELSType.class, "MODELSType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMODELSType_MODEL(), this.getMODELType(), null, "mODEL", null, 1, -1, MODELSType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelTypeEClass, MODELType.class, "MODELType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMODELType_MODELATTRIBUTES(), this.getMODELATTRIBUTESType(), null, "mODELATTRIBUTES", null, 1, 1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMODELType_INSTANCE(), this.getINSTANCEType(), null, "iNSTANCE", null, 0, -1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMODELType_CONNECTOR(), this.getCONNECTORType(), null, "cONNECTOR", null, 0, -1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELType_Applib(), theXMLTypePackage.getString(), "applib", null, 1, 1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELType_Libtype(), theXMLTypePackage.getString(), "libtype", null, 1, 1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELType_Modeltype(), theXMLTypePackage.getString(), "modeltype", null, 1, 1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMODELType_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, MODELType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(recordTypeEClass, RECORDType.class, "RECORDType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRECORDType_ROW(), this.getROWType(), null, "rOW", null, 0, -1, RECORDType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRECORDType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, RECORDType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rowTypeEClass, ROWType.class, "ROWType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getROWType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ROWType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getROWType_ATTRIBUTE(), this.getATTRIBUTEType(), null, "aTTRIBUTE", null, 0, -1, ROWType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getROWType_INTERREF(), this.getINTERREFType(), null, "iNTERREF", null, 0, -1, ROWType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getROWType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ROWType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getROWType_Number(), theXMLTypePackage.getString(), "number", null, 0, 1, ROWType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(toTypeEClass, TOType.class, "TOType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTOType_Class(), theXMLTypePackage.getString(), "class", null, 1, 1, TOType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTOType_Instance(), theXMLTypePackage.getString(), "instance", null, 1, 1, TOType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";	
		addAnnotation
		  (adoxmlTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ADOXML_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getADOXMLType_ATTRIBUTEPROFILES(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTEPROFILES",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getADOXMLType_MODELS(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELS",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getADOXMLType_APPLICATIONMODELS(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "APPLICATIONMODELS",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getADOXMLType_MODELGROUPS(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELGROUPS",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getADOXMLType_Adoversion(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "adoversion"
		   });	
		addAnnotation
		  (getADOXMLType_Database(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "database"
		   });	
		addAnnotation
		  (getADOXMLType_Date(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "date"
		   });	
		addAnnotation
		  (getADOXMLType_Time(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "time"
		   });	
		addAnnotation
		  (getADOXMLType_Username(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "username"
		   });	
		addAnnotation
		  (getADOXMLType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });	
		addAnnotation
		  (applicationmodelsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "APPLICATIONMODELS_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getAPPLICATIONMODELSType_APPLICATIONMODEL(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "APPLICATIONMODEL",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (applicationmodelTypeEClass, 
		   source, 
		   new String[] {
			 "name", "APPLICATIONMODEL_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getAPPLICATIONMODELType_MODELREFERENCE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELREFERENCE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAPPLICATIONMODELType_Applib(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "applib"
		   });	
		addAnnotation
		  (getAPPLICATIONMODELType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (attributeprofilesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ATTRIBUTEPROFILES_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILESType_ATTRPROFDIR(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRPROFDIR",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILESType_ATTRIBUTEPROFILE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTEPROFILE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (attributeprofileTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ATTRIBUTEPROFILE_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_ATTRIBUTE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTE",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_RECORD(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RECORD",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_INTERREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "INTERREF",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_Applib(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "applib"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (getATTRIBUTEPROFILEType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });	
		addAnnotation
		  (attributeTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ATTRIBUTE_._type",
			 "kind", "mixed"
		   });	
		addAnnotation
		  (getATTRIBUTEType_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });	
		addAnnotation
		  (getATTRIBUTEType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (getATTRIBUTEType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });	
		addAnnotation
		  (attrprofdirTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ATTRPROFDIR_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getATTRPROFDIRType_ATTRIBUTEPROFILE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTEPROFILE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getATTRPROFDIRType_ATTRPROFDIR(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRPROFDIR",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getATTRPROFDIRType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (connectorTypeEClass, 
		   source, 
		   new String[] {
			 "name", "CONNECTOR_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getCONNECTORType_FROM(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FROM",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getCONNECTORType_TO(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TO",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getCONNECTORType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:2"
		   });	
		addAnnotation
		  (getCONNECTORType_ATTRIBUTE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTE",
			 "namespace", "##targetNamespace",
			 "group", "#group:2"
		   });	
		addAnnotation
		  (getCONNECTORType_RECORD(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RECORD",
			 "namespace", "##targetNamespace",
			 "group", "#group:2"
		   });	
		addAnnotation
		  (getCONNECTORType_INTERREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "INTERREF",
			 "namespace", "##targetNamespace",
			 "group", "#group:2"
		   });	
		addAnnotation
		  (getCONNECTORType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });	
		addAnnotation
		  (getCONNECTORType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });	
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });	
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });	
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });	
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });	
		addAnnotation
		  (getDocumentRoot_ADOXML(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ADOXML",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_APPLICATIONMODEL(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "APPLICATIONMODEL",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_APPLICATIONMODELS(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "APPLICATIONMODELS",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ATTRIBUTE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ATTRIBUTEPROFILE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTEPROFILE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ATTRIBUTEPROFILES(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTEPROFILES",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ATTRPROFDIR(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRPROFDIR",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_CONNECTOR(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CONNECTOR",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_FROM(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "FROM",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_INSTANCE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "INSTANCE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_INTERREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "INTERREF",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_IREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "IREF",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_MODEL(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODEL",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_MODELATTRIBUTES(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELATTRIBUTES",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_MODELGROUP(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELGROUP",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_MODELGROUPS(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELGROUPS",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_MODELREFERENCE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELREFERENCE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_MODELS(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELS",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_RECORD(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RECORD",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ROW(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ROW",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_TO(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "TO",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (fromTypeEClass, 
		   source, 
		   new String[] {
			 "name", "FROM_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getFROMType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });	
		addAnnotation
		  (getFROMType_Instance(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "instance"
		   });	
		addAnnotation
		  (instanceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "INSTANCE_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getINSTANCEType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });	
		addAnnotation
		  (getINSTANCEType_ATTRIBUTE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTE",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getINSTANCEType_RECORD(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RECORD",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getINSTANCEType_INTERREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "INTERREF",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getINSTANCEType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });	
		addAnnotation
		  (getINSTANCEType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });	
		addAnnotation
		  (getINSTANCEType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (interrefTypeEClass, 
		   source, 
		   new String[] {
			 "name", "INTERREF_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getINTERREFType_IREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "IREF",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getINTERREFType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (irefTypeEClass, 
		   source, 
		   new String[] {
			 "name", "IREF_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getIREFType_Tclassname(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "tclassname"
		   });	
		addAnnotation
		  (getIREFType_Tmodelname(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "tmodelname"
		   });	
		addAnnotation
		  (getIREFType_Tmodeltype(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "tmodeltype"
		   });	
		addAnnotation
		  (getIREFType_Tmodelver(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "tmodelver"
		   });	
		addAnnotation
		  (getIREFType_Tobjname(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "tobjname"
		   });	
		addAnnotation
		  (getIREFType_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });	
		addAnnotation
		  (modelattributesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "MODELATTRIBUTES_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getMODELATTRIBUTESType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });	
		addAnnotation
		  (getMODELATTRIBUTESType_ATTRIBUTE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTE",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getMODELATTRIBUTESType_RECORD(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "RECORD",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (modelgroupsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "MODELGROUPS_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getMODELGROUPSType_MODELGROUP(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELGROUP",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (modelgroupTypeEClass, 
		   source, 
		   new String[] {
			 "name", "MODELGROUP_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getMODELGROUPType_MODELREFERENCE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELREFERENCE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getMODELGROUPType_MODELGROUP(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELGROUP",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getMODELGROUPType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (modelreferenceTypeEClass, 
		   source, 
		   new String[] {
			 "name", "MODELREFERENCE_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getMODELREFERENCEType_Libtype(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "libtype"
		   });	
		addAnnotation
		  (getMODELREFERENCEType_Modeltype(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "modeltype"
		   });	
		addAnnotation
		  (getMODELREFERENCEType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (getMODELREFERENCEType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });	
		addAnnotation
		  (modelsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "MODELS_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getMODELSType_MODEL(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODEL",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (modelTypeEClass, 
		   source, 
		   new String[] {
			 "name", "MODEL_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getMODELType_MODELATTRIBUTES(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "MODELATTRIBUTES",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getMODELType_INSTANCE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "INSTANCE",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getMODELType_CONNECTOR(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "CONNECTOR",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getMODELType_Applib(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "applib"
		   });	
		addAnnotation
		  (getMODELType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });	
		addAnnotation
		  (getMODELType_Libtype(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "libtype"
		   });	
		addAnnotation
		  (getMODELType_Modeltype(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "modeltype"
		   });	
		addAnnotation
		  (getMODELType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (getMODELType_Version(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "version"
		   });	
		addAnnotation
		  (recordTypeEClass, 
		   source, 
		   new String[] {
			 "name", "RECORD_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getRECORDType_ROW(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ROW",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getRECORDType_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (rowTypeEClass, 
		   source, 
		   new String[] {
			 "name", "ROW_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getROWType_Group(), 
		   source, 
		   new String[] {
			 "kind", "group",
			 "name", "group:0"
		   });	
		addAnnotation
		  (getROWType_ATTRIBUTE(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ATTRIBUTE",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getROWType_INTERREF(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "INTERREF",
			 "namespace", "##targetNamespace",
			 "group", "#group:0"
		   });	
		addAnnotation
		  (getROWType_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });	
		addAnnotation
		  (getROWType_Number(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "number"
		   });	
		addAnnotation
		  (toTypeEClass, 
		   source, 
		   new String[] {
			 "name", "TO_._type",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getTOType_Class(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "class"
		   });	
		addAnnotation
		  (getTOType_Instance(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "instance"
		   });
	}

} //AdoPackageImpl
