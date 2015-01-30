package com.xwikisas.learnpad.cw.component.service.bpmn.flownode.internal;

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
		String id = this.getId(currentDocument);
		return id;
	}

	@Override
	public String getId(String documentName) throws BPMNFlowNodeManagerException {
		DocumentReference documentReference = documentReferenceResolver
				.resolve(documentName);
		XWikiDocument document;
		try {
			document = this.xcontextProvider.get().getWiki().getDocument(documentReference,
					this.xcontextProvider.get());
		} catch (XWikiException e) {
			throw new BPMNFlowNodeManagerException("Failed to retrieve the document.", e);
		}
		String id = this.getId(document);
		return id;
	}

	private String getId(XWikiDocument document) {
		BaseObject flowNodeObject = this.getObject(document);
		String id = flowNodeObject.getStringValue(FLOWNODE_PROPERTYNAME_ID);
		return id;
	}

	@Override
	public String getName() throws BPMNFlowNodeManagerException  {
		XWikiDocument currentDocument = this.xcontextProvider.get().getDoc();
		BaseObject flowNodeObject = this.getObject(currentDocument);
		return flowNodeObject.getStringValue(FLOWNODE_PROPERTYNAME_NAME);
	}
}
