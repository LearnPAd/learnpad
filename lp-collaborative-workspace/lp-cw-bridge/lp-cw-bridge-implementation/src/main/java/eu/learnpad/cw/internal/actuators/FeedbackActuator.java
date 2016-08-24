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
package eu.learnpad.cw.internal.actuators;

import org.slf4j.Logger;
import org.xwiki.bridge.event.DocumentCreatedEvent;
import org.xwiki.bridge.event.DocumentUpdatedEvent;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.EntityReference;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.internal.utils.LPCodeLabels;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;

/**
 * @author gulyx
 * 
 **/

public class FeedbackActuator extends SimpleEventActuator{
	
	public FeedbackActuator(UICWBridge cwBridge) {			
		this(cwBridge,null);
	}

	public FeedbackActuator(UICWBridge cwBridge, Logger logger) {			
	  super(cwBridge, logger);
	}
	
	@Override
	protected String forgeResourceID(String optionalLabel) {
		String resourceId = this.editedDocument.getDocumentReference().toString();
		return resourceId;
	}

	@Override
	protected EntityReference targetEntityReference(String optionalLabel) {
		EntityReference reference = new DocumentReference(this.xcontext.getMainXWiki(),LPCodeLabels.getCLASSES_SPACE(),LPCodeLabels.getFEEDBACK_CLASS());
		return reference;
	}

	@Override
	protected void notifyCWBridge(String modelSetId, String modelId,
			String artifactId, String resourceId, String userId) {
		try {
			if (this.event instanceof DocumentUpdatedEvent) {
				this.cwBridge.feedbackNotification(modelSetId, modelId, artifactId,
						resourceId, NotificationActionType.MODIFIED.toString(),
						userId);
			} else if (this.event instanceof DocumentCreatedEvent) {
				this.cwBridge.feedbackNotification(modelSetId, modelId, artifactId,
						resourceId, NotificationActionType.ADDED.toString(),
						userId);
			}
		} catch (LpRestException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}
}
