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
package com.xwikisas.learnpad.cw.component.service.bpmn.flownode.internal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.DocumentReferenceResolver;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;
import com.xwikisas.learnpad.cw.component.service.bpmn.flownode.BPMNFlowNodeManager;
import com.xwikisas.learnpad.cw.component.service.bpmn.flownode.BPMNFlowNodeManagerException;

@Component
@Singleton
public class DefaultBPMNFlowNodeManager implements BPMNFlowNodeManager {
	private static final String FLOWNODE_CLASSNAME = "LearnPAdCode.FlowNodeClass";
	private static final String FLOWNODE_PROPERTYNAME_ID = "id";
	private static final String FLOWNODE_PROPERTYNAME_NAME = "name";
	private static final String LINK_CLASSNAME = "LearnPAdCode.LinkClass";
	private static final String LINK_PROPERTYNAME_URI = "uri";
	private static final String LINK_PROPERTYNAME_TYPE = "type";
	private static final String LINK_PROPERTYNAME_TYPE_INCOMING = "incoming";
	private static final String LINK_PROPERTYNAME_TYPE_OUTGOING = "outgoing";

	@Inject
	private Provider<XWikiContext> xcontextProvider;

	@Inject
	private DocumentReferenceResolver<String> documentReferenceResolver;

	private BaseObject getCurrentObject() {
		return this.getObject(this.xcontextProvider.get().getDoc());
	}

	private BaseObject getObject(XWikiDocument document) {
		DocumentReference flowNodeClassReference = documentReferenceResolver
				.resolve(FLOWNODE_CLASSNAME);
		BaseObject flowNodeObject = document.getXObject(flowNodeClassReference);
		return flowNodeObject;
	}

	@Override
	public String getId() throws BPMNFlowNodeManagerException {
		XWikiDocument currentDocument = this.xcontextProvider.get().getDoc();
		BaseObject flowNodeObject = this.getObject(currentDocument);
		String id = flowNodeObject.getStringValue(FLOWNODE_PROPERTYNAME_ID);
		return id;
	}

	@Override
	public String getName() throws BPMNFlowNodeManagerException {
		XWikiDocument currentDocument = this.xcontextProvider.get().getDoc();
		BaseObject flowNodeObject = this.getObject(currentDocument);
		return flowNodeObject.getStringValue(FLOWNODE_PROPERTYNAME_NAME);
	}

	private List<BaseObject> getLinks() {
		XWikiDocument currentDocument = this.xcontextProvider.get().getDoc();
		DocumentReference LinkClassReference = documentReferenceResolver
				.resolve(LINK_CLASSNAME);
		List<BaseObject> linkObjects = currentDocument
				.getXObjects(LinkClassReference);
		return linkObjects;
	}

	public List<String> getLinks(String type)
			throws BPMNFlowNodeManagerException {
		List<String> incomings = new ArrayList<String>();
		List<BaseObject> linkObjects = this.getLinks();
		for (BaseObject linkObject : linkObjects) {
			String linkType = linkObject.getStringValue(LINK_PROPERTYNAME_TYPE);
			if (linkType.equals(type)) {
				incomings.add(linkObject.getStringValue(LINK_PROPERTYNAME_URI));
			}
		}
		return incomings;
	}

	@Override
	public List<String> getIncoming() throws BPMNFlowNodeManagerException {
		return this.getLinks(LINK_PROPERTYNAME_TYPE_INCOMING);
	}

	@Override
	public List<String> getOutgoing() throws BPMNFlowNodeManagerException {
		return this.getLinks(LINK_PROPERTYNAME_TYPE_OUTGOING);
	}
}
