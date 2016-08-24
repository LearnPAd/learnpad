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
import com.xpn.xwiki.internal.event.AttachmentAddedEvent;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.exception.LpRestException;
import eu.learnpad.or.rest.data.NotificationActionType;

/**
 * @author gulyx
 * 
 **/

public class ModelElementAttachmentActuator extends ModelElementAbstractActuator{
	
	private final String ATTECHMENT_LABEL = "#attachment_";
	
	public ModelElementAttachmentActuator(UICWBridge cwBridge) {			
		this(cwBridge,null);
	}

	public ModelElementAttachmentActuator(UICWBridge cwBridge, Logger logger) {			
		super(cwBridge, logger);
	}

	@Override
	protected String forgeResourceID(String optionalLabel) {
		AttachmentAddedEvent castedEvent = (AttachmentAddedEvent) this.event;
		String resourceId = this.editedDocument.getDocumentReference().toString() + this.ATTECHMENT_LABEL + castedEvent.getName();;
		
		return resourceId;
	}

	@Override
	protected void notifyCWBridge(String modelSetId, String modelId,
			String artifactId, String resourceId, String userId) {
		try {

			this.cwBridge.attachmentNotification(modelSetId, modelId, artifactId, resourceId, NotificationActionType.ADDED.toString(), userId);
			logger.info("found : " + modelSetId +","+ modelId +","+ artifactId +","+ userId);
		} catch (LpRestException e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}
}
