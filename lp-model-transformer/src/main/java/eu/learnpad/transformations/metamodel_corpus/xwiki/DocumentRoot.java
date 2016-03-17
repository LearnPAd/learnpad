/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki;

import org.eclipse.emf.common.util.EList;
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
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getAttachment <em>Attachment</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getAttachments <em>Attachments</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getClass_ <em>Class</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getClasses <em>Classes</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getComment <em>Comment</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getComments <em>Comments</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getHistory <em>History</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObject <em>Object</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObjects <em>Objects</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getObjectSummary <em>Object Summary</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getPage <em>Page</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getPages <em>Pages</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getProperties <em>Properties</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getProperty <em>Property</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSearchResult <em>Search Result</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSearchResults <em>Search Results</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSpace <em>Space</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getSyntaxes <em>Syntaxes</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTag <em>Tag</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTags <em>Tags</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getTranslations <em>Translations</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getWikis <em>Wikis</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot#getXwiki <em>Xwiki</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot()
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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Mixed()
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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_XMLNSPrefixMap()
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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Attachment</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attachment</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attachment</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Attachment()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attachment' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Attachment> getAttachment();

	/**
	 * Returns the value of the '<em><b>Attachments</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attachments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attachments</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Attachments()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attachments' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<AttachmentsType> getAttachments();

	/**
	 * Returns the value of the '<em><b>Class</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Class()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='class' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<eu.learnpad.transformations.metamodel_corpus.xwiki.Class> getClass_();

	/**
	 * Returns the value of the '<em><b>Classes</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Classes</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Classes()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='classes' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ClassesType> getClasses();

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Comment()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='comment' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Comment> getComment();

	/**
	 * Returns the value of the '<em><b>Comments</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Comments()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='comments' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<CommentsType> getComments();

	/**
	 * Returns the value of the '<em><b>History</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>History</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>History</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_History()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='history' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<HistoryType> getHistory();

	/**
	 * Returns the value of the '<em><b>Object</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Object}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Object()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='object' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<eu.learnpad.transformations.metamodel_corpus.xwiki.Object> getObject();

	/**
	 * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Objects()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='objects' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ObjectsType> getObjects();

	/**
	 * Returns the value of the '<em><b>Object Summary</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object Summary</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object Summary</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_ObjectSummary()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='objectSummary' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ObjectSummary> getObjectSummary();

	/**
	 * Returns the value of the '<em><b>Page</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Page()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='page' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Page> getPage();

	/**
	 * Returns the value of the '<em><b>Pages</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pages</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Pages()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='pages' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PagesType> getPages();

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Properties()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='properties' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<PropertiesType> getProperties();

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Property()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Property> getProperty();

	/**
	 * Returns the value of the '<em><b>Search Result</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Result</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Search Result</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_SearchResult()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='searchResult' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<SearchResult> getSearchResult();

	/**
	 * Returns the value of the '<em><b>Search Results</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Search Results</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_SearchResults()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='searchResults' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<SearchResultsType> getSearchResults();

	/**
	 * Returns the value of the '<em><b>Space</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Space</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Space</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Space()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='space' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Space> getSpace();

	/**
	 * Returns the value of the '<em><b>Spaces</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spaces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spaces</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Spaces()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='spaces' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<SpacesType> getSpaces();

	/**
	 * Returns the value of the '<em><b>Syntaxes</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Syntaxes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Syntaxes</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Syntaxes()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='syntaxes' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Syntaxes> getSyntaxes();

	/**
	 * Returns the value of the '<em><b>Tag</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Tag}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Tag()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='tag' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Tag> getTag();

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Tags()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='tags' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TagsType> getTags();

	/**
	 * Returns the value of the '<em><b>Translations</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Translations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Translations</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Translations()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='translations' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Translations> getTranslations();

	/**
	 * Returns the value of the '<em><b>Wiki</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wiki</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wiki</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Wiki()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='wiki' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Wiki> getWiki();

	/**
	 * Returns the value of the '<em><b>Wikis</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wikis</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wikis</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Wikis()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='wikis' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<WikisType> getWikis();

	/**
	 * Returns the value of the '<em><b>Xwiki</b></em>' containment reference list.
	 * The list contents are of type {@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xwiki</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xwiki</em>' containment reference list.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#getDocumentRoot_Xwiki()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='xwiki' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<XWiki> getXwiki();

} // DocumentRoot
