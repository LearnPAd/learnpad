/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

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
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiFactory
 * @model kind="package"
 * @generated
 */
public interface XwikiPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "xwiki";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.xwiki.org";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xwiki";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XwikiPackage eINSTANCE = eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkCollectionImpl <em>Link Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkCollectionImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getLinkCollection()
	 * @generated
	 */
	int LINK_COLLECTION = 11;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_COLLECTION__LINK = 0;

	/**
	 * The number of structural features of the '<em>Link Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_COLLECTION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Link Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_COLLECTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl <em>Attachment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getAttachment()
	 * @generated
	 */
	int ATTACHMENT = 0;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__NAME = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__SIZE = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__VERSION = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__PAGE_ID = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Page Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__PAGE_VERSION = LINK_COLLECTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Mime Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__MIME_TYPE = LINK_COLLECTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__AUTHOR = LINK_COLLECTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__AUTHOR_NAME = LINK_COLLECTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__DATE = LINK_COLLECTION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Xwiki Relative Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__XWIKI_RELATIVE_URL = LINK_COLLECTION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Xwiki Absolute Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT__XWIKI_ABSOLUTE_URL = LINK_COLLECTION_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Attachment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 12;

	/**
	 * The number of operations of the '<em>Attachment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENT_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentsTypeImpl <em>Attachments Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentsTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getAttachmentsType()
	 * @generated
	 */
	int ATTACHMENTS_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENTS_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Attachment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENTS_TYPE__ATTACHMENT = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Attachments Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENTS_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Attachments Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTACHMENTS_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttributeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VALUE = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassImpl <em>Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getClass_()
	 * @generated
	 */
	int CLASS = 3;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__NAME = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS__PROPERTY = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassesTypeImpl <em>Classes Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassesTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getClassesType()
	 * @generated
	 */
	int CLASSES_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSES_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Class</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSES_TYPE__CLASS = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Classes Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSES_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Classes Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSES_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 5;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__PAGE_ID = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__AUTHOR = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__AUTHOR_NAME = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__DATE = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Highlight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__HIGHLIGHT = LINK_COLLECTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__TEXT = LINK_COLLECTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Reply To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__REPLY_TO = LINK_COLLECTION_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentsTypeImpl <em>Comments Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentsTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getCommentsType()
	 * @generated
	 */
	int COMMENTS_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTS_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTS_TYPE__COMMENT = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Comments Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTS_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Comments Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTS_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 7;

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
	 * The feature id for the '<em><b>Attachment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTACHMENT = 3;

	/**
	 * The feature id for the '<em><b>Attachments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ATTACHMENTS = 4;

	/**
	 * The feature id for the '<em><b>Class</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CLASS = 5;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CLASSES = 6;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMMENT = 7;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMMENTS = 8;

	/**
	 * The feature id for the '<em><b>History</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__HISTORY = 9;

	/**
	 * The feature id for the '<em><b>Object</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OBJECT = 10;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OBJECTS = 11;

	/**
	 * The feature id for the '<em><b>Object Summary</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OBJECT_SUMMARY = 12;

	/**
	 * The feature id for the '<em><b>Page</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PAGE = 13;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PAGES = 14;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTIES = 15;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY = 16;

	/**
	 * The feature id for the '<em><b>Search Result</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SEARCH_RESULT = 17;

	/**
	 * The feature id for the '<em><b>Search Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SEARCH_RESULTS = 18;

	/**
	 * The feature id for the '<em><b>Space</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPACE = 19;

	/**
	 * The feature id for the '<em><b>Spaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPACES = 20;

	/**
	 * The feature id for the '<em><b>Syntaxes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SYNTAXES = 21;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TAG = 22;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TAGS = 23;

	/**
	 * The feature id for the '<em><b>Translations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRANSLATIONS = 24;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WIKI = 25;

	/**
	 * The feature id for the '<em><b>Wikis</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WIKIS = 26;

	/**
	 * The feature id for the '<em><b>Xwiki</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XWIKI = 27;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 28;

	/**
	 * The number of operations of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl <em>History Summary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getHistorySummary()
	 * @generated
	 */
	int HISTORY_SUMMARY = 8;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__PAGE_ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__WIKI = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__SPACE = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__NAME = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__VERSION = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Major Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__MAJOR_VERSION = LINK_COLLECTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Minor Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__MINOR_VERSION = LINK_COLLECTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__MODIFIED = LINK_COLLECTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Modifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__MODIFIER = LINK_COLLECTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Modifier Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__MODIFIER_NAME = LINK_COLLECTION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__LANGUAGE = LINK_COLLECTION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY__COMMENT = LINK_COLLECTION_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>History Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 12;

	/**
	 * The number of operations of the '<em>History Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_SUMMARY_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistoryTypeImpl <em>History Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistoryTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getHistoryType()
	 * @generated
	 */
	int HISTORY_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>History Summary</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_TYPE__HISTORY_SUMMARY = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>History Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>History Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HISTORY_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 10;

	/**
	 * The feature id for the '<em><b>Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__HREF = 0;

	/**
	 * The feature id for the '<em><b>Href Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__HREF_LANG = 1;

	/**
	 * The feature id for the '<em><b>Rel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__REL = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__TYPE = 3;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl <em>Object Summary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getObjectSummary()
	 * @generated
	 */
	int OBJECT_SUMMARY = 14;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__GUID = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__PAGE_ID = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Page Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__PAGE_VERSION = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__WIKI = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__SPACE = LINK_COLLECTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Page Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__PAGE_NAME = LINK_COLLECTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Page Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__PAGE_AUTHOR = LINK_COLLECTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Page Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__PAGE_AUTHOR_NAME = LINK_COLLECTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__CLASS_NAME = LINK_COLLECTION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__NUMBER = LINK_COLLECTION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Headline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY__HEADLINE = LINK_COLLECTION_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Object Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 12;

	/**
	 * The number of operations of the '<em>Object Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_SUMMARY_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectImpl <em>Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 12;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__LINK = OBJECT_SUMMARY__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__ID = OBJECT_SUMMARY__ID;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__GUID = OBJECT_SUMMARY__GUID;

	/**
	 * The feature id for the '<em><b>Page Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__PAGE_ID = OBJECT_SUMMARY__PAGE_ID;

	/**
	 * The feature id for the '<em><b>Page Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__PAGE_VERSION = OBJECT_SUMMARY__PAGE_VERSION;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__WIKI = OBJECT_SUMMARY__WIKI;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__SPACE = OBJECT_SUMMARY__SPACE;

	/**
	 * The feature id for the '<em><b>Page Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__PAGE_NAME = OBJECT_SUMMARY__PAGE_NAME;

	/**
	 * The feature id for the '<em><b>Page Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__PAGE_AUTHOR = OBJECT_SUMMARY__PAGE_AUTHOR;

	/**
	 * The feature id for the '<em><b>Page Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__PAGE_AUTHOR_NAME = OBJECT_SUMMARY__PAGE_AUTHOR_NAME;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__CLASS_NAME = OBJECT_SUMMARY__CLASS_NAME;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__NUMBER = OBJECT_SUMMARY__NUMBER;

	/**
	 * The feature id for the '<em><b>Headline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__HEADLINE = OBJECT_SUMMARY__HEADLINE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__PROPERTY = OBJECT_SUMMARY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_FEATURE_COUNT = OBJECT_SUMMARY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_OPERATION_COUNT = OBJECT_SUMMARY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectsTypeImpl <em>Objects Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectsTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getObjectsType()
	 * @generated
	 */
	int OBJECTS_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTS_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Object Summary</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTS_TYPE__OBJECT_SUMMARY = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Objects Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTS_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Objects Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTS_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl <em>Page Summary</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPageSummary()
	 * @generated
	 */
	int PAGE_SUMMARY = 17;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__FULL_NAME = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__WIKI = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__SPACE = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__NAME = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__TITLE = LINK_COLLECTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__PARENT = LINK_COLLECTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Parent Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__PARENT_ID = LINK_COLLECTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__VERSION = LINK_COLLECTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__AUTHOR = LINK_COLLECTION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__AUTHOR_NAME = LINK_COLLECTION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Xwiki Relative Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__XWIKI_RELATIVE_URL = LINK_COLLECTION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Xwiki Absolute Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__XWIKI_ABSOLUTE_URL = LINK_COLLECTION_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Translations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__TRANSLATIONS = LINK_COLLECTION_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Syntax</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY__SYNTAX = LINK_COLLECTION_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>Page Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 15;

	/**
	 * The number of operations of the '<em>Page Summary</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_SUMMARY_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 15;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__LINK = PAGE_SUMMARY__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ID = PAGE_SUMMARY__ID;

	/**
	 * The feature id for the '<em><b>Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__FULL_NAME = PAGE_SUMMARY__FULL_NAME;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__WIKI = PAGE_SUMMARY__WIKI;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__SPACE = PAGE_SUMMARY__SPACE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAME = PAGE_SUMMARY__NAME;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__TITLE = PAGE_SUMMARY__TITLE;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PARENT = PAGE_SUMMARY__PARENT;

	/**
	 * The feature id for the '<em><b>Parent Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__PARENT_ID = PAGE_SUMMARY__PARENT_ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__VERSION = PAGE_SUMMARY__VERSION;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__AUTHOR = PAGE_SUMMARY__AUTHOR;

	/**
	 * The feature id for the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__AUTHOR_NAME = PAGE_SUMMARY__AUTHOR_NAME;

	/**
	 * The feature id for the '<em><b>Xwiki Relative Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__XWIKI_RELATIVE_URL = PAGE_SUMMARY__XWIKI_RELATIVE_URL;

	/**
	 * The feature id for the '<em><b>Xwiki Absolute Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__XWIKI_ABSOLUTE_URL = PAGE_SUMMARY__XWIKI_ABSOLUTE_URL;

	/**
	 * The feature id for the '<em><b>Translations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__TRANSLATIONS = PAGE_SUMMARY__TRANSLATIONS;

	/**
	 * The feature id for the '<em><b>Syntax</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__SYNTAX = PAGE_SUMMARY__SYNTAX;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__LANGUAGE = PAGE_SUMMARY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Major Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MAJOR_VERSION = PAGE_SUMMARY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Minor Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MINOR_VERSION = PAGE_SUMMARY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__CREATED = PAGE_SUMMARY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Creator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__CREATOR = PAGE_SUMMARY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Creator Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__CREATOR_NAME = PAGE_SUMMARY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MODIFIED = PAGE_SUMMARY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Modifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MODIFIER = PAGE_SUMMARY_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Modifier Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MODIFIER_NAME = PAGE_SUMMARY_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__COMMENT = PAGE_SUMMARY_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__CONTENT = PAGE_SUMMARY_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = PAGE_SUMMARY_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_OPERATION_COUNT = PAGE_SUMMARY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PagesTypeImpl <em>Pages Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PagesTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPagesType()
	 * @generated
	 */
	int PAGES_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGES_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Page Summary</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGES_TYPE__PAGE_SUMMARY = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Pages Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGES_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Pages Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGES_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertiesTypeImpl <em>Properties Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertiesTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPropertiesType()
	 * @generated
	 */
	int PROPERTIES_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_TYPE__PROPERTY = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Properties Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Properties Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertyImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 19;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__ATTRIBUTE = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__TYPE = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl <em>Search Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSearchResult()
	 * @generated
	 */
	int SEARCH_RESULT = 20;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__TYPE = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__ID = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Page Full Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__PAGE_FULL_NAME = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__TITLE = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__WIKI = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__SPACE = LINK_COLLECTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Page Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__PAGE_NAME = LINK_COLLECTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__MODIFIED = LINK_COLLECTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__AUTHOR = LINK_COLLECTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__AUTHOR_NAME = LINK_COLLECTION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__VERSION = LINK_COLLECTION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__LANGUAGE = LINK_COLLECTION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__CLASS_NAME = LINK_COLLECTION_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Object Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__OBJECT_NUMBER = LINK_COLLECTION_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__FILENAME = LINK_COLLECTION_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Score</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__SCORE = LINK_COLLECTION_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT__OBJECT = LINK_COLLECTION_FEATURE_COUNT + 16;

	/**
	 * The number of structural features of the '<em>Search Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 17;

	/**
	 * The number of operations of the '<em>Search Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULT_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultsTypeImpl <em>Search Results Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultsTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSearchResultsType()
	 * @generated
	 */
	int SEARCH_RESULTS_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULTS_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Search Result</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULTS_TYPE__SEARCH_RESULT = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULTS_TYPE__TEMPLATE = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Search Results Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULTS_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Search Results Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_RESULTS_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl <em>Space</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSpace()
	 * @generated
	 */
	int SPACE = 22;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__WIKI = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__NAME = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Home</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__HOME = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Xwiki Relative Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__XWIKI_RELATIVE_URL = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Xwiki Absolute Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE__XWIKI_ABSOLUTE_URL = LINK_COLLECTION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Space</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Space</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpacesTypeImpl <em>Spaces Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpacesTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSpacesType()
	 * @generated
	 */
	int SPACES_TYPE = 23;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACES_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Space</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACES_TYPE__SPACE = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Spaces Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACES_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Spaces Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPACES_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SyntaxesImpl <em>Syntaxes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SyntaxesImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSyntaxes()
	 * @generated
	 */
	int SYNTAXES = 24;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAXES__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Syntax</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAXES__SYNTAX = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Syntaxes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAXES_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Syntaxes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAXES_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 25;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__NAME = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagsTypeImpl <em>Tags Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagsTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTagsType()
	 * @generated
	 */
	int TAGS_TYPE = 26;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGS_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGS_TYPE__TAG = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tags Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGS_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tags Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGS_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationImpl <em>Translation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTranslation()
	 * @generated
	 */
	int TRANSLATION = 27;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATION__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATION__LANGUAGE = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Translation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATION_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Translation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATION_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationsImpl <em>Translations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationsImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTranslations()
	 * @generated
	 */
	int TRANSLATIONS = 28;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATIONS__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Translation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATIONS__TRANSLATION = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATIONS__DEFAULT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Translations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATIONS_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Translations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSLATIONS_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikiImpl <em>Wiki</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikiImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getWiki()
	 * @generated
	 */
	int WIKI = 29;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKI__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKI__ID = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKI__NAME = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKI__DESCRIPTION = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKI__OWNER = LINK_COLLECTION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Wiki</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKI_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Wiki</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKI_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikisTypeImpl <em>Wikis Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikisTypeImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getWikisType()
	 * @generated
	 */
	int WIKIS_TYPE = 30;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKIS_TYPE__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Wiki</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKIS_TYPE__WIKI = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Wikis Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKIS_TYPE_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Wikis Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIKIS_TYPE_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XWikiImpl <em>XWiki</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XWikiImpl
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getXWiki()
	 * @generated
	 */
	int XWIKI = 31;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XWIKI__LINK = LINK_COLLECTION__LINK;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XWIKI__VERSION = LINK_COLLECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Syntaxes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XWIKI__SYNTAXES = LINK_COLLECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>XWiki</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XWIKI_FEATURE_COUNT = LINK_COLLECTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>XWiki</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XWIKI_OPERATION_COUNT = LINK_COLLECTION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment <em>Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attachment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment
	 * @generated
	 */
	EClass getAttachment();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getId()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getName()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getSize()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_Size();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getVersion()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_Version();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getPageId <em>Page Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getPageId()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_PageId();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getPageVersion <em>Page Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getPageVersion()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_PageVersion();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getMimeType <em>Mime Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mime Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getMimeType()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_MimeType();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getAuthor()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_Author();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getAuthorName <em>Author Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getAuthorName()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_AuthorName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getDate()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_Date();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xwiki Relative Url</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getXwikiRelativeUrl()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_XwikiRelativeUrl();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xwiki Absolute Url</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment#getXwikiAbsoluteUrl()
	 * @see #getAttachment()
	 * @generated
	 */
	EAttribute getAttachment_XwikiAbsoluteUrl();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType <em>Attachments Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attachments Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType
	 * @generated
	 */
	EClass getAttachmentsType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType#getAttachment <em>Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attachment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType#getAttachment()
	 * @see #getAttachmentsType()
	 * @generated
	 */
	EReference getAttachmentsType_Attachment();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute#getName()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute#getValue()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Value();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Class <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Class
	 * @generated
	 */
	EClass getClass_();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Class#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Class#getId()
	 * @see #getClass_()
	 * @generated
	 */
	EAttribute getClass_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Class#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Class#getName()
	 * @see #getClass_()
	 * @generated
	 */
	EAttribute getClass_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Class#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Class#getProperty()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_Property();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType <em>Classes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Classes Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType
	 * @generated
	 */
	EClass getClassesType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType#getClass_()
	 * @see #getClassesType()
	 * @generated
	 */
	EReference getClassesType_Class();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getId()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getPageId <em>Page Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getPageId()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_PageId();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthor()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Author();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthorName <em>Author Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getAuthorName()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_AuthorName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getDate()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Date();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getHighlight <em>Highlight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Highlight</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getHighlight()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Highlight();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getText()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Text();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getReplyTo <em>Reply To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reply To</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment#getReplyTo()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_ReplyTo();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType <em>Comments Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comments Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType
	 * @generated
	 */
	EClass getCommentsType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType#getComment()
	 * @see #getCommentsType()
	 * @generated
	 */
	EReference getCommentsType_Comment();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getAttachment <em>Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attachment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getAttachment()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Attachment();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getAttachments <em>Attachments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attachments</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getAttachments()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Attachments();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Class</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getClass_()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Class();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Classes</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getClasses()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Classes();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getComment()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Comment();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comments</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getComments()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Comments();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getHistory <em>History</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>History</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getHistory()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_History();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Object</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObject()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Object();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objects</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObjects()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Objects();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObjectSummary <em>Object Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Object Summary</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObjectSummary()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ObjectSummary();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Page</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getPage()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Page();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pages</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getPages()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Pages();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getProperties()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Properties();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getProperty()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Property();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSearchResult <em>Search Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Search Result</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSearchResult()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SearchResult();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSearchResults <em>Search Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Search Results</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSearchResults()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SearchResults();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Space</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSpace()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Space();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSpaces <em>Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Spaces</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSpaces()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Spaces();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSyntaxes <em>Syntaxes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Syntaxes</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSyntaxes()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Syntaxes();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTag()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Tag();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTags()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Tags();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTranslations <em>Translations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Translations</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTranslations()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Translations();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getWiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getWiki()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Wiki();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getWikis <em>Wikis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Wikis</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getWikis()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Wikis();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXwiki <em>Xwiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Xwiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXwiki()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Xwiki();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary <em>History Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>History Summary</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary
	 * @generated
	 */
	EClass getHistorySummary();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getPageId <em>Page Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getPageId()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_PageId();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getWiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getWiki()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Wiki();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getSpace()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Space();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getName()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getVersion()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Version();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMajorVersion <em>Major Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Major Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMajorVersion()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_MajorVersion();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMinorVersion <em>Minor Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minor Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getMinorVersion()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_MinorVersion();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModified <em>Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModified()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Modified();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modifier</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifier()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Modifier();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifierName <em>Modifier Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modifier Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getModifierName()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_ModifierName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getLanguage()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Language();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary#getComment()
	 * @see #getHistorySummary()
	 * @generated
	 */
	EAttribute getHistorySummary_Comment();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType <em>History Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>History Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType
	 * @generated
	 */
	EClass getHistoryType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType#getHistorySummary <em>History Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>History Summary</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType#getHistorySummary()
	 * @see #getHistoryType()
	 * @generated
	 */
	EReference getHistoryType_HistorySummary();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHref <em>Href</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Href</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHref()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Href();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHrefLang <em>Href Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Href Lang</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getHrefLang()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_HrefLang();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getRel <em>Rel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rel</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getRel()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Rel();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Link#getType()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Type();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.LinkCollection <em>Link Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link Collection</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.LinkCollection
	 * @generated
	 */
	EClass getLinkCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.LinkCollection#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Link</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.LinkCollection#getLink()
	 * @see #getLinkCollection()
	 * @generated
	 */
	EReference getLinkCollection_Link();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Object
	 * @generated
	 */
	EClass getObject();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Object#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Object#getProperty()
	 * @see #getObject()
	 * @generated
	 */
	EReference getObject_Property();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType <em>Objects Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objects Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType
	 * @generated
	 */
	EClass getObjectsType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType#getObjectSummary <em>Object Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Object Summary</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType#getObjectSummary()
	 * @see #getObjectsType()
	 * @generated
	 */
	EReference getObjectsType_ObjectSummary();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary <em>Object Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Summary</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary
	 * @generated
	 */
	EClass getObjectSummary();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getId()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getGuid <em>Guid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Guid</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getGuid()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_Guid();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageId <em>Page Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageId()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_PageId();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageVersion <em>Page Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageVersion()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_PageVersion();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getWiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getWiki()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_Wiki();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getSpace()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_Space();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageName <em>Page Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageName()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_PageName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageAuthor <em>Page Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Author</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageAuthor()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_PageAuthor();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageAuthorName <em>Page Author Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Author Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getPageAuthorName()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_PageAuthorName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getClassName()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getNumber()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_Number();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getHeadline <em>Headline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Headline</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary#getHeadline()
	 * @see #getObjectSummary()
	 * @generated
	 */
	EAttribute getObjectSummary_Headline();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getLanguage()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Language();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMajorVersion <em>Major Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Major Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMajorVersion()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_MajorVersion();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMinorVersion <em>Minor Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minor Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getMinorVersion()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_MinorVersion();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreated()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Created();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreator <em>Creator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creator</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreator()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Creator();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreatorName <em>Creator Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creator Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getCreatorName()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_CreatorName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModified <em>Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModified()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Modified();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modifier</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifier()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Modifier();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifierName <em>Modifier Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modifier Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getModifierName()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_ModifierName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getComment()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Comment();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page#getContent()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Content();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType <em>Pages Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pages Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType
	 * @generated
	 */
	EClass getPagesType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType#getPageSummary <em>Page Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Page Summary</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType#getPageSummary()
	 * @see #getPagesType()
	 * @generated
	 */
	EReference getPagesType_PageSummary();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary <em>Page Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page Summary</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary
	 * @generated
	 */
	EClass getPageSummary();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getId()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getFullName <em>Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getFullName()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_FullName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getWiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getWiki()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Wiki();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getSpace()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Space();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getName()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getTitle()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Title();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parent</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getParent()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Parent();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getParentId <em>Parent Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parent Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getParentId()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_ParentId();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getVersion()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Version();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getAuthor()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Author();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getAuthorName <em>Author Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getAuthorName()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_AuthorName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xwiki Relative Url</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getXwikiRelativeUrl()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_XwikiRelativeUrl();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xwiki Absolute Url</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getXwikiAbsoluteUrl()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_XwikiAbsoluteUrl();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getTranslations <em>Translations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Translations</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getTranslations()
	 * @see #getPageSummary()
	 * @generated
	 */
	EReference getPageSummary_Translations();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getSyntax <em>Syntax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Syntax</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary#getSyntax()
	 * @see #getPageSummary()
	 * @generated
	 */
	EAttribute getPageSummary_Syntax();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType <em>Properties Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Properties Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType
	 * @generated
	 */
	EClass getPropertiesType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType#getProperty()
	 * @see #getPropertiesType()
	 * @generated
	 */
	EReference getPropertiesType_Property();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getAttribute()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Property#getType()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Type();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult <em>Search Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Search Result</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult
	 * @generated
	 */
	EClass getSearchResult();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getType()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Type();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getId()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getPageFullName <em>Page Full Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Full Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getPageFullName()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_PageFullName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getTitle()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Title();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getWiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getWiki()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Wiki();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Space</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getSpace()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Space();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getPageName <em>Page Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getPageName()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_PageName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getModified <em>Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getModified()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Modified();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getAuthor()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Author();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getAuthorName <em>Author Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getAuthorName()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_AuthorName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getVersion()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Version();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getLanguage()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Language();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getClassName()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getObjectNumber <em>Object Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Object Number</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getObjectNumber()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_ObjectNumber();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getFilename <em>Filename</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filename</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getFilename()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Filename();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getScore <em>Score</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Score</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getScore()
	 * @see #getSearchResult()
	 * @generated
	 */
	EAttribute getSearchResult_Score();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Object</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult#getObject()
	 * @see #getSearchResult()
	 * @generated
	 */
	EReference getSearchResult_Object();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType <em>Search Results Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Search Results Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType
	 * @generated
	 */
	EClass getSearchResultsType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType#getSearchResult <em>Search Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Search Result</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType#getSearchResult()
	 * @see #getSearchResultsType()
	 * @generated
	 */
	EReference getSearchResultsType_SearchResult();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType#getTemplate()
	 * @see #getSearchResultsType()
	 * @generated
	 */
	EAttribute getSearchResultsType_Template();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Space</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space
	 * @generated
	 */
	EClass getSpace();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getId()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getWiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getWiki()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_Wiki();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getName()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getHome <em>Home</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Home</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getHome()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_Home();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiRelativeUrl <em>Xwiki Relative Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xwiki Relative Url</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiRelativeUrl()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_XwikiRelativeUrl();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiAbsoluteUrl <em>Xwiki Absolute Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xwiki Absolute Url</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space#getXwikiAbsoluteUrl()
	 * @see #getSpace()
	 * @generated
	 */
	EAttribute getSpace_XwikiAbsoluteUrl();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType <em>Spaces Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Spaces Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType
	 * @generated
	 */
	EClass getSpacesType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType#getSpace <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Space</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType#getSpace()
	 * @see #getSpacesType()
	 * @generated
	 */
	EReference getSpacesType_Space();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes <em>Syntaxes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Syntaxes</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes
	 * @generated
	 */
	EClass getSyntaxes();

	/**
	 * Returns the meta object for the attribute list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes#getSyntax <em>Syntax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Syntax</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes#getSyntax()
	 * @see #getSyntaxes()
	 * @generated
	 */
	EAttribute getSyntaxes_Syntax();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Tag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Tag#getName()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Name();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType <em>Tags Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tags Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType
	 * @generated
	 */
	EClass getTagsType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType#getTag()
	 * @see #getTagsType()
	 * @generated
	 */
	EReference getTagsType_Tag();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translation <em>Translation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Translation</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Translation
	 * @generated
	 */
	EClass getTranslation();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translation#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Translation#getLanguage()
	 * @see #getTranslation()
	 * @generated
	 */
	EAttribute getTranslation_Language();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations <em>Translations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Translations</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Translations
	 * @generated
	 */
	EClass getTranslations();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations#getTranslation <em>Translation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Translation</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Translations#getTranslation()
	 * @see #getTranslations()
	 * @generated
	 */
	EReference getTranslations_Translation();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Translations#getDefault()
	 * @see #getTranslations()
	 * @generated
	 */
	EAttribute getTranslations_Default();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki
	 * @generated
	 */
	EClass getWiki();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getId()
	 * @see #getWiki()
	 * @generated
	 */
	EAttribute getWiki_Id();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getName()
	 * @see #getWiki()
	 * @generated
	 */
	EAttribute getWiki_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getDescription()
	 * @see #getWiki()
	 * @generated
	 */
	EAttribute getWiki_Description();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Owner</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki#getOwner()
	 * @see #getWiki()
	 * @generated
	 */
	EAttribute getWiki_Owner();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType <em>Wikis Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wikis Type</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType
	 * @generated
	 */
	EClass getWikisType();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType#getWiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Wiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType#getWiki()
	 * @see #getWikisType()
	 * @generated
	 */
	EReference getWikisType_Wiki();

	/**
	 * Returns the meta object for class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki <em>XWiki</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XWiki</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki
	 * @generated
	 */
	EClass getXWiki();

	/**
	 * Returns the meta object for the attribute '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getVersion()
	 * @see #getXWiki()
	 * @generated
	 */
	EAttribute getXWiki_Version();

	/**
	 * Returns the meta object for the containment reference '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getSyntaxes <em>Syntaxes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Syntaxes</em>'.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki#getSyntaxes()
	 * @see #getXWiki()
	 * @generated
	 */
	EReference getXWiki_Syntaxes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	XwikiFactory getXwikiFactory();

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
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl <em>Attachment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getAttachment()
		 * @generated
		 */
		EClass ATTACHMENT = eINSTANCE.getAttachment();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__ID = eINSTANCE.getAttachment_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__NAME = eINSTANCE.getAttachment_Name();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__SIZE = eINSTANCE.getAttachment_Size();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__VERSION = eINSTANCE.getAttachment_Version();

		/**
		 * The meta object literal for the '<em><b>Page Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__PAGE_ID = eINSTANCE.getAttachment_PageId();

		/**
		 * The meta object literal for the '<em><b>Page Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__PAGE_VERSION = eINSTANCE.getAttachment_PageVersion();

		/**
		 * The meta object literal for the '<em><b>Mime Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__MIME_TYPE = eINSTANCE.getAttachment_MimeType();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__AUTHOR = eINSTANCE.getAttachment_Author();

		/**
		 * The meta object literal for the '<em><b>Author Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__AUTHOR_NAME = eINSTANCE.getAttachment_AuthorName();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__DATE = eINSTANCE.getAttachment_Date();

		/**
		 * The meta object literal for the '<em><b>Xwiki Relative Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__XWIKI_RELATIVE_URL = eINSTANCE.getAttachment_XwikiRelativeUrl();

		/**
		 * The meta object literal for the '<em><b>Xwiki Absolute Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTACHMENT__XWIKI_ABSOLUTE_URL = eINSTANCE.getAttachment_XwikiAbsoluteUrl();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentsTypeImpl <em>Attachments Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttachmentsTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getAttachmentsType()
		 * @generated
		 */
		EClass ATTACHMENTS_TYPE = eINSTANCE.getAttachmentsType();

		/**
		 * The meta object literal for the '<em><b>Attachment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTACHMENTS_TYPE__ATTACHMENT = eINSTANCE.getAttachmentsType_Attachment();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.AttributeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__VALUE = eINSTANCE.getAttribute_Value();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassImpl <em>Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getClass_()
		 * @generated
		 */
		EClass CLASS = eINSTANCE.getClass_();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS__ID = eINSTANCE.getClass_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS__NAME = eINSTANCE.getClass_Name();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__PROPERTY = eINSTANCE.getClass_Property();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassesTypeImpl <em>Classes Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ClassesTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getClassesType()
		 * @generated
		 */
		EClass CLASSES_TYPE = eINSTANCE.getClassesType();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSES_TYPE__CLASS = eINSTANCE.getClassesType_Class();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__ID = eINSTANCE.getComment_Id();

		/**
		 * The meta object literal for the '<em><b>Page Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__PAGE_ID = eINSTANCE.getComment_PageId();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__AUTHOR = eINSTANCE.getComment_Author();

		/**
		 * The meta object literal for the '<em><b>Author Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__AUTHOR_NAME = eINSTANCE.getComment_AuthorName();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__DATE = eINSTANCE.getComment_Date();

		/**
		 * The meta object literal for the '<em><b>Highlight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__HIGHLIGHT = eINSTANCE.getComment_Highlight();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__TEXT = eINSTANCE.getComment_Text();

		/**
		 * The meta object literal for the '<em><b>Reply To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__REPLY_TO = eINSTANCE.getComment_ReplyTo();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentsTypeImpl <em>Comments Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.CommentsTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getCommentsType()
		 * @generated
		 */
		EClass COMMENTS_TYPE = eINSTANCE.getCommentsType();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENTS_TYPE__COMMENT = eINSTANCE.getCommentsType_Comment();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getDocumentRoot()
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
		 * The meta object literal for the '<em><b>Attachment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTACHMENT = eINSTANCE.getDocumentRoot_Attachment();

		/**
		 * The meta object literal for the '<em><b>Attachments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ATTACHMENTS = eINSTANCE.getDocumentRoot_Attachments();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CLASS = eINSTANCE.getDocumentRoot_Class();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CLASSES = eINSTANCE.getDocumentRoot_Classes();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMMENT = eINSTANCE.getDocumentRoot_Comment();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMMENTS = eINSTANCE.getDocumentRoot_Comments();

		/**
		 * The meta object literal for the '<em><b>History</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__HISTORY = eINSTANCE.getDocumentRoot_History();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OBJECT = eINSTANCE.getDocumentRoot_Object();

		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OBJECTS = eINSTANCE.getDocumentRoot_Objects();

		/**
		 * The meta object literal for the '<em><b>Object Summary</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OBJECT_SUMMARY = eINSTANCE.getDocumentRoot_ObjectSummary();

		/**
		 * The meta object literal for the '<em><b>Page</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PAGE = eINSTANCE.getDocumentRoot_Page();

		/**
		 * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PAGES = eINSTANCE.getDocumentRoot_Pages();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROPERTIES = eINSTANCE.getDocumentRoot_Properties();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROPERTY = eINSTANCE.getDocumentRoot_Property();

		/**
		 * The meta object literal for the '<em><b>Search Result</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SEARCH_RESULT = eINSTANCE.getDocumentRoot_SearchResult();

		/**
		 * The meta object literal for the '<em><b>Search Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SEARCH_RESULTS = eINSTANCE.getDocumentRoot_SearchResults();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPACE = eINSTANCE.getDocumentRoot_Space();

		/**
		 * The meta object literal for the '<em><b>Spaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPACES = eINSTANCE.getDocumentRoot_Spaces();

		/**
		 * The meta object literal for the '<em><b>Syntaxes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SYNTAXES = eINSTANCE.getDocumentRoot_Syntaxes();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TAG = eINSTANCE.getDocumentRoot_Tag();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TAGS = eINSTANCE.getDocumentRoot_Tags();

		/**
		 * The meta object literal for the '<em><b>Translations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRANSLATIONS = eINSTANCE.getDocumentRoot_Translations();

		/**
		 * The meta object literal for the '<em><b>Wiki</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WIKI = eINSTANCE.getDocumentRoot_Wiki();

		/**
		 * The meta object literal for the '<em><b>Wikis</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WIKIS = eINSTANCE.getDocumentRoot_Wikis();

		/**
		 * The meta object literal for the '<em><b>Xwiki</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XWIKI = eINSTANCE.getDocumentRoot_Xwiki();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl <em>History Summary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistorySummaryImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getHistorySummary()
		 * @generated
		 */
		EClass HISTORY_SUMMARY = eINSTANCE.getHistorySummary();

		/**
		 * The meta object literal for the '<em><b>Page Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__PAGE_ID = eINSTANCE.getHistorySummary_PageId();

		/**
		 * The meta object literal for the '<em><b>Wiki</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__WIKI = eINSTANCE.getHistorySummary_Wiki();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__SPACE = eINSTANCE.getHistorySummary_Space();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__NAME = eINSTANCE.getHistorySummary_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__VERSION = eINSTANCE.getHistorySummary_Version();

		/**
		 * The meta object literal for the '<em><b>Major Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__MAJOR_VERSION = eINSTANCE.getHistorySummary_MajorVersion();

		/**
		 * The meta object literal for the '<em><b>Minor Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__MINOR_VERSION = eINSTANCE.getHistorySummary_MinorVersion();

		/**
		 * The meta object literal for the '<em><b>Modified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__MODIFIED = eINSTANCE.getHistorySummary_Modified();

		/**
		 * The meta object literal for the '<em><b>Modifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__MODIFIER = eINSTANCE.getHistorySummary_Modifier();

		/**
		 * The meta object literal for the '<em><b>Modifier Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__MODIFIER_NAME = eINSTANCE.getHistorySummary_ModifierName();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__LANGUAGE = eINSTANCE.getHistorySummary_Language();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HISTORY_SUMMARY__COMMENT = eINSTANCE.getHistorySummary_Comment();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistoryTypeImpl <em>History Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.HistoryTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getHistoryType()
		 * @generated
		 */
		EClass HISTORY_TYPE = eINSTANCE.getHistoryType();

		/**
		 * The meta object literal for the '<em><b>History Summary</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HISTORY_TYPE__HISTORY_SUMMARY = eINSTANCE.getHistoryType_HistorySummary();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Href</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__HREF = eINSTANCE.getLink_Href();

		/**
		 * The meta object literal for the '<em><b>Href Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__HREF_LANG = eINSTANCE.getLink_HrefLang();

		/**
		 * The meta object literal for the '<em><b>Rel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__REL = eINSTANCE.getLink_Rel();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__TYPE = eINSTANCE.getLink_Type();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkCollectionImpl <em>Link Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.LinkCollectionImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getLinkCollection()
		 * @generated
		 */
		EClass LINK_COLLECTION = eINSTANCE.getLinkCollection();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_COLLECTION__LINK = eINSTANCE.getLinkCollection_Link();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectImpl <em>Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getObject()
		 * @generated
		 */
		EClass OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT__PROPERTY = eINSTANCE.getObject_Property();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectsTypeImpl <em>Objects Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectsTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getObjectsType()
		 * @generated
		 */
		EClass OBJECTS_TYPE = eINSTANCE.getObjectsType();

		/**
		 * The meta object literal for the '<em><b>Object Summary</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECTS_TYPE__OBJECT_SUMMARY = eINSTANCE.getObjectsType_ObjectSummary();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl <em>Object Summary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.ObjectSummaryImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getObjectSummary()
		 * @generated
		 */
		EClass OBJECT_SUMMARY = eINSTANCE.getObjectSummary();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__ID = eINSTANCE.getObjectSummary_Id();

		/**
		 * The meta object literal for the '<em><b>Guid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__GUID = eINSTANCE.getObjectSummary_Guid();

		/**
		 * The meta object literal for the '<em><b>Page Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__PAGE_ID = eINSTANCE.getObjectSummary_PageId();

		/**
		 * The meta object literal for the '<em><b>Page Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__PAGE_VERSION = eINSTANCE.getObjectSummary_PageVersion();

		/**
		 * The meta object literal for the '<em><b>Wiki</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__WIKI = eINSTANCE.getObjectSummary_Wiki();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__SPACE = eINSTANCE.getObjectSummary_Space();

		/**
		 * The meta object literal for the '<em><b>Page Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__PAGE_NAME = eINSTANCE.getObjectSummary_PageName();

		/**
		 * The meta object literal for the '<em><b>Page Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__PAGE_AUTHOR = eINSTANCE.getObjectSummary_PageAuthor();

		/**
		 * The meta object literal for the '<em><b>Page Author Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__PAGE_AUTHOR_NAME = eINSTANCE.getObjectSummary_PageAuthorName();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__CLASS_NAME = eINSTANCE.getObjectSummary_ClassName();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__NUMBER = eINSTANCE.getObjectSummary_Number();

		/**
		 * The meta object literal for the '<em><b>Headline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OBJECT_SUMMARY__HEADLINE = eINSTANCE.getObjectSummary_Headline();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__LANGUAGE = eINSTANCE.getPage_Language();

		/**
		 * The meta object literal for the '<em><b>Major Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__MAJOR_VERSION = eINSTANCE.getPage_MajorVersion();

		/**
		 * The meta object literal for the '<em><b>Minor Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__MINOR_VERSION = eINSTANCE.getPage_MinorVersion();

		/**
		 * The meta object literal for the '<em><b>Created</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__CREATED = eINSTANCE.getPage_Created();

		/**
		 * The meta object literal for the '<em><b>Creator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__CREATOR = eINSTANCE.getPage_Creator();

		/**
		 * The meta object literal for the '<em><b>Creator Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__CREATOR_NAME = eINSTANCE.getPage_CreatorName();

		/**
		 * The meta object literal for the '<em><b>Modified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__MODIFIED = eINSTANCE.getPage_Modified();

		/**
		 * The meta object literal for the '<em><b>Modifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__MODIFIER = eINSTANCE.getPage_Modifier();

		/**
		 * The meta object literal for the '<em><b>Modifier Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__MODIFIER_NAME = eINSTANCE.getPage_ModifierName();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__COMMENT = eINSTANCE.getPage_Comment();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__CONTENT = eINSTANCE.getPage_Content();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PagesTypeImpl <em>Pages Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PagesTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPagesType()
		 * @generated
		 */
		EClass PAGES_TYPE = eINSTANCE.getPagesType();

		/**
		 * The meta object literal for the '<em><b>Page Summary</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGES_TYPE__PAGE_SUMMARY = eINSTANCE.getPagesType_PageSummary();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl <em>Page Summary</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PageSummaryImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPageSummary()
		 * @generated
		 */
		EClass PAGE_SUMMARY = eINSTANCE.getPageSummary();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__ID = eINSTANCE.getPageSummary_Id();

		/**
		 * The meta object literal for the '<em><b>Full Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__FULL_NAME = eINSTANCE.getPageSummary_FullName();

		/**
		 * The meta object literal for the '<em><b>Wiki</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__WIKI = eINSTANCE.getPageSummary_Wiki();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__SPACE = eINSTANCE.getPageSummary_Space();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__NAME = eINSTANCE.getPageSummary_Name();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__TITLE = eINSTANCE.getPageSummary_Title();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__PARENT = eINSTANCE.getPageSummary_Parent();

		/**
		 * The meta object literal for the '<em><b>Parent Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__PARENT_ID = eINSTANCE.getPageSummary_ParentId();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__VERSION = eINSTANCE.getPageSummary_Version();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__AUTHOR = eINSTANCE.getPageSummary_Author();

		/**
		 * The meta object literal for the '<em><b>Author Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__AUTHOR_NAME = eINSTANCE.getPageSummary_AuthorName();

		/**
		 * The meta object literal for the '<em><b>Xwiki Relative Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__XWIKI_RELATIVE_URL = eINSTANCE.getPageSummary_XwikiRelativeUrl();

		/**
		 * The meta object literal for the '<em><b>Xwiki Absolute Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__XWIKI_ABSOLUTE_URL = eINSTANCE.getPageSummary_XwikiAbsoluteUrl();

		/**
		 * The meta object literal for the '<em><b>Translations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE_SUMMARY__TRANSLATIONS = eINSTANCE.getPageSummary_Translations();

		/**
		 * The meta object literal for the '<em><b>Syntax</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE_SUMMARY__SYNTAX = eINSTANCE.getPageSummary_Syntax();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertiesTypeImpl <em>Properties Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertiesTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getPropertiesType()
		 * @generated
		 */
		EClass PROPERTIES_TYPE = eINSTANCE.getPropertiesType();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTIES_TYPE__PROPERTY = eINSTANCE.getPropertiesType_Property();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.PropertyImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__ATTRIBUTE = eINSTANCE.getProperty_Attribute();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__TYPE = eINSTANCE.getProperty_Type();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl <em>Search Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSearchResult()
		 * @generated
		 */
		EClass SEARCH_RESULT = eINSTANCE.getSearchResult();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__TYPE = eINSTANCE.getSearchResult_Type();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__ID = eINSTANCE.getSearchResult_Id();

		/**
		 * The meta object literal for the '<em><b>Page Full Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__PAGE_FULL_NAME = eINSTANCE.getSearchResult_PageFullName();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__TITLE = eINSTANCE.getSearchResult_Title();

		/**
		 * The meta object literal for the '<em><b>Wiki</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__WIKI = eINSTANCE.getSearchResult_Wiki();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__SPACE = eINSTANCE.getSearchResult_Space();

		/**
		 * The meta object literal for the '<em><b>Page Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__PAGE_NAME = eINSTANCE.getSearchResult_PageName();

		/**
		 * The meta object literal for the '<em><b>Modified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__MODIFIED = eINSTANCE.getSearchResult_Modified();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__AUTHOR = eINSTANCE.getSearchResult_Author();

		/**
		 * The meta object literal for the '<em><b>Author Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__AUTHOR_NAME = eINSTANCE.getSearchResult_AuthorName();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__VERSION = eINSTANCE.getSearchResult_Version();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__LANGUAGE = eINSTANCE.getSearchResult_Language();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__CLASS_NAME = eINSTANCE.getSearchResult_ClassName();

		/**
		 * The meta object literal for the '<em><b>Object Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__OBJECT_NUMBER = eINSTANCE.getSearchResult_ObjectNumber();

		/**
		 * The meta object literal for the '<em><b>Filename</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__FILENAME = eINSTANCE.getSearchResult_Filename();

		/**
		 * The meta object literal for the '<em><b>Score</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULT__SCORE = eINSTANCE.getSearchResult_Score();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEARCH_RESULT__OBJECT = eINSTANCE.getSearchResult_Object();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultsTypeImpl <em>Search Results Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SearchResultsTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSearchResultsType()
		 * @generated
		 */
		EClass SEARCH_RESULTS_TYPE = eINSTANCE.getSearchResultsType();

		/**
		 * The meta object literal for the '<em><b>Search Result</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEARCH_RESULTS_TYPE__SEARCH_RESULT = eINSTANCE.getSearchResultsType_SearchResult();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_RESULTS_TYPE__TEMPLATE = eINSTANCE.getSearchResultsType_Template();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl <em>Space</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpaceImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSpace()
		 * @generated
		 */
		EClass SPACE = eINSTANCE.getSpace();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__ID = eINSTANCE.getSpace_Id();

		/**
		 * The meta object literal for the '<em><b>Wiki</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__WIKI = eINSTANCE.getSpace_Wiki();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__NAME = eINSTANCE.getSpace_Name();

		/**
		 * The meta object literal for the '<em><b>Home</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__HOME = eINSTANCE.getSpace_Home();

		/**
		 * The meta object literal for the '<em><b>Xwiki Relative Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__XWIKI_RELATIVE_URL = eINSTANCE.getSpace_XwikiRelativeUrl();

		/**
		 * The meta object literal for the '<em><b>Xwiki Absolute Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPACE__XWIKI_ABSOLUTE_URL = eINSTANCE.getSpace_XwikiAbsoluteUrl();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpacesTypeImpl <em>Spaces Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SpacesTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSpacesType()
		 * @generated
		 */
		EClass SPACES_TYPE = eINSTANCE.getSpacesType();

		/**
		 * The meta object literal for the '<em><b>Space</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPACES_TYPE__SPACE = eINSTANCE.getSpacesType_Space();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SyntaxesImpl <em>Syntaxes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.SyntaxesImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getSyntaxes()
		 * @generated
		 */
		EClass SYNTAXES = eINSTANCE.getSyntaxes();

		/**
		 * The meta object literal for the '<em><b>Syntax</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYNTAXES__SYNTAX = eINSTANCE.getSyntaxes_Syntax();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__NAME = eINSTANCE.getTag_Name();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagsTypeImpl <em>Tags Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TagsTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTagsType()
		 * @generated
		 */
		EClass TAGS_TYPE = eINSTANCE.getTagsType();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAGS_TYPE__TAG = eINSTANCE.getTagsType_Tag();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationImpl <em>Translation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTranslation()
		 * @generated
		 */
		EClass TRANSLATION = eINSTANCE.getTranslation();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSLATION__LANGUAGE = eINSTANCE.getTranslation_Language();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationsImpl <em>Translations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.TranslationsImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getTranslations()
		 * @generated
		 */
		EClass TRANSLATIONS = eINSTANCE.getTranslations();

		/**
		 * The meta object literal for the '<em><b>Translation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSLATIONS__TRANSLATION = eINSTANCE.getTranslations_Translation();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSLATIONS__DEFAULT = eINSTANCE.getTranslations_Default();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikiImpl <em>Wiki</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikiImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getWiki()
		 * @generated
		 */
		EClass WIKI = eINSTANCE.getWiki();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIKI__ID = eINSTANCE.getWiki_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIKI__NAME = eINSTANCE.getWiki_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIKI__DESCRIPTION = eINSTANCE.getWiki_Description();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIKI__OWNER = eINSTANCE.getWiki_Owner();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikisTypeImpl <em>Wikis Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.WikisTypeImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getWikisType()
		 * @generated
		 */
		EClass WIKIS_TYPE = eINSTANCE.getWikisType();

		/**
		 * The meta object literal for the '<em><b>Wiki</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WIKIS_TYPE__WIKI = eINSTANCE.getWikisType_Wiki();

		/**
		 * The meta object literal for the '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XWikiImpl <em>XWiki</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XWikiImpl
		 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.impl.XwikiPackageImpl#getXWiki()
		 * @generated
		 */
		EClass XWIKI = eINSTANCE.getXWiki();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute XWIKI__VERSION = eINSTANCE.getXWiki_Version();

		/**
		 * The meta object literal for the '<em><b>Syntaxes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XWIKI__SYNTAXES = eINSTANCE.getXWiki_Syntaxes();

	}

} //XwikiPackage
