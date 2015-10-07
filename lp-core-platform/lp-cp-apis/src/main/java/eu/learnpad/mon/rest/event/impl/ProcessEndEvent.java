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
package eu.learnpad.mon.rest.event.impl;

import eu.learnpad.mon.rest.event.AbstractEvent;
import eu.learnpad.mon.rest.event.EventType;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public class ProcessEndEvent extends AbstractEvent {

	/**
	 * Unique ID of the process instance
	 */
	public String processid;

	public ProcessEndEvent() {
		super();
	}

	public ProcessEndEvent(Long timestamp, String processid) {
		super(timestamp);
		this.processid = processid;
	}

	@Override
	public EventType getType() {
		return EventType.PROCESS_END;
	}

}
