/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiFactory;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XwikiFactoryImpl extends EFactoryImpl implements XwikiFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static XwikiFactory init() {
		try {
			XwikiFactory theXwikiFactory = (XwikiFactory)EPackage.Registry.INSTANCE.getEFactory(XwikiPackage.eNS_URI);
			if (theXwikiFactory != null) {
				return theXwikiFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new XwikiFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XwikiFactoryImpl() {
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
			case XwikiPackage.ATTACHMENT: return createAttachment();
			case XwikiPackage.ATTACHMENTS_TYPE: return createAttachmentsType();
			case XwikiPackage.ATTRIBUTE: return createAttribute();
			case XwikiPackage.CLASS: return createClass();
			case XwikiPackage.CLASSES_TYPE: return createClassesType();
			case XwikiPackage.COMMENT: return createComment();
			case XwikiPackage.COMMENTS_TYPE: return createCommentsType();
			case XwikiPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case XwikiPackage.HISTORY_SUMMARY: return createHistorySummary();
			case XwikiPackage.HISTORY_TYPE: return createHistoryType();
			case XwikiPackage.LINK: return createLink();
			case XwikiPackage.LINK_COLLECTION: return createLinkCollection();
			case XwikiPackage.OBJECT: return createObject();
			case XwikiPackage.OBJECTS_TYPE: return createObjectsType();
			case XwikiPackage.OBJECT_SUMMARY: return createObjectSummary();
			case XwikiPackage.PAGE: return createPage();
			case XwikiPackage.PAGES_TYPE: return createPagesType();
			case XwikiPackage.PAGE_SUMMARY: return createPageSummary();
			case XwikiPackage.PROPERTIES_TYPE: return createPropertiesType();
			case XwikiPackage.PROPERTY: return createProperty();
			case XwikiPackage.SEARCH_RESULT: return createSearchResult();
			case XwikiPackage.SEARCH_RESULTS_TYPE: return createSearchResultsType();
			case XwikiPackage.SPACE: return createSpace();
			case XwikiPackage.SPACES_TYPE: return createSpacesType();
			case XwikiPackage.SYNTAXES: return createSyntaxes();
			case XwikiPackage.TAG: return createTag();
			case XwikiPackage.TAGS_TYPE: return createTagsType();
			case XwikiPackage.TRANSLATION: return createTranslation();
			case XwikiPackage.TRANSLATIONS: return createTranslations();
			case XwikiPackage.WIKI: return createWiki();
			case XwikiPackage.WIKIS_TYPE: return createWikisType();
			case XwikiPackage.XWIKI: return createXWiki();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attachment createAttachment() {
		AttachmentImpl attachment = new AttachmentImpl();
		return attachment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttachmentsType createAttachmentsType() {
		AttachmentsTypeImpl attachmentsType = new AttachmentsTypeImpl();
		return attachmentsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public eu.learnpad.transformations.metamodel_corpus.xwiki.Class createClass() {
		ClassImpl class_ = new ClassImpl();
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassesType createClassesType() {
		ClassesTypeImpl classesType = new ClassesTypeImpl();
		return classesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comment createComment() {
		CommentImpl comment = new CommentImpl();
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CommentsType createCommentsType() {
		CommentsTypeImpl commentsType = new CommentsTypeImpl();
		return commentsType;
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
	public HistorySummary createHistorySummary() {
		HistorySummaryImpl historySummary = new HistorySummaryImpl();
		return historySummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HistoryType createHistoryType() {
		HistoryTypeImpl historyType = new HistoryTypeImpl();
		return historyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link createLink() {
		LinkImpl link = new LinkImpl();
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkCollection createLinkCollection() {
		LinkCollectionImpl linkCollection = new LinkCollectionImpl();
		return linkCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public eu.learnpad.transformations.metamodel_corpus.xwiki.Object createObject() {
		ObjectImpl object = new ObjectImpl();
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectsType createObjectsType() {
		ObjectsTypeImpl objectsType = new ObjectsTypeImpl();
		return objectsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectSummary createObjectSummary() {
		ObjectSummaryImpl objectSummary = new ObjectSummaryImpl();
		return objectSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PagesType createPagesType() {
		PagesTypeImpl pagesType = new PagesTypeImpl();
		return pagesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PageSummary createPageSummary() {
		PageSummaryImpl pageSummary = new PageSummaryImpl();
		return pageSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertiesType createPropertiesType() {
		PropertiesTypeImpl propertiesType = new PropertiesTypeImpl();
		return propertiesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchResult createSearchResult() {
		SearchResultImpl searchResult = new SearchResultImpl();
		return searchResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchResultsType createSearchResultsType() {
		SearchResultsTypeImpl searchResultsType = new SearchResultsTypeImpl();
		return searchResultsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Space createSpace() {
		SpaceImpl space = new SpaceImpl();
		return space;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpacesType createSpacesType() {
		SpacesTypeImpl spacesType = new SpacesTypeImpl();
		return spacesType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Syntaxes createSyntaxes() {
		SyntaxesImpl syntaxes = new SyntaxesImpl();
		return syntaxes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag createTag() {
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagsType createTagsType() {
		TagsTypeImpl tagsType = new TagsTypeImpl();
		return tagsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Translation createTranslation() {
		TranslationImpl translation = new TranslationImpl();
		return translation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Translations createTranslations() {
		TranslationsImpl translations = new TranslationsImpl();
		return translations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Wiki createWiki() {
		WikiImpl wiki = new WikiImpl();
		return wiki;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WikisType createWikisType() {
		WikisTypeImpl wikisType = new WikisTypeImpl();
		return wikisType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XWiki createXWiki() {
		XWikiImpl xWiki = new XWikiImpl();
		return xWiki;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XwikiPackage getXwikiPackage() {
		return (XwikiPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static XwikiPackage getPackage() {
		return XwikiPackage.eINSTANCE;
	}

} //XwikiFactoryImpl
