/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment;
import eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute;
import eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Comment;
import eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot;
import eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Link;
import eu.learnpad.transformations.metamodel_corpus.xwiki.LinkCollection;
import eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Page;
import eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Property;
import eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult;
import eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Space;
import eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Tag;
import eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Translation;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Translations;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki;
import eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

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
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage
 * @generated
 */
public class XwikiSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static XwikiPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XwikiSwitch() {
		if (modelPackage == null) {
			modelPackage = XwikiPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
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
			case XwikiPackage.ATTACHMENT: {
				Attachment attachment = (Attachment)theEObject;
				T result = caseAttachment(attachment);
				if (result == null) result = caseLinkCollection(attachment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.ATTACHMENTS_TYPE: {
				AttachmentsType attachmentsType = (AttachmentsType)theEObject;
				T result = caseAttachmentsType(attachmentsType);
				if (result == null) result = caseLinkCollection(attachmentsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.ATTRIBUTE: {
				Attribute attribute = (Attribute)theEObject;
				T result = caseAttribute(attribute);
				if (result == null) result = caseLinkCollection(attribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.CLASS: {
				eu.learnpad.transformations.metamodel_corpus.xwiki.Class class_ = (eu.learnpad.transformations.metamodel_corpus.xwiki.Class)theEObject;
				T result = caseClass(class_);
				if (result == null) result = caseLinkCollection(class_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.CLASSES_TYPE: {
				ClassesType classesType = (ClassesType)theEObject;
				T result = caseClassesType(classesType);
				if (result == null) result = caseLinkCollection(classesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.COMMENT: {
				Comment comment = (Comment)theEObject;
				T result = caseComment(comment);
				if (result == null) result = caseLinkCollection(comment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.COMMENTS_TYPE: {
				CommentsType commentsType = (CommentsType)theEObject;
				T result = caseCommentsType(commentsType);
				if (result == null) result = caseLinkCollection(commentsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.HISTORY_SUMMARY: {
				HistorySummary historySummary = (HistorySummary)theEObject;
				T result = caseHistorySummary(historySummary);
				if (result == null) result = caseLinkCollection(historySummary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.HISTORY_TYPE: {
				HistoryType historyType = (HistoryType)theEObject;
				T result = caseHistoryType(historyType);
				if (result == null) result = caseLinkCollection(historyType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.LINK: {
				Link link = (Link)theEObject;
				T result = caseLink(link);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.LINK_COLLECTION: {
				LinkCollection linkCollection = (LinkCollection)theEObject;
				T result = caseLinkCollection(linkCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.OBJECT: {
				eu.learnpad.transformations.metamodel_corpus.xwiki.Object object = (eu.learnpad.transformations.metamodel_corpus.xwiki.Object)theEObject;
				T result = caseObject(object);
				if (result == null) result = caseObjectSummary(object);
				if (result == null) result = caseLinkCollection(object);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.OBJECTS_TYPE: {
				ObjectsType objectsType = (ObjectsType)theEObject;
				T result = caseObjectsType(objectsType);
				if (result == null) result = caseLinkCollection(objectsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.OBJECT_SUMMARY: {
				ObjectSummary objectSummary = (ObjectSummary)theEObject;
				T result = caseObjectSummary(objectSummary);
				if (result == null) result = caseLinkCollection(objectSummary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.PAGE: {
				Page page = (Page)theEObject;
				T result = casePage(page);
				if (result == null) result = casePageSummary(page);
				if (result == null) result = caseLinkCollection(page);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.PAGES_TYPE: {
				PagesType pagesType = (PagesType)theEObject;
				T result = casePagesType(pagesType);
				if (result == null) result = caseLinkCollection(pagesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.PAGE_SUMMARY: {
				PageSummary pageSummary = (PageSummary)theEObject;
				T result = casePageSummary(pageSummary);
				if (result == null) result = caseLinkCollection(pageSummary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.PROPERTIES_TYPE: {
				PropertiesType propertiesType = (PropertiesType)theEObject;
				T result = casePropertiesType(propertiesType);
				if (result == null) result = caseLinkCollection(propertiesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.PROPERTY: {
				Property property = (Property)theEObject;
				T result = caseProperty(property);
				if (result == null) result = caseLinkCollection(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.SEARCH_RESULT: {
				SearchResult searchResult = (SearchResult)theEObject;
				T result = caseSearchResult(searchResult);
				if (result == null) result = caseLinkCollection(searchResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.SEARCH_RESULTS_TYPE: {
				SearchResultsType searchResultsType = (SearchResultsType)theEObject;
				T result = caseSearchResultsType(searchResultsType);
				if (result == null) result = caseLinkCollection(searchResultsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.SPACE: {
				Space space = (Space)theEObject;
				T result = caseSpace(space);
				if (result == null) result = caseLinkCollection(space);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.SPACES_TYPE: {
				SpacesType spacesType = (SpacesType)theEObject;
				T result = caseSpacesType(spacesType);
				if (result == null) result = caseLinkCollection(spacesType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.SYNTAXES: {
				Syntaxes syntaxes = (Syntaxes)theEObject;
				T result = caseSyntaxes(syntaxes);
				if (result == null) result = caseLinkCollection(syntaxes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.TAG: {
				Tag tag = (Tag)theEObject;
				T result = caseTag(tag);
				if (result == null) result = caseLinkCollection(tag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.TAGS_TYPE: {
				TagsType tagsType = (TagsType)theEObject;
				T result = caseTagsType(tagsType);
				if (result == null) result = caseLinkCollection(tagsType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.TRANSLATION: {
				Translation translation = (Translation)theEObject;
				T result = caseTranslation(translation);
				if (result == null) result = caseLinkCollection(translation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.TRANSLATIONS: {
				Translations translations = (Translations)theEObject;
				T result = caseTranslations(translations);
				if (result == null) result = caseLinkCollection(translations);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.WIKI: {
				Wiki wiki = (Wiki)theEObject;
				T result = caseWiki(wiki);
				if (result == null) result = caseLinkCollection(wiki);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.WIKIS_TYPE: {
				WikisType wikisType = (WikisType)theEObject;
				T result = caseWikisType(wikisType);
				if (result == null) result = caseLinkCollection(wikisType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case XwikiPackage.XWIKI: {
				XWiki xWiki = (XWiki)theEObject;
				T result = caseXWiki(xWiki);
				if (result == null) result = caseLinkCollection(xWiki);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attachment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attachment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttachment(Attachment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attachments Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attachments Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttachmentsType(AttachmentsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(Attribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClass(eu.learnpad.transformations.metamodel_corpus.xwiki.Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Classes Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Classes Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassesType(ClassesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comments Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comments Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommentsType(CommentsType object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>History Summary</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>History Summary</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHistorySummary(HistorySummary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>History Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>History Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHistoryType(HistoryType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLink(Link object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkCollection(LinkCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObject(eu.learnpad.transformations.metamodel_corpus.xwiki.Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Objects Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Objects Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectsType(ObjectsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object Summary</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Summary</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectSummary(ObjectSummary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePage(Page object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pages Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pages Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePagesType(PagesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page Summary</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page Summary</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePageSummary(PageSummary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePropertiesType(PropertiesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperty(Property object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Search Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Search Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSearchResult(SearchResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Search Results Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Search Results Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSearchResultsType(SearchResultsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Space</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Space</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpace(Space object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Spaces Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Spaces Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpacesType(SpacesType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Syntaxes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Syntaxes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSyntaxes(Syntaxes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTag(Tag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tags Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tags Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTagsType(TagsType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Translation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Translation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTranslation(Translation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Translations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Translations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTranslations(Translations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wiki</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wiki</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWiki(Wiki object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wikis Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wikis Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWikisType(WikisType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XWiki</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XWiki</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXWiki(XWiki object) {
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

} //XwikiSwitch
