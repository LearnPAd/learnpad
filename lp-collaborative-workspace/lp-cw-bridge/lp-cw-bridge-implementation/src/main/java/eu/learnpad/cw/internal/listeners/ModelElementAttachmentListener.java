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
import org.xwiki.observation.event.Event;

import com.xpn.xwiki.internal.event.AttachmentAddedEvent;
import com.xpn.xwiki.internal.event.AttachmentEventGeneratorListener;

import eu.learnpad.cw.UICWBridge;
import eu.learnpad.cw.internal.actuators.ModelElementAbstractActuator;
import eu.learnpad.cw.internal.actuators.ModelElementAttachmentActuator;
import eu.learnpad.cw.internal.utils.LPCodeLabels;

/**
 * @author gulyx
 * 
 **/

@Component
@Named("cw-model-element-attchment-event-listener")
@Singleton
public class ModelElementAttachmentListener extends AttachmentEventGeneratorListener implements Initializable {

	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "cw-model-element-attchment-event-listener";

	@Inject
	@Named("eu.learnpad.cw.internal.CWXwikiBridge")
	protected UICWBridge cwBridge;

	@Inject
	protected  Logger logger;

	private String name;
	private List<Event> events;

	public ModelElementAttachmentListener() {
		this.name = ModelElementAttachmentListener.ROLEHINT;
/*
 * In the current version the CW only monitors the
 * attachments uploaded in the platform. No reaction is
 * foreseen for others kind of events (e.g. delete, modify).
 * In future this may change, so this implementation can
 * be simplified by removing the attribute "this.events"
 * 
 */
		this.events = new ArrayList<Event>();
		this.events.add(new AttachmentAddedEvent());		
	}
	
	@Override
	public void initialize() throws InitializationException {
	}

	@Override
	public void onEvent(Event event, Object source, Object data) {
		 ModelElementAbstractActuator actuator = new ModelElementAttachmentActuator(this.cwBridge, this.logger);
		 actuator.configureEvent(event, source, data);
		 new Thread(actuator).start();
	}

	@Override
	public String getName(){
		return this.name;
	}
	
	@Override
	public List<Event> getEvents() {
		return this.events;
	}

}
