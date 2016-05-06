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
package eu.learnpad.sim.rest.event;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public abstract class AbstractEvent implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4734413104214101930L;

	public EventType type;
	public Long timestamp;
	public String simulationsessionid;
	public List<String> involvedusers;
	public String modelsetid;
	public Map<String, Object> simulationSessionData;

	public AbstractEvent() {
		super();
	}

	public AbstractEvent(EventType type, Long timeStamp, String simulationsessionid, List<String> involvedusers,
			String modelsetid, Map<String, Object> simulationSessionData) {
		super();
		this.type = type;
		this.timestamp = timeStamp;
		this.simulationsessionid = simulationsessionid;
		this.involvedusers = involvedusers;
		this.modelsetid = modelsetid;
		this.simulationSessionData = simulationSessionData;
	}

	@Override
	public String toString() {
		return "Event: " + "type=" + type + " timestamp=" + timestamp + " simulationsessionid=" + simulationsessionid
				+ " involvedusers=" + involvedusers + " modelsetid=" + modelsetid + " simulationSessionData="
				+ simulationSessionData;
	}
}