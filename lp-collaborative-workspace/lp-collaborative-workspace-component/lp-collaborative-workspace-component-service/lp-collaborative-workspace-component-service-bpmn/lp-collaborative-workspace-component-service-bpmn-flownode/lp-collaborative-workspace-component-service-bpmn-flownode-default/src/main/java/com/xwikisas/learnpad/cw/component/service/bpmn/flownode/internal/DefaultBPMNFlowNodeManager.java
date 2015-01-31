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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.DocumentReferenceResolver;

import com.xpn.xwiki.XWiki;
import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;
import com.xwikisas.learnpad.cw.component.service.bpmn.flownode.BPMNFlowNodeManager;
import com.xwikisas.learnpad.cw.component.service.bpmn.flownode.BPMNFlowNodeManagerException;

@Component
@Singleton
public class DefaultBPMNFlowNodeManager implements BPMNFlowNodeManager {
	private String FLOWNODE_CLASSNAME = "LearnPAdCode.FlowNodeClass";
	private String FLOWNODE_PROPERTYNAME_ID = "id";
	private String FLOWNODE_PROPERTYNAME_NAME = "name";
	@Inject
	private Provider<XWikiContext> xcontextProvider;

	@Inject
	private DocumentReferenceResolver<String> documentReferenceResolver;



	private BaseObject getCurrentObject() {
		return this.getObject(this.xcontextProvider.get().getDoc());
	}

	private BaseObject getObject(XWikiDocument document) {
		DocumentReference flowNodeClassReference = documentReferenceResolver
				.resolve(this.FLOWNODE_CLASSNAME);
		BaseObject flowNodeObject = document.getXObject(flowNodeClassReference);
		return flowNodeObject;
	}

	@Override
	public String getId() throws BPMNFlowNodeManagerException  {
		XWikiDocument currentDocument = this.xcontextProvider.get().getDoc();
		BaseObject flowNodeObject = this.getObject(currentDocument);
		String id = flowNodeObject.getStringValue(FLOWNODE_PROPERTYNAME_ID);
		return id;
	}

	@Override
	public String getName() throws BPMNFlowNodeManagerException  {
		XWikiDocument currentDocument = this.xcontextProvider.get().getDoc();
		BaseObject flowNodeObject = this.getObject(currentDocument);
		return flowNodeObject.getStringValue(FLOWNODE_PROPERTYNAME_NAME);
	}

	@Override
	public List<String> getIncoming() throws BPMNFlowNodeManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getOutgoing() throws BPMNFlowNodeManagerException {
		// TODO Auto-generated method stub
		return null;
	}
}
