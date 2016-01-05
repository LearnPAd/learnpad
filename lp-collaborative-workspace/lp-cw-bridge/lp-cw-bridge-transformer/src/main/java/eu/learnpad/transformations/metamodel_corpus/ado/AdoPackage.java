/**
 */
package eu.learnpad.transformations.metamodel_corpus.ado;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see eu.learnpad.transformations.metamodel_corpus.ado.AdoFactory
 * @model kind="package"
 * @generated
 */
public interface AdoPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ado";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.ado.org";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ado";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AdoPackage eINSTANCE = eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl <em>ADOXML Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getADOXMLType()
	 * @generated
	 */
	int ADOXML_TYPE = 0;

	/**
	 * The feature id for the '<em><b>ATTRIBUTEPROFILES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__ATTRIBUTEPROFILES = 0;

	/**
	 * The feature id for the '<em><b>MODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__MODELS = 1;

	/**
	 * The feature id for the '<em><b>APPLICATIONMODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__APPLICATIONMODELS = 2;

	/**
	 * The feature id for the '<em><b>MODELGROUPS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__MODELGROUPS = 3;

	/**
	 * The feature id for the '<em><b>Adoversion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__ADOVERSION = 4;

	/**
	 * The feature id for the '<em><b>Database</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__DATABASE = 5;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__DATE = 6;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__TIME = 7;

	/**
	 * The feature id for the '<em><b>Username</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__USERNAME = 8;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE__VERSION = 9;

	/**
	 * The number of structural features of the '<em>ADOXML Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>ADOXML Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADOXML_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELSTypeImpl <em>APPLICATIONMODELS Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELSTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getAPPLICATIONMODELSType()
	 * @generated
	 */
	int APPLICATIONMODELS_TYPE = 1;

	/**
	 * The feature id for the '<em><b>APPLICATIONMODEL</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODELS_TYPE__APPLICATIONMODEL = 0;

	/**
	 * The number of structural features of the '<em>APPLICATIONMODELS Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODELS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>APPLICATIONMODELS Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODELS_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELTypeImpl <em>APPLICATIONMODEL Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getAPPLICATIONMODELType()
	 * @generated
	 */
	int APPLICATIONMODEL_TYPE = 2;

	/**
	 * The feature id for the '<em><b>MODELREFERENCE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODEL_TYPE__MODELREFERENCE = 0;

	/**
	 * The feature id for the '<em><b>Applib</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODEL_TYPE__APPLIB = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODEL_TYPE__NAME = 2;

	/**
	 * The number of structural features of the '<em>APPLICATIONMODEL Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODEL_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>APPLICATIONMODEL Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATIONMODEL_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILESTypeImpl <em>ATTRIBUTEPROFILES Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILESTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRIBUTEPROFILESType()
	 * @generated
	 */
	int ATTRIBUTEPROFILES_TYPE = 3;

	/**
	 * The feature id for the '<em><b>ATTRPROFDIR</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR = 0;

	/**
	 * The feature id for the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE = 1;

	/**
	 * The number of structural features of the '<em>ATTRIBUTEPROFILES Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILES_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>ATTRIBUTEPROFILES Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILES_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILETypeImpl <em>ATTRIBUTEPROFILE Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILETypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRIBUTEPROFILEType()
	 * @generated
	 */
	int ATTRIBUTEPROFILE_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>ATTRIBUTE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>RECORD</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__RECORD = 2;

	/**
	 * The feature id for the '<em><b>INTERREF</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__INTERREF = 3;

	/**
	 * The feature id for the '<em><b>Applib</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__APPLIB = 4;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__CLASS = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__NAME = 6;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE__VERSION = 7;

	/**
	 * The number of structural features of the '<em>ATTRIBUTEPROFILE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>ATTRIBUTEPROFILE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTEPROFILE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTETypeImpl <em>ATTRIBUTE Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTETypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRIBUTEType()
	 * @generated
	 */
	int ATTRIBUTE_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__MIXED = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE__VALUE = 3;

	/**
	 * The number of structural features of the '<em>ATTRIBUTE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>ATTRIBUTE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRPROFDIRTypeImpl <em>ATTRPROFDIR Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRPROFDIRTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRPROFDIRType()
	 * @generated
	 */
	int ATTRPROFDIR_TYPE = 6;

	/**
	 * The feature id for the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE = 0;

	/**
	 * The feature id for the '<em><b>ATTRPROFDIR</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRPROFDIR_TYPE__ATTRPROFDIR = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRPROFDIR_TYPE__NAME = 2;

	/**
	 * The number of structural features of the '<em>ATTRPROFDIR Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRPROFDIR_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>ATTRPROFDIR Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRPROFDIR_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.CONNECTORTypeImpl <em>CONNECTOR Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.CONNECTORTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getCONNECTORType()
	 * @generated
	 */
	int CONNECTOR_TYPE = 7;

	/**
	 * The feature id for the '<em><b>FROM</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__FROM = 0;

	/**
	 * The feature id for the '<em><b>TO</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__TO = 1;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__GROUP = 2;

	/**
	 * The feature id for the '<em><b>ATTRIBUTE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__ATTRIBUTE = 3;

	/**
	 * The feature id for the '<em><b>RECORD</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__RECORD = 4;

	/**
	 * The feature id for the '<em><b>INTERREF</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__INTERREF = 5;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__CLASS = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__ID = 7;

	/**
	 * The number of structural features of the '<em>CONNECTOR Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>CONNECTOR Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 8;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>ADOXML</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ADOXML = 3;

	/**
	 * The feature id for the '<em><b>APPLICATIONMODEL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLICATIONMODEL = 4;

	/**
	 * The feature id for the '<em><b>APPLICATIONMODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__APPLICATIONMODELS = 5;

	/**
	 * The feature id for the '<em><b>ATTRIBUTE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTE = 6;

	/**
	 * The feature id for the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTEPROFILE = 7;

	/**
	 * The feature id for the '<em><b>ATTRIBUTEPROFILES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRIBUTEPROFILES = 8;

	/**
	 * The feature id for the '<em><b>ATTRPROFDIR</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTRPROFDIR = 9;

	/**
	 * The feature id for the '<em><b>CONNECTOR</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONNECTOR = 10;

	/**
	 * The feature id for the '<em><b>FROM</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FROM = 11;

	/**
	 * The feature id for the '<em><b>INSTANCE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INSTANCE = 12;

	/**
	 * The feature id for the '<em><b>INTERREF</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__INTERREF = 13;

	/**
	 * The feature id for the '<em><b>IREF</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IREF = 14;

	/**
	 * The feature id for the '<em><b>MODEL</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODEL = 15;

	/**
	 * The feature id for the '<em><b>MODELATTRIBUTES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODELATTRIBUTES = 16;

	/**
	 * The feature id for the '<em><b>MODELGROUP</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODELGROUP = 17;

	/**
	 * The feature id for the '<em><b>MODELGROUPS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODELGROUPS = 18;

	/**
	 * The feature id for the '<em><b>MODELREFERENCE</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODELREFERENCE = 19;

	/**
	 * The feature id for the '<em><b>MODELS</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MODELS = 20;

	/**
	 * The feature id for the '<em><b>RECORD</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RECORD = 21;

	/**
	 * The feature id for the '<em><b>ROW</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ROW = 22;

	/**
	 * The feature id for the '<em><b>TO</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TO = 23;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 24;

	/**
	 * The number of operations of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.FROMTypeImpl <em>FROM Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.FROMTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getFROMType()
	 * @generated
	 */
	int FROM_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_TYPE__CLASS = 0;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_TYPE__INSTANCE = 1;

	/**
	 * The number of structural features of the '<em>FROM Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>FROM Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl <em>INSTANCE Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getINSTANCEType()
	 * @generated
	 */
	int INSTANCE_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>ATTRIBUTE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE__ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>RECORD</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE__RECORD = 2;

	/**
	 * The feature id for the '<em><b>INTERREF</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE__INTERREF = 3;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE__CLASS = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE__ID = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE__NAME = 6;

	/**
	 * The number of structural features of the '<em>INSTANCE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>INSTANCE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INTERREFTypeImpl <em>INTERREF Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.INTERREFTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getINTERREFType()
	 * @generated
	 */
	int INTERREF_TYPE = 11;

	/**
	 * The feature id for the '<em><b>IREF</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERREF_TYPE__IREF = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERREF_TYPE__NAME = 1;

	/**
	 * The number of structural features of the '<em>INTERREF Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERREF_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>INTERREF Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERREF_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl <em>IREF Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getIREFType()
	 * @generated
	 */
	int IREF_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Tclassname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE__TCLASSNAME = 0;

	/**
	 * The feature id for the '<em><b>Tmodelname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE__TMODELNAME = 1;

	/**
	 * The feature id for the '<em><b>Tmodeltype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE__TMODELTYPE = 2;

	/**
	 * The feature id for the '<em><b>Tmodelver</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE__TMODELVER = 3;

	/**
	 * The feature id for the '<em><b>Tobjname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE__TOBJNAME = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE__TYPE = 5;

	/**
	 * The number of structural features of the '<em>IREF Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>IREF Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IREF_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELATTRIBUTESTypeImpl <em>MODELATTRIBUTES Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELATTRIBUTESTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELATTRIBUTESType()
	 * @generated
	 */
	int MODELATTRIBUTES_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELATTRIBUTES_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>ATTRIBUTE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELATTRIBUTES_TYPE__ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>RECORD</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELATTRIBUTES_TYPE__RECORD = 2;

	/**
	 * The number of structural features of the '<em>MODELATTRIBUTES Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELATTRIBUTES_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>MODELATTRIBUTES Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELATTRIBUTES_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPSTypeImpl <em>MODELGROUPS Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPSTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELGROUPSType()
	 * @generated
	 */
	int MODELGROUPS_TYPE = 14;

	/**
	 * The feature id for the '<em><b>MODELGROUP</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUPS_TYPE__MODELGROUP = 0;

	/**
	 * The number of structural features of the '<em>MODELGROUPS Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUPS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>MODELGROUPS Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUPS_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPTypeImpl <em>MODELGROUP Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELGROUPType()
	 * @generated
	 */
	int MODELGROUP_TYPE = 15;

	/**
	 * The feature id for the '<em><b>MODELREFERENCE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUP_TYPE__MODELREFERENCE = 0;

	/**
	 * The feature id for the '<em><b>MODELGROUP</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUP_TYPE__MODELGROUP = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUP_TYPE__NAME = 2;

	/**
	 * The number of structural features of the '<em>MODELGROUP Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUP_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>MODELGROUP Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELGROUP_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl <em>MODELREFERENCE Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELREFERENCEType()
	 * @generated
	 */
	int MODELREFERENCE_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Libtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELREFERENCE_TYPE__LIBTYPE = 0;

	/**
	 * The feature id for the '<em><b>Modeltype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELREFERENCE_TYPE__MODELTYPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELREFERENCE_TYPE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELREFERENCE_TYPE__VERSION = 3;

	/**
	 * The number of structural features of the '<em>MODELREFERENCE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELREFERENCE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>MODELREFERENCE Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELREFERENCE_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELSTypeImpl <em>MODELS Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELSTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELSType()
	 * @generated
	 */
	int MODELS_TYPE = 17;

	/**
	 * The feature id for the '<em><b>MODEL</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELS_TYPE__MODEL = 0;

	/**
	 * The number of structural features of the '<em>MODELS Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELS_TYPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>MODELS Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELS_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl <em>MODEL Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELType()
	 * @generated
	 */
	int MODEL_TYPE = 18;

	/**
	 * The feature id for the '<em><b>MODELATTRIBUTES</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__MODELATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>INSTANCE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__INSTANCE = 1;

	/**
	 * The feature id for the '<em><b>CONNECTOR</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__CONNECTOR = 2;

	/**
	 * The feature id for the '<em><b>Applib</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__APPLIB = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Libtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__LIBTYPE = 5;

	/**
	 * The feature id for the '<em><b>Modeltype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__MODELTYPE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__NAME = 7;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE__VERSION = 8;

	/**
	 * The number of structural features of the '<em>MODEL Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>MODEL Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.RECORDTypeImpl <em>RECORD Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.RECORDTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getRECORDType()
	 * @generated
	 */
	int RECORD_TYPE = 19;

	/**
	 * The feature id for the '<em><b>ROW</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_TYPE__ROW = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_TYPE__NAME = 1;

	/**
	 * The number of structural features of the '<em>RECORD Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>RECORD Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECORD_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ROWTypeImpl <em>ROW Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ROWTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getROWType()
	 * @generated
	 */
	int ROW_TYPE = 20;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>ATTRIBUTE</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_TYPE__ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>INTERREF</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_TYPE__INTERREF = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_TYPE__ID = 3;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_TYPE__NUMBER = 4;

	/**
	 * The number of structural features of the '<em>ROW Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_TYPE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>ROW Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.TOTypeImpl <em>TO Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.TOTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getTOType()
	 * @generated
	 */
	int TO_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_TYPE__CLASS = 0;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_TYPE__INSTANCE = 1;

	/**
	 * The number of structural features of the '<em>TO Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_TYPE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>TO Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_TYPE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType <em>ADOXML Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ADOXML Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType
	 * @generated
	 */
	EClass getADOXMLType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ATTRIBUTEPROFILES</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getATTRIBUTEPROFILES()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EReference getADOXMLType_ATTRIBUTEPROFILES();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELS <em>MODELS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELS</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELS()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EReference getADOXMLType_MODELS();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>APPLICATIONMODELS</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAPPLICATIONMODELS()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EReference getADOXMLType_APPLICATIONMODELS();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELGROUPS <em>MODELGROUPS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELGROUPS</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getMODELGROUPS()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EReference getADOXMLType_MODELGROUPS();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAdoversion <em>Adoversion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adoversion</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getAdoversion()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EAttribute getADOXMLType_Adoversion();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Database</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDatabase()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EAttribute getADOXMLType_Database();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getDate()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EAttribute getADOXMLType_Date();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getTime()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EAttribute getADOXMLType_Time();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getUsername <em>Username</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Username</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getUsername()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EAttribute getADOXMLType_Username();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ADOXMLType#getVersion()
	 * @see #getADOXMLType()
	 * @generated
	 */
	EAttribute getADOXMLType_Version();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType <em>APPLICATIONMODELS Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>APPLICATIONMODELS Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType
	 * @generated
	 */
	EClass getAPPLICATIONMODELSType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType#getAPPLICATIONMODEL <em>APPLICATIONMODEL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>APPLICATIONMODEL</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELSType#getAPPLICATIONMODEL()
	 * @see #getAPPLICATIONMODELSType()
	 * @generated
	 */
	EReference getAPPLICATIONMODELSType_APPLICATIONMODEL();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType <em>APPLICATIONMODEL Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>APPLICATIONMODEL Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType
	 * @generated
	 */
	EClass getAPPLICATIONMODELType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getMODELREFERENCE <em>MODELREFERENCE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>MODELREFERENCE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getMODELREFERENCE()
	 * @see #getAPPLICATIONMODELType()
	 * @generated
	 */
	EReference getAPPLICATIONMODELType_MODELREFERENCE();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getApplib <em>Applib</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Applib</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getApplib()
	 * @see #getAPPLICATIONMODELType()
	 * @generated
	 */
	EAttribute getAPPLICATIONMODELType_Applib();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.APPLICATIONMODELType#getName()
	 * @see #getAPPLICATIONMODELType()
	 * @generated
	 */
	EAttribute getAPPLICATIONMODELType_Name();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType <em>ATTRIBUTEPROFILES Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ATTRIBUTEPROFILES Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType
	 * @generated
	 */
	EClass getATTRIBUTEPROFILESType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRPROFDIR <em>ATTRPROFDIR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ATTRPROFDIR</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRPROFDIR()
	 * @see #getATTRIBUTEPROFILESType()
	 * @generated
	 */
	EReference getATTRIBUTEPROFILESType_ATTRPROFDIR();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ATTRIBUTEPROFILE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILESType#getATTRIBUTEPROFILE()
	 * @see #getATTRIBUTEPROFILESType()
	 * @generated
	 */
	EReference getATTRIBUTEPROFILESType_ATTRIBUTEPROFILE();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType <em>ATTRIBUTEPROFILE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ATTRIBUTEPROFILE Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType
	 * @generated
	 */
	EClass getATTRIBUTEPROFILEType();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getGroup()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEPROFILEType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getATTRIBUTE <em>ATTRIBUTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ATTRIBUTE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getATTRIBUTE()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EReference getATTRIBUTEPROFILEType_ATTRIBUTE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getRECORD <em>RECORD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RECORD</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getRECORD()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EReference getATTRIBUTEPROFILEType_RECORD();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getINTERREF <em>INTERREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>INTERREF</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getINTERREF()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EReference getATTRIBUTEPROFILEType_INTERREF();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getApplib <em>Applib</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Applib</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getApplib()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEPROFILEType_Applib();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getClass_()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEPROFILEType_Class();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getName()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEPROFILEType_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEPROFILEType#getVersion()
	 * @see #getATTRIBUTEPROFILEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEPROFILEType_Version();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType <em>ATTRIBUTE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ATTRIBUTE Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType
	 * @generated
	 */
	EClass getATTRIBUTEType();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getMixed()
	 * @see #getATTRIBUTEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEType_Mixed();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getName()
	 * @see #getATTRIBUTEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEType_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getType()
	 * @see #getATTRIBUTEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEType_Type();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRIBUTEType#getValue()
	 * @see #getATTRIBUTEType()
	 * @generated
	 */
	EAttribute getATTRIBUTEType_Value();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType <em>ATTRPROFDIR Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ATTRPROFDIR Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType
	 * @generated
	 */
	EClass getATTRPROFDIRType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ATTRIBUTEPROFILE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType#getATTRIBUTEPROFILE()
	 * @see #getATTRPROFDIRType()
	 * @generated
	 */
	EReference getATTRPROFDIRType_ATTRIBUTEPROFILE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType#getATTRPROFDIR <em>ATTRPROFDIR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ATTRPROFDIR</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType#getATTRPROFDIR()
	 * @see #getATTRPROFDIRType()
	 * @generated
	 */
	EReference getATTRPROFDIRType_ATTRPROFDIR();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ATTRPROFDIRType#getName()
	 * @see #getATTRPROFDIRType()
	 * @generated
	 */
	EAttribute getATTRPROFDIRType_Name();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType <em>CONNECTOR Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CONNECTOR Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType
	 * @generated
	 */
	EClass getCONNECTORType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getFROM <em>FROM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>FROM</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getFROM()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EReference getCONNECTORType_FROM();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getTO <em>TO</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>TO</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getTO()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EReference getCONNECTORType_TO();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getGroup()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EAttribute getCONNECTORType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getATTRIBUTE <em>ATTRIBUTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ATTRIBUTE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getATTRIBUTE()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EReference getCONNECTORType_ATTRIBUTE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getRECORD <em>RECORD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RECORD</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getRECORD()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EReference getCONNECTORType_RECORD();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getINTERREF <em>INTERREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>INTERREF</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getINTERREF()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EReference getCONNECTORType_INTERREF();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getClass_()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EAttribute getCONNECTORType_Class();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.CONNECTORType#getId()
	 * @see #getCONNECTORType()
	 * @generated
	 */
	EAttribute getCONNECTORType_Id();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getADOXML <em>ADOXML</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ADOXML</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getADOXML()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ADOXML();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODEL <em>APPLICATIONMODEL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>APPLICATIONMODEL</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODEL()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_APPLICATIONMODEL();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODELS <em>APPLICATIONMODELS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>APPLICATIONMODELS</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getAPPLICATIONMODELS()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_APPLICATIONMODELS();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTE <em>ATTRIBUTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ATTRIBUTE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTE()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ATTRIBUTE();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILE <em>ATTRIBUTEPROFILE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ATTRIBUTEPROFILE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILE()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ATTRIBUTEPROFILE();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILES <em>ATTRIBUTEPROFILES</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ATTRIBUTEPROFILES</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRIBUTEPROFILES()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ATTRIBUTEPROFILES();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRPROFDIR <em>ATTRPROFDIR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ATTRPROFDIR</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getATTRPROFDIR()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ATTRPROFDIR();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getCONNECTOR <em>CONNECTOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>CONNECTOR</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getCONNECTOR()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_CONNECTOR();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getFROM <em>FROM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>FROM</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getFROM()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FROM();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINSTANCE <em>INSTANCE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>INSTANCE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINSTANCE()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_INSTANCE();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINTERREF <em>INTERREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>INTERREF</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getINTERREF()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_INTERREF();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getIREF <em>IREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>IREF</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getIREF()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_IREF();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODEL <em>MODEL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODEL</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODEL()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MODEL();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELATTRIBUTES</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELATTRIBUTES()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MODELATTRIBUTES();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUP <em>MODELGROUP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELGROUP</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUP()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MODELGROUP();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUPS <em>MODELGROUPS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELGROUPS</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELGROUPS()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MODELGROUPS();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELREFERENCE <em>MODELREFERENCE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELREFERENCE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELREFERENCE()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MODELREFERENCE();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELS <em>MODELS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELS</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getMODELS()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MODELS();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getRECORD <em>RECORD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>RECORD</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getRECORD()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RECORD();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getROW <em>ROW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>ROW</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getROW()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ROW();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getTO <em>TO</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>TO</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.DocumentRoot#getTO()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TO();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.FROMType <em>FROM Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FROM Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.FROMType
	 * @generated
	 */
	EClass getFROMType();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.FROMType#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.FROMType#getClass_()
	 * @see #getFROMType()
	 * @generated
	 */
	EAttribute getFROMType_Class();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.FROMType#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.FROMType#getInstance()
	 * @see #getFROMType()
	 * @generated
	 */
	EAttribute getFROMType_Instance();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType <em>INSTANCE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INSTANCE Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType
	 * @generated
	 */
	EClass getINSTANCEType();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getGroup()
	 * @see #getINSTANCEType()
	 * @generated
	 */
	EAttribute getINSTANCEType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getATTRIBUTE <em>ATTRIBUTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ATTRIBUTE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getATTRIBUTE()
	 * @see #getINSTANCEType()
	 * @generated
	 */
	EReference getINSTANCEType_ATTRIBUTE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getRECORD <em>RECORD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RECORD</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getRECORD()
	 * @see #getINSTANCEType()
	 * @generated
	 */
	EReference getINSTANCEType_RECORD();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getINTERREF <em>INTERREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>INTERREF</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getINTERREF()
	 * @see #getINSTANCEType()
	 * @generated
	 */
	EReference getINSTANCEType_INTERREF();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getClass_()
	 * @see #getINSTANCEType()
	 * @generated
	 */
	EAttribute getINSTANCEType_Class();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getId()
	 * @see #getINSTANCEType()
	 * @generated
	 */
	EAttribute getINSTANCEType_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INSTANCEType#getName()
	 * @see #getINSTANCEType()
	 * @generated
	 */
	EAttribute getINSTANCEType_Name();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType <em>INTERREF Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INTERREF Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType
	 * @generated
	 */
	EClass getINTERREFType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType#getIREF <em>IREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>IREF</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType#getIREF()
	 * @see #getINTERREFType()
	 * @generated
	 */
	EReference getINTERREFType_IREF();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.INTERREFType#getName()
	 * @see #getINTERREFType()
	 * @generated
	 */
	EAttribute getINTERREFType_Name();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType <em>IREF Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IREF Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.IREFType
	 * @generated
	 */
	EClass getIREFType();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTclassname <em>Tclassname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tclassname</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTclassname()
	 * @see #getIREFType()
	 * @generated
	 */
	EAttribute getIREFType_Tclassname();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTmodelname <em>Tmodelname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tmodelname</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTmodelname()
	 * @see #getIREFType()
	 * @generated
	 */
	EAttribute getIREFType_Tmodelname();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTmodeltype <em>Tmodeltype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tmodeltype</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTmodeltype()
	 * @see #getIREFType()
	 * @generated
	 */
	EAttribute getIREFType_Tmodeltype();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTmodelver <em>Tmodelver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tmodelver</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTmodelver()
	 * @see #getIREFType()
	 * @generated
	 */
	EAttribute getIREFType_Tmodelver();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTobjname <em>Tobjname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tobjname</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getTobjname()
	 * @see #getIREFType()
	 * @generated
	 */
	EAttribute getIREFType_Tobjname();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.IREFType#getType()
	 * @see #getIREFType()
	 * @generated
	 */
	EAttribute getIREFType_Type();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType <em>MODELATTRIBUTES Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MODELATTRIBUTES Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType
	 * @generated
	 */
	EClass getMODELATTRIBUTESType();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getGroup()
	 * @see #getMODELATTRIBUTESType()
	 * @generated
	 */
	EAttribute getMODELATTRIBUTESType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getATTRIBUTE <em>ATTRIBUTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ATTRIBUTE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getATTRIBUTE()
	 * @see #getMODELATTRIBUTESType()
	 * @generated
	 */
	EReference getMODELATTRIBUTESType_ATTRIBUTE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getRECORD <em>RECORD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>RECORD</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELATTRIBUTESType#getRECORD()
	 * @see #getMODELATTRIBUTESType()
	 * @generated
	 */
	EReference getMODELATTRIBUTESType_RECORD();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType <em>MODELGROUPS Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MODELGROUPS Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType
	 * @generated
	 */
	EClass getMODELGROUPSType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType#getMODELGROUP <em>MODELGROUP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>MODELGROUP</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPSType#getMODELGROUP()
	 * @see #getMODELGROUPSType()
	 * @generated
	 */
	EReference getMODELGROUPSType_MODELGROUP();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType <em>MODELGROUP Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MODELGROUP Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType
	 * @generated
	 */
	EClass getMODELGROUPType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getMODELREFERENCE <em>MODELREFERENCE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>MODELREFERENCE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getMODELREFERENCE()
	 * @see #getMODELGROUPType()
	 * @generated
	 */
	EReference getMODELGROUPType_MODELREFERENCE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getMODELGROUP <em>MODELGROUP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>MODELGROUP</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getMODELGROUP()
	 * @see #getMODELGROUPType()
	 * @generated
	 */
	EReference getMODELGROUPType_MODELGROUP();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELGROUPType#getName()
	 * @see #getMODELGROUPType()
	 * @generated
	 */
	EAttribute getMODELGROUPType_Name();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType <em>MODELREFERENCE Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MODELREFERENCE Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType
	 * @generated
	 */
	EClass getMODELREFERENCEType();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getLibtype <em>Libtype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Libtype</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getLibtype()
	 * @see #getMODELREFERENCEType()
	 * @generated
	 */
	EAttribute getMODELREFERENCEType_Libtype();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getModeltype <em>Modeltype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modeltype</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getModeltype()
	 * @see #getMODELREFERENCEType()
	 * @generated
	 */
	EAttribute getMODELREFERENCEType_Modeltype();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getName()
	 * @see #getMODELREFERENCEType()
	 * @generated
	 */
	EAttribute getMODELREFERENCEType_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELREFERENCEType#getVersion()
	 * @see #getMODELREFERENCEType()
	 * @generated
	 */
	EAttribute getMODELREFERENCEType_Version();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELSType <em>MODELS Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MODELS Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELSType
	 * @generated
	 */
	EClass getMODELSType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELSType#getMODEL <em>MODEL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>MODEL</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELSType#getMODEL()
	 * @see #getMODELSType()
	 * @generated
	 */
	EReference getMODELSType_MODEL();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType <em>MODEL Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>MODEL Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType
	 * @generated
	 */
	EClass getMODELType();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getMODELATTRIBUTES <em>MODELATTRIBUTES</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>MODELATTRIBUTES</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getMODELATTRIBUTES()
	 * @see #getMODELType()
	 * @generated
	 */
	EReference getMODELType_MODELATTRIBUTES();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getINSTANCE <em>INSTANCE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>INSTANCE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getINSTANCE()
	 * @see #getMODELType()
	 * @generated
	 */
	EReference getMODELType_INSTANCE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getCONNECTOR <em>CONNECTOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>CONNECTOR</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getCONNECTOR()
	 * @see #getMODELType()
	 * @generated
	 */
	EReference getMODELType_CONNECTOR();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getApplib <em>Applib</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Applib</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getApplib()
	 * @see #getMODELType()
	 * @generated
	 */
	EAttribute getMODELType_Applib();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getId()
	 * @see #getMODELType()
	 * @generated
	 */
	EAttribute getMODELType_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getLibtype <em>Libtype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Libtype</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getLibtype()
	 * @see #getMODELType()
	 * @generated
	 */
	EAttribute getMODELType_Libtype();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getModeltype <em>Modeltype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modeltype</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getModeltype()
	 * @see #getMODELType()
	 * @generated
	 */
	EAttribute getMODELType_Modeltype();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getName()
	 * @see #getMODELType()
	 * @generated
	 */
	EAttribute getMODELType_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.MODELType#getVersion()
	 * @see #getMODELType()
	 * @generated
	 */
	EAttribute getMODELType_Version();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.RECORDType <em>RECORD Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RECORD Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.RECORDType
	 * @generated
	 */
	EClass getRECORDType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.RECORDType#getROW <em>ROW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ROW</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.RECORDType#getROW()
	 * @see #getRECORDType()
	 * @generated
	 */
	EReference getRECORDType_ROW();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.RECORDType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.RECORDType#getName()
	 * @see #getRECORDType()
	 * @generated
	 */
	EAttribute getRECORDType_Name();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.ROWType <em>ROW Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ROW Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ROWType
	 * @generated
	 */
	EClass getROWType();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getGroup()
	 * @see #getROWType()
	 * @generated
	 */
	EAttribute getROWType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getATTRIBUTE <em>ATTRIBUTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>ATTRIBUTE</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getATTRIBUTE()
	 * @see #getROWType()
	 * @generated
	 */
	EReference getROWType_ATTRIBUTE();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getINTERREF <em>INTERREF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>INTERREF</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getINTERREF()
	 * @see #getROWType()
	 * @generated
	 */
	EReference getROWType_INTERREF();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getId()
	 * @see #getROWType()
	 * @generated
	 */
	EAttribute getROWType_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.ROWType#getNumber()
	 * @see #getROWType()
	 * @generated
	 */
	EAttribute getROWType_Number();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.ado.TOType <em>TO Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>TO Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.TOType
	 * @generated
	 */
	EClass getTOType();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.TOType#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.TOType#getClass_()
	 * @see #getTOType()
	 * @generated
	 */
	EAttribute getTOType_Class();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.ado.TOType#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.ado.TOType#getInstance()
	 * @see #getTOType()
	 * @generated
	 */
	EAttribute getTOType_Instance();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AdoFactory getAdoFactory();

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
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl <em>ADOXML Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ADOXMLTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getADOXMLType()
		 * @generated
		 */
		EClass ADOXML_TYPE = eINSTANCE.getADOXMLType();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTEPROFILES</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADOXML_TYPE__ATTRIBUTEPROFILES = eINSTANCE.getADOXMLType_ATTRIBUTEPROFILES();

		/**
		 * The meta object literal for the '<em><b>MODELS</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADOXML_TYPE__MODELS = eINSTANCE.getADOXMLType_MODELS();

		/**
		 * The meta object literal for the '<em><b>APPLICATIONMODELS</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADOXML_TYPE__APPLICATIONMODELS = eINSTANCE.getADOXMLType_APPLICATIONMODELS();

		/**
		 * The meta object literal for the '<em><b>MODELGROUPS</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADOXML_TYPE__MODELGROUPS = eINSTANCE.getADOXMLType_MODELGROUPS();

		/**
		 * The meta object literal for the '<em><b>Adoversion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADOXML_TYPE__ADOVERSION = eINSTANCE.getADOXMLType_Adoversion();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADOXML_TYPE__DATABASE = eINSTANCE.getADOXMLType_Database();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADOXML_TYPE__DATE = eINSTANCE.getADOXMLType_Date();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADOXML_TYPE__TIME = eINSTANCE.getADOXMLType_Time();

		/**
		 * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADOXML_TYPE__USERNAME = eINSTANCE.getADOXMLType_Username();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADOXML_TYPE__VERSION = eINSTANCE.getADOXMLType_Version();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELSTypeImpl <em>APPLICATIONMODELS Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELSTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getAPPLICATIONMODELSType()
		 * @generated
		 */
		EClass APPLICATIONMODELS_TYPE = eINSTANCE.getAPPLICATIONMODELSType();

		/**
		 * The meta object literal for the '<em><b>APPLICATIONMODEL</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATIONMODELS_TYPE__APPLICATIONMODEL = eINSTANCE.getAPPLICATIONMODELSType_APPLICATIONMODEL();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELTypeImpl <em>APPLICATIONMODEL Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.APPLICATIONMODELTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getAPPLICATIONMODELType()
		 * @generated
		 */
		EClass APPLICATIONMODEL_TYPE = eINSTANCE.getAPPLICATIONMODELType();

		/**
		 * The meta object literal for the '<em><b>MODELREFERENCE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATIONMODEL_TYPE__MODELREFERENCE = eINSTANCE.getAPPLICATIONMODELType_MODELREFERENCE();

		/**
		 * The meta object literal for the '<em><b>Applib</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATIONMODEL_TYPE__APPLIB = eINSTANCE.getAPPLICATIONMODELType_Applib();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATIONMODEL_TYPE__NAME = eINSTANCE.getAPPLICATIONMODELType_Name();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILESTypeImpl <em>ATTRIBUTEPROFILES Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILESTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRIBUTEPROFILESType()
		 * @generated
		 */
		EClass ATTRIBUTEPROFILES_TYPE = eINSTANCE.getATTRIBUTEPROFILESType();

		/**
		 * The meta object literal for the '<em><b>ATTRPROFDIR</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTEPROFILES_TYPE__ATTRPROFDIR = eINSTANCE.getATTRIBUTEPROFILESType_ATTRPROFDIR();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTEPROFILES_TYPE__ATTRIBUTEPROFILE = eINSTANCE.getATTRIBUTEPROFILESType_ATTRIBUTEPROFILE();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILETypeImpl <em>ATTRIBUTEPROFILE Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTEPROFILETypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRIBUTEPROFILEType()
		 * @generated
		 */
		EClass ATTRIBUTEPROFILE_TYPE = eINSTANCE.getATTRIBUTEPROFILEType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTEPROFILE_TYPE__GROUP = eINSTANCE.getATTRIBUTEPROFILEType_Group();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTEPROFILE_TYPE__ATTRIBUTE = eINSTANCE.getATTRIBUTEPROFILEType_ATTRIBUTE();

		/**
		 * The meta object literal for the '<em><b>RECORD</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTEPROFILE_TYPE__RECORD = eINSTANCE.getATTRIBUTEPROFILEType_RECORD();

		/**
		 * The meta object literal for the '<em><b>INTERREF</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTEPROFILE_TYPE__INTERREF = eINSTANCE.getATTRIBUTEPROFILEType_INTERREF();

		/**
		 * The meta object literal for the '<em><b>Applib</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTEPROFILE_TYPE__APPLIB = eINSTANCE.getATTRIBUTEPROFILEType_Applib();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTEPROFILE_TYPE__CLASS = eINSTANCE.getATTRIBUTEPROFILEType_Class();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTEPROFILE_TYPE__NAME = eINSTANCE.getATTRIBUTEPROFILEType_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTEPROFILE_TYPE__VERSION = eINSTANCE.getATTRIBUTEPROFILEType_Version();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTETypeImpl <em>ATTRIBUTE Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRIBUTETypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRIBUTEType()
		 * @generated
		 */
		EClass ATTRIBUTE_TYPE = eINSTANCE.getATTRIBUTEType();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_TYPE__MIXED = eINSTANCE.getATTRIBUTEType_Mixed();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_TYPE__NAME = eINSTANCE.getATTRIBUTEType_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_TYPE__TYPE = eINSTANCE.getATTRIBUTEType_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_TYPE__VALUE = eINSTANCE.getATTRIBUTEType_Value();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRPROFDIRTypeImpl <em>ATTRPROFDIR Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ATTRPROFDIRTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getATTRPROFDIRType()
		 * @generated
		 */
		EClass ATTRPROFDIR_TYPE = eINSTANCE.getATTRPROFDIRType();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRPROFDIR_TYPE__ATTRIBUTEPROFILE = eINSTANCE.getATTRPROFDIRType_ATTRIBUTEPROFILE();

		/**
		 * The meta object literal for the '<em><b>ATTRPROFDIR</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRPROFDIR_TYPE__ATTRPROFDIR = eINSTANCE.getATTRPROFDIRType_ATTRPROFDIR();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRPROFDIR_TYPE__NAME = eINSTANCE.getATTRPROFDIRType_Name();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.CONNECTORTypeImpl <em>CONNECTOR Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.CONNECTORTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getCONNECTORType()
		 * @generated
		 */
		EClass CONNECTOR_TYPE = eINSTANCE.getCONNECTORType();

		/**
		 * The meta object literal for the '<em><b>FROM</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_TYPE__FROM = eINSTANCE.getCONNECTORType_FROM();

		/**
		 * The meta object literal for the '<em><b>TO</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_TYPE__TO = eINSTANCE.getCONNECTORType_TO();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_TYPE__GROUP = eINSTANCE.getCONNECTORType_Group();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_TYPE__ATTRIBUTE = eINSTANCE.getCONNECTORType_ATTRIBUTE();

		/**
		 * The meta object literal for the '<em><b>RECORD</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_TYPE__RECORD = eINSTANCE.getCONNECTORType_RECORD();

		/**
		 * The meta object literal for the '<em><b>INTERREF</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_TYPE__INTERREF = eINSTANCE.getCONNECTORType_INTERREF();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_TYPE__CLASS = eINSTANCE.getCONNECTORType_Class();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTOR_TYPE__ID = eINSTANCE.getCONNECTORType_Id();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.DocumentRootImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>ADOXML</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ADOXML = eINSTANCE.getDocumentRoot_ADOXML();

		/**
		 * The meta object literal for the '<em><b>APPLICATIONMODEL</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__APPLICATIONMODEL = eINSTANCE.getDocumentRoot_APPLICATIONMODEL();

		/**
		 * The meta object literal for the '<em><b>APPLICATIONMODELS</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__APPLICATIONMODELS = eINSTANCE.getDocumentRoot_APPLICATIONMODELS();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTE</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTRIBUTE = eINSTANCE.getDocumentRoot_ATTRIBUTE();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTEPROFILE</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTRIBUTEPROFILE = eINSTANCE.getDocumentRoot_ATTRIBUTEPROFILE();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTEPROFILES</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTRIBUTEPROFILES = eINSTANCE.getDocumentRoot_ATTRIBUTEPROFILES();

		/**
		 * The meta object literal for the '<em><b>ATTRPROFDIR</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTRPROFDIR = eINSTANCE.getDocumentRoot_ATTRPROFDIR();

		/**
		 * The meta object literal for the '<em><b>CONNECTOR</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONNECTOR = eINSTANCE.getDocumentRoot_CONNECTOR();

		/**
		 * The meta object literal for the '<em><b>FROM</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FROM = eINSTANCE.getDocumentRoot_FROM();

		/**
		 * The meta object literal for the '<em><b>INSTANCE</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__INSTANCE = eINSTANCE.getDocumentRoot_INSTANCE();

		/**
		 * The meta object literal for the '<em><b>INTERREF</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__INTERREF = eINSTANCE.getDocumentRoot_INTERREF();

		/**
		 * The meta object literal for the '<em><b>IREF</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IREF = eINSTANCE.getDocumentRoot_IREF();

		/**
		 * The meta object literal for the '<em><b>MODEL</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MODEL = eINSTANCE.getDocumentRoot_MODEL();

		/**
		 * The meta object literal for the '<em><b>MODELATTRIBUTES</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MODELATTRIBUTES = eINSTANCE.getDocumentRoot_MODELATTRIBUTES();

		/**
		 * The meta object literal for the '<em><b>MODELGROUP</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MODELGROUP = eINSTANCE.getDocumentRoot_MODELGROUP();

		/**
		 * The meta object literal for the '<em><b>MODELGROUPS</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MODELGROUPS = eINSTANCE.getDocumentRoot_MODELGROUPS();

		/**
		 * The meta object literal for the '<em><b>MODELREFERENCE</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MODELREFERENCE = eINSTANCE.getDocumentRoot_MODELREFERENCE();

		/**
		 * The meta object literal for the '<em><b>MODELS</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MODELS = eINSTANCE.getDocumentRoot_MODELS();

		/**
		 * The meta object literal for the '<em><b>RECORD</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RECORD = eINSTANCE.getDocumentRoot_RECORD();

		/**
		 * The meta object literal for the '<em><b>ROW</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ROW = eINSTANCE.getDocumentRoot_ROW();

		/**
		 * The meta object literal for the '<em><b>TO</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TO = eINSTANCE.getDocumentRoot_TO();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.FROMTypeImpl <em>FROM Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.FROMTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getFROMType()
		 * @generated
		 */
		EClass FROM_TYPE = eINSTANCE.getFROMType();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FROM_TYPE__CLASS = eINSTANCE.getFROMType_Class();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FROM_TYPE__INSTANCE = eINSTANCE.getFROMType_Instance();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl <em>INSTANCE Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.INSTANCETypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getINSTANCEType()
		 * @generated
		 */
		EClass INSTANCE_TYPE = eINSTANCE.getINSTANCEType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE_TYPE__GROUP = eINSTANCE.getINSTANCEType_Group();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_TYPE__ATTRIBUTE = eINSTANCE.getINSTANCEType_ATTRIBUTE();

		/**
		 * The meta object literal for the '<em><b>RECORD</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_TYPE__RECORD = eINSTANCE.getINSTANCEType_RECORD();

		/**
		 * The meta object literal for the '<em><b>INTERREF</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_TYPE__INTERREF = eINSTANCE.getINSTANCEType_INTERREF();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE_TYPE__CLASS = eINSTANCE.getINSTANCEType_Class();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE_TYPE__ID = eINSTANCE.getINSTANCEType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE_TYPE__NAME = eINSTANCE.getINSTANCEType_Name();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.INTERREFTypeImpl <em>INTERREF Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.INTERREFTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getINTERREFType()
		 * @generated
		 */
		EClass INTERREF_TYPE = eINSTANCE.getINTERREFType();

		/**
		 * The meta object literal for the '<em><b>IREF</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERREF_TYPE__IREF = eINSTANCE.getINTERREFType_IREF();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERREF_TYPE__NAME = eINSTANCE.getINTERREFType_Name();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl <em>IREF Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.IREFTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getIREFType()
		 * @generated
		 */
		EClass IREF_TYPE = eINSTANCE.getIREFType();

		/**
		 * The meta object literal for the '<em><b>Tclassname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IREF_TYPE__TCLASSNAME = eINSTANCE.getIREFType_Tclassname();

		/**
		 * The meta object literal for the '<em><b>Tmodelname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IREF_TYPE__TMODELNAME = eINSTANCE.getIREFType_Tmodelname();

		/**
		 * The meta object literal for the '<em><b>Tmodeltype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IREF_TYPE__TMODELTYPE = eINSTANCE.getIREFType_Tmodeltype();

		/**
		 * The meta object literal for the '<em><b>Tmodelver</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IREF_TYPE__TMODELVER = eINSTANCE.getIREFType_Tmodelver();

		/**
		 * The meta object literal for the '<em><b>Tobjname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IREF_TYPE__TOBJNAME = eINSTANCE.getIREFType_Tobjname();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IREF_TYPE__TYPE = eINSTANCE.getIREFType_Type();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELATTRIBUTESTypeImpl <em>MODELATTRIBUTES Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELATTRIBUTESTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELATTRIBUTESType()
		 * @generated
		 */
		EClass MODELATTRIBUTES_TYPE = eINSTANCE.getMODELATTRIBUTESType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELATTRIBUTES_TYPE__GROUP = eINSTANCE.getMODELATTRIBUTESType_Group();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELATTRIBUTES_TYPE__ATTRIBUTE = eINSTANCE.getMODELATTRIBUTESType_ATTRIBUTE();

		/**
		 * The meta object literal for the '<em><b>RECORD</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELATTRIBUTES_TYPE__RECORD = eINSTANCE.getMODELATTRIBUTESType_RECORD();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPSTypeImpl <em>MODELGROUPS Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPSTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELGROUPSType()
		 * @generated
		 */
		EClass MODELGROUPS_TYPE = eINSTANCE.getMODELGROUPSType();

		/**
		 * The meta object literal for the '<em><b>MODELGROUP</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELGROUPS_TYPE__MODELGROUP = eINSTANCE.getMODELGROUPSType_MODELGROUP();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPTypeImpl <em>MODELGROUP Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELGROUPTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELGROUPType()
		 * @generated
		 */
		EClass MODELGROUP_TYPE = eINSTANCE.getMODELGROUPType();

		/**
		 * The meta object literal for the '<em><b>MODELREFERENCE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELGROUP_TYPE__MODELREFERENCE = eINSTANCE.getMODELGROUPType_MODELREFERENCE();

		/**
		 * The meta object literal for the '<em><b>MODELGROUP</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELGROUP_TYPE__MODELGROUP = eINSTANCE.getMODELGROUPType_MODELGROUP();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELGROUP_TYPE__NAME = eINSTANCE.getMODELGROUPType_Name();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl <em>MODELREFERENCE Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELREFERENCETypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELREFERENCEType()
		 * @generated
		 */
		EClass MODELREFERENCE_TYPE = eINSTANCE.getMODELREFERENCEType();

		/**
		 * The meta object literal for the '<em><b>Libtype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELREFERENCE_TYPE__LIBTYPE = eINSTANCE.getMODELREFERENCEType_Libtype();

		/**
		 * The meta object literal for the '<em><b>Modeltype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELREFERENCE_TYPE__MODELTYPE = eINSTANCE.getMODELREFERENCEType_Modeltype();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELREFERENCE_TYPE__NAME = eINSTANCE.getMODELREFERENCEType_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELREFERENCE_TYPE__VERSION = eINSTANCE.getMODELREFERENCEType_Version();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELSTypeImpl <em>MODELS Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELSTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELSType()
		 * @generated
		 */
		EClass MODELS_TYPE = eINSTANCE.getMODELSType();

		/**
		 * The meta object literal for the '<em><b>MODEL</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELS_TYPE__MODEL = eINSTANCE.getMODELSType_MODEL();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl <em>MODEL Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.MODELTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getMODELType()
		 * @generated
		 */
		EClass MODEL_TYPE = eINSTANCE.getMODELType();

		/**
		 * The meta object literal for the '<em><b>MODELATTRIBUTES</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_TYPE__MODELATTRIBUTES = eINSTANCE.getMODELType_MODELATTRIBUTES();

		/**
		 * The meta object literal for the '<em><b>INSTANCE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_TYPE__INSTANCE = eINSTANCE.getMODELType_INSTANCE();

		/**
		 * The meta object literal for the '<em><b>CONNECTOR</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_TYPE__CONNECTOR = eINSTANCE.getMODELType_CONNECTOR();

		/**
		 * The meta object literal for the '<em><b>Applib</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_TYPE__APPLIB = eINSTANCE.getMODELType_Applib();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_TYPE__ID = eINSTANCE.getMODELType_Id();

		/**
		 * The meta object literal for the '<em><b>Libtype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_TYPE__LIBTYPE = eINSTANCE.getMODELType_Libtype();

		/**
		 * The meta object literal for the '<em><b>Modeltype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_TYPE__MODELTYPE = eINSTANCE.getMODELType_Modeltype();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_TYPE__NAME = eINSTANCE.getMODELType_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_TYPE__VERSION = eINSTANCE.getMODELType_Version();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.RECORDTypeImpl <em>RECORD Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.RECORDTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getRECORDType()
		 * @generated
		 */
		EClass RECORD_TYPE = eINSTANCE.getRECORDType();

		/**
		 * The meta object literal for the '<em><b>ROW</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECORD_TYPE__ROW = eINSTANCE.getRECORDType_ROW();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECORD_TYPE__NAME = eINSTANCE.getRECORDType_Name();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.ROWTypeImpl <em>ROW Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.ROWTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getROWType()
		 * @generated
		 */
		EClass ROW_TYPE = eINSTANCE.getROWType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROW_TYPE__GROUP = eINSTANCE.getROWType_Group();

		/**
		 * The meta object literal for the '<em><b>ATTRIBUTE</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROW_TYPE__ATTRIBUTE = eINSTANCE.getROWType_ATTRIBUTE();

		/**
		 * The meta object literal for the '<em><b>INTERREF</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROW_TYPE__INTERREF = eINSTANCE.getROWType_INTERREF();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROW_TYPE__ID = eINSTANCE.getROWType_Id();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROW_TYPE__NUMBER = eINSTANCE.getROWType_Number();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.ado.impl.TOTypeImpl <em>TO Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.TOTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.ado.impl.AdoPackageImpl#getTOType()
		 * @generated
		 */
		EClass TO_TYPE = eINSTANCE.getTOType();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TO_TYPE__CLASS = eINSTANCE.getTOType_Class();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TO_TYPE__INSTANCE = eINSTANCE.getTOType_Instance();

	}

} //AdoPackage
