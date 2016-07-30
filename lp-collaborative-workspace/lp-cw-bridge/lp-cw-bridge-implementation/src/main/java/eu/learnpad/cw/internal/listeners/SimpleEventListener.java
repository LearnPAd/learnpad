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

import org.slf4j.Logger;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.phase.Initializable;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.internal.utils.LPCodeLabels;

/**
 * @author gulyx
 * 
 * This code is based on this tutorial : http://extensions.xwiki.org/xwiki/bin/view/Extension/Observation+Module+Local
 **/

@Component
@Named("cw-simple-event-listener")
public abstract class SimpleEventListener implements EventListener, Initializable{

	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "cw-simple-event-listener";

	/**
	 * The key under which the last encountered error is stored in the current
	 * execution context.
	 */
	protected static final String LEARNPADERROR_KEY = "simplelistener.learnpad.error";
			
	protected String name;	
	protected List<Event> event;
	
	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	protected UICWBridge cwBridge;

	@Inject
	protected  Logger logger;
	
	public SimpleEventListener() {
		this.name = SimpleEventListener.ROLEHINT;
		this.event = new ArrayList<Event>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void onEvent(Event event, Object source, Object data) {
		XWikiDocument editedDocument = (XWikiDocument) source;
		XWikiContext xcontext = (XWikiContext) data;

		EntityReference classReference = this.targetEntityReference(event,
				editedDocument, xcontext);
		BaseObject xObject = editedDocument.getXObject(classReference);
		if (xObject != null) {
			String userId = xcontext.getUserReference().getName();
			String resourceId = this.forgeResourceID(event, editedDocument,
					xcontext);

			String modelSetId = xObject.getStringValue(LPCodeLabels
					.getMODELSETID_LABEL());
			String modelId = xObject.getStringValue(LPCodeLabels
					.getMODELID_LABEL());
			String artifactId = xObject.getStringValue(LPCodeLabels
					.getARTIFACTID_LABEL());

			logger.info("found : " + modelSetId +","+ modelId +","+ artifactId +","+ userId);
			this.notifyCWBridge(modelSetId, modelId, artifactId, resourceId,
					userId, event);
		}
	}

	protected abstract EntityReference targetEntityReference(Event event, XWikiDocument doc, XWikiContext xcontext);

	protected abstract String forgeResourceID(Event event, XWikiDocument doc, XWikiContext xcontext);

	protected abstract void notifyCWBridge(String modelSetId, String modelId, String artifactId, String resourceId, String userId, Event event);

	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Event> getEvents() {
		return this.event;
	}

}
