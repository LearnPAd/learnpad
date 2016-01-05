/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType;
import eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType;
import eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType;
import eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType;
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
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getADOXML <em>ADOXML</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getAPPLICATIONMODEL <em>APPLICATIONMODEL</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getATTRIBUTE <em>ATTRIBUTE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getATTRPROFDIR <em>ATTRPROFDIR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getCONNECTOR <em>CONNECTOR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getFROM <em>FROM</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getINSTANCE <em>INSTANCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getINTERREF <em>INTERREF</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getIREF <em>IREF</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getMODEL <em>MODEL</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getMODELGROUP <em>MODELGROUP</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getMODELGROUPS <em>MODELGROUPS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getMODELREFERENCE <em>MODELREFERENCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getMODELS <em>MODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getRECORD <em>RECORD</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getROW <em>ROW</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl#getTO <em>TO</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdoPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, AdoPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, AdoPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, AdoPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ADOXMLType getADOXML() {
		return (ADOXMLType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__ADOXML, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetADOXML(ADOXMLType newADOXML, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__ADOXML, newADOXML, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setADOXML(ADOXMLType newADOXML) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__ADOXML, newADOXML);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public APPLICATIONMODELType getAPPLICATIONMODEL() {
		return (APPLICATIONMODELType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__APPLICATIONMODEL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAPPLICATIONMODEL(APPLICATIONMODELType newAPPLICATIONMODEL, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__APPLICATIONMODEL, newAPPLICATIONMODEL, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAPPLICATIONMODEL(APPLICATIONMODELType newAPPLICATIONMODEL) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__APPLICATIONMODEL, newAPPLICATIONMODEL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public APPLICATIONMODELSType getAPPLICATIONMODELS() {
		return (APPLICATIONMODELSType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__APPLICATIONMODELS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAPPLICATIONMODELS(APPLICATIONMODELSType newAPPLICATIONMODELS, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__APPLICATIONMODELS, newAPPLICATIONMODELS, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAPPLICATIONMODELS(APPLICATIONMODELSType newAPPLICATIONMODELS) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__APPLICATIONMODELS, newAPPLICATIONMODELS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEType getATTRIBUTE() {
		return (ATTRIBUTEType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetATTRIBUTE(ATTRIBUTEType newATTRIBUTE, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE, newATTRIBUTE, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setATTRIBUTE(ATTRIBUTEType newATTRIBUTE) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTE, newATTRIBUTE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEPROFILEType getATTRIBUTEPROFILE() {
		return (ATTRIBUTEPROFILEType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTEPROFILE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetATTRIBUTEPROFILE(ATTRIBUTEPROFILEType newATTRIBUTEPROFILE, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTEPROFILE, newATTRIBUTEPROFILE, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setATTRIBUTEPROFILE(ATTRIBUTEPROFILEType newATTRIBUTEPROFILE) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTEPROFILE, newATTRIBUTEPROFILE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRIBUTEPROFILESType getATTRIBUTEPROFILES() {
		return (ATTRIBUTEPROFILESType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTEPROFILES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetATTRIBUTEPROFILES(ATTRIBUTEPROFILESType newATTRIBUTEPROFILES, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTEPROFILES, newATTRIBUTEPROFILES, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setATTRIBUTEPROFILES(ATTRIBUTEPROFILESType newATTRIBUTEPROFILES) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__ATTRIBUTEPROFILES, newATTRIBUTEPROFILES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ATTRPROFDIRType getATTRPROFDIR() {
		return (ATTRPROFDIRType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__ATTRPROFDIR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetATTRPROFDIR(ATTRPROFDIRType newATTRPROFDIR, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__ATTRPROFDIR, newATTRPROFDIR, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setATTRPROFDIR(ATTRPROFDIRType newATTRPROFDIR) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__ATTRPROFDIR, newATTRPROFDIR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CONNECTORType getCONNECTOR() {
		return (CONNECTORType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__CONNECTOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCONNECTOR(CONNECTORType newCONNECTOR, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__CONNECTOR, newCONNECTOR, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCONNECTOR(CONNECTORType newCONNECTOR) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__CONNECTOR, newCONNECTOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FROMType getFROM() {
		return (FROMType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__FROM, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFROM(FROMType newFROM, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__FROM, newFROM, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFROM(FROMType newFROM) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__FROM, newFROM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INSTANCEType getINSTANCE() {
		return (INSTANCEType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__INSTANCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetINSTANCE(INSTANCEType newINSTANCE, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__INSTANCE, newINSTANCE, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setINSTANCE(INSTANCEType newINSTANCE) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__INSTANCE, newINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public INTERREFType getINTERREF() {
		return (INTERREFType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__INTERREF, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetINTERREF(INTERREFType newINTERREF, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__INTERREF, newINTERREF, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setINTERREF(INTERREFType newINTERREF) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__INTERREF, newINTERREF);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IREFType getIREF() {
		return (IREFType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__IREF, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIREF(IREFType newIREF, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__IREF, newIREF, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIREF(IREFType newIREF) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__IREF, newIREF);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELType getMODEL() {
		return (MODELType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__MODEL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODEL(MODELType newMODEL, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__MODEL, newMODEL, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODEL(MODELType newMODEL) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__MODEL, newMODEL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELATTRIBUTESType getMODELATTRIBUTES() {
		return (MODELATTRIBUTESType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__MODELATTRIBUTES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELATTRIBUTES(MODELATTRIBUTESType newMODELATTRIBUTES, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__MODELATTRIBUTES, newMODELATTRIBUTES, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELATTRIBUTES(MODELATTRIBUTESType newMODELATTRIBUTES) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__MODELATTRIBUTES, newMODELATTRIBUTES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELGROUPType getMODELGROUP() {
		return (MODELGROUPType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__MODELGROUP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELGROUP(MODELGROUPType newMODELGROUP, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__MODELGROUP, newMODELGROUP, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELGROUP(MODELGROUPType newMODELGROUP) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__MODELGROUP, newMODELGROUP);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELGROUPSType getMODELGROUPS() {
		return (MODELGROUPSType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__MODELGROUPS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELGROUPS(MODELGROUPSType newMODELGROUPS, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__MODELGROUPS, newMODELGROUPS, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELGROUPS(MODELGROUPSType newMODELGROUPS) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__MODELGROUPS, newMODELGROUPS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELREFERENCEType getMODELREFERENCE() {
		return (MODELREFERENCEType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__MODELREFERENCE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELREFERENCE(MODELREFERENCEType newMODELREFERENCE, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__MODELREFERENCE, newMODELREFERENCE, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELREFERENCE(MODELREFERENCEType newMODELREFERENCE) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__MODELREFERENCE, newMODELREFERENCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MODELSType getMODELS() {
		return (MODELSType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__MODELS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMODELS(MODELSType newMODELS, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__MODELS, newMODELS, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMODELS(MODELSType newMODELS) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__MODELS, newMODELS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RECORDType getRECORD() {
		return (RECORDType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__RECORD, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRECORD(RECORDType newRECORD, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__RECORD, newRECORD, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRECORD(RECORDType newRECORD) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__RECORD, newRECORD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ROWType getROW() {
		return (ROWType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__ROW, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetROW(ROWType newROW, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__ROW, newROW, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setROW(ROWType newROW) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__ROW, newROW);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOType getTO() {
		return (TOType)getMixed().get(AdoPackage.Literals.DOCUMENT_ROOT__TO, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTO(TOType newTO, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(AdoPackage.Literals.DOCUMENT_ROOT__TO, newTO, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTO(TOType newTO) {
		((FeatureMap.Internal)getMixed()).set(AdoPackage.Literals.DOCUMENT_ROOT__TO, newTO);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdoPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case AdoPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case AdoPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case AdoPackage.DOCUMENT_ROOT__ADOXML:
				return basicSetADOXML(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODEL:
				return basicSetAPPLICATIONMODEL(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODELS:
				return basicSetAPPLICATIONMODELS(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTE:
				return basicSetATTRIBUTE(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILE:
				return basicSetATTRIBUTEPROFILE(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILES:
				return basicSetATTRIBUTEPROFILES(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__ATTRPROFDIR:
				return basicSetATTRPROFDIR(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__CONNECTOR:
				return basicSetCONNECTOR(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__FROM:
				return basicSetFROM(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__INSTANCE:
				return basicSetINSTANCE(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__INTERREF:
				return basicSetINTERREF(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__IREF:
				return basicSetIREF(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__MODEL:
				return basicSetMODEL(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__MODELATTRIBUTES:
				return basicSetMODELATTRIBUTES(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__MODELGROUP:
				return basicSetMODELGROUP(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__MODELGROUPS:
				return basicSetMODELGROUPS(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__MODELREFERENCE:
				return basicSetMODELREFERENCE(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__MODELS:
				return basicSetMODELS(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__RECORD:
				return basicSetRECORD(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__ROW:
				return basicSetROW(null, msgs);
			case AdoPackage.DOCUMENT_ROOT__TO:
				return basicSetTO(null, msgs);
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
			case AdoPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case AdoPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case AdoPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case AdoPackage.DOCUMENT_ROOT__ADOXML:
				return getADOXML();
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODEL:
				return getAPPLICATIONMODEL();
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODELS:
				return getAPPLICATIONMODELS();
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTE:
				return getATTRIBUTE();
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILE:
				return getATTRIBUTEPROFILE();
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILES:
				return getATTRIBUTEPROFILES();
			case AdoPackage.DOCUMENT_ROOT__ATTRPROFDIR:
				return getATTRPROFDIR();
			case AdoPackage.DOCUMENT_ROOT__CONNECTOR:
				return getCONNECTOR();
			case AdoPackage.DOCUMENT_ROOT__FROM:
				return getFROM();
			case AdoPackage.DOCUMENT_ROOT__INSTANCE:
				return getINSTANCE();
			case AdoPackage.DOCUMENT_ROOT__INTERREF:
				return getINTERREF();
			case AdoPackage.DOCUMENT_ROOT__IREF:
				return getIREF();
			case AdoPackage.DOCUMENT_ROOT__MODEL:
				return getMODEL();
			case AdoPackage.DOCUMENT_ROOT__MODELATTRIBUTES:
				return getMODELATTRIBUTES();
			case AdoPackage.DOCUMENT_ROOT__MODELGROUP:
				return getMODELGROUP();
			case AdoPackage.DOCUMENT_ROOT__MODELGROUPS:
				return getMODELGROUPS();
			case AdoPackage.DOCUMENT_ROOT__MODELREFERENCE:
				return getMODELREFERENCE();
			case AdoPackage.DOCUMENT_ROOT__MODELS:
				return getMODELS();
			case AdoPackage.DOCUMENT_ROOT__RECORD:
				return getRECORD();
			case AdoPackage.DOCUMENT_ROOT__ROW:
				return getROW();
			case AdoPackage.DOCUMENT_ROOT__TO:
				return getTO();
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
			case AdoPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__ADOXML:
				setADOXML((ADOXMLType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODEL:
				setAPPLICATIONMODEL((APPLICATIONMODELType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODELS:
				setAPPLICATIONMODELS((APPLICATIONMODELSType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTE:
				setATTRIBUTE((ATTRIBUTEType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILE:
				setATTRIBUTEPROFILE((ATTRIBUTEPROFILEType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILES:
				setATTRIBUTEPROFILES((ATTRIBUTEPROFILESType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRPROFDIR:
				setATTRPROFDIR((ATTRPROFDIRType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__CONNECTOR:
				setCONNECTOR((CONNECTORType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__FROM:
				setFROM((FROMType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__INSTANCE:
				setINSTANCE((INSTANCEType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__INTERREF:
				setINTERREF((INTERREFType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__IREF:
				setIREF((IREFType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODEL:
				setMODEL((MODELType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELATTRIBUTES:
				setMODELATTRIBUTES((MODELATTRIBUTESType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELGROUP:
				setMODELGROUP((MODELGROUPType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELGROUPS:
				setMODELGROUPS((MODELGROUPSType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELREFERENCE:
				setMODELREFERENCE((MODELREFERENCEType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELS:
				setMODELS((MODELSType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__RECORD:
				setRECORD((RECORDType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__ROW:
				setROW((ROWType)newValue);
				return;
			case AdoPackage.DOCUMENT_ROOT__TO:
				setTO((TOType)newValue);
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
			case AdoPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case AdoPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case AdoPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case AdoPackage.DOCUMENT_ROOT__ADOXML:
				setADOXML((ADOXMLType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODEL:
				setAPPLICATIONMODEL((APPLICATIONMODELType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODELS:
				setAPPLICATIONMODELS((APPLICATIONMODELSType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTE:
				setATTRIBUTE((ATTRIBUTEType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILE:
				setATTRIBUTEPROFILE((ATTRIBUTEPROFILEType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILES:
				setATTRIBUTEPROFILES((ATTRIBUTEPROFILESType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__ATTRPROFDIR:
				setATTRPROFDIR((ATTRPROFDIRType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__CONNECTOR:
				setCONNECTOR((CONNECTORType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__FROM:
				setFROM((FROMType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__INSTANCE:
				setINSTANCE((INSTANCEType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__INTERREF:
				setINTERREF((INTERREFType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__IREF:
				setIREF((IREFType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODEL:
				setMODEL((MODELType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELATTRIBUTES:
				setMODELATTRIBUTES((MODELATTRIBUTESType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELGROUP:
				setMODELGROUP((MODELGROUPType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELGROUPS:
				setMODELGROUPS((MODELGROUPSType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELREFERENCE:
				setMODELREFERENCE((MODELREFERENCEType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__MODELS:
				setMODELS((MODELSType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__RECORD:
				setRECORD((RECORDType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__ROW:
				setROW((ROWType)null);
				return;
			case AdoPackage.DOCUMENT_ROOT__TO:
				setTO((TOType)null);
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
			case AdoPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case AdoPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case AdoPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case AdoPackage.DOCUMENT_ROOT__ADOXML:
				return getADOXML() != null;
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODEL:
				return getAPPLICATIONMODEL() != null;
			case AdoPackage.DOCUMENT_ROOT__APPLICATIONMODELS:
				return getAPPLICATIONMODELS() != null;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTE:
				return getATTRIBUTE() != null;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILE:
				return getATTRIBUTEPROFILE() != null;
			case AdoPackage.DOCUMENT_ROOT__ATTRIBUTEPROFILES:
				return getATTRIBUTEPROFILES() != null;
			case AdoPackage.DOCUMENT_ROOT__ATTRPROFDIR:
				return getATTRPROFDIR() != null;
			case AdoPackage.DOCUMENT_ROOT__CONNECTOR:
				return getCONNECTOR() != null;
			case AdoPackage.DOCUMENT_ROOT__FROM:
				return getFROM() != null;
			case AdoPackage.DOCUMENT_ROOT__INSTANCE:
				return getINSTANCE() != null;
			case AdoPackage.DOCUMENT_ROOT__INTERREF:
				return getINTERREF() != null;
			case AdoPackage.DOCUMENT_ROOT__IREF:
				return getIREF() != null;
			case AdoPackage.DOCUMENT_ROOT__MODEL:
				return getMODEL() != null;
			case AdoPackage.DOCUMENT_ROOT__MODELATTRIBUTES:
				return getMODELATTRIBUTES() != null;
			case AdoPackage.DOCUMENT_ROOT__MODELGROUP:
				return getMODELGROUP() != null;
			case AdoPackage.DOCUMENT_ROOT__MODELGROUPS:
				return getMODELGROUPS() != null;
			case AdoPackage.DOCUMENT_ROOT__MODELREFERENCE:
				return getMODELREFERENCE() != null;
			case AdoPackage.DOCUMENT_ROOT__MODELS:
				return getMODELS() != null;
			case AdoPackage.DOCUMENT_ROOT__RECORD:
				return getRECORD() != null;
			case AdoPackage.DOCUMENT_ROOT__ROW:
				return getROW() != null;
			case AdoPackage.DOCUMENT_ROOT__TO:
				return getTO() != null;
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
