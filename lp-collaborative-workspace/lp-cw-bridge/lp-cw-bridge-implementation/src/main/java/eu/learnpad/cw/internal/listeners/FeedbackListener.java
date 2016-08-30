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
import org.xwiki.observation.event.Event;

import eu.learnpad.cw.internal.actuators.FeedbackActuator;

/**
 * @author gulyx
 * 
 **/

@Component
@Named("cw-feedback-event-listener")
@Singleton
public class FeedbackListener extends SimpleEventListener{
	/**
	 * Hint of the component.
	 */
	public static final String ROLEHINT = "cw-feedback-event-listener";

	public FeedbackListener() {
		this.name = FeedbackListener.ROLEHINT;
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
	@Override
	public void onEvent(Event event, Object source, Object data) {
		 FeedbackActuator actuator = new FeedbackActuator(this.cwBridge, this.logger);
		 actuator.configureEvent(event, source, data);		 
		 new Thread(actuator).start();
	}

}
