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
package eu.learnpad.simulator.monitoring.event;

/*
 * #%L
 * LearnPAd Simulator
 * %%
 * Copyright (C) 2014 - 2015 Linagora
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.util.Collection;

/**
 *
 * @author Tom Jorquera - Linagora
 *
 */
public abstract class AbstractSimEvent {

	public Long timestamp;
	public String simulationsessionid;
	public Collection<String> involvedusers;

	public AbstractSimEvent() {
		super();
	}

	public AbstractSimEvent(Long timeStamp, String simulationsessionid,
			Collection<String> involvedusers) {
		super();
		this.timestamp = timeStamp;
		this.simulationsessionid = simulationsessionid;
		this.involvedusers = involvedusers;
	}

	public abstract SimEventType getType();

	@Override
	public String toString() {
		return "Event: " + getType() + " " + timestamp + " "
				+ simulationsessionid + " " + involvedusers;
	}

}
