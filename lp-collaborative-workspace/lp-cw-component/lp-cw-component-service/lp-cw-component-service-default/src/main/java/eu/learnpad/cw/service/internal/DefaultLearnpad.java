/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package eu.learnpad.cw.service.internal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.DocumentReferenceResolver;
import org.xwiki.query.Query;
import org.xwiki.query.QueryException;
import org.xwiki.query.QueryManager;

import com.xpn.xwiki.XWiki;
import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;
import com.xpn.xwiki.web.ViewAction;

import eu.learnpad.cw.service.Learnpad;
import eu.learnpad.cw.service.LearnpadException;

@Component
@Singleton
public class DefaultLearnpad implements Learnpad {
	public static final String BASEELEMENT_CLASSNAME = "LearnPAdCode.BaseElementClass";
	public static final String BASEELEMENT_PROPERTYNAME_ID = "id";
	public static final String BASEELEMENT_PROPERTYNAME_NAME = "name";
	public static final String BASEELEMENT_PROPERTYNAME_DOCUMENTATION = "documentation";
	public static final String LINK_CLASSNAME = "LearnPAdCode.LinkClass";
	public static final String LINK_PROPERTYNAME_URI = "uri";
	public static final String LINK_PROPERTYNAME_TYPE = "type";
	public static final String LINK_PROPERTYNAME_TYPE_INCOMING = "incoming";
	public static final String LINK_PROPERTYNAME_TYPE_OUTGOING = "outgoing";

	@Inject
	private Provider<XWikiContext> xcontextProvider;

	@Inject
	private DocumentReferenceResolver<String> documentReferenceResolver;

	@Inject
	private QueryManager queryManager;

	private DocumentReference getBaseElementReference(String id)
			throws LearnpadException {
		String queryString = String.format("where doc.object(%s).id = '%s'",
				BASEELEMENT_CLASSNAME, id);
		Query query;
		List<String> results;
		try {
			query = queryManager.createQuery(queryString, Query.XWQL);
			results = query.execute();
		} catch (QueryException e) {
			throw new LearnpadException(
					"Error in querying the source document.", e);
		}
		DocumentReference documentReference = null;
		String message = null;
		switch (results.size()) {
		case 0:
			message = String.format("Can not find FlowNode with id='%s'.", id);
			throw new LearnpadException(message);
		case 1:
			documentReference = documentReferenceResolver.resolve(results
					.get(0));
			break;
		default:
			message = String.format(
					"Multiple FlowNode with id='%s' has been found.", id);
			throw new LearnpadException(message);
		}
		return documentReference;
	}

	private XWikiDocument getBaseElementDocument(String id)
			throws LearnpadException {
		DocumentReference documentReference = this.getBaseElementReference(id);
		XWikiContext xcontext = xcontextProvider.get();
		XWiki xwiki = xcontext.getWiki();
		XWikiDocument document;
		try {
			document = xwiki.getDocument(documentReference, xcontext);
		} catch (XWikiException e) {
			String message = String.format(
					"Cannot retrieve the document '%s'.",
					documentReference.toString());
			throw new LearnpadException(message, e);
		}
		return document;
	}

	private BaseObject getBaseElementObject(XWikiDocument document)
			throws LearnpadException {
		DocumentReference flowNodeClassReference = documentReferenceResolver
				.resolve(BASEELEMENT_CLASSNAME);
		BaseObject flowNodeObject = document.getXObject(flowNodeClassReference);
		return flowNodeObject;
	}

	private String getName(XWikiDocument document)
			throws LearnpadException {
		String name = null;
		BaseObject flowNodeObject = this.getBaseElementObject(document);
		if (flowNodeObject != null) {
			name = flowNodeObject.getStringValue(BASEELEMENT_PROPERTYNAME_NAME);
		}
		return name;
	}

	@Override
	public String getName(String id) throws LearnpadException {
		XWikiDocument document = this.getBaseElementDocument(id);
		String name = this.getName(document);
		return name;
	}

	private String getDocumentation(XWikiDocument document)
			throws LearnpadException {
		String documentation = null;
		BaseObject flowNodeObject = this.getBaseElementObject(document);
		if (flowNodeObject != null) {
			documentation = flowNodeObject
					.getStringValue(BASEELEMENT_PROPERTYNAME_DOCUMENTATION);
		}
		return documentation;
	}

	@Override
	public String getDocumentation(String id) throws LearnpadException {
		XWikiDocument document = this.getBaseElementDocument(id);
		String documentation = this.getDocumentation(document);
		return documentation;
	}

	@Override
	public String getURL(String id) throws LearnpadException {
		XWikiContext xcontext = xcontextProvider.get();
		XWikiDocument document = this.getBaseElementDocument(id);
		String url = document.getURL(ViewAction.VIEW_ACTION, xcontext);
		return url;
	}

	private List<BaseObject> getLinks(XWikiDocument document) {
		DocumentReference LinkClassReference = documentReferenceResolver
				.resolve(LINK_CLASSNAME);
		List<BaseObject> linkObjects = document.getXObjects(LinkClassReference);
		return linkObjects;
	}

	private List<String> getLinks(XWikiDocument document, String type)
			throws LearnpadException {
		List<String> incomings = new ArrayList<String>();
		List<BaseObject> linkObjects = this.getLinks(document);
		for (BaseObject linkObject : linkObjects) {
			String linkType = linkObject.getStringValue(LINK_PROPERTYNAME_TYPE);
			if (linkType.equals(type)) {
				incomings.add(linkObject.getStringValue(LINK_PROPERTYNAME_URI));
			}
		}
		return incomings;
	}

	@Override
	public List<String> getIncomings(String id) throws LearnpadException {
		XWikiDocument document = this.getBaseElementDocument(id);
		return this.getLinks(document, LINK_PROPERTYNAME_TYPE_INCOMING);
	}

	@Override
	public List<String> getOutgoings(String id) throws LearnpadException {
		XWikiDocument document = this.getBaseElementDocument(id);
		return this.getLinks(document, LINK_PROPERTYNAME_TYPE_OUTGOING);
	}
}
