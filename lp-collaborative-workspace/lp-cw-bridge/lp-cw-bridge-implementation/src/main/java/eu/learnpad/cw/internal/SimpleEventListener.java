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
package eu.learnpad.cw.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.xwiki.bridge.event.DocumentCreatedEvent;
import org.xwiki.bridge.event.DocumentUpdatedEvent;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.phase.Initializable;
import org.xwiki.component.phase.InitializationException;
import org.xwiki.model.reference.DocumentReference;
import org.xwiki.model.reference.EntityReference;
import org.xwiki.observation.EventListener;
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.XWikiContext;
import com.xpn.xwiki.XWikiException;
import com.xpn.xwiki.doc.XWikiDocument;
import com.xpn.xwiki.objects.BaseObject;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.exception.LpRestException;

/**
 * @author gulyx
 * 
 * This code is based on this tutorial : http://extensions.xwiki.org/xwiki/bin/view/Extension/Observation+Module+Local
 **/

@Component
@Named("cw-simple-event-listener")
@Singleton
public class SimpleEventListener implements EventListener, Initializable{
//public class SimpleEventListener extends AbstractEventListener{

	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "cw-simple-event-listener";

	/**
	 * The key under which the last encountered error is stored in the current
	 * execution context.
	 */
	private static final String LEARNPADERROR_KEY = "simplelistener.learnpad.error";

	
	private String name;
	
	private final String CLASSES_SPACE = "LPCode";
	private final String COLLABORATIVE_DOCUMENT_CLASS = "CollaborativeDocumentClass";
	private final String FEEDBACK_CLASS = "FeedbackClass";
	private final String MODEL_CLASS = "ModelClass";
	private final String BASE_ELEMENT_CLASS = "BaseElementClass";
	
	private final String MODELSETID_LABEL = "modelsetid";
	private final String MODELID_LABEL = "modelid";
	private final String ARTIFACTID_LABEL = "artifactid";
		
	private List<Event> event;
	
	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	private UICWBridge cwBridge;

	@Inject
	private Logger logger;
	
//	/**
//	 * The observation manager that will be use to fire user creation events.
//	 * Note: We can't have the OM as a requirement, since it would create an
//	 * infinite initialization loop, causing a stack overflow error (this event
//	 * listener would require an initialized OM and the OM requires a list of
//	 * initialized event listeners)
//	 */
//	private ObservationManager observationManager;

	public SimpleEventListener() throws ComponentLookupException {
//		super("this-is-foo", new DocumentCreatedEvent());		
		this.name = ROLEHINT;
		this.event = new ArrayList<Event>();
	}

	@Override
	public void initialize() throws InitializationException {
		this.event.add(new DocumentCreatedEvent());		
		this.event.add(new DocumentUpdatedEvent());
		logger.info("Created listener : " + this.name);
	}

	/**
	 * {@inheritDoc}
	 */
	public void onEvent(Event event, Object source, Object data) {
		XWikiDocument editedDocument = (XWikiDocument) source;
		XWikiContext xcontext = (XWikiContext) data;
		
		String message;
		if (editedDocument != null){
			message = "Notified : " + editedDocument.getId() + ", titled : " + editedDocument.getTitle() + ", created on : " + editedDocument.getCreationDate().toString();
		}else{
			message = "******** Document was null *************";			
		}
		logger.info(message);

		
		DocumentReference classReference = new DocumentReference(xcontext.getMainXWiki(),this.CLASSES_SPACE,this.COLLABORATIVE_DOCUMENT_CLASS);
		List<BaseObject> xObjects = editedDocument.getXObjects(classReference);
		for (BaseObject xObject : xObjects) {
			String modelSetId = xObject.getStringValue(this.MODELSETID_LABEL);
			String resourceId = xObject.getStringValue(this.MODELID_LABEL);
			String relatedArtifactId = xObject.getStringValue(this.ARTIFACTID_LABEL);
			
			String userId = xcontext.getUserReference().getName();
			logger.info(modelSetId + resourceId + relatedArtifactId + userId);
//			try {
//			this.cwBridge.pageNotification(modelSetId, resourceId, relatedArtifactId, userId, "action");
//		} catch (LpRestException e) {
//			logger.error(e.getMessage(), e.getCause());
//		}
		}
		
//		String wikiName = document.getDocumentReference().getWikiReference()
//				.getName();
//		DocumentReference userClass = new DocumentReference(wikiName, "XWiki",
//				"XWikiUsers");
//
//		if (document.getXObject(userClass) != null) {
//			// Create a map to hold our new event data
//			Map<String, String> userData = new HashMap<String, String>();
//			userData.put("firstName", document.getXObject(userClass)
//					.getStringValue("firstName"));
//			userData.put("lastName", document.getXObject(userClass)
//					.getStringValue("lastName"));
//			userData.put("email", document.getXObject(userClass)
//					.getStringValue("email"));
//			// Fire the user created event
//			UserCreatedEvent newEvent = new UserCreationEvent();
//			getObservationManager().notify(newEvent, source, userData);
//		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Event> getEvents() {
		return this.event;
	}

//	private ObservationManager getObservationManager() {
//		if (this.observationManager == null) {
//			try {
//				this.observationManager = componentManager
//						.getInstance(ObservationManager.class);
//			} catch (ComponentLookupException e) {
//				throw new RuntimeException(
//						"Cound not retrieve an Observation Manager against the component manager");
//			}
//		}
//		return this.observationManager;
//	}

}
