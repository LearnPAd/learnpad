/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getADOXML <em>ADOXML</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODEL <em>APPLICATIONMODEL</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTE <em>ATTRIBUTE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRPROFDIR <em>ATTRPROFDIR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getCONNECTOR <em>CONNECTOR</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getFROM <em>FROM</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINSTANCE <em>INSTANCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINTERREF <em>INTERREF</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getIREF <em>IREF</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODEL <em>MODEL</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUP <em>MODELGROUP</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUPS <em>MODELGROUPS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELREFERENCE <em>MODELREFERENCE</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELS <em>MODELS</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getRECORD <em>RECORD</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getROW <em>ROW</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getTO <em>TO</em>}</li>
 * </ul>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>ADOXML</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ADOXML</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ADOXML</em>' containment reference.
	 * @see #setADOXML(ADOXMLType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_ADOXML()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ADOXML' namespace='##targetNamespace'"
	 * @generated
	 */
	ADOXMLType getADOXML();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getADOXML <em>ADOXML</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ADOXML</em>' containment reference.
	 * @see #getADOXML()
	 * @generated
	 */
	void setADOXML(ADOXMLType value);

	/**
	 * Returns the value of the '<em><b>APPLICATIONMODEL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>APPLICATIONMODEL</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>APPLICATIONMODEL</em>' containment reference.
	 * @see #setAPPLICATIONMODEL(APPLICATIONMODELType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_APPLICATIONMODEL()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='APPLICATIONMODEL' namespace='##targetNamespace'"
	 * @generated
	 */
	APPLICATIONMODELType getAPPLICATIONMODEL();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODEL <em>APPLICATIONMODEL</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>APPLICATIONMODEL</em>' containment reference.
	 * @see #getAPPLICATIONMODEL()
	 * @generated
	 */
	void setAPPLICATIONMODEL(APPLICATIONMODELType value);

	/**
	 * Returns the value of the '<em><b>APPLICATIONMODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>APPLICATIONMODELS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>APPLICATIONMODELS</em>' containment reference.
	 * @see #setAPPLICATIONMODELS(APPLICATIONMODELSType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_APPLICATIONMODELS()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='APPLICATIONMODELS' namespace='##targetNamespace'"
	 * @generated
	 */
	APPLICATIONMODELSType getAPPLICATIONMODELS();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>APPLICATIONMODELS</em>' containment reference.
	 * @see #getAPPLICATIONMODELS()
	 * @generated
	 */
	void setAPPLICATIONMODELS(APPLICATIONMODELSType value);

	/**
	 * Returns the value of the '<em><b>ATTRIBUTE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRIBUTE</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRIBUTE</em>' containment reference.
	 * @see #setATTRIBUTE(ATTRIBUTEType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_ATTRIBUTE()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ATTRIBUTE' namespace='##targetNamespace'"
	 * @generated
	 */
	ATTRIBUTEType getATTRIBUTE();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTE <em>ATTRIBUTE</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATTRIBUTE</em>' containment reference.
	 * @see #getATTRIBUTE()
	 * @generated
	 */
	void setATTRIBUTE(ATTRIBUTEType value);

	/**
	 * Returns the value of the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRIBUTEPROFILE</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRIBUTEPROFILE</em>' containment reference.
	 * @see #setATTRIBUTEPROFILE(ATTRIBUTEPROFILEType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_ATTRIBUTEPROFILE()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ATTRIBUTEPROFILE' namespace='##targetNamespace'"
	 * @generated
	 */
	ATTRIBUTEPROFILEType getATTRIBUTEPROFILE();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATTRIBUTEPROFILE</em>' containment reference.
	 * @see #getATTRIBUTEPROFILE()
	 * @generated
	 */
	void setATTRIBUTEPROFILE(ATTRIBUTEPROFILEType value);

	/**
	 * Returns the value of the '<em><b>ATTRIBUTEPROFILES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRIBUTEPROFILES</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRIBUTEPROFILES</em>' containment reference.
	 * @see #setATTRIBUTEPROFILES(ATTRIBUTEPROFILESType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_ATTRIBUTEPROFILES()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ATTRIBUTEPROFILES' namespace='##targetNamespace'"
	 * @generated
	 */
	ATTRIBUTEPROFILESType getATTRIBUTEPROFILES();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATTRIBUTEPROFILES</em>' containment reference.
	 * @see #getATTRIBUTEPROFILES()
	 * @generated
	 */
	void setATTRIBUTEPROFILES(ATTRIBUTEPROFILESType value);

	/**
	 * Returns the value of the '<em><b>ATTRPROFDIR</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ATTRPROFDIR</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATTRPROFDIR</em>' containment reference.
	 * @see #setATTRPROFDIR(ATTRPROFDIRType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_ATTRPROFDIR()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ATTRPROFDIR' namespace='##targetNamespace'"
	 * @generated
	 */
	ATTRPROFDIRType getATTRPROFDIR();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRPROFDIR <em>ATTRPROFDIR</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATTRPROFDIR</em>' containment reference.
	 * @see #getATTRPROFDIR()
	 * @generated
	 */
	void setATTRPROFDIR(ATTRPROFDIRType value);

	/**
	 * Returns the value of the '<em><b>CONNECTOR</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>CONNECTOR</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>CONNECTOR</em>' containment reference.
	 * @see #setCONNECTOR(CONNECTORType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_CONNECTOR()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='CONNECTOR' namespace='##targetNamespace'"
	 * @generated
	 */
	CONNECTORType getCONNECTOR();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getCONNECTOR <em>CONNECTOR</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>CONNECTOR</em>' containment reference.
	 * @see #getCONNECTOR()
	 * @generated
	 */
	void setCONNECTOR(CONNECTORType value);

	/**
	 * Returns the value of the '<em><b>FROM</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>FROM</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>FROM</em>' containment reference.
	 * @see #setFROM(FROMType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_FROM()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='FROM' namespace='##targetNamespace'"
	 * @generated
	 */
	FROMType getFROM();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getFROM <em>FROM</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>FROM</em>' containment reference.
	 * @see #getFROM()
	 * @generated
	 */
	void setFROM(FROMType value);

	/**
	 * Returns the value of the '<em><b>INSTANCE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>INSTANCE</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>INSTANCE</em>' containment reference.
	 * @see #setINSTANCE(INSTANCEType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_INSTANCE()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='INSTANCE' namespace='##targetNamespace'"
	 * @generated
	 */
	INSTANCEType getINSTANCE();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINSTANCE <em>INSTANCE</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>INSTANCE</em>' containment reference.
	 * @see #getINSTANCE()
	 * @generated
	 */
	void setINSTANCE(INSTANCEType value);

	/**
	 * Returns the value of the '<em><b>INTERREF</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>INTERREF</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>INTERREF</em>' containment reference.
	 * @see #setINTERREF(INTERREFType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_INTERREF()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='INTERREF' namespace='##targetNamespace'"
	 * @generated
	 */
	INTERREFType getINTERREF();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINTERREF <em>INTERREF</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>INTERREF</em>' containment reference.
	 * @see #getINTERREF()
	 * @generated
	 */
	void setINTERREF(INTERREFType value);

	/**
	 * Returns the value of the '<em><b>IREF</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IREF</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IREF</em>' containment reference.
	 * @see #setIREF(IREFType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_IREF()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='IREF' namespace='##targetNamespace'"
	 * @generated
	 */
	IREFType getIREF();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getIREF <em>IREF</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>IREF</em>' containment reference.
	 * @see #getIREF()
	 * @generated
	 */
	void setIREF(IREFType value);

	/**
	 * Returns the value of the '<em><b>MODEL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODEL</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODEL</em>' containment reference.
	 * @see #setMODEL(MODELType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_MODEL()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MODEL' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELType getMODEL();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODEL <em>MODEL</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODEL</em>' containment reference.
	 * @see #getMODEL()
	 * @generated
	 */
	void setMODEL(MODELType value);

	/**
	 * Returns the value of the '<em><b>MODELATTRIBUTES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELATTRIBUTES</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELATTRIBUTES</em>' containment reference.
	 * @see #setMODELATTRIBUTES(MODELATTRIBUTESType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_MODELATTRIBUTES()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MODELATTRIBUTES' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELATTRIBUTESType getMODELATTRIBUTES();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELATTRIBUTES</em>' containment reference.
	 * @see #getMODELATTRIBUTES()
	 * @generated
	 */
	void setMODELATTRIBUTES(MODELATTRIBUTESType value);

	/**
	 * Returns the value of the '<em><b>MODELGROUP</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELGROUP</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELGROUP</em>' containment reference.
	 * @see #setMODELGROUP(MODELGROUPType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_MODELGROUP()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MODELGROUP' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELGROUPType getMODELGROUP();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUP <em>MODELGROUP</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELGROUP</em>' containment reference.
	 * @see #getMODELGROUP()
	 * @generated
	 */
	void setMODELGROUP(MODELGROUPType value);

	/**
	 * Returns the value of the '<em><b>MODELGROUPS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELGROUPS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELGROUPS</em>' containment reference.
	 * @see #setMODELGROUPS(MODELGROUPSType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_MODELGROUPS()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MODELGROUPS' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELGROUPSType getMODELGROUPS();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUPS <em>MODELGROUPS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELGROUPS</em>' containment reference.
	 * @see #getMODELGROUPS()
	 * @generated
	 */
	void setMODELGROUPS(MODELGROUPSType value);

	/**
	 * Returns the value of the '<em><b>MODELREFERENCE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELREFERENCE</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELREFERENCE</em>' containment reference.
	 * @see #setMODELREFERENCE(MODELREFERENCEType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_MODELREFERENCE()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MODELREFERENCE' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELREFERENCEType getMODELREFERENCE();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELREFERENCE <em>MODELREFERENCE</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELREFERENCE</em>' containment reference.
	 * @see #getMODELREFERENCE()
	 * @generated
	 */
	void setMODELREFERENCE(MODELREFERENCEType value);

	/**
	 * Returns the value of the '<em><b>MODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MODELS</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MODELS</em>' containment reference.
	 * @see #setMODELS(MODELSType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_MODELS()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='MODELS' namespace='##targetNamespace'"
	 * @generated
	 */
	MODELSType getMODELS();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELS <em>MODELS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MODELS</em>' containment reference.
	 * @see #getMODELS()
	 * @generated
	 */
	void setMODELS(MODELSType value);

	/**
	 * Returns the value of the '<em><b>RECORD</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>RECORD</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>RECORD</em>' containment reference.
	 * @see #setRECORD(RECORDType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_RECORD()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='RECORD' namespace='##targetNamespace'"
	 * @generated
	 */
	RECORDType getRECORD();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getRECORD <em>RECORD</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>RECORD</em>' containment reference.
	 * @see #getRECORD()
	 * @generated
	 */
	void setRECORD(RECORDType value);

	/**
	 * Returns the value of the '<em><b>ROW</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ROW</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ROW</em>' containment reference.
	 * @see #setROW(ROWType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_ROW()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ROW' namespace='##targetNamespace'"
	 * @generated
	 */
	ROWType getROW();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getROW <em>ROW</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ROW</em>' containment reference.
	 * @see #getROW()
	 * @generated
	 */
	void setROW(ROWType value);

	/**
	 * Returns the value of the '<em><b>TO</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TO</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TO</em>' containment reference.
	 * @see #setTO(TOType)
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoPackage#getDocumentRoot_TO()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='TO' namespace='##targetNamespace'"
	 * @generated
	 */
	TOType getTO();

	/**
	 * Sets the value of the '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getTO <em>TO</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TO</em>' containment reference.
	 * @see #getTO()
	 * @generated
	 */
	void setTO(TOType value);

} // DocumentRoot
