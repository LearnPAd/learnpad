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
import org.slf4j.LoggerFactory;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.internal.utils.LPCodeLabels;

/**
 * @author gulyx
 * 
 **/

public abstract class SimpleEventActuator implements Runnable {
			
	protected UICWBridge cwBridge;

	protected  Logger logger;
	
	protected boolean isConfigured;
	
	protected Event event; 
	protected XWikiDocument editedDocument;
	protected XWikiContext xcontext;
	
	public SimpleEventActuator(UICWBridge cwBridge) {
		this(cwBridge, null);
	}

	public SimpleEventActuator(UICWBridge cwBridge, Logger logger) {			
		this.cwBridge = cwBridge;
		if (logger != null){
			this.logger = logger;
		}else{
			this.logger = LoggerFactory.getLogger(SimpleEventActuator.class);
		}
		
		this.isConfigured = false;
		this.editedDocument = null;
		this.xcontext = null;
		this.event = null;
	}
	
	protected abstract EntityReference targetEntityReference(String optionalLabel);

	protected abstract String forgeResourceID(String optionalLabel);

	protected abstract void notifyCWBridge(String modelSetId, String modelId, String artifactId, String resourceId, String userId);

	public void configureEvent(Event event, Object source, Object data) {
		this.event = event;
		this.editedDocument = (XWikiDocument) source;
		this.xcontext = (XWikiContext) data;

		this.isConfigured = true;
	}

	protected void processEvent() {
		EntityReference classReference = this.targetEntityReference(null);
		BaseObject xObject = editedDocument.getXObject(classReference);
		if (xObject != null) {
			String userId = xcontext.getUserReference().getName();
			String resourceId = this.forgeResourceID(null);

			String modelSetId = xObject.getStringValue(LPCodeLabels
					.getMODELSETID_LABEL());
			String modelId = xObject.getStringValue(LPCodeLabels
					.getMODELID_LABEL());
			String artifactId = xObject.getStringValue(LPCodeLabels
					.getARTIFACTID_LABEL());

			logger.info("found : " + modelSetId +","+ modelId +","+ artifactId +","+ userId);
			this.notifyCWBridge(modelSetId, modelId, artifactId, resourceId, userId);
		}			
	}
	
	@Override
	public synchronized void run() {
		if (this.isConfigured){
			this.isConfigured = false;
			this.processEvent();
		}else{
			logger.warn("the method \"configureEvent(Event event, Object source, Object data)\" must be invoked before each activation of the thread!!!");
		}
	}
}
