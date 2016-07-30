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

import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.bridge.event.DocumentCreatedEvent;
import org.xwiki.bridge.event.DocumentUpdatedEvent;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.doc.XWikiDocument;

import eu.learnpad.cw.internal.utils.LPCodeLabels;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;

/**
 * @author gulyx
 * 
 **/

@Component
@Named("cw-cd-event-listener")
@Singleton
public class CollaborativeDocumentListener extends SimpleEventListener{
	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "cw-cd-event-listener";

	public CollaborativeDocumentListener() {
		this.name = CollaborativeDocumentListener.ROLEHINT;
	}

	@Override
	public void initialize() throws InitializationException {
		this.event.add(new DocumentCreatedEvent());		
		this.event.add(new DocumentUpdatedEvent());
		logger.info("Created listener : " + this.name);
	}

	@Override
	protected String forgeResourceID(Event event, XWikiDocument doc, XWikiContext xcontext) {
		String resourceId = doc.getDocumentReference().toString();
		return resourceId;
	}

	@Override
	protected EntityReference targetEntityReference(Event event, XWikiDocument doc, XWikiContext xcontext) {
		EntityReference reference = new DocumentReference(xcontext.getMainXWiki(),LPCodeLabels.getCLASSES_SPACE(),LPCodeLabels.getCOLLABORATIVE_DOCUMENT_CLASS());
		return reference;
	}

	@Override
	protected void notifyCWBridge(String modelSetId, String modelId,
			String artifactId, String resourceId, String userId, Event event) {
		try {
			if (event instanceof DocumentUpdatedEvent) {
				this.cwBridge.pageNotification(modelSetId, modelId, artifactId,
						resourceId, NotificationActionType.MODIFIED.toString(),
						userId);
			} else if (event instanceof DocumentCreatedEvent) {
				this.cwBridge.pageNotification(modelSetId, modelId, artifactId,
						resourceId, NotificationActionType.ADDED.toString(),
						userId);
			}
		} catch (LpRestException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}

}
