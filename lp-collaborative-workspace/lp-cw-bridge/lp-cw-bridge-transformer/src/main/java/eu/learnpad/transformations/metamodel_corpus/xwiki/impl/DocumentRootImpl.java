/**
 */
package eu.learnpad.transformations.metamodel_corpus.xwiki.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
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

import eu.learnpad.transformations.metamodel_corpus.xwiki.Attachment;
import eu.learnpad.transformations.metamodel_corpus.xwiki.AttachmentsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.ClassesType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Comment;
import eu.learnpad.transformations.metamodel_corpus.xwiki.CommentsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.DocumentRoot;
import eu.learnpad.transformations.metamodel_corpus.xwiki.HistoryType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectSummary;
import eu.learnpad.transformations.metamodel_corpus.xwiki.ObjectsType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Page;
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
import eu.learnpad.transformations.metamodel_corpus.xwiki.Translations;
import eu.learnpad.transformations.metamodel_corpus.xwiki.Wiki;
import eu.learnpad.transformations.metamodel_corpus.xwiki.WikisType;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XWiki;
import eu.learnpad.transformations.metamodel_corpus.xwiki.XwikiPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getAttachment <em>Attachment</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getAttachments <em>Attachments</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getHistory <em>History</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getObject <em>Object</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getObjects <em>Objects</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getObjectSummary <em>Object Summary</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getPage <em>Page</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getPages <em>Pages</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getSearchResult <em>Search Result</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getSearchResults <em>Search Results</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getSpace <em>Space</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getSpaces <em>Spaces</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getSyntaxes <em>Syntaxes</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getTags <em>Tags</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getTranslations <em>Translations</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getWiki <em>Wiki</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getWikis <em>Wikis</em>}</li>
 *   <li>{@link eu.learnpad.transformations.metamodel_corpus.xwiki.impl.DocumentRootImpl#getXwiki <em>Xwiki</em>}</li>
 * </ul>
 * </p>
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
		return XwikiPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, XwikiPackage.DOCUMENT_ROOT__MIXED);
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
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XwikiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
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
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XwikiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Attachment> getAttachment() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__ATTACHMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AttachmentsType> getAttachments() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__ATTACHMENTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<eu.learnpad.transformations.metamodel_corpus.xwiki.Class> getClass_() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__CLASS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ClassesType> getClasses() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__CLASSES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Comment> getComment() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__COMMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CommentsType> getComments() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__COMMENTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HistoryType> getHistory() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__HISTORY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<eu.learnpad.transformations.metamodel_corpus.xwiki.Object> getObject() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectsType> getObjects() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__OBJECTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectSummary> getObjectSummary() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__OBJECT_SUMMARY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Page> getPage() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__PAGE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PagesType> getPages() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__PAGES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertiesType> getProperties() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProperty() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__PROPERTY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SearchResult> getSearchResult() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__SEARCH_RESULT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SearchResultsType> getSearchResults() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__SEARCH_RESULTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Space> getSpace() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__SPACE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpacesType> getSpaces() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__SPACES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Syntaxes> getSyntaxes() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__SYNTAXES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tag> getTag() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__TAG);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TagsType> getTags() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__TAGS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Translations> getTranslations() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__TRANSLATIONS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Wiki> getWiki() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__WIKI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WikisType> getWikis() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__WIKIS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<XWiki> getXwiki() {
		return getMixed().list(XwikiPackage.Literals.DOCUMENT_ROOT__XWIKI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XwikiPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENT:
				return ((InternalEList<?>)getAttachment()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENTS:
				return ((InternalEList<?>)getAttachments()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__CLASS:
				return ((InternalEList<?>)getClass_()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__CLASSES:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__COMMENT:
				return ((InternalEList<?>)getComment()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__COMMENTS:
				return ((InternalEList<?>)getComments()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__HISTORY:
				return ((InternalEList<?>)getHistory()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__OBJECT:
				return ((InternalEList<?>)getObject()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__OBJECTS:
				return ((InternalEList<?>)getObjects()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__OBJECT_SUMMARY:
				return ((InternalEList<?>)getObjectSummary()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__PAGE:
				return ((InternalEList<?>)getPage()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__PAGES:
				return ((InternalEList<?>)getPages()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULT:
				return ((InternalEList<?>)getSearchResult()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULTS:
				return ((InternalEList<?>)getSearchResults()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__SPACE:
				return ((InternalEList<?>)getSpace()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__SPACES:
				return ((InternalEList<?>)getSpaces()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__SYNTAXES:
				return ((InternalEList<?>)getSyntaxes()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__TAG:
				return ((InternalEList<?>)getTag()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__TAGS:
				return ((InternalEList<?>)getTags()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__TRANSLATIONS:
				return ((InternalEList<?>)getTranslations()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__WIKI:
				return ((InternalEList<?>)getWiki()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__WIKIS:
				return ((InternalEList<?>)getWikis()).basicRemove(otherEnd, msgs);
			case XwikiPackage.DOCUMENT_ROOT__XWIKI:
				return ((InternalEList<?>)getXwiki()).basicRemove(otherEnd, msgs);
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
			case XwikiPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case XwikiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case XwikiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENT:
				return getAttachment();
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENTS:
				return getAttachments();
			case XwikiPackage.DOCUMENT_ROOT__CLASS:
				return getClass_();
			case XwikiPackage.DOCUMENT_ROOT__CLASSES:
				return getClasses();
			case XwikiPackage.DOCUMENT_ROOT__COMMENT:
				return getComment();
			case XwikiPackage.DOCUMENT_ROOT__COMMENTS:
				return getComments();
			case XwikiPackage.DOCUMENT_ROOT__HISTORY:
				return getHistory();
			case XwikiPackage.DOCUMENT_ROOT__OBJECT:
				return getObject();
			case XwikiPackage.DOCUMENT_ROOT__OBJECTS:
				return getObjects();
			case XwikiPackage.DOCUMENT_ROOT__OBJECT_SUMMARY:
				return getObjectSummary();
			case XwikiPackage.DOCUMENT_ROOT__PAGE:
				return getPage();
			case XwikiPackage.DOCUMENT_ROOT__PAGES:
				return getPages();
			case XwikiPackage.DOCUMENT_ROOT__PROPERTIES:
				return getProperties();
			case XwikiPackage.DOCUMENT_ROOT__PROPERTY:
				return getProperty();
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULT:
				return getSearchResult();
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULTS:
				return getSearchResults();
			case XwikiPackage.DOCUMENT_ROOT__SPACE:
				return getSpace();
			case XwikiPackage.DOCUMENT_ROOT__SPACES:
				return getSpaces();
			case XwikiPackage.DOCUMENT_ROOT__SYNTAXES:
				return getSyntaxes();
			case XwikiPackage.DOCUMENT_ROOT__TAG:
				return getTag();
			case XwikiPackage.DOCUMENT_ROOT__TAGS:
				return getTags();
			case XwikiPackage.DOCUMENT_ROOT__TRANSLATIONS:
				return getTranslations();
			case XwikiPackage.DOCUMENT_ROOT__WIKI:
				return getWiki();
			case XwikiPackage.DOCUMENT_ROOT__WIKIS:
				return getWikis();
			case XwikiPackage.DOCUMENT_ROOT__XWIKI:
				return getXwiki();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case XwikiPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENT:
				getAttachment().clear();
				getAttachment().addAll((Collection<? extends Attachment>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENTS:
				getAttachments().clear();
				getAttachments().addAll((Collection<? extends AttachmentsType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__CLASS:
				getClass_().clear();
				getClass_().addAll((Collection<? extends eu.learnpad.transformations.metamodel_corpus.xwiki.Class>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends ClassesType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__COMMENT:
				getComment().clear();
				getComment().addAll((Collection<? extends Comment>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection<? extends CommentsType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__HISTORY:
				getHistory().clear();
				getHistory().addAll((Collection<? extends HistoryType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__OBJECT:
				getObject().clear();
				getObject().addAll((Collection<? extends eu.learnpad.transformations.metamodel_corpus.xwiki.Object>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__OBJECTS:
				getObjects().clear();
				getObjects().addAll((Collection<? extends ObjectsType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__OBJECT_SUMMARY:
				getObjectSummary().clear();
				getObjectSummary().addAll((Collection<? extends ObjectSummary>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__PAGE:
				getPage().clear();
				getPage().addAll((Collection<? extends Page>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__PAGES:
				getPages().clear();
				getPages().addAll((Collection<? extends PagesType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends PropertiesType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends Property>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULT:
				getSearchResult().clear();
				getSearchResult().addAll((Collection<? extends SearchResult>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULTS:
				getSearchResults().clear();
				getSearchResults().addAll((Collection<? extends SearchResultsType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__SPACE:
				getSpace().clear();
				getSpace().addAll((Collection<? extends Space>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__SPACES:
				getSpaces().clear();
				getSpaces().addAll((Collection<? extends SpacesType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__SYNTAXES:
				getSyntaxes().clear();
				getSyntaxes().addAll((Collection<? extends Syntaxes>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__TAG:
				getTag().clear();
				getTag().addAll((Collection<? extends Tag>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__TAGS:
				getTags().clear();
				getTags().addAll((Collection<? extends TagsType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__TRANSLATIONS:
				getTranslations().clear();
				getTranslations().addAll((Collection<? extends Translations>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__WIKI:
				getWiki().clear();
				getWiki().addAll((Collection<? extends Wiki>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__WIKIS:
				getWikis().clear();
				getWikis().addAll((Collection<? extends WikisType>)newValue);
				return;
			case XwikiPackage.DOCUMENT_ROOT__XWIKI:
				getXwiki().clear();
				getXwiki().addAll((Collection<? extends XWiki>)newValue);
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
			case XwikiPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENT:
				getAttachment().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENTS:
				getAttachments().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__CLASS:
				getClass_().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__CLASSES:
				getClasses().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__COMMENT:
				getComment().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__COMMENTS:
				getComments().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__HISTORY:
				getHistory().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__OBJECT:
				getObject().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__OBJECTS:
				getObjects().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__OBJECT_SUMMARY:
				getObjectSummary().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__PAGE:
				getPage().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__PAGES:
				getPages().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__PROPERTIES:
				getProperties().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__PROPERTY:
				getProperty().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULT:
				getSearchResult().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULTS:
				getSearchResults().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__SPACE:
				getSpace().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__SPACES:
				getSpaces().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__SYNTAXES:
				getSyntaxes().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__TAG:
				getTag().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__TAGS:
				getTags().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__TRANSLATIONS:
				getTranslations().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__WIKI:
				getWiki().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__WIKIS:
				getWikis().clear();
				return;
			case XwikiPackage.DOCUMENT_ROOT__XWIKI:
				getXwiki().clear();
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
			case XwikiPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENT:
				return !getAttachment().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__ATTACHMENTS:
				return !getAttachments().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__CLASS:
				return !getClass_().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__CLASSES:
				return !getClasses().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__COMMENT:
				return !getComment().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__COMMENTS:
				return !getComments().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__HISTORY:
				return !getHistory().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__OBJECT:
				return !getObject().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__OBJECTS:
				return !getObjects().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__OBJECT_SUMMARY:
				return !getObjectSummary().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__PAGE:
				return !getPage().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__PAGES:
				return !getPages().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__PROPERTIES:
				return !getProperties().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__PROPERTY:
				return !getProperty().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULT:
				return !getSearchResult().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__SEARCH_RESULTS:
				return !getSearchResults().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__SPACE:
				return !getSpace().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__SPACES:
				return !getSpaces().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__SYNTAXES:
				return !getSyntaxes().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__TAG:
				return !getTag().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__TAGS:
				return !getTags().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__TRANSLATIONS:
				return !getTranslations().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__WIKI:
				return !getWiki().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__WIKIS:
				return !getWikis().isEmpty();
			case XwikiPackage.DOCUMENT_ROOT__XWIKI:
				return !getXwiki().isEmpty();
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
