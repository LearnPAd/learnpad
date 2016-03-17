/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

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
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XwikiPackageImpl extends EPackageImpl implements XwikiPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attachmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attachmentsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentsTypeEClass = null;

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
	private EClass historySummaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass historyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectSummaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pagesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pageSummaryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertiesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass searchResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass searchResultsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass spacesTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass syntaxesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagsTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass translationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass translationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wikiEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wikisTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xWikiEClass = null;

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
	 * @see eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private XwikiPackageImpl() {
		super(eNS_URI, XwikiFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link XwikiPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static XwikiPackage init() {
		if (isInited) return (XwikiPackage)EPackage.Registry.INSTANCE.getEPackage(XwikiPackage.eNS_URI);

		// Obtain or create and register package
		XwikiPackageImpl theXwikiPackage = (XwikiPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XwikiPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XwikiPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theXwikiPackage.createPackageContents();

		// Initialize created meta-data
		theXwikiPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theXwikiPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(XwikiPackage.eNS_URI, theXwikiPackage);
		return theXwikiPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttachment() {
		return attachmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_Id() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_Name() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_Size() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_Version() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_PageId() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_PageVersion() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_MimeType() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_Author() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_AuthorName() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_Date() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_XwikiRelativeUrl() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttachment_XwikiAbsoluteUrl() {
		return (EAttribute)attachmentEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttachmentsType() {
		return attachmentsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttachmentsType_Attachment() {
		return (EReference)attachmentsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Name() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Value() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClass_() {
		return classEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass_Id() {
		return (EAttribute)classEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClass_Name() {
		return (EAttribute)classEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClass_Property() {
		return (EReference)classEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassesType() {
		return classesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassesType_Class() {
		return (EReference)classesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Id() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_PageId() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Author() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_AuthorName() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Date() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Highlight() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Text() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_ReplyTo() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCommentsType() {
		return commentsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommentsType_Comment() {
		return (EReference)commentsTypeEClass.getEStructuralFeatures().get(0);
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
	public EReference getDocumentRoot_Attachment() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Attachments() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Class() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Classes() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Comment() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Comments() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_History() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Object() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Objects() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ObjectSummary() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Page() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Pages() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Properties() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Property() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SearchResult() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SearchResults() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Space() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Spaces() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Syntaxes() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Tag() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Tags() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Translations() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Wiki() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Wikis() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Xwiki() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHistorySummary() {
		return historySummaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_PageId() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Wiki() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Space() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Name() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Version() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_MajorVersion() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_MinorVersion() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Modified() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Modifier() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_ModifierName() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Language() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHistorySummary_Comment() {
		return (EAttribute)historySummaryEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHistoryType() {
		return historyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHistoryType_HistorySummary() {
		return (EReference)historyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLink() {
		return linkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLink_Href() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLink_HrefLang() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLink_Rel() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLink_Type() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinkCollection() {
		return linkCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLinkCollection_Link() {
		return (EReference)linkCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObject() {
		return objectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObject_Property() {
		return (EReference)objectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjectsType() {
		return objectsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObjectsType_ObjectSummary() {
		return (EReference)objectsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjectSummary() {
		return objectSummaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_Id() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_Guid() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_PageId() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_PageVersion() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_Wiki() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_Space() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_PageName() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_PageAuthor() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_PageAuthorName() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_ClassName() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_Number() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getObjectSummary_Headline() {
		return (EAttribute)objectSummaryEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPage() {
		return pageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Language() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_MajorVersion() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_MinorVersion() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Created() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Creator() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_CreatorName() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Modified() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Modifier() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_ModifierName() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Comment() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPage_Content() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPagesType() {
		return pagesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPagesType_PageSummary() {
		return (EReference)pagesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPageSummary() {
		return pageSummaryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Id() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_FullName() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Wiki() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Space() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Name() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Title() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Parent() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_ParentId() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Version() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Author() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_AuthorName() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_XwikiRelativeUrl() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_XwikiAbsoluteUrl() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPageSummary_Translations() {
		return (EReference)pageSummaryEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPageSummary_Syntax() {
		return (EAttribute)pageSummaryEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertiesType() {
		return propertiesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertiesType_Property() {
		return (EReference)propertiesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty_Attribute() {
		return (EReference)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Name() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Type() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSearchResult() {
		return searchResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Type() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Id() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_PageFullName() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Title() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Wiki() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Space() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_PageName() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Modified() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Author() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_AuthorName() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Version() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Language() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_ClassName() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_ObjectNumber() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Filename() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResult_Score() {
		return (EAttribute)searchResultEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSearchResult_Object() {
		return (EReference)searchResultEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSearchResultsType() {
		return searchResultsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSearchResultsType_SearchResult() {
		return (EReference)searchResultsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSearchResultsType_Template() {
		return (EAttribute)searchResultsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpace() {
		return spaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_Id() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_Wiki() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_Name() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_Home() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_XwikiRelativeUrl() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSpace_XwikiAbsoluteUrl() {
		return (EAttribute)spaceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSpacesType() {
		return spacesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSpacesType_Space() {
		return (EReference)spacesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSyntaxes() {
		return syntaxesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSyntaxes_Syntax() {
		return (EAttribute)syntaxesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTag() {
		return tagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTag_Name() {
		return (EAttribute)tagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagsType() {
		return tagsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagsType_Tag() {
		return (EReference)tagsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTranslation() {
		return translationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTranslation_Language() {
		return (EAttribute)translationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTranslations() {
		return translationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTranslations_Translation() {
		return (EReference)translationsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTranslations_Default() {
		return (EAttribute)translationsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWiki() {
		return wikiEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWiki_Id() {
		return (EAttribute)wikiEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWiki_Name() {
		return (EAttribute)wikiEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWiki_Description() {
		return (EAttribute)wikiEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWiki_Owner() {
		return (EAttribute)wikiEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWikisType() {
		return wikisTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWikisType_Wiki() {
		return (EReference)wikisTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXWiki() {
		return xWikiEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getXWiki_Version() {
		return (EAttribute)xWikiEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getXWiki_Syntaxes() {
		return (EReference)xWikiEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XwikiFactory getXwikiFactory() {
		return (XwikiFactory)getEFactoryInstance();
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
		attachmentEClass = createEClass(ATTACHMENT);
		createEAttribute(attachmentEClass, ATTACHMENT__ID);
		createEAttribute(attachmentEClass, ATTACHMENT__NAME);
		createEAttribute(attachmentEClass, ATTACHMENT__SIZE);
		createEAttribute(attachmentEClass, ATTACHMENT__VERSION);
		createEAttribute(attachmentEClass, ATTACHMENT__PAGE_ID);
		createEAttribute(attachmentEClass, ATTACHMENT__PAGE_VERSION);
		createEAttribute(attachmentEClass, ATTACHMENT__MIME_TYPE);
		createEAttribute(attachmentEClass, ATTACHMENT__AUTHOR);
		createEAttribute(attachmentEClass, ATTACHMENT__AUTHOR_NAME);
		createEAttribute(attachmentEClass, ATTACHMENT__DATE);
		createEAttribute(attachmentEClass, ATTACHMENT__XWIKI_RELATIVE_URL);
		createEAttribute(attachmentEClass, ATTACHMENT__XWIKI_ABSOLUTE_URL);

		attachmentsTypeEClass = createEClass(ATTACHMENTS_TYPE);
		createEReference(attachmentsTypeEClass, ATTACHMENTS_TYPE__ATTACHMENT);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__NAME);
		createEAttribute(attributeEClass, ATTRIBUTE__VALUE);

		classEClass = createEClass(CLASS);
		createEAttribute(classEClass, CLASS__ID);
		createEAttribute(classEClass, CLASS__NAME);
		createEReference(classEClass, CLASS__PROPERTY);

		classesTypeEClass = createEClass(CLASSES_TYPE);
		createEReference(classesTypeEClass, CLASSES_TYPE__CLASS);

		commentEClass = createEClass(COMMENT);
		createEAttribute(commentEClass, COMMENT__ID);
		createEAttribute(commentEClass, COMMENT__PAGE_ID);
		createEAttribute(commentEClass, COMMENT__AUTHOR);
		createEAttribute(commentEClass, COMMENT__AUTHOR_NAME);
		createEAttribute(commentEClass, COMMENT__DATE);
		createEAttribute(commentEClass, COMMENT__HIGHLIGHT);
		createEAttribute(commentEClass, COMMENT__TEXT);
		createEAttribute(commentEClass, COMMENT__REPLY_TO);

		commentsTypeEClass = createEClass(COMMENTS_TYPE);
		createEReference(commentsTypeEClass, COMMENTS_TYPE__COMMENT);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTACHMENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ATTACHMENTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CLASS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CLASSES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__COMMENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__COMMENTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__HISTORY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OBJECT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OBJECTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OBJECT_SUMMARY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PAGE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PAGES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTIES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SEARCH_RESULT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SEARCH_RESULTS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPACE);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SPACES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__SYNTAXES);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TAG);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TAGS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TRANSLATIONS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WIKI);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WIKIS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XWIKI);

		historySummaryEClass = createEClass(HISTORY_SUMMARY);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__PAGE_ID);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__WIKI);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__SPACE);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__NAME);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__VERSION);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__MAJOR_VERSION);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__MINOR_VERSION);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__MODIFIED);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__MODIFIER);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__MODIFIER_NAME);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__LANGUAGE);
		createEAttribute(historySummaryEClass, HISTORY_SUMMARY__COMMENT);

		historyTypeEClass = createEClass(HISTORY_TYPE);
		createEReference(historyTypeEClass, HISTORY_TYPE__HISTORY_SUMMARY);

		linkEClass = createEClass(LINK);
		createEAttribute(linkEClass, LINK__HREF);
		createEAttribute(linkEClass, LINK__HREF_LANG);
		createEAttribute(linkEClass, LINK__REL);
		createEAttribute(linkEClass, LINK__TYPE);

		linkCollectionEClass = createEClass(LINK_COLLECTION);
		createEReference(linkCollectionEClass, LINK_COLLECTION__LINK);

		objectEClass = createEClass(OBJECT);
		createEReference(objectEClass, OBJECT__PROPERTY);

		objectsTypeEClass = createEClass(OBJECTS_TYPE);
		createEReference(objectsTypeEClass, OBJECTS_TYPE__OBJECT_SUMMARY);

		objectSummaryEClass = createEClass(OBJECT_SUMMARY);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__ID);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__GUID);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__PAGE_ID);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__PAGE_VERSION);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__WIKI);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__SPACE);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__PAGE_NAME);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__PAGE_AUTHOR);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__PAGE_AUTHOR_NAME);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__CLASS_NAME);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__NUMBER);
		createEAttribute(objectSummaryEClass, OBJECT_SUMMARY__HEADLINE);

		pageEClass = createEClass(PAGE);
		createEAttribute(pageEClass, PAGE__LANGUAGE);
		createEAttribute(pageEClass, PAGE__MAJOR_VERSION);
		createEAttribute(pageEClass, PAGE__MINOR_VERSION);
		createEAttribute(pageEClass, PAGE__CREATED);
		createEAttribute(pageEClass, PAGE__CREATOR);
		createEAttribute(pageEClass, PAGE__CREATOR_NAME);
		createEAttribute(pageEClass, PAGE__MODIFIED);
		createEAttribute(pageEClass, PAGE__MODIFIER);
		createEAttribute(pageEClass, PAGE__MODIFIER_NAME);
		createEAttribute(pageEClass, PAGE__COMMENT);
		createEAttribute(pageEClass, PAGE__CONTENT);

		pagesTypeEClass = createEClass(PAGES_TYPE);
		createEReference(pagesTypeEClass, PAGES_TYPE__PAGE_SUMMARY);

		pageSummaryEClass = createEClass(PAGE_SUMMARY);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__ID);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__FULL_NAME);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__WIKI);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__SPACE);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__NAME);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__TITLE);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__PARENT);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__PARENT_ID);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__VERSION);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__AUTHOR);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__AUTHOR_NAME);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__XWIKI_RELATIVE_URL);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__XWIKI_ABSOLUTE_URL);
		createEReference(pageSummaryEClass, PAGE_SUMMARY__TRANSLATIONS);
		createEAttribute(pageSummaryEClass, PAGE_SUMMARY__SYNTAX);

		propertiesTypeEClass = createEClass(PROPERTIES_TYPE);
		createEReference(propertiesTypeEClass, PROPERTIES_TYPE__PROPERTY);

		propertyEClass = createEClass(PROPERTY);
		createEReference(propertyEClass, PROPERTY__ATTRIBUTE);
		createEAttribute(propertyEClass, PROPERTY__VALUE);
		createEAttribute(propertyEClass, PROPERTY__NAME);
		createEAttribute(propertyEClass, PROPERTY__TYPE);

		searchResultEClass = createEClass(SEARCH_RESULT);
		createEAttribute(searchResultEClass, SEARCH_RESULT__TYPE);
		createEAttribute(searchResultEClass, SEARCH_RESULT__ID);
		createEAttribute(searchResultEClass, SEARCH_RESULT__PAGE_FULL_NAME);
		createEAttribute(searchResultEClass, SEARCH_RESULT__TITLE);
		createEAttribute(searchResultEClass, SEARCH_RESULT__WIKI);
		createEAttribute(searchResultEClass, SEARCH_RESULT__SPACE);
		createEAttribute(searchResultEClass, SEARCH_RESULT__PAGE_NAME);
		createEAttribute(searchResultEClass, SEARCH_RESULT__MODIFIED);
		createEAttribute(searchResultEClass, SEARCH_RESULT__AUTHOR);
		createEAttribute(searchResultEClass, SEARCH_RESULT__AUTHOR_NAME);
		createEAttribute(searchResultEClass, SEARCH_RESULT__VERSION);
		createEAttribute(searchResultEClass, SEARCH_RESULT__LANGUAGE);
		createEAttribute(searchResultEClass, SEARCH_RESULT__CLASS_NAME);
		createEAttribute(searchResultEClass, SEARCH_RESULT__OBJECT_NUMBER);
		createEAttribute(searchResultEClass, SEARCH_RESULT__FILENAME);
		createEAttribute(searchResultEClass, SEARCH_RESULT__SCORE);
		createEReference(searchResultEClass, SEARCH_RESULT__OBJECT);

		searchResultsTypeEClass = createEClass(SEARCH_RESULTS_TYPE);
		createEReference(searchResultsTypeEClass, SEARCH_RESULTS_TYPE__SEARCH_RESULT);
		createEAttribute(searchResultsTypeEClass, SEARCH_RESULTS_TYPE__TEMPLATE);

		spaceEClass = createEClass(SPACE);
		createEAttribute(spaceEClass, SPACE__ID);
		createEAttribute(spaceEClass, SPACE__WIKI);
		createEAttribute(spaceEClass, SPACE__NAME);
		createEAttribute(spaceEClass, SPACE__HOME);
		createEAttribute(spaceEClass, SPACE__XWIKI_RELATIVE_URL);
		createEAttribute(spaceEClass, SPACE__XWIKI_ABSOLUTE_URL);

		spacesTypeEClass = createEClass(SPACES_TYPE);
		createEReference(spacesTypeEClass, SPACES_TYPE__SPACE);

		syntaxesEClass = createEClass(SYNTAXES);
		createEAttribute(syntaxesEClass, SYNTAXES__SYNTAX);

		tagEClass = createEClass(TAG);
		createEAttribute(tagEClass, TAG__NAME);

		tagsTypeEClass = createEClass(TAGS_TYPE);
		createEReference(tagsTypeEClass, TAGS_TYPE__TAG);

		translationEClass = createEClass(TRANSLATION);
		createEAttribute(translationEClass, TRANSLATION__LANGUAGE);

		translationsEClass = createEClass(TRANSLATIONS);
		createEReference(translationsEClass, TRANSLATIONS__TRANSLATION);
		createEAttribute(translationsEClass, TRANSLATIONS__DEFAULT);

		wikiEClass = createEClass(WIKI);
		createEAttribute(wikiEClass, WIKI__ID);
		createEAttribute(wikiEClass, WIKI__NAME);
		createEAttribute(wikiEClass, WIKI__DESCRIPTION);
		createEAttribute(wikiEClass, WIKI__OWNER);

		wikisTypeEClass = createEClass(WIKIS_TYPE);
		createEReference(wikisTypeEClass, WIKIS_TYPE__WIKI);

		xWikiEClass = createEClass(XWIKI);
		createEAttribute(xWikiEClass, XWIKI__VERSION);
		createEReference(xWikiEClass, XWIKI__SYNTAXES);
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
		attachmentEClass.getESuperTypes().add(this.getLinkCollection());
		attachmentsTypeEClass.getESuperTypes().add(this.getLinkCollection());
		attributeEClass.getESuperTypes().add(this.getLinkCollection());
		classEClass.getESuperTypes().add(this.getLinkCollection());
		classesTypeEClass.getESuperTypes().add(this.getLinkCollection());
		commentEClass.getESuperTypes().add(this.getLinkCollection());
		commentsTypeEClass.getESuperTypes().add(this.getLinkCollection());
		historySummaryEClass.getESuperTypes().add(this.getLinkCollection());
		historyTypeEClass.getESuperTypes().add(this.getLinkCollection());
		objectEClass.getESuperTypes().add(this.getObjectSummary());
		objectsTypeEClass.getESuperTypes().add(this.getLinkCollection());
		objectSummaryEClass.getESuperTypes().add(this.getLinkCollection());
		pageEClass.getESuperTypes().add(this.getPageSummary());
		pagesTypeEClass.getESuperTypes().add(this.getLinkCollection());
		pageSummaryEClass.getESuperTypes().add(this.getLinkCollection());
		propertiesTypeEClass.getESuperTypes().add(this.getLinkCollection());
		propertyEClass.getESuperTypes().add(this.getLinkCollection());
		searchResultEClass.getESuperTypes().add(this.getLinkCollection());
		searchResultsTypeEClass.getESuperTypes().add(this.getLinkCollection());
		spaceEClass.getESuperTypes().add(this.getLinkCollection());
		spacesTypeEClass.getESuperTypes().add(this.getLinkCollection());
		syntaxesEClass.getESuperTypes().add(this.getLinkCollection());
		tagEClass.getESuperTypes().add(this.getLinkCollection());
		tagsTypeEClass.getESuperTypes().add(this.getLinkCollection());
		translationEClass.getESuperTypes().add(this.getLinkCollection());
		translationsEClass.getESuperTypes().add(this.getLinkCollection());
		wikiEClass.getESuperTypes().add(this.getLinkCollection());
		wikisTypeEClass.getESuperTypes().add(this.getLinkCollection());
		xWikiEClass.getESuperTypes().add(this.getLinkCollection());

		// Initialize classes, features, and operations; add parameters
		initEClass(attachmentEClass, Attachment.class, "Attachment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttachment_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_Size(), theXMLTypePackage.getInt(), "size", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_PageId(), theXMLTypePackage.getString(), "pageId", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_PageVersion(), theXMLTypePackage.getString(), "pageVersion", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_MimeType(), theXMLTypePackage.getString(), "mimeType", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_Author(), theXMLTypePackage.getString(), "author", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_AuthorName(), theXMLTypePackage.getString(), "authorName", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_Date(), theXMLTypePackage.getDateTime(), "date", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_XwikiRelativeUrl(), theXMLTypePackage.getString(), "xwikiRelativeUrl", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttachment_XwikiAbsoluteUrl(), theXMLTypePackage.getString(), "xwikiAbsoluteUrl", null, 1, 1, Attachment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attachmentsTypeEClass, AttachmentsType.class, "AttachmentsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttachmentsType_Attachment(), this.getAttachment(), null, "attachment", null, 0, -1, AttachmentsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classEClass, eu.learnpad.transformations.metamodel_corpus.xwiki.Class.class, "Class", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClass_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, eu.learnpad.transformations.metamodel_corpus.xwiki.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClass_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, eu.learnpad.transformations.metamodel_corpus.xwiki.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getClass_Property(), this.getProperty(), null, "property", null, 0, -1, eu.learnpad.transformations.metamodel_corpus.xwiki.Class.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(classesTypeEClass, ClassesType.class, "ClassesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassesType_Class(), this.getClass_(), null, "class", null, 0, -1, ClassesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComment_Id(), theXMLTypePackage.getInt(), "id", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_PageId(), theXMLTypePackage.getString(), "pageId", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Author(), theXMLTypePackage.getString(), "author", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_AuthorName(), theXMLTypePackage.getString(), "authorName", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Date(), theXMLTypePackage.getDateTime(), "date", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Highlight(), theXMLTypePackage.getString(), "highlight", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Text(), theXMLTypePackage.getString(), "text", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_ReplyTo(), theXMLTypePackage.getIntObject(), "replyTo", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commentsTypeEClass, CommentsType.class, "CommentsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCommentsType_Comment(), this.getComment(), null, "comment", null, 0, -1, CommentsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Attachment(), this.getAttachment(), null, "attachment", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Attachments(), this.getAttachmentsType(), null, "attachments", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Class(), this.getClass_(), null, "class", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Classes(), this.getClassesType(), null, "classes", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Comment(), this.getComment(), null, "comment", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Comments(), this.getCommentsType(), null, "comments", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_History(), this.getHistoryType(), null, "history", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Object(), this.getObject(), null, "object", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Objects(), this.getObjectsType(), null, "objects", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ObjectSummary(), this.getObjectSummary(), null, "objectSummary", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Page(), this.getPage(), null, "page", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Pages(), this.getPagesType(), null, "pages", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Properties(), this.getPropertiesType(), null, "properties", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Property(), this.getProperty(), null, "property", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SearchResult(), this.getSearchResult(), null, "searchResult", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_SearchResults(), this.getSearchResultsType(), null, "searchResults", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Space(), this.getSpace(), null, "space", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Spaces(), this.getSpacesType(), null, "spaces", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Syntaxes(), this.getSyntaxes(), null, "syntaxes", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Tag(), this.getTag(), null, "tag", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Tags(), this.getTagsType(), null, "tags", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Translations(), this.getTranslations(), null, "translations", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Wiki(), this.getWiki(), null, "wiki", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Wikis(), this.getWikisType(), null, "wikis", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Xwiki(), this.getXWiki(), null, "xwiki", null, 0, -1, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(historySummaryEClass, HistorySummary.class, "HistorySummary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHistorySummary_PageId(), theXMLTypePackage.getString(), "pageId", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Wiki(), theXMLTypePackage.getString(), "wiki", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Space(), theXMLTypePackage.getString(), "space", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_MajorVersion(), theXMLTypePackage.getInt(), "majorVersion", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_MinorVersion(), theXMLTypePackage.getInt(), "minorVersion", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Modified(), theXMLTypePackage.getDateTime(), "modified", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Modifier(), theXMLTypePackage.getString(), "modifier", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_ModifierName(), theXMLTypePackage.getString(), "modifierName", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Language(), theXMLTypePackage.getString(), "language", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHistorySummary_Comment(), theXMLTypePackage.getString(), "comment", null, 1, 1, HistorySummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(historyTypeEClass, HistoryType.class, "HistoryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHistoryType_HistorySummary(), this.getHistorySummary(), null, "historySummary", null, 0, -1, HistoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLink_Href(), theXMLTypePackage.getString(), "href", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_HrefLang(), theXMLTypePackage.getString(), "hrefLang", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Rel(), theXMLTypePackage.getString(), "rel", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkCollectionEClass, LinkCollection.class, "LinkCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLinkCollection_Link(), this.getLink(), null, "link", null, 0, -1, LinkCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objectEClass, eu.learnpad.transformations.metamodel_corpus.xwiki.Object.class, "Object", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObject_Property(), this.getProperty(), null, "property", null, 0, -1, eu.learnpad.transformations.metamodel_corpus.xwiki.Object.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objectsTypeEClass, ObjectsType.class, "ObjectsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObjectsType_ObjectSummary(), this.getObjectSummary(), null, "objectSummary", null, 0, -1, ObjectsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objectSummaryEClass, ObjectSummary.class, "ObjectSummary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getObjectSummary_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_Guid(), theXMLTypePackage.getString(), "guid", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_PageId(), theXMLTypePackage.getString(), "pageId", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_PageVersion(), theXMLTypePackage.getString(), "pageVersion", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_Wiki(), theXMLTypePackage.getString(), "wiki", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_Space(), theXMLTypePackage.getString(), "space", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_PageName(), theXMLTypePackage.getString(), "pageName", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_PageAuthor(), theXMLTypePackage.getString(), "pageAuthor", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_PageAuthorName(), theXMLTypePackage.getString(), "pageAuthorName", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_ClassName(), theXMLTypePackage.getString(), "className", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_Number(), theXMLTypePackage.getInt(), "number", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjectSummary_Headline(), theXMLTypePackage.getString(), "headline", null, 1, 1, ObjectSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPage_Language(), theXMLTypePackage.getString(), "language", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_MajorVersion(), theXMLTypePackage.getInt(), "majorVersion", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_MinorVersion(), theXMLTypePackage.getInt(), "minorVersion", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Created(), theXMLTypePackage.getDateTime(), "created", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Creator(), theXMLTypePackage.getString(), "creator", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_CreatorName(), theXMLTypePackage.getString(), "creatorName", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Modified(), theXMLTypePackage.getDateTime(), "modified", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Modifier(), theXMLTypePackage.getString(), "modifier", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_ModifierName(), theXMLTypePackage.getString(), "modifierName", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Comment(), theXMLTypePackage.getString(), "comment", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Content(), theXMLTypePackage.getString(), "content", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pagesTypeEClass, PagesType.class, "PagesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPagesType_PageSummary(), this.getPageSummary(), null, "pageSummary", null, 0, -1, PagesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageSummaryEClass, PageSummary.class, "PageSummary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPageSummary_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_FullName(), theXMLTypePackage.getString(), "fullName", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Wiki(), theXMLTypePackage.getString(), "wiki", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Space(), theXMLTypePackage.getString(), "space", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Title(), theXMLTypePackage.getString(), "title", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Parent(), theXMLTypePackage.getString(), "parent", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_ParentId(), theXMLTypePackage.getString(), "parentId", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Author(), theXMLTypePackage.getString(), "author", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_AuthorName(), theXMLTypePackage.getString(), "authorName", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_XwikiRelativeUrl(), theXMLTypePackage.getString(), "xwikiRelativeUrl", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_XwikiAbsoluteUrl(), theXMLTypePackage.getString(), "xwikiAbsoluteUrl", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPageSummary_Translations(), this.getTranslations(), null, "translations", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPageSummary_Syntax(), theXMLTypePackage.getString(), "syntax", null, 1, 1, PageSummary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertiesTypeEClass, PropertiesType.class, "PropertiesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertiesType_Property(), this.getProperty(), null, "property", null, 0, -1, PropertiesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProperty_Attribute(), this.getAttribute(), null, "attribute", null, 0, -1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), theXMLTypePackage.getString(), "value", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(searchResultEClass, SearchResult.class, "SearchResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSearchResult_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_PageFullName(), theXMLTypePackage.getString(), "pageFullName", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Title(), theXMLTypePackage.getString(), "title", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Wiki(), theXMLTypePackage.getString(), "wiki", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Space(), theXMLTypePackage.getString(), "space", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_PageName(), theXMLTypePackage.getString(), "pageName", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Modified(), theXMLTypePackage.getDateTime(), "modified", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Author(), theXMLTypePackage.getString(), "author", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_AuthorName(), theXMLTypePackage.getString(), "authorName", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Language(), theXMLTypePackage.getString(), "language", null, 1, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_ClassName(), theXMLTypePackage.getString(), "className", null, 0, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_ObjectNumber(), theXMLTypePackage.getInt(), "objectNumber", null, 0, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Filename(), theXMLTypePackage.getString(), "filename", null, 0, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResult_Score(), theXMLTypePackage.getFloat(), "score", null, 0, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSearchResult_Object(), this.getObject(), null, "object", null, 0, 1, SearchResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(searchResultsTypeEClass, SearchResultsType.class, "SearchResultsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSearchResultsType_SearchResult(), this.getSearchResult(), null, "searchResult", null, 0, -1, SearchResultsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSearchResultsType_Template(), theXMLTypePackage.getString(), "template", null, 0, 1, SearchResultsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spaceEClass, Space.class, "Space", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSpace_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpace_Wiki(), theXMLTypePackage.getString(), "wiki", null, 1, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpace_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpace_Home(), theXMLTypePackage.getString(), "home", null, 1, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpace_XwikiRelativeUrl(), theXMLTypePackage.getString(), "xwikiRelativeUrl", null, 1, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSpace_XwikiAbsoluteUrl(), theXMLTypePackage.getString(), "xwikiAbsoluteUrl", null, 1, 1, Space.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(spacesTypeEClass, SpacesType.class, "SpacesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSpacesType_Space(), this.getSpace(), null, "space", null, 0, -1, SpacesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(syntaxesEClass, Syntaxes.class, "Syntaxes", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSyntaxes_Syntax(), theXMLTypePackage.getString(), "syntax", null, 0, -1, Syntaxes.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tagEClass, Tag.class, "Tag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTag_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Tag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tagsTypeEClass, TagsType.class, "TagsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTagsType_Tag(), this.getTag(), null, "tag", null, 0, -1, TagsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(translationEClass, Translation.class, "Translation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTranslation_Language(), theXMLTypePackage.getString(), "language", null, 0, 1, Translation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(translationsEClass, Translations.class, "Translations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTranslations_Translation(), this.getTranslation(), null, "translation", null, 0, -1, Translations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTranslations_Default(), theXMLTypePackage.getString(), "default", null, 0, 1, Translations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wikiEClass, Wiki.class, "Wiki", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWiki_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Wiki.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWiki_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Wiki.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWiki_Description(), theXMLTypePackage.getString(), "description", null, 1, 1, Wiki.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWiki_Owner(), theXMLTypePackage.getString(), "owner", null, 1, 1, Wiki.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wikisTypeEClass, WikisType.class, "WikisType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWikisType_Wiki(), this.getWiki(), null, "wiki", null, 0, -1, WikisType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(xWikiEClass, XWiki.class, "XWiki", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getXWiki_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, XWiki.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getXWiki_Syntaxes(), this.getSyntaxes(), null, "syntaxes", null, 1, 1, XWiki.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (attachmentEClass, 
		   source, 
		   new String[] {
			 "name", "Attachment",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getAttachment_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_Name(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_Size(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "size",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "version",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_PageId(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageId",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_PageVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageVersion",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_MimeType(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "mimeType",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_Author(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "author",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_AuthorName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "authorName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_Date(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "date",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_XwikiRelativeUrl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "xwikiRelativeUrl",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getAttachment_XwikiAbsoluteUrl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "xwikiAbsoluteUrl",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (attachmentsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "attachments_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getAttachmentsType_Attachment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "attachment",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (attributeEClass, 
		   source, 
		   new String[] {
			 "name", "Attribute",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getAttribute_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (getAttribute_Value(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "value"
		   });	
		addAnnotation
		  (classEClass, 
		   source, 
		   new String[] {
			 "name", "Class",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getClass_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getClass_Name(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getClass_Property(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "property",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (classesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "classes_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getClassesType_Class(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "class",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (commentEClass, 
		   source, 
		   new String[] {
			 "name", "Comment",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getComment_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getComment_PageId(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageId",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getComment_Author(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "author",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getComment_AuthorName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "authorName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getComment_Date(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "date",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getComment_Highlight(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "highlight",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getComment_Text(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "text",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getComment_ReplyTo(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "replyTo",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (commentsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "comments_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getCommentsType_Comment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "comment",
			 "namespace", "##targetNamespace"
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
		  (getDocumentRoot_Attachment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "attachment",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Attachments(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "attachments",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Class(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "class",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Classes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "classes",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Comment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "comment",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Comments(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "comments",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_History(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "history",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Object(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "object",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Objects(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "objects",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_ObjectSummary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "objectSummary",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Page(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "page",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Pages(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pages",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Properties(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "properties",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Property(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "property",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_SearchResult(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "searchResult",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_SearchResults(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "searchResults",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "space",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Spaces(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "spaces",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Syntaxes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "syntaxes",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Tag(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "tag",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Tags(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "tags",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Translations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "translations",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Wiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Wikis(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wikis",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getDocumentRoot_Xwiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "xwiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (historySummaryEClass, 
		   source, 
		   new String[] {
			 "name", "HistorySummary",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getHistorySummary_PageId(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageId",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Wiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "space",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Name(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "version",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_MajorVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "majorVersion",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_MinorVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "minorVersion",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Modified(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "modified",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Modifier(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "modifier",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_ModifierName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "modifierName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Language(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "language",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getHistorySummary_Comment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "comment",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (historyTypeEClass, 
		   source, 
		   new String[] {
			 "name", "history_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getHistoryType_HistorySummary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "historySummary",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (linkEClass, 
		   source, 
		   new String[] {
			 "name", "Link",
			 "kind", "empty"
		   });	
		addAnnotation
		  (getLink_Href(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "href"
		   });	
		addAnnotation
		  (getLink_HrefLang(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "hrefLang"
		   });	
		addAnnotation
		  (getLink_Rel(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "rel"
		   });	
		addAnnotation
		  (getLink_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });	
		addAnnotation
		  (linkCollectionEClass, 
		   source, 
		   new String[] {
			 "name", "LinkCollection",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getLinkCollection_Link(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "link",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (objectEClass, 
		   source, 
		   new String[] {
			 "name", "Object",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getObject_Property(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "property",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (objectsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "objects_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getObjectsType_ObjectSummary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "objectSummary",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (objectSummaryEClass, 
		   source, 
		   new String[] {
			 "name", "ObjectSummary",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getObjectSummary_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_Guid(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "guid",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_PageId(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageId",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_PageVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageVersion",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_Wiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "space",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_PageName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_PageAuthor(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageAuthor",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_PageAuthorName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageAuthorName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_ClassName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "className",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_Number(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "number",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getObjectSummary_Headline(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "headline",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (pageEClass, 
		   source, 
		   new String[] {
			 "name", "Page",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getPage_Language(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "language",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_MajorVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "majorVersion",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_MinorVersion(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "minorVersion",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_Created(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "created",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_Creator(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "creator",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_CreatorName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "creatorName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_Modified(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "modified",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_Modifier(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "modifier",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_ModifierName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "modifierName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_Comment(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "comment",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPage_Content(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "content",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (pagesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "pages_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getPagesType_PageSummary(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageSummary",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (pageSummaryEClass, 
		   source, 
		   new String[] {
			 "name", "PageSummary",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getPageSummary_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_FullName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "fullName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Wiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "space",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Name(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Title(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "title",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Parent(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "parent",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_ParentId(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "parentId",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "version",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Author(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "author",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_AuthorName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "authorName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_XwikiRelativeUrl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "xwikiRelativeUrl",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_XwikiAbsoluteUrl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "xwikiAbsoluteUrl",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Translations(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "translations",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getPageSummary_Syntax(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "syntax",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (propertiesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "properties_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getPropertiesType_Property(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "property",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (propertyEClass, 
		   source, 
		   new String[] {
			 "name", "Property",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getProperty_Attribute(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "attribute",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getProperty_Value(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "value",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getProperty_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (getProperty_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });	
		addAnnotation
		  (searchResultEClass, 
		   source, 
		   new String[] {
			 "name", "SearchResult",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getSearchResult_Type(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "type",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_PageFullName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageFullName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Title(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "title",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Wiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "space",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_PageName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "pageName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Modified(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "modified",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Author(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "author",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_AuthorName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "authorName",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "version",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Language(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "language",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_ClassName(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "className",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_ObjectNumber(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "objectNumber",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Filename(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "filename",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Score(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "score",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResult_Object(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "object",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (searchResultsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "searchResults_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getSearchResultsType_SearchResult(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "searchResult",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSearchResultsType_Template(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "template"
		   });	
		addAnnotation
		  (spaceEClass, 
		   source, 
		   new String[] {
			 "name", "Space",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getSpace_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSpace_Wiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSpace_Name(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSpace_Home(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "home",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSpace_XwikiRelativeUrl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "xwikiRelativeUrl",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getSpace_XwikiAbsoluteUrl(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "xwikiAbsoluteUrl",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (spacesTypeEClass, 
		   source, 
		   new String[] {
			 "name", "spaces_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getSpacesType_Space(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "space",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (syntaxesEClass, 
		   source, 
		   new String[] {
			 "name", "Syntaxes",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getSyntaxes_Syntax(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "syntax",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (tagEClass, 
		   source, 
		   new String[] {
			 "name", "Tag",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getTag_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });	
		addAnnotation
		  (tagsTypeEClass, 
		   source, 
		   new String[] {
			 "name", "tags_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getTagsType_Tag(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "tag",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (translationEClass, 
		   source, 
		   new String[] {
			 "name", "Translation",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getTranslation_Language(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "language"
		   });	
		addAnnotation
		  (translationsEClass, 
		   source, 
		   new String[] {
			 "name", "Translations",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getTranslations_Translation(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "translation",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getTranslations_Default(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "default"
		   });	
		addAnnotation
		  (wikiEClass, 
		   source, 
		   new String[] {
			 "name", "Wiki",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getWiki_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getWiki_Name(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "name",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getWiki_Description(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "description",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getWiki_Owner(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "owner",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (wikisTypeEClass, 
		   source, 
		   new String[] {
			 "name", "wikis_._type",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getWikisType_Wiki(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "wiki",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (xWikiEClass, 
		   source, 
		   new String[] {
			 "name", "XWiki",
			 "kind", "elementOnly"
		   });	
		addAnnotation
		  (getXWiki_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "version",
			 "namespace", "##targetNamespace"
		   });	
		addAnnotation
		  (getXWiki_Syntaxes(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "syntaxes",
			 "namespace", "##targetNamespace"
		   });
	}

} //XwikiPackageImpl
