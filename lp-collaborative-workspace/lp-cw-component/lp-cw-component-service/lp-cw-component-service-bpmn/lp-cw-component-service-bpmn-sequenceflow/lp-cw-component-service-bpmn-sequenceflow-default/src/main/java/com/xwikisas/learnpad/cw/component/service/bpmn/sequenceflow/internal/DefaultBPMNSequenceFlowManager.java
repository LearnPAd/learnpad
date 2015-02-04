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
package eu.learnpad.cw.service.bpmn.sequenceflow.internal;

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
import eu.learnpad.cw.service.bpmn.sequenceflow.BPMNSequenceFlowManager;
import eu.learnpad.cw.service.bpmn.sequenceflow.BPMNSequenceFlowManagerException;

@Component
@Singleton
public class DefaultBPMNSequenceFlowManager implements BPMNSequenceFlowManager {
	private static final String SEQUENCEFLOW_CLASSNAME = "LearnPAdCode.SequenceFlowClass";
	private static final String SEQUENCEFLOW_PROPERTYNAME_ID = "id";
	private static final String SEQUENCEFLOW_PROPERTYNAME_NAME = "name";
	private static final String SEQUENCEFLOW_PROPERTYNAME_SOURCEREF = "sourceRef";
	private static final String SEQUENCEFLOW_PROPERTYNAME_TARGETREF = "targetRef";

	@Inject
	private Provider<XWikiContext> xcontextProvider;

	@Inject
	private DocumentReferenceResolver<String> documentReferenceResolver;

	@Inject
	private QueryManager queryManager;

	private BaseObject getSequenceFlowObject(XWikiDocument document) {
		DocumentReference flowNodeClassReference = documentReferenceResolver
				.resolve(SEQUENCEFLOW_CLASSNAME);
		BaseObject flowNodeObject = document.getXObject(flowNodeClassReference);
		return flowNodeObject;
	}

	private DocumentReference getSequenceFlowReference(String id)
			throws BPMNSequenceFlowManagerException {
		String queryString = String.format("where doc.object(%s).id = '%s'",
				SEQUENCEFLOW_CLASSNAME, id);
		Query query;
		List<String> results;
		try {
			query = queryManager.createQuery(queryString, Query.XWQL);
			results = query.execute();
		} catch (QueryException e) {
			throw new BPMNSequenceFlowManagerException(
					"Error in querying the source document.", e);
		}
		DocumentReference documentReference = null;
		switch (results.size()) {
		case 0:
			throw new BPMNSequenceFlowManagerException(
					"Can not find the URL of the source document.");
		case 1:
			documentReference = documentReferenceResolver.resolve(results
					.get(0));
			break;
		default:
			throw new BPMNSequenceFlowManagerException(
					"Multiple source document has been found.");
		}
		return documentReference;
	}

	private XWikiDocument getSequenceFlowDocument(String id)
			throws BPMNSequenceFlowManagerException {
		DocumentReference documentReference = this.getSequenceFlowReference(id);
		XWikiContext xcontext = xcontextProvider.get();
		XWiki xwiki = xcontext.getWiki();
		XWikiDocument document;
		try {
			document = xwiki.getDocument(documentReference, xcontext);
		} catch (XWikiException e) {
			String message = String.format(
					"Cannot retrieve the document '%s'.",
					documentReference.toString());
			throw new BPMNSequenceFlowManagerException(message, e);
		}
		return document;
	}

	@Override
	public String getCurrent() throws BPMNSequenceFlowManagerException {
		XWikiDocument currentDocument = this.xcontextProvider.get().getDoc();
		BaseObject flowNodeObject = this.getSequenceFlowObject(currentDocument);
		return flowNodeObject.getStringValue(SEQUENCEFLOW_PROPERTYNAME_ID);
	}

	@Override
	public String getName() throws BPMNSequenceFlowManagerException {
		XWikiDocument document = this.xcontextProvider.get().getDoc();
		BaseObject flowNodeObject = this.getSequenceFlowObject(document);
		return flowNodeObject.getStringValue(SEQUENCEFLOW_PROPERTYNAME_NAME);
	}

	@Override
	public String getName(String id) throws BPMNSequenceFlowManagerException {
		XWikiDocument document = this.getSequenceFlowDocument(id);
		BaseObject flowNodeObject = this.getSequenceFlowObject(document);
		return flowNodeObject.getStringValue(SEQUENCEFLOW_PROPERTYNAME_NAME);
	}

	private String getRef(XWikiDocument document, String type)
			throws BPMNSequenceFlowManagerException {
		BaseObject sequenceFlowObject = this.getSequenceFlowObject(document);
		String id = sequenceFlowObject.getStringValue(type);
		return id;
	}

	@Override
	public String getSourceRef() throws BPMNSequenceFlowManagerException {
		XWikiDocument document = this.xcontextProvider.get().getDoc();
		String ref = this.getRef(document, SEQUENCEFLOW_PROPERTYNAME_SOURCEREF);
		return ref;
	}

	@Override
	public String getSourceRef(String id)
			throws BPMNSequenceFlowManagerException {
		XWikiDocument document = this.getSequenceFlowDocument(id);
		String ref = this.getRef(document, SEQUENCEFLOW_PROPERTYNAME_SOURCEREF);
		return ref;
	}

	@Override
	public String getTargetRef() throws BPMNSequenceFlowManagerException {
		XWikiDocument document = this.xcontextProvider.get().getDoc();
		String ref = this.getRef(document, SEQUENCEFLOW_PROPERTYNAME_TARGETREF);
		return ref;
	}

	@Override
	public String getTargetRef(String id)
			throws BPMNSequenceFlowManagerException {
		XWikiDocument document = this.getSequenceFlowDocument(id);
		String ref = this.getRef(document, SEQUENCEFLOW_PROPERTYNAME_TARGETREF);
		return ref;
	}
}
