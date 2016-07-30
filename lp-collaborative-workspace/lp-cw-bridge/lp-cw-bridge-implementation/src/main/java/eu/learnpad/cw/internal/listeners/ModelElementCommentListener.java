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
package eu.learnpad.cw.internal.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.internal.event.CommentAddedEvent;
import com.xpn.xwiki.internal.event.CommentEventGeneratorListener;
import com.xpn.xwiki.objects.BaseObject;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.internal.utils.LPCodeLabels;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;

/**
 * @author gulyx
 * 
 **/

@Component
@Named("cw-model-element-comment-event-listener")
@Singleton
public class ModelElementCommentListener extends CommentEventGeneratorListener
		implements Initializable {

	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "cw-model-element-comment-event-listener";

	private final String COMMENT_LABEL = "#xwikicomment_";

	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	protected UICWBridge cwBridge;

	@Inject
	protected Logger logger;

	private String name;
	private List<Event> events;
	List<String> referenceLabelList;

	public ModelElementCommentListener() {
		this.name = ModelElementCommentListener.ROLEHINT;
		this.referenceLabelList = new ArrayList<String>();
		/*
		 * In the current version the CW only monitors the new comments posted
		 * in the platform. No reaction is foreseen for others kind of events
		 * (e.g. delete, modify). In future this may change, so this
		 * implementation can be simplified by removing the attribute
		 * "this.events"
		 */
		this.events = new ArrayList<Event>();
		this.events.add(new CommentAddedEvent());
	}

	@Override
	public void initialize() throws InitializationException {
		this.referenceLabelList.add(LPCodeLabels.getBASE_ELEMENT_CLASS());
		this.referenceLabelList.add(LPCodeLabels.getMODEL_CLASS());
		this.referenceLabelList.add(LPCodeLabels.getMODELSET_CLASS());
	}

	protected String forgeResourceID(Event event, XWikiDocument doc,
			XWikiContext xcontext) {
		CommentAddedEvent castedEvent = (CommentAddedEvent) event;
		String resourceId = doc.getDocumentReference().toString()
				+ this.COMMENT_LABEL + castedEvent.getIdentifier();

		return resourceId;
	}

	@Override
	public void onEvent(Event event, Object source, Object data) {
		XWikiDocument doc = (XWikiDocument) source;
		XWikiContext xcontext = (XWikiContext) data;

		for (String referenceLabel : this.referenceLabelList) {
			EntityReference reference = new DocumentReference(xcontext.getMainXWiki(),LPCodeLabels.getCLASSES_SPACE(),referenceLabel);
			BaseObject xObject = doc.getXObject(reference);
			if (xObject != null) {
				String userId = xcontext.getUserReference().getName();
				String resourceId = this.forgeResourceID(event, doc, xcontext);

				String modelSetId = xObject.getStringValue(LPCodeLabels
						.getMODELSETID_LABEL(referenceLabel));
				String modelId = xObject.getStringValue(LPCodeLabels
						.getMODELID_LABEL(referenceLabel));
				String artifactId = xObject.getStringValue(LPCodeLabels
						.getARTIFACTID_LABEL(referenceLabel));

				logger.info(modelSetId + modelId + artifactId + userId);
				try {
					this.cwBridge.commentNotification(modelSetId, modelId,
							artifactId, resourceId,
							NotificationActionType.ADDED.toString(), userId);
					logger.info("found : " + modelSetId + "," + modelId + ","
							+ artifactId + "," + userId);
				} catch (LpRestException e) {
					logger.error(e.getMessage(), e.getCause());
				}
			}
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Event> getEvents() {
		return this.events;
	}

}
