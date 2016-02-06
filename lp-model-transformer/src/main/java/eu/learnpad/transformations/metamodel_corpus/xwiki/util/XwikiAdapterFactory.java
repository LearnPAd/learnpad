/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage
 * @generated
 */
public class XwikiAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static XwikiPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XwikiAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = XwikiPackage.eINSTANCE;
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
	protected XwikiSwitch<Adapter> modelSwitch =
		new XwikiSwitch<Adapter>() {
			@Override
			public Adapter caseAttachment(Attachment object) {
				return createAttachmentAdapter();
			}
			@Override
			public Adapter caseAttachmentsType(AttachmentsType object) {
				return createAttachmentsTypeAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseClass(eu.learnpad.transformations.metamodel_corpus.xwiki.Class object) {
				return createClassAdapter();
			}
			@Override
			public Adapter caseClassesType(ClassesType object) {
				return createClassesTypeAdapter();
			}
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter caseCommentsType(CommentsType object) {
				return createCommentsTypeAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseHistorySummary(HistorySummary object) {
				return createHistorySummaryAdapter();
			}
			@Override
			public Adapter caseHistoryType(HistoryType object) {
				return createHistoryTypeAdapter();
			}
			@Override
			public Adapter caseLink(Link object) {
				return createLinkAdapter();
			}
			@Override
			public Adapter caseLinkCollection(LinkCollection object) {
				return createLinkCollectionAdapter();
			}
			@Override
			public Adapter caseObject(eu.learnpad.transformations.metamodel_corpus.xwiki.Object object) {
				return createObjectAdapter();
			}
			@Override
			public Adapter caseObjectsType(ObjectsType object) {
				return createObjectsTypeAdapter();
			}
			@Override
			public Adapter caseObjectSummary(ObjectSummary object) {
				return createObjectSummaryAdapter();
			}
			@Override
			public Adapter casePage(Page object) {
				return createPageAdapter();
			}
			@Override
			public Adapter casePagesType(PagesType object) {
				return createPagesTypeAdapter();
			}
			@Override
			public Adapter casePageSummary(PageSummary object) {
				return createPageSummaryAdapter();
			}
			@Override
			public Adapter casePropertiesType(PropertiesType object) {
				return createPropertiesTypeAdapter();
			}
			@Override
			public Adapter caseProperty(Property object) {
				return createPropertyAdapter();
			}
			@Override
			public Adapter caseSearchResult(SearchResult object) {
				return createSearchResultAdapter();
			}
			@Override
			public Adapter caseSearchResultsType(SearchResultsType object) {
				return createSearchResultsTypeAdapter();
			}
			@Override
			public Adapter caseSpace(Space object) {
				return createSpaceAdapter();
			}
			@Override
			public Adapter caseSpacesType(SpacesType object) {
				return createSpacesTypeAdapter();
			}
			@Override
			public Adapter caseSyntaxes(Syntaxes object) {
				return createSyntaxesAdapter();
			}
			@Override
			public Adapter caseTag(Tag object) {
				return createTagAdapter();
			}
			@Override
			public Adapter caseTagsType(TagsType object) {
				return createTagsTypeAdapter();
			}
			@Override
			public Adapter caseTranslation(Translation object) {
				return createTranslationAdapter();
			}
			@Override
			public Adapter caseTranslations(Translations object) {
				return createTranslationsAdapter();
			}
			@Override
			public Adapter caseWiki(Wiki object) {
				return createWikiAdapter();
			}
			@Override
			public Adapter caseWikisType(WikisType object) {
				return createWikisTypeAdapter();
			}
			@Override
			public Adapter caseXWiki(XWiki object) {
				return createXWikiAdapter();
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
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment <em>Attachment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment
	 * @generated
	 */
	public Adapter createAttachmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType <em>Attachments Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType
	 * @generated
	 */
	public Adapter createAttachmentsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Class <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Class
	 * @generated
	 */
	public Adapter createClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType <em>Classes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType
	 * @generated
	 */
	public Adapter createClassesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType <em>Comments Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType
	 * @generated
	 */
	public Adapter createCommentsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary <em>History Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistorySummary
	 * @generated
	 */
	public Adapter createHistorySummaryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType <em>History Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType
	 * @generated
	 */
	public Adapter createHistoryTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Link
	 * @generated
	 */
	public Adapter createLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.LinkCollection <em>Link Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.LinkCollection
	 * @generated
	 */
	public Adapter createLinkCollectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Object
	 * @generated
	 */
	public Adapter createObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType <em>Objects Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType
	 * @generated
	 */
	public Adapter createObjectsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary <em>Object Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary
	 * @generated
	 */
	public Adapter createObjectSummaryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Page
	 * @generated
	 */
	public Adapter createPageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType <em>Pages Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PagesType
	 * @generated
	 */
	public Adapter createPagesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary <em>Page Summary</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PageSummary
	 * @generated
	 */
	public Adapter createPageSummaryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType <em>Properties Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.PropertiesType
	 * @generated
	 */
	public Adapter createPropertiesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult <em>Search Result</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResult
	 * @generated
	 */
	public Adapter createSearchResultAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType <em>Search Results Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SearchResultsType
	 * @generated
	 */
	public Adapter createSearchResultsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Space <em>Space</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Space
	 * @generated
	 */
	public Adapter createSpaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType <em>Spaces Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.SpacesType
	 * @generated
	 */
	public Adapter createSpacesTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes <em>Syntaxes</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Syntaxes
	 * @generated
	 */
	public Adapter createSyntaxesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Tag
	 * @generated
	 */
	public Adapter createTagAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType <em>Tags Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.TagsType
	 * @generated
	 */
	public Adapter createTagsTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translation <em>Translation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Translation
	 * @generated
	 */
	public Adapter createTranslationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Translations <em>Translations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Translations
	 * @generated
	 */
	public Adapter createTranslationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki <em>Wiki</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki
	 * @generated
	 */
	public Adapter createWikiAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType <em>Wikis Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType
	 * @generated
	 */
	public Adapter createWikisTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki <em>XWiki</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki
	 * @generated
	 */
	public Adapter createXWikiAdapter() {
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

} //XwikiAdapterFactory
